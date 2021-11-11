package persistencia.dao.interfaz;

import java.util.List;

import dto.EmpleadoDTO;


public interface EmpleadoDAO {

	public boolean insert(EmpleadoDTO empleado);

	public boolean delete(EmpleadoDTO empleado_a_eliminar);
	
	public boolean update(int id_empleado_a_actualizar, EmpleadoDTO empleado_nuevo);

	public List<EmpleadoDTO> readAll();
	
	public EmpleadoDTO selectEmpleado(int idEmpleado);
	
	public EmpleadoDTO selectUserCorreo(String correo); 
	
	public EmpleadoDTO selectUser(String correo, String contra);
	
	public List<EmpleadoDTO> getFiltrarPor(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3);
}
