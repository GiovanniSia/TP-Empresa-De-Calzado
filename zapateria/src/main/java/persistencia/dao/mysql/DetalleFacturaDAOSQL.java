package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DetalleFacturaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DetalleFacturaDAO;

public class DetalleFacturaDAOSQL implements DetalleFacturaDAO{

	private static final String insert = "INSERT INTO Detalle VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String readAll = "SELECT * FROM Detalle";
	private static final String select = "SELECT * FROM Detalle WHERE Id=?";
	
	@Override
	public boolean insert(DetalleFacturaDTO detalleFactura) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, detalleFactura.getId());
			statement.setInt(2, detalleFactura.getIdProducto());
			statement.setDouble(3, detalleFactura.getCantidad());
			statement.setString(4, detalleFactura.getDescripcion());
			statement.setDouble(5, detalleFactura.getPrecioCosto());
			statement.setDouble(6, detalleFactura.getPrecioVenta());
			statement.setDouble(7, detalleFactura.getMonto());
			statement.setInt(8, detalleFactura.getIdFactura());
			statement.setString(9, detalleFactura.getUnidadMedida());

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
	public List<DetalleFacturaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<DetalleFacturaDTO> detallesFactura = new ArrayList<DetalleFacturaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				detallesFactura.add(getDetalleFacturaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detallesFactura;
	}

	private DetalleFacturaDTO getDetalleFacturaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("Id");
		int idProducto = resultSet.getInt("IdProducto");
		double cantidad = resultSet.getDouble("Cantidad");
		String descripcion = resultSet.getString("Descripcion");
		double precioCosto = resultSet.getDouble("PrecioCosto");
		double precioVenta = resultSet.getDouble("PrecioVenta");
		double monto = resultSet.getDouble("Monto");
		int idFactura = resultSet.getInt("IdFactura");
		String unidadMedida = resultSet.getString("UnidadMedida");
		return new DetalleFacturaDTO(id,idProducto,cantidad,descripcion,precioCosto,precioVenta,monto,idFactura,unidadMedida);
	}
	
	@Override
	public DetalleFacturaDTO selectDetalleFactura(int id) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		DetalleFacturaDTO detalleFactura = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setInt(1,id);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				detalleFactura = getDetalleFacturaDTO(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detalleFactura;
	}

}
