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
import presentacion.vista.fabrica.VentanaBuscarOrdenesPendientes;
import presentacion.vista.fabrica.VentanaIngresarFechaDeLlegada;
import presentacion.vista.fabrica.VentanaMostrarMaterialesDeUnaReceta;
import presentacion.vista.fabrica.VentanaSeleccionarUnaReceta;
import presentacion.vista.fabrica.VentanaTrabajarUnPedido;
import presentacion.vista.fabrica.VentanaVerFabricacionesEnMarcha;

public class ControladorOperario implements ActionListener {

	static final String error = "[HiperLink error]";
	static final String stringQueDescribeLosTrabajosListosPeroEstanEnEsperaParaEnviar = "En envio";
	
	static final String stringQuePreguntaCancelacionDeProduccion = "�Estas seguro que lo quieres cancelar?";
	static final String stringQueConfirmaCancelacionDeProduccion = "Se a cancelado la produccion";
	static final String stringQueNoCancelaDeProduccion = "No se a cancelado";
	
	VentanaBuscarOrdenesPendientes ventana;
	List<OrdenFabricaDTO> ordenesEnLista;
	OrdenFabricaDTO ordenSeleccionado;
	RecetaDTO recetaSeleccionado;
	
	VentanaVerFabricacionesEnMarcha ventanaTrabajos;
	List<FabricacionesDTO> trabajosEnLista;
	
	List<RecetaDTO> recetasEnLista;
	
	FabricacionesDTO fabricacionTrabajando;
	VentanaTrabajarUnPedido ventanaUnaTrabajo;
	
	VentanaSeleccionarUnaReceta ventanaElegirReceta;
	
	VentanaIngresarFechaDeLlegada ventanaDiaDeLlegada;
	
	int idFabrica;
	
	VentanaMostrarMaterialesDeUnaReceta ventanaMostrarIngredientes;
	
	Fabricacion modeloFabricacion;
	MaestroProducto modeloProducto;
	OrdenFabrica modeloOrden;
	Stock modeloStock;
	
