package dto;

public class SucursalDTO {

	int idSucursal;

	String telefono, calle, altura, provincia, localidad, pais, codPostal;
	
	public SucursalDTO(int idSucursal, String telefono, String calle, String altura, String provincia, String localidad,
			String pais, String codPostal) {

		this.idSucursal = idSucursal;
		this.telefono = telefono;
		this.calle = calle;
		this.altura = altura;
		this.provincia = provincia;
		this.localidad = localidad;
		this.pais = pais;
		this.codPostal = codPostal;
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
}
