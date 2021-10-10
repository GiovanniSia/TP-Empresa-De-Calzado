package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;

public class VentanaBusquedaProductos {

	private JFrame frame;
	
	private JTable tableProductosFiltrados;
	private DefaultTableModel modelTablaProductosFiltrados;
	private String[] nombreColumnasProductosFiltrados = { "Nombre", "Talle", "Precio","Stock Disp","Cod Lote"};
	private JScrollPane scrollPaneProductosFiltrados;

	private JTable tableCarrito;
	private DefaultTableModel modelTablaCarrito;
	private String[] nombreColumnasCarrito = { "Producto Elegido", "Cantidad", "Precio Total","Cod Lote","Talle"};
	private JScrollPane scrollPaneCarrito;
	
	

	private JTable tableCliente;
	private DefaultTableModel modelTablaCliente;
	private String[] nombreColumnasCliente = { "Nombre", "Apellido", "DNI", "Tipo de Cliente"};
	private JScrollPane scrollPaneCliente;
	
	private JLabel lblValorTotal;
	private JTextField txtNombreProducto;
	private JTextField txtTalle;
	private JLabel lblNombre;
	private JLabel lblTalle;
	private JButton btnAniadirProd;
	private JPanel panel_1;
	private JLabel lblTitulo;
	private JLabel lblSubtitulo;


	private JButton btnQuitarProducto;
	private JButton btnAtras;
	private JButton btnArmarVenta;
	

	private JLabel lblCantidad_1;
	private JLabel lblQuitarDelCarrito;
	private JLabel lblPrecioDesde;
	private JLabel lblPrecioHasta;

	SpinnerModel spinnerModelDesde;
	SpinnerModel spinnerModelHasta;
	
