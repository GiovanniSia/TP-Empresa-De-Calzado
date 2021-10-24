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
	private JPanel panel_2;
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

	public VentanaGenerarOrdenManufactura() {
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

		spCliente.setViewportView(tabla);

		btnGenerarPedido = new JButton("Generar pedido");
		btnGenerarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGenerarPedido.setBounds(212, 352, 177, 23);
		panel.add(btnGenerarPedido);
		
		lblId = new JLabel("Id");
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		textId = new JTextField();
		textId.setBounds(10, 36, 75, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		lblSucursal = new JLabel("Talle");
		lblSucursal.setBounds(180, 11, 46, 14);
		panel.add(lblSucursal);
		
		textTalle = new JTextField();
		textTalle.setColumns(10);
		textTalle.setBounds(180, 38, 75, 20);
		panel.add(textTalle);
		
		textProducto = new JTextField();
		textProducto.setColumns(10);
		textProducto.setBounds(95, 38, 75, 20);
		panel.add(textProducto);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(95, 11, 46, 14);
		panel.add(lblProducto);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(740, 351, 46, 38);
		cambiarIconoBotones(btnSalir,  "back.png");
		panel.add(btnSalir);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad deseada");
		lblNewLabel_1.setBounds(20, 352, 104, 23);
		panel.add(lblNewLabel_1);
		
		spinnerCantidad = new JSpinner();
		spinnerCantidad.setBounds(134, 352, 68, 23);
		panel.add(spinnerCantidad);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 806, 10);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Generar orden de manufactura");
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

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_DEFAULT));
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
