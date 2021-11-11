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
import javax.swing.border.MatteBorder;

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
	private JLabel lblSeleccionarProveedor;
	private JLabel lblAtras;
	private JLabel lblAsignarProductoA;
	
	private JButton btnAniadir;
	private JButton btnEditar;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel panel_1;
	private JLabel lblregistrarProveedor;
	private JLabel lbleditarProveedor;


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
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		lbleditarProveedor = new JLabel("<html>Editar Proveedor</html>");
		lbleditarProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbleditarProveedor.setBounds(498, 544, 71, 58);
		frame.getContentPane().add(lbleditarProveedor);
		
		lblregistrarProveedor = new JLabel("<html>Registrar Proveedor</html>");
		lblregistrarProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblregistrarProveedor.setBounds(205, 544, 71, 58);
		frame.getContentPane().add(lblregistrarProveedor);
		
		JLabel lblListaProveedores = new JLabel("Lista de proveedores");
		lblListaProveedores.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblListaProveedores.setBounds(10, 52, 370, 50);
		frame.getContentPane().add(lblListaProveedores);
		
		
		//
		modelTablaProveedores = new DefaultTableModel(null,this.nombreColumnasProveedores) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};
		scrollPane = new JScrollPane(this.table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		scrollPane.setBounds(10, 170, 702, 348);
		
		table = new JTable(modelTablaProveedores);
		table.setBounds(10, 136, 702, 371);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.table.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);
		
		btnRegresar = new JButton("");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegresar.setBounds(10, 544, 60, 60);
		cambiarIconoBotones(btnRegresar,  "back2.png");
		frame.getContentPane().add(btnRegresar);
		
		btnSeleccionarProveedor = new JButton("");
		btnSeleccionarProveedor.setBounds(277, 544, 60, 60);
		frame.getContentPane().add(btnSeleccionarProveedor);
		cambiarIconoBotones(btnSeleccionarProveedor,  "proveedor.png");
		btnSeleccionarProveedor.setVisible(false);
		
		btnAsignarProdAProv = new JButton("");
		btnAsignarProdAProv.setBounds(571, 544, 60, 60);
		cambiarIconoBotones(btnAsignarProdAProv,  "plus.png");
		frame.getContentPane().add(btnAsignarProdAProv);
		btnAsignarProdAProv.setVisible(false);
		
		lblSeleccionarProveedor = new JLabel("<html>Seleccionar Proveedor</html>");
		lblSeleccionarProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSeleccionarProveedor.setBounds(347, 544, 71, 60);
		frame.getContentPane().add(lblSeleccionarProveedor);
		lblSeleccionarProveedor.setVisible(false);
		
		lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAtras.setBounds(80, 544, 45, 60);
		frame.getContentPane().add(lblAtras);
		
		lblAsignarProductoA = new JLabel("<html>Asignar Producto a Proveedor</html>");
		lblAsignarProductoA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAsignarProductoA.setBounds(641, 544, 71, 58);
		frame.getContentPane().add(lblAsignarProductoA);
		
		btnAniadir = new JButton("");
		btnAniadir.setBounds(135, 544, 60, 60);
		cambiarIconoBotones(btnAniadir,  "proveedor+.png");
		frame.getContentPane().add(btnAniadir);
		btnAniadir.setVisible(false);
		
		btnEditar = new JButton("");
		btnEditar.setBounds(428, 544, 60, 60);
		cambiarIconoBotones(btnEditar,  "proveedoredit.png");
		frame.getContentPane().add(btnEditar);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 720, 53);
		frame.getContentPane().add(panel);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);
		
		lblNewLabel_1 = new JLabel("Sucursal:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(547, 28, 59, 19);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Empleado:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(316, 28, 59, 19);
		panel.add(lblNewLabel_2);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255, 180));
		panel_1.setBounds(0, 103, 720, 425);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(79, 40, 174, 19);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 40, 96, 19);
		panel_1.add(lblNombre);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
				
				lblNewLabel = new JLabel("Filtrar por:");
				lblNewLabel.setBounds(10, 11, 96, 19);
				panel_1.add(lblNewLabel);
				lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditar.setVisible(false);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(1, 0, 960, 720);
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
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
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
	
	
	public JLabel getLbleditarProveedor() {
		return lbleditarProveedor;
	}
}
