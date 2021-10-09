package presentacion.vista.fabrica;

import java.awt.Font;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReVentanaVerFabricaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Id pedido","Sucursal", "Producto", "Fecha requerido", "Cantidad", "Paso actual", "Estado", "Fecha completada", "Dias envio"};
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JPanel panel_2;
	private JScrollPane spCliente;

	private JButton btnSeleccionarProceso;
	private JLabel lblNewLabel;

	public ReVentanaVerFabricaciones() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 64, 806, 314);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 11, 776, 258);
		panel.add(spCliente);

		modelOrdenes = new DefaultTableModel(null, nombreColumnas);
		tabla = new JTable(modelOrdenes);

		spCliente.setViewportView(tabla);

		btnSeleccionarProceso = new JButton("Seleccionar proceso");
		btnSeleccionarProceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSeleccionarProceso.setBounds(10, 280, 177, 23);
		panel.add(btnSeleccionarProceso);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 806, 10);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Ordenes de fabricacion pendientes");
		lblNewLabel.setBounds(10, 21, 324, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
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

	public JTable getTablaFabricacionesEnMarcha() {
		return tabla;
	}

	public DefaultTableModel getModelOrdenes() {
		return modelOrdenes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getBtnTrabajarPedido() {
		return btnSeleccionarProceso;
	}
}
