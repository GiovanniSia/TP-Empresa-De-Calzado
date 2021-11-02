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
	
	public boolean agregarLocalidad(LocalidadDTO nuevaLocalidad) {
		return this.localidad.insert(nuevaLocalidad);
	}
	
	public boolean borrarLocalidad(LocalidadDTO Localidad_a_eliminar) {
		return this.localidad.delete(Localidad_a_eliminar);
	}
	public boolean editarLocalidad(LocalidadDTO Localidad_a_editar,String nuevoNombre) {
		return this.localidad.update(Localidad_a_editar,nuevoNombre);
	}
	public List<LocalidadDTO> readAll(){
		return this.localidad.readAll();
	}
}
