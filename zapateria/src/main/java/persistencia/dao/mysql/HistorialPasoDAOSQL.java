package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CarritoDTO;
import dto.HistorialPasoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.HistorialPasoDAO;

public class HistorialPasoDAOSQL implements HistorialPasoDAO{

	private static final String readall = "SELECT * FROM HistorialPasos";

	public List<HistorialPasoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<HistorialPasoDTO> stock = new ArrayList<HistorialPasoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				stock.add(getHistorialPasoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}

	private HistorialPasoDTO getHistorialPasoDTO(ResultSet resultSet) throws SQLException {
		int Id = resultSet.getInt("Id");
		String Hora = resultSet.getString("Hora");
		String Fecha = resultSet.getString("Fecha");
		int IdOrden = resultSet.getInt("IdOrden");
		int IdEmpleado = resultSet.getInt("IdEmpleado");
		String NombreCompleto = resultSet.getString("NombreCompleto");
		String DescrPasoCompletado = resultSet.getString("DescrPasoCompletado");
		String Descr = resultSet.getString("Descr");
		return new HistorialPasoDTO(Id, Hora, Fecha, IdOrden, IdEmpleado, NombreCompleto, DescrPasoCompletado, Descr);
	}

	private static final String insert = "INSERT INTO stock(Hora, Fecha, IdOrden, IdEmpleado, NombreCompleto, DescrPasoCompletado, Descr) VALUES(?,?,?,?,?,?,?);";

	public boolean insert(HistorialPasoDTO pasoHistorial){
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, pasoHistorial.getHora());
			statement.setString(2, pasoHistorial.getHora());
			statement.setInt(3, pasoHistorial.getIdOrden());
			statement.setInt(4, pasoHistorial.getIdEmpleado());
			statement.setString(5, pasoHistorial.getNombreCompleto());
			statement.setString(6, pasoHistorial.getDescrPasoCompletado());
			statement.setString(7, pasoHistorial.getDescr());
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

}
