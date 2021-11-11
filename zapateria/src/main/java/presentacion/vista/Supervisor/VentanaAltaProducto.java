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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import persistencia.conexion.Conexion;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VentanaAltaProducto {

	private JFrame frame;

	private JTextField textDescripcion;

	private JTextField textCosto;
	private JTextField textPrecioMayorista;
	private JTextField textPrecioMinorista;
	private JTextField textPuntoRepMinimo;
	private JTextField textDiasParaReponer;
	private JTextField textCantidadAReponer;

	private JButton btnRegistrar;
	private JButton btnRegresar;

	private JComboBox<String> comboBoxEstado;
	private JComboBox<String> comboBoxFabricado;
	private JComboBox<String> comboBoxTipo;

	private JLabel lblCantidadAReponer;
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel lblAtras;
	private JLabel lblRegistrarProducto;
	private JComboBox<String> comboBoxUnidadDeMedida;
	
	private JComboBox<String> comboBoxTalle;
	private JButton btnAniadirUnidadMedida;
	private JCheckBox chckbxNumerico;
	
	
	private JButton btnAgregarProv;	
	
	private DefaultTableModel modelTablaProveedores;
	private String[] nombreColumnas = {"Nombre Proveedor","Correo","Limite de credito","Precio Venta","Cant Prod por lote","Proveedor Preferenciado"};
	private JTable tableProveedores;

	private JButton btnBorrarProv;

	private JScrollPane scrollPaneProveedores;
	private JButton btnEditar;
	private JLabel lblEditarProducto;
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
		frame.setBounds(500, 100, 833, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Zapateria Argento - Registrar un nuevo producto");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

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
		lblCostoDeProduccion.setBounds(408, 95, 156, 19);
		frame.getContentPane().add(lblCostoDeProduccion);

		JLabel lblPrecioMayorista = new JLabel("Precio Mayorista");
		lblPrecioMayorista.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPrecioMayorista.setBounds(408, 133, 156, 19);
		frame.getContentPane().add(lblPrecioMayorista);

		JLabel lblPrecioMinorista = new JLabel("Precio Minorista");
		lblPrecioMinorista.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPrecioMinorista.setBounds(408, 173, 156, 19);
		frame.getContentPane().add(lblPrecioMinorista);

		JLabel lblPuntoDeReposicion = new JLabel("Punto de reposicion minimo");
		lblPuntoDeReposicion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPuntoDeReposicion.setBounds(408, 208, 156, 19);
		frame.getContentPane().add(lblPuntoDeReposicion);

		JLabel lblTalle = new JLabel("Talle");
		lblTalle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTalle.setBounds(20, 208, 148, 19);
		frame.getContentPane().add(lblTalle);

		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblUnidadDeMedida.setBounds(28, 237, 148, 19);
		frame.getContentPane().add(lblUnidadDeMedida);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEstado.setBounds(408, 237, 148, 19);
		frame.getContentPane().add(lblEstado);

		JLabel lblDiasParaReponer = new JLabel("Dias para reponer");
		lblDiasParaReponer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDiasParaReponer.setBounds(408, 266, 148, 19);
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
				if (textCosto.getText().length() >= 15) {
					e.consume();
				}
			}
		});
		textCosto.setColumns(10);
		textCosto.setBounds(574, 90, 178, 25);
		frame.getContentPane().add(textCosto);

		textPrecioMayorista = new JTextField();
		textPrecioMayorista.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textPrecioMayorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textPrecioMayorista.getText().length() >= 15) {
					e.consume();
				}
			}
		});
		textPrecioMayorista.setColumns(10);
		textPrecioMayorista.setBounds(574, 130, 178, 25);
		frame.getContentPane().add(textPrecioMayorista);

		textPrecioMinorista = new JTextField();
		textPrecioMinorista.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textPrecioMinorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textPrecioMinorista.getText().length() >= 15) {
					e.consume();
				}
			}
		});
		textPrecioMinorista.setColumns(10);
		textPrecioMinorista.setBounds(574, 170, 178, 25);
		frame.getContentPane().add(textPrecioMinorista);

		textPuntoRepMinimo = new JTextField();
		textPuntoRepMinimo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textPuntoRepMinimo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textPuntoRepMinimo.getText().length() >= 3) {
					e.consume();
				}
			}
		});
		textPuntoRepMinimo.setColumns(10);
		textPuntoRepMinimo.setBounds(574, 205, 178, 25);
		frame.getContentPane().add(textPuntoRepMinimo);

		textCantidadAReponer = new JTextField();
		textCantidadAReponer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textCantidadAReponer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textCantidadAReponer.getText().length() >= 3) {
					e.consume();
				}
			}
		});
		textCantidadAReponer.setColumns(10);
		textCantidadAReponer.setBounds(178, 268, 178, 19);
		frame.getContentPane().add(textCantidadAReponer);

		textDiasParaReponer = new JTextField();
		textDiasParaReponer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textDiasParaReponer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textDiasParaReponer.getText().length() >= 3) {
					e.consume();
				}
			}
		});
		textDiasParaReponer.setColumns(10);
		textDiasParaReponer.setBounds(574, 266, 178, 19);
		frame.getContentPane().add(textDiasParaReponer);

		btnRegresar = new JButton("");
		btnRegresar.setBounds(20, 517, 60, 60);
		cambiarIconoBotones(btnRegresar, "back2.png");
		frame.getContentPane().add(btnRegresar);

		btnRegistrar = new JButton("");
		btnRegistrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnRegistrar.setBounds(211, 517, 60, 60);
		cambiarIconoBotones(btnRegistrar, "regis2.png");
		frame.getContentPane().add(btnRegistrar);
