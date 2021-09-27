package dto;

public class ProductoDTO {

	private int id;
	private String nombre;

	// Durante el primer sprint no estara
	private int idProveedor;

	public ProductoDTO(int id, String nombre, int idProveedor) {
		this.id = id;
		this.nombre = nombre;
		this.idProveedor = idProveedor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

}
