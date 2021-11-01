package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ProvinciaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProvinciaDAO;

public class ProvinciaDAOSQL implements ProvinciaDAO{
	
	private static final String insert = "INSERT INTO provincias(idProvincia, nombreProvincia,idForeignPais) VALUES(?,?,?)";
	private static final String delete = "DELETE FROM provincias WHERE idProvincia = ?";
	private static final String edit = "UPDATE provincias set nombreProvincia=? where idProvincia=?";
	private static final String readall = "SELECT * FROM provincias";

	@Override
	public boolean insert(ProvinciaDTO Provincia) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, Provincia.getIdProvincia());
			statement.setString(2, Provincia.getNombreProvincia());
			statement.setInt(3, Provincia.getForeignPais());
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
	public boolean delete(ProvinciaDTO Provincia_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(Provincia_a_eliminar.getIdProvincia()));
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
	public boolean edit(String nombreProvincia, ProvinciaDTO Provincia_a_editar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(edit);

			statement.setString(1, nombreProvincia);
			statement.setInt(2, Provincia_a_editar.getIdProvincia());

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
	public ArrayList<ProvinciaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<ProvinciaDTO> provincias = new ArrayList<ProvinciaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				provincias.add(getProvinciaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return provincias;
	}

	private ProvinciaDTO getProvinciaDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idProvincia");
		String nombre = resultSet.getString("nombreProvincia");
		int idPais = resultSet.getInt("idForeignPais");
		return new ProvinciaDTO(id, nombre, idPais);
	}
}
