package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.HistorialCambioEmpleadoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.HistorialCambioEmpleadoDAO;

public class HistorialCambioEmpleadoDAOSQL implements HistorialCambioEmpleadoDAO {

	private static final String insert = "INSERT INTO historialCambioEmpleados VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	private static final String readall = "SELECT * FROM historialCambioEmpleados";

	@Override
	public boolean insert(HistorialCambioEmpleadoDTO historialCambioEmpleado) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, historialCambioEmpleado.getIdHistorialCambioEmpleados());
			statement.setString(2, historialCambioEmpleado.getIdEmpleadoResponsable());
			statement.setString(3, historialCambioEmpleado.getFecha());
			statement.setString(4, historialCambioEmpleado.getIdSucursal());
			statement.setString(5, historialCambioEmpleado.getIdEmpleado());
			statement.setString(6, historialCambioEmpleado.getCUILAntiguo());
			statement.setString(7, historialCambioEmpleado.getCUILNuevo());
			statement.setString(8, historialCambioEmpleado.getNombreAntiguo());
			statement.setString(9, historialCambioEmpleado.getNombreNuevo());
			statement.setString(10, historialCambioEmpleado.getApellidoAntiguo());
			statement.setString(11, historialCambioEmpleado.getApellidoNuevo());
			statement.setString(12, historialCambioEmpleado.getCorreoElectronicoAntiguo());
			statement.setString(13, historialCambioEmpleado.getCorreoElectronicoNuevo());
			statement.setString(14, historialCambioEmpleado.getTipoEmpleadoAntiguo());
			statement.setString(15, historialCambioEmpleado.getTipoEmpleadoNuevo());

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
	public List<HistorialCambioEmpleadoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<HistorialCambioEmpleadoDTO> historialCambioMoneda = new ArrayList<HistorialCambioEmpleadoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				historialCambioMoneda.add(getHistorialCambioEmpleadoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historialCambioMoneda;
	}

	@Override
	public List<HistorialCambioEmpleadoDTO> getFiltrarPor(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3, String nombreColumna4,
			String txtAprox4) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query

		String sel = "SELECT * FROM historialCambioMoneda WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'"
				+ " AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'" + " AND " + nombreColumna3 + " LIKE '%"
				+ txtAprox3 + "%'" + " AND " + nombreColumna4 + " LIKE '%" + txtAprox4 + "%'";

		ArrayList<HistorialCambioEmpleadoDTO> historialCambioEmpleado = new ArrayList<HistorialCambioEmpleadoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				historialCambioEmpleado.add(getHistorialCambioEmpleadoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historialCambioEmpleado;
	}

	private HistorialCambioEmpleadoDTO getHistorialCambioEmpleadoDTO(ResultSet resultSet) throws SQLException {

		int IdHistorialCambioEmpleadosresultSet = resultSet.getInt("IdHistorialCambioEmpleados");
		String IdEmpleadoResponsable = resultSet.getString("IdEmpleadoResponsable");
		String Fecha = resultSet.getString("Fecha");
		String IdSucursal = resultSet.getString("IdSucursal");
		String IdEmpleado = resultSet.getString("IdEmpleado");
		String CUILAntiguo = resultSet.getString("CUILAntiguo");
		String CUILNuevo = resultSet.getString("CUILNuevo");
		String NombreAntiguo = resultSet.getString("NombreAntiguo");
		String NombreNuevo = resultSet.getString("NombreNuevo");
		String ApellidoAntiguo = resultSet.getString("ApellidoAntiguo");
		String ApellidoNuevo = resultSet.getString("ApellidoNuevo");
		String CorreoElectronicoAntiguo = resultSet.getString("CorreoElectronicoAntiguo");
		String CorreoElectronicoNuevo = resultSet.getString("CorreoElectronicoNuevo");
		String TipoEmpleadoAntiguo = resultSet.getString("TipoEmpleadoAntiguo");
		String TipoEmpleadoNuevo = resultSet.getString("TipoEmpleadoNuevo");

		return new HistorialCambioEmpleadoDTO(IdHistorialCambioEmpleadosresultSet, IdEmpleadoResponsable, Fecha,
				IdSucursal, IdEmpleado, CUILAntiguo, CUILNuevo, NombreAntiguo, NombreNuevo, ApellidoAntiguo,
				ApellidoNuevo, CorreoElectronicoAntiguo, CorreoElectronicoNuevo, TipoEmpleadoAntiguo,
				TipoEmpleadoNuevo);
	}
}
