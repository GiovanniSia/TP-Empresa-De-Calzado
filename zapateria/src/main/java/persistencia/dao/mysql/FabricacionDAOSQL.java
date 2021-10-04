package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.FabricacionesDTO;
import dto.GeneroDTO;
import dto.MaestroProductoDTO;
import dto.PasoDTO;
import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.OrdenFabricaDAO;

public class FabricacionDAOSQL {
	
	private static final String readAllReceta = "SELECT * FROM recetas";
	
	private static final String readAllPasosFromOneReceta = "SELECT * FROM pasosReceta pr, paso p WHERE pr.IdReceta = ? AND pr.IdPaso = p.IdPaso;";
	private static final String readAllMaterialesFromOnePaso = "SELECT * FROM materialesDePaso mdp, maestroProductos mp WHERE mdp.IdPaso = ? AND mdp.IdMaterial = mp.IdMaestroProducto;";
	private static final String readAllCantidadMaterialesFromOnePaso = "SELECT * FROM materialesDePaso WHERE IdPaso = ? AND IdMaterial = ?;";
	private static final String readOnePaso = "SELECT * FROM paso WHERE IdPaso = ?";
	
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
	
	public static List<PasoDeRecetaDTO> readAllPasosFromOneReceta(int idReceta) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PasoDeRecetaDTO> pasos = new ArrayList<PasoDeRecetaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllPasosFromOneReceta);
			statement.setInt(1,idReceta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				pasos.add(getPasoDeRecetaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pasos;
	}
	
	private static PasoDeRecetaDTO getPasoDeRecetaDTO(ResultSet resultSet) throws SQLException {
		int IdPasoReceta = resultSet.getInt("IdPasoReceta");
		int IdReceta = resultSet.getInt("IdReceta");
		int NroOrden = resultSet.getInt("NroOrden");
		int IdPaso = resultSet.getInt("IdPaso");
		
		List<MaestroProductoDTO> materiales = new ArrayList<MaestroProductoDTO>();
		materiales = readAllMaterialesFromOnePaso(IdPaso);
		List<Integer> cantidades = new ArrayList<Integer>();
		if(materiales.size()>0) {
			for(MaestroProductoDTO m : materiales) {
				cantidades.add(obtenerCantidadMaterial(IdPaso,m.getIdMaestroProducto()));
			}
		}
		String descr = obtenerDescrpcionPaso(IdPaso);
		PasoDeRecetaDTO paso = new PasoDeRecetaDTO(IdPasoReceta,IdReceta,NroOrden,IdPaso,new PasoDTO(IdPaso,descr, materiales, cantidades));
		return paso;
	}
	
	private static List<MaestroProductoDTO> readAllMaterialesFromOnePaso(int idPaso) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<MaestroProductoDTO> material = new ArrayList<MaestroProductoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllMaterialesFromOnePaso);
			statement.setInt(1, idPaso);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				material.add(getMaestroProductoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return material;
	}
	
	private static MaestroProductoDTO getMaestroProductoDTO(ResultSet resultSet) throws SQLException {
		int idMaestroProducto = resultSet.getInt("mp.IdMaestroProducto");
		String descripcion = resultSet.getString("mp.Descripcion");
		String tipo = resultSet.getString("mp.Tipo");
		String fabricado = resultSet.getString("mp.Fabricado");
		int precioCosto = resultSet.getInt("mp.PrecioCosto");
		int precioVenta = resultSet.getInt("mp.PrecioVenta");
		int puntoRepositorio = resultSet.getInt("mp.PuntoRepositorio");
		int idProveedor = resultSet.getInt("mp.IdProveedor");
		String talle = resultSet.getString("mp.Talle");
		int unidadMedida = resultSet.getInt("mp.UnidadMedida");
		String estado = resultSet.getString("mp.Estado");
		
		int CantidadAReponer = resultSet.getInt("mp.CantidadAReponer");
		int DiasParaReponer = resultSet.getInt("mp.DiasParaReponer");
		return new MaestroProductoDTO(idMaestroProducto, descripcion, tipo,fabricado,precioCosto,precioVenta,puntoRepositorio,idProveedor,talle,unidadMedida,estado, CantidadAReponer, DiasParaReponer);
	}
	
	private static Integer obtenerCantidadMaterial(int idPaso, int idMaterial){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Integer lista = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllCantidadMaterialesFromOnePaso);
			statement.setInt(1,idPaso);
			statement.setInt(2,idMaterial);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				lista = resultSet.getInt("CantidadUsada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
		
	}
	
	private static String obtenerDescrpcionPaso(int idPaso){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String ret = "";
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOnePaso);
			statement.setInt(1,idPaso);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				ret = resultSet.getString("Descripcion");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
		
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Puedo leer todas las recetas con sus datos, ahora tengo que leer todos las fabricaciones en marcha
	private static final String readAllFabricacionesEnMarcha = "SELECT * FROM fabricacionesEnMarcha WHERE Estado = 'activo'";
	
	public static List<FabricacionesDTO> readAllFabricacionesEnMarcha(){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<FabricacionesDTO> fabri = new ArrayList<FabricacionesDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllFabricacionesEnMarcha);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				fabri.add(getFabricacionesEnMarcha(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fabri;
	}
	
	private static FabricacionesDTO getFabricacionesEnMarcha(ResultSet resultSet) throws SQLException {
		int IdFabricacionesEnMarcha = resultSet.getInt("IdFabricacionesEnMarcha");
		int IdOrdenFabrica = resultSet.getInt("IdOrdenFabrica");
		int IdReceta = resultSet.getInt("IdReceta");
		int NroPasoActual = resultSet.getInt("NroPasoActual");
		String Estado = resultSet.getString("Estado");
		return new FabricacionesDTO(IdFabricacionesEnMarcha, IdOrdenFabrica, IdReceta, NroPasoActual, Estado);
	}
	
	private static final String insertFabricacionesEnMarcha = "INSERT INTO fabricacionesEnMarcha(IdOrdenFabrica, IdReceta, NroPasoActual, Estado) VALUES(?, ?, ?, ?)";
	public boolean insert(FabricacionesDTO fabri) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insertFabricacionesEnMarcha);
			statement.setInt(1, fabri.getIdOrdenFabrica());
			statement.setInt(2, fabri.getIdReceta());
			statement.setInt(3, fabri.getNroPasoActual());
			statement.setString(4, fabri.getEstado());
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
	
	/*
	public static void main(String[] args) {
		List<PasoDeRecetaDTO> pasos = readAllPasosFromOneReceta(1);
		for(PasoDeRecetaDTO p: pasos) {
			System.out.println();
			System.out.print(p.getIdPaso() + " " + p.getNroOrden() + " " + p.getPasosDTO().getDescripcion());
			
			
			for(int x = 0; x < p.getPasosDTO().getMateriales().size(); x++) {
				System.out.print("[" +p.getPasosDTO().getMateriales().get(x).getIdMaestroProducto() + " "+ p.getPasosDTO().getMateriales().get(x).getDescripcion()+ " CantidadUsada= " + p.getPasosDTO().getCantidadUsada().get(x) +"]");
			}
		}
	}*/

}
