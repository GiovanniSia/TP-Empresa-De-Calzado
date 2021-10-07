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

public class VentanaBuscarOrdenesPendientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Sucursal", "Producto", "Fecha requerido", "Cantidad" };
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JPanel panel_2;
	private JScrollPane spCliente;

	private JButton btnTrabajarPedido;
	private JButton btnVerFabricaciones;

	public VentanaBuscarOrdenesPendientes() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 64, 806, 299);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 11, 776, 228);
		panel.add(spCliente);

		modelOrdenes = new DefaultTableModel(null, nombreColumnas);
		tabla = new JTable(modelOrdenes);

		tabla.getColumnModel().getColumn(0).setPreferredWidth(103);
		tabla.getColumnModel().getColumn(0).setResizable(false);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(1).setResizable(false);

		spCliente.setViewportView(tabla);

		btnTrabajarPedido = new JButton("Seleccionar orden");
		btnTrabajarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTrabajarPedido.setBounds(10, 250, 177, 23);
		panel.add(btnTrabajarPedido);
		
		btnVerFabricaciones = new JButton("Ver fabricaciones en marcha");
		btnVerFabricaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerFabricaciones.setBounds(609, 250, 177, 23);
		panel.add(btnVerFabricaciones);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 806, 10);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Ordenes de fabricacion pendientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 21, 324, 32);
		frame.getContentPane().add(lblNewLabel);
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

	public JTable getTablaOrdenesPendientes() {
		return tabla;
	}

	public DefaultTableModel getModelOrdenes() {
		return modelOrdenes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getBtnTrabajarPedido() {
		return btnTrabajarPedido;
	}
	
	public JButton getBtnVerFabricaciones() {
		return btnVerFabricaciones;
	}
}
