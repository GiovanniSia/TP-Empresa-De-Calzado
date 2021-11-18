package presentacion.controlador.verFacturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
		this.controlador = controlador;
		modeloFactura = new Factura(new DAOSQLFactory());
		this.egresos = new Egresos(new DAOSQLFactory());
		ventanaPrincipal = new VentanaVerFabricaciones();
		inicializarTextFields();
		inicializarCheckBox();
		inicializarDateChooser();
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
		ret = ret && cumpleConLasFechas(f);
		return ret;
	}
	
	private boolean cumpleConLasFechas(FacturaDTO f) {
		Date fechaPaso = new Date();
		String[] fechaPasoString = f.getFecha().split("-");
		fechaPaso.setYear(Integer.valueOf(fechaPasoString[0])-1900);
		fechaPaso.setMonth(Integer.valueOf(fechaPasoString[1])-1);
		fechaPaso.setDate(Integer.valueOf(fechaPasoString[2]));
		
		boolean cumpleDesde = false;
		if(getFechaDesdeDate() != null) {
			if(getFechaDesdeDate().compareTo(fechaPaso)<=0) {
				cumpleDesde = true;
			}
		}else {
			cumpleDesde = true;
		}
		boolean cumpleHasta = false;
		if(getFechaDesdeHasta() != null) {
			if(getFechaDesdeHasta().compareTo(fechaPaso)>=0) {
				cumpleHasta = true;
			}
		}else {
			cumpleHasta = true;
		}
		return (cumpleDesde && cumpleHasta);
	}
	
	private boolean matchea(String conjunto, String subConjunto) {
		return conjunto.toLowerCase().matches(".*"+subConjunto.toLowerCase()+".*");
	}
	
	@SuppressWarnings("deprecation")
	private Date getFechaDesdeDate() {
		Date ret = this.ventanaPrincipal.getFechaDesde().getDate();
		if(ret != null) {
			ret.setHours(0);
			ret.setMinutes(0);
			ret.setSeconds(0);
		}
		return ret;
	}
	
	@SuppressWarnings("deprecation")
	private Date getFechaDesdeHasta() {
		Date ret = this.ventanaPrincipal.getFechaHasta().getDate();
		if(ret != null) {
			ret.setHours(23);
			ret.setMinutes(59);
			ret.setSeconds(59);
		}
		return ret;
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
	
	private void inicializarDateChooser() {
		ventanaPrincipal.getFechaDesde().addPropertyChangeListener(
				new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			                System.out.println(e.getPropertyName()
			                    + ": " + (Date) e.getNewValue());
			            }
			            refrescarTabla();
			        }
			    });
		
		ventanaPrincipal.getFechaHasta().addPropertyChangeListener(
				new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			                System.out.println(e.getPropertyName()
			                    + ": " + (Date) e.getNewValue());
			            }
			            refrescarTabla();
			        }
			    });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControladorVerFacturas controlFactura = new ControladorVerFacturas(null, new SucursalDTO(2,"","","","","","","","",""));
		controlFactura.inicializar();

	}

}
