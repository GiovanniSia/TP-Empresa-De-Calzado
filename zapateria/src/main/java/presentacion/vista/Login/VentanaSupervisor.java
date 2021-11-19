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
	private JLabel lblSucursal;
	private JLabel lblEmpleado;
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
	private JButton btnVerFactura;
	private JLabel lblNewLabel;
	private JButton btnReporteRiesgoStock;
	private JLabel lblriesgoStock;

	public VentanaSupervisor() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1151, 626);
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
		panel.setBounds(1, 0, 1134, 53);
		contentPane.add(panel);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo,"argentoshoes2.png");
		panel.add(lblLogo);
		
		lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(585, 28, 326, 19);
		panel.add(lblSucursal);
		
		lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEmpleado.setBounds(218, 28, 294, 19);
		panel.add(lblEmpleado);

		btnGenerarOrdenDe = new JButton("Orden de Manufactura");
		btnGenerarOrdenDe.setForeground(new Color(51, 102, 153));
		btnGenerarOrdenDe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGenerarOrdenDe.setBackground(new Color(51, 102, 204));
		btnGenerarOrdenDe.setBounds(692, 521, 282, 41);
		btnGenerarOrdenDe.setVisible(false);
		contentPane.add(btnGenerarOrdenDe);
		
		lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(91, 513, 132, 60);
		contentPane.add(lblNewLabel_1);
		
		btnCerrarSesion = new JButton("");
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		btnCerrarSesion.setBounds(21, 513, 60, 60);
		cambiarIconoBotones(btnCerrarSesion, "exit.png");
		contentPane.add(btnCerrarSesion);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(11, 64, 1114, 438);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnArmarVenta = new JButton("");
		btnArmarVenta.setBounds(822, 53, 120, 120);
		panel_1.add(btnArmarVenta);
		btnArmarVenta.setForeground(new Color(51, 102, 153));
		btnArmarVenta.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnArmarVenta.setBackground(new Color(51, 102, 204));
		cambiarIconoBotones(btnArmarVenta, "tag.png");
		
		lblNewLabel_3 = new JLabel("<html><center>Armar Venta</center></html>");
		lblNewLabel_3.setBounds(822, 11, 120, 43);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
				btnModPrecioUnitario = new JButton("");
				btnModPrecioUnitario.setBounds(22, 271, 120, 120);
				panel_1.add(btnModPrecioUnitario);
				btnModPrecioUnitario.setForeground(new Color(51, 102, 153));
				btnModPrecioUnitario.setFont(new Font("Segoe UI", Font.BOLD, 20));
				btnModPrecioUnitario.setBackground(new Color(51, 102, 204));
				cambiarIconoBotones(btnModPrecioUnitario, "product$.png");
				
				lblNewLabel_5 = new JLabel("<html><center>Modificar Productos</center></html>");
				lblNewLabel_5.setBounds(22, 228, 120, 43);
				panel_1.add(lblNewLabel_5);
				lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				
						btnCotizaciones = new JButton("");
						btnCotizaciones.setBounds(502, 271, 120, 120);
						panel_1.add(btnCotizaciones);
						btnCotizaciones.setForeground(new Color(51, 102, 153));
						btnCotizaciones.setFont(new Font("Segoe UI", Font.BOLD, 20));
						btnCotizaciones.setBackground(new Color(51, 102, 204));
						cambiarIconoBotones(btnCotizaciones, "cotiz.png");
						
						lblNewLabel_4 = new JLabel("<html><center>Cotizaciones</center></html>");
						lblNewLabel_4.setBounds(502, 228, 120, 43);
						panel_1.add(lblNewLabel_4);
						lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
						
								btnConfig = new JButton("");
								btnConfig.setBounds(662, 271, 120, 120);
								panel_1.add(btnConfig);
								btnConfig.setForeground(new Color(51, 102, 153));
								btnConfig.setFont(new Font("Segoe UI", Font.BOLD, 20));
								btnConfig.setBackground(new Color(51, 102, 204));
								cambiarIconoBotones(btnConfig, "auto.png");
								
								lblNewLabel_12 = new JLabel("<html><center>Tareas Automaticas</center></html>");
								lblNewLabel_12.setBounds(662, 228, 120, 43);
								panel_1.add(lblNewLabel_12);
								lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_12.setFont(new Font("Segoe UI", Font.PLAIN, 16));
								
										btnVerReporteRanking = new JButton("");
										btnVerReporteRanking.setBounds(342, 271, 120, 120);
										panel_1.add(btnVerReporteRanking);
										btnVerReporteRanking.setForeground(new Color(51, 102, 153));
										btnVerReporteRanking.setFont(new Font("Segoe UI", Font.BOLD, 20));
										btnVerReporteRanking.setBackground(new Color(51, 102, 204));
										cambiarIconoBotones(btnVerReporteRanking, "report$.png");
										
										lblNewLabel_7 = new JLabel("<html><center>Ranking de Ventas</center></html>");
										lblNewLabel_7.setBounds(342, 228, 120, 43);
										panel_1.add(lblNewLabel_7);
										lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
										lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 16));
										
												btnGestionarProductos = new JButton("");
												btnGestionarProductos.setBounds(22, 53, 120, 120);
												panel_1.add(btnGestionarProductos);
												btnGestionarProductos.setForeground(new Color(51, 102, 153));
												btnGestionarProductos.setFont(new Font("Segoe UI", Font.BOLD, 20));
												btnGestionarProductos.setBackground(new Color(51, 102, 204));
												cambiarIconoBotones(btnGestionarProductos, "product.png");
												
												lblNewLabel_9 = new JLabel("<html><center>Productos</center></html>");
												lblNewLabel_9.setBounds(22, 11, 120, 43);
												panel_1.add(lblNewLabel_9);
												lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
												lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 16));
												
														btnVerProveedores = new JButton("");
														btnVerProveedores.setBounds(182, 53, 120, 120);
														panel_1.add(btnVerProveedores);
														btnVerProveedores.setForeground(new Color(51, 102, 153));
														btnVerProveedores.setFont(new Font("Segoe UI", Font.BOLD, 20));
														btnVerProveedores.setBackground(new Color(51, 102, 204));
														cambiarIconoBotones(btnVerProveedores, "proveedor.png");
														
														lblNewLabel_6 = new JLabel("<html><center>Proveedores</center></html>");
														lblNewLabel_6.setBounds(182, 11, 120, 43);
														panel_1.add(lblNewLabel_6);
														lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
														lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
														
														lblNewLabel_11 = new JLabel("<html><center>Compras Virtuales</center></html>");
														lblNewLabel_11.setBounds(662, 11, 120, 43);
														panel_1.add(lblNewLabel_11);
														lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
														lblNewLabel_11.setFont(new Font("Segoe UI", Font.PLAIN, 16));
														
																btnVerComprasVirtuales = new JButton("");
																btnVerComprasVirtuales.setBounds(662, 53, 120, 120);
																panel_1.add(btnVerComprasVirtuales);
																btnVerComprasVirtuales.setForeground(new Color(51, 102, 153));
																btnVerComprasVirtuales.setFont(new Font("Segoe UI", Font.BOLD, 20));
																btnVerComprasVirtuales.setBackground(new Color(51, 102, 204));
																cambiarIconoBotones(btnVerComprasVirtuales, "virtual.png");
																
																lblNewLabel_10 = new JLabel("<html><center>Pedidos a Proveedor</center></html>");
																lblNewLabel_10.setBounds(182, 228, 120, 43);
																panel_1.add(lblNewLabel_10);
																lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
																lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																
																		btnVerPedidosA = new JButton("");
																		btnVerPedidosA.setBounds(182, 271, 120, 120);
																		panel_1.add(btnVerPedidosA);
																		btnVerPedidosA.setForeground(new Color(51, 102, 153));
																		btnVerPedidosA.setFont(new Font("Segoe UI", Font.BOLD, 20));
																		btnVerPedidosA.setBackground(new Color(51, 102, 204));
																		cambiarIconoBotones(btnVerPedidosA, "pedido.png");
																		
																				btnGestionarClientes = new JButton("");
																				btnGestionarClientes.setBounds(342, 53, 120, 120);
																				panel_1.add(btnGestionarClientes);
																				btnGestionarClientes.setForeground(new Color(51, 102, 153));
																				btnGestionarClientes.setFont(new Font("Segoe UI", Font.BOLD, 20));
																				btnGestionarClientes.setBackground(new Color(51, 102, 204));
																				cambiarIconoBotones(btnGestionarClientes, "person.png");
																				
																				lblNewLabel_8 = new JLabel("<html><center>Clientes</center></html>");
																				lblNewLabel_8.setBounds(342, 11, 120, 43);
																				panel_1.add(lblNewLabel_8);
																				lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				
																				btnGestionarSucursales = new JButton("");
																				btnGestionarSucursales.setBounds(502, 53, 120, 120);
																				panel_1.add(btnGestionarSucursales);
																				btnGestionarSucursales.setForeground(new Color(51, 102, 153));
																				btnGestionarSucursales.setFont(new Font("Segoe UI", Font.BOLD, 20));
																				btnGestionarSucursales.setBackground(new Color(51, 102, 204));
																				cambiarIconoBotones(btnGestionarSucursales, "store.png");
																				
																				lblNewLabel_13 = new JLabel("<html><center>Sucursales</center></html>");
																				lblNewLabel_13.setBounds(502, 11, 120, 43);
																				panel_1.add(lblNewLabel_13);
																				lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_13.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				
																				btnVerFactura = new JButton("");
																				btnVerFactura.setForeground(new Color(51, 102, 153));
																				btnVerFactura.setFont(new Font("Segoe UI", Font.BOLD, 20));
																				btnVerFactura.setBackground(new Color(51, 102, 204));
																				btnVerFactura.setBounds(822, 271, 120, 120);
																				cambiarIconoBotones(btnVerFactura, "facturaver.png");
																				panel_1.add(btnVerFactura);
																				
																				lblNewLabel = new JLabel("<html><center>Ver Facturas</center></html>");
																				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel.setBounds(822, 228, 120, 43);
																				panel_1.add(lblNewLabel);
																				
																				btnReporteRiesgoStock = new JButton("");
																				btnReporteRiesgoStock.setForeground(new Color(51, 102, 153));
																				btnReporteRiesgoStock.setFont(new Font("Segoe UI", Font.BOLD, 20));
																				btnReporteRiesgoStock.setBackground(new Color(51, 102, 204));
																				btnReporteRiesgoStock.setBounds(974, 172, 120, 120);
																				cambiarIconoBotones(btnReporteRiesgoStock, "stock.png");
																				panel_1.add(btnReporteRiesgoStock);
																				
																				lblriesgoStock = new JLabel("<html><center>Riesgo Stock</center></html>");
																				lblriesgoStock.setHorizontalAlignment(SwingConstants.CENTER);
																				lblriesgoStock.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblriesgoStock.setBounds(974, 130, 120, 43);
																				panel_1.add(lblriesgoStock);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 1280, 960);
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
				int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que quieres salir?", "Advertencia",
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

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JButton getBtnVerFactura() {
		return btnVerFactura;
	}

	public JButton getBtnReporteRiesgoStock() {
		return btnReporteRiesgoStock;
	}
}
