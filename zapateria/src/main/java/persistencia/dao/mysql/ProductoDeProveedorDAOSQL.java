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
	private static final String readAll = "SELECT * FROM productosDeProveedor";
	
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

}
