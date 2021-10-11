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
	
	private static final String insert = "INSERT INTO Factura VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String readall = "SELECT * FROM factura";

	
	@Override
	public boolean insert(FacturaDTO factura) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, factura.getIdFactura());
			statement.setDouble(2, factura.getMontoPendiente());
			statement.setInt(3, factura.getIdCliente());
			statement.setString(4, factura.getNombreCliente());
			statement.setInt(5, factura.getIdEmpleado());
			statement.setString(6, factura.getNombreEmpleado());
			statement.setString(7, factura.getFecha());
			statement.setString(8, factura.getTipoFactura());
			statement.setString(9, factura.getNroFacturaCompleta());
			statement.setInt(10, factura.getIdSucursal());
			statement.setDouble(11, factura.getDescuento());
			statement.setDouble(12, factura.getTotalBruto());
			statement.setDouble(13, factura.getTotalFactura());
			statement.setString(14, factura.getTipoVenta());
			statement.setString(15, factura.getCalleCliente());
			statement.setString(16, factura.getAlturaCliente());
			statement.setString(17, factura.getPaisCliente());
			statement.setString(18, factura.getProvinciaCliente());
			statement.setString(19, factura.getLocalidadCliente());
			statement.setString(20, factura.getCodPostalCliente());
			statement.setString(21, factura.getCuilCliente());
			statement.setString(22, factura.getCorreoElectronicoCliente());
			statement.setString(23, factura.getImpuestoAFIPCliente());
			statement.setDouble(24, factura.getIVA());

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
	/*
	CREATE TABLE `Factura`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `MontoPendiente` double(45,2) NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `NombreCliente` varchar(45) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `NombreEmpleado` varchar(45) NOT NULL,
  `Fecha` DATE NOT NULL,
  `TipoFactura` varchar(45) NOT NULL,
  `NroFacturaCompleta` varchar(45) NOT NULL,
  `IdSucursal` int(11) NOT NULL,
  `Descuento` double(45,2) NOT NULL,
  `TotalBruto` double(45,2) NOT NULL,
  `TotalFactura` double(45,2) NOT NULL,
  `TipoVenta` varchar(45) NOT NULL,
  `Calle` varchar(45) NOT NULL,
  `Altura` varchar(45) NOT NULL,
  `Pais` varchar(45) NOT NULL,
  `Provincia` varchar(45) NOT NULL,
  `Localidad` varchar(45) NOT NULL,
  `CodPostal` varchar(45) NOT NULL,
  `CUIL` varchar(45) NOT NULL,
  `CorreoElectronico` varchar(45) NOT NULL,
  `ImpuestoAFIP` varchar(45) NOT NULL,
  `IVA` double(45,2) NOT NULL,
  PRIMARY KEY (`Id`)
  
  
  NO HACE FALTA EL MEDIO DE PAGO TAMBIEN?
);
	*/
}
