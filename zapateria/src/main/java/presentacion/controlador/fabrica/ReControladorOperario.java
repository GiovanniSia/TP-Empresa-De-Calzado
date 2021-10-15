package presentacion.controlador.fabrica;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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
import presentacion.controlador.Controlador;
import presentacion.vista.fabrica.ReVentanaIngresarFechaDeLlegada;
import presentacion.vista.fabrica.ReVentanaSeleccionarUnaReceta;
import presentacion.vista.fabrica.ReVentanaTrabajarUnPedido;
import presentacion.vista.fabrica.ReVentanaVerFabricaciones;
import presentacion.vista.fabrica.fecha;

public class ReControladorOperario implements ActionListener {

	static final String error = "[HiperLink error]";
	static final String stringQueDescribeLosTrabajosListosPeroEstanEnEsperaParaEnviar = "En envio";
	
	static final String stringQueDescribeElPasoActualDeLosOrdenesSinEmpezar = "Sin empezar";
	static final String stringQueDescribeElEstadoDeLasOrdenesSinEmpezar = "Inactivo/Pendiente";
	
	static final String stringQuePreguntaCancelacionDeProduccion = "¿Estas seguro que lo quieres cancelar?";
	static final String stringQueConfirmaCancelacionDeProduccion = "Se a cancelado la produccion";
	static final String stringQueNoCancelaDeProduccion = "No se a cancelado";
	
	static final String mensajeHayMateriales = "Hay materiales suficientes para el paso.";
	static final String mensajeNoHayMateriales = "No hay materiales suficientes para el paso.";
	
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
	
	fecha ventanaParaCumple;
	String mesCumpleCreado;
	String anioCumpleCreado;
	String fechaDesde = "";
	String fechaHasta = "";
	String diaCumpleCreado = "";
	boolean seleccionarDesde = true;
	
	Controlador controlador;
	
	public ReControladorOperario(Controlador controlador,SucursalDTO fabrica) {
		idFabrica = fabrica.getIdSucursal();
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
		}
		
		this.controlador = controlador;
		
		modeloFabricacion = new Fabricacion(new DAOSQLFactory());
		modeloProducto = new MaestroProducto(new DAOSQLFactory());
		modeloOrden = new OrdenFabrica(new DAOSQLFactory());
		modeloStock = new Stock(new DAOSQLFactory());
		
