package modelo;

import java.util.List;

import dto.FacturaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.FacturaDAO;

public class Factura {
	
	private FacturaDAO factura;
	
	public Factura(DAOAbstractFactory metodo_persistencia) {
		this.factura = metodo_persistencia.createFacturaDAO();
	}
	
	public boolean insert(FacturaDTO factura) {
		return this.factura.insert(factura);
	}

	public List<FacturaDTO> readAll(){
		return this.factura.readAll();
	}
}