	private JSpinner spinnerCarrito;
	private JSpinner spinnerProductos;
	private JSpinner spinnerPrecioDesde;
	private JSpinner spinnerPrecioHasta;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBusquedaProductos window = new VentanaBusquedaProductos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the application.
	 */
	public VentanaBusquedaProductos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 953, 654);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Zapatería Argento - Realizar Venta");
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 96, 919, 419);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//Tabla productos filtrados
		modelTablaProductosFiltrados = new DefaultTableModel(null, nombreColumnasProductosFiltrados) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				if(columnas == 5) {
					return true;
				}else {
					return false;
				}
			}
		};
		
		scrollPaneProductosFiltrados = new JScrollPane(this.tableProductosFiltrados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneProductosFiltrados.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPaneProductosFiltrados.setBounds(10, 167, 548, 242);
		
		tableProductosFiltrados = new JTable(modelTablaProductosFiltrados);
		this.tableProductosFiltrados.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableProductosFiltrados.getColumnModel().getColumn(0).setResizable(false);
		scrollPaneProductosFiltrados.setViewportView(tableProductosFiltrados);
		
		panel.add(scrollPaneProductosFiltrados);
		
		txtNombreProducto = new JTextField();
		txtNombreProducto.setColumns(10);
		txtNombreProducto.setBounds(10, 138, 69, 19);
		panel.add(txtNombreProducto);		
		
		txtTalle = new JTextField();
		txtTalle.setColumns(10);
		txtTalle.setBounds(115, 138, 29, 19);
		panel.add(txtTalle);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 121, 69, 13);
		panel.add(lblNombre);
		
		lblTalle = new JLabel("Talle");
		lblTalle.setBounds(115, 121, 29, 13);
		panel.add(lblTalle);
		
		JLabel lblFiltrarPor = new JLabel("Filtrar Por:");
		lblFiltrarPor.setBounds(10, 100, 69, 13);
		panel.add(lblFiltrarPor);
		
		
		//BOTONES
		btnAniadirProd = new JButton();
		btnAniadirProd.setBounds(511, 115, 47, 42);
		btnAniadirProd.setIcon(setIcono("imagenes/cart.png",btnAniadirProd));
		panel.add(btnAniadirProd);
		
		btnQuitarProducto = new JButton("");
		btnQuitarProducto.setBounds(855, 23, 39, 37);
		btnQuitarProducto.setIcon(setIcono("imagenes/trash.png",btnQuitarProducto));
		panel.add(btnQuitarProducto);
		
		btnArmarVenta = new JButton("");
		btnArmarVenta.setForeground(Color.WHITE);
		btnArmarVenta.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		btnArmarVenta.setBounds(508, 522, 93, 63);
		btnArmarVenta.setIcon(setIcono("imagenes/dollar-symbol.png",btnArmarVenta));
		frame.getContentPane().add(btnArmarVenta);
		
		btnAtras = new JButton("");
		btnAtras.setBounds(20, 534, 51, 63);
		btnAtras.setIcon(setIcono("imagenes/back.png",btnAtras));
		frame.getContentPane().add(btnAtras);
		
		lblValorTotal = new JLabel("$0");
		lblValorTotal.setForeground(new Color(0, 100, 0));
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValorTotal.setBounds(776, 370, 133, 39);
		panel.add(lblValorTotal);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblTotal.setBounds(707, 370, 63, 39);
		panel.add(lblTotal);
		
		//Tabla carrito

		modelTablaCarrito = new DefaultTableModel(null, nombreColumnasCarrito) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				if(columnas == 5) {
					return true;
				}else {
					return false;
				}
			}
		};
		
		scrollPaneCarrito = new JScrollPane(this.tableCarrito, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCarrito.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPaneCarrito.setBounds(588, 70, 321, 284);
		panel.add(scrollPaneCarrito);
		
		tableCarrito = new JTable(modelTablaCarrito);
		
		this.tableCarrito.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCarrito.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneCarrito.setViewportView(tableCarrito);
				
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(588, 41, 55, 19);
		panel.add(lblCantidad);
		

		JLabel lblClienteSeleccionado = new JLabel("Cliente Seleccionado:");
		lblClienteSeleccionado.setBounds(10, 0, 134, 13);
		panel.add(lblClienteSeleccionado);

		
		
		//Tabla Cliente
		modelTablaCliente = new DefaultTableModel(null, nombreColumnasCliente) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				if(columnas == 4) {
					return true;
				}else {
					return false;
				}
			}
		};
		
		scrollPaneCliente = new JScrollPane(this.tableCliente, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCliente.setBounds(10, 23, 548, 42);
		
		tableCliente = new JTable(modelTablaCliente);
		this.tableCliente.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCliente.getColumnModel().getColumn(0).setResizable(false);
		scrollPaneCliente.setViewportView(tableCliente);
		
		panel.add(scrollPaneCliente);
		
		
		
		lblCantidad_1 = new JLabel("Cantidad");
		lblCantidad_1.setBounds(308, 118, 55, 19);
		panel.add(lblCantidad_1);
		
		lblQuitarDelCarrito = new JLabel("Quitar");
		lblQuitarDelCarrito.setBounds(806, 40, 39, 20);
		panel.add(lblQuitarDelCarrito);
		
		lblPrecioDesde = new JLabel("Precio desde");
		lblPrecioDesde.setBounds(154, 121, 77, 13);
		panel.add(lblPrecioDesde);
		
		lblPrecioHasta = new JLabel("Precio hasta");
		lblPrecioHasta.setBounds(218, 121, 77, 13);
		panel.add(lblPrecioHasta);
		

		
		JLabel lblAgregar = new JLabel("Agregar");
		lblAgregar.setBounds(462, 137, 39, 20);
		panel.add(lblAgregar);
		
		JLabel lblCarrito = new JLabel("Carrito de compras");
		lblCarrito.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblCarrito.setBounds(587, 0, 258, 31);
		panel.add(lblCarrito);
		
		//
		

		panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 919, 41);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblTitulo = new JLabel("Zapater\u00EDa");
		lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblTitulo.setBounds(10, 0, 195, 41);
		panel_1.add(lblTitulo);
		
		lblSubtitulo = new JLabel("Productos");
		lblSubtitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblSubtitulo.setBounds(20, 54, 195, 41);
		frame.getContentPane().add(lblSubtitulo);
		
		JLabel lblNewLabel = new JLabel("Confirmar pedido");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
		lblNewLabel.setBounds(221, 525, 356, 50);
		frame.getContentPane().add(lblNewLabel);
		
		//Spinners
		spinnerCarrito = new JSpinner();
		spinnerCarrito.setBounds(632, 41, 47, 19);
		panel.add(spinnerCarrito);
		
		spinnerProductos = new JSpinner();
		spinnerProductos.setBounds(316, 138, 47, 19);
		panel.add(spinnerProductos);
		
		
		spinnerModelDesde = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 100); //default value,lower bound,upper bound,increment by
		spinnerPrecioDesde = new JSpinner(spinnerModelDesde);
		spinnerPrecioDesde.setBounds(164, 138, 47, 19);
		panel.add(spinnerPrecioDesde);
		
		spinnerModelHasta = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 100); //default value,lower bound,upper bound,increment by
		spinnerPrecioHasta = new JSpinner(spinnerModelHasta);
		spinnerPrecioHasta.setBounds(218, 138, 47, 19);
		panel.add(spinnerPrecioHasta);
		//
	}
	
	
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?. Se perderá todos los progresos que se hayan realizado", 
		             "Advertencia", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		this.frame.setVisible(true);
	}

	public void cerrar() {
		frame.setVisible(false);
	}
	
	
	public Icon setIcono(String url,JButton boton) {
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
	}
	
	
	public JTextField getTxtNombreProducto() {
		return txtNombreProducto;
	}
