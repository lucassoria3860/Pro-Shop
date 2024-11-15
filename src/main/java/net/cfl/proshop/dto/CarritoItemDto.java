package net.cfl.proshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CarritoItemDto {
	private Long CarritoItemId;
	private Integer cantidad;
	private BigDecimal precioUnitario;
	private ProductoDto producto;
}
