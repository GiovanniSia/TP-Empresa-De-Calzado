package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dto.PedidosPendientesDTO;
import dto.StockDTO;
import modelo.PedidosPendientes;
import modelo.Stock;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Supervisor.VentanaVerPedidosAProveedores;

public class ControladorVerPedidosAProveedor {

	PedidosPendientes pedidosPendientes;
	
	Stock stock;
	List<StockDTO> listaStock;
	
	List<PedidosPendientesDTO> todosLosPedidosPendientes;
	List<PedidosPendientesDTO> pedidosPendientesEnTabla;
	
	VentanaVerPedidosAProveedores ventanaVerPedidosAProveedor;
	
	Controlador controlador;
	
	public ControladorVerPedidosAProveedor(Controlador controlador,PedidosPendientes pedidosPendientes, Stock stock) {
		this.pedidosPendientes = pedidosPendientes;
		this.todosLosPedidosPendientes = new ArrayList<PedidosPendientesDTO>();
		this.pedidosPendientesEnTabla = new ArrayList<PedidosPendientesDTO>();
		this.ventanaVerPedidosAProveedor = new VentanaVerPedidosAProveedores();
		this.stock = stock;
		this.controlador=controlador;
	}
	
	public void inicializar() {
		this.listaStock = this.stock.readAll();
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
		this.ventanaVerPedidosAProveedor.getTextProveedor().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaVerPedidosAProveedor.getTextProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaVerPedidosAProveedor.getTextPrecio().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaVerPedidosAProveedor.getComboBoxEstado().addActionListener(a -> realizarBusqueda(a));

		this.ventanaVerPedidosAProveedor.getDateChooserDesde().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				realizarBusqueda();
				}		
		});
		
		this.ventanaVerPedidosAProveedor.getSpinnerHoraDesde().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				//actualizar cantidad
				realizarBusqueda();
			}			
		});
				
		this.ventanaVerPedidosAProveedor.getDateChooserHasta().addPropertyChangeListener(
			new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stub
					realizarBusqueda();
				}
		});
		
		this.ventanaVerPedidosAProveedor.getSpinnerHoraHasta().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent a) {
				realizarBusqueda();
			}
		});
		
		this.ventanaVerPedidosAProveedor.getComboBoxEstadoSolo().addActionListener(a -> realizarBusqueda(a));
		
		llenarComboBoxes();
		llenarTablaCompleta();
	}
	
	public void realizarBusqueda(ActionEvent a) {
		realizarBusqueda();
	}
	
	
	
	public void mostrarVentana() {
		this.ventanaVerPedidosAProveedor.show();
	}
	
	public void cerrarVentana() {
		this.ventanaVerPedidosAProveedor.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
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

		//Fecha desde
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechD = this.ventanaVerPedidosAProveedor.getDateChooserDesde().getDate();
		
		String fechaDesde = null;
        if (fechD != null) {
            fechaDesde = dateFormat.format(fechD);
        } 
        
        
        
		DateFormat dateFmt = new SimpleDateFormat("HH:mm:ss");
		Date fechaHoraDesde = (Date) this.ventanaVerPedidosAProveedor.getSpinnerHoraDesde().getValue();
	
		//Hora desde
		String horaDesde = null;
        if (fechaHoraDesde != null) {
            horaDesde = dateFmt.format(fechaHoraDesde);
        } 
        
        
        //Fecha HASTA
        Date fechaH = this.ventanaVerPedidosAProveedor.getDateChooserHasta().getDate();
        String fechaHasta = null;
        if( fechaH != null) {
        	fechaHasta = dateFormat.format(fechaH);
        }
        
        //Hora hasta
        Date fechaHoraHasta = (Date) this.ventanaVerPedidosAProveedor.getSpinnerHoraHasta().getValue(); 
        String horaHasta = null;
        if(fechaHoraHasta != null) {
        	horaHasta = dateFmt.format(fechaHoraHasta);
        }

        //falta el ultimo cb que no se que va
//        String cBestadoSolo = (String) this.ventanaVerPedidosAProveedor.getComboBoxEstadoSolo().getSelectedItem(); 
        
        
		
		ArrayList<PedidosPendientesDTO> pedidosFiltrados = (ArrayList<PedidosPendientesDTO>) this.pedidosPendientes.getPedidosPendientesFiltrados("Id", id, "NombreProveedor", proveedor, "NombreMaestroProducto", producto, "PrecioTotal", precio, "Estado", estado, "Fecha", fechaDesde, fechaHasta, "Hora", horaDesde,horaHasta);
		
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
		 String cBestadoSolo = (String) this.ventanaVerPedidosAProveedor.getComboBoxEstadoSolo().getSelectedItem(); 
		for(PedidosPendientesDTO p: pedidosFiltrados) {
			System.out.println("prod: "+p.getNombreMaestroProducto()+" - "+p.getEstado());
			if(!cBestadoSolo.equals("Sin seleccionar")) {
				if(cBestadoSolo.equals("Alta") && p.getFecha()!=null) {
					escribirTabla(p);
				}
				if(cBestadoSolo.equals("Envio") && p.getFechaEnvioMail()!=null) {
					escribirTabla(p);
				}
				if(cBestadoSolo.equals("Cierre") && p.getFechaCompleto()!=null) {
					escribirTabla(p);
				}
			}else {
				escribirTabla(p);
			}
			
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
		if(pedidoSeleccionado.getEstado().equals("Recibido")) {
			JOptionPane.showMessageDialog(null, "No es posible marcar como recibido este pedido", "Error", JOptionPane.ERROR_MESSAGE);
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
		
	    
		StockDTO stockDeProd = getStock(pedidoSeleccionado.getIdMaestroProducto());
		
		int resp = JOptionPane.showConfirmDialog(null, "Esta seguro que desea marcar el pedido como completado?.\nSe aumentara el stock de: "+pedidoSeleccionado.getNombreMaestroProducto()+" "+pedidoSeleccionado.getUnidadMedida()+"\nStock previo: "+stockDeProd.getStockDisponible()+" -> Stock actualizado: "+(stockDeProd.getStockDisponible()+pedidoSeleccionado.getCantidad()), "Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(resp==0) {
			boolean update = this.pedidosPendientes.finalizarPedido("Recibido", fecha, hora, pedidoSeleccionado.getId());
			
			if(update) {
				JOptionPane.showMessageDialog(null, "Pedido marcado como recibido con exito");
			}else {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al marcar como recibido el pedido");
			}
			
			int nuevaCant = stockDeProd.getStockDisponible() + pedidoSeleccionado.getCantidad();
			
			boolean descontarStock = this.stock.actualizarStock(stockDeProd.getIdStock(), nuevaCant);
			if(!descontarStock) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al reponer el stock", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
		this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
		llenarTablaCompleta();
		
	}
	
	public StockDTO getStock(int idProducto) {
		for(StockDTO s: this.listaStock) {
			if(s.getIdProducto()==idProducto) {
				return s;
			}
		}
		return null;
	}
	
	
	public void llenarComboBoxes() {
		String[] estados = {"En espera","Recibido","Pagado","Cancelado"};
		for(int i=0; i<estados.length; i++) {
			this.ventanaVerPedidosAProveedor.getComboBoxEstado().addItem(estados[i]);
		}
		
		String[] estadosTabla = {"Alta","Envio","Cierre"};
		for(int i=0; i<estadosTabla.length; i++) {
			this.ventanaVerPedidosAProveedor.getComboBoxEstadoSolo().addItem(estadosTabla[i]);
		}
	}
	
	
//	public static void main(String[] args) {
//		PedidosPendientes pedido = new PedidosPendientes(new DAOSQLFactory());
//		Stock stock = new Stock(new DAOSQLFactory());
//		ControladorVerPedidosAProveedor c = new ControladorVerPedidosAProveedor(pedido,stock);
//		c.inicializar();
//		c.mostrarVentana();
//	}
	
}
