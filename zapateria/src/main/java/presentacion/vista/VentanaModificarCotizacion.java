package presentacion.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaModificarCotizacion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Cod. Moneda", "Descripcion", "Tasa Conversion" };
	private DefaultTableModel modelProducto;
	private JTable tablaMedioPago;
	private JLabel lblDescripcion;
	private JLabel lblActualizarDescripcion;
	private JLabel lblTasaConvercion;
	private JLabel lblFiltrarPor;
	private JLabel lblModificarCotizacion;
	private JLabel lblDatosModificables;
	private JScrollPane spMedioPago;
	private JTextField txtFiltroDescripcion;
	private JTextField txtActualizarTasaConvercion;
	private JButton btnActualizarCotizacion;
	private JButton btnVerHistorialDeCambios;
	private JButton btnAtras;
	private JPanel panel_1;
	private JTextField textFiltroCodMoneda;
	private JLabel lblCodMoneda;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblActualizar;
	private JLabel lblAtras;
	private JLabel lblHistorialDeCambios;

	public static void main(String[] args) {
		VentanaModificarCotizacion v = new VentanaModificarCotizacion();
		v.show();
	}
	
	public VentanaModificarCotizacion() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 517, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 162, 501, 350);
		frame.getContentPane().add(panel);
		frame.setLocationRelativeTo(null);
		panel.setLayout(null);

		spMedioPago = new JScrollPane();
		spMedioPago.setBounds(10, 11, 481, 145);
		panel.add(spMedioPago);

		
		modelProducto = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if(columnas == 3) {
                    return true;
                }else {
                    return false;
                }
            }
		};
		tablaMedioPago = new JTable(modelProducto);
		
		tablaMedioPago.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaMedioPago.getColumnModel().getColumn(0).setResizable(false);
		tablaMedioPago.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaMedioPago.getColumnModel().getColumn(1).setResizable(false);

		spMedioPago.setViewportView(tablaMedioPago);

		btnAtras = new JButton("");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtras.setBounds(25, 272, 50, 50);
		cambiarIconoBotones(btnAtras,  "back2.png");
		panel.add(btnAtras);

		lblActualizarDescripcion = new JLabel("Descripci\u00F3n");
		lblActualizarDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblActualizarDescripcion.setBounds(150, 192, 151, 21);
		panel.add(lblActualizarDescripcion);

		lblTasaConvercion = new JLabel("Tasa Conversion");
		lblTasaConvercion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTasaConvercion.setBounds(51, 219, 89, 14);
		panel.add(lblTasaConvercion);

		txtActualizarTasaConvercion = new JTextField();
		txtActualizarTasaConvercion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtActualizarTasaConvercion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57 || key == 46;
				if (txtActualizarTasaConvercion.getText().length() >= 9 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarTasaConvercion.setBounds(150, 215, 151, 23);
		panel.add(txtActualizarTasaConvercion);

		btnActualizarCotizacion = new JButton("");
		btnActualizarCotizacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnActualizarCotizacion.setBounds(311, 201, 50, 50);
		cambiarIconoBotones(btnActualizarCotizacion,  "update.png");
		panel.add(btnActualizarCotizacion);

		lblDatosModificables = new JLabel("Datos modificables de moneda seleccionada");
		lblDatosModificables.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDatosModificables.setBounds(10, 167, 307, 14);
		panel.add(lblDatosModificables);

		btnVerHistorialDeCambios = new JButton("");
		btnVerHistorialDeCambios.setBounds(266, 272, 50, 50);
		cambiarIconoBotones(btnVerHistorialDeCambios,  "history.png");
		panel.add(btnVerHistorialDeCambios);
		btnVerHistorialDeCambios.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblActualizar = new JLabel("Actualizar");
		lblActualizar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblActualizar.setBounds(371, 199, 86, 50);
		panel.add(lblActualizar);
		
		lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(85, 272, 50, 50);
		panel.add(lblAtras);
		
		lblHistorialDeCambios = new JLabel("Historial de Cambios");
		lblHistorialDeCambios.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblHistorialDeCambios.setBounds(326, 272, 151, 50);
		panel.add(lblHistorialDeCambios);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBounds(0, 86, 501, 415);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(139, 24, 158, 29);
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_1.add(lblDescripcion);

		txtFiltroDescripcion = new JTextField();

		txtFiltroDescripcion.setBounds(139, 52, 158, 20);
		panel_1.add(txtFiltroDescripcion);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFiltrarPor.setBounds(10, 0, 86, 29);
		panel_1.add(lblFiltrarPor);
		 
		textFiltroCodMoneda = new JTextField();
		textFiltroCodMoneda.setBounds(10, 52, 119, 20);
		panel_1.add(textFiltroCodMoneda);
		
		lblCodMoneda = new JLabel("Cod. Moneda");
		lblCodMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodMoneda.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCodMoneda.setBounds(10, 24, 119, 29);
		panel_1.add(lblCodMoneda);

		lblModificarCotizacion = new JLabel("Modificar Cotizaci\u00F3n");
		lblModificarCotizacion.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblModificarCotizacion.setBounds(10, 50, 222, 36);
		frame.getContentPane().add(lblModificarCotizacion);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 501, 50);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Zapateria Argento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 0, 421, 50);
		panel_2.add(lblNewLabel);
	}

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	
	public JTextField getTxtFiltroCodMoneda() {
		return textFiltroCodMoneda;
	}

	public JLabel getLblActualizarDescripcion() {
		return lblActualizarDescripcion;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelProducto() {
		return modelProducto;
	}

	public JTable getTablaMedioPago() {
		return tablaMedioPago;
	}

	public JTextField getTxtFiltroDescripcion() {
		return txtFiltroDescripcion;
	}

	public JTextField getTxtActualizarTasaConvercion() {
		return txtActualizarTasaConvercion;
	}

	public JButton getBtnActualizarCotizacion() {
		return btnActualizarCotizacion;
	}

	public JButton getBtnVerHistorialDeCambios() {
		return btnVerHistorialDeCambios;
	}

	public JButton getBtnAtras() {
		return btnAtras;
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
		this.txtFiltroDescripcion.setText(null);
		this.lblActualizarDescripcion.setText("Descripción");
		this.txtActualizarTasaConvercion.setText(null);
	}

	public void cerrar() {
		this.txtFiltroDescripcion.setText(null);
		this.lblActualizarDescripcion.setText("Descripción");
		this.txtActualizarTasaConvercion.setText(null);
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

}