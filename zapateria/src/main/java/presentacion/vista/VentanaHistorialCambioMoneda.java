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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaHistorialCambioMoneda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Cod. Moneda","Descripcion","Empleado","Fecha","T.Conversion Antigua","T.Conversion Nueva" };
	private DefaultTableModel modelHistorialCambioMoneda;
	private JTable tablaHistorialCambioMoneda;
	private JPanel panel_2;
	private JLabel lblZapateria;
	private JLabel lblFiltrarPor;
	private JLabel lblHistorialCotizacion;

	private JScrollPane spHistorialCambioMoneda;
	private JButton btnVolverAModificarConversion;

	private JPanel panel_1;
	private JTextField textFiltroDescripcion;
	private JTextField textFiltroFecha;

	public static void main(String[] args) {
		VentanaHistorialCambioMoneda v = new VentanaHistorialCambioMoneda();
		v.show();
	}
	
	public VentanaHistorialCambioMoneda() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		frame = new JFrame();
		frame.setBounds(100, 100, 765, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 148, 749, 198);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spHistorialCambioMoneda = new JScrollPane();
		spHistorialCambioMoneda.setBounds(10, 11, 729, 145);
		panel.add(spHistorialCambioMoneda);

		modelHistorialCambioMoneda = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if(columnas == 6) {
                    return true;
                }else {
                    return false;
                }
            }
		};
		
		tablaHistorialCambioMoneda = new JTable(modelHistorialCambioMoneda);

		tablaHistorialCambioMoneda.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaHistorialCambioMoneda.getColumnModel().getColumn(0).setResizable(false);
		tablaHistorialCambioMoneda.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaHistorialCambioMoneda.getColumnModel().getColumn(1).setResizable(false);

		spHistorialCambioMoneda.setViewportView(tablaHistorialCambioMoneda);

		btnVolverAModificarConversion = new JButton("Volver a Modificar Conversi\u00F3n");
		btnVolverAModificarConversion.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnVolverAModificarConversion.setBounds(268, 167, 244, 23);
		panel.add(btnVolverAModificarConversion);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 69, 749, 81);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 11, 70, 14);
		panel_1.add(lblFiltrarPor);
		
		JLabel lblCodMoneda = new JLabel("Descripci\u00F3n");
		lblCodMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodMoneda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodMoneda.setBounds(195, 25, 153, 14);
		panel_1.add(lblCodMoneda);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(402, 25, 151, 14);
		panel_1.add(lblFecha);
		
		textFiltroDescripcion = new JTextField();
		textFiltroDescripcion.setBounds(195, 50, 153, 20);
		panel_1.add(textFiltroDescripcion);
		textFiltroDescripcion.setColumns(10);
		
		textFiltroFecha = new JTextField();
		textFiltroFecha.setColumns(10);
		textFiltroFecha.setBounds(400, 50, 153, 20);
		panel_1.add(textFiltroFecha);

		lblHistorialCotizacion = new JLabel("Historial de Cambios de Cotizaci\u00F3n");
		lblHistorialCotizacion.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblHistorialCotizacion.setBounds(10, 41, 729, 30);
		frame.getContentPane().add(lblHistorialCotizacion);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 749, 41);
		frame.getContentPane().add(panel_2);

		lblZapateria = new JLabel("Zapater\u00EDa");
		panel_2.add(lblZapateria);
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 22));
	}
	
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelHistorialCambioMoneda() {
		return modelHistorialCambioMoneda;
	}

	public JTable getTablaHistorialCambioMoneda() {
		return tablaHistorialCambioMoneda;
	}

	public JButton getBtnVolverAModificarConversion() {
		return btnVolverAModificarConversion;
	}

	public JTextField getTextFiltroDescripcion() {
		return textFiltroDescripcion;
	}

	public JTextField getTextFiltroFecha() {
		return textFiltroFecha;
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
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
}