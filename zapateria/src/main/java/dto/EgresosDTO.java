package dto;

/*
CREATE TABLE `Egresos`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Hora` TIME NOT NULL,
  `Tipo` varchar(45) NOT NULL,
  `MedioPago` varchar(45) NOT NULL,
  `Detalle` varchar(45) NOT NULL,
  `Total` double(45,2) NOT NULL,
  PRIMARY KEY (`Id`)
);
 */
public class EgresosDTO {
	int id;
	int idSucursal;
	String fecha;
	String hora;
	String tipo;
	String MedioPago;
	String detalle;
	double total;

	public EgresosDTO(int id, int idSucursal, String fecha, String hora, String tipo, String medioPago, String detalle,
			double total) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.fecha = fecha;
		this.hora = hora;
		this.tipo = tipo;
		MedioPago = medioPago;
		this.detalle = detalle;
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

	public String getMedioPago() {
		return MedioPago;
	}

	public String getDetalle() {
		return detalle;
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

	public void setMedioPago(String medioPago) {
		MedioPago = medioPago;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
