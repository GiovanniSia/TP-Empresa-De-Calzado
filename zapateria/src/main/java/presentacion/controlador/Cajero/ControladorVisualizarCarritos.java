package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
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
		
		this.ventanaVisualizarCarritos.getBtnElegirCarrito().addActionListener(a -> pasarVentana(a));
		
		llenarTabla();
		this.ventanaVisualizarCarritos.show();
	}
	
	
	public void llenarTabla() {

		
		//ahora hay dos tablas: una para el carrito y otra para ver el detalle
		for(CarritoDTO carrito: this.listaCarritos) {
			for(DetalleCarritoDTO detalle: this.listaDetalleCarrito) {
				if(carrito.getIdCarrito()==detalle.getIdCarrito() && carrito.getIdSucursal()==this.idSucursal
						&& !yaFueAgregado(carrito)) {
					ClienteDTO cliente = this.cliente.selectCliente(detalle.getIdCliente());
					
					int idCarrito=carrito.getIdCarrito();
					String hora = carrito.getHora();
					int idCliente=detalle.getIdCarrito();
					String nombreCliente = cliente.getNombre()+" "+cliente.getApellido();
					String tipoCliente = cliente.getTipoCliente();
					double precioTotal = carrito.getTotal();		
					
					Object[] fila = {idCarrito,hora,idCliente,nombreCliente,tipoCliente,precioTotal};
					this.ventanaVisualizarCarritos.getModelTablaSucursales().addRow(fila);
					
					this.carritosEnTabla.add(carrito);
				}
			}
			

		}		
	}
	
	public boolean yaFueAgregado(CarritoDTO carrito) {
		for(CarritoDTO c: this.carritosEnTabla) {
			System.out.println("carrito a ver: "+carrito.getIdCarrito()+" carritoEnTabla: "+c.getIdCarrito());
			if(carrito.getIdCarrito()==c.getIdCarrito()) {
				return true;
			}
		}
		return false;
	}
	
	public void mostrarDetalle() {
		this.detalleCarritoEnTabla.removeAll(detalleCarritoEnTabla);
		this.ventanaVisualizarCarritos.getModelTablaDetalle().setRowCount(0);//borrar datos de la tabla
		this.ventanaVisualizarCarritos.getModelTablaDetalle().setColumnCount(0);
		this.ventanaVisualizarCarritos.getModelTablaDetalle().setColumnIdentifiers(this.ventanaVisualizarCarritos.getNombreColumnasDetalle());
		
		int filaSeleccionada = this.ventanaVisualizarCarritos.getTableCarritos().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "wtf esto no deberia aparecer xd");
			return;
		}
		CarritoDTO carritoSeleccionado = this.carritosEnTabla.get(filaSeleccionada);
		for(DetalleCarritoDTO detalle: this.listaDetalleCarrito) {
			if(detalle.getIdCarrito()==carritoSeleccionado.getIdCarrito() && carritoSeleccionado.getIdSucursal()==this.idSucursal) {
				MaestroProductoDTO prod = this.maestroProducto.selectMaestroProducto(detalle.getIdProducto());
				String nombreProd = prod.getDescripcion();
				int cant = detalle.getCantidad();
				double p = detalle.getPrecio()*cant;
				BigDecimal precio = new BigDecimal(p);
				
				Object[] fila = {nombreProd,cant,precio};
				this.ventanaVisualizarCarritos.getModelTablaDetalle().addRow(fila);
				this.detalleCarritoEnTabla.add(detalle);
			}
		}
	
		//"Productos","Cantidad","P. Unitario"
	}
	
	public void pasarVentana(ActionEvent a) {
		int filaSeleccionada = this.ventanaVisualizarCarritos.getTableCarritos().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningún carrito!");
			return;
		
		}
		int resp = JOptionPane.showConfirmDialog(null, "Pasar a cobrar producto?", "Cobrar Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(this.carritosEnTabla.size()==0) {
			JOptionPane.showMessageDialog(null, "No hay ningún carrito para cobrar!");
			return;
		
		}

		//si selecciona que si devuelve un 0, no un 1, y la x un -1
		if(resp==0) {
			
			this.ventanaVisualizarCarritos.cerrar();
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
