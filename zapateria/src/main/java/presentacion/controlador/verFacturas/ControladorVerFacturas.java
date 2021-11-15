package presentacion.controlador.verFacturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dto.FacturaDTO;
import modelo.Factura;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.verFacturas.VentanaVerFabricaciones;

public class ControladorVerFacturas implements ActionListener {
	
	List<FacturaDTO> facturasEnLista;
	
	Factura modeloFactura;
	
	VentanaVerFabricaciones ventanaPrincipal;

	public ControladorVerFacturas() {
		modeloFactura = new Factura(new DAOSQLFactory());
		
		ventanaPrincipal = new VentanaVerFabricaciones();
	}
	
	public void inicializar() {
		refrescarTabla();
		ventanaPrincipal.mostrarVentana();
	}
	
	private void refrescarTabla() {
		reiniciarTablaTrabajos();
		facturasEnLista = recuperarListaFactura();
		llenarTablaConFacturas(facturasEnLista);
	}
	
	private void reiniciarTablaTrabajos() {
		ventanaPrincipal.getModelFactura().setRowCount(0);
		ventanaPrincipal.getModelFactura().setColumnCount(0);
		ventanaPrincipal.getModelFactura().setColumnIdentifiers(ventanaPrincipal.getNombreColumnas());
	}
	
	private void llenarTablaConFacturas(List<FacturaDTO> facturas) {
		for(FacturaDTO f: facturas ) {
			escribirEnTabla(f);
		}
	}
	
	private void escribirEnTabla(FacturaDTO f) {
		//"Nro factura","Vendedor", "Cajero", "Cliente", "CUIL", "IVA", "Total"
		String nroFactura = f.getNroFacturaCompleta();
		String vendedor = f.getNombreVendedor();
		String cajero = f.getNombreCajero();
		String cliente = f.getNombreCliente();
		String cuil = f.getCuilCliente();
		BigDecimal iva = new BigDecimal(f.getIVA());
		BigDecimal total = new BigDecimal(f.getTotalFactura());
		Object[] agregar = {nroFactura, vendedor, cajero, cliente, cuil, iva, total};
		ventanaPrincipal.getModelFactura().addRow(agregar);
		
	}

	private List<FacturaDTO> recuperarListaFactura(){
		ArrayList<FacturaDTO> ret = new ArrayList<FacturaDTO>();
		List<FacturaDTO> todasLasFacturas = this.modeloFactura.readAll();
		for(FacturaDTO f: todasLasFacturas) {
			if(cumpleConLosParametros(f)) {
				ret.add(f);
			}
		}
		return ret;
	}
	
	private boolean cumpleConLosParametros(FacturaDTO f) {
		boolean ret = true;
		
		return ret;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControladorVerFacturas controlFactura = new ControladorVerFacturas();
		controlFactura.inicializar();

	}

}
