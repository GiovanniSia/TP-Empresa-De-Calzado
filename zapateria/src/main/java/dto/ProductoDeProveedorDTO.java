package dto;

public class ProductoDeProveedorDTO {

	int Id, IdProveedor, IdMaestroProducto;
	double precioVenta;
	double cantidadPorLote;
	
	public ProductoDeProveedorDTO(int id, int idProveedor, int idMaestroProducto,
			double precioVenta, double cantidadPorLote) {
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

	public double getCantidadPorLote() {
		return cantidadPorLote;
	}

	public void setIdProveedor(int idProveedor) {
		IdProveedor = idProveedor;
	}

	public void setIdMaestroProducto(int idMaestroProducto) {
		IdMaestroProducto = idMaestroProducto;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public void setCantidadPorLote(int cantidadPorLote) {
		this.cantidadPorLote = cantidadPorLote;
	}
	
}
