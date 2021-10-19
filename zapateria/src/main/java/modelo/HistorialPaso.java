package modelo;

import java.util.List;

import dto.EmpleadoDTO;
import dto.HistorialPasoDTO;
import persistencia.dao.interfaz.CajaDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HistorialPasoDAO;

public class HistorialPaso {
	private HistorialPasoDAO caja;

	public HistorialPaso(DAOAbstractFactory metodo_persistencia) {
		this.caja = metodo_persistencia.createHistorialPasoDAO();
	}

	public void insert(HistorialPasoDTO historialPaso) {
		this.caja.insert(historialPaso);
	}

	public List<HistorialPasoDTO> readAll() {
		return this.caja.readAll();
	}
}
