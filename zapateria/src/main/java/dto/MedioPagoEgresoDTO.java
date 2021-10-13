package dto;

public class MedioPagoEgresoDTO {
	String idMoneda;
	String descripcion;
	double tasaConversion;

	public MedioPagoEgresoDTO(String idMoneda, String descripcion, double tasaConversion) {
		super();
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
