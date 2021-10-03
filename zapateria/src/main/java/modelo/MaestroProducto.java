package modelo;

import java.util.List;

import dto.MaestroProductoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.MaestroProductoDAO;

public class MaestroProducto {
	
	private MaestroProductoDAO maestroProducto;
	
	public MaestroProducto(DAOAbstractFactory metodo_persistencia) {
		this.maestroProducto = metodo_persistencia.createMaestroProductoDAO();
	}

	public List<MaestroProductoDTO> readAll(){
		return this.maestroProducto.readAll();
	}
}
