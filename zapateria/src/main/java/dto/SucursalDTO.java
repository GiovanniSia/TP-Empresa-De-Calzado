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
}
