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
import javax.swing.border.MatteBorder;

public class VentanaCajero extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JButton btnIngresoDeCaja;
	private JButton btnEgresoDeCaja;
	private JButton btnCierreDeCaja;
	private JButton btnCobrarVenta;
	private JLabel lblNewLabel_1;
	private JButton btnCerrarSesion;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_3;
	private JLabel lblNewLabel_1_4;

	public VentanaCajero() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 708, 397);
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
		panel.setBounds(0, 0, 719, 53);
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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255, 180));
		panel_1.setBounds(10, 66, 672, 216);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnIngresoDeCaja = new JButton("");
		btnIngresoDeCaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIngresoDeCaja.setBounds(34, 61, 120, 120);
		btnIngresoDeCaja.setBackground(new Color(51, 102, 204));
		cambiarIconoBotones(btnIngresoDeCaja, "cashier+.png");
		panel_1.add(btnIngresoDeCaja);

		btnEgresoDeCaja = new JButton("");
		btnEgresoDeCaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEgresoDeCaja.setBounds(194, 61, 120, 120);
		btnEgresoDeCaja.setBackground(new Color(51, 102, 204));
		cambiarIconoBotones(btnEgresoDeCaja, "cashier-.png");
		panel_1.add(btnEgresoDeCaja);

		btnCierreDeCaja = new JButton("");
		btnCierreDeCaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCierreDeCaja.setBounds(514, 61, 120, 120);
		btnCierreDeCaja.setBackground(new Color(51, 102, 204));
		cambiarIconoBotones(btnCierreDeCaja, "cashierlock.png");
		panel_1.add(btnCierreDeCaja);

		btnCobrarVenta = new JButton("");
		btnCobrarVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCobrarVenta.setBounds(354, 61, 120, 120);
		btnCobrarVenta.setBackground(new Color(51, 102, 204));
		cambiarIconoBotones(btnCobrarVenta, "factura.png");
		panel_1.add(btnCobrarVenta);
		
		JLabel lblNewLabel_1_1 = new JLabel("<html>Ingresos de Caja</html>");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(34, 21, 120, 43);
		panel_1.add(lblNewLabel_1_1);
		
		lblNewLabel_1_2 = new JLabel("<html>Egresos de Caja</html>");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(194, 21, 120, 43);
		panel_1.add(lblNewLabel_1_2);
		
		lblNewLabel_1_3 = new JLabel("<html>Cobrar Venta</html>");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(354, 21, 120, 43);
		panel_1.add(lblNewLabel_1_3);
		
		lblNewLabel_1_4 = new JLabel("<html>Cierre de Caja</html>");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(514, 21, 120, 43);
		panel_1.add(lblNewLabel_1_4);
		
		lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setBounds(80, 293, 132, 60);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		btnCerrarSesion = new JButton("");
		btnCerrarSesion.setBounds(10, 293, 60, 60);
		contentPane.add(btnCerrarSesion);
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		cambiarIconoBotones(btnCerrarSesion, "exit.png");
		
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

	public JButton getBtnIngresoDeCaja() {
		return btnIngresoDeCaja;
	}

	public JButton getBtnEgresoDeCaja() {
		return btnEgresoDeCaja;
	}

	public JButton getBtnCierreDeCaja() {
		return btnCierreDeCaja;
	}

	public JButton getBtnCobrarVenta() {
		return btnCobrarVenta;
	}

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}
}
