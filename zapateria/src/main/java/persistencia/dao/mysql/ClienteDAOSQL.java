package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dto.ClienteDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ClienteDAO;
import presentacion.vista.VentanaBusquedaCliente;
import java.util.List;
public class ClienteDAOSQL implements ClienteDAO{
	
	private static final String insert = "INSERT INTO clientes VALUES(?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM clientes WHERE idCliente = ?";
	private static final String update = "UPDATE clientes set Nombre=?, Apellido=?, CorreoElectronico=?, LimiteCredito=?, CreditoDisponible=?, TipoCliente=?, ImpuestoAFIP=?, Estado=?, Calle=?, Altura=?, Pais=?, Provincia=?, Localidad=?, CodPostal=? where IdCliente=?";
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
			statement.setString(4, cliente.getDNI());
			statement.setString(5, cliente.getCorreo());
			statement.setInt(6, cliente.getLimiteCredito());
			statement.setInt(7, cliente.getCreditoDisponible());
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
			statement.setString(3, cliente_nuevo.getDNI());
			statement.setString(4, cliente_nuevo.getCorreo());
			statement.setInt(5, cliente_nuevo.getLimiteCredito());
			statement.setInt(6, cliente_nuevo.getCreditoDisponible());
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
		String DNI = resultSet.getString("DNI");
		String correo = resultSet.getString("CorreoElectronico");
		int limiteCredito = resultSet.getInt("LimiteCredito");
		int creditoDisponible = resultSet.getInt("CreditoDisponible");
		String tipoCliente = resultSet.getString("TipoCliente");
		String impuestoAFIP = resultSet.getString("ImpuestoAFIP");
		String estado = resultSet.getString("Estado");
		String calle = resultSet.getString("Calle");
		String altura = resultSet.getString("Altura");
		String pais = resultSet.getString("Pais");
		String provincia = resultSet.getString("Provincia");
		String localidad = resultSet.getString("Localidad");
		String codPostal = resultSet.getString("CodPostal");
		return new ClienteDTO(idCliente, nombre, apellido,DNI, correo, limiteCredito, creditoDisponible, tipoCliente, impuestoAFIP, estado, calle, altura, pais, provincia, localidad, codPostal);
	}
	
	public List<ClienteDTO> filtrarPorCodCliente(String valor) {
		
		VentanaBusquedaCliente ventanaBusquedaCliente = new VentanaBusquedaCliente();
		
		ArrayList<ClienteDTO> listaCliente = new ArrayList<ClienteDTO>();
		
		String[] titulos = {"Cod. Cliente" , "Nombre" , "Apellido" , "DNI" , "Correo Electronico", "Estado"};
		String[] registros = new String[7];
		
		DefaultTableModel modelo = new DefaultTableModel(null, titulos);
		
		String SQL = "select * from clientes where IdCliente like '"+ valor +"%'";
		
		try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			
			Statement st=conexion.createStatement();
			ResultSet rs=st.executeQuery(SQL);
			 
	
          while (rs.next()) {
        	  listaCliente.add(getClienteDTO(rs));
          }
   
		}catch (Exception e) {
			System.out.println("Error al mostrar datos: "+ e.getMessage());
		}
		 return listaCliente;

	}
	
	
	
	
	
	
}
