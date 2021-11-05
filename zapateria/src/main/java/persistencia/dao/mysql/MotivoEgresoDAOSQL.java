package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MotivoEgresoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MotivoEgresoDAO;

public class MotivoEgresoDAOSQL implements MotivoEgresoDAO{
	
	private static final String readall = "SELECT * FROM MotivoEgresos";
	private static final String insert = "INSERT INTO MotivoEgresos VALUES(?, ?)";
	
	public boolean insert(MotivoEgresoDTO m) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setString(1, m.getNroFacturaCompleto());
			statement.setString(2, m.getDescripcion());

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
	
	public List<MotivoEgresoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<MotivoEgresoDTO> moti = new ArrayList<MotivoEgresoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				moti.add(getMotivoEgresoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return moti;
	}
	
	private MotivoEgresoDTO getMotivoEgresoDTO(ResultSet resultSet) throws SQLException {
		String nroFacturaCompleto = resultSet.getString("NroFacturaCompleta");
		String descripcion = resultSet.getString("Motivo");
		return new MotivoEgresoDTO(nroFacturaCompleto, descripcion);
	}

}
