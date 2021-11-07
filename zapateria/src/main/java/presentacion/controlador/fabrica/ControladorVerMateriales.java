package presentacion.controlador.fabrica;

import java.util.ArrayList;
import java.util.List;

import dto.MaestroProductoDTO;
import modelo.MaestroProducto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.fabrica.VentanaVerMateriales;

public class ControladorVerMateriales {
	
	VentanaVerMateriales ventanaPrincipal;
	
	MaestroProducto modeloMaestroProducto;
	
	List<MaestroProductoDTO> ingredientesEnLista;
	
	public ControladorVerMateriales() {
		modeloMaestroProducto = new MaestroProducto(new DAOSQLFactory());
		
		ventanaPrincipal = new VentanaVerMateriales();
	}
	
	public void inicializar() {
		refrescarTabla();
		ventanaPrincipal.mostrarVentana();
	}
	
	private void refrescarTabla() {
		reiniciarTablaIngredientes();
		ingredientesEnLista = new ArrayList<MaestroProductoDTO>();
		ingredientesEnLista = recuperarIngredientes();
		llenarTablaConIngredientes(ingredientesEnLista);
		
	}
	
	private void llenarTablaConIngredientes(List<MaestroProductoDTO> ingredientes) {
		for(MaestroProductoDTO mp: ingredientes) {
			ventanaPrincipal.getModelOrdenes().addRow(filaParaTabla(mp));
		}
	}

	private Object[] filaParaTabla(MaestroProductoDTO mp) {
		String idMostrar = mp.getIdMaestroProducto()+"";
		String descripcionMostrar = mp.getDescripcion();
		String talleMostrar = mp.getTalle();
		String unidadMedida = mp.getUnidadMedida();
		//"Id", "Descripcion", "Talle", "Unidad medida", "Cantidad Actual"
		Object[] agregar = {idMostrar, descripcionMostrar, talleMostrar, unidadMedida};
		return agregar;
	}

	private List<MaestroProductoDTO> recuperarIngredientes(){
		List<MaestroProductoDTO> todosProductos = this.modeloMaestroProducto.readAll();
		List<MaestroProductoDTO> ret = new ArrayList<MaestroProductoDTO>();
		for(MaestroProductoDTO mp: todosProductos) {
			if(mp.getTipo().toLowerCase().equals("mp")) {
				ret.add(mp);
			}
		}
		return ret;
	}
	
	private void reiniciarTablaIngredientes() {
		ventanaPrincipal.getModelOrdenes().setRowCount(0);
		ventanaPrincipal.getModelOrdenes().setColumnCount(0);
		ventanaPrincipal.getModelOrdenes().setColumnIdentifiers(ventanaPrincipal.getNombreColumnas());
	}

	public static void main(String[] args) {
		ControladorVerMateriales conVerMat = new ControladorVerMateriales();
		conVerMat.inicializar();
	}
}
