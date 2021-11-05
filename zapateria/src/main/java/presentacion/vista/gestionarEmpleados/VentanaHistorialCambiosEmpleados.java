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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;

public class VentanaHistorialCambiosEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private String[] nombreColumnas = { "Cod.EmpleadoResponsable","Fecha","Cod.Sucursal", "Cod.Empleado", "CUILAntiguo", "CUILNuevo",
			"NombreAntiguo", "NombreNuevo", "ApellidoAntiguo", "ApellidoAntiguo", "CorreoElectronicoAntiguo",
			"CorreoElectronicoNuevo", "TipoEmpleadoAntiguo","TipoEmpleadoNuevo"};
	private DefaultTableModel model;
	private JTextField txtFiltroCodEmpleado;
	private JTextField txtFiltroCodSucursal;
	private JTable tablaHistorialEmpleados;
	private JTextField txtFiltroCodEmpleadoResponsable;
	private JButton btnAtras;
	private JDateChooser dateFiltroFecha;
	
	private JCheckBox checkboxEmpleadosModificados;
	private JCheckBox checkboxEmpleadosAgregados;
	
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
		cambiarIconoBotones(btnAtras, "back2.png");
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
		
		txtFiltroCodSucursal = new JTextField();
		txtFiltroCodSucursal.setBounds(245, 158, 92, 20);
		contentPane.add(txtFiltroCodSucursal);
		
		txtFiltroCodEmpleadoResponsable = new JTextField();
		txtFiltroCodEmpleadoResponsable.setBounds(10, 158, 108, 20);
		contentPane.add(txtFiltroCodEmpleadoResponsable);
		
		JLabel lblCuil_1 = new JLabel("Cod.Empleado");
		lblCuil_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1.setBounds(10, 119, 114, 20);
		contentPane.add(lblCuil_1);
		
		dateFiltroFecha = new JDateChooser();
		dateFiltroFecha.setBounds(127, 158, 108, 19);
		contentPane.add(dateFiltroFecha);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setBounds(127, 132, 108, 20);
		contentPane.add(lblFecha);
		
		JLabel lblCuil_1_1 = new JLabel("Responsable");
		lblCuil_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1_1.setBounds(10, 137, 114, 20);
		contentPane.add(lblCuil_1_1);
		
		checkboxEmpleadosModificados = new JCheckBox("");
		checkboxEmpleadosModificados.setBounds(563, 154, 21, 23);
		contentPane.add(checkboxEmpleadosModificados);
		
		checkboxEmpleadosAgregados = new JCheckBox("");
		checkboxEmpleadosAgregados.setBounds(454, 155, 21, 23);
		contentPane.add(checkboxEmpleadosAgregados);
		
		JLabel lblCuil_1_1_1 = new JLabel("Agregados");
		lblCuil_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1_1_1.setBounds(481, 156, 114, 20);
		contentPane.add(lblCuil_1_1_1);
		
		JLabel lblCuil_1_2 = new JLabel("Empleados");
		lblCuil_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1_2.setBounds(481, 138, 114, 20);
		contentPane.add(lblCuil_1_2);
		
		JLabel lblCuil_1_1_2 = new JLabel("Modificados");
		lblCuil_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1_1_2.setBounds(590, 156, 114, 20);
		contentPane.add(lblCuil_1_1_2);
		
		JLabel lblCuil_1_3 = new JLabel("Empleados");
		lblCuil_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuil_1_3.setBounds(590, 138, 114, 20);
		contentPane.add(lblCuil_1_3);
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
				int confirm = JOptionPane.showOptionDialog(null, "�Est�s seguro que quieres salir?", "Advertencia",
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
	
	public JCheckBox getCheckboxEmpleadosModificados() {
		return checkboxEmpleadosModificados;
	}

	public JCheckBox getCheckboxEmpleadosAgregados() {
		return checkboxEmpleadosAgregados;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JTextField getTxtFiltroCodEmpleado() {
		return txtFiltroCodEmpleado;
	}

	public JTextField getTxtFiltroCodSucursal() {
		return txtFiltroCodSucursal;
	}

	public JTable getTablaHistorialEmpleados() {
		return tablaHistorialEmpleados;
	}

	public JTextField getTxtFiltroCodEmpleadoResponsable() {
		return txtFiltroCodEmpleadoResponsable;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JDateChooser getDateFiltroFecha() {
		return dateFiltroFecha;
	}

	public void limpiarFiltros() {
		txtFiltroCodEmpleado.setText("");
		txtFiltroCodEmpleadoResponsable.setText("");
		txtFiltroCodSucursal.setText("");
		checkboxEmpleadosAgregados.setSelected(false);
		checkboxEmpleadosModificados.setSelected(false);
	}
	
	public void cerrarVentana() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.show();
	}
}
