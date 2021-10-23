package modelo.compraVirtual;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import datos.JsonListaCompraVirtual;
import dto.ClienteDTO;
import dto.CompraVirtualDTO;
import dto.DetalleFacturaDTO;
import dto.FacturaDTO;
import dto.IngresosDTO;
import dto.MaestroProductoDTO;
import dto.RechazoCompraVirtualDetalleDTO;
import dto.StockDTO;
import dto.SucursalDTO;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.Ingresos;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import modelo.generarOrdenesFabricacion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.reportes.ReporteFactura;

public class ProcesarCompraVirtual {
	
	private static final int idVendedorVirtual = 0;
	private static final int idCajeroVirtual = 0;
	private static String nombreCajeroVirtual = "compraVirtual";
	private static String nombreVendedorVirtual = "compraVirtual";
	
	private static String nombreParaProductoQueNoEstaEnLaBaseDeDatos = "[Hiperlink error]";
	
	private static int porcentajeTolerancia = 1;
	
	public static void RutinaProcesarCompra(int minutos) {
		long tiempo = minutos*60*1000;
		Timer timer = new Timer();
		TimerTask tarea = new TimerTask() {
			@Override
			public void run() {
				FuncionProcesarCompra();
			}
		};
		timer.scheduleAtFixedRate(tarea, 0, tiempo);
		
	}
	
	public static void FuncionProcesarCompra() {
		ArrayList<CompraVirtualDTO> listaCompraProcesar = JsonListaCompraVirtual.getLista();
		if(listaCompraProcesar == null) {
			return;
		}
		if(listaCompraProcesar.size()==0) {
			return;
		}
		String mensajeReporte = "";
		for(CompraVirtualDTO compraVirtual: listaCompraProcesar) {
			mensajeReporte = reporteDatosErroneos(compraVirtual);
			if(reporteDeDatosNoValidoParaComprar(mensajeReporte)) {
				//No se compra
				//System.out.println(mensajeReporte);
				registrarRechazo(compraVirtual,mensajeReporte);
			}else {
				//Se compra
				registrarCompraVirtual(compraVirtual);	//GENERA Y MUESTRA LAS FACTURAS
			}
		}
	}

	private static void registrarRechazo(CompraVirtualDTO compraVirtual,String motivoRechazo) {
		RechazoCompraVirtual modeloRechazo = new RechazoCompraVirtual(new DAOSQLFactory());
		/*
		RechazoCompraVirtualDTO rechazo = new RechazoCompraVirtualDTO(0, "", "", compraVirtual.getIdSucursal(),compraVirtual.getPago(),
				compraVirtual.getNombre(), compraVirtual.getApellido(), compraVirtual.getCUIL(), compraVirtual.getCorreoElectronico(), 
				compraVirtual.getTipoCliente(), compraVirtual.getImpuestoAFIP(), compraVirtual.getEstado(), compraVirtual.getCalle(),
				compraVirtual.getAltura(), compraVirtual.getPais(), compraVirtual.getProvincia(), compraVirtual.getLocalidad(), 
				compraVirtual.getCodPostal(), motivoRechazo);
				*/
		modeloRechazo.insertRechazoCompraVirtualDAOSQL(compraVirtual, motivoRechazo);
		int idRechazo = modeloRechazo.readAllRechazosComprasVirtuales().size();
		for(int idProducto: compraVirtual.getCompra().keySet()) {
			MaestroProductoDTO producto = getProducto(idProducto);
			String nombreProducto = nombreParaProductoQueNoEstaEnLaBaseDeDatos;
			double precioMayorista = 0;
			double precioMinorista = 0;
			double precioCosto = 0;
			if(producto != null) {
				nombreProducto = producto.getDescripcion()+producto.getTalle();
				precioMayorista = producto.getPrecioMayorista();
				precioMinorista = producto.getPrecioMinorista();
				precioCosto = producto.getPrecioCosto();
			}
			RechazoCompraVirtualDetalleDTO detalleInsertar = new RechazoCompraVirtualDetalleDTO(0, idRechazo, idProducto, 
					nombreProducto, precioMayorista, precioMinorista, precioCosto, compraVirtual.getCompra().get(idProducto));
			modeloRechazo.insertDetalleRechazoCompraVirtual(detalleInsertar);
		}
		
	}

