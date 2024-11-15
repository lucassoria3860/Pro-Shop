package net.cfl.proshop.request;

import lombok.Data;

@Data
public class AgregaUsuarioReq {
	private String usuarioNombre;
	private String usuarioApellido;
	private String email;
	private String pwd;
}
