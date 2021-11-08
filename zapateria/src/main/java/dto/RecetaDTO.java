package dto;

public class RecetaDTO {
	
	private int IdReceta;
	private int IdProducto;
	private String Descripcion;
	private String Estado;
	
	public RecetaDTO(int idReceta, int idProducto, String descripcion, String Estado) {
		super();
		IdReceta = idReceta;
		IdProducto = idProducto;
		Descripcion = descripcion;
		this.Estado = Estado;
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

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	
}
