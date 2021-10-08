package presentacion.vista.Cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;

public class VentanaRealizarVenta {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	
	private JTable tableCliente;
	private DefaultTableModel modelTablaCliente;
	private String[] nombreColumnasCliente = {"cod Cliente","Nombre","Apellido","DNI","Correo Electrónico","Estado", "Categoría AFIP","Tipo","Límite Crédito","Crédito Disponible"};
	private JScrollPane scrollPaneCliente;
	
	
	private JTable tableDomicilio;
	private DefaultTableModel modelTablaDomicilio;
	private String[] nombreColumnasDomicilio = {"Calle","Altura","Localidad","Cód. Postal","Provincia","País"};
	private JScrollPane scrollPaneDomicilio;
	
	private JTable tableProductos;
	private DefaultTableModel modelTablaProductos;
	private String[] nombreColumnasProductos = {"Producto","Cantidad","Precio Unitario","Precio Total"};
	private JScrollPane scrollPaneProductos;
	
	private JTable tableMedioPago;
	private DefaultTableModel modelTablaMedioPago;
	private String[] nombreColumnasMedioPago = {"Método","Moneda","Nom. Tarjeta","4 díg.","Cantidad","Cant. (en AR$)"};
	private JScrollPane scrollPaneMedioPago;
	
	private JComboBox comboBoxMetodoPago;
	private JComboBox comboBoxMoneda;

	
	private JTextField textNombreTarjeta;
	private JTextField textFieldUltDigitos;
	private JSpinner spinnerCantidadPago;


	private JButton btnAgregarMedioPago;

