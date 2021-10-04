package dto;

public class RecetaDTO {
	
	private int IdReceta;
	private int IdProducto;
	private String Descripcion;
	
	public RecetaDTO(int idReceta, int idProducto, String descripcion) {
		super();
		IdReceta = idReceta;
		IdProducto = idProducto;
		Descripcion = descripcion;
	}
	
	public int getIdReceta() {
		return IdReceta;
	}
	public void setIdReceta(int idReceta) {
		IdReceta = idReceta;
	}
	public int getIdProducto() {
		return IdProducto;
	}
	public void setIdProducto(int idProducto) {
		IdProducto = idProducto;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
}
