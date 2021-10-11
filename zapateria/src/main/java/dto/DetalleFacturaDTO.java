package dto;

public class DetalleFacturaDTO {
	
	int id, idProducto, cantidad;
	String descripcion;
	double precioCosto, precioVenta, monto;
	int idFactura;
	String unidadMedida;
	
	public DetalleFacturaDTO(int id, int idProducto, int cantidad, String descripcion, double precioCosto,
			double precioVenta, double monto, int idFactura, String unidadMedida) {
		super();
		this.id = id;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.precioCosto = precioCosto;
		this.precioVenta = precioVenta;
		this.monto = monto;
		this.idFactura = idFactura;
		this.unidadMedida = unidadMedida;
	}
	
	
	public int getId() {
		return id;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecioCosto() {
		return precioCosto;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public double getMonto() {
		return monto;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	
	
	
	/*
	   `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdProducto` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `PrecioCosto` double(45,2) NOT NULL,
  `PrecioVenta` double(45,2) NOT NULL,
  `Monto` double(45,2) NOT NULL,
  `idFactura` int(11) NOT NULL,
  `UnidadMedida` varchar(45) NOT NULL, 
	 
	 */
}
