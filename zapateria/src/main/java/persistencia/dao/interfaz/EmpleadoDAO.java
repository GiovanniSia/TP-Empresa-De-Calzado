package persistencia.dao.interfaz;

import java.util.List;

import dto.EmpleadoDTO;


public interface EmpleadoDAO {

	public boolean insert(EmpleadoDTO empleado);

	public boolean delete(EmpleadoDTO empleado_a_eliminar);
	
	public boolean update(int id_empleado_a_actualizar, EmpleadoDTO empleado_nuevo);

	public List<EmpleadoDTO> readAll();
	
	public EmpleadoDTO selectEmpleado(int idEmpleado);
	
	public EmpleadoDTO selectUser(String correo, String contra);
}
