package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.ProductoDeProveedorDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProductoDeProveedorDAO;

public class ProductoDeProveedorDAOSQL implements ProductoDeProveedorDAO{

	private static final String insert = "INSERT INTO productosDeProveedor VALUES(?,?,?,?,?)";
	private static final String delete = "DELETE FROM productosDeProveedor WHERE Id=?";
	private static final String readAll = "SELECT * FROM productosDeProveedor ORDER BY PrecioVenta";
	
	private static final String updateCantPorLote = "UPDATE productosDeProveedor SET CantidadPorLote=? WHERE Id=?";
	
	private static final String update = "UPDATE productosDeProveedor SET IdProveedor=?, IdMaestroProducto=?, PrecioVenta=?, CantidadPorLote=?  WHERE Id=?";
	
	@Override
	public boolean insert(ProductoDeProveedorDTO producto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, producto.getId());
			statement.setInt(2, producto.getIdProveedor());
			statement.setInt(3, producto.getIdMaestroProducto());
			statement.setDouble(4, producto.getPrecioVenta());
			statement.setDouble(5, producto.getCantidadPorLote());

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
	public boolean delete(ProductoDeProveedorDTO producto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, producto.getId());
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
	public List<ProductoDeProveedorDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<ProductoDeProveedorDTO> productos = new ArrayList<ProductoDeProveedorDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				productos.add(getProductoDeProveedorDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}

	private ProductoDeProveedorDTO getProductoDeProveedorDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("Id");
		int idProveedor = resultSet.getInt("IdProveedor");
		int idMaestroProducto = resultSet.getInt("IdMaestroProducto");
		double precioVenta = resultSet.getDouble("PrecioVenta");
		int cantPorLote = resultSet.getInt("CantidadPorLote");
		return new ProductoDeProveedorDTO(id,idProveedor,idMaestroProducto,precioVenta,cantPorLote);
	}
	
	
	@Override
	public boolean updateCantidadPorLote(double cantidad,int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(updateCantPorLote);

			statement.setDouble(1, cantidad);
			statement.setInt(2, id);

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
	public boolean update(ProductoDeProveedorDTO producto, int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);
	
			statement.setInt(1, producto.getIdProveedor());
			statement.setInt(2, producto.getIdMaestroProducto());
			statement.setDouble(3, producto.getPrecioVenta());
			statement.setInt(4, producto.getCantidadPorLote());
			statement.setDouble(5, id);
			
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}
}
