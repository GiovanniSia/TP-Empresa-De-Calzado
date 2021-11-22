package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import dto.EgresosDTO;
import dto.EmpleadoDTO;
import dto.FacturaDTO;
import dto.IngresosDTO;
import dto.MedioPagoEgresoDTO;
import dto.MotivoEgresoDTO;
import dto.PedidosPendientesDTO;
import dto.ProveedorDTO;
import dto.TipoEgresosDTO;
import inicioSesion.sucursalProperties;
import modelo.Egresos;
import modelo.Empleado;
import modelo.Factura;
import modelo.Ingresos;
import modelo.MaestroProducto;
import modelo.MedioPagoEgreso;
import modelo.PedidosPendientes;
import modelo.Proveedor;
import modelo.Stock;
import modelo.TipoEgresos;
import modelo.compraVirtual.MotivoEgreso;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.supervisor.ControladorVerPedidosAProveedor;
import presentacion.reportes.ReporteNotaCredito;
import presentacion.vista.Cajero.VentanaEgresoCaja;

public class ControladorEgresosCaja {
	private VentanaEgresoCaja ventanaEgresoCaja;
	private Egresos egresos;

	PedidosPendientes pedidosPendientes;
	List<PedidosPendientesDTO> listaPedidosPendientes;

	private List<TipoEgresosDTO> listaTipoEgresos;
	private TipoEgresos tipoEgresos;

	
	ControladorVerPedidosAProveedor controladorVerPedidosAProveedor;
	
	private int idSucursal = 0;

	public void obtenerDatosPropertiesSucursalEmpleado() {
		try {
			sucursalProperties sucursalProp = sucursalProperties.getInstance();
			idSucursal = Integer.parseInt(sucursalProp.getValue("IdSucursal"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<MedioPagoEgresoDTO> listaMedioPagoEgreso;
	private MedioPagoEgreso medioPagoEgreso;

	Controlador controlador;

	private Ingresos ingresos;
	private List<IngresosDTO> ingresosEnTabla;

	private List<EgresosDTO> egresosEnTabla;

	Stock stock;
	MaestroProducto maestroProducto;
	
	
	public ControladorEgresosCaja() {
		obtenerDatosPropertiesSucursalEmpleado();
		this.ventanaEgresoCaja = new VentanaEgresoCaja();
		this.egresos = new Egresos(new DAOSQLFactory());
		this.ingresos = new Ingresos(new DAOSQLFactory());
	}

	public ControladorEgresosCaja(Controlador controlador, Egresos egresos, PedidosPendientes pedidosPendientes) {
		obtenerDatosPropertiesSucursalEmpleado();
		this.ventanaEgresoCaja = new VentanaEgresoCaja();
		this.egresos = egresos;
		this.pedidosPendientes = pedidosPendientes;
		this.controlador = controlador;
		this.listaPedidosPendientes = new ArrayList<PedidosPendientesDTO>();

	}

	public void inicializar() {
		this.listaPedidosPendientes = this.pedidosPendientes.readAll();
		this.ventanaEgresoCaja = new VentanaEgresoCaja();

		this.egresos = new Egresos(new DAOSQLFactory());
		this.ingresos = new Ingresos(new DAOSQLFactory());

		// ComboBox TipoEgresos
		this.tipoEgresos = new TipoEgresos(new DAOSQLFactory());
		this.listaTipoEgresos = this.tipoEgresos.readAll();
		llenarCBTipoEgreso();

		// ComboBox MedioPagos
		this.medioPagoEgreso = new MedioPagoEgreso(new DAOSQLFactory());
		this.listaMedioPagoEgreso = this.medioPagoEgreso.readAll();

		// Botones
		this.ventanaEgresoCaja.getBtnAtras().addActionListener(a -> atras(a));
		this.ventanaEgresoCaja.getBtnConfirmarEgreso().addActionListener(c -> confirmarEgreso());

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		this.ventanaEgresoCaja.getLblActualizarFechaFA().setText(fecha);

		actualizarLblBalance();

		// Cambia el estado de combobox
		this.ventanaEgresoCaja.getCbTipoEgreso().addActionListener(a -> tipoEgresoSeleccionado());
		this.ventanaEgresoCaja.getBtnVerPedidos().addActionListener(a -> pasarAVerPedidosAProveedor());
	}

	public void actualizarLblBalance() {
		this.ventanaEgresoCaja.getLblActualizarTotalBalanceCaja()
				.setText("$" + new BigDecimal("" + obtenerValorBalance() + "").setScale(2, RoundingMode.HALF_UP));
	}

	public double obtenerValorBalance() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());

		double totalIngresos = 0.0;
		double totalEgresos = 0.0;
		if (ingresos.getIngresosAproximado("Fecha", fecha, null, null).size() != 0) {
			totalIngresos = ObtenerTotalIngreso();
		}

		if (egresos.getEgresosAproximado("Fecha", fecha, null, null).size() != 0) {
			totalEgresos = obtenerTotalEgreso();
		}

		double balanceCaja = totalIngresos - totalEgresos;
		return balanceCaja;
	}

	private List<IngresosDTO> obtenerIngresosDeHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		return ingresos.getIngresosAproximado("Fecha", fecha, null, null);
	}

	public double ObtenerTotalIngreso() {
		this.ingresosEnTabla = obtenerIngresosDeHoy();
		double total = 0;
		for (IngresosDTO i : ingresosEnTabla) {
			total += i.getTotal();
		}
		return total;
	}

	public double obtenerTotalEgreso() {
		this.egresosEnTabla = obtenerEgresosDeHoy();
		double total = 0;
		for (EgresosDTO e : egresosEnTabla) {
			total += e.getTotal();
		}
		return total;
	}

	public List<EgresosDTO> obtenerEgresosDeHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		return egresos.getEgresosAproximado("Fecha", fecha, null, null);
	}

