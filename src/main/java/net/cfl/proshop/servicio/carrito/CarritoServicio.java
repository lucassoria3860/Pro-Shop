package net.cfl.proshop.servicio.carrito;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.proshop.modelo.Carrito;
import net.cfl.proshop.repositorio.CarritoItemRepositorio;
import net.cfl.proshop.repositorio.CarritoRepositorio;

@Service
@RequiredArgsConstructor
public class CarritoServicio implements ICarritoServicio {

	private final CarritoRepositorio carritoRepositorio;
	private final CarritoItemRepositorio carritoItemRepositorio;
	
	@Override
	public Carrito traeCarrito(Long id) {
		Carrito carrito = carritoRepositorio.findById(id)
					.orElseThrow(() -> new RecursoNoEncontradoEx("Recurso no encontrado"));
		BigDecimal montoTotal = carrito.getCostoTotal();
		carrito.setCostoTotal(montoTotal);
		return carrito;
	}

	@Override
	public void LimpiaCarrito(Long id) {
		Carrito carrito = traeCarrito(id);
		carritoItemRepositorio.deleteAllByCarritoId(id);
		carrito.getCarritoItems().clear();
		carritoRepositorio.deleteById(id);
	}

	@Override
	public BigDecimal traePrecioTotal(Long id) {
		Carrito carrito = traeCarrito(id);
		return carrito.getCostoTotal();
	}
	
	
	
	
	
	
	
	
	

}
