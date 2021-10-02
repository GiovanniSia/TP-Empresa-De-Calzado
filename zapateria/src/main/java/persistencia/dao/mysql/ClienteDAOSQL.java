package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ClienteDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ClienteDAO;

public class ClienteDAOSQL implements ClienteDAO{
	
	private static final String insert = "INSERT INTO clientes VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM clientes WHERE idCliente = ?";
	private static final String update = "UPDATE clientes set nombre=?, apellido=?, correo=?, limiteCredito=?, creditoDisponible=?, tipoCliente=?, impuestoAFIP=?, estado=?, calle=?, altura=?, pais=?, provincia=?, localidad=?, codPostal=? where idCliente=?";
	private static final String readall = "SELECT * FROM clientes";

	@Override
	public boolean insert(ClienteDTO cliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			
			statement.setInt(1, cliente.getIdCliente());
			statement.setString(2, cliente.getNombre());
			statement.setString(3, cliente.getApellido());
			statement.setString(4, cliente.getCorreo());
			statement.setInt(5, cliente.getLimiteCredito());
			statement.setInt(6, cliente.getCreditoDisponible());
			statement.setString(7, cliente.getTipoCliente());
			statement.setString(8, cliente.getImpuestoAFIP());
			statement.setString(9, cliente.getEstado());
			statement.setString(10, cliente.getCalle());
			statement.setString(11, cliente.getAltura());
			statement.setString(12, cliente.getPais());
			statement.setString(13, cliente.getProvincia());
			statement.setString(14, cliente.getLocalidad());
			statement.setString(15, cliente.getCodPostal());
			
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
	public boolean delete(ClienteDTO cliente_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, cliente_a_eliminar.getIdCliente());
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
	public boolean update(int id_cliente_a_actualizar, ClienteDTO cliente_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);


			statement.setString(1, cliente_nuevo.getNombre());
			statement.setString(2, cliente_nuevo.getApellido());
			statement.setString(3, cliente_nuevo.getCorreo());
			statement.setInt(4, cliente_nuevo.getLimiteCredito());
			statement.setInt(5, cliente_nuevo.getCreditoDisponible());
			statement.setString(6, cliente_nuevo.getTipoCliente());
			statement.setString(7, cliente_nuevo.getImpuestoAFIP());
			statement.setString(8, cliente_nuevo.getEstado());
			statement.setString(9, cliente_nuevo.getCalle());
			statement.setString(10, cliente_nuevo.getAltura());
			statement.setString(11, cliente_nuevo.getPais());
			statement.setString(12, cliente_nuevo.getProvincia());
			statement.setString(13, cliente_nuevo.getLocalidad());
			statement.setString(14, cliente_nuevo.getCodPostal());
			statement.setInt(15, id_cliente_a_actualizar);

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
	public ArrayList<ClienteDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clientes.add(getClienteDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	private ClienteDTO getClienteDTO(ResultSet resultSet) throws SQLException {
		int idCliente = resultSet.getInt("idCliente");
		String nombre = resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		String correo = resultSet.getString("correo");
		int limiteCredito = resultSet.getInt("limiteCredito");
		int creditoDisponible = resultSet.getInt("creditoDisponible");
		String tipoCliente = resultSet.getString("tipoCliente");
		String impuestoAFIP = resultSet.getString("impuestoAFIP");
		String estado = resultSet.getString("estado");
		String calle = resultSet.getString("calle");
		String altura = resultSet.getString("altura");
		String pais = resultSet.getString("pais");
		String provincia = resultSet.getString("provincia");
		String localidad = resultSet.getString("localidad");
		String codPostal = resultSet.getString("codPostal");
		return new ClienteDTO(idCliente, nombre, apellido, correo, limiteCredito, creditoDisponible, tipoCliente, impuestoAFIP, estado, calle, altura, pais, provincia, localidad, codPostal);
	}
	
}
