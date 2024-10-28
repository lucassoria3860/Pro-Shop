package net.cfl.proshop.modelo;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int cantidad;
	private BigDecimal precioUni;
	private BigDecimal precioTot;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carrito_id")
	private Carrito carrito;
	
	public void setPrecioTot() {
		this.precioTot = this.precioUni.multiply(new BigDecimal(cantidad));
	}
}
