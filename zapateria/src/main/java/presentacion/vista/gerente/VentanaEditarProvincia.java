package presentacion.vista.gerente;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.awt.Color;

public class VentanaEditarProvincia extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnSalir;
	private String[] nombreColumnasProvincia = {"Provincia"};
	private JLabel lblGestionar;
	private JLabel txtIdPais;
	private JComboBox<String> ComboBoxLocalidades;
	private JTextField txtNombreLocalidad;
	private JButton btnEditar;
	private DefaultTableModel modelProvincia;
	private JTable tablaProvincia;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarProvincia window = new VentanaEditarProvincia();
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaEditarProvincia() {
		super();
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 375, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		//Se crea la tabla
		modelProvincia = new DefaultTableModel(null, nombreColumnasProvincia ) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 5) {
					return true;
				} else {
					return false;
				}
			}
		};

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 362, 349);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 316, 83, 23);
		panel_1.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(96, 316, 70, 23);
		panel_1.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(176, 316, 78, 23);
		panel_1.add(btnBorrar);

		lblGestionar = new JLabel("Gestionar localidades");
		lblGestionar.setBackground(Color.LIGHT_GRAY);
		lblGestionar.setForeground(Color.BLACK);
		lblGestionar.setBounds(10, 10, 180, 30);
		lblGestionar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblGestionar);

		ComboBoxLocalidades = new JComboBox<String>();
		ComboBoxLocalidades.setBounds(10, 50, 180, 20);
		panel_1.add(ComboBoxLocalidades);
		
		txtIdPais = new JLabel("Nombre:");
		txtIdPais.setBounds(235, 105, 70, 20);
		txtIdPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(txtIdPais);
		
		txtNombreLocalidad = new JTextField();
		txtNombreLocalidad.setBounds(225, 135, 100, 20);
		txtNombreLocalidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(txtNombreLocalidad.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		panel_1.add(txtNombreLocalidad);
		
		JScrollPane spProvincia = new JScrollPane();
		spProvincia.setBounds(10, 80, 180, 214);
		panel_1.add(spProvincia);
		tablaProvincia = new JTable(modelProvincia);
		tablaProvincia.getTableHeader().setReorderingAllowed(false);
		spProvincia.setViewportView(tablaProvincia);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(264, 316, 89, 23);
		panel_1.add(btnSalir);
		}
	
	public JComboBox<String> getComboBox() {
		return ComboBoxLocalidades;
	}
	
	public JTextField getTxtLocalidad() {
		return txtNombreLocalidad;
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}
	
	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
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
	
	public void cerrarVentana() {
		frame.setVisible(false);
	}

	public String[] getNombreColumnasProvincia() {
		return nombreColumnasProvincia;
	}

	public DefaultTableModel getModelProvincia() {
		return modelProvincia;
	}
	
	public String obtenerSeleccion() {
		String s = (String) ComboBoxLocalidades.getSelectedItem();
		return s;
	}

	public int tablaProvinciaSeleccionada() {
		return tablaProvincia.getSelectedRow();
	}

	public JTable getTablaProvincia() {
		return tablaProvincia;
	}

	public JFrame getFrame() {
		return frame;
	}

}

