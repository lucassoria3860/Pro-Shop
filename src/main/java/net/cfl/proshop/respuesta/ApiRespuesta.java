package net.cfl.proshop.respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiRespuesta {
 	private String mensage;
	private Object data;
}
