package dto;
/*
CREATE TABLE `medioPago`
(					
  `IdMoneda` varchar(45) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `TasaConversion` double(45,2) NOT NULL,
  PRIMARY KEY (`IdMoneda`)
);
 */

public class MedioPagoDTO {
	String idMoneda;
	String descripcion;
	double tasaConversion;

	public MedioPagoDTO(String idMoneda, String descripcion, double tasaConversion) {
		this.idMoneda = idMoneda;
		this.descripcion = descripcion;
		this.tasaConversion = tasaConversion;
	}

	public String getIdMoneda() {
		return idMoneda;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getTasaConversion() {
		return tasaConversion;
	}

	public void setIdMoneda(String idMoneda) {
		this.idMoneda = idMoneda;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setTasaConversion(double tasaConversion) {
		this.tasaConversion = tasaConversion;
	}

}
