package presentacion.controlador.supervisor;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.View;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import modelo.Cliente;
import modelo.Localidad;
import modelo.MaestroProducto;
import modelo.Pais;
import modelo.PedidosPendientes;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import modelo.Provincia;
import modelo.Stock;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.controlador.gerente.ControladorAltaCliente;
import presentacion.vista.Supervisor.VentanaGestionarProductos;

public class ControladorGestionarProductos {
	
	public final int idSucursal=1;
	
	MaestroProducto maestroProducto;
	Stock stock;
	
	List<MaestroProductoDTO> todosLosProductos;
	List<StockDTO> todoElStock;
	
	List<MaestroProductoDTO> productosEnTabla;
	
	
	VentanaGestionarProductos ventanaGestionarProductos;
	
	Controlador controlador;
	ControladorAltaProducto controladorAltaProducto;
	
	ControladorGenerarPedidoAProveedorManualmente controladorGenerarPedidoAProveedorManualmente;
	
	
	public ControladorGestionarProductos(Controlador controlador,MaestroProducto maestroProducto,Stock stock) {
		this.maestroProducto = maestroProducto;
		this.stock = stock;
		
		this.controlador=controlador;
		
		this.todoElStock = new ArrayList<StockDTO>();
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.productosEnTabla = new ArrayList<MaestroProductoDTO>();
		this.ventanaGestionarProductos = new VentanaGestionarProductos();
		

		
	}
	

	public ControladorGestionarProductos(MaestroProducto maestroProducto,Stock stock) {
		this.maestroProducto = maestroProducto;
		this.stock = stock;
			
		this.todoElStock = new ArrayList<StockDTO>();
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
		this.productosEnTabla = new ArrayList<MaestroProductoDTO>();
		
		
	}
	

	public void setControladorAltaProducto(ControladorAltaProducto controladorAltaProducto) {
		this.controladorAltaProducto = controladorAltaProducto;
	}

	public void setControladorGenerarPedidoAProveedorManualmente(ControladorGenerarPedidoAProveedorManualmente controladorGenerarPedidoAProveedorManualmente) {
		this.controladorGenerarPedidoAProveedorManualmente = controladorGenerarPedidoAProveedorManualmente;
	}
	
	public void inicializar() {
		this.todosLosProductos = this.maestroProducto.readAll();
		this.todoElStock = this.stock.readAll();
		

		this.ventanaGestionarProductos = new VentanaGestionarProductos();
		
		this.ventanaGestionarProductos.getBtnAgregarProducto().addActionListener(a -> pasarAAgregarProducto(a));
		
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
		
		this.ventanaGestionarProductos.getChckbxProdSinStock().addActionListener(a -> realizarBusqueda());
		
		
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
		
		this.ventanaGestionarProductos.getChckbxProdSinStock().addActionListener(a -> realizarBusqueda());
		
		this.ventanaGestionarProductos.getBtnGenerarPedido().addActionListener(a -> pasarAGenerarPedido());
		
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
	}
	
	public void mostrarVentana() {
		this.ventanaGestionarProductos.show();	
	}
	
