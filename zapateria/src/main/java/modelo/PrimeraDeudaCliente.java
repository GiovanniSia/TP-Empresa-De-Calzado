package modelo;

import java.util.List;

import dto.PrimeraDeudaClienteDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PrimeraDeudaClienteDAO;

public class PrimeraDeudaCliente {
	private PrimeraDeudaClienteDAO primeraDeudaCliente;

	public PrimeraDeudaCliente(DAOAbstractFactory metodo_persistencia) {
		this.primeraDeudaCliente = metodo_persistencia.createPrimeraDeudaClienteDAO();
	}

	public void insert(PrimeraDeudaClienteDTO primeraDeudaCliente) {
		this.primeraDeudaCliente.insert(primeraDeudaCliente);
	}

	public void delete(PrimeraDeudaClienteDTO primeraDeudaCliente_a_eliminar) {
		this.primeraDeudaCliente.delete(primeraDeudaCliente_a_eliminar);
	}

	public void update(int id_primeraDeudaCliente_a_actualizar, PrimeraDeudaClienteDTO primeraDeudaCliente_nuevo) {
		this.primeraDeudaCliente.update(id_primeraDeudaCliente_a_actualizar, primeraDeudaCliente_nuevo);
	}

	public List<PrimeraDeudaClienteDTO> readAll() {
		return this.primeraDeudaCliente.readAll();
	}

	public List<PrimeraDeudaClienteDTO> getFiltrarPor(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		return this.primeraDeudaCliente.getFiltrarPor(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2);
	}
}
