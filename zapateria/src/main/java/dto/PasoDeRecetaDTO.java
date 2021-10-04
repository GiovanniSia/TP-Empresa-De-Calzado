package dto;

public class PasoDeRecetaDTO {

	private int IdPasoReceta;
	private int IdReceta;
	private int NroOrden;
	private int IdPaso;
	private PasoDTO PasosDTO;

	public PasoDeRecetaDTO(int idPasoReceta, int idReceta, int nroOrden, int idPaso, PasoDTO pasosDTO) {
		IdPasoReceta = idPasoReceta;
		IdReceta = idReceta;
		NroOrden = nroOrden;
		IdPaso = idPaso;
		PasosDTO = pasosDTO;
	}

	public int getIdPasoReceta() {
		return IdPasoReceta;
	}

	public void setIdPasoReceta(int idPasoReceta) {
		IdPasoReceta = idPasoReceta;
	}

	public int getIdReceta() {
		return IdReceta;
	}

	public void setIdReceta(int idReceta) {
		IdReceta = idReceta;
	}

	public int getNroOrden() {
		return NroOrden;
	}

	public void setNroOrden(int nroOrden) {
		NroOrden = nroOrden;
	}

	public int getIdPaso() {
		return IdPaso;
	}

	public void setIdPaso(int idPaso) {
		IdPaso = idPaso;
	}

	public PasoDTO getPasosDTO() {
		return PasosDTO;
	}

	public void setPasosDTO(PasoDTO pasosDTO) {
		PasosDTO = pasosDTO;
	}

}
