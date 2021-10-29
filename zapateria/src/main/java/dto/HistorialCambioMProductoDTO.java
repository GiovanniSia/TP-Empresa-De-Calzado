package dto;

/*
   `DescripcionAntiguo` varchar(45) NOT NULL,
  `DescripcionNuevo` varchar(45) NOT NULL,
  `ProveedorAntiguo` varchar(45) NOT NULL,
  `ProveedorNuevo` varchar(45) NOT NULL,
  
  
  
  `PuntoRepositorioAntiguo` double(45,2) NOT NULL,
  `PuntoRepositorioNuevo` double(45,2) NOT NULL,
  `CantidadAReponerAntiguo` double(45,2) NOT NULL,
  `CantidadAReponerNuevo` double(45,2) NOT NULL,
  `DiasParaReponerAntiguo` double(45,2) NOT NULL,
  `DiasParaReponerNuevo` double(45,2) NOT NULL,

*/
public class HistorialCambioMProductoDTO {

	private int idHistorialCambioProducto;
	private String idSucursal;
	private String idEmpleado;
	private String idMaestroProducto;
	private String fecha;

	private String DescripcionAntiguo;
	private String DescripcionNuevo;

	private String ProveedorAntiguo;
	private String ProveedorNuevo;

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

	public HistorialCambioMProductoDTO(int idHistorialCambioProducto, String idSucursal, String idEmpleado,
			String idMaestroProducto, String fecha, String descripcionAntiguo, String descripcionNuevo,
			String proveedorAntiguo, String proveedorNuevo, String precioCostoAntiguo, String precioCostoNuevo,
			String precioMayoristaAntiguo, String precioMayoristaNuevo, String precioMinoristaAntiguo,
			String precioMinoristaNuevo, String puntoRepositorioAntiguo, String puntoRepositorioNuevo,
			String cantidadAReponerAntiguo, String cantidadAReponerNuevo, String diasParaReponerAntiguo,
			String diasParaReponerNuevo) {
		super();
		this.idHistorialCambioProducto = idHistorialCambioProducto;
		this.idSucursal = idSucursal;
		this.idEmpleado = idEmpleado;
		this.idMaestroProducto = idMaestroProducto;
		this.fecha = fecha;
		DescripcionAntiguo = descripcionAntiguo;
		DescripcionNuevo = descripcionNuevo;
		ProveedorAntiguo = proveedorAntiguo;
		ProveedorNuevo = proveedorNuevo;
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

	public void setIdHistorialCambioProducto(int idHistorialCambioProducto) {
		this.idHistorialCambioProducto = idHistorialCambioProducto;
	}

	public String getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(String idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getIdMaestroProducto() {
		return idMaestroProducto;
	}

	public void setIdMaestroProducto(String idMaestroProducto) {
		this.idMaestroProducto = idMaestroProducto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcionAntiguo() {
		return DescripcionAntiguo;
	}

	public void setDescripcionAntiguo(String descripcionAntiguo) {
		DescripcionAntiguo = descripcionAntiguo;
	}

	public String getDescripcionNuevo() {
		return DescripcionNuevo;
	}

	public void setDescripcionNuevo(String descripcionNuevo) {
		DescripcionNuevo = descripcionNuevo;
	}

	public String getProveedorAntiguo() {
		return ProveedorAntiguo;
	}

	public void setProveedorAntiguo(String proveedorAntiguo) {
		ProveedorAntiguo = proveedorAntiguo;
	}

	public String getProveedorNuevo() {
		return ProveedorNuevo;
	}

	public void setProveedorNuevo(String proveedorNuevo) {
		ProveedorNuevo = proveedorNuevo;
	}

	public String getPrecioCostoAntiguo() {
		return precioCostoAntiguo;
	}

	public void setPrecioCostoAntiguo(String precioCostoAntiguo) {
		this.precioCostoAntiguo = precioCostoAntiguo;
	}

	public String getPrecioCostoNuevo() {
		return precioCostoNuevo;
	}

	public void setPrecioCostoNuevo(String precioCostoNuevo) {
		this.precioCostoNuevo = precioCostoNuevo;
	}

	public String getPrecioMayoristaAntiguo() {
		return precioMayoristaAntiguo;
	}

	public void setPrecioMayoristaAntiguo(String precioMayoristaAntiguo) {
		this.precioMayoristaAntiguo = precioMayoristaAntiguo;
	}

	public String getPrecioMayoristaNuevo() {
		return precioMayoristaNuevo;
	}

	public void setPrecioMayoristaNuevo(String precioMayoristaNuevo) {
		this.precioMayoristaNuevo = precioMayoristaNuevo;
	}

	public String getPrecioMinoristaAntiguo() {
		return precioMinoristaAntiguo;
	}

	public void setPrecioMinoristaAntiguo(String precioMinoristaAntiguo) {
		this.precioMinoristaAntiguo = precioMinoristaAntiguo;
	}

	public String getPrecioMinoristaNuevo() {
		return precioMinoristaNuevo;
	}

	public void setPrecioMinoristaNuevo(String precioMinoristaNuevo) {
		this.precioMinoristaNuevo = precioMinoristaNuevo;
	}

	public String getPuntoRepositorioAntiguo() {
		return puntoRepositorioAntiguo;
	}

	public void setPuntoRepositorioAntiguo(String puntoRepositorioAntiguo) {
		this.puntoRepositorioAntiguo = puntoRepositorioAntiguo;
	}

	public String getPuntoRepositorioNuevo() {
		return puntoRepositorioNuevo;
	}

	public void setPuntoRepositorioNuevo(String puntoRepositorioNuevo) {
		this.puntoRepositorioNuevo = puntoRepositorioNuevo;
	}

	public String getCantidadAReponerAntiguo() {
		return cantidadAReponerAntiguo;
	}

	public void setCantidadAReponerAntiguo(String cantidadAReponerAntiguo) {
		this.cantidadAReponerAntiguo = cantidadAReponerAntiguo;
	}

	public String getCantidadAReponerNuevo() {
		return cantidadAReponerNuevo;
	}

	public void setCantidadAReponerNuevo(String cantidadAReponerNuevo) {
		this.cantidadAReponerNuevo = cantidadAReponerNuevo;
	}

	public String getDiasParaReponerAntiguo() {
		return diasParaReponerAntiguo;
	}

	public void setDiasParaReponerAntiguo(String diasParaReponerAntiguo) {
		this.diasParaReponerAntiguo = diasParaReponerAntiguo;
	}

	public String getDiasParaReponerNuevo() {
		return diasParaReponerNuevo;
	}

	public void setDiasParaReponerNuevo(String diasParaReponerNuevo) {
		this.diasParaReponerNuevo = diasParaReponerNuevo;
	}

}
