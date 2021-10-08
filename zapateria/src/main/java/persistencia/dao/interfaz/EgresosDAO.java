package persistencia.dao.interfaz;

import java.util.List;

import dto.EgresosDTO;

public interface EgresosDAO {
	public boolean insert(EgresosDTO egresos);

	public boolean delete(EgresosDTO egresos_a_eliminar);
	
	public boolean update(int id_egresos_a_actualizar, EgresosDTO egresos_nuevo);

	public List<EgresosDTO> readAll();
	
	public List<EgresosDTO> getEgresosAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2);
	
}
