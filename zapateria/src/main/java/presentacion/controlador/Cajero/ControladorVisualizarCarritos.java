package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.DetalleCarritoDTO;
import dto.MaestroProductoDTO;
import modelo.Carrito;
import modelo.Cliente;
import modelo.DetalleCarrito;
import modelo.MaestroProducto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.Cajero.VentanaVisualizarCarritos;

public class ControladorVisualizarCarritos {

	private final int idSucursal=1;
	
	Carrito carrito;
	DetalleCarrito detalleCarrito;
	Cliente cliente;
	MaestroProducto maestroProducto;
	
	List<CarritoDTO> listaCarritos;
	List<DetalleCarritoDTO> listaDetalleCarrito;
	List<ClienteDTO> listaClientes;
	
	List<CarritoDTO> carritosEnTabla;
	List<DetalleCarritoDTO> detalleCarritoEnTabla;
	
	VentanaVisualizarCarritos ventanaVisualizarCarritos;
	ControladorRealizarVenta controladorRealizarVenta;
	
	public ControladorVisualizarCarritos(Carrito carrito, DetalleCarrito detalleCarrito,Cliente cliente, MaestroProducto maestroProducto) {
		this.carrito = carrito;
		this.detalleCarrito = detalleCarrito;
		this.cliente = cliente;
		this.maestroProducto = maestroProducto;
		
		this.listaCarritos = new ArrayList<CarritoDTO>();
		this.listaDetalleCarrito = new ArrayList<DetalleCarritoDTO>();
		this.listaClientes = new ArrayList<ClienteDTO>();
		this.carritosEnTabla = new ArrayList<CarritoDTO>();
		this.detalleCarritoEnTabla = new ArrayList<DetalleCarritoDTO>();
		this.controladorRealizarVenta = new ControladorRealizarVenta();
	}

	
	
