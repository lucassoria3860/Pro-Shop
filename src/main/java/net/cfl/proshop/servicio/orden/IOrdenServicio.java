package net.cfl.proshop.servicio.orden;

import java.util.List;

import net.cfl.proshop.dto.OrdenDto;
import net.cfl.proshop.modelo.Orden;

public interface IOrdenServicio {
	Orden realizarOrden(Long usuarioId);
	OrdenDto traeOrden(Long ordenId);
	List<OrdenDto> traeUsuarioOrdenes(Long usuarioId);
}
