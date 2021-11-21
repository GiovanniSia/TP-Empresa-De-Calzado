package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

import dto.MaestroProductoDTO;
import dto.PedidosPendientesDTO;
import dto.StockDTO;
import inicioSesion.empleadoProperties;
import inicioSesion.sucursalProperties;
import modelo.MaestroProducto;
import modelo.PedidosPendientes;
import modelo.Stock;
import modelo.generarOrdenesFabricacion;
import presentacion.controlador.Controlador;
import presentacion.controlador.Cajero.ControladorEgresosCaja;
import presentacion.vista.Supervisor.VentanaVerPedidosAProveedores;

public class ControladorVerPedidosAProveedor {

	int idSucursal;
	int idEmpleado;
	
	
	PedidosPendientes pedidosPendientes;
	
	Stock stock;
	List<StockDTO> listaStock;
	
	List<PedidosPendientesDTO> todosLosPedidosPendientes;
	List<PedidosPendientesDTO> pedidosPendientesEnTabla;

	
	MaestroProducto maestroProducto;
	List<MaestroProductoDTO> todosLosProductos;
	
	public VentanaVerPedidosAProveedores ventanaVerPedidosAProveedor;
	
	Controlador controlador;
	
	ControladorEgresosCaja controladorEgresosCaja;
	
	public ControladorVerPedidosAProveedor(Controlador controlador,PedidosPendientes pedidosPendientes, Stock stock,MaestroProducto maestroProducto) {
		this.pedidosPendientes = pedidosPendientes;
		this.todosLosPedidosPendientes = new ArrayList<PedidosPendientesDTO>();
		this.pedidosPendientesEnTabla = new ArrayList<PedidosPendientesDTO>();
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.maestroProducto = maestroProducto;
		
		this.stock = stock;
		this.controlador=controlador;
	}
	
