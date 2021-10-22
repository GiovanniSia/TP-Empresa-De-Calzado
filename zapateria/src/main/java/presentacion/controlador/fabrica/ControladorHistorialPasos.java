package presentacion.controlador.fabrica;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.UIManager;

import dto.HistorialPasoDTO;
import dto.MaestroProductoDTO;
import dto.OrdenFabricaDTO;
import dto.SucursalDTO;
import modelo.Fabricacion;
import modelo.HistorialPaso;
import modelo.MaestroProducto;
import modelo.OrdenFabrica;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.fabrica.VentanaVerDetalle;
import presentacion.vista.fabrica.VentanaVerHistorialPasos;

public class ControladorHistorialPasos {

	List<HistorialPasoDTO> historialEnLista;

	Controlador controlador;
	SucursalDTO fabrica;

	HistorialPaso modelosHistorialPaso;

	VentanaVerHistorialPasos ventana;

	OrdenFabrica modeloOrden;
	Fabricacion modeloFabricacion;
	MaestroProducto modeloProducto;
	
	VentanaVerDetalle ventanaExplicacion;

	// public ControladorHistorialPasos(Controlador controlador,SucursalDTO fabrica)
	// {
	public ControladorHistorialPasos(SucursalDTO fabrica) {
		// this.controlador = controlador;
		this.fabrica = fabrica;
		this.modeloFabricacion = new Fabricacion(new DAOSQLFactory());
		modeloProducto = new MaestroProducto(new DAOSQLFactory());

		modeloOrden = new OrdenFabrica(new DAOSQLFactory());
		historialEnLista = new ArrayList<HistorialPasoDTO>();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		/*
		 * modeloFabricacion = new Fabricacion(new DAOSQLFactory()); modeloProducto =
		 * new MaestroProducto(new DAOSQLFactory()); modeloOrden = new OrdenFabrica(new
		 * DAOSQLFactory()); modeloStock = new Stock(new DAOSQLFactory());
		 */
		modelosHistorialPaso = new HistorialPaso(new DAOSQLFactory());
		ventana = new VentanaVerHistorialPasos();
		ventana.getBtnVerDescripcion().addActionListener(r -> verDescripcion(r));
		ventana.getTextProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		ventana.getTextId().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		ventana.getTextSucursal().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		ventana.getTextAccion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		ventana.getTextEmpleado().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		
		ventanaExplicacion = new VentanaVerDetalle();
		ventanaExplicacion.getBtnSalir().addActionListener(r -> cerrarDescripcion(r));
	}

	public void inicializar() {
		refrescarTabla();
		mostrarVentana();
	}
	
