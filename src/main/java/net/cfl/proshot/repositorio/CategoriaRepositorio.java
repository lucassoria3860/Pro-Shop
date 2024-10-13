package net.cfl.proshot.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.proshot.modelo.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
	Categoria findByAtCategoria(String nombre);
}
