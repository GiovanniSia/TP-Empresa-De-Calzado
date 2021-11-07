package persistencia.dao.interfaz;

import java.util.List;

import dto.HistorialCambioClienteDTO;

public interface HistorialCambioClienteDAO {
	
	public boolean insert(HistorialCambioClienteDTO historialCambioCliente);

	public boolean delete(HistorialCambioClienteDTO historialCC);
	
	public boolean update(int idHistorialCambioCliente, HistorialCambioClienteDTO historialCC);

	public List<HistorialCambioClienteDTO> readAll();
	
	public List<HistorialCambioClienteDTO> getHistorialCambioClienteAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2);

}
