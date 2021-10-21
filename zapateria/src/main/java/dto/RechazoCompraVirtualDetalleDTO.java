package dto;

public class RechazoCompraVirtualDetalleDTO {
	
	int Id;
	int IdRechazoCompraVirtual;
	int IdProducto;
	String NombreProducto;
	double PrecioMayorista;
	double PrecioMinorista;
	double PrecioCosto;
	public RechazoCompraVirtualDetalleDTO(int id, int idRechazoCompraVirtual, int idProducto, String nombreProducto,
			double precioMayorista, double precioMinorista, double precioCosto) {
		super();
		Id = id;
		IdRechazoCompraVirtual = idRechazoCompraVirtual;
		IdProducto = idProducto;
		NombreProducto = nombreProducto;
		PrecioMayorista = precioMayorista;
		PrecioMinorista = precioMinorista;
		PrecioCosto = precioCosto;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getIdRechazoCompraVirtual() {
		return IdRechazoCompraVirtual;
	}
	public void setIdRechazoCompraVirtual(int idRechazoCompraVirtual) {
		IdRechazoCompraVirtual = idRechazoCompraVirtual;
	}
	public int getIdProducto() {
		return IdProducto;
	}
	public void setIdProducto(int idProducto) {
		IdProducto = idProducto;
	}
	public String getNombreProducto() {
		return NombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		NombreProducto = nombreProducto;
	}
	public double getPrecioMayorista() {
		return PrecioMayorista;
	}
	public void setPrecioMayorista(double precioMayorista) {
		PrecioMayorista = precioMayorista;
	}
	public double getPrecioMinorista() {
		return PrecioMinorista;
	}
	public void setPrecioMinorista(double precioMinorista) {
		PrecioMinorista = precioMinorista;
	}
	public double getPrecioCosto() {
		return PrecioCosto;
	}
	public void setPrecioCosto(double precioCosto) {
		PrecioCosto = precioCosto;
	}

}
