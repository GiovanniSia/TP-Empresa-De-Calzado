package presentacion.vista.fabrica;

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
import com.toedter.calendar.JDateChooser;

public class VentanaVerHistorialPasos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Nro orden","Sucursal", "Producto", "Cantidad", "Accion", "Fecha y hora","Empleado"};
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JPanel panel_2;
	private JScrollPane spCliente;

	private JButton btnVerDescripcion;
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JTextField textId;
	private JLabel lblSucursal;
	private JTextField textProducto;
	private JLabel lblProducto;
	JTextField textSucursal;

	private JButton btnSalir;
	private JTextField textAccion;
	private JTextField textEmpleado;
	
	JDateChooser fechaHasta;
	JDateChooser fechaDesde;

	public VentanaVerHistorialPasos() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 64, 974, 399);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 69, 954, 272);
		panel.add(spCliente);

		modelOrdenes = new DefaultTableModel(null, nombreColumnas){
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int filas, int columnas) {
            	/*
                if(columnas == 3) {
                    return true;
                }else {*/
                    return false;
                //}
            }
        };
		tabla = new JTable(modelOrdenes);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(1000);
		tabla.getColumnModel().getColumn(0).setResizable(false);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(1).setResizable(false);
		
		spCliente.setViewportView(tabla);

		btnVerDescripcion = new JButton("Ver descripcion");
		btnVerDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerDescripcion.setBounds(10, 352, 177, 23);
		panel.add(btnVerDescripcion);
		
		lblId = new JLabel("Id");
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		textId = new JTextField();
		textId.setBounds(10, 36, 75, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(95, 11, 46, 14);
		panel.add(lblSucursal);
		
		textSucursal = new JTextField();
		textSucursal.setColumns(10);
		textSucursal.setBounds(95, 36, 75, 20);
		panel.add(textSucursal);
		
		textProducto = new JTextField();
		textProducto.setColumns(10);
		textProducto.setBounds(180, 36, 75, 20);
		panel.add(textProducto);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(180, 11, 46, 14);
		panel.add(lblProducto);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(740, 351, 46, 38);
		cambiarIconoBotones(btnSalir,  "back.png");
		panel.add(btnSalir);
		
		textAccion = new JTextField();
		textAccion.setColumns(10);
		textAccion.setBounds(265, 36, 136, 20);
		panel.add(textAccion);
		
		JLabel lblPaso = new JLabel("Accion");
		lblPaso.setBounds(265, 11, 46, 14);
		panel.add(lblPaso);
		
		textEmpleado = new JTextField();
		textEmpleado.setColumns(10);
		textEmpleado.setBounds(828, 38, 136, 20);
		panel.add(textEmpleado);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(828, 11, 75, 14);
		panel.add(lblEmpleado);

		fechaDesde = new JDateChooser();
		fechaDesde.setBounds(411, 36, 108, 19);
		panel.add(fechaDesde);
		
		fechaHasta = new JDateChooser();
		fechaHasta.setBounds(529, 36, 108, 19);
		panel.add(fechaHasta);
		
		JLabel lblFechaDesde = new JLabel("Fecha desde");
		lblFechaDesde.setBounds(411, 11, 108, 14);
		panel.add(lblFechaDesde);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setBounds(529, 11, 108, 14);
		panel.add(lblFechaHasta);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 806, 10);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Historial de fabricacion");
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

	public JButton getBtnVerDescripcion() {
		return btnVerDescripcion;
	}

	public JTextField getTextId() {
		return textId;
	}

	public JTextField getTextProducto() {
		return textProducto;
	}

	public JTextField getTextSucursal() {
		return textSucursal;
	}
	
	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JTextField getTextAccion() {
		return textAccion;
	}

	public JTextField getTextEmpleado() {
		return textEmpleado;
	}
	
	public JDateChooser getFechaDesde() {
		return fechaDesde;
	}

	public JDateChooser getFechaHasta() {
		return fechaHasta;
	}
}
