package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.HistorialCambioMProductoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.HistorialCambioMProductoDAO;

public class HistorialCambioMProductoDAOSQL implements HistorialCambioMProductoDAO {

	private static final String insert = "INSERT INTO historialCambioMProducto VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM historialCambioMProducto WHERE IdHistorialCambioMProducto = ?";												
	private static final String update = "UPDATE historialCambioMProducto set IdHistorialCambioMProducto=?, IdEmpleado=?, IdMaestroProducto=?, Fecha=?, PrecioCostoAntiguo=?, PrecioCostoNuevo=?, PrecioMayoristaAntiguo=?, PrecioMayoristaNuevo=?, PrecioMinoristaAntiguo=?, PrecioMinoristaNuevo=?";
	private static final String readall = "SELECT * FROM historialCambioMProducto";

	@Override
	public boolean insert(HistorialCambioMProductoDTO historialCambiMProducto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, historialCambiMProducto.getIdHistorialCambioProducto());
			statement.setInt(2, historialCambiMProducto.getIdEmpleado());
			statement.setInt(3, historialCambiMProducto.getIdMaestroProducto());
			statement.setString(4, historialCambiMProducto.getFecha());

			statement.setDouble(5, historialCambiMProducto.getPrecioCostoAntiguo());
			statement.setDouble(6, historialCambiMProducto.getPrecioCostoNuevo());

			statement.setDouble(7, historialCambiMProducto.getPrecioMayoristaAntiguo());
			statement.setDouble(8, historialCambiMProducto.getPrecioMayoristaNuevo());

			statement.setDouble(9, historialCambiMProducto.getPrecioMinoristaAntiguo());
			statement.setDouble(10, historialCambiMProducto.getPrecioMinoristaNuevo());

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
	public boolean delete(HistorialCambioMProductoDTO historialCambioMProducto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, historialCambioMProducto.getIdHistorialCambioProducto());
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
	public boolean update(int id_historialCambioMProducto_a_actualizar,
			HistorialCambioMProductoDTO historialCambioMProducto_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setInt(1, historialCambioMProducto_nuevo.getIdEmpleado());
			statement.setInt(2, historialCambioMProducto_nuevo.getIdMaestroProducto());
			statement.setString(3, historialCambioMProducto_nuevo.getFecha());

			statement.setDouble(4, historialCambioMProducto_nuevo.getPrecioCostoAntiguo());
			statement.setDouble(5, historialCambioMProducto_nuevo.getPrecioCostoNuevo());

			statement.setDouble(6, historialCambioMProducto_nuevo.getPrecioMayoristaAntiguo());
			statement.setDouble(7, historialCambioMProducto_nuevo.getPrecioMayoristaNuevo());

			statement.setDouble(8, historialCambioMProducto_nuevo.getPrecioMinoristaAntiguo());
			statement.setDouble(9, historialCambioMProducto_nuevo.getPrecioMinoristaNuevo());
			statement.setDouble(10, id_historialCambioMProducto_a_actualizar);

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
	public ArrayList<HistorialCambioMProductoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<HistorialCambioMProductoDTO> historialCambioMProducto = new ArrayList<HistorialCambioMProductoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				historialCambioMProducto.add(getHistorialCambioMProductoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historialCambioMProducto;
	}

	private HistorialCambioMProductoDTO getHistorialCambioMProductoDTO(ResultSet resultSet) throws SQLException {
		int idHistorialCambioProducto = resultSet.getInt("IdHistorialCambioMProducto");
		int idEmpleado = resultSet.getInt("IdEmpleado");
		int idMaestroProducto = resultSet.getInt("IdMaestroProducto");
		String fecha = resultSet.getString("Fecha");

		double precioCostoAntiguo = resultSet.getDouble("PrecioCostoAntiguo");
		double precioCostoNuevo = resultSet.getDouble("PrecioCostoNuevo");

		double precioMayoristaAntiguo = resultSet.getDouble("PrecioMayoristaAntiguo");
		double precioMayoristaNuevo = resultSet.getDouble("PrecioMayoristaNuevo");

		double precioMinoristaAntiguo = resultSet.getDouble("PrecioMinoristaAntiguo");
		double precioMinoristaNuevo = resultSet.getDouble("PrecioMinoristaNuevo");

		return new HistorialCambioMProductoDTO(idHistorialCambioProducto, idEmpleado, idMaestroProducto, fecha, precioCostoAntiguo, precioCostoNuevo, precioMayoristaAntiguo, precioMayoristaNuevo, precioMinoristaAntiguo, precioMinoristaNuevo);
	}

	public List<HistorialCambioMProductoDTO> getHistorialCambioMProductoAproximado(String nombreColumna1,
			String txtAprox1, String nombreColumna2, String txtAprox2) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM historialCambioMProducto WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'";

		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + "AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}

		ArrayList<HistorialCambioMProductoDTO> historialCambioMProducto = new ArrayList<HistorialCambioMProductoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				historialCambioMProducto.add(getHistorialCambioMProductoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historialCambioMProducto;
	}

}
