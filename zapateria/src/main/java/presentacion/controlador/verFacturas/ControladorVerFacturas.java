package presentacion.controlador.verFacturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import dto.EgresosDTO;
import dto.FacturaDTO;
import dto.SucursalDTO;
import modelo.Egresos;
import modelo.Factura;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.reportes.ReporteFactura;
import presentacion.reportes.ReporteNotaCredito;
import presentacion.vista.verFacturas.VentanaVerFabricaciones;

public class ControladorVerFacturas implements ActionListener {
	
	List<FacturaDTO> facturasEnLista;
	
	Factura modeloFactura;
	private Egresos egresos;
	
	VentanaVerFabricaciones ventanaPrincipal;
	
	SucursalDTO sucursal;
	Controlador controlador;

	public ControladorVerFacturas(Controlador controlador, SucursalDTO sucursal) {
		this.sucursal = sucursal;
		modeloFactura = new Factura(new DAOSQLFactory());
		this.egresos = new Egresos(new DAOSQLFactory());
		ventanaPrincipal = new VentanaVerFabricaciones();
		inicializarTextFields();
		inicializarCheckBox();
		
		this.ventanaPrincipal.getBtnVerFactura().addActionListener(r->mostrarFactura(r));
		this.ventanaPrincipal.getBtnSalir().addActionListener(r->salir(r));
	}
	
	private void salir(ActionEvent r) {
		this.ventanaPrincipal.cerrar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	private void mostrarFactura(ActionEvent r) {
		if(this.ventanaPrincipal.getTablaFactura().getSelectedRow() < 0) {
			return;
		}
		String nroFactura = this.facturasEnLista.get(this.ventanaPrincipal.getTablaFactura().getSelectedRow()).getNroFacturaCompleta();
		ReporteFactura factura = new ReporteFactura(nroFactura);
		factura.mostrar();
		
		if(hayNotaCredito(nroFactura)) {
			ReporteNotaCredito notaCredito = new ReporteNotaCredito(nroFactura);
			notaCredito.mostrar();
		}
	}

	private boolean hayNotaCredito(String nroFactura) {
		boolean ret = false;
		for(EgresosDTO e: this.egresos.readAll()) {
			ret = ret || e.getDetalle().toLowerCase().equals(nroFactura.toLowerCase());
		}
		return ret;
		
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
			if(cumpleConLosParametros(f) && !esCompraVirtual(f) && perteneceAMISucursal(f)) {
				ret.add(f);
			}
		}
		return ret;
	}
	
	private boolean perteneceAMISucursal(FacturaDTO f) {
		return f.getIdSucursal() == this.sucursal.getIdSucursal();
	}

	private boolean esCompraVirtual(FacturaDTO f) {
		return f.getIdVendedor() == 0;
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
		if(this.ventanaPrincipal.getChckbxIVA().isSelected()) {
			ret = ret && f.getIVA()!=0.0;
		}
		
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
	
	private void inicializarCheckBox() {
		asignarRefrescarTablaACheckBox(this.ventanaPrincipal.getChckbxIVA());
	}
	
	private void asignarRefrescarTablaATextFields(JTextField jtf) {
		jtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla();
			}
		});
	}
	
	private void asignarRefrescarTablaACheckBox(JCheckBox cb) {
		cb.addMouseListener((MouseListener) new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				refrescarTabla();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ControladorVerFacturas controlFactura = new ControladorVerFacturas(new SucursalDTO(2,"","","","","","","","",""));
		//controlFactura.inicializar();

	}

}