		ventanaPrincipal = new ReVentanaVerFabricaciones();

	}
	
	public void inicializar() {
		ventanaPrincipal.getBtnTrabajarPedido().addActionListener(r->trabajarSeleccionado(r));
		ventanaPrincipal.getTextProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		ventanaPrincipal.getTextId().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		ventanaPrincipal.getTextSucursal().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		ventanaPrincipal.getTextEstado().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		ventanaPrincipal.getTextPasoActual().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTablaPorTecla();
			}
		});
		ventanaPrincipal.getBtnFechaDesde().addActionListener(r->this.seleccionarDesde(r));
		ventanaPrincipal.getBtnFechaHasta().addActionListener(r->this.seleccionarHasta(r));
		
		ventanaElegirReceta = new ReVentanaSeleccionarUnaReceta();
		ventanaElegirReceta.getComboBox().addActionListener(r->botonSeleccionarReceta(r));
		ventanaElegirReceta.getBtnTrabajar().addActionListener(r->crearTrabajo(r));
		
		ventanaUnaTrabajo = new ReVentanaTrabajarUnPedido();
		ventanaUnaTrabajo.getBtnAvanzarUnPaso().addActionListener(r->avanzarUnPaso(r));
		ventanaUnaTrabajo.getBtnCancelar().addActionListener(r->cancelarOrden(r));
		
		ventanaDiaDeLlegada = new ReVentanaIngresarFechaDeLlegada();
		ventanaDiaDeLlegada.getBtnbtnIngresarFecha().addActionListener(r->ingresarDias(r));
		
		ventanaParaCumple = fecha.getInstance();
		
		this.ventanaPrincipal.getBtnSalir().addActionListener(a -> salir(a));
		
		
		
		
		refrescarTabla();
		
	}
	
	public void mostrarVentana() {
		this.ventanaPrincipal.show();
	}
	
	public void cerrarVentana() {
		this.ventanaPrincipal.cerrar();
	}
	
	public void salir(ActionEvent a) {
		this.ventanaPrincipal.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenu();
	}
	
	
	public void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
        return;
	}
	
	private void refrescarTablaPorTecla() {
		refrescarTabla();
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
				//texto = "Pedido de la sucursal:" +  oM.getIdSucursal() + "; " + mp.getDescripcion() + " X " + oM.getCantidad();
				texto = mp.getDescripcion() + ": X " + oM.getCantidad()+" unidades";
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
			/*
			static final String stringQueDescribeElPasoActualDeLosOrdenesSinEmpezar = "Sin empezar";
			sstatic final String stringQueDescribeElEstadoDeLasOrdenesSinEmpezar = "Sin empezar";
			*/
			String paso = stringQueDescribeElPasoActualDeLosOrdenesSinEmpezar;
			String estado = stringQueDescribeElEstadoDeLasOrdenesSinEmpezar;
			Object[] agregar = 
				{o.getIdOrdenFabrica(), o.getIdSucursal(), nombreProducto, o.getFechaRequerido(), o.getCantidad(), paso, estado};
			ventanaPrincipal.getModelOrdenes().addRow(agregar);
		}
	}
	
	private void llenarTablaConTrabajos() {
		String id = ventanaPrincipal.getTextId().getText();
		String sucursal = ventanaPrincipal.getTextSucursal().getText();
		String productoText = ventanaPrincipal.getTextProducto().getText();
		
		String estadoParametro = ventanaPrincipal.getTextEstado().getText();
		String pasoActParametro = ventanaPrincipal.getTextPasoActual().getText();
		
		List<FabricacionesDTO> trabajos = modeloFabricacion.readAllFabricacionesEnMarcha(productoText, sucursal, id, fechaDesde, fechaHasta);
		trabajosEnLista = new ArrayList<FabricacionesDTO>();
		List<OrdenFabricaDTO> todasLasOrdenes = modeloOrden.readAll();
		
		OrdenFabricaDTO orden = null;
		for(FabricacionesDTO f: trabajos) {
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
					
					if(f.getEstado().toLowerCase().matches(".*"+estadoParametro.toLowerCase()+".*") && pasoActualString.toLowerCase().matches(".*"+pasoActParametro.toLowerCase()+".*")) {
						trabajosEnLista.add(f);
						Object[] agregar = {orden.getIdOrdenFabrica() ,orden.getIdSucursal(), nombreProducto, orden.getFechaRequerido(), orden.getCantidad(), pasoActualString , f.getEstado(), fechaCompletado, diasEnvio};
						ventanaPrincipal.getModelOrdenes().addRow(agregar);
						
						ventanaPrincipal.getTablaFabricacionesEnMarcha().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
						    @Override
						    public Component getTableCellRendererComponent(JTable table,
						            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

						        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

						        String status = (String)table.getModel().getValueAt(row, 6);
						        if ("activo".equals(status)) {
						           setBackground(Color.ORANGE);
						           setForeground(Color.BLACK);
						        } else if ("completo".equals(status)){
						        	setBackground(Color.green);
						        	setForeground(Color.BLACK);
						        } else {
						        	setBackground(table.getBackground());
						        	setForeground(Color.BLACK);
						        }
						        return this;
						    }   
						});
					}
					
				}
			}
		}
		
	}
	
	private void recuperarListaDeOrdenesPendientes() {
		String id = ventanaPrincipal.getTextId().getText();
		String sucursal = ventanaPrincipal.getTextSucursal().getText();
		String producto = ventanaPrincipal.getTextProducto().getText();
		
		String estadoParametro = ventanaPrincipal.getTextEstado().getText();
		String pasoActParametro = ventanaPrincipal.getTextPasoActual().getText();
		/*
		ordenesEnLista = new ArrayList<OrdenFabricaDTO>();
		for(OrdenFabricaDTO of: modeloFabricacion.readAllOrdenesSinTrabajar()) {
			if((id.equals("") || id.equals(of.getIdOrdenFabrica()+"")) && 
					(sucursal.equals("") || sucursal.equals(of.getIdsucursal()+"")) && 
					()
					) {
				
			}
		}*/
		ordenesEnLista  = new ArrayList<OrdenFabricaDTO>();
		List<OrdenFabricaDTO> listaAux = new ArrayList<OrdenFabricaDTO>();
		if(stringQueDescribeElPasoActualDeLosOrdenesSinEmpezar.toLowerCase().matches(".*"+pasoActParametro.toLowerCase()+".*") && stringQueDescribeElEstadoDeLasOrdenesSinEmpezar.toLowerCase().matches(".*"+estadoParametro.toLowerCase()+".*")) {
			ordenesEnLista = modeloFabricacion.readAllOrdenesSinTrabajar(producto,sucursal,id,fechaDesde,fechaHasta);
			System.out.println(ordenesEnLista.size());
		}
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
		this.ventanaElegirReceta.getComboBox().setSelectedIndex(-1);
	}
	
	private void botonSeleccionarReceta(ActionEvent s) {
		String nombreProducto = "";
		MaestroProductoDTO producto = buscarProducto(this.ordenSeleccionado.getIdProd());
		if(producto == null) {
			nombreProducto = error;
		}else {
			nombreProducto = producto.getDescripcion();
		}
		System.out.println(this.ventanaElegirReceta.getComboBox().getSelectedIndex() + " El combo box");
		if(this.ventanaElegirReceta.getComboBox().getSelectedIndex() == -1) {
			
			ventanaElegirReceta.getLblSolicitado().setText(nombreProducto+"; cantidad ordenada: "+this.ordenSeleccionado.getCantidad()+".");
			ventanaElegirReceta.getLblSucursal().setText("sucursal: "+ordenSeleccionado.getIdSucursal());
			ventanaElegirReceta.getLblIdPedido().setText("IdPedido: "+ordenSeleccionado.getIdOrdenFabrica());
			ventanaElegirReceta.getLblFecha().setText("Fecha requerido: "+ordenSeleccionado.getFechaRequerido());
			this.ventanaElegirReceta.getLblMensaje().setText("No es posible trabajar, no hay receta");
			return;
		}
		recetaSeleccionado = recetasEnLista.get(this.ventanaElegirReceta.getComboBox().getSelectedIndex());
		
		ventanaElegirReceta.getLblSolicitado().setText(nombreProducto+"; cantidad ordenada: "+this.ordenSeleccionado.getCantidad()+".");
		ventanaElegirReceta.getLblSucursal().setText("sucursal: "+ordenSeleccionado.getIdSucursal());
		ventanaElegirReceta.getLblIdPedido().setText("IdPedido: "+ordenSeleccionado.getIdOrdenFabrica());
		ventanaElegirReceta.getLblFecha().setText("Fecha requerido: "+ordenSeleccionado.getFechaRequerido());
		//String texto = "";
		reiniciarTablaIngredientes();
		List<PasoDeRecetaDTO> pasos = modeloFabricacion.readAllPasosFromOneReceta(recetaSeleccionado.getIdReceta());
		for(PasoDeRecetaDTO p: pasos) {
			for(int x = 0; x < p.getPasosDTO().getMateriales().size(); x++) {
				//texto = texto + "" + p.getPasosDTO().getMateriales().get(x).getDescripcion() + " Cantidad usada: "+ (p.getPasosDTO().getCantidadUsada().get(x)*this.ordenSeleccionado.getCantidad());
				
				String desc = p.getPasosDTO().getMateriales().get(x).getDescripcion();
				//String cantUsada = (p.getPasosDTO().getCantidadUsada().get(x)*this.ordenSeleccionado.getCantidad() + " "+p.getPasosDTO().getMateriales().get(x).getUnidadMedida());
				String cantUsarXunidad = p.getPasosDTO().getCantidadUsada().get(x)+" ";
				String cantUsada = (p.getPasosDTO().getCantidadUsada().get(x)*this.ordenSeleccionado.getCantidad())+"";
				String cantActual = this.cantidadEnStock(p.getPasosDTO().getMateriales().get(x))+"";
				String unidadMedida = p.getPasosDTO().getMateriales().get(x).getUnidadMedida();
				//
				Object[] agregar = {desc, cantUsarXunidad, cantUsada, cantActual, unidadMedida};
				ventanaElegirReceta.getModelOrdenes().addRow(agregar);
			}
		}
		
		if(existeMaterialSuficiente(recetaSeleccionado, ordenSeleccionado.getCantidad())) {
			this.ventanaElegirReceta.getLblMensaje().setText("Hay materiales suficientes");
		}else {
			this.ventanaElegirReceta.getLblMensaje().setText("No hay materiales suficientes");
		}
		
		ventanaElegirReceta.getTablaOrdenesPendientes().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                String status = (String)table.getModel().getValueAt(row, 2);
                int usar = Integer.valueOf(status);
                status = (String)table.getModel().getValueAt(row, 3);
                int tengo = Integer.valueOf(status);
                if (tengo<usar) {
                    setBackground(Color.pink);
                    //setForeground(Color.WHITE);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }
        });
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
		
		ventanaUnaTrabajo.getLblIdPedido().setText("Id orden: "+of.getIdOrdenFabrica());
		ventanaUnaTrabajo.getLblSucursal().setText("Id sucursal: "+of.getIdSucursal());
		ventanaUnaTrabajo.getLblFechaRequerida().setText("Fecha requerida: "+of.getFechaRequerido());
		String pasoActual = "" + p.getPasosDTO().getDescripcion()+" ("+this.fabricacionTrabajando.getNroPasoActual()+" de "+modeloFabricacion.readCantPasosReceta(this.fabricacionTrabajando.getIdReceta())+")";
		ventanaUnaTrabajo.getLblPasos().setText(pasoActual);
		boolean hayMateriales = true;
		for(int x = 0; x<p.getPasosDTO().getMateriales().size(); x++) {
			//{ "Material", "Cantidad a usar", "Cantidad en stock", "Unidad medida"};
			String desc = p.getPasosDTO().getMateriales().get(x).getDescripcion();
			int cantUsar = (p.getPasosDTO().getCantidadUsada().get(x)*of.getCantidad());
			String cantUsada = cantUsar+"";
			int cantDisponible = this.cantidadEnStock(p.getPasosDTO().getMateriales().get(x));
			String cantActual = cantDisponible+"";
			String unidadMedida = p.getPasosDTO().getMateriales().get(x).getUnidadMedida();
			Object[] agregar = {desc, cantUsada, cantActual, unidadMedida};
			ventanaUnaTrabajo.getModelOrdenes().addRow(agregar);
			//Object[] agregar = {p.getPasosDTO().getMateriales().get(x).getDescripcion(), (p.getPasosDTO().getCantidadUsada().get(x)*of.getCantidad())};
			//ventanaUnaTrabajo.getModelOrdenes().addRow(agregar);
			hayMateriales = hayMateriales && cantUsar <= cantDisponible;
			ventanaUnaTrabajo.getTablaIngredientes().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                    String status = (String)table.getModel().getValueAt(row, 1);
                    int usar = Integer.valueOf(status);
                    status = (String)table.getModel().getValueAt(row, 2);
                    int tengo = Integer.valueOf(status);
                    if (tengo<usar) {
                        setBackground(Color.pink);
                        //setForeground(Color.WHITE);
                    } else {
                        setBackground(table.getBackground());
                        setForeground(table.getForeground());
                    }
                    return this;
                }
            });
			
		}
		if(!hayMateriales) {
			ventanaUnaTrabajo.getLblMensaje().setText(mensajeNoHayMateriales);
		}else {
			ventanaUnaTrabajo.getLblMensaje().setText(mensajeHayMateriales);
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
	
	// FECHAS
	
	private void seleccionarDesde(ActionEvent a) {
		seleccionarDesde = true;
		seleccionarAnio();
	}
	
	private void seleccionarHasta(ActionEvent a) {
		seleccionarDesde = false;
		seleccionarAnio();
	}
	
	private void seleccionarAnio() {
		ventanaParaCumple.getLblFecha().setText("Seleccione año");
		ventanaParaCumple.getComboBox().removeAllItems();
		DefaultComboBoxModel valores = new DefaultComboBoxModel();
		ArrayList<String> val = new ArrayList<String>();
		for(int x = 0; x < 122-100; x++) {
			val.add(Integer.toString(x + 1900 + 100));
		}
		valores.addAll(val);
		ventanaParaCumple.getComboBox().setModel(valores);
		ventanaParaCumple.getComboBox().setSelectedIndex(1);
		
		for(ActionListener i: ventanaParaCumple.getBtnElegir().getActionListeners()) {
			ventanaParaCumple.getBtnElegir().removeActionListener(i);
		}
		
		ventanaParaCumple.getBtnElegir().addActionListener(e->elegirAnio(e));
		ventanaParaCumple.mostrarVentana();
	}
	
	private void elegirAnio(ActionEvent a) {
		anioCumpleCreado = ventanaParaCumple.getComboBox().getSelectedItem().toString();
		System.out.println(anioCumpleCreado);
		
		seleccionarMes();
	}
	
	private void seleccionarMes() {
		ventanaParaCumple.getLblFecha().setText("Seleccione mes");
		ventanaParaCumple.getComboBox().removeAllItems();
		DefaultComboBoxModel valores = new DefaultComboBoxModel();
		ventanaParaCumple.getComboBox().setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		ventanaParaCumple.getComboBox().setSelectedIndex(0);
		
		for(ActionListener i: ventanaParaCumple.getBtnElegir().getActionListeners()) {
			ventanaParaCumple.getBtnElegir().removeActionListener(i);
		}
		ventanaParaCumple.getBtnElegir().addActionListener(e->elegirMes(e));
		
		ventanaParaCumple.mostrarVentana();
	}
	
	private void elegirMes(ActionEvent a) {
		mesCumpleCreado = Integer.toString(ventanaParaCumple.getComboBox().getSelectedIndex()+1);
		System.out.println(anioCumpleCreado);
		seleccionarDia();
	}
	
	private void seleccionarDia() {
		ventanaParaCumple.getLblFecha().setText("Seleccione dia");
		ventanaParaCumple.getComboBox().removeAllItems();
		
		int diaMaximo = 0;
		switch(mesCumpleCreado) {
			case("1"):
				diaMaximo = 31;
				break;
			case("2"):
				//28 o 29
				if(anioBisiesto(Integer.parseInt(anioCumpleCreado))) {
					diaMaximo = 29;
				}else {
					diaMaximo = 28;
				}
				break;
			case("3"):
				diaMaximo = 31;
				break;
			case("4"):
				diaMaximo = 30;
				break;
			case("5"):
				diaMaximo = 31;
				break;
			case("6"):
				diaMaximo = 30;
				break;
			case("7"):
				diaMaximo = 31;
				break;
			case("8"):
				diaMaximo = 31;
				break;
			case("9"):
				diaMaximo = 30;
				break;
			case("10"):
				diaMaximo = 31;
				break;
			case("11"):
				diaMaximo = 30;
				break;
			case("12"):
				diaMaximo = 31;
				break;
		}
		DefaultComboBoxModel valores = new DefaultComboBoxModel();
		ArrayList<String> val = new ArrayList<String>();
		for(int x = 1; x <= diaMaximo; x++) {
			val.add(Integer.toString(x));
		}
		valores.addAll(val);
		ventanaParaCumple.getComboBox().setModel(valores);
		ventanaParaCumple.getComboBox().setSelectedIndex(0);
		
		for(ActionListener i: ventanaParaCumple.getBtnElegir().getActionListeners()) {
			ventanaParaCumple.getBtnElegir().removeActionListener(i);
		}
		ventanaParaCumple.getBtnElegir().addActionListener(e->elegirDia(e));
		
		ventanaParaCumple.mostrarVentana();
	}
	
	private void elegirDia(ActionEvent a) {
		diaCumpleCreado = Integer.toString(ventanaParaCumple.getComboBox().getSelectedIndex()+1);
		System.out.println(anioCumpleCreado + " " + mesCumpleCreado + " " + diaCumpleCreado);
		ventanaParaCumple.cerrar();
		if(this.seleccionarDesde) {
			fechaDesde = formarFecha();
			ventanaPrincipal.getLblFechaDesde().setText(fechaDesde);
		}else {
			fechaHasta = formarFecha();
			ventanaPrincipal.getLblFechaHasta().setText(fechaHasta);
		}
		this.refrescarTabla();
	}
	
	private boolean anioBisiesto(int anio) {
		if(anio % 4 == 0) {
			if(anio % 100 == 0) {
				if(anio % 400 == 0) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	private String formarFecha() {
		if(diaCumpleCreado.equals("")) {
			return "1000-10-10";
		}
		return anioCumpleCreado+"-"+mesCumpleCreado+"-"+diaCumpleCreado;
	}
	
	private int cantidadEnStock(MaestroProductoDTO producto) {
		List<StockDTO> todoElStock = modeloStock.readAll();
		int cantidadTotalDisponible = 0;
		for(StockDTO s: todoElStock) {
			if(s.getIdProducto() == producto.getIdMaestroProducto() && s.getIdSucursal() == this.idFabrica) {
				cantidadTotalDisponible = cantidadTotalDisponible + s.getStockDisponible();
			}
		}
		return cantidadTotalDisponible;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
