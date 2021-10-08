package modelo;

import java.util.List;

import dto.DetalleCarritoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DetalleCarritoDAO;

public class DetalleCarrito {
	
	private DetalleCarritoDAO detalle;
	
	public DetalleCarrito(DAOAbstractFactory metodo_persistencia) {
		this.detalle = metodo_persistencia.createDetalleCarritoDAO();
	}
	
	public boolean insert(DetalleCarritoDTO detalle) {
		return this.detalle.insert(detalle);
	}
	
	public boolean delete(DetalleCarritoDTO detalle) {
		return this.detalle.delete(detalle);
	}
	
	public List<DetalleCarritoDTO> readAll(){
		return this.detalle.readAll();
	}
}
