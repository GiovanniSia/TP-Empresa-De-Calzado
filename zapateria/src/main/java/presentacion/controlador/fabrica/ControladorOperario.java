package presentacion.controlador.fabrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.FabricacionesDTO;
import dto.MaestroProductoDTO;
import dto.OrdenFabricaDTO;
import dto.RecetaDTO;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.fabrica.VentanaBuscarOrdenesPendientes;
import presentacion.vista.fabrica.VentanaTrabajarUnPedido;
import presentacion.vista.fabrica.VentanaVerFabricacionesEnMarcha;

public class ControladorOperario implements ActionListener {

	static final String error = "[HiperLink error]";
	VentanaBuscarOrdenesPendientes ventana;
	List<OrdenFabricaDTO> ordenesEnLista;
	
	VentanaVerFabricacionesEnMarcha ventanaTrabajos;
	List<FabricacionesDTO> trabajosEnLista;
	
	FabricacionesDTO fabricacionTrabajando;
	VentanaTrabajarUnPedido ventanaUnaTrabajo;
	
	public ControladorOperario() {
		ventana = new VentanaBuscarOrdenesPendientes();
		ventana.getBtnTrabajarPedido().addActionListener(r->trabajarUnPedidoSeleccionado(r));
		actualizarTabla();
		
		ventanaTrabajos = new VentanaVerFabricacionesEnMarcha();
		ventana.getBtnVerFabricaciones().addActionListener(r->abrirVentanaTrabajos(r));
		
		ventanaTrabajos = new VentanaVerFabricacionesEnMarcha();
		ventanaTrabajos.getBtnVerFabricaciones().addActionListener(r->abrirVentanaOrdenes(r));
		
		ventanaUnaTrabajo = new VentanaTrabajarUnPedido();
		ventanaTrabajos.getBtnTrabajarPedido().addActionListener(r->abrirUnaVentanaDeUnTrabajo(r));
		
		ventanaUnaTrabajo.getBtnAvanzarUnPaso().addActionListener(r->avanzarUnPaso(r));
		ventanaUnaTrabajo.getBtnRetrocederUnPaso().addActionListener(r->retrocederUnPaso(r));
		ventanaUnaTrabajo.getBtnCancelar().addActionListener(r->cancelarOrden(r));
	}
	
	public void inicializar() {
		actualizarTabla();
		ventana.mostrarVentana();
	}
	
	public void actualizarTabla() {
		recuperarListaDeOrdenesPendientes();
		llenarTablaOrdenes();
	}

	private void recuperarListaDeOrdenesPendientes() {
		DAOSQLFactory a = new DAOSQLFactory();
		ordenesEnLista = a.createFabricacionDAO().readAllOrdenesSinTrabajar();
	}
	
	private void llenarTablaOrdenes() {
		ventana.getTablaOrdenesPendientes().removeAll();
		reiniciarTablaOrdenes();
		String nombreProducto = "";
		//"Sucursal", "Producto", "Fecha requerido", "Cantidad"
		for(OrdenFabricaDTO o: ordenesEnLista) {
			MaestroProductoDTO producto = buscarProducto(o.getIdProd());
			if(producto == null) {
				nombreProducto = error;
			}else {
				nombreProducto = producto.getDescripcion();
			}
			Object[] agregar = {o.getIdSucursal(), nombreProducto, o.getFechaRequerido(), o.getCantidad()};
			ventana.getModelOrdenes().addRow(agregar);
		}
	}

	private void reiniciarTablaOrdenes() {
		ventana.getModelOrdenes().setRowCount(0);
		ventana.getModelOrdenes().setColumnCount(0);
		ventana.getModelOrdenes().setColumnIdentifiers(ventana.getNombreColumnas());
	}
	
	private MaestroProductoDTO buscarProducto(int idProducto) {
		DAOSQLFactory a = new DAOSQLFactory();
		List<MaestroProductoDTO> todosLosProductos = a.createMaestroProductoDAO().readAll();
		for(MaestroProductoDTO mp: todosLosProductos) {
			if(mp.getIdMaestroProducto() == idProducto) {
				return mp;
			}
		}
		return null;
	}
	
	public void trabajarUnPedidoSeleccionado(ActionEvent s) {
		int[] filasSeleccionadas = ventana.getTablaOrdenesPendientes().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		crearFabricacionEnMarcha(filasSeleccionadas[0]);
		actualizarTabla();
	}
	
