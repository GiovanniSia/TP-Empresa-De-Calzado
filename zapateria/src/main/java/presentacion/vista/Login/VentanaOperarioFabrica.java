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

public class VentanaOperarioFabrica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JButton btnOperatoriaDeFabrica;
	
	
	public VentanaOperarioFabrica() {
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
		
		JLabel lblNewLabel = new JLabel("Operario de Fabrica");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, "name_5308677579600");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 66, 476, 216);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnOperatoriaDeFabrica = new JButton("Operatoria de Fabrica");
		btnOperatoriaDeFabrica.setToolTipText("");
		btnOperatoriaDeFabrica.setOpaque(true);
		btnOperatoriaDeFabrica.setForeground(new Color(51, 102, 153));
		btnOperatoriaDeFabrica.setFont(new Font("Segoe UI", Font.PLAIN, 29));
		btnOperatoriaDeFabrica.setBackground(new Color(51, 102, 204));
		btnOperatoriaDeFabrica.setBounds(71, 11, 323, 57);
		panel_1.add(btnOperatoriaDeFabrica);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		for (WindowListener listener : this.frame.getWindowListeners()) {
			this.frame.removeWindowListener(listener);
		}
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "�Est�s seguro que quieres salir?", "Advertencia",
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

	public JButton getBtnOperatoriaDeFabrica() {
		return btnOperatoriaDeFabrica;
	}
	
}
