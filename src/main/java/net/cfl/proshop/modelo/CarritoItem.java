package net.cfl.proshop.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarritoItem {

	private String producto;
	private int catnidad;
	private BigDecimal presioUni;
	private BigDecimal precioTot;
	private Carrito carrito;
	
}
