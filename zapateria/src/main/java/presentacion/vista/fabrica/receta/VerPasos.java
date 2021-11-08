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
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class VerPasos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	//private String[] nombreColumnas = { "Id paso","Descripcion"};
	private String[] nombreColumnas = {"Descripcion","Estado"};
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JScrollPane spCliente;
	private JLabel lblNewLabel;
	private JLabel lblSucursal;
	JTextField textDescripcion;

	private JButton btnSalir;
	private JTextField textDescripcionAgregar;
	private JLabel lblSucursal_1;
	private JButton btnAgregar;
	private JTextField textFieldReceta;
	
	JComboBox comboBoxReceta;
	JComboBox comboBoxIngredientes;
	
	JScrollPane spPasosReceta;
	//private String[] nombreColumnasPasosReceta = { "Id paso","Descripcion","Nro orden"};
	private String[] nombreColumnasPasosReceta = { "Descripcion","Nro orden","Estado"};
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
	private JLabel lblUnidadMedida;
	private JLabel lblReceta;
	private JPanel panel_1;
	private JLabel lblLogo;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JSeparator separator_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JSeparator separator_2;
	private JLabel lblSucursal_2;
	private JLabel lblNewLabel_5;
	private JButton btnEliminar;
	private JTextField textId;
	private JLabel lblProductoAsociado;
	private JLabel lblNombre;
	private JLabel lblNueva;
	
	JButton btnInactivarPaso;

	public VerPasos() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 848, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(10, 104, 812, 409);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 114, 267, 147);
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
		
		lblSucursal = new JLabel("Filtrar por Descripcion");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(10, 53, 160, 20);
		panel.add(lblSucursal);
		
		textDescripcion = new JTextField();
		textDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(10, 80, 160, 27);
		panel.add(textDescripcion);
		
		textDescripcionAgregar = new JTextField();
		textDescripcionAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textDescripcionAgregar.setColumns(10);
		textDescripcionAgregar.setBounds(84, 302, 153, 30);
		panel.add(textDescripcionAgregar);
		
		lblSucursal_1 = new JLabel("Descripcion");
		lblSucursal_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal_1.setBounds(10, 302, 75, 30);
		panel.add(lblSucursal_1);
		
		btnAgregar = new JButton("");
		btnAgregar.setToolTipText("Agregar Paso a Lista");
		btnAgregar.setBounds(247, 302, 30, 30);
		cambiarIconoBotones(btnAgregar,  "arrowup.png");
		
		panel.add(btnAgregar);
		
		comboBoxReceta = new JComboBox();
		comboBoxReceta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxReceta.setBounds(402, 31, 186, 27);
		panel.add(comboBoxReceta);
		
		spPasosReceta = new JScrollPane();
		spPasosReceta.setBounds(361, 114, 177, 147);
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
		spIngredientes.setBounds(608, 49, 194, 212);
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
		btnReceta.setBounds(332, 320, 60, 60);
		cambiarIconoBotones(btnReceta,  "listadd.png");
		panel.add(btnReceta);
		
		textFieldReceta = new JTextField();
		textFieldReceta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldReceta.setColumns(10);
		textFieldReceta.setBounds(402, 80, 186, 27);
		panel.add(textFieldReceta);
		
		comboBoxIngredientes = new JComboBox();
		comboBoxIngredientes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxIngredientes.setBounds(608, 321, 154, 30);
		panel.add(comboBoxIngredientes);
		
		btnAgregarIngrediente = new JButton("");
		btnAgregarIngrediente.setToolTipText("Agregar");
		btnAgregarIngrediente.setBounds(772, 321, 30, 30);
		cambiarIconoBotones(btnAgregarIngrediente,  "plus2.png");
		panel.add(btnAgregarIngrediente);
		
		btnAgregarPasoAReceta = new JButton("");
		btnAgregarPasoAReceta.setToolTipText("Agregar Paso a Receta");
		btnAgregarPasoAReceta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAgregarPasoAReceta.setBounds(289, 125, 60, 60);
		cambiarIconoBotones(btnAgregarPasoAReceta,  "plus.png");
		panel.add(btnAgregarPasoAReceta);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 100, 1);
		spinnerCantidadIngrediente = new JSpinner(sm);
		spinnerCantidadIngrediente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinnerCantidadIngrediente.setBounds(608, 360, 52, 20);
		panel.add(spinnerCantidadIngrediente);
		
		comboBoxProductos = new JComboBox();
		comboBoxProductos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxProductos.setBounds(442, 276, 146, 27);
		panel.add(comboBoxProductos);
		
		btnQuitarPasoReceta = new JButton("");
		btnQuitarPasoReceta.setToolTipText("Quitar Paso de Receta");
		btnQuitarPasoReceta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnQuitarPasoReceta.setBounds(289, 196, 60, 60);
		cambiarIconoBotones(btnQuitarPasoReceta,  "minus.png");
		panel.add(btnQuitarPasoReceta);
		
		btnQuitarIngrediente = new JButton("");
		btnQuitarIngrediente.setBounds(772, 272, 30, 30);
		cambiarIconoBotones(btnQuitarIngrediente,  "minus2.png");
		panel.add(btnQuitarIngrediente);
		
		btnSubirPaso = new JButton("");
		btnSubirPaso.setToolTipText("Mover Paso Seleccionado un lugar Arriba");
		btnSubirPaso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSubirPaso.setBounds(548, 152, 40, 40);
		cambiarIconoBotones(btnSubirPaso,  "up.png");
		panel.add(btnSubirPaso);
		
		btnBajarPaso = new JButton("");
		btnBajarPaso.setToolTipText("Mover Paso Seleccionado un lugar Abajo");
		btnBajarPaso.setBounds(548, 192, 40, 40);
		cambiarIconoBotones(btnBajarPaso,  "down.png");
		panel.add(btnBajarPaso);
		
		btnActualizarReceta = new JButton("");
		btnActualizarReceta.setBounds(456, 320, 60, 60);
		cambiarIconoBotones(btnActualizarReceta,  "pencil.png");
		panel.add(btnActualizarReceta);
		
		JLabel lblQuitarMaterial = new JLabel("Quitar seleccionado");
		lblQuitarMaterial.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblQuitarMaterial.setBounds(608, 272, 154, 27);
		panel.add(lblQuitarMaterial);
		
		JLabel lblAgregar_1_1 = new JLabel("<html>Agregar Nueva Receta</html>");
		lblAgregar_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregar_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAgregar_1_1.setBounds(398, 321, 54, 59);
		panel.add(lblAgregar_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(318, 267, 12, 128);
		panel.add(separator);
		
		JLabel lblAgregar_1_1_1 = new JLabel("<html>Modificar Receta Existente</html>");
		lblAgregar_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregar_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAgregar_1_1_1.setBounds(522, 321, 69, 59);
		panel.add(lblAgregar_1_1_1);
		
		lblUnidadMedida = new JLabel("New label");
		lblUnidadMedida.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblUnidadMedida.setBounds(663, 360, 139, 20);
		panel.add(lblUnidadMedida);
		
		lblReceta = new JLabel("New label");
		lblReceta.setBounds(402, 54, 186, 20);
		panel.add(lblReceta);

		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(598, 12, 12, 383);
		panel.add(separator_1);
		
		lblNewLabel_3 = new JLabel("Ingredientes");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(598, 0, 214, 31);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Receta");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(318, 0, 276, 31);
		panel.add(lblNewLabel_4);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(318, 11, 12, 99);
		panel.add(separator_2);
		
		lblSucursal_2 = new JLabel("Agregar Nuevo Paso");
		lblSucursal_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal_2.setBounds(10, 279, 117, 20);
		panel.add(lblSucursal_2);
		
		lblNewLabel_5 = new JLabel("Pasos de Fabricacion");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(0, 0, 322, 31);
		panel.add(lblNewLabel_5);
		
		lblProductoAsociado = new JLabel("Producto a fabricar");
		lblProductoAsociado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProductoAsociado.setBounds(332, 279, 106, 20);
		panel.add(lblProductoAsociado);
		
		lblNombre = new JLabel("Existente");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombre.setBounds(332, 32, 60, 24);
		panel.add(lblNombre);
		
		lblNueva = new JLabel("Nueva");
		lblNueva.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNueva.setBounds(332, 80, 60, 27);
		panel.add(lblNueva);
		
		btnInactivarPaso = new JButton("");
		btnInactivarPaso.setToolTipText("Agregar Paso a Lista");
		btnInactivarPaso.setBounds(247, 350, 30, 30);
		panel.add(btnInactivarPaso);
		
		JLabel lblInactivar = new JLabel("Inactivar");
		lblInactivar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblInactivar.setBounds(162, 350, 75, 30);
		panel.add(lblInactivar);

		
		lblNewLabel = new JLabel("Editor de Recetas");
		lblNewLabel.setBounds(10, 54, 324, 48);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 832, 53);
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
		lblNewLabel_1.setBounds(640, 28, 172, 19);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Empleado:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(343, 28, 59, 19);
		panel_1.add(lblNewLabel_2);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(20, 524, 60, 60);
		frame.getContentPane().add(btnSalir);
		cambiarIconoBotones(btnSalir,  "back2.png");
		
				JLabel lblAtras = new JLabel("Atras");
				lblAtras.setBounds(90, 524, 90, 60);
				frame.getContentPane().add(lblAtras);
				lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				
				JLabel lblFondo = new JLabel("");
				lblFondo.setBounds(0, 30, 960, 720);
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

	public JLabel getLblUnidadMedida() {
		return lblUnidadMedida;
	}

	public JLabel getLblReceta() {
		return lblReceta;
	}

	public JButton getBtnInactivarPaso() {
		return btnInactivarPaso;
	}
}
