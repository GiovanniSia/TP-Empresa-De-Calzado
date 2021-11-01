package presentacion.vista.gerente;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
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

import dto.PaisDTO;
import dto.ProvinciaDTO;
import persistencia.conexion.Conexion;

public class VentanaEditarProvincia extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnSalir;
	private String[] nombreColumnasProvincia = { "Nombre", "Nombre Pais"};
	private JLabel txtGestionar;
	private JLabel txtIdPais;
	private JLabel txtIdProvincia;
	private JComboBox TipoLocalidad;
	private JTextField txtFieldNombreLocalidad;
	private JTextField txtFieldId;
	private String[] opciones = {"",""};
	private JButton btnEditar;
	private DefaultTableModel modelProvincia;
	private JTable tablaProvincia;

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
		frame.setBounds(100, 100, 532, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(252, 0, 271, 232);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spProvincia = new JScrollPane();
		spProvincia.setBounds(10, 11, 250, 182);
		panel.add(spProvincia);
		
		//Se crea la tabla
		modelProvincia = new DefaultTableModel(null, nombreColumnasProvincia );
		tablaProvincia = new JTable(modelProvincia);
		tablaProvincia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaProvincia.rowAtPoint(e.getPoint());
				txtFieldId.setText(tablaProvincia.getValueAt(filaSeleccionada, 0).toString());
			}
		});
		tablaProvincia.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProvincia.getColumnModel().getColumn(0).setResizable(false);
		tablaProvincia.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProvincia.getColumnModel().getColumn(1).setResizable(false);
		tablaProvincia.getTableHeader().setReorderingAllowed(false);
		spProvincia.setViewportView(tablaProvincia);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		btnSalir.setBounds(150, 198, 89, 23);
		panel.add(btnSalir);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 254, 232);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 198, 83, 23);
		panel_1.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(100, 198, 70, 23);
		panel_1.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(174, 198, 78, 23);
		panel_1.add(btnBorrar);

		txtGestionar = new JLabel("Gestionar localidades");
		txtGestionar.setBounds(50, 10, 150, 30);
		txtGestionar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(txtGestionar);

		TipoLocalidad = new JComboBox();
		TipoLocalidad.setBounds(40, 110, 190, 20);
		TipoLocalidad.setModel(new DefaultComboBoxModel(opciones));
		panel_1.add(TipoLocalidad);
		
		txtIdPais = new JLabel("Nombre:");
		txtIdPais.setBounds(40, 60, 70, 20);
		txtIdPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(txtIdPais);
		
		txtFieldId = new JTextField();
		txtFieldId.setBounds(130, 65, 100, 20);
		txtFieldId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
                if(txtFieldId.getText().length()>=25) {
                    e.consume();
                }
			}
		});
		panel_1.add(txtFieldId);
		}
	
	public JComboBox getComboBox() {
		return TipoLocalidad;
	}
	
	public JTextField getTxtFieldId() {
		return txtFieldId;
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
		String s = (String) TipoLocalidad.getSelectedItem();
		return s;
	}

	public int tablaProvinciaSeleccionada() {
		return tablaProvincia.getSelectedRow();
	}

	public JTable getTablaProvincia() {
		return tablaProvincia;
	}

	public void limpiarTodosTxt() {
		getTxtFieldId().setText(null);
    	return;
	}
}

