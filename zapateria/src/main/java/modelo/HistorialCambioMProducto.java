package modelo;

import java.util.List;

import dto.HistorialCambioMProductoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HistorialCambioMProductoDAO;

public class HistorialCambioMProducto {

	private HistorialCambioMProductoDAO historialCambioMProducto;

	public HistorialCambioMProducto(DAOAbstractFactory metodo_persistencia) {
		this.historialCambioMProducto = metodo_persistencia.createHistorialCambioMProductoDAO();
	}

	public void insert(HistorialCambioMProductoDTO historialCambioMProducto) {
		this.historialCambioMProducto.insert(historialCambioMProducto);
	}

	public void delete(HistorialCambioMProductoDTO historialCambioMProducto_a_eliminar) {
		this.historialCambioMProducto.delete(historialCambioMProducto_a_eliminar);
	}

	public void update(int id_historialCambioMProducto_a_actualizar,
			HistorialCambioMProductoDTO historialCambioMProducto_nuevo) {
		this.historialCambioMProducto.update(id_historialCambioMProducto_a_actualizar, historialCambioMProducto_nuevo);
	}

	public List<HistorialCambioMProductoDTO> readAll() {
		return this.historialCambioMProducto.readAll();
	}

	public List<HistorialCambioMProductoDTO> getFiltroHistorialCambioMProducto(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3, String nombreColumna4,
			String txtAprox4) {
		return this.historialCambioMProducto.getFiltroHistorialCambioMProducto(nombreColumna1, txtAprox1,
				nombreColumna2, txtAprox2, nombreColumna3, txtAprox3, nombreColumna4, txtAprox4);
	}

}
