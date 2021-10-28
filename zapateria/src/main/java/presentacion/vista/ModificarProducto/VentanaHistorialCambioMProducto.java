package presentacion.vista.ModificarProducto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaHistorialCambioMProducto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = {"Cod.Emp","Cod. Suc", "Cod.Prod", "Fecha", "P.CostAntiguo", "P.CostNuevo", "P.MayoAntiguo",
			"P.MayoNuevo", "P.MinoAntiguo", "P.MinoNuevo","PtoRepoAntiguo","PtoRepoNuevo","CantRepoAntiguo","CantRepoNuevo","DiasRepAntiguo","DiasRepNuevo"};
	private DefaultTableModel modelhistorialCambioMProducto;
	private JTable tablaHistorialCambioMProducto;
	private JLabel lblCodProducto;
	private JLabel lblCodEmpleado;
	private JLabel lblFiltrarPor;
	private JLabel lblVerHistorialCambiosMProducto;

	private JScrollPane spHistorialCambiosMProducto;
	private JTextField txtFiltroCodProducto;
	private JTextField txtFiltroFecha;
	private JTextField txtFiltroCodEmpleado;
	private JButton btnVolverAModificarProducto;

	private JPanel panel_1;
	private JTextField txtFiltroCodSucursal;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblVolver;

	public VentanaHistorialCambioMProducto() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 1280, 477);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 175, 1264, 263);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spHistorialCambiosMProducto = new JScrollPane();
		spHistorialCambiosMProducto.setBounds(0, 11, 1264, 167);
		panel.add(spHistorialCambiosMProducto);

		modelhistorialCambioMProducto = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if(columnas == 9) {
                    return true;
                }else {
                    return false;
                }
            }
		};
		tablaHistorialCambioMProducto = new JTable(modelhistorialCambioMProducto);

		tablaHistorialCambioMProducto.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaHistorialCambioMProducto.getColumnModel().getColumn(0).setResizable(false);
		tablaHistorialCambioMProducto.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaHistorialCambioMProducto.getColumnModel().getColumn(1).setResizable(false);

		spHistorialCambiosMProducto.setViewportView(tablaHistorialCambioMProducto);

		btnVolverAModificarProducto = new JButton("");
		btnVolverAModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolverAModificarProducto.setBounds(151, 189, 60, 60);
		cambiarIconoBotones(btnVolverAModificarProducto,  "back2.png");
		panel.add(btnVolverAModificarProducto);
		btnVolverAModificarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblVolver = new JLabel("Volver");
		lblVolver.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVolver.setBounds(221, 189, 70, 60);
		panel.add(lblVolver);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 89, 1264, 87);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblCodProducto = new JLabel("Cod. Producto");
		lblCodProducto.setBounds(262, 36, 114, 20);
		lblCodProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblCodProducto);

		txtFiltroCodProducto = new JTextField();

		txtFiltroCodProducto.setBounds(259, 56, 116, 20);
		panel_1.add(txtFiltroCodProducto);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setBounds(386, 36, 70, 20);
		panel_1.add(lblFecha);

		txtFiltroFecha = new JTextField();
		txtFiltroFecha.setBounds(386, 56, 114, 20);
		panel_1.add(txtFiltroFecha);

		lblCodEmpleado = new JLabel("Cod. Empleado");
		lblCodEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCodEmpleado.setBounds(10, 36, 117, 20);
		panel_1.add(lblCodEmpleado);

		txtFiltroCodEmpleado = new JTextField();
		txtFiltroCodEmpleado.setBounds(10, 56, 114, 20);
		panel_1.add(txtFiltroCodEmpleado);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 0, 70, 32);
		panel_1.add(lblFiltrarPor);
		
		JLabel lblCodSucursal = new JLabel("Cod. Sucursal");
		lblCodSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCodSucursal.setBounds(137, 36, 114, 20);
		panel_1.add(lblCodSucursal);
		
		txtFiltroCodSucursal = new JTextField();
		txtFiltroCodSucursal.setBounds(134, 56, 116, 20);
		panel_1.add(txtFiltroCodSucursal);

		lblVerHistorialCambiosMProducto = new JLabel("Historial de Cambios de Productos");
		lblVerHistorialCambiosMProducto.setBackground(new Color(248, 248, 255));
		lblVerHistorialCambiosMProducto.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblVerHistorialCambiosMProducto.setBounds(10, 50, 716, 41);
		frame.getContentPane().add(lblVerHistorialCambiosMProducto);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 1264, 50);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Zapateria Argento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 0, 421, 50);
		panel_2.add(lblNewLabel);
	}
	
	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}

	public DefaultTableModel getModelhistorialCambioMProducto() {
		return modelhistorialCambioMProducto;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaHistorialCambioMProducto() {
		return tablaHistorialCambioMProducto;
	}

	public JTextField getTxtFiltroCodProducto() {
		return txtFiltroCodProducto;
	}
	
	public JTextField getTxtFiltroCodSucursal() {
		return txtFiltroCodSucursal;
	}

	public JTextField getTxtFiltroFecha() {
		return txtFiltroFecha;
	}

	public JTextField getTxtFiltroCodEmpleado() {
		return txtFiltroCodEmpleado;
	}

	public JButton getBtnVolverAModificarProducto() {
		return btnVolverAModificarProducto;
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
		this.txtFiltroCodProducto.setText(null);
		this.txtFiltroFecha.setText(null);
		this.txtFiltroCodEmpleado.setText(null);
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
}