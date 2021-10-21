package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dto.PedidosPendientesDTO;
import modelo.PedidosPendientes;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.Supervisor.VentanaVerPedidosAProveedores;

public class ControladorVerPedidosAProveedor {

	PedidosPendientes pedidosPendientes;
	
	List<PedidosPendientesDTO> todosLosPedidosPendientes;
	List<PedidosPendientesDTO> pedidosPendientesEnTabla;
	
	VentanaVerPedidosAProveedores ventanaVerPedidosAProveedor;
	
	public ControladorVerPedidosAProveedor(PedidosPendientes pedidosPendientes) {
		this.pedidosPendientes = pedidosPendientes;
		this.todosLosPedidosPendientes = new ArrayList<PedidosPendientesDTO>();
		this.pedidosPendientesEnTabla = new ArrayList<PedidosPendientesDTO>();
		this.ventanaVerPedidosAProveedor = new VentanaVerPedidosAProveedores();
	}
	
	public void inicializar() {
		this.todosLosPedidosPendientes = this.pedidosPendientes.readAll();
		
		this.ventanaVerPedidosAProveedor.getBtnSalir().addActionListener(a -> salir(a));
		
		llenarTabla();
	}
	
	
	public void mostrarVentana() {
		this.ventanaVerPedidosAProveedor.show();
	}
	
	public void cerrarVentana() {
		this.ventanaVerPedidosAProveedor.cerrar();
	}
	
	
	public void salir(ActionEvent a) {
		cerrarVentana();
	}
	
	public void llenarTabla() {
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().setRowCount(0);//borrar datos de la tabla
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().setColumnCount(0);
		this.ventanaVerPedidosAProveedor.getModelTablaPedidos().setColumnIdentifiers(this.ventanaVerPedidosAProveedor.getNombreColumnasTablaPedidos());
//		{"Id,","Proveedor","Producto","Cantidad","Uni. Medida","Precio de pedido","Estado","Fecha - hora de alta","Fecha - hora de envio","Fecha - hora de cierre"};
		for(PedidosPendientesDTO p: this.todosLosPedidosPendientes) {
			int id = p.getId();
			String proveedor = p.getNombreProveedor();
			String prod = p.getNombreMaestroProducto();
			int cant = p.getCantidad();
			String unidadMedida = p.getUnidadMedida();
			
			double precioTota = p.getPrecioTotal();
			BigDecimal precioTotal = new BigDecimal(precioTota);
			
			
			
			String estado = p.getEstado();
			String fechaHora = p.getFecha()+" - "+p.getHora();
			String fechaHoraEnvio = p.getFechaEnvioMail() != null ? p.getFechaEnvioMail()+" - "+p.getHoraEnvioMail() : "-";
			String fechaHoraCierre = p.getFechaCompleto() != null ? p.getFechaCompleto()+" - "+p.getHoraCompleto() : "-";
			Object[] fila = {id,proveedor,prod,cant,unidadMedida,precioTotal,estado,fechaHora,fechaHoraEnvio,fechaHoraCierre};
			this.ventanaVerPedidosAProveedor.getModelTablaPedidos().addRow(fila);
			this.pedidosPendientesEnTabla.add(p);
		}
	}
	
	public static void main(String[] args) {
		PedidosPendientes pedido = new PedidosPendientes(new DAOSQLFactory());
		ControladorVerPedidosAProveedor c = new ControladorVerPedidosAProveedor(pedido);
		c.inicializar();
		c.mostrarVentana();
	}
	
}
