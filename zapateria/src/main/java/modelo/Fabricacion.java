package modelo;

import java.util.List;

import dto.FabricacionesDTO;
import dto.OrdenFabricaDTO;
import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.FabricacionDAO;

public class Fabricacion {
	
	private FabricacionDAO fab;
	
	public Fabricacion(DAOAbstractFactory metodo_persistencia) {
		this.fab = metodo_persistencia.createFabricacionDAO();
	}
	
	public List<RecetaDTO> readAllReceta(){
		return fab.readAllReceta();
	}
	
	public boolean isRecetaDisponible(RecetaDTO receta) {
		return fab.isRecetaDisponible(receta);
	}
	
	public List<PasoDeRecetaDTO> readAllPasosFromOneReceta(int idReceta){
		return fab.readAllPasosFromOneReceta(idReceta);
	}
	
	public List<FabricacionesDTO> readAllFabricacionesEnMarcha(String descrProducto, String idSucursal, String idOrden, String fechaDesde, String Hasta){
		return fab.readAllFabricacionesEnMarcha(descrProducto,idSucursal,idOrden, fechaDesde, Hasta);
	}
	
	public List<OrdenFabricaDTO> readAllOrdenesSinTrabajar(String descrProducto, String idSucursal, String idOrden, String fechaDesde, String Hasta){
		return fab.readAllOrdenesSinTrabajar(descrProducto, idSucursal, idOrden, fechaDesde, Hasta);
	}
	
	public boolean insertFabricacionEnMarcha(FabricacionesDTO fabri) {
		return fab.insertFabricacionEnMarcha(fabri);
	}
	
	public boolean actualizarFabricacionEnMarcha(FabricacionesDTO fabri) {
		return fab.actualizarFabricacionEnMarcha(fabri);
	}
	
	public FabricacionesDTO completarOrden(FabricacionesDTO fabri, int diaLlega) {
		return fab.completarOrden(fabri, diaLlega);
	}
	
	public boolean verificarSiOrdenCompleta(FabricacionesDTO fabri) {
		return fab.verificarSiOrdenCompleta(fabri);
	}
	
	public int readCantPasosReceta(int idReceta) {
		return fab.readCantPasosReceta(idReceta);
	}
	
	public void actualizarSiLlegoFechaDeEntrega(FabricacionesDTO f) {
		fab.actualizarSiLlegoFechaDeEntrega(f);
	}
	
	public List<FabricacionesDTO> readAllFabricacionesCompletas(){
		return fab.readAllFabricacionesCompletas();
	}
	
	public boolean pasarOrdenAStock(FabricacionesDTO fabri) {
		return fab.pasarOrdenAStock(fabri);
	}
	
	public boolean actuaizarCantidadStockDeUnProductoEnUnaSucursal(int nuevoValor, int idStock) {
		return fab.actuaizarCantidadStockDeUnProductoEnUnaSucursal(nuevoValor, idStock);
	}

}
