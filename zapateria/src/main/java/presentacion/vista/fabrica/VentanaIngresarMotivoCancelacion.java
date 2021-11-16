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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class VentanaIngresarMotivoCancelacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private DefaultTableModel modelOrdenes;
	private JPanel panel_2;

	private JButton btnCancelar;
	private JLabel lblNewLabel;
	private JLabel lblId;
	
	JTextPane textPane;
	JButton btnNoCancelar;
	JComboBox<String> comboBoxMotivo;
	private JLabel lblVolverNoCancelar;
	private JLabel lblConfirmarCancelacion;
	

	public VentanaIngresarMotivoCancelacion() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 524, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 64, 508, 334);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblId = new JLabel("Motivo por el que se cancela la fabricacion del producto");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblId.setBounds(20, 10, 296, 23);
		panel.add(lblId);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(598, 11, 46, 14);
		panel.add(lblEstado);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textPane.setBounds(10, 70, 488, 252);
		panel.add(textPane);
		
		comboBoxMotivo = new JComboBox<String>();
		comboBoxMotivo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxMotivo.setBounds(20, 36, 227, 23);
		panel.add(comboBoxMotivo);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 508, 10);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Cancelacion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 21, 508, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		lblVolverNoCancelar = new JLabel("Volver, no cancelar");
		lblVolverNoCancelar.setBounds(92, 409, 131, 60);
		frame.getContentPane().add(lblVolverNoCancelar);
		lblVolverNoCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		btnNoCancelar = new JButton("");
		btnNoCancelar.setBounds(22, 409, 60, 60);
		frame.getContentPane().add(btnNoCancelar);
		cambiarIconoBotones(btnNoCancelar,  "back2.png");
		
		btnCancelar = new JButton("");
		btnCancelar.setBounds(258, 409, 60, 60);
		frame.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cambiarIconoBotones(btnCancelar,  "cancel2.png");
		
		lblConfirmarCancelacion = new JLabel("Confirmar Cancelacion");
		lblConfirmarCancelacion.setBounds(328, 409, 158, 60);
		frame.getContentPane().add(lblConfirmarCancelacion);
		lblConfirmarCancelacion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
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

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public JButton getBtnNoCancelar() {
		return btnNoCancelar;
	}

	public JComboBox<String> getComboBoxMotivo() {
		return comboBoxMotivo;
	}
}
