package persistencia.dao.interfaz;

import java.util.List;

import dto.ClienteDTO;
import dto.FabricacionesDTO;
import dto.OrdenFabricaDTO;
import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;

public interface FabricacionDAO {

	public List<RecetaDTO> readAllReceta();
	
	public boolean isRecetaDisponible(RecetaDTO receta);
	
	public List<PasoDeRecetaDTO> readAllPasosFromOneReceta(int idReceta);
	
	public List<FabricacionesDTO> readAllFabricacionesEnMarcha(String descrProducto, String idSucursal, String idOrden);
	
	public List<OrdenFabricaDTO> readAllOrdenesSinTrabajar(String descrProducto, String idSucursal, String idOrden);
	
	public boolean insertFabricacionEnMarcha(FabricacionesDTO fabri);
	
	public boolean actualizarFabricacionEnMarcha(FabricacionesDTO fabri);
	
	public FabricacionesDTO completarOrden(FabricacionesDTO fabri, int diaLlega);
	
	public boolean verificarSiOrdenCompleta(FabricacionesDTO fabri);
	
	public int readCantPasosReceta(int idReceta);
	
	public void actualizarSiLlegoFechaDeEntrega(FabricacionesDTO f);
	
	public List<FabricacionesDTO> readAllFabricacionesCompletas();
	
	public boolean pasarOrdenAStock(FabricacionesDTO fabri);
	
	public boolean actuaizarCantidadStockDeUnProductoEnUnaSucursal(int nuevoValor, int idStock);
	
	

}
