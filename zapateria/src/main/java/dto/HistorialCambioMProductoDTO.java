package dto;

/*
  `PuntoRepositorioAntiguo` double(45,2) NOT NULL,
  `PuntoRepositorioNuevo` double(45,2) NOT NULL,
  `CantidadAReponerAntiguo` double(45,2) NOT NULL,
  `CantidadAReponerNuevo` double(45,2) NOT NULL,
  `DiasParaReponerAntiguo` double(45,2) NOT NULL,
  `DiasParaReponerNuevo` double(45,2) NOT NULL,

*/
public class HistorialCambioMProductoDTO {

	private int idHistorialCambioProducto;
	private String idEmpleado;
	private String idMaestroProducto;
	private String fecha;

	private String precioCostoAntiguo;
	private String precioCostoNuevo;

	private String precioMayoristaAntiguo;
	private String precioMayoristaNuevo;

	private String precioMinoristaAntiguo;
	private String precioMinoristaNuevo;

	private String puntoRepositorioAntiguo;
	private String puntoRepositorioNuevo;

	private String cantidadAReponerAntiguo;
	private String cantidadAReponerNuevo;

	private String diasParaReponerAntiguo;
	private String diasParaReponerNuevo;

	public HistorialCambioMProductoDTO(int idHistorialCambioProducto, String idEmpleado, String idMaestroProducto,
			String fecha, String precioCostoAntiguo, String precioCostoNuevo, String precioMayoristaAntiguo,
			String precioMayoristaNuevo, String precioMinoristaAntiguo, String precioMinoristaNuevo,
			String puntoRepositorioAntiguo, String puntoRepositorioNuevo, String cantidadAReponerAntiguo,
			String cantidadAReponerNuevo, String diasParaReponerAntiguo, String diasParaReponerNuevo) {
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
		this.puntoRepositorioAntiguo = puntoRepositorioAntiguo;
		this.puntoRepositorioNuevo = puntoRepositorioNuevo;
		this.cantidadAReponerAntiguo = cantidadAReponerAntiguo;
		this.cantidadAReponerNuevo = cantidadAReponerNuevo;
		this.diasParaReponerAntiguo = diasParaReponerAntiguo;
		this.diasParaReponerNuevo = diasParaReponerNuevo;
	}

	public int getIdHistorialCambioProducto() {
		return idHistorialCambioProducto;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public String getIdMaestroProducto() {
		return idMaestroProducto;
	}

	public String getFecha() {
		return fecha;
	}

	public String getPrecioCostoAntiguo() {
		return precioCostoAntiguo;
	}

	public String getPrecioCostoNuevo() {
		return precioCostoNuevo;
	}

	public String getPrecioMayoristaAntiguo() {
		return precioMayoristaAntiguo;
	}

	public String getPrecioMayoristaNuevo() {
		return precioMayoristaNuevo;
	}

	public String getPrecioMinoristaAntiguo() {
		return precioMinoristaAntiguo;
	}

	public String getPrecioMinoristaNuevo() {
		return precioMinoristaNuevo;
	}

	public String getPuntoRepositorioAntiguo() {
		return puntoRepositorioAntiguo;
	}

	public String getPuntoRepositorioNuevo() {
		return puntoRepositorioNuevo;
	}

	public String getCantidadAReponerAntiguo() {
		return cantidadAReponerAntiguo;
	}

	public String getCantidadAReponerNuevo() {
		return cantidadAReponerNuevo;
	}

	public String getDiasParaReponerAntiguo() {
		return diasParaReponerAntiguo;
	}

	public String getDiasParaReponerNuevo() {
		return diasParaReponerNuevo;
	}

	public void setIdHistorialCambioProducto(int idHistorialCambioProducto) {
		this.idHistorialCambioProducto = idHistorialCambioProducto;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setIdMaestroProducto(String idMaestroProducto) {
		this.idMaestroProducto = idMaestroProducto;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setPrecioCostoAntiguo(String precioCostoAntiguo) {
		this.precioCostoAntiguo = precioCostoAntiguo;
	}

	public void setPrecioCostoNuevo(String precioCostoNuevo) {
		this.precioCostoNuevo = precioCostoNuevo;
	}

	public void setPrecioMayoristaAntiguo(String precioMayoristaAntiguo) {
		this.precioMayoristaAntiguo = precioMayoristaAntiguo;
	}

	public void setPrecioMayoristaNuevo(String precioMayoristaNuevo) {
		this.precioMayoristaNuevo = precioMayoristaNuevo;
	}

	public void setPrecioMinoristaAntiguo(String precioMinoristaAntiguo) {
		this.precioMinoristaAntiguo = precioMinoristaAntiguo;
	}

	public void setPrecioMinoristaNuevo(String precioMinoristaNuevo) {
		this.precioMinoristaNuevo = precioMinoristaNuevo;
	}

	public void setPuntoRepositorioAntiguo(String puntoRepositorioAntiguo) {
		this.puntoRepositorioAntiguo = puntoRepositorioAntiguo;
	}

	public void setPuntoRepositorioNuevo(String puntoRepositorioNuevo) {
		this.puntoRepositorioNuevo = puntoRepositorioNuevo;
	}

	public void setCantidadAReponerAntiguo(String cantidadAReponerAntiguo) {
		this.cantidadAReponerAntiguo = cantidadAReponerAntiguo;
	}

	public void setCantidadAReponerNuevo(String cantidadAReponerNuevo) {
		this.cantidadAReponerNuevo = cantidadAReponerNuevo;
	}

	public void setDiasParaReponerAntiguo(String diasParaReponerAntiguo) {
		this.diasParaReponerAntiguo = diasParaReponerAntiguo;
	}

	public void setDiasParaReponerNuevo(String diasParaReponerNuevo) {
		this.diasParaReponerNuevo = diasParaReponerNuevo;
	}

}
