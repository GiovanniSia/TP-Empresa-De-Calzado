package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.MedioPagoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MedioPagoDAO;

public class MedioPagoDAOSQL implements MedioPagoDAO {
	private static final String insert = "INSERT INTO medioPago VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM medioPago WHERE IdMoneda = ?";
	private static final String update = "UPDATE medioPago set Descripcion=?,TasaConversion=? where IdMoneda=?";
	private static final String readall = "SELECT * FROM medioPago";

	@Override
	public boolean insert(MedioPagoDTO medioPago) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setString(1, medioPago.getIdMoneda());
			statement.setString(2, medioPago.getDescripcion());
			statement.setDouble(3, medioPago.getTasaConversion());

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
	public boolean delete(MedioPagoDTO medioPago) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, medioPago.getIdMoneda());
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
	public boolean update(String id_medioPago_a_actualizar, MedioPagoDTO medioPago_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, medioPago_nuevo.getDescripcion());
			statement.setDouble(2, medioPago_nuevo.getTasaConversion());
			statement.setString(3, id_medioPago_a_actualizar);

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
	public List<MedioPagoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<MedioPagoDTO> medioPago = new ArrayList<MedioPagoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				medioPago.add(getMedioPagoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medioPago;
	}

	@Override
	public List<MedioPagoDTO> getMedioPagoAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2,
			String txtAprox2) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM medioPago WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'";

		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + "AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}

		ArrayList<MedioPagoDTO> medioPago = new ArrayList<MedioPagoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				medioPago.add(getMedioPagoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medioPago;
	}

	private MedioPagoDTO getMedioPagoDTO(ResultSet resultSet) throws SQLException {
		String idMedioPago = resultSet.getString("IdMoneda");
		String descripcion = resultSet.getString("Descripcion");
		double tasaConversion = resultSet.getDouble("TasaConversion");

		return new MedioPagoDTO(idMedioPago, descripcion, tasaConversion);
	}
}
