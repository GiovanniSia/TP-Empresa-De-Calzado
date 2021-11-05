package modelo;

import java.util.List;

import dto.HistorialCambioEmpleadoDTO;
import persistencia.dao.interfaz.HistorialCambioEmpleadoDAO;

public class HistorialCambioEmpleado {
	private HistorialCambioEmpleadoDAO historialCambioEmpleado;
	public boolean insert(HistorialCambioEmpleadoDTO historialCambioEmpleado)  {
		return this.historialCambioEmpleado.insert(historialCambioEmpleado);
	}

	public List<HistorialCambioEmpleadoDTO> readAll() {
		return this.historialCambioEmpleado.readAll();
	}

	public List<HistorialCambioEmpleadoDTO> getFiltrarPor(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3) {
		return this.historialCambioEmpleado.getFiltrarPor(nombreColumna1, txtAprox1, nombreColumna2,
				txtAprox2, nombreColumna3, txtAprox3);
	}
}
