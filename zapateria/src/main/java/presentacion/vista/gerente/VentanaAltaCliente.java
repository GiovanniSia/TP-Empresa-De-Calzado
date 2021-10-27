package presentacion.vista.gerente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import persistencia.conexion.Conexion;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;

public class VentanaAltaCliente {

	private JFrame frame;
	
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCUIL;
	private JTextField textCorreo;
	private JTextField textPais;
	private JTextField textProvincia;
	private JTextField textLocalidad;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textCodPostal;

	
	private JButton btnRegistrar;
	private JButton btnCancelar;

	private JComboBox<String> comboBoxImpuestoAFIP;
	private JComboBox<String> comboBoxTipoCliente;
	private JTextField textSaldoInicial;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblAtras;
	private JLabel lblRegistrarCliente_1;
	private JLabel lblSaldoInicial_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaCliente window = new VentanaAltaCliente();
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
	public VentanaAltaCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(500, 100, 685, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Zapateria Argento - Registrar un cliente");
		frame.setResizable(false);
		
		JLabel lblRegistrarCliente = new JLabel("Registrar Cliente");
		lblRegistrarCliente.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblRegistrarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrarCliente.setBounds(10, 51, 393, 50);
		frame.getContentPane().add(lblRegistrarCliente);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombre.setBounds(10, 114, 108, 18);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblApellido.setBounds(10, 142, 108, 19);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblCUIL = new JLabel("CUIL/CUIT");
		lblCUIL.setHorizontalAlignment(SwingConstants.LEFT);
		lblCUIL.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCUIL.setBounds(10, 169, 108, 24);
		frame.getContentPane().add(lblCUIL);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCorreo.setBounds(10, 203, 108, 24);
		frame.getContentPane().add(lblCorreo);
		
		JLabel lblTipoCliente = new JLabel("Tipo de Cliente");
		lblTipoCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoCliente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTipoCliente.setBounds(10, 246, 108, 24);
		frame.getContentPane().add(lblTipoCliente);
		
		
		
		JLabel lblImpuestoAFIP = new JLabel("Impuesto AFIP");
		lblImpuestoAFIP.setHorizontalAlignment(SwingConstants.LEFT);
		lblImpuestoAFIP.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblImpuestoAFIP.setBounds(10, 281, 108, 18);
		frame.getContentPane().add(lblImpuestoAFIP);
		

		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setHorizontalAlignment(SwingConstants.LEFT);
		lblPais.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPais.setBounds(386, 112, 94, 24);
		frame.getContentPane().add(lblPais);
		
		JLabel lblProv = new JLabel("Provincia");
		lblProv.setHorizontalAlignment(SwingConstants.LEFT);
		lblProv.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProv.setBounds(386, 150, 116, 24);
		frame.getContentPane().add(lblProv);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLocalidad.setBounds(386, 189, 116, 24);
		frame.getContentPane().add(lblLocalidad);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCalle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCalle.setBounds(386, 246, 116, 24);
		frame.getContentPane().add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setHorizontalAlignment(SwingConstants.LEFT);
		lblAltura.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAltura.setBounds(386, 280, 116, 24);
		frame.getContentPane().add(lblAltura);
		
		JLabel lblCodPostal = new JLabel("Cod Postal");
		lblCodPostal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodPostal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCodPostal.setBounds(386, 313, 116, 24);
		frame.getContentPane().add(lblCodPostal);
		
		
		textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textNombre.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		textNombre.setBounds(128, 109, 195, 23);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textApellido.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		textApellido.setColumns(10);
		textApellido.setBounds(128, 141, 195, 23);
		frame.getContentPane().add(textApellido);
		
		textCUIL = new JTextField();
		textCUIL.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCUIL.getText().length()>=12) {
                    e.consume();
                }
            }
        });
		textCUIL.setColumns(10);
		textCUIL.setBounds(128, 171, 195, 23);
		frame.getContentPane().add(textCUIL);
		
		
		textCorreo = new JTextField();
		textCorreo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCorreo.getText().length()>=20) {
                    e.consume();
                }
            }
        });
		textCorreo.setColumns(10);
		textCorreo.setBounds(128, 205, 195, 23);
		frame.getContentPane().add(textCorreo);
		
		textPais = new JTextField();
		textPais.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textPais.getText().length()>=20) {
                    e.consume();
                }
            }
        });
		textPais.setColumns(10);
		textPais.setBounds(458, 114, 195, 23);
		frame.getContentPane().add(textPais);
		
		textProvincia = new JTextField();
		textProvincia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textProvincia.getText().length()>=20) {
                    e.consume();
                }
            }
        });
		textProvincia.setColumns(10);
		textProvincia.setBounds(458, 153, 195, 23);
		frame.getContentPane().add(textProvincia);
		
		textLocalidad = new JTextField();
		textLocalidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textLocalidad.getText().length()>=20) {
                    e.consume();
                }
            }
        });
		textLocalidad.setColumns(10);
		textLocalidad.setBounds(458, 192, 195, 23);
		frame.getContentPane().add(textLocalidad);
		
		textCalle = new JTextField();
		textCalle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCalle.getText().length()>=20) {
                    e.consume();
                }
            }
        });
		textCalle.setColumns(10);
		textCalle.setBounds(458, 249, 195, 23);
		frame.getContentPane().add(textCalle);
		
		textAltura = new JTextField();
		textAltura.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textAltura.getText().length()>=10) {
                    e.consume();
                }
            }
        });
		textAltura.setColumns(10);
		textAltura.setBounds(458, 281, 137, 23);
		frame.getContentPane().add(textAltura);
		
		textCodPostal = new JTextField();
		textCodPostal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCodPostal.getText().length()>=6) {
                    e.consume();
                }
            }
        });
		textCodPostal.setColumns(10);
		textCodPostal.setBounds(458, 313, 137, 23);
		frame.getContentPane().add(textCodPostal);
		
		btnRegistrar = new JButton("");
		btnRegistrar.setBounds(358, 394, 60, 60);
		cambiarIconoBotones(btnRegistrar,  "registrar.png");
		frame.getContentPane().add(btnRegistrar);
		
		btnCancelar = new JButton("");
		btnCancelar.setBounds(101, 394, 60, 60);
		cambiarIconoBotones(btnCancelar,  "back2.png");
		frame.getContentPane().add(btnCancelar);
		
		comboBoxImpuestoAFIP = new JComboBox<String>();
		comboBoxImpuestoAFIP.setBounds(128, 278, 195, 23);
		this.comboBoxImpuestoAFIP.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxImpuestoAFIP);
		
		comboBoxTipoCliente = new JComboBox<String>();
		comboBoxTipoCliente.setBounds(128, 247, 195, 23);
		this.comboBoxTipoCliente.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxTipoCliente);
		
		textSaldoInicial = new JTextField();
		textSaldoInicial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textSaldoInicial.getText().length()>=15) {
                    e.consume();
                }
            }
        });
		textSaldoInicial.setColumns(10);
		textSaldoInicial.setBounds(158, 313, 165, 23);
		frame.getContentPane().add(textSaldoInicial);
		
		JLabel lblSaldoInicial = new JLabel("Credito disponible");
		lblSaldoInicial.setHorizontalAlignment(SwingConstants.LEFT);
		lblSaldoInicial.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSaldoInicial.setBounds(10, 311, 108, 24);
		frame.getContentPane().add(lblSaldoInicial);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(-1, 0, 670, 50);
		frame.getContentPane().add(panel);
		
		lblNewLabel = new JLabel("Zapateria Argento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 0, 421, 50);
		panel.add(lblNewLabel);
		
		lblAtras = new JLabel("Atras");
		lblAtras.setHorizontalAlignment(SwingConstants.LEFT);
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(171, 394, 60, 60);
		frame.getContentPane().add(lblAtras);
		
		lblRegistrarCliente_1 = new JLabel("Registrar Cliente");
		lblRegistrarCliente_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrarCliente_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegistrarCliente_1.setBounds(428, 394, 137, 60);
		frame.getContentPane().add(lblRegistrarCliente_1);
		
		lblSaldoInicial_1 = new JLabel("$");
		lblSaldoInicial_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSaldoInicial_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSaldoInicial_1.setBounds(128, 311, 21, 24);
		frame.getContentPane().add(lblSaldoInicial_1);
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?", 
		             "Advertencia", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
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
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextNombre() {
		return textNombre;
	}

	public JTextField getTextApellido() {
		return textApellido;
	}

	public JTextField getTextCUIL() {
		return textCUIL;
	}

	public JTextField getTextCorreo() {
		return textCorreo;
	}

	public JTextField getTextPais() {
		return textPais;
	}

	public JTextField getTextProvincia() {
		return textProvincia;
	}

	public JTextField getTextLocalidad() {
		return textLocalidad;
	}

	public JTextField getTextCalle() {
		return textCalle;
	}

	public JTextField getTextAltura() {
		return textAltura;
	}

	public JTextField getTextCodPostal() {
		return textCodPostal;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	public JComboBox<String> getComboBoxImpuestoAFIP() {
		return comboBoxImpuestoAFIP;
	}

	public JComboBox<String> getComboBoxTipoCliente() {
		return comboBoxTipoCliente;
	}
	public JTextField getTextSaldoInicial() {
		return textSaldoInicial;
	}

}
