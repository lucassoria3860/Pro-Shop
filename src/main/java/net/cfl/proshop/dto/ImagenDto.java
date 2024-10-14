package net.cfl.proshop.dto;

import java.sql.Blob;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ImagenDto {
	
	private Long id;
	private String archivoNombre;
	private String achivoTipo;
	private Blob imagen;
	private String descargaUrl;
}
