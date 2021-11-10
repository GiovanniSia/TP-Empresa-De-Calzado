package dto;

import java.util.ArrayList;
import java.util.List;

public class PasoDTO {

	private int IdPaso;
	private String Descripcion;

	private List<MaestroProductoDTO> materiales;
	private List<Double> cantidadUsada;
	
	private String estado;

	public PasoDTO(int idPaso, String descripcion, List<MaestroProductoDTO> materiales,
			List<Double> cantidadUsada, String estado) {
		super();
		IdPaso = idPaso;
		Descripcion = descripcion;
		this.materiales = materiales;
		this.cantidadUsada = cantidadUsada;
		this.estado = estado;
	}
	
	public PasoDTO(int idPaso, String descripcion, String estado) {
		super();
		IdPaso = idPaso;
		Descripcion = descripcion;
		this.materiales = new ArrayList<MaestroProductoDTO>();
		this.cantidadUsada = new ArrayList<Double>();
		this.estado = estado;
	}

	public List<Double> getCantidadUsada() {
		return cantidadUsada;
	}

	public void setCantidadUsada(List<Double> cantidadUsada) {
		this.cantidadUsada = cantidadUsada;
	}

	public int getIdPaso() {
		return IdPaso;
	}

	public void setIdPaso(int idPaso) {
		IdPaso = idPaso;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public List<MaestroProductoDTO> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<MaestroProductoDTO> materiales) {
		this.materiales = materiales;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
