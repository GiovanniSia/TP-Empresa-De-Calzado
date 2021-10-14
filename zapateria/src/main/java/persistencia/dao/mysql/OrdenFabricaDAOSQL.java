package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.OrdenFabricaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.OrdenFabricaDAO;

public class OrdenFabricaDAOSQL implements OrdenFabricaDAO{
	
	private static final String insert = "INSERT INTO ordenFabrica(IdProd, FechaRequerido, Cantidad, CodigoLote, "
			+ "IdSucursal) VALUES(?, ?, ?, ?, ?)";
	private static final String readall = "SELECT * FROM ordenFabrica";
	
	public boolean insert(OrdenFabricaDTO ordenAInsertar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, ordenAInsertar.getIdProd());
			
			java.util.Date fecha = new java.util.Date();
			/*
			System.out.println (fecha);
			System.out.println(fecha.getDate());
			System.out.println(fecha.getMonth() + 1);
			System.out.println(fecha.getYear() + 1900);
			*/
			statement.setString(2, ordenAInsertar.getFechaRequerido());
			//statement.setString(3, "1987-01-01");
			//+getYear()+"-"+getMonth()+"-"+getDay()
			
			statement.setInt(3, ordenAInsertar.getCantidad());
			statement.setString(4, ordenAInsertar.getCodigoLote());
			statement.setInt(5, ordenAInsertar.getIdSucursal());
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
	
	public List<OrdenFabricaDTO> readAll(){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<OrdenFabricaDTO> clientes = new ArrayList<OrdenFabricaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clientes.add(getOrdenFabricaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	private OrdenFabricaDTO getOrdenFabricaDTO(ResultSet resultSet) throws SQLException {
		int IdOrdenFabrica = resultSet.getInt("IdOrdenFabrica");
		int IdProd = resultSet.getInt("IdProd");
		String FechaRequerido = resultSet.getString("FechaRequerido");
		int Cantidad = resultSet.getInt("Cantidad");
		String CodigoLote = resultSet.getString("CodigoLote");
		int IdSucursal = resultSet.getInt("IdSucursal");
		return new OrdenFabricaDTO(IdOrdenFabrica,IdProd,FechaRequerido,Cantidad,CodigoLote,IdSucursal);
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	
	private static final String readAllOrdenesPendientesDeUnProductoEnUnaSucursal = "SELECT * FROM ordenFabrica as orden WHERE (orden.IdOrdenFabrica IN (SELECT orden2.IdOrdenFabrica FROM ordenFabrica as orden2, fabricacionesEnMarcha as fabri WHERE (fabri.Estado = 'activo' OR fabri.Estado = 'completo')) OR orden.IdOrdenFabrica IN (SELECT ord3.IdOrdenFabrica FROM ordenFabrica as ord3 WHERE ord3.IdOrdenFabrica NOT IN (SELECT fabri2.IdOrdenFabrica FROM fabricacionesEnMarcha as fabri2))) AND orden.IdProd = ? AND orden.IdSucursal = ?;";
	
	public boolean hayOrdenPendiente(int idProducto, int idSucursal) {
		boolean ret = false;
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllOrdenesPendientesDeUnProductoEnUnaSucursal);
			statement.setInt(1, idProducto);
			statement.setInt(2, idSucursal);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				System.out.println("Encontro, no hay que generar");
				ret = true;		//ENCONTRO UNA ORDEN AUN ACTIVA, COMPLETA O CANCELADA
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
