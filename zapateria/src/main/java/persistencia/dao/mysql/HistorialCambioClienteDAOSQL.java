package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.HistorialCambioClienteDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.HistorialCambioClienteDAO;

public class HistorialCambioClienteDAOSQL implements HistorialCambioClienteDAO{

	private static final String insert = "INSERT INTO HistorialDeCambiosCliente VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM HistorialDeCambiosCliente WHERE Id = ?";
	private static final String readall = "SELECT * FROM HistorialDeCambiosCliente";
	
	@Override
	public boolean insert(HistorialCambioClienteDTO historialCambioCliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
	
			statement.setInt(1, historialCambioCliente.getId());
			statement.setInt(2, historialCambioCliente.getIdEmpleado());
			statement.setString(3, historialCambioCliente.getFecha());
			statement.setString(4, historialCambioCliente.getNombreAntiguo());
			statement.setString(5, historialCambioCliente.getNombreNuevo());
			statement.setString(6, historialCambioCliente.getApellidoAntiguo());
			statement.setString(7, historialCambioCliente.getApellidoNuevo());
			statement.setString(8, historialCambioCliente.getCUILAntiguo());
			statement.setString(9, historialCambioCliente.getCUILNuevo());
			statement.setString(10, historialCambioCliente.getCorreoAntiguo());
			statement.setString(11, historialCambioCliente.getCorreoNuevo());
			statement.setDouble(12, historialCambioCliente.getLimiteCreditoAntiguo());
			statement.setDouble(13, historialCambioCliente.getLimiteCreditoNuevo());
			statement.setDouble(14, historialCambioCliente.getCreditoDisponibleAntiguo());
			statement.setDouble(15, historialCambioCliente.getCreditoDisponibleNuevo());
			statement.setString(16, historialCambioCliente.getTipoClienteAntiguo());
			statement.setString(17, historialCambioCliente.getTipoClienteNuevo());
			statement.setString(18, historialCambioCliente.getImpuestoAFIPAntiguo());
			statement.setString(19, historialCambioCliente.getImpuestoAFIPNuevo());
			statement.setString(20, historialCambioCliente.getEstadoAntiguo());
			statement.setString(21, historialCambioCliente.getEstadoNuevo());
			statement.setString(22, historialCambioCliente.getCalleAntiguo());
			statement.setString(23, historialCambioCliente.getCalleNuevo());
			statement.setString(24, historialCambioCliente.getAlturaAntiguo());
			statement.setString(25, historialCambioCliente.getAlturaNuevo());
			statement.setString(26, historialCambioCliente.getPaisAntiguo());
			statement.setString(27, historialCambioCliente.getPaisNuevo());
			statement.setString(28, historialCambioCliente.getProvinciaAntiguo());
			statement.setString(29, historialCambioCliente.getProvinciaNuevo());
			statement.setString(30, historialCambioCliente.getLocalidadAntiguo());
			statement.setString(31, historialCambioCliente.getLocalidadNuevo());
			statement.setString(32, historialCambioCliente.getCodPostalAntiguo());
			statement.setString(33, historialCambioCliente.getCodPostalNuevo());
			
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
	public boolean delete(HistorialCambioClienteDTO historialCC) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int idHistorialCambioCliente, HistorialCambioClienteDTO historialCC) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<HistorialCambioClienteDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<HistorialCambioClienteDTO> historial = new ArrayList<HistorialCambioClienteDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				historial.add(getHistorialCambioClienteDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historial;
	}

	
	private HistorialCambioClienteDTO getHistorialCambioClienteDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("Id");
		int idEmpleado = resultSet.getInt("IdEmpleado");
		String fecha = resultSet.getString("Fecha");
		
		String nombreAntiguo = resultSet.getString("NombreAntiguo");
		String nombreNuevo = resultSet.getString("NombreNuevo");
		
		String apellidoAntiguo= resultSet.getString("ApellidoAntiguo");
		String apellidoNuevo = resultSet.getString("ApellidoNuevo");
		
		String CUILAntiguo = resultSet.getString("CUILAntiguo");
		String CUILNuevo = resultSet.getString("CUILNuevo");
		
		String correoAntiguo = resultSet.getString("CorreoElectronicoAntiguo");
		String correoNuevo = resultSet.getString("CorreoElectronicoNuevo");
		
		double limiteCreditoAntiguo = resultSet.getDouble("LimiteCreditoAntiguo");
		double limiteCreditoNuevo = resultSet.getDouble("LimiteCreditoNuevo");
		
		double creditoDisponibleAntiguo = resultSet.getDouble("CreditoDisponibleAntiguo");
		double creditoDisponibleNuevo = resultSet.getDouble("CreditoDisponibleNuevo");
		
		String tipoClienteAntiguo = resultSet.getString("TipoClienteAntiguo");
		String tipoClienteNuevo = resultSet.getString("TipoClienteNuevo");
		
		String impuestoAFIPAntiguo = resultSet.getString("ImpuestoAFIPAntiguo");
		String impuestoAFIPNuevo = resultSet.getString("ImpuestoAFIPNuevo");
		
		String estadoAntiguo = resultSet.getString("EstadoAntiguo");
		String estadoNuevo = resultSet.getString("EstadoNuevo");
		
		String calleAntiguo = resultSet.getString("CalleAntiguo");
		String calleNuevo = resultSet.getString("CalleNuevo");
		
		String alturaAntiguo = resultSet.getString("AlturaAntiguo");
		String alturaNuevo = resultSet.getString("AlturaNuevo");
		
		String paisAntiguo = resultSet.getString("PaisAntiguo");
		String paisNuevo = resultSet.getString("PaisNuevo");
		
		String provinciaAntiguo = resultSet.getString("ProvinciaAntiguo");
		String provinciaNuevo = resultSet.getString("ProvinciaNuevo");
		
		String localidadAntiguo = resultSet.getString("LocalidadAntiguo");
		String localidadNuevo = resultSet.getString("LocalidadNuevo");
		
		String codPostalAntiguo = resultSet.getString("CodPostalAntiguo");
		String codPostalNuevo = resultSet.getString("CodPostalNuevo");
		
		return new HistorialCambioClienteDTO(id,idEmpleado,fecha,nombreAntiguo,nombreNuevo,apellidoAntiguo,apellidoNuevo,CUILAntiguo,CUILNuevo,correoAntiguo,correoNuevo,limiteCreditoAntiguo,limiteCreditoNuevo,creditoDisponibleAntiguo,creditoDisponibleNuevo,tipoClienteAntiguo,tipoClienteNuevo,impuestoAFIPAntiguo,impuestoAFIPNuevo,estadoAntiguo,estadoNuevo,calleAntiguo,calleNuevo,alturaAntiguo,alturaNuevo,paisAntiguo,paisNuevo,provinciaAntiguo,provinciaNuevo,localidadAntiguo,localidadNuevo,codPostalAntiguo,codPostalNuevo);
	}
	
	
	@Override
	public List<HistorialCambioClienteDTO> getHistorialCambioClienteAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2) {
		// TODO Auto-generated method stub
		return null;
	}

}
