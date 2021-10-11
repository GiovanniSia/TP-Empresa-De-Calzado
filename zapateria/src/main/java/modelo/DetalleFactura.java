package modelo;

import java.util.List;

import dto.DetalleFacturaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DetalleFacturaDAO;


public class DetalleFactura {

	private DetalleFacturaDAO detalleFactura;
	
	public DetalleFactura(DAOAbstractFactory metodo_persistencia) {
		this.detalleFactura = metodo_persistencia.createDetalleFacturaDAO();
	}
	
	public boolean insert(DetalleFacturaDTO detalleFacturaDTO) {
		return this.detalleFactura.insert(detalleFacturaDTO);
	}
	
	public List<DetalleFacturaDTO> readAll(){
		return this.detalleFactura.readAll();
	}
	
	public DetalleFacturaDTO selectDetalleFactura(int id) {
		return this.detalleFactura.selectDetalleFactura(id);
	}
}
