package presentacion.vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import persistencia.conexion.Conexion;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class VentanaTareasAutomatizadas {

	private JFrame frame;
	
	private JComboBox<String> comboBoxPedidosDias;
	private JSpinner spinnerPedidosHora;	
	private JSpinner spinnerTolerancia;
	private JComboBox<String> comboBoxFrecuenciaProcesamientoCompraVirtuales;
	private JButton btnActualizar;
	private JButton btnRegresar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTareasAutomatizadas window = new VentanaTareasAutomatizadas();
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
	public VentanaTareasAutomatizadas() {
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
		frame.setBounds(100, 100, 1125, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1111, 567);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panelParametros = new JPanel();
		panelParametros.setBounds(10, 144, 1091, 412);
		panel.add(panelParametros);
		panelParametros.setLayout(null);
		
		JLabel lblComprasVirtuales = new JLabel("Compras Virtuales");
		lblComprasVirtuales.setFont(new Font("Consolas", Font.PLAIN, 30));
		lblComprasVirtuales.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprasVirtuales.setBounds(10, 36, 393, 50);
		panelParametros.add(lblComprasVirtuales);
		
		JLabel lblPedidosAProveedor = new JLabel("Pedidos a Proveedor");
		lblPedidosAProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedidosAProveedor.setFont(new Font("Consolas", Font.PLAIN, 30));
		lblPedidosAProveedor.setBounds(582, 36, 393, 50);
		panelParametros.add(lblPedidosAProveedor);
				
		JLabel lblLosDias = new JLabel("Los dias: ");
		lblLosDias.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblLosDias.setBounds(582, 114, 286, 28);
		panelParametros.add(lblLosDias);
			
		JLabel lblALas = new JLabel("A las:");
		lblALas.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblALas.setBounds(582, 175, 286, 28);
		panelParametros.add(lblALas);
				
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 96, 223, 93);
		lblNewLabel_1.setText("<html>"+"Tolerancia de desfasaje entre precio de compra y precio de venta"+"<html>");
		panelParametros.add(lblNewLabel_1);
				
		JLabel lblNewLabel_2 = new JLabel("%");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(304, 112, 29, 32);
		panelParametros.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel();
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setText("<html>Procesarc compras virtuales cada: <html>");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(20, 227, 223, 93);
		panelParametros.add(lblNewLabel_1_1);		
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 1101, 63);
		panel.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("ZAPATERIA");
		lblTitulo.setBounds(0, 10, 206, 48);
		panelTitulo.add(lblTitulo);
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 34));
		
		JLabel lblNewLabel = new JLabel("Parametros de tareas automatizadas");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 44));
		lblNewLabel.setBounds(10, 83, 1091, 63);
		panel.add(lblNewLabel);
		
		
		

		
		spinnerPedidosHora = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor de_spinnerHora = new JSpinner.DateEditor(spinnerPedidosHora, "HH:mm:ss");
		spinnerPedidosHora.setEditor(de_spinnerHora);
		spinnerPedidosHora.setBounds(867, 175, 180, 25);
		panelParametros.add(spinnerPedidosHora);
		
		SpinnerModel sm = new SpinnerNumberModel(0, 0, 100, 1); //default value,lower bound,upper bound,increment by
		spinnerTolerancia = new JSpinner(sm);
		spinnerTolerancia.setBounds(239, 109, 60, 35);
		panelParametros.add(spinnerTolerancia);
		

		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
		btnActualizar.setBounds(303, 330, 375, 60);
		panelParametros.add(btnActualizar);

		comboBoxPedidosDias = new JComboBox<String>();
		comboBoxPedidosDias.setBounds(867, 115, 214, 25);
		panelParametros.add(comboBoxPedidosDias);
		
		comboBoxFrecuenciaProcesamientoCompraVirtuales = new JComboBox<String>();
		comboBoxFrecuenciaProcesamientoCompraVirtuales.setBounds(239, 228, 214, 25);
		panelParametros.add(comboBoxFrecuenciaProcesamientoCompraVirtuales);
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(10, 355, 44, 47);
		cambiarIconoBotones(btnRegresar, "back.png");
		panelParametros.add(btnRegresar);
	}

	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?. Se perderá todos los progresos que se hayan realizado", 
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
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_DEFAULT));
		boton.setIcon(Icono);
	}
	
	public JComboBox<String> getComboBoxPedidosDias() {
		return comboBoxPedidosDias;
	}

	public JSpinner getSpinnerPedidosHora() {
		return spinnerPedidosHora;
	}

	public JSpinner getSpinnerTolerancia() {
		return spinnerTolerancia;
	}

	public JComboBox<String> getComboBoxFrecuenciaProcesamientoCompraVirtuales() {
		return comboBoxFrecuenciaProcesamientoCompraVirtuales;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

}

