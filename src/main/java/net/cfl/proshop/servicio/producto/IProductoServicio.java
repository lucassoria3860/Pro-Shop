package net.cfl.proshop.servicio.producto;

import java.util.List;

import net.cfl.proshop.modelo.Producto;
import net.cfl.proshop.request.ActualizaProductoReq;
import net.cfl.proshop.request.AgregaProductoReq;

public interface IProductoServicio {
	
	//crud
	Producto agregarProducto(AgregaProductoReq request);
	Producto listarProductoPorId(Long id);
	
	void borrarProducto(Long id);
	Producto actualizarProducto(ActualizaProductoReq request, Long id);
	
	//Filtros
	List<Producto> listarProductos();
	List<Producto> listarPorCategoria(String categoria);
	List<Producto> listarPorMarca(String marca);
	List<Producto> listarPorMarcaYCategoria(String marca, String categoria);
	List<Producto> listarPorNombre(String nombre);
	List<Producto> listarPorNombreYMarca(String nombre, String marca);
	Long contarProductosPorNombreYMarca(String nombre, String Marca);
}
