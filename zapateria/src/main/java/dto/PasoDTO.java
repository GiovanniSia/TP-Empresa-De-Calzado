package dto;

import java.util.List;

public class PasoDTO {

	private int IdPaso;
	private String Descripcion;

	private List<MaestroProductoDTO> materiales;
	private List<Integer> cantidadUsada;

	public PasoDTO(int idPaso, String descripcion, List<MaestroProductoDTO> materiales,
			List<Integer> cantidadUsada) {
		super();
		IdPaso = idPaso;
		Descripcion = descripcion;
		this.materiales = materiales;
		this.cantidadUsada = cantidadUsada;
	}

	public List<Integer> getCantidadUsada() {
		return cantidadUsada;
	}

	public void setCantidadUsada(List<Integer> cantidadUsada) {
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

}
