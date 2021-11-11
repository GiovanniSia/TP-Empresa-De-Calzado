package presentacion.vista.gerente;


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
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

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
	private JLabel lblTitulo;
	private JScrollPane spCliente;
	private JButton btnAtras;
	private JLabel lblAtrs;
	private JButton btnAgregarCliente;
	private JButton btnEditarCliente;
	private JButton btnHistorialDeCambios;
	private JPanel panel_2;
	private JLabel lblLogo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblAgregarCliente;
	private JLabel lblEditarCliente;
	private JLabel lblVerHistorialDe;


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
		frame.setBounds(100, 100, 1092, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(0, 191, 1076, 210);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 11, 1056, 188);
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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255, 180));
		panel_1.setBounds(0, 95, 1076, 99);
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

		lblTitulo = new JLabel("Elegir Cliente");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setBounds(10, 52, 452, 43);
		frame.getContentPane().add(lblTitulo);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 1076, 53);
		frame.getContentPane().add(panel_2);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel_2.add(lblLogo);
		
		lblNewLabel = new JLabel("Sucursal:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(814, 28, 59, 19);
		panel_2.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Empleado:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(620, 28, 59, 19);
		panel_2.add(lblNewLabel_1);
		
				btnAtras = new JButton("");
				btnAtras.setBounds(37, 412, 60, 60);
				frame.getContentPane().add(btnAtras);
				btnAtras.setBackground(new Color(248, 248, 255));
				cambiarIconoBotones(btnAtras, "back2.png");
				
						lblAtrs = new JLabel("Atras");
						lblAtrs.setBounds(107, 412, 70, 60);
						frame.getContentPane().add(lblAtrs);
						lblAtrs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
						
						btnAgregarCliente = new JButton("");
						btnAgregarCliente.setBounds(272, 412, 60, 60);
						cambiarIconoBotones(btnAgregarCliente, "person+.png");
						frame.getContentPane().add(btnAgregarCliente);
						
						btnEditarCliente = new JButton("");
						btnEditarCliente.setBounds(551, 412, 60, 60);
						cambiarIconoBotones(btnEditarCliente, "personedit.png");
						frame.getContentPane().add(btnEditarCliente);
						
						btnHistorialDeCambios = new JButton("");
						btnHistorialDeCambios.setBounds(803, 412, 60, 60);
						cambiarIconoBotones(btnHistorialDeCambios, "history.png");
						frame.getContentPane().add(btnHistorialDeCambios);
						
						lblAgregarCliente = new JLabel("Agregar Cliente");
						lblAgregarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
						lblAgregarCliente.setBounds(342, 412, 117, 60);
						frame.getContentPane().add(lblAgregarCliente);
						
						lblEditarCliente = new JLabel("Editar Cliente");
						lblEditarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
						lblEditarCliente.setBounds(621, 412, 117, 60);
						frame.getContentPane().add(lblEditarCliente);
						
						lblVerHistorialDe = new JLabel("Ver Historial de Cambios");
						lblVerHistorialDe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
						lblVerHistorialDe.setBounds(873, 412, 180, 60);
						frame.getContentPane().add(lblVerHistorialDe);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 1100, 825);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
	}
	
	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
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
	
	public void mostrarVentanaParaGerente() {
		lblTitulo.setText("Ver Clientes");
		btnAgregarCliente.setVisible(false);
		lblAgregarCliente.setVisible(false);
		btnAtras.setVisible(true);
		btnEditarCliente.setVisible(false);
		lblEditarCliente.setVisible(false);
		btnHistorialDeCambios.setVisible(false);
		lblVerHistorialDe.setVisible(false);	
	}
	
	public void mostrarVentanaParaSupervisor() {
		lblTitulo.setText("Ver Clientes");
		btnAgregarCliente.setVisible(false);
		lblAgregarCliente.setVisible(false);
		btnAtras.setVisible(true);
		btnEditarCliente.setVisible(false);
		lblEditarCliente.setVisible(false);
		btnHistorialDeCambios.setVisible(false);
		lblVerHistorialDe.setVisible(false);	
	}
	
	public void mostrarVentanaParaVendedor() {
		lblTitulo.setText("Ver y Agregar Clientes");
		btnAtras.setVisible(true);
		btnEditarCliente.setVisible(false);
		lblEditarCliente.setVisible(false);
		btnHistorialDeCambios.setVisible(false);
		lblVerHistorialDe.setVisible(false);	
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
