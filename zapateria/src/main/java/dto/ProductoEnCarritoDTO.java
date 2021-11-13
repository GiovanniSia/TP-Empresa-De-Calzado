package dto;

public class ProductoEnCarritoDTO {
	MaestroProductoDTO producto;
	StockDTO stock;
	double cantidad;
	
	public ProductoEnCarritoDTO(MaestroProductoDTO producto, StockDTO stock, double cantidad) {
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

	public double getCantidad() {
		return cantidad;
	}
	
	public void aniadirProducto(double cantidad) {
		this.cantidad +=cantidad;
	}
	public void cambiarCantidad(double valor) {
		this.cantidad=valor;
	}
}
