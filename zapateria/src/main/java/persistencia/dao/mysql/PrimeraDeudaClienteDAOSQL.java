package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PrimeraDeudaClienteDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PrimeraDeudaClienteDAO;

public class PrimeraDeudaClienteDAOSQL implements PrimeraDeudaClienteDAO{
	private static final String insert = "INSERT INTO primeraDeudaCliente VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM primeraDeudaCliente WHERE Id = ?";
	private static final String update = "UPDATE primeraDeudaCliente set IdCliente=?, FechaDeuda=? where Id=?";
	private static final String readall = "SELECT * FROM primeraDeudaCliente";
	@Override
	public boolean insert(PrimeraDeudaClienteDTO primeraDeudaCliente)  {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			
			statement.setInt(1, primeraDeudaCliente.getId());
			statement.setInt(2, primeraDeudaCliente.getIdCliente());
			statement.setString(3, primeraDeudaCliente.getFechaDeuda());

			
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
	public boolean delete(PrimeraDeudaClienteDTO primeraDeudaCliente_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, primeraDeudaCliente_a_eliminar.getId());
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
	public boolean update(int id_primeraDeudaCliente_a_actualizar, PrimeraDeudaClienteDTO primeraDeudaCliente_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setInt(1, primeraDeudaCliente_nuevo.getIdCliente());
			statement.setString(2, primeraDeudaCliente_nuevo.getFechaDeuda());
			statement.setInt(3, id_primeraDeudaCliente_a_actualizar);

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
	public List<PrimeraDeudaClienteDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PrimeraDeudaClienteDTO> ingresos = new ArrayList<PrimeraDeudaClienteDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ingresos.add(getIPrimeraDeudaClienteDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingresos;
	}

	@Override
	public List<PrimeraDeudaClienteDTO> getFiltrarPor(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query

		String sel = "SELECT * FROM primeraDeudaCliente WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'"
				+ " AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		
		ArrayList<PrimeraDeudaClienteDTO> ingresos = new ArrayList<PrimeraDeudaClienteDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ingresos.add(getIPrimeraDeudaClienteDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingresos;
	}

	private PrimeraDeudaClienteDTO getIPrimeraDeudaClienteDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("Id");
		int idCliente = resultSet.getInt("IdCliente");
		String fechaDeuda = resultSet.getString("FechaDeuda");

		return new PrimeraDeudaClienteDTO(id, idCliente, fechaDeuda);
	}

}