	public static String reporteDatosErroneos(CompraVirtualDTO compraVirtual) {
		String ret = "";
		if(estaRegistradoElCliente(compraVirtual.getCUIL())) {
			if(!esPagoValido(compraVirtual.getPago())) {
				ret = ret + ";El pago no es valido";
			} else
			if(!esPagoSuficiente(compraVirtual)) {
				ret = ret + ";El pago no es suficiente, pago de la compra: "+compraVirtual.getPago()+", total a pagar:"+calcularTotalAPagar(compraVirtual)+", la tolerancia es de"+porcentajeTolerancia+"%";
			}
			if(!esSucursalValida(compraVirtual.getIdSucursal())) {
				ret = ret + ";La sucursal no es valida";
			}
			for(int idProducto: compraVirtual.getCompra().keySet()) {
				if(!sePuedeVenderElProducto(idProducto, compraVirtual.getIdSucursal(), compraVirtual.getCompra().get(idProducto))) {
					ret = ret + ";No es posible vender uno de los productos";
				}
			}
			return ret;
		}
		if(!esPagoValido(compraVirtual.getPago())) {
			ret = ret + ";El pago no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getNombre())) {
			ret = ret + ";El nombre no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getCUIL())) {
			ret = ret + ";El CUIL no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getApellido())) {
			ret = ret + ";El apellido no es valido";
		}
		if(!esMailValido(compraVirtual.getCorreoElectronico())) {
			ret = ret + ";El CorreoElectronico no es valido";
		}
		/*
		if(!esTipoClienteValido(compraVirtual.getTipoCliente())) {
			ret = ret + ";El TipoCliente no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getTipoCliente())) {
			ret = ret + ";El TipoCliente no es valido";
		}
		*/
		if(!esAfipValido(compraVirtual.getImpuestoAFIP())) {
			ret = ret + ";El tipo de afip no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getCalle())) {
			ret = ret + ";La calle no es valida";
		}
		if(!esDatoStringValido(compraVirtual.getAltura())) {
			ret = ret + ";La altura no es valida";
		}
		if(!esDatoStringValido(compraVirtual.getPais())) {
			ret = ret + ";El pais no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getProvincia())) {
			ret = ret + ";La provincia no es valida";
		}
		if(!esDatoStringValido(compraVirtual.getLocalidad())) {
			ret = ret + ";La localidad no es valida";
		}
		if(!esDatoStringValido(compraVirtual.getCodPostal())) {
			ret = ret + ";El codigo postal no es valido";
		}
		/* por si lo quiero booleando
		boolean ret = true;
		ret = ret && esPagoValido(compraVirtual.getPago());
		ret = ret && esDatoStringValido(compraVirtual.getNombre());
		ret = ret && esDatoStringValido(compraVirtual.getApellido());
		ret = ret && esMailValido(compraVirtual.getCorreoElectronico());
		ret = ret && esTipoClienteValido(compraVirtual.getTipoCliente());
		ret = ret && esAfipValido(compraVirtual.getImpuestoAFIP());
		
		ret = ret && esDatoStringValido(compraVirtual.getCalle());
		ret = ret && esDatoStringValido(compraVirtual.getAltura());
		ret = ret && esDatoStringValido(compraVirtual.getPais());
		ret = ret && esDatoStringValido(compraVirtual.getProvincia());
		ret = ret && esDatoStringValido(compraVirtual.getLocalidad());
		ret = ret && esDatoStringValido(compraVirtual.getCodPostal());
		*/
		if(!esSucursalValida(compraVirtual.getIdSucursal())) {
			ret = ret + ";La sucursal no es valida";
		}else {
			for(int idProducto: compraVirtual.getCompra().keySet()) {
				if(!sePuedeVenderElProducto(idProducto, compraVirtual.getIdSucursal(), compraVirtual.getCompra().get(idProducto))) {
					ret = ret + ";No es posible vender uno de los productos";
				}
			}
		}
		
		if(ret.equals("")) {
			//darDeAltaCliente();
			Cliente modeloCliente = new Cliente(new DAOSQLFactory());
			modeloCliente.insert(new ClienteDTO(0, compraVirtual.getNombre(), compraVirtual.getApellido(),compraVirtual.getCUIL(), compraVirtual.getCorreoElectronico(), 0,
			0,
			//compraVirtual.getTipoCliente(),
			"Minorista",
			compraVirtual.getImpuestoAFIP(), "Activo", compraVirtual.getCalle(), compraVirtual.getAltura(),
			compraVirtual.getPais(), compraVirtual.getProvincia(), compraVirtual.getLocalidad(), compraVirtual.getCodPostal()));
		
			if(!esPagoSuficiente(compraVirtual)) {
				ret = ret + ";El pago no es suficiente, pago de la compra: "+compraVirtual.getPago()+", total a pagar:"+calcularTotalAPagar(compraVirtual)+", la tolerancia es de"+porcentajeTolerancia+"%";
			}
		}
		return ret;
	}
	
