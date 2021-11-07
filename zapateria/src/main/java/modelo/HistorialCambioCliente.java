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
	
}
