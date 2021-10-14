package presentacion.vista.Cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaVisualizarCarritos {

	private JFrame frame;
	
	private JTable tableCarritos;
	private DefaultTableModel modelTablaCarritos;
	private String[] nombreColumnasCarritos = {"CUIL","Nombre","Hora","Tipo Cliente","Estado","P. Total Venta"};
	private JScrollPane scrollPaneTablaCarritos;
	
	private JButton btnElegirCarrito;
	
	private DefaultTableModel modelTablaDetalle;
	private String[] nombreColumnasDetalle = {"Productos","Cantidad","P. Unitario"};	
	private JTable tableDetalle;
	private JScrollPane scrollPaneDetalle;
	private JLabel lblFiltrarPor;
	private JLabel lblNombre;
	private JLabel lblCUIL;
	private JLabel lblApellido;
	
	private JTextField textNombre;
	private JTextField textCUIL;
	private JTextField textApellido;
	private JButton btnRegresar;

	private JButton btnBorrarCarrito;
	
	private JLabel lblSalir;

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
		frame.setBounds(100, 100, 934, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Zapatería Argento - Realizar Venta");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 928, 592);
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
		modelTablaCarritos = new DefaultTableModel(null, this.nombreColumnasCarritos){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				if(columnas == 6) {
					return true;
				}else {
					return false;
				}
			}
		};
		scrollPaneTablaCarritos = new JScrollPane(this.tableCarritos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTablaCarritos.setBounds(10, 171, 515, 365);
		
		tableCarritos = new JTable(modelTablaCarritos);
		tableCarritos.setBounds(10, 133, 908, 322);	
		this.tableCarritos.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCarritos.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneTablaCarritos.setViewportView(tableCarritos);
		
		panel.add(scrollPaneTablaCarritos);
		//
		
		
		JLabel lblNewLabel = new JLabel("Elegir Carrito a vender");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 34));
		lblNewLabel.setBounds(10, 56, 505, 44);
		panel.add(lblNewLabel);
		
		btnElegirCarrito = new JButton("");
		btnElegirCarrito.setBounds(793, 485, 83, 67);
		btnElegirCarrito.setIcon(setIcono("../imagenes/cashier.png", btnElegirCarrito));
		panel.add(btnElegirCarrito);
		
		
		//tabla detalle
		modelTablaDetalle = new DefaultTableModel(null, this.nombreColumnasDetalle){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				if(columnas == 3) {
					return true;
				}else {
					return false;
				}
			}
		};
		scrollPaneDetalle = new JScrollPane(this.tableDetalle, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneDetalle.setBounds(598, 103, 320, 347);
		
		tableDetalle = new JTable(modelTablaDetalle);
		tableDetalle.setBounds(598, 139, 320, 311);
		this.tableDetalle.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableDetalle.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneDetalle.setViewportView(tableDetalle);
		
		
		
		panel.add(scrollPaneDetalle);
		
		JLabel lblNewLabel_1 = new JLabel("Productos en el Carrito");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(598, 72, 294, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblCobrar = new JLabel("Cobrar Carrito");
		lblCobrar.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		lblCobrar.setBounds(627, 500, 164, 41);
		panel.add(lblCobrar);
		
		lblFiltrarPor = new JLabel("Filtrar Por:");
		lblFiltrarPor.setFont(new Font("Consolas", Font.PLAIN, 10));
		lblFiltrarPor.setBounds(10, 102, 93, 21);
		panel.add(lblFiltrarPor);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Consolas", Font.PLAIN, 10));
		lblNombre.setBounds(113, 122, 93, 13);
		panel.add(lblNombre);
		
		lblCUIL = new JLabel("CUIL");
		lblCUIL.setFont(new Font("Consolas", Font.PLAIN, 10));
		lblCUIL.setBounds(10, 122, 93, 13);
		panel.add(lblCUIL);
		
		
		//TXT
		
		textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textNombre.getText().length()>=15) {
                    e.consume();
                }
			}
		});
		textNombre.setBounds(113, 142, 96, 19);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textCUIL = new JTextField();
		textCUIL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textCUIL.getText().length()>=15) {
                    e.consume();
                }
			}
		});
		textCUIL.setColumns(10);
		textCUIL.setBounds(10, 142, 96, 19);
		panel.add(textCUIL);
		
		textApellido = new JTextField();
		textApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textApellido.getText().length()>=15) {
                    e.consume();
                }
			}
		});
		textApellido.setColumns(10);
		textApellido.setBounds(219, 142, 96, 19);
		panel.add(textApellido);
		
		//
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Consolas", Font.PLAIN, 10));
		lblApellido.setBounds(219, 120, 93, 13);
		panel.add(lblApellido);
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(20, 546, 35, 36);
		btnRegresar.setIcon(setIcono("../imagenes/back.png",btnRegresar));
		panel.add(btnRegresar);
		
		lblSalir = new JLabel("Salir");
		lblSalir.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblSalir.setBounds(65, 561, 52, 21);
		panel.add(lblSalir);
		
		btnBorrarCarrito = new JButton("");
		btnBorrarCarrito.setBounds(480, 546, 35, 36);
		btnBorrarCarrito.setIcon(setIcono("../imagenes/trash.png",btnBorrarCarrito));
		panel.add(btnBorrarCarrito);
		
		JLabel lblCancelarCompra = new JLabel("Cancelar compra");
		lblCancelarCompra.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblCancelarCompra.setBounds(357, 546, 120, 36);
		panel.add(lblCancelarCompra);
		

		
		
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
	
	
	public Icon setIcono(String url,JButton boton) {
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
	}
	
	
	public JFrame getFrame() {
		return frame;
	}

	public JTable getTableCarritos() {
		return tableCarritos;
	}

	public DefaultTableModel getModelTablaCarritos() {
		return modelTablaCarritos;
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
	
	public JTextField getTextNombre() {
		return textNombre;
	}

	public JTextField getTextCUIL() {
		return textCUIL;
	}

	public JTextField getTextApellido() {
		return textApellido;
	}
	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JButton getBtnBorrarCarrito() {
		return btnBorrarCarrito;
	}	
	
}
