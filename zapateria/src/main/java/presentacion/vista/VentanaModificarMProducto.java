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
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaModificarMProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Codigo", "Descripción","Proveedor", "Talle", "PrecioCosto", "PrecioMayorista",
			"PrecioMinorista", "PuntoRepositorio", "CantidadAReponer", "DiasParaReponer" };
	private DefaultTableModel modelProducto;
	private JTable tablaProducto;
	private JPanel panel_2;
	private JLabel lblZapateria;
	private JLabel lblDescripcion;
	private JLabel lblActualizarDescripcion;
	private JLabel lblPrecioCosto;
	private JLabel lblPrecioMayorista;
	private JLabel lblPrecioMinorista;
	private JLabel lblCantidadAReponer;
	private JLabel lblDiasParaReponer;
	private JLabel lblCodProducto;
	private JLabel lblFiltrarPor;
	private JLabel lblModificarProducto;
	private JLabel lblDatosDeProducto;
	private JLabel lblPuntoRepositorio;

	private JScrollPane spProducto;
	private JTextField txtFiltroDescripcion;
	private JTextField txtFiltroTalle;
	private JTextField txtFiltroCodProducto;
	private JTextField txtActualizarPrecioCosto;
	private JTextField txtActualizarPrecioMinorista;
	private JTextField txtActualizarPrecioMayorista;
	private JButton btnActualizarProducto;
	private JButton btnVerHistorialDeCambios;
	private JButton btnAtras;
	private JSpinner spinnerCantidadAReponer;
	private JSpinner spinnerDiasParaReponer;
	private JSpinner spinnerPuntoRepositorio;

	private JPanel panel_1;
	private JTextField txtFiltroProveedor;

	public VentanaModificarMProducto() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 880, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBounds(10, 166, 854, 291);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spProducto = new JScrollPane();
		spProducto.setBounds(10, 11, 823, 145);
		panel.add(spProducto);

		modelProducto =new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if(columnas == 9) {
                    return true;
                }else {
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

		btnAtras.setBounds(10, 258, 108, 23);
		panel.add(btnAtras);

		lblActualizarDescripcion = new JLabel("Descripci\u00F3n");
		lblActualizarDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblActualizarDescripcion.setBounds(10, 196, 198, 21);
		panel.add(lblActualizarDescripcion);

		lblPrecioCosto = new JLabel("Precio Costo");
		lblPrecioCosto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioCosto.setBounds(10, 228, 86, 14);
		panel.add(lblPrecioCosto);

		lblPrecioMayorista = new JLabel("Precio Mayorista");
		lblPrecioMayorista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioMayorista.setBounds(218, 196, 99, 14);
		panel.add(lblPrecioMayorista);

		lblPrecioMinorista = new JLabel("Precio Minorista");
		lblPrecioMinorista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioMinorista.setBounds(218, 228, 99, 14);
		panel.add(lblPrecioMinorista);

		lblCantidadAReponer = new JLabel("Cantidad a Reponer");
		lblCantidadAReponer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidadAReponer.setBounds(448, 228, 128, 14);
		panel.add(lblCantidadAReponer);

		lblDiasParaReponer = new JLabel("Dias para Reponer");
		lblDiasParaReponer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiasParaReponer.setBounds(654, 196, 128, 14);
		panel.add(lblDiasParaReponer);

		txtActualizarPrecioCosto = new JTextField();
		txtActualizarPrecioCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtActualizarPrecioCosto.getText().length()>=15) {
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
				if(txtActualizarPrecioMayorista.getText().length()>=15) {
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
				if(txtActualizarPrecioMinorista.getText().length()>=15) {
					e.consume();
				}
			}
		});
		panel.add(txtActualizarPrecioMinorista);

		btnActualizarProducto = new JButton("Actualizar \r\nProducto");
		btnActualizarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizarProducto.setBounds(670, 224, 174, 23);
		panel.add(btnActualizarProducto);

		lblDatosDeProducto = new JLabel("Datos modificables de producto seleccionado:");
		lblDatosDeProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDatosDeProducto.setBounds(10, 167, 307, 14);
		panel.add(lblDatosDeProducto);

		lblPuntoRepositorio = new JLabel("Punto Repositorio");
		lblPuntoRepositorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuntoRepositorio.setBounds(448, 196, 117, 14);
		panel.add(lblPuntoRepositorio);

		spinnerCantidadAReponer = new JSpinner();
		spinnerCantidadAReponer.setBounds(573, 227, 70, 20);
		panel.add(spinnerCantidadAReponer);

		spinnerDiasParaReponer = new JSpinner();
		spinnerDiasParaReponer.setBounds(773, 195, 70, 20);
		panel.add(spinnerDiasParaReponer);

		spinnerPuntoRepositorio = new JSpinner();
		spinnerPuntoRepositorio.setBounds(571, 195, 72, 20);
		panel.add(spinnerPuntoRepositorio);

		btnVerHistorialDeCambios = new JButton("Ver Historial de Cambios");
		btnVerHistorialDeCambios.setBounds(643, 258, 201, 23);
		panel.add(btnVerHistorialDeCambios);
		btnVerHistorialDeCambios.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_1 = new JPanel();
		panel_1.setBounds(10, 69, 854, 100);
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
		lblModificarProducto.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblModificarProducto.setBounds(10, 41, 198, 30);
		frame.getContentPane().add(lblModificarProducto);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 864, 41);
		frame.getContentPane().add(panel_2);

		lblZapateria = new JLabel("Zapater\u00EDa");
		panel_2.add(lblZapateria);
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 22));
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

	public JButton getBtnActualizarProducto() {
		return btnActualizarProducto;
	}

	public JButton getBtnVerHistorialDeCambios() {
		return btnVerHistorialDeCambios;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JSpinner getSpinnerCantidadAReponer() {
		return spinnerCantidadAReponer;
	}

	public JSpinner getSpinnerDiasParaReponer() {
		return spinnerDiasParaReponer;
	}

	public JSpinner getSpinnerPuntoRepositorio() {
		return spinnerPuntoRepositorio;
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

		this.spinnerCantidadAReponer.setValue(0);
		this.spinnerDiasParaReponer.setValue(0);
		this.spinnerPuntoRepositorio.setValue(0);
	}
	
	public void cerrar() {
		this.txtFiltroDescripcion.setText(null);
		this.txtFiltroTalle.setText(null);
		this.txtFiltroCodProducto.setText(null);

		this.lblActualizarDescripcion.setText("Descripción");
		this.txtActualizarPrecioCosto.setText(null);
		this.txtActualizarPrecioMayorista.setText(null);
		this.txtActualizarPrecioMinorista.setText(null);

		this.spinnerCantidadAReponer.setValue(0);
		this.spinnerDiasParaReponer.setValue(0);
		this.spinnerPuntoRepositorio.setValue(0);

		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
}