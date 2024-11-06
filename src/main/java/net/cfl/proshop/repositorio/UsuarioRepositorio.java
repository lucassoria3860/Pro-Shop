package net.cfl.proshop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.proshop.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
