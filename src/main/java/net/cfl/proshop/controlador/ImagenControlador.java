package net.cfl.proshop.controlador;

import java.sql.SQLException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.dto.ImagenDto;
import net.cfl.proshop.modelo.Imagen;
import net.cfl.proshop.respuesta.ApiRespuesta;
import net.cfl.proshop.servicio.imagen.IImagenServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/imagenes")
public class ImagenControlador {
	private final IImagenServicio imagenServicio;
	
	// REST API Imagenes
	// api/v1/imagenes
	@PostMapping("/upload")
	public ResponseEntity<ApiRespuesta> guardaImagenes(@RequestBody List<MultipartFile> archivos, @RequestBody Long idProducto){
		try {
			List<ImagenDto> imagenesDto = imagenServicio.guardaImagenes(archivos, idProducto);
			return ResponseEntity.ok(new ApiRespuesta("imagen subida correctamente", imagenesDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiRespuesta("Error al subir imagen", e.getMessage()));
		}
		
	}
	@GetMapping("/imagen/descraga/{imagenId}")
	public ResponseEntity<Resource> descargaImagen( @PathVariable Long imagenId) throws SQLException{
			Imagen imagen = imagenServicio.listaImagenPorId(imagenId);
			ByteArrayResource recurso = new ByteArrayResource(imagen.getImagen().getBytes(1, (int)imagen.getImagen().length()));
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(imagen.getArchivoTipo()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "Attachment: filename=\"" + imagen.getArchivoNombre() + "\"")
					.body(recurso);
		}
	
	@PutMapping("/imagen/{imagenId}/actualiza")
	public ResponseEntity<ApiRespuesta> actualizaImagen(@PathVariable Long imagenId, @RequestBody MultipartFile archivo){
		try {
			Imagen imagen = imagenServicio.listaImagenPorId(imagenId) ;
			if(imagen != null) {
				imagenServicio.actualizarImagen(archivo, imagenId);
				return ResponseEntity.ok(new ApiRespuesta("imagen actualizada", null));
			}
		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null ));
		}
		
			
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiRespuesta("Fallo al actrualizar la imagen", null));
	}
	
	
	@DeleteMapping("/imagen/{imagenId}/borrar")
	public ResponseEntity<ApiRespuesta> borraImagen(@PathVariable Long imagenId){
		try {
			Imagen imagen = imagenServicio.listaImagenPorId(imagenId);
			if(imagen != null) {
				imagenServicio.borraImagenPorId(imagenId);
				return ResponseEntity.ok(new ApiRespuesta("imagen Borrada", null));
			}
		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiRespuesta(e.getMessage(),null ));
		}
		
			
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiRespuesta("Fallo al actrualizar la imagen", null));
	}
}
