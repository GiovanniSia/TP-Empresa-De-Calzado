package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class vistaBusquedaProductos {

	private JFrame frame;

	private JTextField txtNombreProducto;
	private JTextField txtIdSucursal;
	private JButton btnBuscar; 
	
	private JTable table;
	private DefaultTableModel modelTabla;
	private String[] nombreColumnas = { "Nombre", "Codigo Lote", "Id Sucursal", " Stock Disponible","Tipo", "Fabricado", "Costo Producción", "Precio Venta", "Punto de Reposición"};

	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaBusquedaProductos window = new vistaBusquedaProductos();
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
	public vistaBusquedaProductos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 953, 654);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombreProducto = new JLabel("Nombre de Producto");
		lblNombreProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreProducto.setBounds(10, 10, 159, 23);
		frame.getContentPane().add(lblNombreProducto);
		
		txtNombreProducto = new JTextField();
		txtNombreProducto.setBounds(179, 10, 217, 27);
		frame.getContentPane().add(txtNombreProducto);
		txtNombreProducto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id Sucursal");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(406, 10, 130, 20);
		frame.getContentPane().add(lblNewLabel);
		
		txtIdSucursal = new JTextField();
		txtIdSucursal.setColumns(10);
		txtIdSucursal.setBounds(496, 10, 217, 27);
		frame.getContentPane().add(txtIdSucursal);
		
		scrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 74, 919, 533);
		frame.getContentPane().add(scrollPane);
		
		modelTabla = new DefaultTableModel(null, nombreColumnas);
		table = new JTable(modelTabla);
		
		this.table.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.table.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPane.setViewportView(table);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(733, 10, 125, 23);
		frame.getContentPane().add(btnBuscar);
		/*
		table.setBounds(10, 74, 768, 533);
		frame.getContentPane().add(table);
		*/

		
		
		/*
		 JScrollPane scrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(224, 10, 202, 243);
		frame.getContentPane().add(scrollPane);
		
		modelTabla = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(modelTabla);
		
		this.table.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.table.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPane.setViewportView(table);
		 
		 */
		
		
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
	
	
	public JTextField getTxtNombreProducto() {
		return txtNombreProducto;
	}
	public JTextField getTxtIdSucursal() {
		return txtIdSucursal;
	}
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	public JTable getTable() {
		return table;
	}
	public DefaultTableModel getModelTabla() {
		return modelTabla;
	}
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
}
