package dto;

public class ProveedorDTO {

	int id;
	String nombre;
	String correo;
	double limiteCredito;
	
	public ProveedorDTO(int id, String nombre, String correo, double limiteCredito) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.limiteCredito = limiteCredito;
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

	
}
