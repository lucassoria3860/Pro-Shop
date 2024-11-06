package net.cfl.proshop.modelo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal costoTotal = BigDecimal.ZERO;
	
	@OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CarritoItem> carritoItems = new HashSet<>();
	
	private Usuario usuario;
	
	public void agregaItem(CarritoItem item) {
		this.carritoItems.add(item);
		item.setCarrito(this);
		actualizaCostoTotal();
		
	}
	
	public void quitarItem(CarritoItem item) {
		this.carritoItems.remove(item);
		item.setCarrito(this);
		actualizaCostoTotal();
	}
	
	private void actualizaCostoTotal() {
		this.costoTotal = carritoItems
				.stream()
				.map(item ->{
						BigDecimal precioUnitario = item.getPrecioUni();
						if(precioUnitario == null) {
						}
						
						return precioUnitario.multiply(BigDecimal.valueOf(item.getCantidad()));
						
					}).reduce(BigDecimal.ZERO, BigDecimal :: add);
				

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
