package presentacion.vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class VentanaBusquedaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnPasarAVenta;
	private JButton btnAtras;
	private String[] nombreColumnas = { "Cod. Cliente", "Nombre", "Apellido", "DNI", "Correo Electronico",
			"Estado" };
	private JLabel lblZapateria;
	private JLabel lblNombre;
	private JTextField txtFieldNombre;
	private DefaultTableModel modelCliente;
	private JTable tablaClientes;
	private JTextField txtFieldApellido;
	private JTextField txtFieldDNI;
	private JPanel panel_2;

	private JLabel lblCodCliente;
	private JTextField txtFieldCodCliente;
	private JLabel lblFiltrarPor;
	private JLabel lblElegirCliente;

	public VentanaBusquedaCliente() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 191, 806, 203);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spCliente = new JScrollPane();
		spCliente.setBounds(10, 11, 776, 145);
		panel.add(spCliente);

		modelCliente = new DefaultTableModel(null, nombreColumnas);
		tablaClientes = new JTable(modelCliente);
		
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaClientes.getColumnModel().getColumn(0).setResizable(false);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(1).setResizable(false);
		
		spCliente.setViewportView(tablaClientes);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnAtras.setBounds(10, 167, 108, 23);
		panel.add(btnAtras);

		btnPasarAVenta = new JButton("Pasar a Venta");
		btnPasarAVenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPasarAVenta.setBounds(290, 167, 159, 23);
		panel.add(btnPasarAVenta);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 95, 806, 95);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(108, 36, 70, 20);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblNombre);

		txtFieldNombre = new JTextField();

		txtFieldNombre.setBounds(108, 67, 116, 20);
		panel_1.add(txtFieldNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellido.setBounds(251, 36, 70, 20);
		panel_1.add(lblApellido);

		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNI.setBounds(409, 36, 41, 20);
		panel_1.add(lblDNI);

		txtFieldApellido = new JTextField();
		txtFieldApellido.setBounds(251, 67, 131, 20);
		panel_1.add(txtFieldApellido);

		txtFieldDNI = new JTextField();
		txtFieldDNI.setBounds(409, 67, 116, 20);
		panel_1.add(txtFieldDNI);

		lblCodCliente = new JLabel("Cod. Cliente");
		lblCodCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodCliente.setBounds(10, 36, 86, 20);
		panel_1.add(lblCodCliente);

		txtFieldCodCliente = new JTextField();
		txtFieldCodCliente.setBounds(10, 67, 70, 20);
		panel_1.add(txtFieldCodCliente);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 11, 70, 14);
		panel_1.add(lblFiltrarPor);

		lblZapateria = new JLabel("Zapater\u00EDa");
		lblZapateria.setBounds(10, 11, 129, 30);
		frame.getContentPane().add(lblZapateria);
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 22));

		lblElegirCliente = new JLabel("Elegir Cliente");
		lblElegirCliente.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblElegirCliente.setBounds(10, 52, 129, 30);
		frame.getContentPane().add(lblElegirCliente);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 806, 41);
		frame.getContentPane().add(panel_2);
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
		this.txtFieldCodCliente.setText(null);
		this.txtFieldNombre.setText(null);
		this.txtFieldApellido.setText(null);
		this.txtFieldDNI.setText(null);
		frame.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	public JButton getBtnPasarAVenta() {
		return btnPasarAVenta;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JTextField getTxtFieldNombre() {
		return txtFieldNombre;
	}

	public DefaultTableModel getModelCliente() {
		return modelCliente;
	}

	public JTable getTablaClientes() {
		return tablaClientes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public JTextField getTxtFieldApellido() {
		return txtFieldApellido;
	}

	public JTextField getTxtFieldDNI() {
		return txtFieldDNI;
	}

	public JTextField getTxtFieldCodCliente() {
		return txtFieldCodCliente;
	}

	public int filaClienteSeleccionada() {
		return tablaClientes.getSelectedRow();
	}
	
}
