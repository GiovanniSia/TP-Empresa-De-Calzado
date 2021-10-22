package presentacion.vista.Supervisor;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import persistencia.conexion.Conexion;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;

public class VentanaAltaProducto {

	private JFrame frame;
	
	

	private JTextField textDescripcion;
	
	private JTextField textCosto;
	private JTextField textPrecioMayorista;
	private JTextField textPrecioMinorista;
	private JTextField textPuntoRepMinimo;
	private JTextField textTalle;
	private JTextField textUnidadMedida;
	private JTextField textDiasParaReponer;
	private JTextField textCantidadAReponer;
	
	private JButton btnRegistrar;
	private JButton btnRegresar;
	private JButton btnBorrarProveedor;

	private JComboBox<String> comboBoxEstado;
	private JComboBox<String> comboBoxFabricado;
	private JComboBox<String> comboBoxTipo;

	private JLabel lblCantidadAReponer;
	private JButton btnElegirProveedor;
	private JLabel lblProveedorElegido;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaProducto window = new VentanaAltaProducto();
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
	public VentanaAltaProducto() {
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
		frame.setBounds(500, 100, 427, 656);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Zapateria Argento - Registrar un nuevo producto");
		frame.setResizable(false);
		
		
		
		JLabel lblNewLabel = new JLabel("Registrar Producto");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 403, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_1.setBounds(82, 50, 93, 19);
		frame.getContentPane().add(lblNewLabel_1);
		

		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipo.setFont(new Font("Consolas", Font.BOLD, 14));
		lblTipo.setBounds(125, 90, 50, 19);
		frame.getContentPane().add(lblTipo);
		

		
		JLabel lblProductoPropio = new JLabel("Producto Propio?");
		lblProductoPropio.setHorizontalAlignment(SwingConstants.LEFT);
		lblProductoPropio.setFont(new Font("Consolas", Font.BOLD, 14));
		lblProductoPropio.setBounds(32, 130, 143, 19);
		frame.getContentPane().add(lblProductoPropio);
		

		
		JLabel lblCostoDeProduccion = new JLabel("Costo de Produccion");
		lblCostoDeProduccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblCostoDeProduccion.setFont(new Font("Consolas", Font.BOLD, 14));
		lblCostoDeProduccion.setBounds(20, 172, 165, 19);
		frame.getContentPane().add(lblCostoDeProduccion);
		

		
		JLabel lblPrecioMayorista = new JLabel("Precio Mayorista");
		lblPrecioMayorista.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioMayorista.setFont(new Font("Consolas", Font.BOLD, 14));
		lblPrecioMayorista.setBounds(32, 213, 143, 19);
		frame.getContentPane().add(lblPrecioMayorista);
		

		
		JLabel lblPrecioMinorista = new JLabel("Precio Minorista");
		lblPrecioMinorista.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioMinorista.setFont(new Font("Consolas", Font.BOLD, 14));
		lblPrecioMinorista.setBounds(32, 255, 143, 19);
		frame.getContentPane().add(lblPrecioMinorista);
		

		
		JLabel lblPuntoDeReposicion = new JLabel("Punto de reposicion minimo");
		lblPuntoDeReposicion.setHorizontalAlignment(SwingConstants.LEFT);
		lblPuntoDeReposicion.setFont(new Font("Consolas", Font.BOLD, 13));
		lblPuntoDeReposicion.setBounds(32, 295, 193, 19);
		frame.getContentPane().add(lblPuntoDeReposicion);
		

		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setHorizontalAlignment(SwingConstants.LEFT);
		lblProveedor.setFont(new Font("Consolas", Font.BOLD, 14));
		lblProveedor.setBounds(32, 355, 79, 19);
		frame.getContentPane().add(lblProveedor);
		

		
		JLabel lblTalle = new JLabel("Talle");
		lblTalle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTalle.setFont(new Font("Consolas", Font.BOLD, 14));
		lblTalle.setBounds(125, 407, 56, 19);
		frame.getContentPane().add(lblTalle);
		

		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnidadDeMedida.setFont(new Font("Consolas", Font.BOLD, 14));
		lblUnidadDeMedida.setBounds(32, 436, 143, 19);
		frame.getContentPane().add(lblUnidadDeMedida);
		

		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstado.setFont(new Font("Consolas", Font.BOLD, 14));
		lblEstado.setBounds(119, 465, 56, 19);
		frame.getContentPane().add(lblEstado);
		

		
		JLabel lblDiasParaReponer = new JLabel("Dias para reponer");
		lblDiasParaReponer.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiasParaReponer.setFont(new Font("Consolas", Font.BOLD, 14));
		lblDiasParaReponer.setBounds(32, 523, 143, 19);
		frame.getContentPane().add(lblDiasParaReponer);
	
		textDescripcion = new JTextField();
		textDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textDescripcion.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		textDescripcion.setBounds(178, 49, 178, 19);
		frame.getContentPane().add(textDescripcion);
		textDescripcion.setColumns(10);
		
		textCosto = new JTextField();
		textCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textCosto.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		textCosto.setColumns(10);
		textCosto.setBounds(178, 171, 178, 19);
		frame.getContentPane().add(textCosto);
		
		textPrecioMayorista = new JTextField();
		textPrecioMayorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textPrecioMayorista.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		textPrecioMayorista.setColumns(10);
		textPrecioMayorista.setBounds(178, 212, 178, 19);
		frame.getContentPane().add(textPrecioMayorista);
		
		textPrecioMinorista = new JTextField();
		textPrecioMinorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textPrecioMinorista.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		textPrecioMinorista.setColumns(10);
		textPrecioMinorista.setBounds(178, 254, 178, 19);
		frame.getContentPane().add(textPrecioMinorista);
		
		textPuntoRepMinimo = new JTextField();
		textPuntoRepMinimo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textPuntoRepMinimo.getText().length()>=10) {
                    e.consume();
                }
			}
		});
		textPuntoRepMinimo.setColumns(10);
		textPuntoRepMinimo.setBounds(235, 293, 178, 19);
		frame.getContentPane().add(textPuntoRepMinimo);
		
		textTalle = new JTextField();
		textTalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textTalle.getText().length()>=10) {
                    e.consume();
                }
			}
		});
		textTalle.setColumns(10);
		textTalle.setBounds(178, 406, 178, 19);
		frame.getContentPane().add(textTalle);
		
		textCantidadAReponer = new JTextField();
		textCantidadAReponer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textCantidadAReponer.getText().length()>=10) {
                    e.consume();
                }
			}
		});
		textCantidadAReponer.setColumns(10);
		textCantidadAReponer.setBounds(178, 493, 178, 19);
		frame.getContentPane().add(textCantidadAReponer);
		
		
		textDiasParaReponer = new JTextField();
		textDiasParaReponer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textDiasParaReponer.getText().length()>=10) {
                    e.consume();
                }
			}
		});
		textDiasParaReponer.setColumns(10);
		textDiasParaReponer.setBounds(178, 522, 178, 19);
		frame.getContentPane().add(textDiasParaReponer);
		
		textUnidadMedida = new JTextField();
		textUnidadMedida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textUnidadMedida.getText().length()>=20) {
                    e.consume();
                }
			}
		});
		textUnidadMedida.setColumns(10);
		textUnidadMedida.setBounds(178, 435, 178, 19);
		frame.getContentPane().add(textUnidadMedida);
		
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(10, 553, 50, 58);
//		btnRegresar.setIcon(setIcono("../imagenes/back.png",btnRegresar));
		frame.getContentPane().add(btnRegresar);
		
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(135, 578, 184, 33);
		frame.getContentPane().add(btnRegistrar);
		
		
		comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setBounds(178, 464, 178, 19);
		comboBoxEstado.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxEstado);
		
		comboBoxFabricado = new JComboBox<String>();
		comboBoxFabricado.setBounds(178, 129, 178, 19);
		comboBoxFabricado.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxFabricado);
		
		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setBounds(178, 89, 178, 19);
		comboBoxTipo.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxTipo);
		

		
		lblCantidadAReponer = new JLabel("Cantidad a reponer");
		lblCantidadAReponer.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadAReponer.setFont(new Font("Consolas", Font.BOLD, 14));
		lblCantidadAReponer.setBounds(10, 494, 165, 19);
		frame.getContentPane().add(lblCantidadAReponer);
		
		btnElegirProveedor = new JButton("Elegir proveedor");
		btnElegirProveedor.setBounds(268, 354, 113, 19);
		frame.getContentPane().add(btnElegirProveedor);
		
		lblProveedorElegido = new JLabel("Sin seleccionar");
		lblProveedorElegido.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblProveedorElegido.setBounds(125, 355, 143, 19);
		frame.getContentPane().add(lblProveedorElegido);
		
		btnBorrarProveedor = new JButton("");
		btnBorrarProveedor.setBounds(380, 341, 33, 33);
