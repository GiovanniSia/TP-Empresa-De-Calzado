package presentacion.vista.Supervisor;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import persistencia.conexion.Conexion;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;

public class VentanaAltaProducto {

	private JFrame frame;
	
	
	
	
	private JTextField textDescripcion;
	
	private JTextField textCosto;
	private JTextField textPrecioMayorista;
	private JTextField textPrecioMinorista;
	private JTextField textPuntoRepMinimo;
	private JTextField textTalle;
	private JTextField textUnidadMedida;
	private JTextField textDiasParaReponer;

	private JButton btnRegistrar;
	private JButton btnRegresar;
	
	private JComboBox comboBoxEstado;
	private JComboBox comboBoxProveedorPreferenciado;
	private JComboBox comboBoxFabricado;
	private JComboBox comboBoxTipo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaProducto window = new VentanaAltaProducto();
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
	public VentanaAltaProducto() {
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
		frame.setBounds(500, 100, 427, 656);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Registrar Producto");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 403, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 50, 203, 19);
		frame.getContentPane().add(lblNewLabel_1);
		

		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setFont(new Font("Consolas", Font.BOLD, 14));
		lblTipo.setBounds(20, 90, 203, 19);
		frame.getContentPane().add(lblTipo);
		

		
		JLabel lblProductoPropio = new JLabel("Producto Propio?");
		lblProductoPropio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductoPropio.setFont(new Font("Consolas", Font.BOLD, 14));
		lblProductoPropio.setBounds(20, 130, 203, 19);
		frame.getContentPane().add(lblProductoPropio);
		

		
		JLabel lblCostoDeProduccion = new JLabel("Costo de Produccion");
		lblCostoDeProduccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCostoDeProduccion.setFont(new Font("Consolas", Font.BOLD, 14));
		lblCostoDeProduccion.setBounds(20, 172, 203, 19);
		frame.getContentPane().add(lblCostoDeProduccion);
		

		
		JLabel lblPrecioMayorista = new JLabel("Precio Mayorista");
		lblPrecioMayorista.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioMayorista.setFont(new Font("Consolas", Font.BOLD, 14));
		lblPrecioMayorista.setBounds(20, 213, 203, 19);
		frame.getContentPane().add(lblPrecioMayorista);
		

		
		JLabel lblPrecioMinorista = new JLabel("Precio Minorista");
		lblPrecioMinorista.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioMinorista.setFont(new Font("Consolas", Font.BOLD, 14));
		lblPrecioMinorista.setBounds(20, 255, 203, 19);
		frame.getContentPane().add(lblPrecioMinorista);
		

		
		JLabel lblPuntoDeReposicion = new JLabel("Punto de reposicion minimo");
		lblPuntoDeReposicion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntoDeReposicion.setFont(new Font("Consolas", Font.BOLD, 13));
		lblPuntoDeReposicion.setBounds(35, 295, 203, 19);
		frame.getContentPane().add(lblPuntoDeReposicion);
		

		
		JLabel lblProveedorPreferenciado = new JLabel("Proveedor Preferenciado");
		lblProveedorPreferenciado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProveedorPreferenciado.setFont(new Font("Consolas", Font.BOLD, 14));
		lblProveedorPreferenciado.setBounds(20, 329, 203, 19);
		frame.getContentPane().add(lblProveedorPreferenciado);
		

		
		JLabel lblTalle = new JLabel("Talle");
		lblTalle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTalle.setFont(new Font("Consolas", Font.BOLD, 14));
		lblTalle.setBounds(20, 365, 203, 19);
		frame.getContentPane().add(lblTalle);
		

		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnidadDeMedida.setFont(new Font("Consolas", Font.BOLD, 14));
		lblUnidadDeMedida.setBounds(20, 402, 203, 19);
		frame.getContentPane().add(lblUnidadDeMedida);
		

		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setFont(new Font("Consolas", Font.BOLD, 14));
		lblEstado.setBounds(20, 441, 203, 19);
		frame.getContentPane().add(lblEstado);
		

		
		JLabel lblDiasParaReponer = new JLabel("Dias para reponer");
		lblDiasParaReponer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiasParaReponer.setFont(new Font("Consolas", Font.BOLD, 14));
		lblDiasParaReponer.setBounds(20, 479, 203, 19);
		frame.getContentPane().add(lblDiasParaReponer);
	
		textDescripcion = new JTextField();
		textDescripcion.setBounds(235, 50, 178, 19);
		frame.getContentPane().add(textDescripcion);
		textDescripcion.setColumns(10);
		
		textCosto = new JTextField();
		textCosto.setColumns(10);
		textCosto.setBounds(235, 171, 178, 19);
		frame.getContentPane().add(textCosto);
		
		textPrecioMayorista = new JTextField();
		textPrecioMayorista.setColumns(10);
		textPrecioMayorista.setBounds(235, 212, 178, 19);
		frame.getContentPane().add(textPrecioMayorista);
		
		textPrecioMinorista = new JTextField();
		textPrecioMinorista.setColumns(10);
		textPrecioMinorista.setBounds(235, 254, 178, 19);
		frame.getContentPane().add(textPrecioMinorista);
		
		textPuntoRepMinimo = new JTextField();
		textPuntoRepMinimo.setColumns(10);
		textPuntoRepMinimo.setBounds(235, 293, 178, 19);
		frame.getContentPane().add(textPuntoRepMinimo);
		
		textTalle = new JTextField();
		textTalle.setColumns(10);
		textTalle.setBounds(235, 364, 178, 19);
		frame.getContentPane().add(textTalle);
		
		textDiasParaReponer = new JTextField();
		textDiasParaReponer.setColumns(10);
		textDiasParaReponer.setBounds(235, 478, 178, 19);
		frame.getContentPane().add(textDiasParaReponer);
		
		textUnidadMedida = new JTextField();
		textUnidadMedida.setColumns(10);
		textUnidadMedida.setBounds(235, 401, 178, 19);
		frame.getContentPane().add(textUnidadMedida);
		
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(10, 553, 50, 58);
		frame.getContentPane().add(btnRegresar);
		
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(118, 526, 184, 33);
		frame.getContentPane().add(btnRegistrar);
		frame.setTitle("Zapateria Argento - Registrar un nuevo producto");
		frame.setResizable(false);
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(235, 440, 178, 19);
		frame.getContentPane().add(comboBoxEstado);
		
		comboBoxProveedorPreferenciado = new JComboBox();
		comboBoxProveedorPreferenciado.setBounds(235, 328, 178, 19);
		frame.getContentPane().add(comboBoxProveedorPreferenciado);
		
		comboBoxFabricado = new JComboBox();
		comboBoxFabricado.setBounds(235, 129, 178, 19);
		frame.getContentPane().add(comboBoxFabricado);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(235, 89, 178, 19);
		frame.getContentPane().add(comboBoxTipo);
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
	
	public Icon setIcono(String url,JButton boton) {
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
	}
	
	
	public JTextField getTextDescripcion() {
		return textDescripcion;
	}

	public JTextField getTextCosto() {
		return textCosto;
	}

	public JTextField getTextPrecioMayorista() {
		return textPrecioMayorista;
	}

	public JTextField getTextPrecioMinorista() {
		return textPrecioMinorista;
	}

	public JTextField getTextPuntoRepMinimo() {
		return textPuntoRepMinimo;
	}

	public JTextField getTextTalle() {
		return textTalle;
	}

	public JTextField getTextUnidadMedida() {
		return textUnidadMedida;
	}

	public JTextField getTextDiasParaReponer() {
		return textDiasParaReponer;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JComboBox getComboBoxEstado() {
		return comboBoxEstado;
	}

	public JComboBox getComboBoxProveedorPreferenciado() {
		return comboBoxProveedorPreferenciado;
	}

	public JComboBox getComboBoxFabricado() {
		return comboBoxFabricado;
	}

	public JComboBox getComboBoxTipo() {
		return comboBoxTipo;
	}

}
