package persistencia.dao.interfaz;

import java.util.List;
import dto.ClienteDTO;

public interface ClienteDAO {

	public boolean insert(ClienteDTO cliente);

	public boolean delete(ClienteDTO cliente_a_eliminar);
	
	public boolean update(int id_cliente_a_actualizar, ClienteDTO cliente_nuevo);

	public List<ClienteDTO> readAll();
	
	public List<ClienteDTO> getClienteAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2,String nombreColumna3, String txtAprox3, String nombreColumna4,
			String txtAprox4);

	public ClienteDTO selectCliente(int idCliente);
}
