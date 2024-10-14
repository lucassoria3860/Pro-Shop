package net.cfl.proshop.servicio.imagen;

import org.springframework.web.multipart.MultipartFile;

import net.cfl.proshop.modelo.Imagen;

public interface IImagenServicio {
	Imagen listaImagenPorId(Long id);
	void borraImagenPorId(Long id);
	Imagen guardaImagen(MultipartFile archivo, Long id);
	void actualizarImagen(MultipartFile archivo, Long imagenId);
	
}
