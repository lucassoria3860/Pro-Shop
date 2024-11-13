package net.cfl.proshop.servicio.orden;

import java.util.List;

import net.cfl.proshop.modelo.Orden;

public interface IOrdenServicio {
	Orden realizarOrden(Long usuarioId);
	Orden traeOrden(Long ordenId);
	List<Orden> traeUsuarioOrdenes(Long usuarioId);
}
