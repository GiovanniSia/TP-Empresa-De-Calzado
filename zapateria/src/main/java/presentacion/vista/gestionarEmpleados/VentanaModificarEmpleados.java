package presentacion.vista.gestionarEmpleados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class VentanaModificarEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCUIL;
	private JTextField txtCorreoElectronico;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTipoEmpleado;
	private JButton btnActualizar;
	private JButton btnAtras;
	private JCheckBox checkboxCambiarClave;
	private JLabel lblMostrarClaveNueva;
	private JPasswordField txtClaveNueva;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel;
	private JPanel panel_1;

	public static void main(String[] args) {
		VentanaModificarEmpleados a = new VentanaModificarEmpleados();
		a.initialize();
		a.mostrarVentana();
	}

	public VentanaModificarEmpleados() {
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
		frame.setBounds(100, 100, 334, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMostrarClaveNueva = new JLabel("");	
		lblMostrarClaveNueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtClaveNueva.setEchoChar((char)0);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtClaveNueva.setEchoChar('\u25cf');
			}
		});
		
		lblMostrarClaveNueva.setBorder(null);
		lblMostrarClaveNueva.setBounds(297, 260, 15, 18);
		cambiarIconoLabel(lblMostrarClaveNueva, "ojo.png");
		contentPane.add(lblMostrarClaveNueva);

		JLabel lblNewLabel_1 = new JLabel("Modificar Empleado");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel_1.setBounds(10, 52, 280, 44);
		contentPane.add(lblNewLabel_1);

		btnAtras = new JButton("");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtras.setBounds(10, 314, 50, 50);
		cambiarIconoBotones(btnAtras, "back2.png");
		contentPane.add(btnAtras);

		JLabel lblNewLabel_4 = new JLabel("Atras");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(70, 314, 38, 50);
		contentPane.add(lblNewLabel_4);

		btnActualizar = new JButton("");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnActualizar.setBounds(176, 314, 50, 50);
		cambiarIconoBotones(btnActualizar, "update.png");
		contentPane.add(btnActualizar);

		JLabel lblNewLabel_4_1 = new JLabel("Actualizar");
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(233, 314, 75, 50);
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
		txtApellido.setBounds(176, 126, 116, 20);
		contentPane.add(txtApellido);

		JLabel lblIdempleado = new JLabel("Nombre");
		lblIdempleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdempleado.setBounds(10, 101, 117, 20);
		contentPane.add(lblIdempleado);

		JLabel lblCuil = new JLabel("Apellido");
		lblCuil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil.setBounds(179, 100, 114, 20);
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
		txtCorreoElectronico.setBounds(176, 185, 116, 20);
		contentPane.add(txtCorreoElectronico);

		JLabel lblIdempleado_1 = new JLabel("CUIL");
		lblIdempleado_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdempleado_1.setBounds(10, 160, 117, 20);
		contentPane.add(lblIdempleado_1);

		JLabel lblCuil_1 = new JLabel("Correo Electronico");
		lblCuil_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1.setBounds(179, 159, 133, 20);
		contentPane.add(lblCuil_1);

		JLabel lblIdempleado_2 = new JLabel("Tipo Empleado");
		lblIdempleado_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdempleado_2.setBounds(10, 231, 117, 20);
		contentPane.add(lblIdempleado_2);

		cbTipoEmpleado = new JComboBox();
		cbTipoEmpleado.setBounds(10, 256, 114, 22);
		cbTipoEmpleado.addItem("Sin seleccionar");
		contentPane.add(cbTipoEmpleado);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(155, 98, 11, 191);
		contentPane.add(separator);
		
		txtClaveNueva = new JPasswordField();
		txtClaveNueva.setBounds(177, 258, 116, 20);
		txtClaveNueva.setEditable(false);
		contentPane.add(txtClaveNueva);
		
		checkboxCambiarClave = new JCheckBox("Cambiar Clave");
		checkboxCambiarClave.setBackground(new Color(248, 248, 255));
		checkboxCambiarClave.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkboxCambiarClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkboxCambiarClave.isSelected()) {
					txtClaveNueva.setEditable(true);
				}else {
					txtClaveNueva.setEditable(false);
					
				}
			}
		});
		checkboxCambiarClave.setBounds(176, 230, 116, 23);
		contentPane.add(checkboxCambiarClave);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 318, 53);
		contentPane.add(panel);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 64, 42);
		cambiarIconoLabel(lblLogo, "icono2.png");
		panel.add(lblLogo);
		
		lblNewLabel_2 = new JLabel("Empleado:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(100, 28, 59, 19);
		panel.add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("Sucursal:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(100, 5, 59, 19);
		panel.add(lblNewLabel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 98, 318, 191);
		contentPane.add(panel_1);
		frame.setLocationRelativeTo(null);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 48, 640, 480);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
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

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JTextField getTxtClaveNueva() {
		return txtClaveNueva;
	}

	public JCheckBox getCheckboxCambiarClave() {
		return checkboxCambiarClave;
	}

	public void cerrarVentana() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.show();
	}
}