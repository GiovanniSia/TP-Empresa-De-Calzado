package presentacion.vista.Cajero;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		panel_1.setBounds(0, 62, 610, 52);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ingresos de Caja");
		lblNewLabel_1.setBounds(10, 5, 377, 36);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 125, 476, 157);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		lblIngresoSaldoInicial = new JLabel("Ingresar saldo inicial:");
		lblIngresoSaldoInicial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIngresoSaldoInicial.setBounds(59, 11, 205, 32);
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
		txtFieldIngresoSaldoInicial.setBounds(240, 15, 136, 28);
		panel_2.add(txtFieldIngresoSaldoInicial);
		txtFieldIngresoSaldoInicial.setColumns(10);
		
		lblRegarcaSaldo = new JLabel("Ingresar recarga de saldo:");
		lblRegarcaSaldo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRegarcaSaldo.setBounds(25, 54, 205, 32);
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
		txtFieldRegarcaSaldo.setBounds(240, 58, 136, 28);
		panel_2.add(txtFieldRegarcaSaldo);
		
		btnRealizarIngreso = new JButton("Realizar Ingreso");
		btnRealizarIngreso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRealizarIngreso.setBounds(229, 114, 158, 32);
		panel_2.add(btnRealizarIngreso);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(10, 114, 98, 32);
		panel_2.add(btnAtras);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
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

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JButton getBtnAtras() {
		return btnAtras;
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
