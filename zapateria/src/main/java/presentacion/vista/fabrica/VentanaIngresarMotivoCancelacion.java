package presentacion.vista.fabrica;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	

	public VentanaIngresarMotivoCancelacion() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 64, 496, 399);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnCancelar = new JButton("Cancelacion");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(10, 352, 177, 23);
		panel.add(btnCancelar);
		
		lblId = new JLabel("Motivo por el que se cancela");
		lblId.setBounds(10, 11, 145, 14);
		panel.add(lblId);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(598, 11, 46, 14);
		panel.add(lblEstado);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 38, 476, 299);
		panel.add(textPane);
		
		btnNoCancelar = new JButton("No Cancelar");
		btnNoCancelar.setBounds(309, 352, 177, 23);
		panel.add(btnNoCancelar);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 806, 10);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Cancelacion");
		lblNewLabel.setBounds(10, 21, 324, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
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
}
