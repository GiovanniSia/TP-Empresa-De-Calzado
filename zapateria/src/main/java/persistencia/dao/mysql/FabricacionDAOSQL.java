package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.FabricacionesDTO;
import dto.MaestroProductoDTO;
import dto.OrdenFabricaDTO;
import dto.PasoDTO;
import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.FabricacionDAO;
import persistencia.dao.interfaz.OrdenFabricaDAO;

public class FabricacionDAOSQL implements FabricacionDAO{
	
	private static final String estadoActivoDeUnMaterial = "Activo";
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
	
	public boolean isRecetaDisponible(RecetaDTO receta) {	
		//Una receta se encuentra disponible si todos sus materiales estan activos
		boolean ret = true;
		List<PasoDeRecetaDTO> pasosDeLaReceta = this.readAllPasosFromOneReceta(receta.getIdReceta());
		for(PasoDeRecetaDTO pdr: pasosDeLaReceta) {
			MaestroProductoDTO mpAux;
			for(int x = 0; x < pdr.getPasosDTO().getMateriales().size(); x++) {
				mpAux = pdr.getPasosDTO().getMateriales().get(x);
				ret = ret && mpAux.getEstado().equals(estadoActivoDeUnMaterial);
			}
		}
		return ret;
	}
	
	public List<PasoDeRecetaDTO> readAllPasosFromOneReceta(int idReceta) {
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
	
	private PasoDeRecetaDTO getPasoDeRecetaDTO(ResultSet resultSet) throws SQLException {
		int IdPasoReceta = resultSet.getInt("IdPasoReceta");
		int IdReceta = resultSet.getInt("IdReceta");
		int NroOrden = resultSet.getInt("NroOrden");
		int IdPaso = resultSet.getInt("IdPaso");
		
		List<MaestroProductoDTO> materiales = new ArrayList<MaestroProductoDTO>();
		materiales = readAllMaterialesFromOnePaso(IdPasoReceta);
		List<Integer> cantidades = new ArrayList<Integer>();
		if(materiales.size()>0) {
			for(MaestroProductoDTO m : materiales) {
				//cantidades.add(obtenerCantidadMaterial(IdPaso,m.getIdMaestroProducto()));
				cantidades.add(obtenerCantidadMaterial(IdPasoReceta,m.getIdMaestroProducto()));
			}
		}
		String descr = obtenerDescrpcionPaso(IdPaso);
		PasoDeRecetaDTO paso = new PasoDeRecetaDTO(IdPasoReceta,IdReceta,NroOrden,IdPaso,new PasoDTO(IdPaso,descr, materiales, cantidades));
		return paso;
	}
	
	private List<MaestroProductoDTO> readAllMaterialesFromOnePaso(int idPaso) {
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
	
	private MaestroProductoDTO getMaestroProductoDTO(ResultSet resultSet) throws SQLException {
		int idMaestroProducto = resultSet.getInt("mp.IdMaestroProducto");
		String descripcion = resultSet.getString("mp.Descripcion");
		String tipo = resultSet.getString("mp.Tipo");
		String fabricado = resultSet.getString("mp.Fabricado");
		int precioCosto = resultSet.getInt("mp.PrecioCosto");
		int precioMayorista = resultSet.getInt("mp.PrecioMayorista");
		int precioMinorista = resultSet.getInt("mp.PrecioMinorista");
		int puntoRepositorio = resultSet.getInt("mp.PuntoRepositorio");
		int idProveedor = resultSet.getInt("mp.IdProveedor");
		String talle = resultSet.getString("mp.Talle");
		String unidadMedida = resultSet.getString("mp.UnidadMedida");
		String estado = resultSet.getString("mp.Estado");
		int CantidadAReponer = resultSet.getInt("mp.CantidadAReponer");
		int DiasParaReponer = resultSet.getInt("mp.DiasParaReponer");
		return new MaestroProductoDTO(idMaestroProducto, descripcion, tipo,fabricado,precioCosto,precioMayorista,precioMinorista,puntoRepositorio,idProveedor,talle,unidadMedida,estado, CantidadAReponer, DiasParaReponer);
	}
	
	private Integer obtenerCantidadMaterial(int idPaso, int idMaterial){
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
	
	private String obtenerDescrpcionPaso(int idPaso){
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
	private static final String readAllFabricacionesEnMarcha = "SELECT * FROM fabricacionesEnMarcha WHERE (Estado = 'activo' OR Estado = 'completo')";
	
	public List<FabricacionesDTO> readAllFabricacionesEnMarcha(String descrProducto, String idSucursal, String idOrden, String fechaDesde, String Hasta){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<FabricacionesDTO> fabri = new ArrayList<FabricacionesDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			String comandoSql = readAllFabricacionesEnMarcha;
			comandoSql = comandoSql + " AND EXISTS (SELECT * FROM maestroProductos, ordenFabrica as ordenF WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenF.IdOrdenFabrica AND maestroProductos.IdMaestroProducto = ordenF.IdProd AND maestroProductos.Descripcion LIKE '%"+descrProducto+"%')";
			
			if(!idSucursal.equals("")) {
				comandoSql = comandoSql + " AND EXISTS (SELECT * FROM ordenFabrica as ordenfa WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenfa.IdOrdenFabrica AND ordenfa.IdSucursal = "+idSucursal+")";
			}
			if(!idOrden.equals("")) {
				comandoSql = comandoSql + " AND EXISTS (SELECT * FROM ordenFabrica as ordenfab WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenfab.IdOrdenFabrica AND ordenfab.IdOrdenFabrica = "+idOrden+")";
			}
			if(!fechaDesde.equals("")) {
				comandoSql = comandoSql + " AND EXISTS (SELECT * FROM ordenFabrica as ordenfab WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenfab.IdOrdenFabrica AND ordenfab.FechaRequerido >= '"+fechaDesde+"')";
			}
			if(!Hasta.equals("")) {
				comandoSql = comandoSql + " AND EXISTS (SELECT * FROM ordenFabrica as ordenfab WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenfab.IdOrdenFabrica AND ordenfab.FechaRequerido <= '"+Hasta+"')";
			}
			
			statement = conexion.getSQLConexion().prepareStatement(comandoSql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				fabri.add(getFabricacionesEnMarcha(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fabri;
	}
	
	private static final String readAllOrdenesSinTrabajar = "SELECT * FROM ordenFabrica WHERE NOT EXISTS (SELECT * FROM fabricacionesEnMarcha WHERE ordenFabrica.IdOrdenFabrica = fabricacionesEnMarcha.IdOrdenFabrica AND (fabricacionesEnMarcha.Estado = 'completo' OR fabricacionesEnMarcha.Estado = 'entregado' OR fabricacionesEnMarcha.Estado = 'activo' OR fabricacionesEnMarcha.Estado = 'cancelado'))";
	public List<OrdenFabricaDTO> readAllOrdenesSinTrabajar(String descrProducto, String idSucursal, String idOrden, String fechaDesde, String Hasta){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<OrdenFabricaDTO> fabri = new ArrayList<OrdenFabricaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			String comandoSql = readAllOrdenesSinTrabajar;
			comandoSql = comandoSql + " AND EXISTS (SELECT * FROM maestroProductos WHERE maestroProductos.IdMaestroProducto = ordenFabrica.IdProd AND maestroProductos.Descripcion LIKE '%"+descrProducto+"%')";
			if(!idSucursal.equals("")) {
				comandoSql = comandoSql + " AND ordenFabrica.IdSucursal = " + idSucursal+"";
			}
			if(!idOrden.equals("")) {
				comandoSql = comandoSql + " AND ordenFabrica.IdOrdenFabrica = " + idOrden+"";
			}
			if(!fechaDesde.equals("")) {
				comandoSql = comandoSql + " AND ordenFabrica.FechaRequerido >= '"+fechaDesde+"'";
			}
			if(!Hasta.equals("")) {
				comandoSql = comandoSql + " AND ordenFabrica.FechaRequerido <= '"+Hasta+"'";
			}
			statement = conexion.getSQLConexion().prepareStatement(comandoSql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				fabri.add(getOrdenFabricaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fabri;
	}
	
	private OrdenFabricaDTO getOrdenFabricaDTO(ResultSet resultSet) throws SQLException {
		int IdOrdenFabrica = resultSet.getInt("IdOrdenFabrica");
		int IdProd = resultSet.getInt("IdProd");
		String FechaRequerido = resultSet.getString("FechaRequerido");
		int Cantidad = resultSet.getInt("Cantidad");
		String CodigoLote = resultSet.getString("CodigoLote");
		int IdSucursal = resultSet.getInt("IdSucursal");
		return new OrdenFabricaDTO(IdOrdenFabrica,IdProd,FechaRequerido,Cantidad,CodigoLote,IdSucursal);
	}
	
	private FabricacionesDTO getFabricacionesEnMarcha(ResultSet resultSet) throws SQLException {
		int IdFabricacionesEnMarcha = resultSet.getInt("IdFabricacionesEnMarcha");
		int IdOrdenFabrica = resultSet.getInt("IdOrdenFabrica");
		int IdReceta = resultSet.getInt("IdReceta");
		int NroPasoActual = resultSet.getInt("NroPasoActual");
		String Estado = resultSet.getString("Estado");
		
		String fechaC = resultSet.getString("FechaCompletado");
		String aux = fechaC.substring(8, 10);
		int diaC = Integer.parseInt(aux);
		aux = fechaC.substring(5, 7);
		int mesC = Integer.parseInt(aux);
		aux = fechaC.substring(0, 4);
		int anioC = Integer.parseInt(aux);
		FabricacionesDTO ret = new FabricacionesDTO(IdFabricacionesEnMarcha, IdOrdenFabrica, IdReceta, NroPasoActual, Estado);
		ret.setDiaCompletado(diaC);
		ret.setMesCompletado(mesC);
		ret.setAnioCompletado(anioC);
		int DiaDisponible = resultSet.getInt("DiaDisponible");
		ret.setDiaDisponible(DiaDisponible);
		return ret;
	}
	
	private static final String insertFabricacionesEnMarcha = "INSERT INTO fabricacionesEnMarcha(IdOrdenFabrica, IdReceta, NroPasoActual, Estado, FechaCompletado, DiaDisponible) VALUES(?, ?, ?, ?, ?, ?);";
	public boolean insertFabricacionEnMarcha(FabricacionesDTO fabri) {
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
			String diaCompletado = fabri.getAnioCompletado()+"-"+fabri.getMesCompletado()+"-"+fabri.getDiaCompletado();
			statement.setString(5, diaCompletado);//fechaCompletado
			statement.setInt(6, fabri.getDiaDisponible());//dia disponible
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
	
	private static final String actualizarFabricacionEnMarcha = "UPDATE fabricacionesEnMarcha SET NroPasoActual = ?, Estado = ?, FechaCompletado = ?, DiaDisponible = ? WHERE IdFabricacionesEnMarcha = ?;";
	public boolean actualizarFabricacionEnMarcha(FabricacionesDTO fabri) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(actualizarFabricacionEnMarcha);
			statement.setInt(1, fabri.getNroPasoActual());
			statement.setString(2, fabri.getEstado());
			statement.setInt(5, fabri.getIdFabricacionesEnMarcha());
			
			String diaCompletado = fabri.getAnioCompletado()+"-"+fabri.getMesCompletado()+"-"+fabri.getDiaCompletado();
			statement.setString(3, diaCompletado);//fechaCompletado
			statement.setInt(4, fabri.getDiaDisponible());
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
	
	public FabricacionesDTO completarOrden(FabricacionesDTO fabri, int diaLlega) {
		fabri.completarOrden();
		java.util.Date fechaActual = new java.util.Date();
		fechaActual.setDate(fechaActual.getDate());
		int dia = fechaActual.getDate();
		int mes = fechaActual.getMonth() + 1;
		int anio = fechaActual.getYear() + 1900;
		fabri.setAnioCompletado(anio);
		fabri.setDiaCompletado(dia);
		fabri.setMesCompletado(mes);
		fabri.setDiaDisponible(diaLlega);
		actualizarFabricacionEnMarcha(fabri);
		return fabri;
		//dar de alta stock cuando llegue el dia completado + dia disponible
	}
	
	public boolean verificarSiOrdenCompleta(FabricacionesDTO fabri) {
		int idReceta = fabri.getIdReceta();
		int re = readCantPasosReceta(idReceta);
		if(fabri.getNroPasoActual() > re) {
			return true;
		}
		return false;
	}
	
	private static final String readOneReceta = "SELECT COUNT(*) FROM pasosReceta WHERE IdReceta = ?";
	public int readCantPasosReceta(int idReceta) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		int ret = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOneReceta);
			statement.setInt(1, idReceta);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				ret = resultSet.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public void actualizarSiLlegoFechaDeEntrega(FabricacionesDTO f) {
		java.util.Date fechaActual = new java.util.Date();
		fechaActual.setDate(fechaActual.getDate());
		int anio = fechaActual.getYear();
		int mes = fechaActual.getMonth();
		int dia = fechaActual.getDate();
		
		java.util.Date fechaEntrega = new java.util.Date();
		fechaEntrega.setYear(f.getAnioCompletado()-1900);
		fechaEntrega.setMonth(f.getMesCompletado()-1);
		fechaEntrega.setDate(f.getDiaCompletado()+f.getDiaDisponible());
		boolean deboStockear = false;
		if(fechaActual.getYear() >= fechaEntrega.getYear()) {
			deboStockear = true;
		}
		
		if(fechaActual.getMonth() >= fechaEntrega.getMonth()) {
			deboStockear = true;
		}
		
		if(fechaActual.getDate() >= fechaEntrega.getDate() && fechaActual.getMonth() == fechaEntrega.getMonth() 
				&& fechaActual.getYear() == fechaEntrega.getYear()) {
			deboStockear = true;
		}
		
		if(deboStockear) {
			f.entregadaLaOrden();
			actualizarFabricacionEnMarcha(f);
			pasarOrdenAStock(f);
		}
		
	}
	
	private static final String readAllFabricacionesCompletas = "SELECT * FROM fabricacionesEnMarcha WHERE Estado = 'completo'";
	public List<FabricacionesDTO> readAllFabricacionesCompletas(){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<FabricacionesDTO> fabri = new ArrayList<FabricacionesDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllFabricacionesCompletas);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				fabri.add(getFabricacionesEnMarcha(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fabri;
	}
	
	private static final String readOneordenFabricacionAndFabricacionEnMarcha 
	= "SELECT * FROM ordenFabrica WHERE IdOrdenFabrica = ?;";
	public boolean pasarOrdenAStock(FabricacionesDTO fabri) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			ResultSet resultSet; // Guarda el resultado de la query
			statement = conexion.prepareStatement(readOneordenFabricacionAndFabricacionEnMarcha);
			statement.setInt(1, fabri.getIdOrdenFabrica());
			resultSet = statement.executeQuery();
			
			//List<FabricacionesDTO> fabricacionesCompletas = readAllFabricacionesCompletas(); 
			OrdenFabricaDTO a;
			int IdOrdenFabrica;
			int IdProd;
			String FechaRequerido;
			int Cantidad;
			String CodigoLote;
			int IdSucursal;
			if (resultSet.next()) {
				System.out.println("Entro");
				IdOrdenFabrica = resultSet.getInt("IdOrdenFabrica");
				IdProd = resultSet.getInt("IdProd");
				FechaRequerido = resultSet.getString("FechaRequerido");
				Cantidad = resultSet.getInt("Cantidad");
				CodigoLote = resultSet.getString("CodigoLote");
				System.out.println(CodigoLote);
				IdSucursal = resultSet.getInt("IdSucursal");
				a =  new OrdenFabricaDTO(IdOrdenFabrica,IdProd,FechaRequerido,Cantidad,CodigoLote,IdSucursal);
				insertarStock(a);
				
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
	
	private static final String insertStock = "INSERT INTO stock(IdSucursal, IdProducto, CodigoLote, StockDisponible) VALUES(?, ?, ?, ?);";
	private boolean insertarStock(OrdenFabricaDTO fabri) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insertStock);
			statement.setInt(1, fabri.getIdSucursal());
			statement.setInt(2, fabri.getIdProd());
			statement.setString(3, fabri.getCodigoLote());
			statement.setInt(4, fabri.getCantidad());
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
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	
	private static final String actualizarCantidadDeUnStock = "UPDATE stock SET StockDisponible = ? WHERE IdStock = ?;";
	public boolean actuaizarCantidadStockDeUnProductoEnUnaSucursal(int nuevoValor, int idStock) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(actualizarCantidadDeUnStock);
			statement.setInt(1, nuevoValor);
			statement.setInt(2, idStock);
			
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
	
	private static final String readAllFabricacionesCanceladas = "SELECT * FROM fabricacionesEnMarcha WHERE (Estado = 'cancelado')";
	public List<FabricacionesDTO> readAllFabricacionesCanceladas(String descrProducto, String idSucursal, String idOrden, String fechaDesde, String Hasta){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<FabricacionesDTO> fabri = new ArrayList<FabricacionesDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			String comandoSql = readAllFabricacionesCanceladas;
			comandoSql = comandoSql + " AND EXISTS (SELECT * FROM maestroProductos, ordenFabrica as ordenF WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenF.IdOrdenFabrica AND maestroProductos.IdMaestroProducto = ordenF.IdProd AND maestroProductos.Descripcion LIKE '%"+descrProducto+"%')";
			
			if(!idSucursal.equals("")) {
				comandoSql = comandoSql + " AND EXISTS (SELECT * FROM ordenFabrica as ordenfa WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenfa.IdOrdenFabrica AND ordenfa.IdSucursal = "+idSucursal+")";
			}
			if(!idOrden.equals("")) {
				comandoSql = comandoSql + " AND EXISTS (SELECT * FROM ordenFabrica as ordenfab WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenfab.IdOrdenFabrica AND ordenfab.IdOrdenFabrica = "+idOrden+")";
			}
			if(!fechaDesde.equals("")) {
				comandoSql = comandoSql + " AND EXISTS (SELECT * FROM ordenFabrica as ordenfab WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenfab.IdOrdenFabrica AND ordenfab.FechaRequerido >= '"+fechaDesde+"')";
			}
			if(!Hasta.equals("")) {
				comandoSql = comandoSql + " AND EXISTS (SELECT * FROM ordenFabrica as ordenfab WHERE fabricacionesEnMarcha.IdOrdenFabrica = ordenfab.IdOrdenFabrica AND ordenfab.FechaRequerido <= '"+Hasta+"')";
			}
			
			statement = conexion.getSQLConexion().prepareStatement(comandoSql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				fabri.add(getFabricacionesEnMarcha(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fabri;
	}


}
