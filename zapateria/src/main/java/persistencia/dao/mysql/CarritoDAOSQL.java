package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.MaestroProductoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.CarritoDAO;

public class CarritoDAOSQL implements CarritoDAO{

	private static final String insert = "INSERT INTO Carrito VALUES (?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM Carrito WHERE idCarrito=?";
	private static final String readAll = "SELECT * FROM Carrito";
	
	@Override
	public boolean insert(CarritoDTO carrito) {
 		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, carrito.getIdCarrito());
			statement.setInt(2, carrito.getIdSucursal());
			statement.setInt(3, carrito.getIdCliente());
			statement.setInt(4, carrito.getIdVendedor());
			statement.setDouble(5, carrito.getTotal());
			statement.setString(6, carrito.getHora());

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
	public boolean delete(CarritoDTO carrito) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, carrito.getIdCarrito());
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
	public List<CarritoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<CarritoDTO> carrito = new ArrayList<CarritoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				carrito.add(getCarritoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carrito;
	}
	private CarritoDTO getCarritoDTO(ResultSet resultSet) throws SQLException {
		int idCarrito = resultSet.getInt("idCarrito");
		int idSucursal = resultSet.getInt("idSucursal");
		int idCliente = resultSet.getInt("idCliente");
		int idVendedor = resultSet.getInt("idVendedor");
		double total = resultSet.getDouble("Total");
		String hora = resultSet.getString("Hora");
		
		return new CarritoDTO(idCarrito,idSucursal,idCliente,idVendedor,total,hora);
	}
	
	@Override
	public List<CarritoDTO> getCarritoFiltrado(String nombreColumna1, String txt1, String nombreColumna2, String txt2, String nombreColumna3,String txt3){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM Carrito";
		if(nombreColumna1!=null && txt1!=null) {
			sel = sel +" WHERE " + nombreColumna1 + " LIKE '%" + txt1 + "%'";
		}
		if(nombreColumna2!=null && txt2!=null) {
			sel = sel+" AND " + nombreColumna2 + " LIKE '%" + txt2 + "%'";
		}
		if(nombreColumna3!=null && txt3!=null) {
			sel = " AND " + nombreColumna3 + " LIKE '%" + txt3 + "%'";
		}
		
		ArrayList<CarritoDTO> carritos = new ArrayList<CarritoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				carritos.add(getCarritoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carritos;
	}

}
