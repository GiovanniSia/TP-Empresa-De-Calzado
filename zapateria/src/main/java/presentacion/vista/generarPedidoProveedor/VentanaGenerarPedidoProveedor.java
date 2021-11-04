package presentacion.vista.generarPedidoProveedor;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class VentanaGenerarPedidoProveedor {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGenerarPedidoProveedor window = new VentanaGenerarPedidoProveedor();
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
	public VentanaGenerarPedidoProveedor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 735, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 719, 53);
		frame.getContentPane().add(panel_2);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel_2.add(lblLogo);
		
		JLabel lblNewLabel_2 = new JLabel("Sucursal:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(537, 28, 59, 19);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Empleado:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(343, 28, 59, 19);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel = new JLabel("Pedido a Proveedor");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 64, 284, 39);
		frame.getContentPane().add(lblNewLabel);
				
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(0, 106, 719, 364);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Producto Elegido:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 11, 124, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Elegir Proveedor");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(21, 67, 124, 22);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Cantidad a Reponer");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(21, 256, 149, 22);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("(datos producto)");
		lblNewLabel_3.setBounds(322, 18, 83, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("(aqu\u00ED ir\u00EDa la tabla de proveedores)");
		lblNewLabel_3_1.setBounds(21, 97, 213, 14);
		panel.add(lblNewLabel_3_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Proveedores preferenciados");
		rdbtnNewRadioButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnNewRadioButton.setBounds(358, 70, 173, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnTodosLosProveedores = new JRadioButton("Todos los proveedores");
		rdbtnTodosLosProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdbtnTodosLosProveedores.setBounds(533, 70, 161, 23);
		panel.add(rdbtnTodosLosProveedores);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(188, 256, 83, 22);
		panel.add(spinner);
		
		JLabel lblNewLabel_3_2 = new JLabel("(al menos 1 y hasta 999)");
		lblNewLabel_3_2.setBounds(281, 256, 124, 14);
		panel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("(elegir qu\u00E9 proveedores mostrar: los del producto o todos)");
		lblNewLabel_3_1_1.setBounds(375, 50, 334, 14);
		panel.add(lblNewLabel_3_1_1);
		
		JButton btnNewButton = new JButton("Generar Pedido");
		btnNewButton.setBounds(463, 481, 132, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(113, 481, 132, 23);
		frame.getContentPane().add(btnAtras);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 48, 720, 540);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
	}
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}
}
