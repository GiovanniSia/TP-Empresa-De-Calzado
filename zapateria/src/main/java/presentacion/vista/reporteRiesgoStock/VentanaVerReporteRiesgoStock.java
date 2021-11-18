package presentacion.vista.reporteRiesgoStock;

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
import javax.swing.JTextField;

public class VentanaVerReporteRiesgoStock extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	private JButton btnVerReporte;
	private JLabel lblNewLabel;
	private JButton btnRegresar;
	private JPanel panel_1;
	private JLabel lblVolver;
	private JTextField txtPrimerFlag;
	private JTextField txtSegundoFlag;
	
	JLabel lblSucursal;

	public VentanaVerReporteRiesgoStock() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 379, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 64, 363, 139);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnVerReporte = new JButton("");
		btnVerReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerReporte.setBounds(293, 68, 60, 60);
		cambiarIconoBotones(btnVerReporte,  "report.png");
		panel.add(btnVerReporte);
		
		lblSucursal = new JLabel("Sucursal: ");
		lblSucursal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSucursal.setBounds(10, 11, 162, 29);
		panel.add(lblSucursal);
		
		JLabel lblNewLabel_1_1 = new JLabel("Porcentaje de riesgo grave");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 55, 203, 29);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Porcentaje de riesgo muy grave");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 99, 203, 29);
		panel.add(lblNewLabel_1_1_1);
		
		txtPrimerFlag = new JTextField();
		txtPrimerFlag.setBounds(223, 55, 60, 29);
		panel.add(txtPrimerFlag);
		txtPrimerFlag.setColumns(10);
		
		txtSegundoFlag = new JTextField();
		txtSegundoFlag.setColumns(10);
		txtSegundoFlag.setBounds(223, 99, 60, 29);
		panel.add(txtSegundoFlag);
		
		lblNewLabel = new JLabel("Ver reporte riesgo stock");
		lblNewLabel.setBounds(10, 21, 324, 32);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(0, 1, 363, 9);
		frame.getContentPane().add(panel_1);
		
		btnRegresar = new JButton("");
		btnRegresar.setBounds(117, 214, 60, 60);
		frame.getContentPane().add(btnRegresar);
		btnRegresar.setBackground(new Color(248, 248, 255));
		cambiarIconoBotones(btnRegresar,  "back2.png");
		
		lblVolver = new JLabel("Volver");
		lblVolver.setBounds(184, 214, 51, 60);
		frame.getContentPane().add(lblVolver);
		lblVolver.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(-104, -26, 900, 675);
		cambiarIconoLabel(lblFondo, "fondo.png");
		frame.getContentPane().add(lblFondo);
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
	
	public void cambiarIconoLabel(JLabel label, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/" + ruta));
		ImageIcon Icono = new ImageIcon(
				Imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		label.setIcon(Icono);
	}
	
	public JButton getBtnVerReporte() {
		return btnVerReporte;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JTextField getTxtPrimerFlag() {
		return txtPrimerFlag;
	}

	public JTextField getTxtSegundoFlag() {
		return txtSegundoFlag;
	}
}
