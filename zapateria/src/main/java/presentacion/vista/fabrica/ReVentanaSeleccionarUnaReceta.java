package presentacion.vista.fabrica;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class ReVentanaSeleccionarUnaReceta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	JComboBox<String> comboBox;
	
	JButton btnTrabajar;
	
	JLabel lblSolicitado;
	
	private String[] nombreColumnas = { "Material", "Cantidad usada x unidad", "Cantidad a usar en total", "En stock actualmente", "Unidad medida"};
	private JScrollPane spCliente;
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JLabel lblMensaje;
	private JLabel lblIdPedido;
	
	private JLabel lblSucursal;
	private JLabel lblNewLabel;
	private JLabel lblFecha;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	
	private JButton btnAtras ;

	public ReVentanaSeleccionarUnaReceta() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 703, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(10, 25, 664, 408);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox.setBounds(98, 11, 252, 22);
		panel.add(comboBox);
		
		lblSolicitado = new JLabel("New label");
		lblSolicitado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSolicitado.setBounds(10, 45, 356, 28);
		panel.add(lblSolicitado);
		
		spCliente = new JScrollPane();
		spCliente.setBounds(10, 212, 644, 112);
		panel.add(spCliente);
		
		modelOrdenes = new DefaultTableModel(null, nombreColumnas);
		tabla = new JTable(modelOrdenes);
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spCliente.setViewportView(tabla);
		
		lblMensaje = new JLabel("New label");
		lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMensaje.setBounds(10, 335, 356, 28);
		panel.add(lblMensaje);
		
		btnTrabajar = new JButton("");
		btnTrabajar.setBounds(447, 337, 60, 60);
		cambiarIconoBotones(btnTrabajar,  "pick.png");
		panel.add(btnTrabajar);
		
		lblNewLabel = new JLabel("Receta");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 15, 78, 14);
		panel.add(lblNewLabel);
		
		lblSucursal = new JLabel("New label");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(10, 123, 356, 28);
		panel.add(lblSucursal);
		
		lblIdPedido = new JLabel("New label");
		lblIdPedido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblIdPedido.setBounds(10, 84, 356, 28);
		panel.add(lblIdPedido);
		
		lblFecha = new JLabel("New label");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFecha.setBounds(10, 162, 356, 28);
		panel.add(lblFecha);
		
		lblNewLabel_1 = new JLabel("Pasar a Produccion");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(517, 337, 137, 60);
		panel.add(lblNewLabel_1);
		
		btnAtras = new JButton("");
		btnAtras.setBounds(304, 337, 60, 60);
		cambiarIconoBotones(btnAtras,  "back2.png");
		panel.add(btnAtras);
		
		JLabel lblNewLabel_1_1 = new JLabel("Atras");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(371, 337, 66, 60);
		panel.add(lblNewLabel_1_1);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 687, 14);
		frame.getContentPane().add(panel_1);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 960, 720);
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

	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	
	public JButton getBtnTrabajar() {
		return btnTrabajar;
	}

	public JLabel getLblSolicitado() {
		return lblSolicitado;
	}
	
	public JTable getTablaOrdenesPendientes() {
		return tabla;
	}

	public DefaultTableModel getModelOrdenes() {
		return modelOrdenes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JLabel getLblMensaje() {
		return lblMensaje;
	}

	public JLabel getLblIdPedido() {
		return lblIdPedido;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}
}
