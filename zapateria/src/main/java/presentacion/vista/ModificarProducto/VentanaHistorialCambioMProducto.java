package presentacion.vista.ModificarProducto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private String[] nombreColumnas = {"Cod.Empleado","Cod. Sucursal", "Cod.Producto", "Fecha", "P.CostoAntiguo", "P.CostoNuevo", "P.MayoristaAntiguo",
			"P.MayoristaNuevo", "P.MinoristaAntiguo", "P.MinoristaNuevo","PuntoRepositorioAntiguo","PuntoRepositorioNuevo","CantidadAReponerAntiguo","CantidadAReponerNuevo","DiasParaReponerAntiguo","DiasParaReponerNuevo"};
	private DefaultTableModel modelhistorialCambioMProducto;
	private JTable tablaHistorialCambioMProducto;
	private JPanel panel_2;
	private JLabel lblZapateria;
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
		frame.setBounds(100, 100, 1038, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBounds(0, 166, 1022, 226);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spHistorialCambiosMProducto = new JScrollPane();
		spHistorialCambiosMProducto.setBounds(10, 11, 1002, 167);
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

		btnVolverAModificarProducto = new JButton("Volver a Modificar Producto");
		btnVolverAModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolverAModificarProducto.setBounds(433, 189, 201, 23);
		panel.add(btnVolverAModificarProducto);
		btnVolverAModificarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_1 = new JPanel();
		panel_1.setBounds(0, 69, 1022, 100);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblCodProducto = new JLabel("Cod. Producto");
		lblCodProducto.setBounds(137, 36, 114, 20);
		lblCodProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblCodProducto);

		txtFiltroCodProducto = new JTextField();

		txtFiltroCodProducto.setBounds(134, 67, 116, 20);
		panel_1.add(txtFiltroCodProducto);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(261, 36, 70, 20);
		panel_1.add(lblFecha);

		txtFiltroFecha = new JTextField();
		txtFiltroFecha.setBounds(261, 67, 114, 20);
		panel_1.add(txtFiltroFecha);

		lblCodEmpleado = new JLabel("Cod. Empleado");
		lblCodEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodEmpleado.setBounds(10, 36, 117, 20);
		panel_1.add(lblCodEmpleado);

		txtFiltroCodEmpleado = new JTextField();
		txtFiltroCodEmpleado.setBounds(10, 67, 114, 20);
		panel_1.add(txtFiltroCodEmpleado);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 11, 70, 14);
		panel_1.add(lblFiltrarPor);

		lblVerHistorialCambiosMProducto = new JLabel("Historial de Cambios de Productos");
		lblVerHistorialCambiosMProducto.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblVerHistorialCambiosMProducto.setBounds(10, 41, 716, 30);
		frame.getContentPane().add(lblVerHistorialCambiosMProducto);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 1022, 41);
		frame.getContentPane().add(panel_2);

		lblZapateria = new JLabel("Zapater\u00EDa");
		panel_2.add(lblZapateria);
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 22));
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