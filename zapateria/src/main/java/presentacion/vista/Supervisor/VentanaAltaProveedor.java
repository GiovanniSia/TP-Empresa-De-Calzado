package presentacion.vista.Supervisor;

import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.UIManager;

import persistencia.conexion.Conexion;
import javax.swing.border.MatteBorder;

public class VentanaAltaProveedor {
	
	private JFrame frame;

	private JTextField textNombre;

	private JTextField textCorreo;
	private JTextField textLimiteCredito;

	private JButton btnRegistrar;
	private JButton btnRegresar;
	private JLabel lblAtras;
	private JLabel lblRegistrarProducto;
	
	private JButton btnEditar;
	private JLabel lblEditar;
	private JPanel panel_1;
	private JPanel panel;
	private JLabel lblLogo;

	private JLabel lblTitulo;

	private JLabel lblSubtitulo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaProveedor window = new VentanaAltaProveedor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaAltaProveedor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(500, 100, 442, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		lblSubtitulo = new JLabel("Registrar Proveedor");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblSubtitulo.setBounds(10, 47, 403, 52);
		frame.getContentPane().add(lblSubtitulo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombre.setBounds(34, 110, 156, 19);
		frame.getContentPane().add(lblNombre);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCorreo.setBounds(34, 146, 156, 19);
		frame.getContentPane().add(lblCorreo);

		JLabel lblLimiteDeCredito = new JLabel("Limite de credito");
		lblLimiteDeCredito.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLimiteDeCredito.setBounds(34, 181, 156, 19);
		frame.getContentPane().add(lblLimiteDeCredito);

		textNombre = new JTextField();
		textNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textNombre.getText().length() >= 25) {
					e.consume();
				}
			}
		});
		textNombre.setBounds(200, 108, 178, 25);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);

		textCorreo = new JTextField();
		textCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textCorreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textCorreo.getText().length() >= 30) {
					e.consume();
				}
			}
		});
		textCorreo.setColumns(10);
		textCorreo.setBounds(200, 143, 178, 25);
		frame.getContentPane().add(textCorreo);

		textLimiteCredito = new JTextField();
		textLimiteCredito.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textLimiteCredito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textLimiteCredito.getText().length() >= 7) {
					e.consume();
				}
			}
		});
		textLimiteCredito.setColumns(10);
		textLimiteCredito.setBounds(200, 178, 178, 25);
		frame.getContentPane().add(textLimiteCredito);

		btnRegresar = new JButton("");
		btnRegresar.setBounds(34, 237, 60, 60);
		cambiarIconoBotones(btnRegresar, "back2.png");
		frame.getContentPane().add(btnRegresar);

		btnRegistrar = new JButton("");
		btnRegistrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRegistrar.setBounds(178, 237, 60, 60);
		cambiarIconoBotones(btnRegistrar, "regis2.png");
		frame.getContentPane().add(btnRegistrar);
		btnRegistrar.setVisible(false);
		
		lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(104, 237, 64, 60);
		frame.getContentPane().add(lblAtras);
		
		lblRegistrarProducto = new JLabel("<html>Registrar Proveedor Nuevo<html>");
		lblRegistrarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegistrarProducto.setBounds(248, 237, 137, 60);
		frame.getContentPane().add(lblRegistrarProducto);
		lblRegistrarProducto.setVisible(false);
		
		btnEditar = new JButton("");
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEditar.setBounds(178, 237, 60, 60);
		cambiarIconoBotones(btnEditar,"pencil.png");
		frame.getContentPane().add(btnEditar);
		btnEditar.setVisible(false);
		
		lblEditar = new JLabel("Editar");
		lblEditar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEditar.setBounds(248, 237, 137, 60);
		frame.getContentPane().add(lblEditar);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 96, 426, 124);
		frame.getContentPane().add(panel_1);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 426, 53);
		frame.getContentPane().add(panel);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);
		lblEditar.setVisible(false);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 640, 480);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que quieres salir?", "Advertencia",
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

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_DEFAULT));
		boton.setIcon(Icono);
	}
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextNombre() {
		return textNombre;
	}

	public JTextField getTextCorreo() {
		return textCorreo;
	}

	public JTextField getTextLimiteCredito() {
		return textLimiteCredito;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public JLabel getLblAtras() {
		return lblAtras;
	}

	public JLabel getLblRegistrarProducto() {
		return lblRegistrarProducto;
	}
	
	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JLabel getLblEditar() {
		return lblEditar;
	}

	public JLabel getLblSubtitulo() {
		return lblSubtitulo;
	}
	
	
}
