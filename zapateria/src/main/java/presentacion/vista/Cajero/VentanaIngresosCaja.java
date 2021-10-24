package presentacion.vista.Cajero;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class VentanaIngresosCaja extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtFieldIngresoSaldoInicial;
	private JTextField txtFieldRegarcaSaldo;
	private JLabel lblIngresoSaldoInicial;
	private JLabel lblRegarcaSaldo;
	private JButton btnRealizarIngreso;
	private JButton btnAtras;
	private JLabel lblActualizarEstadoCaja;
	private JLabel lblSaldoActual;
	private JLabel lblActualizarSaldoActual;
	private JLabel lblActualizarFechaHoy;
	
	
	public static void main(String[] args) {
		VentanaIngresosCaja n = new VentanaIngresosCaja();
		n.show();
	}
	
	public VentanaIngresosCaja() {
		initialize();
	}

	public void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 512, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 610, 62);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Zapater\u00EDa");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(10, 5, 590, 52);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 62, 496, 86);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ingresos de Caja");
		lblNewLabel_1.setBounds(10, 5, 208, 70);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estado de la Caja:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(207, 36, 142, 14);
		panel_1.add(lblNewLabel_2);
		
		lblActualizarEstadoCaja = new JLabel("Sin estado");
		lblActualizarEstadoCaja.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActualizarEstadoCaja.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarEstadoCaja.setBounds(207, 61, 142, 14);
		panel_1.add(lblActualizarEstadoCaja);
		
		lblSaldoActual = new JLabel("Saldo actual:");
		lblSaldoActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldoActual.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSaldoActual.setBounds(344, 36, 142, 14);
		panel_1.add(lblSaldoActual);
		
		lblActualizarSaldoActual = new JLabel("$0");
		lblActualizarSaldoActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarSaldoActual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActualizarSaldoActual.setBounds(344, 61, 142, 14);
		panel_1.add(lblActualizarSaldoActual);
		
		lblActualizarFechaHoy = new JLabel("");
		lblActualizarFechaHoy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblActualizarFechaHoy.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarFechaHoy.setBounds(287, 5, 125, 14);
		panel_1.add(lblActualizarFechaHoy);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 148, 496, 145);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		lblIngresoSaldoInicial = new JLabel("Ingresar saldo inicial:");
		lblIngresoSaldoInicial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIngresoSaldoInicial.setBounds(70, 11, 205, 32);
		panel_2.add(lblIngresoSaldoInicial);
		
		txtFieldIngresoSaldoInicial = new JTextField();
		txtFieldIngresoSaldoInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				
				boolean numeros = key >=48 && key<=57;
				if(txtFieldIngresoSaldoInicial.getText().length()>=9 || !numeros) {
					e.consume();
				}
			}
		});
		txtFieldIngresoSaldoInicial.setBounds(251, 15, 136, 28);
		panel_2.add(txtFieldIngresoSaldoInicial);
		txtFieldIngresoSaldoInicial.setColumns(10);
		
		lblRegarcaSaldo = new JLabel("Ingresar recarga de saldo:");
		lblRegarcaSaldo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRegarcaSaldo.setBounds(36, 54, 205, 32);
		panel_2.add(lblRegarcaSaldo);
		
		txtFieldRegarcaSaldo = new JTextField();
		txtFieldRegarcaSaldo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				
				boolean numeros = key >=48 && key<=57;
				if(txtFieldRegarcaSaldo.getText().length()>=9 || !numeros) {
					e.consume();
				}
			}
		});
		txtFieldRegarcaSaldo.setColumns(10);
		txtFieldRegarcaSaldo.setBounds(251, 58, 136, 28);
		panel_2.add(txtFieldRegarcaSaldo);
		
		btnRealizarIngreso = new JButton("Realizar Ingreso");
		btnRealizarIngreso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRealizarIngreso.setBounds(229, 97, 158, 32);
		panel_2.add(btnRealizarIngreso);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(10, 97, 98, 32);
		panel_2.add(btnAtras);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
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
		this.frame.setVisible(true);
	}
	
	public void limpiarCampos() {
		txtFieldIngresoSaldoInicial.setText(null);
		txtFieldRegarcaSaldo.setText(null);
	}
	
	public void cerrar() {
		limpiarCampos();
		frame.setVisible(false);
	}

	public void mostrarSaldoActual() {
		this.lblSaldoActual.setVisible(true);
		this.lblActualizarSaldoActual.setVisible(true);
	}
	public void ocultarSaldoActual() {
		this.lblSaldoActual.setVisible(false);
		this.lblActualizarSaldoActual.setVisible(false);
	}
	
	public JLabel getLblActualizarSaldoActual() {
		return lblActualizarSaldoActual;
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JLabel getLblActualizarFechaHoy() {
		return lblActualizarFechaHoy;
	}
	
	public void mostrarHoraHoy() {
		this.lblActualizarFechaHoy.setVisible(true);
	}
	
	public void ocultarHoraHoy() {
		this.lblActualizarFechaHoy.setVisible(false);
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JLabel getLblActualizarEstadoCaja() {
		return lblActualizarEstadoCaja;
	}

	public void mostrarIngresarSaldoInicial() {
		lblIngresoSaldoInicial.setVisible(true);
		txtFieldIngresoSaldoInicial.setVisible(true);
		ocultarIngresarRecargaSaldo();
	}
	
	public void ocultarIngresarSaldoInicial() {
		lblIngresoSaldoInicial.setVisible(false);
		txtFieldIngresoSaldoInicial.setVisible(false);
	}
	
	public void mostrarIngresarRecargaSaldo() {
		lblRegarcaSaldo.setVisible(true);
		txtFieldRegarcaSaldo.setVisible(true);
		ocultarIngresarSaldoInicial();
	}
	
	public void ocultarIngresarRecargaSaldo() {
		lblRegarcaSaldo.setVisible(false);
		txtFieldRegarcaSaldo.setVisible(false);		
	}
	
	public JTextField getTxtFieldIngresoSaldoInicial() {
		return txtFieldIngresoSaldoInicial;
	}

	public JTextField getTxtFieldRecargaSaldo() {
		return txtFieldRegarcaSaldo;
	}

	public JLabel getLblIngresoSaldoInicial() {
		return lblIngresoSaldoInicial;
	}

	public JLabel getLblRegarcaSaldo() {
		return lblRegarcaSaldo;
	}

	public JButton getBtnRealizarIngreso() {
		return btnRealizarIngreso;
	}
}
