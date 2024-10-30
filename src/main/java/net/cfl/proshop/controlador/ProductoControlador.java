package net.cfl.proshop.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import lombok.RequiredArgsConstructor;
import net.cfl.proshop.dto.ProductoDto;
import net.cfl.proshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.proshop.modelo.Producto;
import net.cfl.proshop.request.ActualizaProductoReq;
import net.cfl.proshop.request.AgregaProductoReq;
import net.cfl.proshop.respuesta.ApiRespuesta;
import net.cfl.proshop.servicio.producto.IProductoServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/productos")
public class ProductoControlador {
	private final IProductoServicio productoServicio;
	
	@GetMapping("/todos")
	public ResponseEntity<ApiRespuesta> listaTodosProductos(){
		List<Producto> productos = productoServicio.listarProductos();
		List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
		return ResponseEntity.ok(new ApiRespuesta("Exito! ", productosConvertidos));
		
	}
	
	@GetMapping("/producto/{productoId}/producto")
	public ResponseEntity<ApiRespuesta> listarProductoPorId(@PathVariable Long productoId){
		try {
			Producto producto = productoServicio.listarProductoPorId(productoId);
			ProductoDto productoConvertido = productoServicio.convertirAProductoDto(producto);
			return ResponseEntity.ok(new ApiRespuesta("Exito! ",productoConvertido));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespuesta(e.getMessage(), null)); 
		}
	}
	
	@PostMapping("/agrega")
	public ResponseEntity<ApiRespuesta> agregaProducto(@RequestBody AgregaProductoReq producto){
		try {
			Producto elProducto = productoServicio.agregarProducto(producto);
			ProductoDto productoConvertido = productoServicio.convertirAProductoDto(elProducto);
			return ResponseEntity.ok(new ApiRespuesta("Producto agregado! ", productoConvertido));
		} catch (Exception e) {
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@PutMapping("/producto/{productoId}/actualiza")
	public ResponseEntity<ApiRespuesta> actualizaProducto(@RequestBody ActualizaProductoReq request, @PathVariable Long idProducto){
		try {
			Producto elProducto = productoServicio.actualizarProducto(request, idProducto);
			ProductoDto productoConvertido = productoServicio.convertirAProductoDto(elProducto);
			return ResponseEntity.ok(new ApiRespuesta("Producto Actualizado! ", productoConvertido));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespuesta(e.getMessage(), null)) ;
		}
	}
	
	@DeleteMapping("/producto/{productoId}/borrar")
	public ResponseEntity<ApiRespuesta> borrarProduto(@PathVariable Long productoId){
		try {
			productoServicio.borrarProducto(productoId);
			return ResponseEntity.ok(new ApiRespuesta("Producto Borrado!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@GetMapping("/por/marca-y-nombre")
	public ResponseEntity<ApiRespuesta> listarPorMarcaYNombre(@RequestParam String marcaNombre, @RequestParam String productoNombre){
		try {
			List<Producto> productos = productoServicio.listarPorNombreYMarca(productoNombre, marcaNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespuesta("No se encontrearon productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito! ", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiRespuesta(e.getMessage(), null)); 
		}
	}
	
	
	@GetMapping("/por/marca-y-categoria")
	public ResponseEntity<ApiRespuesta> listarPorMarcaYCategoria(@RequestParam String marcaNombre, @RequestParam String categoriaNombre){
		try {
			List<Producto> productos = productoServicio.listarPorMarcaYCategoria(marcaNombre, categoriaNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespuesta("No se encontrearon productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito! ", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiRespuesta(e.getMessage(), null)); 
		}
	}
	
	@GetMapping("/producto/{productoNombre}/producto")
	public ResponseEntity<ApiRespuesta> listarPorNombre(@PathVariable String productoNombre){
		try {
			List<Producto> productos = productoServicio.listarPorNombre(productoNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespuesta("No se encontrearon productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito! ", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiRespuesta(e.getMessage(), null)); 
		}
	}
	
	@GetMapping("/por/marca")
	public ResponseEntity<ApiRespuesta> listarPorMarca(@RequestParam String marcaNombre){
		try {
			List<Producto> productos = productoServicio.listarPorMarca(marcaNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespuesta("No se encontrearon productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito! ", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiRespuesta(e.getMessage(), null)); 
		}
	}
	
	@GetMapping("/producto/{productoCategoria}/todos/productos")
	public ResponseEntity<ApiRespuesta> listarPorCategoria(@PathVariable String categoriaNombre){
		try {
			List<Producto> productos = productoServicio.listarPorCategoria(categoriaNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespuesta("No se encontrearon productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito! ", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiRespuesta(e.getMessage(), null)); 
		}
	}
	
	@GetMapping("/producto/cuenta/por/marca-y-nombre")
	public ResponseEntity<ApiRespuesta> contarPorMarcaYNombre(@RequestBody String marcaNombre, @RequestBody String ProductoNombre){
		try {
			//var crea una variable sin especificar el tipo de dato
			//el tipo de variable se define al momento de ingresar el dato
			Long productoCuenta = productoServicio.contarProductosPorNombreYMarca(marcaNombre, ProductoNombre);
			return ResponseEntity.ok(new ApiRespuesta("Exito! ", productoCuenta));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ApiRespuesta(e.getMessage(), null)); 
		}
	}
	
	
}
