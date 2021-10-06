package presentacion.vista.fabrica;

import java.awt.Font;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaTrabajarUnPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	JButton btnAvanzarUnPaso;
	JButton btnRetrocederUnPaso;
	JButton btnCancelar;

	public VentanaTrabajarUnPedido() {
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
		
		btnAvanzarUnPaso = new JButton("Avanzar un paso");
		btnAvanzarUnPaso.setBounds(86, 25, 154, 23);
		panel.add(btnAvanzarUnPaso);
		
		btnRetrocederUnPaso = new JButton("Volver un paso");
		btnRetrocederUnPaso.setBounds(86, 59, 154, 23);
		panel.add(btnRetrocederUnPaso);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(86, 93, 154, 23);
		panel.add(btnCancelar);
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

	public JButton getBtnAvanzarUnPaso() {
		return btnAvanzarUnPaso;
	}

	public JButton getBtnRetrocederUnPaso() {
		return btnRetrocederUnPaso;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
}
