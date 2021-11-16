package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dto.ClienteDTO;
import dto.PrimeraDeudaClienteDTO;
import persistencia.dao.mysql.DAOSQLFactory;

public class CambiarEstadoMorosoClienteAutomaticamente extends Thread {
	
	public void run() {
		try {
			CambiarEstadoMorosoClienteAutomaticamente.cambiarEstadoClientesAMorosos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cambiarEstadoClientesAMorosos() {
		PrimeraDeudaCliente primeraDeudaCliente = new PrimeraDeudaCliente(new DAOSQLFactory());
		List<PrimeraDeudaClienteDTO> listaPrimeraDeudaCliente = primeraDeudaCliente.readAll();

		for (PrimeraDeudaClienteDTO p : listaPrimeraDeudaCliente) {
			int diasTotales = calcularDiasTotales(p.getFechaDeuda());
			if(superaElLimiteParaSerMoroso(diasTotales)) {
				pasarClienteAEstadoMoroso(p.getIdCliente());
			}
		}

	}
	
	public static void pasarClienteAEstadoMoroso(int idCliente) {
		if (obtenerPrimeraDeudaClienteDeCliente(idCliente) != null) {
			PrimeraDeudaCliente primeraDeudaCliente = new PrimeraDeudaCliente(new DAOSQLFactory());
			PrimeraDeudaClienteDTO clienteMorosoEnPrimeraDeudaCliente = obtenerPrimeraDeudaClienteDeCliente(idCliente);
			Cliente cliente = new Cliente(new DAOSQLFactory());
			ClienteDTO clienteElegido = cliente.selectCliente(idCliente);
			clienteElegido.setEstado("Moroso");
			cliente.update(idCliente, clienteElegido);
			primeraDeudaCliente.delete(clienteMorosoEnPrimeraDeudaCliente);
		}
	}

	public static PrimeraDeudaClienteDTO obtenerPrimeraDeudaClienteDeCliente(int idCliente) {
		PrimeraDeudaCliente primeraDeudaCliente = new PrimeraDeudaCliente(new DAOSQLFactory());
		List<PrimeraDeudaClienteDTO> listaPrimeraDeudaCliente;
		listaPrimeraDeudaCliente = primeraDeudaCliente.readAll();
		for (PrimeraDeudaClienteDTO p : listaPrimeraDeudaCliente) {
			if (p.getIdCliente() == idCliente) {
				return new PrimeraDeudaClienteDTO(p.getId(), p.getIdCliente(), p.getFechaDeuda());
			}
		}
		return null;
	}
	
	public static int calcularDiasTotales(String fechaDeuda) {

		String Dateinicio = fechaDeuda;
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaInicio = null;
		try {
			fechaInicio = date.parse(Dateinicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date fechaactual = new Date(System.currentTimeMillis());

		int milisecondsByDay = 86400000;
		int dias = (int) ((fechaactual.getTime() - fechaInicio.getTime()) / milisecondsByDay);

		return dias;
	}

	public static boolean superaElLimiteParaSerMoroso(int diasTotales) {
		if(diasTotales >=30) {
			return true;
		}
		return false;
	}
	
}
