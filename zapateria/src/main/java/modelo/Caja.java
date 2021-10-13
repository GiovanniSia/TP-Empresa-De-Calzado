package modelo;

import java.util.List;

import dto.CajaDTO;
import dto.EmpleadoDTO;
import persistencia.dao.interfaz.CajaDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;

public class Caja {
	private CajaDAO caja;

	public Caja(DAOAbstractFactory metodo_persistencia) {
		this.caja = metodo_persistencia.createCajaDAO();
	}

	public void insert(CajaDTO caja) {
		this.caja.insert(caja);
	}

	public void delete(CajaDTO caja_a_eliminar) {
		this.caja.delete(caja_a_eliminar);
	}

	public void update(int id_caja_a_actualizar, CajaDTO caja_nuevo) {
		this.caja.update(id_caja_a_actualizar, caja_nuevo);
	}

	public List<CajaDTO> readAll() {
		return this.caja.readAll();
	}

	public List<CajaDTO> getCajaAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		return this.caja.getCajaAproximado(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2);
	}
	
	public CajaDTO getCajaDeHoy(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		return this.caja.getCajaDeHoy(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2);
	}
	
	public boolean cerrarCaja(EmpleadoDTO empleado, int idCaja) {
		return this.caja.cerrarCaja(empleado,idCaja);
	}
	
}
