package net.cfl.proshop.controlador;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.proshop.modelo.Carrito;
import net.cfl.proshop.respuesta.ApiRespuesta;
import net.cfl.proshop.servicio.carrito.ICarritoServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carritos")
public class CarritoControlador {
	private final ICarritoServicio carritoServicio;
	
	@GetMapping("/{carritoId}/carritos")
	public ResponseEntity<ApiRespuesta> traeCarrito(@PathVariable Long carritoId){
		try {
			Carrito carrito = carritoServicio.traeCarrito(carritoId);
			return ResponseEntity.ok(new ApiRespuesta("Exito", carrito));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/{carritoId}/limpiar")
	public ResponseEntity<ApiRespuesta> limpiaCarrito(@PathVariable Long carritoId){
		try {
			carritoServicio.LimpiaCarrito(carritoId);
			return ResponseEntity.ok(new ApiRespuesta ("Exito", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@GetMapping("/{carritoId}/carrito/monto-total")
	public ResponseEntity<ApiRespuesta> calcularMontoTotal(@PathVariable Long carritoId){
		try {
			BigDecimal montoTotal = carritoServicio.traePrecioTotal(carritoId);
			return ResponseEntity.ok(new ApiRespuesta("Monto Total: ", montoTotal));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta (e.getMessage(), null));
		}
	}
	
	
	
	
	
	
	
	
	
	
}
