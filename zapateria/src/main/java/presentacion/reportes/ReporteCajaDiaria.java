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

public class ReporteCajaDiaria {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	private Logger log = Logger.getLogger(ReporteProducto.class);

	// Recibe la lista de personas para armar el reporte
	public ReporteCajaDiaria(int idSucursal) {
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("IdSucursal", idSucursal+"");
		try {
			/*
			 * File n = new File(""); String dir =
			 * n.getAbsolutePath()+"\\reportes\\Factura_A_-_Zapateria.jrxml"; this.reporte =
			 * JasperCompileManager.compileReport(dir);
			 */
			
			this.reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reportes" + File.separator + "caja" + File.separator + "Caja_Diaria_-_Zapateria.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,
					Conexion.getConexion().getSQLConexion());
			log.info("Se cargó correctamente el reporte");
		} catch (JRException ex) {
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
	}

	public void mostrarUltimoReporte() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}

}