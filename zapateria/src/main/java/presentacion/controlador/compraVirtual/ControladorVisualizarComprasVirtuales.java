package presentacion.controlador.compraVirtual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import dto.ClienteDTO;
import dto.FacturaDTO;
import dto.IngresosDTO;
import dto.RechazoCompraVirtualDTO;
import dto.RechazoCompraVirtualDetalleDTO;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.Ingresos;
import modelo.compraVirtual.RechazoCompraVirtual;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.reportes.ReporteFactura;
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
	
	RechazoCompraVirtual modeloRechazoVirtual;
	List<IngresosDTO> ingresosEnLista;
	List<RechazoCompraVirtualDTO> rechazosEnLista;
	
	public ControladorVisualizarComprasVirtuales() {
		modeloRechazoVirtual = new RechazoCompraVirtual(new DAOSQLFactory());
		modeloFactura = new Factura(new DAOSQLFactory());
		modeloDetalleFactura = new DetalleFactura(new DAOSQLFactory());
		modeloIngresos = new Ingresos(new DAOSQLFactory());
		modeloCliente = new Cliente(new DAOSQLFactory());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		ventanaPrincipal = new VentanaVerComprasVirtuales();
		ventanaPrincipal.getBtnVerDescripcion().addActionListener(r->botonVerDescripcion(r));
		
		ventanaRechazo = new VentanaVerDetalleRechazo();
	}
	
	public void inicializar() {
		refrescarTabla();
		ventanaPrincipal.mostrarVentana();
	}
	
	public void refrescarTabla() {
		vaciarTablaPrincipal();
		ingresosEnLista = recuperarComprasVirtuales();
		llenarTablaConIngresos(ingresosEnLista);
		
		rechazosEnLista = recuperarRechazos();
		llenarTablaConRechazos(rechazosEnLista);
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
	
	private List<IngresosDTO> recuperarComprasVirtuales(){
		List<IngresosDTO> ret  = new ArrayList<IngresosDTO>();
		List<IngresosDTO> todasLosIngresos = this.modeloIngresos.readAll();
		for(IngresosDTO ingreso: todasLosIngresos) {
			if(ingreso.getMedioPago().equals("PV")) {
				ret.add(ingreso);
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
			
			Object[] agregar = {idIngreso, idSucursal, cuil, nombre, fecha, hora};
			ventanaPrincipal.getModelDeTablaPrincipal().addRow(agregar);
		}
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
		// TODO Auto-generated method stub
		//stringQueIndicaQueUnDatoEstaVacio
		for(RechazoCompraVirtualDTO re: rechazos) {
			String idRechazo = re.getId()+"";
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
				nombre = re.getIdSucursal()+"";
			}
			String fecha = re.getFecha();
			String hora = re.getHora();
			Object[] agregar = {idRechazo, idSucursal, cuil, nombre, fecha, hora};
			ventanaPrincipal.getModelDeTablaPrincipal().addRow(agregar);
		}
		
	}
	
	private List<RechazoCompraVirtualDTO> recuperarRechazos(){
		List<RechazoCompraVirtualDTO> todosRechazos = modeloRechazoVirtual.readAllRechazosComprasVirtuales();
		return todosRechazos;
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		ControladorVisualizarComprasVirtuales co = new ControladorVisualizarComprasVirtuales();
		co.inicializar();
	}

}
