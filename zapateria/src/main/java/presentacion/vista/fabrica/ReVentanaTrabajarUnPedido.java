package presentacion.vista.fabrica;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class ReVentanaTrabajarUnPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	JButton btnAvanzarUnPaso;
	JButton btnCancelar;
	
	private String[] nombreColumnas = { "Material", "Cantidad a usar", "Cantidad en stock", "Unidad medida"};
	private JScrollPane spCliente;
	private DefaultTableModel modelOrdenes;
	private JTable tabla;
	private JLabel lblOrden;
	
	JLabel lblMensaje;
	JLabel lblSucursal;
	JLabel lblFechaRequerida;
	JLabel lblIdPedido;
	private JLabel lblPasos;
	JButton btnSalirVentana;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblsalir;
	private JLabel lblcancelarFabricacin;

	public ReVentanaTrabajarUnPedido() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 676, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(10, 25, 640, 370);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnAvanzarUnPaso = new JButton("");
		btnAvanzarUnPaso.setBounds(476, 16, 60, 60);
		cambiarIconoBotones(btnAvanzarUnPaso,  "pick.png");
		panel.add(btnAvanzarUnPaso);
		
		btnCancelar = new JButton("");
		btnCancelar.setBounds(476, 158, 60, 60);
		cambiarIconoBotones(btnCancelar,  "cancel2.png");
		panel.add(btnCancelar);
		
		spCliente = new JScrollPane();
		spCliente.setBounds(10, 229, 620, 120);
		panel.add(spCliente);
		
		modelOrdenes = new DefaultTableModel(null, nombreColumnas);
		tabla = new JTable(modelOrdenes);
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spCliente.setViewportView(tabla);
		
		lblOrden = new JLabel("Se pidio:");
		lblOrden.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblOrden.setBounds(10, 11, 356, 23);
		panel.add(lblOrden);
		
		lblIdPedido = new JLabel("New label");
		lblIdPedido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblIdPedido.setBounds(10, 45, 356, 14);
		panel.add(lblIdPedido);
		
		lblFechaRequerida = new JLabel("New label");
		lblFechaRequerida.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFechaRequerida.setBounds(10, 70, 356, 14);
		panel.add(lblFechaRequerida);

		lblSucursal = new JLabel("New label");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSucursal.setBounds(10, 95, 356, 14);
		panel.add(lblSucursal);
		
		lblMensaje = new JLabel("New label");
		lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMensaje.setBounds(10, 205, 356, 14);
		panel.add(lblMensaje);
		
		lblPasos = new JLabel("New label");
		lblPasos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPasos.setBounds(10, 120, 356, 14);
		panel.add(lblPasos);
		
		btnSalirVentana = new JButton("");
		btnSalirVentana.setBounds(476, 87, 60, 60);
		cambiarIconoBotones(btnSalirVentana,  "back2.png");
		panel.add(btnSalirVentana);
		
		lblNewLabel = new JLabel("<html>Avanzar un paso<html>");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel.setBounds(546, 16, 73, 60);
		panel.add(lblNewLabel);
		
		lblsalir = new JLabel("<html>Salir<html>");
		lblsalir.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblsalir.setBounds(546, 87, 84, 60);
		panel.add(lblsalir);
		
		lblcancelarFabricacin = new JLabel("<html>Cancelar Fabricacion<html>");
		lblcancelarFabricacin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblcancelarFabricacin.setBounds(546, 158, 84, 60);
		panel.add(lblcancelarFabricacin);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 660, 14);
		frame.getContentPane().add(panel_1);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 960, 720);
		frame.getContentPane().add(lblFondo);
		cambiarIconoLabel(lblFondo, "fondo.png");
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
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
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

	public JButton getBtnAvanzarUnPaso() {
		return btnAvanzarUnPaso;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	public JTable getTablaIngredientes() {
		return tabla;
	}

	public DefaultTableModel getModelOrdenes() {
		return modelOrdenes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JLabel getLblOrden() {
		return lblOrden;
	}

	public JLabel getLblMensaje() {
		return lblMensaje;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JLabel getLblFechaRequerida() {
		return lblFechaRequerida;
	}

	public JLabel getLblIdPedido() {
		return lblIdPedido;
	}

	public JLabel getLblPasos() {
		return lblPasos;
	}

	public JButton getBtnSalirVentana() {
		return btnSalirVentana;
	}
}
