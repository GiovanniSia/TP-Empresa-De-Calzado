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
		frame.setBounds(100, 100, 1044, 743);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRankingDeVentas_2 = new JLabel("Ranking de Ventas x Vendedor");
		lblRankingDeVentas_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRankingDeVentas_2.setForeground(Color.WHITE);
		lblRankingDeVentas_2.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		lblRankingDeVentas_2.setBounds(803, 17, 320, 22);
		frame.getContentPane().add(lblRankingDeVentas_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 340, 704);
		panel.setBackground(new Color(248, 248, 255));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 11, 319, 86);
		panel.add(lblLogo);
		cambiarIconoLabel(lblLogo,"argentoshoes.png");
		
		JLabel lblNewLabel = new JLabel("Gerente:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 108, 91, 22);
		panel.add(lblNewLabel);
		
		JLabel lblNombreGerente = new JLabel("Nombre Gerente");
		lblNombreGerente.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombreGerente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNombreGerente.setBounds(121, 108, 199, 22);
		panel.add(lblNombreGerente);
		
		btnGestionarProductos = new JButton("Gestionar Productos");
		btnGestionarProductos.setForeground(new Color(51, 102, 153));
		btnGestionarProductos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarProductos.setBackground(new Color(51, 102, 204));
		btnGestionarProductos.setBounds(30, 145, 282, 41);
		panel.add(btnGestionarProductos);
		
		btnModPrecioUnitario = new JButton("Modificar Precios Masivos");
		btnModPrecioUnitario.setForeground(new Color(51, 102, 153));
		btnModPrecioUnitario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnModPrecioUnitario.setBackground(new Color(51, 102, 204));
		btnModPrecioUnitario.setBounds(38, 178, 282, 41);
		panel.add(btnModPrecioUnitario);
		
		btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.setForeground(new Color(51, 102, 153));
		btnGestionarClientes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarClientes.setBackground(new Color(51, 102, 204));
		btnGestionarClientes.setBounds(27, 213, 282, 41);
		panel.add(btnGestionarClientes);
		
		btnGestionarEmpleados = new JButton("Gestionar Empleados");
		btnGestionarEmpleados.setForeground(new Color(51, 102, 153));
		btnGestionarEmpleados.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarEmpleados.setBackground(new Color(51, 102, 204));
		btnGestionarEmpleados.setBounds(38, 248, 282, 41);
		panel.add(btnGestionarEmpleados);
		
		btnVerReporteRanking = new JButton("Ranking de Ventas");
		btnVerReporteRanking.setForeground(new Color(51, 102, 153));
		btnVerReporteRanking.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerReporteRanking.setBackground(new Color(51, 102, 204));
		btnVerReporteRanking.setBounds(10, 280, 282, 41);
		panel.add(btnVerReporteRanking);
		
		btnTareasAutomaticas = new JButton("Tareas Automaticas");
		btnTareasAutomaticas.setForeground(new Color(51, 102, 153));
		btnTareasAutomaticas.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnTareasAutomaticas.setBackground(new Color(51, 102, 204));
		btnTareasAutomaticas.setBounds(20, 312, 282, 41);
		panel.add(btnTareasAutomaticas);
		
		btnVerPedidosA = new JButton("Pedidos a Proveedores");
		btnVerPedidosA.setForeground(new Color(51, 102, 153));
		btnVerPedidosA.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerPedidosA.setBackground(new Color(51, 102, 204));
		btnVerPedidosA.setBounds(38, 348, 282, 41);
		panel.add(btnVerPedidosA);
		
		btnCotizaciones = new JButton("Cotizaciones");
		btnCotizaciones.setForeground(new Color(51, 102, 153));
		btnCotizaciones.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCotizaciones.setBackground(new Color(51, 102, 204));
		btnCotizaciones.setBounds(10, 379, 282, 41);
		panel.add(btnCotizaciones);
		
		btnCerrarSesion = new JButton("");
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		btnCerrarSesion.setBounds(10, 633, 60, 60);
		cambiarIconoBotones(btnCerrarSesion, "exit.png");
		panel.add(btnCerrarSesion);
		
		JLabel lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(80, 633, 132, 60);
		panel.add(lblNewLabel_1);
		
		btnHistorialFabrica = new JButton("Gestion Fabrica");
		btnHistorialFabrica.setForeground(new Color(51, 102, 153));
		btnHistorialFabrica.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnHistorialFabrica.setBackground(new Color(51, 102, 204));
		btnHistorialFabrica.setBounds(48, 415, 282, 41);
		panel.add(btnHistorialFabrica);
		
		btnCobrarVenta = new JButton("Cobrar Venta");
		btnCobrarVenta.setForeground(new Color(51, 102, 153));
		btnCobrarVenta.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCobrarVenta.setBackground(new Color(51, 102, 204));
		btnCobrarVenta.setBounds(9, 547, 282, 41);
		panel.add(btnCobrarVenta);
		
		btnIngresoDeCaja = new JButton("Ingreso de Caja");
		btnIngresoDeCaja.setForeground(new Color(51, 102, 153));
		btnIngresoDeCaja.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnIngresoDeCaja.setBackground(new Color(51, 102, 204));
		btnIngresoDeCaja.setBounds(27, 450, 282, 41);
		panel.add(btnIngresoDeCaja);
		
		btnEgresoDeCaja = new JButton("Egreso de Caja");
		btnEgresoDeCaja.setForeground(new Color(51, 102, 153));
		btnEgresoDeCaja.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnEgresoDeCaja.setBackground(new Color(51, 102, 204));
		btnEgresoDeCaja.setBounds(-11, 481, 282, 41);
		panel.add(btnEgresoDeCaja);
		
		btnCierreDeCaja = new JButton("Cierre de Caja");
		btnCierreDeCaja.setForeground(new Color(51, 102, 153));
		btnCierreDeCaja.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCierreDeCaja.setBackground(new Color(51, 102, 204));
		btnCierreDeCaja.setBounds(27, 514, 282, 41);
		panel.add(btnCierreDeCaja);
		
		btnGestionarRecetasYPasos = new JButton("Gestionar Recetas y Pasos");
		btnGestionarRecetasYPasos.setForeground(new Color(51, 102, 153));
		btnGestionarRecetasYPasos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnGestionarRecetasYPasos.setBackground(new Color(51, 102, 204));
		btnGestionarRecetasYPasos.setBounds(20, 577, 282, 41);
		panel.add(btnGestionarRecetasYPasos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(383, 50, 320, 246);
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
		
		ChartPanel panelTorta = new ChartPanel(graficoTorta);
		panelTorta.setBackground(new Color(255, 255, 255));
		panelTorta.setBounds(0, 5, 320, 240);
		panelTorta.setMouseWheelEnabled(true);
		panelTorta.setPreferredSize(new Dimension(320,240));
		
		panel_1.add(panelTorta);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(383, 380, 320, 246);
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
		
		ChartPanel panelTorta2 = new ChartPanel(graficoTorta2);
		panelTorta2.setBackground(new Color(255, 255, 255));
		panelTorta2.setBounds(0, 5, 320, 240);
		panelTorta2.setMouseWheelEnabled(true);
		panelTorta2.setPreferredSize(new Dimension(320,240));
		
		panel_1_1.add(panelTorta2);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBounds(803, 50, 320, 246);
		frame.getContentPane().add(panel_1_2);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setLayout(null);
		panel_1_3.setBounds(803, 380, 320, 246);
		frame.getContentPane().add(panel_1_3);
		
		JLabel lblRankingDeVentas = new JLabel("Ranking de Ventas x Sucursal");
		lblRankingDeVentas.setForeground(new Color(255, 255, 255));
		lblRankingDeVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRankingDeVentas.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		lblRankingDeVentas.setBounds(383, 17, 320, 22);
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
}
