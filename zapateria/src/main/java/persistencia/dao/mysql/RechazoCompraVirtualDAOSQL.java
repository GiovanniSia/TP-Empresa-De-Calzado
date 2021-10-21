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
import dto.RechazoCompraVirtualDetalleDTO;
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
		//String TipoCliente = resultSet.getString("TipoCliente");
		String ImpuestoAFIP = resultSet.getString("ImpuestoAFIP");
		String Estado = resultSet.getString("Estado");
		String Calle = resultSet.getString("Calle");
		String Altura = resultSet.getString("Altura"); 
		String Pais = resultSet.getString("Pais");
		String Provincia = resultSet.getString("Provincia");
		String Localidad = resultSet.getString("Localidad");
		String CodPostal = resultSet.getString("CodPostal");
		String motivo = resultSet.getString("Motivo");
		return new RechazoCompraVirtualDTO(Id,Hora,Fecha,idSucursal,pago,Nombre,Apellido,CUIL,CorreoElectronico,
				//TipoCliente,
				ImpuestoAFIP, Estado, Calle, Altura, Pais, Provincia, Localidad, CodPostal, motivo);
	}
	
	private static final String insertRechazoCompraVirtual = "INSERT INTO RechazoCompraVirtual VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			
			statement.setString(6, seguroNoNull(compra.getNombre()));
			statement.setString(7, seguroNoNull(compra.getApellido()));
			statement.setString(8, seguroNoNull(compra.getCUIL()));
			statement.setString(9, seguroNoNull(compra.getCorreoElectronico()));
			//statement.setString(10, compra.getTipoCliente());
			statement.setString(10, seguroNoNull(compra.getImpuestoAFIP()));
			statement.setString(11, seguroNoNull(compra.getEstado()));
			statement.setString(12, seguroNoNull(compra.getCalle()));
			statement.setString(13, seguroNoNull(compra.getAltura()));
			statement.setString(14, seguroNoNull(compra.getPais()));
			statement.setString(15, seguroNoNull(compra.getProvincia()));
			statement.setString(16, seguroNoNull(compra.getLocalidad()));
			statement.setString(17, seguroNoNull(compra.getCodPostal()));
			statement.setString(18, seguroNoNull(motivo));

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
	
	private String seguroNoNull(String verificar) {
		if(verificar == null)
			return "[Dato nulo]";
		return verificar;
	}
	
	private static final String insertDetalleRechazoCompraVirtual = "INSERT INTO RechazoCompraVirtualDetalle VALUES(?,?,?,?,?,?,?,?)";
	public boolean insertDetalleRechazoCompraVirtual(RechazoCompraVirtualDetalleDTO detalle) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insertDetalleRechazoCompraVirtual);
			statement.setInt(1, 0);
			statement.setInt(2, detalle.getIdRechazoCompraVirtual());
			statement.setInt(3, detalle.getIdProducto());
			statement.setString(4, detalle.getNombreProducto());
			statement.setDouble(5, detalle.getPrecioMayorista());
			statement.setDouble(6, detalle.getPrecioMinorista());
			statement.setDouble(7, detalle.getPrecioCosto());
			statement.setInt(8, detalle.getCantidad());
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
	
	private static final String readAllDetalle = "SELECT * FROM RechazoCompraVirtualDetalle WHERE IdRechazoCompraVirtual = ?;";
	public List<RechazoCompraVirtualDetalleDTO> readAllDetallesDeUnRechazoCompraVirtual(int IdRechazoCompraVirtual) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<RechazoCompraVirtualDetalleDTO> rechazos = new ArrayList<RechazoCompraVirtualDetalleDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllDetalle);
			statement.setInt(1, IdRechazoCompraVirtual);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				rechazos.add(getDetalleRechazoCompraVirtualDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rechazos;
	}
	
	private RechazoCompraVirtualDetalleDTO getDetalleRechazoCompraVirtualDTO(ResultSet resultSet) throws SQLException {
		int Id = resultSet.getInt("Id");
		int IdRechazoCompraVirtual = resultSet.getInt("IdRechazoCompraVirtual");
		int IdProducto = resultSet.getInt("IdProducto");
		String NombreProducto = resultSet.getString("NombreProducto");
		double PrecioMayorista = resultSet.getDouble("PrecioMayorista");
		double PrecioMinorista = resultSet.getDouble("PrecioMinorista");
		double PrecioCosto = resultSet.getDouble("PrecioCosto");
		int Cantidad = resultSet.getInt("Cantidad");
		return new RechazoCompraVirtualDetalleDTO(Id,IdRechazoCompraVirtual,IdProducto,NombreProducto,
				PrecioMayorista,PrecioMinorista,PrecioCosto,Cantidad);
	}

}
