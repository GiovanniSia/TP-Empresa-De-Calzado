package presentacion.vista.gerente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import persistencia.conexion.Conexion;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VentanaAltaCliente {

	private JFrame frame;
	
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCUIL;
	private JTextField textCorreo;
	private JTextField textFieldPais;
	private JTextField textFieldProvincia;
	private JTextField textFieldLocalidad;
	private JTextField textFieldCalle;
	private JTextField textFieldAltura;
	private JTextField textFieldCodPostal;

	
	private JButton btnRegistrar;
	private JButton btnCancelar;

	private JComboBox comboBoxImpuestoAFIP;
	private JComboBox comboBoxTipoCliente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaCliente window = new VentanaAltaCliente();
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
	public VentanaAltaCliente() {
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
		frame.setBounds(100, 100, 427, 712);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Registrar Cliente");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 393, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 114, 94, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(128, 113, 275, 19);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(128, 142, 275, 19);
		frame.getContentPane().add(textApellido);
		
		textCUIL = new JTextField();
		textCUIL.setColumns(10);
		textCUIL.setBounds(128, 171, 275, 19);
		frame.getContentPane().add(textCUIL);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 142, 94, 19);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Cuil");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(10, 169, 94, 24);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setBounds(128, 206, 275, 19);
		frame.getContentPane().add(textCorreo);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Correo");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(10, 203, 94, 24);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Tipo de Cliente");
		lblNewLabel_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_2_1_1.setBounds(10, 247, 137, 24);
		frame.getContentPane().add(lblNewLabel_1_2_1_1);
		
		
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Impuesto AFIP");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(10, 281, 137, 18);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		

		
		JLabel lblNewLabel_1_3 = new JLabel("Pais");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(10, 359, 94, 24);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Provincia");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_3_1.setBounds(10, 398, 116, 24);
		frame.getContentPane().add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Localidad");
		lblNewLabel_1_3_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_3_1_1.setBounds(10, 432, 116, 24);
		frame.getContentPane().add(lblNewLabel_1_3_1_1);
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel("Calle");
		lblNewLabel_1_3_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1_1_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_3_1_1_1.setBounds(10, 466, 116, 24);
		frame.getContentPane().add(lblNewLabel_1_3_1_1_1);
		
		JLabel lblNewLabel_1_3_1_1_1_1 = new JLabel("Altura");
		lblNewLabel_1_3_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1_1_1_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_3_1_1_1_1.setBounds(10, 500, 116, 24);
		frame.getContentPane().add(lblNewLabel_1_3_1_1_1_1);
		
		JLabel lblNewLabel_1_3_1_1_1_1_1 = new JLabel("Cod Postal");
		lblNewLabel_1_3_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1_1_1_1_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1_3_1_1_1_1_1.setBounds(10, 533, 116, 24);
		frame.getContentPane().add(lblNewLabel_1_3_1_1_1_1_1);
		
		textFieldPais = new JTextField();
		textFieldPais.setColumns(10);
		textFieldPais.setBounds(128, 361, 275, 19);
		frame.getContentPane().add(textFieldPais);
		
		textFieldProvincia = new JTextField();
		textFieldProvincia.setColumns(10);
		textFieldProvincia.setBounds(128, 400, 275, 19);
		frame.getContentPane().add(textFieldProvincia);
		
		textFieldLocalidad = new JTextField();
		textFieldLocalidad.setColumns(10);
		textFieldLocalidad.setBounds(128, 434, 275, 19);
		frame.getContentPane().add(textFieldLocalidad);
		
		textFieldCalle = new JTextField();
		textFieldCalle.setColumns(10);
		textFieldCalle.setBounds(128, 468, 275, 19);
		frame.getContentPane().add(textFieldCalle);
		
		textFieldAltura = new JTextField();
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(128, 500, 137, 19);
		frame.getContentPane().add(textFieldAltura);
		
		textFieldCodPostal = new JTextField();
		textFieldCodPostal.setColumns(10);
		textFieldCodPostal.setBounds(128, 535, 137, 19);
		frame.getContentPane().add(textFieldCodPostal);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(128, 592, 179, 35);
		frame.getContentPane().add(btnRegistrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 642, 75, 30);
		frame.getContentPane().add(btnCancelar);
		
		comboBoxImpuestoAFIP = new JComboBox();
		comboBoxImpuestoAFIP.setBounds(157, 280, 131, 23);
		this.comboBoxImpuestoAFIP.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxImpuestoAFIP);
		
		comboBoxTipoCliente = new JComboBox();
		comboBoxTipoCliente.setBounds(157, 247, 131, 23);
		this.comboBoxTipoCliente.addItem("Sin seleccionar");
		frame.getContentPane().add(comboBoxTipoCliente);
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
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextNombre() {
		return textNombre;
	}

	public JTextField getTextApellido() {
		return textApellido;
	}

	public JTextField getTextCUIL() {
		return textCUIL;
	}

	public JTextField getTextCorreo() {
		return textCorreo;
	}

	public JTextField getTextFieldPais() {
		return textFieldPais;
	}

	public JTextField getTextFieldProvincia() {
		return textFieldProvincia;
	}

	public JTextField getTextFieldLocalidad() {
		return textFieldLocalidad;
	}

	public JTextField getTextFieldCalle() {
		return textFieldCalle;
	}

	public JTextField getTextFieldAltura() {
		return textFieldAltura;
	}

	public JTextField getTextFieldCodPostal() {
		return textFieldCodPostal;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	public JComboBox getComboBoxImpuestoAFIP() {
		return comboBoxImpuestoAFIP;
	}

	public JComboBox getComboBoxTipoCliente() {
		return comboBoxTipoCliente;
	}

}
