package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import persistencia.conexion.Conexion;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VentanaMenuSistemaDeVentas {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	private JButton btnArmarVenta;
	private JButton btnCobrarVenta;
	private JButton btnCotizaciones;
	private JButton btnEgresoCaja;
	private JButton btnIngresoCaja;
	private JButton btnCierreCaja;
	private JButton btnModPrecioUnitario;
	private JButton btnRegresar;

	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JButton btnGenerarOrdenDe;

	private JLabel lblNewLabel_8;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuSistemaDeVentas window = new VentanaMenuSistemaDeVentas();
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
	public VentanaMenuSistemaDeVentas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Zapateria Argento - Sistema de Ventas");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 818, 391);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 0, 798, 43);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("ZAPATERIA");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		lblNewLabel.setBounds(0, 5, 421, 28);
		panel_1.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("(Vendedor)");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(113, 121, 184, 20);
		panel.add(lblNewLabel_1);
		
		btnArmarVenta = new JButton("Armar Venta");
		btnArmarVenta.setFont(new Font("Consolas", Font.PLAIN, 28));
		btnArmarVenta.setBounds(65, 70, 282, 41);
		panel.add(btnArmarVenta);
		
		btnCobrarVenta = new JButton("Cobrar Venta");
		btnCobrarVenta.setFont(new Font("Consolas", Font.PLAIN, 28));
		btnCobrarVenta.setBounds(65, 151, 282, 41);
		panel.add(btnCobrarVenta);
		
		btnCotizaciones = new JButton("Cotizaciones");
		btnCotizaciones.setFont(new Font("Consolas", Font.PLAIN, 28));
		btnCotizaciones.setBounds(65, 232, 282, 41);
		panel.add(btnCotizaciones);
		
		btnEgresoCaja = new JButton("Egreso de Caja");
		btnEgresoCaja.setFont(new Font("Consolas", Font.PLAIN, 28));
		btnEgresoCaja.setBounds(473, 151, 282, 41);
		panel.add(btnEgresoCaja);
		
		btnIngresoCaja = new JButton("Ingreso de Caja");
		btnIngresoCaja.setFont(new Font("Consolas", Font.PLAIN, 28));
		btnIngresoCaja.setBounds(473, 70, 282, 41);
		panel.add(btnIngresoCaja);
		
		btnCierreCaja = new JButton("Cierre de Caja");
		btnCierreCaja.setFont(new Font("Consolas", Font.PLAIN, 28));
		btnCierreCaja.setBounds(473, 232, 282, 41);
		panel.add(btnCierreCaja);
		
		lblNewLabel_2 = new JLabel("(Cajero)");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(113, 202, 184, 20);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("(Supervisor)");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(113, 283, 184, 20);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("(Cajero)");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(530, 281, 184, 20);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("(Cajero)");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(530, 202, 184, 20);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("(Cajero)");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblNewLabel_6.setBounds(530, 121, 184, 20);
		panel.add(lblNewLabel_6);
		
		btnModPrecioUnitario = new JButton("Modificar Precio Unitario");
		btnModPrecioUnitario.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnModPrecioUnitario.setBounds(65, 313, 282, 35);
		panel.add(btnModPrecioUnitario);
		
		lblNewLabel_7 = new JLabel("(Supervisor)");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblNewLabel_7.setBounds(113, 358, 184, 20);
		panel.add(lblNewLabel_7);
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(760, 331, 48, 50);
		btnRegresar.setIcon(setIcono("imagenes/back.png",btnRegresar));
		panel.add(btnRegresar);
		
		btnGenerarOrdenDe = new JButton("Generar orden de manufactura");
		btnGenerarOrdenDe.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnGenerarOrdenDe.setBounds(473, 307, 282, 41);
		panel.add(btnGenerarOrdenDe);
		
		lblNewLabel_8 = new JLabel("(Gerente)");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblNewLabel_8.setBounds(530, 358, 184, 20);
		panel.add(lblNewLabel_8);
	}
	
	
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?", 
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
		frame.setVisible(false);
	}
	
	
	public Icon setIcono(String url,JButton boton) {
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
	}
	
	
	
	
	
	public JButton getBtnArmarVenta() {
		return btnArmarVenta;
	}

	public JButton getBtnCobrarVenta() {
		return btnCobrarVenta;
	}

	public JButton getBtnCotizaciones() {
		return btnCotizaciones;
	}

	public JButton getBtnEgresoCaja() {
		return btnEgresoCaja;
	}

	public JButton getBtnIngresoCaja() {
		return btnIngresoCaja;
	}

	public JButton getBtnCierreCaja() {
		return btnCierreCaja;
	}
	public JButton getBtnModPrecioUnitario() {
		return btnModPrecioUnitario;
	}
	
	public JButton getBtnRegresar() {
		return btnRegresar;
	}
	
	public JButton getBtnGenerarOrdenDe() {
		return btnGenerarOrdenDe;
	}

}
