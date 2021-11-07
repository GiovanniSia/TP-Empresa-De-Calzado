package dto;
/*
CREATE TABLE `historialCambioEmpleados`
(
 1 `IdHistorialCambioEmpleados` int(11) NOT NULL AUTO_INCREMENT,
 2 `IdEmpleadoResponsable` varchar(45) NOT NULL,
 3 `Fecha` Date NOT NULL,
 4 `IdSucursal` varchar(45) NOT NULL,
 5 `IdEmpleado` varchar(45) NOT NULL,
 6 `CUILAntiguo` varchar(45) NOT NULL,
 7 `CUILNuevo` varchar(45) NOT NULL,
 8 `NombreAntiguo` varchar(45) NOT NULL,
 9 `NombreNuevo` varchar(45) NOT NULL,
 10 `ApellidoAntiguo` varchar(45) NOT NULL,
 11 `ApellidoNuevo` varchar(45) NOT NULL,
 12 `CorreoElectronicoAntiguo` varchar(45) NOT NULL,
 13 `CorreoElectronicoNuevo` varchar(45) NOT NULL,
 14 `TipoEmpleadoAntiguo` varchar(45) NOT NULL,
 15 `TipoEmpleadoNuevo` varchar(45) NOT NULL,
  PRIMARY KEY (`IdHistorialCambioEmpleados`)
);
*/

public class HistorialCambioEmpleadoDTO {
	private int IdHistorialCambioEmpleados;
	private String IdEmpleadoResponsable;
	private String Fecha;
	private String IdSucursal;
	private String IdEmpleado;
	private String CUILAntiguo;
	private String CUILNuevo;
	private String NombreAntiguo;
	private String NombreNuevo;
	private String ApellidoAntiguo;
	private String ApellidoNuevo;
	private String CorreoElectronicoAntiguo;
	private String CorreoElectronicoNuevo;
	private String TipoEmpleadoAntiguo;
	private String TipoEmpleadoNuevo;

	public HistorialCambioEmpleadoDTO(int idHistorialCambioEmpleados, String idEmpleadoResponsable, String fecha,
			String idSucursal, String idEmpleado, String cUILAntiguo, String cUILNuevo, String nombreAntiguo,
			String nombreNuevo, String apellidoAntiguo, String apellidoNuevo, String correoElectronicoAntiguo,
			String correoElectronicoNuevo, String tipoEmpleadoAntiguo, String tipoEmpleadoNuevo) {
		super();
		IdHistorialCambioEmpleados = idHistorialCambioEmpleados;
		IdEmpleadoResponsable = idEmpleadoResponsable;
		Fecha = fecha;
		IdSucursal = idSucursal;
		IdEmpleado = idEmpleado;
		CUILAntiguo = cUILAntiguo;
		CUILNuevo = cUILNuevo;
		NombreAntiguo = nombreAntiguo;
		NombreNuevo = nombreNuevo;
		ApellidoAntiguo = apellidoAntiguo;
		ApellidoNuevo = apellidoNuevo;
		CorreoElectronicoAntiguo = correoElectronicoAntiguo;
		CorreoElectronicoNuevo = correoElectronicoNuevo;
		TipoEmpleadoAntiguo = tipoEmpleadoAntiguo;
		TipoEmpleadoNuevo = tipoEmpleadoNuevo;
	}

	public int getIdHistorialCambioEmpleados() {
		return IdHistorialCambioEmpleados;
	}

	public void setIdHistorialCambioEmpleados(int idHistorialCambioEmpleados) {
		IdHistorialCambioEmpleados = idHistorialCambioEmpleados;
	}

	public String getIdEmpleadoResponsable() {
		return IdEmpleadoResponsable;
	}

	public void setIdEmpleadoResponsable(String idEmpleadoResponsable) {
		IdEmpleadoResponsable = idEmpleadoResponsable;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getIdSucursal() {
		return IdSucursal;
	}

	public void setIdSucursal(String idSucursal) {
		IdSucursal = idSucursal;
	}

	public String getIdEmpleado() {
		return IdEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		IdEmpleado = idEmpleado;
	}

	public String getCUILAntiguo() {
		return CUILAntiguo;
	}

	public void setCUILAntiguo(String cUILAntiguo) {
		CUILAntiguo = cUILAntiguo;
	}

	public String getCUILNuevo() {
		return CUILNuevo;
	}

	public void setCUILNuevo(String cUILNuevo) {
		CUILNuevo = cUILNuevo;
	}

	public String getNombreAntiguo() {
		return NombreAntiguo;
	}

	public void setNombreAntiguo(String nombreAntiguo) {
		NombreAntiguo = nombreAntiguo;
	}

	public String getNombreNuevo() {
		return NombreNuevo;
	}

	public void setNombreNuevo(String nombreNuevo) {
		NombreNuevo = nombreNuevo;
	}

	public String getApellidoAntiguo() {
		return ApellidoAntiguo;
	}

	public void setApellidoAntiguo(String apellidoAntiguo) {
		ApellidoAntiguo = apellidoAntiguo;
	}

	public String getApellidoNuevo() {
		return ApellidoNuevo;
	}

	public void setApellidoNuevo(String apellidoNuevo) {
		ApellidoNuevo = apellidoNuevo;
	}

	public String getCorreoElectronicoAntiguo() {
		return CorreoElectronicoAntiguo;
	}

	public void setCorreoElectronicoAntiguo(String correoElectronicoAntiguo) {
		CorreoElectronicoAntiguo = correoElectronicoAntiguo;
	}

	public String getCorreoElectronicoNuevo() {
		return CorreoElectronicoNuevo;
	}

	public void setCorreoElectronicoNuevo(String correoElectronicoNuevo) {
		CorreoElectronicoNuevo = correoElectronicoNuevo;
	}

	public String getTipoEmpleadoAntiguo() {
		return TipoEmpleadoAntiguo;
	}

	public void setTipoEmpleadoAntiguo(String tipoEmpleadoAntiguo) {
		TipoEmpleadoAntiguo = tipoEmpleadoAntiguo;
	}

	public String getTipoEmpleadoNuevo() {
		return TipoEmpleadoNuevo;
	}

	public void setTipoEmpleadoNuevo(String tipoEmpleadoNuevo) {
		TipoEmpleadoNuevo = tipoEmpleadoNuevo;
	}

}
