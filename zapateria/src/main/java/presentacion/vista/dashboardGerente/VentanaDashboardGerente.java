package presentacion.vista.dashboardGerente;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import persistencia.conexion.Conexion;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;

public class VentanaDashboardGerente {

	private JFrame frame;
	private JButton btnGestionarProductos;
	private JButton btnModPrecioUnitario;
	private JButton btnGestionarClientes;
	private JButton btnGestionarEmpleados;
	private JButton btnVerReporteRanking;
	private JButton btnTareasAutomaticas;
	private JButton btnVerPedidosA;
	private JButton btnCotizaciones;
	private JButton btnCerrarSesion;
	private JButton btnHistorialFabrica;
	private JButton btnCobrarVenta;
	private JButton btnIngresoDeCaja;
	private JButton btnEgresoDeCaja;
	private JButton btnCierreDeCaja;
	private JButton btnGestionarRecetasYPasos;
	private JButton btnGestionarSucursales_1;
	private JButton btnGestionarProveedores;
	private JLabel lblSucursal;
	private JLabel lblProveedores;
	private JLabel lblpedidosAProveedor;
	private JLabel lblClientes;
	private JLabel lblProductos;
	private JLabel lblmodificarProductos;
	private JLabel lblEmpleados;
	private JLabel lblCotizaciones;
	private JLabel lbltareasAutomatizadas;
	private JLabel lblSucursales;
	private JLabel lblrankingDeVentas;
	private JLabel lblcobrarVenta;
	private JLabel lblingresosDeCaja;
	private JLabel lblegresosDeCaja;
	private JLabel lblcierreDeCaja;
	private JLabel lblrecetasDeFabricacion;
	private JLabel lblcierreDeCaja_2;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel lblGerente;
	
	ChartPanel panelTorta;
	JPanel panel_1;
	JPanel panel_1_1;
	ChartPanel panelTorta2;
	private JButton btnVerFacturas;
	private JLabel lblcierreDeCaja_1;
	private JButton btnReporteRiesgoStock;
	private JLabel lblcierreDeCaja_3;
	
