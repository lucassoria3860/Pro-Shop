package net.cfl.proshop.data;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.modelo.Usuario;
import net.cfl.proshop.repositorio.UsuarioRepositorio;

@Component
@RequiredArgsConstructor
public class InicializadorDatos implements ApplicationListener<ApplicationReadyEvent> {
	private final UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent evento) {
		creaUsuarioPorDefectoSiNoExiste();
	}
	
	private void creaUsuarioPorDefectoSiNoExiste() {
		for(int i = 1; i <5; i++) {
			String emailDefault = "usuario"+i+"@email.com";
			if(usuarioRepositorio.existsByEmail(emailDefault)) {
				continue;
			}
			Usuario usuario = new Usuario();
			usuario.setUsuarioNombre("El usuario");
			usuario.setUsuarioApellido("usuario" + i);
			usuario.setEmail(emailDefault);
			usuario.setPwd("123456");
			usuarioRepositorio.save(usuario);
			System.out.println("usuario " + i + " creados satisfactoriamente");
		}
	}
}
