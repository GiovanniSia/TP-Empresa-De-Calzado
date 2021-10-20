package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dto.CajaDTO;
import dto.CompraVirtualDTO;
import dto.RechazoCompraVirtualDTO;
import dto.StockDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.RechazoCompraVirtualDAO;

public class RechazoCompraVirtualDAOSQL implements RechazoCompraVirtualDAO {
	
	private static final String readall = "SELECT * FROM RechazoCompraVirtual";
	public List<RechazoCompraVirtualDTO> readAllRechazosComprasVirtuales() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<RechazoCompraVirtualDTO> rechazos = new ArrayList<RechazoCompraVirtualDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				rechazos.add(getCompraVirtualDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rechazos;
	}
	
	private RechazoCompraVirtualDTO getCompraVirtualDTO(ResultSet resultSet) throws SQLException {
		int Id = resultSet.getInt("Id");
		String Hora = resultSet.getString("Hora");
		String Fecha = resultSet.getString("Fecha");
		int idSucursal = resultSet.getInt("idSucursal");
		double pago = resultSet.getDouble("pago");
		String Nombre = resultSet.getString("Nombre");
		String Apellido = resultSet.getString("Apellido");
		String CUIL = resultSet.getString("CUIL");
		String CorreoElectronico = resultSet.getString("CorreoElectronico");
		String TipoCliente = resultSet.getString("TipoCliente");
		String ImpuestoAFIP = resultSet.getString("ImpuestoAFIP");
		String Estado = resultSet.getString("Estado");
		String Calle = resultSet.getString("Calle");
		String Altura = resultSet.getString("Altura"); 
		String Pais = resultSet.getString("Pais");
		String Provincia = resultSet.getString("Provincia");
		String Localidad = resultSet.getString("Localidad");
		String CodPostal = resultSet.getString("CodPostal");
		String motivo = resultSet.getString("Motivo");
		/*
		int idStock = resultSet.getInt("IdStock");
		String codigoLote = resultSet.getString("CodigoLote");
		double totalFactura = resultSet.getDouble("TotalFactura");*/
		return new RechazoCompraVirtualDTO(Id,Hora,Fecha,idSucursal,pago,Nombre,Apellido,CUIL,CorreoElectronico,
				TipoCliente, ImpuestoAFIP, Estado, Calle, Altura, Pais, Provincia, Localidad, CodPostal, motivo);
	}
	
	private static final String insertRechazoCompraVirtual = "INSERT INTO RechazoCompraVirtual VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public boolean insertRechazoCompraVirtualDAOSQL(CompraVirtualDTO compra, String motivo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insertRechazoCompraVirtual);

			statement.setInt(1, 0);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	        String fecha = dtf.format(LocalDateTime.now());

	        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	        String hora = tf.format(LocalDateTime.now());
	        
			statement.setString(2, hora);
			statement.setString(3, fecha);
			statement.setInt(4, compra.getIdSucursal());
			statement.setDouble(5, compra.getPago());
			statement.setString(6, compra.getNombre());
			statement.setString(7, compra.getApellido());
			statement.setString(8, compra.getCUIL());
			statement.setString(9, compra.getCorreoElectronico());
			statement.setString(10, compra.getTipoCliente());
			statement.setString(11, compra.getImpuestoAFIP());
			statement.setString(12, compra.getEstado());
			statement.setString(13, compra.getCalle());
			statement.setString(14, compra.getAltura());
			statement.setString(15, compra.getPais());
			statement.setString(16, compra.getProvincia());
			statement.setString(17, compra.getLocalidad());
			statement.setString(18, compra.getCodPostal());
			statement.setString(19, motivo);

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

}
