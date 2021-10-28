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
	private JPanel panel;
	private JLabel lblNewLabel_1;
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
		frame.setBounds(100, 100, 621, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

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
		panel_1.setBounds(0, 96, 605, 257);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblProductcoSeleccionado = new JLabel("Producto Seleccionado:");
		lblProductcoSeleccionado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProductcoSeleccionado.setBounds(10, 11, 161, 14);
		panel_1.add(lblProductcoSeleccionado);

		JScrollPane spProductoSeleccionado = new JScrollPane();
		spProductoSeleccionado.setBounds(10, 34, 579, 43);
		panel_1.add(spProductoSeleccionado);

		tablaProductoSeleccionado = new JTable(modelProducto);

		tablaProductoSeleccionado.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProductoSeleccionado.getColumnModel().getColumn(0).setResizable(false);
		tablaProductoSeleccionado.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProductoSeleccionado.getColumnModel().getColumn(1).setResizable(false);

		spProductoSeleccionado.setViewportView(tablaProductoSeleccionado);

		btnAtras = new JButton("");
		btnAtras.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAtras.setBounds(89, 166, 60, 60);
		cambiarIconoBotones(btnAtras,  "back2.png");
		panel_1.add(btnAtras);

		btnActualizarProducto = new JButton("");
		btnActualizarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnActualizarProducto.setBounds(316, 166, 60, 60);
		cambiarIconoBotones(btnActualizarProducto,  "update.png");
		panel_1.add(btnActualizarProducto);

		JLabel lblNewLabel = new JLabel("Cambiar Descripcion");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 102, 130, 23);
		panel_1.add(lblNewLabel);

		JLabel lblNuevoProveedor = new JLabel("Cambiar Proveedor");
		lblNuevoProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNuevoProveedor.setBounds(324, 102, 130, 23);
		panel_1.add(lblNuevoProveedor);

		txtActualizarCambioDescripcion = new JTextField();
		txtActualizarCambioDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			 char validar = e.getKeyChar();
			 if(!Character.isLetter(validar) || txtActualizarCambioDescripcion.getText().length() >= 20) {
				 e.consume();
			 }
			}
		});
		txtActualizarCambioDescripcion.setBounds(166, 103, 127, 20);
		panel_1.add(txtActualizarCambioDescripcion);
		txtActualizarCambioDescripcion.setColumns(10);

		cbActualizarCambioProveedor = new JComboBox<String>();
		cbActualizarCambioProveedor.setBounds(464, 104, 127, 22);
		panel_1.add(cbActualizarCambioProveedor);
		
		lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAtras.setBounds(159, 166, 130, 60);
		panel_1.add(lblAtras);
		
		lblActualizarProducto = new JLabel("Actualizar Producto");
		lblActualizarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblActualizarProducto.setBounds(386, 166, 130, 60);
		panel_1.add(lblActualizarProducto);

		lblTItulo = new JLabel("Cambiar Descripcion y Proveedor de Producto");
		lblTItulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTItulo.setBounds(10, 55, 492, 30);
		frame.getContentPane().add(lblTItulo);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 605, 50);
		frame.getContentPane().add(panel);
		
		lblNewLabel_1 = new JLabel("Zapateria Argento");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_1.setBounds(10, 0, 421, 50);
		panel.add(lblNewLabel_1);
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