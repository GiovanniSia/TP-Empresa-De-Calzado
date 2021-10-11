package modelo;

import java.util.List;

import dto.CarritoDTO;
import persistencia.dao.interfaz.CarritoDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;

public class Carrito {
	private CarritoDAO carrito;
	
	public Carrito(DAOAbstractFactory metodo_presistencia) {
		this.carrito = metodo_presistencia.createCarritoDAO();
	}
	
	public boolean insert(CarritoDTO carrito) {
		return this.carrito.insert(carrito);
	}
	public boolean delete (CarritoDTO carrito) {
		return this.carrito.delete(carrito);
	}
	public List<CarritoDTO> readAll(){
		return this.carrito.readAll();
	}

	public List<CarritoDTO> getCarritoFiltrado(String nombreColumna1, String txt1, String nombreColumna2, String txt2, String nombreColumna3,String txt3){
		return this.carrito.getCarritoFiltrado(nombreColumna1, txt1, nombreColumna2, txt2, nombreColumna3, txt3);
	}
}
