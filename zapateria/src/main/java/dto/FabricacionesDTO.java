package dto;

public class FabricacionesDTO {

	private int IdFabricacionesEnMarcha;
	private int IdOrdenFabrica;
	private int IdReceta;
	private int NroPasoActual;
	private String Estado;

	public FabricacionesDTO(int idFabricacionesEnMarcha, int idOrdenFabrica, int idReceta, int nroPasoActual,
			String estado) {
		super();
		IdFabricacionesEnMarcha = idFabricacionesEnMarcha;
		IdOrdenFabrica = idOrdenFabrica;
		IdReceta = idReceta;
		NroPasoActual = nroPasoActual;
		Estado = estado;
	}
	
	public void cancelarOrden() {
		Estado = "cancelado";
	}
	
	public void completarOrden() {
		Estado = "completo";
	}
	
	public void sumarUnPaso() {
		NroPasoActual++;
	}
	
	public void restarUnPaso() {
		if(NroPasoActual == 1) {
			return;
		}
		NroPasoActual--;
	}

	public int getIdFabricacionesEnMarcha() {
		return IdFabricacionesEnMarcha;
	}

	public void setIdFabricacionesEnMarcha(int idFabricacionesEnMarcha) {
		IdFabricacionesEnMarcha = idFabricacionesEnMarcha;
	}

	public int getIdOrdenFabrica() {
		return IdOrdenFabrica;
	}

	public void setIdOrdenFabrica(int idOrdenFabrica) {
		IdOrdenFabrica = idOrdenFabrica;
	}

	public int getIdReceta() {
		return IdReceta;
	}

	public void setIdReceta(int idReceta) {
		IdReceta = idReceta;
	}

	public int getNroPasoActual() {
		return NroPasoActual;
	}

	public void setNroPasoActual(int nroPasoActual) {
		NroPasoActual = nroPasoActual;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

}
