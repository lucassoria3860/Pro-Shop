package net.cfl.proshot.servicio.producto;

import java.util.List;

import net.cfl.proshot.modelo.Producto;

public interface IProductoServicio {
	
	Producto agregarProducto(Producto producto);
	Producto listarProductoPorId(Long id);
	
	void borrarProducto(Long id);
	void actualizarProducto(Producto producto, Long id);
	
	List<Producto> listarProductos();
	List<Producto> listarPorCategoria(String categoria);
	List<Producto> listarPorMarca(String marca);
	List<Producto> listarPorMarcaYCategoria(String marca, String categoria);
	List<Producto> listarPorNombre(String nombre);
	List<Producto> listarPorNombreYMarca(String nombre, String marca);
	Long contarProductosPorNombreYMarca(String nombre, String Marca);
}
