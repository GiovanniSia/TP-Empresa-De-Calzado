package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaProducto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtIdProveedor;
	private JButton btnAgregar;
	private static VentanaProducto INSTANCE;

	public static VentanaProducto getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaProducto();
			return new VentanaProducto();
		} else
			return INSTANCE;
	}

	private VentanaProducto() {
		super();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 123);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 113, 14);
		panel.add(lblNombre);

		JLabel lblIdProveedor = new JLabel("Id proveedor");
		lblIdProveedor.setBounds(10, 52, 113, 14);
		panel.add(lblIdProveedor);

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtIdProveedor = new JTextField();
		txtIdProveedor.setBounds(133, 49, 164, 20);
		panel.add(txtIdProveedor);
		txtIdProveedor.setColumns(10);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(208, 92, 89, 23);
		panel.add(btnAgregar);

		this.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtIdProveedor() {
		return txtIdProveedor;
	}

	public JButton getBtnAgregarProducto() {
		return btnAgregar;
	}

	public void cerrar() {
		this.txtNombre.setText(null);
		this.txtIdProveedor.setText(null);
		this.dispose();
	}

}
