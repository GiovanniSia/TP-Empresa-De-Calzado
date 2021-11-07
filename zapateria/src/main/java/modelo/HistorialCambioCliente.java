package modelo;

import java.util.List;

import dto.HistorialCambioClienteDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HistorialCambioClienteDAO;

public class HistorialCambioCliente {
	
	private HistorialCambioClienteDAO historial;
	
	public HistorialCambioCliente(DAOAbstractFactory metodo_persistencia) {
		this.historial = metodo_persistencia.createHistorialCambioClienteDAO();
	}
	
	public boolean insert(HistorialCambioClienteDTO historial) {
		return this.historial.insert(historial);
	}

	public List<HistorialCambioClienteDTO> readAll(){
		return this.historial.readAll();
	}
	
	public List<HistorialCambioClienteDTO> obtenerListaFiltrada(String nombreColumna1,String txt1,String nombreColumna2,String txt2,String nombreColumna3,String txt3,String nombreColumna4,String txt4,String nombreColumna5,String txt5) {
		return this.historial.obtenerListaFiltrada(nombreColumna1, txt1, nombreColumna2, txt2, nombreColumna3, txt3, nombreColumna4, txt4, nombreColumna5, txt5);
	}
	
}
