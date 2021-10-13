package modelo;

import java.util.List;

import dto.MedioPagoEgresoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.MedioPagoEgresoDAO;

public class MedioPagoEgreso {
	private MedioPagoEgresoDAO medioPagoEgreso;

	public MedioPagoEgreso(DAOAbstractFactory metodo_persistencia) {
		this.medioPagoEgreso = metodo_persistencia.createMedioPagoEgresoDAO();
	}

	public void insert(MedioPagoEgresoDTO medioPagoEgreso) {
		this.medioPagoEgreso.insert(medioPagoEgreso);
	}

	public void delete(MedioPagoEgresoDTO medioPagoEgreso_a_eliminar) {
		this.medioPagoEgreso.delete(medioPagoEgreso_a_eliminar);
	}

	public void update(String id_medioPagoEgreso_a_actualizar, MedioPagoEgresoDTO medioPagoEgreso_nuevo) {
		this.medioPagoEgreso.update(id_medioPagoEgreso_a_actualizar, medioPagoEgreso_nuevo);
	}

	public List<MedioPagoEgresoDTO> readAll() {
		return this.medioPagoEgreso.readAll();
	}

	public List<MedioPagoEgresoDTO> getMedioPagoEgresoAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		return this.medioPagoEgreso.getMedioPagoEgresoAproximado(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2);
	}
}
