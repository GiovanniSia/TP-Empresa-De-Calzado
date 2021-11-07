package dto;

public class HistorialCambioClienteDTO {
	int Id;
	int IdEmpleado;
	String fecha;
	String nombreAntiguo,nombreNuevo,apellidoAntiguo,apellidoNuevo,CUILAntiguo,CUILNuevo,correoAntiguo,correoNuevo;
	double limiteCreditoAntiguo,limiteCreditoNuevo,creditoDisponibleAntiguo,creditoDisponibleNuevo;
	String tipoClienteAntiguo,tipoClienteNuevo,impuestoAFIPAntiguo,impuestoAFIPNuevo,estadoAntiguo,estadoNuevo,calleAntiguo,calleNuevo,alturaAntiguo,alturaNuevo,paisAntiguo,paisNuevo,provinciaAntiguo,provinciaNuevo,localidadAntiguo,localidadNuevo,codPostalAntiguo,codPostalNuevo;
	
	
	public HistorialCambioClienteDTO(int id, int idEmpleado, String fecha, String nombreAntiguo, String nombreNuevo,
			String apellidoAntiguo, String apellidoNuevo, String cUILAntiguo, String cUILNuevo, String correoAntiguo,
			String correoNuevo, double limiteCreditoAntiguo, double limiteCreditoNuevo, double creditoDisponibleAntiguo,
			double creditoDisponibleNuevo, String tipoClienteAntiguo, String tipoClienteNuevo,
			String impuestoAFIPAntiguo, String impuestoAFIPNuevo, String estadoAntiguo, String estadoNuevo,
			String calleAntiguo, String calleNuevo, String alturaAntiguo, String alturaNuevo, String paisAntiguo,
			String paisNuevo, String provinciaAntiguo, String provinciaNuevo, String localidadAntiguo,
			String localidadNuevo, String codPostalAntiguo, String codPostalNuevo) {
		super();
		Id = id;
		IdEmpleado = idEmpleado;
		this.fecha = fecha;
		this.nombreAntiguo = nombreAntiguo;
		this.nombreNuevo = nombreNuevo;
		this.apellidoAntiguo = apellidoAntiguo;
		this.apellidoNuevo = apellidoNuevo;
		CUILAntiguo = cUILAntiguo;
		CUILNuevo = cUILNuevo;
		this.correoAntiguo = correoAntiguo;
		this.correoNuevo = correoNuevo;
		this.limiteCreditoAntiguo = limiteCreditoAntiguo;
		this.limiteCreditoNuevo = limiteCreditoNuevo;
		this.creditoDisponibleAntiguo = creditoDisponibleAntiguo;
		this.creditoDisponibleNuevo = creditoDisponibleNuevo;
		this.tipoClienteAntiguo = tipoClienteAntiguo;
		this.tipoClienteNuevo = tipoClienteNuevo;
		this.impuestoAFIPAntiguo = impuestoAFIPAntiguo;
		this.impuestoAFIPNuevo = impuestoAFIPNuevo;
		this.estadoAntiguo = estadoAntiguo;
		this.estadoNuevo = estadoNuevo;
		this.calleAntiguo = calleAntiguo;
		this.calleNuevo = calleNuevo;
		this.alturaAntiguo = alturaAntiguo;
		this.alturaNuevo = alturaNuevo;
		this.paisAntiguo = paisAntiguo;
		this.paisNuevo = paisNuevo;
		this.provinciaAntiguo = provinciaAntiguo;
		this.provinciaNuevo = provinciaNuevo;
		this.localidadAntiguo = localidadAntiguo;
		this.localidadNuevo = localidadNuevo;
		this.codPostalAntiguo = codPostalAntiguo;
		this.codPostalNuevo = codPostalNuevo;
	}
	
	public int getId() {
		return Id;
	}
	public int getIdEmpleado() {
		return IdEmpleado;
	}
	public String getFecha() {
		return fecha;
	}
	public String getNombreAntiguo() {
		return nombreAntiguo;
	}
	public String getNombreNuevo() {
		return nombreNuevo;
	}
	public String getApellidoAntiguo() {
		return apellidoAntiguo;
	}
	public String getApellidoNuevo() {
		return apellidoNuevo;
	}
	public String getCUILAntiguo() {
		return CUILAntiguo;
	}
	public String getCUILNuevo() {
		return CUILNuevo;
	}
	public String getCorreoAntiguo() {
		return correoAntiguo;
	}
	public String getCorreoNuevo() {
		return correoNuevo;
	}
	public double getLimiteCreditoAntiguo() {
		return limiteCreditoAntiguo;
	}
	public double getLimiteCreditoNuevo() {
		return limiteCreditoNuevo;
	}
	public double getCreditoDisponibleAntiguo() {
		return creditoDisponibleAntiguo;
	}
	public double getCreditoDisponibleNuevo() {
		return creditoDisponibleNuevo;
	}
	public String getTipoClienteAntiguo() {
		return tipoClienteAntiguo;
	}
	public String getTipoClienteNuevo() {
		return tipoClienteNuevo;
	}
	public String getImpuestoAFIPAntiguo() {
		return impuestoAFIPAntiguo;
	}
	public String getImpuestoAFIPNuevo() {
		return impuestoAFIPNuevo;
	}
	public String getEstadoAntiguo() {
		return estadoAntiguo;
	}
	public String getEstadoNuevo() {
		return estadoNuevo;
	}
	public String getCalleAntiguo() {
		return calleAntiguo;
	}
	public String getCalleNuevo() {
		return calleNuevo;
	}
	public String getAlturaAntiguo() {
		return alturaAntiguo;
	}
	public String getAlturaNuevo() {
		return alturaNuevo;
	}
	public String getPaisAntiguo() {
		return paisAntiguo;
	}
	public String getPaisNuevo() {
		return paisNuevo;
	}
	public String getProvinciaAntiguo() {
		return provinciaAntiguo;
	}
	public String getProvinciaNuevo() {
		return provinciaNuevo;
	}
	public String getLocalidadAntiguo() {
		return localidadAntiguo;
	}
	public String getLocalidadNuevo() {
		return localidadNuevo;
	}
	public String getCodPostalAntiguo() {
		return codPostalAntiguo;
	}
	public String getCodPostalNuevo() {
		return codPostalNuevo;
	}	

	
}
