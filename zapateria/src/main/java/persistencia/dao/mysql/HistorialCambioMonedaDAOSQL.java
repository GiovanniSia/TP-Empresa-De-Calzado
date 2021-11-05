package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.HistorialCambioMonedaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.HistorialCambioMonedaDAO;

public class HistorialCambioMonedaDAOSQL implements HistorialCambioMonedaDAO {

	private static final String insert = "INSERT INTO historialCambioMoneda VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM historialCambioMoneda WHERE IdMoneda = ?";
	private static final String update = "UPDATE historialCambioMoneda set IdMoneda=?,Descripcion=?,IdEmpleado=?,Fecha=?,Hora=?,TasaConversionAntigua=?,TasaConversionNueva=? where IdCambioMoneda=?";
	private static final String readall = "SELECT * FROM historialCambioMoneda";

	@Override
	public boolean insert(HistorialCambioMonedaDTO historialCambioMoneda) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, historialCambioMoneda.getIdCambioMoneda());
			statement.setString(2, historialCambioMoneda.getIdMoneda());
			statement.setString(3, historialCambioMoneda.getDescripcion());
			statement.setInt(4, historialCambioMoneda.getIdEmpleado());
			statement.setString(5, historialCambioMoneda.getFecha());
			statement.setString(6, historialCambioMoneda.getHora());
			statement.setDouble(7, historialCambioMoneda.getTasaConversionAntigua());
			statement.setDouble(8, historialCambioMoneda.getTasaConversionNueva());

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
	public boolean delete(HistorialCambioMonedaDTO historialCambioMoneda) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, historialCambioMoneda.getIdCambioMoneda());
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
	public boolean update(int id_historialCambioMoneda_a_actualizar,
			HistorialCambioMonedaDTO historialCambioMoneda_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, historialCambioMoneda_nuevo.getIdMoneda());
			statement.setString(2, historialCambioMoneda_nuevo.getDescripcion());
			statement.setInt(3, historialCambioMoneda_nuevo.getIdEmpleado());
			statement.setString(4, historialCambioMoneda_nuevo.getFecha());
			statement.setString(5, historialCambioMoneda_nuevo.getHora());
			statement.setDouble(6, historialCambioMoneda_nuevo.getTasaConversionAntigua());
			statement.setDouble(7, historialCambioMoneda_nuevo.getTasaConversionNueva());
			statement.setInt(8, id_historialCambioMoneda_a_actualizar);

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
	public List<HistorialCambioMonedaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<HistorialCambioMonedaDTO> historialCambioMoneda = new ArrayList<HistorialCambioMonedaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				historialCambioMoneda.add(getHistorialCambioMonedaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historialCambioMoneda;
	}

	@Override
	public List<HistorialCambioMonedaDTO> getHistorialCambioMonedaAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2, String nombreColumna3, String fechaDesde, String fechaHasta) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query

		String sel = "SELECT * FROM historialCambioMoneda WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'"
				+ " AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";

		if (nombreColumna3 != null && fechaDesde != null && fechaHasta == null) {
			sel = sel + "AND " + nombreColumna3 + " LIKE '%" + fechaDesde + "%'";
		}

		if (nombreColumna3 != null && fechaDesde != null && fechaHasta != null) {
			sel = sel + " AND " + nombreColumna3 + " BETWEEN '" + fechaDesde + "' AND '" + fechaHasta + "'";
		}
		
		ArrayList<HistorialCambioMonedaDTO> historialCambioMoneda = new ArrayList<HistorialCambioMonedaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				historialCambioMoneda.add(getHistorialCambioMonedaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historialCambioMoneda;
	}

	private HistorialCambioMonedaDTO getHistorialCambioMonedaDTO(ResultSet resultSet) throws SQLException {
		int idCambioMoneda = resultSet.getInt("IdCambioMoneda");
		String idMoneda = resultSet.getString("IdMoneda");
		String descripcion = resultSet.getString("Descripcion");
		int idEmpleado = resultSet.getInt("IdEmpleado");
		String fecha = resultSet.getString("Fecha");
		String hora = resultSet.getString("Hora");
		double tasaConversionAntigua = resultSet.getDouble("TasaConversionAntigua");
		double tasaConversionNueva = resultSet.getDouble("TasaConversionNueva");

		return new HistorialCambioMonedaDTO(idCambioMoneda, idMoneda, descripcion, idEmpleado, fecha, hora,
				tasaConversionAntigua, tasaConversionNueva);
	}

}
