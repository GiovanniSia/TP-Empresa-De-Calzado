package dto;

public class ClienteDTO {
	
	int IdCliente;
	String Nombre;
	String Apellido;
	String CUIL;
	String CorreoElectronico;
	double LimiteCredito;
	double CreditoDisponible;
	String TipoCliente;
	String ImpuestoAFIP;
	String Estado;
	String Calle;
	String Altura;
	String Pais;
	String Provincia;
	String Localidad;
	String CodPostal;


	public ClienteDTO(int idCliente, String nombre, String apellido,String CUIL, String correoElectronico, double limiteCredito,
			double creditoDisponible, String tipoCliente, String impuestoAFIP, String estado, String calle, String altura,
			String pais, String provincia, String localidad, String codPostal) {

		this.IdCliente = idCliente;
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.CUIL = CUIL;
		this.CorreoElectronico = correoElectronico;
		this.LimiteCredito = limiteCredito;
		this.CreditoDisponible = creditoDisponible;
		this.TipoCliente = tipoCliente;
		this.ImpuestoAFIP = impuestoAFIP;
		this.Estado = estado;
		this.Calle = calle;
		this.Altura = altura;
		this.Pais = pais;
		this.Provincia = provincia;
		this.Localidad = localidad;
		this.CodPostal = codPostal;
	}
	
	
	
	
	
	public int getIdCliente() {
		return IdCliente;
	}


	public String getNombre() {
		return Nombre;
	}


	public String getApellido() {
		return Apellido;
	}
	
	public String getCUIL() {
		return CUIL;
	}


	public String getCorreo() {
		return CorreoElectronico;
	}


	public double getLimiteCredito() {
		return LimiteCredito;
	}


	public double getCreditoDisponible() {
		return CreditoDisponible;
	}


	public String getTipoCliente() {
		return TipoCliente;
	}


	public String getImpuestoAFIP() {
		return ImpuestoAFIP;
	}


	public String getEstado() {
		return Estado;
	}


	public String getCalle() {
		return Calle;
	}


	public String getAltura() {
		return Altura;
	}


	public String getPais() {
		return Pais;
	}


	public String getProvincia() {
		return Provincia;
	}


	public String getLocalidad() {
		return Localidad;
	}


	public String getCodPostal() {
		return CodPostal;
	}	
	
	public String getCorreoElectronico() {
		return CorreoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		CorreoElectronico = correoElectronico;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public void setLimiteCredito(double limiteCredito) {
		LimiteCredito = limiteCredito;
	}

	public void setCreditoDisponible(double creditoDisponible) {
		CreditoDisponible = creditoDisponible;
	}

	public void setTipoCliente(String tipoCliente) {
		TipoCliente = tipoCliente;
	}

	public void setImpuestoAFIP(String impuestoAFIP) {
		ImpuestoAFIP = impuestoAFIP;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public void setCalle(String calle) {
		Calle = calle;
	}
	public void setAltura(String altura) {
		Altura = altura;
	}

	public void setPais(String pais) {
		Pais = pais;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public void setCodPostal(String codPostal) {
		CodPostal = codPostal;
	}


}
