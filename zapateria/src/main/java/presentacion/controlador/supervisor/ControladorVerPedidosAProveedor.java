package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dto.PedidosPendientesDTO;
import modelo.PedidosPendientes;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.Supervisor.VentanaVerPedidosAProveedores;

public class ControladorVerPedidosAProveedor {

	PedidosPendientes pedidosPendientes;
	
	List<PedidosPendientesDTO> todosLosPedidosPendientes;
	List<PedidosPendientesDTO> pedidosPendientesEnTabla;
	
	VentanaVerPedidosAProveedores ventanaVerPedidosAProveedor;
	
	public ControladorVerPedidosAProveedor(PedidosPendientes pedidosPendientes) {
		this.pedidosPendientes = pedidosPendientes;
		this.todosLosPedidosPendientes = new ArrayList<PedidosPendientesDTO>();
		this.pedidosPendientesEnTabla = new ArrayList<PedidosPendientesDTO>();
		this.ventanaVerPedidosAProveedor = new VentanaVerPedidosAProveedores();
	}
	
	public void inicializar() {
		this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
		
		this.ventanaVerPedidosAProveedor.getBtnSalir().addActionListener(a -> salir(a));
		
		this.ventanaVerPedidosAProveedor.getBtnConfirmarPedido().addActionListener(a -> confirmarPedido(a));
		
		this.ventanaVerPedidosAProveedor.getBtnConfirmarCancelacionDe().addActionListener(a -> cancelarPedido(a));
		
		this.ventanaVerPedidosAProveedor.getTextId().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		
		llenarTablaCompleta();
	}
	
	
	public void mostrarVentana() {
		this.ventanaVerPedidosAProveedor.show();
	}
	
	public void cerrarVentana() {
		this.ventanaVerPedidosAProveedor.cerrar();
	}
	
	
	public void salir(ActionEvent a) {
		cerrarVentana();
	}
	
	public void llenarTablaCompleta() {
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().setRowCount(0);//borrar datos de la tabla
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().setColumnCount(0);
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().setColumnIdentifiers(this.ventanaVerPedidosAProveedor.getNombreColumnasTablaPedidos());
		this.pedidosPendientesEnTabla.removeAll(this.pedidosPendientesEnTabla);
		
		for(PedidosPendientesDTO p: this.todosLosPedidosPendientes) {
			escribirTabla(p);
		}
	}
	
	public void realizarBusqueda() {
		String id=this.ventanaVerPedidosAProveedor.getTextId().getText();
		String proveedor = this.ventanaVerPedidosAProveedor.getTextProveedor().getText();
		String producto = this.ventanaVerPedidosAProveedor.getTextProducto().getText();
		String precio = this.ventanaVerPedidosAProveedor.getTextPrecio().getText();
		String estado = (String)this.ventanaVerPedidosAProveedor.getComboBoxEstado().getSelectedItem();
		
		
		/*
		public String fechaDesde() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDesde = this.ventanaHistorialCambioMoneda.getFechaDesde().getDate();

        String fechaDesdeFormato = null;
        if (fechaDesde != null) {
            fechaDesdeFormato = dateFormat.format(fechaDesde);
        }
        return fechaDesdeFormato;
    }
		
		
		*/
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fech = this.ventanaVerPedidosAProveedor.getDateChooser().getDate();
		
		String fecha = null;
        if (fech != null) {
            fecha = dateFormat.format(fech);
        } 
		
		System.out.println(fecha);
		Object hora =ventanaVerPedidosAProveedor.getSpinnerHora().getValue();
		System.out.println("hora: "+hora);
		//falta el ultimo cb que no se que va
		
		ArrayList<PedidosPendientesDTO> pedidosFiltrados = (ArrayList<PedidosPendientesDTO>) this.pedidosPendientes.getPedidosPendientesFiltrados("Id", id, "NombreProveedor", proveedor, "NombreMaestroProducto", producto, "PrecioTotal", precio, "Estado", estado, "Fecha", fecha, "Hora", null, null, null);
		
		llenarTablaFiltrada(pedidosFiltrados);
	}
	
