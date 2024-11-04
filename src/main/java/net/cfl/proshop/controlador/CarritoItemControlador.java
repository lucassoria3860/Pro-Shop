package net.cfl.proshop.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.proshop.respuesta.ApiRespuesta;
import net.cfl.proshop.servicio.carrito.ICarritoItemServicio;
import net.cfl.proshop.servicio.carrito.ICarritoServicio;
import net.cfl.proshop.servicio.producto.IProductoServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/items-carrito")
public class CarritoItemControlador {
	private final ICarritoItemServicio carritoItemServicio;
	private final ICarritoServicio carritoServicio;
	
	@PostMapping("/item/agrega")
	public ResponseEntity<ApiRespuesta> agregaItemAlCarrito(@RequestParam(required = false) Long carritoId, 
															@RequestParam Long productoId, 
															@RequestParam Integer cantidad){
		try {
			if(carritoId == null) {
				carritoId = carritoServicio.inicializaCarrito();
			}
			carritoItemServicio.agregaItemAlCarrito(carritoId, productoId, cantidad);
			return ResponseEntity.ok(new ApiRespuesta("Item agregado con exito", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/carrito/{carritoId}/item/{productoId}/quitar")
	public ResponseEntity<ApiRespuesta> quitarItemDelCarrito(@PathVariable Long carritoId,
															 @PathVariable Long ProductoId){
		try {
			carritoItemServicio.quitaItemDelCarrito(carritoId, ProductoId);
			return ResponseEntity.ok(new ApiRespuesta("Item quitado con exito", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null)); 
		}
	}
	@PutMapping("/carrito/{carritoId}/item/{productoId}/actualiza")
	public ResponseEntity<ApiRespuesta> actuaizaCantidadDeItem(@PathVariable Long carritoId, 
															   @PathVariable Long productoId, 
															   @RequestParam Integer cantidad){
		try {
			carritoItemServicio.actualizaCantidadItem(carritoId, productoId, cantidad);
			return ResponseEntity.ok(new ApiRespuesta("Item actualizado con exito", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
