package dto;

/*
CREATE TABLE `tipoEgreso`
(					
  `IdTipoEgreso` varchar(45) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`IdTipoEgreso`)
);
*/
public class TipoEgresosDTO {
	String IdTipoEgreso;
	String descripcion;

	public TipoEgresosDTO(String idTipoEgreso, String descripcion) {
		super();
		IdTipoEgreso = idTipoEgreso;
		this.descripcion = descripcion;
	}

	public String getIdTipoEgreso() {
		return IdTipoEgreso;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
