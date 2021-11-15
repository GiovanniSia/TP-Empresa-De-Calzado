package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO{
	
	private static final String insert = "INSERT INTO localidades(idLocalidad, nombreLocalidad,idForeignProvincia) VALUES(?, ?,?)";
	private static final String delete = "DELETE FROM localidades WHERE idLocalidad = ?";
	private static final String update = "UPDATE localidades set nombreLocalidad=? where idLocalidad=?";
	private static final String readall = "SELECT * FROM localidades ORDER BY nombreLocalidad ASC";

	@Override
	public boolean insert(LocalidadDTO Localidad) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, Localidad.getIdLocalidad());
			statement.setString(2, Localidad.getNombreLocalidad());
			statement.setInt(3, Localidad.getIdForeignProvincia());
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
	public boolean delete(LocalidadDTO Localidad_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			System.out.println("se borra la localidad con id:"+Localidad_a_eliminar.getIdLocalidad());
			statement.setInt(1, Localidad_a_eliminar.getIdLocalidad());
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
	public boolean update(LocalidadDTO Localidad_a_editar,String nuevoNombre) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, nuevoNombre);
			statement.setInt(2, Localidad_a_editar.getIdLocalidad());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("se ejecuto el update");
		return isUpdateExitoso;
	}

	@Override
	public ArrayList<LocalidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				localidades.add(getLocalidadDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localidades;
	}

	private LocalidadDTO getLocalidadDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idLocalidad");
		String nombre = resultSet.getString("nombreLocalidad");
		int idProvincia = resultSet.getInt("idForeignProvincia");
		return new LocalidadDTO(id, nombre,idProvincia);
	}
}
