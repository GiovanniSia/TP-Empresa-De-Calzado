package dto;

public class LocalidadDTO {
	private int idLocalidad;
	private String nombreLocalidad;
	private int idForeignProvincia;

	public LocalidadDTO(int idLocalidad, String nombreLocalidad, int idForeignProvincia)
	{
		this.idLocalidad = idLocalidad;
		this.nombreLocalidad = nombreLocalidad;
		this.idForeignProvincia = idForeignProvincia;
	}

	public int getIdLocalidad() 
	{
		return this.idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) 
	{
		this.idLocalidad = idLocalidad;
	}

	public String getNombreLocalidad() 
	{
		return this.nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) 
	{
		this.nombreLocalidad = nombreLocalidad;
	}
	
	public int getIdForeignProvincia() {
		return idForeignProvincia;
	}
}
