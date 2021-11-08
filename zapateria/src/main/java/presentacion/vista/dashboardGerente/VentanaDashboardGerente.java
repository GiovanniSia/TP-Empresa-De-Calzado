package presentacion.vista.dashboardGerente;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaDashboardGerente {

	private JFrame frame;

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
		frame.setBounds(100, 100, 1185, 700);
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
		panel.setBounds(0, 0, 340, 661);
		panel.setBackground(new Color(248, 248, 255));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 50, 319, 86);
		panel.add(lblLogo);
		cambiarIconoLabel(lblLogo,"argentoshoes.png");
		
		JLabel lblNewLabel = new JLabel("Gerente:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 156, 91, 22);
		panel.add(lblNewLabel);
		
		JLabel lblNombreGerente = new JLabel("Nombre Gerente");
		lblNombreGerente.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombreGerente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNombreGerente.setBounds(121, 156, 199, 22);
		panel.add(lblNombreGerente);
		
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
}
