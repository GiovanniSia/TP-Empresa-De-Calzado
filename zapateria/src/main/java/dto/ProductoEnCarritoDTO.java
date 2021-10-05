package dto;

public class ProductoEnCarritoDTO {
	MaestroProductoDTO producto;
	int cantidad;
	
	public ProductoEnCarritoDTO(MaestroProductoDTO producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public MaestroProductoDTO getProducto() {
		return producto;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public void sumarUno() {
		this.cantidad++;
	}
}
