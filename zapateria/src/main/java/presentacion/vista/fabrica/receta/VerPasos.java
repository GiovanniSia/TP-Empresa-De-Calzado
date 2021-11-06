package presentacion.vista.fabrica.receta;

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
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class VerPasos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	//private String[] nombreColumnas = { "Id paso","Descripcion"};
	private String[] nombreColumnas = {"Descripcion"};
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JScrollPane spCliente;
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JTextField textId;
	private JLabel lblSucursal;
	JTextField textDescripcion;

	private JButton btnSalir;
	private JTextField textDescripcionAgregar;
	private JLabel lblSucursal_1;
	private JButton btnAgregar;
	private JLabel lblAgregar;
	private JButton btnEliminar;
	private JLabel lblEliminar;
	private JTextField textFieldReceta;
	
	JComboBox comboBoxReceta;
	JComboBox comboBoxIngredientes;
	
	JScrollPane spPasosReceta;
	//private String[] nombreColumnasPasosReceta = { "Id paso","Descripcion","Nro orden"};
	private String[] nombreColumnasPasosReceta = { "Descripcion","Nro orden"};
	private DefaultTableModel modelPasosReceta;
	private JTable tablaPasosReceta;
	
	JScrollPane spIngredientes;
	private String[] nombreColumnasIngredientes = { "Producto","Cantidad"};
	private DefaultTableModel modelIngredientes;
	private JTable tablaIngredientes;
	private JButton btnAgregarPasoAReceta;
	JButton btnReceta;
	
	JSpinner spinnerCantidadIngrediente;
	JButton btnAgregarIngrediente;
	JComboBox comboBoxProductos;
	JButton btnQuitarPasoReceta;
	private JButton btnQuitarIngrediente;
	private JButton btnSubirPaso;
	private JButton btnBajarPaso;
	
	JButton btnActualizarReceta ;

	public VerPasos() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 838, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 93, 812, 452);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 81, 267, 147);
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
		lblId.setVisible(false);
		
		textId = new JTextField();
		textId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textId.setBounds(10, 36, 75, 20);
		panel.add(textId);
		textId.setColumns(10);
		textId.setVisible(false);
		
		lblSucursal = new JLabel("Descripcion");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(10, 25, 160, 14);
		panel.add(lblSucursal);
		
		textDescripcion = new JTextField();
		textDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(10, 50, 160, 20);
		panel.add(textDescripcion);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(10, 352, 60, 60);
		cambiarIconoBotones(btnSalir,  "back2.png");
		panel.add(btnSalir);

		JLabel lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(80, 352, 90, 60);
		panel.add(lblAtras);
		
		textDescripcionAgregar = new JTextField();
		textDescripcionAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textDescripcionAgregar.setColumns(10);
		textDescripcionAgregar.setBounds(10, 268, 107, 20);
		panel.add(textDescripcionAgregar);
		
		lblSucursal_1 = new JLabel("Descripcion");
		lblSucursal_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal_1.setBounds(10, 249, 75, 14);
		panel.add(lblSucursal_1);
		
		btnAgregar = new JButton("");
		btnAgregar.setBounds(127, 239, 60, 60);
		panel.add(btnAgregar);
		
		lblAgregar = new JLabel("Agregar");
		lblAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAgregar.setBounds(197, 239, 80, 60);
		panel.add(lblAgregar);
		
		btnEliminar = new JButton("");
		btnEliminar.setBounds(180, 11, 60, 60);
		panel.add(btnEliminar);
		btnEliminar.setVisible(false);
		
		lblEliminar = new JLabel("Eliminar");
		lblEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEliminar.setBounds(250, 11, 75, 60);
		panel.add(lblEliminar);
		lblEliminar.setVisible(false);
		
		comboBoxReceta = new JComboBox();
		comboBoxReceta.setBounds(361, 36, 177, 22);
		panel.add(comboBoxReceta);
		
		spPasosReceta = new JScrollPane();
		spPasosReceta.setBounds(361, 81, 177, 147);
		panel.add(spPasosReceta);
		this.modelPasosReceta = new DefaultTableModel(null, this.nombreColumnasPasosReceta){
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
		this.tablaPasosReceta = new JTable(modelPasosReceta);
		tablaPasosReceta.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		this.spPasosReceta.setViewportView(tablaPasosReceta);
		
		spIngredientes = new JScrollPane();
		spIngredientes.setBounds(647, 81, 138, 147);
		panel.add(spIngredientes);
		this.modelIngredientes = new DefaultTableModel(null, this.nombreColumnasIngredientes){
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int filas, int columnas) {
            	return false;
            }
        };
		this.tablaIngredientes = new JTable(modelIngredientes);
		tablaIngredientes.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		this.spIngredientes.setViewportView(tablaIngredientes);
		
		btnReceta = new JButton("");
		btnReceta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReceta.setBounds(361, 301, 60, 60);
		panel.add(btnReceta);
		
		textFieldReceta = new JTextField();
		textFieldReceta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldReceta.setColumns(10);
		textFieldReceta.setBounds(361, 239, 177, 20);
		panel.add(textFieldReceta);
		
		comboBoxIngredientes = new JComboBox();
		comboBoxIngredientes.setBounds(647, 239, 138, 22);
		panel.add(comboBoxIngredientes);
		
		btnAgregarIngrediente = new JButton("");
		btnAgregarIngrediente.setBounds(647, 301, 60, 60);
		panel.add(btnAgregarIngrediente);
		
		btnAgregarPasoAReceta = new JButton("");
		btnAgregarPasoAReceta.setBounds(291, 81, 60, 60);
		panel.add(btnAgregarPasoAReceta);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 100, 1);
		spinnerCantidadIngrediente = new JSpinner(sm);
		spinnerCantidadIngrediente.setBounds(647, 269, 60, 20);
		panel.add(spinnerCantidadIngrediente);
		
		comboBoxProductos = new JComboBox();
		comboBoxProductos.setBounds(361, 268, 177, 22);
		panel.add(comboBoxProductos);
		
		btnQuitarPasoReceta = new JButton("");
		btnQuitarPasoReceta.setBounds(291, 152, 60, 60);
		panel.add(btnQuitarPasoReceta);
		
		btnQuitarIngrediente = new JButton("");
		btnQuitarIngrediente.setBounds(647, 11, 60, 60);
		panel.add(btnQuitarIngrediente);
		
		btnSubirPaso = new JButton("");
		btnSubirPaso.setBounds(548, 117, 50, 50);
		panel.add(btnSubirPaso);
		
		btnBajarPaso = new JButton("");
		btnBajarPaso.setBounds(548, 178, 50, 50);
		panel.add(btnBajarPaso);
		
		btnActualizarReceta = new JButton("");
		btnActualizarReceta.setBounds(361, 372, 60, 60);
		panel.add(btnActualizarReceta);
		
		JLabel lblQuitarMaterial = new JLabel("Quitar");
		lblQuitarMaterial.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblQuitarMaterial.setBounds(717, 12, 75, 60);
		panel.add(lblQuitarMaterial);
		
		JLabel lblAgregar_1 = new JLabel("Agregar");
		lblAgregar_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAgregar_1.setBounds(717, 301, 80, 60);
		panel.add(lblAgregar_1);
		
		JLabel lblAgregar_1_1 = new JLabel("Agregar");
		lblAgregar_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAgregar_1_1.setBounds(431, 301, 80, 60);
		panel.add(lblAgregar_1_1);
		
		JLabel lblAgregar_1_1_1 = new JLabel("Modificar");
		lblAgregar_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAgregar_1_1_1.setBounds(431, 372, 80, 60);
		panel.add(lblAgregar_1_1_1);

		
		lblNewLabel = new JLabel("Pasos");
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
		             null, "�Estas seguro que quieres salir?", 
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

	public DefaultTableModel getModelPasos() {
		return modelOrdenes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTextField getTextId() {
		return textId;
	}

	public JTextField getTextSucursal() {
		return textDescripcion;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JTextField getTextDescripcion() {
		return textDescripcion;
	}

	public JTextField getTextDescripcionAgregar() {
		return textDescripcionAgregar;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JTextField getTextFieldReceta() {
		return textFieldReceta;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxReceta() {
		return comboBoxReceta;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxIngredientes() {
		return comboBoxIngredientes;
	}
	

	public JScrollPane getSpPasosReceta() {
		return spPasosReceta;
	}

	public String[] getNombreColumnasPasosReceta() {
		return nombreColumnasPasosReceta;
	}

	public DefaultTableModel getModelPasosReceta() {
		return modelPasosReceta;
	}

	public JTable getTablaPasosReceta() {
		return tablaPasosReceta;
	}
	
	public JScrollPane getSpIngredientes() {
		return spIngredientes;
	}

	public String[] getNombreColumnasIngredientes() {
		return nombreColumnasIngredientes;
	}

	public DefaultTableModel getModelIngredientes() {
		return modelIngredientes;
	}

	public JTable getTablaIngredientes() {
		return tablaIngredientes;
	}

	public JButton getBtnAgregarPasoAReceta() {
		return btnAgregarPasoAReceta;
	}

	public JButton getBtnReceta() {
		return btnReceta;
	}

	public JSpinner getSpinnerCantidadIngrediente() {
		return spinnerCantidadIngrediente;
	}

	public JButton getBtnAgregarIngrediente() {
		return btnAgregarIngrediente;
	}

	public JComboBox getComboBoxProductos() {
		return comboBoxProductos;
	}

	public JButton getBtnQuitarPasoReceta() {
		return btnQuitarPasoReceta;
	}

	public JButton getBtnQuitarIngrediente() {
		return btnQuitarIngrediente;
	}

	public JButton getBtnSubirPaso() {
		return btnSubirPaso;
	}

	public JButton getBtnBajarPaso() {
		return btnBajarPaso;
	}

	public JButton getBtnActualizarReceta() {
		return btnActualizarReceta;
	}
}
