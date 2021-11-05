package presentacion.vista.Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
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
import javax.swing.border.LineBorder;

public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
	private JLabel lblContrasenia;
	private JLabel lblCorreo;
	private JTextField txtFieldCorreo;
	private JButton btnIniciarSesion;
	private JPasswordField txtFieldContra;
	@SuppressWarnings("rawtypes")
	private JComboBox cbSucursales;
	private JLabel lblNewLabel_1;
	private JLabel lblZapateriaArgento;

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
		frame.setBounds(100, 100, 808, 635);
		
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
	
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(51, 102, 204));
		panel_1.setBorder(new LineBorder(new Color(248, 248, 255), 2, true));

		panel_1.setBackground(new Color(0, 102, 153));
		panel_1.setBounds(244, 115, 292, 138);

		contentPane.add(panel_1);
		panel_1.setLayout(null);

		cbSucursales = new JComboBox();
		cbSucursales.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbSucursales.setBounds(140, 11, 129, 29);
		panel_1.add(cbSucursales);
		cbSucursales.setBorder(null);

		txtFieldCorreo = new JTextField();
		txtFieldCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtFieldCorreo.setBounds(140, 54, 129, 29);
		panel_1.add(txtFieldCorreo);
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
		txtFieldContra.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtFieldContra.setBounds(140, 94, 129, 29);
		panel_1.add(txtFieldContra);
		txtFieldContra.setBorder(null);

		lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setForeground(new Color(255, 255, 255));
		lblContrasenia.setBounds(10, 93, 120, 30);
		panel_1.add(lblContrasenia);
		lblContrasenia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);

		lblCorreo = new JLabel("Correo");
		lblCorreo.setForeground(new Color(255, 255, 255));
		lblCorreo.setBounds(10, 52, 120, 30);
		panel_1.add(lblCorreo);
		lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setForeground(new Color(255, 255, 255));
		lblSucursal.setBounds(10, 11, 120, 30);
		panel_1.add(lblSucursal);
		lblSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtFieldContra.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtFieldContra.getText().length() >= 40) {
					e.consume();
				}
			}
		});

		btnIniciarSesion = new JButton("");
		btnIniciarSesion.setBounds(358, 264, 60, 60);
		contentPane.add(btnIniciarSesion);
		btnIniciarSesion.setForeground(Color.BLACK);
		btnIniciarSesion.setBackground(Color.WHITE);
		btnIniciarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cambiarIconoBotones(btnIniciarSesion, "login.png");

		lblZapateriaArgento = new JLabel("");
		lblZapateriaArgento.setBackground(new Color(0, 102, 204));
		lblZapateriaArgento.setVerticalAlignment(SwingConstants.TOP);
		lblZapateriaArgento.setHorizontalAlignment(SwingConstants.CENTER);
		lblZapateriaArgento.setForeground(new Color(0, 102, 204));
		lblZapateriaArgento.setFont(new Font("Segoe UI", Font.BOLD, 54));
		lblZapateriaArgento.setBounds(167, 448, 451, 121);
		cambiarIconoLabel(lblZapateriaArgento, "argentoshoes.png");

		contentPane.add(lblZapateriaArgento);

		JLabel lblNewLabel = new JLabel("pepe@ Cajero");
		lblNewLabel.setBounds(659, 384, 112, 14);
		contentPane.add(lblNewLabel);

		JLabel lblJuangmailcomVendedor = new JLabel("juan@ Vendedor");
		lblJuangmailcomVendedor.setBounds(659, 409, 112, 14);
		contentPane.add(lblJuangmailcomVendedor);

		JLabel lblCamilaVendedor = new JLabel("camila@ Supervisor");
		lblCamilaVendedor.setBounds(659, 432, 112, 14);
		contentPane.add(lblCamilaVendedor);

		JLabel lblMaximilianoCajero = new JLabel("maximiliano@ OperarioF");
		lblMaximilianoCajero.setBounds(659, 460, 123, 14);
		contentPane.add(lblMaximilianoCajero);

		JLabel lblSupervisorf = new JLabel("josselyn@ SupervisorF");
		lblSupervisorf.setBounds(659, 485, 112, 14);
		contentPane.add(lblSupervisorf);

		JLabel lblMichelleAdmin = new JLabel("michelle@ Admin");
		lblMichelleAdmin.setBounds(659, 508, 112, 14);
		contentPane.add(lblMichelleAdmin);
		
		JLabel lblMatiasGerente = new JLabel("matias@ Gerente");
		lblMatiasGerente.setBounds(659, 530, 112, 14);
		contentPane.add(lblMatiasGerente);

		lblNewLabel_1 = new JLabel("Clave: 1234");
		lblNewLabel_1.setBounds(659, 555, 112, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_2);
		cambiarIconoLabel(lblNewLabel_2, "foto.png");

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
	
	public void limpiarCampos() {
		this.txtFieldCorreo.setText(null);
		this.txtFieldContra.setText(null);
	}

	public void cerrarVentana() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.show();
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

}
