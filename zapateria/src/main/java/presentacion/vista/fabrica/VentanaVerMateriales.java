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

import persistencia.conexion.Conexion;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class VentanaVerMateriales extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = {"Id", "Descripcion", "Talle", "Unidad medida", "Cantidad Actual"};
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JScrollPane spCliente;
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JTextField textId;
	private JLabel lblDescrip;
	private JTextField textTalle;
	private JLabel lblProducto;
	JTextField textDescrip;

	private JButton btnSalir;
	private JTextField textUnidad;
	private JCheckBox chckbxCancelados;
	

	public VentanaVerMateriales() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 918, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 93, 902, 424);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 69, 884, 272);
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
		
		lblId = new JLabel("Id");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		textId = new JTextField();
		textId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textId.setBounds(10, 36, 75, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		lblDescrip = new JLabel("Descripcion");
		lblDescrip.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDescrip.setBounds(95, 11, 75, 14);
		panel.add(lblDescrip);
		
		textDescrip = new JTextField();
		textDescrip.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textDescrip.setColumns(10);
		textDescrip.setBounds(95, 36, 75, 20);
		panel.add(textDescrip);
		
		textTalle = new JTextField();
		textTalle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textTalle.setColumns(10);
		textTalle.setBounds(180, 36, 75, 20);
		panel.add(textTalle);
		
		lblProducto = new JLabel("Talle");
		lblProducto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProducto.setBounds(180, 11, 60, 14);
		panel.add(lblProducto);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(152, 352, 60, 60);
		cambiarIconoBotones(btnSalir,  "back2.png");
		panel.add(btnSalir);
		
		textUnidad = new JTextField();
		textUnidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textUnidad.setColumns(10);
		textUnidad.setBounds(265, 36, 75, 20);
		panel.add(textUnidad);
		
		JLabel lblPaso = new JLabel("Unidad");
		lblPaso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPaso.setBounds(265, 11, 46, 14);
		panel.add(lblPaso);
		
		chckbxCancelados = new JCheckBox("Ver cancelados");
		chckbxCancelados.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxCancelados.setBounds(679, 35, 111, 23);
		panel.add(chckbxCancelados);

		JLabel lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(222, 352, 90, 60);
		panel.add(lblAtras);

		
		lblNewLabel = new JLabel("Materiales de fabricacion");
		lblNewLabel.setBounds(10, 49, 324, 44);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 902, 50);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Zapateria Argento");
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_1.setBounds(10, 0, 421, 50);
		panel_1.add(lblNewLabel_1);
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
	
	
	public JTable getTablaFabricacionesEnMarcha() {
		return tabla;
	}

	public DefaultTableModel getModelOrdenes() {
		return modelOrdenes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTextField getTextId() {
		return textId;
	}

	public JTextField gettextTalle() {
		return textTalle;
	}

	public JTextField gettextDescrip() {
		return textDescrip;
	}
	
	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JTextField gettextUnidad() {
		return textUnidad;
	}
	
	public JCheckBox getChckbxCancelados() {
		return chckbxCancelados;
	}
}
