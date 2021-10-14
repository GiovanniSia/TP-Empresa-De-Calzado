package presentacion.vista.fabrica;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	private JLabel lblId;
	private JTextField textId;
	private JLabel lblSucursal;
	private JTextField textProducto;
	private JLabel lblProducto;
	JTextField textSucursal;
	private JButton btnFechaDesde;
	private JButton btnFechaHasta;
	private JLabel lblFechaDesde;
	private JLabel lblFechaHasta;

	private JButton btnSalir;
	

	public ReVentanaVerFabricaciones() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 64, 806, 399);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 69, 776, 272);
		panel.add(spCliente);

		modelOrdenes = new DefaultTableModel(null, nombreColumnas);
		tabla = new JTable(modelOrdenes);

		spCliente.setViewportView(tabla);

		btnSeleccionarProceso = new JButton("Seleccionar proceso");
		btnSeleccionarProceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSeleccionarProceso.setBounds(10, 352, 177, 23);
		panel.add(btnSeleccionarProceso);
		
		lblId = new JLabel("Id");
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		textId = new JTextField();
		textId.setBounds(10, 36, 75, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(95, 11, 46, 14);
		panel.add(lblSucursal);
		
		textSucursal = new JTextField();
		textSucursal.setColumns(10);
		textSucursal.setBounds(95, 36, 75, 20);
		panel.add(textSucursal);
		
		textProducto = new JTextField();
		textProducto.setColumns(10);
		textProducto.setBounds(180, 36, 75, 20);
		panel.add(textProducto);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(180, 11, 46, 14);
		panel.add(lblProducto);
		
		btnFechaDesde = new JButton("Desde");
		btnFechaDesde.setBounds(265, 7, 89, 23);
		panel.add(btnFechaDesde);
		
		btnFechaHasta = new JButton("Hasta");
		btnFechaHasta.setBounds(265, 35, 89, 23);
		panel.add(btnFechaHasta);
		
		lblFechaDesde = new JLabel("-");
		lblFechaDesde.setBounds(364, 11, 75, 14);
		panel.add(lblFechaDesde);
		
		lblFechaHasta = new JLabel("-");
		lblFechaHasta.setBounds(364, 39, 75, 14);
		panel.add(lblFechaHasta);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(740, 351, 46, 38);
		btnSalir.setIcon(setIcono("../imagenes/back.png",btnSalir));
		panel.add(btnSalir);

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

	public Icon setIcono(String url,JButton boton) {
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
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

	public JTextField getTextId() {
		return textId;
	}

	public JTextField getTextProducto() {
		return textProducto;
	}

	public JTextField getTextSucursal() {
		return textSucursal;
	}

	public JButton getBtnFechaDesde() {
		return btnFechaDesde;
	}

	public JButton getBtnFechaHasta() {
		return btnFechaHasta;
	}

	public JLabel getLblFechaDesde() {
		return lblFechaDesde;
	}

	public JLabel getLblFechaHasta() {
		return lblFechaHasta;
	}
	
	public JButton getBtnSalir() {
		return btnSalir;
	}
}
