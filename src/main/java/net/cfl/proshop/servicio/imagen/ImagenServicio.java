package net.cfl.proshop.servicio.imagen;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.proshop.modelo.Imagen;
import net.cfl.proshop.repositorio.ImagenRepositorio;
import net.cfl.proshop.repositorio.ProductoRepositorio;

@Service
@RequiredArgsConstructor
public class ImagenServicio implements IImagenServicio {
	
	private final ImagenRepositorio imagenRepositorio;
	private final ProductoRepositorio productoRepositorio;
	
	@Override
	public Imagen listaImagenPorId(Long id) {
		return imagenRepositorio.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoEx("no se encontro la imagen con id " + id));
	}

	@Override
	public void borraImagenPorId(Long id) {
		imagenRepositorio.findById(id).ifPresentOrElse(imagenRepositorio::delete, () -> { 
			throw new RecursoNoEncontradoEx("No se encontra la imagen con id " + id);
		});
	}

	@Override
	public Imagen guardaImagen(MultipartFile archivo, Long id) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public void actualizarImagen(MultipartFile archivo, Long imagenId) {
		Imagen imagen = listaImagenPorId(imagenId);
		try {
			imagen.setArchivoNombre(archivo.getOriginalFilename());
			imagen.setImagen(new SerialBlob(archivo.getBytes()));
		}catch(IOException | SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

}






