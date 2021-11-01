package dto;

import java.util.HashMap;

public class CompraVirtualDTO {
	
	//private ClienteDTO cliente;
					//idProducto,cantidad
	private HashMap<Integer,Integer> compra;
	private int idSucursal;
	private double pago;
	int IdCliente;
	String Nombre;
	String Apellido;
	String CUIL;
	String CorreoElectronico;
	//String TipoCliente;
	String ImpuestoAFIP;
	String Estado;
	String Calle;
	String Altura;
	String Pais;
	String Provincia;
	String Localidad;
	String CodPostal;
	String nroTelefono;
	
	public CompraVirtualDTO(HashMap<Integer, Integer> compra, int idSucursal, double pago, int idCliente, String nombre,
			String apellido, String cUIL, String correoElectronico,
			//String tipoCliente,
			String calle, String altura, String pais,
			String provincia, String localidad, String codPostal, String nroTelefono) {
		super();
		this.compra = compra;
		this.idSucursal = idSucursal;
		this.pago = pago;
		IdCliente = idCliente;
		Nombre = nombre;
		Apellido = apellido;
		CUIL = cUIL;
		CorreoElectronico = correoElectronico;
		//TipoCliente = tipoCliente;
		ImpuestoAFIP = "CF";
		Estado = "Activo";
		Calle = calle;
		Altura = altura;
		Pais = pais;
		Provincia = provincia;
		Localidad = localidad;
		CodPostal = codPostal;
		
		this.nroTelefono = nroTelefono;
	}

	public void agregarUnaCompra(int idProducto, int cantidad) {
		if(compra.containsKey(idProducto)) {
			compra.put(idProducto, compra.get(idProducto)+cantidad);
		}else {
			compra.put(idProducto, cantidad);
		}
	}
/*
	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
*/
	public HashMap<Integer, Integer> getCompra() {
		return compra;
	}

	public void setCompra(HashMap<Integer, Integer> compra) {
		this.compra = compra;
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
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 

	public int getIdCliente() {
		return IdCliente;
	}

	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
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
/*
	public String getTipoCliente() {
		return TipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		TipoCliente = tipoCliente;
	}
*/
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

	public String getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}
	

}
