package presentacion.vista.gerente;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class VentanaEditarLocalidad {

	private JFrame frame;

	private JTable table;
	private DefaultTableModel modelTabla;

	private String[] nombreColumnas = {"Localidad" };

	private JLabel lblNuevaLocalidad;
	private JLabel lblPaises;
	private JLabel lblProvincias;

	private JComboBox<String> comboBoxPaises;
	private JComboBox<String> comboProvincias;
	private JTextField txtNuevaLocalidad;

	private JButton btnAgregarLocalidad;
	private JButton btnEditarLocalidad;
	private JButton btnBorrarLocalidad;
	private JButton btnSalirLocalidad;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarLocalidad window = new VentanaEditarLocalidad();
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
	public VentanaEditarLocalidad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 568, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		comboBoxPaises = new JComboBox<String>();
		comboBoxPaises.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxPaises.setBounds(46, 34, 164, 21);
		frame.getContentPane().add(comboBoxPaises);
		
		lblPaises = new JLabel("Seleccione un pais");
		lblPaises.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPaises.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaises.setBounds(46, 12, 164, 13);
		frame.getContentPane().add(lblPaises);
		
		lblProvincias = new JLabel("Seleccione una provincia");
		lblProvincias.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProvincias.setHorizontalAlignment(SwingConstants.CENTER);
		lblProvincias.setBounds(46, 74, 164, 13);
		frame.getContentPane().add(lblProvincias);
		
		comboProvincias = new JComboBox<String>();
		comboProvincias.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboProvincias.setBounds(46, 97, 164, 21);
		frame.getContentPane().add(comboProvincias);
		
		txtNuevaLocalidad = new JTextField();
		txtNuevaLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtNuevaLocalidad.setBounds(46, 172, 164, 21);
		frame.getContentPane().add(txtNuevaLocalidad);
		txtNuevaLocalidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txtNuevaLocalidad.getText().length()>=15) {
                    e.consume();
                }
            }
        });
		txtNuevaLocalidad.setColumns(10);
		
		lblNuevaLocalidad = new JLabel("Escriba una nueva localidad");
		lblNuevaLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNuevaLocalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaLocalidad.setBounds(46, 149, 164, 13);
		frame.getContentPane().add(lblNuevaLocalidad);
		
		JScrollPane scrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(301, 10, 243, 243);
		frame.getContentPane().add(scrollPane);
		
				
		modelTabla = new DefaultTableModel(null,nombreColumnas){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
				
			}
		};
		
		table = new JTable(modelTabla);
		
//		this.table.getColumnModel().getColumn(0).setPreferredWidth(103);
//		this.table.getColumnModel().getColumn(0).setResizable(false);
//		this.table.getColumnModel().getColumn(1).setPreferredWidth(100);
//		this.table.getColumnModel().getColumn(1).setResizable(false);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAgregarLocalidad.setBounds(220, 70, 75, 21);
		frame.getContentPane().add(btnAgregarLocalidad);
		
		btnEditarLocalidad = new JButton("Editar");
		btnEditarLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnEditarLocalidad.setBounds(220, 97, 75, 21);
		frame.getContentPane().add(btnEditarLocalidad);
		
		btnBorrarLocalidad = new JButton("Borrar");
		btnBorrarLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnBorrarLocalidad.setBounds(220, 123, 75, 21);
		frame.getContentPane().add(btnBorrarLocalidad);
		
		btnSalirLocalidad = new JButton("");
		btnSalirLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSalirLocalidad.setBounds(10, 204, 46, 46);
		cambiarIconoBotones(btnSalirLocalidad,  "back2.png");
		frame.getContentPane().add(btnSalirLocalidad);
	}
	
	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		for (WindowListener listener : this.frame.getWindowListeners())
	    {
			this.frame.removeWindowListener(listener);
	    }
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

	
	
	public JComboBox<String> getComboBoxPaises() {
		return comboBoxPaises;
	}

	public JComboBox<String> getComboProvincias() {
		return comboProvincias;
	}

	public JTextField getTxtNuevaLocalidad() {
		return txtNuevaLocalidad;
	}
	
	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}

	public JButton getBtnEditarLocalidad() {
		return btnEditarLocalidad;
	}

	public JButton getBtnBorrarLocalidad() {
		return btnBorrarLocalidad;
	}

	public JTable getTable() {
		return table;
	}
	
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	public DefaultTableModel getModelTabla() {
		return modelTabla;
	}

	public JButton getBtnSalirLocalidad() {
		return btnSalirLocalidad;
	}
	

	public JFrame getFrame() {
		return frame;
	}
}
