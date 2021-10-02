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
	private static final String readall = "SELECT * FROM Factura";



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
		double montoPendiente = resultSet.getDouble("MontoPendiente");
		String detalle = resultSet.getString("Detalle");
		int idCliente = resultSet.getInt("IdCliente");
		int idEmpleado = resultSet.getInt("IdEmpleado");
		Date fecha = resultSet.getDate("Fecha");
		String tipoFactura = resultSet.getString("TipoFactura");
		int nroFacturaCompleta = resultSet.getInt("NroFacturaCompleta");
		int idSucursal = resultSet.getInt("IdSucursal");
		double descuento = resultSet.getDouble("Descuento");
		double totalFactura = resultSet.getDouble("TotalFactura");
		int idMedioDePago = resultSet.getInt("IdMedioDePago");
		String tipoVenta = resultSet.getString("TipoVenta");
		
		return new FacturaDTO(idFactura,montoPendiente,detalle,idCliente,idEmpleado,fecha,tipoFactura,nroFacturaCompleta,idSucursal,descuento,totalFactura,idMedioDePago,tipoVenta);
	}
}

