package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.IngresosDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.IngresosDAO;

public class IngresosDAOSQL implements IngresosDAO {

	private static final String insert = "INSERT INTO Ingresos VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM Ingresos WHERE Id = ?";
	private static final String update = "UPDATE Ingresos set IdSucursal=?,Fecha=?,Hora=?,Tipo=?,IdCliente=?,TipoFactura=?,NroFactura=?,MedioPago=?,Cantidad=?,Cotizacion=?,Operacion=?,Total=? where Id=?";
	private static final String readall = "SELECT * FROM Ingresos";

	@Override
	public boolean insert(IngresosDTO ingresos) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			
			statement.setInt(1, ingresos.getId());
			statement.setInt(2, ingresos.getIdSucursal());
			statement.setString(3, ingresos.getFecha());
			statement.setString(4, ingresos.getHora());
			statement.setString(5, ingresos.getTipo());
			statement.setInt(6, ingresos.getIdCliente());
			statement.setString(7, ingresos.getTipoFactura());
			statement.setString(8, ingresos.getNroFactura());
			statement.setString(9, ingresos.getMedioPago());
			statement.setDouble(10, ingresos.getCantidad());
			statement.setDouble(11, ingresos.getCotizacion());
			statement.setString(12, ingresos.getOperacion());
			statement.setDouble(13, ingresos.getTotal());
			
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
	public boolean delete(IngresosDTO ingresos_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, ingresos_a_eliminar.getId());
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
	public boolean update(int id_ingresos_a_actualizar, IngresosDTO ingresos_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setInt(1, ingresos_nuevo.getIdSucursal());
			statement.setString(2, ingresos_nuevo.getFecha());
			statement.setString(3, ingresos_nuevo.getHora());
			statement.setString(4, ingresos_nuevo.getTipo());
			statement.setInt(5, ingresos_nuevo.getIdCliente());
			statement.setString(6, ingresos_nuevo.getTipoFactura());
			statement.setString(7, ingresos_nuevo.getNroFactura());
			statement.setString(8, ingresos_nuevo.getMedioPago());
			statement.setDouble(9, ingresos_nuevo.getCantidad());
			statement.setDouble(10, ingresos_nuevo.getCotizacion());
			statement.setString(11, ingresos_nuevo.getOperacion());
			statement.setDouble(12, ingresos_nuevo.getTotal());
			statement.setInt(13, id_ingresos_a_actualizar);

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
	public List<IngresosDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<IngresosDTO> ingresos = new ArrayList<IngresosDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ingresos.add(getIngresosDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingresos;
	}

	@Override
	public List<IngresosDTO> getIngresosAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2,
			String txtAprox2) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM medioPago WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'";

		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + "AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}

		ArrayList<IngresosDTO> ingresos = new ArrayList<IngresosDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ingresos.add(getIngresosDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingresos;
	}

	private IngresosDTO getIngresosDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("Id");
		int idSucursal = resultSet.getInt("IdSucursal");
		String fecha = resultSet.getString("Fecha");
		String hora = resultSet.getString("Hora");
		String tipo = resultSet.getString("Tipo");
		int idCliente = resultSet.getInt("IdCliente");
		String tipoFactura = resultSet.getString("TipoFactura");
		String nroFactura = resultSet.getString("NroFactura");
		String medioPago = resultSet.getString("MedioPago");
		double cantidad = resultSet.getDouble("Cantidad");
		double cotizacion = resultSet.getDouble("Cotizacion");
		String operacion = resultSet.getString("Operacion");
		double total = resultSet.getDouble("Total");

		return new IngresosDTO(id, idSucursal,fecha,hora,tipo,idCliente,tipoFactura,nroFactura,medioPago,cantidad,cotizacion,operacion,total);
	}
}