	public ControladorOperario(SucursalDTO fabrica) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
		}
		
		modeloFabricacion = new Fabricacion(new DAOSQLFactory());
		modeloProducto = new MaestroProducto(new DAOSQLFactory());
		modeloOrden = new OrdenFabrica(new DAOSQLFactory());
		modeloStock = new Stock(new DAOSQLFactory());
		
		ventanaMostrarIngredientes = new VentanaMostrarMaterialesDeUnaReceta();
		ventanaMostrarIngredientes.getBtnTrabajar().addActionListener(r->crearTrabajoEnMarcha(r));;
		
		
		this.idFabrica = fabrica.getIdSucursal();
		ventana = new VentanaBuscarOrdenesPendientes();
		ventana.getBtnTrabajarPedido().addActionListener(r->trabajarUnPedidoSeleccionado(r));
		
		ventanaElegirReceta = new VentanaSeleccionarUnaReceta();
		ventanaElegirReceta.getBtnElegirReceta().addActionListener(r->botonSeleccionarReceta(r));//trabajarUnPedidoSeleccionado
		
		
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
		
		ventanaDiaDeLlegada = new VentanaIngresarFechaDeLlegada();
		ventanaDiaDeLlegada.getBtnbtnIngresarFecha().addActionListener(r->ingresarDias(r));
		/*
		DAOSQLFactory a = new DAOSQLFactory();
		List<RecetaDTO> rec = a.createFabricacionDAO().readAllReceta();
		for(RecetaDTO r: rec) {
			System.out.println("* * * * * ");
			System.out.println("RECETA: " + r.getDescripcion());
			List<PasoDeRecetaDTO> pasos = a.createFabricacionDAO().readAllPasosFromOneReceta(r.getIdReceta());
			for(PasoDeRecetaDTO p: pasos) {
				System.out.println("	PASO NROorden:" + p.getNroOrden() + ", " + p.getPasosDTO().getDescripcion());
				int x = 0;
				for(MaestroProductoDTO mp:  p.getPasosDTO().getMateriales()) {
					System.out.println("		Material: " + mp.getDescripcion() + ", cantidadUsada: " + p.getPasosDTO().getCantidadUsada().get(x));
					x++;
				}
			}
		}*/
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
		ordenesEnLista = modeloFabricacion.readAllOrdenesSinTrabajar();
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
		List<MaestroProductoDTO> todosLosProductos = modeloProducto.readAll();
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
		OrdenFabricaDTO ordenATrabajar = ordenesEnLista.get(filasSeleccionadas[0]);
		ordenSeleccionado = ordenATrabajar;
		inicializarComboBoxRecetas(filasSeleccionadas[0]);
		this.ventanaElegirReceta.show();
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
	
	public void crearTrabajoEnMarcha(ActionEvent s) {
		if(this.ventanaElegirReceta.getComboBox().getSelectedIndex() == -1) {
			return;
		}
		recetaSeleccionado = recetasEnLista.get(this.ventanaElegirReceta.getComboBox().getSelectedIndex());
		FabricacionesDTO fabricacion = new FabricacionesDTO(0, ordenSeleccionado.getIdOrdenFabrica(), recetaSeleccionado.getIdReceta(), 1, "activo");
		modeloFabricacion.insertFabricacionEnMarcha(fabricacion);
		actualizarTabla();
		this.ventanaElegirReceta.cerrar();
		ventanaMostrarIngredientes.cerrar();
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * *
	
	private void reiniciarTablaTrabajos() {
		ventanaTrabajos.getModelOrdenes().setRowCount(0);
		ventanaTrabajos.getModelOrdenes().setColumnCount(0);
		ventanaTrabajos.getModelOrdenes().setColumnIdentifiers(ventanaTrabajos.getNombreColumnas());
	}
	
	private void llenarTablaTrabajos() {
		reiniciarTablaTrabajos();
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
					// "Sucursal", "Producto", "Fecha requerido", "Cantidad", "Paso actual", "Nro Paso", "Estado", "Fecha completada", "Dias envio"
					String fechaCompletado;
					String diasEnvio = "";
					if(f.getEstado().equals("completo")) {
						fechaCompletado = f.getAnioCompletado() + "-" + f.getMesCompletado() + "-" + f.getDiaCompletado();
						diasEnvio = "" + f.getDiaDisponible();
					}else {
						fechaCompletado = "";
						diasEnvio = "No";
					}
					Object[] agregar = {orden.getIdSucursal(), nombreProducto, orden.getFechaRequerido(), orden.getCantidad(), descrPaso, f.getNroPasoActual(), f.getEstado(), fechaCompletado, diasEnvio};
					ventanaTrabajos.getModelOrdenes().addRow(agregar);
					
					ventanaTrabajos.getTablaFabricacionesEnMarcha().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
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
	
	public void abrirVentanaTrabajos(ActionEvent s) {
		llenarTablaTrabajos();
		ventanaTrabajos.show();
		ventana.cerrar();
	}
	
	public void abrirVentanaOrdenes(ActionEvent s) {
		llenarTablaOrdenes();
		actualizarTabla();
		ventanaTrabajos.cerrar();
		ventana.show();
	}
	
	public void abrirUnaVentanaDeUnTrabajo(ActionEvent s) {
		if(ventanaTrabajos.getTablaFabricacionesEnMarcha().getSelectedRows().length == 0) {
			return;
		}
		fabricacionTrabajando = trabajosEnLista.get(ventanaTrabajos.getTablaFabricacionesEnMarcha().getSelectedRows()[0]);
		if(fabricacionTrabajando.getEstado().equals("activo")) {
			reiniciarTablaIngredientesDeUnTrabajo();
			ventanaUnaTrabajo.show();
		}
		if(fabricacionTrabajando.getEstado().equals("completo")) {
			reiniciarTablaIngredientesDeUnTrabajo();
			this.ventanaDiaDeLlegada.show();
		}
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
			llenarTablaTrabajos();
			
		}else {
			this.ventanaTrabajos.ventanaErrorMaterialesNoSuficientes();
		}
		reiniciarTablaIngredientesDeUnTrabajo();
	}
	
	public void retrocederUnPaso(ActionEvent s) {
		if(fabricacionTrabajando.getNroPasoActual() == 1) {
			return;
		}
		fabricacionTrabajando.setNroPasoActual(fabricacionTrabajando.getNroPasoActual()-1);
		modeloFabricacion.actualizarFabricacionEnMarcha(fabricacionTrabajando);
		llenarTablaTrabajos();
		reiniciarTablaIngredientesDeUnTrabajo();
	}
	
	public void cancelarOrden(ActionEvent s) {
		int res = JOptionPane.showConfirmDialog(null, stringQuePreguntaCancelacionDeProduccion, "", JOptionPane.YES_NO_OPTION);
        switch (res) {
            case JOptionPane.YES_OPTION:
            	fabricacionTrabajando.cancelarOrden();
        		modeloFabricacion.actualizarFabricacionEnMarcha(fabricacionTrabajando);
        		llenarTablaTrabajos();
        		ventanaUnaTrabajo.cerrar();
        		reiniciarTablaIngredientesDeUnTrabajo();
        		JOptionPane.showMessageDialog(null, stringQueConfirmaCancelacionDeProduccion);
        		break;
            case JOptionPane.NO_OPTION:
            	JOptionPane.showMessageDialog(null, stringQueNoCancelaDeProduccion);
            	break;
        }
        
		
	}
	
	public void actualizarTodosLosTrabajosListosParaLosEnvios() {
		//ESTA FUNCION ES MAS PARA EL MODELO YA PARA QUE SEA USADA AL FFINAL DE CADA DIA O AL INICIO, DARA DE ALTA EL STOCK DE 
		//LOS TRABAJOS COMPLETOS CUYAS FECHAS DE LLEGADA PASEN A LA ACTUAL
		//, LITERALMENTE RECORRE TODAS LOS PROCESOS DE TRABAJO COMPLETADOS Y 
		//VERFICA SI LA FECHA ESTA LISTA PARA EL ENVIO
		List<FabricacionesDTO> todasLasOrdenesCompletas = modeloFabricacion.readAllFabricacionesCompletas();
		for(FabricacionesDTO of: todasLasOrdenesCompletas) {
			modeloFabricacion.actualizarSiLlegoFechaDeEntrega(of);
		}
	}
	
	public boolean existeMaterialSuficiente(RecetaDTO receta, int cantidadDeseada) {
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
	
	private void ingresarDias(ActionEvent s) {
		int valorIngresado = (int) ventanaDiaDeLlegada.getSpinner().getValue();
		if(valorIngresado < 0) {
			return;
		}
		modeloFabricacion.completarOrden(fabricacionTrabajando, valorIngresado);
		this.ventanaDiaDeLlegada.cerrar();
		this.actualizarTabla();
		this.reiniciarTablaTrabajos();
		llenarTablaTrabajos();
	}
	
	private void botonSeleccionarReceta(ActionEvent s) {
		recetaSeleccionado = recetasEnLista.get(this.ventanaElegirReceta.getComboBox().getSelectedIndex());
		String nombreProducto = "";
		MaestroProductoDTO producto = buscarProducto(this.ordenSeleccionado.getIdProd());
		if(producto == null) {
			nombreProducto = error;
		}else {
			nombreProducto = producto.getDescripcion();
		}
		ventanaMostrarIngredientes.getLblSolicitado().setText(nombreProducto + ", cantidad ordenada: " + this.ordenSeleccionado.getCantidad() + ".");
		
		//String texto = "";
		reiniciarTablaIngredientes();
		List<PasoDeRecetaDTO> pasos = modeloFabricacion.readAllPasosFromOneReceta(recetaSeleccionado.getIdReceta());
		for(PasoDeRecetaDTO p: pasos) {
			for(int x = 0; x < p.getPasosDTO().getMateriales().size(); x++) {
				//texto = texto + "" + p.getPasosDTO().getMateriales().get(x).getDescripcion() + " Cantidad usada: "+ (p.getPasosDTO().getCantidadUsada().get(x)*this.ordenSeleccionado.getCantidad());
				Object[] agregar = {p.getPasosDTO().getMateriales().get(x).getDescripcion(), (p.getPasosDTO().getCantidadUsada().get(x)*this.ordenSeleccionado.getCantidad())};
				ventanaMostrarIngredientes.getModelOrdenes().addRow(agregar);
			}
		}
		
		if(existeMaterialSuficiente(recetaSeleccionado, ordenSeleccionado.getCantidad())) {
			this.ventanaMostrarIngredientes.getLblMensaje().setText("Hay materiales suficientes");
		}else {
			this.ventanaMostrarIngredientes.getLblMensaje().setText("No hay materiales suficientes");
		}
		
		ventanaMostrarIngredientes.show();
	}
	
	public void reiniciarTablaIngredientes() {
		ventanaMostrarIngredientes.getModelOrdenes().setRowCount(0);
		ventanaMostrarIngredientes.getModelOrdenes().setColumnCount(0);
		ventanaMostrarIngredientes.getModelOrdenes().setColumnIdentifiers(ventanaMostrarIngredientes.getNombreColumnas());
	}
	
	public void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
        return;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
