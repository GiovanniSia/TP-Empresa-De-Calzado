package presentacion.vista.Cajero;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class VentanaCierreCajaEclipse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JLabel lblZapateria;
	private JLabel lblCajaDiariaDeSucursal;
	private JPanel panelIngresosEgresos;
	private JScrollPane spIngresos;
	private JScrollPane spEgresos;
	private JLabel lblEgresos;
	private JLabel lblIngresos;
	private JButton btnAtras;
	private JLabel lblFechaHoy;
	private JPanel panelMedioPagoTotales;
	private JScrollPane spMedioPagoIngresos;
	private JLabel lblCierreCaja;
	private JScrollPane spMedioPagoEgresos;
	private JLabel lblTotalIngresos;
	private JLabel lblActualizarTotalIngresos;
	private JLabel lblTotalEgresos;
	private JButton btnCerrarCajaDeSucursal;
	private JLabel lblBalanceCaja;
	private JLabel lblActualizarTotalEgresos;
	private JPanel panel_3;
	private JPanel panel_3_1;
	private JPanel panel_3_2;
	private JLabel lblActualizarBalanceCaja;

	private JTable tablaIngresos;
	private String[] nombreColumnasIngresos = { "Hora", "Tipo", "Cliente", "T.Factura", "M.Pago", "Monto","Cotizacion",
			"Nro.Operacion", "Total"};
	private DefaultTableModel modelIngresos;

	private JTable tablaEgresos;
	private String[] nombreColumnasEgresos = { "Hora", "Tipo", "Detalle", "M.Pago", "Total" };
	private DefaultTableModel modelEgresos;

	private JTable tablaMedioPagoIngresos;
	private String[] nombreColumnasMedioPagoIngresos = { "M.Pago", "Descripcion", "Total","Validar" };
	private DefaultTableModel modelMedioPagoIngresos;

	private JTable tablaMedioPagoEgresos;
	private String[] nombreColumnasMedioPagoEgresos = { "M.Pago", "Descripcion", "Total", "Validar" };
	private DefaultTableModel modelMedioPagoEgresos;

	public VentanaCierreCajaEclipse() {
		initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 926, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBounds(0, 0, 910, 51);
		panel.setBackground(Color.GRAY);
		panel.setForeground(Color.GRAY);
		contentPane.add(panel);
		panel.setLayout(null);

		lblZapateria = new JLabel("ZAPATER\u00CDA");
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblZapateria.setBounds(10, 11, 347, 29);
		panel.add(lblZapateria);

		lblCajaDiariaDeSucursal = new JLabel("Caja Diaria de Sucursal");
		lblCajaDiariaDeSucursal.setBounds(10, 46, 528, 63);
		lblCajaDiariaDeSucursal.setFont(new Font("Tahoma", Font.PLAIN, 33));
		contentPane.add(lblCajaDiariaDeSucursal);

		panelIngresosEgresos = new JPanel();
		panelIngresosEgresos.setBounds(0, 108, 613, 431);
		contentPane.add(panelIngresosEgresos);
		panelIngresosEgresos.setLayout(null);

		spIngresos = new JScrollPane();
		spIngresos.setBounds(10, 32, 591, 159);
		panelIngresosEgresos.add(spIngresos);

		modelIngresos = new DefaultTableModel(null, nombreColumnasIngresos) {
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
		tablaIngresos = new JTable(modelIngresos);

		tablaIngresos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaIngresos.getColumnModel().getColumn(0).setResizable(false);
		tablaIngresos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaIngresos.getColumnModel().getColumn(1).setResizable(false);

		spIngresos.setViewportView(tablaIngresos);

		spEgresos = new JScrollPane();
		spEgresos.setBounds(10, 226, 591, 159);
		panelIngresosEgresos.add(spEgresos);

		modelEgresos = new DefaultTableModel(null, nombreColumnasEgresos) {
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
		tablaEgresos = new JTable(modelEgresos);

		tablaEgresos.getColumnModel().getColumn(0).setPreferredWidth(4);
		tablaEgresos.getColumnModel().getColumn(0).setResizable(false);
		tablaEgresos.getColumnModel().getColumn(1).setPreferredWidth(50);
		tablaEgresos.getColumnModel().getColumn(1).setResizable(false);
		tablaEgresos.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaEgresos.getColumnModel().getColumn(2).setResizable(false);

		spEgresos.setViewportView(tablaEgresos);

		lblEgresos = new JLabel("Egresos");
		lblEgresos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEgresos.setBounds(10, 202, 141, 21);
		panelIngresosEgresos.add(lblEgresos);

		lblIngresos = new JLabel("Ingresos");
		lblIngresos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIngresos.setBounds(10, 11, 153, 21);
		panelIngresosEgresos.add(lblIngresos);

		btnAtras = new JButton("Atras");
		btnAtras.setBounds(20, 396, 103, 24);
		panelIngresosEgresos.add(btnAtras);

		lblFechaHoy = new JLabel("Dia XX de XX de XXXX");
		lblFechaHoy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaHoy.setBounds(682, 62, 198, 21);
		contentPane.add(lblFechaHoy);

		panelMedioPagoTotales = new JPanel();
		panelMedioPagoTotales.setBounds(612, 108, 298, 431);
		contentPane.add(panelMedioPagoTotales);
		panelMedioPagoTotales.setLayout(null);

		spMedioPagoIngresos = new JScrollPane();
		spMedioPagoIngresos.setBounds(10, 32, 278, 159);
		panelMedioPagoTotales.add(spMedioPagoIngresos);

		modelMedioPagoIngresos = new DefaultTableModel(null, nombreColumnasMedioPagoIngresos) {
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

		tablaMedioPagoIngresos = new JTable(modelMedioPagoIngresos);

		tablaMedioPagoIngresos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaMedioPagoIngresos.getColumnModel().getColumn(0).setResizable(false);
		tablaMedioPagoIngresos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaMedioPagoIngresos.getColumnModel().getColumn(1).setResizable(false);

		spMedioPagoIngresos.setViewportView(tablaMedioPagoIngresos);
		
		
		agregarCheckBox(3,tablaMedioPagoIngresos);
		
		
		lblCierreCaja = new JLabel("Cierre Caja");
		lblCierreCaja.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCierreCaja.setBounds(10, 11, 141, 21);
		panelMedioPagoTotales.add(lblCierreCaja);

		spMedioPagoEgresos = new JScrollPane();
		spMedioPagoEgresos.setBounds(10, 229, 278, 58);
		panelMedioPagoTotales.add(spMedioPagoEgresos);

		modelMedioPagoEgresos = new DefaultTableModel(null, nombreColumnasMedioPagoEgresos) {
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

		tablaMedioPagoEgresos = new JTable(modelMedioPagoEgresos);

		tablaMedioPagoEgresos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaMedioPagoEgresos.getColumnModel().getColumn(0).setResizable(false);
		tablaMedioPagoEgresos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaMedioPagoEgresos.getColumnModel().getColumn(1).setResizable(false);

		spMedioPagoEgresos.setViewportView(tablaMedioPagoEgresos);
		
		agregarCheckBox(3,tablaMedioPagoEgresos);
		
		lblTotalIngresos = new JLabel("Total Ingresos (AR$)");
		lblTotalIngresos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalIngresos.setBounds(10, 195, 171, 29);
		panelMedioPagoTotales.add(lblTotalIngresos);

		lblActualizarTotalIngresos = new JLabel("Valor");
		lblActualizarTotalIngresos.setBounds(158, 202, 130, 14);
		panelMedioPagoTotales.add(lblActualizarTotalIngresos);

		lblTotalEgresos = new JLabel("Total Egresos(AR$)");
		lblTotalEgresos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalEgresos.setBounds(10, 296, 171, 29);
		panelMedioPagoTotales.add(lblTotalEgresos);

		btnCerrarCajaDeSucursal = new JButton("Cerrar Caja de Sucursal");
		btnCerrarCajaDeSucursal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCerrarCajaDeSucursal.setBounds(27, 391, 250, 29);
		panelMedioPagoTotales.add(btnCerrarCajaDeSucursal);

		lblBalanceCaja = new JLabel("Balance Caja");
		lblBalanceCaja.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBalanceCaja.setBounds(46, 340, 110, 29);
		panelMedioPagoTotales.add(lblBalanceCaja);

		lblActualizarTotalEgresos = new JLabel("Valor");
		lblActualizarTotalEgresos.setBounds(158, 305, 130, 14);
		panelMedioPagoTotales.add(lblActualizarTotalEgresos);

		panel_3 = new JPanel();
		panel_3.setBounds(147, 195, 141, 29);
		panelMedioPagoTotales.add(panel_3);
		panel_3.setLayout(null);
		panel_3.setForeground(Color.GRAY);
		panel_3.setBackground(Color.GRAY);

		panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setForeground(Color.GRAY);
		panel_3_1.setBackground(Color.GRAY);
		panel_3_1.setBounds(147, 296, 141, 29);
		panelMedioPagoTotales.add(panel_3_1);

		panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setForeground(Color.GRAY);
		panel_3_2.setBackground(Color.GRAY);
		panel_3_2.setBounds(147, 340, 141, 29);
		panelMedioPagoTotales.add(panel_3_2);

		lblActualizarBalanceCaja = new JLabel("Valor");
		lblActualizarBalanceCaja.setBounds(10, 11, 131, 14);
		panel_3_2.add(lblActualizarBalanceCaja);
	}
	
	public void agregarCheckBox(int columna, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(columna);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}

	//Fila: donde esta el checkbox
	//Columna: donde esta el checkbox
	//tabla: donde esta el checkbox
	
	public boolean estaSeleccionado(int fila, int columna, JTable tabla) {
		
		return tabla.getValueAt(fila,columna) != null;
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

	}

	public void cerrar() {
		limpiarCampos();
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JLabel getLblFechaHoy() {
		return lblFechaHoy;
	}

	public JLabel getLblActualizarTotalIngresos() {
		return lblActualizarTotalIngresos;
	}

	public JButton getBtnCerrarCajaDeSucursal() {
		return btnCerrarCajaDeSucursal;
	}

	public JLabel getLblActualizarTotalEgresos() {
		return lblActualizarTotalEgresos;
	}

	public JLabel getLblActualizarBalanceCaja() {
		return lblActualizarBalanceCaja;
	}

	public JTable getTablaIngresos() {
		return tablaIngresos;
	}

	public String[] getNombreColumnasIngresos() {
		return nombreColumnasIngresos;
	}

	public DefaultTableModel getModelIngresos() {
		return modelIngresos;
	}

	public JTable getTablaEgresos() {
		return tablaEgresos;
	}

	public String[] getNombreColumnasEgresos() {
		return nombreColumnasEgresos;
	}

	public DefaultTableModel getModelEgresos() {
		return modelEgresos;
	}

	public JTable getTablaMedioPagoIngresos() {
		return tablaMedioPagoIngresos;
	}

	public String[] getNombreColumnasMedioPagoIngresos() {
		return nombreColumnasMedioPagoIngresos;
	}

	public DefaultTableModel getModelMedioPagoIngresos() {
		return modelMedioPagoIngresos;
	}

	public JTable getTablaMedioPagoEgresos() {
		return tablaMedioPagoEgresos;
	}

	public String[] getNombreColumnasMedioPagoEgresos() {
		return nombreColumnasMedioPagoEgresos;
	}

	public DefaultTableModel getModelMedioPagoEgresos() {
		return modelMedioPagoEgresos;
	}

	public static void main(String[] args) {
		VentanaCierreCajaEclipse a = new VentanaCierreCajaEclipse();
		a.show();
	}

}
