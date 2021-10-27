package presentacion.vista.Cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private JPanel panel;
	private JPanel panelTablas;
	private JLabel lblNewLabel_2;

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
		frame.setLocationRelativeTo(null);
		


		panelTablas = new JPanel();
		panelTablas.setBackground(new Color(248, 248, 255));
		panelTablas.setBounds(0, 0, 918, 592);
		frame.getContentPane().add(panelTablas);
		panelTablas.setLayout(null);
		

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
		scrollPaneTablaCarritos.setBounds(10, 172, 515, 302);
		
		tableCarritos = new JTable(modelTablaCarritos);
		tableCarritos.setBounds(10, 133, 908, 322);	
		this.tableCarritos.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCarritos.getColumnModel().getColumn(0).setResizable(false);
		tableCarritos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		scrollPaneTablaCarritos.setViewportView(tableCarritos);
		
		panelTablas.add(scrollPaneTablaCarritos);
		//
		
		
		JLabel lblNewLabel = new JLabel("Elegir Carrito a Vender");


		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 48, 505, 42);
		panelTablas.add(lblNewLabel);
		
		btnElegirCarrito = new JButton("");
		btnElegirCarrito.setBounds(826, 485, 77, 67);
		cambiarIconoBotones(btnElegirCarrito,  "cobrar.png");
		panelTablas.add(btnElegirCarrito);


		
		
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
		scrollPaneDetalle.setBounds(535, 94, 368, 380);
		
		tableDetalle = new JTable(modelTablaDetalle);
		tableDetalle.setBounds(598, 139, 320, 311);
		this.tableDetalle.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableDetalle.getColumnModel().getColumn(0).setResizable(false);
		tableDetalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		scrollPaneDetalle.setViewportView(tableDetalle);
		

		
		
		panelTablas.add(scrollPaneDetalle);
		
		JLabel lblNewLabel_1 = new JLabel("Productos en el Carrito");

		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(535, 48, 294, 42);
		panelTablas.add(lblNewLabel_1);
		
		JLabel lblCobrar = new JLabel("Cobrar Carrito");
		lblCobrar.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblCobrar.setBounds(665, 485, 164, 67);
		panelTablas.add(lblCobrar);
		
		lblFiltrarPor = new JLabel("Filtrar Por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 95, 93, 21);
		panelTablas.add(lblFiltrarPor);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombre.setBounds(113, 117, 93, 21);
		panelTablas.add(lblNombre);
		
		lblCUIL = new JLabel("CUIL");
		lblCUIL.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCUIL.setBounds(10, 117, 93, 21);
		panelTablas.add(lblCUIL);

		
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


		textNombre.setBounds(113, 141, 96, 19);
		panelTablas.add(textNombre);


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


		textCUIL.setBounds(10, 141, 96, 19);
		panelTablas.add(textCUIL);


		
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


		textApellido.setBounds(219, 141, 96, 19);
		panelTablas.add(textApellido);

		//
		
		lblApellido = new JLabel("Apellido");


		

		lblApellido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblApellido.setBounds(219, 115, 93, 23);
		panelTablas.add(lblApellido);
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(60, 485, 68, 67);
		cambiarIconoBotones(btnRegresar,  "back2.png");
		panelTablas.add(btnRegresar);
		
		lblSalir = new JLabel("Atras");
		lblSalir.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblSalir.setBounds(138, 485, 52, 67);
		panelTablas.add(lblSalir);
		
		btnBorrarCarrito = new JButton("");
		btnBorrarCarrito.setBounds(473, 485, 52, 67);
		cambiarIconoBotones(btnBorrarCarrito,  "trash2.png");
		panelTablas.add(btnBorrarCarrito);
		
		JLabel lblCancelarCompra = new JLabel("Cancelar compra");
		lblCancelarCompra.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblCancelarCompra.setBounds(296, 485, 167, 67);
		panelTablas.add(lblCancelarCompra);

		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 918, 50);
		panelTablas.add(panel);
		
		lblNewLabel_2 = new JLabel("Zapateria Argento");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_2.setBounds(10, 0, 421, 50);
		panel.add(lblNewLabel_2);

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
	
	


	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);

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
	
	public JPanel getPanelTablas() {
		return panelTablas;
	}	
}
