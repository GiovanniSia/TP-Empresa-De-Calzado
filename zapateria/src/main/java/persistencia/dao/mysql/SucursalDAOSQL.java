package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.SucursalDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.SucursalDAO;

public class SucursalDAOSQL implements SucursalDAO{

	int idSucursal;

	String telefono, calle, altura, provincia, localidad, pais, codPostal;
	
	private static final String insert = "INSERT INTO sucursales VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM sucursales WHERE idSucursal = ?";
	private static final String update = "UPDATE sucursales set telefono=?, calle=?, altura=?, provincia=?, localidad=?, pais=?, codPostal where idSucursal=?";
	private static final String readall = "SELECT * FROM sucursales";
	
	@Override
	public boolean insert(SucursalDTO sucursal) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			
			statement.setInt(1, sucursal.getIdSucursal());
			statement.setString(2, sucursal.getTelefono());
			statement.setString(3, sucursal.getCalle());
			statement.setString(4, sucursal.getAltura());
			statement.setString(5, sucursal.getProvincia());
			statement.setString(6, sucursal.getLocalidad());
			statement.setString(7, sucursal.getPais());
			statement.setString(8, sucursal.getCodPostal());
			
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
	public boolean delete(SucursalDTO sucursal_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, sucursal_a_eliminar.getIdSucursal());
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
	public boolean update(int id_sucursal_a_actualizar, SucursalDTO sucursal_nueva) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, sucursal_nueva.getTelefono());
			statement.setString(2, sucursal_nueva.getCalle());
			statement.setString(3, sucursal_nueva.getAltura());
			statement.setString(4, sucursal_nueva.getProvincia());
			statement.setString(5, sucursal_nueva.getLocalidad());
			statement.setString(6, sucursal_nueva.getPais());
			statement.setString(7, sucursal_nueva.getCodPostal());

			statement.setInt(8, id_sucursal_a_actualizar);

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
	public List<SucursalDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<SucursalDTO> sucursales = new ArrayList<SucursalDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				sucursales.add(getSucursalDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucursales;
	}

	
	private SucursalDTO getSucursalDTO(ResultSet resultSet) throws SQLException {
		int idSucursal = resultSet.getInt("idSucursal");
		String telefono = resultSet.getString("telefono");
		String calle = resultSet.getString("calle");
		String altura = resultSet.getString("altura");
		String provincia = resultSet.getString("provincia");
		String localidad = resultSet.getString("localidad");
		String pais = resultSet.getString("pais");
		String codPostal = resultSet.getString("codPostal");
		return new SucursalDTO(idSucursal,telefono,calle,altura,provincia,localidad,pais,codPostal);
	}
	
}
