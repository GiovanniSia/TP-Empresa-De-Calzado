package dto;

/*
CREATE TABLE `Ingresos`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Hora` TIME NOT NULL,
  `Tipo` varchar(45) NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `TipoFactura` varchar(45) NOT NULL,
  `NroFactura` int(11) NOT NULL,
  `MedioPago` varchar(45) NOT NULL,
  `Cantidad` double(11,2) NOT NULL,
  `Cotizacion` double(11,2) NOT NULL,
  `Operacion` varchar(45) NOT NULL,
  `Total` double(45,2) NOT NULL,
  PRIMARY KEY (`Id`)
);
*/
public class IngresosDTO {
	int id;
	int idSucursal;
	String fecha;
	String hora;
	String tipo;
	int idCliente;
	String tipoFactura;
	String nroFactura;
	String medioPago;
	double cantidad;
	double cotizacion;
	String operacion;
	double total;

	public IngresosDTO(int id, int idSucursal, String fecha, String hora, String tipo, int idCliente,
			String tipoFactura, String nroFactura, String medioPago, double cantidad, double cotizacion, String operacion,
			double total) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.fecha = fecha;
		this.hora = hora;
		this.tipo = tipo;
		this.idCliente = idCliente;
		this.tipoFactura = tipoFactura;
		this.nroFactura = nroFactura;
		this.medioPago = medioPago;
		this.cantidad = cantidad;
		this.cotizacion = cotizacion;
		this.operacion = operacion;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public String getFecha() {
		return fecha;
	}

	public String getHora() {
		return hora;
	}

	public String getTipo() {
		return tipo;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public String getNroFactura() {
		return nroFactura;
	}

	public String getMedioPago() {
		return medioPago;
	}

	public double getCantidad() {
		return cantidad;
	}

	public double getCotizacion() {
		return cotizacion;
	}

	public String getOperacion() {
		return operacion;
	}

	public double getTotal() {
		return total;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
