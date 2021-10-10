package persistencia.dao.interfaz;

import java.util.List;

import dto.MaestroProductoDTO;

public interface MaestroProductoDAO {

	boolean insert(MaestroProductoDTO maestroProductos);

	boolean delete(MaestroProductoDTO maestroProductos);

	boolean update(int id_maestroProductos_a_actualizar, MaestroProductoDTO maestroProductos_nuevo);

	public List<MaestroProductoDTO> readAll();

	public List<MaestroProductoDTO> getMaestroProductoAproximado(String nombreColumna1, String txtAprox1,
			String nombreColumna2, String txtAprox2,String nombreColumna3,String txtAprox3, String nombreColumna4, String txtAprox4);
	
	public MaestroProductoDTO selectMaestroProducto(int idMaestroProducto);
}