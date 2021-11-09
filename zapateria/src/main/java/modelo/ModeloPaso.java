package modelo;

import java.util.List;

import dto.PasoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ModeloPasoDAO;

public class ModeloPaso {
	
	ModeloPasoDAO modeloPaso;
	
	public ModeloPaso(DAOAbstractFactory metodo_persistencia) {
		modeloPaso = metodo_persistencia.createModeloPasoDAO();
	}
	
	public boolean insert(PasoDTO paso) {
		return modeloPaso.insert(paso);
	}

	public boolean delete(PasoDTO paso) {
		return modeloPaso.delete(paso);
	}
	
	public List<PasoDTO> readAll(){
		return modeloPaso.readAll();
	}
	
	public boolean update(PasoDTO paso) {
		return modeloPaso.updatePaso(paso);
	}

}
