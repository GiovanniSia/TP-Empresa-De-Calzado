package presentacion.vista;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import persistencia.conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaInformacionBusquedaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblSeleccionarCliente;
	private JLabel lblTalle;
	private JLabel lblImagenFiltro;
	private JLabel lblLuegoPresioneEn;
	private JLabel lblImagenBtnElegirProductos;

//	public static void main(String[] args){
//		VentanaInformacionBusquedaCliente a = new VentanaInformacionBusquedaCliente();
//		a.show();
//	}

	public VentanaInformacionBusquedaCliente() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 637, 426);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 621, 53);
		contentPane.add(panel);

		lblImagenFiltro = new JLabel("");
		lblImagenFiltro.setBounds(10, 140, 485, 53);
		cambiarIconoLabel(lblImagenFiltro, "BusquedaClienteFiltro.png");
		contentPane.add(lblImagenFiltro);

		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);

		lblSeleccionarCliente = new JLabel("\u00BFComo Elegir un Cliente?");
		lblSeleccionarCliente.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblSeleccionarCliente.setBounds(10, 64, 442, 41);
		contentPane.add(lblSeleccionarCliente);

		lblTalle = new JLabel("Para encontrar al cliente podemos filtrar por estos campos");
		lblTalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTalle.setBounds(10, 116, 442, 13);
		contentPane.add(lblTalle);

		JLabel lblEnEstosCampos = new JLabel("Se puede filtrar por codigo de cliente, nombre, apellido o su CUIL");
		lblEnEstosCampos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblEnEstosCampos.setBounds(10, 200, 485, 13);
		contentPane.add(lblEnEstosCampos);

		JLabel lblAContinuacionSeleccionamos = new JLabel(
				"A continuacion seleccionamos el cliente al cual se le realizara la venta");
		lblAContinuacionSeleccionamos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblAContinuacionSeleccionamos.setBounds(10, 224, 485, 13);
		contentPane.add(lblAContinuacionSeleccionamos);

		JLabel lblImagenSeleccionarCliente = new JLabel("");
		lblImagenSeleccionarCliente.setBounds(10, 239, 601, 63);
		cambiarIconoLabel(lblImagenSeleccionarCliente, "BusquedaClienteSeleccionado.png");
		contentPane.add(lblImagenSeleccionarCliente);

		lblLuegoPresioneEn = new JLabel("Luego presione el boton siguiente boton para continuar con la venta");
		lblLuegoPresioneEn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblLuegoPresioneEn.setBounds(10, 313, 485, 13);
		contentPane.add(lblLuegoPresioneEn);

		lblImagenBtnElegirProductos = new JLabel("");
		lblImagenBtnElegirProductos.setBounds(382, 311, 216, 65);
		cambiarIconoLabel(lblImagenBtnElegirProductos, "BusquedaClienteBotonElegirProductos.png");
		contentPane.add(lblImagenBtnElegirProductos);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});
		frame.setVisible(true);
	}

	public void cerrar() {
		frame.setVisible(false);
	}

	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}
}
