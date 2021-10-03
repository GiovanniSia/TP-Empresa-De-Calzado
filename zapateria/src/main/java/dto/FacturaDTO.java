package dto;

import java.sql.Date;

public class FacturaDTO {
	private int idFactura;
	private double montoPendiente;
	private String detalle;
	private int idCliente;
	private int idEmpleado;
	private Date fecha;
	private String tipoFactura;
	private int nroFacturaCompleta;
	private int idSucursal;
	private double descuento;
	private double totalFactura;
	private int idMedioDePago;
	private String tipoVenta;	
	
	public FacturaDTO(int idFactura, double montoPendiente, String detalle, int idCliente, int idEmpleado, Date fecha,
			String tipoFactura, int nroFacturaCompleta, int idSucursal, double descuento, double totalFactura,
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
	public double getMontoPendiente() {
		return montoPendiente;
	}
	public void setMontoPendiente(double montoPendiente) {
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
	public int getNroFacturaCompleta() {
		return nroFacturaCompleta;
	}
	public void setNroFacturaCompleta(int nroFacturaCompleta) {
		this.nroFacturaCompleta = nroFacturaCompleta;
	}
	public int getIdScurusal() {
		return idSucursal;
	}
	public void setIdScurusal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getTotalFactura() {
		return totalFactura;
	}
	public void setTotalFactura(double totalFactura) {
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
