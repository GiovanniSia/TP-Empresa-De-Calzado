package presentacion.vista.Login;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

public class VentanaAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JLabel lblNewLabel_1;
	private JButton btnCerrarSesion;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;

	private JButton btnGestionarClientes;
	private JButton btnGestionarProductos;
	private JButton btnGestionarEmpleados;
	private JButton btnVerComprasVirtuales;
	private JButton btnGenerarOrdenDeManufactura;
	private JButton btnPedidosAProveedores;
	private JButton btnTareasAutomaticas;

	public VentanaAdministrador() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 735, 475);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		btnTareasAutomaticas = new JButton("Tareas Automaticas");
		btnTareasAutomaticas.setForeground(new Color(51, 102, 153));
		btnTareasAutomaticas.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnTareasAutomaticas.setBackground(new Color(51, 102, 204));
		btnTareasAutomaticas.setBounds(255, 376, 282, 41);
		contentPane.add(btnTareasAutomaticas);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(1, 0, 719, 53);
		contentPane.add(panel);

		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);

		lblNewLabel = new JLabel("Sucursal:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(537, 28, 59, 19);
		panel.add(lblNewLabel);

		lblNewLabel_2 = new JLabel("Empleado:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(343, 28, 59, 19);
		panel.add(lblNewLabel_2);

		lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(80, 365, 132, 60);
		contentPane.add(lblNewLabel_1);

		btnCerrarSesion = new JButton("");
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		btnCerrarSesion.setBounds(10, 365, 60, 60);
		cambiarIconoBotones(btnCerrarSesion, "exit.png");
		contentPane.add(btnCerrarSesion);

		btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.setForeground(new Color(51, 102, 153));
		btnGestionarClientes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarClientes.setBackground(new Color(51, 102, 204));
		btnGestionarClientes.setBounds(255, 116, 282, 41);
		contentPane.add(btnGestionarClientes);

		btnGestionarProductos = new JButton("Gestionar Productos");
		btnGestionarProductos.setForeground(new Color(51, 102, 153));
		btnGestionarProductos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarProductos.setBackground(new Color(51, 102, 204));
		btnGestionarProductos.setBounds(255, 64, 282, 41);
		contentPane.add(btnGestionarProductos);

		btnGestionarEmpleados = new JButton("Gestionar Empleados");
		btnGestionarEmpleados.setForeground(new Color(51, 102, 153));
		btnGestionarEmpleados.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarEmpleados.setBackground(new Color(51, 102, 204));
		btnGestionarEmpleados.setBounds(255, 168, 282, 41);
		contentPane.add(btnGestionarEmpleados);

		btnVerComprasVirtuales = new JButton("Ver Compras Virtuales");
		btnVerComprasVirtuales.setForeground(new Color(51, 102, 153));
		btnVerComprasVirtuales.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerComprasVirtuales.setBackground(new Color(51, 102, 204));
		btnVerComprasVirtuales.setBounds(255, 324, 282, 41);
		contentPane.add(btnVerComprasVirtuales);

		btnGenerarOrdenDeManufactura = new JButton("Generar Orden de Manufactura");
		btnGenerarOrdenDeManufactura.setForeground(new Color(51, 102, 153));
		btnGenerarOrdenDeManufactura.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGenerarOrdenDeManufactura.setBackground(new Color(51, 102, 204));
		btnGenerarOrdenDeManufactura.setBounds(225, 272, 339, 41);
		contentPane.add(btnGenerarOrdenDeManufactura);

		btnPedidosAProveedores = new JButton("Pedidos a Proveedores");
		btnPedidosAProveedores.setForeground(new Color(51, 102, 153));
		btnPedidosAProveedores.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnPedidosAProveedores.setBackground(new Color(51, 102, 204));
		btnPedidosAProveedores.setBounds(255, 220, 282, 41);
		contentPane.add(btnPedidosAProveedores);

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(1, 0, 720, 540);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
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
	
	public JButton getBtnGestionarClientes() {
		return btnGestionarClientes;
	}

	public JButton getBtnGestionarProductos() {
		return btnGestionarProductos;
	}

	public JButton getBtnGestionarEmpleados() {
		return btnGestionarEmpleados;
	}

	public JButton getBtnVerComprasVirtuales() {
		return btnVerComprasVirtuales;
	}

	public JButton getBtnGenerarOrdenDeManufactura() {
		return btnGenerarOrdenDeManufactura;
	}

	public JButton getBtnPedidosAProveedores() {
		return btnPedidosAProveedores;
	}
	public JButton getBtnTareasAutomaticas() {
		return btnTareasAutomaticas;
	} 

	public void cerrarVentana() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.show();
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}
}
