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
import java.awt.event.WindowListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class VentanaAltaCliente {

	private JFrame frame;
	
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCUIL;
	private JTextField textCorreo;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textCodPostal;

	
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JButton btnEditar;

	private JComboBox<String> comboBoxImpuestoAFIP;
	private JComboBox<String> comboBoxTipoCliente;
	private JTextField textSaldoInicial;
	private JLabel lblAtras;
	private JLabel lblRegistrarCliente;

	private JLabel lblSaldoInicial_1;
	
	private JComboBox<String> comboBoxLocalidad;
	private JComboBox<String> comboBoxProvincia;
	private JComboBox<String> comboBoxPais;
	private JButton btnUbicacion;
	
	private JLabel lblLimiteDeCredito;
	private JTextField textLimiteCredito;
	private JLabel lblEstado;
	private JComboBox<String> comboBoxEstado;	
	private JPanel panel;
	private JLabel lblTitulo;

	private JLabel lblEditarCliente;
	
	
	
	
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
		frame.setBounds(500, 100, 682, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Zapateria Argento - Registrar un cliente");
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
//		JLabel lblRegistrarCliente = new JLabel("Registrar Cliente");
//		lblRegistrarCliente.setFont(new Font("Segoe UI", Font.BOLD, 22));
//		lblRegistrarCliente.setHorizontalAlignment(SwingConstants.LEFT);
//		lblRegistrarCliente.setBounds(10, 51, 393, 50);
//		frame.getContentPane().add(lblRegistrarCliente);
		
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
		textNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
		textApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
		textCUIL.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textCUIL.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCUIL.getText().length()>=11) {
                    e.consume();
                }
            }
        });
		textCUIL.setColumns(10);
		textCUIL.setBounds(128, 171, 195, 23);
		frame.getContentPane().add(textCUIL);
		
		
		textCorreo = new JTextField();
		textCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textCorreo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCorreo.getText().length()>=30) {
                    e.consume();
                }
            }
        });
		textCorreo.setColumns(10);
		textCorreo.setBounds(128, 205, 195, 23);
		frame.getContentPane().add(textCorreo);
		
		textCalle = new JTextField();
		textCalle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textCalle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCalle.getText().length()>=25) {
                    e.consume();
                }
            }
        });
		textCalle.setColumns(10);
		textCalle.setBounds(458, 249, 195, 23);
		frame.getContentPane().add(textCalle);
		
		textAltura = new JTextField();
		textAltura.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
		textCodPostal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
		
		
		
		btnCancelar = new JButton("");
		btnCancelar.setBounds(23, 402, 60, 60);
		cambiarIconoBotones(btnCancelar,  "back2.png");
		frame.getContentPane().add(btnCancelar);
		
		comboBoxImpuestoAFIP = new JComboBox<String>();
		comboBoxImpuestoAFIP.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxImpuestoAFIP.setBounds(128, 278, 195, 23);
		frame.getContentPane().add(comboBoxImpuestoAFIP);
		
		comboBoxTipoCliente = new JComboBox<String>();
		comboBoxTipoCliente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxTipoCliente.setBounds(128, 247, 195, 23);
		frame.getContentPane().add(comboBoxTipoCliente);
		
		textSaldoInicial = new JTextField();
		textSaldoInicial.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textSaldoInicial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textSaldoInicial.getText().length()>=10) {
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
		
		lblAtras = new JLabel("Atras");
		lblAtras.setHorizontalAlignment(SwingConstants.LEFT);
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(93, 402, 60, 60);
		frame.getContentPane().add(lblAtras);
				
		lblSaldoInicial_1 = new JLabel("$");
		lblSaldoInicial_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSaldoInicial_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSaldoInicial_1.setBounds(128, 311, 21, 24);
		frame.getContentPane().add(lblSaldoInicial_1);
		
		btnUbicacion = new JButton("");
		btnUbicacion.setToolTipText("Editar ubicaciones disponibles");
		btnUbicacion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnUbicacion.setBounds(623, 150, 30, 30);
		cambiarIconoBotones(btnUbicacion,  "earth.png");
		frame.getContentPane().add(btnUbicacion);
		
		comboBoxPais = new JComboBox<String>();
		comboBoxPais.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxPais.setBounds(451, 109, 165, 23);
		frame.getContentPane().add(comboBoxPais);
		
		comboBoxProvincia = new JComboBox<String>();
		comboBoxProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxProvincia.setBounds(451, 153, 165, 23);
		frame.getContentPane().add(comboBoxProvincia);
		
		comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxLocalidad.setBounds(451, 192, 165, 23);
		frame.getContentPane().add(comboBoxLocalidad);
		
		
		lblRegistrarCliente = new JLabel("Registrar Cliente");
		lblRegistrarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegistrarCliente.setBounds(529, 402, 137, 60);
		frame.getContentPane().add(lblRegistrarCliente);
		lblRegistrarCliente.setVisible(false);
		
		btnRegistrar = new JButton("");
		btnRegistrar.setBounds(459, 402, 60, 60);
		cambiarIconoBotones(btnRegistrar,  "person+.png");
		frame.getContentPane().add(btnRegistrar);
		btnRegistrar.setVisible(false);
		
		btnEditar = new JButton("");
		btnEditar.setBounds(229, 402, 60, 60);
		frame.getContentPane().add(btnEditar);
		cambiarIconoBotones(btnEditar,  "personedit.png");
		btnEditar.setVisible(false);
		
		textLimiteCredito = new JTextField();
		textLimiteCredito.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textLimiteCredito.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textLimiteCredito.getText().length()>=10) {
                    e.consume();
                }
            }
        });		
		textLimiteCredito.setColumns(10);
		textLimiteCredito.setBounds(158, 346, 165, 23);
		frame.getContentPane().add(textLimiteCredito);
		textLimiteCredito.setVisible(false);
		
		lblLimiteDeCredito = new JLabel("Limite de credito");
		lblLimiteDeCredito.setHorizontalAlignment(SwingConstants.LEFT);
		lblLimiteDeCredito.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLimiteDeCredito.setBounds(10, 345, 108, 24);
		frame.getContentPane().add(lblLimiteDeCredito);
		lblLimiteDeCredito.setVisible(false);
		
		comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxEstado.setBounds(458, 347, 195, 23);
		frame.getContentPane().add(comboBoxEstado);
		comboBoxEstado.setVisible(false);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEstado.setBounds(386, 347, 116, 24);
		frame.getContentPane().add(lblEstado);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 666, 53);
		frame.getContentPane().add(panel_2);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel_2.add(lblLogo);
		
		lblEditarCliente = new JLabel("Editar Cliente");
		lblEditarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEditarCliente.setBounds(299, 402, 127, 60);
		frame.getContentPane().add(lblEditarCliente);
		lblEditarCliente.setVisible(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 97, 666, 294);
		frame.getContentPane().add(panel);
		
		lblTitulo = new JLabel("Registrar Cliente");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setBounds(10, 50, 312, 53);
		frame.getContentPane().add(lblTitulo);
		lblEstado.setVisible(false);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 11, 800, 600);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		for (WindowListener listener : this.frame.getWindowListeners())
	    {
			this.frame.removeWindowListener(listener);
	    }
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

	public JTextField getTextApellido() {
		return textApellido;
	}

	public JTextField getTextCUIL() {
		return textCUIL;
	}

	public JTextField getTextCorreo() {
		return textCorreo;
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
	
	
	
	
	public JComboBox<String> getComboBoxLocalidad() {
		return comboBoxLocalidad;
	}

	public JComboBox<String> getComboBoxProvincia() {
		return comboBoxProvincia;
	}

	public JComboBox<String> getComboBoxPais() {
		return comboBoxPais;
	}

	public JButton getBtnUbicacion() {
		return btnUbicacion;
	}
	
	public JButton getBtnEditar() {
		return btnEditar;
	}
	public JLabel getLblRegistrarCliente() {
		return lblRegistrarCliente;
	}

	public JLabel getLblLimiteDeCredito() {
		return lblLimiteDeCredito;
	}

	public JTextField getTextLimiteCredito() {
		return textLimiteCredito;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox<String> getComboBoxEstado() {
		return comboBoxEstado;
	}
	
	public JLabel getLblEditarCliente() {
		return lblEditarCliente;
	}
	public JLabel getLblTitulo() {
		return lblTitulo;
	}
}
