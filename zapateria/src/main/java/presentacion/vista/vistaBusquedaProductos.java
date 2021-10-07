package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;

public class vistaBusquedaProductos {

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
	private JLabel lblAniadir;
	private JPanel panel_1;
	private JLabel lblTitulo;
	private JLabel lblSubtitulo;


	private JButton btnQuitarProducto;
	private JButton btnAtras;
	private JButton btnArmarVenta;
	
	private JSpinner spinnerCarrito;
	private JSpinner spinnerProductos;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaBusquedaProductos window = new vistaBusquedaProductos();
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
	public vistaBusquedaProductos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 953, 654);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Zapater�a Argento - Realizar Venta");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 96, 919, 419);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//Tabla productos filtrados
		modelTablaProductosFiltrados = new DefaultTableModel(null, nombreColumnasProductosFiltrados);
		
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
		
		btnAniadirProd = new JButton("->");
		btnAniadirProd.setBounds(443, 136, 55, 21);
		panel.add(btnAniadirProd);
		
		lblAniadir = new JLabel("A\u00F1adir");
		lblAniadir.setBounds(443, 121, 39, 13);
		panel.add(lblAniadir);
		
		lblValorTotal = new JLabel("$0");
		lblValorTotal.setForeground(new Color(0, 100, 0));
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValorTotal.setBounds(776, 370, 133, 39);
		panel.add(lblValorTotal);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 17));
		lblTotal.setBounds(711, 370, 55, 39);
		panel.add(lblTotal);
		
		//Tabla carrito

		modelTablaCarrito = new DefaultTableModel(null, nombreColumnasCarrito);
		
		scrollPaneCarrito = new JScrollPane(this.tableCarrito, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCarrito.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPaneCarrito.setBounds(588, 70, 321, 284);
		panel.add(scrollPaneCarrito);
		
		tableCarrito = new JTable(modelTablaCarrito);
		
		this.tableCarrito.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCarrito.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneCarrito.setViewportView(tableCarrito);
		
		spinnerCarrito = new JSpinner();
		spinnerCarrito.setBounds(632, 41, 47, 19);
		panel.add(spinnerCarrito);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(588, 41, 55, 19);
		panel.add(lblCantidad);
		
		btnQuitarProducto = new JButton("Quitar del carrito");
		btnQuitarProducto.setBounds(776, 39, 133, 21);
		panel.add(btnQuitarProducto);
		
		JLabel lblClienteSeleccionado = new JLabel("Cliente Seleccionado:");
		lblClienteSeleccionado.setBounds(10, 0, 134, 13);
		panel.add(lblClienteSeleccionado);

		
		
		//Tabla Cliente
		modelTablaCliente = new DefaultTableModel(null, nombreColumnasCliente);
		
		scrollPaneCliente = new JScrollPane(this.tableCliente, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCliente.setBounds(10, 23, 548, 42);
		
		tableCliente = new JTable(modelTablaCliente);
		this.tableCliente.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCliente.getColumnModel().getColumn(0).setResizable(false);
		scrollPaneCliente.setViewportView(tableCliente);
		
		panel.add(scrollPaneCliente);
		
		spinnerProductos = new JSpinner();
		spinnerProductos.setBounds(386, 138, 47, 19);
		panel.add(spinnerProductos);
		
		//
		
		btnAtras = new JButton("< Atr\u00E1s");
		btnAtras.setBounds(20, 534, 93, 28);
		frame.getContentPane().add(btnAtras);
		
		btnArmarVenta = new JButton("Armar Venta");
		btnArmarVenta.setFont(new Font("Consolas", Font.PLAIN, 28));
		btnArmarVenta.setBounds(211, 527, 419, 63);
		frame.getContentPane().add(btnArmarVenta);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 919, 41);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblTitulo = new JLabel("Zapater\u00EDa");
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTitulo.setBounds(10, 10, 195, 21);
		panel_1.add(lblTitulo);
		
		lblSubtitulo = new JLabel("Productos");
		lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 27));
		lblSubtitulo.setBounds(20, 61, 195, 21);
		frame.getContentPane().add(lblSubtitulo);
	}
	
	
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "�Est�s seguro que quieres salir?. Se perder� todos los progresos que se hayan realizado", 
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

	public JSpinner getSpinner() {
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
	
}

