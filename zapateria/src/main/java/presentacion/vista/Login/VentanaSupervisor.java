package presentacion.vista.Login;

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

	private JButton btnArmarVenta;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JPanel panel_1;

	public VentanaSupervisor() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1000, 667);
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
		panel.setBounds(1, 0, 983, 53);
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
		lblNewLabel.setBounds(803, 28, 59, 19);
		panel.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("Empleado:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(609, 28, 59, 19);
		panel.add(lblNewLabel_2);

		btnGestionarClientes = new JButton("");
		btnGestionarClientes.setForeground(new Color(51, 102, 153));
		btnGestionarClientes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarClientes.setBackground(new Color(51, 102, 204));
		btnGestionarClientes.setBounds(497, 116, 120, 120);
		contentPane.add(btnGestionarClientes);

		btnCotizaciones = new JButton("");
		btnCotizaciones.setForeground(new Color(51, 102, 153));
		btnCotizaciones.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCotizaciones.setBackground(new Color(51, 102, 204));
		btnCotizaciones.setBounds(178, 116, 120, 120);
		contentPane.add(btnCotizaciones);

		btnGestionarProductos = new JButton("");
		btnGestionarProductos.setForeground(new Color(51, 102, 153));
		btnGestionarProductos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarProductos.setBackground(new Color(51, 102, 204));
		btnGestionarProductos.setBounds(334, 116, 120, 120);
		contentPane.add(btnGestionarProductos);

		btnModPrecioUnitario = new JButton("");
		btnModPrecioUnitario.setForeground(new Color(51, 102, 153));
		btnModPrecioUnitario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnModPrecioUnitario.setBackground(new Color(51, 102, 204));
		btnModPrecioUnitario.setBounds(48, 335, 120, 120);
		contentPane.add(btnModPrecioUnitario);

		btnGenerarOrdenDe = new JButton("Orden de Manufactura");
		btnGenerarOrdenDe.setForeground(new Color(51, 102, 153));
		btnGenerarOrdenDe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGenerarOrdenDe.setBackground(new Color(51, 102, 204));
		btnGenerarOrdenDe.setBounds(692, 576, 282, 41);
		btnGenerarOrdenDe.setVisible(false);
		contentPane.add(btnGenerarOrdenDe);

		btnConfig = new JButton("");
		btnConfig.setForeground(new Color(51, 102, 153));
		btnConfig.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnConfig.setBackground(new Color(51, 102, 204));
		btnConfig.setBounds(334, 336, 120, 120);
		contentPane.add(btnConfig);

		btnVerComprasVirtuales = new JButton("");
		btnVerComprasVirtuales.setForeground(new Color(51, 102, 153));
		btnVerComprasVirtuales.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerComprasVirtuales.setBackground(new Color(51, 102, 204));
		btnVerComprasVirtuales.setBounds(178, 335, 120, 120);
		contentPane.add(btnVerComprasVirtuales);

		btnVerReporteRanking = new JButton("");
		btnVerReporteRanking.setForeground(new Color(51, 102, 153));
		btnVerReporteRanking.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerReporteRanking.setBackground(new Color(51, 102, 204));
		btnVerReporteRanking.setBounds(819, 336, 120, 120);
		contentPane.add(btnVerReporteRanking);

		btnVerProveedores = new JButton("");
		btnVerProveedores.setForeground(new Color(51, 102, 153));
		btnVerProveedores.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerProveedores.setBackground(new Color(51, 102, 204));
		btnVerProveedores.setBounds(819, 116, 120, 120);
		contentPane.add(btnVerProveedores);

		btnVerPedidosA = new JButton("");
		btnVerPedidosA.setForeground(new Color(51, 102, 153));
		btnVerPedidosA.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerPedidosA.setBackground(new Color(51, 102, 204));
		btnVerPedidosA.setBounds(657, 116, 120, 120);
		contentPane.add(btnVerPedidosA);
		
		lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(80, 557, 132, 60);
		contentPane.add(lblNewLabel_1);
		
		btnCerrarSesion = new JButton("");
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		btnCerrarSesion.setBounds(10, 557, 60, 60);
		cambiarIconoBotones(btnCerrarSesion, "exit.png");
		contentPane.add(btnCerrarSesion);
		
		btnGestionarSucursales = new JButton("");
		btnGestionarSucursales.setForeground(new Color(51, 102, 153));
		btnGestionarSucursales.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarSucursales.setBackground(new Color(51, 102, 204));
		btnGestionarSucursales.setBounds(497, 334, 120, 120);
		contentPane.add(btnGestionarSucursales);
		btnArmarVenta = new JButton("");
		btnArmarVenta.setForeground(new Color(51, 102, 153));
		btnArmarVenta.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnArmarVenta.setBackground(new Color(51, 102, 204));
		btnArmarVenta.setBounds(20, 116, 120, 120);
		contentPane.add(btnArmarVenta);
		
		lblNewLabel_3 = new JLabel("<html><center>Armar Venta</center></html>");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(20, 74, 120, 43);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("<html><center>Cotizaciones</center></html>");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(178, 74, 120, 43);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("<html><center>Modificar Productos</center></html>");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(48, 292, 120, 43);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("<html><center>Proveedores</center></html>");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(819, 74, 120, 43);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("<html><center>Ranking de Ventas</center></html>");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(819, 292, 120, 43);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("<html><center>Clientes</center></html>");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(497, 74, 120, 43);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("<html><center>Productos</center></html>");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(334, 74, 120, 43);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("<html><center>Pedidos a Proveedor</center></html>");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(657, 74, 120, 43);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("<html><center>Compras Virtuales</center></html>");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(178, 289, 120, 43);
		contentPane.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("<html><center>Tareas Automaticas</center></html>");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_12.setBounds(334, 292, 120, 43);
		contentPane.add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("<html><center>Sucursales</center></html>");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(497, 292, 120, 43);
		contentPane.add(lblNewLabel_13);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255, 180));
		panel_1.setBounds(11, 64, 963, 482);
		contentPane.add(panel_1);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 1000, 750);
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

	public void cerrarVentana() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.show();
	}

	public JButton getBtnArmarVenta() {
		return btnArmarVenta;
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
