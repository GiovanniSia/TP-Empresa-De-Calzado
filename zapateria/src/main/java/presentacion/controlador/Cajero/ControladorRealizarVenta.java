package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CarritoDTO;
import dto.ClienteDTO;
import dto.DetalleCarritoDTO;
import dto.DetalleFacturaDTO;
import dto.EmpleadoDTO;
import dto.FacturaDTO;
import dto.IngresosDTO;
import dto.MaestroProductoDTO;
import dto.MedioPagoDTO;
import modelo.Carrito;
import modelo.Cliente;
import modelo.DetalleCarrito;
import modelo.DetalleFactura;
import modelo.Empleado;
import modelo.Factura;
import modelo.Ingresos;
import modelo.MaestroProducto;
import modelo.MedioPago;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Cajero.VentanaRealizarVenta;
import presentacion.vista.Cajero.VentanaVisualizarCarritos;

public class ControladorRealizarVenta {
	
	//hardcodeado
	private static final int idEmpleado=1;
	private static final int idSucursal=1;
	
	public double totalPagado=0;
	public double totalAPagar;
	public double totalAPagarAux;
	public double descuento=0;
	public double totalAPagarSinDescuento;
	
	boolean seAplicoDesc=false;
	
	
	
	double cantidadUsadaCC;
	
	VentanaRealizarVenta ventanaRealizarVenta;
	
	CarritoDTO carritoACobrar;
	List<DetalleCarritoDTO> detalleCarritoACobrar;
	ClienteDTO clienteCarrito;
	
	MedioPago medioPago;
	Cliente cliente;
	Empleado cajero;
	
	Carrito carrito;
	DetalleCarrito detalleCarrito;
	MaestroProducto maestroProducto;
	Factura factura;
	DetalleFactura detalleFactura;
	Ingresos ingresos;
	
	List<MedioPagoDTO> listamediosDePago;
	
	List<IngresosDTO> listaDeIngresosARegistrar;//representa tambien el pago que este en la tabla (por indice)
	
	ControladorVisualizarCarritos controladorVisualizarCarritos; 
//	VentanaVisualizarCarritos ventanaVisualizarCarritos;
	
	public ControladorRealizarVenta(ControladorVisualizarCarritos controladorVisualizarCarritos) {
		this.medioPago=new MedioPago(new DAOSQLFactory());;
		this.cliente = new Cliente(new DAOSQLFactory());
		this.cajero = new Empleado(new DAOSQLFactory());
		this.carrito = new Carrito(new DAOSQLFactory());
		this.detalleCarrito = new DetalleCarrito(new DAOSQLFactory());
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.factura = new Factura(new DAOSQLFactory());
		this.detalleFactura = new DetalleFactura(new DAOSQLFactory());
		
		this.ingresos = new Ingresos(new DAOSQLFactory());
		
		this.listamediosDePago = new ArrayList<MedioPagoDTO>();
		this.listaDeIngresosARegistrar = new ArrayList<IngresosDTO>();
//		this.controladorVisualizarCarritos = new ControladorVisualizarCarritos(carrito, detalleCarrito, cliente, maestroProducto);
		
		this.controladorVisualizarCarritos=controladorVisualizarCarritos;
//		this.ventanaVisualizarCarritos = new VentanaVisualizarCarritos();
		
//		BigDecimal b = new BigDecimal();
		
	}
	
	public void establecerCarritoACobrar(CarritoDTO carrito,List<DetalleCarritoDTO> detalles,ClienteDTO cliente) {
		this.carritoACobrar=carrito;
		this.detalleCarritoACobrar=detalles;
		this.clienteCarrito = cliente;
	}
	
	
	public void inicializar() {
		this.totalAPagarSinDescuento=this.carritoACobrar.getTotal();
		this.ventanaRealizarVenta = new VentanaRealizarVenta();
		
		this.ventanaRealizarVenta.getBtnAgregarMedioPago().addActionListener(a -> agregarMedioDePago(a));
		this.ventanaRealizarVenta.getBtnQuitarMedioPago().addActionListener(a -> quitarMedioPago(a));
		this.ventanaRealizarVenta.getBtnFinalizarVenta().addActionListener(a -> registrarPago(a));
		this.ventanaRealizarVenta.getBtnCancelarVenta().addActionListener(a -> cancelarPago(a));
		this.ventanaRealizarVenta.getTextDescuento().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				actualizarTablaMedioPago();
			}
		});
		
		
		this.listamediosDePago = this.medioPago.readAll();
		
		llenarCbMedioPago();
