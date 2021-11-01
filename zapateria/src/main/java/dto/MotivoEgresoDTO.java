package dto;

public class MotivoEgresoDTO {
	
	String nroFacturaCompleto;
	String descripcion;
	
	public MotivoEgresoDTO(String nroFacturaCompleto, String descripcion) {
		super();
		this.nroFacturaCompleto = nroFacturaCompleto;
		this.descripcion = descripcion;
	}

	public String getNroFacturaCompleto() {
		return nroFacturaCompleto;
	}

	public void setNroFacturaCompleto(String nroFacturaCompleto) {
		this.nroFacturaCompleto = nroFacturaCompleto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
