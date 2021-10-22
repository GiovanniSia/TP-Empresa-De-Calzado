package presentacion.vista.fabrica;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

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

	public ReVentanaSeleccionarUnaReceta() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 664, 408);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(98, 11, 192, 22);
		panel.add(comboBox);
		
		lblSolicitado = new JLabel("New label");
		lblSolicitado.setBounds(10, 45, 356, 28);
		panel.add(lblSolicitado);
		
		spCliente = new JScrollPane();
		spCliente.setBounds(10, 212, 644, 112);
		panel.add(spCliente);
		
		modelOrdenes = new DefaultTableModel(null, nombreColumnas);
		tabla = new JTable(modelOrdenes);
		spCliente.setViewportView(tabla);
		
		lblMensaje = new JLabel("New label");
		lblMensaje.setBounds(10, 335, 356, 28);
		panel.add(lblMensaje);
		
		btnTrabajar = new JButton("Pasar a produccion");
		btnTrabajar.setBounds(10, 374, 154, 23);
		panel.add(btnTrabajar);
		
		lblNewLabel = new JLabel("Receta");
		lblNewLabel.setBounds(10, 15, 78, 14);
		panel.add(lblNewLabel);
		
		lblSucursal = new JLabel("New label");
		lblSucursal.setBounds(10, 123, 356, 28);
		panel.add(lblSucursal);
		
		lblIdPedido = new JLabel("New label");
		lblIdPedido.setBounds(10, 84, 356, 28);
		panel.add(lblIdPedido);
		
		lblFecha = new JLabel("New label");
		lblFecha.setBounds(10, 162, 356, 28);
		panel.add(lblFecha);
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
}
