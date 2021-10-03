package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.FacturaDAO;
import dto.FacturaDTO;

public class FacturaDAOSQL implements FacturaDAO {
	private static final String readall = "SELECT * FROM factura";



	public List<FacturaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<FacturaDTO> factura = new ArrayList<FacturaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				factura.add(getFacturaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return factura;
	}

	private FacturaDTO getFacturaDTO(ResultSet resultSet) throws SQLException {
		int idFactura = resultSet.getInt("IdFactura");
		int montoPendiente = resultSet.getInt("MontoPendiente");
		String detalle = resultSet.getString("Detalle");
		int idCliente = resultSet.getInt("IdCliente");
		int idEmpleado = resultSet.getInt("IdEmpleado");
		Date fecha = resultSet.getDate("Fecha");
		String tipoFactura = resultSet.getString("TipoFactura");
		String nroFacturaCompleta = resultSet.getString("NroFacturaCompleta");
		int idSucursal = resultSet.getInt("IdSucursal");
		int terceraParte = resultSet.getInt("TerceraParte");
		int descuento = resultSet.getInt("Descuento");
		int totalFactura = resultSet.getInt("TotalFactura");
		int idMedioDePago = resultSet.getInt("IdMedioDePago");
		String tipoVenta = resultSet.getString("TipoVenta");
		
		return new FacturaDTO(idFactura,montoPendiente,detalle,idCliente,idEmpleado,fecha,tipoFactura,nroFacturaCompleta,idSucursal,terceraParte,descuento,totalFactura,idMedioDePago,tipoVenta);
	}
}
