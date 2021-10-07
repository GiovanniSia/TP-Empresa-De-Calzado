package dto;

public class DetalleCarritoDTO {
	
	int id, idCarrito, idProducto, idStock, Cantidad;
	double precio;
	
	public DetalleCarritoDTO(int id, int idCarrito, int idProducto, int idStock, int cantidad, double precio) {
		super();
		this.id = id;
		this.idCarrito = idCarrito;
		this.idProducto = idProducto;
		this.idStock = idStock;
		Cantidad = cantidad;
		this.precio = precio;
	}
	
	
	public int getId() {
		return id;
	}

	public int getIdCarrito() {
		return idCarrito;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public int getIdStock() {
		return idStock;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public double getPrecio() {
		return precio;
	}

}
