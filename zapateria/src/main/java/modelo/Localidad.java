package modelo;

import java.util.List;

import dto.LocalidadDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;

public class Localidad {
private LocalidadDAO localidad;
	
	public Localidad(DAOAbstractFactory metodo_Persistencia) {
		this.localidad = metodo_Persistencia.createLocalidadDAO();
	}
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad) {
		this.localidad.insert(nuevaLocalidad);
	}
	
	public void borrarLocalidad(LocalidadDTO Localidad_a_eliminar) {
		this.localidad.delete(Localidad_a_eliminar);
	}
	public void editarLocalidad(LocalidadDTO Localidad_a_editar,String nuevoNombre) {
		this.localidad.update(Localidad_a_editar,nuevoNombre);
	}
	public List<LocalidadDTO> readAll(){
		return this.localidad.readAll();
	}
}
