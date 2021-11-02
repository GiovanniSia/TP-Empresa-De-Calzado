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
import javax.swing.JButton;

public class VentanaCajero extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JButton btnIngresoDeCaja;
	private JButton btnEgresoDeCaja;
	private JButton btnCierreDeCaja;
	private JButton btnCobrarVenta;

	public VentanaCajero() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 512, 332);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();

		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 0, 496, 55);

		contentPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Cajero");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, "name_5308677579600");

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 66, 476, 216);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnIngresoDeCaja = new JButton("Ingreso de Caja");
		btnIngresoDeCaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIngresoDeCaja.setBounds(153, 11, 178, 39);
		panel_1.add(btnIngresoDeCaja);

		btnEgresoDeCaja = new JButton("Egreso de Caja");
		btnEgresoDeCaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEgresoDeCaja.setBounds(153, 61, 178, 39);
		panel_1.add(btnEgresoDeCaja);

		btnCierreDeCaja = new JButton("Cierre de Caja");
		btnCierreDeCaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCierreDeCaja.setBounds(153, 111, 178, 39);
		panel_1.add(btnCierreDeCaja);

		btnCobrarVenta = new JButton("Cobrar Venta");
		btnCobrarVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCobrarVenta.setBounds(153, 161, 178, 39);
		panel_1.add(btnCobrarVenta);
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

}
