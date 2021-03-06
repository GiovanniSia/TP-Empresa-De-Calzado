package presentacion.controlador.supervisor;

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import inicioSesion.empleadoProperties;
import inicioSesion.sucursalProperties;

import modelo.MaestroProducto;

import modelo.Stock;
import modelo.generarOrdenesFabricacion;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Supervisor.VentanaGestionarProductos;

public class ControladorGestionarProductos {

	public int idSucursal = 0;
	static String tipoEmpleado = "";

	public void obtenerDatosPropertiesSucursalEmpleado() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			idSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));

			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			tipoEmpleado = empleadoProp.getValue("TipoEmpleado");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	MaestroProducto maestroProducto;
	Stock stock;

	List<MaestroProductoDTO> todosLosProductos;
	List<StockDTO> todoElStock;

	List<MaestroProductoDTO> productosEnTabla;

	VentanaGestionarProductos ventanaGestionarProductos;
	
	Controlador controlador;
	ControladorAltaProducto controladorAltaProducto;

	ControladorGenerarPedidoAProveedorManualmente controladorGenerarPedidoAProveedorManualmente;

	public ControladorGestionarProductos(Controlador controlador, MaestroProducto maestroProducto, Stock stock) {
		this.maestroProducto = maestroProducto;
		this.stock = stock;

		this.controlador = controlador;

		this.todoElStock = new ArrayList<StockDTO>();
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.productosEnTabla = new ArrayList<MaestroProductoDTO>();
	}

	public void setControladorAltaProducto(ControladorAltaProducto controladorAltaProducto) {
		this.controladorAltaProducto = controladorAltaProducto;
	}

	public void setControladorGenerarPedidoAProveedorManualmente(
			ControladorGenerarPedidoAProveedorManualmente controladorGenerarPedidoAProveedorManualmente) {
		this.controladorGenerarPedidoAProveedorManualmente = controladorGenerarPedidoAProveedorManualmente;
	}
	
	public void inicializar() {
		obtenerDatosPropertiesSucursalEmpleado();
		this.todosLosProductos = this.maestroProducto.readAll();
		this.todoElStock = this.stock.readAll();

		this.ventanaGestionarProductos = new VentanaGestionarProductos();

		if (tipoEmpleado.equals("Vendedor")) {
			mostrarVentanaParaVerProductos();
		} else if(tipoEmpleado.equals("Administrativo")){
			mostrarVentanaParaVerProductosYOrdenDeManufactura();
			this.ventanaGestionarProductos.getBtnGenerarOrdenDeManufactura()
			.addActionListener(a -> generarOrdenDeManufactura());
			this.ventanaGestionarProductos.getBtnGenerarPedido().addActionListener(a -> pasarAGenerarPedido());
		}else{
			
			this.ventanaGestionarProductos.getBtnAgregarProducto().addActionListener(a -> pasarAAgregarProducto(a));
			this.ventanaGestionarProductos.getBtnEditar().addActionListener(a -> pasarAEditarProducto());
			this.ventanaGestionarProductos.getBtnGenerarOrdenDeManufactura().addActionListener(a -> generarOrdenDeManufactura());
			this.ventanaGestionarProductos.getBtnGenerarPedido().addActionListener(a -> pasarAGenerarPedido());
			
		}
		this.ventanaGestionarProductos.getBtnAtras().addActionListener(a -> volverAtras(a));

		this.ventanaGestionarProductos.getTxtFieldNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaGestionarProductos.getTextTalle().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});

		this.ventanaGestionarProductos.getTxtFieldNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaGestionarProductos.getTextTalle().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		
//		this.ventanaGestionarProductos.getChckbxProdSinStock().addActionListener(a -> realizarBusqueda());
		
//		this.ventanaGestionarProductos.getBtnGenerarPedido().addActionListener(a -> pasarAGenerarPedido());

		this.ventanaGestionarProductos.getTablaProductos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = this.ventanaGestionarProductos.getTablaProductos().getSelectionModel();

		rowSM.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				filaSeleccionada();
			}

		});

		this.ventanaGestionarProductos.getFrame().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ventanaGestionarProductos.getTablaProductos().getSelectionModel().clearSelection();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		escribirTablaCompleta();
		validarTeclado();
