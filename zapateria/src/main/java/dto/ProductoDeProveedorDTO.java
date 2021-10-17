package dto;

public class ProductoDeProveedorDTO {

	int Id, IdProveedor, IdMaestroProducto;
	double precioVenta;
	int cantidadPorLote;
	
	public ProductoDeProveedorDTO(int id, int idProveedor, int idMaestroProducto,
			double precioVenta, int cantidadPorLote) {
		super();
		Id = id;
		IdProveedor = idProveedor;
		IdMaestroProducto = idMaestroProducto;
		this.precioVenta = precioVenta;
		this.cantidadPorLote = cantidadPorLote;
	}
	
	public int getId() {
		return Id;
	}

	public int getIdProveedor() {
		return IdProveedor;
	}

	public int getIdMaestroProducto() {
		return IdMaestroProducto;
	}


	public double getPrecioVenta() {
		return precioVenta;
	}

	public int getCantidadPorLote() {
		return cantidadPorLote;
	}
}
