package modelo;

import java.util.List;

import dto.EgresosDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.EgresosDAO;

public class Egresos {
	private EgresosDAO egresos;

	public Egresos(DAOAbstractFactory metodo_persistencia) {
		this.egresos = metodo_persistencia.createEgresosDAO();
	}

	public void insert(EgresosDTO egresos) {
		this.egresos.insert(egresos);
	}

	public void delete(EgresosDTO egresos_a_eliminar) {
		this.egresos.delete(egresos_a_eliminar);
	}

	public void update(int id_egresos_a_actualizar, EgresosDTO egresos_nuevo) {
		this.egresos.update(id_egresos_a_actualizar, egresos_nuevo);
	}

	public List<EgresosDTO> readAll() {
		return this.egresos.readAll();
	}

	public List<EgresosDTO> getEgresosAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		return this.egresos.getEgresosAproximado(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2);
	}
}