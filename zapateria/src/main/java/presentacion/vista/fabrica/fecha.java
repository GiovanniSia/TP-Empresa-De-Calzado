package presentacion.vista.fabrica;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JComboBox;

public class fecha extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnElegir;
	private static fecha INSTANCE;
	
	private JLabel lblFecha;
	
	private JComboBox comboBox;
	
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
		
		comboBox = new JComboBox();
		comboBox.setBounds(248, 7, 89, 22);
		panel.add(comboBox);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JButton getBtnElegir() 
	{
		return btnElegir;
	}
	
	public JLabel getLblFecha() {
		return lblFecha;
	}
	
	public JComboBox getComboBox() {
		return comboBox;
	}
	
	public void cerrar()
	{
		//this..setText(null);
		this.dispose();
	}
}

