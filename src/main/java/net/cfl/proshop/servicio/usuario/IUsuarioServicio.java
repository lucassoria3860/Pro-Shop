package net.cfl.proshop.servicio.usuario;

import net.cfl.proshop.modelo.Usuario;
import net.cfl.proshop.request.ActualizaUsuarioReq;
import net.cfl.proshop.request.AgragaUsuarioReq;

public interface IUsuarioServicio {
	Usuario traeUsuarioPorId(Long usuarioId);
	Usuario creaUsuario(AgragaUsuarioReq request);
	Usuario actualizaUsuario(ActualizaUsuarioReq request, Long usuarioId);
	void borrarUsuario(Long usuarioId);
}
