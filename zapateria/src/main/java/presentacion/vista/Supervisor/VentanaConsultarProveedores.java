package presentacion.vista.Supervisor;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import persistencia.conexion.Conexion;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class VentanaConsultarProveedores {

	private JFrame frame;

	private JLabel lblNewLabel;
	private JLabel lblNombre;

	private JButton btnRegresar;
	private JTextField textNombre;
	
	
	private JTable table;
	private DefaultTableModel modelTablaProveedores;

	private String[] nombreColumnasProveedores = {"Id","Nombre","Correo","Limite de crédito","Credito disponible"};
	private JScrollPane scrollPane;
	
	private JButton btnSeleccionarProveedor;
	private JButton btnEditarProveedor;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConsultarProveedores window = new VentanaConsultarProveedores();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaConsultarProveedores() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
      }
		frame = new JFrame();
		frame.setBounds(100, 100, 736, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		JLabel lblListaProveedores = new JLabel("Lista de proveedores");
		lblListaProveedores.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
		lblListaProveedores.setBounds(10, 10, 370, 40);
		frame.getContentPane().add(lblListaProveedores);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 107, 96, 19);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		
		//
		modelTablaProveedores = new DefaultTableModel(null,this.nombreColumnasProveedores) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};
		scrollPane = new JScrollPane(this.table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		scrollPane.setBounds(10, 136, 702, 371);
		
		table = new JTable(modelTablaProveedores);
		table.setBounds(10, 136, 702, 371);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.table.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);

		
		lblNewLabel = new JLabel("Filtrar por:");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 10));
		lblNewLabel.setBounds(10, 48, 96, 19);
		frame.getContentPane().add(lblNewLabel);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Consolas", Font.PLAIN, 10));
		lblNombre.setBounds(10, 89, 96, 19);
		frame.getContentPane().add(lblNombre);
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(10, 529, 56, 46);
		cambiarIconoBotones(btnRegresar,  "back.png");
		frame.getContentPane().add(btnRegresar);
		
		btnSeleccionarProveedor = new JButton("Seleccionar Proveedor");
		btnSeleccionarProveedor.setBounds(218, 517, 211, 29);
		frame.getContentPane().add(btnSeleccionarProveedor);
		btnSeleccionarProveedor.setVisible(false);
		
		btnEditarProveedor = new JButton("Asignar Producto a Proveedor");
		btnEditarProveedor.setBounds(526, 517, 184, 29);
		frame.getContentPane().add(btnEditarProveedor);
		btnEditarProveedor.setVisible(false);
	}

	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?", 
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

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_DEFAULT));
		boton.setIcon(Icono);
	}
	
	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JTextField getTextNombre() {
		return textNombre;
	}

	public JTable getTable() {
		return table;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JButton getBtnSeleccionarProveedor() {
		return btnSeleccionarProveedor;
	}

	public JButton getBtnEditarProveedor() {
		return btnEditarProveedor;
	}
	public DefaultTableModel getModelTablaProveedores() {
		return modelTablaProveedores;
	}
	public String[] getNombreColumnasProveedores() {
		return nombreColumnasProveedores;
	}
}
