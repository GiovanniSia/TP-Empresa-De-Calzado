package persistencia.dao.mysql;

import java.util.List;

import dto.HistorialCambioClienteDTO;
import persistencia.dao.interfaz.HistorialCambioClienteDAO;

public class HistorialCambioClienteDAOSQL implements HistorialCambioClienteDAO{

	@Override
	public boolean insert(HistorialCambioClienteDTO historialCambioCliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(HistorialCambioClienteDTO historialCC) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int idHistorialCambioCliente, HistorialCambioClienteDTO historialCC) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<HistorialCambioClienteDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistorialCambioClienteDTO> getCajaAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		// TODO Auto-generated method stub
		return null;
	}

}
