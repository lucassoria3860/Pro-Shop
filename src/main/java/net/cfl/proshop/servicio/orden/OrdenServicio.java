package net.cfl.proshop.servicio.orden;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.cfl.proshop.dto.OrdenDto;
import net.cfl.proshop.enums.OrdenEstado;
import net.cfl.proshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.proshop.modelo.Carrito;
import net.cfl.proshop.modelo.Orden;
import net.cfl.proshop.modelo.OrdenItem;
import net.cfl.proshop.modelo.Producto;
import net.cfl.proshop.repositorio.OrdenRepositorio;
import net.cfl.proshop.repositorio.ProductoRepositorio;
import net.cfl.proshop.servicio.carrito.ICarritoServicio;

@Service
@RequiredArgsConstructor
public class OrdenServicio implements IOrdenServicio {
	
	private final OrdenRepositorio ordenRepositorio;
	private final ProductoRepositorio productoRepositorio;
	private final ICarritoServicio carritoServicio;
	private final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public Orden realizarOrden(Long usuarioId) {
		Carrito carrito = carritoServicio.traeCarritoPorUsuarioId(usuarioId);
		Orden orden = crearOrden(carrito);
		List<OrdenItem> listaDeItemsOrden = crearOrdenItems(orden, carrito);
		
		orden.setOrdenItems(new HashSet<>(listaDeItemsOrden));
		orden.setMontoTotal(calculaMontoTotal(listaDeItemsOrden));
		Orden ordenGuardada = ordenRepositorio.save(orden);
		carritoServicio.LimpiaCarrito(carrito.getId());
		
		return ordenGuardada;
	}
	
	private Orden crearOrden(Carrito carrito) {
		Orden orden = new Orden();
		orden.setUsuario(carrito.getUsuario());;
		orden.setOrdenEstado(OrdenEstado.PENDIENTE);
		orden.setOrdenFecha(LocalDate.now());
		return orden;
	}
	
	private List<OrdenItem> crearOrdenItems(Orden orden, Carrito carrito){
		return carrito.getCarritoItems().stream().map(carritoItem -> {
			Producto producto = carritoItem.getProducto();
			producto .setStock(producto.getStock() - carritoItem.getCantidad());
			productoRepositorio.save(producto);
			return new OrdenItem(
					orden, 
					producto, 
					carritoItem.getCantidad(), 
					carritoItem.getPrecioUni()
				);
		}).toList();
	}
	
	private BigDecimal calculaMontoTotal(List<OrdenItem> listaDeItems) {
		
		return listaDeItems
					.stream()
					.map(item -> item.getPrecio()
							.multiply(new BigDecimal(item.getCantidad())))
					.reduce(BigDecimal.ZERO, BigDecimal :: add);
	}
	

	@Override
	public OrdenDto traeOrden(Long ordenId) {
		return ordenRepositorio.findById(ordenId)
					.map(this :: convertirAOrdenDto)
					.orElseThrow(() -> new RecursoNoEncontradoEx("Orden no encontrado"));
	}
	
	@Override
	public List<OrdenDto> traeUsuarioOrdenes(Long usuarioId){
		List<Orden> ordenes = ordenRepositorio.findByUsuarioId(usuarioId);
		return ordenes.stream().map(this :: convertirAOrdenDto).toList();
	}
	
	
	private OrdenDto convertirAOrdenDto(Orden orden) {
		return modelMapper.map(orden, OrdenDto.class);
	}
	
	
	
	
	
	
	
	
	
	

}
