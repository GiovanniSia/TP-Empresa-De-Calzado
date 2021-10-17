package presentacion.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaModificarMProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Codigo", "Descripción", "Proveedor", "Talle", "PrecioCosto", "PrecioMayorista",
			"PrecioMinorista", "PuntoRepositorio", "CantidadAReponer", "DiasParaReponer" };
	private DefaultTableModel modelProducto;
	private JTable tablaProducto;
	private JPanel panel_2;
	private JPanel panel_1;

	private JScrollPane spProducto;
	private JTextField txtFiltroDescripcion;
	private JTextField txtFiltroTalle;
	private JTextField txtFiltroCodProducto;
	private JButton btnActualizarProducto;
	private JButton btnVerHistorialDeCambios;
	private JButton btnAtras;
	private JLabel lblZapateria;
	private JLabel lblFiltrarPor;
	private JLabel lblCodProducto;
	private JLabel lblDescripcion;
	private JLabel lblModificarProducto;

	private JLabel lblModificacionUnitaria;
	private JLabel lblActualizarDescripcion;
	private JLabel lblMUPrecioCosto;
	private JLabel lblMUPrecioMayorista;
	private JLabel lblMUPrecioMinorista;
	private JLabel lblMUCantidadAReponer;
	private JLabel lblMUDiasParaReponer;
	private JLabel lblMUPuntoRepositorio;
	private JTextField txtActualizarPrecioCosto;
	private JTextField txtActualizarPrecioMinorista;
	private JTextField txtActualizarPrecioMayorista;
	private JTextField txtFiltroProveedor;
	private JTextField txtActualizarPuntoRepositorio;
	private JTextField txtActualizarCantidadAReponer;
	private JTextField txtActualizarDiasParaResponder;

	private JLabel lblModificacionMasiva;
	private JTextField txtActualizarAumentar;
	private JTextField txtActualizarDisminuir;
	private JLabel lblAumentar;
	private JLabel lblDisminuir;

	public VentanaModificarMProducto() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 880, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBounds(0, 163, 864, 324);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spProducto = new JScrollPane();
		spProducto.setBounds(10, 11, 834, 145);
		panel.add(spProducto);

		modelProducto = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 9) {
					return true;
				} else {
					return false;
				}
			}
		};

		tablaProducto = new JTable(modelProducto);

		tablaProducto.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProducto.getColumnModel().getColumn(0).setResizable(false);
		tablaProducto.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProducto.getColumnModel().getColumn(1).setResizable(false);

		spProducto.setViewportView(tablaProducto);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnAtras.setBounds(10, 290, 108, 23);
		panel.add(btnAtras);

		lblActualizarDescripcion = new JLabel("Descripci\u00F3n");
		lblActualizarDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblActualizarDescripcion.setBounds(10, 196, 198, 21);
		panel.add(lblActualizarDescripcion);

		lblMUPrecioCosto = new JLabel("Precio Costo");
		lblMUPrecioCosto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMUPrecioCosto.setBounds(10, 228, 86, 14);
		panel.add(lblMUPrecioCosto);

		lblMUPrecioMayorista = new JLabel("Precio Mayorista");
		lblMUPrecioMayorista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMUPrecioMayorista.setBounds(218, 196, 99, 14);
		panel.add(lblMUPrecioMayorista);

		lblMUPrecioMinorista = new JLabel("Precio Minorista");
		lblMUPrecioMinorista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMUPrecioMinorista.setBounds(218, 228, 99, 14);
		panel.add(lblMUPrecioMinorista);

		lblMUCantidadAReponer = new JLabel("Cantidad a Reponer");
		lblMUCantidadAReponer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMUCantidadAReponer.setBounds(448, 228, 128, 14);
		panel.add(lblMUCantidadAReponer);

		lblMUDiasParaReponer = new JLabel("Dias para Reponer");
		lblMUDiasParaReponer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMUDiasParaReponer.setBounds(654, 200, 128, 14);
		panel.add(lblMUDiasParaReponer);

		txtActualizarPrecioCosto = new JTextField();
		txtActualizarPrecioCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57 || key == 46;
				if (txtActualizarPrecioCosto.getText().length() >= 11 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarPrecioCosto.setBounds(98, 226, 108, 20);
		panel.add(txtActualizarPrecioCosto);

		txtActualizarPrecioMayorista = new JTextField();
		txtActualizarPrecioMayorista.setBounds(327, 195, 111, 20);
		txtActualizarPrecioMayorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57 || key == 46;
				if (txtActualizarPrecioMayorista.getText().length() >= 11 || !numeros) {
					e.consume();
				}
			}
		});
		panel.add(txtActualizarPrecioMayorista);

		txtActualizarPrecioMinorista = new JTextField();
		txtActualizarPrecioMinorista.setBounds(327, 222, 111, 20);
		txtActualizarPrecioMinorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57 || key == 46;
				if (txtActualizarPrecioMinorista.getText().length() >= 11 || !numeros) {
					e.consume();
				}
			}
		});
		panel.add(txtActualizarPrecioMinorista);

		btnActualizarProducto = new JButton("Actualizar \r\nProducto");
		btnActualizarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizarProducto.setBounds(670, 256, 174, 23);
		panel.add(btnActualizarProducto);

		lblModificacionUnitaria = new JLabel("Modificacion Unitaria");
		lblModificacionUnitaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificacionUnitaria.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblModificacionUnitaria.setBounds(20, 158, 824, 23);
		panel.add(lblModificacionUnitaria);

		lblMUPuntoRepositorio = new JLabel("Punto Repositorio");
		lblMUPuntoRepositorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMUPuntoRepositorio.setBounds(448, 196, 117, 14);
		panel.add(lblMUPuntoRepositorio);

		btnVerHistorialDeCambios = new JButton("Ver Historial de Cambios");
		btnVerHistorialDeCambios.setBounds(643, 290, 201, 23);
		panel.add(btnVerHistorialDeCambios);
		btnVerHistorialDeCambios.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtActualizarPuntoRepositorio = new JTextField();
		txtActualizarPuntoRepositorio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarPuntoRepositorio.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarPuntoRepositorio.setBounds(575, 196, 69, 20);
		panel.add(txtActualizarPuntoRepositorio);
		txtActualizarPuntoRepositorio.setColumns(10);

		txtActualizarCantidadAReponer = new JTextField();
		txtActualizarCantidadAReponer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarCantidadAReponer.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarCantidadAReponer.setColumns(10);
		txtActualizarCantidadAReponer.setBounds(575, 227, 69, 20);
		panel.add(txtActualizarCantidadAReponer);

		txtActualizarDiasParaResponder = new JTextField();
		txtActualizarDiasParaResponder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarDiasParaResponder.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarDiasParaResponder.setColumns(10);
		txtActualizarDiasParaResponder.setBounds(775, 199, 69, 20);
		panel.add(txtActualizarDiasParaResponder);

		lblModificacionMasiva = new JLabel("Modificacion Masiva de Precios en %");
		lblModificacionMasiva.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificacionMasiva.setBounds(259, 253, 273, 14);
		panel.add(lblModificacionMasiva);

		lblAumentar = new JLabel("Aumentar");
		lblAumentar.setBounds(231, 285, 92, 14);
		panel.add(lblAumentar);

		lblDisminuir = new JLabel("Disminuir");
		lblDisminuir.setBounds(391, 285, 69, 14);
		panel.add(lblDisminuir);

		txtActualizarAumentar = new JTextField();
		txtActualizarAumentar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarAumentar.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarAumentar.setBounds(288, 282, 58, 20);
		panel.add(txtActualizarAumentar);
		txtActualizarAumentar.setColumns(10);

		txtActualizarDisminuir = new JTextField();
		txtActualizarDisminuir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarDisminuir.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});

		txtActualizarDisminuir.setColumns(10);
		txtActualizarDisminuir.setBounds(443, 282, 58, 20);
		panel.add(txtActualizarDisminuir);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 69, 864, 100);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(137, 36, 70, 20);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblDescripcion);

		txtFiltroDescripcion = new JTextField();

		txtFiltroDescripcion.setBounds(135, 67, 116, 20);
		panel_1.add(txtFiltroDescripcion);

		JLabel lblTalle = new JLabel("Talle");
		lblTalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTalle.setBounds(387, 36, 70, 20);
		panel_1.add(lblTalle);

		txtFiltroTalle = new JTextField();
		txtFiltroTalle.setBounds(387, 67, 70, 20);
		panel_1.add(txtFiltroTalle);

		lblCodProducto = new JLabel("Cod Producto");
		lblCodProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodProducto.setBounds(10, 36, 86, 20);
		panel_1.add(lblCodProducto);

		txtFiltroCodProducto = new JTextField();
		txtFiltroCodProducto.setBounds(10, 67, 86, 20);
		panel_1.add(txtFiltroCodProducto);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 11, 70, 14);
		panel_1.add(lblFiltrarPor);

		txtFiltroProveedor = new JTextField();
		txtFiltroProveedor.setBounds(273, 67, 94, 20);
		panel_1.add(txtFiltroProveedor);

		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProveedor.setBounds(273, 41, 94, 20);
		panel_1.add(lblProveedor);

		lblModificarProducto = new JLabel("Modificar Producto");
		lblModificarProducto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblModificarProducto.setBounds(10, 41, 382, 30);
		frame.getContentPane().add(lblModificarProducto);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 864, 41);
		frame.getContentPane().add(panel_2);

		lblZapateria = new JLabel("Zapater\u00EDa");
		panel_2.add(lblZapateria);
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 22));
	}

	public void mostrarModificacionUnitaria() {
		lblModificacionUnitaria.setVisible(true);
		lblActualizarDescripcion.setVisible(true);
		lblMUPrecioCosto.setVisible(true);
		lblMUPrecioMayorista.setVisible(true);
		lblMUPrecioMinorista.setVisible(true);
		lblMUCantidadAReponer.setVisible(true);
		lblMUDiasParaReponer.setVisible(true);
		lblMUPuntoRepositorio.setVisible(true);
		txtActualizarPrecioCosto.setVisible(true);
		txtActualizarPrecioMinorista.setVisible(true);
		txtActualizarPrecioMayorista.setVisible(true);
		txtFiltroProveedor.setVisible(true);
		txtActualizarPuntoRepositorio.setVisible(true);
		txtActualizarCantidadAReponer.setVisible(true);
		txtActualizarDiasParaResponder.setVisible(true);
		ocultarModificacionUnitaria();
		ocultarModificacionMasiva();
	}

	public void ocultarModificacionUnitaria() {
		lblModificacionUnitaria.setVisible(false);
		lblActualizarDescripcion.setVisible(false);
		lblMUPrecioCosto.setVisible(false);
		lblMUPrecioMayorista.setVisible(false);
		lblMUPrecioMinorista.setVisible(false);
		lblMUCantidadAReponer.setVisible(false);
		lblMUDiasParaReponer.setVisible(false);
		lblMUPuntoRepositorio.setVisible(false);
		txtActualizarPrecioCosto.setVisible(false);
		txtActualizarPrecioMinorista.setVisible(false);
		txtActualizarPrecioMayorista.setVisible(false);
		txtFiltroProveedor.setVisible(false);
		txtActualizarPuntoRepositorio.setVisible(false);
		txtActualizarCantidadAReponer.setVisible(false);
		txtActualizarDiasParaResponder.setVisible(false);
	}

	public void mostrarModificacionMasiva() {
		lblModificacionMasiva.setVisible(true);
		txtActualizarAumentar.setVisible(true);
		txtActualizarDisminuir.setVisible(true);
		lblAumentar.setVisible(true);
		lblDisminuir.setVisible(true);
		ocultarModificacionMasiva();
		ocultarModificacionUnitaria();
	}

	public void ocultarModificacionMasiva() {
		lblModificacionMasiva.setVisible(false);
		txtActualizarAumentar.setVisible(false);
		txtActualizarDisminuir.setVisible(false);
		lblAumentar.setVisible(false);
		lblDisminuir.setVisible(false);
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelProducto() {
		return modelProducto;
	}

	public JTextField getTxtFiltroProveedor() {
		return txtFiltroProveedor;
	}

	public JTable getTablaProducto() {
		return tablaProducto;
	}

	public JScrollPane getSpProducto() {
		return spProducto;
	}

	public JTextField getTxtFiltroDescripcion() {
		return txtFiltroDescripcion;
	}

	public JTextField getTxtFiltroTalle() {
		return txtFiltroTalle;
	}

	public JTextField getTxtFiltroCodProducto() {
		return txtFiltroCodProducto;
	}

	public JLabel getLblActualizarDescripcion() {
		return lblActualizarDescripcion;
	}

	public JTextField getTxtActualizarPrecioCosto() {
		return txtActualizarPrecioCosto;
	}

	public JTextField getTxtActualizarPrecioMinorista() {
		return txtActualizarPrecioMinorista;
	}

	public JTextField getTxtActualizarPrecioMayorista() {
		return txtActualizarPrecioMayorista;
	}

	public JTextField getTxtActualizarPuntoRepositorio() {
		return txtActualizarPuntoRepositorio;
	}

	public JTextField getTxtActualizarCantidadAReponer() {
		return txtActualizarCantidadAReponer;
	}

	public JTextField getTxtActualizarDiasParaResponder() {
		return txtActualizarDiasParaResponder;
	}

	public JTextField getTxtActualizarAumentar() {
		return txtActualizarAumentar;
	}

	public JTextField getTxtActualizarDisminuir() {
		return txtActualizarDisminuir;
	}

	public JButton getBtnActualizarProducto() {
		return btnActualizarProducto;
	}

	public JButton getBtnVerHistorialDeCambios() {
		return btnVerHistorialDeCambios;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});
		this.frame.setVisible(true);
	}

	public void limpiarCampos() {
		this.txtFiltroDescripcion.setText(null);
		this.txtFiltroTalle.setText(null);
		this.txtFiltroCodProducto.setText(null);
		this.lblActualizarDescripcion.setText("Descripción");
		this.txtActualizarPrecioCosto.setText(null);
		this.txtActualizarPrecioMayorista.setText(null);
		this.txtActualizarPrecioMinorista.setText(null);
		this.txtActualizarPuntoRepositorio.setText(null);
		this.txtActualizarCantidadAReponer.setText(null);
		this.txtActualizarDiasParaResponder.setText(null);
		this.txtActualizarDisminuir.setText(null);
		this.txtActualizarAumentar.setText(null);
	}

	public void cerrar() {
		limpiarCampos();
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
}