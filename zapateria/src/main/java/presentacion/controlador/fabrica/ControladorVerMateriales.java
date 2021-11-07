package presentacion.controlador.fabrica;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import dto.SucursalDTO;
import modelo.MaestroProducto;
import modelo.Stock;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.fabrica.VentanaVerMateriales;

public class ControladorVerMateriales {
	
	VentanaVerMateriales ventanaPrincipal;
	
	Stock modeloStock;
	MaestroProducto modeloMaestroProducto;
	
	List<MaestroProductoDTO> ingredientesEnLista;
	
	SucursalDTO fabrica;
	
	public ControladorVerMateriales(SucursalDTO fabrica) {
		modeloMaestroProducto = new MaestroProducto(new DAOSQLFactory());
		modeloStock = new Stock(new DAOSQLFactory());
		ventanaPrincipal = new VentanaVerMateriales();
		this.fabrica = fabrica;
		asignarCodigoAText(ventanaPrincipal.getTextId());
		asignarCodigoAText(ventanaPrincipal.gettextDescrip());
	}
	
	public void inicializar() {
		refrescarTabla();
		ventanaPrincipal.mostrarVentana();
	}
	
	private void asignarCodigoAText(JTextField text) {
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla();
			}
		});
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
		String cantidadDisponible = getCantidadEnStockDeMaterial(mp)+"";
		//"Id", "Descripcion", "Talle", "Unidad medida", "Cantidad Actual"
		Object[] agregar = {idMostrar, descripcionMostrar, talleMostrar, unidadMedida, cantidadDisponible};
		return agregar;
	}

	private List<MaestroProductoDTO> recuperarIngredientes(){
		List<MaestroProductoDTO> todosProductos = this.modeloMaestroProducto.readAll();
		List<MaestroProductoDTO> ret = new ArrayList<MaestroProductoDTO>();
		
		String idText = obtenerTexto(this.ventanaPrincipal.getTextId());
		String descrText = obtenerTexto(this.ventanaPrincipal.gettextDescrip());
		String talle = obtenerTexto(this.ventanaPrincipal.gettextTalle());
		String unidad = obtenerTexto(this.ventanaPrincipal.gettextUnidad());
		obtenerTexto(this.ventanaPrincipal.getTextId());
		for(MaestroProductoDTO mp: todosProductos) {
			if(mp.getTipo().toLowerCase().equals("mp")) {
				boolean deboAgregar = true;
				//deboAgregar= deboAgregar && mp.getDescripcion().toLowerCase().matches(".*"+descrText.toLowerCase()+".*");
				deboAgregar = deboAgregar && matchea(mp.getDescripcion().toLowerCase(), descrText.toLowerCase());
				if(deboAgregar)
					ret.add(mp);
			}
		}
		return ret;
	}
	
	private boolean matchea(String conjunto, String subConjunto) {
		return conjunto.matches(".*"+subConjunto+".*");
	}
	
	private String obtenerTexto(JTextField text) {
		return text.getText();
	}
	
	private void reiniciarTablaIngredientes() {
		ventanaPrincipal.getModelOrdenes().setRowCount(0);
		ventanaPrincipal.getModelOrdenes().setColumnCount(0);
		ventanaPrincipal.getModelOrdenes().setColumnIdentifiers(ventanaPrincipal.getNombreColumnas());
	}
	
	private int getCantidadEnStockDeMaterial(MaestroProductoDTO ingrediente) {
		int cantidadTotalDisponible = 0;
		List<StockDTO> todoElStock = modeloStock.readAll();
		for(StockDTO s: todoElStock) {
			if(s.getIdProducto() == ingrediente.getIdMaestroProducto() && s.getIdSucursal() == this.fabrica.getIdSucursal()) {
				cantidadTotalDisponible = cantidadTotalDisponible + s.getStockDisponible();
			}
		}
		return cantidadTotalDisponible;
	}

	public static void main(String[] args) {
		ControladorVerMateriales conVerMat = new ControladorVerMateriales(new SucursalDTO(1,"","","","","","","",""));
		conVerMat.inicializar();
	}
}
