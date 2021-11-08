package presentacion.vista.Supervisor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;
import javax.swing.JCheckBox;

public class VentanaGestionarProductos extends JFrame {

private static final long serialVersionUID = 1L;
	
	private JFrame frame;

	private String[] nombreColumnas = {"Id Producto","Descripcion","Tipo","Prod. Propio","Costo prod.","Precio Mayorista","Precio Minorista","Punto de rep. minimo","Id Prov","Talle","Medida","Estado","Cant a rep","Dias para rep.","Stock disp","Cod. lote"};
	private JLabel lblNombre;
	private JTextField txtFieldNombre;
	private DefaultTableModel modelProductos;
	private JTable tablaProductos;
	private JLabel lblFiltrarPor;
	private JLabel lblTitulo;
	private JScrollPane spProductos;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JButton btnAtras;
	private JLabel lblAtrs;
	private JButton btnAgregarProducto;
	private JTextField textTalle;

	private JCheckBox chckbxProdSinStock;
	JButton btnGenerarPedido;
	JButton btnGenerarOrdenDeManufactura;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestionarProductos window = new VentanaGestionarProductos();
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaGestionarProductos() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 932, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 191, 908, 351);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spProductos = new JScrollPane();
		spProductos.setBounds(10, 11, 898, 270);
		spProductos.setBackground(new Color(248, 248, 255));


		modelProductos = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 5) {
					return true;
				} else {
					return false;
				}
			}
		};

		tablaProductos = new JTable(modelProductos);
		tablaProductos.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(190);
		tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(30);
		tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(70);
		tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(7).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(8).setPreferredWidth(80);
		tablaProductos.getColumnModel().getColumn(9).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(10).setPreferredWidth(120);
		tablaProductos.getColumnModel().getColumn(11).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(12).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(13).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(14).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(15).setPreferredWidth(190);
		
		
		
//		tablaProductos.getColumnModel().getColumn(0).setResizable(false);
//		tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(100);
//		tablaProductos.getColumnModel().getColumn(1).setResizable(false);
		
		
		
		tablaProductos.getTableHeader().setReorderingAllowed(false);
		spProductos.setViewportView(tablaProductos);
		tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaProductos.doLayout();
		
		panel.add(spProductos);
		
		
		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(248, 248, 255));
		btnAtras.setBounds(10, 291, 55, 50);
		panel.add(btnAtras);
		cambiarIconoBotones(btnAtras, "back2.png");

		lblAtrs = new JLabel("Atras");
		lblAtrs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtrs.setBounds(84, 291, 70, 50);
		panel.add(lblAtrs);
		
		btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.setBounds(164, 293, 132, 48);
		panel.add(btnAgregarProducto);
		
		btnGenerarPedido = new JButton("Generar Pedido");
		btnGenerarPedido.setBounds(394, 293, 179, 48);
		panel.add(btnGenerarPedido);
		
		btnGenerarOrdenDeManufactura = new JButton("Generar Orden de manufactura");
		btnGenerarOrdenDeManufactura.setBounds(733, 291, 175, 27);
		panel.add(btnGenerarOrdenDeManufactura);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(248, 248, 255));
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 95, 908, 95);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 35, 70, 20);
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNombre);

		txtFieldNombre = new JTextField();

		txtFieldNombre.setBounds(10, 65, 116, 20);
		panel_1.add(txtFieldNombre);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 0, 70, 25);
		panel_1.add(lblFiltrarPor);
		
		textTalle = new JTextField();
		textTalle.setBounds(148, 65, 96, 19);
		panel_1.add(textTalle);
		textTalle.setColumns(10);
		
		JLabel lblTalle = new JLabel("Talle");
		lblTalle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTalle.setBounds(148, 35, 70, 20);
		panel_1.add(lblTalle);
		
		chckbxProdSinStock = new JCheckBox("Productos sin stock asignado");
		chckbxProdSinStock.setBounds(737, 64, 165, 21);
		panel_1.add(chckbxProdSinStock);

		lblTitulo = new JLabel("Gestionar Productos");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setBounds(10, 52, 643, 43);
		frame.getContentPane().add(lblTitulo);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 908, 50);
		frame.getContentPane().add(panel_2);

		lblNewLabel = new JLabel("Zapateria Argento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 0, 421, 50);
		panel_2.add(lblNewLabel);
	}

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
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

	public void mostrarVentanaParaVendedor() {
		lblTitulo.setText("Ver Productos");
		btnAgregarProducto.setVisible(false);
		btnGenerarOrdenDeManufactura.setVisible(false);
		btnGenerarPedido.setVisible(false);
		btnAtras.setVisible(true);
	}
	
	public void cerrar() {

		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTextField getTxtFieldNombre() {
		return txtFieldNombre;
	}

	public DefaultTableModel getModelProductos() {
		return modelProductos;
	}

	public JTable getTablaProductos() {
		return tablaProductos;
	}

	public JScrollPane getSpProductos() {
		return spProductos;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnAgregarProducto() {
		return btnAgregarProducto;
	}


	public JTextField getTextTalle() {
		return textTalle;
	}
	
	
	public JCheckBox getChckbxProdSinStock() {
		return chckbxProdSinStock;
	}
	

	public JButton getBtnGenerarPedido() {
		return btnGenerarPedido;
	}
	

	public JButton getBtnGenerarOrdenDeManufactura() {
		return btnGenerarOrdenDeManufactura;
	}

}

