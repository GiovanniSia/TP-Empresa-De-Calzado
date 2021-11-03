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
import javax.swing.JButton;

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
	private JButton btnCerrarSesion;

	public VentanaSupervisor() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 662, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();

		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(10, 0, 626, 55);

		contentPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Supervisor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, "name_5308677579600");

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

		btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setOpaque(true);
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		btnCerrarSesion.setBounds(10, 359, 156, 39);
		contentPane.add(btnCerrarSesion);
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

}
