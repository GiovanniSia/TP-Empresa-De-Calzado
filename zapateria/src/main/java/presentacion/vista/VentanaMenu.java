package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import persistencia.conexion.Conexion;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class VentanaMenu {

	private JFrame frame;
	private JButton btnOperatoriaDeFabrica;
	private JButton btnSistemaDeVentas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu window = new VentanaMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Zapateria Argento - Inicio");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Zapateria");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 54));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 10, 772, 127);
		frame.getContentPane().add(lblNewLabel);
		
		btnOperatoriaDeFabrica = new JButton("Operatoria de Fabrica");
		btnOperatoriaDeFabrica.setFont(new Font("Consolas", Font.PLAIN, 29));
		btnOperatoriaDeFabrica.setBounds(162, 147, 471, 57);
		frame.getContentPane().add(btnOperatoriaDeFabrica);
		
		btnSistemaDeVentas = new JButton("Sistema de Ventas");
		btnSistemaDeVentas.setFont(new Font("Consolas", Font.PLAIN, 29));
		btnSistemaDeVentas.setBounds(166, 245, 471, 57);
		frame.getContentPane().add(btnSistemaDeVentas);
		
		
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?", 
		             "Advertencia", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		this.frame.setVisible(true);
	}

	public void cerrar() {
		frame.setVisible(false);
	}
	
	
	

	public JButton getBtnOperatoriaDeFabrica() {
		return btnOperatoriaDeFabrica;
	}

	public JButton getBtnSistemaDeVentas() {
		return btnSistemaDeVentas;
	}

}
