package presentacion.controlador.fabrica;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import dto.FabricacionesDTO;
import dto.MaestroProductoDTO;
import dto.OrdenFabricaDTO;
import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import dto.StockDTO;
import dto.SucursalDTO;
import modelo.Fabricacion;
import modelo.MaestroProducto;
import modelo.OrdenFabrica;
import modelo.Stock;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.fabrica.ReVentanaIngresarFechaDeLlegada;
import presentacion.vista.fabrica.ReVentanaSeleccionarUnaReceta;
import presentacion.vista.fabrica.ReVentanaTrabajarUnPedido;
import presentacion.vista.fabrica.ReVentanaVerFabricaciones;
import presentacion.vista.fabrica.VentanaBuscarOrdenesPendientes;
import presentacion.vista.fabrica.VentanaIngresarFechaDeLlegada;
import presentacion.vista.fabrica.VentanaMostrarMaterialesDeUnaReceta;
import presentacion.vista.fabrica.VentanaSeleccionarUnaReceta;
import presentacion.vista.fabrica.VentanaTrabajarUnPedido;
import presentacion.vista.fabrica.VentanaVerFabricacionesEnMarcha;

public class ReControladorOperario implements ActionListener {

	static final String error = "[HiperLink error]";
	static final String stringQueDescribeLosTrabajosListosPeroEstanEnEsperaParaEnviar = "En envio";
	
	static final String stringQuePreguntaCancelacionDeProduccion = "¿Estas seguro que lo quieres cancelar?";
	static final String stringQueConfirmaCancelacionDeProduccion = "Se a cancelado la produccion";
	static final String stringQueNoCancelaDeProduccion = "No se a cancelado";
	
	private ReVentanaVerFabricaciones ventanaPrincipal;
	List<OrdenFabricaDTO> ordenesEnLista;
	List<FabricacionesDTO> trabajosEnLista;
	List<RecetaDTO> recetasEnLista;
	
	OrdenFabricaDTO ordenSeleccionado;
	RecetaDTO recetaSeleccionado;
	FabricacionesDTO fabricacionTrabajando;
	ReVentanaSeleccionarUnaReceta ventanaElegirReceta;
	ReVentanaTrabajarUnPedido ventanaUnaTrabajo;
	int idFabrica;
	ReVentanaIngresarFechaDeLlegada ventanaDiaDeLlegada;
	
	Fabricacion modeloFabricacion;
	MaestroProducto modeloProducto;
	OrdenFabrica modeloOrden;
	Stock modeloStock;
	
	public ReControladorOperario(SucursalDTO fabrica) {
		idFabrica = fabrica.getIdSucursal();
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
		}
		
		modeloFabricacion = new Fabricacion(new DAOSQLFactory());
		modeloProducto = new MaestroProducto(new DAOSQLFactory());
		modeloOrden = new OrdenFabrica(new DAOSQLFactory());
		modeloStock = new Stock(new DAOSQLFactory());
		
		ventanaPrincipal = new ReVentanaVerFabricaciones();
		ventanaPrincipal.getBtnTrabajarPedido().addActionListener(r->trabajarSeleccionado(r));
		
		ventanaElegirReceta = new ReVentanaSeleccionarUnaReceta();
		ventanaElegirReceta.getComboBox().addActionListener(r->botonSeleccionarReceta(r));
		ventanaElegirReceta.getBtnTrabajar().addActionListener(r->crearTrabajo(r));
		
		ventanaUnaTrabajo = new ReVentanaTrabajarUnPedido();
		ventanaUnaTrabajo.getBtnAvanzarUnPaso().addActionListener(r->avanzarUnPaso(r));
		ventanaUnaTrabajo.getBtnCancelar().addActionListener(r->cancelarOrden(r));
		
