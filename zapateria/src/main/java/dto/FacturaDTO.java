package dto;

import java.sql.Date;

public class FacturaDTO {
	private int idFactura;
	private int montoPendiente;
	private String detalle;
	private int idCliente;
	private int idEmpleado;
	private Date fecha;
	private String tipoFactura;
	private String nroFacturaCompleta;
	private int idSucursal;
	private int terceraParte;
	private int descuento;
	private int totalFactura;
	private int idMedioDePago;
	private String tipoVenta;	
	
	public FacturaDTO(int idFactura, int montoPendiente, String detalle, int idCliente, int idEmpleado, Date fecha,
			String tipoFactura, String nroFacturaCompleta, int idSucursal,int terceraParte, int descuento, int totalFactura,
			int idMedioDePago, String tipoVenta) {
		super();
		this.idFactura = idFactura;
		this.montoPendiente = montoPendiente;
		this.detalle = detalle;
		this.idCliente = idCliente;
		this.idEmpleado = idEmpleado;
		this.fecha = fecha;
		this.tipoFactura = tipoFactura;
		this.nroFacturaCompleta = nroFacturaCompleta;
		this.idSucursal = idSucursal;
		this.terceraParte = terceraParte;
		this.descuento = descuento;
		this.totalFactura = totalFactura;
		this.idMedioDePago = idMedioDePago;
		this.tipoVenta = tipoVenta;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getMontoPendiente() {
		return montoPendiente;
	}
	public void setMontoPendiente(int montoPendiente) {
		this.montoPendiente = montoPendiente;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipoFactura() {
		return tipoFactura;
	}
	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
	public String getNroFacturaCompleta() {
		return nroFacturaCompleta;
	}
	public void setNroFacturaCompleta(String nroFacturaCompleta) {
		this.nroFacturaCompleta = nroFacturaCompleta;
	}
	public int getIdScurusal() {
		return idSucursal;
	}
	public void setIdScurusal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	
	public int getTerceraParte() {
		return terceraParte;
	}
	public void setTerceraParte(int terceraParte) {
		this.terceraParte = terceraParte;
	}
	
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public int getTotalFactura() {
		return totalFactura;
	}
	public void setTotalFactura(int totalFactura) {
		this.totalFactura = totalFactura;
	}
	public int getIdMedioDePago() {
		return idMedioDePago;
	}
	public void setIdMedioDePago(int idMedioDePago) {
		this.idMedioDePago = idMedioDePago;
	}
	public String getTipoVenta() {
		return tipoVenta;
	}
	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
	}
	
}