	public void inicializar() {
		
		this.listaCarritos = this.carrito.readAll();
		this.listaDetalleCarrito = this.detalleCarrito.readAll();
		this.listaClientes = this.cliente.readAll();
		
		this.ventanaVisualizarCarritos = new VentanaVisualizarCarritos();
		
		this.ventanaVisualizarCarritos.getTableCarritos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle();
			}
		});
		
		this.ventanaVisualizarCarritos.getTextCUIL().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaVisualizarCarritos.getTextNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.ventanaVisualizarCarritos.getTextApellido().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		
		this.ventanaVisualizarCarritos.getBtnElegirCarrito().addActionListener(a -> pasarVentana(a));
		
		
		
		llenarTablaCompleta();
		this.ventanaVisualizarCarritos.show();
	}
	
	
	
	public void realizarBusqueda() {
		String nombre = this.ventanaVisualizarCarritos.getTextNombre().getText();
		String apellido = this.ventanaVisualizarCarritos.getTextApellido().getText();
		String CUIL = this.ventanaVisualizarCarritos.getTextCUIL().getText();
		
		ArrayList<ClienteDTO> clientesConCarrito = (ArrayList<ClienteDTO>) this.cliente.getClienteAproximado("Nombre", nombre, "Apellido", apellido, "CUIL", CUIL,null, null, null, null);
		escribirTablaFiltrada(clientesConCarrito);
	}
	
	
	public void escribirTablaFiltrada(ArrayList<ClienteDTO> clientesConCarrito) {
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setRowCount(0);//borrar datos de la tabla
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setColumnCount(0);
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setColumnIdentifiers(this.ventanaVisualizarCarritos.getNombreColumnasCarritos());
		this.carritosEnTabla.removeAll(this.carritosEnTabla);
		
		for(CarritoDTO carrito: this.listaCarritos) {
			for(ClienteDTO cliente: clientesConCarrito) {
				System.out.println("cliente agreagdo: "+cliente.getNombre());
				if(carrito.getIdCliente()==cliente.getIdCliente() && carrito.getIdSucursal()==this.idSucursal) {
					agregarATabla(cliente, carrito);
				}
			}
		}
	}
	
	public void llenarTablaCompleta() {
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setRowCount(0);//borrar datos de la tabla
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setColumnCount(0);
		this.ventanaVisualizarCarritos.getModelTablaCarritos().setColumnIdentifiers(this.ventanaVisualizarCarritos.getNombreColumnasCarritos());
		
//		this.carritosEnTabla.removeAll(carritosEnTabla);
		
		for(CarritoDTO carrito: this.listaCarritos) {
			if(carrito.getIdSucursal()==this.idSucursal	&& !yaFueAgregado(carrito)) {
		
				ClienteDTO cliente = this.cliente.selectCliente(carrito.getIdCliente());
				agregarATabla(cliente,carrito);
			}
			//"CUIL","Nombre","Hora","Tipo Cliente","P. Total Venta"}
		}		
	}
	
	public boolean yaFueAgregado(CarritoDTO carrito) {
		for(CarritoDTO c: this.carritosEnTabla) {
			if(carrito.getIdCarrito()==c.getIdCarrito()) {
				return true;
			}
		}
		return false;
	}
	
	
	public void agregarATabla(ClienteDTO cliente, CarritoDTO carrito) {
		String CUIL = cliente.getCUIL();
		String nombreCliente = cliente.getNombre()+" "+cliente.getApellido();
		String hora = carrito.getHora();
		String TipoCliente = cliente.getTipoCliente();

		double precioTota = carrito.getTotal();
		BigDecimal precioTotal = new BigDecimal(precioTota);
		
		Object[] fila = {CUIL,nombreCliente,hora,TipoCliente,precioTotal};
		this.ventanaVisualizarCarritos.getModelTablaCarritos().addRow(fila);
			
		this.carritosEnTabla.add(carrito);
	}
	
	
	public void mostrarDetalle() {
		this.detalleCarritoEnTabla.removeAll(detalleCarritoEnTabla);
		this.ventanaVisualizarCarritos.getModelTablaDetalle().setRowCount(0);//borrar datos de la tabla
		this.ventanaVisualizarCarritos.getModelTablaDetalle().setColumnCount(0);
		this.ventanaVisualizarCarritos.getModelTablaDetalle().setColumnIdentifiers(this.ventanaVisualizarCarritos.getNombreColumnasDetalle());
		this.detalleCarritoEnTabla.removeAll(this.detalleCarritoEnTabla);
		
		int filaSeleccionada = this.ventanaVisualizarCarritos.getTableCarritos().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "wtf esto no deberia aparecer xd");
			return;
		}
		CarritoDTO carritoSeleccionado = this.carritosEnTabla.get(filaSeleccionada);
		
		DetalleCarritoDTO detalleCarrito = getDetalle(carritoSeleccionado);	
		System.out.println("el total del carrito seleccionado es :"+carritoSeleccionado.getTotal()+"\nEL id del carrito es :"+carritoSeleccionado.getIdCarrito()+" el id del detalle: "+detalleCarrito.getIdCarrito());
		
		MaestroProductoDTO prod = this.maestroProducto.selectMaestroProducto(detalleCarrito.getIdProducto());
		String nombreProd = prod.getDescripcion();
		int cant = detalleCarrito.getCantidad();
		double p = detalleCarrito.getPrecio()*cant;
		BigDecimal precio = new BigDecimal(p);
				
		Object[] fila = {nombreProd,cant,precio};
		this.ventanaVisualizarCarritos.getModelTablaDetalle().addRow(fila);
		this.detalleCarritoEnTabla.add(detalleCarrito);
			
		
	
		//"Productos","Cantidad","P. Unitario"
	}
	
	public DetalleCarritoDTO getDetalle(CarritoDTO carrito) {
		for(DetalleCarritoDTO detalle: this.listaDetalleCarrito) {
			if(detalle.getIdCarrito()==carrito.getIdCarrito()) {
				return detalle;
			}
		}
		return null;
	}
	
	public void pasarVentana(ActionEvent a) {
		int filaSeleccionada = this.ventanaVisualizarCarritos.getTableCarritos().getSelectedRow();
		
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ning�n carrito!");
			return;
		
		}
		int resp = JOptionPane.showConfirmDialog(null, "Pasar a cobrar producto?", "Cobrar Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(this.carritosEnTabla.size()==0) {
			JOptionPane.showMessageDialog(null, "No hay ning�n carrito para cobrar!");
			return;
		
		}

		CarritoDTO carrito = this.carritosEnTabla.get(filaSeleccionada);
		DetalleCarritoDTO detalleCarrito = getDetalle(carrito);
		
		//si selecciona que si devuelve un 0, no un 1, y la x un -1
		if(resp==0) {
			controladorRealizarVenta.establecerCarritoACobrar(carrito, detalleCarrito);
			controladorRealizarVenta.inicializar();
		}
		
		
	}
	
	public static void main(String[] args) {
		Carrito carrito = new Carrito(new DAOSQLFactory());
		DetalleCarrito detalleCarrito= new DetalleCarrito(new DAOSQLFactory());
		Cliente cliente = new Cliente(new DAOSQLFactory());
		MaestroProducto maestroProducto = new MaestroProducto(new DAOSQLFactory());
		ControladorVisualizarCarritos c = new ControladorVisualizarCarritos(carrito,detalleCarrito,cliente,maestroProducto);
		c.inicializar();
	}
	
}
