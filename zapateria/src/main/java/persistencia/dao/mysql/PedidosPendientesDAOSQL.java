package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.PedidosPendientesDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PedidosPendientesDAO;

public class PedidosPendientesDAOSQL implements PedidosPendientesDAO{

	private static final String insert = "INSERT INTO PedidosPendientes VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM PedidosPendientes WHERE Id=?";
	private static final String update = "UPDATE PedidosPendientes SET FechaCompleto=?";
	private static final String readAll = "SELECT * FROM PedidosPendientes";
	
	@Override
	public boolean insert(PedidosPendientesDTO pedido) {
 		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, pedido.getId());
			statement.setInt(2,pedido.getIdProveedor());
			statement.setString(3, pedido.getNombreProveedor());
			statement.setInt(4, pedido.getIdMaestroProducto());
			statement.setString(5,pedido.getNombreMaestroProducto());
			statement.setInt(6, pedido.getCantidad());
			statement.setString(7, pedido.getFecha());
			statement.setString(8, pedido.getHora());
			statement.setDouble(9, pedido.getPrecioUnidad());
			statement.setDouble(10, pedido.getPrecioTotal());
			statement.setString(11, pedido.getEstado());
			statement.setInt(12, pedido.getIdSucursal());
			statement.setString(13, pedido.getFechaEnvioMail());
			statement.setString(14, pedido.getFechaCompleto());

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
	public boolean delete(PedidosPendientesDTO pedido) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, pedido.getId());
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
	public boolean update(PedidosPendientesDTO nuevopedido, int idPedido) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PedidosPendientesDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PedidosPendientesDTO> pedido = new ArrayList<PedidosPendientesDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				pedido.add(getPedidoPendienteDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedido;
	}

	private PedidosPendientesDTO getPedidoPendienteDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("Id");
		int idProveedor = resultSet.getInt("IdProveedor");
		String nombreProveedor = resultSet.getString("NombreProveedor");
		int idMaestroProducto = resultSet.getInt("IdMaestroProducto");
		String nombreMaestroProducto = resultSet.getString("NombreMaestroProducto");
		int cantidad = resultSet.getInt("Cantidad");
		String fecha = resultSet.getString("Fecha");
		String hora = resultSet.getString("Hora");
		double precioUnidad = resultSet.getDouble("PrecioUnidad");
		double precioTotal = resultSet.getDouble("PrecioTotal");
		String estado = resultSet.getString("Estado");
		int idSucursal = resultSet.getInt("IdSucursal");
		String fechaEnvioMail = resultSet.getString("FechaEnvioMail");
		String fechaCompleto = resultSet.getString("FechaCompleto");
		
		return new PedidosPendientesDTO(id,idProveedor,nombreProveedor,idMaestroProducto,nombreMaestroProducto,cantidad,fecha,hora,precioUnidad,precioTotal,estado,idSucursal,fechaEnvioMail,fechaCompleto);
	}
}
