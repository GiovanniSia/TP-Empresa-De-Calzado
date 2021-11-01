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
	
	public boolean insert(PaisDTO nuevoPais) {
		return this.pais.insert(nuevoPais);
	}
	
	public boolean borrarPais(PaisDTO Pais_a_eliminar) {
		return this.pais.delete(Pais_a_eliminar);
	}
	public boolean update(PaisDTO Pais_a_editar,String nombreNuevo) {
		return this.pais.update(Pais_a_editar, nombreNuevo);
	}
	public List<PaisDTO> readAll(){
		return this.pais.readAll();
	}
	
}