	public void volverAtras(ActionEvent a) {
		this.ventanaGestionarProductos.cerrar();
		this.controlador.inicializar();
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
		List<MaestroProductoDTO> productosAproximados = this.maestroProducto.getMaestroProductoAproximado("Descripcion",txtNombre,"Talle",txtTalle, null, null, null, null);

		escribirTabla(productosAproximados);
	}
	
	
	public void escribirTablaCompleta() {
		this.ventanaGestionarProductos.getModelProductos().setRowCount(0);//borrar datos de la tabla
		this.ventanaGestionarProductos.getModelProductos().setColumnCount(0);
		this.ventanaGestionarProductos.getModelProductos().setColumnIdentifiers(this.ventanaGestionarProductos.getNombreColumnas());
		productosEnTabla.removeAll(productosEnTabla);
		for(MaestroProductoDTO m: this.todosLosProductos) {
			for(StockDTO s: this.todoElStock) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal()) {
					agregarATabla(s,m);
				}
			}
		}
	}
	
	public void escribirTabla(List<MaestroProductoDTO> productosAproximados) {
		this.ventanaGestionarProductos.getModelProductos().setRowCount(0);//borrar datos de la tabla
		this.ventanaGestionarProductos.getModelProductos().setColumnCount(0);
		this.ventanaGestionarProductos.getModelProductos().setColumnIdentifiers(this.ventanaGestionarProductos.getNombreColumnas());
		productosEnTabla.removeAll(productosEnTabla);
		
		for(MaestroProductoDTO m: productosAproximados) {
			boolean tieneStock = false;
			for(StockDTO s: this.todoElStock){
				
				tieneStock = tieneStock || m.getIdMaestroProducto() == s.getIdProducto();		
				
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal()) {
					agregarATabla(s,m);

				}
				//si tiene stock entonces ya se agrego
			}	
			
			if(!tieneStock && this.ventanaGestionarProductos.getChckbxProdSinStock().isSelected()) {
				agregarATabla(null,m);
			}
			tieneStock=false;
		}
	}
	
	public void agregarATabla(StockDTO s, MaestroProductoDTO m) {
		int id = m.getIdMaestroProducto();
		String nombre=m.getDescripcion();
		String tipo = m.getTipo();
		String propio = m.getFabricado();
		
		double costoProd = m.getPrecioCosto();
		BigDecimal costoProduccion = new BigDecimal(costoProd);
		
		double precioM = m.getPrecioMayorista();
		BigDecimal precioMayo = new BigDecimal(precioM);
		
		double precioMi = m.getPrecioMinorista();
		BigDecimal precioMino = new BigDecimal(precioMi);
		
		int puntoRepMin = m.getPuntoRepositorio();
		
		int idProv = m.getIdProveedor();
		
		String talle = m.getTalle();
		String medida = m.getUnidadMedida();
		String estado = m.getEstado();
		
		int cantARep = m.getCantidadAReponer();
		int diasARep = m.getDiasParaReponer();

		int stockDisp;
		String codLote;
		if(s!=null) {
			stockDisp = s.getStockDisponible();
			codLote = s.getCodigoLote();	
		}else {
			stockDisp = 0;
			codLote = "Sin asignar";
		}
		
		Object[] fila = { id,nombre,tipo,propio,costoProduccion,precioMayo,precioMino,puntoRepMin,idProv,talle,medida,estado,cantARep,diasARep,stockDisp,codLote};
		this.ventanaGestionarProductos.getModelProductos().addRow(fila);
		productosEnTabla.add(m);
		
		verificarCambiarColorAEstaFila();
		
		
	}
	
	private void verificarCambiarColorAEstaFila() {
		this.ventanaGestionarProductos.getTablaProductos().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				//al parecer el row toma la ultima fila agregada
		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		        int status = (int) table.getModel().getValueAt(row,14);
		        if(status<=0) {
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
		if(fila==-1) {
			return;
		}
		cambiarColorFila();
			
	}
	
	public void cambiarColorFila() {
		this.ventanaGestionarProductos.getTablaProductos().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		        
		        if(isSelected) {    
//		        	if((int)table.getValueAt(row, 14) > 0) {
//		        		setBackground(Color.lightGray);
//			        	setForeground(Color.WHITE);	
//		        	}
		        	
		        }else {
		        	if((int)table.getValueAt(row, 14) == 0) {
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
		if(fila==-1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un producto primero", "Error", JOptionPane.OK_OPTION);
			return;
		}
		MaestroProductoDTO productoSeleccionado = this.productosEnTabla.get(fila);
		if(productoSeleccionado.getFabricado().equals("S")) {
			JOptionPane.showMessageDialog(null, "El producto es propio. Imposible generar orden a proveedor", "Error", JOptionPane.OK_OPTION);
			return;	
		}
		StockDTO stockDeProd=null;
		for(StockDTO s: this.todoElStock) {
			if(s.getIdProducto() == productoSeleccionado.getIdMaestroProducto()) {
				stockDeProd = s;
			}
		}
		
		
		this.controladorGenerarPedidoAProveedorManualmente.setProductoElegido(productoSeleccionado,stockDeProd);
		this.ventanaGestionarProductos.cerrar();
		this.controladorGenerarPedidoAProveedorManualmente.inicializar();
		this.controladorGenerarPedidoAProveedorManualmente.mostrarVentana();
	}
	
	
	public static void main(String[] args) {
		MaestroProducto m = new MaestroProducto(new DAOSQLFactory());
		Stock stock = new Stock(new DAOSQLFactory());
		Cliente cliente = new Cliente(new DAOSQLFactory());
	
		Proveedor prov = new Proveedor(new DAOSQLFactory());
		ProductoDeProveedor prodProv = new ProductoDeProveedor(new DAOSQLFactory());
		PedidosPendientes pedi = new PedidosPendientes(new DAOSQLFactory());
		
		ControladorAltaProducto alta = new ControladorAltaProducto(m, prov, prodProv); 
		
		
		
		ControladorConsultarProveedor consultar = new ControladorConsultarProveedor(prov,prodProv);
		
		ControladorGenerarPedidoAProveedorManualmente controladorGenerarPedidoAProveedorManualmente = new ControladorGenerarPedidoAProveedorManualmente(prov,stock,pedi, prodProv);
		ControladorGestionarProductos g = new ControladorGestionarProductos(m, stock);
		g.setControladorGenerarPedidoAProveedorManualmente(controladorGenerarPedidoAProveedorManualmente);
		g.setControladorAltaProducto(alta);
		alta.setControladorGestionarProductos(g);
		alta.setControladorConsultarProveedor(consultar);
		controladorGenerarPedidoAProveedorManualmente.setControladorGestionarProductos(g);
		consultar.setControladorAltaProducto(alta);
		consultar.setControladorAsignarProductoAProveedor(null);
		g.inicializar();
		g.mostrarVentana();
	}
	
}
