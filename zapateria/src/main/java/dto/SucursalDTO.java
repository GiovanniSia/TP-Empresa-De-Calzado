package dto;

public class SucursalDTO {

	int IdSucursal;
	String Telefono, Calle, Altura, Provincia, Localidad, Pais, CodigoPostal, Nombre;
	
	public SucursalDTO(int idSucursal, String telefono, String calle, String altura, String provincia, String localidad,
			String pais, String codigoPostal, String nombre) {
		super();
		IdSucursal = idSucursal;
		Telefono = telefono;
		Calle = calle;
		Altura = altura;
		Provincia = provincia;
		Localidad = localidad;
		Pais = pais;
		CodigoPostal = codigoPostal;
		Nombre = nombre;
	}

	public int getIdSucursal() {
		return IdSucursal;
	}

	public String getTelefono() {
		return Telefono;
	}

	public String getCalle() {
		return Calle;
	}

	public String getAltura() {
		return Altura;
	}

	public String getProvincia() {
		return Provincia;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public String getPais() {
		return Pais;
	}

	public String getCodigoPostal() {
		return CodigoPostal;
	}

	public String getNombre() {
		return Nombre;
	}
	
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public void setCalle(String calle) {
		Calle = calle;
	}

	public void setAltura(String altura) {
		Altura = altura;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public void setPais(String pais) {
		Pais = pais;
	}

	public void setCodigoPostal(String codigoPostal) {
		CodigoPostal = codigoPostal;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
}
