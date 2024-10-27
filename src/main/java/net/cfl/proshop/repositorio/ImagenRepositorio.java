package net.cfl.proshop.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.proshop.modelo.Imagen;

public interface ImagenRepositorio extends JpaRepository<Imagen, Long> {
	List<Imagen> findByProductoId(Long id);
}
