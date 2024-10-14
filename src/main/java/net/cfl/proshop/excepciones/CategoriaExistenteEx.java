package net.cfl.proshop.excepciones;

public class CategoriaExistenteEx extends RuntimeException {
	
	public CategoriaExistenteEx(String mensaje) {
		super(mensaje);
	}
}
