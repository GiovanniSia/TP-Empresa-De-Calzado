package persistencia.dao.interfaz;

import java.util.List;

import dto.HistorialCambioClienteDTO;

public interface HistorialCambioClienteDAO {
	
	public boolean insert(HistorialCambioClienteDTO historialCambioCliente);

	public boolean delete(HistorialCambioClienteDTO historialCC);
	
	public boolean update(int idHistorialCambioCliente, HistorialCambioClienteDTO historialCC);

	public List<HistorialCambioClienteDTO> readAll();
	
	public List<HistorialCambioClienteDTO> obtenerListaFiltrada(String nombreColumna1,String txt1,String nombreColumna2,String txt2,String nombreColumna3,String txt3,String nombreColumna4,String txt4,String nombreColumna5,String txt5) ;

}
