package presentacion.reportes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import dto.ClienteDTO;
import dto.FacturaDTO;
import dto.SucursalDTO;
import modelo.EnviadorDeMails;
import modelo.Factura;
import modelo.Sucursal;
import modelo.Zapateria;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;

public class ReporteFactura {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	private Logger log = Logger.getLogger(ReporteProducto.class);
	
	private static final String dataMissing = "-";//"[Hiperlink error]"

	// Recibe la lista de personas para armar el reporte
	public ReporteFactura(String nroFacturaCompleto, int idSucursal,ClienteDTO cliente) {
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("NroFactura", nroFacturaCompleto);
		
		String direccionSucursal = "";
		Sucursal daosql = new Sucursal(new DAOSQLFactory());
		List<SucursalDTO> sucursales = daosql.readAll();
		for(SucursalDTO suc: sucursales) {
			if(suc.getIdSucursal() == idSucursal) {
				direccionSucursal = suc.getCalle() + suc.getAltura();
			}
		}
		if(direccionSucursal.equals("") || direccionSucursal == "") {
			direccionSucursal = dataMissing;
		}
		parametersMap.put("Direccion",direccionSucursal);
		Zapateria zapa = new Zapateria(); 
		parametersMap.put("Categoria", zapa.getCategoriaAFIP());
		parametersMap.put("Cuit", zapa.getCUIT());
		parametersMap.put("Correo", zapa.getCorreoElectronico());
		try {
			/*
			 * File n = new File(""); String dir =
			 * n.getAbsolutePath()+"\\reportes\\Factura_A_-_Zapateria.jrxml"; this.reporte =
			 * JasperCompileManager.compileReport(dir);
			 */
			
			this.reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reportes" + File.separator + "Factura_A_-_Zapateria.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,
					Conexion.getConexion().getSQLConexion());
			
			guardarFactura(this.reporte,this.reporteLleno,cliente);
			
			log.info("Se cargó correctamente el reporte");
		} catch (JRException ex) {
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
	}
	
