package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PaisDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PaisDAO;

public class PaisDAOSQL implements PaisDAO {
	
	private static final String insert = "INSERT INTO paises(idPais, nombrePais) VALUES(?, ?)";
	private static final String delete = "DELETE FROM paises WHERE idPais = ?";
	private static final String update = "UPDATE paises set nombrePais=? where idPais=?";
	private static final String readall = "SELECT * FROM paises ORDER BY nombrePais ASC";

	@Override
	public boolean insert(PaisDTO Pais) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, Pais.getIdPais());
			statement.setString(2, Pais.getNombrePais());
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
	public boolean delete(PaisDTO Pais_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(Pais_a_eliminar.getIdPais()));
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
	public boolean update(PaisDTO Pais_a_editar,String paisNuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, paisNuevo);
			statement.setInt(2, Pais_a_editar.getIdPais());

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
	public ArrayList<PaisDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PaisDTO> paises = new ArrayList<PaisDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				paises.add(getPaisDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paises;
	}

	private PaisDTO getPaisDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idPais");
		String nombre = resultSet.getString("nombrePais");
		return new PaisDTO(id, nombre);
	}
}
