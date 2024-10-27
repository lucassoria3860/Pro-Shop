package net.cfl.proshop.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import net.cfl.proshop.modelo.Categoria;

@Data
public class ProductoDto {

	private Long id;
	private String nombre;
	private String marca;
	private String desccripcion;
	private BigDecimal precio;
	private  int stock;
	private Categoria categoria;
	private List<ImagenDto> imagenes;
}
