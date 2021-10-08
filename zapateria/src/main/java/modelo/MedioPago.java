package modelo;

import java.util.List;

import dto.MedioPagoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.MedioPagoDAO;

public class MedioPago {
	private MedioPagoDAO medioPago;

	public MedioPago(DAOAbstractFactory metodo_persistencia) {
		this.medioPago = metodo_persistencia.createMedioPagoDAO();
	}

	public void insert(MedioPagoDTO medioPago) {
		this.medioPago.insert(medioPago);
	}

	public void delete(MedioPagoDTO medioPago_a_eliminar) {
		this.medioPago.delete(medioPago_a_eliminar);
	}

	public void update(String id_medioPago_a_actualizar, MedioPagoDTO medioPago_nuevo) {
		this.medioPago.update(id_medioPago_a_actualizar, medioPago_nuevo);
	}

	public List<MedioPagoDTO> readAll() {
		return this.medioPago.readAll();
	}

	public List<MedioPagoDTO> getMedioPagoAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		return this.medioPago.getMedioPagoAproximado(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2);
	}
}
