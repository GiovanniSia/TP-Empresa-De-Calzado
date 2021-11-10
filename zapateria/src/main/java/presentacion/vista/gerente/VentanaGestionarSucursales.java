package presentacion.vista.gerente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

public class VentanaGestionarSucursales {
	
	private JFrame frame;

	private JLabel lblNewLabel;
	private JLabel lblNombre;

	private JButton btnRegresar;
	private JTextField textNombre;
	
	
	private JTable table;
	private DefaultTableModel modelTablaSucursales;
	
	private String[] nombreColumnas = {"Id","Nro Sucursal","Nombre","Telefono","Calle","Altura","Provincia","Localidad","Pais","Cod. Postal"};
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel lblAtras;
	
	private JButton btnAniadir;
	private JButton btnEditar;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestionarSucursales window = new VentanaGestionarSucursales();
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
	public VentanaGestionarSucursales() {
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
		
		
		JLabel lblListaDeSucursales = new JLabel("Lista de sucursales");
		lblListaDeSucursales.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblListaDeSucursales.setBounds(10, 52, 370, 50);
		frame.getContentPane().add(lblListaDeSucursales);
		
		textNombre = new JTextField();
		textNombre.setBounds(79, 132, 174, 19);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		
		//
		modelTablaSucursales = new DefaultTableModel(null,this.nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};
		scrollPane = new JScrollPane(this.table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		scrollPane.setBounds(10, 162, 702, 371);
		
		table = new JTable(modelTablaSucursales);
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
		
		lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAtras.setBounds(101, 546, 45, 58);
		frame.getContentPane().add(lblAtras);
		
		btnAniadir = new JButton("A\u00F1adir");
		btnAniadir.setBounds(247, 550, 63, 54);
		frame.getContentPane().add(btnAniadir);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(349, 550, 63, 54);
		frame.getContentPane().add(btnEditar);
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
	

	public JFrame getFrame() {
		return frame;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JLabel getLblNombre() {
		return lblNombre;
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

	public DefaultTableModel getModelTablaSucursales() {
		return modelTablaSucursales;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public JLabel getLblAtras() {
		return lblAtras;
	}

	public JButton getBtnAniadir() {
		return btnAniadir;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

}
