package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import persistencia.conexion.Conexion;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class VentanaMenuSistemaDeVentas {

	private JFrame frame;
	private JLabel lblNewLabel;
	
	private JButton btnArmarVenta;
	private JButton btnCobrarVenta;
	private JButton btnCotizaciones;
	private JButton btnEgresoCaja;
	private JButton btnIngresoCaja;
	private JButton btnCierreCaja;
	private JButton btnModPrecioUnitario;
	private JButton btnRegresar;
	private JButton btnRegistrarUnCliente;
	JButton btnVerPedidosA;

	private JButton btnIngresarProductoNuevo;
	private JButton btnVerProveedores;
	private JButton btnGenerarOrdenDe;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel lblNewLabel_5;
	private JButton btnConfig;

//	private JButton btnConfig;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuSistemaDeVentas window = new VentanaMenuSistemaDeVentas();
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
	public VentanaMenuSistemaDeVentas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Zapateria Argento - Sistema de Ventas");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 0, 806, 550);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(0, 0, 808, 50);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("Zapateria Argento");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Oxygen", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 421, 28);
		panel_1.add(lblNewLabel);
		
		lblNewLabel_4 = new JLabel("Sucursal:");
		lblNewLabel_4.setFont(new Font("Oxygen", Font.BOLD, 14));
		lblNewLabel_4.setBounds(565, 22, 109, 14);
		panel_1.add(lblNewLabel_4);
		
		btnArmarVenta = new JButton("Armar Venta");
		btnArmarVenta.setForeground(new Color(51, 102, 153));
		btnArmarVenta.setBackground(new Color(51, 102, 204));
		btnArmarVenta.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnArmarVenta.setBounds(53, 133, 282, 41);
		panel.add(btnArmarVenta);
		
		btnCobrarVenta = new JButton("Cobrar Venta");
		btnCobrarVenta.setForeground(new Color(51, 102, 153));
		btnCobrarVenta.setBackground(new Color(51, 102, 204));
		btnCobrarVenta.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnCobrarVenta.setBounds(53, 221, 282, 41);
		panel.add(btnCobrarVenta);
		
		btnCotizaciones = new JButton("Cotizaciones");
		btnCotizaciones.setForeground(new Color(51, 102, 153));
		btnCotizaciones.setBackground(new Color(51, 102, 204));
		btnCotizaciones.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnCotizaciones.setBounds(449, 133, 282, 41);
		panel.add(btnCotizaciones);
		
		btnEgresoCaja = new JButton("Egreso de Caja");
		btnEgresoCaja.setForeground(new Color(51, 102, 153));
		btnEgresoCaja.setBackground(new Color(51, 102, 204));
		btnEgresoCaja.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnEgresoCaja.setBounds(53, 322, 282, 41);
		panel.add(btnEgresoCaja);
		
		btnIngresoCaja = new JButton("Ingreso de Caja");
		btnIngresoCaja.setForeground(new Color(51, 102, 153));
		btnIngresoCaja.setBackground(new Color(51, 102, 204));
		btnIngresoCaja.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnIngresoCaja.setBounds(53, 273, 282, 41);
		panel.add(btnIngresoCaja);
		
		btnCierreCaja = new JButton("Cierre de Caja");
		btnCierreCaja.setForeground(new Color(51, 102, 153));
		btnCierreCaja.setBackground(new Color(51, 102, 204));
		btnCierreCaja.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnCierreCaja.setBounds(53, 374, 282, 41);
		panel.add(btnCierreCaja);
		

		btnModPrecioUnitario = new JButton("Modificar Producto");
		btnModPrecioUnitario.setForeground(new Color(51, 102, 153));
		btnModPrecioUnitario.setBackground(new Color(51, 102, 204));
		btnModPrecioUnitario.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnModPrecioUnitario.setBounds(449, 303, 282, 41);

		panel.add(btnModPrecioUnitario);
		
		btnRegresar = new JButton("");

		btnRegresar.setBackground(new Color(248, 248, 255));
		btnRegresar.setBounds(53, 469, 55, 50);
		cambiarIconoBotones(btnRegresar,  "back2.png");

		panel.add(btnRegresar);
		
		btnGenerarOrdenDe = new JButton("Orden de Manufactura");
		btnGenerarOrdenDe.setForeground(new Color(51, 102, 153));
		btnGenerarOrdenDe.setBackground(new Color(51, 102, 204));
		btnGenerarOrdenDe.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnGenerarOrdenDe.setBounds(449, 374, 282, 41);
		panel.add(btnGenerarOrdenDe);
		

		btnRegistrarUnCliente = new JButton("Registrar un Cliente");
		btnRegistrarUnCliente.setForeground(new Color(51, 102, 153));
		btnRegistrarUnCliente.setBackground(new Color(51, 102, 204));
		btnRegistrarUnCliente.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnRegistrarUnCliente.setBounds(449, 185, 282, 41);
		panel.add(btnRegistrarUnCliente);
		
		btnIngresarProductoNuevo = new JButton("Ingresar Producto Nuevo");
		btnIngresarProductoNuevo.setForeground(new Color(51, 102, 153));
		btnIngresarProductoNuevo.setBackground(new Color(51, 102, 204));
		btnIngresarProductoNuevo.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnIngresarProductoNuevo.setBounds(449, 254, 282, 41);
		panel.add(btnIngresarProductoNuevo);
		

		btnVerProveedores = new JButton("Ver Proveedores");
		btnVerProveedores.setForeground(new Color(51, 102, 153));
		btnVerProveedores.setBackground(new Color(51, 102, 204));
		btnVerProveedores.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnVerProveedores.setBounds(449, 478, 282, 41);
		panel.add(btnVerProveedores);
		
		btnVerPedidosA = new JButton("Ver Pedidos a Proveedores");
		btnVerPedidosA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerPedidosA.setForeground(new Color(51, 102, 153));
		btnVerPedidosA.setBackground(new Color(51, 102, 204));
		btnVerPedidosA.setFont(new Font("Oxygen", Font.BOLD, 20));
		btnVerPedidosA.setBounds(449, 426, 282, 41);
		panel.add(btnVerPedidosA);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(53, 185, 282, 6);
		panel.add(separator);
		
		lblNewLabel_1 = new JLabel("Vendedor");
		lblNewLabel_1.setFont(new Font("Oxygen", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(53, 108, 55, 14);
		panel.add(lblNewLabel_1);
		

		lblNewLabel_2 = new JLabel("Cajero");
		lblNewLabel_2.setFont(new Font("Oxygen", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(53, 196, 55, 14);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Supervisor / Gerente");
		lblNewLabel_3.setFont(new Font("Oxygen", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(449, 108, 109, 14);
		panel.add(lblNewLabel_3);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(449, 237, 282, 6);
		panel.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(449, 357, 282, 6);
		panel.add(separator_2);
		
		lblNewLabel_5 = new JLabel("Atr\u00E1s");
		lblNewLabel_5.setFont(new Font("Oxygen", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(138, 485, 55, 21);
		panel.add(lblNewLabel_5);
		
		btnConfig = new JButton("");
		btnConfig.setBounds(741, 58, 55, 41);
		panel.add(btnConfig);

	}
	
	
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estas seguro que quieres salir?", 
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
	
	public JButton getBtnArmarVenta() {
		return btnArmarVenta;
	}

	public JButton getBtnCobrarVenta() {
		return btnCobrarVenta;
	}

	public JButton getBtnCotizaciones() {
		return btnCotizaciones;
	}

	public JButton getBtnEgresoCaja() {
		return btnEgresoCaja;
	}

	public JButton getBtnIngresoCaja() {
		return btnIngresoCaja;
	}

	public JButton getBtnCierreCaja() {
		return btnCierreCaja;
	}
	public JButton getBtnModPrecioUnitario() {
		return btnModPrecioUnitario;
	}
	
	public JButton getBtnRegresar() {
		return btnRegresar;
	}
	
	public JButton getBtnGenerarOrdenDe() {
		return btnGenerarOrdenDe;
	}
	
	public JButton getBtnRegistrarUnCliente() {
		return btnRegistrarUnCliente;
	}
	
	public JButton getBtnIngresarProductoNuevo() {
		return btnIngresarProductoNuevo;
	}

	public JButton getBtnVerProveedores() {
		return btnVerProveedores;
	}
	
	public JButton getBtnVerPedidosA() {
		return btnVerPedidosA;
	}


	public JButton getBtnConfig() {
		return btnConfig;
	}


}