//		escribirLabels();
	}

//	public void escribirLabels() {
//		empleadoProperties empleado = empleadoProperties.getInstance();
//		sucursalProperties sucu = sucursalProperties.getInstance();
//		try {
//			String nombreEmp = empleado.getValue("Nombre")+" "+empleado.getValue("Apellido");
//			String sucursal = sucu.getValue("Nombre");
//			this.ventanaGestionarProductos.getLblNombreEmpleado().setText(nombreEmp);
//			this.ventanaGestionarProductos.getLblNombreSucursal().setText(sucursal);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void mostrarVentanaParaVerProductosYOrdenDeManufactura() {
		this.ventanaGestionarProductos.mostrarVentanaParaVerProductosYOrdenDeManufactura();
	}
	
	public void mostrarVentanaParaVerProductos() {
		this.ventanaGestionarProductos.mostrarVentanaParaVerProductos();
	}

	public void mostrarVentana() {
		this.ventanaGestionarProductos.show();
	}
	
	public void volverAtras(ActionEvent a) {
		this.ventanaGestionarProductos.cerrar();
		this.controlador.mostrarVentanaMenuDeSistemas();

	}

	public void pasarAAgregarProducto(ActionEvent a) {
		this.ventanaGestionarProductos.cerrar();
		this.controladorAltaProducto.inicializar();
		this.controladorAltaProducto.mostrarVentana();
	}

	public void realizarBusqueda() {
		String txtNombre = this.ventanaGestionarProductos.getTxtFieldNombre().getText();
		String txtTalle = this.ventanaGestionarProductos.getTextTalle().getText();
		List<MaestroProductoDTO> productosAproximados = this.maestroProducto.getMaestroProductoAproximado("Descripcion",
				txtNombre, "Talle", txtTalle, null, null, null, null);

		escribirTablaFiltrada(productosAproximados);
	}

	public void escribirTablaCompleta() {
		this.ventanaGestionarProductos.getModelProductos().setRowCount(0);// borrar datos de la tabla
		this.ventanaGestionarProductos.getModelProductos().setColumnCount(0);
		this.ventanaGestionarProductos.getModelProductos()
				.setColumnIdentifiers(this.ventanaGestionarProductos.getNombreColumnas());
		productosEnTabla.removeAll(productosEnTabla);
		for(MaestroProductoDTO m: this.todosLosProductos) {
			agregarATabla(m);	
				
			
		}
	}
	
	public boolean sucursalVendeEsteProducto(MaestroProductoDTO m) {
		for(StockDTO s: this.todoElStock) {
			if(s.getIdProducto() == m.getIdMaestroProducto() && s.getIdSucursal() == this.idSucursal) {
				return true;
			}
		}return false;
	}
	
	public void escribirTablaFiltrada(List<MaestroProductoDTO> productosAproximados) {
		this.ventanaGestionarProductos.getModelProductos().setRowCount(0);//borrar datos de la tabla
		this.ventanaGestionarProductos.getModelProductos().setColumnCount(0);
		this.ventanaGestionarProductos.getModelProductos().setColumnIdentifiers(this.ventanaGestionarProductos.getNombreColumnas());
		productosEnTabla.removeAll(productosEnTabla);
		
		for(MaestroProductoDTO m: productosAproximados) {
			agregarATabla(m);	
			
		}
	}
	
	public void agregarATabla(MaestroProductoDTO m) {
		int id = m.getIdMaestroProducto();
		String nombre = m.getDescripcion();
		String tipo = m.getTipo();
		String propio = m.getFabricado();

		double costoProd = m.getPrecioCosto();
		BigDecimal costoProduccion = new BigDecimal(costoProd).setScale(2, RoundingMode.HALF_UP);
				
		double precioM = m.getPrecioMayorista();
		BigDecimal precioMayo = new BigDecimal(precioM).setScale(2, RoundingMode.HALF_UP);

		double precioMi = m.getPrecioMinorista();
		BigDecimal precioMino = new BigDecimal(precioMi).setScale(2, RoundingMode.HALF_UP);

		int puntoRepMin = m.getPuntoRepositorio();

		int idProv = m.getIdProveedor();

		String talle = m.getTalle();
		String medida = m.getUnidadMedida();
		String estado = m.getEstado();

		int cantARep = m.getCantidadAReponer();
		int diasARep = m.getDiasParaReponer();
		
//		BigDecimal cantStockDisp = obtenerCantidadStockDisp(m);
//		BigDecimal cantStockDisp = new BigDecimal( Stock.cantidadTotalDeStock(m));
		BigDecimal cantStockDisp = sumarTodoElStock(m);
		
		Object[] fila = { id,nombre,cantStockDisp,tipo,propio,costoProduccion,precioMayo,precioMino,puntoRepMin,idProv,talle,medida,estado,cantARep,diasARep};
		this.ventanaGestionarProductos.getModelProductos().addRow(fila);
		productosEnTabla.add(m);

		verificarCambiarColorAEstaFila();

	}
	
	public BigDecimal sumarTodoElStock(MaestroProductoDTO m) {
		BigDecimal cant=new BigDecimal(0);
		for(StockDTO s: todoElStock) {
			if(s.getIdProducto() == m.getIdMaestroProducto() && s.getIdSucursal() == this.idSucursal) {
				BigDecimal stockDisp = new BigDecimal(s.getStockDisponible());
				cant = cant.add(stockDisp);
			}
		}return cant;
	}
	
