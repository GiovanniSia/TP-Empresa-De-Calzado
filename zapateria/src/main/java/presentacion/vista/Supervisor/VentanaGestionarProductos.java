package presentacion.vista.Supervisor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

public class VentanaGestionarProductos extends JFrame {

private static final long serialVersionUID = 1L;
	
	private JFrame frame;

	private String[] nombreColumnas = {"Id Producto","Descripcion","Stock Disponible","Tipo","Prod. Propio","Costo prod.","Precio Mayorista","Precio Minorista","Punto de rep. minimo","Id Prov","Talle","Medida","Estado","Cant a rep","Dias para rep."};
	private JLabel lblNombre;
	private JTextField txtFieldNombre;
	private DefaultTableModel modelProductos;
	private JTable tablaProductos;
	private JLabel lblFiltrarPor;
	private JLabel lblTitulo;
	private JScrollPane spProductos;
	private JButton btnAtras;
	private JLabel lblAtrs;
	private JButton btnAgregarProducto;
	private JTextField textTalle;
	JButton btnGenerarPedido;
	JButton btnGenerarOrdenDeManufactura;
	private JPanel panel_2;
	private JLabel lblLogo;
	private JLabel lblAgregarProducto;
	private JLabel lblAtrs_2;
	private JLabel lblAtrs_1;

	private JButton btnEditar;
	private JLabel lbleditarProducto;
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
		frame.setBounds(100, 100, 923, 609);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(0, 191, 908, 295);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spProductos = new JScrollPane();
		spProductos.setBounds(10, 11, 888, 270);
		spProductos.setBackground(new Color(248, 248, 255));


		modelProductos = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				return false;
				
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
		
		
		
//		tablaProductos.getColumnModel().getColumn(0).setResizable(false);
//		tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(100);
//		tablaProductos.getColumnModel().getColumn(1).setResizable(false);
		
		
		
		tablaProductos.getTableHeader().setReorderingAllowed(false);
		spProductos.setViewportView(tablaProductos);
		tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaProductos.doLayout();
		
		panel.add(spProductos);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(248, 248, 255));
		panel_1.setBackground(new Color(255, 255, 255, 180));
		panel_1.setBounds(0, 95, 908, 99);
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

		lblTitulo = new JLabel("Gestionar Productos");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setBounds(10, 52, 643, 43);
		frame.getContentPane().add(lblTitulo);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 906, 53);
		frame.getContentPane().add(panel_2);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel_2.add(lblLogo);
		
		btnGenerarPedido = new JButton("");
		btnGenerarPedido.setBounds(480, 494, 60, 60);
		cambiarIconoBotones(btnGenerarPedido, "arroba.png");
		frame.getContentPane().add(btnGenerarPedido);
		
		btnAgregarProducto = new JButton("");
		btnAgregarProducto.setBounds(132, 494, 60, 60);
		cambiarIconoBotones(btnAgregarProducto, "product+.png");
		frame.getContentPane().add(btnAgregarProducto);
		

		lblAtrs = new JLabel("Atras");
		lblAtrs.setBounds(75, 494, 51, 60);
		frame.getContentPane().add(lblAtrs);
		lblAtrs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		
		btnAtras = new JButton("");
		btnAtras.setBounds(10, 494, 60, 60);
		frame.getContentPane().add(btnAtras);
		btnAtras.setBackground(new Color(248, 248, 255));
		cambiarIconoBotones(btnAtras, "back2.png");
		
		btnGenerarOrdenDeManufactura = new JButton("");
		btnGenerarOrdenDeManufactura.setBounds(682, 494, 60, 60);
		cambiarIconoBotones(btnGenerarOrdenDeManufactura, "fabrica.png");
		frame.getContentPane().add(btnGenerarOrdenDeManufactura);
		
		lblAgregarProducto = new JLabel("<html>Agregar Producto</html>");
		lblAgregarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAgregarProducto.setBounds(202, 497, 84, 60);
		frame.getContentPane().add(lblAgregarProducto);
		
		lblAtrs_2 = new JLabel("<html>Generar Pedido a Proveedor</html>");
		lblAtrs_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtrs_2.setBounds(551, 494, 121, 60);
		frame.getContentPane().add(lblAtrs_2);
		
		lblAtrs_1 = new JLabel("<html>Generar Orden de Manufactura</html>");
		lblAtrs_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtrs_1.setBounds(752, 494, 121, 60);
		frame.getContentPane().add(lblAtrs_1);
		
		btnEditar = new JButton("");
		btnEditar.setBounds(305, 496, 60, 60);
		cambiarIconoBotones(btnEditar, "update.png");
		frame.getContentPane().add(btnEditar);
		
		lbleditarProducto = new JLabel("<html>Editar Producto<html>");
		lbleditarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lbleditarProducto.setBounds(366, 496, 90, 60);
		frame.getContentPane().add(lbleditarProducto);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 1000, 750);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
	}

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
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

	public void mostrarVentanaParaVerProductos() {
		lblTitulo.setText("Ver Productos");
		btnAgregarProducto.setVisible(false);
		btnGenerarOrdenDeManufactura.setVisible(false);
		btnGenerarPedido.setVisible(false);
		btnAtras.setVisible(true);
		lblAtrs_1.setVisible(false);
		lblAgregarProducto.setVisible(false);
		lblAtrs_2.setVisible(false);
		btnEditar.setVisible(false);
		lbleditarProducto.setVisible(false);
		
	}
	
	public void mostrarVentanaParaVerProductosYOrdenDeManufactura() {
		lblTitulo.setText("Ver y Ordenar Productos");
		btnAgregarProducto.setVisible(false);
		btnGenerarOrdenDeManufactura.setVisible(true);
		btnGenerarPedido.setVisible(true);
		btnAtras.setVisible(true);
		lblAgregarProducto.setVisible(false);
		lblAtrs_2.setVisible(true);
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


	public JButton getBtnGenerarPedido() {
		return btnGenerarPedido;
	}
	

	public JButton getBtnGenerarOrdenDeManufactura() {
		return btnGenerarOrdenDeManufactura;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblFiltrarPor() {
		return lblFiltrarPor;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public JLabel getLblAtrs() {
		return lblAtrs;
	}

	public JPanel getPanel_2() {
		return panel_2;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}



	public JLabel getLblAgregarProducto() {
		return lblAgregarProducto;
	}

	public JLabel getLblAtrs_2() {
		return lblAtrs_2;
	}

	public JLabel getLblAtrs_1() {
		return lblAtrs_1;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	
}

