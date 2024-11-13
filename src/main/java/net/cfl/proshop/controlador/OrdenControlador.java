package net.cfl.proshop.controlador;

import net.cfl.proshop.dto.OrdenDto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.proshop.modelo.Orden;
import net.cfl.proshop.respuesta.ApiRespuesta;
import net.cfl.proshop.servicio.categoria.ICategoriaServicio;
import net.cfl.proshop.servicio.orden.IOrdenServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/ordenes")
public class OrdenControlador {
	private final IOrdenServicio ordenServicio;
	
	@PostMapping("/orden")
	public ResponseEntity<ApiRespuesta> creaOrden(@RequestParam Long usuarioId){
		try {
			Orden laOrden = ordenServicio.realizarOrden(usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Orden Agregada con exito ", laOrden));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
									.body(new ApiRespuesta("Se ha producido un error", e.getMessage()));
		}
	}
	
	@GetMapping("/{ordenId}/orden")
	public ResponseEntity<ApiRespuesta> traeOrdenPorId(@PathVariable Long ordenId){
		try {
			OrdenDto laOrden = ordenServicio.traeOrden(ordenId);
			return ResponseEntity.ok(new ApiRespuesta("Exito! ", laOrden));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("oops", e.getMessage()));
		}
	}
	
	@GetMapping("/{usuarioId}/orden")
	public ResponseEntity<ApiRespuesta> traeOrdenUsuario(@PathVariable Long usuarioId){
		try {
			List<OrdenDto> laOrden = ordenServicio.traeUsuarioOrdenes(usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Exito! ", laOrden));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("oops", e.getMessage()));
		}
	}
	
	
	
	
	
}
