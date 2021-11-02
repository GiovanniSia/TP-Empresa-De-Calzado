package presentacion.vista.Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
	private JLabel lblContrasenia;
	private JPanel panel_2;
	private JLabel lblTiuloZapateria;
	private JLabel lblCorreo;

	private JTextField txtFieldCorreo;
	private JButton btnIniciarSesion;
	private JPasswordField txtFieldContra;
	@SuppressWarnings("rawtypes")
	private JComboBox cbSucursales;
	
	private JCheckBox checkboxControlador;

	public static void main(String[] args) {
		VentanaLogin n = new VentanaLogin();
		n.show();
	}

	public VentanaLogin() {
		initialize();
	}

	@SuppressWarnings("rawtypes")
	public void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 476, 282);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel_1 = new JPanel();

		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(10, 66, 440, 166);

		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(155, 70, 129, 14);
		panel_1.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(155, 108, 129, 14);
		panel_1.add(separator_1);

		cbSucursales = new JComboBox();
		cbSucursales.setBounds(155, 6, 129, 22);
		panel_1.add(cbSucursales);
		cbSucursales.setBorder(null);

		txtFieldCorreo = new JTextField();
		txtFieldCorreo.setBounds(155, 51, 129, 20);
		panel_1.add(txtFieldCorreo);
		txtFieldCorreo.setText("michelle@gmail.com");
		txtFieldCorreo.setBorder(null);
		txtFieldCorreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtFieldCorreo.getText().length() >= 40) {
					e.consume();
				}
			}
		});
		txtFieldCorreo.setColumns(10);

		txtFieldContra = new JPasswordField();
		txtFieldContra.setBounds(155, 89, 129, 20);
		panel_1.add(txtFieldContra);
		txtFieldContra.setBorder(null);

		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBounds(155, 123, 129, 32);
		panel_1.add(btnIniciarSesion);
		btnIniciarSesion.setForeground(Color.BLACK);
		btnIniciarSesion.setBackground(Color.WHITE);
		btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setBounds(38, 82, 120, 30);
		panel_1.add(lblContrasenia);
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);

		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(38, 41, 120, 30);
		panel_1.add(lblCorreo);
		lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(38, 0, 120, 30);
		panel_1.add(lblSucursal);
		lblSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSucursal.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel = new JLabel("pepe@ Cajero");
		lblNewLabel.setBounds(318, 6, 112, 14);
		panel_1.add(lblNewLabel);

		JLabel lblJuangmailcomVendedor = new JLabel("juan@ Vendedor");
		lblJuangmailcomVendedor.setBounds(318, 31, 112, 14);
		panel_1.add(lblJuangmailcomVendedor);

		JLabel lblCamilaVendedor = new JLabel("camila@ Supervisor");
		lblCamilaVendedor.setBounds(318, 54, 112, 14);
		panel_1.add(lblCamilaVendedor);

		JLabel lblMichelleAdmin = new JLabel("michelle@ Admin");
		lblMichelleAdmin.setBounds(318, 130, 112, 14);
		panel_1.add(lblMichelleAdmin);

		JLabel lblSupervisorf = new JLabel("josselyn@ SupervisorF");
		lblSupervisorf.setBounds(318, 107, 112, 14);
		panel_1.add(lblSupervisorf);

		JLabel lblMaximilianoCajero = new JLabel("maximiliano@ OperarioF");
		lblMaximilianoCajero.setBounds(318, 82, 112, 14);
		panel_1.add(lblMaximilianoCajero);
		
		checkboxControlador = new JCheckBox("Activar Controlador");
		checkboxControlador.setBounds(23, 123, 129, 23);
		panel_1.add(checkboxControlador);
		txtFieldContra.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtFieldContra.getText().length() >= 40) {
					e.consume();
				}
			}
		});

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 11, 440, 44);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		lblTiuloZapateria = new JLabel("Zapateria");
		lblTiuloZapateria.setBounds(117, 5, 103, 31);
		lblTiuloZapateria.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(lblTiuloZapateria);

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

	public void mostrar() {
		frame.setVisible(true);
	}

	public JTextField getTxtFieldCorreo() {
		return txtFieldCorreo;
	}

	public JPasswordField getTxtFieldContra() {
		return txtFieldContra;
	}

	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbSucursales() {
		return cbSucursales;
	}

	public JCheckBox getCheckboxControlador() {
		return checkboxControlador;
	}
	
}
