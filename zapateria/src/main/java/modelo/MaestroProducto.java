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

	public void insert(MaestroProductoDTO empleado) {
		this.maestroProducto.insert(empleado);
	}

	public void delete(MaestroProductoDTO maestroProducto_a_eliminar) {
		this.maestroProducto.delete(maestroProducto_a_eliminar);
	}

	public void update(int id_maestroProducto_a_actualizar, MaestroProductoDTO maestroProducto_nuevo) {
		this.maestroProducto.update(id_maestroProducto_a_actualizar, maestroProducto_nuevo);
	}

	public List<MaestroProductoDTO> readAll() {
		return this.maestroProducto.readAll();
	}

	public List<MaestroProductoDTO> getMaestroProductoAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2,String nombreColumna3,String txtAprox3,String nombreColumna4, String txtAprox4) {
		return this.maestroProducto.getMaestroProductoAproximado(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2,nombreColumna3,txtAprox3,nombreColumna4,txtAprox4);
	}
	

	public List<MaestroProductoDTO> getFiltroModificarMProdcto(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2,String nombreColumna3, String txtAprox3,String nombreColumna4, String txtAprox4) {
		return this.maestroProducto.getFiltroModificarMProdcto(nombreColumna1, txtAprox1, nombreColumna2, txtAprox2,nombreColumna3, txtAprox3,nombreColumna4,txtAprox4);
	}
	public MaestroProductoDTO selectMaestroProducto(int idMaestroProducto) {
		return this.maestroProducto.selectMaestroProducto(idMaestroProducto);
	}
}