	JButton btnSubirCantSucursales;
	JButton btnBajarCantSucursales;
	JButton btnSubirCantEmpleados;
	JButton btnBajarCantEmpleados;
	private JButton btnBajarDias1;
	private JButton btnSubirDia1;
	private JButton btnBajarDias10;
	private JButton btnSubirDia10;
	JLabel lblDiasParaAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDashboardGerente window = new VentanaDashboardGerente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaDashboardGerente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(67, 73, 86));
		frame.setBounds(100, 100, 915, 692);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
				
				btnBajarDias1 = new JButton("");
				btnBajarDias1.setForeground(new Color(51, 102, 153));
				btnBajarDias1.setFont(new Font("Segoe UI", Font.BOLD, 20));
				btnBajarDias1.setBackground(new Color(51, 102, 204));
				btnBajarDias1.setBounds(570, 301, 40, 40);
				frame.getContentPane().add(btnBajarDias1);
				cambiarIconoBotones(btnBajarDias1,"down.png");
				
				btnSubirDia1 = new JButton("");
				btnSubirDia1.setForeground(new Color(51, 102, 153));
				btnSubirDia1.setFont(new Font("Segoe UI", Font.BOLD, 20));
				btnSubirDia1.setBackground(new Color(51, 102, 204));
				btnSubirDia1.setBounds(781, 301, 40, 40);
				frame.getContentPane().add(btnSubirDia1);
				cambiarIconoBotones(btnSubirDia1,"up.png");
				
				btnBajarDias10 = new JButton("");
				btnBajarDias10.setForeground(new Color(51, 102, 153));
				btnBajarDias10.setFont(new Font("Segoe UI", Font.BOLD, 20));
				btnBajarDias10.setBackground(new Color(51, 102, 204));
				btnBajarDias10.setBounds(520, 301, 40, 40);
				frame.getContentPane().add(btnBajarDias10);
				cambiarIconoBotones(btnBajarDias10,"down2.png");
				
				btnSubirDia10 = new JButton("");
				btnSubirDia10.setForeground(new Color(51, 102, 153));
				btnSubirDia10.setFont(new Font("Segoe UI", Font.BOLD, 20));
				btnSubirDia10.setBackground(new Color(51, 102, 204));
				btnSubirDia10.setBounds(831, 301, 40, 40);
				frame.getContentPane().add(btnSubirDia10);
				cambiarIconoBotones(btnSubirDia10,"up2.png");
				
				lblDiasParaAtras = new JLabel("Ranking de Ventas x Vendedor(total en pesos)");
				lblDiasParaAtras.setHorizontalAlignment(SwingConstants.CENTER);
				lblDiasParaAtras.setForeground(Color.WHITE);
				lblDiasParaAtras.setFont(new Font("Segoe UI", Font.ITALIC, 16));
				lblDiasParaAtras.setBounds(620, 309, 151, 22);
				frame.getContentPane().add(lblDiasParaAtras);
				
				btnBajarCantEmpleados = new JButton("");
				btnBajarCantEmpleados.setForeground(new Color(51, 102, 153));
				btnBajarCantEmpleados.setFont(new Font("Segoe UI", Font.BOLD, 20));
				btnBajarCantEmpleados.setBackground(new Color(51, 102, 204));
				btnBajarCantEmpleados.setBounds(861, 580, 40, 40);
				frame.getContentPane().add(btnBajarCantEmpleados);
				cambiarIconoBotones(btnBajarCantEmpleados,"minus.png");
				
				btnSubirCantEmpleados = new JButton("");
				btnSubirCantEmpleados.setForeground(new Color(51, 102, 153));
				btnSubirCantEmpleados.setFont(new Font("Segoe UI", Font.BOLD, 20));
				btnSubirCantEmpleados.setBackground(new Color(51, 102, 204));
				btnSubirCantEmpleados.setBounds(861, 529, 40, 40);
				frame.getContentPane().add(btnSubirCantEmpleados);
				cambiarIconoBotones(btnSubirCantEmpleados,"plus.png");
		
				btnBajarCantSucursales = new JButton("");
				btnBajarCantSucursales.setForeground(new Color(51, 102, 153));
				btnBajarCantSucursales.setFont(new Font("Segoe UI", Font.BOLD, 20));
				btnBajarCantSucursales.setBackground(new Color(51, 102, 204));
				btnBajarCantSucursales.setBounds(861, 250, 40, 40);
				frame.getContentPane().add(btnBajarCantSucursales);
				cambiarIconoBotones(btnBajarCantSucursales,"minus.png");
		
		btnSubirCantSucursales = new JButton("");
		btnSubirCantSucursales.setForeground(new Color(51, 102, 153));
		btnSubirCantSucursales.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnSubirCantSucursales.setBackground(new Color(51, 102, 204));
		btnSubirCantSucursales.setBounds(861, 199, 40, 40);
		frame.getContentPane().add(btnSubirCantSucursales);
		cambiarIconoBotones(btnSubirCantSucursales,"plus.png");
		
		JLabel lblRankingDeVentas_2 = new JLabel("Ranking de Ventas x Vendedor(total en pesos)");
		lblRankingDeVentas_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRankingDeVentas_2.setForeground(Color.WHITE);
		lblRankingDeVentas_2.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		lblRankingDeVentas_2.setBounds(531, 342, 320, 22);
		frame.getContentPane().add(lblRankingDeVentas_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 490, 653);
		panel.setBackground(new Color(248, 248, 255));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 331, 415, 11);
		panel.add(separator);
		
		btnGestionarProveedores = new JButton("");
		btnGestionarProveedores.setForeground(new Color(51, 102, 153));
		btnGestionarProveedores.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarProveedores.setBackground(new Color(51, 102, 204));
		btnGestionarProveedores.setBounds(34, 129, 40, 40);
		cambiarIconoBotones(btnGestionarProveedores, "proveedor.png");
		panel.add(btnGestionarProveedores);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(80, 11, 319, 86);
		panel.add(lblLogo);
		cambiarIconoLabel(lblLogo,"argentoshoes.png");
		
		lblGerente = new JLabel("Gerente:");
		lblGerente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblGerente.setBounds(65, 96, 166, 22);
		panel.add(lblGerente);
		
		btnGestionarProductos = new JButton("");
		btnGestionarProductos.setForeground(new Color(51, 102, 153));
		btnGestionarProductos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarProductos.setBackground(new Color(51, 102, 204));
		btnGestionarProductos.setBounds(34, 231, 40, 40);
		cambiarIconoBotones(btnGestionarProductos, "product.png");
		panel.add(btnGestionarProductos);
		
		btnModPrecioUnitario = new JButton("");
		btnModPrecioUnitario.setForeground(new Color(51, 102, 153));
		btnModPrecioUnitario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnModPrecioUnitario.setBackground(new Color(51, 102, 204));
		btnModPrecioUnitario.setBounds(175, 231, 40, 40);
		cambiarIconoBotones(btnModPrecioUnitario, "product$.png");
		panel.add(btnModPrecioUnitario);
		
		btnGestionarClientes = new JButton("");
		btnGestionarClientes.setForeground(new Color(51, 102, 153));
		btnGestionarClientes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarClientes.setBackground(new Color(51, 102, 204));
		btnGestionarClientes.setBounds(34, 180, 40, 40);
		cambiarIconoBotones(btnGestionarClientes, "person.png");
		panel.add(btnGestionarClientes);
		
		btnGestionarEmpleados = new JButton("");
		btnGestionarEmpleados.setForeground(new Color(51, 102, 153));
		btnGestionarEmpleados.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarEmpleados.setBackground(new Color(51, 102, 204));
		btnGestionarEmpleados.setBounds(175, 180, 40, 40);
		cambiarIconoBotones(btnGestionarEmpleados, "employee.png");
		panel.add(btnGestionarEmpleados);
		
		btnVerReporteRanking = new JButton("");
		btnVerReporteRanking.setForeground(new Color(51, 102, 153));
		btnVerReporteRanking.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerReporteRanking.setBackground(new Color(51, 102, 204));
		btnVerReporteRanking.setBounds(175, 282, 40, 40);
		cambiarIconoBotones(btnVerReporteRanking, "report$.png");
		panel.add(btnVerReporteRanking);
		
		btnTareasAutomaticas = new JButton("");
		btnTareasAutomaticas.setForeground(new Color(51, 102, 153));
		btnTareasAutomaticas.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnTareasAutomaticas.setBackground(new Color(51, 102, 204));
		btnTareasAutomaticas.setBounds(175, 342, 40, 40);
		cambiarIconoBotones(btnTareasAutomaticas, "auto.png");
		panel.add(btnTareasAutomaticas);
		
		btnVerPedidosA = new JButton("");
		btnVerPedidosA.setForeground(new Color(51, 102, 153));
		btnVerPedidosA.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerPedidosA.setBackground(new Color(51, 102, 204));
		btnVerPedidosA.setBounds(175, 129, 40, 40);
		cambiarIconoBotones(btnVerPedidosA, "arroba.png");
		panel.add(btnVerPedidosA);
		
		btnCotizaciones = new JButton("");
		btnCotizaciones.setForeground(new Color(51, 102, 153));
		btnCotizaciones.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCotizaciones.setBackground(new Color(51, 102, 204));
		btnCotizaciones.setBounds(34, 342, 40, 40);
		cambiarIconoBotones(btnCotizaciones, "cotiz.png");
		panel.add(btnCotizaciones);
		
		btnCerrarSesion = new JButton("");
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		btnCerrarSesion.setBounds(10, 582, 60, 60);
		cambiarIconoBotones(btnCerrarSesion, "exit.png");
		panel.add(btnCerrarSesion);
		
		JLabel lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(80, 582, 132, 60);
		panel.add(lblNewLabel_1);
		
		btnHistorialFabrica = new JButton("");
		btnHistorialFabrica.setForeground(new Color(51, 102, 153));
		btnHistorialFabrica.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnHistorialFabrica.setBackground(new Color(51, 102, 204));
		btnHistorialFabrica.setBounds(175, 519, 40, 40);
		cambiarIconoBotones(btnHistorialFabrica, "fabrica.png");
		panel.add(btnHistorialFabrica);
		
		btnCobrarVenta = new JButton("");
		btnCobrarVenta.setForeground(new Color(51, 102, 153));
		btnCobrarVenta.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCobrarVenta.setBackground(new Color(51, 102, 204));
		btnCobrarVenta.setBounds(34, 404, 40, 40);
		cambiarIconoBotones(btnCobrarVenta, "factura.png");
		panel.add(btnCobrarVenta);
		
		btnIngresoDeCaja = new JButton("");
		btnIngresoDeCaja.setForeground(new Color(51, 102, 153));
		btnIngresoDeCaja.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnIngresoDeCaja.setBackground(new Color(51, 102, 204));
		btnIngresoDeCaja.setBounds(175, 404, 40, 40);
		cambiarIconoBotones(btnIngresoDeCaja, "cashier+.png");
		panel.add(btnIngresoDeCaja);
		
		btnEgresoDeCaja = new JButton("");
		btnEgresoDeCaja.setForeground(new Color(51, 102, 153));
		btnEgresoDeCaja.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnEgresoDeCaja.setBackground(new Color(51, 102, 204));
		btnEgresoDeCaja.setBounds(34, 455, 40, 40);
		cambiarIconoBotones(btnEgresoDeCaja, "cashier-.png");
		panel.add(btnEgresoDeCaja);
		
		btnCierreDeCaja = new JButton("");
		btnCierreDeCaja.setForeground(new Color(51, 102, 153));
		btnCierreDeCaja.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCierreDeCaja.setBackground(new Color(51, 102, 204));
		btnCierreDeCaja.setBounds(175, 455, 40, 40);
		cambiarIconoBotones(btnCierreDeCaja, "cashierlock.png");
		panel.add(btnCierreDeCaja);
		
		btnGestionarRecetasYPasos = new JButton("");
		btnGestionarRecetasYPasos.setForeground(new Color(51, 102, 153));
		btnGestionarRecetasYPasos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarRecetasYPasos.setBackground(new Color(51, 102, 204));
		btnGestionarRecetasYPasos.setBounds(34, 519, 40, 40);
		cambiarIconoBotones(btnGestionarRecetasYPasos, "descripcion.png");
		panel.add(btnGestionarRecetasYPasos);
		
		btnGestionarSucursales_1 = new JButton("");
		btnGestionarSucursales_1.setForeground(new Color(51, 102, 153));
		btnGestionarSucursales_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarSucursales_1.setBackground(new Color(51, 102, 204));
		btnGestionarSucursales_1.setBounds(34, 282, 40, 40);
		cambiarIconoBotones(btnGestionarSucursales_1, "store.png");
		panel.add(btnGestionarSucursales_1);
		
		lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(241, 96, 171, 22);
		panel.add(lblSucursal);
		
		lblProveedores = new JLabel("Proveedores");
		lblProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProveedores.setBounds(83, 129, 82, 40);
		panel.add(lblProveedores);
		
		lblpedidosAProveedor = new JLabel("<html>Pedidos a Proveedor</html>");
		lblpedidosAProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblpedidosAProveedor.setBounds(225, 129, 82, 40);
		panel.add(lblpedidosAProveedor);
		
		lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblClientes.setBounds(83, 180, 82, 40);
		panel.add(lblClientes);
		
		lblProductos = new JLabel("Productos");
		lblProductos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProductos.setBounds(83, 231, 82, 40);
		panel.add(lblProductos);
		
		lblmodificarProductos = new JLabel("<html>Modificar Productos</html>");
		lblmodificarProductos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblmodificarProductos.setBounds(225, 231, 82, 40);
		panel.add(lblmodificarProductos);
		
		lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEmpleados.setBounds(225, 180, 82, 40);
		panel.add(lblEmpleados);
		
		lblCotizaciones = new JLabel("Cotizaciones");
		lblCotizaciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCotizaciones.setBounds(83, 342, 82, 40);
		panel.add(lblCotizaciones);
		
		lbltareasAutomatizadas = new JLabel("<html>Tareas Automaticas</html>");
		lbltareasAutomatizadas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbltareasAutomatizadas.setBounds(225, 342, 82, 40);
		panel.add(lbltareasAutomatizadas);
		
		lblSucursales = new JLabel("Sucursales");
		lblSucursales.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSucursales.setBounds(84, 282, 82, 40);
		panel.add(lblSucursales);
		
		lblrankingDeVentas = new JLabel("<html>Ranking de Ventas</html>");
		lblrankingDeVentas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblrankingDeVentas.setBounds(225, 282, 82, 40);
		panel.add(lblrankingDeVentas);
		
		lblcobrarVenta = new JLabel("<html>Cobrar Venta</html>");
		lblcobrarVenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblcobrarVenta.setBounds(83, 404, 82, 40);
		panel.add(lblcobrarVenta);
		
		lblingresosDeCaja = new JLabel("<html>Ingresos de Caja</html>");
		lblingresosDeCaja.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblingresosDeCaja.setBounds(225, 404, 82, 40);
		panel.add(lblingresosDeCaja);
		
		lblegresosDeCaja = new JLabel("<html>Egresos de Caja</html>");
		lblegresosDeCaja.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblegresosDeCaja.setBounds(83, 455, 82, 40);
		panel.add(lblegresosDeCaja);
		
		lblcierreDeCaja = new JLabel("<html>Cierre de Caja</html>");
		lblcierreDeCaja.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblcierreDeCaja.setBounds(225, 455, 82, 40);
		panel.add(lblcierreDeCaja);
		
		lblrecetasDeFabricacion = new JLabel("<html>Recetas de Fabricacion</html>");
		lblrecetasDeFabricacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblrecetasDeFabricacion.setBounds(83, 519, 82, 40);
		panel.add(lblrecetasDeFabricacion);
		
		lblcierreDeCaja_2 = new JLabel("<html>Ordenes de Fabricacion</html>");
		lblcierreDeCaja_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblcierreDeCaja_2.setBounds(225, 519, 82, 40);
		panel.add(lblcierreDeCaja_2);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(34, 393, 415, 11);
		panel.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(34, 506, 415, 11);
		panel.add(separator_2);
		
		btnVerFacturas = new JButton("");
		btnVerFacturas.setForeground(new Color(51, 102, 153));
		btnVerFacturas.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerFacturas.setBackground(new Color(51, 102, 204));
		btnVerFacturas.setBounds(317, 404, 40, 40);
		cambiarIconoBotones(btnVerFacturas, "facturaver.png");
		panel.add(btnVerFacturas);
		
		lblcierreDeCaja_1 = new JLabel("<html>Ver Facturas</html>");
		lblcierreDeCaja_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblcierreDeCaja_1.setBounds(367, 404, 82, 40);
		panel.add(lblcierreDeCaja_1);
		
		btnReporteRiesgoStock = new JButton("");
		btnReporteRiesgoStock.setForeground(new Color(51, 102, 153));
		btnReporteRiesgoStock.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnReporteRiesgoStock.setBackground(new Color(51, 102, 204));
		btnReporteRiesgoStock.setBounds(317, 231, 40, 40);
		cambiarIconoBotones(btnReporteRiesgoStock, "stock.png");
		panel.add(btnReporteRiesgoStock);
		
		lblcierreDeCaja_3 = new JLabel("<html>Reporte de Riesgo Stock</html>");
		lblcierreDeCaja_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblcierreDeCaja_3.setBounds(367, 231, 82, 40);
		panel.add(lblcierreDeCaja_3);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(531, 44, 320, 246);
		panel_1.setBorder(null);
		frame.getContentPane().add(panel_1);
		
		int n1 = 12000;
		int n2 = 23000;
		int n3 = 45000;
		
		DefaultPieDataset datos = new DefaultPieDataset();
		datos.setValue("Tortuguitas", Double.valueOf(n1));
		datos.setValue("Grand Bourg", Double.valueOf(n2));
		datos.setValue("Don Torcuato", Double.valueOf(n3));
		
		JFreeChart graficoTorta = ChartFactory.createPieChart(null, datos, false, false, false);
		
		panelTorta = new ChartPanel(graficoTorta);
		panelTorta.setBackground(new Color(255, 255, 255));
		panelTorta.setBounds(0, 5, 320, 240);
		panelTorta.setMouseWheelEnabled(true);
		panelTorta.setPreferredSize(new Dimension(320,240));
		
		panel_1.add(panelTorta);
		
		panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(531, 374, 320, 246);
		frame.getContentPane().add(panel_1_1);
		
		int e1 = 12000;
		int e2 = 23000;
		int e3 = 45000;
		int e4 = 54320;
		
		DefaultPieDataset datos2 = new DefaultPieDataset();
		datos2.setValue("Pepe Perez", Double.valueOf(e1));
		datos2.setValue("Adriana Aran", Double.valueOf(e2));
		datos2.setValue("Ernesta Escobar", Double.valueOf(e3));
		datos2.setValue("Kiko Miller", Double.valueOf(e4));
		
		JFreeChart graficoTorta2 = ChartFactory.createPieChart(null, datos2, false, false, false);
		
		panelTorta2 = new ChartPanel(graficoTorta2);
		panelTorta2.setBackground(new Color(255, 255, 255));
		panelTorta2.setBounds(0, 5, 320, 240);
		panelTorta2.setMouseWheelEnabled(true);
		panelTorta2.setPreferredSize(new Dimension(320,240));
		
		panel_1_1.add(panelTorta2);
		
		JLabel lblRankingDeVentas = new JLabel("Ranking de Ventas x Sucursal (total en pesos)");
		lblRankingDeVentas.setForeground(new Color(255, 255, 255));
		lblRankingDeVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRankingDeVentas.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		lblRankingDeVentas.setBounds(531, 11, 320, 22);
		frame.getContentPane().add(lblRankingDeVentas);
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(327, 0, 960, 720);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo2.png");
		

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

	public JButton getBtnGestionarProveedores() {
		return btnGestionarProveedores;
	}

	public JButton getBtnGestionarSucursales() {
		return btnGestionarSucursales_1;
	}

	public JButton getBtnGestionarRecetasYPasos() {
		return btnGestionarRecetasYPasos;
	}

	public JButton getBtnCobrarVenta() {
		return btnCobrarVenta;
	}

	public JButton getBtnIngresoDeCaja() {
		return btnIngresoDeCaja;
	}

	public JButton getBtnEgresoDeCaja() {
		return btnEgresoDeCaja;
	}

	public JButton getBtnCierreDeCaja() {
		return btnCierreDeCaja;
	}

	public JButton getBtnGestionarProductos() {
		return btnGestionarProductos;
	}

	public JButton getBtnModPrecioMasivo() {
		return btnModPrecioUnitario;
	}

	public JButton getBtnGestionarClientes() {
		return btnGestionarClientes;
	}

	public JButton getBtnGestionarEmpleados() {
		return btnGestionarEmpleados;
	}

	public JButton getBtnVerReporteRanking() {
		return btnVerReporteRanking;
	}

	public JButton getBtnTareasAutomaticas() {
		return btnTareasAutomaticas;
	}

	public JButton getBtnVerPedidosA() {
		return btnVerPedidosA;
	}

	public JButton getBtnCotizaciones() {
		return btnCotizaciones;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	public JButton getBtnHistorialFabrica() {
		return btnHistorialFabrica;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JLabel getLblGerente() {
		return lblGerente;
	}

	public ChartPanel getPanelTorta() {
		return panelTorta;
	}

	public void setPanelTorta(ChartPanel panelTorta) {
		this.panelTorta = panelTorta;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public JPanel getPanel_1_1() {
		return panel_1_1;
	}

	public ChartPanel getPanelTorta2() {
		return panelTorta2;
	}

	public void setPanelTorta2(ChartPanel panelTorta2) {
		this.panelTorta2 = panelTorta2;
	}

	public JButton getBtnVerFacturas() {
		return btnVerFacturas;
	}

	public JButton getBtnReporteRiesgoStock() {
		return btnReporteRiesgoStock;
	}

	public JButton getBtnSubirCantSucursales() {
		return btnSubirCantSucursales;
	}

	public JButton getBtnBajarCantSucursales() {
		return btnBajarCantSucursales;
	}

	public JButton getBtnSubirCantEmpleados() {
		return btnSubirCantEmpleados;
	}

	public JButton getBtnBajarCantEmpleados() {
		return btnBajarCantEmpleados;
	}

	public JButton getBtnBajarDias1() {
		return btnBajarDias1;
	}

	public JButton getBtnSubirDia1() {
		return btnSubirDia1;
	}

	public JButton getBtnBajarDias10() {
		return btnBajarDias10;
	}

	public JButton getBtnSubirDia10() {
		return btnSubirDia10;
	}

	public JLabel getLblDiasParaAtras() {
		return lblDiasParaAtras;
	}
}
