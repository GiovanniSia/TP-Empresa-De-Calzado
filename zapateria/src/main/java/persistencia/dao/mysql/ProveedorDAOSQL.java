package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PaisDTO;
import dto.ProveedorDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProveedorDAO;

public class ProveedorDAOSQL implements ProveedorDAO{
	
	private static final String insert = "INSERT INTO proveedor VALUES(?,?,?,?)";
	private static final String readAll = "SELECT * FROM proveedor";
	private static final String update = "UPDATE proveedor SET Nombre=?, CorreoElectronico=?, LimiteCredito=? WHERE IdProveedor=?";
	
	@Override
	public boolean insert(ProveedorDTO proveedor) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, proveedor.getId());
			statement.setString(2, proveedor.getNombre());
			statement.setString(3, proveedor.getCorreo());
			statement.setDouble(4, proveedor.getLimiteCredito());

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
	public boolean update(ProveedorDTO proveedor,int idProveedor) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, proveedor.getNombre());
			statement.setString(2, proveedor.getCorreo());
			statement.setDouble(3, proveedor.getLimiteCredito());
			statement.setInt(4, idProveedor);

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
	public List<ProveedorDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<ProveedorDTO> proveedores = new ArrayList<ProveedorDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				proveedores.add(getProveedorDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proveedores;
	}

	private ProveedorDTO getProveedorDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("IdProveedor");
		String nombre = resultSet.getString("Nombre");
		String correo = resultSet.getString("CorreoElectronico");
		double limiteCredito = resultSet.getDouble("LimiteCredito");
		return new ProveedorDTO(id,nombre,correo,limiteCredito);
	}

	@Override
	public List<ProveedorDTO> getProveedorAproximado(String nombreColumna,String txt){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM proveedor";

		if (nombreColumna != null && txt!= null) {
			sel = sel + " WHERE "+ nombreColumna + " LIKE '%" + txt + "%'";
		}
		ArrayList<ProveedorDTO> proveedores = new ArrayList<ProveedorDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				proveedores.add(getProveedorDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proveedores;
	}
	
}
