package persistencia.dao.interfaz;

import java.util.List;

import dto.TipoEgresosDTO;

public interface TipoEgresosDAO {
	boolean insert(TipoEgresosDTO tipoEgresos);

	boolean delete(TipoEgresosDTO tipoEgresos);

	boolean update(String id_tipoEgresos_a_actualizar, TipoEgresosDTO tipoEgresos_nuevo);

	public List<TipoEgresosDTO> readAll();

	public List<TipoEgresosDTO> getTipoEgresosAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2);
}
