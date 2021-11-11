package presentacion.vista.Cajero;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;

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
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JPanel panel_1;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_6;

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
		frame.setBounds(100, 100, 501, 478);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 485, 53);
		contentPane.add(panel);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);
		
		lblNewLabel = new JLabel("Sucursal:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(266, 5, 59, 19);
		panel.add(lblNewLabel);
		
		lblNewLabel_6 = new JLabel("Empleado:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(266, 28, 59, 19);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_1 = new JLabel("Tipo de Egreso");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(20, 71, 160, 42);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Medio Pago");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(20, 198, 160, 42);
		contentPane.add(lblNewLabel_1_1);

		btnConfirmarEgreso = new JButton("");
		btnConfirmarEgreso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirmarEgreso.setBounds(273, 368, 60, 60);
		cambiarIconoBotones(btnConfirmarEgreso,  "cashier2.png");
		contentPane.add(btnConfirmarEgreso);

		btnAtras = new JButton("");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtras.setBounds(40, 368, 60, 60);
		cambiarIconoBotones(btnAtras,  "back2.png");
		contentPane.add(btnAtras);

		JLabel lblNewLabel_2 = new JLabel("Monto");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(272, 210, 130, 22);
		contentPane.add(lblNewLabel_2);

		txtFieldMonto = new JTextField();
		txtFieldMonto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
		txtFieldMonto.setBounds(269, 243, 133, 29);
		contentPane.add(txtFieldMonto);
		txtFieldMonto.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Disponible en caja:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(257, 283, 160, 29);
		contentPane.add(lblNewLabel_3);

		lblActualizarTotalBalanceCaja = new JLabel("$0");
		lblActualizarTotalBalanceCaja.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblActualizarTotalBalanceCaja.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarTotalBalanceCaja.setBounds(272, 308, 133, 22);
		contentPane.add(lblActualizarTotalBalanceCaja);

		cbTipoEgreso = new JComboBox<String>();
		cbTipoEgreso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		cbTipoEgreso.setBounds(20, 124, 160, 22);
		contentPane.add(cbTipoEgreso);

		cbTipoMedioPago = new JComboBox<String>();
		cbTipoMedioPago.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cbTipoMedioPago.setBounds(20, 246, 160, 22);
		contentPane.add(cbTipoMedioPago);

		JLabel lblNewLabel_2_1 = new JLabel("Detalle");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(272, 83, 130, 22);
		contentPane.add(lblNewLabel_2_1);

		lblAS = new JLabel("N\u00B0 Legajo:");
		lblAS.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAS.setBounds(219, 143, 58, 18);
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
		txtFieldAS.setBounds(282, 143, 110, 20);
		contentPane.add(txtFieldAS);
		txtFieldAS.setColumns(10);

		lblFA = new JLabel("Fecha de hoy:");
		lblFA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFA.setHorizontalAlignment(SwingConstants.CENTER);
		lblFA.setBounds(272, 116, 130, 14);
		contentPane.add(lblFA);

		lblActualizarFechaFA = new JLabel("0000-00-00");
		lblActualizarFechaFA.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarFechaFA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblActualizarFechaFA.setBounds(272, 149, 130, 22);
		contentPane.add(lblActualizarFechaFA);

		lblPP1 = new JLabel("Nro. Proveedor");
		lblPP1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
		lblNroOrdenDe.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNroOrdenDe.setBounds(348, 124, 130, 14);
		contentPane.add(lblNroOrdenDe);

		txtFieldPPNroOrdenCompra = new JTextField();
		txtFieldPPNroOrdenCompra.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
		lblNC.setBounds(257, 128, 160, 14);
		lblNC.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		contentPane.add(lblNC);

		txtFieldNC = new JTextField();
		txtFieldNC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				/*
				boolean numeros = key >= 48 && key <= 57;
				if (txtFieldNC.getText().length() >= 9 || !numeros) {
					e.consume();
				}*/
				if (txtFieldNC.getText().length() >= 9) {
					e.consume();
				}
			}
		});
		txtFieldNC.setBounds(287, 143, 99, 20);
		contentPane.add(txtFieldNC);
		txtFieldNC.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Atras");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(110, 368, 38, 60);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Realizar Egreso");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(343, 368, 117, 60);
		contentPane.add(lblNewLabel_5);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(201, 71, 8, 277);
		contentPane.add(separator);
		

		cbTipoEgreso.insertItemAt("", 0);
		cbTipoMedioPago.insertItemAt("",0);
		
		ocultarDetalles();
		limpiarCampos();
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255, 180));
		panel_1.setBounds(0, 65, 485, 295);
		contentPane.add(panel_1);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 53, 640, 480);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");

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
	
	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
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
		this.cbTipoEgreso.setSelectedIndex(0);
		this.cbTipoMedioPago.removeAllItems();
		this.txtFieldAS.setText(null);
		this.txtFieldMonto.setText(null);
		this.txtFieldPPNroOrdenCompra.setText(null);
		this.txtFieldPPNroProveedor.setText(null);
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
