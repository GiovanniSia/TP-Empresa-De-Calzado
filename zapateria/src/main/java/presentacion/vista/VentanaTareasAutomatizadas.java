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
import javax.swing.JSeparator;

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
		frame.setBounds(100, 100, 660, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 0, 806, 550);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panelParametros = new JPanel();
		panelParametros.setBackground(new Color(248, 248, 255));
		panelParametros.setBounds(10, 99, 626, 440);
		panel.add(panelParametros);
		panelParametros.setLayout(null);
		
		JLabel lblComprasVirtuales = new JLabel("Compras Virtuales");
		lblComprasVirtuales.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblComprasVirtuales.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprasVirtuales.setBounds(10, 36, 294, 50);
		panelParametros.add(lblComprasVirtuales);
		
		JLabel lblPedidosAProveedor = new JLabel("Pedidos a Proveedor");
		lblPedidosAProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedidosAProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblPedidosAProveedor.setBounds(336, 36, 294, 50);
		panelParametros.add(lblPedidosAProveedor);
				
		JLabel lblLosDias = new JLabel("Los dias: ");
		lblLosDias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLosDias.setBounds(368, 126, 109, 28);
		panelParametros.add(lblLosDias);
			
		JLabel lblALas = new JLabel("A las:");
		lblALas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblALas.setBounds(368, 174, 109, 28);
		panelParametros.add(lblALas);
				
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 96, 197, 93);
		lblNewLabel_1.setText("<html>"+"Tolerancia de desfasaje entre precio de compra y precio de venta"+"<html>");
		panelParametros.add(lblNewLabel_1);
				
		JLabel lblNewLabel_2 = new JLabel("%");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(284, 123, 29, 35);
		panelParametros.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel();
		lblNewLabel_1_1.setText("<html>Procesar compras virtuales cada: <html>");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(20, 227, 141, 60);
		panelParametros.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("Parametros de tareas automatizadas");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 50, 391, 50);
		panel.add(lblNewLabel);
		
		
		

		
		spinnerPedidosHora = new JSpinner(new SpinnerDateModel());
		spinnerPedidosHora.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		JSpinner.DateEditor de_spinnerHora = new JSpinner.DateEditor(spinnerPedidosHora, "HH:mm:ss");
		spinnerPedidosHora.setEditor(de_spinnerHora);
		spinnerPedidosHora.setBounds(487, 177, 104, 25);
		panelParametros.add(spinnerPedidosHora);
		
		spinnerTolerancia = new JSpinner();
		spinnerTolerancia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinnerTolerancia.setBounds(227, 124, 60, 35);
		panelParametros.add(spinnerTolerancia);
		

		
		btnActualizar = new JButton("");
		btnActualizar.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
		btnActualizar.setBounds(356, 341, 60, 60);
		cambiarIconoBotones(btnActualizar, "update.png");
		panelParametros.add(btnActualizar);

		comboBoxPedidosDias = new JComboBox<String>();
		comboBoxPedidosDias.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxPedidosDias.setBounds(487, 129, 104, 25);
		panelParametros.add(comboBoxPedidosDias);
		
		comboBoxFrecuenciaProcesamientoCompraVirtuales = new JComboBox<String>();
		comboBoxFrecuenciaProcesamientoCompraVirtuales.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBoxFrecuenciaProcesamientoCompraVirtuales.setBounds(178, 246, 126, 25);
		panelParametros.add(comboBoxFrecuenciaProcesamientoCompraVirtuales);
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(84, 341, 60, 60);
		cambiarIconoBotones(btnRegresar, "back2.png");
		panelParametros.add(btnRegresar);
		
		JLabel lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(151, 341, 109, 60);
		panelParametros.add(lblAtras);
		
		JLabel lblActualizar = new JLabel("Actualizar");
		lblActualizar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblActualizar.setBounds(426, 341, 109, 60);
		panelParametros.add(lblActualizar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(0, 0, 650, 50);
		panel.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Zapateria Argento");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_3.setBounds(10, 0, 421, 50);
		panel_1.add(lblNewLabel_3);
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
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
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

