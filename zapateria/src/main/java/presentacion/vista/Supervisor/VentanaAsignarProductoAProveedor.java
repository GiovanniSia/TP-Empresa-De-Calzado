package presentacion.vista.Supervisor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VentanaAsignarProductoAProveedor {

	private JFrame frame;
	
	private JTable tableProvElegido;
	private DefaultTableModel modelTablaProvElegido;
	private String[] nombreColumnasProvElegido = {"Nombre","Correo","Limite de Credito"};
	
	
	private JTable tableProdDeProv;
	private DefaultTableModel modelTablaProdDeProv;
	private String[] nombreColumnasProdDeProv = {"Descripcion","Tipo","Costo de produccion","Precio Mayorista","Precio Minorista","Punto de Rep minimo","Talle","Precio Venta","Cantidad por Lote"};
	
	private JTable tableTodosLosProd;
	private DefaultTableModel modelTablaProductos;
	private String[] nombreColumnasProductos = {"Descripcion","Tipo","Costo de produccion","Precio Mayorista","Precio Minorista","Punto de Rep minimo","Talle"};
	private JTextField textNombre;
	
	JButton btnSalir;
	JButton btnQuitar;
	JButton btnAgregar;
	JButton btnModificarCantidadPorLote;


	private JTextField textPrecioVenta;
	private JTextField textCantPorLote;

	private JButton btnModificarPrecioDe;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAsignarProductoAProveedor window = new VentanaAsignarProductoAProveedor();
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
	public VentanaAsignarProductoAProveedor() {
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
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(150, 10, 1221, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
//		panel.add(scrollPaneFrame);		
		/*
		JScrollPane scrollPaneTodosLosProd = new JScrollPane(this.tableTodosLosProd,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTodosLosProd.setBounds(10, 273, 1116, 168);
		
		tableTodosLosProd = new JTable(modelTablaProductos);
		tableTodosLosProd.setBounds(10, 535, 1126, 168);
		tableTodosLosProd.getTableHeader().setReorderingAllowed(false) ;
		scrollPaneTodosLosProd.setViewportView(tableTodosLosProd);
		frame.getContentPane().add(scrollPaneTodosLosProd);		
		
		*/
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		this.modelTablaProvElegido = new DefaultTableModel(null,this.nombreColumnasProvElegido){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};
		
		
		////////////////////////////////////////////////////////////////////////////////////////////
		
		
		modelTablaProdDeProv = new DefaultTableModel(null,this.nombreColumnasProdDeProv){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};
		
////////////////////////////////////////////////////////////////////////////////////////////
		
		modelTablaProductos = new DefaultTableModel(null,this.nombreColumnasProductos){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1205, 571);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(248, 248, 255));
		// panel.setBounds(10, 0, 1126, 705);
		// frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1279, 600));
		JLabel lblAsignarProductoAProv = new JLabel("Asignar Producto a un proveedor");
		lblAsignarProductoAProv.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblAsignarProductoAProv.setBounds(10, 56, 1080, 46);
		// frame.getContentPane().add(lblAsignarProductoAProv);
		panel.add(lblAsignarProductoAProv);

		JLabel lblNewLabel = new JLabel("Proveedor elegido");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 97, 560, 46);
		// frame.getContentPane().add(lblNewLabel);
		panel.add(lblNewLabel);

		JLabel lblProductosDelProveedor = new JLabel("Productos del proveedor");
		lblProductosDelProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblProductosDelProveedor.setBounds(10, 366, 348, 30);
		// frame.getContentPane().add(lblProductosDelProveedor);
		panel.add(lblProductosDelProveedor);

		JLabel lblListaDeProductos = new JLabel("Lista de Productos");
		lblListaDeProductos.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblListaDeProductos.setBounds(498, 109, 322, 30);
		// frame.getContentPane().add(lblListaDeProductos);
		panel.add(lblListaDeProductos);
		JScrollPane scrollPaneProvElegido = new JScrollPane(this.tableProvElegido,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneProvElegido.setBounds(10, 138, 470, 50);

		tableProvElegido = new JTable(modelTablaProvElegido);
		tableProvElegido.setBackground(Color.WHITE);
		tableProvElegido.setBounds(10, 106, 1116, 97);
		tableProvElegido.getTableHeader().setReorderingAllowed(false);
		scrollPaneProvElegido.setViewportView(tableProvElegido);
		// frame.getContentPane().add(scrollPaneProvElegido);
		panel.add(scrollPaneProvElegido);
		JScrollPane scrollPaneProdDeProv = new JScrollPane(this.tableProdDeProv,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneProdDeProv.setBounds(10, 395, 1185, 168);

		tableProdDeProv = new JTable(modelTablaProdDeProv);
		tableProdDeProv.setBounds(10, 252, 1116, 208);
		tableProdDeProv.getTableHeader().setReorderingAllowed(false);
		scrollPaneProdDeProv.setViewportView(tableProdDeProv);
		// frame.getContentPane().add(scrollPaneProdDeProv);
		panel.add(scrollPaneProdDeProv);

		JScrollPane scrollPaneTodosLosProd = new JScrollPane(this.tableTodosLosProd,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTodosLosProd.setBounds(498, 184, 698, 168);

		tableTodosLosProd = new JTable(modelTablaProductos);
		tableTodosLosProd.setBounds(10, 535, 1126, 168);
		tableTodosLosProd.getTableHeader().setReorderingAllowed(false);
		scrollPaneTodosLosProd.setViewportView(tableTodosLosProd);
		// frame.getContentPane().add(scrollPaneTodosLosProd);
		panel.add(scrollPaneTodosLosProd);

		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(498, 150, 96, 25);
		// frame.getContentPane().add(lblNewLabel_1);
		panel.add(lblNewLabel_1);

		textNombre = new JTextField();
		textNombre.setBounds(604, 154, 132, 19);
		// frame.getContentPane().add(textNombre);
		panel.add(textNombre);
		textNombre.setColumns(10);

		btnAgregar = new JButton("");
		btnAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAgregar.setBounds(1116, 123, 50, 50);
		cambiarIconoBotones(btnAgregar, "plus.png");
		// frame.getContentPane().add(btnAgregar);
		panel.add(btnAgregar);

		btnQuitar = new JButton("Quitar");
		btnQuitar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuitar.setBounds(1070, 359, 126, 25);
		// frame.getContentPane().add(btnQuitar);
		panel.add(btnQuitar);

		btnSalir = new JButton("");
		btnSalir.setBounds(182, 257, 60, 60);
		cambiarIconoBotones(btnSalir, "back2.png");
		// frame.getContentPane().add(btnSalir);
		panel.add(btnSalir);

		textPrecioVenta = new JTextField();
		textPrecioVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textPrecioVenta.getText().length() >= 20) {
					e.consume();
				}
			}
		});
		textPrecioVenta.setBounds(889, 154, 96, 19);
		// frame.getContentPane().add(textPrecioVenta);
		panel.add(textPrecioVenta);
		textPrecioVenta.setColumns(10);

		textCantPorLote = new JTextField();
		textCantPorLote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textCantPorLote.getText().length() >= 20) {
					e.consume();
				}
			}
		});
		textCantPorLote.setColumns(10);
		textCantPorLote.setBounds(1010, 153, 96, 19);
		// frame.getContentPane().add(textCantPorLote);
		panel.add(textCantPorLote);

		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPrecioVenta.setBounds(889, 130, 89, 13);
		// frame.getContentPane().add(lblPrecioVenta);
		panel.add(lblPrecioVenta);

		JLabel lblCantidadPorLote = new JLabel("Cantidad por lote");
		lblCantidadPorLote.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCantidadPorLote.setBounds(1010, 130, 96, 13);
		// frame.getContentPane().add(lblCantidadPorLote);
		panel.add(lblCantidadPorLote);

		btnModificarCantidadPorLote = new JButton("Modificar Cantidad por Lote");
		btnModificarCantidadPorLote.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnModificarCantidadPorLote.setBounds(786, 359, 274, 25);
		// frame.getContentPane().add(btnModificarCantidadPorLote);
		panel.add(btnModificarCantidadPorLote);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(0, 0, 1269, 50);
		panel.add(panel_1);

		JLabel lblNewLabel_2 = new JLabel("Zapateria Argento");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_2.setBounds(10, 0, 236, 50);
		panel_1.add(lblNewLabel_2);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblDescripcion.setBounds(604, 141, 89, 13);
		panel.add(lblDescripcion);

		JLabel lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblAtras.setBounds(252, 257, 50, 60);
		panel.add(lblAtras);
		
		btnModificarPrecioDe = new JButton("Modificar Precio de Venta");
		btnModificarPrecioDe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnModificarPrecioDe.setBounds(508, 359, 274, 25);
		panel.add(btnModificarPrecioDe);

	}


	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?", 
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
	
	public JFrame getFrame() {
		return frame;
	}

	public JTable getTableProvElegido() {
		return tableProvElegido;
	}

	public DefaultTableModel getModelTablaProvElegido() {
		return modelTablaProvElegido;
	}

	public String[] getNombreColumnasProvElegido() {
		return nombreColumnasProvElegido;
	}

	public JTable getTableProdDeProv() {
		return tableProdDeProv;
	}

	public DefaultTableModel getModelTablaProdDeProv() {
		return modelTablaProdDeProv;
	}

	public String[] getNombreColumnasProdDeProv() {
		return nombreColumnasProdDeProv;
	}

	public JTable getTableTodosLosProd() {
		return tableTodosLosProd;
	}

	public DefaultTableModel getModelTablaProductos() {
		return modelTablaProductos;
	}

	public String[] getNombreColumnasProductos() {
		return nombreColumnasProductos;
	}

	public JTextField getTextNombre() {
		return textNombre;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}
	public JTextField getTextPrecioVenta() {
		return textPrecioVenta;
	}

	public JTextField getTextCantPorLote() {
		return textCantPorLote;
	}
	
	
	public JButton getBtnModificarCantidadPorLote() {
		return btnModificarCantidadPorLote;
	}	
	public JButton getBtnModificarPrecioDe() {
		return btnModificarPrecioDe;
	}
	
}
