package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MaestroProductoDAO;
import dto.MaestroProductoDTO;

public class MaestroProductoDAOSQL implements MaestroProductoDAO {
	private static final String insert = "INSERT INTO maestroProductos VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM maestroProductos WHERE IdMaestroProducto = ?";
	private static final String update = "UPDATE maestroProductos set Descripcion=?, Tipo=?, Fabricado=?, PrecioCosto=?, PrecioMayorista=?, PrecioMinorista=?, PuntoRepositorio=?, IdProveedor=?, Talle=?,UnidadMedida=?,Estado=?,CantidadAReponer=?,DiasParaReponer=? where IdMaestroProducto=?";
	private static final String readall = "SELECT * FROM maestroProductos";

	private static final String select = "SELECT * FROM maestroProductos WHERE IdMaestroProducto = ?"; 
	
	@Override
	public boolean insert(MaestroProductoDTO maestroProductos) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, maestroProductos.getIdMaestroProducto());
			statement.setString(2, maestroProductos.getDescripcion());
			statement.setString(3, maestroProductos.getTipo());
			statement.setString(4, maestroProductos.getFabricado());
			statement.setDouble(5, maestroProductos.getPrecioCosto());
			statement.setDouble(6, maestroProductos.getPrecioMayorista());
			statement.setDouble(7, maestroProductos.getPrecioMinorista());
			statement.setDouble(8, maestroProductos.getPuntoRepositorio());
			statement.setInt(9, maestroProductos.getIdProveedor());
			statement.setString(10, maestroProductos.getTalle());
			statement.setInt(11, maestroProductos.getUnidadMedida());
			statement.setString(12, maestroProductos.getEstado());
			statement.setInt(13, maestroProductos.getCantidadAReponer());
			statement.setInt(14, maestroProductos.getDiasParaReponer());

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
	public boolean delete(MaestroProductoDTO maestroProductos) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, maestroProductos.getIdMaestroProducto());
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
	public boolean update(int id_maestroProductos_a_actualizar, MaestroProductoDTO maestroProductos_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, maestroProductos_nuevo.getDescripcion());
			statement.setString(2, maestroProductos_nuevo.getTipo());
			statement.setString(3, maestroProductos_nuevo.getFabricado());
			statement.setDouble(4, maestroProductos_nuevo.getPrecioCosto());
			statement.setDouble(5, maestroProductos_nuevo.getPrecioMayorista());
			statement.setDouble(6, maestroProductos_nuevo.getPrecioMinorista());
			statement.setDouble(7, maestroProductos_nuevo.getPuntoRepositorio());
			statement.setInt(8, maestroProductos_nuevo.getIdProveedor());
			statement.setString(9, maestroProductos_nuevo.getTalle());
			statement.setInt(10, maestroProductos_nuevo.getUnidadMedida());
			statement.setString(11, maestroProductos_nuevo.getEstado());
			statement.setInt(12, maestroProductos_nuevo.getCantidadAReponer());
			statement.setInt(13, maestroProductos_nuevo.getDiasParaReponer());
			statement.setInt(14, id_maestroProductos_a_actualizar);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}

	public List<MaestroProductoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<MaestroProductoDTO> maestroProducto = new ArrayList<MaestroProductoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				maestroProducto.add(getMaestroProductoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maestroProducto;
	}

	private MaestroProductoDTO getMaestroProductoDTO(ResultSet resultSet) throws SQLException {
		int idMaestroProducto = resultSet.getInt("IdMaestroProducto");
		String descripcion = resultSet.getString("Descripcion");
		String tipo = resultSet.getString("Tipo");
		String fabricado = resultSet.getString("Fabricado");
		double precioCosto = resultSet.getDouble("PrecioCosto");
		double precioMayorista = resultSet.getDouble("PrecioMayorista");
		double precioMinorista = resultSet.getDouble("PrecioMinorista");
		int puntoRepositorio = resultSet.getInt("PuntoRepositorio");
		int idProveedor = resultSet.getInt("IdProveedor");
		String talle = resultSet.getString("Talle");
		int unidadMedida = resultSet.getInt("UnidadMedida");
		String estado = resultSet.getString("Estado");

		int CantidadAReponer = resultSet.getInt("CantidadAReponer");
		int DiasParaReponer = resultSet.getInt("DiasParaReponer");
		return new MaestroProductoDTO(idMaestroProducto, descripcion, tipo, fabricado, precioCosto, precioMayorista,
				precioMinorista, puntoRepositorio, idProveedor, talle, unidadMedida, estado, CantidadAReponer,
				DiasParaReponer);
	}

	@Override
	public List<MaestroProductoDTO> getMaestroProductoAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2,String nombreColumna3,String txtAprox3, String nombreColumna4, String txtAprox4) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM maestroProductos WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'";
		
		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + " AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}
		
