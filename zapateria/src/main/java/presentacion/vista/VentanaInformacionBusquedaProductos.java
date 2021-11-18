package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class VentanaInformacionBusquedaProductos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblSeleccionarCliente;
	private JLabel lblTalle;
	private JLabel lblParaElloSi;
	private JLabel lblTalle_1;
	private JLabel lblImagenFiltros;
	private JLabel lblTalle_2;
	private JLabel lblImagenProductoSeleccionado;
	private JLabel lblTalle_3;
	private JLabel lblImagenCantidadYBtnAgregar;
	private JLabel lblTalle_4;
	private JLabel lblImagenCarritoCompra;
	private JLabel lblTalle_5;
	private JLabel lblImagenCantidadBtnBorrar;
	private JLabel lblTalle_6;
	private JLabel lblImagenTotalPagar;
	private JLabel lblTalle_7;
	private JLabel lblConfirmarPedido;

	public static void main(String[] args){
		VentanaInformacionBusquedaProductos a = new VentanaInformacionBusquedaProductos();
		a.show();
	}

	public VentanaInformacionBusquedaProductos() {
		this.initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 543, 691);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(0, 0, 621, 53);
		contentPane.add(panel);

		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblLogo.setBounds(10, 5, 165, 42);
		cambiarIconoLabel(lblLogo, "argentoshoes2.png");
		panel.add(lblLogo);

		lblSeleccionarCliente = new JLabel("\u00BFComo Elegir Productos?");
		lblSeleccionarCliente.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblSeleccionarCliente.setBounds(10, 53, 442, 41);
		contentPane.add(lblSeleccionarCliente);

		lblTalle = new JLabel("Primero tenemos que seleccionar un producto de nuestro catalogo");
		lblTalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTalle.setBounds(10, 91, 442, 13);
		contentPane.add(lblTalle);
		
		lblParaElloSi = new JLabel("Para ello si queremos podemos filtrar el producto por su nombre, talle o hacer una busqueda ");
		lblParaElloSi.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblParaElloSi.setBounds(10, 111, 586, 13);
		contentPane.add(lblParaElloSi);
		
		lblTalle_1 = new JLabel("entre precio desde y precio hasta");
		lblTalle_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTalle_1.setBounds(10, 127, 442, 13);
		contentPane.add(lblTalle_1);
		
		lblImagenFiltros = new JLabel("");
		lblImagenFiltros.setForeground(Color.WHITE);
		lblImagenFiltros.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblImagenFiltros.setBounds(10, 150, 384, 42);
		cambiarIconoLabel(lblImagenFiltros, "BusquedaProductosFiltro.png");
		contentPane.add(lblImagenFiltros);
		
		lblTalle_2 = new JLabel("Seleccionamos el producto que queremos");
		lblTalle_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTalle_2.setBounds(10, 192, 442, 13);
		contentPane.add(lblTalle_2);
		
		lblImagenProductoSeleccionado = new JLabel("");
		lblImagenProductoSeleccionado.setForeground(Color.WHITE);
		lblImagenProductoSeleccionado.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblImagenProductoSeleccionado.setBounds(10, 212, 513, 42);
		cambiarIconoLabel(lblImagenProductoSeleccionado, "BusquedaProductosProductoSeleccionado.png");
		contentPane.add(lblImagenProductoSeleccionado);
		
		lblTalle_3 = new JLabel("A continuacion elegimos la cantidad que queremos del producto y presionamos el boton \"Agregar\"");
		lblTalle_3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTalle_3.setBounds(10, 254, 529, 13);
		contentPane.add(lblTalle_3);
		
		lblImagenCantidadYBtnAgregar = new JLabel("");
		lblImagenCantidadYBtnAgregar.setForeground(Color.WHITE);
		lblImagenCantidadYBtnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblImagenCantidadYBtnAgregar.setBounds(154, 265, 199, 61);
		cambiarIconoLabel(lblImagenCantidadYBtnAgregar, "BusquedaProductosCantidadBtnAgregar.png");
		contentPane.add(lblImagenCantidadYBtnAgregar);
		
		lblTalle_4 = new JLabel("Se agrega automaticamente el producto en el carrito de la compra");
		lblTalle_4.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTalle_4.setBounds(10, 326, 509, 13);
		contentPane.add(lblTalle_4);
		
		lblImagenCarritoCompra = new JLabel("");
		lblImagenCarritoCompra.setForeground(Color.WHITE);
		lblImagenCarritoCompra.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblImagenCarritoCompra.setBounds(135, 337, 259, 76);
		cambiarIconoLabel(lblImagenCarritoCompra, "BusquedaProductosCarritoCompra.png");
		contentPane.add(lblImagenCarritoCompra);
		
		lblTalle_5 = new JLabel("Si seleccionamos el producto podemos editar su cantidad o borrarlo del carrito");
		lblTalle_5.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTalle_5.setBounds(14, 413, 509, 13);
		contentPane.add(lblTalle_5);
		
		lblImagenCantidadBtnBorrar = new JLabel("");
		lblImagenCantidadBtnBorrar.setForeground(Color.WHITE);
		lblImagenCantidadBtnBorrar.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblImagenCantidadBtnBorrar.setBounds(135, 437, 259, 76);
		cambiarIconoLabel(lblImagenCantidadBtnBorrar, "BusquedaProductosEditarCantidadBtnQuitarDelCarrito.png");
		contentPane.add(lblImagenCantidadBtnBorrar);
		
		lblTalle_6 = new JLabel("Cada vez que agregamos productos al carrito se actualiza el totol a pagar");
		lblTalle_6.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTalle_6.setBounds(14, 518, 509, 13);
		contentPane.add(lblTalle_6);
		
		lblImagenTotalPagar = new JLabel("");
		lblImagenTotalPagar.setForeground(Color.WHITE);
		lblImagenTotalPagar.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblImagenTotalPagar.setBounds(145, 533, 165, 31);
		cambiarIconoLabel(lblImagenTotalPagar, "BusquedaProductosTotalPagar.png");
		contentPane.add(lblImagenTotalPagar);
		
		lblTalle_7 = new JLabel("Por ultimo, para finalizar la venta se presiona el boton \"Confirmar Pedido\"");
		lblTalle_7.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTalle_7.setBounds(14, 564, 509, 13);
		contentPane.add(lblTalle_7);
		
		lblConfirmarPedido = new JLabel("");
		lblConfirmarPedido.setForeground(Color.WHITE);
		lblConfirmarPedido.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblConfirmarPedido.setBounds(171, 588, 165, 53);
		cambiarIconoLabel(lblConfirmarPedido, "BusquedaProductosConfirmarPedido.png");
		contentPane.add(lblConfirmarPedido);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});
		frame.setVisible(true);
	}

	public void cerrar() {
		frame.setVisible(false);
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

}
