package modelo;

import dto.ProductoDeProveedorDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ProductoDeProveedorDAO;

public class ProductoDeProveedor {
	
	private ProductoDeProveedorDAO productoDeProveedor;
	
	public ProductoDeProveedor(DAOAbstractFactory metodo_persistencia) {
		this.productoDeProveedor = metodo_persistencia.createProductoDeProveedorDAO();
	}
	
	public boolean insert(ProductoDeProveedorDTO proveedor) {
		return this.productoDeProveedor.insert(proveedor);
	}
	
}