//		txtAprox3=desde, txtAprox4=hasta
		
		if(nombreColumna3.equals("PrecioMayorista") || nombreColumna3.equals("PrecioMinorista")){
			System.out.println("la columna fue: "+nombreColumna3);
			int precioDesde = Integer.parseInt(txtAprox3);
			int precioHasta = Integer.parseInt(txtAprox4);
			
			if(precioHasta!=0 && precioDesde<precioHasta) {
				System.out.println("se realiza una busqueda entre "+precioDesde+" hasta "+precioHasta);
				sel = sel + " AND "+nombreColumna3+" BETWEEN "+precioDesde+" AND "+precioHasta;
			}
			if(precioDesde!=0 && precioHasta==0) {
				System.out.println("El precioDesde es !=0 y el precioHasta==0 \nse busca todo lo que sea mayor a "+precioDesde);
				sel = sel + " AND "+nombreColumna3 + " > "+precioDesde;
			}
			if(precioDesde==0 && precioHasta!=0) {
				System.out.println("El precioDesde es ==0 y el precioHasta!=0 \nse busca todo lo que sea menor a "+precioHasta);
				sel = sel +" AND "+nombreColumna3+" < "+precioHasta;
			}
			if(precioDesde==0 && precioHasta==0) {
			//no deberia hacer la busqueda de esto
			}
			
		}else {
			if (nombreColumna3 != null && txtAprox3 != null) {
				sel = sel + " AND " + nombreColumna3 + " LIKE '%" + txtAprox3 + "%'";
			}	
			if (nombreColumna4 != null && txtAprox4 != null) {
				sel = sel + " AND " + nombreColumna4 + " LIKE '%" + txtAprox4 + "%'";
			}
		}

		
		/*
		//chequear que cuando se realiza una busqueda y desde>
		if(nombreColumna3!=null && precioHasta!=0 && precioDesde<precioHasta) {
//			System.out.println("se realiza una busqueda entre "+precioDesde+" hasta "+precioHasta);
			sel = sel + " AND "+nombreColumna3+" BETWEEN "+precioDesde+" AND "+precioHasta;
		}
		if(nombreColumna3!=null && precioDesde!=0 && precioHasta==0) {
//			System.out.println("El precioDesde es !=0 y el precioHasta==0 \nse busca todo lo que sea mayor a "+precioDesde);
			sel = sel + " AND "+nombreColumna3 + " > "+precioDesde;
		}
		if(nombreColumna3!=null && precioDesde==0 && precioHasta!=0) {
//			System.out.println("El precioDesde es ==0 y el precioHasta!=0 \nse busca todo lo que sea menor a "+precioHasta);
			sel = sel +" AND "+nombreColumna3+" < "+precioHasta;
		}
		if(nombreColumna3!=null && precioDesde==0 && precioHasta==0) {
			//no deberia hacer la busqueda de esto
		}
		*/
		
		
		
		
		
		System.out.println(sel);
		ArrayList<MaestroProductoDTO> maestroProducto = new ArrayList<MaestroProductoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				maestroProducto.add(getMaestroProductoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maestroProducto;
	}

	
	@Override
	public MaestroProductoDTO selectMaestroProducto(int idMaestroProducto) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		MaestroProductoDTO producto = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setInt(1,idMaestroProducto);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				producto = getMaestroProductoDTO(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}
}
