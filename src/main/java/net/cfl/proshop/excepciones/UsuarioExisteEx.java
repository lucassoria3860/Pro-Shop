package net.cfl.proshop.excepciones;

public class UsuarioExisteEx extends RuntimeException {
	public UsuarioExisteEx(String mensaje) {
		super(mensaje);
	}
}
