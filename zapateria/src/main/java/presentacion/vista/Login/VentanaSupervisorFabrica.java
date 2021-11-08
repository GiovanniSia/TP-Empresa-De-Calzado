package presentacion.vista.Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

public class VentanaSupervisorFabrica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JButton btnCerrarSesion;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JButton btnOperatoriaDeFabrica;

	public VentanaSupervisorFabrica() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 732, 332);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		JLabel lblNewLabel_1 = new JLabel("Cerrar Sesion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(80, 222, 132, 60);
		contentPane.add(lblNewLabel_1);
		
		btnCerrarSesion = new JButton("");
		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setForeground(new Color(51, 102, 153));
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnCerrarSesion.setBackground(new Color(51, 102, 204));
		btnCerrarSesion.setBounds(10, 222, 60, 60);
		cambiarIconoBotones(btnCerrarSesion, "exit.png");
		contentPane.add(btnCerrarSesion);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 719, 53);
		contentPane.add(panel);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes.png");
		panel.add(lblLogo);
		
		lblNewLabel = new JLabel("F\u00E1brica");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(537, 28, 172, 19);
		panel.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("Empleado:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(343, 28, 59, 19);
		panel.add(lblNewLabel_2);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 720, 540);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
		
		btnOperatoriaDeFabrica = new JButton("Operatoria de Fabrica");
		btnOperatoriaDeFabrica.setToolTipText("");
		btnOperatoriaDeFabrica.setOpaque(true);
		btnOperatoriaDeFabrica.setForeground(new Color(51, 102, 153));
		btnOperatoriaDeFabrica.setFont(new Font("Segoe UI", Font.PLAIN, 29));
		btnOperatoriaDeFabrica.setBackground(new Color(51, 102, 204));
		btnOperatoriaDeFabrica.setBounds(202, 79, 323, 57);
		contentPane.add(btnOperatoriaDeFabrica);
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

	public JButton getBtnOperatoriaDeFabrica() {
		return btnOperatoriaDeFabrica;
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
}
