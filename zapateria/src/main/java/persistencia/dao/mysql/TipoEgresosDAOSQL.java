package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoEgresosDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoEgresosDAO;

public class TipoEgresosDAOSQL implements TipoEgresosDAO {
	private static final String insert = "INSERT INTO tipoEgreso VALUES(?, ?)";
	private static final String delete = "DELETE FROM tipoEgreso WHERE IdTipoEgreso = ?";
	private static final String update = "UPDATE tipoEgreso set Descripcion=?, where IdTipoEgreso=?";
	private static final String readall = "SELECT * FROM tipoegreso";

	@Override
	public boolean insert(TipoEgresosDTO tipoEgresos){
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setString(1, tipoEgresos.getIdTipoEgreso());
			statement.setString(2, tipoEgresos.getDescripcion());

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
	public boolean delete(TipoEgresosDTO tipoEgresos) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, tipoEgresos.getIdTipoEgreso());
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
	public boolean update(String id_tipoEgresos_a_actualizar, TipoEgresosDTO tipoEgresos_nuevo)  {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, tipoEgresos_nuevo.getDescripcion());
			statement.setString(2, id_tipoEgresos_a_actualizar);

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
	public List<TipoEgresosDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TipoEgresosDTO> tipoEgresos = new ArrayList<TipoEgresosDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tipoEgresos.add(getTipoEgresosDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoEgresos;
	}

	@Override
	public List<TipoEgresosDTO> getTipoEgresosAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2,
			String txtAprox2) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM tipoEgreso WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'";

		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + "AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}

		ArrayList<TipoEgresosDTO> tipoEgresos = new ArrayList<TipoEgresosDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tipoEgresos.add(getTipoEgresosDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoEgresos;
	}

	private TipoEgresosDTO getTipoEgresosDTO(ResultSet resultSet) throws SQLException {
		String idTipoEgreso = resultSet.getString("IdTipoEgreso");
		String descripcion = resultSet.getString("Descripcion");

		return new TipoEgresosDTO(idTipoEgreso, descripcion);
	}








}
