package modelo;

import java.util.List;

import dto.PaisDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PaisDAO;

public class Pais {
private PaisDAO pais;
	
	public Pais(DAOAbstractFactory metodo_Persistencia) {
		this.pais = metodo_Persistencia.createPaisDAO();
	}
	
	public void agregarPais(PaisDTO nuevoPais) {
		this.pais.insert(nuevoPais);
	}
	
	public void borrarPais(PaisDTO Pais_a_eliminar) {
		this.pais.delete(Pais_a_eliminar);
	}
	public void editarPais(PaisDTO Pais_a_editar,String nombreNuevo) {
		this.pais.update(Pais_a_editar, nombreNuevo);
	}
	public List<PaisDTO> readAll(){
		return this.pais.readAll();
	}
	
}
