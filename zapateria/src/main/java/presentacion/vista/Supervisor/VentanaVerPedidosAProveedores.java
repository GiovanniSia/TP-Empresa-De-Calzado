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

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;

public class VentanaVerPedidosAProveedores {

	private JFrame frame;
	
	private JTextField textId;
	private JTextField textProveedor;
	private JTextField textProducto;
	private JTextField textPrecio;
	
	private JTable tablePedidos;
	private DefaultTableModel modelTablaPedidos;
	private String[] nombreColumnasTablaPedidos = {"Id,","Proveedor","Producto","Cantidad","Uni. Medida","Precio de pedido","Estado","Fecha - hora de alta","Fecha - hora de envio","Fecha - hora de cierre"};

	
	private JButton btnSalir;
	private JButton btnConfirmarPedido;
	private JButton btnConfirmarCancelacionDe;
	
	private JComboBox comboBoxEstado;
	
	private JDateChooser dateChooser;
	private JSpinner spinnerHora;
	
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
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 25, 1301, 762);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1287, 725);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 1277, 72);
		panel.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("ZAPATERIA");
		lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
		lblTitulo.setBounds(10, 10, 631, 52);
		panelTitulo.add(lblTitulo);
		
		JLabel lblNewLabel = new JLabel("Pedidos a Proveedores");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 82, 646, 72);
		panel.add(lblNewLabel);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(10, 144, 1267, 471);
		panel.add(panelTabla);
		panelTabla.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(0, 0, 183, 31);
		panelTabla.add(lblNewLabel_1);		
		
		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblId.setBounds(10, 37, 29, 19);
		panelTabla.add(lblId);
				
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblProveedor.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblProveedor.setBounds(56, 37, 79, 19);
		panelTabla.add(lblProveedor);

		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProducto.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblProducto.setBounds(174, 38, 79, 19);
		panelTabla.add(lblProducto);
				
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblPrecio.setBounds(300, 37, 79, 19);
		panelTabla.add(lblPrecio);
				
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblEstado.setBounds(449, 37, 79, 19);
		panelTabla.add(lblEstado);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblFecha.setBounds(626, 38, 79, 19);
		panelTabla.add(lblFecha);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblHora.setBounds(727, 37, 79, 19);
		panelTabla.add(lblHora);
		
		
		
		
		
		
		modelTablaPedidos = new DefaultTableModel(null,this.nombreColumnasTablaPedidos) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}			
		};
		JScrollPane scrollPaneTablaPedidos = new JScrollPane(this.tablePedidos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTablaPedidos.setBounds(10, 81, 1247, 380);
		
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
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(422, 51, 146, 21);
		comboBoxEstado.addItem("Sin seleccionar");
		panelTabla.add(comboBoxEstado);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(965, 51, 146, 21);
		panelTabla.add(comboBox_1);
		
		btnSalir = new JButton("<");
		btnSalir.setBounds(61, 620, 74, 63);
		btnSalir.setIcon(setIcono("../imagenes/back.png",btnSalir));
		panel.add(btnSalir);
		
		btnConfirmarPedido = new JButton("Confirmar Pedido Completado");
		btnConfirmarPedido.setFont(new Font("Consolas", Font.PLAIN, 17));
		btnConfirmarPedido.setBounds(188, 625, 399, 51);
		panel.add(btnConfirmarPedido);
		
		btnConfirmarCancelacionDe = new JButton("Confirmar Cancelacion de Pedido");
		btnConfirmarCancelacionDe.setFont(new Font("Consolas", Font.PLAIN, 17));
		btnConfirmarCancelacionDe.setBounds(648, 625, 399, 51);
		panel.add(btnConfirmarCancelacionDe);
		
		textId = new JTextField();
		textId.setBounds(10, 52, 29, 19);
		panelTabla.add(textId);
		textId.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(290, 52, 96, 19);
		panelTabla.add(textPrecio);
		
		textProducto = new JTextField();
		textProducto.setColumns(10);
		textProducto.setBounds(170, 52, 96, 19);
		panelTabla.add(textProducto);
		
		textProveedor = new JTextField();
		textProveedor.setColumns(10);
		textProveedor.setBounds(56, 52, 96, 19);
		panelTabla.add(textProveedor);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(636, 51, 70, 19);
		panelTabla.add(dateChooser);
		
		spinnerHora = new JSpinner( new SpinnerDateModel());
		JSpinner.DateEditor de_spinnerHora = new JSpinner.DateEditor(spinnerHora, "HH:mm:ss");
		spinnerHora.setEditor(de_spinnerHora);
		spinnerHora.setBounds(727, 52, 70, 19);
//		spinner.setValue(new Date()); // will only show the current time
		panelTabla.add(spinnerHora);
		
		
		
		
	}
	
	public Icon setIcono(String url,JButton boton) {
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?", 
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

	public JComboBox getComboBoxEstado() {
		return comboBoxEstado;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public JSpinner getSpinnerHora() {
		return spinnerHora;
	}

}
