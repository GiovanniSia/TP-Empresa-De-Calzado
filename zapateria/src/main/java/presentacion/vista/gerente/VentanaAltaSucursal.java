package presentacion.vista.gerente;

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
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;

public class VentanaAltaSucursal {

	private JFrame frame;

	private JTextField textNombre;

	private JTextField textTelefono;
	private JTextField textCalle;

	private JButton btnRegistrar;
	private JButton btnRegresar;
	private JLabel lblAtras;
	private JLabel lblRegistrar;
	
	private JButton btnEditar;
	private JLabel lblEditar;
	private JTextField textAltura;
	private JTextField textCodPostal;

	private JLabel lblNumeroDeSucursal;

	private JComboBox<String> comboBoxPais;
	private JComboBox<String> comboBoxProvincia;
	private JComboBox<String> comboBoxLocalidad;

	private JButton btnUbicacion;
	private JTextField textNroSucursal;
	private JPanel panel;
	private JLabel lblLogo;

	private JLabel lblTitulo;
	private JPanel panel_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaSucursal window = new VentanaAltaSucursal();
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
	public VentanaAltaSucursal() {
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
		frame.setBounds(500, 100, 442, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		JLabel lblSubtitulo = new JLabel("Registrar Sucursal");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblSubtitulo.setBounds(10, 47, 289, 51);
		frame.getContentPane().add(lblSubtitulo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombre.setBounds(20, 109, 156, 19);
		frame.getContentPane().add(lblNombre);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTelefono.setBounds(20, 145, 156, 19);
		frame.getContentPane().add(lblTelefono);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCalle.setBounds(20, 180, 156, 19);
		frame.getContentPane().add(lblCalle);

		textNombre = new JTextField();
		textNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textNombre.getText().length() >= 25) {
					e.consume();
				}
			}
		});
		textNombre.setBounds(186, 106, 178, 25);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);

		textTelefono = new JTextField();
		textTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textTelefono.getText().length() >= 18) {
					e.consume();
				}
			}
		});
		textTelefono.setColumns(10);
		textTelefono.setBounds(186, 142, 178, 25);
		frame.getContentPane().add(textTelefono);

		textCalle = new JTextField();
		textCalle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textCalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textCalle.getText().length() >= 25) {
					e.consume();
				}
			}
		});
		textCalle.setColumns(10);
		textCalle.setBounds(186, 177, 178, 25);
		frame.getContentPane().add(textCalle);

		btnRegresar = new JButton("");
		btnRegresar.setBounds(10, 477, 60, 60);
		cambiarIconoBotones(btnRegresar, "back2.png");
		frame.getContentPane().add(btnRegresar);

		btnRegistrar = new JButton("");
		btnRegistrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRegistrar.setBounds(212, 477, 60, 60);
		cambiarIconoBotones(btnRegistrar, "regis2.png");
		frame.getContentPane().add(btnRegistrar);
		btnRegistrar.setVisible(false);
		
		lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(88, 477, 112, 60);
		frame.getContentPane().add(lblAtras);
		
		lblRegistrar = new JLabel("<html>Registrar Sucursal Nueva<html>");
		lblRegistrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegistrar.setBounds(282, 477, 137, 60);
		frame.getContentPane().add(lblRegistrar);
		lblRegistrar.setVisible(false);
		
		btnEditar = new JButton("");
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEditar.setBounds(212, 477, 60, 60);
		cambiarIconoBotones(btnEditar,"pencil.png");
		frame.getContentPane().add(btnEditar);
		btnEditar.setVisible(false);
		
		lblEditar = new JLabel("Editar");
		lblEditar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEditar.setBounds(282, 466, 137, 60);
		frame.getContentPane().add(lblEditar);
		
		textAltura = new JTextField();
		textAltura.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textAltura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textAltura.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		textAltura.setColumns(10);
		textAltura.setBounds(186, 212, 178, 25);
		frame.getContentPane().add(textAltura);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAltura.setBounds(20, 215, 156, 19);
		frame.getContentPane().add(lblAltura);
		
		comboBoxPais = new JComboBox<String>();
		comboBoxPais.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxPais.setBounds(186, 247, 178, 25);
		frame.getContentPane().add(comboBoxPais);
		
		comboBoxProvincia = new JComboBox<String>();
		comboBoxProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxProvincia.setBounds(186, 288, 178, 25);
		frame.getContentPane().add(comboBoxProvincia);
		
		comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxLocalidad.setBounds(186, 324, 178, 25);
		frame.getContentPane().add(comboBoxLocalidad);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPais.setBounds(20, 250, 156, 19);
		frame.getContentPane().add(lblPais);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProvincia.setBounds(20, 285, 156, 19);
		frame.getContentPane().add(lblProvincia);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLocalidad.setBounds(20, 327, 156, 19);
		frame.getContentPane().add(lblLocalidad);
		
		textCodPostal = new JTextField();
		textCodPostal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textCodPostal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCodPostal.getText().length()>=10) {
                    e.consume();
                }
            }
        });
		textCodPostal.setColumns(10);
		textCodPostal.setBounds(186, 367, 178, 25);
		frame.getContentPane().add(textCodPostal);
		
		JLabel lblCodpostal = new JLabel("CodPostal");
		lblCodpostal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCodpostal.setBounds(20, 370, 156, 19);
		frame.getContentPane().add(lblCodpostal);
		
		btnUbicacion = new JButton("");
		btnUbicacion.setToolTipText("Editar ubicaciones disponibles");
		btnUbicacion.setBounds(376, 283, 30, 30);
		cambiarIconoBotones(btnUbicacion, "earth.png");
		frame.getContentPane().add(btnUbicacion);
		
		textNroSucursal = new JTextField();
		textNroSucursal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textNroSucursal.getText().length()>=8) {
                    e.consume();
                }
            }
        });
		textNroSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textNroSucursal.setColumns(10);
		textNroSucursal.setBounds(186, 408, 178, 25);
		frame.getContentPane().add(textNroSucursal);
		
		lblNumeroDeSucursal = new JLabel("Numero de Sucursal");
		lblNumeroDeSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNumeroDeSucursal.setBounds(20, 411, 156, 19);
		frame.getContentPane().add(lblNumeroDeSucursal);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, -3, 426, 53);
		frame.getContentPane().add(panel);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 100, 426, 344);
		frame.getContentPane().add(panel_1);
		lblEditar.setVisible(false);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 11, 800, 600);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

	public JTextField getTextTelefono() {
		return textTelefono;
	}

	public JTextField getTextCalle() {
		return textCalle;
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

	public JLabel getLblRegistrar() {
		return lblRegistrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JLabel getLblEditar() {
		return lblEditar;
	}

	public JTextField getTextAltura() {
		return textAltura;
	}

	public JTextField getTextCodPostal() {
		return textCodPostal;
	}
	public JComboBox<String> getComboBoxPais() {
		return comboBoxPais;
	}

	public JComboBox<String> getComboBoxProvincia() {
		return comboBoxProvincia;
	}

	public JComboBox<String> getComboBoxLocalidad() {
		return comboBoxLocalidad;
	}
	
	
	public JButton getBtnUbicacion() {
		return btnUbicacion;
	}
	


	public JTextField getTextNroSucursal() {
		return textNroSucursal;
	}
	
	public JLabel getLblNumeroDeSucursal() {
		return lblNumeroDeSucursal;
	}
}
