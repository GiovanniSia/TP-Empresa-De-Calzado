package presentacion.vista.reporteRanking;

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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import persistencia.conexion.Conexion;

public class VentanaVerReporteRankingXSucursal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	private JButton btnVerDescripcion;
	private JLabel lblNewLabel;
	
	JDateChooser fechaHasta;
	JDateChooser fechaDesde;
	JButton btnVerReporteVendedores;
	private JButton btnRegresar;
	private JPanel panel_1;
	private JLabel lblXSucursal;
	private JLabel lblFechaDesde_2;
	private JLabel lblVolver;

	public VentanaVerReporteRankingXSucursal() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 379, 310);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 64, 363, 196);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnVerDescripcion = new JButton("");
		btnVerDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerDescripcion.setBounds(20, 66, 57, 57);
		cambiarIconoBotones(btnVerDescripcion,  "report.png");
		panel.add(btnVerDescripcion);

		fechaDesde = new JDateChooser();
		fechaDesde.setBounds(20, 36, 108, 19);
		panel.add(fechaDesde);
		
		fechaHasta = new JDateChooser();
		fechaHasta.setBounds(194, 36, 108, 19);
		panel.add(fechaHasta);
		
		JLabel lblFechaDesde = new JLabel("Fecha desde");
		lblFechaDesde.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFechaDesde.setBounds(20, 11, 108, 14);
		panel.add(lblFechaDesde);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFechaHasta.setBounds(194, 11, 108, 14);
		panel.add(lblFechaHasta);
		
		btnVerReporteVendedores = new JButton("");
		btnVerReporteVendedores.setBounds(194, 66, 57, 57);
		cambiarIconoBotones(btnVerReporteVendedores,  "report2.png");
		panel.add(btnVerReporteVendedores);
		
		btnRegresar = new JButton("");
		btnRegresar.setBackground(new Color(248, 248, 255));
		btnRegresar.setBounds(20, 134, 60, 60);
		cambiarIconoBotones(btnRegresar,  "back2.png");
		panel.add(btnRegresar);
		
		lblXSucursal = new JLabel("X Sucursal");
		lblXSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblXSucursal.setBounds(87, 66, 97, 57);
		panel.add(lblXSucursal);
		
		lblFechaDesde_2 = new JLabel("X Vendedor");
		lblFechaDesde_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFechaDesde_2.setBounds(261, 66, 97, 57);
		panel.add(lblFechaDesde_2);
		
		lblVolver = new JLabel("Volver");
		lblVolver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblVolver.setBounds(87, 137, 97, 57);
		panel.add(lblVolver);
		
		lblNewLabel = new JLabel("Ver Ranking de Ventas");
		lblNewLabel.setBounds(10, 21, 324, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(0, 1, 363, 9);
		frame.getContentPane().add(panel_1);
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
	
	public JButton getBtnVerDescripcion() {
		return btnVerDescripcion;
	}
	
	public JDateChooser getFechaDesde() {
		return fechaDesde;
	}

	public JDateChooser getFechaHasta() {
		return fechaHasta;
	}

	public JButton getBtnVerReporteVendedores() {
		return btnVerReporteVendedores;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}
}
