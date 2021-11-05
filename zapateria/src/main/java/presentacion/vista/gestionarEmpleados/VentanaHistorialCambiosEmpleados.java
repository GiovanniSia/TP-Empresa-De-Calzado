package presentacion.vista.gestionarEmpleados;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

public class VentanaHistorialCambiosEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private String[] nombreColumnas = { "Cod.EmpleadoResponsable","Fecha","Cod.Sucursal", "Cod.Empleado", "CUILAntiguo", "CUILNuevo",
			"NombreAntiguo", "NombreNuevo", "ApellidoAntiguo", "ApellidoAntiguo", "CorreoElectronicoAntiguo",
			"CorreoElectronicoNuevo", "TipoEmpleadoAntiguo","TipoEmpleadoNuevo"};
	private DefaultTableModel model;
	private JTextField txtFiltroCodEmpleado;
	private JTextField txtFiltroCUIL;
	private JTable tablaHistorialEmpleados;
	private JTextField textField_2;
	private JButton btnAtras;
	private JDateChooser fechaFiltro;
	
	public VentanaHistorialCambiosEmpleados() {
		this.initialize();
	}

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
		frame.setBounds(100, 100, 741, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 725, 50);
		contentPane.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Zapateria Argento");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_2.setBounds(10, 0, 715, 50);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Historial Cambios Empleados");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(10, 50, 705, 38);
		contentPane.add(lblNewLabel_1);
		
		btnAtras = new JButton("");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtras.setBounds(10, 390, 50, 50);
		contentPane.add(btnAtras);
		
		JLabel lblNewLabel_4 = new JLabel("Atras");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(70, 390, 38, 50);
		contentPane.add(lblNewLabel_4);
		
		JScrollPane spEmpleados = new JScrollPane();
		spEmpleados.setBounds(10, 189, 705, 190);
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
		tablaHistorialEmpleados = new JTable(model);
		tablaHistorialEmpleados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spEmpleados.setViewportView(tablaHistorialEmpleados);
		
		txtFiltroCodEmpleado = new JTextField();
		txtFiltroCodEmpleado.setBounds(347, 158, 97, 20);
		contentPane.add(txtFiltroCodEmpleado);
		
		JLabel lblIdempleado = new JLabel("Cod. Empleado");
		lblIdempleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdempleado.setBounds(347, 132, 117, 20);
		contentPane.add(lblIdempleado);
		
		JLabel lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 82, 70, 32);
		contentPane.add(lblFiltrarPor);
		
		JLabel lblCuil = new JLabel("Cod.Sucursal");
		lblCuil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil.setBounds(248, 132, 89, 20);
		contentPane.add(lblCuil);
		
		txtFiltroCUIL = new JTextField();
		txtFiltroCUIL.setBounds(245, 158, 92, 20);
		contentPane.add(txtFiltroCUIL);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 158, 108, 20);
		contentPane.add(textField_2);
		
		JLabel lblCuil_1 = new JLabel("Empleado");
		lblCuil_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1.setBounds(10, 119, 114, 20);
		contentPane.add(lblCuil_1);
		
		fechaFiltro = new JDateChooser();
		fechaFiltro.setBounds(127, 158, 108, 19);
		contentPane.add(fechaFiltro);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setBounds(127, 132, 108, 20);
		contentPane.add(lblFecha);
		
		JLabel lblCuil_1_1 = new JLabel("Responsable");
		lblCuil_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1_1.setBounds(10, 137, 114, 20);
		contentPane.add(lblCuil_1_1);
		frame.setLocationRelativeTo(null);

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

	public void cerrarVentana() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.show();
	}
}
