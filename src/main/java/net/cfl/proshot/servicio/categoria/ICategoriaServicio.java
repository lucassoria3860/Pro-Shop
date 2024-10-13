package net.cfl.proshot.servicio.categoria;

import java.util.List;

import net.cfl.proshot.modelo.Categoria;

public interface ICategoriaServicio {
	Categoria listaCategoriaPorId(Long id);
	Categoria listaCategoriaPorNombre(String nombre);
	List<Categoria> listarCategorias();
	
	Categoria agregaCategoria(Categoria categoria);
	Categoria actualizaCategoria(Categoria categoria);
	void borrarCategoriaPorId(Long id);
}
