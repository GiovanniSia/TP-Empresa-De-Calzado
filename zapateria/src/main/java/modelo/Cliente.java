package modelo;

import java.util.List;
import dto.ClienteDTO;
import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;

public class Cliente {

	private ClienteDAO cliente;

	public Cliente(DAOAbstractFactory metodo_persistencia) {
		this.cliente = metodo_persistencia.createClienteDAO();
	}

	public void insert(ClienteDTO cliente) {
		this.cliente.insert(cliente);
	}

	public void delete(ClienteDTO cliente_a_eliminar) {
		this.cliente.delete(cliente_a_eliminar);
	}

	public void update(int id_cliente_a_actualizar, ClienteDTO cliente_nuevo) {
		this.cliente.update(id_cliente_a_actualizar, cliente_nuevo);
	}

	public List<ClienteDTO> readAll() {
		return this.cliente.readAll();
	}

	public List<ClienteDTO> getClienteAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2,
			String txtAprox2,String nombreColumna3, String txtAprox3, String nombreColumna4,
			String txtAprox4) {
		return this.cliente.getClienteAproximado(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2, nombreColumna3,  txtAprox3,  nombreColumna4,
				 txtAprox4);
	}

}
