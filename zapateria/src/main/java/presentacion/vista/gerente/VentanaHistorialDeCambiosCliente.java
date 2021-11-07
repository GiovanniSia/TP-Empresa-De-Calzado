package presentacion.vista.gerente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;
import com.toedter.calendar.JDateChooser;

public class VentanaHistorialDeCambiosCliente extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	
	private String[] nombreColumnas = {"Id cliente","Id empleado","Fecha Modificacion","Nombre anterior","Nombre nuevo","Apellido anterior","Apellido nuevo","CUIL anterior","CUIL nuevo","Correo anterior","Correo nuevo","Limite de Cred. anterior","Limite Cred. nuevo","Cred. anterior","Cred nuevo","Tipo cliente anterior","Tipo cliente nuevo","AFIP anterior","AFIP nuevo","Estado anterior","Calle anterior","Calle nueva","Altura anterior","Altura nueva","Pais anterior","Pais nuevo","Provincia anterior","Provincia nueva","Localidad anterior","Localidad nueva","Cod Postal anterior","Cod Postal nuevo"};
	private DefaultTableModel modelhistorialCambioCliente;
	private JTable tablaHistorialCambioCliente;
	private JLabel lblCodEmpleado;
	private JLabel lblFiltrarPor;
	private JLabel lblHistorialCambioCliente;

	private JScrollPane spHistorialCambioCliente;
	private JButton btnVolverAModificarProducto;

	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblVolver;
	
	private JTextField txtCodEmpleado;
	private JTextField textFieldIdCliente;
	private JDateChooser dateChooserFechaMod;
	private JTextField textNombreCliente;
	private JTextField textCUIL;

	private JButton btnBorrarFiltroFecha;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHistorialDeCambiosCliente window = new VentanaHistorialDeCambiosCliente();
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaHistorialDeCambiosCliente() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 1280, 477);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 175, 1264, 263);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spHistorialCambioCliente = new JScrollPane();
		spHistorialCambioCliente.setBounds(0, 11, 1264, 167);
//		panel.add(spHistorialCambioCliente);

		modelhistorialCambioCliente = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int filas, int columnas) {
            	return false;
            }
		};
		tablaHistorialCambioCliente = new JTable(modelhistorialCambioCliente);

		for(int i=0;i<this.nombreColumnas.length;i++) {
			tablaHistorialCambioCliente.getColumnModel().getColumn(i).setPreferredWidth(130);	
		}
		

		spHistorialCambioCliente.setViewportView(tablaHistorialCambioCliente);
		tablaHistorialCambioCliente.getTableHeader().setReorderingAllowed(false);
		tablaHistorialCambioCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaHistorialCambioCliente.doLayout();
		panel.add(spHistorialCambioCliente);
		
		
		btnVolverAModificarProducto = new JButton("");
		btnVolverAModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolverAModificarProducto.setBounds(151, 189, 60, 60);
		cambiarIconoBotones(btnVolverAModificarProducto,  "back2.png");
		panel.add(btnVolverAModificarProducto);
		btnVolverAModificarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblVolver = new JLabel("Volver");
		lblVolver.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVolver.setBounds(221, 189, 70, 60);
		panel.add(lblVolver);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 89, 1264, 87);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblFecha = new JLabel("Fecha Modificacion");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setBounds(486, 36, 122, 20);
		panel_1.add(lblFecha);

		lblCodEmpleado = new JLabel("Cod. Empleado");
		lblCodEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCodEmpleado.setBounds(10, 36, 117, 20);
		panel_1.add(lblCodEmpleado);

		txtCodEmpleado = new JTextField();
		txtCodEmpleado.setBounds(10, 56, 55, 20);
		panel_1.add(txtCodEmpleado);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 0, 70, 32);
		panel_1.add(lblFiltrarPor);
		
		JLabel lblIdCliente = new JLabel("Id Cliente");
		lblIdCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdCliente.setBounds(134, 36, 59, 20);
		panel_1.add(lblIdCliente);
		
		textFieldIdCliente = new JTextField();
		textFieldIdCliente.setBounds(134, 56, 59, 20);
		panel_1.add(textFieldIdCliente);
		
		dateChooserFechaMod = new JDateChooser();
		dateChooserFechaMod.setBounds(517, 56, 70, 19);
		panel_1.add(dateChooserFechaMod);
		
		textNombreCliente = new JTextField();
		textNombreCliente.setBounds(225, 56, 85, 20);
		panel_1.add(textNombreCliente);
		textNombreCliente.setColumns(10);
		
		JLabel lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombreCliente.setBounds(222, 36, 107, 20);
		panel_1.add(lblNombreCliente);
		
		textCUIL = new JTextField();
		textCUIL.setColumns(10);
		textCUIL.setBounds(339, 56, 85, 20);
		panel_1.add(textCUIL);
		
		JLabel lblCuilCliente = new JLabel("CUIL cliente");
		lblCuilCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCuilCliente.setBounds(339, 36, 107, 20);
		panel_1.add(lblCuilCliente);
		
		btnBorrarFiltroFecha = new JButton("");
		btnBorrarFiltroFecha.setBounds(598, 50, 26, 26);
		cambiarIconoBotones(btnBorrarFiltroFecha,  "trash.png");
		panel_1.add(btnBorrarFiltroFecha);

		lblHistorialCambioCliente = new JLabel("Historial de Cambios de Clilentes");
		lblHistorialCambioCliente.setBackground(new Color(248, 248, 255));
		lblHistorialCambioCliente.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblHistorialCambioCliente.setBounds(10, 50, 716, 41);
		frame.getContentPane().add(lblHistorialCambioCliente);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 1264, 50);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Zapateria Argento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 0, 421, 50);
		panel_2.add(lblNewLabel);
	}
	
	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
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

		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JFrame getFrame() {
		return frame;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelhistorialCambioCliente() {
		return modelhistorialCambioCliente;
	}

	public JTable getTablaHistorialCambioCliente() {
		return tablaHistorialCambioCliente;
	}

	public JLabel getLblCodEmpleado() {
		return lblCodEmpleado;
	}

	public JLabel getLblFiltrarPor() {
		return lblFiltrarPor;
	}

	public JLabel getLblHistorialCambioCliente() {
		return lblHistorialCambioCliente;
	}

	public JScrollPane getSpHistorialCambioCliente() {
		return spHistorialCambioCliente;
	}

	public JTextField getTxtFiltroCodEmpleado() {
		return txtCodEmpleado;
	}

	public JButton getBtnVolverAModificarProducto() {
		return btnVolverAModificarProducto;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}


	public JPanel getPanel_2() {
		return panel_2;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JLabel getLblVolver() {
		return lblVolver;
	}
	
	public JTextField getTxtCodEmpleado() {
		return txtCodEmpleado;
	}

	public JTextField getTextFieldIdCliente() {
		return textFieldIdCliente;
	}

	public JDateChooser getDateChooserFechaMod() {
		return dateChooserFechaMod;
	}

	public JTextField getTextNombreCliente() {
		return textNombreCliente;
	}

	public JTextField getTextCUIL() {
		return textCUIL;
	}
	

	public JButton getBtnBorrarFiltroFecha() {
		return btnBorrarFiltroFecha;
	}
}