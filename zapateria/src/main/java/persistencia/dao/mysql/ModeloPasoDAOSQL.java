package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PasoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ModeloPasoDAO;

public class ModeloPasoDAOSQL implements ModeloPasoDAO{
	
	private static final String insert = "INSERT INTO paso VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM paso WHERE IdPaso = ?";
	private static final String readall = "SELECT * FROM paso";
	
	@Override
	public boolean insert(PasoDTO paso) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, paso.getIdPaso());
			statement.setString(2, paso.getDescripcion());
			statement.setString(3, paso.getEstado());
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
	public boolean delete(PasoDTO paso) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, paso.getIdPaso());
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
	public List<PasoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PasoDTO> caja = new ArrayList<PasoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				caja.add(getCajaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return caja;
	}
	
	private PasoDTO getCajaDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("IdPaso");
		String descr = resultSet.getString("Descripcion");
		String estado = resultSet.getString("Estado");
		return new PasoDTO(id, descr, estado);
	}

}
