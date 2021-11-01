package presentacion.controlador.compraVirtual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.UIManager;

import dto.ClienteDTO;
import dto.EgresosDTO;
import dto.IngresosDTO;
import dto.MotivoEgresoDTO;
import dto.RechazoCompraVirtualDTO;
import dto.RechazoCompraVirtualDetalleDTO;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Egresos;
import modelo.Factura;
import modelo.Ingresos;
import modelo.compraVirtual.CodigoErrorComprasVirtuales;
import modelo.compraVirtual.MotivoEgreso;
import modelo.compraVirtual.RechazoCompraVirtual;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.reportes.ReporteFactura;
import presentacion.reportes.ReporteNotaCredito;
import presentacion.vista.compraVirtual.VentanaVerComprasVirtuales;
import presentacion.vista.compraVirtual.VentanaVerDetalleRechazo;

public class ControladorVisualizarComprasVirtuales implements ActionListener  {
	
	private final static String  stringQueIndicaQueUnDatoEstaVacio = "[dato inexistente]"; 
	
	VentanaVerComprasVirtuales ventanaPrincipal;
	
	VentanaVerDetalleRechazo ventanaRechazo;
	
	Factura modeloFactura;
	DetalleFactura modeloDetalleFactura;
	Ingresos modeloIngresos;
	Cliente modeloCliente;
	Egresos modeloEgresos;
	
