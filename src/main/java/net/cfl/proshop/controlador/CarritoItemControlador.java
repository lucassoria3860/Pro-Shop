package net.cfl.proshop.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.proshop.respuesta.ApiRespuesta;
import net.cfl.proshop.servicio.carrito.ICarritoItemServicio;
import net.cfl.proshop.servicio.producto.IProductoServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/productos")
public class CarritoItemControlador {
	private ICarritoItemServicio carritoItemServicio;
	
	@PostMapping("/item/agrega")
	public ResponseEntity<ApiRespuesta> agregaItemAlCarrito(@RequestParam Long carritoId, 
															@RequestParam Long productoId, 
															@RequestParam Integer cantidad){
		try {
			carritoItemServicio.agregaItemAlCarrito(carritoId, productoId, cantidad);
			return ResponseEntity.ok(new ApiRespuesta("Item agregado con exito", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/carrito/{carritoId}/item/{productoId}/quitar")
	public ResponseEntity<ApiRespuesta> quitarItemDelCarrito(@PathVariable Long carritoId,
															 @PathVariable Long ProductId){
		try {
			carritoItemServicio.quitaItemDelCarrito(carritoId, ProductId);
			return ResponseEntity.ok(new ApiRespuesta("Item quitado con exito", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null)); 
		}
	}
	@GetMapping("/carrito/{carritoId}/item/{productoId}/actualiza")
	public ResponseEntity<ApiRespuesta> actuaizaCantidadDeItem(@PathVariable Long carritoId, 
															   @PathVariable Long productoId, 
															   @PathVariable Integer cantidad){
		try {
			carritoItemServicio.actualizaCantidadItem(carritoId, productoId, cantidad);
			return ResponseEntity.ok(new ApiRespuesta("Item actualizado con exito", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
