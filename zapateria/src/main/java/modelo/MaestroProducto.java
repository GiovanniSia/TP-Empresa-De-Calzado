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
	
	public List<MaestroProductoDTO> getMaestroProductoAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2, String txtAprox2){
		return this.maestroProducto.getMaestroProductoAproximado(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2);
	}
	
	public List<MaestroProductoDTO> filtrarPorCodProducto(String valor){
		return this.maestroProducto.filtrarPorCodProducto(valor);
	}
	
	public List<MaestroProductoDTO> filtrarPorDescripcion(String valor){
		return this.maestroProducto.filtrarPorDescripcion(valor);
	}
	
	public List<MaestroProductoDTO> filtrarPorTalle(String valor){
		return this.maestroProducto.filtrarPorTalle(valor);
	}
}
