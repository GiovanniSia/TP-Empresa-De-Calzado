package modelo.compraVirtual;

import java.util.List;

import dto.MotivoEgresoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.MotivoEgresoDAO;

public class MotivoEgreso {
	
	MotivoEgresoDAO modeloEgreso;

	public MotivoEgreso(DAOAbstractFactory metodo_presistencia) {
		this.modeloEgreso = metodo_presistencia.createMotivoEgresoDAO();
	}
	
	public boolean insert(MotivoEgresoDTO m) {
		return modeloEgreso.insert(m);
	}
	
	public List<MotivoEgresoDTO> readAll(){
		return modeloEgreso.readAll();
	}
	
	

}