//		btnRegistrar.setVisible(false);
		
		lblRegistrarProducto = new JLabel("Registrar Producto");
		lblRegistrarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegistrarProducto.setBounds(276, 517, 137, 60);
		frame.getContentPane().add(lblRegistrarProducto);
//		lblRegistrarProducto.setVisible(false);
		

		comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxEstado.setBounds(574, 237, 178, 19);
		frame.getContentPane().add(comboBoxEstado);

		comboBoxFabricado = new JComboBox<String>();
		comboBoxFabricado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxFabricado.setBounds(178, 173, 178, 25);
		frame.getContentPane().add(comboBoxFabricado);

		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxTipo.setBounds(178, 133, 178, 25);
		frame.getContentPane().add(comboBoxTipo);

		lblCantidadAReponer = new JLabel("Cantidad a reponer (por lote)");
		lblCantidadAReponer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCantidadAReponer.setBounds(20, 266, 156, 19);
		frame.getContentPane().add(lblCantidadAReponer);
		
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
		lblAtras.setBounds(90, 517, 112, 60);
		frame.getContentPane().add(lblAtras);
		
		comboBoxUnidadDeMedida = new JComboBox<String>();
		comboBoxUnidadDeMedida.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxUnidadDeMedida.setBounds(178, 237, 93, 19);
		frame.getContentPane().add(comboBoxUnidadDeMedida);
		
		comboBoxTalle = new JComboBox<String>();
		comboBoxTalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxTalle.setBounds(178, 208, 93, 19);
		frame.getContentPane().add(comboBoxTalle);
		
		chckbxNumerico = new JCheckBox("Numerico");
		chckbxNumerico.setBounds(277, 208, 93, 21);
		frame.getContentPane().add(chckbxNumerico);
		
		btnAniadirUnidadMedida = new JButton("Otros...");
		btnAniadirUnidadMedida.setBounds(285, 237, 85, 21);
		frame.getContentPane().add(btnAniadirUnidadMedida);
		
		
		
		/*
				modelProductos = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 5) {
					return true;
				} else {
					return false;
				}
			}
		};

		tablaProductos = new JTable(modelProductos);
		tablaProductos.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(190);
		tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(30);
		tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(70);
		tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(7).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(8).setPreferredWidth(80);
		tablaProductos.getColumnModel().getColumn(9).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(10).setPreferredWidth(120);
		tablaProductos.getColumnModel().getColumn(11).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(12).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(13).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(14).setPreferredWidth(100);
		
		
		
//		tablaProductos.getColumnModel().getColumn(0).setResizable(false);
//		tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(100);
//		tablaProductos.getColumnModel().getColumn(1).setResizable(false);
		
		
		
		tablaProductos.getTableHeader().setReorderingAllowed(false);
		spProductos.setViewportView(tablaProductos);
		tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaProductos.doLayout();
		
		panel.add(spProductos);
		*/
		
		scrollPaneProveedores = new JScrollPane();
		scrollPaneProveedores.setBounds(10, 345, 799, 162);
		
		this.modelTablaProveedores = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int filas, int columnas) {
				return columnas==5;
				
			}
		};
		
		tableProveedores = new JTable(modelTablaProveedores){
			
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Double.class;
				case 3:
					return Double.class;
				case 4:
					return Double.class;
				default:
					return Boolean.class;
				}
			}
		};
		scrollPaneProveedores.setViewportView(tableProveedores);
