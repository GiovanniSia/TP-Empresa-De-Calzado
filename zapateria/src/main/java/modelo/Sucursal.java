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
	
	public boolean insert(SucursalDTO empleado) {
		return this.sucursal.insert(empleado);
	}
		

	public boolean delete(SucursalDTO empleado_a_eliminar) {
		return this.sucursal.delete(empleado_a_eliminar);
	}
	
	public boolean update(int id_empleado_a_actualizar, SucursalDTO empleado_nuevo) {
		return this.sucursal.update(id_empleado_a_actualizar, empleado_nuevo);
	}

	public List<SucursalDTO> readAll(){
		return this.sucursal.readAll();
	}
	
	public SucursalDTO select(int idSucursal) {
		return this.sucursal.select(idSucursal);
	}
	
	public List<SucursalDTO> obtenerListaFiltrada(String nombreColumna1, String txt1){
		return this.sucursal.obtenerListaFiltrada(nombreColumna1,txt1);
	}
	
}
