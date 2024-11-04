package net.cfl.proshop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.proshop.modelo.Orden;

public interface OrdenRepositorio extends JpaRepository<Orden, Long> {

}
