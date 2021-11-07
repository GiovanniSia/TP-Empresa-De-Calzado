package modelo;

import java.util.List;

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

	public boolean update(ProveedorDTO proveedor,int idProveedor) {
		return this.proveedor.update(proveedor, idProveedor);
	}
	
	public List<ProveedorDTO> readAll(){
		return this.proveedor.readAll();
	}
	
	public List<ProveedorDTO> getProveedorAproximado(String nombreColumna,String txt){
		return this.proveedor.getProveedorAproximado(nombreColumna, txt);
	}
}
