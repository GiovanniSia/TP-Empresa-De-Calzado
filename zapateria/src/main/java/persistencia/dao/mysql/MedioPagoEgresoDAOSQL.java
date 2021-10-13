package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.MedioPagoEgresoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MedioPagoEgresoDAO;

public class MedioPagoEgresoDAOSQL implements MedioPagoEgresoDAO{	
	private static final String insert = "INSERT INTO medioPagoEgreso VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM medioPagoEgreso WHERE IdMoneda = ?";
	private static final String update = "UPDATE medioPagoEgreso set Descripcion=?,TasaConversion=? where IdMoneda=?";
	private static final String readall = "SELECT * FROM medioPagoEgreso";

	@Override
	public boolean insert(MedioPagoEgresoDTO medioPagoEgreso) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setString(1, medioPagoEgreso.getIdMoneda());
			statement.setString(2, medioPagoEgreso.getDescripcion());
			statement.setDouble(3, medioPagoEgreso.getTasaConversion());

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
	public boolean delete(MedioPagoEgresoDTO medioPagoEgreso) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, medioPagoEgreso.getIdMoneda());
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
	public boolean update(String id_medioPagoEgreso_a_actualizar, MedioPagoEgresoDTO medioPagoEgreso_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, medioPagoEgreso_nuevo.getDescripcion());
			statement.setDouble(2, medioPagoEgreso_nuevo.getTasaConversion());
			statement.setString(3, id_medioPagoEgreso_a_actualizar);

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
	public List<MedioPagoEgresoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<MedioPagoEgresoDTO> medioPagoEgreso = new ArrayList<MedioPagoEgresoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				medioPagoEgreso.add(getMedioPagoEgresoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medioPagoEgreso;
	}

	@Override
	public List<MedioPagoEgresoDTO> getMedioPagoEgresoAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM medioPagoEgreso WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'";

		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + "AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}

		ArrayList<MedioPagoEgresoDTO> medioPagoEgreso = new ArrayList<MedioPagoEgresoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				medioPagoEgreso.add(getMedioPagoEgresoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medioPagoEgreso;
	}

	private MedioPagoEgresoDTO getMedioPagoEgresoDTO(ResultSet resultSet) throws SQLException {
		String idMedioPago = resultSet.getString("IdMoneda");
		String descripcion = resultSet.getString("Descripcion");
		double tasaConversion = resultSet.getDouble("TasaConversion");

		return new MedioPagoEgresoDTO(idMedioPago, descripcion, tasaConversion);
	}
}
