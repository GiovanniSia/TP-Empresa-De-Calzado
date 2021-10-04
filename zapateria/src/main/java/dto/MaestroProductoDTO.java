package dto;

public class MaestroProductoDTO {

	private int idMaestroProducto;
	private String descripcion;
	private String tipo;
	private String fabricado;
	private int precioCosto;
	private int precioVenta;
	private int puntoRepositorio;
	private int idProveedor;
	private String talle;
	private int unidadMedida;
	private String estado;
	
	private int CantidadAReponer;
	private int DiasParaReponer;

	public MaestroProductoDTO(int idMaestroProducto, String descripcion, String tipo, String fabricado, int precioCosto,
			int precioVenta, int puntoRepositorio, int idProveedor, String talle, int unidadMedida, String estado, 
			int CantidadAReponer, int DiasParaReponer) {
		this.idMaestroProducto = idMaestroProducto;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fabricado = fabricado;
		this.precioCosto = precioCosto;
		this.precioVenta = precioVenta;
		this.puntoRepositorio = puntoRepositorio;
		this.idProveedor = idProveedor;
		this.talle = talle;
		this.unidadMedida = unidadMedida;
		this.estado = estado;
		
		this.CantidadAReponer = CantidadAReponer; //Agregado para la generacion de ordenes de manufactura
		this.DiasParaReponer = DiasParaReponer;
	}

	public int getDiasParaReponer() {
		return DiasParaReponer;
	}

	public void setDiasParaReponer(int diasParaReponer) {
		DiasParaReponer = diasParaReponer;
	}

	public int getCantidadAReponer() {
		return CantidadAReponer;
	}

	public void setCantidadAReponer(int cantidadAReponer) {
		CantidadAReponer = cantidadAReponer;
	}

	public int getIdMaestroProducto() {
		return idMaestroProducto;
	}

	public void setIdMaestroProducto(int idMaestroProducto) {
		this.idMaestroProducto = idMaestroProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFabricado() {
		return fabricado;
	}

	public void setFabricado(String fabricado) {
		this.fabricado = fabricado;
	}

	public int getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(int precioCosto) {
		this.precioCosto = precioCosto;
	}

	public int getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getPuntoRepositorio() {
		return puntoRepositorio;
	}

	public void setPuntoRepositorio(int puntoRepositorio) {
		this.puntoRepositorio = puntoRepositorio;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public int getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(int unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "MaestroProductoDTO [idMaestroProducto=" + idMaestroProducto + ", descripcion=" + descripcion + ", tipo="
				+ tipo + ", fabricado=" + fabricado + ", precioCosto=" + precioCosto + ", precioVenta=" + precioVenta
				+ ", puntoRepositorio=" + puntoRepositorio + ", idProveedor=" + idProveedor + ", talle=" + talle
				+ ", unidadMedida=" + unidadMedida + ", estado=" + estado + ", CantidadAReponer=" + CantidadAReponer
				+ ", DiasParaReponer=" + DiasParaReponer + "]";
	}

	
	
	
}
