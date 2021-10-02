package dto;

public class ClienteDTO {

	int idCliente;
	String nombre;
	String apellido;
	String correo;
	int limiteCredito;
	int creditoDisponible;
	String tipoCliente;
	String impuestoAFIP;
	String estado;
	String calle;
	String altura;
	String pais;
	String provincia;
	String localidad;
	String codPostal;


	public ClienteDTO(int idCliente, String nombre, String apellido, String correo, int limiteCredito,
			int creditoDisponible, String tipoCliente, String impuestoAFIP, String estado, String calle, String altura,
			String pais, String provincia, String localidad, String codPostal) {

		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.limiteCredito = limiteCredito;
		this.creditoDisponible = creditoDisponible;
		this.tipoCliente = tipoCliente;
		this.impuestoAFIP = impuestoAFIP;
		this.estado = estado;
		this.calle = calle;
		this.altura = altura;
		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;
		this.codPostal = codPostal;
	}
	
	
	
	
	
	public int getIdCliente() {
		return idCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public String getCorreo() {
		return correo;
	}


	public int getLimiteCredito() {
		return limiteCredito;
	}


	public int getCreditoDisponible() {
		return creditoDisponible;
	}


	public String getTipoCliente() {
		return tipoCliente;
	}


	public String getImpuestoAFIP() {
		return impuestoAFIP;
	}


	public String getEstado() {
		return estado;
	}


	public String getCalle() {
		return calle;
	}


	public String getAltura() {
		return altura;
	}


	public String getPais() {
		return pais;
	}


	public String getProvincia() {
		return provincia;
	}


	public String getLocalidad() {
		return localidad;
	}


	public String getCodPostal() {
		return codPostal;
	}
}
