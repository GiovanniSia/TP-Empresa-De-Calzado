package presentacion.vista.Login;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.border.MatteBorder;

public class VentanaSupervisor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JButton btnGestionarClientes;
	private JButton btnCotizaciones;
	private JButton btnGestionarProductos;
	private JButton btnModPrecioUnitario;
	private JButton btnGenerarOrdenDe;
	private JButton btnConfig;
	private JButton btnVerComprasVirtuales;
	private JButton btnVerReporteRanking;
	private JButton btnVerProveedores;
	private JButton btnVerPedidosA;
	private JLabel lblNewLabel_1;
	private JButton btnCerrarSesion;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JButton btnGestionarSucursales;


	public VentanaSupervisor() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 737, 478);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
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
		cambiarIconoLabel(lblLogo,"argentoshoes2.png");
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

		btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.setForeground(new Color(51, 102, 153));
		btnGestionarClientes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarClientes.setBackground(new Color(51, 102, 204));
		btnGestionarClientes.setBounds(20, 118, 282, 41);
		contentPane.add(btnGestionarClientes);

		btnCotizaciones = new JButton("Cotizaciones");
		btnCotizaciones.setForeground(new Color(51, 102, 153));
		btnCotizaciones.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCotizaciones.setBackground(new Color(51, 102, 204));
		btnCotizaciones.setBounds(20, 66, 282, 41);
		contentPane.add(btnCotizaciones);

		btnGestionarProductos = new JButton("Gestionar Productos");
		btnGestionarProductos.setForeground(new Color(51, 102, 153));
		btnGestionarProductos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarProductos.setBackground(new Color(51, 102, 204));
		btnGestionarProductos.setBounds(20, 187, 282, 41);
		contentPane.add(btnGestionarProductos);

		btnModPrecioUnitario = new JButton("Modificar Producto");
		btnModPrecioUnitario.setForeground(new Color(51, 102, 153));
		btnModPrecioUnitario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnModPrecioUnitario.setBackground(new Color(51, 102, 204));
		btnModPrecioUnitario.setBounds(20, 236, 282, 41);
		contentPane.add(btnModPrecioUnitario);

		btnGenerarOrdenDe = new JButton("Orden de Manufactura");
		btnGenerarOrdenDe.setForeground(new Color(51, 102, 153));
		btnGenerarOrdenDe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGenerarOrdenDe.setBackground(new Color(51, 102, 204));
		btnGenerarOrdenDe.setBounds(20, 307, 282, 41);
		contentPane.add(btnGenerarOrdenDe);

		btnConfig = new JButton("Tareas Autom\u00E1ticas");
		btnConfig.setForeground(new Color(51, 102, 153));
		btnConfig.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnConfig.setBackground(new Color(51, 102, 204));
		btnConfig.setBounds(343, 307, 282, 41);
		contentPane.add(btnConfig);

		btnVerComprasVirtuales = new JButton("Compras Virtuales");
		btnVerComprasVirtuales.setForeground(new Color(51, 102, 153));
		btnVerComprasVirtuales.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerComprasVirtuales.setBackground(new Color(51, 102, 204));
		btnVerComprasVirtuales.setBounds(343, 239, 282, 41);
		contentPane.add(btnVerComprasVirtuales);

		btnVerReporteRanking = new JButton("Ranking de Ventas");
		btnVerReporteRanking.setForeground(new Color(51, 102, 153));
		btnVerReporteRanking.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerReporteRanking.setBackground(new Color(51, 102, 204));
		btnVerReporteRanking.setBounds(343, 187, 282, 41);
		contentPane.add(btnVerReporteRanking);

		btnVerProveedores = new JButton("Ver Proveedores");
		btnVerProveedores.setForeground(new Color(51, 102, 153));
		btnVerProveedores.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerProveedores.setBackground(new Color(51, 102, 204));
		btnVerProveedores.setBounds(343, 118, 282, 41);
		contentPane.add(btnVerProveedores);

		btnVerPedidosA = new JButton("Pedidos a Proveedores");
		btnVerPedidosA.setForeground(new Color(51, 102, 153));
		btnVerPedidosA.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerPedidosA.setBackground(new Color(51, 102, 204));
		btnVerPedidosA.setBounds(343, 66, 282, 41);
		contentPane.add(btnVerPedidosA);
		
		lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(90, 368, 132, 60);
		contentPane.add(lblNewLabel_1);
		
		btnCerrarSesion = new JButton("");
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		btnCerrarSesion.setBounds(20, 368, 60, 60);
		cambiarIconoBotones(btnCerrarSesion, "exit.png");
		contentPane.add(btnCerrarSesion);
		
		btnGestionarSucursales = new JButton("Gestionar Sucursales");
		btnGestionarSucursales.setForeground(new Color(51, 102, 153));
		btnGestionarSucursales.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarSucursales.setBackground(new Color(51, 102, 204));
		btnGestionarSucursales.setBounds(343, 366, 282, 41);
		contentPane.add(btnGestionarSucursales);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 720, 540);
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

	public JButton getBtnGestionarClientes() {
		return btnGestionarClientes;
	}

	public JButton getBtnCotizaciones() {
		return btnCotizaciones;
	}

	public JButton getBtnGestionarProductos() {
		return btnGestionarProductos;
	}

	public JButton getBtnModPrecioUnitario() {
		return btnModPrecioUnitario;
	}

	public JButton getBtnGenerarOrdenDe() {
		return btnGenerarOrdenDe;
	}

	public JButton getBtnConfig() {
		return btnConfig;
	}

	public JButton getBtnVerComprasVirtuales() {
		return btnVerComprasVirtuales;
	}

	public JButton getBtnVerReporteRanking() {
		return btnVerReporteRanking;
	}

	public JButton getBtnVerProveedores() {
		return btnVerProveedores;
	}

	public JButton getBtnVerPedidosA() {
		return btnVerPedidosA;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}
	

	public JButton getBtnGestionarSucursales() {
		return btnGestionarSucursales;
	}
}
