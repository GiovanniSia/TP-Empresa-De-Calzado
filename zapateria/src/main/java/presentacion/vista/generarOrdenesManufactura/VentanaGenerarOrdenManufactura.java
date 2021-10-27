package presentacion.vista.generarOrdenesManufactura;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

import persistencia.conexion.Conexion;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class VentanaGenerarOrdenManufactura extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Id producto","Talle", "Descripcion", "Cantidad en stock"};
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JScrollPane spCliente;

	private JButton btnGenerarPedido;
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JTextField textId;
	private JLabel lblSucursal;
	private JTextField textProducto;
	private JLabel lblProducto;
	JTextField textTalle;

	private JButton btnSalir;
	
	JSpinner spinnerCantidad;
	private JPanel panel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblFiltrarPor;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

	public VentanaGenerarOrdenManufactura() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 822, 571);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 100, 806, 432);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 81, 776, 272);
		panel.add(spCliente);

		modelOrdenes = new DefaultTableModel(null, this.nombreColumnas){
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int filas, int columnas) {
            	/*
                if(columnas == 3) {
                    return true;
                }else {
                    return false;
                }*/
            	return false;
            }
        };
        tabla = new JTable(modelOrdenes);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		spCliente.setViewportView(tabla);

		btnGenerarPedido = new JButton("");
		btnGenerarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGenerarPedido.setBounds(511, 361, 60, 60);
		cambiarIconoBotones(btnGenerarPedido,  "plus.png");
		panel.add(btnGenerarPedido);
		
		lblId = new JLabel("id");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblId.setBounds(10, 34, 46, 14);
		panel.add(lblId);
		
		textId = new JTextField();
		textId.setBounds(10, 48, 75, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		lblSucursal = new JLabel("Talle");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSucursal.setBounds(180, 34, 46, 14);
		panel.add(lblSucursal);
		
		textTalle = new JTextField();
		textTalle.setColumns(10);
		textTalle.setBounds(180, 50, 75, 20);
		panel.add(textTalle);
		
		textProducto = new JTextField();
		textProducto.setColumns(10);
		textProducto.setBounds(95, 50, 75, 20);
		panel.add(textProducto);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProducto.setBounds(95, 34, 57, 14);
		panel.add(lblProducto);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(118, 361, 60, 60);
		cambiarIconoBotones(btnSalir,  "back2.png");
		panel.add(btnSalir);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad Deseada");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(382, 361, 119, 23);
		panel.add(lblNewLabel_1);
		
		spinnerCantidad = new JSpinner();
		spinnerCantidad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		spinnerCantidad.setBounds(416, 385, 60, 36);
		panel.add(spinnerCantidad);
		
		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 0, 75, 25);
		panel.add(lblFiltrarPor);
		
		lblNewLabel_3 = new JLabel("Generar Orden");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(580, 361, 163, 60);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Atras");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(190, 361, 163, 60);
		panel.add(lblNewLabel_4);
		
		lblNewLabel = new JLabel("Generar orden de manufactura");
		lblNewLabel.setBounds(10, 48, 324, 50);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(0, 0, 806, 50);
		frame.getContentPane().add(panel_1);
		
		lblNewLabel_2 = new JLabel("Zapateria Argento");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_2.setBounds(10, 0, 421, 50);
		panel_1.add(lblNewLabel_2);
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

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
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

	public JButton getBtnGenerarPedido() {
		return btnGenerarPedido;
	}

	public JTextField getTextId() {
		return textId;
	}

	public JTextField getTextProducto() {
		return textProducto;
	}

	public JTextField getTextTalle() {
		return textTalle;
	}
	
	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JSpinner getSpinnerCantidad() {
		return spinnerCantidad;
	}
	
	
	
	
}