	private JLabel lblTotalAPagarValor;
	private JLabel lblPrecioVentaValor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRealizarVenta window = new VentanaRealizarVenta();
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
	public VentanaRealizarVenta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1019, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1005, 689);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 10, 985, 41);
		panel.add(panel_1);
		
		JLabel lblTitulo = new JLabel("Zapater\u00EDa");
		lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 38));
		lblTitulo.setBounds(10, 0, 310, 41);
		panel_1.add(lblTitulo);
		
		JLabel lblNewLabel = new JLabel("Venta");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 29));
		lblNewLabel.setBounds(20, 50, 234, 47);
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 96, 985, 553);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDatosCliente = new JLabel("Datos de Cliente");
		lblDatosCliente.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblDatosCliente.setBounds(10, 10, 131, 21);
		panel_2.add(lblDatosCliente);
		
		
		JLabel lblDomicilio = new JLabel("Datos de domicilio");
		lblDomicilio.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblDomicilio.setBounds(10, 100, 142, 21);
		panel_2.add(lblDomicilio);
		
		
		JLabel lblDatosCompra = new JLabel("Datos de la compra");
		lblDatosCompra.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblDatosCompra.setBounds(10, 163, 158, 21);
		panel_2.add(lblDatosCompra);
		
		JLabel lblMediosDePago = new JLabel("Medios de pago");
		lblMediosDePago.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblMediosDePago.setBounds(334, 164, 158, 21);
		panel_2.add(lblMediosDePago);
		
		JLabel lblPrecioVenta = new JLabel("Precio de venta:");
		lblPrecioVenta.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblPrecioVenta.setBounds(10, 471, 158, 34);
		panel_2.add(lblPrecioVenta);
		
		lblPrecioVentaValor = new JLabel("$0");
		lblPrecioVentaValor.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblPrecioVentaValor.setForeground(new Color(0, 0, 0));
		lblPrecioVentaValor.setBounds(169, 471, 155, 34);
		panel_2.add(lblPrecioVentaValor);
		
		JLabel lblMtodoDePago = new JLabel("Método de pago");
		lblMtodoDePago.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblMtodoDePago.setBounds(334, 196, 100, 21);
		panel_2.add(lblMtodoDePago);
		
		JLabel lblMoneda = new JLabel("Moneda");
		lblMoneda.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblMoneda.setBounds(444, 196, 72, 21);
		panel_2.add(lblMoneda);
		
		JLabel lblNombreTarjeta = new JLabel("Nombre de la Tarjeta");
		lblNombreTarjeta.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNombreTarjeta.setBounds(526, 196, 142, 21);
		panel_2.add(lblNombreTarjeta);
		
		textNombreTarjeta = new JTextField();
		textNombreTarjeta.setBounds(526, 214, 142, 20);
		panel_2.add(textNombreTarjeta);
		textNombreTarjeta.setColumns(10);
		
		JLabel lblltimosDgitos = new JLabel("Últimos 4 dígitos");
		lblltimosDgitos.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblltimosDgitos.setBounds(678, 198, 142, 21);
		panel_2.add(lblltimosDgitos);
		
		textFieldUltDigitos = new JTextField();
		textFieldUltDigitos.setColumns(10);
		textFieldUltDigitos.setBounds(678, 214, 80, 20);
		panel_2.add(textFieldUltDigitos);
		
		btnAgregarMedioPago = new JButton("Agregar otro medio de pago");
		btnAgregarMedioPago.setBounds(798, 164, 177, 27);
		panel_2.add(btnAgregarMedioPago);
		
		JLabel lblTotalAPagar = new JLabel("Total a pagar:");
		lblTotalAPagar.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblTotalAPagar.setBounds(535, 471, 158, 34);
		panel_2.add(lblTotalAPagar);
		
		lblTotalAPagarValor = new JLabel("$0");
		lblTotalAPagarValor.setBackground(new Color(255, 255, 255));
		lblTotalAPagarValor.setForeground(new Color(0, 128, 0));
		lblTotalAPagarValor.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblTotalAPagarValor.setBounds(665, 471, 155, 34);
		panel_2.add(lblTotalAPagarValor);
		
		//TABLAS
		//TABLA Cliente
		this.modelTablaCliente = new DefaultTableModel(null, this.nombreColumnasCliente){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				if(columnas == 10) {
					return true;
				}else {
					return false;
				}
			}
		};
		scrollPaneCliente = new JScrollPane(this.tableCliente, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCliente.setBounds(10, 26, 965, 64);
		
		tableCliente = new JTable(modelTablaCliente);
		tableCliente.setBounds(10, 26, 965, 64);
		this.tableCliente.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCliente.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneCliente.setViewportView(tableCliente);
		panel_2.add(scrollPaneCliente);
		
		
		//Tabla Domicilio
		this.modelTablaDomicilio = new DefaultTableModel(null, this.nombreColumnasDomicilio){
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
		scrollPaneDomicilio = new JScrollPane(this.tableDomicilio, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneDomicilio.setBounds(140, 101, 835, 53);
		
		tableDomicilio = new JTable(modelTablaDomicilio);
		tableDomicilio.setBounds(140, 100, 835, 54);
		this.tableDomicilio.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableDomicilio.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneDomicilio.setViewportView(tableDomicilio);
		panel_2.add(scrollPaneDomicilio);
		
		
		
		
		//Tabla Productos
		this.modelTablaProductos = new DefaultTableModel(null, this.nombreColumnasProductos){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				if(columnas == 4) {
					return true;
				}else {
					return false;
				}
			}
		};
		scrollPaneProductos = new JScrollPane(this.tableProductos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneProductos.setBounds(10, 183, 314, 278);
		
		tableProductos = new JTable(modelTablaProductos);
		tableProductos.setBounds(10, 183, 314, 278);
		this.tableProductos.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableProductos.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneProductos.setViewportView(tableProductos);
		panel_2.add(scrollPaneProductos);
		
		
		
		//Tabla Medio Pago
		this.modelTablaMedioPago = new DefaultTableModel(null, this.nombreColumnasMedioPago){
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
		scrollPaneMedioPago = new JScrollPane(this.tableMedioPago, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneMedioPago.setBounds(334, 254, 641, 208);
		
		tableMedioPago = new JTable(modelTablaMedioPago);
		tableMedioPago.setBounds(334, 254, 641, 208);
		this.tableMedioPago.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableMedioPago.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneMedioPago.setViewportView(tableMedioPago);
		panel_2.add(scrollPaneMedioPago);
		//
		
		//ComboBox
		comboBoxMetodoPago = new JComboBox();
		comboBoxMetodoPago.addItem("Sin seleccionar");
		comboBoxMetodoPago.setBounds(334, 213, 100, 21);
		panel_2.add(comboBoxMetodoPago);
		
		comboBoxMoneda = new JComboBox();
		comboBoxMoneda.addItem("Sin seleccionar");
		comboBoxMoneda.setBounds(444, 213, 72, 21);
		panel_2.add(comboBoxMoneda);
		
		
		
		//
		//Spinner
		spinnerCantidadPago = new JSpinner();
		spinnerCantidadPago.setBounds(830, 214, 117, 20);
		panel_2.add(spinnerCantidadPago);
		
		JLabel lblModificarCantidad = new JLabel("Modificar Cantidad");
		lblModificarCantidad.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblModificarCantidad.setBounds(830, 198, 142, 21);
		panel_2.add(lblModificarCantidad);


	}
	
	
	
	public JTable getTableCliente() {
		return tableCliente;
	}

	public JScrollPane getScrollPaneCliente() {
		return scrollPaneCliente;
	}

	public JTable getTableDomicilio() {
		return tableDomicilio;
	}

	public JScrollPane getScrollPaneDomicilio() {
		return scrollPaneDomicilio;
	}

	public JTable getTableProductos() {
		return tableProductos;
	}

	public JScrollPane getScrollPaneProductos() {
		return scrollPaneProductos;
	}

	public JTable getTableMedioPago() {
		return tableMedioPago;
	}

	public JComboBox getComboBoxMetodoPago() {
		return comboBoxMetodoPago;
	}

	public JComboBox getComboBoxMoneda() {
		return comboBoxMoneda;
	}

	public JScrollPane getScrollPaneMedioPago() {
		return scrollPaneMedioPago;
	}

	public JTextField getTextNombreTarjeta() {
		return textNombreTarjeta;
	}

	public JTextField getTextFieldUltDigitos() {
		return textFieldUltDigitos;
	}

	public JButton getBtnAgregarMedioPago() {
		return btnAgregarMedioPago;
	}

	public JLabel getLblTotalAPagarValor() {
		return lblTotalAPagarValor;
	}

	public JLabel getLblPrecioVentaValor() {
		return lblPrecioVentaValor;
	}
	public DefaultTableModel getModelTablaCliente() {
		return modelTablaCliente;
	}

	public String[] getNombreColumnasCliente() {
		return nombreColumnasCliente;
	}

	public DefaultTableModel getModelTablaDomicilio() {
		return modelTablaDomicilio;
	}

	public String[] getNombreColumnasDomicilio() {
		return nombreColumnasDomicilio;
	}

	public DefaultTableModel getModelTablaProductos() {
		return modelTablaProductos;
	}

	public String[] getNombreColumnasProductos() {
		return nombreColumnasProductos;
	}

	public DefaultTableModel getModelTablaMedioPago() {
		return modelTablaMedioPago;
	}

	public String[] getNombreColumnasMedioPago() {
		return nombreColumnasMedioPago;
	}
	
	public JSpinner getSpinnerCantidadPago() {
		return spinnerCantidadPago;
	}
}
