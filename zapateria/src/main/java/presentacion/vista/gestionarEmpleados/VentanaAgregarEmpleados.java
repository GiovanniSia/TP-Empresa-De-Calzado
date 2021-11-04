package presentacion.vista.gestionarEmpleados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class VentanaAgregarEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCUIL;
	private JTextField txtCorreoElectronico;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTipoEmpleado;
	private JButton btnAgregar;
	private JButton btnAtras;
	private JPasswordField txtClaveNueva;
	private JPasswordField txtRepetirClave;
	private JLabel lblMostrarClaveNueva;
	private JLabel lblMostrarRepetirClave;

	public static void main(String[] args) {
		VentanaAgregarEmpleados a = new VentanaAgregarEmpleados();
		a.initialize();
		a.mostrarVentana();
	}

	public VentanaAgregarEmpleados() {
		this.initialize();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 323, 432);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMostrarClaveNueva = new JLabel("");
		lblMostrarClaveNueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtClaveNueva.setEchoChar((char) 0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txtClaveNueva.setEchoChar('\u25cf');
			}
		});

		lblMostrarClaveNueva.setBorder(null);
		lblMostrarClaveNueva.setBounds(285, 256, 15, 18);
		cambiarIconoLabel(lblMostrarClaveNueva, "ojo.png");
		contentPane.add(lblMostrarClaveNueva);

		lblMostrarRepetirClave = new JLabel("");
		lblMostrarRepetirClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtRepetirClave.setEchoChar((char) 0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txtRepetirClave.setEchoChar('\u25cf');
			}
		});

		lblMostrarRepetirClave.setBorder(null);
		lblMostrarRepetirClave.setBounds(285, 306, 15, 18);
		cambiarIconoLabel(lblMostrarRepetirClave, "ojo.png");
		contentPane.add(lblMostrarRepetirClave);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 496, 50);
		contentPane.add(panel);

		JLabel lblNewLabel_2 = new JLabel("Zapateria Argento");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_2.setBounds(10, 0, 280, 50);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("Agregar Empleado Nuevo");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(10, 49, 280, 38);
		contentPane.add(lblNewLabel_1);

		btnAtras = new JButton("");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtras.setBounds(10, 337, 50, 50);
		cambiarIconoBotones(btnAtras, "back2.png");
		contentPane.add(btnAtras);

		JLabel lblNewLabel_4 = new JLabel("Atras");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(70, 337, 38, 50);
		contentPane.add(lblNewLabel_4);

		btnAgregar = new JButton("");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgregar.setBounds(167, 337, 50, 50);
		cambiarIconoBotones(btnAgregar, "plus.png");
		contentPane.add(btnAgregar);

		JLabel lblNewLabel_4_1 = new JLabel("Agregar");
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(226, 337, 64, 50);
		contentPane.add(lblNewLabel_4_1);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				if (!Character.isLetter(validar) || txtNombre.getText().length() >= 30) {
					e.consume();
				}
			}
		});
		txtNombre.setBounds(10, 127, 114, 20);
		contentPane.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				if (!Character.isLetter(validar) || txtApellido.getText().length() >= 30) {
					e.consume();
				}
			}
		});
		txtApellido.setBounds(164, 128, 116, 20);
		contentPane.add(txtApellido);

		JLabel lblIdempleado = new JLabel("Nombre");
		lblIdempleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdempleado.setBounds(10, 101, 117, 20);
		contentPane.add(lblIdempleado);

		JLabel lblCuil = new JLabel("Apellido");
		lblCuil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil.setBounds(167, 102, 114, 20);
		contentPane.add(lblCuil);

		txtCUIL = new JTextField();
		txtCUIL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtCUIL.getText().length() >= 12 || !numeros) {
					e.consume();
				}
			}
		});
		txtCUIL.setBounds(10, 186, 114, 20);
		contentPane.add(txtCUIL);

		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57 || key == 46 || key == 64;
				if (!(Character.isLetter(validar) || numeros) || txtCorreoElectronico.getText().length() >= 30) {
					e.consume();
				}
			}
		});
		txtCorreoElectronico.setBounds(164, 187, 116, 20);
		contentPane.add(txtCorreoElectronico);

		JLabel lblIdempleado_1 = new JLabel("CUIL");
		lblIdempleado_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdempleado_1.setBounds(10, 160, 117, 20);
		contentPane.add(lblIdempleado_1);

		JLabel lblCuil_1 = new JLabel("Correo Electronico");
		lblCuil_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1.setBounds(167, 161, 133, 20);
		contentPane.add(lblCuil_1);

		JLabel lblIdempleado_2 = new JLabel("Tipo Empleado");
		lblIdempleado_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdempleado_2.setBounds(10, 231, 117, 20);
		contentPane.add(lblIdempleado_2);

		JLabel lblClave = new JLabel("Clave Nueva");
		lblClave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblClave.setBounds(167, 232, 114, 20);
		contentPane.add(lblClave);

		cbTipoEmpleado = new JComboBox();
		cbTipoEmpleado.setBounds(10, 256, 114, 22);
		cbTipoEmpleado.addItem("Sin seleccionar");
		contentPane.add(cbTipoEmpleado);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(137, 98, 17, 228);
		contentPane.add(separator);

		JLabel lblRepetirClave = new JLabel("Repetir Clave");
		lblRepetirClave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblRepetirClave.setBounds(166, 282, 114, 20);
		contentPane.add(lblRepetirClave);

		txtRepetirClave = new JPasswordField();
		txtRepetirClave.setBounds(164, 306, 116, 20);
		contentPane.add(txtRepetirClave);

		txtClaveNueva = new JPasswordField();
		txtClaveNueva.setBounds(164, 256, 116, 20);
		contentPane.add(txtClaveNueva);
		frame.setLocationRelativeTo(null);

	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		for (WindowListener listener : this.frame.getWindowListeners()) {
			this.frame.removeWindowListener(listener);
		}
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "�Est�s seguro que quieres salir?", "Advertencia",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public JTextField getTxtCUIL() {
		return txtCUIL;
	}

	public JTextField getTxtCorreoElectronico() {
		return txtCorreoElectronico;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbTipoEmpleado() {
		return cbTipoEmpleado;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JPasswordField getTxtClaveNueva() {
		return txtClaveNueva;
	}

	public JPasswordField getTxtRepetirClave() {
		return txtRepetirClave;
	}

	public void cerrarVentana() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.show();
	}
}