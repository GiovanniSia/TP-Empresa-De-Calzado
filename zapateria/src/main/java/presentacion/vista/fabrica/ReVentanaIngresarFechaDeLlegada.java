package presentacion.vista.fabrica;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Color;

public class ReVentanaIngresarFechaDeLlegada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	JButton btnIngresarFecha;
	JSpinner spinner;
	private JLabel lblCompletar;

	public ReVentanaIngresarFechaDeLlegada() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 427, 264);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(10, 11, 391, 210);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnIngresarFecha = new JButton("");
		btnIngresarFecha.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnIngresarFecha.setBounds(188, 108, 60, 60);
		cambiarIconoBotones(btnIngresarFecha,  "check2.png");
		panel.add(btnIngresarFecha);
		
		spinner = new JSpinner();
		spinner.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		spinner.setBounds(77, 108, 70, 60);
		panel.add(spinner);
		
		JLabel lblNewLabel = new JLabel("\u00BFEn cuantos dias llegara a la sucursal?");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 35, 371, 46);
		panel.add(lblNewLabel);
		
		lblCompletar = new JLabel("Completar");
		lblCompletar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCompletar.setBounds(258, 108, 123, 56);
		panel.add(lblCompletar);
	}
	
	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});
		this.frame.setVisible(true);
	}

	public void cerrar() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JButton getBtnbtnIngresarFecha() {
		return btnIngresarFecha;
	}

	public JSpinner getSpinner() {
		return spinner;
	}
	
	
}
