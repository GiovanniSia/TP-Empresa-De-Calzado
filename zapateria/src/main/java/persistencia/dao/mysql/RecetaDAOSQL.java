package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.RecetaDAO;

public class RecetaDAOSQL implements RecetaDAO {
	/*
	private static final String readAllReceta = "SELECT * FROM recetas";
	
	public List<RecetaDTO> readAllReceta() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<RecetaDTO> recetas = new ArrayList<RecetaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllReceta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				recetas.add(getRecetaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recetas;
	}
	
	private RecetaDTO getRecetaDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("IdReceta");
		int IdProducto = resultSet.getInt("IdProducto");
		String Descripcion = resultSet.getString("Descripcion");
		return new RecetaDTO(id, IdProducto, Descripcion);
	}
	*/
	private static String insertRecetaNueva = "insert into recetas values(?,?,?);";
	
	public boolean insertReceta(RecetaDTO receta) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insertRecetaNueva);
			statement.setInt(1, receta.getIdReceta());
			statement.setInt(2, receta.getIdProducto());
			statement.setString(3, receta.getDescripcion());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		}catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}
	
	public boolean insertPasosReceta(List<PasoDeRecetaDTO> pasos) {
		boolean ret = true;
		for(PasoDeRecetaDTO p: pasos) {
			ret = ret && insertPasoReceta(p);
		}
		return ret;
	}
	
	private static String insertPasoNuevo = "insert into pasosreceta values(?,?,?,?);";
	private boolean insertPasoReceta(PasoDeRecetaDTO paso) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insertPasoNuevo);
			statement.setInt(1, paso.getIdPasoReceta());
			statement.setInt(2, paso.getIdReceta());
			statement.setInt(3, paso.getNroOrden());
			statement.setInt(4, paso.getIdPaso());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		}catch (SQLException e) 
		{
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
