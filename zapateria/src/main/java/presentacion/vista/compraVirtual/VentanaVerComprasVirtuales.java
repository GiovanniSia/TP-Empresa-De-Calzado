package presentacion.vista.compraVirtual;

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

import javax.swing.JCheckBox;

public class VentanaVerComprasVirtuales extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnasTablaPrincipal = { "Nro orden","Sucursal", "CUIL", "Cliente", "Fecha", "Hora", "Estado", "Nota Credito"};
	private DefaultTableModel modelDeTablaPrincipal;
	private JTable tabla;
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
	JCheckBox chckbxCancelados;
	
	JCheckBox chckbxErrorSucursal;

	public VentanaVerComprasVirtuales() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 993, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 106, 974, 420);
		frame.getContentPane().add(panel);
		frame.setLocationRelativeTo(null);
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
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		spCliente.setViewportView(tabla);

		btnVerDescripcion = new JButton("");
		btnVerDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerDescripcion.setBounds(622, 352, 65, 59);
		cambiarIconoBotones(btnVerDescripcion,  "descripcion.png");
		panel.add(btnVerDescripcion);
		
		lblId = new JLabel("Id");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		textId = new JTextField();
		textId.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textId.setBounds(10, 36, 75, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		lblSucursal = new JLabel("Sucursal");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblSucursal.setBounds(95, 11, 46, 14);
		panel.add(lblSucursal);
		
		textSucursal = new JTextField();
		textSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textSucursal.setColumns(10);
		textSucursal.setBounds(95, 36, 75, 20);
		panel.add(textSucursal);
		
		textCUIL = new JTextField();
		textCUIL.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textCUIL.setColumns(10);
		textCUIL.setBounds(180, 36, 75, 20);
		panel.add(textCUIL);
		
		lblCUIL = new JLabel("CUIL");
		lblCUIL.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCUIL.setBounds(180, 11, 46, 14);
		panel.add(lblCUIL);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(130, 352, 65, 59);
		cambiarIconoBotones(btnSalir,  "back2.png");
		panel.add(btnSalir);
		
		textCliente = new JTextField();
		textCliente.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textCliente.setColumns(10);
		textCliente.setBounds(265, 36, 136, 20);
		panel.add(textCliente);
		
		JLabel lblNombre = new JLabel("Cliente");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNombre.setBounds(265, 11, 46, 14);
		panel.add(lblNombre);

		fechaDesde = new JDateChooser();
		fechaDesde.setBounds(411, 36, 108, 19);
		panel.add(fechaDesde);
		
		fechaHasta = new JDateChooser();
		fechaHasta.setBounds(529, 36, 108, 19);
		panel.add(fechaHasta);
		
		JLabel lblFechaDesde = new JLabel("Fecha desde");
		lblFechaDesde.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFechaDesde.setBounds(411, 11, 108, 14);
		panel.add(lblFechaDesde);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFechaHasta.setBounds(529, 11, 108, 14);
		panel.add(lblFechaHasta);
		
		chckbxCancelados = new JCheckBox("Cancelados");
		chckbxCancelados.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxCancelados.setBounds(643, 35, 97, 23);
		panel.add(chckbxCancelados);
		
		JLabel lblNewLabel_2 = new JLabel("Ver Descripcion");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(711, 352, 123, 57);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Atras");
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(220, 352, 123, 57);
		panel.add(lblNewLabel_2_1);
		
		chckbxErrorSucursal = new JCheckBox("Error/Sucursal");
		chckbxErrorSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxErrorSucursal.setBounds(742, 36, 97, 23);
		panel.add(chckbxErrorSucursal);
		
		lblNewLabel = new JLabel("Compras Virtuales");
		lblNewLabel.setBounds(10, 63, 324, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 2, 974, 50);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Zapateria Argento");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_1.setBounds(10, 0, 421, 50);
		panel_2.add(lblNewLabel_1);
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

	public JCheckBox getChckbxCancelados() {
		return chckbxCancelados;
	}
	
	public JCheckBox getChckbxErrorSucursal() {
		return chckbxErrorSucursal;
	}
}