		ventanaDiaDeLlegada = new ReVentanaIngresarFechaDeLlegada();
		ventanaDiaDeLlegada.getBtnbtnIngresarFecha().addActionListener(r->ingresarDias(r));
	}
	
	public void inicializar() {
		refrescarTabla();
		ventanaPrincipal.show();
	}
	
	public void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
        return;
	}
	
	private void refrescarTabla() {
		recuperarListaDeOrdenesPendientes();
		reiniciarTablaTrabajos();
		llenarTablaConOrdenesSinEmpezarATrabajar();
		llenarTablaConTrabajos();
	}
	
	public void trabajarSeleccionado(ActionEvent s) {
		int[] filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		
		if(ordenesEnLista.size() > filasSeleccionadas[0]) {	//Escogio una orden sin trabajar
			OrdenFabricaDTO ordenATrabajar = ordenesEnLista.get(filasSeleccionadas[0]);
			ordenSeleccionado = ordenATrabajar;
			inicializarComboBoxRecetas(filasSeleccionadas[0]);
			this.ventanaElegirReceta.show();
		}else {
			//SELECCIONO UNA ORDEN YA EN MARCHA
			fabricacionTrabajando = trabajosEnLista.get(ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows()[0]-ordenesEnLista.size());
			if(fabricacionTrabajando.getEstado().equals("activo")) {
				reiniciarTablaIngredientesDeUnTrabajo();
				OrdenFabricaDTO oM = this.getOrdenManufactura(fabricacionTrabajando.getIdOrdenFabrica());
				String texto = "";
				MaestroProductoDTO mp = this.buscarProducto(oM.getIdProd());
				texto = "Pedido de la sucursal:" +  oM.getIdSucursal() + "; " + mp.getDescripcion() + " X " + oM.getCantidad();
				ventanaUnaTrabajo.getLblOrden().setText(texto);
				ventanaUnaTrabajo.show();
			}
			if(fabricacionTrabajando.getEstado().equals("completo")) {
				reiniciarTablaIngredientesDeUnTrabajo();
				this.ventanaDiaDeLlegada.show();
			}
			
		}
		
	}
	
	public void crearTrabajo(ActionEvent s) {
		if(this.ventanaElegirReceta.getComboBox().getSelectedIndex() == -1) {
			return;
		}
		recetaSeleccionado = recetasEnLista.get(this.ventanaElegirReceta.getComboBox().getSelectedIndex());
		FabricacionesDTO fabricacion = new FabricacionesDTO(0, ordenSeleccionado.getIdOrdenFabrica(), recetaSeleccionado.getIdReceta(), 1, "activo");
		modeloFabricacion.insertFabricacionEnMarcha(fabricacion);
		refrescarTabla();
		this.ventanaElegirReceta.cerrar();
	}
	
	public void avanzarUnPaso(ActionEvent s) {
		PasoDeRecetaDTO pasoActual = getPasoActual();
		OrdenFabricaDTO ordenTra = getOrdenDeFabricacionDelTrabajoActual();
		boolean tengoMateriales = hayMaterialesSuficientesParaDarPaso(pasoActual, ordenTra);
		
		if(tengoMateriales) {
			// Descontar los materiales que usara
			int cont = 0;
			int cantidadADescontar = 0;
			int restante = 0;
			for(MaestroProductoDTO mp: pasoActual.getPasosDTO().getMateriales()) {
				cantidadADescontar = pasoActual.getPasosDTO().getCantidadUsada().get(cont)*ordenTra.getCantidad();
				for(StockDTO ss: modeloStock.readAll()) {
					if(ss.getIdProducto() == mp.getIdMaestroProducto() && ss.getIdSucursal() == this.idFabrica) {
						restante = ss.getStockDisponible() - cantidadADescontar;
						if(restante < 0){
							cantidadADescontar = -restante;
							restante = 0;
						}
						modeloFabricacion.actuaizarCantidadStockDeUnProductoEnUnaSucursal(restante, ss.getIdStock());
					}
				}
				cont++;
			}
			mostrarMensajeEmergente("Paso concretado");
			fabricacionTrabajando.setNroPasoActual(fabricacionTrabajando.getNroPasoActual()+1);
			modeloFabricacion.actualizarFabricacionEnMarcha(fabricacionTrabajando);
			if(fabricacionTrabajando.getNroPasoActual() > modeloFabricacion.readCantPasosReceta(fabricacionTrabajando.getIdReceta())) {
				/*
				fabricacionTrabajando.completarOrden();
				a.createFabricacionDAO().completarOrden(fabricacionTrabajando, 1);
				a.createFabricacionDAO().actualizarFabricacionEnMarcha(fabricacionTrabajando);
				
				ventanaUnaTrabajo.cerrar();
				*/
				fabricacionTrabajando.completarOrden();
				modeloFabricacion.actualizarFabricacionEnMarcha(fabricacionTrabajando);
				this.ventanaDiaDeLlegada.show();
				this.ventanaUnaTrabajo.cerrar();
				mostrarMensajeEmergente("Se completo el ultimo paso de fabricacion.");
			}
			refrescarTabla();
			
		}else {
			this.ventanaPrincipal.ventanaErrorMaterialesNoSuficientes();
		}
		reiniciarTablaIngredientesDeUnTrabajo();
	}
	
	public void cancelarOrden(ActionEvent s) {
		int res = JOptionPane.showConfirmDialog(null, stringQuePreguntaCancelacionDeProduccion, "", JOptionPane.YES_NO_OPTION);
        switch (res) {
            case JOptionPane.YES_OPTION:
            	fabricacionTrabajando.cancelarOrden();
        		modeloFabricacion.actualizarFabricacionEnMarcha(fabricacionTrabajando);
        		this.refrescarTabla();
        		ventanaUnaTrabajo.cerrar();
        		reiniciarTablaIngredientesDeUnTrabajo();
        		JOptionPane.showMessageDialog(null, stringQueConfirmaCancelacionDeProduccion);
        		break;
            case JOptionPane.NO_OPTION:
            	JOptionPane.showMessageDialog(null, stringQueNoCancelaDeProduccion);
            	break;
        }
	}
	
	private void ingresarDias(ActionEvent s) {
		int valorIngresado = (int) ventanaDiaDeLlegada.getSpinner().getValue();
		if(valorIngresado < 0) {
			return;
		}
		modeloFabricacion.completarOrden(fabricacionTrabajando, valorIngresado);
		this.ventanaDiaDeLlegada.cerrar();
		this.refrescarTabla();
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	
	private void reiniciarTablaTrabajos() {
		ventanaPrincipal.getModelOrdenes().setRowCount(0);
		ventanaPrincipal.getModelOrdenes().setColumnCount(0);
		ventanaPrincipal.getModelOrdenes().setColumnIdentifiers(ventanaPrincipal.getNombreColumnas());
	}
	
	private void llenarTablaConOrdenesSinEmpezarATrabajar() {
		String nombreProducto = "";
		//"Sucursal", "Producto", "Fecha requerido", "Cantidad"
		for(OrdenFabricaDTO o: ordenesEnLista) {
			MaestroProductoDTO producto = buscarProducto(o.getIdProd());
			if(producto == null) {
				nombreProducto = error;
			}else {
				nombreProducto = producto.getDescripcion();
			}
			Object[] agregar = {o.getIdOrdenFabrica(), o.getIdSucursal(), nombreProducto, o.getFechaRequerido(), o.getCantidad()};
			ventanaPrincipal.getModelOrdenes().addRow(agregar);
		}
	}
	
	private void llenarTablaConTrabajos() {
		trabajosEnLista = modeloFabricacion.readAllFabricacionesEnMarcha();
		
		List<OrdenFabricaDTO> todasLasOrdenes = modeloOrden.readAll();
		
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
					String descrPaso;
					if(modeloFabricacion.readAllPasosFromOneReceta(f.getIdReceta()).size() < f.getNroPasoActual()) {
						descrPaso = stringQueDescribeLosTrabajosListosPeroEstanEnEsperaParaEnviar;	//Esta completo por lo que se esta enviando
					}else {
						descrPaso = modeloFabricacion.readAllPasosFromOneReceta(f.getIdReceta()).get(f.getNroPasoActual()-1).getPasosDTO().getDescripcion();
					}
					// "Sucursal", "Producto", "Fecha requerido", "Cantidad", "Paso actual", "Estado", "Fecha completada", "Dias envio"
					String fechaCompletado;
					String diasEnvio = "";
					if(f.getEstado().equals("completo")) {
						fechaCompletado = f.getAnioCompletado() + "-" + f.getMesCompletado() + "-" + f.getDiaCompletado();
						diasEnvio = "" + f.getDiaDisponible();
					}else {
						fechaCompletado = "";
						diasEnvio = "No";
					}
					String pasoActualString = "";
					if(f.getNroPasoActual() <= modeloFabricacion.readAllPasosFromOneReceta(f.getIdReceta()).size()) {
						pasoActualString = descrPaso + ": " + f.getNroPasoActual() + " de " + 
								modeloFabricacion.readAllPasosFromOneReceta(f.getIdReceta()).size();
					}else {
						pasoActualString = stringQueDescribeLosTrabajosListosPeroEstanEnEsperaParaEnviar;
					}
						
					
					Object[] agregar = {orden.getIdOrdenFabrica() ,orden.getIdSucursal(), nombreProducto, orden.getFechaRequerido(), orden.getCantidad(), pasoActualString , f.getEstado(), fechaCompletado, diasEnvio};
					ventanaPrincipal.getModelOrdenes().addRow(agregar);
					
					ventanaPrincipal.getTablaFabricacionesEnMarcha().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
					    @Override
					    public Component getTableCellRendererComponent(JTable table,
					            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

					        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

					        String status = (String)table.getModel().getValueAt(row, 6);
					        if ("activo".equals(status)) {
					            setBackground(Color.YELLOW);
					            //setForeground(Color.WHITE);
					        } else {
					            setBackground(table.getBackground());
					            setForeground(table.getForeground());
					        }       
					        return this;
					    }   
					});
				}
			}
		}
		
	}
	
	private void recuperarListaDeOrdenesPendientes() {
		ordenesEnLista = modeloFabricacion.readAllOrdenesSinTrabajar();
	}
	
	private MaestroProductoDTO buscarProducto(int idProducto) {
		List<MaestroProductoDTO> todosLosProductos = modeloProducto.readAll();
		for(MaestroProductoDTO mp: todosLosProductos) {
			if(mp.getIdMaestroProducto() == idProducto) {
				return mp;
			}
		}
		return null;
	}
	
	public void inicializarComboBoxRecetas(int filaSeleccionada) {
		OrdenFabricaDTO ordenATrabajar = ordenesEnLista.get(filaSeleccionada);
		ordenSeleccionado = ordenATrabajar;
		List<RecetaDTO>listaRecetas = modeloFabricacion.readAllReceta();	//EN UN FUTURO TENDRA QUE CAMBIAR A RECETAS DISPONIBLES
		//RecetaDTO receta = new RecetaDTO(1,1,error);
		recetasEnLista = new ArrayList<RecetaDTO>();
		this.ventanaElegirReceta.getComboBox().removeAllItems();
		for(RecetaDTO r: listaRecetas) {
			if(ordenATrabajar.getIdProd() == r.getIdProducto()) {
				if(modeloFabricacion.isRecetaDisponible(r)) {
					/*
					if(existeMaterialSuficiente(r, ordenSeleccionado.getCantidad())) {
						//System.out.println("LEI RECETA DISPONIBLE");s
						//receta = r;
						recetasEnLista.add(r);
						this.ventanaElegirReceta.getComboBox().addItem(r.getDescripcion());
					}*/
					recetasEnLista.add(r);
					this.ventanaElegirReceta.getComboBox().addItem(r.getDescripcion());
				}
			}
		}
	}
	
	private void botonSeleccionarReceta(ActionEvent s) {
		if(this.ventanaElegirReceta.getComboBox().getSelectedIndex() == -1) {
			return;
		}
		recetaSeleccionado = recetasEnLista.get(this.ventanaElegirReceta.getComboBox().getSelectedIndex());
		String nombreProducto = "";
		MaestroProductoDTO producto = buscarProducto(this.ordenSeleccionado.getIdProd());
		if(producto == null) {
			nombreProducto = error;
		}else {
			nombreProducto = producto.getDescripcion();
		}
		ventanaElegirReceta.getLblSolicitado().setText(nombreProducto + ", cantidad ordenada: " + this.ordenSeleccionado.getCantidad() + ".");
		
		//String texto = "";
		reiniciarTablaIngredientes();
		List<PasoDeRecetaDTO> pasos = modeloFabricacion.readAllPasosFromOneReceta(recetaSeleccionado.getIdReceta());
		for(PasoDeRecetaDTO p: pasos) {
			for(int x = 0; x < p.getPasosDTO().getMateriales().size(); x++) {
				//texto = texto + "" + p.getPasosDTO().getMateriales().get(x).getDescripcion() + " Cantidad usada: "+ (p.getPasosDTO().getCantidadUsada().get(x)*this.ordenSeleccionado.getCantidad());
				Object[] agregar = {p.getPasosDTO().getMateriales().get(x).getDescripcion(), (p.getPasosDTO().getCantidadUsada().get(x)*this.ordenSeleccionado.getCantidad())};
				ventanaElegirReceta.getModelOrdenes().addRow(agregar);
			}
		}
		
		if(existeMaterialSuficiente(recetaSeleccionado, ordenSeleccionado.getCantidad())) {
			this.ventanaElegirReceta.getLblMensaje().setText("Hay materiales suficientes");
		}else {
			this.ventanaElegirReceta.getLblMensaje().setText("No hay materiales suficientes");
		}
	}
	
	private void reiniciarTablaIngredientes() {
		ventanaElegirReceta.getModelOrdenes().setRowCount(0);
		ventanaElegirReceta.getModelOrdenes().setColumnCount(0);
		ventanaElegirReceta.getModelOrdenes().setColumnIdentifiers(ventanaElegirReceta.getNombreColumnas());
	}
	
	private boolean existeMaterialSuficiente(RecetaDTO receta, int cantidadDeseada) {
		boolean ret = true;
		List<PasoDeRecetaDTO> pasos = modeloFabricacion.readAllPasosFromOneReceta(receta.getIdReceta());
		for(PasoDeRecetaDTO p: pasos) {
			int x = 0;
			for(MaestroProductoDTO mp: p.getPasosDTO().getMateriales()) {
				ret = ret && hayStockSuficienteDeUnMaterial(mp.getIdMaestroProducto(), p.getPasosDTO().getCantidadUsada().get(x)*cantidadDeseada);
				x++;
			}
		}
		return ret;
	}
	
	private boolean hayStockSuficienteDeUnMaterial(int idMaestroProducto, int i) {
		//Primero cuento todo el stock luego juzgo
		List<StockDTO> todoElStock = modeloStock.readAll();
		int cantidadTotalDisponible = 0;
		for(StockDTO s: todoElStock) {
			if(s.getIdProducto() == idMaestroProducto && s.getIdSucursal() == this.idFabrica) {
				cantidadTotalDisponible = cantidadTotalDisponible + s.getStockDisponible();
			}
		}
		return cantidadTotalDisponible >= i;
	}
	
	private void reiniciarTablaIngredientesDeUnTrabajo() {
		ventanaUnaTrabajo.getModelOrdenes().setRowCount(0);
		ventanaUnaTrabajo.getModelOrdenes().setColumnCount(0);
		ventanaUnaTrabajo.getModelOrdenes().setColumnIdentifiers(ventanaUnaTrabajo.getNombreColumnas());
		
		OrdenFabricaDTO of = this.getOrdenManufactura(this.fabricacionTrabajando.getIdOrdenFabrica());
		
		PasoDeRecetaDTO p = this.getPasoActual();
		for(int x = 0; x<p.getPasosDTO().getMateriales().size(); x++) {
			Object[] agregar = {p.getPasosDTO().getMateriales().get(x).getDescripcion(), (p.getPasosDTO().getCantidadUsada().get(x)*of.getCantidad())};
			ventanaUnaTrabajo.getModelOrdenes().addRow(agregar);
		}
	}
	
	private OrdenFabricaDTO getOrdenManufactura(int idOrdenManufactura) {
		List<OrdenFabricaDTO> todasLasOrdenes = modeloOrden.readAll();
		OrdenFabricaDTO orden = null;
		for(OrdenFabricaDTO of: todasLasOrdenes) {
			if(of.getIdOrdenFabrica() == idOrdenManufactura) {
				return of;
			}
		}
		return orden;
	}
	
	public PasoDeRecetaDTO getPasoActual() {
		List<PasoDeRecetaDTO> pasos = modeloFabricacion.readAllPasosFromOneReceta(fabricacionTrabajando.getIdReceta());
		PasoDeRecetaDTO pasoActual = pasos.get(0);
		for(PasoDeRecetaDTO p: pasos) {
			if(p.getNroOrden() == fabricacionTrabajando.getNroPasoActual()) {
				pasoActual = p;
			}
		}
		return pasoActual;
	}
	
	public OrdenFabricaDTO getOrdenDeFabricacionDelTrabajoActual() {
		List<OrdenFabricaDTO> ordenes = modeloOrden.readAll();
		OrdenFabricaDTO ordenTra = ordenes.get(0);
		for(OrdenFabricaDTO of: ordenes) {
			if(of.getIdOrdenFabrica() == fabricacionTrabajando.getIdOrdenFabrica()) {
				ordenTra = of;
			}
		}
		return ordenTra;
	}
	
	public boolean hayMaterialesSuficientesParaDarPaso(PasoDeRecetaDTO pasoActual, OrdenFabricaDTO ordenTra) {
		boolean tengoMateriales = true;
		int cont = 0;
		for(MaestroProductoDTO mp: pasoActual.getPasosDTO().getMateriales()) {
			tengoMateriales = tengoMateriales && this.hayStockSuficienteDeUnMaterial(mp.getIdMaestroProducto(), pasoActual.getPasosDTO().getCantidadUsada().get(cont)*ordenTra.getCantidad());
			cont++;
		}
		return tengoMateriales;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
