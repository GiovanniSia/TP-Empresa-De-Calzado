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

public class ControladorOperario implements ActionListener {

	static final String error = "[HiperLink error]";
	VentanaBuscarOrdenesPendientes ventana;
	List<OrdenFabricaDTO> ordenesEnLista;
	
	public ControladorOperario() {
		ventana = new VentanaBuscarOrdenesPendientes();
		ventana.getBtnTrabajarPedido().addActionListener(r->trabajarUnPedidoSeleccionado(r));
		actualizarTabla();
	}
	
	public void inicializar() {
		actualizarTabla();
		ventana.mostrarVentana();
	}
	
	public void actualizarTabla() {
		recuperarListaDeOrdenesPendientes();
		llenarTabla();
	}

	private void recuperarListaDeOrdenesPendientes() {
		DAOSQLFactory a = new DAOSQLFactory();
		ordenesEnLista = a.createFabricacionDAO().readAllOrdenesSinTrabajar();
	}
	
	private void llenarTabla() {
		ventana.getTablaOrdenesPendientes().removeAll();
		reiniciarTabla();
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

	private void reiniciarTabla() {
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
