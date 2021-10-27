package presentacion.vista.Cajero;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VentanaCierreCaja extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
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
	private String[] nombreColumnasIngresos = { "Hora", "Tipo", "Cliente", "T.Factura", "M.Pago", "Monto", "Cotizacion",
			"Nro.Operacion", "Total" };
	private DefaultTableModel modelIngresos;

	private JTable tablaEgresos;
	private String[] nombreColumnasEgresos = { "Hora", "Tipo", "Detalle", "M.Pago", "Total" };
	private DefaultTableModel modelEgresos;

	private JTable tablaMedioPagoIngresos;
	private String[] nombreColumnasMedioPagoIngresos = { "M.Pago", "Descripcion", "Total", "Validar" };
	private DefaultTableModel modelMedioPagoIngresos;

	private JTable tablaMedioPagoEgresos;
	private String[] nombreColumnasMedioPagoEgresos = { "M.Pago", "Descripcion", "Total", "Validar" };
	private DefaultTableModel modelMedioPagoEgresos;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblCerrarCajaDe;
	private JLabel lblAtras;

	public VentanaCierreCaja() {
		initialize();
	}

	public void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 926, 653);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCajaDiariaDeSucursal = new JLabel("Caja Diaria de Sucursal");
		lblCajaDiariaDeSucursal.setBackground(new Color(248, 248, 255));
		lblCajaDiariaDeSucursal.setBounds(10, 49, 528, 50);
		lblCajaDiariaDeSucursal.setFont(new Font("Segoe UI", Font.BOLD, 22));
		contentPane.add(lblCajaDiariaDeSucursal);

		panelIngresosEgresos = new JPanel();
		panelIngresosEgresos.setBackground(new Color(248, 248, 255));
		panelIngresosEgresos.setBounds(0, 94, 613, 520);
		contentPane.add(panelIngresosEgresos);
		panelIngresosEgresos.setLayout(null);

		spIngresos = new JScrollPane();
		spIngresos.setBounds(10, 45, 591, 187);
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
		tablaIngresos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spIngresos.setViewportView(tablaIngresos);

		spEgresos = new JScrollPane();
		spEgresos.setBounds(10, 275, 591, 159);
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
		tablaEgresos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spEgresos.setViewportView(tablaEgresos);

		lblEgresos = new JLabel("Egresos");
		lblEgresos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEgresos.setBounds(10, 234, 141, 45);
		panelIngresosEgresos.add(lblEgresos);

		lblIngresos = new JLabel("Ingresos");
		lblIngresos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblIngresos.setBounds(10, 0, 153, 45);
		panelIngresosEgresos.add(lblIngresos);
		
				btnAtras = new JButton("");
				btnAtras.setBounds(82, 440, 69, 69);
				cambiarIconoBotones(btnAtras,  "back2.png");
				panelIngresosEgresos.add(btnAtras);
				
				lblAtras = new JLabel("Atras");
				lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblAtras.setBounds(161, 440, 42, 69);
				panelIngresosEgresos.add(lblAtras);

		lblFechaHoy = new JLabel("Dia XX de XX de XXXX");
		lblFechaHoy.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaHoy.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFechaHoy.setBounds(612, 49, 298, 44);
		contentPane.add(lblFechaHoy);

		panelMedioPagoTotales = new JPanel();
		panelMedioPagoTotales.setBackground(new Color(248, 248, 255));
		panelMedioPagoTotales.setBounds(612, 94, 298, 520);
		contentPane.add(panelMedioPagoTotales);
		panelMedioPagoTotales.setLayout(null);

		spMedioPagoIngresos = new JScrollPane();
		spMedioPagoIngresos.setBounds(10, 46, 278, 152);
		panelMedioPagoTotales.add(spMedioPagoIngresos);

		modelMedioPagoIngresos = new DefaultTableModel(null, nombreColumnasMedioPagoIngresos);
		tablaMedioPagoIngresos = new JTable(modelMedioPagoIngresos) {
			
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Double.class;
				default:
					return Boolean.class;
				}
			}
		};
		tablaMedioPagoIngresos.setPreferredScrollableViewportSize(tablaMedioPagoIngresos.getPreferredSize());

		tablaMedioPagoIngresos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaMedioPagoIngresos.getColumnModel().getColumn(0).setResizable(false);
		tablaMedioPagoIngresos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaMedioPagoIngresos.getColumnModel().getColumn(1).setResizable(false);
		tablaMedioPagoIngresos.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		spMedioPagoIngresos.setViewportView(tablaMedioPagoIngresos);

		lblCierreCaja = new JLabel("Cierre Caja");
		lblCierreCaja.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCierreCaja.setBounds(10, 0, 141, 46);
		panelMedioPagoTotales.add(lblCierreCaja);

		spMedioPagoEgresos = new JScrollPane();
		spMedioPagoEgresos.setBounds(10, 277, 278, 70);
		panelMedioPagoTotales.add(spMedioPagoEgresos);

		modelMedioPagoEgresos = new DefaultTableModel(null, nombreColumnasMedioPagoEgresos);
		tablaMedioPagoEgresos = new JTable(modelMedioPagoEgresos) {
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Double.class;
				default:
					return Boolean.class;
				}
			}
		};
		tablaMedioPagoEgresos.setPreferredScrollableViewportSize(tablaMedioPagoEgresos.getPreferredSize());

		tablaMedioPagoEgresos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaMedioPagoEgresos.getColumnModel().getColumn(0).setResizable(false);
		tablaMedioPagoEgresos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaMedioPagoEgresos.getColumnModel().getColumn(1).setResizable(false);
		tablaMedioPagoEgresos.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		spMedioPagoEgresos.setViewportView(tablaMedioPagoEgresos);

		lblTotalIngresos = new JLabel("Total Ingresos (AR$)");
		lblTotalIngresos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalIngresos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTotalIngresos.setBounds(10, 209, 130, 29);
		panelMedioPagoTotales.add(lblTotalIngresos);

		lblActualizarTotalIngresos = new JLabel("$0000.00");
		lblActualizarTotalIngresos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblActualizarTotalIngresos.setForeground(new Color(255, 255, 255));
		lblActualizarTotalIngresos.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblActualizarTotalIngresos.setBounds(147, 209, 130, 29);
		panelMedioPagoTotales.add(lblActualizarTotalIngresos);

		lblTotalEgresos = new JLabel("Total Egresos(AR$)");
		lblTotalEgresos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalEgresos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTotalEgresos.setBounds(10, 356, 130, 29);
		panelMedioPagoTotales.add(lblTotalEgresos);

		btnCerrarCajaDeSucursal = new JButton("");
		btnCerrarCajaDeSucursal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCerrarCajaDeSucursal.setBounds(10, 440, 69, 69);
		cambiarIconoBotones(btnCerrarCajaDeSucursal,  "check.png");
		panelMedioPagoTotales.add(btnCerrarCajaDeSucursal);

		lblBalanceCaja = new JLabel("Balance Caja");
		lblBalanceCaja.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBalanceCaja.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblBalanceCaja.setBounds(46, 400, 94, 29);
		panelMedioPagoTotales.add(lblBalanceCaja);

		lblActualizarTotalEgresos = new JLabel("$0000.00");
		lblActualizarTotalEgresos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblActualizarTotalEgresos.setForeground(new Color(255, 255, 255));
		lblActualizarTotalEgresos.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblActualizarTotalEgresos.setBounds(147, 358, 130, 27);
		panelMedioPagoTotales.add(lblActualizarTotalEgresos);

		panel_3 = new JPanel();
		panel_3.setBounds(147, 209, 141, 29);
		panelMedioPagoTotales.add(panel_3);
		panel_3.setLayout(null);
		panel_3.setForeground(Color.GRAY);
		panel_3.setBackground(Color.GRAY);

		panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setForeground(Color.GRAY);
		panel_3_1.setBackground(Color.GRAY);
		panel_3_1.setBounds(147, 356, 141, 29);
		panelMedioPagoTotales.add(panel_3_1);

		panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setForeground(Color.GRAY);
		panel_3_2.setBackground(Color.GRAY);
		panel_3_2.setBounds(147, 400, 141, 29);
		panelMedioPagoTotales.add(panel_3_2);

		lblActualizarBalanceCaja = new JLabel("$0000.00");
		lblActualizarBalanceCaja.setHorizontalAlignment(SwingConstants.TRAILING);
		lblActualizarBalanceCaja.setForeground(new Color(255, 255, 255));
		lblActualizarBalanceCaja.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblActualizarBalanceCaja.setBounds(0, 0, 131, 29);
		panel_3_2.add(lblActualizarBalanceCaja);
		
		lblCerrarCajaDe = new JLabel("Cerrar Caja de Sucursal");
		lblCerrarCajaDe.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCerrarCajaDe.setBounds(89, 440, 199, 69);
		panelMedioPagoTotales.add(lblCerrarCajaDe);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 910, 50);
		contentPane.add(panel);
		
		lblNewLabel = new JLabel("Zapateria Argento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 0, 421, 50);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Sucursal:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(565, 22, 109, 14);
		panel.add(lblNewLabel_1);

	}
	
	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);

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
		VentanaCierreCaja a = new VentanaCierreCaja();
		a.show();
	}
}
