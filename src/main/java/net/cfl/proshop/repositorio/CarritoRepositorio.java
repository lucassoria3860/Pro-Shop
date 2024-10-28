package net.cfl.proshop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.proshop.modelo.Carrito;

public interface CarritoRepositorio extends JpaRepository<Carrito, Long>{
	
}
