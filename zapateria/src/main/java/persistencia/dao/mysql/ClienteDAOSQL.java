package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.ClienteDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ClienteDAO;
import java.util.List;

public class ClienteDAOSQL implements ClienteDAO {

	private static final String insert = "INSERT INTO clientes VALUES(?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM clientes WHERE idCliente = ?";
	private static final String update = "UPDATE clientes set Nombre=?, Apellido=?,CUIL=?, CorreoElectronico=?, LimiteCredito=?, CreditoDisponible=?, TipoCliente=?, ImpuestoAFIP=?, Estado=?, Calle=?, Altura=?, Pais=?, Provincia=?, Localidad=?, CodPostal=? where IdCliente=?";
	private static final String readall = "SELECT * FROM clientes";

	private static final String select = "SELECT * FROM clientes WHERE IdCliente=?";

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
			statement.setString(4, cliente.getCUIL());
			statement.setString(5, cliente.getCorreo());
			statement.setDouble(6, cliente.getLimiteCredito());
			statement.setDouble(7, cliente.getCreditoDisponible());
			statement.setString(8, cliente.getTipoCliente());
			statement.setString(9, cliente.getImpuestoAFIP());
			statement.setString(10, cliente.getEstado());
			statement.setString(11, cliente.getCalle());
			statement.setString(12, cliente.getAltura());
			statement.setString(13, cliente.getPais());
			statement.setString(14, cliente.getProvincia());
			statement.setString(15, cliente.getLocalidad());
			statement.setString(16, cliente.getCodPostal());

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
			statement.setString(3, cliente_nuevo.getCUIL());
			statement.setString(4, cliente_nuevo.getCorreo());
			statement.setDouble(5, cliente_nuevo.getLimiteCredito());
			statement.setDouble(6, cliente_nuevo.getCreditoDisponible());
			statement.setString(7, cliente_nuevo.getTipoCliente());
			statement.setString(8, cliente_nuevo.getImpuestoAFIP());
			statement.setString(9, cliente_nuevo.getEstado());
			statement.setString(10, cliente_nuevo.getCalle());
			statement.setString(11, cliente_nuevo.getAltura());
			statement.setString(12, cliente_nuevo.getPais());
			statement.setString(13, cliente_nuevo.getProvincia());
			statement.setString(14, cliente_nuevo.getLocalidad());
			statement.setString(15, cliente_nuevo.getCodPostal());
			statement.setInt(16, id_cliente_a_actualizar);

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
		int idCliente = resultSet.getInt("IdCliente");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		String CUIL = resultSet.getString("CUIL");
		String correo = resultSet.getString("CorreoElectronico");
		double limiteCredito = resultSet.getDouble("LimiteCredito");
		double creditoDisponible = resultSet.getDouble("CreditoDisponible");
		String tipoCliente = resultSet.getString("TipoCliente");
		String impuestoAFIP = resultSet.getString("ImpuestoAFIP");
		String estado = resultSet.getString("Estado");
		String calle = resultSet.getString("Calle");
		String altura = resultSet.getString("Altura");
		String pais = resultSet.getString("Pais");
		String provincia = resultSet.getString("Provincia");
		String localidad = resultSet.getString("Localidad");
		String codPostal = resultSet.getString("CodPostal");
		return new ClienteDTO(idCliente, nombre, apellido, CUIL, correo, limiteCredito, creditoDisponible, tipoCliente,
				impuestoAFIP, estado, calle, altura, pais, provincia, localidad, codPostal);
	}

	public List<ClienteDTO> getClienteAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2,
			String txtAprox2, String nombreColumna3, String txtAprox3, String nombreColumna4, String txtAprox4,
			String nombreColumna5, String txtAprox5) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		/*
		String sel = "SELECT * FROM clientes WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'" + " AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'" +  " AND " + nombreColumna3 + " LIKE '%" + txtAprox3 + "%'" +
				" AND " + nombreColumna4 + " LIKE '%" + txtAprox4 + "%'"+ " AND " + nombreColumna5 + " LIKE '%" + txtAprox5 + "%'";

		*/
		String sel = "SELECT * FROM Clientes";

		if (nombreColumna1 != null && txtAprox1 != null) {
			sel = sel + " WHERE " + nombreColumna1 + " LIKE '%" + txtAprox1 + "%'";
		}
		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + " AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}
		if (nombreColumna3 != null && txtAprox3 != null) {
			sel = sel + " AND " + nombreColumna3 + " LIKE '%" + txtAprox3 + "%'";
		}
		if (nombreColumna4 != null && txtAprox4 != null) {
			sel = sel + " AND " + nombreColumna4 + " LIKE '%" + txtAprox4 + "%'";
		}
		if (nombreColumna5 != null && txtAprox5 != null) {
			sel = sel + " AND " + nombreColumna5 + " LIKE '%" + txtAprox5 + "%'";
		}
			
		ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clientes.add(getClienteDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public ClienteDTO selectCliente(int idCliente) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ClienteDTO cliente = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setInt(1, idCliente);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				cliente = getClienteDTO(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

}
