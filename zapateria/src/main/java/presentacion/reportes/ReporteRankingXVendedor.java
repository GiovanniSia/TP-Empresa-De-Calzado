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

public class ReporteRankingXVendedor {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	private Logger log = Logger.getLogger(ReporteProducto.class);

	@SuppressWarnings("deprecation")
	public ReporteRankingXVendedor(String fechaDesde, String fechaHasta) { //EL FORMATO ES EL YYYY-MM-DD
		String[] fechaAux = palabrasPorBarra(fechaDesde);
		int anioAux = Integer.valueOf(fechaAux[0]);
		int mesAux = Integer.valueOf(fechaAux[1]);
		int diaAux = Integer.valueOf(fechaAux[2]);
		java.sql.Date fechaDate = new java.sql.Date(anioAux-1900, mesAux-1, diaAux);
		System.out.println(fechaDate);
		
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("fechaDesde", fechaDate);
		
		fechaAux = palabrasPorBarra(fechaHasta);
		anioAux = Integer.valueOf(fechaAux[0]);
		mesAux = Integer.valueOf(fechaAux[1]);
		diaAux = Integer.valueOf(fechaAux[2]);
		fechaDate = new java.sql.Date(anioAux-1900, mesAux-1, diaAux);
		parametersMap.put("fechaHasta", fechaDate);
		try {
			/*
			 * File n = new File(""); String dir =
			 * n.getAbsolutePath()+"\\reportes\\Factura_A_-_Zapateria.jrxml"; this.reporte =
			 * JasperCompileManager.compileReport(dir);
			 */
			
			this.reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reportes" + File.separator +"rankingVendedores"+File.separator+"rankingVentasXvendedor.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,
					Conexion.getConexion().getSQLConexion());
			log.info("Se cargó correctamente el reporte");
		} catch (JRException ex) {
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
	}

	public void mostrarReporte() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}
	
	private String[] palabrasPorBarra(String sentence) {
		if (sentence == null || sentence.isEmpty()) {
			return new String[0];
		}
		String[] words = sentence.split("-");
		return words;
	}
	
	public static void main(String[] args) {
		ReporteRankingXVendedor reporteRanking = new ReporteRankingXVendedor("2021-10-23","2021-11-23");
		reporteRanking.mostrarReporte();
	}
}