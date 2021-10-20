package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		/*
		int idStock = resultSet.getInt("IdStock");
		String codigoLote = resultSet.getString("CodigoLote");
		double totalFactura = resultSet.getDouble("TotalFactura");*/
		return new RechazoCompraVirtualDTO(Id,Hora,Fecha,idSucursal,pago,Nombre,Apellido,CUIL,CorreoElectronico,
				TipoCliente, ImpuestoAFIP, Estado, Calle, Altura, Pais, Provincia, Localidad, CodPostal);
	}

}
