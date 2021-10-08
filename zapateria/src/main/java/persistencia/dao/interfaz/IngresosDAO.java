package persistencia.dao.interfaz;

import java.util.List;

import dto.IngresosDTO;

public interface IngresosDAO {
	
	public boolean insert(IngresosDTO ingresos);

	public boolean delete(IngresosDTO ingresos_a_eliminar);
	
	public boolean update(int id_ingresos_a_actualizar, IngresosDTO ingresos_nuevo);

	public List<IngresosDTO> readAll();
	
	public List<IngresosDTO> getIngresosAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2);
	
}
