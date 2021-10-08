package presentacion.vista.Cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JButton;

public class VentanaVisualizarCarritos {

	private JFrame frame;
	
	private JTable tableCarritos;
	private DefaultTableModel modelTablaSucursales;
	private String[] nombreColumnasCarritos = {"ID Carrito","Hora","Cod Cliente","Nombre Cliente","Tipo Cliente","P. Total Venta"};
	private JScrollPane scrollPaneTablaCarritos;
	
	private JButton btnElegirCarrito;
	
	private DefaultTableModel modelTablaDetalle;
	private String[] nombreColumnasDetalle = {"Productos","Cantidad","P. Unitario"};	
	private JTable tableDetalle;
	private JScrollPane scrollPaneDetalle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVisualizarCarritos window = new VentanaVisualizarCarritos();
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
	public VentanaVisualizarCarritos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
      }
		
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setForeground(Color.BLACK);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 934, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Zapatería Argento - Realizar Venta");
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 928, 503);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 10, 909, 41);
		panel.add(panel_1);
		
		JLabel lblTitulo = new JLabel("Zapater\u00EDa");
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTitulo.setBounds(10, 10, 310, 21);
		panel_1.add(lblTitulo);
		
		//tabla
		modelTablaSucursales = new DefaultTableModel(null, this.nombreColumnasCarritos);
		scrollPaneTablaCarritos = new JScrollPane(this.tableCarritos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTablaCarritos.setBounds(10, 133, 515, 322);
		
		tableCarritos = new JTable(modelTablaSucursales);
		tableCarritos.setBounds(10, 133, 908, 322);	
		this.tableCarritos.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCarritos.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneTablaCarritos.setViewportView(tableCarritos);
		
		panel.add(scrollPaneTablaCarritos);
		//
		
		
		JLabel lblNewLabel = new JLabel("Elegir Carrito a vender");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 34));
		lblNewLabel.setBounds(10, 79, 505, 44);
		panel.add(lblNewLabel);
		
		btnElegirCarrito = new JButton("Elegir Carrito");
		btnElegirCarrito.setBounds(244, 465, 363, 28);
		panel.add(btnElegirCarrito);
		
		
		//tabla detalle
		modelTablaDetalle = new DefaultTableModel(null, this.nombreColumnasDetalle);
		scrollPaneDetalle = new JScrollPane(this.tableDetalle, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneDetalle.setBounds(598, 137, 320, 313);
		
		tableDetalle = new JTable(modelTablaDetalle);
		tableDetalle.setBounds(598, 139, 320, 311);
		this.tableDetalle.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableDetalle.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneDetalle.setViewportView(tableDetalle);
		
		
		
		panel.add(scrollPaneDetalle);
		
		JLabel lblNewLabel_1 = new JLabel("Productos en el Carrito");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(601, 102, 294, 21);
		panel.add(lblNewLabel_1);
		
		
		/*
		 modelTablaSucursales = new DefaultTableModel(null, this.nombreColumnasCarritos);
		scrollPaneTablaCarritos = new JScrollPane(this.tableCarritos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTablaCarritos.setBounds(10, 133, 515, 322);
		
		tableCarritos = new JTable(modelTablaSucursales);
		tableCarritos.setBounds(10, 133, 908, 322);	
		this.tableCarritos.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCarritos.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneTablaCarritos.setViewportView(tableCarritos);
		
		panel.add(scrollPaneTablaCarritos); 
		 **/
		
		//

		

	}
	
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?. Se perderá todos los progresos que se hayan realizado", 
		             "Advertencia", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
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
	
	
	
	
	
	public JFrame getFrame() {
		return frame;
	}

	public JTable getTableCarritos() {
		return tableCarritos;
	}

	public DefaultTableModel getModelTablaSucursales() {
		return modelTablaSucursales;
	}

	public String[] getNombreColumnasCarritos() {
		return nombreColumnasCarritos;
	}

	public JScrollPane getScrollPaneTablaCarritos() {
		return scrollPaneTablaCarritos;
	}

	public JButton getBtnElegirCarrito() {
		return btnElegirCarrito;
	}
	
	public DefaultTableModel getModelTablaDetalle() {
		return modelTablaDetalle;
	}

	public String[] getNombreColumnasDetalle() {
		return nombreColumnasDetalle;
	}

	public JTable getTableDetalle() {
		return tableDetalle;
	}
}
