package dto;

public class SucursalDTO {

	int idSucursal;

	String telefono, calle, altura, provincia, localidad, pais, codPostal, nombre;
	
	public SucursalDTO(int idSucursal, String telefono, String calle, String altura, String provincia, String localidad,
			String pais, String codPostal, String nombre) {

		this.idSucursal = idSucursal;
		this.telefono = telefono;
		this.calle = calle;
		this.altura = altura;
		this.provincia = provincia;
		this.localidad = localidad;
		this.pais = pais;
		this.codPostal = codPostal;
		this.nombre = nombre;
	}
	
	public int getIdSucursal() {
		return idSucursal;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getCalle() {
		return calle;
	}

	public String getAltura() {
		return altura;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getPais() {
		return pais;
	}

	public String getCodPostal() {
		return codPostal;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
