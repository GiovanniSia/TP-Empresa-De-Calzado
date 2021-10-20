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
	private static final String insert = "INSERT INTO historialCambioMProducto VALUES(?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM historialCambioMProducto WHERE IdHistorialCambioMProducto = ?";
	private static final String update = "UPDATE historialCambioMProducto set IdSucursal=? IdEmpleado=?, IdMaestroProducto=?, Fecha=?, PrecioCostoAntiguo=?, PrecioCostoNuevo=?, PrecioMayoristaAntiguo=?, PrecioMayoristaNuevo=?, PrecioMinoristaAntiguo=?, PrecioMinoristaNuevo=?, PuntoRepositorioAntiguo=?,PuntoRepositorioNuevo=?, CantidadAReponerAntiguo=?,CantidadAReponerNuevo=?,DiasParaReponerAntiguo=?,DiasParaReponerNuevo=?  where IdHistorialCambioMProducto=?";
	private static final String readall = "SELECT * FROM historialCambioMProducto";

	@Override
	public boolean insert(HistorialCambioMProductoDTO historialCambiMProducto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, historialCambiMProducto.getIdHistorialCambioProducto());
			statement.setString(2, historialCambiMProducto.getIdSucursal());
			statement.setString(3, historialCambiMProducto.getIdEmpleado());
			statement.setString(4, historialCambiMProducto.getIdMaestroProducto());
			statement.setString(5, historialCambiMProducto.getFecha());

			statement.setString(6, historialCambiMProducto.getPrecioCostoAntiguo());
			statement.setString(7, historialCambiMProducto.getPrecioCostoNuevo());

			statement.setString(8, historialCambiMProducto.getPrecioMayoristaAntiguo());
			statement.setString(9, historialCambiMProducto.getPrecioMayoristaNuevo());

			statement.setString(10, historialCambiMProducto.getPrecioMinoristaAntiguo());
			statement.setString(11, historialCambiMProducto.getPrecioMinoristaNuevo());

			statement.setString(12, historialCambiMProducto.getPuntoRepositorioAntiguo());
			statement.setString(13, historialCambiMProducto.getPuntoRepositorioNuevo());

			statement.setString(14, historialCambiMProducto.getCantidadAReponerAntiguo());
			statement.setString(15, historialCambiMProducto.getCantidadAReponerNuevo());

			statement.setString(16, historialCambiMProducto.getDiasParaReponerAntiguo());
			statement.setString(17, historialCambiMProducto.getDiasParaReponerNuevo());

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

			statement.setString(1, historialCambioMProducto_nuevo.getIdSucursal());
			statement.setString(2, historialCambioMProducto_nuevo.getIdEmpleado());
			statement.setString(3, historialCambioMProducto_nuevo.getIdMaestroProducto());
			statement.setString(4, historialCambioMProducto_nuevo.getFecha());

			statement.setString(5, historialCambioMProducto_nuevo.getPrecioCostoAntiguo());
			statement.setString(6, historialCambioMProducto_nuevo.getPrecioCostoNuevo());

			statement.setString(7, historialCambioMProducto_nuevo.getPrecioMayoristaAntiguo());
			statement.setString(8, historialCambioMProducto_nuevo.getPrecioMayoristaNuevo());

			statement.setString(9, historialCambioMProducto_nuevo.getPrecioMinoristaAntiguo());
			statement.setString(10, historialCambioMProducto_nuevo.getPrecioMinoristaNuevo());

			statement.setString(11, historialCambioMProducto_nuevo.getPuntoRepositorioAntiguo());
			statement.setString(12, historialCambioMProducto_nuevo.getPuntoRepositorioNuevo());

			statement.setString(13, historialCambioMProducto_nuevo.getCantidadAReponerAntiguo());
			statement.setString(14, historialCambioMProducto_nuevo.getCantidadAReponerNuevo());

			statement.setString(15, historialCambioMProducto_nuevo.getDiasParaReponerAntiguo());
			statement.setString(16, historialCambioMProducto_nuevo.getDiasParaReponerNuevo());

			statement.setInt(17, id_historialCambioMProducto_a_actualizar);

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
		String idSucursal = resultSet.getString("IdSucursal");
		String idEmpleado = resultSet.getString("IdEmpleado");
		String idMaestroProducto = resultSet.getString("IdMaestroProducto");
		String fecha = resultSet.getString("Fecha");

		String precioCostoAntiguo = resultSet.getString("PrecioCostoAntiguo");
		String precioCostoNuevo = resultSet.getString("PrecioCostoNuevo");

		String precioMayoristaAntiguo = resultSet.getString("PrecioMayoristaAntiguo");
		String precioMayoristaNuevo = resultSet.getString("PrecioMayoristaNuevo");

		String precioMinoristaAntiguo = resultSet.getString("PrecioMinoristaAntiguo");
		String precioMinoristaNuevo = resultSet.getString("PrecioMinoristaNuevo");

		String PuntoRepositorioAntiguo = resultSet.getString("PuntoRepositorioAntiguo");
		String PuntoRepositorioNuevo = resultSet.getString("PuntoRepositorioNuevo");

		String CantidadAReponerAntiguo = resultSet.getString("CantidadAReponerAntiguo");
		String CantidadAReponerNuevo = resultSet.getString("CantidadAReponerNuevo");

		String DiasParaReponerAntiguo = resultSet.getString("DiasParaReponerAntiguo");
		String DiasParaReponerNuevo = resultSet.getString("DiasParaReponerNuevo");

		return new HistorialCambioMProductoDTO(idHistorialCambioProducto, idSucursal, idEmpleado, idMaestroProducto, fecha,
				precioCostoAntiguo, precioCostoNuevo, precioMayoristaAntiguo, precioMayoristaNuevo,
				precioMinoristaAntiguo, precioMinoristaNuevo, PuntoRepositorioAntiguo, PuntoRepositorioNuevo,
				CantidadAReponerAntiguo, CantidadAReponerNuevo, DiasParaReponerAntiguo, DiasParaReponerNuevo);
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
