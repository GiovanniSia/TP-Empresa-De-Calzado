package presentacion.vista.gerente;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;

public class VentanaEditarPais {
	private JFrame frame;


	private JTextField textPaisNuevo;

	private JTable table;
	private DefaultTableModel modelTabla;
	private String[] nombreColumnas = { "Pais"};
	
	private JButton btnAgregarPais;
	private JButton btnEditarPais;
	private JButton btnEliminarPais;
	private JButton btnSalirPais;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarPais window = new VentanaEditarPais();
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
	public VentanaEditarPais() {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Zapateria Argento - Editar Pais");
		frame.setLocationRelativeTo(null);	
		
		btnAgregarPais = new JButton("Agregar");
		btnAgregarPais.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnAgregarPais.setBounds(10, 114, 69, 21);
		frame.getContentPane().add(btnAgregarPais);
		
		btnEditarPais = new JButton("Editar");
		btnEditarPais.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEditarPais.setBounds(85, 114, 69, 21);
		frame.getContentPane().add(btnEditarPais);
		
		btnEliminarPais = new JButton("Eliminar");
		btnEliminarPais.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEliminarPais.setBounds(85, 145, 69, 21);
		frame.getContentPane().add(btnEliminarPais);
		
		btnSalirPais = new JButton("Salir");
		btnSalirPais.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnSalirPais.setBounds(10, 145, 69, 21);
		frame.getContentPane().add(btnSalirPais);
		
		textPaisNuevo = new JTextField();
		textPaisNuevo.setBounds(30, 60, 103, 19);
		frame.getContentPane().add(textPaisNuevo);
		textPaisNuevo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(textPaisNuevo.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		textPaisNuevo.setColumns(10);
		
		JLabel lblPais = new JLabel("Escriba un nuevo pais");
		lblPais.setBounds(30, 28, 146, 21);
		frame.getContentPane().add(lblPais);

		
		JScrollPane scrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(224, 10, 202, 243);
		frame.getContentPane().add(scrollPane);
		
		modelTabla = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(modelTabla);
		
		this.table.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.table.getColumnModel().getColumn(0).setResizable(false);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
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
	
	
	
	
	
	public JTextField getTextPaisNuevo() {
		return textPaisNuevo;
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModelTabla() {
		return modelTabla;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getBtnAgregarPais() {
		return btnAgregarPais;
	}

	public JButton getBtnEditarPais() {
		return btnEditarPais;
	}

	public JButton getBtnEliminarPais() {
		return btnEliminarPais;
	}

	public JButton getBtnSalirPais() {
		return btnSalirPais;
	}
	
	
	public JFrame getFrame() {
		return frame;
	}
}
