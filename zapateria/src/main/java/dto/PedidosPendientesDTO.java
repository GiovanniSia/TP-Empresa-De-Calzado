package dto;

public class PedidosPendientesDTO {

	int id, idProveedor;
	String nombreProveedor;
	int idMaestroProducto;
	String nombreMaestroProducto;
	double cantidad;
	String fecha;
	String hora;
	double precioUnidad, precioTotal;
	String estado;
	int idSucursal;
	int idEmpleado;
	String fechaEnvioMail;
	String horaEnvioMail;
	String fechaCompleto;
	String horaCompleto;
	String unidadMedida;
	double totalPagado;
	

	public PedidosPendientesDTO(int id, int idProveedor, String nombreProveedor, int idMaestroProducto,
			String nombreMaestroProducto, double cantidad, String fecha, String hora, double precioUnidad,
			double precioTotal, String estado, int idSucursal,int idEmpleado, String fechaEnvioMail,String horaEnvioMail,String fechaCompleto,String horaCompleto, String unidadMedida,double totalPagado) {
		super();
		this.id = id;
		this.idProveedor = idProveedor;
		this.nombreProveedor = nombreProveedor;
		this.idMaestroProducto = idMaestroProducto;
		this.nombreMaestroProducto = nombreMaestroProducto;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.hora = hora;
		this.precioUnidad = precioUnidad;
		this.precioTotal = precioTotal;
		this.estado = estado;
		this.idSucursal = idSucursal;
		this.idEmpleado = idEmpleado;
		this.fechaEnvioMail = fechaEnvioMail;
		this.horaEnvioMail = horaEnvioMail;
		this.horaCompleto = horaCompleto;
		this.fechaCompleto = fechaCompleto;
		this.unidadMedida = unidadMedida;
		this.totalPagado = totalPagado;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public int getIdMaestroProducto() {
		return idMaestroProducto;
	}

	public void setIdMaestroProducto(int idMaestroProducto) {
		this.idMaestroProducto = idMaestroProducto;
	}

	public String getNombreMaestroProducto() {
		return nombreMaestroProducto;
	}

	public void setNombreMaestroProducto(String nombreMaestroProducto) {
		this.nombreMaestroProducto = nombreMaestroProducto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getFechaEnvioMail() {
		return fechaEnvioMail;
	}

	public void setFechaEnvioMail(String fechaEnvioMail) {
		this.fechaEnvioMail = fechaEnvioMail;
	}

	public String getFechaCompleto() {
		return fechaCompleto;
	}

	public void setFechaCompleto(String fechaCompleto) {
		this.fechaCompleto = fechaCompleto;
	}
	
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public String getHoraEnvioMail() {
		return horaEnvioMail;
	}

	public String getHoraCompleto() {
		return horaCompleto;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setHoraEnvioMail(String horaEnvioMail) {
		this.horaEnvioMail = horaEnvioMail;
	}

	public void setHoraCompleto(String horaCompleto) {
		this.horaCompleto = horaCompleto;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public double getTotalPagado() {
		return totalPagado;
	}

	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}
	
	
}