	public void inicializar() {
		this.ventanaVerPedidosAProveedor = new VentanaVerPedidosAProveedores();

		this.todosLosProductos = this.maestroProducto.readAll();
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
		
		this.ventanaVerPedidosAProveedor.getBtnSalirAEgresos().addActionListener(a -> regresarAEgresos());
		
		setearDatosDeProperties();
		llenarComboBoxes();
		llenarTablaCompleta();
	}

	
	public void setearDatosDeProperties() {
		empleadoProperties empleado = empleadoProperties.getInstance();
		sucursalProperties sucu = sucursalProperties.getInstance();
		try {
			this.idSucursal = Integer.parseInt(sucu.getValue("IdSucursal"));
			this.idEmpleado = Integer.parseInt(empleado.getValue("IdEmpleado"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public void realizarBusqueda(ActionEvent a) {
		realizarBusqueda();
	}
	
	
	
	public void mostrarVentana() {
		this.ventanaVerPedidosAProveedor.show();
	}
	
	public void cerrarVentana() {
		this.ventanaVerPedidosAProveedor.cerrar();
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
			if(p.getIdSucursal() == this.idSucursal) {
				escribirTabla(p);	
			}
				
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
		int idProveedor = p.getIdProveedor();
		String prod = p.getNombreMaestroProducto();
		
		double cantidad = p.getCantidad();
		BigDecimal cant = new BigDecimal(cantidad).setScale(2, RoundingMode.HALF_UP);
		
		String unidadMedida = p.getUnidadMedida();
		
		double precioTota = p.getPrecioTotal();
		BigDecimal precioTotal = new BigDecimal(precioTota).setScale(2, RoundingMode.HALF_UP);
		
		
		
		String estado = p.getEstado();
		String fechaHora = p.getFecha()+" - "+p.getHora();
		String fechaHoraEnvio = p.getFechaEnvioMail() != null ? p.getFechaEnvioMail()+" - "+p.getHoraEnvioMail() : "-";
		String fechaHoraCierre = p.getFechaCompleto() != null ? p.getFechaCompleto()+" - "+p.getHoraCompleto() : "-";
		
		BigDecimal totalPagado = new BigDecimal(p.getTotalPagado()).setScale(2, RoundingMode.HALF_UP);
		
		Object[] fila = {id,proveedor,idProveedor,prod,cant,unidadMedida,precioTotal,estado,fechaHora,fechaHoraEnvio,fechaHoraCierre,totalPagado};
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
			
			if(p.getIdSucursal() == this.idSucursal) {
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
		
	}
	
	
	public void cancelarPedido(ActionEvent a) {
		int filaSeleccionada = this.ventanaVerPedidosAProveedor.getTablePedidos().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun pedido");
			return;
		}
		PedidosPendientesDTO pedidoSeleccionado = this.pedidosPendientesEnTabla.get(filaSeleccionada);
		if(pedidoSeleccionado.getEstado().equals("Recibido") || pedidoSeleccionado.getEstado().equals("Pagado")) {
			 JOptionPane.showMessageDialog(null, "No se puede cancelar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
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
		
	    
//	    Double cantidadDeStockDisp = getStockDisponible(pedidoSeleccionado.getIdMaestroProducto());
	    BigDecimal cantidadDeStockDisp = sumarTodoElStock(pedidoSeleccionado.getIdMaestroProducto());
	    BigDecimal cantidadPedido = new BigDecimal(pedidoSeleccionado.getCantidad());
	    BigDecimal stockAct = cantidadDeStockDisp.add(cantidadPedido); 
		
		int resp = JOptionPane.showConfirmDialog(null, "Esta seguro que desea marcar el pedido como completado?.\nSe aumentara el stock de: "+pedidoSeleccionado.getNombreMaestroProducto()+" "+pedidoSeleccionado.getUnidadMedida()+"\nStock previo: "+cantidadDeStockDisp+" -> Stock actualizado: "+stockAct, "Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(resp==0) {
			boolean update = this.pedidosPendientes.finalizarPedido("Recibido", fecha, hora, pedidoSeleccionado.getId());
			
			if(update) {
				JOptionPane.showMessageDialog(null, "Pedido marcado como recibido con exito");
			}else {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al marcar como recibido el pedido");
				return;
			}
			
			MaestroProductoDTO producto = getMaestroProductoDePedido(pedidoSeleccionado.getIdMaestroProducto());
			
			int idStock=0;
			int idSucursal = this.idSucursal;
			int idProd = producto.getIdMaestroProducto();
			String codLote = generarOrdenesFabricacion.crearCodigoLote(producto);
			Double stockDisp = (double) pedidoSeleccionado.getCantidad();

			
			StockDTO nuevoStock = new StockDTO(idStock,idSucursal,idProd,codLote,stockDisp);
			
			boolean insert = this.stock.insert(nuevoStock);
			if(!insert) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar dar de alta un stock nuevo");				
			}else {
				JOptionPane.showMessageDialog(null, "Se ha creado un nuevo stock en el sistema con el codigo: "+nuevoStock.getCodigoLote());
			}
		}
		
		
		this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
		this.listaStock = this.stock.readAll();
//		llenarTablaCompleta();
		realizarBusqueda();
		
	}
	
	private MaestroProductoDTO getMaestroProductoDePedido(int idMaestroProducto) {
		for(MaestroProductoDTO p: this.todosLosProductos) {
			if(p.getIdMaestroProducto() == idMaestroProducto) {
				return p;
			}
		}return null;
	}

	public Double getStockDisponible(int idProducto) {
		Double cant=0.0;
		for(StockDTO s: this.listaStock) {
			//si ese stock tiene este producto asociado
			if(s.getIdProducto()==idProducto) {
				cant = cant+s.getStockDisponible();
			}
		}
		return cant;
	}
	
	public BigDecimal sumarTodoElStock(int idMP) {
		BigDecimal cant=new BigDecimal(0);
		for(StockDTO s: listaStock) {
			if(s.getIdProducto() == idMP && this.idSucursal==s.getIdSucursal()) {
				BigDecimal stockDisp = new BigDecimal(s.getStockDisponible());
				cant = cant.add(stockDisp);
			}
		}return cant;
	}
	
	
	public void llenarComboBoxes() {
		String[] estados = {"En espera","Enviado","Recibido","Pagado","Cancelado"};
		for(int i=0; i<estados.length; i++) {
			this.ventanaVerPedidosAProveedor.getComboBoxEstado().addItem(estados[i]);
		}
		
		String[] estadosTabla = {"Alta","Envio","Cierre"};
		for(int i=0; i<estadosTabla.length; i++) {
			this.ventanaVerPedidosAProveedor.getComboBoxEstadoSolo().addItem(estadosTabla[i]);
		}
	}
	
	public void regresarAEgresos() {
		this.ventanaVerPedidosAProveedor.cerrar();
	}
}
