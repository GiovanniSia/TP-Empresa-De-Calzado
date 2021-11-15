package presentacion.vista.verFacturas;

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
import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

public class VentanaVerFabricaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Nro factura","Vendedor", "Cajero", "Cliente", "CUIL", "IVA", "Total"};
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JScrollPane spCliente;

	private JButton btnVerFactura;
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JTextField textNroFactura;
	private JLabel lblSucursal;
	private JTextField textCajero;
	private JLabel lblProducto;
	JTextField textVendedor;

	private JButton btnSalir;
	private JTextField textCliente;
	private JTextField textCuil;
	private JCheckBox chckbxIVA;
	private JLabel lblVerFactura;
	private JPanel panel_1;
	private JLabel lblLogo;

	public VentanaVerFabricaciones() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 918, 569);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(0, 117, 902, 330);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 69, 884, 252);
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
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		spCliente.setViewportView(tabla);
		
		lblId = new JLabel("Nro factura");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 75, 14);
		panel.add(lblId);
		
		textNroFactura = new JTextField();
		textNroFactura.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textNroFactura.setBounds(10, 36, 75, 20);
		panel.add(textNroFactura);
		textNroFactura.setColumns(10);
		
		lblSucursal = new JLabel("Vendedor");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(137, 11, 75, 14);
		panel.add(lblSucursal);
		
		textVendedor = new JTextField();
		textVendedor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textVendedor.setColumns(10);
		textVendedor.setBounds(137, 38, 75, 20);
		panel.add(textVendedor);
		
		textCajero = new JTextField();
		textCajero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textCajero.setColumns(10);
		textCajero.setBounds(261, 36, 75, 20);
		panel.add(textCajero);
		
		lblProducto = new JLabel("Cajero");
		lblProducto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProducto.setBounds(261, 11, 75, 14);
		panel.add(lblProducto);
		
		textCliente = new JTextField();
		textCliente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textCliente.setColumns(10);
		textCliente.setBounds(386, 38, 75, 20);
		panel.add(textCliente);
		
		textCuil = new JTextField();
		textCuil.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textCuil.setColumns(10);
		textCuil.setBounds(514, 38, 75, 20);
		panel.add(textCuil);
		
		JLabel lblPaso = new JLabel("Cliente");
		lblPaso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPaso.setBounds(386, 11, 46, 14);
		panel.add(lblPaso);
		
		JLabel lblCuil = new JLabel("CUIL");
		lblCuil.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCuil.setBounds(514, 11, 46, 14);
		panel.add(lblCuil);
		
		chckbxIVA = new JCheckBox("IVA");
		chckbxIVA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxIVA.setBounds(635, 35, 111, 23);
		panel.add(chckbxIVA);

		
		lblNewLabel = new JLabel("Ordenes de fabricacion pendientes");
		lblNewLabel.setBounds(10, 49, 389, 67);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 902, 53);
		frame.getContentPane().add(panel_1);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes.png");
		panel_1.add(lblLogo);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(10, 458, 60, 60);
		frame.getContentPane().add(btnSalir);
		cambiarIconoBotones(btnSalir,  "back2.png");
		
				JLabel lblAtras = new JLabel("Atras");
				lblAtras.setBounds(80, 458, 90, 60);
				frame.getContentPane().add(lblAtras);
				lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				
						btnVerFactura = new JButton("");
						btnVerFactura.setBounds(559, 458, 60, 60);
						frame.getContentPane().add(btnVerFactura);
						btnVerFactura.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						cambiarIconoBotones(btnVerFactura,  "right.png");
						
						lblVerFactura = new JLabel("Ver factura");
						lblVerFactura.setBounds(629, 458, 138, 60);
						frame.getContentPane().add(lblVerFactura);
						lblVerFactura.setFont(new Font("Segoe UI", Font.PLAIN, 16));
						
						JLabel lblFondo = new JLabel("");
						lblFondo.setBounds(0, 0, 960, 720);
						frame.getContentPane().add(lblFondo);
						cambiarIconoLabel(lblFondo, "fondo.png");
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
	}
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}
	
	public JTable getTablaFactura() {
		return tabla;
	}

	public DefaultTableModel getModelFactura() {
		return modelOrdenes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getBtnVerFactura() {
		return btnVerFactura;
	}

	public JTextField getTextNroFactura() {
		return textNroFactura;
	}

	public JTextField getTextCajero() {
		return textCajero;
	}

	public JTextField getTextVendedor() {
		return textVendedor;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JTextField getTextCliente() {
		return textCliente;
	}

	public JTextField getTextCuil() {
		return textCuil;
	}

	public JCheckBox getChckbxIVA() {
		return chckbxIVA;
	}
}
