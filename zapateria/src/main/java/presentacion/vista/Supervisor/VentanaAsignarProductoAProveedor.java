package presentacion.vista.Supervisor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VentanaAsignarProductoAProveedor {

	private JFrame frame;
	
	private JTable tableProvElegido;
	private DefaultTableModel modelTablaProvElegido;
	private String[] nombreColumnasProvElegido = {"Nombre","Correo","Limite de Credito","Credito disponible"};
	
	
	private JTable tableProdDeProv;
	private DefaultTableModel modelTablaProdDeProv;
	private String[] nombreColumnasProdDeProv = {"Descripcion","Tipo","Costo de produccion","Precio Mayorista","Precio Minorista","Punto de Rep minimo","Talle","Precio Venta","Cantidad por Lote"};
	
	private JTable tableTodosLosProd;
	private DefaultTableModel modelTablaProductos;
	private String[] nombreColumnasProductos = {"Descripcion","Tipo","Costo de produccion","Precio Mayorista","Precio Minorista","Punto de Rep minimo","Talle"};
	private JTextField textNombre;
	
	JButton btnSalir;
	JButton btnQuitar;
	JButton btnAgregar;
	JButton btnModificarCantidadPorLote;


	private JTextField textPrecioVenta;
	private JTextField textCantPorLote;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAsignarProductoAProveedor window = new VentanaAsignarProductoAProveedor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaAsignarProductoAProveedor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(150, 10, 1207, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
//		panel.setBounds(10, 0, 1126, 705);
//		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1100,800));
		
		JScrollPane scrollPaneFrame = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneFrame.setBounds(0, 0, 1183, 595);
		scrollPaneFrame.setViewportView(panel);
		frame.getContentPane().add(scrollPaneFrame);
//		panel.add(scrollPaneFrame);		
		/*
		JScrollPane scrollPaneTodosLosProd = new JScrollPane(this.tableTodosLosProd,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTodosLosProd.setBounds(10, 273, 1116, 168);
		
		tableTodosLosProd = new JTable(modelTablaProductos);
		tableTodosLosProd.setBounds(10, 535, 1126, 168);
		tableTodosLosProd.getTableHeader().setReorderingAllowed(false) ;
		scrollPaneTodosLosProd.setViewportView(tableTodosLosProd);
		frame.getContentPane().add(scrollPaneTodosLosProd);		
		
		*/
		JLabel lblAsignarProductoAProv = new JLabel("Asignar Producto a un proveedor");
		lblAsignarProductoAProv.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		lblAsignarProductoAProv.setBounds(10, 10, 1116, 46);
//		frame.getContentPane().add(lblAsignarProductoAProv);
		panel.add(lblAsignarProductoAProv);
		
		JLabel lblNewLabel = new JLabel("Proveedor elegido");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 66, 560, 30);
//		frame.getContentPane().add(lblNewLabel);
		panel.add(lblNewLabel);
		
		JLabel lblProductosDelProveedor = new JLabel("Productos del proveedor");
		lblProductosDelProveedor.setFont(new Font("Consolas", Font.PLAIN, 17));
		lblProductosDelProveedor.setBounds(10, 458, 231, 30);
//		frame.getContentPane().add(lblProductosDelProveedor);
		panel.add(lblProductosDelProveedor);
		
		JLabel lblListaDeProductos = new JLabel("Lista de Productos de la fabrica");
		lblListaDeProductos.setFont(new Font("Consolas", Font.PLAIN, 17));
		lblListaDeProductos.setBounds(10, 198, 322, 30);
//		frame.getContentPane().add(lblListaDeProductos);
		panel.add(lblListaDeProductos);
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		this.modelTablaProvElegido = new DefaultTableModel(null,this.nombreColumnasProvElegido){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};
		JScrollPane scrollPaneProvElegido = new JScrollPane(this.tableProvElegido,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneProvElegido.setBounds(10, 91, 1116, 97);
		
		tableProvElegido = new JTable(modelTablaProvElegido);
		tableProvElegido.setBackground(Color.WHITE);
		tableProvElegido.setBounds(10, 106, 1116, 97);
		tableProvElegido.getTableHeader().setReorderingAllowed(false) ;
		scrollPaneProvElegido.setViewportView(tableProvElegido);
//		frame.getContentPane().add(scrollPaneProvElegido);
		panel.add(scrollPaneProvElegido);
		
		
		////////////////////////////////////////////////////////////////////////////////////////////
		
		
		modelTablaProdDeProv = new DefaultTableModel(null,this.nombreColumnasProdDeProv){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};
		JScrollPane scrollPaneProdDeProv = new JScrollPane(this.tableProdDeProv,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneProdDeProv.setBounds(10, 495, 1116, 208);
		
		tableProdDeProv = new JTable(modelTablaProdDeProv);
		tableProdDeProv.setBounds(10, 252, 1116, 208);
		tableProdDeProv.getTableHeader().setReorderingAllowed(false) ;
		scrollPaneProdDeProv.setViewportView(tableProdDeProv);
//		frame.getContentPane().add(scrollPaneProdDeProv);
		panel.add(scrollPaneProdDeProv);
		
////////////////////////////////////////////////////////////////////////////////////////////
		
		modelTablaProductos = new DefaultTableModel(null,this.nombreColumnasProductos){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				return false;
			}
		};

		
		JScrollPane scrollPaneTodosLosProd = new JScrollPane(this.tableTodosLosProd,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTodosLosProd.setBounds(10, 273, 1116, 168);
		
		tableTodosLosProd = new JTable(modelTablaProductos);
		tableTodosLosProd.setBounds(10, 535, 1126, 168);
		tableTodosLosProd.getTableHeader().setReorderingAllowed(false) ;
		scrollPaneTodosLosProd.setViewportView(tableTodosLosProd);
//		frame.getContentPane().add(scrollPaneTodosLosProd);
		panel.add(scrollPaneTodosLosProd);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 238, 96, 25);
//		frame.getContentPane().add(lblNewLabel_1);
		panel.add(lblNewLabel_1);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNombre.setBounds(116, 238, 64, 25);
//		frame.getContentPane().add(lblNombre);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(179, 238, 132, 19);
//		frame.getContentPane().add(textNombre);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(1000, 216, 126, 25);
//		frame.getContentPane().add(btnAgregar);
		panel.add(btnAgregar);
		
	    btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(1010, 460, 126, 25);
//		frame.getContentPane().add(btnQuitar);
		panel.add(btnQuitar);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(1072, 713, 54, 46);
//		btnSalir.setIcon(setIcono("../imagenes/back.png",btnSalir));
//		frame.getContentPane().add(btnSalir);
		panel.add(btnSalir);
		
		textPrecioVenta = new JTextField();
		textPrecioVenta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textPrecioVenta.getText().length()>=9) {
                    e.consume();
                }
            }
        });
		textPrecioVenta.setBounds(762, 240, 96, 19);
