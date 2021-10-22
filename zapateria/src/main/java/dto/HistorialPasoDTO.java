package dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistorialPasoDTO {

	private int id;
	private String hora;
	private String Fecha;
	private int IdOrden;
	private int IdEmpleado;
	private String NombreCompleto;
	private String DescrPasoCompletado;
	private String Descr;
	private String tipo;

	public HistorialPasoDTO(int id, String hora, String fecha, int idOrden, int idEmpleado, String nombreCompleto,
			String descrPasoCompletado, String descr, String tipo) {
		this.id = id;
		this.hora = hora;
		Fecha = fecha;
		IdOrden = idOrden;
		IdEmpleado = idEmpleado;
		NombreCompleto = nombreCompleto;
		DescrPasoCompletado = descrPasoCompletado;
		Descr = descr;
		this.tipo = tipo;
	}

	public HistorialPasoDTO(int id, int idOrden, int idEmpleado, String nombreCompleto, String descrPasoCompletado,
			String descr, String tipo) {
		this.id = id;
		IdOrden = idOrden;
		IdEmpleado = idEmpleado;
		NombreCompleto = nombreCompleto;
		DescrPasoCompletado = descrPasoCompletado;
		Descr = descr;
		this.tipo = tipo;

		Fecha = generarFechaActual();
		hora = generarHoraActual();
	}

	private String generarFechaActual() {
		java.util.Date fecha = new java.util.Date();
		int diaCompletado = fecha.getDate();
		int mesCompletado = fecha.getMonth() + 1;
		int anioCompletado = fecha.getYear() + 1900;
		String fecha1 = anioCompletado + "-" + mesCompletado + "-" + diaCompletado;
		return fecha1;
	}

	private String generarHoraActual() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		String hora = dtf.format(LocalDateTime.now());
		return hora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public int getIdOrden() {
		return IdOrden;
	}

	public void setIdOrden(int idOrden) {
		IdOrden = idOrden;
	}

	public int getIdEmpleado() {
		return IdEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		IdEmpleado = idEmpleado;
	}

	public String getNombreCompleto() {
		return NombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}

	public String getDescrPasoCompletado() {
		return DescrPasoCompletado;
	}

	public void setDescrPasoCompletado(String descrPasoCompletado) {
		DescrPasoCompletado = descrPasoCompletado;
	}

	public String getDescr() {
		return Descr;
	}

	public void setDescr(String descr) {
		Descr = descr;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
