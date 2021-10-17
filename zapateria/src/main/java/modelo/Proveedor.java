package modelo;

import dto.ProveedorDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ProveedorDAO;

public class Proveedor {
	
	private ProveedorDAO proveedor;
	
	public Proveedor(DAOAbstractFactory metodo_persistencia) {
		proveedor = metodo_persistencia.createProveedorDAO();
	}
	
	public boolean insert(ProveedorDTO proveedor) {
		return this.proveedor.insert(proveedor);
	}

}
