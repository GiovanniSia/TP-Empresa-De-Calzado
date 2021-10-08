package persistencia.dao.interfaz;

import java.util.List;

import dto.ClienteDTO;

public interface ClienteDAO {

	public boolean insert(ClienteDTO cliente);

	public boolean delete(ClienteDTO cliente_a_eliminar);
	
	public boolean update(int id_cliente_a_actualizar, ClienteDTO cliente_nuevo);

	public List<ClienteDTO> readAll();
	
	public List<ClienteDTO> filtrarPorCodCliente(String valor);
	
	public List<ClienteDTO> filtrarPorNombre(String valor);
	
	public List<ClienteDTO> filtrarPorApellido(String valor);
	
	public List<ClienteDTO> filtradoPorDNI(String valor);
	
	public ClienteDTO selectCliente(int idCliente);
}
