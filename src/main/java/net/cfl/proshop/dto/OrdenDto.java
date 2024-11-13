package net.cfl.proshop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import net.cfl.proshop.enums.OrdenEstado;
import net.cfl.proshop.modelo.OrdenItem;

@Data
public class OrdenDto {
	private Long id;
	private Long UsuarioId;
	private LocalDate ordenFecha;
	private BigDecimal montoTotal;
	private String ordenEstado;
	private List<OrdenItemDto> items;
}