//	public BigDecimal obtenerCantidadStockDisp(MaestroProductoDTO prod) {
//		BigDecimal cant=new BigDecimal(0);
//		for(StockDTO s: this.todoElStock) {
//			if(s.getIdProducto() == prod.getIdMaestroProducto()) {
//				BigDecimal stock = new BigDecimal(s.getStockDisponible());
//				cant.add(stock);
//			}
//		}
//		return cant;
//	}
	
	private void verificarCambiarColorAEstaFila() {
		this.ventanaGestionarProductos.getTablaProductos().setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				//al parecer el row toma la ultima fila agregada
		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		        
//	        	if((Double)table.getValueAt(row, 2) == 0) {
		        BigDecimal valor = (BigDecimal) table.getValueAt(row, 2);
		        BigDecimal aux = new BigDecimal(0);
		        if(valor.compareTo(aux) == 0 || valor.compareTo(aux)==-1) {
		        	setBackground(Color.red);
		        	setForeground(Color.WHITE);	
		        }else {
		        	setBackground(table.getBackground());
		        	setForeground(Color.BLACK);
		        }
		        
		        return this;
		    }   
		});
	}

	public void filaSeleccionada() {
		int fila = this.ventanaGestionarProductos.getTablaProductos().getSelectedRow();
		if (fila == -1) {
			return;
		}
		cambiarColorFila();

	}

	public void cambiarColorFila() {
		this.ventanaGestionarProductos.getTablaProductos().setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int col) {

						super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

						if (isSelected) {
//		        	if((int)table.getValueAt(row, 14) > 0) {
//		        		setBackground(Color.lightGray);
//			        	setForeground(Color.WHITE);	
//		        	}
		        	
		        }else {
//		        	if((Double)table.getValueAt(row, 2) == 0) {
			        BigDecimal valor = (BigDecimal) table.getValueAt(row, 2);
			        BigDecimal aux = new BigDecimal(0);
			        if(valor.compareTo(aux) == 0 || valor.compareTo(aux)==-1) {
			        	setBackground(Color.red);
			        	setForeground(Color.WHITE);	
		        	}else {
		        		setBackground(table.getBackground());
			        	setForeground(Color.BLACK);
		        	}
		        }
		        
		        return this;
		    }   
		});
	}

	public void validarTeclado() {
		this.ventanaGestionarProductos.getTxtFieldNombre().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasNumerosYEspacios(e);
			}
		}));
		this.ventanaGestionarProductos.getTextTalle().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
	}

	public void pasarAGenerarPedido() {
		int fila = this.ventanaGestionarProductos.getTablaProductos().getSelectedRow();
		if (fila == -1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un producto primero", "Error", JOptionPane.OK_OPTION);
			return;
		}		
		MaestroProductoDTO productoSeleccionado = this.productosEnTabla.get(fila);
		if (productoSeleccionado.getFabricado().equals("S")) {
			JOptionPane.showMessageDialog(null, "El producto es propio. Imposible generar orden a proveedor", "Error",
					JOptionPane.OK_OPTION);
			return;
		}
		if(productoSeleccionado.getEstado().equals("Inactivo") || productoSeleccionado.getEstado().equals("Suspendido")) {
			JOptionPane.showMessageDialog(null, "El producto no se encuentra activo", "Info",JOptionPane.INFORMATION_MESSAGE);
			return;	
		}
		
		StockDTO stockDeProd = null;
		for (StockDTO s : this.todoElStock) {
			if (s.getIdProducto() == productoSeleccionado.getIdMaestroProducto()) {
				stockDeProd = s;
			}
		}

		this.controladorGenerarPedidoAProveedorManualmente.setProductoElegido(productoSeleccionado, stockDeProd);
		this.ventanaGestionarProductos.cerrar();
		this.controladorGenerarPedidoAProveedorManualmente.inicializar();
		this.controladorGenerarPedidoAProveedorManualmente.mostrarVentana();
	}

	public void generarOrdenDeManufactura() {
		int filaseleccionada = this.ventanaGestionarProductos.getTablaProductos().getSelectedRow();
		if (filaseleccionada == -1) {
			JOptionPane.showMessageDialog(ventanaGestionarProductos, "Debe seleccionar un producto");
			return;
		}
		MaestroProductoDTO prodSeleccionado = this.productosEnTabla.get(filaseleccionada);
		if (prodSeleccionado.getFabricado().equals("N")) {
			JOptionPane.showMessageDialog(ventanaGestionarProductos, "El producto no se fabrica en la fabrica");
			return;
		}
		if(prodSeleccionado.getEstado().equals("Inactivo") || prodSeleccionado.getEstado().equals("Suspendido")) {
			JOptionPane.showMessageDialog(null, "El producto no se encuentra activo", "Info",JOptionPane.INFORMATION_MESSAGE);
			return;	
		}
		
		boolean repetir = true;
		String resp = null;
		while (repetir) {

			try {
				// si la resp es null, se eligio cancelar
				resp = JOptionPane.showInputDialog("Ingrese la cantidad de lotes. (Limite 999)");
				if (resp == null) {
					repetir = false;
				} else {
					if (resp.equals("")) {
						JOptionPane.showMessageDialog(null, "El valor no puede ser nulo", "Informacion",
								JOptionPane.OK_OPTION);
					} else {
						int cantidad = Integer.parseInt(resp);
						if (cantidad > 999) {
							JOptionPane.showMessageDialog(null, "El valor no puede ser mayor a 999", "Informacion",
									JOptionPane.OK_OPTION);
						}
						if (cantidad <= 0) {
							JOptionPane.showMessageDialog(null, "El valor no puede ser menor a 0", "Informacion",
									JOptionPane.OK_OPTION);
						}
						if (cantidad > 0 && cantidad < 999) {
							generarPedido(prodSeleccionado, cantidad);
							repetir = false;
						}
					}
				}
			} catch (HeadlessException | NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor ingresado incorrecto", "Informacion",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	public void generarPedido(MaestroProductoDTO productoSeleccionado, int cantidad) {
		productoSeleccionado.setCantidadAReponer(cantidad);
		productoSeleccionado.setPuntoRepositorio(0);
		generarOrdenesFabricacion.crearOrdenFabricacion(idSucursal, productoSeleccionado);
		mostrarMensajeEmergente(
				"Orden generada exitosamente para el producto " + productoSeleccionado.getDescripcion());
	}

	public void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
		return;
	}
	
	
	public void pasarAEditarProducto() {
		int fila = this.ventanaGestionarProductos.getTablaProductos().getSelectedRow();
		if(fila==-1) {
			JOptionPane.showMessageDialog(ventanaGestionarProductos, "Debe seleccionar un producto");
			return;
		}
		MaestroProductoDTO p = this.productosEnTabla.get(fila);
		this.ventanaGestionarProductos.cerrar();
		this.controladorAltaProducto.setProductoAEditar(p);
		this.controladorAltaProducto.inicializar();
		this.controladorAltaProducto.mostrarVentanaEditar();		
	}
	
}
