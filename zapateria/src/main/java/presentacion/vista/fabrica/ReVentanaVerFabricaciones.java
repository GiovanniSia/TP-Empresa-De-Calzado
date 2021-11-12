package presentacion.vista.fabrica;

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
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

public class ReVentanaVerFabricaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Id pedido","Sucursal", "Producto", "Fecha requerido", "Cantidad", "Paso actual", "Estado", "Fecha completada", "Dias envio"};
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JScrollPane spCliente;

	private JButton btnSeleccionarProceso;
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JTextField textId;
	private JLabel lblSucursal;
	private JTextField textProducto;
	private JLabel lblProducto;
	JTextField textSucursal;
	private JButton btnFechaDesde;
	private JButton btnFechaHasta;
	private JLabel lblFechaDesde;
	private JLabel lblFechaHasta;

	private JButton btnSalir;
	private JTextField textPasoActual;
	private JTextField textEstado;
	private JCheckBox chckbxCancelados;
	private JButton btnVerHistorialPasos;
	private JLabel lblSeleccionarProceso;
	
	private JLabel lblHistorial;
	private JPanel panel_1;
	private JLabel lblLogo;
	
	JButton btnVerMateriales;
	
	JLabel lblMateriales;

	public ReVentanaVerFabricaciones() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 918, 569);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
		
		lblMateriales = new JLabel("Materiales");
		lblMateriales.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMateriales.setBounds(250, 458, 90, 60);
		frame.getContentPane().add(lblMateriales);
		
		btnVerMateriales = new JButton("");
		btnVerMateriales.setBounds(180, 458, 60, 60);
		frame.getContentPane().add(btnVerMateriales);
		cambiarIconoBotones(btnVerMateriales,  "product.png");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBounds(0, 117, 902, 330);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		spCliente = new JScrollPane();
		spCliente.setBounds(10, 69, 884, 252);
		panel.add(spCliente);

		modelOrdenes = new DefaultTableModel(null, nombreColumnas){
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int filas, int columnas) {
            	/*
                if(columnas == 3) {
                    return true;
                }else {*/
                    return false;
                //}
            }
        };
		tabla = new JTable(modelOrdenes);
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		spCliente.setViewportView(tabla);
		
		lblId = new JLabel("Id");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		textId = new JTextField();
		textId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textId.setBounds(10, 36, 75, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		lblSucursal = new JLabel("Sucursal");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(95, 11, 46, 14);
		panel.add(lblSucursal);
		
		textSucursal = new JTextField();
		textSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textSucursal.setColumns(10);
		textSucursal.setBounds(95, 36, 75, 20);
		panel.add(textSucursal);
		
		textProducto = new JTextField();
		textProducto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textProducto.setColumns(10);
		textProducto.setBounds(180, 36, 75, 20);
		panel.add(textProducto);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblProducto.setBounds(180, 11, 60, 14);
		panel.add(lblProducto);
		
		btnFechaDesde = new JButton("Desde");
		btnFechaDesde.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnFechaDesde.setBounds(265, 7, 89, 23);
		panel.add(btnFechaDesde);
		
		btnFechaHasta = new JButton("Hasta");
		btnFechaHasta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnFechaHasta.setBounds(265, 35, 89, 23);
		panel.add(btnFechaHasta);
		
		lblFechaDesde = new JLabel("-");
		lblFechaDesde.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFechaDesde.setBounds(364, 11, 75, 14);
		panel.add(lblFechaDesde);
		
		lblFechaHasta = new JLabel("-");
		lblFechaHasta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFechaHasta.setBounds(364, 39, 75, 14);
		panel.add(lblFechaHasta);
		
		textPasoActual = new JTextField();
		textPasoActual.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textPasoActual.setColumns(10);
		textPasoActual.setBounds(503, 38, 75, 20);
		panel.add(textPasoActual);
		
		textEstado = new JTextField();
		textEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textEstado.setColumns(10);
		textEstado.setBounds(598, 38, 75, 20);
		panel.add(textEstado);
		
		JLabel lblPaso = new JLabel("Paso");
		lblPaso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPaso.setBounds(503, 11, 46, 14);
		panel.add(lblPaso);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEstado.setBounds(598, 11, 46, 14);
		panel.add(lblEstado);
		
		chckbxCancelados = new JCheckBox("Ver cancelados");
		chckbxCancelados.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxCancelados.setBounds(679, 35, 111, 23);
		panel.add(chckbxCancelados);

		
		lblNewLabel = new JLabel("Ordenes de fabricacion pendientes");
		lblNewLabel.setBounds(10, 49, 389, 67);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 902, 53);
		frame.getContentPane().add(panel_1);
		
		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes.png");
		panel_1.add(lblLogo);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(10, 458, 60, 60);
		frame.getContentPane().add(btnSalir);
		cambiarIconoBotones(btnSalir,  "back2.png");
		
				JLabel lblAtras = new JLabel("Atras");
				lblAtras.setBounds(80, 458, 90, 60);
				frame.getContentPane().add(lblAtras);
				lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				

				btnVerHistorialPasos = new JButton("");
				btnVerHistorialPasos.setBounds(339, 458, 60, 60);
				frame.getContentPane().add(btnVerHistorialPasos);
				cambiarIconoBotones(btnVerHistorialPasos,"history.png");
				
				lblHistorial = new JLabel("Historial");
				lblHistorial.setBounds(409, 458, 90, 60);
				frame.getContentPane().add(lblHistorial);
				lblHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				
						btnSeleccionarProceso = new JButton("");
						btnSeleccionarProceso.setBounds(559, 458, 60, 60);
						frame.getContentPane().add(btnSeleccionarProceso);
						btnSeleccionarProceso.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						cambiarIconoBotones(btnSeleccionarProceso,  "right.png");
						
						lblSeleccionarProceso = new JLabel("Seleccionar Proceso");
						lblSeleccionarProceso.setBounds(629, 458, 138, 60);
						frame.getContentPane().add(lblSeleccionarProceso);
						lblSeleccionarProceso.setFont(new Font("Segoe UI", Font.PLAIN, 16));
						
						JLabel lblFondo = new JLabel("");
						lblFondo.setBounds(0, 0, 960, 720);
						frame.getContentPane().add(lblFondo);
						cambiarIconoLabel(lblFondo, "fondo.png");
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "�Estas seguro que quieres salir?", 
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

	public void mostrarVentana() {
		this.setVisible(true);
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
	
	public JTable getTablaFabricacionesEnMarcha() {
		return tabla;
	}

	public DefaultTableModel getModelOrdenes() {
		return modelOrdenes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JButton getBtnTrabajarPedido() {
		return btnSeleccionarProceso;
	}

	public JTextField getTextId() {
		return textId;
	}

	public JTextField getTextProducto() {
		return textProducto;
	}

	public JTextField getTextSucursal() {
		return textSucursal;
	}

	public JButton getBtnFechaDesde() {
		return btnFechaDesde;
	}

	public JButton getBtnFechaHasta() {
		return btnFechaHasta;
	}

	public JLabel getLblFechaDesde() {
		return lblFechaDesde;
	}

	public JLabel getLblFechaHasta() {
		return lblFechaHasta;
	}
	
	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JTextField getTextPasoActual() {
		return textPasoActual;
	}

	public JTextField getTextEstado() {
		return textEstado;
	}
	
	public JCheckBox getChckbxCancelados() {
		return chckbxCancelados;
	}

	public JButton getBtnVerHistorialPasos() {
		return btnVerHistorialPasos;
	}

	public JLabel getLblHistorial() {
		return lblHistorial;
	}

	public JButton getBtnVerMateriales() {
		return btnVerMateriales;
	}

	public JLabel getLblMateriales() {
		return lblMateriales;
	}
}