//	public JTextField getTxtIdSucursal() {
//		return txtIdSucursal;
//	}
	public JTable getTable() {
		return tableProductosFiltrados;
	}
	public DefaultTableModel getModelTabla() {
		return modelTablaProductosFiltrados;
	}
	public String[] getNombreColumnas() {
		return nombreColumnasProductosFiltrados;
	}
	
	
	public JTable getTableProductosFiltrados() {
		return tableProductosFiltrados;
	}



	public DefaultTableModel getModelTablaProductosFiltrados() {
		return modelTablaProductosFiltrados;
	}



	public String[] getNombreColumnasProductosFiltrados() {
		return nombreColumnasProductosFiltrados;
	}



	public JTable getTableCarrito() {
		return tableCarrito;
	}



	public DefaultTableModel getModelTablaCarrito() {
		return modelTablaCarrito;
	}



	public String[] getNombreColumnasCarrito() {
		return nombreColumnasCarrito;
	}



	public JLabel getLblValorTotal() {
		return lblValorTotal;
	}



	public JTextField getTxtTalle() {
		return txtTalle;
	}

	public JButton getBtnAniadirProd() {
		return btnAniadirProd;
	}
	public JButton getBtnQuitarProducto() {
		return btnQuitarProducto;
	}



	public JButton getBtnAtras() {
		return btnAtras;
	}



	public JButton getBtnPasarAElegir() {
		return btnArmarVenta;
	}

	public JSpinner getSpinnerCarrito() {
		return spinnerCarrito;
	}
	
	public JTable getTableCliente() {
		return tableCliente;
	}



	public DefaultTableModel getModelTablaCliente() {
		return modelTablaCliente;
	}



	public String[] getNombreColumnasCliente() {
		return nombreColumnasCliente;
	}



	public JScrollPane getScrollPaneCliente() {
		return scrollPaneCliente;
	}

	public JSpinner getSpinnerProductos() {
		return spinnerProductos;
	}
	
	public JSpinner getSpinnerPrecioDesde() {
		return spinnerPrecioDesde;
	}

	public JSpinner getSpinnerPrecioHasta() {
		return spinnerPrecioHasta;
	}

	public SpinnerModel getSpinnerModelDesde() {
		return spinnerModelDesde;
	}
	public SpinnerModel getSpinnerModelHasta() {
		return spinnerModelHasta;
	}
	public void setSpinnerModelDesde(SpinnerModel spinnerModelDesde) {
		this.spinnerModelDesde = spinnerModelDesde;
	}
	public void setSpinnerModelHasta(SpinnerModel spinnerModelHasta) {
		this.spinnerModelHasta = spinnerModelHasta;
	}
	public void setSpinnerPrecioDesde(JSpinner spinnerPrecioDesde) {
		this.spinnerPrecioDesde = spinnerPrecioDesde;
	}
}