//		actualizarTablaMedioPago();
		this.totalAPagar=carritoACobrar.getTotal();
		BigDecimal tpgr = new BigDecimal(this.totalAPagar);
		this.ventanaRealizarVenta.getLblTotalAPagarValor().setText(""+tpgr);
		validarTeclado();
		this.ventanaRealizarVenta.show();
	}
	
	
	
	public void llenarCbMedioPago() {
		for(MedioPagoDTO m: this.listamediosDePago) {
			this.ventanaRealizarVenta.getComboBoxMetodoPago().addItem(m.getDescripcion());
		}
	}
	
	public void agregarMedioDePago(ActionEvent a) {
		if(!todosLosCamposSonValidos()) {
			JOptionPane.showMessageDialog(null, "El campo de monto no es correcto");
			return;
		}
		
		String nroOperacion = this.ventanaRealizarVenta.getTextNumOperacion().getText();
		
		if(ventanaRealizarVenta.getTextCantidad().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No ha agregado ningun valor");
			return;
		}
//		if(!todosLosCamposSonValidos()) {
//			return;
//		}
		String metodoPagoCb =(String) this.ventanaRealizarVenta.getComboBoxMetodoPago().getSelectedItem();
		
		if(metodoPagoCb.equals("Sin seleccionar")) {
			JOptionPane.showMessageDialog(null, "No ha agregado ningun medio de pago");
			return;
		}
		
		MedioPagoDTO medioPago = obtenerMedioPago(metodoPagoCb);
		
		double cantidad =(double) Double.parseDouble(ventanaRealizarVenta.getTextCantidad().getText());
		
		if(!esUnPagoConTarjeta(medioPago) && nroOperacion!=null) {
			JOptionPane.showMessageDialog(null, "No se puede agregar un nro de operacion");
			return;
		}
		
		
		if( ( cantidad *medioPago.getTasaConversion())> this.totalAPagar) {
			JOptionPane.showMessageDialog(null, "Esta cantidad supera el total a pagar!");
			return;
		}
		
		if(nroOperacion.equals("") && esUnPagoConTarjeta(medioPago)) {
			JOptionPane.showMessageDialog(null, "Debe agregar el número de operación para agregar el medio de pago!");
			return;
		}		

        
		//cuando se registra un pago se guarda un ingreso para registrar cuando se tenga la factura		
		
		if( medioPago.getDescripcion().equals("Cuenta Corriente")) {
			if(this.clienteCarrito.getEstado().equals("Moroso")) {
				JOptionPane.showMessageDialog(null, "El cliente es Moroso, no puede usar la cuenta corriente!");
				return;
			}
			if(!poseeSaldoSuficiente(this.clienteCarrito,cantidad)) {
				JOptionPane.showMessageDialog(null, "El cliente no posee saldo suficiente!");
				return;
			}	
		}
	
		//verificamos si el pago ya esta en la tabla 
		if(!esUnPagoConTarjeta(medioPago) && pagoYaFueRegistrado(medioPago)) {
			for(IngresosDTO i: this.listaDeIngresosARegistrar) {
				System.out.println("fue un pago sin tarjeta y ya fue registrado");
				if(i.getMedioPago().equals(medioPago.getIdMoneda())) {
					double cantNuevo = i.getCantidad() + cantidad;
					double totalNuevo = i.getTotal() + (cantidad*medioPago.getTasaConversion());
					i.setCantidad(cantNuevo);
					i.setTotal(totalNuevo);
					
					actualizarTablaMedioPago();
					System.out.println("Se actualizo la tabla");
					this.ventanaRealizarVenta.getTextCantidad().setText("");
					this.ventanaRealizarVenta.getTextNumOperacion().setText("");
					
					return;
				}
			}
		}
		
		
		int idCliente = this.carritoACobrar.getIdCliente();
			
		String tipoFactura = determinarCategoriaFactura(this.clienteCarrito);
		double valorConversion = medioPago.getTasaConversion();
		double totalArg = cantidad *valorConversion;
		String mp = medioPago.getIdMoneda();
				
		//EL NRO DE FACTURA SE DEBERA SETEAR AL MOMENTO DE REALIZAR EL COBRO, RECORRER TODOS LOS INGRESOS Y SETEARLE A CADA UNO EL NRO DE FACTURA GENERADO
		IngresosDTO ingreso = new IngresosDTO(0,this.idSucursal,"","","VT",idCliente,tipoFactura,"0",mp,cantidad,valorConversion,nroOperacion,totalArg);
		this.listaDeIngresosARegistrar.add(ingreso);
		//{"Método","Moneda","Nom. Tarjeta","Cantidad","Cant. (en AR$)"};
				
		actualizarTablaMedioPago();
		this.ventanaRealizarVenta.getTextCantidad().setText("");
		this.ventanaRealizarVenta.getTextNumOperacion().setText("");		
	}
	
	
	public MedioPagoDTO obtenerMedioPago(String nombre) {
		for(MedioPagoDTO m: this.listamediosDePago) {
			if(m.getDescripcion().equals(nombre)) {
				return m;
			}
		}
		return null;
	}
	
	
	public boolean esUnPagoConTarjeta(MedioPagoDTO medioPago) {
		return medioPago.getDescripcion().charAt(0)=='T' || medioPago.getDescripcion().equals("Mercado Pago");
	}
	
	public boolean pagoYaFueRegistrado(MedioPagoDTO medioPago) {
		for(IngresosDTO i: this.listaDeIngresosARegistrar) {
			if(i.getMedioPago().equals(medioPago.getIdMoneda())) {
				return true;
			}
		}return false;
	}
	
	
	public void actualizarTablaMedioPago() {
		this.ventanaRealizarVenta.getModelTablaMedioPago().setRowCount(0);//borrar datos de la tabla
		this.ventanaRealizarVenta.getModelTablaMedioPago().setColumnCount(0);
		this.ventanaRealizarVenta.getModelTablaMedioPago().setColumnIdentifiers(this.ventanaRealizarVenta.getNombreColumnasMedioPago());
		
		this.cantidadUsadaCC=0;
		this.totalPagado=0;
		this.totalAPagar= this.carritoACobrar.getTotal();
		this.totalAPagarAux = this.carritoACobrar.getTotal();
		
		for(IngresosDTO i: this.listaDeIngresosARegistrar) {
			for(MedioPagoDTO medioPago: this.listamediosDePago) {
				if(i.getMedioPago().equals(medioPago.getIdMoneda())) {
					String metodoPago=medioPago.getDescripcion();
					double valorConversion = medioPago.getTasaConversion();
					String moneda = valorConversion==1 ? "AR$"  : medioPago.getIdMoneda()+"$";
//					String nombreTarjeta = medioPago.getIdMoneda().charAt(0)=='T' ? medioPago.getDescripcion() : "-";
					String numOperacionAux = i.getOperacion();
					String numOperacion = numOperacionAux.equals("") ? "-" : numOperacionAux;
					
					double cantida = i.getCantidad();
					BigDecimal cantidad = new BigDecimal(cantida);
					double totalAr = cantida *valorConversion;
					BigDecimal totalArg = new BigDecimal(totalAr); 
					
					System.out.println("la cantidad que se escribe: "+cantidad);
					
					Object[] fila = {metodoPago,moneda,numOperacion,cantidad,totalArg};
					this.ventanaRealizarVenta.getModelTablaMedioPago().addRow(fila);
					actualizarTotalAPagar(i,medioPago);
				}
				
			}
		}
		descontarDescuento();
		
//		descontarDescuento();
		
		this.totalAPagar -=this.descuento;
		
		BigDecimal totalPgd = new BigDecimal(this.totalPagado);
		BigDecimal totalPgr = new BigDecimal(this.totalAPagar);
		
		this.ventanaRealizarVenta.getLblPrecioVentaValor().setText(""+totalPgd);
		this.ventanaRealizarVenta.getLblTotalAPagarValor().setText(""+totalPgr);
		
//		System.out.println("valor de total pagado: "+this.totalPagado+"\nCantidad de medios de pago por registrar: "+this.listaDeIngresosARegistrar.size());
	}

	
	public void actualizarTotalAPagar(IngresosDTO ingreso, MedioPagoDTO medioPago) {
		double pagadoArg = ingreso.getCantidad() * medioPago.getTasaConversion();
		
		if(medioPago.getIdMoneda().equals("CC")) {
			this.cantidadUsadaCC +=pagadoArg;
			System.out.println("cantidad de credito cc usado: "+this.cantidadUsadaCC);
		}
		
		
		
		
		this.totalPagado += pagadoArg;
		this.totalAPagar -= pagadoArg;
		this.totalAPagarAux = this.totalAPagar;
//		this.totalAPagar = this.totalAPagar-this.descuento;
	}
	
	public void descontarDescuento() {		
		if(!todosLosCamposSonValidos()) {
			return;
		}
		
		
		//si el campo es vacio se reincia a 0
		if(this.ventanaRealizarVenta.getTextDescuento().getText().equals("")) {
			this.totalAPagarAux = this.totalAPagar;
			this.descuento=0;
			
			BigDecimal desc = new BigDecimal(this.descuento);
			BigDecimal pgraux = new BigDecimal(this.totalAPagarAux); 
			
			this.ventanaRealizarVenta.getLblDescuentoDescontado().setText(""+desc);
			this.ventanaRealizarVenta.getLblTotalAPagarValor().setText(""+pgraux);
			return;
		}
		if(this.ventanaRealizarVenta.getTextDescuento().getText() != null) {			
			this.descuento = Double.parseDouble(this.ventanaRealizarVenta.getTextDescuento().getText());
			BigDecimal desc = new BigDecimal(this.descuento);
			
			
			this.ventanaRealizarVenta.getLblDescuentoDescontado().setText(""+desc);
			//si el desc escrito es mayor al total a pagar, se reinicia
			if(descuento>this.totalAPagar) {
				JOptionPane.showMessageDialog(null, "Esta cantidad de descuento supera el total a pagar!");
				
				this.totalAPagarAux = this.totalAPagar;
				this.descuento=0;
				
				BigDecimal desc1 = new BigDecimal(this.descuento);
				BigDecimal pgraux = new BigDecimal(this.totalAPagarAux); 
				
				this.ventanaRealizarVenta.getLblDescuentoDescontado().setText(""+desc1);
				this.ventanaRealizarVenta.getLblTotalAPagarValor().setText(""+pgraux);
				this.ventanaRealizarVenta.getTextDescuento().setText("");
				
				return;
			}
			
			this.totalAPagarAux = this.totalAPagar - this.descuento;
			BigDecimal pgraux = new BigDecimal(this.totalAPagarAux); 
			this.ventanaRealizarVenta.getLblTotalAPagarValor().setText(""+pgraux);
		}
	}

	
	public boolean poseeSaldoSuficiente(ClienteDTO cliente,double cantidad) {
		return cliente.getCreditoDisponible() >= cantidad;
	}
	
	public void registrarPago(ActionEvent a) {
		System.out.println("el total: "+this.totalPagado);
		if((this.totalPagado+this.descuento)>=this.carritoACobrar.getTotal()) {
			
//			ClienteDTO cliente = this.cliente.selectCliente(this.carritoACobrar.getIdCliente());
	
			generarFactura();
			borrarCarritoConDetalle();
			
			JOptionPane.showMessageDialog(null, "Pago efectuado con exito!");

			
			this.ventanaRealizarVenta.cerrar();
			this.controladorVisualizarCarritos.cerrarVentana();
//			ControladorVisualizarCarritos c = new ControladorVisualizarCarritos();
			this.controladorVisualizarCarritos.inicializar();
			this.controladorVisualizarCarritos.mostrarVentana();
			
			return;
			
			
		}else {
			JOptionPane.showMessageDialog(null, "Todavía no se han pagado todos los productos!");
			return;
		}
	}
	
	public void cancelarPago(ActionEvent a) {
		this.ventanaRealizarVenta.cerrar();
		this.controladorVisualizarCarritos.cerrarVentana();
//		ControladorVisualizarCarritos c = new ControladorVisualizarCarritos();
		this.controladorVisualizarCarritos.inicializar();
		this.controladorVisualizarCarritos.mostrarVentana();
		
	}
	public void quitarMedioPago(ActionEvent a) {
		int filaSeleccionada = this.ventanaRealizarVenta.getTableMedioPago().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun medio de pago");
			return;
		}
		IngresosDTO ingreso = this.listaDeIngresosARegistrar.get(filaSeleccionada);
		if(ingreso.getMedioPago().equals("CTE")) {
			this.cantidadUsadaCC=0;
		}
		
		this.listaDeIngresosARegistrar.remove(filaSeleccionada);
		actualizarTablaMedioPago();
