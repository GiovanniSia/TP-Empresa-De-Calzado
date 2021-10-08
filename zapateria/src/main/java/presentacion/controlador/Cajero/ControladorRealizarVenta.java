package presentacion.controlador.Cajero;

import dto.CarritoDTO;
import dto.DetalleCarritoDTO;
import presentacion.vista.Cajero.VentanaRealizarVenta;

public class ControladorRealizarVenta {
	
	VentanaRealizarVenta ventanaRealizarVenta;
	
	CarritoDTO carritoACobrar;
	DetalleCarritoDTO detalleCarritoACobrar;
	
	
	
	
	public ControladorRealizarVenta() {
		
	}
	
	public void establecerCarritoACobrar(CarritoDTO carrito,DetalleCarritoDTO detalle) {
		this.carritoACobrar=carrito;
		this.detalleCarritoACobrar=detalle;
	}
	
	
	public void inicializar() {
		
	}
	
}
