package presentacion.vista.fabrica;

import java.awt.Font;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JLabel;

public class ReVentanaIngresarFechaDeLlegada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	JButton btnIngresarFecha;
	JSpinner spinner;

	public ReVentanaIngresarFechaDeLlegada() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 409, 264);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 376, 210);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnIngresarFecha = new JButton("Completar");
		btnIngresarFecha.setBounds(196, 92, 154, 23);
		panel.add(btnIngresarFecha);
		
		spinner = new JSpinner();
		spinner.setBounds(59, 93, 57, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel = new JLabel("En cuantos dias llegara a la sucursal");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 35, 356, 14);
		panel.add(lblNewLabel);
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
