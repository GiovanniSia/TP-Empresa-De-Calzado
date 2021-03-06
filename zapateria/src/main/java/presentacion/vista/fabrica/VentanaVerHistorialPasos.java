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

import persistencia.conexion.Conexion;

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
	private JLabel lblAtras;
	private JLabel lblVerDetalleDe;

	public VentanaVerHistorialPasos() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 1000, 537);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 64, 984, 355);
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
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		spCliente.setViewportView(tabla);
		
		lblId = new JLabel("Id");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		textId = new JTextField();
		textId.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textId.setBounds(10, 36, 75, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		lblSucursal = new JLabel("Sucursal");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(95, 11, 46, 14);
		panel.add(lblSucursal);
		
		textSucursal = new JTextField();
		textSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textSucursal.setColumns(10);
		textSucursal.setBounds(95, 36, 75, 20);
		panel.add(textSucursal);
		
		textProducto = new JTextField();
		textProducto.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textProducto.setColumns(10);
		textProducto.setBounds(180, 36, 75, 20);
		panel.add(textProducto);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProducto.setBounds(180, 11, 60, 14);
		panel.add(lblProducto);
		
		textAccion = new JTextField();
		textAccion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textAccion.setColumns(10);
		textAccion.setBounds(265, 36, 136, 20);
		panel.add(textAccion);
		
		JLabel lblPaso = new JLabel("Accion");
		lblPaso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPaso.setBounds(265, 11, 46, 14);
		panel.add(lblPaso);
		
		textEmpleado = new JTextField();
		textEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textEmpleado.setColumns(10);
		textEmpleado.setBounds(828, 38, 136, 20);
		panel.add(textEmpleado);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEmpleado.setBounds(828, 11, 75, 14);
		panel.add(lblEmpleado);

		fechaDesde = new JDateChooser();
		fechaDesde.setBounds(411, 36, 108, 19);
		panel.add(fechaDesde);
		
		fechaHasta = new JDateChooser();
		fechaHasta.setBounds(529, 36, 108, 19);
		panel.add(fechaHasta);
		
		JLabel lblFechaDesde = new JLabel("Fecha desde");
		lblFechaDesde.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFechaDesde.setBounds(411, 11, 108, 14);
		panel.add(lblFechaDesde);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFechaHasta.setBounds(529, 11, 108, 14);
		panel.add(lblFechaHasta);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 984, 10);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Historial de fabricacion");
		lblNewLabel.setBounds(10, 21, 324, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		btnSalir = new JButton("");
		btnSalir.setBounds(170, 430, 60, 60);
		frame.getContentPane().add(btnSalir);
		cambiarIconoBotones(btnSalir,  "back2.png");
		
		lblAtras = new JLabel("Atras");
		lblAtras.setBounds(240, 430, 46, 60);
		frame.getContentPane().add(lblAtras);
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
				btnVerDescripcion = new JButton("");
				btnVerDescripcion.setBounds(546, 430, 60, 60);
				frame.getContentPane().add(btnVerDescripcion);
				btnVerDescripcion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cambiarIconoBotones(btnVerDescripcion,  "descripcion.png");
				
				lblVerDetalleDe = new JLabel("Ver Detalle de Fabricacion");
				lblVerDetalleDe.setBounds(616, 430, 182, 60);
				frame.getContentPane().add(lblVerDetalleDe);
				lblVerDetalleDe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				
				JLabel lblFondo = new JLabel("");
				lblFondo.setBounds(0, 0, 1000, 750);
				frame.getContentPane().add(lblFondo);
				cambiarIconoLabel(lblFondo, "fondo.png");
	}

	public void cerrar() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "?Estas seguro que quieres salir?", 
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

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
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
