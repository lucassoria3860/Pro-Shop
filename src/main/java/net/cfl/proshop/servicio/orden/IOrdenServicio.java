package net.cfl.proshop.servicio.orden;

import net.cfl.proshop.modelo.Orden;

public interface IOrdenServicio {
	Orden realizarOrden(Long usuarioId);
	Orden traeOrdem(Long ordenId);
}
