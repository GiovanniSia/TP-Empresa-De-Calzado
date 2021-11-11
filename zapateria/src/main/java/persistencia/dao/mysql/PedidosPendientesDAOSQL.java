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

	private static final String insert = "INSERT INTO PedidosPendientes VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM PedidosPendientes WHERE Id=?";
	private static final String finalzarPedido = "UPDATE PedidosPendientes SET Estado=?, FechaCompleto=?, HoraCompleto=? WHERE Id=?";
	private static final String readAll = "SELECT * FROM PedidosPendientes";
	
	private static final String updateTotal = "UPDATE PedidosPendientes SET PrecioTotal=? WHERE Id=?";
	
	private static final String cambiarEstado = "UPDATE PedidosPendientes SET Estado=? WHERE Id=?";
	
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
			statement.setDouble(6, pedido.getCantidad());
			statement.setString(7, pedido.getFecha());
			statement.setString(8, pedido.getHora());
			statement.setDouble(9, pedido.getPrecioUnidad());
			statement.setDouble(10, pedido.getPrecioTotal());
			statement.setString(11, pedido.getEstado());
			statement.setInt(12, pedido.getIdSucursal());
			statement.setInt(13, pedido.getIdEmpleado());
			statement.setString(14, pedido.getFechaEnvioMail());
			statement.setString(15, pedido.getHoraEnvioMail());
			statement.setString(16, pedido.getFechaCompleto());
			statement.setString(17, pedido.getHoraCompleto());
			statement.setString(18, pedido.getUnidadMedida());

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
	public boolean cambiarEstado(int id,String estado) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(cambiarEstado);

			statement.setString(1, estado);
			statement.setInt(2, id);


			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}
	
	@Override
	public boolean updateTotal(int idPedido, double nuevoPrecio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(updateTotal);

			statement.setDouble(1, nuevoPrecio);
			statement.setInt(2, idPedido);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}
	
	@Override
	public boolean update(PedidosPendientesDTO nuevopedido, int idPedido) {
		return false;
	}

	@Override
	public boolean finalizarPedido(String nuevoEstado, String fechaCompleto, String HoraCompleto, int idPedido) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(finalzarPedido);

			statement.setString(1, nuevoEstado);
			statement.setString(2, fechaCompleto);
			statement.setString(3, HoraCompleto);
			statement.setInt(4, idPedido);


			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
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
		double cantidad = resultSet.getDouble("Cantidad");
		String fecha = resultSet.getString("Fecha");
		String hora = resultSet.getString("Hora");
		double precioUnidad = resultSet.getDouble("PrecioUnidad");
		double precioTotal = resultSet.getDouble("PrecioTotal");
		String estado = resultSet.getString("Estado");
		int idSucursal = resultSet.getInt("IdSucursal");
		int idEmpleado = resultSet.getInt("IdEmpleado");
		String fechaEnvioMail = resultSet.getString("FechaEnvioMail");
		String horaEnvio = resultSet.getString("HoraEnvioMail");
		String fechaCompleto = resultSet.getString("FechaCompleto");
		String horaCompleto = resultSet.getString("HoraCompleto");
		String unidadMedida = resultSet.getString("UnidadMedida");
		return new PedidosPendientesDTO(id,idProveedor,nombreProveedor,idMaestroProducto,nombreMaestroProducto,cantidad,fecha,hora,precioUnidad,precioTotal,estado,idSucursal,idEmpleado,fechaEnvioMail,horaEnvio,fechaCompleto,horaCompleto,unidadMedida);
	}
	
	
	
//	@SuppressWarnings("null")
	@Override
	public List<PedidosPendientesDTO> getPedidosPendientesFiltrados(String nombreColumna1, String txt1, String nombreColumna2, String txt2, String nombreColumna3,String txt3, String nombreColumna4,String txt4, String nombreColumna5,String txt5, String nombreColumna6,String fechaDesde, String fechaHasta,String nombreColumna7, String horaDesde,String horaHasta){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM PedidosPendientes WHERE ";
		
		if(nombreColumna1!=null ) {
			sel = sel+ nombreColumna1 + " LIKE '%" + txt1 + "%'";
		}
		if(nombreColumna2!=null) {
			sel = sel+" AND " + nombreColumna2 + " LIKE '%" + txt2 + "%'";
		}
		if(nombreColumna3!=null ) {
			sel = sel+" AND " + nombreColumna3 + " LIKE '%" + txt3 + "%'";
		}
		if(nombreColumna4!=null ) {
			sel = sel+ " AND " + nombreColumna4 + " LIKE '%" + txt4 + "%'";
		}
		if(nombreColumna5!=null && !txt5.equals("Sin seleccionar")  ) {
			sel = sel+ " AND " + nombreColumna5 + " LIKE '%" + txt5 + "%'";
		}
		//JDate chooser puede ser nulo
		if(nombreColumna6!=null && (fechaDesde!=null || fechaHasta != null)) {
			if(fechaDesde != null && fechaHasta == null) {
				sel = sel+ " AND " + nombreColumna6 + " >= DATE('" + fechaDesde + "')";
			}
			if(fechaDesde == null && fechaHasta != null) {
				sel = sel+ " AND " + nombreColumna6 + " <= DATE('" + fechaHasta + "')";	
			}
			if(fechaDesde != null && fechaHasta != null) {
				sel = sel + "AND " + nombreColumna6 +" BETWEEN '" + fechaDesde +  "' AND '"  + fechaHasta +"'";
			}
		}
		//spiner de hora/date puede ser nulo
		if(nombreColumna7 != null && (!horaDesde.equals("00:00:00") || !horaHasta.equals("00:00:00"))) {
			if(!horaDesde.equals("00:00:00") && horaHasta.equals("00:00:00")) {
				sel = sel+ " AND " + nombreColumna7 + " >='" + horaDesde + "'";
			}
			if(horaDesde.equals("00:00:00") && !horaHasta.equals("00:00:00")){
				sel = sel+ " AND " + nombreColumna7 + " <= '" + horaHasta + "'";	
			}
			if(!horaDesde.equals("00:00:00") && !horaHasta.equals("00:00:00")) {
				sel = sel + "AND " + nombreColumna7 +" BETWEEN '" + horaDesde +  "' AND '"  + horaHasta +"'";		
			}
		}
		
		ArrayList<PedidosPendientesDTO> pedidos = new ArrayList<PedidosPendientesDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				pedidos.add(getPedidoPendienteDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedidos;
	}	
	
	
}
