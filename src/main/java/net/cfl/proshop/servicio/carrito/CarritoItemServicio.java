package net.cfl.proshop.servicio.carrito;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.proshop.modelo.Carrito;
import net.cfl.proshop.modelo.CarritoItem;
import net.cfl.proshop.modelo.Producto;
import net.cfl.proshop.repositorio.CarritoItemRepositorio;
import net.cfl.proshop.repositorio.CarritoRepositorio;
import net.cfl.proshop.servicio.producto.IProductoServicio;

@RequiredArgsConstructor
@Service
public class CarritoItemServicio implements ICarritoItemServicio {

	private final CarritoItemRepositorio carritoItemRepositorio;
	private final CarritoRepositorio carritoRepositorio;
	private final IProductoServicio productoServicio;
	private final ICarritoServicio carritoServicio;
	
	@Override
	public void agregaItemAlCarrito(Long carritoId, Long productoId, int cantidad) {
		//1: Obtener carrito
		//2: Obtener el producto
		//3: Verificar si el producto existe en el carrito
		//4: si existe, establecer la cantidad con la cantidad requerida
		//5: Si No existe, iniciar el ingreso del item
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		Producto producto =productoServicio.listarProductoPorId(productoId);
		CarritoItem carritoItem = carrito.getCarritoItems()
				.stream()
				.filter(item -> item.getProducto().getId().equals(productoId))
				.findFirst().orElse(new CarritoItem());
		if(carritoItem.getId() == null) {
			carritoItem.setCarrito(carrito);
			carritoItem.setProducto(producto);
			carritoItem.setCantidad(cantidad);
			carritoItem.setPrecioUni(producto.getPrecio());
		}else {
			carritoItem.setCantidad(carritoItem.getCantidad() + cantidad);
		}
		carritoItem.setPrecioTot();
		carritoItem.agregaItem(carritoItem);
		carritoItemRepositorio.save(carritoItem);
		carritoRepositorio.save(carrito);
	
	}

	@Override
	public void quitaItemDelCarrito(Long id, Long productoId) {
		Carrito carrito = 
		carrito.quitarItem(itemARemover);
		carritoRepositorio.save(carrito);
		
	}

	@Override
	public void actualizaCantidadItem(Long carritoId, Long productoId, int cantidad) {
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		carrito.getCarritoItems().stream().filter(item -> item.getProducto().getId().equals(productoId))
		.findFirst().ifPresent(item -> {
			item.setCantidad(cantidad);
			item.setPrecioUni(item.getProducto().getPrecio());
			item.setPrecioTot();
			
		});;
		BigDecimal montototal = carrito.getCostoTotal();
		carrito.setCostoTotal(montototal);
		carritoRepositorio.save(carrito);
	}
	@Override
	public CarritoItem traeCarritoItem(Long carritoId, Long  productoId) {
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		return carritoServicio.traeCarrito(carritoId);
		CarritoItem itemARemover = carrito.getCarritoItems()
				.stream()
				.filter(item -> item.getProducto().getId().equals(productoId))
				.findFirst()
				.orElseThrow(() -> new RecursoNoEncontradoEx("Producto no encontrado"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
