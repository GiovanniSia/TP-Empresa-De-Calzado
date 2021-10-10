package dto;

/*
CREATE TABLE `historialCambioMoneda`
(
  `IdCambioMoneda` int(11) NOT NULL AUTO_INCREMENT,
  `IdMoneda` varchar(11) NOT NULL,
  `Descripcion` varchar(11) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Hora` TIME NOT NULL,
  `TasaConversionAntigua` double(45,2) NOT NULL,
  `TasaConversionNueva` double(45,2) NOT NULL,
  PRIMARY KEY (`IdCambioMoneda`));
*/

public class HistorialCambioMonedaDTO {
	int idCambioMoneda;
	String idMoneda;
	String descripcion;
	int idEmpleado;
	String fecha;
	String hora;
	double tasaConversionAntigua;
	double tasaConversionNueva;

	public HistorialCambioMonedaDTO(int idCambioMoneda, String idMoneda, String descripcion, int idEmpleado,
			String fecha, String hora, double tasaConversionAntigua, double tasaConversionNueva) {
		super();
		this.idCambioMoneda = idCambioMoneda;
		this.idMoneda = idMoneda;
		this.descripcion = descripcion;
		this.idEmpleado = idEmpleado;
		this.fecha = fecha;
		this.hora = hora;
		this.tasaConversionAntigua = tasaConversionAntigua;
		this.tasaConversionNueva = tasaConversionNueva;
	}

	public int getIdCambioMoneda() {
		return idCambioMoneda;
	}

	public String getIdMoneda() {
		return idMoneda;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public String getFecha() {
		return fecha;
	}

	public String getHora() {
		return hora;
	}

	public double getTasaConversionAntigua() {
		return tasaConversionAntigua;
	}

	public double getTasaConversionNueva() {
		return tasaConversionNueva;
	}

	public void setIdCambioMoneda(int idCambioMoneda) {
		this.idCambioMoneda = idCambioMoneda;
	}

	public void setIdMoneda(String idMoneda) {
		this.idMoneda = idMoneda;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setTasaConversionAntigua(double tasaConversionAntigua) {
		this.tasaConversionAntigua = tasaConversionAntigua;
	}

	public void setTasaConversionNueva(double tasaConversionNueva) {
		this.tasaConversionNueva = tasaConversionNueva;
	}

}
