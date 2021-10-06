package dto;

public class HistorialCambioMProductoDTO {

	private int idHistorialCambioProducto;
	private int idEmpleado;
	private int idMaestroProducto;
	private String fecha;

	private double precioCostoAntiguo;
	private double precioCostoNuevo;

	private double precioMayoristaAntiguo;
	private double precioMayoristaNuevo;

	private double precioMinoristaAntiguo;
	private double precioMinoristaNuevo;

	public HistorialCambioMProductoDTO(int idHistorialCambioProducto, int idEmpleado, int idMaestroProducto,
			String fecha, double precioCostoAntiguo, double precioCostoNuevo, double precioMayoristaAntiguo,
			double precioMayoristaNuevo, double precioMinoristaAntiguo, double precioMinoristaNuevo) {
		super();
		this.idHistorialCambioProducto = idHistorialCambioProducto;
		this.idEmpleado = idEmpleado;
		this.idMaestroProducto = idMaestroProducto;
		this.fecha = fecha;
		this.precioCostoAntiguo = precioCostoAntiguo;
		this.precioCostoNuevo = precioCostoNuevo;
		this.precioMayoristaAntiguo = precioMayoristaAntiguo;
		this.precioMayoristaNuevo = precioMayoristaNuevo;
		this.precioMinoristaAntiguo = precioMinoristaAntiguo;
		this.precioMinoristaNuevo = precioMinoristaNuevo;
	}

	public int getIdHistorialCambioProducto() {
		return idHistorialCambioProducto;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public int getIdMaestroProducto() {
		return idMaestroProducto;
	}

	public String getFecha() {
		return fecha;
	}

	public double getPrecioCostoAntiguo() {
		return precioCostoAntiguo;
	}

	public double getPrecioCostoNuevo() {
		return precioCostoNuevo;
	}

	public double getPrecioMayoristaAntiguo() {
		return precioMayoristaAntiguo;
	}

	public double getPrecioMayoristaNuevo() {
		return precioMayoristaNuevo;
	}

	public double getPrecioMinoristaAntiguo() {
		return precioMinoristaAntiguo;
	}

	public double getPrecioMinoristaNuevo() {
		return precioMinoristaNuevo;
	}

	public void setIdHistorialCambioProducto(int idHistorialCambioProducto) {
		this.idHistorialCambioProducto = idHistorialCambioProducto;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setIdMaestroProducto(int idMaestroProducto) {
		this.idMaestroProducto = idMaestroProducto;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setPrecioCostoAntiguo(double precioCostoAntiguo) {
		this.precioCostoAntiguo = precioCostoAntiguo;
	}

	public void setPrecioCostoNuevo(double precioCostoNuevo) {
		this.precioCostoNuevo = precioCostoNuevo;
	}

	public void setPrecioMayoristaAntiguo(double precioMayoristaAntiguo) {
		this.precioMayoristaAntiguo = precioMayoristaAntiguo;
	}

	public void setPrecioMayoristaNuevo(double precioMayoristaNuevo) {
		this.precioMayoristaNuevo = precioMayoristaNuevo;
	}

	public void setPrecioMinoristaAntiguo(double precioMinoristaAntiguo) {
		this.precioMinoristaAntiguo = precioMinoristaAntiguo;
	}

	public void setPrecioMinoristaNuevo(double precioMinoristaNuevo) {
		this.precioMinoristaNuevo = precioMinoristaNuevo;
	}

}
