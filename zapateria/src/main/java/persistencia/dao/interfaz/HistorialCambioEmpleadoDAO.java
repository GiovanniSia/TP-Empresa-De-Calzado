package persistencia.dao.interfaz;

import java.util.List;

import dto.HistorialCambioEmpleadoDTO;

public interface HistorialCambioEmpleadoDAO {
	public boolean insert(HistorialCambioEmpleadoDTO historialCambioEmpleado);

	public List<HistorialCambioEmpleadoDTO> readAll();
	
	public List<HistorialCambioEmpleadoDTO> getFiltrarPor(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3);

}
