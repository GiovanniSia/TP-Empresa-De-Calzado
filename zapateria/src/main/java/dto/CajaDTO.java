package dto;

/*
CREATE TABLE `Caja`
(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Hora` TIME NOT NULL,
  `Apertura` int(11) NOT NULL, (idEmpleado)
  `Cierre` int(11) NOT NULL,   (idEmpleado)
  `AperturaNombre` varchar(45) NOT NULL,
  `CierreNombre` varchar(45) NOT NULL,
  `AuditApertura` TIME NOT NULL,
  `AuditCierre` TIME NOT NULL,
  PRIMARY KEY (`IdCaja`)
);

insert into caja values(0,1,"2021-10-13","10:10:00",2,0,"Juan","Carlos","10:10:00","20:21:00");
insert into caja values(0,1,"2021-10-14","10:10:00",2,0,"Juan","Carlos","10:10:00","20:21:00");
insert into caja values(0,1,"2021-10-15","10:10:00",2,0,"Juan","Carlos","10:10:00","20:21:00");
insert into caja values(0,2,"2021-10-12","10:10:00",2,0,"Juan","Carlos","10:10:00","20:21:00");
insert into caja values(0,2,"2021-10-13","10:10:00",2,0,"Juan","Carlos","10:10:00","20:21:00");
insert into caja values(0,2,"2021-10-14","10:10:00",2,0,"Juan","Carlos","10:10:00","20:21:00");
*/
public class CajaDTO {
	int idCaja;
	int idSucursal;
	String fecha;
	String hora;
	int apertura;
	int cierre;
	String aperturaNombre;
	String cierreNombre;
	String auditApertura;
	String auditCierre;

	public CajaDTO(int idCaja, int idSucursal, String fecha, String hora, int apertura, int cierre,
			String aperturaNombre, String cierreNombre, String auditApertura, String auditCierre) {
		super();
		this.idCaja = idCaja;
		this.idSucursal = idSucursal;
		this.fecha = fecha;
		this.hora = hora;
		this.apertura = apertura;
		this.cierre = cierre;
		this.aperturaNombre = aperturaNombre;
		this.cierreNombre = cierreNombre;
		this.auditApertura = auditApertura;
		this.auditCierre = auditCierre;
	}

	public int getIdCaja() {
		return idCaja;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public String getFecha() {
		return fecha;
	}

	public String getHora() {
		return hora;
	}

	public int getApertura() {
		return apertura;
	}

	public int getCierre() {
		return cierre;
	}

	public String getAperturaNombre() {
		return aperturaNombre;
	}

	public String getCierreNombre() {
		return cierreNombre;
	}

	public String getAuditApertura() {
		return auditApertura;
	}

	public String getAuditCierre() {
		return auditCierre;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setApertura(int apertura) {
		this.apertura = apertura;
	}

	public void setCierre(int cierre) {
		this.cierre = cierre;
	}

	public void setAperturaNombre(String aperturaNombre) {
		this.aperturaNombre = aperturaNombre;
	}

	public void setCierreNombre(String cierreNombre) {
		this.cierreNombre = cierreNombre;
	}

	public void setAuditApertura(String auditApertura) {
		this.auditApertura = auditApertura;
	}

	public void setAuditCierre(String auditCierre) {
		this.auditCierre = auditCierre;
	}

}
