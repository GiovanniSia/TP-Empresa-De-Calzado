package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DetalleCarritoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DetalleCarritoDAO;

public class DetalleCarritoDAOSQL implements DetalleCarritoDAO{

	private static final String insert = "INSERT INTO DetalleCarrito VALUES (?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM DetalleCarrito WHERE id = ?";
	private static final String readAll = "SELECT * FROM DetalleCarrito";
	
	
	@Override
	public boolean insert(DetalleCarritoDTO detalle) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			
			statement.setInt(1, detalle.getId());
			statement.setInt(2, detalle.getIdCarrito());
			statement.setInt(3, detalle.getIdProducto());
			statement.setInt(4, detalle.getIdStock());
			statement.setDouble(5, detalle.getCantidad());
			statement.setDouble(6, detalle.getPrecio());	

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
	public boolean delete(DetalleCarritoDTO detalle) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, detalle.getId());
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
	public List<DetalleCarritoDTO> readAll() {
		 PreparedStatement statement;
			ResultSet resultSet; // Guarda el resultado de la query
			ArrayList<DetalleCarritoDTO> detalle = new ArrayList<DetalleCarritoDTO>();
			Conexion conexion = Conexion.getConexion();
			try {
				statement = conexion.getSQLConexion().prepareStatement(readAll);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					detalle.add(getDetalleCarrito(resultSet));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return detalle;
	}
	
	private DetalleCarritoDTO getDetalleCarrito(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		int idCarrito = resultSet.getInt("idCarrito");
		int idProducto = resultSet.getInt("idProducto");
		int idStock = resultSet.getInt("idStock");
		double cant = resultSet.getDouble("cantidad");
		double precio = resultSet.getDouble("precio");
		
		return new DetalleCarritoDTO(id,idCarrito,idProducto,idStock,cant, precio);
	}

}
