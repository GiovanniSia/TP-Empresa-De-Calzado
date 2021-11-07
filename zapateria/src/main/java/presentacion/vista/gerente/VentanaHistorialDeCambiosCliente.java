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

public class VentanaHistorialDeCambiosCliente extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	private String[] nombreColumnas = {"Id empleado","Fecha Modificacion","Id cliente","Nombre anterior","Nombre nuevo","CUIL anterior","CUIL nuevo","Correo anterior","Correo nuevo","Limite de Cred. anterior","Limite Cred. nuevo","Cred. anterior","Cred nuevo","Tipo cliente anterior","Tipo cliente nuevo","AFIP anterior","AFIP nuevo","Estado anterior","Calle anterior","Calle nueva","Altura anterior","Altura nueva","Pais anterior","Pais nuevo","Provincia anterior","Provincia nueva","Localidad anterior","Localidad nueva","Cod Postal anterior","Cod Postal nuevo"};
	private DefaultTableModel modelhistorialCambioCliente;
	private JTable tablaHistorialCambioCliente;
	private JLabel lblCodEmpleado;
	private JLabel lblFiltrarPor;
	private JLabel lblHistorialCambioCliente;

	private JScrollPane spHistorialCambioCliente;
	private JTextField txtFiltroCodEmpleado;
	private JButton btnVolverAModificarProducto;

	private JPanel panel_1;
	private JTextField txtFiltroCodSucursal;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblVolver;
	private JTextField textFieldIdCliente;

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

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setBounds(386, 36, 70, 20);
		panel_1.add(lblFecha);

		lblCodEmpleado = new JLabel("Cod. Empleado");
		lblCodEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCodEmpleado.setBounds(10, 36, 117, 20);
		panel_1.add(lblCodEmpleado);

		txtFiltroCodEmpleado = new JTextField();
		txtFiltroCodEmpleado.setBounds(10, 56, 114, 20);
		panel_1.add(txtFiltroCodEmpleado);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 0, 70, 32);
		panel_1.add(lblFiltrarPor);
		
		JLabel lblCodSucursal = new JLabel("Cod. Sucursal");
		lblCodSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCodSucursal.setBounds(137, 36, 114, 20);
		panel_1.add(lblCodSucursal);
		
		txtFiltroCodSucursal = new JTextField();
		txtFiltroCodSucursal.setBounds(134, 56, 84, 20);
		panel_1.add(txtFiltroCodSucursal);
		
		JLabel lblIdCliente = new JLabel("Id Cliente");
		lblIdCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdCliente.setBounds(224, 36, 117, 20);
		panel_1.add(lblIdCliente);
		
		textFieldIdCliente = new JTextField();
		textFieldIdCliente.setBounds(224, 56, 84, 20);
		panel_1.add(textFieldIdCliente);

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
}



