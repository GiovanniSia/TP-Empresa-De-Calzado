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
		int idFactura = resultSet.getInt("Id");
		double montoPendiente = resultSet.getDouble("MontoPendiente");
		int idCliente = resultSet.getInt("IdCliente");
		String nombreCliente = resultSet.getString("NombreCliente");
		int idEmpleado = resultSet.getInt("IdEmpleado");
		String nombreEmpleado = resultSet.getString("NombreEmpleado");
		String fecha = resultSet.getString("Fecha");
		String tipoFactura = resultSet.getString("TipoFactura");
		
		String nroFacturaCompleta = resultSet.getString("NroFacturaCompleta");
		int idSucursal = resultSet.getInt("IdSucursal");
		double descuento = resultSet.getDouble("Descuento");
		double totalBruto = resultSet.getDouble("TotalBruto");
		double totalFactura = resultSet.getDouble("TotalFactura");
		String tipoVenta = resultSet.getString("TipoVenta");
		
		String calle = resultSet.getString("Calle");
		String altura = resultSet.getString("Altura");
		String pais = resultSet.getString("Pais");
		String prov = resultSet.getString("Provincia");
		String localidad = resultSet.getString("Localidad");
		String CodPostal = resultSet.getString("CodPostal");
		String CUIL = resultSet.getString("CUIL");
		String correo = resultSet.getString("CorreoElectronico");
		String impuestoAFIP = resultSet.getString("ImpuestoAFIP");
		double iva = resultSet.getDouble("IVA");
//		int idMedioDePago = resultSet.getInt("IdMedioDePago");
		
		
		return new FacturaDTO(idFactura,montoPendiente,idCliente,nombreCliente,idEmpleado,nombreEmpleado,fecha,tipoFactura,nroFacturaCompleta,idSucursal,descuento,totalBruto,totalFactura,tipoVenta,calle,altura,pais,prov,localidad,CodPostal,CUIL,correo,impuestoAFIP,iva);
	}
}
