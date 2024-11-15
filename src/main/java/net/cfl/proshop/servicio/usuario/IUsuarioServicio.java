package net.cfl.proshop.servicio.usuario;

import net.cfl.proshop.modelo.Usuario;
import net.cfl.proshop.request.ActualizaUsuarioReq;
import net.cfl.proshop.request.AgregaUsuarioReq;

public interface IUsuarioServicio {
	Usuario traeUsuarioPorId(Long usuarioId);
	Usuario creaUsuario(AgregaUsuarioReq request);
	Usuario actualizaUsuario(ActualizaUsuarioReq request, Long usuarioId);
	void borrarUsuario(Long usuarioId);
}