//		btnBorrarProveedor.setIcon(setIcono("../imagenes/trash.png",btnBorrarProveedor));
		frame.getContentPane().add(btnBorrarProveedor);
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
	
	public Icon setIcono(String url,JButton boton) {
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
	}
	
	
	
	
	public JTextField getTextDescripcion() {
		return textDescripcion;
	}

	public JTextField getTextCosto() {
		return textCosto;
	}

	public JTextField getTextPrecioMayorista() {
		return textPrecioMayorista;
	}

	public JTextField getTextPrecioMinorista() {
		return textPrecioMinorista;
	}

	public JTextField getTextPuntoRepMinimo() {
		return textPuntoRepMinimo;
	}

	public JTextField getTextTalle() {
		return textTalle;
	}

	public JTextField getTextUnidadMedida() {
		return textUnidadMedida;
	}

	public JTextField getTextDiasParaReponer() {
		return textDiasParaReponer;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JComboBox<String> JComboBox() {
		return comboBoxEstado;
	}


	public JComboBox<String> getComboBoxFabricado() {
		return comboBoxFabricado;
	}

	public JComboBox<String> getComboBoxTipo() {
		return comboBoxTipo;
	}
	public JTextField getTextCantidadAReponer() {
		return textCantidadAReponer;
	}
	public JButton getBtnElegirProveedor() {
		return btnElegirProveedor;
	}

	public JLabel getLblProveedorElegido() {
		return lblProveedorElegido;
	}

	public void setLblProveedorElegido(JLabel lblProveedorElegido) {
		this.lblProveedorElegido = lblProveedorElegido;
	}
	
	public JButton getBtnBorrarProveedor() {
		return btnBorrarProveedor;
	}

	
	
	public JFrame getFrame() {
		return frame;
	}
}
