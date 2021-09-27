package modelo;

import java.util.List;
import dto.ProductoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ProductoDAO;

public class Zapateria {
	private ProductoDAO producto;

	public Zapateria(DAOAbstractFactory metodo_persistencia) {
		this.producto = metodo_persistencia.createProductoDAO();
	}

	public void agregarProducto(ProductoDTO nuevoProducto) {
		this.producto.insert(nuevoProducto);
	}

	public void borrarProducto(ProductoDTO productoEliminar) {
		this.producto.delete(productoEliminar);
	}

	public List<ProductoDTO> obtenerProducto() {
		return this.producto.readAll();
	}

}
