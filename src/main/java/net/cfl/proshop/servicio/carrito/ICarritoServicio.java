package net.cfl.proshop.servicio.carrito;

import java.math.BigDecimal;

import net.cfl.proshop.modelo.Carrito;

public interface ICarritoServicio {
	
	Carrito traeCarrito(Long id);
	void LimpiaCarrito(Long id);
	BigDecimal traePrecioTotal(Long id);
	
}
