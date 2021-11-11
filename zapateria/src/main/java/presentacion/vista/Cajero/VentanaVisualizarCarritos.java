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
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

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
	private JPanel panelTablas;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblSucursal;
	private JLabel lblEmpleado;
	private JPanel panel_1;



	private JLabel lblNombreEmpleado;



	private JLabel lblNombreSucursal;

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
		frame.setBounds(100, 100, 934, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Zapatería Argento - Realizar Venta");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		


		panelTablas = new JPanel();
		panelTablas.setBackground(new Color(248, 248, 255, 0));
		panelTablas.setBounds(0, 0, 918, 604);
		frame.getContentPane().add(panelTablas);
		

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
		scrollPaneTablaCarritos.setBounds(10, 195, 515, 302);
		
		tableCarritos = new JTable(modelTablaCarritos);
		tableCarritos.setBounds(10, 133, 908, 322);	
		this.tableCarritos.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableCarritos.getColumnModel().getColumn(0).setResizable(false);
		tableCarritos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		this.tableCarritos.getTableHeader().setReorderingAllowed(false);
		panelTablas.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 918, 53);
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panelTablas.add(panel);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);
		
		lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(634, 23, 59, 19);
		panel.add(lblSucursal);
		
		lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEmpleado.setBounds(334, 23, 59, 19);
		panel.add(lblEmpleado);
		
		lblNombreEmpleado = new JLabel("");
		lblNombreEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombreEmpleado.setBounds(400, 23, 165, 19);
		panel.add(lblNombreEmpleado);
		
		lblNombreSucursal = new JLabel("");
		lblNombreSucursal.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombreSucursal.setBounds(701, 23, 165, 19);
		panel.add(lblNombreSucursal);
		scrollPaneTablaCarritos.setViewportView(tableCarritos);
		
		panelTablas.add(scrollPaneTablaCarritos);
		//
		
		
		JLabel lblNewLabel = new JLabel("Elegir Carrito a Vender");
		lblNewLabel.setBounds(10, 64, 505, 42);


		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		panelTablas.add(lblNewLabel);
		
		btnElegirCarrito = new JButton("");
		btnElegirCarrito.setBounds(807, 526, 77, 67);
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
		scrollPaneDetalle.setBounds(535, 117, 368, 380);
		
		tableDetalle = new JTable(modelTablaDetalle);
		tableDetalle.setBounds(598, 139, 320, 311);
		this.tableDetalle.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableDetalle.getColumnModel().getColumn(0).setResizable(false);
		tableDetalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		scrollPaneDetalle.setViewportView(tableDetalle);
		

		
		
		panelTablas.add(scrollPaneDetalle);
		
		JLabel lblNewLabel_1 = new JLabel("Productos en el Carrito");
		lblNewLabel_1.setBounds(535, 64, 294, 42);

		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panelTablas.add(lblNewLabel_1);
		
		JLabel lblCobrar = new JLabel("Cobrar Carrito");
		lblCobrar.setBounds(646, 526, 164, 67);
		lblCobrar.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panelTablas.add(lblCobrar);
		
		lblFiltrarPor = new JLabel("Filtrar Por:");
		lblFiltrarPor.setBounds(10, 118, 93, 21);
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTablas.add(lblFiltrarPor);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(113, 140, 93, 21);
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTablas.add(lblNombre);
		
		lblCUIL = new JLabel("CUIL");
		lblCUIL.setBounds(10, 140, 93, 21);
		lblCUIL.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTablas.add(lblCUIL);

		
		//TXT
		
		textNombre = new JTextField();
		textNombre.setBounds(113, 164, 96, 19);
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textNombre.getText().length()>=15) {
                    e.consume();
                }
			}
		});
		panelTablas.add(textNombre);


		textNombre.setColumns(10);
		
		textCUIL = new JTextField();
		textCUIL.setBounds(10, 164, 96, 19);
		textCUIL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textCUIL.getText().length()>=15) {
                    e.consume();
                }
			}
		});
		textCUIL.setColumns(10);
		panelTablas.add(textCUIL);


		
		textApellido = new JTextField();
		textApellido.setBounds(219, 164, 96, 19);
		textApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textApellido.getText().length()>=15) {
                    e.consume();
                }
			}
		});
		textApellido.setColumns(10);
		panelTablas.add(textApellido);

		//
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(219, 140, 93, 23);


		

		lblApellido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTablas.add(lblApellido);
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(41, 526, 68, 67);
		cambiarIconoBotones(btnRegresar,  "back2.png");
		panelTablas.add(btnRegresar);
		
		lblSalir = new JLabel("Atras");
		lblSalir.setBounds(119, 526, 52, 67);
		lblSalir.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panelTablas.add(lblSalir);
		
		btnBorrarCarrito = new JButton("");
		btnBorrarCarrito.setBounds(454, 526, 52, 67);
		cambiarIconoBotones(btnBorrarCarrito,  "trash2.png");
		panelTablas.add(btnBorrarCarrito);
		
		JLabel lblCancelarCompra = new JLabel("Cancelar compra");
		lblCancelarCompra.setBounds(277, 526, 167, 67);
		lblCancelarCompra.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panelTablas.add(lblCancelarCompra);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 110, 918, 399);
		panel_1.setBackground(new Color(255, 255, 255, 180));
		panelTablas.add(panel_1);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 24, 960, 720);
		cambiarIconoLabel(lblFondo, "fondo.png");
		frame.getContentPane().add(lblFondo);
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
	
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
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

	public JScrollPane getScrollPaneDetalle() {
		return scrollPaneDetalle;
	}

	public JLabel getLblFiltrarPor() {
		return lblFiltrarPor;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblCUIL() {
		return lblCUIL;
	}

	public JLabel getLblApellido() {
		return lblApellido;
	}

	public JLabel getLblSalir() {
		return lblSalir;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public JLabel getLblNombreEmpleado() {
		return lblNombreEmpleado;
	}

	public JLabel getLblNombreSucursal() {
		return lblNombreSucursal;
	}	
	
	
	
}
