package presentacion.vista.fabrica;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextPane;

public class VentanaVerDetalle extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private DefaultTableModel modelOrdenes;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblId;
	
	JTextPane textPane;
	JButton btnSalir;
	private JLabel lblNewLabel_1;
	

	public VentanaVerDetalle() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 524, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 64, 508, 349);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblId = new JLabel("-");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblId.setBounds(10, 0, 145, 34);
		panel.add(lblId);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(598, 11, 46, 14);
		panel.add(lblEstado);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textPane.setBounds(10, 38, 488, 299);
		panel.add(textPane);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 508, 10);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Detalle");
		lblNewLabel.setBounds(10, 21, 324, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		btnSalir = new JButton("");
		btnSalir.setBounds(180, 426, 60, 60);
		frame.getContentPane().add(btnSalir);
		cambiarIconoBotones(btnSalir,  "back2.png");
		
		lblNewLabel_1 = new JLabel("Volver");
		lblNewLabel_1.setBounds(250, 424, 60, 62);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 1000, 750);
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
	
	public DefaultTableModel getModelOrdenes() {
		return modelOrdenes;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JLabel getLblId() {
		return lblId;
	}
}