//		frame.getContentPane().add(textPrecioVenta);
		panel.add(textPrecioVenta);
		textPrecioVenta.setColumns(10);
		
		textCantPorLote = new JTextField();
		textCantPorLote.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCantPorLote.getText().length()>=9) {
                    e.consume();
                }
            }
        });
		textCantPorLote.setColumns(10);
		textCantPorLote.setBounds(868, 240, 96, 19);
//		frame.getContentPane().add(textCantPorLote);
		panel.add(textCantPorLote);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setBounds(762, 215, 89, 13);
//		frame.getContentPane().add(lblPrecioVenta);
		panel.add(lblPrecioVenta);
		
		JLabel lblCantidadPorLote = new JLabel("Cantidad por lote");
		lblCantidadPorLote.setBounds(868, 215, 89, 13);
//		frame.getContentPane().add(lblCantidadPorLote);
		panel.add(lblCantidadPorLote);
		
		btnModificarCantidadPorLote = new JButton("Modificar Cantidad por lote");
		btnModificarCantidadPorLote.setBounds(828, 462, 179, 25);
//		frame.getContentPane().add(btnModificarCantidadPorLote);
		panel.add(btnModificarCantidadPorLote);

	}
	
	public Icon setIcono(String url,JButton boton) {
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir?", 
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
	
	public JFrame getFrame() {
		return frame;
	}

	public JTable getTableProvElegido() {
		return tableProvElegido;
	}

	public DefaultTableModel getModelTablaProvElegido() {
		return modelTablaProvElegido;
	}

	public String[] getNombreColumnasProvElegido() {
		return nombreColumnasProvElegido;
	}

	public JTable getTableProdDeProv() {
		return tableProdDeProv;
	}

	public DefaultTableModel getModelTablaProdDeProv() {
		return modelTablaProdDeProv;
	}

	public String[] getNombreColumnasProdDeProv() {
		return nombreColumnasProdDeProv;
	}

	public JTable getTableTodosLosProd() {
		return tableTodosLosProd;
	}

	public DefaultTableModel getModelTablaProductos() {
		return modelTablaProductos;
	}

	public String[] getNombreColumnasProductos() {
		return nombreColumnasProductos;
	}

	public JTextField getTextNombre() {
		return textNombre;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}
	public JTextField getTextPrecioVenta() {
		return textPrecioVenta;
	}

	public JTextField getTextCantPorLote() {
		return textCantPorLote;
	}
	
	
	public JButton getBtnModificarCantidadPorLote() {
		return btnModificarCantidadPorLote;
	}
}
