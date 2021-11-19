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
	private JLabel lblSucursal;
	private JLabel lblEmpleado;

	private JButton btnGestionarClientes;
	private JButton btnVerProductos;
	private JButton btnGestionarEmpleados;
	private JButton btnVerComprasVirtuales;
	private JButton btnPedidosAProveedores;
	private JButton btnTareasAutomaticas;
	private JButton btnModificacionMasivaDePrecios;
	private JButton btnGestionarProveedores;
	private JPanel panel_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JButton btnVerFacturas;
	private JLabel lblNewLabel;

	public VentanaAdministrador() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 945, 633);
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
		panel.setBounds(1, 0, 928, 53);
		contentPane.add(panel);

		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);

		lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(547, 28, 138, 19);
		panel.add(lblSucursal);

		lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEmpleado.setBounds(316, 28, 151, 19);
		panel.add(lblEmpleado);

		lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(80, 523, 132, 60);
		contentPane.add(lblNewLabel_1);

		btnCerrarSesion = new JButton("");
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		btnCerrarSesion.setBounds(10, 523, 60, 60);
		cambiarIconoBotones(btnCerrarSesion, "exit.png");
		contentPane.add(btnCerrarSesion);
				
				panel_1 = new JPanel();
				panel_1.setBackground(new Color(248, 248, 255));
				panel_1.setBounds(11, 64, 908, 445);
				contentPane.add(panel_1);
				panel_1.setLayout(null);
				
						btnVerProductos = new JButton("");
						btnVerProductos.setBounds(42, 71, 120, 120);
						panel_1.add(btnVerProductos);
						btnVerProductos.setForeground(new Color(51, 102, 153));
						btnVerProductos.setFont(new Font("Segoe UI", Font.BOLD, 8));
						cambiarIconoBotones(btnVerProductos, "product.png");
						btnVerProductos.setBackground(new Color(51, 102, 204));
						
								btnModificacionMasivaDePrecios = new JButton("");
								btnModificacionMasivaDePrecios.setBounds(184, 71, 120, 120);
								panel_1.add(btnModificacionMasivaDePrecios);
								btnModificacionMasivaDePrecios.setForeground(new Color(51, 102, 153));
								btnModificacionMasivaDePrecios.setFont(new Font("Segoe UI", Font.BOLD, 8));
								cambiarIconoBotones(btnModificacionMasivaDePrecios, "product$.png");
								btnModificacionMasivaDePrecios.setBackground(new Color(51, 102, 204));
								
										btnGestionarClientes = new JButton("");
										btnGestionarClientes.setBounds(374, 71, 120, 120);
										cambiarIconoBotones(btnGestionarClientes, "person.png");
										panel_1.add(btnGestionarClientes);
										btnGestionarClientes.setForeground(new Color(51, 102, 153));
										btnGestionarClientes.setFont(new Font("Segoe UI", Font.BOLD, 8));
										btnGestionarClientes.setBackground(new Color(51, 102, 204));
										
												btnGestionarEmpleados = new JButton("");
												btnGestionarEmpleados.setBounds(564, 71, 120, 120);
												cambiarIconoBotones(btnGestionarEmpleados, "employee.png");
												panel_1.add(btnGestionarEmpleados);
												btnGestionarEmpleados.setForeground(new Color(51, 102, 153));
												btnGestionarEmpleados.setFont(new Font("Segoe UI", Font.BOLD, 8));
												btnGestionarEmpleados.setBackground(new Color(51, 102, 204));
												
														btnGestionarProveedores = new JButton("");
														btnGestionarProveedores.setBounds(42, 280, 120, 120);
														cambiarIconoBotones(btnGestionarProveedores, "proveedor.png");
														panel_1.add(btnGestionarProveedores);
														btnGestionarProveedores.setForeground(new Color(51, 102, 153));
														btnGestionarProveedores.setFont(new Font("Segoe UI", Font.BOLD, 8));
														btnGestionarProveedores.setBackground(new Color(51, 102, 204));
														
																btnVerComprasVirtuales = new JButton("");
																btnVerComprasVirtuales.setBounds(374, 280, 120, 120);
																cambiarIconoBotones(btnVerComprasVirtuales, "virtual.png");
																panel_1.add(btnVerComprasVirtuales);
																btnVerComprasVirtuales.setForeground(new Color(51, 102, 153));
																btnVerComprasVirtuales.setFont(new Font("Segoe UI", Font.PLAIN, 8));
																btnVerComprasVirtuales.setBackground(new Color(51, 102, 204));
																
																		btnTareasAutomaticas = new JButton("");
																		btnTareasAutomaticas.setBounds(564, 280, 120, 120);
																		cambiarIconoBotones(btnTareasAutomaticas, "auto.png");
																		panel_1.add(btnTareasAutomaticas);
																		btnTareasAutomaticas.setForeground(new Color(51, 102, 153));
																		btnTareasAutomaticas.setFont(new Font("Segoe UI", Font.BOLD, 8));
																		btnTareasAutomaticas.setBackground(new Color(51, 102, 204));
																		
																				btnPedidosAProveedores = new JButton("");
																				btnPedidosAProveedores.setBounds(184, 280, 120, 120);
																				cambiarIconoBotones(btnPedidosAProveedores, "arroba.png");
																				panel_1.add(btnPedidosAProveedores);
																				btnPedidosAProveedores.setForeground(new Color(51, 102, 153));
																				btnPedidosAProveedores.setFont(new Font("Segoe UI", Font.BOLD, 8));
																				btnPedidosAProveedores.setBackground(new Color(51, 102, 204));
																				
																				lblNewLabel_3 = new JLabel("Productos");
																				lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel_3.setBounds(42, 28, 120, 43);
																				panel_1.add(lblNewLabel_3);
																				
																				lblNewLabel_5 = new JLabel("Clientes");
																				lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel_5.setBounds(374, 28, 120, 43);
																				panel_1.add(lblNewLabel_5);
																				
																				lblNewLabel_6 = new JLabel("Empleados");
																				lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel_6.setBounds(564, 28, 120, 43);
																				panel_1.add(lblNewLabel_6);
																				
																				lblNewLabel_7 = new JLabel("Proveedores");
																				lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel_7.setBounds(42, 238, 120, 43);
																				panel_1.add(lblNewLabel_7);
																				
																				lblNewLabel_8 = new JLabel("<html><center>Pedidos a Proveedor</center></html>");
																				lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel_8.setBounds(184, 238, 120, 43);
																				panel_1.add(lblNewLabel_8);
																				
																				lblNewLabel_4 = new JLabel("<html><center>Modificar Productos</center></html>");
																				lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel_4.setBounds(184, 28, 120, 43);
																				panel_1.add(lblNewLabel_4);
																				
																				lblNewLabel_9 = new JLabel("<html><center>Compras Virtuales</center></html>");
																				lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel_9.setBounds(374, 238, 120, 43);
																				panel_1.add(lblNewLabel_9);
																				
																				lblNewLabel_10 = new JLabel("<html><center>Tareas Autom\u00E1ticas</center></html>");
																				lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel_10.setBounds(564, 238, 120, 43);
																				panel_1.add(lblNewLabel_10);
																				
																				btnVerFacturas = new JButton("");
																				btnVerFacturas.setForeground(new Color(51, 102, 153));
																				btnVerFacturas.setFont(new Font("Segoe UI", Font.BOLD, 8));
																				btnVerFacturas.setBackground(new Color(51, 102, 204));
																				btnVerFacturas.setBounds(749, 190, 120, 120);
																				cambiarIconoBotones(btnVerFacturas, "facturaver.png");
																				panel_1.add(btnVerFacturas);
																				
																				lblNewLabel = new JLabel("<html><center>Ver Facturas</center></html>");
																				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
																				lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
																				lblNewLabel.setBounds(749, 148, 120, 43);
																				panel_1.add(lblNewLabel);
		
				JLabel lblFondo = new JLabel("");
				lblFondo.setBounds(1, 0, 960, 720);
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

	public JButton getBtnGestionarProveedores() {
		return btnGestionarProveedores;
	}

	public JButton getBtnModificacionMasivaDePrecios() {
		return btnModificacionMasivaDePrecios;
	}

	public JButton getBtnGestionarClientes() {
		return btnGestionarClientes;
	}

	public JButton getBtnVerProductos() {
		return btnVerProductos;
	}

	public JButton getBtnGestionarEmpleados() {
		return btnGestionarEmpleados;
	}

	public JButton getBtnVerComprasVirtuales() {
		return btnVerComprasVirtuales;
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

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JButton getBtnVerFacturas() {
		return btnVerFacturas;
	}
}