	public void crearFabricacionEnMarcha(int filaSeleccionada) {
		OrdenFabricaDTO ordenATrabajar = ordenesEnLista.get(filaSeleccionada);
		DAOSQLFactory a = new DAOSQLFactory();
		List<RecetaDTO>listaRecetas = a.createFabricacionDAO().readAllReceta();	//EN UN FUTURO TENDRA QUE CAMBIAR A RECETAS DISPONIBLES
		RecetaDTO receta = new RecetaDTO(1,1,error);
		for(RecetaDTO r: listaRecetas) {
			if(ordenATrabajar.getIdProd() == r.getIdProducto()) {
				receta = r;
			}
		}
		FabricacionesDTO fabricacion = new FabricacionesDTO(0, ordenATrabajar.getIdOrdenFabrica(), receta.getIdReceta(), 1, "activo");
		a.createFabricacionDAO().insertFabricacionEnMarcha(fabricacion);
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * *
	
	private void reiniciarTablaTrabajos() {
		ventanaTrabajos.getModelOrdenes().setRowCount(0);
		ventanaTrabajos.getModelOrdenes().setColumnCount(0);
		ventanaTrabajos.getModelOrdenes().setColumnIdentifiers(ventanaTrabajos.getNombreColumnas());
	}
	
	private void llenarTablaTrabajos() {
		reiniciarTablaTrabajos();
		
		DAOSQLFactory a = new DAOSQLFactory();
		trabajosEnLista = a.createFabricacionDAO().readAllFabricacionesEnMarcha();
		
		List<OrdenFabricaDTO> todasLasOrdenes = a.createOrdenFabricaDAO().readAll();
		
		OrdenFabricaDTO orden = null;
		for(FabricacionesDTO f: trabajosEnLista) {
			String nombreProducto = null;
			for(OrdenFabricaDTO of: todasLasOrdenes) {
				if(of.getIdOrdenFabrica() == f.getIdOrdenFabrica()) {
					orden = of;
					MaestroProductoDTO producto = buscarProducto(orden.getIdProd());
					if(producto == null) {
						nombreProducto = error;
					}else {
						nombreProducto = producto.getDescripcion();
					}
					String descrPaso = a.createFabricacionDAO().readAllPasosFromOneReceta(f.getIdReceta()).get(f.getNroPasoActual()-1).getPasosDTO().getDescripcion();
					Object[] agregar = {orden.getIdSucursal(), nombreProducto, orden.getFechaRequerido(), orden.getCantidad(), descrPaso, f.getNroPasoActual(), f.getEstado()};
					ventanaTrabajos.getModelOrdenes().addRow(agregar);
				}
			}
		}
		//"Sucursal", "Producto", "Fecha requerido", "Cantidad", "Paso actual", "Nro Paso", "Estado"
		
	}
	
	public void abrirVentanaTrabajos(ActionEvent s) {
		llenarTablaTrabajos();
		ventanaTrabajos.show();
		ventana.cerrar();
	}
	
	public void abrirVentanaOrdenes(ActionEvent s) {
		llenarTablaOrdenes();
		ventanaTrabajos.cerrar();
		ventana.show();
	}
	
	public void abrirUnaVentanaDeUnTrabajo(ActionEvent s) {
		if(ventanaTrabajos.getTablaFabricacionesEnMarcha().getSelectedRows().length == 0) {
			return;
		}
		fabricacionTrabajando = trabajosEnLista.get(ventanaTrabajos.getTablaFabricacionesEnMarcha().getSelectedRows()[0]);
		ventanaUnaTrabajo.show();
	}
	
	public void avanzarUnPaso(ActionEvent s) {
		fabricacionTrabajando.setNroPasoActual(fabricacionTrabajando.getNroPasoActual()+1);
		DAOSQLFactory a = new DAOSQLFactory();
		a.createFabricacionDAO().actualizarFabricacionEnMarcha(fabricacionTrabajando);
		
		if(fabricacionTrabajando.getNroPasoActual() > a.createFabricacionDAO().readCantPasosReceta(fabricacionTrabajando.getIdReceta())) {
			fabricacionTrabajando.completarOrden();
			a.createFabricacionDAO().completarOrden(fabricacionTrabajando, 1);
			a.createFabricacionDAO().actualizarFabricacionEnMarcha(fabricacionTrabajando);
			//a.createFabricacionDAO().actualizarSiLlegoFechaDeEntrega(fabricacionTrabajando);
		}
		llenarTablaTrabajos();
	}
	
	public void retrocederUnPaso(ActionEvent s) {
		if(fabricacionTrabajando.getNroPasoActual() == 1) {
			return;
		}
		fabricacionTrabajando.setNroPasoActual(fabricacionTrabajando.getNroPasoActual()-1);
		DAOSQLFactory a = new DAOSQLFactory();
		a.createFabricacionDAO().actualizarFabricacionEnMarcha(fabricacionTrabajando);
		llenarTablaTrabajos();
	}
	
	public void cancelarOrden(ActionEvent s) {
		fabricacionTrabajando.cancelarOrden();
		DAOSQLFactory a = new DAOSQLFactory();
		a.createFabricacionDAO().actualizarFabricacionEnMarcha(fabricacionTrabajando);
		llenarTablaTrabajos();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