	private static boolean reporteDeDatosNoValidoParaComprar(String reporte) {
		return !reporte.equals("");
	}
	
	private static boolean esPagoSuficiente(CompraVirtualDTO compraVirtual) {
		double cantidadAPagar = calcularTotalAPagar(compraVirtual);
		int porcentaje = porcentajeTolerancia;
		double mayor = cantidadAPagar + (cantidadAPagar*porcentaje/100);
		double menor = cantidadAPagar - (cantidadAPagar*porcentaje/100);
		return menor < compraVirtual.getPago() && mayor > compraVirtual.getPago();
	}
	
	private static double calcularTotalAPagar(CompraVirtualDTO compraVirtual) {
		double cantidadAPagar = 0.0;
		ClienteDTO cliente = getCliente(compraVirtual.getCUIL());
		for(int idProducto: compraVirtual.getCompra().keySet()) {
			MaestroProductoDTO producto = getProducto(idProducto);
			if(cliente.getTipoCliente().equals("Mayorista")) {
				cantidadAPagar = cantidadAPagar+(producto.getPrecioMayorista()*compraVirtual.getCompra().get(idProducto));
			}else {
				cantidadAPagar = cantidadAPagar+(producto.getPrecioMinorista()*compraVirtual.getCompra().get(idProducto));
			}
		}
		return cantidadAPagar;
	}
	
	private static boolean esAfipValido(String afip) {
		boolean ret = true;
		if(afip == null) {
			return false;
		}
		ret = ret && (afip.equals("RI") || afip.equals("E") || afip.equals("CF"));
		return ret;
	}
	/*
	private static boolean esTipoClienteValido(String tipoCliente) {
		boolean ret = true;
		if(tipoCliente == null) {
			return false;
		}
		ret = ret && (tipoCliente.equals("Mayorista") || tipoCliente.equals("Minorista"));
		return ret;
	}*/
	
	private static boolean esDatoStringValido(String dato) {
		boolean ret = true;
		if(dato == null)
			return false;
		ret = ret && !dato.equals("");
		return ret;
	}
	
	private static boolean esPagoValido(double pago) {
		return !(pago <= 0);
	}
	
	private static boolean estaRegistradoElCliente(String cuil) {
		boolean ret = false;
		if(cuil == null)
			return false;
		if(cuil.equals(""))
			return false;
		Cliente modeloCliente = new Cliente(new DAOSQLFactory());
		for(ClienteDTO c: modeloCliente.readAll()) {
			ret = ret || c.getCUIL().equals(cuil);
		}
		return ret;
	}
	
	private static ClienteDTO getCliente(String CUIL) {
		ClienteDTO ret = null;
		Cliente modeloCliente = new Cliente(new DAOSQLFactory());
		for(ClienteDTO c: modeloCliente.readAll()) {
			if(c.getCUIL().equals(CUIL)) {
				ret = c;
			}
		}
		return ret;
	}
	
