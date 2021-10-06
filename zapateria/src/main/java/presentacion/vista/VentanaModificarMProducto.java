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

public class VentanaModificarMProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Codigo", "Descripci�n", "Talle", "PrecioCosto", "PrecioMayorista",
			"PrecioMinorista", "PuntoRepositorio", "CantidadAReponer", "DiasParaReponer" };
	private DefaultTableModel modelProducto;
	private JTable tablaProducto;
	private JPanel panel_2;
	private JLabel lblZapateria;
	private JLabel lblDescripcion;
	private JLabel lblDescripcin;
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
	private JTextField textActualizarDescripcion;
	private JTextField textActualizarPrecioCosto;
	private JTextField textActualizarPrecioMinorista;
	private JTextField textActualizarPrecioMayorista;
	private JButton btnActualizarProducto;
	private JButton btnVerHistorialDeCambios;
	private JButton btnAtras;
	private JSpinner spinnerCantidadAReponer;
	private JSpinner spinnerDiasParaReponer;
	private JSpinner spinnerPuntoRepositorio;

	private JPanel panel_1;

	public VentanaModificarMProducto() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 852, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 165, 826, 292);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spProducto = new JScrollPane();
		spProducto.setBounds(10, 11, 806, 145);
		panel.add(spProducto);

		modelProducto = new DefaultTableModel(null, nombreColumnas);
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

		textActualizarDescripcion = new JTextField();
		textActualizarDescripcion.setBounds(98, 195, 108, 20);
		panel.add(textActualizarDescripcion);

		lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcin.setBounds(10, 196, 70, 14);
		panel.add(lblDescripcin);

		lblPrecioCosto = new JLabel("Precio Costo");
		lblPrecioCosto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioCosto.setBounds(10, 228, 86, 14);
		panel.add(lblPrecioCosto);

		lblPrecioMayorista = new JLabel("Precio Mayorista");
		lblPrecioMayorista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioMayorista.setBounds(231, 196, 99, 14);
		panel.add(lblPrecioMayorista);

		lblPrecioMinorista = new JLabel("Precio Minorista");
		lblPrecioMinorista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioMinorista.setBounds(231, 228, 99, 14);
		panel.add(lblPrecioMinorista);

		lblCantidadAReponer = new JLabel("Cantidad a Reponer");
		lblCantidadAReponer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidadAReponer.setBounds(461, 196, 128, 14);
		panel.add(lblCantidadAReponer);

		lblDiasParaReponer = new JLabel("Dias para Reponer");
		lblDiasParaReponer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiasParaReponer.setBounds(461, 228, 128, 14);
		panel.add(lblDiasParaReponer);

		textActualizarPrecioCosto = new JTextField();
		textActualizarPrecioCosto.setBounds(98, 226, 108, 20);
		panel.add(textActualizarPrecioCosto);

		textActualizarPrecioMayorista = new JTextField();
		textActualizarPrecioMayorista.setBounds(340, 196, 111, 20);
		panel.add(textActualizarPrecioMayorista);

		textActualizarPrecioMinorista = new JTextField();
		textActualizarPrecioMinorista.setBounds(340, 227, 108, 20);
		panel.add(textActualizarPrecioMinorista);

		btnActualizarProducto = new JButton("Actualizar \r\nProducto");
		btnActualizarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizarProducto.setBounds(644, 224, 174, 23);
		panel.add(btnActualizarProducto);

		lblDatosDeProducto = new JLabel("Datos modificables de producto seleccionado:");
		lblDatosDeProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDatosDeProducto.setBounds(10, 167, 307, 14);
		panel.add(lblDatosDeProducto);

		lblPuntoRepositorio = new JLabel("Punto Repositorio");
		lblPuntoRepositorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuntoRepositorio.setBounds(634, 196, 128, 14);
		panel.add(lblPuntoRepositorio);

		spinnerCantidadAReponer = new JSpinner();
		spinnerCantidadAReponer.setBounds(594, 195, 30, 20);
		panel.add(spinnerCantidadAReponer);

		spinnerDiasParaReponer = new JSpinner();
		spinnerDiasParaReponer.setBounds(594, 227, 30, 20);
		panel.add(spinnerDiasParaReponer);

		spinnerPuntoRepositorio = new JSpinner();
		spinnerPuntoRepositorio.setBounds(754, 195, 30, 20);
		panel.add(spinnerPuntoRepositorio);

		btnVerHistorialDeCambios = new JButton("Ver Historial de Cambios");
		btnVerHistorialDeCambios.setBounds(615, 258, 201, 23);
		panel.add(btnVerHistorialDeCambios);
		btnVerHistorialDeCambios.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_1 = new JPanel();
		panel_1.setBounds(0, 69, 836, 100);
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
		lblTalle.setBounds(284, 36, 70, 20);
		panel_1.add(lblTalle);

		txtFiltroTalle = new JTextField();
		txtFiltroTalle.setBounds(283, 67, 70, 20);
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

		lblZapateria = new JLabel("Zapater\u00EDa");
		lblZapateria.setBounds(10, 11, 129, 30);
		frame.getContentPane().add(lblZapateria);
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 22));

		lblModificarProducto = new JLabel("Modificar Producto");
		lblModificarProducto.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblModificarProducto.setBounds(20, 41, 198, 30);
		frame.getContentPane().add(lblModificarProducto);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 806, 41);
		frame.getContentPane().add(panel_2);
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelProducto() {
		return modelProducto;
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

	public JTextField getTextActualizarDescripcion() {
		return textActualizarDescripcion;
	}

	public JTextField getTextActualizarPrecioCosto() {
		return textActualizarPrecioCosto;
	}

	public JTextField getTextActualizarPrecioMinorista() {
		return textActualizarPrecioMinorista;
	}

	public JTextField getTextActualizarPrecioMayorista() {
		return textActualizarPrecioMayorista;
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
	
	public void cerrar() {
		this.txtFiltroDescripcion.setText(null);
		this.txtFiltroTalle.setText(null);
		this.txtFiltroCodProducto.setText(null);
		
		this.textActualizarDescripcion.setText(null);
		this.textActualizarPrecioCosto.setText(null);
		this.textActualizarPrecioMayorista.setText(null);
		this.textActualizarPrecioMinorista.setText(null);

		this.spinnerCantidadAReponer.setValue(null);
		this.spinnerDiasParaReponer.setValue(null);
		this.spinnerPuntoRepositorio.setValue(null);
	
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

}