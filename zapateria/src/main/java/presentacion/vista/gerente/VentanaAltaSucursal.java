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

public class VentanaAltaSucursal {

	private JFrame frame;

	private JTextField textNombre;

	private JTextField textTelefono;
	private JTextField textCalle;

	private JButton btnRegistrar;
	private JButton btnRegresar;
	private JPanel panel;
	private JLabel lblTitulo;
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
		frame.setTitle("Zapateria Argento - Registrar un nuevo proveedor");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JLabel lblSubtitulo = new JLabel("Registrar Proveedor");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblSubtitulo.setBounds(10, 47, 403, 38);
		frame.getContentPane().add(lblSubtitulo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombre.setBounds(10, 95, 156, 19);
		frame.getContentPane().add(lblNombre);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTelefono.setBounds(10, 131, 156, 19);
		frame.getContentPane().add(lblTelefono);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCalle.setBounds(10, 166, 156, 19);
		frame.getContentPane().add(lblCalle);

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
		textNombre.setBounds(178, 93, 178, 25);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);

		textTelefono = new JTextField();
		textTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textTelefono.getText().length() >= 18) {
					e.consume();
				}
			}
		});
		textTelefono.setColumns(10);
		textTelefono.setBounds(176, 128, 178, 25);
		frame.getContentPane().add(textTelefono);

		textCalle = new JTextField();
		textCalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textCalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textCalle.getText().length() >= 25) {
					e.consume();
				}
			}
		});
		textCalle.setColumns(10);
		textCalle.setBounds(178, 163, 178, 25);
		frame.getContentPane().add(textCalle);

		btnRegresar = new JButton("");
		btnRegresar.setBounds(10, 477, 60, 60);
		cambiarIconoBotones(btnRegresar, "back2.png");
		frame.getContentPane().add(btnRegresar);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 426, 50);
		frame.getContentPane().add(panel);
		
		lblTitulo = new JLabel("Zapateria Argento");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setBounds(10, 0, 236, 50);
		panel.add(lblTitulo);

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
		textAltura.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textAltura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textAltura.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		textAltura.setColumns(10);
		textAltura.setBounds(178, 198, 178, 25);
		frame.getContentPane().add(textAltura);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAltura.setBounds(10, 201, 156, 19);
		frame.getContentPane().add(lblAltura);
		
		comboBoxPais = new JComboBox<String>();
		comboBoxPais.setBounds(178, 233, 178, 19);
		frame.getContentPane().add(comboBoxPais);
		
		comboBoxProvincia = new JComboBox<String>();
		comboBoxProvincia.setBounds(178, 268, 178, 19);
		frame.getContentPane().add(comboBoxProvincia);
		
		comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setBounds(178, 310, 178, 19);
		frame.getContentPane().add(comboBoxLocalidad);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPais.setBounds(10, 236, 156, 19);
		frame.getContentPane().add(lblPais);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProvincia.setBounds(10, 271, 156, 19);
		frame.getContentPane().add(lblProvincia);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLocalidad.setBounds(10, 313, 156, 19);
		frame.getContentPane().add(lblLocalidad);
		
		textCodPostal = new JTextField();
		textCodPostal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textCodPostal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCodPostal.getText().length()>=10) {
                    e.consume();
                }
            }
        });
		textCodPostal.setColumns(10);
		textCodPostal.setBounds(178, 349, 178, 25);
		frame.getContentPane().add(textCodPostal);
		
		JLabel lblCodpostal = new JLabel("CodPostal");
		lblCodpostal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCodpostal.setBounds(10, 356, 156, 19);
		frame.getContentPane().add(lblCodpostal);
		
		btnUbicacion = new JButton("Editar Ubicacion");
		btnUbicacion.setBounds(314, 61, 112, 21);
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
		textNroSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textNroSucursal.setColumns(10);
		textNroSucursal.setBounds(178, 394, 178, 25);
		frame.getContentPane().add(textNroSucursal);
		
		lblNumeroDeSucursal = new JLabel("Numero de Sucursal");
		lblNumeroDeSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNumeroDeSucursal.setBounds(10, 397, 156, 19);
		frame.getContentPane().add(lblNumeroDeSucursal);
		lblEditar.setVisible(false);
		
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
