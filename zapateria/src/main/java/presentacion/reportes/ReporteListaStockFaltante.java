package presentacion.reportes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.conexion.Conexion;

public class ReporteListaStockFaltante {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	private Logger log = Logger.getLogger(ReporteProducto.class);
	
	public ReporteListaStockFaltante(int idSucursal, int flagMasChico, int flagMasGrande) {
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("IdSucursal", idSucursal);
		if(this.esFabrica(idSucursal)) {
			parametersMap.put("TipoProducto", "MP");
		}else {
			parametersMap.put("TipoProducto", "PT");
		}
		parametersMap.put("primerFlag", flagMasChico);
		parametersMap.put("segundoFlag", flagMasGrande);
		try {
			this.reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reportes"+File.separator+"reporteStock"+File.separator+"riesgoStock2.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,
					Conexion.getConexion().getSQLConexion());
			log.info("Se cargó correctamente el reporte");
		} catch (JRException ex) {
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
	}
	
	private boolean esFabrica(int idSucursal) {
		/*
		Sucursal daosql = new Sucursal(new DAOSQLFactory());
		List<SucursalDTO> sucursales = daosql.readAll();
		for(SucursalDTO suc: sucursales) {
			if(suc.getIdSucursal() == idSucursal) {
				
			}
		}*/
		return idSucursal == 1;
	}
	
	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}
	
	public static void main(String[] args){
		ReporteListaStockFaltante reporte = new ReporteListaStockFaltante(2,25,45);
		reporte.mostrar();
	}

}
