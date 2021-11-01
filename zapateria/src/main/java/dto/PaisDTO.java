package dto;

public class PaisDTO {
	private int idPais;
	private String nombrePais;

	public PaisDTO(int idPais, String nombrePais)
	{
		this.idPais = idPais;
		this.nombrePais = nombrePais;
	}
	
	public int getIdPais() 
	{
		return this.idPais;
	}

	public void setIdPais(int idPais) 
	{
		this.idPais = idPais;
	}

	public String getNombrePais() 
	{
		return this.nombrePais;
	}

	public void setNombrePais(String nombrePais) 
	{
		this.nombrePais = nombrePais;
	}
}
