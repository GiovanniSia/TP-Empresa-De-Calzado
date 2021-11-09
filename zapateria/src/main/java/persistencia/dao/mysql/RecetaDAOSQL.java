package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import dto.LocalidadDTO;
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
	private static String insertRecetaNueva = "insert into recetas values(?,?,?,?);";
	
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
			statement.setString(4, receta.getEstado());
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
			int id = readUltimoIdmaterialesdepaso();
			for(int x = 0; x<p.getPasosDTO().getCantidadUsada().size(); x++) {
				p.setIdPasoReceta(id);
				ret = ret && insertIngredientesPaso(p,x);
			}
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
	
	private static String insertIngredientePasoNuevo = "insert into materialesdepaso values(?,?,?,?);";
	private static String insertPasoNuevoIngredientePasoNuevo = "insert into materialesdepaso values(?,?,?,(SELECT MAX(IdPasoReceta) FROM pasosreceta));";
	private boolean insertIngredientesPaso(PasoDeRecetaDTO paso, int indiceMaterial) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			if(paso.getIdPasoReceta() == 0) {
				statement = conexion.prepareStatement(insertPasoNuevoIngredientePasoNuevo);
			}else {
				statement = conexion.prepareStatement(insertIngredientePasoNuevo);
				statement.setInt(4, paso.getIdPasoReceta());
			}
			
			statement.setInt(1, 0);
			statement.setInt(2, paso.getPasosDTO().getMateriales().get(indiceMaterial).getIdMaestroProducto());
			statement.setInt(3, paso.getPasosDTO().getCantidadUsada().get(indiceMaterial));

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
	
	
	private static final String readAllReceta = "SELECT * FROM pasosreceta";
	private Integer readUltimoIdmaterialesdepaso() {
		int ret = 0;
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Integer> recetas = new ArrayList<Integer>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllReceta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				recetas.add(getmaterialesdepaso(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recetas.get(recetas.size()-1);
	}
	
	private int getmaterialesdepaso(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("IdPasoReceta");
		return id;
	}
	
	private static final String update = "UPDATE recetas set Descripcion=?, IdProducto=?, EstadoR=? where IdReceta=?";
	
	@Override
	public boolean updateReceta(RecetaDTO receta, List<PasoDeRecetaDTO> pasos) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, receta.getDescripcion());
			statement.setInt(2, receta.getIdProducto());
			statement.setInt(4, receta.getIdReceta());
			statement.setString(3, receta.getEstado());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(isUpdateExitoso) {
			List<PasoDeRecetaDTO> pasosBorrar = (new FabricacionDAOSQL()).readAllPasosFromOneReceta(receta.getIdReceta());
			for(PasoDeRecetaDTO p: pasosBorrar){
				
				List<Integer> listaId = readAllIdMaterialesOnePaso(p.getIdPasoReceta());
				for(Integer id: listaId) {
					deleteMaterialPaso(id);
				}
				deletePasoReceta(p);
			}
			insertPasosActualizadosReceta(pasos);
		}
		return isUpdateExitoso;
	}
	
	private static final String deletePaso = "DELETE FROM pasosReceta WHERE IdPasoReceta = ?";
	private boolean deletePasoReceta(PasoDeRecetaDTO pasoReceta) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(deletePaso);
			statement.setInt(1, pasoReceta.getIdPasoReceta());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	private static final String deleteMaterial = "DELETE FROM materialesDePaso WHERE IdMaterialDePaso = ?";
	private boolean deleteMaterialPaso(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(deleteMaterial);
			statement.setInt(1, id);
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	private static final String readAllIdMaterialesOnePaso = "SELECT IdMaterialDePaso FROM materialesDePaso WHERE IdPaso=?";
	private List<Integer> readAllIdMaterialesOnePaso(int idPasoReceta) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Integer> recetas = new ArrayList<Integer>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllIdMaterialesOnePaso);
			statement.setInt(1, idPasoReceta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				recetas.add(resultSet.getInt("IdMaterialDePaso"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recetas;
	}
	
	public boolean insertPasosActualizadosReceta(List<PasoDeRecetaDTO> pasos) {
		boolean ret = true;
		for(PasoDeRecetaDTO p: pasos) {
			ret = ret && insertPasoReceta(p);
			for(int x = 0; x<p.getPasosDTO().getCantidadUsada().size(); x++) {
				ret = ret && insertIngredientesPaso(p,x);
			}
		}
		return ret;
	}
}
