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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class VentanaGestionarProveedores {

	private JFrame frame;

	private JLabel lblNewLabel;
	private JLabel lblNombre;

	private JButton btnRegresar;
	private JTextField textNombre;
	
	
	private JTable table;
	private DefaultTableModel modelTablaProveedores;

	private String[] nombreColumnasProveedores = {"Id","Nombre","Correo","Limite de credito"};
	private JScrollPane scrollPane;
	
	private JButton btnSeleccionarProveedor;
	private JButton btnAsignarProdAProv;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel lblSeleccionarProveedor;
	private JLabel lblAtras;
	private JLabel lblAsignarProductoA;
	
	private JButton btnAniadir;
	private JButton btnEditar;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestionarProveedores window = new VentanaGestionarProveedores();
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
	public VentanaGestionarProveedores() {
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
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 736, 663);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		JLabel lblListaProveedores = new JLabel("Lista de proveedores");
		lblListaProveedores.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblListaProveedores.setBounds(10, 52, 370, 50);
		frame.getContentPane().add(lblListaProveedores);
		
		textNombre = new JTextField();
		textNombre.setBounds(79, 132, 174, 19);
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
		scrollPane.setBounds(10, 162, 702, 371);
		
		table = new JTable(modelTablaProveedores);
		table.setBounds(10, 136, 702, 371);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.table.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);

		
		lblNewLabel = new JLabel("Filtrar por:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 103, 96, 19);
		frame.getContentPane().add(lblNewLabel);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombre.setBounds(10, 132, 96, 19);
		frame.getContentPane().add(lblNombre);
		
		btnRegresar = new JButton("");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegresar.setBounds(31, 544, 60, 60);
		cambiarIconoBotones(btnRegresar,  "back2.png");
		frame.getContentPane().add(btnRegresar);
		
		btnSeleccionarProveedor = new JButton("");
		btnSeleccionarProveedor.setBounds(143, 544, 60, 60);
		frame.getContentPane().add(btnSeleccionarProveedor);
		cambiarIconoBotones(btnSeleccionarProveedor,  "plus.png");
		btnSeleccionarProveedor.setVisible(false);
		
		btnAsignarProdAProv = new JButton("");
		btnAsignarProdAProv.setBounds(439, 546, 60, 60);
		cambiarIconoBotones(btnAsignarProdAProv,  "plus.png");
		frame.getContentPane().add(btnAsignarProdAProv);
		btnAsignarProdAProv.setVisible(false);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 720, 50);
		frame.getContentPane().add(panel);
		
		lblNewLabel_1 = new JLabel("Zapateria Argento");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_1.setBounds(10, 0, 236, 50);
		panel.add(lblNewLabel_1);
		
		lblSeleccionarProveedor = new JLabel("Seleccionar Proveedor");
		lblSeleccionarProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSeleccionarProveedor.setBounds(213, 546, 204, 58);
		frame.getContentPane().add(lblSeleccionarProveedor);
		lblSeleccionarProveedor.setVisible(false);
		
		lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAtras.setBounds(101, 546, 45, 58);
		frame.getContentPane().add(lblAtras);
		
		lblAsignarProductoA = new JLabel("Asignar Producto a Proveedor");
		lblAsignarProductoA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAsignarProductoA.setBounds(509, 546, 203, 58);
		frame.getContentPane().add(lblAsignarProductoA);
		
		btnAniadir = new JButton("A\u00F1adir");
		btnAniadir.setBounds(247, 550, 63, 54);
		frame.getContentPane().add(btnAniadir);
		btnAniadir.setVisible(false);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(349, 550, 63, 54);
		frame.getContentPane().add(btnEditar);
		btnEditar.setVisible(false);
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
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
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

	public JButton getBtnAsignarProdAProveedor() {
		return btnAsignarProdAProv;
	}
	public DefaultTableModel getModelTablaProveedores() {
		return modelTablaProveedores;
	}
	public String[] getNombreColumnasProveedores() {
		return nombreColumnasProveedores;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JButton getBtnAsignarProdAProv() {
		return btnAsignarProdAProv;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public JLabel getLblSeleccionarProveedor() {
		return lblSeleccionarProveedor;
	}

	public JLabel getLblAtras() {
		return lblAtras;
	}

	public JLabel getLblAsignarProductoA() {
		return lblAsignarProductoA;
	}

	public JButton getBtnAniadir() {
		return btnAniadir;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}
}
