package dto;

public class RechazoCompraVirtualDTO {
	
	int Id;
	String Hora;
	String Fecha;
	int idSucursal;
	double pago;
	String Nombre;
	String Apellido;
	String CUIL;
	String CorreoElectronico;
	String TipoCliente;
	String ImpuestoAFIP;
	String Estado;
	String Calle;
	String Altura; 
	String Pais;
	String Provincia;
	String Localidad;
	String CodPostal;String motivo;
	public RechazoCompraVirtualDTO(int id, String hora, String fecha, int idSucursal, double pago, String nombre,
			String apellido, String cUIL, String correoElectronico, String tipoCliente, String impuestoAFIP,
			String estado, String calle, String altura, String pais, String provincia, String localidad,
			String codPostal, String motivo) {
		super();
		Id = id;
		Hora = hora;
		Fecha = fecha;
		this.idSucursal = idSucursal;
		this.pago = pago;
		Nombre = nombre;
		Apellido = apellido;
		CUIL = cUIL;
		CorreoElectronico = correoElectronico;
		TipoCliente = tipoCliente;
		ImpuestoAFIP = impuestoAFIP;
		Estado = estado;
		Calle = calle;
		Altura = altura;
		Pais = pais;
		Provincia = provincia;
		Localidad = localidad;
		CodPostal = codPostal;
		this.motivo = motivo;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getHora() {
		return Hora;
	}
	public void setHora(String hora) {
		Hora = hora;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public double getPago() {
		return pago;
	}
	public void setPago(double pago) {
		this.pago = pago;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getCUIL() {
		return CUIL;
	}
	public void setCUIL(String cUIL) {
		CUIL = cUIL;
	}
	public String getCorreoElectronico() {
		return CorreoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		CorreoElectronico = correoElectronico;
	}
	public String getTipoCliente() {
		return TipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		TipoCliente = tipoCliente;
	}
	public String getImpuestoAFIP() {
		return ImpuestoAFIP;
	}
	public void setImpuestoAFIP(String impuestoAFIP) {
		ImpuestoAFIP = impuestoAFIP;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getCalle() {
		return Calle;
	}
	public void setCalle(String calle) {
		Calle = calle;
	}
	public String getAltura() {
		return Altura;
	}
	public void setAltura(String altura) {
		Altura = altura;
	}
	public String getPais() {
		return Pais;
	}
	public void setPais(String pais) {
		Pais = pais;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public String getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}
	public String getCodPostal() {
		return CodPostal;
	}
	public void setCodPostal(String codPostal) {
		CodPostal = codPostal;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
