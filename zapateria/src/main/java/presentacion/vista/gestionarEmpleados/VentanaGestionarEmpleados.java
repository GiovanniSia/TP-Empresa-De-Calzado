package presentacion.vista.gestionarEmpleados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

public class VentanaGestionarEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private String[] nombreColumnas = { "Cod.Empleado", "CUIL", "Nombre", "Apellido", "CorreoElectronico", "TipoEmpleado"};
	private DefaultTableModel model;
	private JTable tablaEmpleados;
	private JButton btnAtras;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnHistorialCambio;
	private JTextField txtFiltroIdEmpleado;
	private JTextField txtFiltroCUIL;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTipoEmpleado;
	
//	public static void main(String[] args) {
//		VentanaGestionarEmpleados a = new VentanaGestionarEmpleados();
//		a.initialize();
//		a.mostrarVentana();
//	}

	public VentanaGestionarEmpleados() {
		this.initialize();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 741, 508);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Gestionar Empleados");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel_1.setBounds(10, 53, 476, 53);
		contentPane.add(lblNewLabel_1);

		JScrollPane spEmpleados = new JScrollPane();
		spEmpleados.setBounds(10, 190, 705, 190);
		contentPane.add(spEmpleados);

		model = new DefaultTableModel(null, nombreColumnas) {
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

		tablaEmpleados = new JTable(model);
		spEmpleados.setViewportView(tablaEmpleados);

		btnAtras = new JButton("");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtras.setBounds(10, 408, 50, 50);
		cambiarIconoBotones(btnAtras, "back2.png");
		contentPane.add(btnAtras);

		JLabel lblNewLabel_4 = new JLabel("Atras");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(70, 408, 38, 50);
		contentPane.add(lblNewLabel_4);

		btnAgregar = new JButton("");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgregar.setBounds(203, 408, 50, 50);
		cambiarIconoBotones(btnAgregar, "plus.png");
		contentPane.add(btnAgregar);

		JLabel lblNewLabel_4_1 = new JLabel("Agregar");
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(263, 408, 104, 50);
		contentPane.add(lblNewLabel_4_1);

		btnModificar = new JButton("");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModificar.setBounds(388, 408, 50, 50);
		cambiarIconoBotones(btnModificar, "update.png");
		contentPane.add(btnModificar);

		JLabel lblNewLabel_4_1_1 = new JLabel("Actualizar");
		lblNewLabel_4_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4_1_1.setBounds(448, 408, 104, 50);
		contentPane.add(lblNewLabel_4_1_1);

		btnHistorialCambio = new JButton("");
		btnHistorialCambio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHistorialCambio.setBounds(597, 408, 50, 50);
		cambiarIconoBotones(btnHistorialCambio, "history.png");
		contentPane.add(btnHistorialCambio);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Historial");
		lblNewLabel_4_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1.setBounds(657, 408, 58, 50);
		contentPane.add(lblNewLabel_4_1_1_1);
		
		JLabel lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 98, 70, 32);
		contentPane.add(lblFiltrarPor);
		
		JLabel lblIdempleado = new JLabel("Cod. Empleado");
		lblIdempleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdempleado.setBounds(10, 128, 117, 20);
		contentPane.add(lblIdempleado);
		
		txtFiltroIdEmpleado = new JTextField();
		txtFiltroIdEmpleado.setBounds(10, 154, 114, 20);
		contentPane.add(txtFiltroIdEmpleado);
		
		txtFiltroCUIL = new JTextField();
		txtFiltroCUIL.setBounds(134, 154, 116, 20);
		contentPane.add(txtFiltroCUIL);
		
		JLabel lblCuil = new JLabel("CUIL");
		lblCuil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil.setBounds(137, 128, 114, 20);
		contentPane.add(lblCuil);
		
		JLabel lblTipoEmpleado = new JLabel("TipoEmpleado");
		lblTipoEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTipoEmpleado.setBounds(262, 128, 127, 20);
		contentPane.add(lblTipoEmpleado);
		
		cbTipoEmpleado = new JComboBox();
		cbTipoEmpleado.setBounds(263, 153, 150, 22);
		cbTipoEmpleado.addItem("Sin seleccionar");
		contentPane.add(cbTipoEmpleado);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 725, 53);
		contentPane.add(panel_2);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel_2.add(lblLogo);
		
		JLabel lblNewLabel_2 = new JLabel("Sucursal:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(537, 28, 59, 19);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Empleado:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(343, 28, 59, 19);
		panel_2.add(lblNewLabel_2_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(0, 99, 725, 290);
		contentPane.add(panel);
		frame.setLocationRelativeTo(null);

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 48, 800, 600);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		for (WindowListener listener : this.frame.getWindowListeners()) {
			this.frame.removeWindowListener(listener);
		}
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir?", "Advertencia",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}

	public JTextField getTxtFiltroIdEmpleado() {
		return txtFiltroIdEmpleado;
	}

	public JTextField getTxtFiltroCUIL() {
		return txtFiltroCUIL;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbTipoEmpleado() {
		return cbTipoEmpleado;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JTable getTablaEmpleados() {
		return tablaEmpleados;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnHistorialCambio() {
		return btnHistorialCambio;
	}
	
	public void limpiarCampos() {
		this.txtFiltroCUIL.setText("");
		this.txtFiltroIdEmpleado.setText("");
		this.cbTipoEmpleado.setSelectedItem("Sin seleccionar");
	}

	public void cerrarVentana() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.show();
	}
}
