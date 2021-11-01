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
	
	public void agregarProvincia(ProvinciaDTO nuevaProvincia) {
		this.provincia.insert(nuevaProvincia);
	}
	
	public void borrarProvincia(ProvinciaDTO Provincia_a_eliminar) {
		this.provincia.delete(Provincia_a_eliminar);
	}
	public void editarProvincia(String nombreProvincia,ProvinciaDTO Provincia_a_editar) {
		this.provincia.edit(nombreProvincia,Provincia_a_editar);
	}
	public List<ProvinciaDTO> readAll(){
		return this.provincia.readAll();
	}
}
