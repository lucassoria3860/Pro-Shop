package net.cfl.proshop.modelo;

import java.math.BigDecimal;
import java.util.Set;

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
public class Carrito {

	private Long id;
	private BigDecimal montoTotal;
	private Set item;
	
}
