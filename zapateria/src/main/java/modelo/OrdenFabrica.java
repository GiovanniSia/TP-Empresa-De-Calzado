package modelo;

import java.util.List;

import dto.OrdenFabricaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.OrdenFabricaDAO;

public class OrdenFabrica {
	
	private OrdenFabricaDAO ofd;
	
	public OrdenFabrica(DAOAbstractFactory metodo_persistencia) {
		ofd = metodo_persistencia.createOrdenFabricaDAO();
	}
	
	public boolean insert(OrdenFabricaDTO sucursal) {
		return ofd.insert(sucursal);
	}

	public List<OrdenFabricaDTO> readAll(){
		return ofd.readAll();
	}

}
