package presentacion.reportes;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import dto.FacturaDTO;
import dto.SucursalDTO;
import modelo.Factura;
import modelo.Sucursal;
import modelo.Zapateria;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;

public class ReporteNotaCredito {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	private Logger log = Logger.getLogger(ReporteProducto.class);
	
	private static final String dataMissing = "-";//"[Hiperlink error]"

	public ReporteNotaCredito(String nroFacturaCompleto) {
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
					.loadObjectFromFile("reportes"+File.separator+"NotaCredito"+File.separator+"notaCredito.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,
					Conexion.getConexion().getSQLConexion());
			log.info("Se cargó correctamente el reporte");
		} catch (JRException ex) {
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
	}

	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}

}