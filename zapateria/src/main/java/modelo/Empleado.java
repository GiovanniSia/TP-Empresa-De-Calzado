package modelo;

import java.util.List;

import dto.EmpleadoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.EmpleadoDAO;

public class Empleado {
	
	private EmpleadoDAO empleado;
	
	public Empleado(DAOAbstractFactory metodo_persistencia) {
		this.empleado = metodo_persistencia.createEmpleadoDAO();
	}
	
	public void insert(EmpleadoDTO empleado) {
		this.empleado.insert(empleado);
	}
		

	public void delete(EmpleadoDTO empleado_a_eliminar) {
		this.empleado.delete(empleado_a_eliminar);
	}
	
	public void update(int id_empleado_a_actualizar, EmpleadoDTO empleado_nuevo) {
		this.empleado.update(id_empleado_a_actualizar, empleado_nuevo);
	}

	public List<EmpleadoDTO> readAll(){
		return this.empleado.readAll();
	}
}