	private String getDescripcion() {
		int[] filasSeleccionadas = ventana.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return "";
		}
		String mensaje = historialEnLista.get(filasSeleccionadas[0]).getDescr();
		return mensaje;
	}
	
	private String getTipoCancelacion() {
		int[] filasSeleccionadas = ventana.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return "";
		}
		String mensaje = historialEnLista.get(filasSeleccionadas[0]).getTipo();
		return mensaje;
	}

	private void cerrarDescripcion(ActionEvent s) {
		ventanaExplicacion.cerrar();
	}
	
	private void verDescripcion(ActionEvent s) {
		String mensaje = getDescripcion();
		String mensajeAMostrar = "<html><body>";
		/*
		int x = 1;
		for(String palabra: palabrasPorPalabras(mensaje)) {
			if(x%15 == 0) {
				mensajeAMostrar = mensajeAMostrar + "<br>";
			}
			mensajeAMostrar = mensajeAMostrar + " " + palabra;
		}
		mensajeAMostrar = mensajeAMostrar + "</body></html>";
		*/
		mensajeAMostrar = mensaje;
		ventanaExplicacion.getTextPane().setText(mensajeAMostrar);
		ventanaExplicacion.getLblId().setText(getTipoCancelacion());
		ventanaExplicacion.mostrarVentana();
		//mostrarMensajeEmergente(mensajeAMostrar);
	}

	private void refrescarTabla() {
		vaciarTabla();
		historialEnLista = recuperarTodoElHistorial();
		llenarTablaConElHistorial();
	}

	private void vaciarTabla() {
		ventana.getModelOrdenes().setRowCount(0);
		ventana.getModelOrdenes().setColumnCount(0);
		ventana.getModelOrdenes().setColumnIdentifiers(ventana.getNombreColumnas());
		ventana.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(2).setPreferredWidth(120);
		ventana.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(4).setPreferredWidth(195);
		ventana.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(5).setPreferredWidth(100);
		ventana.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(6).setPreferredWidth(150);
	}

	private void llenarTablaConElHistorial() {
		for (HistorialPasoDTO hp : historialEnLista) {
			int nroOrden = hp.getIdOrden();
			OrdenFabricaDTO ordenTrabajandoLocal = getOrdenFabricacion(nroOrden);
			MaestroProductoDTO productoTrabajandoLocal = getMaestroProducto(ordenTrabajandoLocal.getIdProd());

			String nroOrdenM = ordenTrabajandoLocal.getIdOrdenFabrica() + "";
			String idSucursal = ordenTrabajandoLocal.getIdSucursal() + "";
			String producto = productoTrabajandoLocal.getIdMaestroProducto() + ", "
					+ productoTrabajandoLocal.getDescripcion() + ", " + productoTrabajandoLocal.getTalle();
			String cantidad = ordenTrabajandoLocal.getCantidad() + "";
			String accion = hp.getDescrPasoCompletado();
			//String detalle = hp.getDescr();
			String idEmpleado = hp.getIdEmpleado() + "";
			String nombreEmpleado = hp.getNombreCompleto();
			String fechaHora = hp.getFecha() + " " + hp.getHora();
			// Object[] agregar = {hp.getId(), hp.getDescrPasoCompletado()};
			// { "Nro orden","Sucursal", "Producto", "Cantidad", "Accion", "Detalle", "Fecha
			// y hora","Empleado"};
			Object[] agregar = { nroOrdenM, idSucursal, producto, cantidad, accion, fechaHora,
					idEmpleado + ", " + nombreEmpleado };
			ventana.getModelOrdenes().addRow(agregar);
			
		}
	}

	private OrdenFabricaDTO getOrdenFabricacion(int idOrden) {
		OrdenFabricaDTO ret = null;
		for (OrdenFabricaDTO of : modeloOrden.readAll()) {
			if (of.getIdOrdenFabrica() == idOrden) {
				ret = of;
			}
		}
		return ret;
	}

	private MaestroProductoDTO getMaestroProducto(int idProducto) {
		List<MaestroProductoDTO> todosLosProductos = modeloProducto.readAll();
		for (MaestroProductoDTO mp : todosLosProductos) {
			if (mp.getIdMaestroProducto() == idProducto) {
				return mp;
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	private List<HistorialPasoDTO> recuperarTodoElHistorial() {
		List<HistorialPasoDTO> historial = modelosHistorialPaso.readAll();
		List<HistorialPasoDTO> historialRet = new ArrayList<HistorialPasoDTO>();
		
		String idOrdenParametro = ventana.getTextId().getText().toLowerCase();
		String productoParametro = ventana.getTextProducto().getText().toLowerCase();
		String sucursalParametro = ventana.getTextSucursal().getText().toLowerCase();
		String accionParametro = ventana.getTextAccion().getText().toLowerCase();
		String empleadoParametro = ventana.getTextEmpleado().getText().toLowerCase();
		
		for(HistorialPasoDTO hp: historial) {
			int nroOrden = hp.getIdOrden();
			OrdenFabricaDTO ordenTrabajandoLocal = getOrdenFabricacion(nroOrden);
			MaestroProductoDTO productoTrabajandoLocal = getMaestroProducto(ordenTrabajandoLocal.getIdProd());
			String producto = productoTrabajandoLocal.getIdMaestroProducto() + ", "
					+ productoTrabajandoLocal.getDescripcion() + ", " + productoTrabajandoLocal.getTalle();
			
			String idOrden = hp.getIdOrden()+"";
			if(idOrden.matches(".*"+idOrdenParametro+".*")) {
				if(producto.toLowerCase().matches(".*"+productoParametro.toLowerCase()+".*")) {
					String idSucursalLocal = ordenTrabajandoLocal.getIdSucursal()+"";
					if(idSucursalLocal.toLowerCase().matches(".*"+sucursalParametro.toLowerCase()+".*")) {
						if(hp.getDescrPasoCompletado().toLowerCase().matches(".*"+accionParametro+".*")) {
							String idEmpleado = hp.getIdEmpleado() + "";
							String nombreEmpleado = hp.getNombreCompleto();
							String nombreEnTabla = idEmpleado + ", " + nombreEmpleado;
							if(nombreEnTabla.toLowerCase().matches(".*"+empleadoParametro+".*")) {
								// fechaHasta()
								//String fechaAccion = (String) palabrasPorPalabras(hp.getFecha())[0];
								Date fechaPaso = new Date();
								String[] fechaPasoString = palabrasPorBarra(hp.getFecha());
								fechaPaso.setYear(Integer.valueOf(fechaPasoString[0])-1900);
								fechaPaso.setMonth(Integer.valueOf(fechaPasoString[1])-1);
								fechaPaso.setDate(Integer.valueOf(fechaPasoString[2]));
								boolean cumpleDesde = false;
								if(getFechaDesdeDate() != null) {
									if(getFechaDesdeDate().compareTo(fechaPaso)<=0) {
										cumpleDesde = true;
									}
								}else {
									cumpleDesde = true;
								}
								boolean cumpleHasta = false;
								if(getFechaDesdeHasta() != null) {
									if(getFechaDesdeHasta().compareTo(fechaPaso)>=0) {
										cumpleHasta = true;
									}
								}else {
									cumpleHasta = true;
								}
								if(cumpleDesde && cumpleHasta) {
									historialRet.add(hp);
								}
								
							}
						}
					}
				}
			}
		}
		
		
		return historialRet;
	}

	private void mostrarVentana() {
		ventana.mostrarVentana();
	}
	public static void main(String[] args) {

		SucursalDTO suc = new SucursalDTO(1, "3123314", "Uruguay", "134", "BsAs", "Tortuguitas", "Argentina", "1669",
				"Una mas");
		ControladorHistorialPasos con = new ControladorHistorialPasos(suc);
		con.inicializar();

	}
	
	private void refrescarTablaPorTecla() {
		refrescarTabla();
	}
	
	@SuppressWarnings("deprecation")
	private Date getFechaDesdeDate() {
		Date ret = this.ventana.getFechaDesde().getDate();
		if(ret != null) {
			ret.setHours(0);
			ret.setMinutes(0);
			ret.setSeconds(0);
		}
		return ret;
	}
	
	@SuppressWarnings("deprecation")
	private Date getFechaDesdeHasta() {
		Date ret = this.ventana.getFechaHasta().getDate();
		if(ret != null) {
			ret.setHours(23);
			ret.setMinutes(59);
			ret.setSeconds(59);
		}
		return ret;
	}
	
	private String[] palabrasPorBarra(String sentence) {
		if (sentence == null || sentence.isEmpty()) {
			return new String[0];
		}
		String[] words = sentence.split("-");
		return words;
	}

}
