package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import persistencia.conexion.Conexion;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.getContentPane().setBackground(new Color(153, 204, 255));
		frame.getContentPane().setForeground(new Color(153, 204, 255));
		frame.setBounds(100, 100, 710, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Zapateria Argento - Inicio");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Zapateria");
		lblNewLabel.setForeground(UIManager.getColor("FormattedTextField.selectionForeground"));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Oxygen", Font.BOLD, 54));
		lblNewLabel.setBounds(370, 11, 314, 71);
		frame.getContentPane().add(lblNewLabel);
		
		btnOperatoriaDeFabrica = new JButton("Operatoria de Fabrica");
		btnOperatoriaDeFabrica.setOpaque(true);
		btnOperatoriaDeFabrica.setBackground(new Color(51, 102, 204));
		btnOperatoriaDeFabrica.setToolTipText("");
		btnOperatoriaDeFabrica.setForeground(new Color(51, 102, 153));
		btnOperatoriaDeFabrica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOperatoriaDeFabrica.setFont(new Font("Oxygen", Font.PLAIN, 29));
		btnOperatoriaDeFabrica.setBounds(361, 171, 323, 57);
		frame.getContentPane().add(btnOperatoriaDeFabrica);
		
		btnSistemaDeVentas = new JButton("Sistema de Ventas");
		btnSistemaDeVentas.setBackground(new Color(51, 102, 204));
		btnSistemaDeVentas.setForeground(new Color(51, 102, 153));
		btnSistemaDeVentas.setFont(new Font("Oxygen", Font.PLAIN, 29));
		btnSistemaDeVentas.setBounds(361, 270, 323, 57);
		frame.getContentPane().add(btnSistemaDeVentas);
		
		JLabel lblArgento = new JLabel("Argento");
		lblArgento.setForeground(UIManager.getColor("FormattedTextField.selectionForeground"));
		lblArgento.setVerticalAlignment(SwingConstants.TOP);
		lblArgento.setHorizontalAlignment(SwingConstants.LEFT);
		lblArgento.setFont(new Font("Oxygen", Font.BOLD, 54));
		lblArgento.setBounds(370, 75, 314, 71);
		frame.getContentPane().add(lblArgento);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 351, 378);
		frame.getContentPane().add(lblNewLabel_1);
		cambiarIconoLabel(lblNewLabel_1, "zapas.png");
		
		
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
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}
	

	public JButton getBtnOperatoriaDeFabrica() {
		return btnOperatoriaDeFabrica;
	}

	public JButton getBtnSistemaDeVentas() {
		return btnSistemaDeVentas;
	}
}
