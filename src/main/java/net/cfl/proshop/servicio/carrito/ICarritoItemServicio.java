package net.cfl.proshop.servicio.carrito;

import net.cfl.proshop.modelo.CarritoItem;

public interface ICarritoItemServicio {
	void agregaItemAlCarrito(Long carritoId, Long productoId, int cantidad);
	void quitaItemDelCarrito(Long id, Long productoId);
	void actualizaCantidadItem(Long carritoId, Long productoId, int cantidad);
	CarritoItem traeCarritoItem(Long carritoId, Long productoId);
	
	
}
