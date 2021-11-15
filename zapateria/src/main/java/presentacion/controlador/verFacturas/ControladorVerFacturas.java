package presentacion.controlador.verFacturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

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
		inicializarTextFields();
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
		String nroFactura = this.ventanaPrincipal.getTextNroFactura().getText();
		ret = ret && matchea(f.getNroFacturaCompleta(),nroFactura);
		
		String cajero = this.ventanaPrincipal.getTextCajero().getText();
		ret = ret && matchea(f.getNombreCajero(), cajero);
		
		String vendedor = this.ventanaPrincipal.getTextVendedor().getText();
		ret = ret && matchea(f.getNombreVendedor(), vendedor);
		
		String cliente = this.ventanaPrincipal.getTextCliente().getText();
		ret = ret && matchea(f.getNombreCliente(), cliente);
		
		String cuil = this.ventanaPrincipal.getTextCuil().getText();
		ret = ret && matchea(f.getCuilCliente(), cuil);
		return ret;
	}
	
	private boolean matchea(String conjunto, String subConjunto) {
		return conjunto.toLowerCase().matches(".*"+subConjunto.toLowerCase()+".*");
	}
	
	private void inicializarTextFields() {
		asignarRefrescarTablaATextFields(this.ventanaPrincipal.getTextCajero());
		asignarRefrescarTablaATextFields(this.ventanaPrincipal.getTextCliente());
		asignarRefrescarTablaATextFields(this.ventanaPrincipal.getTextCuil());
		asignarRefrescarTablaATextFields(this.ventanaPrincipal.getTextNroFactura());
		asignarRefrescarTablaATextFields(this.ventanaPrincipal.getTextVendedor());
	}
	
	private void asignarRefrescarTablaATextFields(JTextField jtf) {
		jtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla();
			}
		});
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
