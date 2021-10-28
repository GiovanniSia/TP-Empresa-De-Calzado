package presentacion.vista.ModificarProducto;

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
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

public class VentanaModificarMProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String[] nombreColumnas = { "Codigo", "Descripción", "Proveedor", "Talle", "PrecioCosto", "PrecioMayo",
			"PrecioMino", "PuntoRepo", "CantARepo", "DiasP/Repo" };
	private DefaultTableModel modelProducto;
	private JTable tablaProducto;
	private JPanel panel_1;
	private JScrollPane spProducto;
	private JTextField txtFiltroDescripcion;
	private JTextField txtFiltroTalle;
	private JTextField txtFiltroCodProducto;
	private JButton btnQuitarFiltro;
	private JButton btnActualizarProductoUnitario;
	private JButton btnVerHistorialDeCambios;
	private JButton btnAtras;
	private JLabel lblFiltrarPor;
	private JLabel lblCodProducto;
	private JLabel lblDescripcion;
	private JLabel lblModificarProducto;
	private JLabel lblModificacionUnitaria;
	private JLabel lblMUPrecioCosto;
	private JLabel lblMUPrecioMayorista;
	private JLabel lblMUPrecioMinorista;
	private JLabel lblMUCantidadAReponer;
	private JLabel lblMUDiasParaReponer;
	private JLabel lblMUPuntoRepositorio;
	private JTextField txtActualizarPrecioCosto;
	private JTextField txtActualizarPrecioMinorista;
	private JTextField txtActualizarPrecioMayorista;
	private JTextField txtFiltroProveedor;
	private JLabel lblActualizarDescripcion;
	private JTextField txtActualizarPuntoRepositorio;
	private JTextField txtActualizarCantidadAReponer;
	private JTextField txtActualizarDiasParaResponder;

	private JLabel lblModificacionMasiva;
	private JTextField txtActualizarAumentar;
	private JTextField txtActualizarDisminuir;
	private JLabel lblAumentar;
	private JLabel lblDisminuir;
	
	private String[] nombreColumnas2 = { "Codigo", "Descripción", "Proveedor", "Talle", "PrecioCosto", "PrecioMayo",
			"PrecioMino", "PuntoRepo", "CantARepo", "DiasP/Repo" };
	private DefaultTableModel modelProducto2;
	private JTable tablaProductosModificar;
	private JButton btnAgregarProductosEnTabla;
	private JButton btnAgregarProductoSeleccionado;
	private JButton btnLimpiarTabla;
	private JButton btnQuitarProductoSeleccionado;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnActualizarMasivamente;
	
	private String[] elementosCB  = {"Mayorista y Minorista","Costo","Mayorista","Minorista"};
	private JComboBox cbTipoPrecio;
	private JButton btnCambiarDescripcionYProveedor;
	private JPanel panel_2;
	private JLabel lblNewLabel_4;
	private JLabel lblactualizarUnitariamente;
	private JLabel lblverHistorialDe;
	private JLabel lblactualizarMasivamente;
	private JLabel lblAtras;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;

	public VentanaModificarMProducto() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(248, 248, 255));
		frame.setBounds(100, 100, 1159, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(0, 86, 1145, 509);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		spProducto = new JScrollPane();
		spProducto.setBounds(291, 11, 844, 117);
		panel.add(spProducto);

		modelProducto = new DefaultTableModel(null, nombreColumnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 9) {
					return true;
				} else {
					return false;
				}
			}
		};

		tablaProducto = new JTable(modelProducto);

		tablaProducto.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProducto.getColumnModel().getColumn(0).setResizable(false);
		tablaProducto.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProducto.getColumnModel().getColumn(1).setResizable(false);
		tablaProducto.getTableHeader().setReorderingAllowed(false) ;
		spProducto.setViewportView(tablaProducto);

		btnAtras = new JButton("");
		btnAtras.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAtras.setBounds(59, 372, 60, 60);
		cambiarIconoBotones(btnAtras,  "back2.png");
		panel.add(btnAtras);

		lblMUPrecioCosto = new JLabel("Precio Costo");
		lblMUPrecioCosto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMUPrecioCosto.setBounds(301, 404, 86, 14);
		panel.add(lblMUPrecioCosto);

		lblMUPrecioMayorista = new JLabel("Precio Mayorista");
		lblMUPrecioMayorista.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMUPrecioMayorista.setBounds(509, 372, 108, 21);
		panel.add(lblMUPrecioMayorista);

		lblMUPrecioMinorista = new JLabel("Precio Minorista");
		lblMUPrecioMinorista.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMUPrecioMinorista.setBounds(301, 375, 99, 14);
		panel.add(lblMUPrecioMinorista);

		lblMUCantidadAReponer = new JLabel("Cantidad a Reponer");
		lblMUCantidadAReponer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMUCantidadAReponer.setBounds(739, 404, 128, 18);
		panel.add(lblMUCantidadAReponer);

		lblMUDiasParaReponer = new JLabel("Dias para Reponer");
		lblMUDiasParaReponer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMUDiasParaReponer.setBounds(509, 402, 108, 19);
		panel.add(lblMUDiasParaReponer);

		txtActualizarPrecioCosto = new JTextField();
		txtActualizarPrecioCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57 || key == 46;
				if (txtActualizarPrecioCosto.getText().length() >= 11 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarPrecioCosto.setBounds(389, 402, 110, 20);
		panel.add(txtActualizarPrecioCosto);

		txtActualizarPrecioMayorista = new JTextField();
		txtActualizarPrecioMayorista.setBounds(618, 371, 111, 20);
		txtActualizarPrecioMayorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57 || key == 46;
				if (txtActualizarPrecioMayorista.getText().length() >= 11 || !numeros) {
					e.consume();
				}
			}
		});
		panel.add(txtActualizarPrecioMayorista);

		txtActualizarPrecioMinorista = new JTextField();
		txtActualizarPrecioMinorista.setBounds(389, 371, 110, 20);
		txtActualizarPrecioMinorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57 || key == 46;
				if (txtActualizarPrecioMinorista.getText().length() >= 11 || !numeros) {
					e.consume();
				}
			}
		});
		panel.add(txtActualizarPrecioMinorista);

		btnActualizarProductoUnitario = new JButton("");
		btnActualizarProductoUnitario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnActualizarProductoUnitario.setBounds(945, 372, 50, 50);
		cambiarIconoBotones(btnActualizarProductoUnitario,  "one.png");
		panel.add(btnActualizarProductoUnitario);

		lblModificacionUnitaria = new JLabel("Modificacion Unitaria");
		lblModificacionUnitaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificacionUnitaria.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblModificacionUnitaria.setBounds(291, 338, 844, 23);
		panel.add(lblModificacionUnitaria);

		lblMUPuntoRepositorio = new JLabel("Punto Reposici\u00F3n");
		lblMUPuntoRepositorio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMUPuntoRepositorio.setBounds(739, 372, 117, 21);
		panel.add(lblMUPuntoRepositorio);

		btnVerHistorialDeCambios = new JButton("");
		btnVerHistorialDeCambios.setBounds(59, 235, 60, 60);
		cambiarIconoBotones(btnVerHistorialDeCambios,  "history.png");
		panel.add(btnVerHistorialDeCambios);
		btnVerHistorialDeCambios.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		txtActualizarPuntoRepositorio = new JTextField();
		txtActualizarPuntoRepositorio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarPuntoRepositorio.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarPuntoRepositorio.setBounds(866, 372, 69, 20);
		panel.add(txtActualizarPuntoRepositorio);
		txtActualizarPuntoRepositorio.setColumns(10);

		txtActualizarCantidadAReponer = new JTextField();
		txtActualizarCantidadAReponer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarCantidadAReponer.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarCantidadAReponer.setColumns(10);
		txtActualizarCantidadAReponer.setBounds(866, 403, 69, 20);
		panel.add(txtActualizarCantidadAReponer);

		txtActualizarDiasParaResponder = new JTextField();
		txtActualizarDiasParaResponder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarDiasParaResponder.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarDiasParaResponder.setColumns(10);
		txtActualizarDiasParaResponder.setBounds(660, 402, 69, 20);
		panel.add(txtActualizarDiasParaResponder);

		lblModificacionMasiva = new JLabel("Modificacion Masiva de Precios");
		lblModificacionMasiva.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblModificacionMasiva.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificacionMasiva.setBounds(579, 440, 296, 21);
		panel.add(lblModificacionMasiva);

		lblAumentar = new JLabel("Aumentar");
		lblAumentar.setBounds(589, 472, 58, 14);
		panel.add(lblAumentar);

		lblDisminuir = new JLabel("Disminuir");
		lblDisminuir.setBounds(731, 472, 47, 14);
		panel.add(lblDisminuir);

		txtActualizarAumentar = new JTextField();
		txtActualizarAumentar.setText("0");
		txtActualizarAumentar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarAumentar.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});
		txtActualizarAumentar.setBounds(646, 469, 58, 20);
		panel.add(txtActualizarAumentar);
		txtActualizarAumentar.setColumns(10);

		txtActualizarDisminuir = new JTextField();
		txtActualizarDisminuir.setText("0");
		txtActualizarDisminuir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (txtActualizarDisminuir.getText().length() >= 8 || !numeros) {
					e.consume();
				}
			}
		});

		txtActualizarDisminuir.setColumns(10);
		txtActualizarDisminuir.setBounds(783, 469, 58, 20);
		panel.add(txtActualizarDisminuir);
		
		JScrollPane spProducto_1 = new JScrollPane();
		spProducto_1.setBounds(291, 193, 844, 102);
		panel.add(spProducto_1);
		
		modelProducto2 = new DefaultTableModel(null, nombreColumnas2) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas == 9) {
					return true;
				} else {
					return false;
				}
			}
		};
		tablaProductosModificar = new JTable(modelProducto2);
		tablaProductosModificar.getTableHeader().setReorderingAllowed(false) ;
		spProducto_1.setViewportView(tablaProductosModificar);
		
		btnAgregarProductosEnTabla = new JButton("Agregar Productos a Tabla Modificar");
		btnAgregarProductosEnTabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAgregarProductosEnTabla.setBounds(893, 134, 242, 23);
		panel.add(btnAgregarProductosEnTabla);
		
		btnAgregarProductoSeleccionado = new JButton("Agregar Producto Seleccionado");
		btnAgregarProductoSeleccionado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAgregarProductoSeleccionado.setBounds(675, 134, 208, 23);
		panel.add(btnAgregarProductoSeleccionado);
		
		JLabel lblNewLabel = new JLabel("Productos a Modificar");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(291, 168, 135, 14);
		panel.add(lblNewLabel);
		
		btnLimpiarTabla = new JButton("Limpiar Tabla");
		btnLimpiarTabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnLimpiarTabla.setBounds(1000, 304, 135, 23);
		panel.add(btnLimpiarTabla);
		
		btnQuitarProductoSeleccionado = new JButton("Quitar Producto Seleccionado");
		btnQuitarProductoSeleccionado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnQuitarProductoSeleccionado.setBounds(761, 304, 229, 23);
		panel.add(btnQuitarProductoSeleccionado);
		
		lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setBounds(709, 472, 30, 14);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("%");
		lblNewLabel_2.setBounds(845, 472, 30, 14);
		panel.add(lblNewLabel_2);
		
		btnActualizarMasivamente = new JButton("");
		btnActualizarMasivamente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnActualizarMasivamente.setBounds(945, 440, 50, 50);
		cambiarIconoBotones(btnActualizarMasivamente,  "many.png");
		panel.add(btnActualizarMasivamente);
		
		cbTipoPrecio = new JComboBox(elementosCB);
		cbTipoPrecio.setBounds(445, 468, 128, 22);
		panel.add(cbTipoPrecio);
		
		JLabel lblNewLabel_3 = new JLabel("Precio:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(399, 471, 47, 14);
		panel.add(lblNewLabel_3);
		
		btnCambiarDescripcionYProveedor = new JButton("Cambiar Descripcion y Proveedor");
		btnCambiarDescripcionYProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnCambiarDescripcionYProveedor.setBounds(447, 134, 218, 23);
		panel.add(btnCambiarDescripcionYProveedor);
		
		lblactualizarUnitariamente = new JLabel("<html>Actualizar Unitariamente<html>");
		lblactualizarUnitariamente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblactualizarUnitariamente.setBounds(1005, 372, 108, 50);
		panel.add(lblactualizarUnitariamente);
		
		lblverHistorialDe = new JLabel("<html>Ver Historial de Cambios<html>");
		lblverHistorialDe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblverHistorialDe.setBounds(130, 235, 99, 60);
		panel.add(lblverHistorialDe);
		
		lblactualizarMasivamente = new JLabel("<html>Actualizar Masivamente<html>");
		lblactualizarMasivamente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblactualizarMasivamente.setBounds(1005, 438, 99, 50);
		panel.add(lblactualizarMasivamente);
		
		lblAtras = new JLabel("Atras");
		lblAtras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAtras.setBounds(133, 372, 92, 60);
		panel.add(lblAtras);
		
		separator = new JSeparator();
		separator.setBounds(291, 433, 844, 17);
		panel.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(291, 331, 844, 17);
		panel.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(279, 150, 23, 347);
		panel.add(separator_2);

		panel_1 = new JPanel();
		panel_1.setBounds(10, 0, 273, 139);
		panel.add(panel_1);
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setLayout(null);

		lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(124, 29, 70, 20);
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblDescripcion);

		txtFiltroDescripcion = new JTextField();

		txtFiltroDescripcion.setBounds(124, 55, 116, 20);
		panel_1.add(txtFiltroDescripcion);

		lblCodProducto = new JLabel("Cod Producto");
		lblCodProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCodProducto.setBounds(10, 29, 86, 20);
		panel_1.add(lblCodProducto);

		txtFiltroCodProducto = new JTextField();
		txtFiltroCodProducto.setBounds(10, 55, 94, 20);
		panel_1.add(txtFiltroCodProducto);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFiltrarPor.setBounds(10, 4, 70, 20);
		panel_1.add(lblFiltrarPor);
		
		lblActualizarDescripcion = new JLabel();
		lblActualizarDescripcion.setBounds(273, 55, 94, 20);
		panel_1.add(lblActualizarDescripcion);
		
		btnQuitarFiltro = new JButton("<html>Quitar Filtro<html>");
		btnQuitarFiltro.setVerticalAlignment(SwingConstants.TOP);
		btnQuitarFiltro.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnQuitarFiltro.setBounds(204, 89, 69, 39);
		panel_1.add(btnQuitarFiltro);
		
				txtFiltroProveedor = new JTextField();
				txtFiltroProveedor.setBounds(10, 108, 94, 20);
				panel_1.add(txtFiltroProveedor);
				
						txtFiltroTalle = new JTextField();
						txtFiltroTalle.setBounds(124, 108, 70, 20);
						panel_1.add(txtFiltroTalle);
						
								JLabel lblTalle = new JLabel("Talle");
								lblTalle.setBounds(124, 82, 70, 20);
								panel_1.add(lblTalle);
								lblTalle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
								
										JLabel lblProveedor = new JLabel("Proveedor");
										lblProveedor.setBounds(10, 82, 94, 20);
										panel_1.add(lblProveedor);
										lblProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		lblModificarProducto = new JLabel("Modificar Producto");
		lblModificarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblModificarProducto.setBounds(10, 50, 382, 43);
		frame.getContentPane().add(lblModificarProducto);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(0, 0, 1145, 50);
		frame.getContentPane().add(panel_2);
		
		lblNewLabel_4 = new JLabel("Zapateria Argento");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_4.setBounds(10, 0, 421, 50);
		panel_2.add(lblNewLabel_4);
		
	}
	
	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH));
		boton.setIcon(Icono);
	}
	
	public JButton getBtnQuitarFiltro() {
		return btnQuitarFiltro;
	}

	public JComboBox getCbTipoPrecio() {
		return cbTipoPrecio;
	}

	public JButton getBtnActualizarMasivamente() {
		return btnActualizarMasivamente;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelProducto() {
		return modelProducto;
	}

	public JTextField getTxtFiltroProveedor() {
		return txtFiltroProveedor;
	}

	public JTable getTablaProducto() {
		return tablaProducto;
	}

	public JScrollPane getSpProducto() {
		return spProducto;
	}

	public JTextField getTxtFiltroDescripcion() {
		return txtFiltroDescripcion;
	}
	
	public JButton getBtnCambiarDescripcionYProveedor() {
		return btnCambiarDescripcionYProveedor;
	}

	public JTextField getTxtFiltroTalle() {
		return txtFiltroTalle;
	}

	public JTextField getTxtFiltroCodProducto() {
		return txtFiltroCodProducto;
	}

	public JLabel getLblActualizarDescripcion() {
		return lblActualizarDescripcion;
	}

	public JTextField getTxtActualizarPrecioCosto() {
		return txtActualizarPrecioCosto;
	}

	public JTextField getTxtActualizarPrecioMinorista() {
		return txtActualizarPrecioMinorista;
	}

	public JTextField getTxtActualizarPrecioMayorista() {
		return txtActualizarPrecioMayorista;
	}

	public JTextField getTxtActualizarPuntoRepositorio() {
		return txtActualizarPuntoRepositorio;
	}

	public JTextField getTxtActualizarCantidadAReponer() {
		return txtActualizarCantidadAReponer;
	}

	public JTextField getTxtActualizarDiasParaResponder() {
		return txtActualizarDiasParaResponder;
	}

	public JTextField getTxtActualizarAumentar() {
		return txtActualizarAumentar;
	}

	public JTextField getTxtActualizarDisminuir() {
		return txtActualizarDisminuir;
	}

	public JButton getBtnActualizarProducto() {
		return btnActualizarProductoUnitario;
	}

	public JButton getBtnVerHistorialDeCambios() {
		return btnVerHistorialDeCambios;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}
	
	public String[] getNombreColumnas2() {
		return nombreColumnas2;
	}

	public DefaultTableModel getModelProducto2() {
		return modelProducto2;
	}

	public JTable getTablaProductosModificar() {
		return tablaProductosModificar;
	}

	public JButton getBtnAgregarProductosEnTabla() {
		return btnAgregarProductosEnTabla;
	}

	public JButton getBtnAgregarProductoSeleccionado() {
		return btnAgregarProductoSeleccionado;
	}

	public JButton getBtnLimpiarTabla() {
		return btnLimpiarTabla;
	}

	public JButton getBtnQuitarProductoSeleccionado() {
		return btnQuitarProductoSeleccionado;
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
		this.txtFiltroTalle.setText(null);
		this.txtFiltroCodProducto.setText(null);
		this.txtFiltroProveedor.setText(null);
		this.lblActualizarDescripcion.setText("Descripción");
		this.txtActualizarPrecioCosto.setText(null);
		this.txtActualizarPrecioMayorista.setText(null);
		this.txtActualizarPrecioMinorista.setText(null);
		this.txtActualizarPuntoRepositorio.setText(null);
		this.txtActualizarCantidadAReponer.setText(null);
		this.txtActualizarDiasParaResponder.setText(null);
		
		this.txtActualizarDisminuir.setText("0");
		this.txtActualizarAumentar.setText("0");
	}

	public void cerrar() {
		limpiarCampos();
		frame.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
}