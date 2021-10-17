package dto;

public class ProveedorDTO {

	int id;
	String nombre;
	String correo;
	double limiteCredito;
	double creditoDisponible;
	
	public ProveedorDTO(int id, String nombre, String correo, double limiteCredito, double creditoDisponible) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.limiteCredito = limiteCredito;
		this.creditoDisponible = creditoDisponible;
	}
	
	
	public int getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}



	public String getCorreo() {
		return correo;
	}



	public double getLimiteCredito() {
		return limiteCredito;
	}



	public double getCreditoDisponible() {
		return creditoDisponible;
	}
	
}
