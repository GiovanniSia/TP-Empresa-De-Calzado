package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
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
	private String[] nombreColumnasProductosFiltrados = { "Nombre", "Talle", "Precio Venta","Stock Disp","Cod Lote"};
	private JScrollPane scrollPaneProductosFiltrados;

	private JTable tableCarrito;
	private DefaultTableModel modelTablaCarrito;
	private String[] nombreColumnasCarrito = { "Producto Elegido", "Cantidad", "Precio Total"};
	private JScrollPane scrollPaneCarrito;
	
	private JLabel lblValorTotal;
	private JTextField txtNombreProducto;
	private JTextField txtTalle;
	private JLabel lblNombre;
	private JLabel lblTalle;
	private JComboBox comboBoxCategoria;
	private JLabel lblCategoria;
	private JButton btnAniadirProd;
	private JLabel lblAniadir;
	private JPanel panel_1;
	private JLabel lblTitulo;
	private JLabel lblSubtitulo;
	private JLabel lbl_quitarDeCarrito;


	private JButton btnQuitarProducto;
	private JButton btnAtras;
	private JButton btnPasarAElegir;
	
	private JSpinner spinner;


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
		frame.setTitle("Zapatería Argento - Realizar Venta");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		modelTablaProductosFiltrados = new DefaultTableModel(null, nombreColumnasProductosFiltrados);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 96, 919, 419);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//Tabla productos filtrados
		scrollPaneProductosFiltrados = new JScrollPane(this.tableProductosFiltrados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneProductosFiltrados.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPaneProductosFiltrados.setBounds(10, 70, 548, 339);
		panel.add(scrollPaneProductosFiltrados);
		tableProductosFiltrados = new JTable(modelTablaProductosFiltrados);
		
		this.tableProductosFiltrados.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableProductosFiltrados.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneProductosFiltrados.setViewportView(tableProductosFiltrados);
		
		txtNombreProducto = new JTextField();
		txtNombreProducto.setColumns(10);
		txtNombreProducto.setBounds(188, 41, 69, 19);
		panel.add(txtNombreProducto);		
		
		txtTalle = new JTextField();
		txtTalle.setColumns(10);
		txtTalle.setBounds(283, 41, 29, 19);
		panel.add(txtTalle);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(188, 23, 69, 13);
		panel.add(lblNombre);
		
		lblTalle = new JLabel("Talle");
		lblTalle.setBounds(283, 23, 29, 13);
		panel.add(lblTalle);
		
		JLabel lblFiltrarPor = new JLabel("Filtrar Por:");
		lblFiltrarPor.setBounds(10, 0, 69, 13);
		panel.add(lblFiltrarPor);
		
		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(52, 41, 113, 19);
		comboBoxCategoria.addItem("Sin seleccionar");
		panel.add(comboBoxCategoria);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setToolTipText("Categoria");
		lblCategoria.setBounds(74, 23, 76, 13);
		panel.add(lblCategoria);
		
		btnAniadirProd = new JButton("->");
		btnAniadirProd.setBounds(447, 40, 55, 21);
		panel.add(btnAniadirProd);
		
		lblAniadir = new JLabel("A\u00F1adir");
		lblAniadir.setBounds(459, 23, 39, 13);
		panel.add(lblAniadir);
		
		lblValorTotal = new JLabel("$0");
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValorTotal.setBounds(776, 370, 133, 39);
		panel.add(lblValorTotal);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 17));
		lblTotal.setBounds(653, 370, 113, 39);
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
		
		spinner = new JSpinner();
		spinner.setBounds(779, 41, 47, 19);
		panel.add(spinner);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(711, 41, 55, 19);
		panel.add(lblCantidad);
		
		btnQuitarProducto = new JButton("<-");
		btnQuitarProducto.setBounds(614, 40, 55, 21);
		panel.add(btnQuitarProducto);
		
		lbl_quitarDeCarrito = new JLabel("Quitar del carrito");
		lbl_quitarDeCarrito.setBounds(614, 23, 113, 13);
		panel.add(lbl_quitarDeCarrito);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Precio Mayorista");
		rdbtnNewRadioButton.setBounds(327, 40, 103, 21);
		panel.add(rdbtnNewRadioButton);
		//
		
		
		btnAtras = new JButton("< Atr\u00E1s");
		btnAtras.setBounds(20, 534, 93, 28);
		frame.getContentPane().add(btnAtras);
		
		btnPasarAElegir = new JButton("Pasar a elegir cliente");
		btnPasarAElegir.setFont(new Font("Consolas", Font.PLAIN, 28));
		btnPasarAElegir.setBounds(211, 527, 419, 63);
		frame.getContentPane().add(btnPasarAElegir);
		
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
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
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







	public JComboBox getComboBoxCategoria() {
		return comboBoxCategoria;
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
		return btnPasarAElegir;
	}

	public JSpinner getSpinner() {
		return spinner;
	}
}

