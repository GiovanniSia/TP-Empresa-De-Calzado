package presentacion.vista;

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
		frame.setBounds(100, 100, 373, 282);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel_1 = new JPanel();

		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(10, 66, 335, 166);

		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIniciarSesion.setBounds(151, 124, 145, 32);
		panel_1.add(btnIniciarSesion);

		lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenia.setBounds(34, 83, 120, 30);
		panel_1.add(lblContrasenia);

		txtFieldCorreo = new JTextField();
		txtFieldCorreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtFieldCorreo.getText().length() >= 40) {
					e.consume();
				}
			}
		});

		txtFieldCorreo.setBounds(160, 52, 120, 20);
		panel_1.add(txtFieldCorreo);
		txtFieldCorreo.setColumns(10);

		lblCorreo = new JLabel("Correo");
		lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCorreo.setBounds(34, 42, 120, 30);
		panel_1.add(lblCorreo);

		txtFieldContra = new JPasswordField();
		txtFieldContra.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtFieldContra.getText().length() >= 40) {
					e.consume();
				}
			}
		});

		txtFieldContra.setBounds(160, 90, 120, 20);
		panel_1.add(txtFieldContra);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSucursal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSucursal.setBounds(34, 1, 120, 30);
		panel_1.add(lblSucursal);
		
		cbSucursales = new JComboBox();
		cbSucursales.setBounds(160, 7, 120, 22);
		panel_1.add(cbSucursales);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 11, 337, 44);
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
	
}
