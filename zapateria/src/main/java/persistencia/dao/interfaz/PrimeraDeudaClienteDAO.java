package persistencia.dao.interfaz;

import java.util.List;

import dto.PrimeraDeudaClienteDTO;

public interface PrimeraDeudaClienteDAO {
	public boolean insert(PrimeraDeudaClienteDTO primeraDeudaCliente);

	public boolean delete(PrimeraDeudaClienteDTO primeraDeudaCliente_a_eliminar);
	
	public boolean update(int id_primeraDeudaCliente_a_actualizar, PrimeraDeudaClienteDTO primeraDeudaCliente_nuevo);

	public List<PrimeraDeudaClienteDTO> readAll();
	
	public List<PrimeraDeudaClienteDTO> getFiltrarPor(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2);
}