	public ReporteFactura(String nroFacturaCompleto) {
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("NroFactura", nroFacturaCompleto);
		
		Factura modeloFactura = new Factura(new DAOSQLFactory());
		int idSucursal = 0;
		for(FacturaDTO facturas : modeloFactura.readAll()) {
			if(facturas.getNroFacturaCompleta().equals(nroFacturaCompleto)) {
				idSucursal = facturas.getIdSucursal();
			}
		}
		
		String direccionSucursal = "";
		Sucursal daosql = new Sucursal(new DAOSQLFactory());
		List<SucursalDTO> sucursales = daosql.readAll();
		for(SucursalDTO suc: sucursales) {
			if(suc.getIdSucursal() == idSucursal) {
				direccionSucursal = suc.getCalle() + suc.getAltura();
			}
		}
		if(direccionSucursal.equals("") || direccionSucursal == "") {
			direccionSucursal = dataMissing;
		}
		parametersMap.put("Direccion",direccionSucursal);
		Zapateria zapa = new Zapateria(); 
		parametersMap.put("Categoria", zapa.getCategoriaAFIP());
		parametersMap.put("Cuit", zapa.getCUIT());
		parametersMap.put("Correo", zapa.getCorreoElectronico());
		try {
			/*
			 * File n = new File(""); String dir =
			 * n.getAbsolutePath()+"\\reportes\\Factura_A_-_Zapateria.jrxml"; this.reporte =
			 * JasperCompileManager.compileReport(dir);
			 */
			
			this.reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reportes" + File.separator + "Factura_A_-_Zapateria.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,
					Conexion.getConexion().getSQLConexion());
						
			log.info("Se cargó correctamente el reporte");
			//descargarPDF(nroFacturaCompleto);
		} catch (JRException ex) {
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
	}
	
	
	public static void guardarYEnviarFacturaVirtual(String nroFacturaCompleto,ClienteDTO cliente) {
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("NroFactura", nroFacturaCompleto);
		System.out.println("nomrbeCliente: "+cliente.getNombre());
		Factura modeloFactura = new Factura(new DAOSQLFactory());
		int idSucursal = 0;
		for(FacturaDTO facturas : modeloFactura.readAll()) {
			if(facturas.getNroFacturaCompleta().equals(nroFacturaCompleto)) {
				idSucursal = facturas.getIdSucursal();
			}
		}
		System.out.println("idSuc"+idSucursal);
		String direccionSucursal = "";
		Sucursal daosql = new Sucursal(new DAOSQLFactory());
		List<SucursalDTO> sucursales = daosql.readAll();
		for(SucursalDTO suc: sucursales) {
			if(suc.getIdSucursal() == idSucursal) {
				direccionSucursal = suc.getCalle() + suc.getAltura();
			}
		}
		System.out.println("direccionsucursal: "+direccionSucursal);
		if(direccionSucursal.equals("") || direccionSucursal == "") {
			direccionSucursal = dataMissing;
		}
		parametersMap.put("Direccion",direccionSucursal);
		Zapateria zapa = new Zapateria(); 
		parametersMap.put("Categoria", zapa.getCategoriaAFIP());
		parametersMap.put("Cuit", zapa.getCUIT());
		parametersMap.put("Correo", zapa.getCorreoElectronico());
		
		try {
			/*
			 * File n = new File(""); String dir =
			 * n.getAbsolutePath()+"\\reportes\\Factura_A_-_Zapateria.jrxml"; this.reporte =
			 * JasperCompileManager.compileReport(dir);
			 */
			
			JasperReport reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reportes" + File.separator + "Factura_A_-_Zapateria.jasper");
			JasperPrint reporteLleno = JasperFillManager.fillReport(reporte, parametersMap,
					Conexion.getConexion().getSQLConexion());
			
			File folder = new File("Facturas");
			if (!folder.exists()) {
				folder.mkdir();
			}

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String fecha = dtf.format(LocalDateTime.now());
			DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH-mm-ss");
			String hora = tf.format(LocalDateTime.now());

			String fechaCompleta = "-" + fecha + "-" + hora;

			String urlFactura = "Facturas\\Factura_A_-_Zapateria" + fechaCompleta + ".pdf";
			
//			JasperViewer reporteViewer = new JasperViewer(reporteLleno, false);
//			reporteViewer.setVisible(true);
			
			JasperExportManager.exportReportToPdfFile(reporteLleno, urlFactura);

			EnviadorDeMails.enviarFacturaACliente(cliente, urlFactura);

		} catch (JRException ex) {
			System.err.println("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper"+ ex.getMessage());
		}
	}
	
	/*
	@SuppressWarnings({ "deprecation", "rawtypes" })
	private void descargarPDF(String nroFacturaCompleto) {
		String destino = "facturas"+ File.separator + "" + nroFacturaCompleto +".pdf";
		try{
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, this.reporteLleno);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destino);
			exporter.exportReport();
		}catch(Exception e){
			System.err.println( "Error iReport: " + e.getMessage() );
		}
	}*/

	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}

	public static void guardarFactura(JasperReport jasperReport,JasperPrint jasperPrint,ClienteDTO cliente) {
		File folder = new File("Facturas");
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			String fecha = dtf.format(LocalDateTime.now());
		    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH-mm-ss");
		    String hora = tf.format(LocalDateTime.now());
		    
		    String fechaCompleta = "-"+fecha+"-"+hora;
		    
		    String urlFactura ="Facturas\\Factura_A_-_Zapateria"+fechaCompleta+".pdf"; 
		    
			JasperExportManager.exportReportToPdfFile(jasperPrint, urlFactura);
			
			EnviadorDeMails.enviarFacturaACliente(cliente,urlFactura);
			
		} catch (JRException ex) {
			System.err.println("Error iReport: " + ex.getMessage());
		}
		  
		
	}

}