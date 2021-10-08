package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.EgresosDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.EgresosDAO;

public class EgresosDAOSQL implements EgresosDAO {
	
	private static final String insert = "INSERT INTO Egresos VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM Egresos WHERE Id = ?";
	private static final String update = "UPDATE Egresos set IdSucursal=?,Fecha=?,Hora=?,Tipo=?,Detalle=?,Total=? where Id=?";
	private static final String readall = "SELECT * FROM Egresos";

	@Override
	public boolean insert(EgresosDTO egresos) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			
			statement.setInt(1, egresos.getId());
			statement.setInt(2, egresos.getIdSucursal());
			statement.setString(3, egresos.getFecha());
			statement.setString(4, egresos.getHora());
			statement.setString(5, egresos.getTipo());
			statement.setString(6, egresos.getDetalle());
			statement.setDouble(7, egresos.getTotal());
			
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
	public boolean delete(EgresosDTO egresos_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, egresos_a_eliminar.getId());
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
	public boolean update(int id_egresos_a_actualizar, EgresosDTO egresos_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setInt(1, egresos_nuevo.getIdSucursal());
			statement.setString(2, egresos_nuevo.getFecha());
			statement.setString(3, egresos_nuevo.getHora());
			statement.setString(4, egresos_nuevo.getTipo());
			statement.setString(5, egresos_nuevo.getDetalle());
			statement.setDouble(6, egresos_nuevo.getTotal());
			statement.setInt(7, id_egresos_a_actualizar);

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
	public List<EgresosDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<EgresosDTO> egresos = new ArrayList<EgresosDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				egresos.add(getEgresosDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return egresos;
	}

	public List<EgresosDTO> getEgresosAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2,
			String txtAprox2) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM medioPago WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'";

		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + "AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}

		ArrayList<EgresosDTO> egresos = new ArrayList<EgresosDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				egresos.add(getEgresosDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return egresos;
	}
	
	private EgresosDTO getEgresosDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("Id");
		int idSucursal = resultSet.getInt("IdSucursal");
		String fecha = resultSet.getString("Fecha");
		String hora = resultSet.getString("Hora");
		String tipo = resultSet.getString("Tipo");
		String detalle = resultSet.getString("Detalle");
		double total = resultSet.getDouble("Total");

		return new EgresosDTO(id,idSucursal,fecha,hora,tipo,detalle,total);
	}
}