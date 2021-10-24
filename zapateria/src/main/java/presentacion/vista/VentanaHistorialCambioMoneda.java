package presentacion.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import persistencia.conexion.Conexion;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaHistorialCambioMoneda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Cod. Moneda","Descripcion","Empleado","Fecha","Hora","T.Conversion Antigua","T.Conversion Nueva" };
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
	private JTextField textFiltroCodMoneda;
	private JLabel lblCodMoneda;
	private JLabel lblDescripcion;
	private JLabel lblFechaDesde;
	private JLabel lblFechaHasta;
	private JDateChooser fechaDesde; 
	private JDateChooser fechaHasta;
	private JButton btnFiltrarFechas;
	private JButton btnReiniciarTabla;
	
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
		frame.setBounds(100, 100, 765, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBounds(0, 157, 749, 207);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spHistorialCambioMoneda = new JScrollPane();
		spHistorialCambioMoneda.setBounds(10, 11, 729, 159);
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

		btnVolverAModificarConversion.setBounds(251, 181, 244, 23);
		panel.add(btnVolverAModificarConversion);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 69, 749, 93);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 11, 70, 14);
		panel_1.add(lblFiltrarPor);
		
		lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(133, 37, 153, 14);
		panel_1.add(lblDescripcion);
		
		lblFechaDesde = new JLabel("Fecha desde");
		lblFechaDesde.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDesde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaDesde.setBounds(309, 37, 108, 14);
		panel_1.add(lblFechaDesde);
		
		textFiltroDescripcion = new JTextField();
		textFiltroDescripcion.setBounds(133, 62, 153, 20);
		panel_1.add(textFiltroDescripcion);
		textFiltroDescripcion.setColumns(10);
		
		textFiltroCodMoneda = new JTextField();
		textFiltroCodMoneda.setColumns(10);
		textFiltroCodMoneda.setBounds(20, 62, 103, 20);
		panel_1.add(textFiltroCodMoneda);
		
		lblCodMoneda = new JLabel("Cod. Moneda");
		lblCodMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodMoneda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodMoneda.setBounds(20, 39, 103, 14);
		panel_1.add(lblCodMoneda);
		
		fechaHasta = new JDateChooser();
		fechaHasta.setBounds(427, 62, 108, 19);
		panel_1.add(fechaHasta);
		
		fechaDesde = new JDateChooser();
		fechaDesde.setBounds(309, 62, 108, 19);
		panel_1.add(fechaDesde);
		
		lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaHasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaHasta.setBounds(427, 39, 108, 14);
		panel_1.add(lblFechaHasta);
		
		btnFiltrarFechas = new JButton("Filtrar por Fechas");
		btnFiltrarFechas.setBounds(545, 62, 117, 21);
		panel_1.add(btnFiltrarFechas);
		
		btnReiniciarTabla = new JButton("Quitar filtro");
		btnReiniciarTabla.setBounds(583, 9, 138, 23);
		panel_1.add(btnReiniciarTabla);

		lblHistorialCotizacion = new JLabel("Historial de Cambios de Cotizaci\u00F3n");
		lblHistorialCotizacion.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblHistorialCotizacion.setBounds(10, 41, 397, 30);
		frame.getContentPane().add(lblHistorialCotizacion);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 749, 41);
		frame.getContentPane().add(panel_2);

		lblZapateria = new JLabel("Zapater\u00EDa");
		panel_2.add(lblZapateria);
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 22));
	}
	
	
	public JButton getBtnReiniciarTabla() {
		return btnReiniciarTabla;
	}

	public JButton getBtnFiltrarFechas() {
		return btnFiltrarFechas;
	}

	public JDateChooser getFechaDesde() {
		return fechaDesde;
	}

	public JDateChooser getFechaHasta() {
		return fechaHasta;
	}

	public JTextField getTextFiltroCodMoneda() {
		return textFiltroCodMoneda;
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

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estas seguro que quieres salir?", 
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
		this.textFiltroCodMoneda.setText(null);
		this.textFiltroDescripcion.setText(null);
		this.fechaDesde.setDate(null);
		this.fechaHasta.setDate(null);
		frame.setVisible(false);
	}
	
	public void limpiarCampos() {
		this.textFiltroCodMoneda.setText(null);
		this.textFiltroDescripcion.setText(null);
		this.fechaDesde.setDate(null);
		this.fechaHasta.setDate(null);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
}