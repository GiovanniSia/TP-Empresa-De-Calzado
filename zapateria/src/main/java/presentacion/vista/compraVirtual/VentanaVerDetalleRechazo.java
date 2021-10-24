package presentacion.vista.compraVirtual;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import java.awt.Color;
import javax.swing.JTextPane;

public class VentanaVerDetalleRechazo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	
	JTextPane textPane;
	JButton btnSalir;
	JLabel lblFecha;
	JLabel lblHora;
	private JLabel lblNewLabel_2;
	private JLabel lblSucursal;
	private JLabel lblNewLabel_3;
	private JLabel lblPago;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblCuil;
	private JLabel lblCorreo;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_15;
	private JLabel lblImpuesto;
	private JLabel lblEstadoa;
	private JLabel lblCalle;
	private JLabel lblAltura;
	private JLabel lblPais;
	private JLabel lblProvincia;
	private JLabel lblLocalidad;
	private JLabel lblCodPostal;
	
	private JScrollPane spCliente;
	private String[] nombreColumnasTablaPrincipal = { "Cod Producto","Descripcion","Mayorista", "Minorista", "Costo"};
	private DefaultTableModel modelDeTablaPrincipal;
	private JTable tabla;

	public VentanaVerDetalleRechazo() {
		initialize();
	}
	
	public void ventanaErrorMaterialesNoSuficientes() {
		JOptionPane.showMessageDialog(null, "No se cuenta con los materiales para avanzar");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 651);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 64, 496, 537);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(598, 11, 46, 14);
		panel.add(lblEstado);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 344, 476, 148);
		panel.add(textPane);
		
		btnSalir = new JButton("Cerrar");
		btnSalir.setBounds(309, 503, 177, 23);
		panel.add(btnSalir);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		lblFecha = new JLabel("New label");
		lblFecha.setBounds(66, 11, 79, 14);
		panel.add(lblFecha);

		lblHora = new JLabel("New label");
		lblHora.setBounds(155, 11, 79, 14);
		panel.add(lblHora);
		
		lblNewLabel_2 = new JLabel("Codigo sucursal");
		lblNewLabel_2.setBounds(10, 36, 84, 14);
		panel.add(lblNewLabel_2);
		
		lblSucursal = new JLabel("New label");
		lblSucursal.setBounds(104, 36, 79, 14);
		panel.add(lblSucursal);
		
		lblNewLabel_3 = new JLabel("Pago");
		lblNewLabel_3.setBounds(10, 61, 84, 14);
		panel.add(lblNewLabel_3);
		
		lblPago = new JLabel("New label");
		lblPago.setBounds(104, 61, 79, 14);
		panel.add(lblPago);
		
		lblNewLabel_4 = new JLabel("Nombre");
		lblNewLabel_4.setBounds(10, 86, 84, 14);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Apellido");
		lblNewLabel_5.setBounds(10, 111, 84, 14);
		panel.add(lblNewLabel_5);
		
		lblNombre = new JLabel("New label");
		lblNombre.setBounds(104, 86, 79, 14);
		panel.add(lblNombre);
		
		lblApellido = new JLabel("New label");
		lblApellido.setBounds(104, 111, 79, 14);
		panel.add(lblApellido);
		
		lblNewLabel_6 = new JLabel("CUIL: ");
		lblNewLabel_6.setBounds(10, 136, 84, 14);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Correo electronico");
		lblNewLabel_7.setBounds(10, 216, 103, 14);
		panel.add(lblNewLabel_7);
		
		lblCuil = new JLabel("New label");
		lblCuil.setBounds(104, 136, 79, 14);
		panel.add(lblCuil);
		
		lblCorreo = new JLabel("New label");
		lblCorreo.setBounds(123, 216, 79, 14);
		panel.add(lblCorreo);
		
		lblNewLabel_8 = new JLabel("ImpuestoAFIP");
		lblNewLabel_8.setBounds(309, 11, 84, 14);
		panel.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Estado");
		lblNewLabel_9.setBounds(309, 36, 84, 14);
		panel.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Calle");
		lblNewLabel_10.setBounds(309, 61, 84, 14);
		panel.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("Altura");
		lblNewLabel_11.setBounds(309, 86, 84, 14);
		panel.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Pais");
		lblNewLabel_12.setBounds(309, 111, 84, 14);
		panel.add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("Provincia");
		lblNewLabel_13.setBounds(309, 136, 84, 14);
		panel.add(lblNewLabel_13);
		
		lblNewLabel_14 = new JLabel("Localidad");
		lblNewLabel_14.setBounds(309, 161, 84, 14);
		panel.add(lblNewLabel_14);
		
		lblNewLabel_15 = new JLabel("CodPostal");
		lblNewLabel_15.setBounds(309, 186, 84, 14);
		panel.add(lblNewLabel_15);
		
		lblImpuesto = new JLabel("New label");
		lblImpuesto.setBounds(403, 11, 79, 14);
		panel.add(lblImpuesto);
		
		lblEstadoa = new JLabel("New label");
		lblEstadoa.setBounds(403, 36, 79, 14);
		panel.add(lblEstadoa);
		
		lblCalle = new JLabel("New label");
		lblCalle.setBounds(403, 61, 79, 14);
		panel.add(lblCalle);
		
		lblAltura = new JLabel("New label");
		lblAltura.setBounds(403, 86, 79, 14);
		panel.add(lblAltura);
		
		lblPais = new JLabel("New label");
		lblPais.setBounds(403, 111, 79, 14);
		panel.add(lblPais);
		
		lblProvincia = new JLabel("New label");
		lblProvincia.setBounds(403, 136, 79, 14);
		panel.add(lblProvincia);
		
		lblLocalidad = new JLabel("New label");
		lblLocalidad.setBounds(403, 161, 79, 14);
		panel.add(lblLocalidad);
		
		lblCodPostal = new JLabel("New label");
		lblCodPostal.setBounds(403, 186, 79, 14);
		panel.add(lblCodPostal);
		
		modelDeTablaPrincipal = new DefaultTableModel(null, nombreColumnasTablaPrincipal){
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
		tabla = new JTable(modelDeTablaPrincipal);
		
		spCliente = new JScrollPane();
		spCliente.setBounds(10, 241, 476, 92);
		
		spCliente.setViewportView(tabla);
		panel.add(spCliente);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 0, 806, 10);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel = new JLabel("Detalle rechazo");
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
	
	public DefaultTableModel getModelOrdenes() {
		return modelDeTablaPrincipal;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JLabel getLblHora() {
		return lblHora;
	}

	public JLabel getLblSucursal() {
		return lblSucursal;
	}

	public JLabel getLblPago() {
		return lblPago;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblApellido() {
		return lblApellido;
	}

	public JLabel getLblCuil() {
		return lblCuil;
	}

	public JLabel getLblCorreo() {
		return lblCorreo;
	}

	public JLabel getLblImpuesto() {
		return lblImpuesto;
	}

	public JLabel getLblEstadoa() {
		return lblEstadoa;
	}

	public JLabel getLblCalle() {
		return lblCalle;
	}

	public JLabel getLblAltura() {
		return lblAltura;
	}

	public JLabel getLblPais() {
		return lblPais;
	}

	public JLabel getLblProvincia() {
		return lblProvincia;
	}

	public JLabel getLblLocalidad() {
		return lblLocalidad;
	}

	public JLabel getLblCodPostal() {
		return lblCodPostal;
	}

	public String[] getNombreColumnasTablaPrincipal() {
		return nombreColumnasTablaPrincipal;
	}
}
