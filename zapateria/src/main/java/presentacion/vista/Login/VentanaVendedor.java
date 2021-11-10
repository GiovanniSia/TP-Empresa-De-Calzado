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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaVendedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JButton btnArmarVenta;
	private JButton btnCerrarSesion;
	private JLabel lblNewLabel_1;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JButton btnVerAgregarClientes;
	private JButton btnVerProductos;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	public VentanaVendedor() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 736, 459);
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
		panel_1.setBounds(10, 65, 700, 273);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnArmarVenta = new JButton("");
		btnArmarVenta.setForeground(new Color(51, 102, 153));
		btnArmarVenta.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnArmarVenta.setBackground(new Color(51, 102, 204));
		btnArmarVenta.setBounds(35, 74, 120, 120);
		cambiarIconoBotones(btnArmarVenta, "tag.png");
		panel_1.add(btnArmarVenta);
		
		btnVerAgregarClientes = new JButton("");
		btnVerAgregarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerAgregarClientes.setForeground(new Color(51, 102, 153));
		btnVerAgregarClientes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerAgregarClientes.setBackground(new Color(51, 102, 204));
		btnVerAgregarClientes.setBounds(285, 74, 120, 120);
		cambiarIconoBotones(btnVerAgregarClientes, "person.png");
		panel_1.add(btnVerAgregarClientes);
		
		btnVerProductos = new JButton("");
		btnVerProductos.setForeground(new Color(51, 102, 153));
		btnVerProductos.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerProductos.setBackground(new Color(51, 102, 204));
		btnVerProductos.setBounds(535, 74, 120, 120);
		cambiarIconoBotones(btnVerProductos, "product.png");
		panel_1.add(btnVerProductos);
		
		lblNewLabel_3 = new JLabel("Armar Venta");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(35, 30, 120, 43);
		panel_1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Clientes");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(285, 30, 120, 43);
		panel_1.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Productos");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(535, 30, 120, 43);
		panel_1.add(lblNewLabel_5);
		
		lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setBounds(80, 349, 132, 60);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
				btnCerrarSesion = new JButton("");
				btnCerrarSesion.setBounds(10, 349, 60, 60);
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

	public JButton getBtnVerAgregarClientes() {
		return btnVerAgregarClientes;
	}

	public JButton getBtnVerProductos() {
		return btnVerProductos;
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

	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}
}
