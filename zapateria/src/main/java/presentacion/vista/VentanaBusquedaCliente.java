package presentacion.vista;

import java.awt.Font;
import java.awt.Image;
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

import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

public class VentanaBusquedaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Cod. Cliente", "Nombre", "Apellido", "CUIL", "Correo Electronico","Limite Credito", "Credito Disponible",
			"Estado" };
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
	private JLabel lblLogo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnPasarAVenta;
	private JLabel lblElegirProductos;
	private JButton btnAtras;
	private JLabel lblAtrs;

	public VentanaBusquedaCliente() {
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
		frame.setBounds(100, 100, 822, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		lblAtrs = new JLabel("Atras");
		lblAtrs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtrs.setBounds(198, 369, 70, 60);
		frame.getContentPane().add(lblAtrs);
		
		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(248, 248, 255));
		btnAtras.setBounds(133, 369, 60, 60);
		cambiarIconoBotones(btnAtras,  "back2.png");
		frame.getContentPane().add(btnAtras);
		
		btnPasarAVenta = new JButton("");
		btnPasarAVenta.setBackground(new Color(248, 248, 255));
		btnPasarAVenta.setBounds(497, 369, 60, 60);
		cambiarIconoBotones(btnPasarAVenta,  "carrito2.png");
		frame.getContentPane().add(btnPasarAVenta);
		
		lblElegirProductos = new JLabel("Elegir Productos");
		lblElegirProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblElegirProductos.setBounds(562, 369, 119, 60);
		frame.getContentPane().add(lblElegirProductos);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(0, 191, 806, 167);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 11, 776, 145);
		spCliente.setBackground(new Color(248, 248, 255));
		panel.add(spCliente);

		modelCliente =	new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if(columnas == 5) {
                    return true;
                }else {
                    return false;
                }
            }
		};
		
		tablaClientes = new JTable(modelCliente);
		tablaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaClientes.getColumnModel().getColumn(0).setResizable(false);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(1).setResizable(false);
		
		spCliente.setViewportView(tablaClientes);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255, 180));
		panel_1.setBackground(new Color(255, 255, 255, 180));
		panel_1.setBounds(0, 95, 806, 95);
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
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 806, 53);
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
		lblNewLabel.setBounds(606, 23, 59, 19);
		panel_2.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Empleado:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(412, 23, 59, 19);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 24, 854, 642);
		cambiarIconoLabel(lblFondo, "fondo.png");
		frame.getContentPane().add(lblFondo);
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

	public JButton getBtnPasarAVenta() {
		return btnPasarAVenta;
	}
	public JScrollPane getSpCliente() {
		return spCliente;
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

	public JTextField getTxtFieldCUIL() {
		return txtFieldCUIL;
	}

	public JTextField getTxtFieldCodCliente() {
		return txtFieldCodCliente;
	}
}
