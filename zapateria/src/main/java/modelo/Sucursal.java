package modelo;

import java.util.List;

import dto.SucursalDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.SucursalDAO;

public class Sucursal {
	private SucursalDAO sucursal;
	
	public Sucursal(DAOAbstractFactory metodo_persistencia) {
		this.sucursal = metodo_persistencia.createSucursalDAO();
	}
	
	public void insert(SucursalDTO empleado) {
		this.sucursal.insert(empleado);
	}
		

	public void delete(SucursalDTO empleado_a_eliminar) {
		this.sucursal.delete(empleado_a_eliminar);
	}
	
	public void update(int id_empleado_a_actualizar, SucursalDTO empleado_nuevo) {
		this.sucursal.update(id_empleado_a_actualizar, empleado_nuevo);
	}

	public List<SucursalDTO> readAll(){
		return this.sucursal.readAll();
	}
	
	public SucursalDTO select(int idSucursal) {
		return this.sucursal.select(idSucursal);
	}
}
