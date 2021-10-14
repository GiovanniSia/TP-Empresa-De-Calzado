package presentacion.vista.Cajero;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class VentanaEgresoCaja extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtFieldMonto;
	private JComboBox<String> cbTipoEgreso;
	private JComboBox<String> cbTipoMedioPago;
	private JButton btnConfirmarEgreso;
	private JButton btnAtras;

	// AS
	private JLabel lblAS;
	private JTextField txtFieldAS;

	// FA
	private JLabel lblFA;
	private JLabel lblActualizarFechaFA;

	// PP
	private JLabel lblPP1;
	private JTextField txtFieldPPNroProveedor;
	private JLabel lblNroOrdenDe;
	private JTextField txtFieldPPNroOrdenCompra;

	// NC
	private JLabel lblNC;
	private JTextField txtFieldNC;
	
	private JLabel lblActualizarTotalBalanceCaja;

	public VentanaEgresoCaja() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 518, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 597, 68);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Zapater\u00EDa");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblNewLabel.setBounds(10, 11, 308, 46);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tipo de Egreso");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(10, 71, 236, 42);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Medio Pago");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(10, 196, 236, 42);
		contentPane.add(lblNewLabel_1_1);

		btnConfirmarEgreso = new JButton("Confirmar Egreso");
		btnConfirmarEgreso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirmarEgreso.setBounds(272, 380, 182, 34);
		contentPane.add(btnConfirmarEgreso);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtras.setBounds(10, 388, 89, 29);
		contentPane.add(btnAtras);

		JLabel lblNewLabel_2 = new JLabel("Monto");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(287, 224, 130, 22);
		contentPane.add(lblNewLabel_2);

		txtFieldMonto = new JTextField();
		txtFieldMonto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean numeros = key >= 48 && key <= 57;
				if (txtFieldMonto.getText().length() >= 9 || !numeros) {
					e.consume();
				}
			}
		});
		txtFieldMonto.setBounds(287, 257, 133, 29);
		contentPane.add(txtFieldMonto);
		txtFieldMonto.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Disponible en caja:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(272, 297, 160, 29);
		contentPane.add(lblNewLabel_3);

		lblActualizarTotalBalanceCaja = new JLabel("$0");
		lblActualizarTotalBalanceCaja.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarTotalBalanceCaja.setBounds(287, 322, 133, 22);
		contentPane.add(lblActualizarTotalBalanceCaja);

		cbTipoEgreso = new JComboBox<String>();
		
		cbTipoEgreso.setBounds(10, 124, 160, 22);
		contentPane.add(cbTipoEgreso);

		cbTipoMedioPago = new JComboBox<String>();
		cbTipoMedioPago.setBounds(10, 244, 160, 22);
		contentPane.add(cbTipoMedioPago);

		JLabel lblNewLabel_2_1 = new JLabel("Detalle");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(287, 91, 130, 22);
		contentPane.add(lblNewLabel_2_1);

		lblAS = new JLabel("Nro. Legajo:");
		lblAS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAS.setBounds(204, 145, 99, 14);
		contentPane.add(lblAS);

		txtFieldAS = new JTextField();
		txtFieldAS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean numeros = key >= 48 && key <= 57;
				if (txtFieldAS.getText().length() >= 9 || !numeros) {
					e.consume();
				}
			}
		});
		txtFieldAS.setBounds(287, 143, 130, 20);
		contentPane.add(txtFieldAS);
		txtFieldAS.setColumns(10);

		lblFA = new JLabel("Fecha de hoy:");
		lblFA.setHorizontalAlignment(SwingConstants.CENTER);
		lblFA.setBounds(287, 128, 130, 14);
		contentPane.add(lblFA);

		lblActualizarFechaFA = new JLabel("0000-00-00");
		lblActualizarFechaFA.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarFechaFA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblActualizarFechaFA.setBounds(287, 150, 130, 22);
		contentPane.add(lblActualizarFechaFA);

		lblPP1 = new JLabel("Nro. Proveedor");
		lblPP1.setBounds(219, 124, 99, 14);
		contentPane.add(lblPP1);

		txtFieldPPNroProveedor = new JTextField();
		txtFieldPPNroProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean numeros = key >= 48 && key <= 57;
				if (txtFieldPPNroProveedor.getText().length() >= 9 || !numeros) {
					e.consume();
				}
			}
		});
		txtFieldPPNroProveedor.setBounds(214, 143, 101, 20);
		contentPane.add(txtFieldPPNroProveedor);
		txtFieldPPNroProveedor.setColumns(10);

		lblNroOrdenDe = new JLabel("Nro. Orden de Compra");
		lblNroOrdenDe.setBounds(348, 124, 122, 14);
		contentPane.add(lblNroOrdenDe);

		txtFieldPPNroOrdenCompra = new JTextField();
		txtFieldPPNroOrdenCompra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean numeros = key >= 48 && key <= 57;
				if (txtFieldPPNroOrdenCompra.getText().length() >= 9 || !numeros) {
					e.consume();
				}
			}
		});
		txtFieldPPNroOrdenCompra.setColumns(10);
		txtFieldPPNroOrdenCompra.setBounds(353, 143, 101, 20);
		contentPane.add(txtFieldPPNroOrdenCompra);

		lblNC = new JLabel("Nro. Factura Relacionada");
		lblNC.setHorizontalAlignment(SwingConstants.CENTER);
		lblNC.setBounds(272, 128, 160, 14);
		contentPane.add(lblNC);

		txtFieldNC = new JTextField();
		txtFieldNC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean numeros = key >= 48 && key <= 57;
				if (txtFieldNC.getText().length() >= 9 || !numeros) {
					e.consume();
				}
			}
		});
		txtFieldNC.setBounds(287, 143, 130, 20);
		contentPane.add(txtFieldNC);
		txtFieldNC.setColumns(10);
		

		cbTipoEgreso.insertItemAt("", 0);
		cbTipoMedioPago.insertItemAt("",0);
		
		ocultarDetalles();
		limpiarCampos();

	}

	public void mostrarAS() {
		this.lblAS.setVisible(true);
		this.txtFieldAS.setVisible(true);
		ocultarFA();
		ocultarPP();
		ocultarNC();
	}

	public void ocultarAS() {
		this.lblAS.setVisible(false);
		this.txtFieldAS.setVisible(false);
	}

	public void mostrarFA() {
		this.lblFA.setVisible(true);
		this.lblActualizarFechaFA.setVisible(true);
		ocultarAS();
		ocultarPP();
		ocultarNC();
	}

	public void ocultarFA() {
		this.lblFA.setVisible(false);
		this.lblActualizarFechaFA.setVisible(false);
	}

	public void mostrarPP() {
		this.lblPP1.setVisible(true);
		;
		this.txtFieldPPNroProveedor.setVisible(true);
		this.lblNroOrdenDe.setVisible(true);
		this.txtFieldPPNroOrdenCompra.setVisible(true);
		ocultarAS();
		ocultarFA();
		ocultarNC();
	}

	public void ocultarPP() {
		this.lblPP1.setVisible(false);
		;
		this.txtFieldPPNroProveedor.setVisible(false);
		this.lblNroOrdenDe.setVisible(false);
		this.txtFieldPPNroOrdenCompra.setVisible(false);
	}

	public void mostrarNC() {
		this.lblNC.setVisible(true);
		this.txtFieldNC.setVisible(true);
		ocultarAS();
		ocultarFA();
		ocultarPP();
	}

	public void ocultarNC() {
		this.lblNC.setVisible(false);
		this.txtFieldNC.setVisible(false);
	}
	
	public String getTipoEgresoSeleccionado() {
		return (String) this.cbTipoEgreso.getSelectedItem();
	}
	
	public String getMedioPagoSeleccionado() {
		return (String) this.cbTipoMedioPago.getSelectedItem();
	}
	
	public void ocultarDetalles() {
		ocultarAS();
		ocultarFA();
		ocultarPP();
		ocultarNC();
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
		this.cbTipoEgreso.setSelectedIndex(-1);
		this.cbTipoMedioPago.setSelectedIndex(-1);
	}

	public void cerrar() {
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JTextField getTxtFieldMonto() {
		return txtFieldMonto;
	}

	public JComboBox<String> getCbTipoEgreso() {
		return cbTipoEgreso;
	}

	public JComboBox<String> getCbTipoMedioPago() {
		return cbTipoMedioPago;
	}

	public JButton getBtnConfirmarEgreso() {
		return btnConfirmarEgreso;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JTextField getTxtFieldAS() {
		return txtFieldAS;
	}

	public JLabel getLblActualizarFechaFA() {
		return lblActualizarFechaFA;
	}

	public JTextField getTxtFieldPPNroProveedor() {
		return txtFieldPPNroProveedor;
	}

	public JTextField getTxtFieldPPNroOrdenCompra() {
		return txtFieldPPNroOrdenCompra;
	}

	public JTextField getTxtFieldNC() {
		return txtFieldNC;
	}

	public JLabel getLblActualizarTotalBalanceCaja() {
		return lblActualizarTotalBalanceCaja;
	}

	public static void main(String[] args) {
		VentanaEgresoCaja a = new VentanaEgresoCaja();
		a.show();
	}

}
