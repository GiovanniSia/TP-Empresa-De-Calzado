package dto;

public class ProductoEnCarritoDTO {
	MaestroProductoDTO producto;
	StockDTO stock;
	int cantidad;
	
	public ProductoEnCarritoDTO(MaestroProductoDTO producto, StockDTO stock, int cantidad) {
		super();
		this.producto = producto;
		this.stock=stock;
		this.cantidad = cantidad;
	}
	
	public MaestroProductoDTO getProducto() {
		return producto;
	}
	
	public StockDTO getStock() {
		return this.stock;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public void aniadirProducto() {
		this.cantidad++;
	}
	public void cambiarCantidad(int valor) {
		this.cantidad=valor;
	}
}
