package dto;

public class ProvinciaDTO {
	private int idProvincia;
	private String nombreProvincia;
	private int foreignPais;

	public ProvinciaDTO(int idProvincia, String nombreProvincia, int foreignPais)
	{
		this.idProvincia = idProvincia;
		this.nombreProvincia = nombreProvincia;
		this.foreignPais = foreignPais;
	}

	public int getIdProvincia() 
	{
		return this.idProvincia;
	}

	public void setIdProvincia(int idProvincia) 
	{
		this.idProvincia = idProvincia;
	}

	public String getNombreProvincia() 
	{
		return this.nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) 
	{
		this.nombreProvincia = nombreProvincia;
	}
	
	public int getForeignPais() {
		return foreignPais;
	}

	
}
