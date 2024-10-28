package net.cfl.proshop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.proshop.modelo.CarritoItem;

public interface CarritoItemRepositorio extends JpaRepository<CarritoItem, Long> {
	void deleteAllByCarritoId(Long id);
}
