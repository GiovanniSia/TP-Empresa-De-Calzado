package persistencia.dao.interfaz;

import java.util.List;

import dto.DetalleFacturaDTO;

public interface DetalleFacturaDAO {
	
	public boolean insert(DetalleFacturaDTO detalleFactura);
	
	public List<DetalleFacturaDTO> readAll();
	
	public DetalleFacturaDTO selectDetalleFactura(int id);
	
}
