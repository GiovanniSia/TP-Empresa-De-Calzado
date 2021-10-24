package presentacion.vista.ModificarProducto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaDescripcionProveedorMProducto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Codigo", "Descripción", "Proveedor", "Talle" };
	private DefaultTableModel modelProducto;
	private JTable tablaProductoSeleccionado;
	private JPanel panel_2;
	private JLabel lblZapateria;
	private JLabel lblProductcoSeleccionado;
	private JLabel lblTItulo;
	private JPanel panel_1;
	private JButton btnAtras;
	private JButton btnActualizarProducto;
	private JTextField txtActualizarCambioDescripcion;
	private JComboBox<String> cbActualizarCambioProveedor;

	public static void main(String[] args) {
		VentanaDescripcionProveedorMProducto v = new VentanaDescripcionProveedorMProducto();
		v.show();
	}

	public VentanaDescripcionProveedorMProducto() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		frame = new JFrame();
		frame.setBounds(100, 100, 621, 294);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		modelProducto = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 6) {
					return true;
				} else {
					return false;
				}
			}
		};

		panel_1 = new JPanel();
		panel_1.setBounds(0, 69, 605, 188);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblProductcoSeleccionado = new JLabel("Producto Seleccionado:");
		lblProductcoSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductcoSeleccionado.setBounds(10, 11, 161, 14);
		panel_1.add(lblProductcoSeleccionado);

		JScrollPane spProductoSeleccionado = new JScrollPane();
		spProductoSeleccionado.setBounds(10, 34, 579, 43);
		panel_1.add(spProductoSeleccionado);

		tablaProductoSeleccionado = new JTable(modelProducto);

		tablaProductoSeleccionado.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProductoSeleccionado.getColumnModel().getColumn(0).setResizable(false);
		tablaProductoSeleccionado.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProductoSeleccionado.getColumnModel().getColumn(1).setResizable(false);

		spProductoSeleccionado.setViewportView(tablaProductoSeleccionado);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAtras.setBounds(10, 151, 89, 23);
		panel_1.add(btnAtras);

		btnActualizarProducto = new JButton("Actualizar Producto");
		btnActualizarProducto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnActualizarProducto.setBounds(410, 148, 179, 28);
		panel_1.add(btnActualizarProducto);

		JLabel lblNewLabel = new JLabel("Cambiar Descripcion");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 102, 130, 23);
		panel_1.add(lblNewLabel);

		JLabel lblNuevoProveedor = new JLabel("Cambiar Proveedor");
		lblNuevoProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNuevoProveedor.setBounds(324, 102, 130, 23);
		panel_1.add(lblNuevoProveedor);

		txtActualizarCambioDescripcion = new JTextField();
		txtActualizarCambioDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			 char validar = e.getKeyChar();
			 if(!Character.isLetter(validar) || txtActualizarCambioDescripcion.getText().length() >= 20) {
				 e.consume();
			 }
			}
		});
		txtActualizarCambioDescripcion.setBounds(166, 103, 127, 20);
		panel_1.add(txtActualizarCambioDescripcion);
		txtActualizarCambioDescripcion.setColumns(10);

		cbActualizarCambioProveedor = new JComboBox<String>();
		cbActualizarCambioProveedor.setBounds(464, 104, 127, 22);
		panel_1.add(cbActualizarCambioProveedor);

		lblTItulo = new JLabel("Cambiar Descripcion y Proveedor de Producto");
		lblTItulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTItulo.setBounds(10, 41, 595, 30);
		frame.getContentPane().add(lblTItulo);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 605, 41);
		frame.getContentPane().add(panel_2);

		lblZapateria = new JLabel("Zapater\u00EDa");
		panel_2.add(lblZapateria);
		lblZapateria.setFont(new Font("Tahoma", Font.PLAIN, 22));
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelModelProducto() {
		return modelProducto;
	}

	public JTable getTablaProductoSeleccionado() {
		return tablaProductoSeleccionado;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnActualizarProducto() {
		return btnActualizarProducto;
	}

	public JTextField getTxtActualizarCambioDescripcion() {
		return txtActualizarCambioDescripcion;
	}

	public JComboBox<String> getCbActualizarCambioProveedor() {
		return cbActualizarCambioProveedor;
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
		limpiarCampos();
		frame.setVisible(false);
	}

	public void limpiarCampos() {
		this.txtActualizarCambioDescripcion.setText(null);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

}