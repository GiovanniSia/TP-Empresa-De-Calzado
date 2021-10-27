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
	private JPanel panel_2;

	private JButton btnVerDescripcion;
	private JLabel lblNewLabel;
	
	JDateChooser fechaHasta;
	JDateChooser fechaDesde;
	JButton btnVerReporteVendedores;
	private JButton btnRegresar;

	public VentanaVerReporteRankingXSucursal() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 379, 260);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 64, 353, 146);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnVerDescripcion = new JButton("Ver reporte por sucursales");
		btnVerDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerDescripcion.setBounds(73, 66, 177, 23);
		panel.add(btnVerDescripcion);

		fechaDesde = new JDateChooser();
		fechaDesde.setBounds(10, 36, 108, 19);
		panel.add(fechaDesde);
		
		fechaHasta = new JDateChooser();
		fechaHasta.setBounds(184, 36, 108, 19);
		panel.add(fechaHasta);
		
		JLabel lblFechaDesde = new JLabel("Fecha desde");
		lblFechaDesde.setBounds(10, 11, 108, 14);
		panel.add(lblFechaDesde);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setBounds(184, 11, 108, 14);
		panel.add(lblFechaHasta);
		
		btnVerReporteVendedores = new JButton("Ver reporte por vendedores");
		btnVerReporteVendedores.setBounds(73, 100, 177, 23);
		panel.add(btnVerReporteVendedores);
		
		btnRegresar = new JButton("");
		btnRegresar.setBackground(new Color(248, 248, 255));
		btnRegresar.setBounds(297, 97, 46, 38);
		panel.add(btnRegresar);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 806, 10);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Ver ranking de ventas");
		lblNewLabel.setBounds(10, 21, 324, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
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
