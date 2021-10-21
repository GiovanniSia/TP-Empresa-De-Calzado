package presentacion.controlador.compraVirtual;

import javax.swing.UIManager;

import presentacion.vista.compraVirtual.VentanaVerComprasVirtuales;

public class ControladorVisualizarComprasVirtuales {
	
	VentanaVerComprasVirtuales ventanaPrincipal;
	
	public ControladorVisualizarComprasVirtuales() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		ventanaPrincipal = new VentanaVerComprasVirtuales();
	}
	
	public void inicializar() {
		refrescarTabla();
	}
	
	public void refrescarTabla() {
		vaciarTablaPrincipal();
	}
	
	private void vaciarTablaPrincipal() {
		ventanaPrincipal.getModelDeTablaPrincipal().setRowCount(0);
		ventanaPrincipal.getModelDeTablaPrincipal().setColumnCount(0);
		ventanaPrincipal.getModelDeTablaPrincipal().setColumnIdentifiers(ventanaPrincipal.getNombreColumnas());
		/*
		ventanaPrincipal.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(0).setPreferredWidth(5);
		ventanaPrincipal.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(1).setPreferredWidth(5);
		ventanaPrincipal.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(3).setPreferredWidth(6);
		*/
	}

}
