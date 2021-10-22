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

public class ReVentanaTrabajarUnPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	JButton btnAvanzarUnPaso;
	JButton btnCancelar;
	
	private String[] nombreColumnas = { "Material", "Cantidad a usar", "Cantidad en stock", "Unidad medida"};
	private JScrollPane spCliente;
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JLabel lblOrden;
	
	JLabel lblMensaje;
	JLabel lblSucursal;
	JLabel lblFechaRequerida;
	JLabel lblIdPedido;
	private JLabel lblPasos;
	JButton btnSalirVentana;

	public ReVentanaTrabajarUnPedido() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 676, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 640, 360);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnAvanzarUnPaso = new JButton("Avanzar un paso");
		btnAvanzarUnPaso.setBounds(10, 160, 154, 23);
		panel.add(btnAvanzarUnPaso);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(476, 160, 154, 23);
		panel.add(btnCancelar);
		
		spCliente = new JScrollPane();
		spCliente.setBounds(10, 229, 620, 120);
		panel.add(spCliente);
		
		modelOrdenes = new DefaultTableModel(null, nombreColumnas);
		tabla = new JTable(modelOrdenes);
		spCliente.setViewportView(tabla);
		
		lblOrden = new JLabel("Se pidio:");
		lblOrden.setBounds(10, 11, 356, 23);
		panel.add(lblOrden);
		
		lblIdPedido = new JLabel("New label");
		lblIdPedido.setBounds(10, 45, 356, 14);
		panel.add(lblIdPedido);
		
		lblFechaRequerida = new JLabel("New label");
		lblFechaRequerida.setBounds(10, 70, 356, 14);
		panel.add(lblFechaRequerida);

		lblSucursal = new JLabel("New label");
		lblSucursal.setBounds(10, 95, 356, 14);
		panel.add(lblSucursal);
		
		lblMensaje = new JLabel("New label");
		lblMensaje.setBounds(10, 205, 356, 14);
		panel.add(lblMensaje);
		
		lblPasos = new JLabel("New label");
		lblPasos.setBounds(10, 120, 356, 14);
		panel.add(lblPasos);
		
		btnSalirVentana = new JButton("Salir");
		btnSalirVentana.setBounds(174, 160, 154, 23);
		panel.add(btnSalirVentana);
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

	public JButton getBtnAvanzarUnPaso() {
		return btnAvanzarUnPaso;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	public JTable getTablaIngredientes() {
		return tabla;
	}

	public DefaultTableModel getModelOrdenes() {
		return modelOrdenes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JLabel getLblOrden() {
		return lblOrden;
	}

	public JLabel getLblMensaje() {
		return lblMensaje;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JLabel getLblFechaRequerida() {
		return lblFechaRequerida;
	}

	public JLabel getLblIdPedido() {
		return lblIdPedido;
	}

	public JLabel getLblPasos() {
		return lblPasos;
	}

	public JButton getBtnSalirVentana() {
		return btnSalirVentana;
	}
}
