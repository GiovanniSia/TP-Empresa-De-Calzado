package dto;

import java.sql.Date;

public class FacturaDTO {
	
	private int idFactura;
	private double montoPendiente;
	private int idCliente;
	private String nombreCliente;
	private int idCajero;
	private String nombreCajero;
	private int idVendedor;
	private String nombreVendedor;
	private String fecha;
	private String tipoFactura;
	private String nroFacturaCompleta;
	private int idSucursal;
	private double descuento;
	private double totalBruto;
	private double totalFactura;
	private String tipoVenta;
	private String calleCliente;
	private String alturaCliente;
	private String paisCliente;
	private String provinciaCliente;
	private String localidadCliente;
	private String codPostalCliente;
	private String cuilCliente;
	private String correoElectronicoCliente;
	private String impuestoAFIPCliente;
	private double IVA;
	
	

	public FacturaDTO(int idFactura, double montoPendiente, int idCliente, String nombreCliente, int idCajero,
			String nombreCajero, int idVendedor, String nombreVendedor, String fecha, String tipoFactura, String nroFacturaCompleta, int idSucursal,
			double descuento, double totalBruto, double totalFactura, String tipoVenta, String calleCliente,
			String alturaCliente, String paisCliente, String provinciaCliente, String localidadCliente,
			String codPostalCliente, String cuilCliente, String correoElectronicoCliente, String impuestoAFIPCliente,
			double iVA) {
		super();
		this.idFactura = idFactura;
		this.montoPendiente = montoPendiente;
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.idCajero = idCajero;
		this.nombreCajero = nombreCajero;
		this.idVendedor = idVendedor;
		this.nombreVendedor = nombreVendedor;		
		this.fecha = fecha;
		this.tipoFactura = tipoFactura;
		this.nroFacturaCompleta = nroFacturaCompleta;
		this.idSucursal = idSucursal;
		this.descuento = descuento;
		this.totalBruto = totalBruto;
		this.totalFactura = totalFactura;
		this.tipoVenta = tipoVenta;
		this.calleCliente = calleCliente;
		this.alturaCliente = alturaCliente;
		this.paisCliente = paisCliente;
		this.provinciaCliente = provinciaCliente;
		this.localidadCliente = localidadCliente;
		this.codPostalCliente = codPostalCliente;
		this.cuilCliente = cuilCliente;
		this.correoElectronicoCliente = correoElectronicoCliente;
		this.impuestoAFIPCliente = impuestoAFIPCliente;
		IVA = iVA;
	}

	
	public int getIdFactura() {
		return idFactura;
	}



	public double getMontoPendiente() {
		return montoPendiente;
	}



	public int getIdCliente() {
		return idCliente;
	}



	public String getNombreCliente() {
		return nombreCliente;
	}

	public int getIdCajero() {
		return idCajero;
	}


	public String getNombreCajero() {
		return nombreCajero;
	}


	public int getIdVendedor() {
		return idVendedor;
	}


	public String getNombreVendedor() {
		return nombreVendedor;
	}
	
	public String getFecha() {
		return fecha;
	}



	public String getTipoFactura() {
		return tipoFactura;
	}



	public String getNroFacturaCompleta() {
		return nroFacturaCompleta;
	}



	public int getIdSucursal() {
		return idSucursal;
	}



	public double getDescuento() {
		return descuento;
	}



	public double getTotalBruto() {
		return totalBruto;
	}



	public double getTotalFactura() {
		return totalFactura;
	}



	public String getTipoVenta() {
		return tipoVenta;
	}



	public String getCalleCliente() {
		return calleCliente;
	}



	public String getAlturaCliente() {
		return alturaCliente;
	}



	public String getPaisCliente() {
		return paisCliente;
	}



	public String getProvinciaCliente() {
		return provinciaCliente;
	}



	public String getLocalidadCliente() {
		return localidadCliente;
	}



	public String getCodPostalCliente() {
		return codPostalCliente;
	}



	public String getCuilCliente() {
		return cuilCliente;
	}



	public String getCorreoElectronicoCliente() {
		return correoElectronicoCliente;
	}



	public String getImpuestoAFIPCliente() {
		return impuestoAFIPCliente;
	}



	public double getIVA() {
		return IVA;
	}
	
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
}