	public void escribirTabla(PedidosPendientesDTO p) {
		int id = p.getId();
		String proveedor = p.getNombreProveedor();
		String prod = p.getNombreMaestroProducto();
		int cant = p.getCantidad();
		String unidadMedida = p.getUnidadMedida();
		
		double precioTota = p.getPrecioTotal();
		BigDecimal precioTotal = new BigDecimal(precioTota);
		
		
		
		String estado = p.getEstado();
		String fechaHora = p.getFecha()+" - "+p.getHora();
		String fechaHoraEnvio = p.getFechaEnvioMail() != null ? p.getFechaEnvioMail()+" - "+p.getHoraEnvioMail() : "-";
		String fechaHoraCierre = p.getFechaCompleto() != null ? p.getFechaCompleto()+" - "+p.getHoraCompleto() : "-";
		Object[] fila = {id,proveedor,prod,cant,unidadMedida,precioTotal,estado,fechaHora,fechaHoraEnvio,fechaHoraCierre};
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().addRow(fila);
		this.pedidosPendientesEnTabla.add(p);	
	}
	
	
	public void llenarTablaFiltrada(List<PedidosPendientesDTO> pedidosFiltrados) {
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().setRowCount(0);//borrar datos de la tabla
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().setColumnCount(0);
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().setColumnIdentifiers(this.ventanaVerPedidosAProveedor.getNombreColumnasTablaPedidos());
		this.pedidosPendientesEnTabla.removeAll(this.pedidosPendientesEnTabla);
		
		for(PedidosPendientesDTO p: pedidosFiltrados) {
			escribirTabla(p);
		}
		
	}
	
	
	public void cancelarPedido(ActionEvent a) {
		int filaSeleccionada = this.ventanaVerPedidosAProveedor.getTablePedidos().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun pedido");
			return;
		}
		PedidosPendientesDTO pedidoSeleccionado = this.pedidosPendientesEnTabla.get(filaSeleccionada);
		if(pedidoSeleccionado.getEstado().equals("Completado")) {
			 JOptionPane.showMessageDialog(null, "No se puede cancelar el pedido. El pedido ya esta completado", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		String fecha = dtf.format(LocalDateTime.now());
		
	    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String hora = tf.format(LocalDateTime.now());
		
		boolean update = this.pedidosPendientes.finalizarPedido("Cancelado", fecha, hora,pedidoSeleccionado.getId());
		if(update) {
			JOptionPane.showMessageDialog(null, "Pedido cancelado con exito");
		}else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al cancelar el pedido");
		}
		
		this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
		llenarTablaCompleta();
	}
	
	public void confirmarPedido(ActionEvent a) {
		int filaSeleccionada = this.ventanaVerPedidosAProveedor.getTablePedidos().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun pedido");
			return;
		}
		PedidosPendientesDTO pedidoSeleccionado = this.pedidosPendientesEnTabla.get(filaSeleccionada);
		if(pedidoSeleccionado.getEstado().equals("Completado")) {
			JOptionPane.showMessageDialog(null, "No es posible completar este pedido, el pedido ya ha sido compeltado", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if( pedidoSeleccionado.getEstado().equals("Cancelado") ) {
			JOptionPane.showMessageDialog(null, "No es posible completar este pedido, el pedido ya ha sido Cancelado", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(pedidoSeleccionado.getEstado().equals("En espera")) {
			JOptionPane.showMessageDialog(null, "No es posible completar este pedido, el pedido esta en espera de envio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//si fue enviado
//		g nuevoEstado, String fechaCompleto, String HoraCompleto, int idPedido
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		String fecha = dtf.format(LocalDateTime.now());
		
	    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String hora = tf.format(LocalDateTime.now());
		
		boolean update = this.pedidosPendientes.finalizarPedido("Completado", fecha, hora, pedidoSeleccionado.getId());
		
		if(update) {
			JOptionPane.showMessageDialog(null, "Pedido completado con exito");
		}else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al completar el pedido");
		}
		
		this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
		llenarTablaCompleta();
		
	}
	
	
	public static void main(String[] args) {
		PedidosPendientes pedido = new PedidosPendientes(new DAOSQLFactory());
		ControladorVerPedidosAProveedor c = new ControladorVerPedidosAProveedor(pedido);
		c.inicializar();
		c.mostrarVentana();
	}
	
}
