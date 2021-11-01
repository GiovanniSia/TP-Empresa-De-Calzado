package modelo;

import java.util.List;

import dto.ProvinciaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ProvinciaDAO;

public class Provincia {
private ProvinciaDAO provincia;
	
	public Provincia(DAOAbstractFactory metodo_Persistencia) {
		this.provincia = metodo_Persistencia.createProvinciaDAO();
	}
	
	public boolean insert(ProvinciaDTO nuevaProvincia) {
		return this.provincia.insert(nuevaProvincia);
	}
	
	public boolean delete(ProvinciaDTO Provincia_a_eliminar) {
		return this.provincia.delete(Provincia_a_eliminar);
	}
	public boolean update(String nombreProvincia,ProvinciaDTO Provincia_a_editar) {
		return this.provincia.edit(nombreProvincia,Provincia_a_editar);
	}
	public List<ProvinciaDTO> readAll(){
		return this.provincia.readAll();
	}
}
