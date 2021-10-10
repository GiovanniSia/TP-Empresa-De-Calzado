package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.DetalleCarritoDTO;
import dto.IngresosDTO;
import dto.MedioPagoDTO;
import modelo.Cliente;
import modelo.MedioPago;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.Cajero.VentanaRealizarVenta;

public class ControladorRealizarVenta {
	
	public final int idSucursal=1;
	public double totalPagado;
	
	VentanaRealizarVenta ventanaRealizarVenta;
	
	CarritoDTO carritoACobrar;
	DetalleCarritoDTO detalleCarritoACobrar;
	
	MedioPago medioPago;
	Cliente cliente;
	
	List<MedioPagoDTO> listamediosDePago;
	
	List<IngresosDTO> listaDeIngresosARegistrar;
	
	public ControladorRealizarVenta() {
		this.medioPago=new MedioPago(new DAOSQLFactory());;
		this.cliente = new Cliente(new DAOSQLFactory());
		this.listamediosDePago = new ArrayList<MedioPagoDTO>();
		this.listaDeIngresosARegistrar = new ArrayList<IngresosDTO>();
	}
	
	public void establecerCarritoACobrar(CarritoDTO carrito,DetalleCarritoDTO detalle) {
		this.carritoACobrar=carrito;
		this.detalleCarritoACobrar=detalle;
	}
	
	
	public void inicializar() {
		this.ventanaRealizarVenta = new VentanaRealizarVenta();
		
		this.ventanaRealizarVenta.getBtnAgregarMedioPago().addActionListener(a -> agregarMedioDePago(a));
		this.ventanaRealizarVenta.getBtnFinalizarVenta().addActionListener(a -> registrarPago(a));
		
		this.listamediosDePago = this.medioPago.readAll();
		
		llenarCbMedioPago();
		actualizarTablaMedioPago();
		this.ventanaRealizarVenta.getLblTotalAPagarValor().setText(""+carritoACobrar.getTotal());
		this.ventanaRealizarVenta.getLblPrecioVentaValor().setText(""+carritoACobrar.getTotal());
		this.ventanaRealizarVenta.show();
	}
	
	
	
	public void llenarCbMedioPago() {
		for(MedioPagoDTO m: this.listamediosDePago) {
			this.ventanaRealizarVenta.getComboBoxMetodoPago().addItem(m.getDescripcion());
		}
	}
	
	public void agregarMedioDePago(ActionEvent a) {
		String nroOperacion = this.ventanaRealizarVenta.getTextNumOperacion().getText();
//		if(nroOperacion==null) {
//			JOptionPane.showMessageDialog(null, "Debe agregar el número de operación para agregar el medio de pago")
//			return;
//		}
		
		double cantidad =(double) Double.parseDouble(ventanaRealizarVenta.getTextCantidad().getText());
		String metodoPago =(String) this.ventanaRealizarVenta.getComboBoxMetodoPago().getSelectedItem();
		double valorConversion;
		for(MedioPagoDTO m: this.listamediosDePago) {
			if(m.getDescripcion().equals(metodoPago)) {
		        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				String fecha = f.format(LocalDateTime.now());
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		        String hora = dtf.format(LocalDateTime.now());
				int idCliente = this.detalleCarritoACobrar.getIdCliente();
				valorConversion=m.getTasaConversion();
				double totalArg = cantidad *valorConversion;

				
				//EL NRO DE FACTURA SE DEBERA SETEAR AL MOMENTO DE REALIZAR EL COBRO, RECORRER TODOS LOS INGRESOS Y SETEARLE A CADA UNO EL NRO DE FACTURA GENERADO
				IngresosDTO ingreso = new IngresosDTO(0,this.idSucursal,fecha,hora,"VT",idCliente,"NOSE XD","0",metodoPago,cantidad,valorConversion,nroOperacion,totalArg);
				this.listaDeIngresosARegistrar.add(ingreso);
				//{"Método","Moneda","Nom. Tarjeta","Cantidad","Cant. (en AR$)"};
				}
		}
		actualizarTablaMedioPago();
		System.out.println("Cantidad de ingresos por ingresar: "+this.listaDeIngresosARegistrar.size());
	}
	
	public void actualizarTablaMedioPago() {
		this.ventanaRealizarVenta.getModelTablaMedioPago().setRowCount(0);//borrar datos de la tabla
		this.ventanaRealizarVenta.getModelTablaMedioPago().setColumnCount(0);
		this.ventanaRealizarVenta.getModelTablaMedioPago().setColumnIdentifiers(this.ventanaRealizarVenta.getNombreColumnasMedioPago());
		
		for(IngresosDTO i: this.listaDeIngresosARegistrar) {
			for(MedioPagoDTO medioPago: this.listamediosDePago) {
				if(i.getMedioPago().equals(medioPago.getDescripcion())) {
					String metodoPago=i.getMedioPago();
					double valorConversion = medioPago.getTasaConversion();
					String moneda = valorConversion==1 ? "AR$"  : medioPago.getIdMoneda()+"$";
					String nombreTarjeta = medioPago.getIdMoneda().charAt(0)=='T' ? medioPago.getDescripcion() : "-";
					System.out.println("el id moneda fue: "+nombreTarjeta+" \nla primer letra del id moneda fue: "+medioPago.getIdMoneda().charAt(0));
					double cantidad = i.getCantidad();
					double totalArg = cantidad *valorConversion;
					
					this.totalPagado = this.totalPagado+totalArg;
					
					Object[] fila = {metodoPago,moneda,nombreTarjeta,cantidad,totalArg};
					this.ventanaRealizarVenta.getModelTablaMedioPago().addRow(fila);
				}
				
			}
			
		}
		this.ventanaRealizarVenta.getLblPrecioVentaValor().setText(""+this.totalPagado);
	}

	public void registrarPago(ActionEvent a) {
		if(this.totalPagado<=this.carritoACobrar.getTotal()) {
			ClienteDTO cliente = this.cliente.selectCliente(this.detalleCarritoACobrar.getIdCliente());
			if(cliente.getIdCliente()!=1) {//si el cliente está registrado
				int resp = JOptionPane.showConfirmDialog(null, "Todavía no se han pagado todos los productos!. Desea adeudarlo con su CC (Cuenta corriente)?", "Realizar Pago", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				
				this.ventanaRealizarVenta.cerrar();
				return;
			}
			JOptionPane.showMessageDialog(null, "Todavía no se han pagado todos los productos!");
			return;
		}
	}
	
	public static void main(String[] args) {
//		new ControladorRealizarVenta(new MedioPago(new DAOSQLFactory()));
	}
	
}
