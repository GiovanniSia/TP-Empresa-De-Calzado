package presentacion.vista.ModificarProducto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;

public class VentanaDescripcionProveedorMProducto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Codigo", "Descripción", "Proveedor", "Talle" };
	private DefaultTableModel modelProducto;
	private JTable tablaProductoSeleccionado;
	private JLabel lblProductcoSeleccionado;
	private JLabel lblTItulo;
	private JPanel panel_1;
	private JButton btnAtras;
	private JButton btnActualizarProducto;
	private JTextField txtActualizarCambioDescripcion;
	private JComboBox<String> cbActualizarCambioProveedor;
	private JLabel lblAtras;
	private JLabel lblActualizarProducto;

	public static void main(String[] args) {
		VentanaDescripcionProveedorMProducto v = new VentanaDescripcionProveedorMProducto();
		v.show();
	}

	public VentanaDescripcionProveedorMProducto() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 621, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		modelProducto = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 6) {
					return true;
				} else {
					return false;
				}
			}
		};

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 113, 605, 144);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblProductcoSeleccionado = new JLabel("Producto Seleccionado:");
		lblProductcoSeleccionado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProductcoSeleccionado.setBounds(10, 11, 161, 14);
		panel_1.add(lblProductcoSeleccionado);

		JScrollPane spProductoSeleccionado = new JScrollPane();
		spProductoSeleccionado.setBounds(10, 34, 579, 57);
		panel_1.add(spProductoSeleccionado);

		tablaProductoSeleccionado = new JTable(modelProducto);

		tablaProductoSeleccionado.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProductoSeleccionado.getColumnModel().getColumn(0).setResizable(false);
		tablaProductoSeleccionado.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProductoSeleccionado.getColumnModel().getColumn(1).setResizable(false);

		spProductoSeleccionado.setViewportView(tablaProductoSeleccionado);

		JLabel lblNewLabel = new JLabel("Cambiar Descripcion");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 102, 130, 31);
		panel_1.add(lblNewLabel);

		JLabel lblNuevoProveedor = new JLabel("Cambiar Proveedor");
		lblNuevoProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNuevoProveedor.setBounds(324, 102, 130, 31);
		panel_1.add(lblNuevoProveedor);

		txtActualizarCambioDescripcion = new JTextField();
		txtActualizarCambioDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtActualizarCambioDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			 char validar = e.getKeyChar();
			 if(!Character.isLetter(validar) || txtActualizarCambioDescripcion.getText().length() >= 20) {
				 e.consume();
			 }
			}
		});
		txtActualizarCambioDescripcion.setBounds(166, 103, 127, 30);
		panel_1.add(txtActualizarCambioDescripcion);
		txtActualizarCambioDescripcion.setColumns(10);

		cbActualizarCambioProveedor = new JComboBox<String>();
		cbActualizarCambioProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbActualizarCambioProveedor.setBounds(464, 104, 127, 29);
		panel_1.add(cbActualizarCambioProveedor);

		lblTItulo = new JLabel("Cambiar Descripcion y Proveedor de Producto");
		lblTItulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTItulo.setBounds(10, 55, 492, 53);
		frame.getContentPane().add(lblTItulo);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1_1.setBackground(new Color(153, 204, 255));
		panel_1_1.setBounds(0, 0, 605, 53);
		frame.getContentPane().add(panel_1_1);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel_1_1.add(lblLogo);
		
		lblActualizarProducto = new JLabel("Actualizar Producto");
		lblActualizarProducto.setBounds(389, 268, 130, 60);
		frame.getContentPane().add(lblActualizarProducto);
		lblActualizarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
				btnActualizarProducto = new JButton("");
				btnActualizarProducto.setBounds(319, 268, 60, 60);
				frame.getContentPane().add(btnActualizarProducto);
				btnActualizarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
				cambiarIconoBotones(btnActualizarProducto,  "update.png");
				
						btnAtras = new JButton("");
						btnAtras.setBounds(92, 268, 60, 60);
						frame.getContentPane().add(btnAtras);
						btnAtras.setFont(new Font("Segoe UI", Font.PLAIN, 12));
						cambiarIconoBotones(btnAtras,  "back2.png");
						
						lblAtras = new JLabel("Atras");
						lblAtras.setBounds(162, 268, 130, 60);
						frame.getContentPane().add(lblAtras);
						lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 14));
						
						JLabel lblFondo = new JLabel("");
						lblFondo.setBounds(1, 0, 640, 480);
						frame.getContentPane().add(lblFondo);
						cambiarIconoLabel(lblFondo, "fondo.png");
	}
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}
	
	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelModelProducto() {
		return modelProducto;
	}

	public JTable getTablaProductoSeleccionado() {
		return tablaProductoSeleccionado;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnActualizarProducto() {
		return btnActualizarProducto;
	}

	public JTextField getTxtActualizarCambioDescripcion() {
		return txtActualizarCambioDescripcion;
	}

	public JComboBox<String> getCbActualizarCambioProveedor() {
		return cbActualizarCambioProveedor;
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
		limpiarCampos();
		frame.setVisible(false);
	}

	public void limpiarCampos() {
		this.txtActualizarCambioDescripcion.setText(null);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
}