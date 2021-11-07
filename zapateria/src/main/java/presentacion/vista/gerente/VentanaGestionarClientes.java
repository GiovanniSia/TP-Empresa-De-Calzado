package presentacion.vista.gerente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaGestionarClientes extends JFrame{
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private String[] nombreColumnas = { "Cod. Cliente", "Nombre", "Apellido", "CUIL", "Correo Electronico",
			"Limite Credito", "Credito Disponible","Tipo Cliente","AFIP", "Estado","Calle","Altura","Pais","Provincia","Localidad","CodPostal"};
	private JLabel lblNombre;
	private JTextField txtFieldNombre;
	private DefaultTableModel modelCliente;
	private JTable tablaClientes;
	private JTextField txtFieldApellido;
	private JTextField txtFieldCUIL;

	private JLabel lblCodCliente;
	private JTextField txtFieldCodCliente;
	private JLabel lblFiltrarPor;
	private JLabel lblElegirCliente;
	private JScrollPane spCliente;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JButton btnAtras;
	private JLabel lblAtrs;
	private JButton btnAgregarCliente;
	private JButton btnEditarCliente;
	private JButton btnHistorialDeCambios;


	public VentanaGestionarClientes() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 1100, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 191, 1076, 224);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 11, 1056, 145);
		spCliente.setBackground(new Color(248, 248, 255));
		panel.add(spCliente);

		modelCliente = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 5) {
					return true;
				} else {
					return false;
				}
			}
		};

		tablaClientes = new JTable(modelCliente);
		tablaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(5).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(7).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(8).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(9).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(10).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(11).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(12).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(13).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(14).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(15).setPreferredWidth(100);
		
		
//		tablaClientes.getColumnModel().getColumn(0).setResizable(false);

//		tablaClientes.getColumnModel().getColumn(1).setResizable(false);
		tablaClientes.getTableHeader().setReorderingAllowed(false);
		tablaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spCliente.setViewportView(tablaClientes);

		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(248, 248, 255));
		btnAtras.setBounds(20, 163, 55, 50);
		panel.add(btnAtras);
		cambiarIconoBotones(btnAtras, "back2.png");

		lblAtrs = new JLabel("Atras");
		lblAtrs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtrs.setBounds(85, 163, 70, 50);
		panel.add(lblAtrs);
		
		btnAgregarCliente = new JButton("Agregar Cliente");
		btnAgregarCliente.setBounds(271, 166, 106, 48);
		panel.add(btnAgregarCliente);
		
		btnEditarCliente = new JButton("Editar Cliente");
		btnEditarCliente.setBounds(512, 165, 106, 48);
		panel.add(btnEditarCliente);
		
		btnHistorialDeCambios = new JButton("Ver Historial de cambios");
		btnHistorialDeCambios.setBounds(648, 166, 152, 48);
		panel.add(btnHistorialDeCambios);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(248, 248, 255));
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 95, 1076, 95);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(108, 36, 70, 20);
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblNombre);

		txtFieldNombre = new JTextField();

		txtFieldNombre.setBounds(108, 67, 116, 20);
		panel_1.add(txtFieldNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblApellido.setBounds(251, 36, 70, 20);
		panel_1.add(lblApellido);

		JLabel lblCUIL = new JLabel("CUIL");
		lblCUIL.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCUIL.setBounds(409, 36, 41, 20);
		panel_1.add(lblCUIL);

		txtFieldApellido = new JTextField();
		txtFieldApellido.setBounds(251, 67, 131, 20);
		panel_1.add(txtFieldApellido);

		txtFieldCUIL = new JTextField();
		txtFieldCUIL.setBounds(409, 67, 116, 20);
		panel_1.add(txtFieldCUIL);

		lblCodCliente = new JLabel("Cod. Cliente");
		lblCodCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCodCliente.setBounds(10, 36, 86, 20);
		panel_1.add(lblCodCliente);

		txtFieldCodCliente = new JTextField();
		txtFieldCodCliente.setBounds(10, 67, 70, 20);
		panel_1.add(txtFieldCodCliente);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 0, 70, 25);
		panel_1.add(lblFiltrarPor);

		lblElegirCliente = new JLabel("Elegir Cliente");
		lblElegirCliente.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblElegirCliente.setBounds(10, 52, 189, 43);
		frame.getContentPane().add(lblElegirCliente);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 1076, 50);
		frame.getContentPane().add(panel_2);

		lblNewLabel = new JLabel("Zapateria Argento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 0, 421, 50);
		panel_2.add(lblNewLabel);
	}

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estas seguro que quieres salir?", "Advertencia",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public void cerrar() {
		this.txtFieldCodCliente.setText(null);
		this.txtFieldNombre.setText(null);
		this.txtFieldApellido.setText(null);
		this.txtFieldCUIL.setText(null);
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	
	
	public JFrame getFrame() {
		return frame;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
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

	public JTextField getTxtFieldApellido() {
		return txtFieldApellido;
	}

	public JTextField getTxtFieldCUIL() {
		return txtFieldCUIL;
	}

	public JTextField getTxtFieldCodCliente() {
		return txtFieldCodCliente;
	}

	public JScrollPane getSpCliente() {
		return spCliente;
	}

	public JPanel getPanel_2() {
		return panel_2;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnAgregarCliente() {
		return btnAgregarCliente;
	}

	public JButton getBtnEditarCliente() {
		return btnEditarCliente;
	}

	public JButton getBtnHistorialDeCambios() {
		return btnHistorialDeCambios;
	}
}
