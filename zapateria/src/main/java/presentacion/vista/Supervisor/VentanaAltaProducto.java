package presentacion.vista.Supervisor;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import java.awt.Color;
import javax.swing.JPanel;

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
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel lblAtras;
	private JLabel lblRegistrarProducto;

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
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(500, 100, 442, 705);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Zapateria Argento - Registrar un nuevo producto");
		frame.setResizable(false);

		JLabel lblNewLabel = new JLabel("Registrar Producto");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 47, 403, 38);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(20, 93, 156, 19);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTipo.setBounds(20, 133, 156, 19);
		frame.getContentPane().add(lblTipo);

		JLabel lblProductoPropio = new JLabel("Producto Propio?");
		lblProductoPropio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProductoPropio.setBounds(20, 173, 156, 19);
		frame.getContentPane().add(lblProductoPropio);

		JLabel lblCostoDeProduccion = new JLabel("Costo de Produccion");
		lblCostoDeProduccion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCostoDeProduccion.setBounds(20, 216, 156, 19);
		frame.getContentPane().add(lblCostoDeProduccion);

		JLabel lblPrecioMayorista = new JLabel("Precio Mayorista");
		lblPrecioMayorista.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPrecioMayorista.setBounds(20, 256, 156, 19);
		frame.getContentPane().add(lblPrecioMayorista);

		JLabel lblPrecioMinorista = new JLabel("Precio Minorista");
		lblPrecioMinorista.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPrecioMinorista.setBounds(20, 298, 156, 19);
		frame.getContentPane().add(lblPrecioMinorista);

		JLabel lblPuntoDeReposicion = new JLabel("Punto de reposicion minimo");
		lblPuntoDeReposicion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPuntoDeReposicion.setBounds(20, 339, 156, 19);
		frame.getContentPane().add(lblPuntoDeReposicion);

		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProveedor.setBounds(10, 383, 79, 32);
		frame.getContentPane().add(lblProveedor);

		JLabel lblTalle = new JLabel("Talle");
		lblTalle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTalle.setBounds(20, 427, 148, 19);
		frame.getContentPane().add(lblTalle);

		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblUnidadDeMedida.setBounds(20, 458, 148, 19);
		frame.getContentPane().add(lblUnidadDeMedida);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEstado.setBounds(20, 487, 148, 19);
		frame.getContentPane().add(lblEstado);

		JLabel lblDiasParaReponer = new JLabel("Dias para reponer");
		lblDiasParaReponer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDiasParaReponer.setBounds(20, 543, 148, 19);
		frame.getContentPane().add(lblDiasParaReponer);

		textDescripcion = new JTextField();
		textDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textDescripcion.getText().length() >= 25) {
					e.consume();
				}
			}
		});
		textDescripcion.setBounds(178, 93, 178, 25);
		frame.getContentPane().add(textDescripcion);
		textDescripcion.setColumns(10);

		textCosto = new JTextField();
		textCosto.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textCosto.getText().length() >= 25) {
					e.consume();
				}
			}
		});
		textCosto.setColumns(10);
		textCosto.setBounds(178, 215, 178, 25);
		frame.getContentPane().add(textCosto);

		textPrecioMayorista = new JTextField();
		textPrecioMayorista.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textPrecioMayorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textPrecioMayorista.getText().length() >= 25) {
					e.consume();
				}
			}
		});
		textPrecioMayorista.setColumns(10);
		textPrecioMayorista.setBounds(178, 256, 178, 25);
		frame.getContentPane().add(textPrecioMayorista);

		textPrecioMinorista = new JTextField();
		textPrecioMinorista.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textPrecioMinorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textPrecioMinorista.getText().length() >= 25) {
					e.consume();
				}
			}
		});
		textPrecioMinorista.setColumns(10);
		textPrecioMinorista.setBounds(178, 298, 178, 25);
		frame.getContentPane().add(textPrecioMinorista);

		textPuntoRepMinimo = new JTextField();
		textPuntoRepMinimo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textPuntoRepMinimo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textPuntoRepMinimo.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		textPuntoRepMinimo.setColumns(10);
		textPuntoRepMinimo.setBounds(178, 336, 178, 25);
		frame.getContentPane().add(textPuntoRepMinimo);

		textTalle = new JTextField();
		textTalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textTalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textTalle.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		textTalle.setColumns(10);
		textTalle.setBounds(178, 427, 178, 19);
		frame.getContentPane().add(textTalle);

		textCantidadAReponer = new JTextField();
		textCantidadAReponer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textCantidadAReponer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textCantidadAReponer.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		textCantidadAReponer.setColumns(10);
		textCantidadAReponer.setBounds(178, 514, 178, 19);
		frame.getContentPane().add(textCantidadAReponer);

		textDiasParaReponer = new JTextField();
		textDiasParaReponer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textDiasParaReponer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textDiasParaReponer.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		textDiasParaReponer.setColumns(10);
		textDiasParaReponer.setBounds(178, 543, 178, 19);
		frame.getContentPane().add(textDiasParaReponer);

		textUnidadMedida = new JTextField();
		textUnidadMedida.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textUnidadMedida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textUnidadMedida.getText().length() >= 20) {
					e.consume();
				}
			}
		});
		textUnidadMedida.setColumns(10);
		textUnidadMedida.setBounds(178, 456, 178, 19);
		frame.getContentPane().add(textUnidadMedida);

		btnRegresar = new JButton("");
		btnRegresar.setBounds(30, 597, 60, 60);
		cambiarIconoBotones(btnRegresar, "back2.png");
		frame.getContentPane().add(btnRegresar);

		btnRegistrar = new JButton("");
		btnRegistrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRegistrar.setBounds(188, 596, 60, 60);
		cambiarIconoBotones(btnRegistrar, "regis2.png");
		frame.getContentPane().add(btnRegistrar);

		comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxEstado.setBounds(178, 485, 178, 19);
		comboBoxEstado.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxEstado);

		comboBoxFabricado = new JComboBox<String>();
		comboBoxFabricado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxFabricado.setBounds(178, 173, 178, 25);
		comboBoxFabricado.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxFabricado);

		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxTipo.setBounds(178, 133, 178, 25);
		comboBoxTipo.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxTipo);

		lblCantidadAReponer = new JLabel("Cantidad a reponer");
		lblCantidadAReponer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCantidadAReponer.setBounds(20, 515, 148, 19);
		frame.getContentPane().add(lblCantidadAReponer);

		btnElegirProveedor = new JButton("Elegir proveedor");
		btnElegirProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnElegirProveedor.setBounds(246, 383, 124, 33);
		frame.getContentPane().add(btnElegirProveedor);

		lblProveedorElegido = new JLabel("Sin seleccionar");
		lblProveedorElegido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProveedorElegido.setBounds(99, 383, 159, 31);
		frame.getContentPane().add(lblProveedorElegido);

		btnBorrarProveedor = new JButton("");
		btnBorrarProveedor.setBounds(380, 383, 33, 33);
		cambiarIconoBotones(btnBorrarProveedor, "trash.png");
		frame.getContentPane().add(btnBorrarProveedor);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 426, 50);
		frame.getContentPane().add(panel);
		
		lblNewLabel_2 = new JLabel("Zapateria Argento");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_2.setBounds(10, 0, 236, 50);
		panel.add(lblNewLabel_2);
		
		lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(99, 597, 112, 60);
		frame.getContentPane().add(lblAtras);
		
		lblRegistrarProducto = new JLabel("Registrar Producto");
		lblRegistrarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegistrarProducto.setBounds(258, 596, 137, 60);
		frame.getContentPane().add(lblRegistrarProducto);
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

	public JComboBox<String> getComboBoxEstado() {
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
