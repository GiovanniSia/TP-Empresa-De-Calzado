package presentacion.vista.generarPedidoProveedor;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class VentanaGenerarPedidoProveedor {

	private JFrame frame;
	private JLabel lblNombreEmpleado;
	private JLabel lblNombreSucursal;
	private JTable tablaProducto;
	private String[] nombreColumnasProducto = {"Id Producto","Descripcion","Tipo","Prod. Propio","Costo prod.","Precio Mayorista","Precio Minorista","Punto de rep. minimo","Id Prov","Talle","Medida","Estado","Cant a rep","Dias para rep.","Stock disp","Cod. lote"};
	private DefaultTableModel modelProducto;
	
	private JTable tablaProveedores;
	private DefaultTableModel modelProveedores;
	private String[] nombreColumnasProveedores = {"Id","Nombre","Correo","Credito Disponible","Precio Venta","Cant. Prod. X lote"};

	private JRadioButton rdbtnProveedoresPref;
	private JRadioButton rdbtnTodosLosProveedores;
	private JSpinner spinnerCantARep;
	private JButton btnGenerarPedido;
	private JButton btnAtras;
	
	SpinnerModel spinnerModelCantARep;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGenerarPedidoProveedor window = new VentanaGenerarPedidoProveedor();
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
	public VentanaGenerarPedidoProveedor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 735, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 719, 53);
		frame.getContentPane().add(panel_2);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel_2.add(lblLogo);
		
		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(468, 28, 59, 19);
		panel_2.add(lblSucursal);
		
		JLabel lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEmpleado.setBounds(225, 28, 59, 19);
		panel_2.add(lblEmpleado);
		
		lblNombreEmpleado = new JLabel("");
		lblNombreEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombreEmpleado.setBounds(296, 28, 183, 19);
		panel_2.add(lblNombreEmpleado);
		
		lblNombreSucursal = new JLabel("");
		lblNombreSucursal.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombreSucursal.setBounds(537, 28, 172, 19);
		panel_2.add(lblNombreSucursal);
		
		JLabel lblNewLabel = new JLabel("Pedido a Proveedor");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 64, 284, 39);
		frame.getContentPane().add(lblNewLabel);
				
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(0, 106, 719, 364);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Producto Elegido:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 11, 161, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Elegir Proveedor");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(21, 122, 124, 22);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Cantidad a Reponer");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(21, 314, 149, 22);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("(datos producto)");
		lblNewLabel_3.setBounds(322, 18, 83, 14);
		panel.add(lblNewLabel_3);
		
		rdbtnProveedoresPref = new JRadioButton("Proveedores preferenciados");
		rdbtnProveedoresPref.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnProveedoresPref.setBounds(359, 124, 173, 23);
		panel.add(rdbtnProveedoresPref);
		
		rdbtnTodosLosProveedores = new JRadioButton("Todos los proveedores");
		rdbtnTodosLosProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnTodosLosProveedores.setBounds(534, 124, 161, 23);
		panel.add(rdbtnTodosLosProveedores);
		
		
		spinnerModelCantARep = new SpinnerNumberModel(1, 1, 999, 1); //default value,lower bound,upper bound,increment by
		spinnerCantARep = new JSpinner(spinnerModelCantARep);
		spinnerCantARep.setBounds(180, 318, 83, 22);
//		spinnerCantARep.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				if ((int)spinnerCantARep.getValue() >= 15) {
//					e.consume();
//				}
//			}
//		});
		
		panel.add(spinnerCantARep);
		
		JLabel lblNewLabel_3_2 = new JLabel("(al menos 1 y hasta 999)");
		lblNewLabel_3_2.setBounds(273, 321, 124, 14);
		panel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("(elegir qu\u00E9 proveedores mostrar: los del producto o todos)");
		lblNewLabel_3_1_1.setBounds(375, 104, 334, 14);
		panel.add(lblNewLabel_3_1_1);
		
		
		btnGenerarPedido = new JButton("Generar Pedido");
		btnGenerarPedido.setBounds(463, 481, 132, 23);
		frame.getContentPane().add(btnGenerarPedido);
		
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(113, 481, 132, 23);
		frame.getContentPane().add(btnAtras);
		
		
		
		JScrollPane scrollPaneProductoElegido = new JScrollPane();
		scrollPaneProductoElegido.setBounds(21, 32, 688, 62);
		
		this.modelProducto = new DefaultTableModel(null,nombreColumnasProducto) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				return false;
			}	
		};
		
		tablaProducto = new JTable(modelProducto);
		tablaProducto.setBounds(21, 32, 688, 62);
		
		tablaProducto.getColumnModel().getColumn(5).setPreferredWidth(100);
		tablaProducto.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablaProducto.getColumnModel().getColumn(7).setPreferredWidth(130);
		
		scrollPaneProductoElegido.setViewportView(tablaProducto);
		tablaProducto.getTableHeader().setReorderingAllowed(false);
		tablaProducto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaProducto.doLayout();
		panel.add(scrollPaneProductoElegido);
		

////////////////////////////////////////////////////////////////////////
		JScrollPane scrollPaneProveedores = new JScrollPane();
		scrollPaneProveedores.setBounds(21, 154, 688, 150);
		
		this.modelProveedores = new DefaultTableModel(null,nombreColumnasProveedores) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				return false;
			}	
		};
		
		tablaProveedores = new JTable(modelProveedores);
		tablaProveedores.setBounds(21, 154, 688, 150);
		
		tablaProveedores.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		scrollPaneProveedores.setViewportView(tablaProveedores);
		tablaProveedores.getTableHeader().setReorderingAllowed(false);
//		tablaProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaProveedores.doLayout();
		
		panel.add(scrollPaneProveedores);
		
	
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 48, 720, 540);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
	}
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estas seguro que quieres salir?", "Advertencia",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
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

	public void mostrarVentana() {
		frame.setVisible(true);
	}
	

	public JFrame getFrame() {
		return frame;
	}

	public JLabel getLblNombreEmpleado() {
		return lblNombreEmpleado;
	}

	public JLabel getLblNombreSucursal() {
		return lblNombreSucursal;
	}

	public JTable getTablaProducto() {
		return tablaProducto;
	}

	public String[] getNombreColumnasProducto() {
		return nombreColumnasProducto;
	}

	public DefaultTableModel getModelProducto() {
		return modelProducto;
	}

	public JTable getTablaProveedores() {
		return tablaProveedores;
	}

	public DefaultTableModel getModelProveedores() {
		return modelProveedores;
	}

	public String[] getNombreColumnasProveedores() {
		return nombreColumnasProveedores;
	}

	public JRadioButton getRdbtnProveedoresPref() {
		return rdbtnProveedoresPref;
	}

	public JRadioButton getRdbtnTodosLosProveedores() {
		return rdbtnTodosLosProveedores;
	}

	public JSpinner getSpinnerCantARep() {
		return spinnerCantARep;
	}

	public JButton getBtnGenerarPedido() {
		return btnGenerarPedido;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}
}
