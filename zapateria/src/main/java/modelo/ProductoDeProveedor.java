package modelo;

import java.util.List;

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
	
	public boolean delete(ProductoDeProveedorDTO producto) {
		return this.productoDeProveedor.delete(producto);
	}
	
	public List<ProductoDeProveedorDTO> readAll(){
		return this.productoDeProveedor.readAll();
	}
	
	public boolean updateCantidadPorLote(double cantidad,int id) {
		return this.productoDeProveedor.updateCantidadPorLote(cantidad, id);
	}
}
