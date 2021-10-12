package persistencia.dao.interfaz;

import java.util.List;

import dto.MedioPagoEgresoDTO;

public interface MedioPagoEgresoDAO {
	boolean insert(MedioPagoEgresoDTO medioPagoEgreso);

	boolean delete(MedioPagoEgresoDTO medioPagoEgreso);

	boolean update(String id_medioPagoEgreso_a_actualizar, MedioPagoEgresoDTO medioPagoEgreso_nuevo);

	public List<MedioPagoEgresoDTO> readAll();

	public List<MedioPagoEgresoDTO> getMedioPagoEgresoAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2);
}