//		System.out.println("cantidad de medios de pago a registrar: "+this.listaDeIngresosARegistrar.size());
	}
	
	
//	tipo factura Ri: Responsable inscripto A / M:Monotributista B / CF: Consumidor Final B / E: Exento E
//	NroFacturaCompleta: Primero va el tipo de la facturaSeguido del nro de la sucursal (4 - 5 digtos)Por ultimo 8 DIGITOS SECUENCIALES que conforma la id de la factura
	public void generarFactura() {
		
		int id=0;
		double montoPendiente = this.cantidadUsadaCC;
		
		ClienteDTO client = this.cliente.selectCliente(this.carritoACobrar.getIdCliente());
		
		int idCliente = client.getIdCliente();
		String nombreCliente =client.getApellido()+" "+ client.getNombre();
		
		int idCajero= this.idEmpleado;
		int idVendedor = this.carritoACobrar.getIdVendedor();
		EmpleadoDTO cajero = this.cajero.selectEmpleado(idCajero);
		String nombreCajero = cajero.getApellido()+" "+cajero.getNombre();
		EmpleadoDTO vendedor = this.cajero.selectEmpleado(idVendedor);
		
		String nombreVendedor = vendedor.getApellido()+" "+vendedor.getNombre();
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		String fecha = dtf.format(LocalDateTime.now());
		
		String tipoFactura = determinarCategoriaFactura(client);
		
		
		String nroFacturaCompleto = tipoFactura+ generarNroSucursal()+ generarNroFacturaSecuencial();
		int idSucursal = this.carritoACobrar.getIdSucursal();
		double descuento = this.descuento;
		
//		double totalBruto = this.carritoACobrar.getTotal(); 
//		double totalFactura = calcularTotal(client);//Habria que chequearlo mejor por el impuesto afip
		
		double totalBruto = calcularTotalBruto(client);
		double totalFactura = this.carritoACobrar.getTotal() - this.descuento;
		
		String tipoVenta = client.getTipoCliente();//mayorista/minorista
		
		String calle = client.getCalle();
		String altura = client.getAltura(); 
		String pais = client.getPais();
		String provincia = client.getProvincia();
		String localidad = client.getLocalidad();
		String codPostal = client.getCodPostal();
		String CUIL = client.getCUIL();
		String correo = client.getCorreo();
		
		
		String impuestoAFIP = obtenerNombreCategoria(client);
		double IVA = calcularIVA(client) ? ((21 * totalBruto)/100) : 0.0;
		FacturaDTO factura = new FacturaDTO(id,montoPendiente,idCliente,nombreCliente,idCajero,nombreCajero,idVendedor,nombreVendedor,fecha,tipoFactura,nroFacturaCompleto,idSucursal,descuento,totalBruto,totalFactura,tipoVenta,calle,altura,pais,provincia,localidad,codPostal,CUIL,correo,impuestoAFIP,IVA);
		
		//registramos la factura en la bd
		boolean insertFactura = this.factura.insert(factura);
		if(insertFactura==false) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
			return;
		}
		ArrayList<FacturaDTO> todasLasFacturas = (ArrayList<FacturaDTO>) this.factura.readAll();
		factura.setIdFactura(todasLasFacturas.get(todasLasFacturas.size()-1).getIdFactura());
		registrarDetallesFactura(factura);
		registrarIngreso(client,factura);
	}
	
	
	
	public void registrarDetallesFactura(FacturaDTO factura) {
		for(DetalleCarritoDTO detalleCarrito: this.detalleCarritoACobrar) {
			int id=0;
			int idProd = detalleCarrito.getIdProducto();
			int cant = detalleCarrito.getCantidad();
			MaestroProductoDTO producto = this.maestroProducto.selectMaestroProducto(idProd);
			String descr = producto.getDescripcion();
			double precioCosto = producto.getPrecioCosto();
			double precioVenta = detalleCarrito.getPrecio()/detalleCarrito.getCantidad();
			double monto = precioVenta * cant;
			int idFactura = factura.getIdFactura();
			String unidadMedida =""+producto.getUnidadMedida();//CHEQUEAR
			
			DetalleFacturaDTO detalleFactur = new DetalleFacturaDTO(id,idProd,cant,descr,precioCosto,precioVenta,monto,idFactura,unidadMedida);
			boolean insertDetalleFactura = this.detalleFactura.insert(detalleFactur);//se guarda en la bd
			if(!insertDetalleFactura) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error en uno de los ingresos de factura");
			}
		}
	}
		
	
	public void registrarIngreso(ClienteDTO cliente, FacturaDTO factura) {
	    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    String fecha = f.format(LocalDateTime.now());
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String hora = dtf.format(LocalDateTime.now());
	    
		for(IngresosDTO ingreso: this.listaDeIngresosARegistrar) {
			ingreso.establecerDatosFaltantes(factura.getNroFacturaCompleta(),fecha,hora);
			this.ingresos.insert(ingreso);
			
		}
	}
		
	public String generarNroSucursal() {
		String nroSucursal = ""+this.carritoACobrar.getIdSucursal();
		String nroSucFactura=""+nroSucursal;
		while(nroSucFactura.length() < 5) {
			nroSucFactura = "0" + nroSucFactura;
		}
//		System.out.println("NROSUC generado: "+nroSucFactura+" length: "+nroSucFactura.length());
		return nroSucFactura;
	}

	public String generarNroFacturaSecuencial() {
		ArrayList<FacturaDTO> todasLasFacturas = (ArrayList<FacturaDTO>) this.factura.readAll();
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

	public boolean calcularIVA(ClienteDTO cliente) {
		if(cliente.getImpuestoAFIP().equals("RI") || cliente.getImpuestoAFIP().equals("M")) {
			return true;
		}
		return false;
	}
	
	public double calcularTotalBruto(ClienteDTO cliente) {
		double total= this.carritoACobrar.getTotal();
//		System.out.println("se le debe calcular iva: "+calcularIVA(cliente));
		if(calcularIVA(cliente)) {
			total = total- ((21/100) * total);
		}
		return total;
	}
	
	public String obtenerNombreCategoria(ClienteDTO cliente) {
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
	
	public String determinarCategoriaFactura(ClienteDTO cliente) {
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
	
	public void borrarCarritoConDetalle() {
		for(DetalleCarritoDTO detalleCarrito: this.detalleCarritoACobrar) {
			if(this.carritoACobrar.getIdCarrito()==detalleCarrito.getIdCarrito()) {
				this.detalleCarrito.delete(detalleCarrito);
			}
		}
		this.carrito.delete(this.carritoACobrar);
	}
	
	
	

	public boolean todosLosCamposSonValidos() {
		String monto = this.ventanaRealizarVenta.getTextCantidad().getText();
		boolean m = monto.matches("^[0-9]+(\\.[0-9]{1,2})?$");

        if (!monto.equals("") && !m ) {
//        	JOptionPane.showMessageDialog(null, "El campo monto no es correcto");
            return false;
        }
        
        String desc = this.ventanaRealizarVenta.getTextDescuento().getText();
        boolean d = desc.matches("^[0-9]+(\\.[0-9]{1,2})?$");
        if(!desc.equals("") && !d) {
//        	JOptionPane.showMessageDialog(null, "El campo descuento no es correcto");
        	return false;
        }
        return true;
        
	}
	
	
	public void validarTeclado() {

		this.ventanaRealizarVenta.getTextNumOperacion().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		this.ventanaRealizarVenta.getTextCantidad().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		}));
		this.ventanaRealizarVenta.getTextDescuento().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		});
	}
	
	
	public static void main(String[] args) {
//		new ControladorRealizarVenta(new MedioPago(new DAOSQLFactory()));
	}
	
}
