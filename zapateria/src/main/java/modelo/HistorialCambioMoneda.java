package modelo;

import java.util.List;

import dto.HistorialCambioMonedaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HistorialCambioMonedaDAO;

public class HistorialCambioMoneda {
	private HistorialCambioMonedaDAO historialCambioMoneda;

	public HistorialCambioMoneda(DAOAbstractFactory metodo_persistencia) {
		this.historialCambioMoneda = metodo_persistencia.createHistorialCambioMonedaDAO();
	}

	public void insert(HistorialCambioMonedaDTO historialCambioMoneda) {
		this.historialCambioMoneda.insert(historialCambioMoneda);
	}

	public void delete(HistorialCambioMonedaDTO historialCambioMoneda_a_eliminar) {
		this.historialCambioMoneda.delete(historialCambioMoneda_a_eliminar);
	}

	public void update(int id_historialCambioMoneda_a_actualizar,
			HistorialCambioMonedaDTO historialCambioMoneda_nuevo) {
		this.historialCambioMoneda.update(id_historialCambioMoneda_a_actualizar, historialCambioMoneda_nuevo);
	}

	public List<HistorialCambioMonedaDTO> readAll() {
		return this.historialCambioMoneda.readAll();
	}

	public List<HistorialCambioMonedaDTO> getHistorialCambioMonedaAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3) {
		return this.historialCambioMoneda.getHistorialCambioMonedaAproximado(nombreColumna1, txtAprox1, nombreColumna2,
				txtAprox2, nombreColumna3, txtAprox3);
	}
}
