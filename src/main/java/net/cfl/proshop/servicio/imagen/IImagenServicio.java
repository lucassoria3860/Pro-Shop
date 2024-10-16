package net.cfl.proshop.servicio.imagen;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.cfl.proshop.dto.ImagenDto;
import net.cfl.proshop.modelo.Imagen;

public interface IImagenServicio {
	Imagen listaImagenPorId(Long id);
	void borraImagenPorId(Long id);
	List<ImagenDto> guardaImagenes(List<MultipartFile> archivos, Long id);
	void actualizarImagen(MultipartFile archivo, Long imagenId);
	
}
