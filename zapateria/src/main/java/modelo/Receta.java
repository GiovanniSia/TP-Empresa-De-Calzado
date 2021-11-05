package modelo;

import java.util.List;

import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.RecetaDAO;

public class Receta {
	private RecetaDAO receta;

	public Receta(DAOAbstractFactory metodo_persistencia) {
		receta = metodo_persistencia.createModeloRecetaDAO();
	}
	
	public boolean insertReceta(RecetaDTO receta) {
		return this.receta.insertReceta(receta);
	}
	
	public boolean insertPasosReceta(List<PasoDeRecetaDTO> pasos) {
		return this.receta.insertPasosReceta(pasos);
	}
	
	public boolean updateReceta(RecetaDTO receta, List<PasoDeRecetaDTO> pasos) {
		return this.receta.updateReceta(receta, pasos);
	}
	
}
