package net.cfl.proshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrdenItemDto {
	private Long ProductoId;
	private String ProductoNombre;
	private int cantidad;
	private BigDecimal precio;
}
