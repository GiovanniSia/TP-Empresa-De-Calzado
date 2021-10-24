package presentacion.vista.fabrica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;

import javax.swing.JComboBox;

public class fecha extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnElegir;
	private static fecha INSTANCE;
	
	private JLabel lblFecha;
	
	private JComboBox<String> comboBox;
	
	public static fecha getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new fecha(); 	
			return new fecha();
		}
		else
			return INSTANCE;
	}

	private fecha() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 464, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblFecha = new JLabel("t");
		lblFecha.setBounds(10, 11, 183, 14);
		panel.add(lblFecha);
		
		btnElegir = new JButton("Elegir");
		btnElegir.setBounds(348, 43, 89, 23);
		panel.add(btnElegir);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(248, 7, 89, 22);
		panel.add(comboBox);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		show();
	}
	
	public void show() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() 
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
		setVisible(true);
	}
	
	public JButton getBtnElegir() 
	{
		return btnElegir;
	}
	
	public JLabel getLblFecha() {
		return lblFecha;
	}
	
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	
	public void cerrar()
	{
		//this..setText(null);
		this.dispose();
	}
}

