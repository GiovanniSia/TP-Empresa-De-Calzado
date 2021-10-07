package persistencia.dao.interfaz;

import java.util.List;

import dto.MedioPagoDTO;

public interface MedioPagoDAO {
	boolean insert(MedioPagoDTO medioPago);

	boolean delete(MedioPagoDTO medioPago);

	boolean update(String id_medioPago_a_actualizar, MedioPagoDTO medioPago_nuevo);

	public List<MedioPagoDTO> readAll();

	public List<MedioPagoDTO> getMedioPagoAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2);
}
