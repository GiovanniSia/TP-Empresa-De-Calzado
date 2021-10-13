package modelo;

import java.util.List;

import dto.IngresosDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.IngresosDAO;

public class Ingresos {
	private IngresosDAO ingresos;

	public Ingresos(DAOAbstractFactory metodo_persistencia) {
		this.ingresos = metodo_persistencia.createIngresosDAO();
	}

	public void insert(IngresosDTO ingresos) {
		this.ingresos.insert(ingresos);
	}

	public void delete(IngresosDTO ingresos_a_eliminar) {
		this.ingresos.delete(ingresos_a_eliminar);
	}

	public void update(int id_ingresos_a_actualizar, IngresosDTO ingresos_nuevo) {
		this.ingresos.update(id_ingresos_a_actualizar, ingresos_nuevo);
	}

	public List<IngresosDTO> readAll() {
		return this.ingresos.readAll();
	}

	public List<IngresosDTO> getIngresosAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		return this.ingresos.getIngresosAproximado(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2);
	}
}
