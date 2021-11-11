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
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

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
	private JCheckBox chckbxRiesgo;
	private JPanel panel_1;
	private JLabel lblLogo;
	private JLabel lblNewLabel_1;
	private JLabel lblEmpleado;
	

	public VentanaVerMateriales() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 918, 572);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(0, 103, 902, 350);
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
		
		textUnidad = new JTextField();
		textUnidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textUnidad.setColumns(10);
		textUnidad.setBounds(265, 36, 75, 20);
		panel.add(textUnidad);
		
		JLabel lblPaso = new JLabel("Unidad");
		lblPaso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPaso.setBounds(265, 11, 46, 14);
		panel.add(lblPaso);
		
		chckbxRiesgo = new JCheckBox("Riesgo Stock");
		chckbxRiesgo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxRiesgo.setBounds(679, 35, 111, 23);
		panel.add(chckbxRiesgo);

		
		lblNewLabel = new JLabel("Materiales de fabricacion");
		lblNewLabel.setBounds(10, 49, 324, 53);
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
		
		lblNewLabel_1 = new JLabel("F\u00E1brica");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(706, 28, 172, 19);
		panel_1.add(lblNewLabel_1);
		
		lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEmpleado.setBounds(512, 28, 232, 19);
		panel_1.add(lblEmpleado);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(135, 462, 60, 60);
		frame.getContentPane().add(btnSalir);
		cambiarIconoBotones(btnSalir,  "back2.png");
		
				JLabel lblAtras = new JLabel("Atras");
				lblAtras.setBounds(205, 462, 90, 60);
				frame.getContentPane().add(lblAtras);
				lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				
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
		return chckbxRiesgo;
	}
}