//		tableProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		

		
		frame.getContentPane().add(scrollPaneProveedores);
		
		
		
		
		
		
		
		JLabel lblSeleccioneQueProv = new JLabel("Seleccione que proveedores pueden proveer este producto");
		lblSeleccioneQueProv.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSeleccioneQueProv.setBounds(10, 295, 426, 38);
		frame.getContentPane().add(lblSeleccioneQueProv);
		
		
		
		btnAgregarProv = new JButton("Elegir Proveedores");
		btnAgregarProv.setBounds(440, 307, 164, 21);
		frame.getContentPane().add(btnAgregarProv);
		
		btnBorrarProv = new JButton("");
		btnBorrarProv.setBounds(777, 307, 32, 28);
		cambiarIconoBotones(btnBorrarProv, "trash.png");
		frame.getContentPane().add(btnBorrarProv);
		
		btnEditar = new JButton("");
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEditar.setBounds(423, 517, 60, 60);
		cambiarIconoBotones(btnEditar,"update.png");
		frame.getContentPane().add(btnEditar);
		btnEditar.setVisible(false);
		
		lblEditarProducto = new JLabel("Editar Producto");
		lblEditarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEditarProducto.setBounds(493, 517, 137, 60);
		frame.getContentPane().add(lblEditarProducto);
		lblEditarProducto.setVisible(false);
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

	public JFrame getFrame() {
		return frame;
	}

	public JComboBox<String> getComboBoxUnidadDeMedida() {
		return comboBoxUnidadDeMedida;
	}
	public JComboBox<String> getComboBoxTalle() {
		return comboBoxTalle;
	}

	public JButton getBtnAniadirUnidadMedida() {
		return btnAniadirUnidadMedida;
	}

	public JCheckBox getChckbxNumerico() {
		return chckbxNumerico;
	}
	public JButton getBtnAgregarProv() {
		return btnAgregarProv;
	}
	public JLabel getLblCantidadAReponer() {
		return lblCantidadAReponer;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}

	public JLabel getLblAtras() {
		return lblAtras;
	}

	public JLabel getLblRegistrarProducto() {
		return lblRegistrarProducto;
	}

	public DefaultTableModel getModelTablaProveedores() {
		return modelTablaProveedores;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTableProveedores() {
		return tableProveedores;
	}

	public JButton getBtnBorrarProv() {
		return btnBorrarProv;
	}
	public JScrollPane getScrollPaneProveedores() {
		return scrollPaneProveedores;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JLabel getLblEditarProducto() {
		return lblEditarProducto;
	}
	
}
