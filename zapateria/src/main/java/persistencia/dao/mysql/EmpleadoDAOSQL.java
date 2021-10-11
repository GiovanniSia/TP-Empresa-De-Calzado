package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.EmpleadoDTO;
import dto.MaestroProductoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.EmpleadoDAO;

public class EmpleadoDAOSQL implements EmpleadoDAO{
	
	private static final String insert = "INSERT INTO empleados VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM empleados WHERE IdEmpleado = ?";
	private static final String update = "UPDATE empleados set CUIL=?, Nombre=?, Apellido=?, CorreoElectronico=?, TipoEmpleado=?, Contra=? where IdEmpleado=?";
	private static final String readall = "SELECT * FROM empleados";
	private static final String select = "SELECT * FROM empleados WHERE IdEmpleado=?";
	
	@Override
	public boolean insert(EmpleadoDTO empleado) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			
			statement.setInt(1, empleado.getIdEmpleado());
			statement.setString(2, empleado.getCUIL());
			statement.setString(3, empleado.getNombre());
			statement.setString(4, empleado.getApellido());
			statement.setString(5, empleado.getCorreoElectronico());
			statement.setString(6, empleado.getTipoEmpleado());
			statement.setString(7, empleado.getContra());
			
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return isInsertExitoso;
	}
	@Override
	public boolean delete(EmpleadoDTO empleado_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, empleado_a_eliminar.getIdEmpleado());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	@Override
	public boolean update(int id_empleado_a_actualizar, EmpleadoDTO empleado_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);


			statement.setString(1, empleado_nuevo.getCUIL());
			statement.setString(2, empleado_nuevo.getNombre());
			statement.setString(3, empleado_nuevo.getApellido());
			statement.setString(4, empleado_nuevo.getCorreoElectronico());
			statement.setString(5, empleado_nuevo.getTipoEmpleado());
			statement.setString(6, empleado_nuevo.getContra());

			statement.setInt(7, id_empleado_a_actualizar);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}
	@Override
	public List<EmpleadoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<EmpleadoDTO> empleados = new ArrayList<EmpleadoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				empleados.add(getEmpleadoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empleados;
	}
	private EmpleadoDTO getEmpleadoDTO(ResultSet resultSet) throws SQLException {
		
		int idEmpleado = resultSet.getInt("IdEmpleado");
		String CUIL = resultSet.getString("CUIL");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		String correo = resultSet.getString("CorreoElectronico");
		String tipoEmpleado = resultSet.getString("TipoEmpleado");
		String contrasenia = resultSet.getString("Contra");
		return new EmpleadoDTO(idEmpleado, CUIL, nombre, apellido, correo, tipoEmpleado, contrasenia);
	}
	
	
	@Override
	public EmpleadoDTO selectEmpleado(int idEmpleado) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		EmpleadoDTO empleado = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setInt(1,idEmpleado);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				empleado = getEmpleadoDTO(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empleado;
	}

}