	public void confirmarEgreso() {

		if (validarCampos()) {

			String tipoEgresoSeleccionado = this.ventanaEgresoCaja.getTipoEgresoSeleccionado();
			if (tipoEgresoSeleccionado.equals("Adelanto de sueldo")) {	
				String as = this.ventanaEgresoCaja.getTxtFieldAS().getText();
				ingresarEgreso(as);
			}
			if (tipoEgresoSeleccionado.equals("Faltante")) {
				String fa = this.ventanaEgresoCaja.getLblActualizarFechaFA().getText();
				ingresarEgreso(fa);
			}
			if (tipoEgresoSeleccionado.equals("Nota Credito")) {
				/*
				 * String nc = this.ventanaEgresoCaja.getTxtFieldNC().getText();
				 * ingresarEgreso(nc);
				 */
				// * * * * * * * * * * * * * * *
				String nc = this.ventanaEgresoCaja.getTxtFieldNC().getText();
				if (nroFacturaExiste(nc)) {
					if (yaExisteNotaCredito(nc)) {
						JOptionPane.showMessageDialog(null, "Ya existe una nota de credito asociada a esta factura",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					ingresarEgreso(nc);
					MotivoEgreso modeloMotivo = new MotivoEgreso(new DAOSQLFactory());
					modeloMotivo.insert(new MotivoEgresoDTO(nc, "Este egreso se hizo para la factura: " + nc));
					ReporteNotaCredito notaCredito = new ReporteNotaCredito(nc);
					notaCredito.mostrar();
				} else {
					JOptionPane.showMessageDialog(null, "El numero de factura no corresponde a una factura existente",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// * * * * * * * * * * * * * * *
			}
			if (tipoEgresoSeleccionado.equals("Pago proveedor")) {
				String ppNroProveedor = this.ventanaEgresoCaja.getTxtFieldPPNroProveedor().getText();
				String ppNroOrdenCompra = this.ventanaEgresoCaja.getTxtFieldPPNroOrdenCompra().getText();
				String pago = this.ventanaEgresoCaja.getTxtFieldMonto().getText();
				try {
					Double.parseDouble(ppNroProveedor);
					Double.parseDouble(ppNroOrdenCompra);
					Double.parseDouble(pago);
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Los campos deben ser numericos");
					return;
				}
				
				String detalle = ppNroProveedor + " - " + ppNroOrdenCompra;
				if(this.ventanaEgresoCaja.getCbTipoMedioPago().getSelectedItem().equals("Nota Credito")) {
					ProveedorDTO provSeleccionado = obtenerProveedor(Integer.parseInt(ppNroProveedor));
//					if(provSeleccionado.getLimiteCredito() < Double.parseDouble(pago)) {
					if(laCantidadDeCreditoNoSuperaElLimite(Integer.parseInt(ppNroProveedor),Integer.parseInt(ppNroOrdenCompra),Double.parseDouble(pago),provSeleccionado)){
						JOptionPane.showMessageDialog(ventanaEgresoCaja, "La cantidad elegida supera el limite de credito con el que se le puede pagar a este proveedor");
						return;
					}
				}
				
				if(sePudoRegistrarPedidoComoPagado(Integer.parseInt(ppNroOrdenCompra),Integer.parseInt(ppNroProveedor),Double.parseDouble(pago))){
                    ingresarEgreso(detalle);
                }
			}
			this.ventanaEgresoCaja.limpiarCampos();
			this.ventanaEgresoCaja.ocultarCampos();
		}
		actualizarLblBalance();
	}

	private boolean nroFacturaExiste(String nc) {
		boolean ret = false;
		Factura modeloFactura = new Factura(new DAOSQLFactory());
		for (FacturaDTO f : modeloFactura.readAll()) {
			ret = ret || f.getNroFacturaCompleta().equals(nc);
		}
		return ret;
	}

	private boolean yaExisteNotaCredito(String nc) {
		boolean ret = false;
		for (EgresosDTO e : this.egresos.readAll()) {
			ret = ret || e.getDetalle().toLowerCase().equals(nc.toLowerCase());
		}
		return ret;
	}

	private boolean sePudoRegistrarPedidoComoPagado(int ppNroOrdenCompra,int nroProveedor,double pago) {
		for (PedidosPendientesDTO p : this.listaPedidosPendientes) {
			if (p.getId() == ppNroOrdenCompra && nroProveedor == p.getIdProveedor() && p.getEstado().equals("Recibido")) {				
				double totalPagado = p.getTotalPagado()+pago;
				double pagoRestante = p.getPrecioTotal()-totalPagado;
				boolean update = true;

				if( p.getPrecioTotal()<totalPagado) {
					JOptionPane.showMessageDialog(ventanaEgresoCaja, "La cantidad ingresada supera el total a pagar para este pedido (total a pagar: "+(p.getPrecioTotal()-p.getTotalPagado())+")");
					return false;
				}
				
				//se completa el pago
				if (pagoRestante <= 0) {
					
					p.setEstado("Pagado");
					p.setTotalPagado(totalPagado);
					
					update = this.pedidosPendientes.update(p,p.getId());

					if (!update) {
						JOptionPane.showMessageDialog(null,
								"Ha ocurrido un error al marcar como completo el pedido: " + p.getId(), "Error",
								JOptionPane.ERROR_MESSAGE);
						return false;
					} else {
						JOptionPane.showMessageDialog(null,
								"Se ha completado el pago del pedido: " + p.getId() + ".\nPedido marcado como 'Pagado'","Pago", JOptionPane.INFORMATION_MESSAGE);
						return true;
					}

				} 
				//se paga una parte del pedido
				else {
					p.setTotalPagado(totalPagado);
					update = this.pedidosPendientes.update(p,p.getId());
					if (!update) {
						JOptionPane.showMessageDialog(null,	"Ha ocurrido un error al actualizar el total del pedido: " + p.getId(), "Error",JOptionPane.ERROR_MESSAGE);
						return false;
					} else {
						
						BigDecimal pagoRestant = new BigDecimal(pagoRestante).setScale(2, RoundingMode.HALF_UP);
						
						JOptionPane.showMessageDialog(null, "Se ha pagado una parte del total del pedido: " + p.getId()
								+ ".\nNueva cantidad restante a pagar: " + pagoRestant, "Pago", JOptionPane.INFORMATION_MESSAGE);
						return true;
					}
				}			
			}

		}
		JOptionPane.showMessageDialog(ventanaEgresoCaja, "No se ha encontrado el pedido");
        return false;
	}

	public ProveedorDTO obtenerProveedor(int id) {
		Proveedor proveedor = new Proveedor(new DAOSQLFactory());
		ArrayList<ProveedorDTO> todosLosProveedores = (ArrayList<ProveedorDTO>) proveedor.readAll();
		for(ProveedorDTO p: todosLosProveedores) {
			if(p.getId()==id) {
				return p;
			}
		}return null;
	}
	
	public boolean validarCampos() {
		String tipoEgresoSeleccionado = this.ventanaEgresoCaja.getTipoEgresoSeleccionado();
		String medioPagoSeleccionado = this.ventanaEgresoCaja.getMedioPagoSeleccionado();

		String as = this.ventanaEgresoCaja.getTxtFieldAS().getText();

		String nc = this.ventanaEgresoCaja.getTxtFieldNC().getText();

		String ppNroProveedor = this.ventanaEgresoCaja.getTxtFieldPPNroProveedor().getText();
		String ppNroOrdenCompra = this.ventanaEgresoCaja.getTxtFieldPPNroOrdenCompra().getText();


		
		
		if (tipoEgresoSeleccionado.equals("Adelanto de sueldo")) {
			if(!existeEmpleado(as)) {
				JOptionPane.showMessageDialog(null, "El empleado no existe");
				return false;
			}
		}
		
		
		if (tipoEgresoSeleccionado == null || tipoEgresoSeleccionado.equals("")) {
			JOptionPane.showMessageDialog(null, "Elige un tipo de egreso");
			return false;
		}

		if (tipoEgresoSeleccionado.equals("Adelanto de sueldo") && as.equals("")) {
			JOptionPane.showMessageDialog(null, "El campo de CUIL no puede estar vacio");
			return false;
		}

		if (tipoEgresoSeleccionado.equals("Nota Credito") && nc.equals("")) {
			JOptionPane.showMessageDialog(null, "El campo Nro Factura no puede estar vacio");
			return false;
		}

		if (tipoEgresoSeleccionado.equals("Pago proveedor")
				&& (ppNroProveedor.equals("") || ppNroOrdenCompra.equals(""))) {
			JOptionPane.showMessageDialog(null, "Los campos Nro Proveedor y/o Nro Orden de Compra no puede estar vacio");
			return false;
		}

		if (medioPagoSeleccionado == null || medioPagoSeleccionado.equals("")) {
			JOptionPane.showMessageDialog(null, "Elige un medio de pago");
			return false;
		}

		if (this.ventanaEgresoCaja.getTxtFieldMonto().getText().equals("0")
				|| this.ventanaEgresoCaja.getTxtFieldMonto().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Valor de monto invalido");
			return false;
		}

		try {
			Double.parseDouble(this.ventanaEgresoCaja.getTxtFieldMonto().getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo monto es incorrecto");
			return false;	
		}
		
		if (Double.parseDouble(this.ventanaEgresoCaja.getTxtFieldMonto().getText()) > obtenerValorBalance()) {
			JOptionPane.showMessageDialog(null, "El monto supera lo disponible");
			return false;
		}
		if(!formatoTasaConversionEsValido(this.ventanaEgresoCaja.getTxtFieldMonto().getText())) {
			JOptionPane.showMessageDialog(null, "Formato invalido. Ej: 40 o 40.01");
			return false;
		}
		return true;
	}
	
	public boolean formatoTasaConversionEsValido(String precio) {
		boolean expresion = precio.matches("^[0-9]+(\\.[0-9]{1,2})?$");
		if (expresion) {
			return true;
		}
		return false;
	}

	public void tipoEgresoSeleccionado() {
		
		String tipoEgresoSeleccionado = this.ventanaEgresoCaja.getTipoEgresoSeleccionado();
		if (tipoEgresoSeleccionado.equals("Adelanto de sueldo")) {
			this.ventanaEgresoCaja.getBtnVerPedidos().setVisible(false);
			this.ventanaEgresoCaja.mostrarAS();
			llenarCBMedioPagoQueNoSeaNotaCredito();
		}
		if (tipoEgresoSeleccionado.equals("Faltante")) {
			this.ventanaEgresoCaja.getBtnVerPedidos().setVisible(false);
			this.ventanaEgresoCaja.mostrarFA();
			llenarCBMedioPagoQueNoSeaNotaCredito();
		}
		if (tipoEgresoSeleccionado.equals("Nota Credito")) {
			this.ventanaEgresoCaja.getBtnVerPedidos().setVisible(false);
			this.ventanaEgresoCaja.mostrarNC();
			llenarCBMedioPagoNotaCredito();
		}
		if (tipoEgresoSeleccionado.equals("Pago proveedor")) {
			this.ventanaEgresoCaja.mostrarPP();
			this.ventanaEgresoCaja.getBtnVerPedidos().setVisible(true);
			llenarCBMedioPagoQueNoSeaNotaCredito();
		}
	}

	public void llenarCBTipoEgreso() {
		for (TipoEgresosDTO te : this.listaTipoEgresos) {
			this.ventanaEgresoCaja.getCbTipoEgreso().addItem(te.getDescripcion());
		}
	}

	public void llenarCBMedioPagoQueNoSeaNotaCredito() {
		this.ventanaEgresoCaja.getCbTipoMedioPago().removeAllItems();
		for (MedioPagoEgresoDTO mpe : this.listaMedioPagoEgreso) {
			if (!mpe.getIdMoneda().equals("NC")) {
				this.ventanaEgresoCaja.getCbTipoMedioPago().addItem(mpe.getDescripcion());
			}
			if(mpe.getIdMoneda().equals("NC") && this.ventanaEgresoCaja.getTipoEgresoSeleccionado().equals("Pago proveedor")) {
				this.ventanaEgresoCaja.getCbTipoMedioPago().addItem(mpe.getDescripcion());	
			}

		}
	}

	public void llenarCBMedioPagoNotaCredito() {
		this.ventanaEgresoCaja.getCbTipoMedioPago().removeAllItems();
		for (MedioPagoEgresoDTO mpe : this.listaMedioPagoEgreso) {
			if (mpe.getIdMoneda().equals("NC")) {
				this.ventanaEgresoCaja.getCbTipoMedioPago().addItem(mpe.getDescripcion());
			}
		}
	}

	public void atras(ActionEvent a) {
		this.ventanaEgresoCaja.cerrar();
		if(this.controladorVerPedidosAProveedor!=null) {	
			this.controladorVerPedidosAProveedor.ventanaVerPedidosAProveedor.cerrar();
		}
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void ingresarEgreso(String detalle) {
		
		String tipoEgresoSeleccionado = this.ventanaEgresoCaja.getTipoEgresoSeleccionado();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		DateTimeFormatter dtfhora = DateTimeFormatter.ofPattern("hh:mm");
		String hora = dtfhora.format(LocalDateTime.now());
//		String tipoEgresoSeleccionado = this.ventanaEgresoCaja.getTipoEgresoSeleccionado();
		String tipo = obtenerAbrebiaturaMedioPago(this.ventanaEgresoCaja.getMedioPagoSeleccionado());
		String Detalle = detalle;
		double Monto = Double.parseDouble(this.ventanaEgresoCaja.getTxtFieldMonto().getText());

		EgresosDTO egresoNuevo = new EgresosDTO(0, idSucursal, fecha, hora, tipoEgresoSeleccionado, tipo, Detalle,
				Monto);

		this.egresos.insert(egresoNuevo);
		JOptionPane.showMessageDialog(null, "Egreso realizada con exito");

	}

	public boolean existeEmpleado(String detalle) {
		Empleado empleado = new Empleado(new DAOSQLFactory());
		 List<EmpleadoDTO> listaEmpleados  = new ArrayList<EmpleadoDTO>();
		 listaEmpleados = empleado.readAll();
		for (EmpleadoDTO e : listaEmpleados) {
			if(e.getCUIL().equals(detalle)) {
				return true;
			}
		}
		return false;
	}
	
	public String obtenerAbrebiaturaMedioPago(String medioPago) {
		for (MedioPagoEgresoDTO mp : listaMedioPagoEgreso) {
			if (mp.getDescripcion().equals(medioPago)) {
				return mp.getIdMoneda();
			}
		}
		return null;
	}

	public void limpiarCampos() {
		this.ventanaEgresoCaja.limpiarCampos();
	}

	public void mostrarVentana() {
		this.ventanaEgresoCaja.show();
	}

	public void pasarAVerPedidosAProveedor() {
		stock = new Stock(new DAOSQLFactory());
		maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.controladorVerPedidosAProveedor = new ControladorVerPedidosAProveedor(null,pedidosPendientes, this.stock,this.maestroProducto);
		this.controladorVerPedidosAProveedor.inicializar();
		this.controladorVerPedidosAProveedor.ventanaVerPedidosAProveedor.getBtnSalir().setVisible(false);
		this.controladorVerPedidosAProveedor.ventanaVerPedidosAProveedor.getBtnConfirmarCancelacionDe().setVisible(false);
		this.controladorVerPedidosAProveedor.ventanaVerPedidosAProveedor.getBtnConfirmarPedido().setVisible(false);
		this.controladorVerPedidosAProveedor.ventanaVerPedidosAProveedor.getLblCancelar().setVisible(false);
		this.controladorVerPedidosAProveedor.ventanaVerPedidosAProveedor.getLblConfirmar().setVisible(false);
		
		
		this.controladorVerPedidosAProveedor.ventanaVerPedidosAProveedor.getBtnSalirAEgresos().setVisible(true);
		
		
		this.controladorVerPedidosAProveedor.mostrarVentana();
	}
	
	
	public boolean laCantidadDeCreditoNoSuperaElLimite(int nroProveedor,int nroOrdenCompra,double pago,ProveedorDTO p) {
		double cantidadDeCreditoUsado = pago;
		
		//detalle = nroProv - nroOrdenCompra
		
		ArrayList<EgresosDTO> listaDeEgresos = (ArrayList<EgresosDTO>) this.egresos.readAll();
		for(EgresosDTO e: listaDeEgresos) {
			if(e.getMedioPago().equals("NC") && e.getTipo().equals("Pago proveedor") && e.getIdSucursal() == this.idSucursal) {
			int nroProvEgreso = obtenerIdProvEgreso(e.getDetalle());
			int nroOrdenCompraEgreso = obtenerNroOrdenCompraEgreso(e.getDetalle()); 
//			System.out.println(e.getDetalle());
//			System.out.println("nroProvEgreso: "+nroProvEgreso);
//			System.out.println("nroOrdenCOmpraEgreso: "+nroOrdenCompraEgreso);
//			System.out.println("--------------");
				if(nroProvEgreso == nroProveedor && nroOrdenCompraEgreso == nroOrdenCompra) {
					cantidadDeCreditoUsado +=e.getTotal();
//					System.out.println("se encontro un egreso igual, se suma");
				}
			}
		}
		
		System.out.println("cantidad usado con este nuevo valor: "+cantidadDeCreditoUsado+", limite de credito para este prov: "+p.getLimiteCredito());
		
		return p.getLimiteCredito() < cantidadDeCreditoUsado;
	}
	
	public int obtenerIdProvEgreso(String detalle) {
		String ret ="";
		int i=0;
		while(detalle.length() > i && detalle.charAt(i) != ' ') {
			ret = ret+detalle.charAt(i);
			i++;
		}
		return Integer.parseInt(ret);
	}

	public int obtenerNroOrdenCompraEgreso(String detalle) {
		String ret ="";
		int i=0;
		// 4 - 14
		while(detalle.charAt(i) != '-') {
			i++;
		}
		//como luego del '-' sigue un espacio le sumamos +2
		i = i + 2;
		while(i <= (detalle.length()-1)) {
			ret = ret+detalle.charAt(i);

			i++;

			
		}
		return Integer.parseInt(ret);		
	}
	
}