	RechazoCompraVirtual modeloRechazoVirtual;
	List<IngresosDTO> ingresosEnLista;
	List<RechazoCompraVirtualDTO> rechazosEnLista;
	Controlador controlador;
	public ControladorVisualizarComprasVirtuales(Controlador controlador) {
		this.controlador = controlador;
		modeloRechazoVirtual = new RechazoCompraVirtual(new DAOSQLFactory());
		modeloFactura = new Factura(new DAOSQLFactory());
		modeloDetalleFactura = new DetalleFactura(new DAOSQLFactory());
		modeloIngresos = new Ingresos(new DAOSQLFactory());
		modeloCliente = new Cliente(new DAOSQLFactory());
		modeloEgresos = new Egresos(new DAOSQLFactory());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		ventanaPrincipal = new VentanaVerComprasVirtuales();
		ventanaPrincipal.getBtnVerDescripcion().addActionListener(r->botonVerDescripcion(r));
		ventanaPrincipal.getTextCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla();
			}
		});
		ventanaPrincipal.getTextCUIL().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla();
			}
		});
		ventanaPrincipal.getTextId().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla();
			}
		});
		ventanaPrincipal.getTextSucursal().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla();
			}
		});
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
		agregarRefrescarTablaACheckBox(ventanaPrincipal.getChckbxCancelados());
		agregarRefrescarTablaACheckBox(ventanaPrincipal.getChckbxErrorSucursal());
		agregarRefrescarTablaACheckBox(ventanaPrincipal.getChckbxErrorCorreo());
		agregarRefrescarTablaACheckBox(ventanaPrincipal.getChckbxErrorPais());
		agregarRefrescarTablaACheckBox(ventanaPrincipal.getChckbxErrorProvincia());
		ventanaRechazo = new VentanaVerDetalleRechazo();
		ventanaRechazo.getBtnSalir().addActionListener(r->cerrarVentanaDetalle(r));
		
		ventanaPrincipal.getBtnSalir().addActionListener(r->cerrarTodoElControlador(r));
	}
	
	private void agregarRefrescarTablaACheckBox(JCheckBox checkBox) {
		checkBox.addMouseListener((MouseListener) new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				refrescarTabla();
			}
		});
	}
	
	private void cerrarTodoElControlador(ActionEvent r) {
		this.ventanaPrincipal.cerrar();
		this.ventanaRechazo.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	private void cerrarVentanaDetalle(ActionEvent r) {
		ventanaRechazo.cerrar();
	}

	public void inicializar() {
		refrescarTabla();
		ventanaPrincipal.mostrarVentana();
	}
	
	public void refrescarTabla() {
		vaciarTablaPrincipal();
		ingresosEnLista = recuperarComprasVirtuales();
		llenarTablaConIngresos(ingresosEnLista);
		
		if(ventanaPrincipal.getChckbxCancelados().isSelected()) {
			rechazosEnLista = recuperarRechazos();
			llenarTablaConRechazos(rechazosEnLista);
		}else {
			rechazosEnLista = new ArrayList<RechazoCompraVirtualDTO>();
		}
		
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
	
	@SuppressWarnings("deprecation")
	private List<IngresosDTO> recuperarComprasVirtuales(){
		boolean errorSucursalSelected = this.ventanaPrincipal.getChckbxErrorSucursal().isSelected();
		boolean errorCorreoSelected = this.ventanaPrincipal.getChckbxErrorCorreo().isSelected();
		boolean errorPaisSelected = this.ventanaPrincipal.getChckbxErrorPais().isSelected();
		boolean errorProvinciaSelected = this.ventanaPrincipal.getChckbxErrorProvincia().isSelected();
		List<IngresosDTO> ret  = new ArrayList<IngresosDTO>();
		if(!errorSucursalSelected && !errorCorreoSelected && !errorPaisSelected && !errorProvinciaSelected) {
			String nroOrden = this.ventanaPrincipal.getTextId().getText();
			String sucursal = this.ventanaPrincipal.getTextSucursal().getText();
			String cuil = this.ventanaPrincipal.getTextCUIL().getText();
			String clienteString = this.ventanaPrincipal.getTextCliente().getText();
			
			
			List<IngresosDTO> todasLosIngresos = this.modeloIngresos.readAll();
			for(IngresosDTO ingreso: todasLosIngresos) {
				if(ingreso.getMedioPago().equals("PV")) {
					ClienteDTO cliente = getCliente(ingreso.getIdCliente());
					
					boolean deboAgregar = true;
					deboAgregar = deboAgregar && cliente.getCUIL().toLowerCase().matches(".*"+ cuil +".*");
					String nombreCliente = cliente.getApellido()+", "+cliente.getNombre();
					deboAgregar = deboAgregar && nombreCliente.toLowerCase().matches(".*"+ clienteString.toLowerCase() +".*");
					deboAgregar = deboAgregar && (ingreso.getIdSucursal()+"").toLowerCase().matches(".*"+ sucursal +".*");
					deboAgregar = deboAgregar && (ingreso.getId()+"").toLowerCase().matches(".*"+ nroOrden +".*");
					
					Date fechaPaso = new Date();
					String[] fechaPasoString = palabrasPorBarra(ingreso.getFecha());
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
					deboAgregar = deboAgregar && (cumpleDesde && cumpleHasta);
					if(deboAgregar) {
						ret.add(ingreso);
					}
				}
			}
		}
		return ret;
	}
	
	private void llenarTablaConIngresos(List<IngresosDTO> ingresos) {
		for(IngresosDTO i: ingresos) {
			String idIngreso = i.getId()+"";
			String idSucursal = i.getIdSucursal()+"";
			
			ClienteDTO cliente = getCliente(i.getIdCliente());
			
			String cuil = cliente.getCUIL();
			String nombre = cliente.getApellido()+", "+cliente.getNombre();
			
			String fecha = i.getFecha();
			String hora = i.getHora();
			
			String notaCredit = "0";
			EgresosDTO notaCredito = getNotaCredito(i.getNroFactura(), i);
			if(notaCredito != null) {
				notaCredit = notaCredito.getTotal()+"";
			}
			
			Object[] agregar = {idIngreso, idSucursal, cuil, nombre, fecha, hora, "Aceptada",notaCredit};
			ventanaPrincipal.getModelDeTablaPrincipal().addRow(agregar);
		}
	}

	private EgresosDTO getNotaCredito(String nroFactura, IngresosDTO i) {
		List<EgresosDTO> todosLosEgresos = modeloEgresos.readAll();
		for(EgresosDTO e: todosLosEgresos) {
			if(i.getNroFactura().equals(e.getDetalle())) {
				return e;
			}
		}
		return null;
	}

	private ClienteDTO getCliente(int idCliente) {
		for(ClienteDTO c: modeloCliente.readAll()) {
			if(c.getIdCliente() == idCliente) {
				return c;
			}
		}
		return null;
	}
	
	private void llenarTablaConRechazos(List<RechazoCompraVirtualDTO> rechazos) {
		for(RechazoCompraVirtualDTO re: rechazos) {
			String idRechazo = re.getId()+"(RE)";
			String idSucursal = re.getIdSucursal()+"";
			
			String cuil = "";
			if(re.getCUIL().equals("")) {
				cuil = stringQueIndicaQueUnDatoEstaVacio;
			}else {
				cuil = re.getIdSucursal()+"";
			}
			
			String nombre = "";
			if(re.getNombre().equals("")) {
				nombre = stringQueIndicaQueUnDatoEstaVacio;
			}else {
				nombre = re.getApellido()+", "+re.getNombre();
			}
			String fecha = re.getFecha();
			String hora = re.getHora();
			Object[] agregar = {idRechazo, idSucursal, cuil, nombre, fecha, hora, "Rechazada"};
			ventanaPrincipal.getModelDeTablaPrincipal().addRow(agregar);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private List<RechazoCompraVirtualDTO> recuperarRechazos(){
		List<RechazoCompraVirtualDTO> todosRechazos = modeloRechazoVirtual.readAllRechazosComprasVirtuales();
		List<RechazoCompraVirtualDTO> ret = new ArrayList<RechazoCompraVirtualDTO>();
		String nroOrden = this.ventanaPrincipal.getTextId().getText();
		String sucursal = this.ventanaPrincipal.getTextSucursal().getText();
		String cuil = this.ventanaPrincipal.getTextCUIL().getText();
		String clienteString = this.ventanaPrincipal.getTextCliente().getText();
		
		boolean errorSucursalSelected = this.ventanaPrincipal.getChckbxErrorSucursal().isSelected();
		boolean errorCorreoSelected = this.ventanaPrincipal.getChckbxErrorCorreo().isSelected();
		boolean errorPaisSelected = this.ventanaPrincipal.getChckbxErrorPais().isSelected();
		boolean errorProvinciaSelected = this.ventanaPrincipal.getChckbxErrorProvincia().isSelected();
		for(RechazoCompraVirtualDTO r: todosRechazos) {
			boolean deboAgregar = true;
			deboAgregar = deboAgregar && r.getCUIL().toLowerCase().matches(".*"+ cuil +".*");
			String nombreCliente = r.getApellido()+", "+r.getNombre();
			deboAgregar = deboAgregar && nombreCliente.toLowerCase().matches(".*"+ clienteString.toLowerCase() +".*");
			deboAgregar = deboAgregar && (r.getIdSucursal()+"").toLowerCase().matches(".*"+ sucursal +".*");
			deboAgregar = deboAgregar && (r.getId()+"").toLowerCase().matches(".*"+ nroOrden +".*");
			
			Date fechaPaso = new Date();
			String[] fechaPasoString = palabrasPorBarra(r.getFecha());
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
			deboAgregar = deboAgregar && (cumpleDesde && cumpleHasta);
			
			if(errorSucursalSelected) {
				deboAgregar = deboAgregar && tieneElTexto(r.getMotivo().toLowerCase(), CodigoErrorComprasVirtuales.getCodigoErrorSucursalNoValida().toLowerCase());
			}
			
			if(errorCorreoSelected) {
				deboAgregar = deboAgregar && tieneElTexto(r.getMotivo().toLowerCase(), CodigoErrorComprasVirtuales.getCodigoErrorCorreo().toLowerCase());
			}
			
			if(errorPaisSelected) {
				deboAgregar = deboAgregar && tieneElTexto(r.getMotivo().toLowerCase(), CodigoErrorComprasVirtuales.getCodigoErrorPais().toLowerCase());
			}
			
			if(errorProvinciaSelected) {
				deboAgregar = deboAgregar && tieneElTexto(r.getMotivo().toLowerCase(), CodigoErrorComprasVirtuales.getCodigoErrorProvincia().toLowerCase());
			}
			
			if(deboAgregar) {
				ret.add(r);
			}
		}
		return ret;
	}
	
	private boolean tieneElTexto(String conjunto, String subConjunto) {
		/*	//Esto funciona
		boolean ret = false;
		String[] lineas = conjunto.split("\n");
		for(String linea: lineas) {
			System.out.println(linea.split(" ")[0]);
			ret = ret || ((linea.split(" ")[0]).equals(";"+subConjunto));
			//if((linea.split(" ")[0]).equals(";"+subConjunto))
			//	ret = true;
		}
		return ret;
		*/
		//return conjunto.toLowerCase().matches(".*"+subConjunto.toLowerCase()+".*");	//ESTO NO FUNCIONA
		Pattern p = Pattern.compile(".*"+subConjunto+".*");
		Matcher m = p.matcher(conjunto);
		return m.find();
	}
	
	private void botonVerDescripcion(ActionEvent a) {
		int[] filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		if(ingresosEnLista.size() > filasSeleccionadas[0]) {
			//Seleccion un ingreso
			mostrarFactura(ingresosEnLista.get(filasSeleccionadas[0]).getNroFactura());
		}else {
			mostrarDatosRechazo(filasSeleccionadas[0]-ingresosEnLista.size());
		}
	}

	private void mostrarFactura(String nroFacturaCompleto) {
		ReporteFactura factura = new ReporteFactura(nroFacturaCompleto);
		factura.mostrar();
		int[] filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		boolean deboMostrarNotaCredito = false;
		MotivoEgreso modeloMotivoEgreso = new MotivoEgreso(new DAOSQLFactory());
		List<MotivoEgresoDTO> egresos = modeloMotivoEgreso.readAll();
		for(MotivoEgresoDTO e: egresos) {
			if(e.getNroFacturaCompleto().equals(nroFacturaCompleto)) {
				deboMostrarNotaCredito = true;
			}
		}
		if(deboMostrarNotaCredito) {
			ReporteNotaCredito notaCredito = new ReporteNotaCredito(nroFacturaCompleto);
			notaCredito.mostrar();
		}
	}
	
	private void mostrarDatosRechazo(int indexRechazoSeleccionado) {
		//ventanaRechazo
		RechazoCompraVirtualDTO rechazoSeleccionado = rechazosEnLista.get(indexRechazoSeleccionado);
		ventanaRechazo.getLblAltura().setText(rechazoSeleccionado.getAltura());
		ventanaRechazo.getLblApellido().setText(rechazoSeleccionado.getApellido());
		ventanaRechazo.getLblAltura().setText(rechazoSeleccionado.getAltura());
		ventanaRechazo.getLblCalle().setText(rechazoSeleccionado.getCalle());
		ventanaRechazo.getLblCodPostal().setText(rechazoSeleccionado.getCodPostal());
		ventanaRechazo.getLblCorreo().setText(rechazoSeleccionado.getCorreoElectronico());
		ventanaRechazo.getLblCuil().setText(rechazoSeleccionado.getCUIL());
		ventanaRechazo.getLblEstadoa().setText(rechazoSeleccionado.getEstado());
		ventanaRechazo.getLblFecha().setText(rechazoSeleccionado.getFecha());
		ventanaRechazo.getLblHora().setText(rechazoSeleccionado.getHora());
		ventanaRechazo.getLblImpuesto().setText(rechazoSeleccionado.getImpuestoAFIP());
		ventanaRechazo.getLblLocalidad().setText(rechazoSeleccionado.getLocalidad());
		ventanaRechazo.getLblNombre().setText(rechazoSeleccionado.getNombre());
		ventanaRechazo.getLblPago().setText(rechazoSeleccionado.getPago()+"");
		ventanaRechazo.getLblPais().setText(rechazoSeleccionado.getPais());
		ventanaRechazo.getLblProvincia().setText(rechazoSeleccionado.getProvincia());
		ventanaRechazo.getLblSucursal().setText(rechazoSeleccionado.getIdSucursal()+"");
		ventanaRechazo.getTextPane().setText(rechazoSeleccionado.getMotivo());
		
		ventanaRechazo.getLblTelefono().setText(rechazoSeleccionado.getTelefono());
		
		mostrarDetalleRechazo(rechazoSeleccionado);
		ventanaRechazo.mostrarVentana();
	}
	
	private void mostrarDetalleRechazo(RechazoCompraVirtualDTO rechazo) {
		borrarContenidoTablaDetalleRechazo();
		List<RechazoCompraVirtualDetalleDTO> detalleRechazo = this.modeloRechazoVirtual.readAllDetallesDeUnRechazoCompraVirtual(rechazo.getId());
		for(RechazoCompraVirtualDetalleDTO rcvd: detalleRechazo) {
			//{ "Cod Producto","Descripcion","Mayorista", "Minorista", "Costo"};
			String codProducto = rcvd.getIdProducto()+"";
			String descripcion = rcvd.getNombreProducto();
			String mayorista = rcvd.getPrecioMayorista()+"";
			String minorista = rcvd.getPrecioMinorista()+"";
			String costo = rcvd.getPrecioCosto()+"";
			Object[] agregar = {codProducto, descripcion, mayorista, minorista, costo};
			ventanaRechazo.getModelOrdenes().addRow(agregar);
		}
	}

	private void borrarContenidoTablaDetalleRechazo() {
		ventanaRechazo.getModelOrdenes().setRowCount(0);
		ventanaRechazo.getModelOrdenes().setColumnCount(0);
		ventanaRechazo.getModelOrdenes().setColumnIdentifiers(ventanaRechazo.getNombreColumnasTablaPrincipal());
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
	
	private String[] palabrasPorBarra(String sentence) {
		if (sentence == null || sentence.isEmpty()) {
			return new String[0];
		}
		String[] words = sentence.split("-");
		return words;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		/*
		ControladorVisualizarComprasVirtuales co = new ControladorVisualizarComprasVirtuales();
		co.inicializar();
		*/
	}

}
