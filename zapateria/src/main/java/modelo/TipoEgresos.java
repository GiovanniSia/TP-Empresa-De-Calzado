package modelo;

import java.util.List;

import dto.TipoEgresosDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.TipoEgresosDAO;

public class TipoEgresos {
	private TipoEgresosDAO tipoEgresos;

	public TipoEgresos(DAOAbstractFactory metodo_persistencia) {
		this.tipoEgresos = metodo_persistencia.createTipoEgresosDAO();
	}

	public void insert(TipoEgresosDTO tipoEgresos) {
		this.tipoEgresos.insert(tipoEgresos);
	}

	public void delete(TipoEgresosDTO tipoEgresos_a_eliminar) {
		this.tipoEgresos.delete(tipoEgresos_a_eliminar);
	}

	public void update(String id_tipoEgresos_a_actualizar, TipoEgresosDTO tipoEgresos_nuevo) {
		this.tipoEgresos.update(id_tipoEgresos_a_actualizar, tipoEgresos_nuevo);
	}

	public List<TipoEgresosDTO> readAll() {
		return this.tipoEgresos.readAll();
	}

	public List<TipoEgresosDTO> getTipoEgresosAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		return this.tipoEgresos.getTipoEgresosAproximado(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2);
	}
}
