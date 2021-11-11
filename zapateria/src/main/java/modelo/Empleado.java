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
	
	public EmpleadoDTO selectEmpleado(int idEmpleado) {
		return this.empleado.selectEmpleado(idEmpleado);
	}
	
	public EmpleadoDTO selectUserCorreo(String correo) {
		return this.empleado.selectUserCorreo(correo);
	}
	
	public EmpleadoDTO selectUser(String correo, String contra) {
		return this.empleado.selectUser(correo, contra);
	}
	
	public List<EmpleadoDTO> getFiltrarPor(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3){
		return this.empleado.getFiltrarPor(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2, nombreColumna3, txtAprox3);
	}
}
