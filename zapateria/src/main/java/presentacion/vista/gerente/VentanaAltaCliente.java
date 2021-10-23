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
		frame.setBounds(500, 100, 427, 712);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Zapateria Argento - Registrar un cliente");
		frame.setResizable(false);
		
		JLabel lblRegistrarCliente = new JLabel("Registrar Cliente");
		lblRegistrarCliente.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		lblRegistrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarCliente.setBounds(10, 10, 393, 50);
		frame.getContentPane().add(lblRegistrarCliente);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNombre.setBounds(10, 114, 94, 18);
		frame.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textNombre.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		textNombre.setBounds(128, 113, 275, 19);
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
		textApellido.setBounds(128, 142, 275, 19);
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
		textCUIL.setBounds(128, 171, 275, 19);
		frame.getContentPane().add(textCUIL);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setFont(new Font("Consolas", Font.BOLD, 15));
		lblApellido.setBounds(10, 142, 94, 19);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblCUIL = new JLabel("Cuil");
		lblCUIL.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCUIL.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCUIL.setBounds(10, 169, 94, 24);
		frame.getContentPane().add(lblCUIL);
		
		textCorreo = new JTextField();
		textCorreo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCorreo.getText().length()>=30) {
                    e.consume();
                }
            }
        });
		textCorreo.setColumns(10);
		textCorreo.setBounds(128, 206, 275, 19);
		frame.getContentPane().add(textCorreo);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCorreo.setBounds(10, 203, 94, 24);
		frame.getContentPane().add(lblCorreo);
		
		JLabel lblTipoCliente = new JLabel("Tipo de Cliente");
		lblTipoCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoCliente.setFont(new Font("Consolas", Font.BOLD, 15));
		lblTipoCliente.setBounds(-1, 247, 127, 24);
		frame.getContentPane().add(lblTipoCliente);
		
		
		
		JLabel lblImpuestoAFIP = new JLabel("Impuesto AFIP");
		lblImpuestoAFIP.setHorizontalAlignment(SwingConstants.LEFT);
		lblImpuestoAFIP.setFont(new Font("Consolas", Font.BOLD, 15));
		lblImpuestoAFIP.setBounds(10, 281, 116, 18);
		frame.getContentPane().add(lblImpuestoAFIP);
		

		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setFont(new Font("Consolas", Font.BOLD, 15));
		lblPais.setBounds(10, 359, 94, 24);
		frame.getContentPane().add(lblPais);
		
		JLabel lblProv = new JLabel("Provincia");
		lblProv.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProv.setFont(new Font("Consolas", Font.BOLD, 15));
		lblProv.setBounds(10, 398, 116, 24);
		frame.getContentPane().add(lblProv);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocalidad.setFont(new Font("Consolas", Font.BOLD, 15));
		lblLocalidad.setBounds(10, 432, 116, 24);
		frame.getContentPane().add(lblLocalidad);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCalle.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCalle.setBounds(10, 466, 116, 24);
		frame.getContentPane().add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAltura.setFont(new Font("Consolas", Font.BOLD, 15));
		lblAltura.setBounds(10, 500, 116, 24);
		frame.getContentPane().add(lblAltura);
		
		JLabel lblCodPostal = new JLabel("Cod Postal");
		lblCodPostal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodPostal.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCodPostal.setBounds(10, 533, 116, 24);
		frame.getContentPane().add(lblCodPostal);
		
		textPais = new JTextField();
		textPais.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textPais.getText().length()>=25) {
                    e.consume();
                }
            }
        });
		textPais.setColumns(10);
		textPais.setBounds(128, 361, 275, 19);
		frame.getContentPane().add(textPais);
		
		textProvincia = new JTextField();
		textProvincia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textProvincia.getText().length()>=25) {
                    e.consume();
                }
            }
        });
		textProvincia.setColumns(10);
		textProvincia.setBounds(128, 400, 275, 19);
		frame.getContentPane().add(textProvincia);
		
		textLocalidad = new JTextField();
		textLocalidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textLocalidad.getText().length()>=25) {
                    e.consume();
                }
            }
        });
		textLocalidad.setColumns(10);
		textLocalidad.setBounds(128, 434, 275, 19);
		frame.getContentPane().add(textLocalidad);
		
		textCalle = new JTextField();
		textCalle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCalle.getText().length()>=25) {
                    e.consume();
                }
            }
        });
		textCalle.setColumns(10);
		textCalle.setBounds(128, 468, 275, 19);
		frame.getContentPane().add(textCalle);
		
		textAltura = new JTextField();
		textAltura.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textAltura.getText().length()>=25) {
                    e.consume();
                }
            }
        });
		textAltura.setColumns(10);
		textAltura.setBounds(128, 500, 137, 19);
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
		textCodPostal.setBounds(128, 535, 137, 19);
		frame.getContentPane().add(textCodPostal);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(128, 592, 179, 35);
		frame.getContentPane().add(btnRegistrar);
		
		btnCancelar = new JButton("");
		btnCancelar.setBounds(10, 628, 40, 44);
		cambiarIconoBotones(btnCancelar,  "back.png");
		frame.getContentPane().add(btnCancelar);
		
		comboBoxImpuestoAFIP = new JComboBox<String>();
		comboBoxImpuestoAFIP.setBounds(128, 278, 198, 23);
		this.comboBoxImpuestoAFIP.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxImpuestoAFIP);
		
		comboBoxTipoCliente = new JComboBox<String>();
		comboBoxTipoCliente.setBounds(128, 247, 198, 23);
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
		textSaldoInicial.setBounds(128, 324, 275, 19);
		frame.getContentPane().add(textSaldoInicial);
		
		JLabel lblSaldoInicial = new JLabel("Credito disponible");
		lblSaldoInicial.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldoInicial.setFont(new Font("Consolas", Font.BOLD, 11));
		lblSaldoInicial.setBounds(-1, 325, 127, 24);
		frame.getContentPane().add(lblSaldoInicial);
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
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_DEFAULT));
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
