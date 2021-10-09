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
import javax.swing.JComboBox;

public class ReVentanaSeleccionarUnaReceta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	JComboBox comboBox;
	
	JButton btnTrabajar;
	
	JLabel lblSolicitado;
	
	private String[] nombreColumnas = { "Material", "Cantidad" };
	private JScrollPane spCliente;
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JLabel lblMensaje;

	public ReVentanaSeleccionarUnaReceta() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 409, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 376, 289);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(98, 11, 192, 22);
		panel.add(comboBox);
		
		lblSolicitado = new JLabel("New label");
		lblSolicitado.setBounds(10, 45, 356, 38);
		panel.add(lblSolicitado);
		
		spCliente = new JScrollPane();
		spCliente.setBounds(10, 87, 356, 112);
		panel.add(spCliente);
		
		modelOrdenes = new DefaultTableModel(null, nombreColumnas);
		tabla = new JTable(modelOrdenes);
		spCliente.setViewportView(tabla);
		
		lblMensaje = new JLabel("New label");
		lblMensaje.setBounds(10, 206, 356, 28);
		panel.add(lblMensaje);
		
		btnTrabajar = new JButton("Pasar a produccion");
		btnTrabajar.setBounds(10, 245, 154, 23);
		panel.add(btnTrabajar);
		
		JLabel lblNewLabel = new JLabel("Receta");
		lblNewLabel.setBounds(10, 15, 78, 14);
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

	public JComboBox getComboBox() {
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
}
