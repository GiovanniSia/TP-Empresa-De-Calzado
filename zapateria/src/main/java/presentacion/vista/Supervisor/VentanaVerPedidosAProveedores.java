package presentacion.vista.Supervisor;

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
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.border.MatteBorder;

public class VentanaVerPedidosAProveedores {

	private JFrame frame;
	
	private JTextField textId;
	private JTextField textProveedor;
	private JTextField textProducto;
	private JTextField textPrecio;
	
	private JTable tablePedidos;
	private DefaultTableModel modelTablaPedidos;
	private String[] nombreColumnasTablaPedidos = {"Id","Proveedor","Producto","Cantidad","Uni. Medida","Precio de pedido","Estado","Fecha - hora de alta","Fecha - hora de envio","Fecha - hora de cierre"};

	
	private JButton btnSalir;
	private JButton btnConfirmarPedido;
	private JButton btnConfirmarCancelacionDe;
	
	private JComboBox<String> comboBoxEstado;
	
	private JDateChooser dateChooserDesde;
	private JSpinner spinnerHoraDesde;
	
	private JDateChooser dateChooserHasta;

	private JSpinner spinnerHoraHasta;
	
	private JComboBox<String> comboBoxEstadoSolo;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JPanel panel_1;
	private JLabel lblLogo;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_6;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerPedidosAProveedores window = new VentanaVerPedidosAProveedores();
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
	public VentanaVerPedidosAProveedores() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 30, 1186, 573);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 0));
		panel.setBounds(0, 0, 1169, 539);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pedidos a Proveedores");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 50, 646, 51);
		panel.add(lblNewLabel);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBackground(new Color(255, 255, 255, 180));
		panelTabla.setBounds(0, 98, 1169, 354);
		panel.add(panelTabla);
		panelTabla.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(20, 0, 183, 31);
		panelTabla.add(lblNewLabel_1);		
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblId.setBounds(20, 32, 29, 19);
		panelTabla.add(lblId);
				
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblProveedor.setBounds(66, 32, 79, 19);
		panelTabla.add(lblProveedor);

		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblProducto.setBounds(180, 32, 79, 19);
		panelTabla.add(lblProducto);
				
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPrecio.setBounds(300, 32, 79, 19);
		panelTabla.add(lblPrecio);
				
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEstado.setBounds(432, 32, 79, 19);
		panelTabla.add(lblEstado);
		
		JLabel lblFechaDesde = new JLabel("Desde");
		lblFechaDesde.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDesde.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblFechaDesde.setBounds(691, 32, 79, 19);
		panelTabla.add(lblFechaDesde);
		
		
		
		
		
		
		modelTablaPedidos = new DefaultTableModel(null,this.nombreColumnasTablaPedidos) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}			
		};
		JScrollPane scrollPaneTablaPedidos = new JScrollPane(this.tablePedidos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTablaPedidos.setBounds(10, 81, 1149, 266);
		
		tablePedidos = new JTable(modelTablaPedidos);
		tablePedidos.setBounds(10, 81, 1247, 380);
		tablePedidos.getTableHeader().setReorderingAllowed(false);
		scrollPaneTablaPedidos.setViewportView(tablePedidos);
		panelTabla.add(scrollPaneTablaPedidos);
		
		
		
		
		/*
				modelTablaProdDeProv = new DefaultTableModel(null,this.nombreColumnasProdDeProv){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};
		JScrollPane scrollPaneProdDeProv = new JScrollPane(this.tableProdDeProv,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneProdDeProv.setBounds(10, 495, 1116, 208);
		
		tableProdDeProv = new JTable(modelTablaProdDeProv);
		tableProdDeProv.setBounds(10, 252, 1116, 208);
		tableProdDeProv.getTableHeader().setReorderingAllowed(false) ;
		scrollPaneProdDeProv.setViewportView(tableProdDeProv);
		frame.getContentPane().add(scrollPaneProdDeProv);
		*/
		comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxEstado.setBounds(432, 51, 146, 21);
		comboBoxEstado.addItem("Sin seleccionar");
		panelTabla.add(comboBoxEstado);
		
		comboBoxEstadoSolo = new JComboBox<String>();
		comboBoxEstadoSolo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxEstadoSolo.setBounds(1075, 50, 79, 21);
		comboBoxEstadoSolo.addItem("Sin seleccionar");
		panelTabla.add(comboBoxEstadoSolo);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(155, 463, 60, 60);
		cambiarIconoBotones(btnSalir,  "back2.png");
		panel.add(btnSalir);
		
		btnConfirmarPedido = new JButton("");
		btnConfirmarPedido.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnConfirmarPedido.setBounds(392, 463, 60, 60);
		cambiarIconoBotones(btnConfirmarPedido,  "check2.png");
		panel.add(btnConfirmarPedido);
		
		btnConfirmarCancelacionDe = new JButton("");
		btnConfirmarCancelacionDe.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnConfirmarCancelacionDe.setBounds(719, 463, 60, 60);
		cambiarIconoBotones(btnConfirmarCancelacionDe,  "cancel2.png");
		panel.add(btnConfirmarCancelacionDe);
		
		textId = new JTextField();
		textId.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textId.setBounds(20, 52, 29, 19);
		panelTabla.add(textId);
		textId.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textPrecio.setColumns(10);
		textPrecio.setBounds(300, 52, 96, 19);
		panelTabla.add(textPrecio);
		
		textProducto = new JTextField();
		textProducto.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textProducto.setColumns(10);
		textProducto.setBounds(180, 52, 96, 19);
		panelTabla.add(textProducto);
		
		textProveedor = new JTextField();
		textProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textProveedor.setColumns(10);
		textProveedor.setBounds(66, 52, 96, 19);
		panelTabla.add(textProveedor);
				
		JLabel lblFechaHasta = new JLabel("Hasta");
		lblFechaHasta.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaHasta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblFechaHasta.setBounds(925, 32, 79, 19);
		panelTabla.add(lblFechaHasta);
		
		
		dateChooserDesde = new JDateChooser();
		dateChooserDesde.setBounds(628, 52, 122, 19);
		panelTabla.add(dateChooserDesde);
		
		dateChooserHasta = new JDateChooser();
		dateChooserHasta.setBounds(863, 51, 122, 19);
		panelTabla.add(dateChooserHasta);
		
		
		
		spinnerHoraDesde = new JSpinner( new SpinnerDateModel());
		spinnerHoraDesde.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		JSpinner.DateEditor de_spinnerHoraDesde = new JSpinner.DateEditor(spinnerHoraDesde, "HH:mm");
		spinnerHoraDesde.setEditor(de_spinnerHoraDesde);
		spinnerHoraDesde.setBounds(760, 52, 70, 19);
		
		spinnerHoraDesde.setValue(new Date(0, 0,0, 00, 0, 0));
//		spinner.setValue(new Date()); // will only show the current time
		panelTabla.add(spinnerHoraDesde);
		
		
		spinnerHoraHasta = new JSpinner(new SpinnerDateModel());
		spinnerHoraHasta.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		JSpinner.DateEditor de_spinnerHoraHasta = new JSpinner.DateEditor(spinnerHoraHasta, "HH:mm");
		spinnerHoraHasta.setEditor(de_spinnerHoraHasta);
		spinnerHoraHasta.setBounds(995, 52, 70, 19);
		spinnerHoraHasta.setValue(new Date(0, 0,0, 00, 0, 0));
		panelTabla.add(spinnerHoraHasta);
		
		lblNewLabel_3 = new JLabel("Confirmar Pedido Completado");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(462, 463, 212, 60);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Confirmar Cancelacion de Pedido");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(789, 463, 231, 60);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Atras");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(225, 463, 60, 60);
		panel.add(lblNewLabel_5);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(0, 0, 1169, 53);
		panel.add(panel_1);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel_1.add(lblLogo);
		
		lblNewLabel_2 = new JLabel("Sucursal:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(876, 28, 59, 19);
		panel_1.add(lblNewLabel_2);
		
		lblNewLabel_6 = new JLabel("Empleado:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(645, 28, 59, 19);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(1, 0, 1280, 960);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
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
	
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextId() {
		return textId;
	}

	public JTextField getTextProveedor() {
		return textProveedor;
	}

	public JTextField getTextProducto() {
		return textProducto;
	}

	public JTextField getTextPrecio() {
		return textPrecio;
	}

	public JTable getTablePedidos() {
		return tablePedidos;
	}

	public DefaultTableModel getModelTablaPedidos() {
		return modelTablaPedidos;
	}

	public String[] getNombreColumnasTablaPedidos() {
		return nombreColumnasTablaPedidos;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JButton getBtnConfirmarPedido() {
		return btnConfirmarPedido;
	}

	public JButton getBtnConfirmarCancelacionDe() {
		return btnConfirmarCancelacionDe;
	}

	public JComboBox<String> getComboBoxEstado() {
		return comboBoxEstado;
	}

	public JDateChooser getDateChooserDesde() {
		return dateChooserDesde;
	}

	public JSpinner getSpinnerHoraDesde() {
		return spinnerHoraDesde;
	}

	public JDateChooser getDateChooserHasta() {
		return dateChooserHasta;
	}

	public JSpinner getSpinnerHoraHasta() {
		return spinnerHoraHasta;
	}


	
	public JComboBox<String> getComboBoxEstadoSolo() {
		return comboBoxEstadoSolo;
	}
}
