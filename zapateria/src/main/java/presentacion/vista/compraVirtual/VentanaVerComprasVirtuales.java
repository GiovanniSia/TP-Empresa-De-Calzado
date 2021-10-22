package presentacion.vista.compraVirtual;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
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

public class VentanaVerComprasVirtuales extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnasTablaPrincipal = { "Nro orden","Sucursal", "CUIL", "Cliente", "Fecha", "Hora", "Estado"};
	private DefaultTableModel modelDeTablaPrincipal;
	private JTable tabla;
	private JPanel panel_2;
	private JScrollPane spCliente;

	private JButton btnVerDescripcion;
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JTextField textId;
	private JLabel lblSucursal;
	private JTextField textCUIL;
	private JLabel lblCUIL;
	JTextField textSucursal;

	private JButton btnSalir;
	private JTextField textCliente;
	
	JDateChooser fechaHasta;
	JDateChooser fechaDesde;

	public VentanaVerComprasVirtuales() {
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

		modelDeTablaPrincipal = new DefaultTableModel(null, nombreColumnasTablaPrincipal){
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
		tabla = new JTable(modelDeTablaPrincipal);

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
		
		textCUIL = new JTextField();
		textCUIL.setColumns(10);
		textCUIL.setBounds(180, 36, 75, 20);
		panel.add(textCUIL);
		
		lblCUIL = new JLabel("CUIL");
		lblCUIL.setBounds(180, 11, 46, 14);
		panel.add(lblCUIL);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(918, 350, 46, 38);
//		btnSalir.setIcon(setIcono("../imagenes/back.png",btnSalir));
		panel.add(btnSalir);
		
		textCliente = new JTextField();
		textCliente.setColumns(10);
		textCliente.setBounds(265, 36, 136, 20);
		panel.add(textCliente);
		
		JLabel lblNombre = new JLabel("Cliente");
		lblNombre.setBounds(265, 11, 46, 14);
		panel.add(lblNombre);

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
		
		lblNewLabel = new JLabel("Compras virtuales");
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

	public Icon setIcono(String url,JButton boton) {
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
	}
	
	
	public JTable getTablaFabricacionesEnMarcha() {
		return tabla;
	}

	public DefaultTableModel getModelDeTablaPrincipal() {
		return modelDeTablaPrincipal;
	}

	public String[] getNombreColumnas() {
		return nombreColumnasTablaPrincipal;
	}

	public JButton getBtnVerDescripcion() {
		return btnVerDescripcion;
	}

	public JTextField getTextId() {
		return textId;
	}

	public JTextField getTextCUIL() {
		return textCUIL;
	}

	public JTextField getTextSucursal() {
		return textSucursal;
	}
	
	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JTextField getTextCliente() {
		return textCliente;
	}
	
	public JDateChooser getFechaDesde() {
		return fechaDesde;
	}

	public JDateChooser getFechaHasta() {
		return fechaHasta;
	}
}
