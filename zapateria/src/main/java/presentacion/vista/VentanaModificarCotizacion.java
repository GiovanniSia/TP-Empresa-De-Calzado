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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class VentanaModificarCotizacion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Cod. Moneda", "Descripcion", "Tasa Conversion" };
	private DefaultTableModel modelProducto;
	private JTable tablaMedioPago;
	private JPanel panel_2;
	private JLabel lblZapateria;
	private JLabel lblDescripcion;
	private JLabel lblActualizarDescripcion;
	private JLabel lblTasaConvercion;
	private JLabel lblFiltrarPor;
	private JLabel lblModificarCotizacion;
	private JLabel lblDatosModificables;
	private JScrollPane spMedioPago;
	private JTextField txtFiltroDescripcion;
	private JTextField txtActualizarTasaConvercion;
	private JButton btnActualizarCotizacion;
	private JButton btnVerHistorialDeCambios;
	private JButton btnAtras;
	private JPanel panel_1;

	public static void main(String[] args) {
		VentanaModificarCotizacion v = new VentanaModificarCotizacion();
		v.show();
	}
	
	public VentanaModificarCotizacion() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		frame = new JFrame();
		frame.setBounds(100, 100, 517, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 148, 501, 320);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spMedioPago = new JScrollPane();
		spMedioPago.setBounds(10, 11, 481, 145);
		panel.add(spMedioPago);

		modelProducto = new DefaultTableModel(null, nombreColumnas);
		tablaMedioPago = new JTable(modelProducto);

		tablaMedioPago.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaMedioPago.getColumnModel().getColumn(0).setResizable(false);
		tablaMedioPago.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaMedioPago.getColumnModel().getColumn(1).setResizable(false);

		spMedioPago.setViewportView(tablaMedioPago);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnAtras.setBounds(10, 275, 108, 23);
		panel.add(btnAtras);

		lblActualizarDescripcion = new JLabel("Descripci\u00F3n");
		lblActualizarDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblActualizarDescripcion.setBounds(110, 186, 273, 21);
		panel.add(lblActualizarDescripcion);

		lblTasaConvercion = new JLabel("Tasa Convercion");
		lblTasaConvercion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTasaConvercion.setBounds(51, 218, 120, 14);
		panel.add(lblTasaConvercion);

		txtActualizarTasaConvercion = new JTextField();
		txtActualizarTasaConvercion.setBounds(171, 217, 151, 20);
		panel.add(txtActualizarTasaConvercion);

		btnActualizarCotizacion = new JButton("Actualizar \r\nCotizaci\u00F3n");
		btnActualizarCotizacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizarCotizacion.setBounds(160, 241, 172, 23);
		panel.add(btnActualizarCotizacion);

		lblDatosModificables = new JLabel("Datos modificables de moneda seleccionada");
		lblDatosModificables.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDatosModificables.setBounds(10, 167, 307, 14);
		panel.add(lblDatosModificables);

		btnVerHistorialDeCambios = new JButton("Ver Historial de Cambios");
		btnVerHistorialDeCambios.setBounds(290, 275, 201, 23);
		panel.add(btnVerHistorialDeCambios);
		btnVerHistorialDeCambios.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_1 = new JPanel();
		panel_1.setBounds(0, 69, 501, 81);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(170, 21, 158, 20);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblDescripcion);

		txtFiltroDescripcion = new JTextField();

		txtFiltroDescripcion.setBounds(170, 52, 158, 20);
		panel_1.add(txtFiltroDescripcion);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 11, 70, 14);
		panel_1.add(lblFiltrarPor);

		lblModificarCotizacion = new JLabel("Modificar Cotizaci\u00F3n");
		lblModificarCotizacion.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblModificarCotizacion.setBounds(10, 41, 198, 30);
		frame.getContentPane().add(lblModificarCotizacion);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 501, 41);
		frame.getContentPane().add(panel_2);

		lblZapateria = new JLabel("Zapater\u00EDa");
		panel_2.add(lblZapateria);
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 22));
	}

	
	public JLabel getLblActualizarDescripcion() {
		return lblActualizarDescripcion;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelProducto() {
		return modelProducto;
	}

	public JTable getTablaMedioPago() {
		return tablaMedioPago;
	}

	public JTextField getTxtFiltroDescripcion() {
		return txtFiltroDescripcion;
	}

	public JTextField getTxtActualizarTasaConvercion() {
		return txtActualizarTasaConvercion;
	}

	public JButton getBtnActualizarCotizacion() {
		return btnActualizarCotizacion;
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
		this.lblActualizarDescripcion.setText("Descripci�n");
		this.txtActualizarTasaConvercion.setText(null);
	}

	public void cerrar() {
		this.txtFiltroDescripcion.setText(null);
		this.lblActualizarDescripcion.setText("Descripci�n");
		this.txtActualizarTasaConvercion.setText(null);
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

}