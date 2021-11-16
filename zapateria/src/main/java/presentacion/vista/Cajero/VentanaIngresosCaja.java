package presentacion.vista.Cajero;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;

public class VentanaIngresosCaja extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtFieldIngresoSaldoInicial;
	private JTextField txtFieldRecargaSaldo;
	private JLabel lblIngresoSaldoInicial;
	private JLabel lblRegarcaSaldo;

	private JLabel lblActualizarEstadoCaja;
	private JLabel lblSaldoActual;
	private JLabel lblActualizarSaldoActual;
	private JLabel lblActualizarFechaHoy;
	private JPanel panel;
	private JLabel lblLogo;
	private JButton btnAtras;
	private JButton btnRealizarIngreso;

	public static void main(String[] args) {
		VentanaIngresosCaja n = new VentanaIngresosCaja();
		n.show();
	}

	public VentanaIngresosCaja() {
		initialize();
	}

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
		frame.setBounds(100, 100, 512, 369);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel_1 = new JPanel();

		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 62, 496, 72);

		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ingresos de Caja");

		lblNewLabel_1.setBackground(new Color(248, 248, 255));
		lblNewLabel_1.setBounds(34, 5, 176, 56);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Estado de la Caja:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(207, 22, 142, 14);
		panel_1.add(lblNewLabel_2);

		lblActualizarEstadoCaja = new JLabel("Sin estado");
		lblActualizarEstadoCaja.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblActualizarEstadoCaja.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarEstadoCaja.setBounds(207, 47, 142, 14);
		panel_1.add(lblActualizarEstadoCaja);

		lblSaldoActual = new JLabel("Saldo actual:");
		lblSaldoActual.setHorizontalAlignment(SwingConstants.CENTER);

		lblSaldoActual.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSaldoActual.setBounds(344, 22, 142, 14);
		panel_1.add(lblSaldoActual);

		lblActualizarSaldoActual = new JLabel("$0");
		lblActualizarSaldoActual.setHorizontalAlignment(SwingConstants.CENTER);

		lblActualizarSaldoActual.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblActualizarSaldoActual.setBounds(344, 47, 142, 14);
		panel_1.add(lblActualizarSaldoActual);

		lblActualizarFechaHoy = new JLabel("");
		lblActualizarFechaHoy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblActualizarFechaHoy.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarFechaHoy.setBounds(287, 5, 125, 14);
		panel_1.add(lblActualizarFechaHoy);

		JPanel panel_2 = new JPanel();

		panel_2.setBorder(null);
		panel_2.setBackground(new Color(248, 248, 255));
		panel_2.setBounds(0, 134, 496, 103);

		contentPane.add(panel_2);
		panel_2.setLayout(null);

		lblIngresoSaldoInicial = new JLabel("Ingresar saldo inicial:");

		lblIngresoSaldoInicial.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblIngresoSaldoInicial.setBounds(70, 11, 171, 32);

		panel_2.add(lblIngresoSaldoInicial);

		txtFieldIngresoSaldoInicial = new JTextField();
		txtFieldIngresoSaldoInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean numeros = key >= 48 && key <= 57;
				if (txtFieldIngresoSaldoInicial.getText().length() >= 9 || !numeros) {
					e.consume();
				}
			}
		});

		txtFieldIngresoSaldoInicial.setBounds(313, 16, 136, 28);

		panel_2.add(txtFieldIngresoSaldoInicial);
		txtFieldIngresoSaldoInicial.setColumns(10);

		lblRegarcaSaldo = new JLabel("Ingresar recarga de saldo:");

		lblRegarcaSaldo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegarcaSaldo.setBounds(36, 54, 205, 32);

		panel_2.add(lblRegarcaSaldo);

		txtFieldRecargaSaldo = new JTextField();
		txtFieldRecargaSaldo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean numeros = key >= 48 && key <= 57;
				if (txtFieldRecargaSaldo.getText().length() >= 9 || !numeros) {
					e.consume();
				}
			}
		});

		txtFieldRecargaSaldo.setColumns(10);
		txtFieldRecargaSaldo.setBounds(313, 59, 136, 28);
		panel_2.add(txtFieldRecargaSaldo);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 496, 53);
		contentPane.add(panel);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);
		
		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(248, 248, 255));
		btnAtras.setBounds(36, 248, 60, 60);
		cambiarIconoBotones(btnAtras, "back2.png");
		contentPane.add(btnAtras);
		
		JLabel lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(105, 248, 44, 60);
		contentPane.add(lblAtras);
		
		btnRealizarIngreso = new JButton("");
		btnRealizarIngreso.setBackground(new Color(248, 248, 255));
		btnRealizarIngreso.setBounds(312, 248, 60, 60);
		cambiarIconoBotones(btnRealizarIngreso, "cashier2.png");
		contentPane.add(btnRealizarIngreso);
		
		JLabel lblRecargar = new JLabel("Ingresar");
		lblRecargar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRecargar.setBounds(384, 247, 76, 61);
		contentPane.add(lblRecargar);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 53, 640, 480);
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
		for (WindowListener listener : this.frame.getWindowListeners()) {
			this.frame.removeWindowListener(listener);
		}
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir?", "Advertencia",
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
		txtFieldIngresoSaldoInicial.setText(null);
		txtFieldRecargaSaldo.setText(null);
	}

	public void cerrar() {
		limpiarCampos();
		frame.setVisible(false);
	}

	public void mostrarSaldoActual() {
		this.lblSaldoActual.setVisible(true);
		this.lblActualizarSaldoActual.setVisible(true);
	}

	public void ocultarSaldoActual() {
		this.lblSaldoActual.setVisible(false);
		this.lblActualizarSaldoActual.setVisible(false);
	}

	public JLabel getLblActualizarSaldoActual() {
		return lblActualizarSaldoActual;
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JLabel getLblActualizarFechaHoy() {
		return lblActualizarFechaHoy;
	}

	public void mostrarHoraHoy() {
		this.lblActualizarFechaHoy.setVisible(true);
	}

	public void ocultarHoraHoy() {
		this.lblActualizarFechaHoy.setVisible(false);
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JLabel getLblActualizarEstadoCaja() {
		return lblActualizarEstadoCaja;
	}

	public void mostrarIngresarSaldoInicial() {
		lblIngresoSaldoInicial.setVisible(true);
		txtFieldIngresoSaldoInicial.setVisible(true);
		ocultarIngresarRecargaSaldo();
	}

	public void ocultarIngresarSaldoInicial() {
		lblIngresoSaldoInicial.setVisible(false);
		txtFieldIngresoSaldoInicial.setVisible(false);
	}

	public void mostrarIngresarRecargaSaldo() {
		lblRegarcaSaldo.setVisible(true);
		txtFieldRecargaSaldo.setVisible(true);
		ocultarIngresarSaldoInicial();
	}

	public void ocultarIngresarRecargaSaldo() {
		lblRegarcaSaldo.setVisible(false);
		txtFieldRecargaSaldo.setVisible(false);
	}

	public JTextField getTxtFieldIngresoSaldoInicial() {
		return txtFieldIngresoSaldoInicial;
	}

	public JTextField getTxtFieldRecargaSaldo() {
		return txtFieldRecargaSaldo;
	}

	public JLabel getLblIngresoSaldoInicial() {
		return lblIngresoSaldoInicial;
	}

	public JLabel getLblRegarcaSaldo() {
		return lblRegarcaSaldo;
	}

	public JButton getBtnRealizarIngreso() {
		return btnRealizarIngreso;
	}
}
