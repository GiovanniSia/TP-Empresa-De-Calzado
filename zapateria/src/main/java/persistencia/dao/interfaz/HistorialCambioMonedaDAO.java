package persistencia.dao.interfaz;

import java.util.List;

import dto.HistorialCambioMonedaDTO;


public interface HistorialCambioMonedaDAO {
	boolean insert(HistorialCambioMonedaDTO historialCambioMoneda);

	boolean delete(HistorialCambioMonedaDTO historialCambioMoneda);

	boolean update(int id_historialCambioMoneda_a_actualizar, HistorialCambioMonedaDTO historialCambioMoneda_nuevo);

	public List<HistorialCambioMonedaDTO> readAll();

	public List<HistorialCambioMonedaDTO> getHistorialCambioMonedaAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2,String nombreColumna3, String fechaDede, String fechaHasta );
}