	private static boolean esMailValido(String email) {
		if(email == null) {
			return false;
		}
		Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            return true;
        }
        return false;
	}
	
	private static boolean esSucursalValida(int idSucursal) {
		boolean ret = false;
		Sucursal modeloSucursal = new Sucursal(new DAOSQLFactory());
		for(SucursalDTO s: modeloSucursal.readAll()) {
			ret = ret || s.getIdSucursal() == idSucursal;
		}
		return ret;
	}
	
	private static boolean sePuedeVenderElProducto(int idProducto, int idSucursal, int cantidadAComprar) {
		MaestroProductoDTO producto = getProducto(idProducto);
		if(producto == null) {
			return false;
		}
		if(!producto.getEstado().equals("Activo")) {
			return false;
		}
		int cantStock = generarOrdenesFabricacion.contarStockDeUnProductoEnUnaSucursal(idSucursal,idProducto);
		if(cantStock >= cantidadAComprar) {
			return true;
		}
		return false;
	}
	
	private static MaestroProductoDTO getProducto(int idProducto) {
		MaestroProducto modeloProducto = new MaestroProducto(new DAOSQLFactory());
		MaestroProductoDTO ret = null;
		for(MaestroProductoDTO mp: modeloProducto.readAll()) {
			if(mp.getIdMaestroProducto() == idProducto) {
				ret = mp;
			}
		}
		return ret;
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// FUNCION IMPORTANTE
	
	private static void registrarCompraVirtual(CompraVirtualDTO compraVirtual) {
		String nroFactura = generarFacturaCompraVirtual(compraVirtual);
		registrarIngreso(compraVirtual, nroFactura);
		ReporteFactura reporte = new ReporteFactura(nroFactura);
		reporte.mostrar();
	}

	private static void registrarIngreso(CompraVirtualDTO compraVirtual, String nroFactura) {
		ClienteDTO cliente = getCliente(compraVirtual.getCUIL());
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    String fecha = f.format(LocalDateTime.now());
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String hora = dtf.format(LocalDateTime.now());
	    
	    Ingresos ingresos = new Ingresos(new DAOSQLFactory());
	    
	    String categoriaAFIP = determinarCategoriaFactura(cliente);
	    IngresosDTO datoIngreso = new IngresosDTO(0, compraVirtual.getIdSucursal(), fecha, hora, "VT", 
	    		cliente.getIdCliente(), categoriaAFIP, nroFactura, "PV", compraVirtual.getPago(), 
	    		1.0, "OPERACION", compraVirtual.getPago());
	    
	    ingresos.insert(datoIngreso); 		
	}
	
	private static String obtenerNombreCategoria(ClienteDTO cliente) {
		String tipo = cliente.getImpuestoAFIP();
		if(tipo.equals("RI")) {
			return "Responsable Inscripto";
		}
		if(tipo.equals("M")) {
			return "Monotributista";
		}
		if(tipo.equals("CF")) {
			return "Consumidor Final";
		}
		if(tipo.equals("E")) {
			return "Excento";
		}
		return "Categoria no encontrada en el sistema";
	}
	
	private static String determinarCategoriaFactura(ClienteDTO cliente) {
		if(cliente.getImpuestoAFIP().equals("RI") || cliente.getImpuestoAFIP().equals("M")) {
			return "A";
		}
		if(cliente.getImpuestoAFIP().equals("E")) {
			return "B";
		}
		if(cliente.getPais() != "Argentina") {//si la persona no vive en arg es excento
			return "E";
		}
		return "";
	}

	private static String generarFacturaCompraVirtual(CompraVirtualDTO compraVirtual) {
		ClienteDTO client = getCliente(compraVirtual.getCUIL());
		
		int idCliente = client.getIdCliente();
		String nombreCliente =client.getApellido()+" "+ client.getNombre();
		//private static final int idVendedorVirtual = 0;
		//private static final int idCajeroVirtual = 0;
		int idCajero= idCajeroVirtual;
		int idVendedor = idVendedorVirtual;
		String nombreCajero = nombreCajeroVirtual;
		String nombreVendedor = nombreVendedorVirtual;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		String fecha = dtf.format(LocalDateTime.now());
		
		String tipoFactura = determinarCategoriaFactura(client);
		
		int idSucursal = compraVirtual.getIdSucursal();
		String nroFacturaCompleto = tipoFactura+generarNroSucursal(idSucursal)+generarNroFacturaSecuencial();
		double descuento = 0;
		
		double totalBruto = calcularTotalBruto(client, compraVirtual.getPago());
		double totalFactura = compraVirtual.getPago();
		
		String tipoVenta = client.getTipoCliente();//mayorista/minorista
		
		String calle = client.getCalle();
		String altura = client.getAltura(); 
		String pais = client.getPais();
		String provincia = client.getProvincia();
		String localidad = client.getLocalidad();
		String codPostal = client.getCodPostal();
		
		boolean deboUsarDatosDeLaCompra = true;
		deboUsarDatosDeLaCompra = deboUsarDatosDeLaCompra && esDatoStringValido(compraVirtual.getCalle());
		deboUsarDatosDeLaCompra = deboUsarDatosDeLaCompra && esDatoStringValido(compraVirtual.getAltura());
		deboUsarDatosDeLaCompra = deboUsarDatosDeLaCompra && esDatoStringValido(compraVirtual.getPais());
		deboUsarDatosDeLaCompra = deboUsarDatosDeLaCompra && esDatoStringValido(compraVirtual.getProvincia());
		deboUsarDatosDeLaCompra = deboUsarDatosDeLaCompra && esDatoStringValido(compraVirtual.getLocalidad());
		deboUsarDatosDeLaCompra = deboUsarDatosDeLaCompra && esDatoStringValido(compraVirtual.getCodPostal());
		if(deboUsarDatosDeLaCompra) {
			calle = compraVirtual.getCalle();
			altura = compraVirtual.getAltura(); 
			pais = compraVirtual.getPais();
			provincia = compraVirtual.getProvincia();
			localidad = compraVirtual.getLocalidad();
			codPostal = compraVirtual.getCodPostal();
		}
		
		String CUIL = client.getCUIL();
		String correo = client.getCorreo();
		
		String impuestoAFIP = obtenerNombreCategoria(client);
		double IVA = deboCalcularIVA(client) ? ((21 * totalBruto)/100) : 0.0;
		FacturaDTO facturaGenerada = new FacturaDTO(0,0,idCliente,nombreCliente,idCajero,nombreCajero,idVendedor,nombreVendedor,fecha,tipoFactura,nroFacturaCompleto,idSucursal,descuento,totalBruto,totalFactura,tipoVenta,calle,altura,pais,provincia,localidad,codPostal,CUIL,correo,impuestoAFIP,IVA);
		
		//registramos la factura en la bd
		Factura modeloFactura = new Factura(new DAOSQLFactory());
		boolean insertFactura = modeloFactura.insert(facturaGenerada);
		if(insertFactura==false) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
			return null;
		}
		ArrayList<FacturaDTO> todasLasFacturas = (ArrayList<FacturaDTO>) modeloFactura.readAll();
		facturaGenerada.setIdFactura(todasLasFacturas.get(todasLasFacturas.size()-1).getIdFactura());
		registrarDetallesFactura(facturaGenerada, compraVirtual);
		return nroFacturaCompleto;
	}
	
	private static void registrarDetallesFactura(FacturaDTO factura, CompraVirtualDTO compraVirtual) {
		for(int idProducto: compraVirtual.getCompra().keySet()) {
			int id=0;
			int idProd = idProducto;
			int cant = compraVirtual.getCompra().get(idProducto);
			
			MaestroProductoDTO producto = getProducto(idProd);
			String descr = producto.getDescripcion();
			double precioCosto = producto.getPrecioCosto();
			
			double precioVenta;
			ClienteDTO cliente = getCliente(compraVirtual.getCUIL());
			if(cliente.getTipoCliente().equals("Mayorista")) {
				precioVenta = producto.getPrecioMayorista();
			}else {
				precioVenta = producto.getPrecioMinorista();
			}
					
			double monto = precioVenta * cant;
			int idFactura = factura.getIdFactura();
			String unidadMedida =""+producto.getUnidadMedida();//CHEQUEAR
			
			DetalleFacturaDTO detalleFactur = new DetalleFacturaDTO(id,idProd,cant,descr,precioCosto,precioVenta,monto,idFactura,unidadMedida);
			
			DetalleFactura detalleFactura = new DetalleFactura(new DAOSQLFactory());
			boolean insertDetalleFactura = detalleFactura.insert(detalleFactur);//se guarda en la bd
			if(!insertDetalleFactura) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error en uno de los ingresos de factura");
			}
			descontarStock(compraVirtual.getIdSucursal(), idProducto, compraVirtual.getCompra().get(idProducto));
		}
	}
	
	private static String generarNroSucursal(int idSucursal) {
		String nroSucursal = ""+idSucursal;
		String nroSucFactura=""+nroSucursal;
		while(nroSucFactura.length() < 5) {
			nroSucFactura = "0" + nroSucFactura;
		}
		return nroSucFactura;
	}

	private static String generarNroFacturaSecuencial() {
		Factura factura = new Factura(new DAOSQLFactory());
		ArrayList<FacturaDTO> todasLasFacturas = (ArrayList<FacturaDTO>) factura.readAll();
		if(todasLasFacturas.size()==0) {
			return "1";
		}
		FacturaDTO ultFactura = todasLasFacturas.get(todasLasFacturas.size()-1);
		String nroCompletoUlt = ultFactura.getNroFacturaCompleta();
		
		String ultSec="";
		//damos por hecho que 1 dig sera para el tipo de factura, y 5 para el nro de sucursal
		for(int i=6; i<nroCompletoUlt.length() ; i++) {
			ultSec = ultSec+ nroCompletoUlt.charAt(i);//obtenemos los ult 8 dig secuenciales
		}
		int viejoSuma = Integer.parseInt(ultSec);
		int nuevoSec = (viejoSuma+1);
		return ""+nuevoSec;
	}
	
	private static boolean deboCalcularIVA(ClienteDTO cliente) {
		if(cliente.getImpuestoAFIP().equals("RI") || cliente.getImpuestoAFIP().equals("M")) {
			return true;
		}
		return false;
	}
	
	private static double calcularTotalBruto(ClienteDTO cliente, double total) {
		double ret = total;
		if(deboCalcularIVA(cliente)) {
			ret = total- ((21/100) * total);
		}
		return ret;
	}
	
	private static void descontarStock(int idSucursalADescontar, int idProducto, int cantidadADescontar) {
		Stock modeloStock = new Stock(new DAOSQLFactory());
		int restar = cantidadADescontar;
		for(StockDTO s: modeloStock.readAll()) {
			if(s.getIdProducto()==idProducto && s.getIdSucursal()==idSucursalADescontar) {
				int aux = s.getStockDisponible() - restar;
				if(aux < 0) {
					s.setStockDisponible(0);
					restar = -aux;
				}else {
					s.setStockDisponible(aux);
					restar = 0;
				}
				modeloStock.actualizarStock(s.getIdStock(), s.getStockDisponible());
			}
		}
	}

	public static void main(String[] args) {
		
		ArrayList<CompraVirtualDTO> compras = new ArrayList<CompraVirtualDTO>();
		HashMap<Integer,Integer> detalle = new HashMap<Integer,Integer>();
		detalle.put(1, 5);
		//CompraVirtualDTO cvd = new CompraVirtualDTO(cliente,detalle,1,500);
		CompraVirtualDTO cvd = new CompraVirtualDTO(detalle, 1, 35000, 2, "Juan", "Lopez","4223004","juan@mgail.com",
				//"Mayorista",
				"E","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
		compras.add(cvd);
		
		detalle = new HashMap<Integer,Integer>();
		detalle.put(1, 1);
		detalle.put(2, 3);
		CompraVirtualDTO cvd2 = new CompraVirtualDTO(detalle, 1, 14020, 1, "Sebas",
				"Cubilla", "1231312319987654", "sebastianx3600@gmail.com",
				//"Minorista", 
				"RI", "Calle falsa", "5421", "Argentina",
				"Buenos Aires", "Tortuguitas", "1667");
		compras.add(cvd2);
		
		detalle = new HashMap<Integer,Integer>();
		detalle.put(1, 1);
		detalle.put(2, 3);
		CompraVirtualDTO cvd22 = new CompraVirtualDTO(detalle, 1, 14020, 1, "Sebas",
				"Cubilla", "1231312319987654", "sebastianx3600@gmail.com",
				//"Minorista", 
				"RI", "Calle falsaasdasdas", "5421", "Argentina",
				"Buenos Aires", "Tortuguitas", "1667");
		compras.add(cvd22);
		
		
		detalle = new HashMap<Integer,Integer>();
		detalle.put(1, 1);
		detalle.put(2, 3);
		detalle.put(19, 10);
		CompraVirtualDTO cvd3 = new CompraVirtualDTO(detalle, 1, 500, 1, "Pedro",
				"asd", "", "sebas@gmail.com", 
				//"Minorista", 
				"RI", "", "", "Argentina",
				"Buenos Aires", "Tortuguitas", "1667");
		compras.add(cvd3);
		JsonListaCompraVirtual.guardarLista(compras);
		
		CompraVirtualDTO cvd4 = new CompraVirtualDTO(detalle, 1, 500, 1, null,
				"asd", "", null, 
				"RI", "", "", "Argentina",
				"Buenos Aires", "Tortuguitas", "1667");
		compras.add(cvd4);
		JsonListaCompraVirtual.guardarLista(compras);
		ProcesarCompraVirtual.RutinaProcesarCompra(1);
		
	}

}
