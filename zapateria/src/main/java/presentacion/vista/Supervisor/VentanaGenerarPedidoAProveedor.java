package presentacion.vista.Supervisor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

public class VentanaGenerarPedidoAProveedor extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	
	private String[] nombreColumnas = {"Id","Descripcion","Tipo","Prod. Propio","Costo prod.","Precio Mayorista","Precio Minorista","Punto de rep. minimo","Id Prov","Talle","Medida","Estado","Cant a rep","Dias para rep.","Stock disp"};
	private DefaultTableModel modelProductos;
	private JLabel lblFiltrarPor;
	private JLabel lblGestionarProductos;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JButton btnAtras;
	private JLabel lblAtrs;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGenerarPedidoAProveedor window = new VentanaGenerarPedidoAProveedor();
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaGenerarPedidoAProveedor() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 932, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 191, 908, 224);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		modelProductos = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 5) {
					return true;
				} else {
					return false;
				}
			}
		};

		btnAtras = new JButton("");
		btnAtras.setBackground(new Color(248, 248, 255));
		btnAtras.setBounds(20, 163, 55, 50);
		panel.add(btnAtras);
		cambiarIconoBotones(btnAtras, "back2.png");

		lblAtrs = new JLabel("Atras");
		lblAtrs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtrs.setBounds(85, 163, 70, 50);
		panel.add(lblAtrs);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(248, 248, 255));
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 95, 908, 95);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 0, 70, 25);
		panel_1.add(lblFiltrarPor);

		lblGestionarProductos = new JLabel("Pedidos a Proveedores");
		lblGestionarProductos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblGestionarProductos.setBounds(10, 52, 259, 43);
		frame.getContentPane().add(lblGestionarProductos);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 908, 50);
		frame.getContentPane().add(panel_2);

		lblNewLabel = new JLabel("Zapateria Argento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 0, 421, 50);
		panel_2.add(lblNewLabel);
	}

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estas seguro que quieres salir?", "Advertencia",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
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

	public void mostrarVentana() {
		this.setVisible(true);
	}

}
