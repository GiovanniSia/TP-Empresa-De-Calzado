package presentacion.controlador.fabrica;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import dto.EmpleadoDTO;
import dto.FabricacionesDTO;
import dto.HistorialPasoDTO;
import dto.MaestroProductoDTO;
import dto.OrdenFabricaDTO;
import dto.PasoDeRecetaDTO;
import dto.RecetaDTO;
import dto.StockDTO;
import dto.SucursalDTO;
import inicioSesion.empleadoProperties;
import modelo.Fabricacion;
import modelo.HistorialPaso;
import modelo.MaestroProducto;
import modelo.OrdenFabrica;
import modelo.PedidosPendientes;
import modelo.Stock;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.fabrica.ReVentanaIngresarFechaDeLlegada;
import presentacion.vista.fabrica.ReVentanaSeleccionarUnaReceta;
import presentacion.vista.fabrica.ReVentanaTrabajarUnPedido;
import presentacion.vista.fabrica.ReVentanaVerFabricaciones;
import presentacion.vista.fabrica.VentanaIngresarMotivoCancelacion;
import presentacion.vista.fabrica.fecha;
import modelo.generarOrdenesFabricacion;
public class ReControladorOperario implements ActionListener {
	
	static EmpleadoDTO empleadoDeMuestra;
	
	public void obtenerDatosPropertiesEmpleado() {
		try {
			empleadoProperties empleadoProp = empleadoProperties.getInstance();
			int idEmpleado = Integer.parseInt(empleadoProp.getValue("IdEmpleado"));
			String CUIL = empleadoProp.getValue("CUIL");
			String nombre = empleadoProp.getValue("Nombre");
			String apellido = empleadoProp.getValue("Apellido");
			String correo = empleadoProp.getValue("CorreoElectronico");
			String tipoEmpleado = empleadoProp.getValue("TipoEmpleado");
			String contra = "";
			empleadoDeMuestra = new EmpleadoDTO(idEmpleado, CUIL, nombre, apellido, correo, tipoEmpleado, contra);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static final String error = "[HiperLink error]";
	static final String stringQueDescribeLosTrabajosListosPeroEstanEnEsperaParaEnviar = "En envio";
	
	static final String stringQueDescribeElPasoActualDeLosOrdenesSinEmpezar = "Sin empezar";
	static final String stringQueDescribeElEstadoDeLasOrdenesSinEmpezar = "Inactivo/Pendiente";
	
	static final String stringQueDescribeElEstadoDeLasOrdenesSiendoTrabajadas = "Activo/En proceso";
	static final String stringQueDescribeElEstadoDeLasOrdenesCanceladas = "Cancelada/Cancelado";
	
	static final String stringQuePreguntaCancelacionDeProduccion = "¿Estas seguro que lo quieres cancelar?";
	static final String stringQueConfirmaCancelacionDeProduccion = "Se a cancelado la produccion";
	static final String stringQueNoCancelaDeProduccion = "No se a cancelado";
	
	static final String mensajeHayMateriales = "Hay materiales suficientes para el paso.";
	static final String mensajeNoHayMateriales = "No hay materiales suficientes para el paso.";
	
	private ReVentanaVerFabricaciones ventanaPrincipal;
	List<OrdenFabricaDTO> ordenesEnLista;
	List<FabricacionesDTO> trabajosEnLista;
	List<RecetaDTO> recetasEnLista;
	
	List<FabricacionesDTO> trabajosCanceladosEnLista;
	
	
	OrdenFabricaDTO ordenSeleccionado;
	RecetaDTO recetaSeleccionado;
	FabricacionesDTO fabricacionTrabajando;
	ReVentanaSeleccionarUnaReceta ventanaElegirReceta;
	ReVentanaTrabajarUnPedido ventanaUnaTrabajo;
	
	VentanaIngresarMotivoCancelacion ventanaParaCancelacion;
	int idFabrica;
	ReVentanaIngresarFechaDeLlegada ventanaDiaDeLlegada;
	
	Fabricacion modeloFabricacion;
	MaestroProducto modeloProducto;
	OrdenFabrica modeloOrden;
	Stock modeloStock;
	HistorialPaso historialPaso;
	
	fecha ventanaParaCumple;
	String mesCumpleCreado;
	String anioCumpleCreado;
	String fechaDesde = "";
	String fechaHasta = "";
	String diaCumpleCreado = "";
	boolean seleccionarDesde = true;
	
	Controlador controlador;
	EmpleadoDTO empleado;
	SucursalDTO fabrica;
	//public ReControladorOperario(Controlador controlador,SucursalDTO fabrica, EmpleadoDTO empleado) {
	public ReControladorOperario(Controlador controlador,SucursalDTO fabrica) {
		obtenerDatosPropertiesEmpleado();
		
		this.empleado = empleadoDeMuestra;
		this.fabrica = fabrica;
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
		historialPaso = new HistorialPaso(new DAOSQLFactory());
		
		ventanaPrincipal = new ReVentanaVerFabricaciones();
		ventanaPrincipal.getBtnVerHistorialPasos().addActionListener(r-> verHistorialPasos(r));
		this.ventanaPrincipal.getBtnVerMateriales().addActionListener(r->verMateriales(r));

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
		ventanaPrincipal.getChckbxCancelados().addMouseListener((MouseListener) new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
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
		ventanaUnaTrabajo.getBtnCancelar().addActionListener(r->abrirVentanaParaPreguntarCancelarOrden(r));
		ventanaUnaTrabajo.getBtnSalirVentana().addActionListener(r->cerrarVentanaUnTrabajo(r));
		
		ventanaDiaDeLlegada = new ReVentanaIngresarFechaDeLlegada();
		ventanaDiaDeLlegada.getBtnbtnIngresarFecha().addActionListener(r->ingresarDias(r));
		
		ventanaParaCumple = fecha.getInstance();
		
		this.ventanaPrincipal.getBtnSalir().addActionListener(a -> salir(a));
		
		ventanaParaCancelacion = new VentanaIngresarMotivoCancelacion();
		ventanaParaCancelacion.getBtnCancelar().addActionListener(a -> cancelarOrden(a));
		ventanaParaCancelacion.getBtnNoCancelar().addActionListener(a -> noCancelarOrden(a));
		ventanaParaCancelacion.getComboBoxMotivo().addItem("Error de la maquina");
		ventanaParaCancelacion.getComboBoxMotivo().addItem("Error con los materiales");
		ventanaParaCancelacion.getComboBoxMotivo().addItem("Falta de materiales");
		ventanaParaCancelacion.getComboBoxMotivo().addItem("Accidente en la fabrica");
		ventanaParaCancelacion.getComboBoxMotivo().addItem("Falla electrica");
		ventanaParaCancelacion.getComboBoxMotivo().addItem("Accidente con el operario");
		ventanaParaCancelacion.getComboBoxMotivo().addItem("Otro");
		refrescarTabla();
		if(this.empleado.getTipoEmpleado().toLowerCase().equals("operario de fabrica")) {
			this.ventanaPrincipal.getBtnVerHistorialPasos().setVisible(false);
			this.ventanaPrincipal.getLblHistorial().setVisible(false);
		}
		if(this.empleado.getTipoEmpleado().toLowerCase().equals("gerente")) {
			this.ventanaPrincipal.getBtnVerMateriales().setVisible(false);
			this.ventanaPrincipal.getLblMateriales().setVisible(false);
		}
		
		this.ventanaPrincipal.getTablaFabricacionesEnMarcha().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel rowSMProducto = this.ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectionModel();
        rowSMProducto.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int filaSeleccionada = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRow();
                if (filaSeleccionada == -1)
                    return;
                actualizarColorSeleccionTabla();
            }

        });
        
        
	}

	private void verHistorialPasos(ActionEvent r) {
		ventanaPrincipal.cerrar();
		/*
		SucursalDTO suc = new SucursalDTO(1, "3123314", "Uruguay", "134", "BsAs", "Tortuguitas", "Argentina", "1669",
				"Una mas");
				*/
		ControladorHistorialPasos con = new ControladorHistorialPasos(this.controlador, this.fabrica);
		con.inicializar();
	}
	
	public void mostrarVentana() {
		this.ventanaPrincipal.show();
	}
	
	public void cerrarVentana() {
		this.ventanaPrincipal.cerrar();
	}
	
	public void salir(ActionEvent a) {
		this.ventanaPrincipal.cerrar();
		this.controlador.mostrarVentanaMenu();
	}
	
	private void cerrarVentanaUnTrabajo(ActionEvent a) {
		this.ventanaUnaTrabajo.cerrar();
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
		if(ventanaPrincipal.getChckbxCancelados().isSelected()) {
			llenarTablaConTrabajosCancelados();
		}
	}
	
	public void trabajarSeleccionado(ActionEvent s) {
		int[] filasSeleccionadas = ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		
		if(ordenesEnLista.size() > filasSeleccionadas[0]) {	//Escogio una orden sin trabajar
			if(this.empleado.getTipoEmpleado().toLowerCase().equals("gerente")) {
				return;
			}
			OrdenFabricaDTO ordenATrabajar = ordenesEnLista.get(filasSeleccionadas[0]);
			ordenSeleccionado = ordenATrabajar;
			inicializarComboBoxRecetas(filasSeleccionadas[0]);
			this.ventanaElegirReceta.show();
		}else if(ordenesEnLista.size() + this.trabajosEnLista.size() > filasSeleccionadas[0]){
			//SELECCIONO UNA ORDEN YA EN MARCHA
			fabricacionTrabajando = trabajosEnLista.get(ventanaPrincipal.getTablaFabricacionesEnMarcha().getSelectedRows()[0]-ordenesEnLista.size());
			if(fabricacionTrabajando.getEstado().equals("activo")) {
				if(this.empleado.getTipoEmpleado().toLowerCase().equals("gerente")) {
					return;
				}
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
				if(this.empleado.getTipoEmpleado().toLowerCase().equals("operario de fabrica")) {
					return;
				}
				this.ventanaDiaDeLlegada.show();
			}
			
		}else {
			System.out.println("Se elegio un pedido cancelado");
		}
		
	}
	
	public void crearTrabajo(ActionEvent s) {
		if(this.ventanaElegirReceta.getComboBox().getSelectedIndex() == -1) {
			return;
		}
		recetaSeleccionado = recetasEnLista.get(this.ventanaElegirReceta.getComboBox().getSelectedIndex());
		FabricacionesDTO fabricacion = new FabricacionesDTO(0, ordenSeleccionado.getIdOrdenFabrica(), recetaSeleccionado.getIdReceta(), 1, "activo");
		modeloFabricacion.insertFabricacionEnMarcha(fabricacion);
		insertarEnElHistorial(ordenSeleccionado.getIdOrdenFabrica(), 
				empleado.getIdEmpleado(),
				empleado.getApellido()+", "+empleado.getNombre(),
				"Pasado a produccion",
				"Receta nro: "+recetaSeleccionado.getIdReceta()+", "+recetaSeleccionado.getDescripcion(), 
				"");
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
			Double cantidadADescontar = 0.0;
			Double restante = 0.0;
			for(MaestroProductoDTO mp: pasoActual.getPasosDTO().getMateriales()) {
				cantidadADescontar = pasoActual.getPasosDTO().getCantidadUsada().get(cont)*ordenTra.getCantidad();
				for(StockDTO ss: modeloStock.readAll()) {
					if(ss.getIdProducto() == mp.getIdMaestroProducto() && ss.getIdSucursal() == this.idFabrica) {
						restante = ss.getStockDisponible() - cantidadADescontar;
						if(restante < 0){
							cantidadADescontar = -restante;
							restante = 0.0;
						}
						modeloFabricacion.actuaizarCantidadStockDeUnProductoEnUnaSucursal(restante, ss.getIdStock());
						System.out.println("Producto: "+mp.getDescripcion());
						System.out.println("cantidad de stock disp: "+ss.getStockDisponible()+"\nPunto de rep minimo: "+mp.getPuntoRepositorio());
						if(generarOrdenesFabricacion.contarStockDeUnProductoEnUnaSucursal(this.fabrica.getIdSucursal(),mp.getIdMaestroProducto())<= mp.getPuntoRepositorio()) {
							System.out.println("Se deberia generar el pedido");
							PedidosPendientes.generarPedidoAutomatico(1, mp);
						}
						
					}
				}
				cont++;
			}
			System.out.println("-------------------------------------------");
			this.insertarEnElHistorial(ordenTra.getIdOrdenFabrica(), 
					this.empleado.getIdEmpleado(),
					this.empleado.getApellido()+", "+this.empleado.getNombre(),
					"Paso completado: "+pasoActual.getPasosDTO().getDescripcion()+": "+fabricacionTrabajando.getNroPasoActual()+" de "+modeloFabricacion.readAllPasosFromOneReceta(fabricacionTrabajando.getIdReceta()).size(),
							"", "");
			
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
				this.insertarEnElHistorial(ordenTra.getIdOrdenFabrica(), 
						this.empleado.getIdEmpleado(),
						this.empleado.getApellido()+", "+this.empleado.getNombre(),
						"Se completo la orden",
								"",
								"");
				
				fabricacionTrabajando.completarOrden();
				modeloFabricacion.actualizarFabricacionEnMarcha(fabricacionTrabajando);
				if(this.empleado.getTipoEmpleado().toLowerCase().equals("supervisor de fabrica")) {
					this.ventanaDiaDeLlegada.show();
				}
				this.ventanaUnaTrabajo.cerrar();
				mostrarMensajeEmergente("Se completo el ultimo paso de fabricacion.");
			}
			refrescarTabla();
			
		}else {
			this.ventanaPrincipal.ventanaErrorMaterialesNoSuficientes();
		}
		reiniciarTablaIngredientesDeUnTrabajo();
	}
	
	private void insertarEnElHistorial(int idOrdenFabrica, int idEmpleado, String apellidoNombre, String pasoCompletado, String descripcion, String tipoCancelacion) {
		HistorialPasoDTO pasoHecho = new HistorialPasoDTO(0,
				idOrdenFabrica,
				idEmpleado,
				apellidoNombre,
				pasoCompletado,
				descripcion,
				tipoCancelacion);
		this.historialPaso.insert(pasoHecho);
	}
	
	public void abrirVentanaParaPreguntarCancelarOrden(ActionEvent s) {
		int res = JOptionPane.showConfirmDialog(null, stringQuePreguntaCancelacionDeProduccion, "", JOptionPane.YES_NO_OPTION);
        switch (res) {
            case JOptionPane.YES_OPTION:
            	this.ventanaParaCancelacion.getTextPane().setText("");
            	this.ventanaParaCancelacion.mostrarVentana();
        		
        		break;
            case JOptionPane.NO_OPTION:
            	JOptionPane.showMessageDialog(null, stringQueNoCancelaDeProduccion);
            	break;
        }
	}
	
	private void cancelarOrden(ActionEvent s) {
		String explicacion = this.ventanaParaCancelacion.getTextPane().getText();
		if(explicacion.length() < 10) {
			this.mostrarMensajeEmergente("Ingrese al menos 10 letras.");
			return;
		}
		fabricacionTrabajando.cancelarOrden();
		modeloFabricacion.actualizarFabricacionEnMarcha(fabricacionTrabajando);
		this.refrescarTabla();
		ventanaUnaTrabajo.cerrar();
		reiniciarTablaIngredientesDeUnTrabajo();
		JOptionPane.showMessageDialog(null, stringQueConfirmaCancelacionDeProduccion);
		
		PasoDeRecetaDTO pasoActual = getPasoActual();
		
		
		insertarEnElHistorial(fabricacionTrabajando.getIdOrdenFabrica(), this.empleado.getIdEmpleado(), 
				empleado.getApellido()+", "+empleado.getNombre(), 
				"Cancelacion: "+pasoActual.getPasosDTO().getDescripcion()+": "+fabricacionTrabajando.getNroPasoActual()+" de "+modeloFabricacion.readAllPasosFromOneReceta(fabricacionTrabajando.getIdReceta()).size()
				,explicacion, (String) ventanaParaCancelacion.getComboBoxMotivo().getSelectedItem());//FALTA METER UNA DESCRICION DE PORQUE SE CANCELO
		
		this.ventanaParaCancelacion.cerrar();
	}
	
	private void noCancelarOrden(ActionEvent s) {
		this.ventanaParaCancelacion.cerrar();
	}
	
	private void ingresarDias(ActionEvent s) {
		int valorIngresado = (int) ventanaDiaDeLlegada.getSpinner().getValue();
		if(valorIngresado < 0) {
			return;
		}
		this.insertarEnElHistorial(this.getOrdenDeFabricacionDelTrabajoActual().getIdOrdenFabrica(), 
				this.empleado.getIdEmpleado(),
				this.empleado.getApellido()+", "+this.empleado.getNombre(),
				"Se ingreso una fecha de llegada a la orden: ",
						"Dias para la que llegara a la sucursal: "+valorIngresado,
						"");
		
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
					
					String estadoEnTabla = f.getEstado();
					if(f.getEstado().equals("activo")) {
						estadoEnTabla = stringQueDescribeElEstadoDeLasOrdenesSiendoTrabajadas;
					}
					
					if(estadoEnTabla.toLowerCase().matches(".*"+estadoParametro.toLowerCase()+".*") && pasoActualString.toLowerCase().matches(".*"+pasoActParametro.toLowerCase()+".*")) {
						trabajosEnLista.add(f);
						Object[] agregar = {orden.getIdOrdenFabrica() ,orden.getIdSucursal(), nombreProducto, orden.getFechaRequerido(), orden.getCantidad(), pasoActualString , estadoEnTabla, fechaCompletado, diasEnvio};
						ventanaPrincipal.getModelOrdenes().addRow(agregar);
						
						ventanaPrincipal.getTablaFabricacionesEnMarcha().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
						    /**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							@Override
						    public Component getTableCellRendererComponent(JTable table,
						            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

						        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

						        String status = (String)table.getModel().getValueAt(row, 6);
						        if (stringQueDescribeElEstadoDeLasOrdenesSiendoTrabajadas.equals(status)) {
						           setBackground(Color.ORANGE);
						           setForeground(Color.BLACK);
						        } else if ("completo".equals(status)){
						        	setBackground(Color.green);
						        	setForeground(Color.BLACK);
						        } else if (stringQueDescribeElEstadoDeLasOrdenesCanceladas.equals(status) ){
						        	setBackground(Color.red);
						        	setForeground(Color.WHITE);
						        }else {
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
	
	private void llenarTablaConTrabajosCancelados() {
		String id = ventanaPrincipal.getTextId().getText();
		String sucursal = ventanaPrincipal.getTextSucursal().getText();
		String productoText = ventanaPrincipal.getTextProducto().getText();
		
		String estadoParametro = ventanaPrincipal.getTextEstado().getText();
		String pasoActParametro = ventanaPrincipal.getTextPasoActual().getText();
		List<FabricacionesDTO> tabajosCancelados = modeloFabricacion.readAllFabricacionesCanceladas(productoText, sucursal, id, fechaDesde, fechaHasta);
		trabajosCanceladosEnLista = new ArrayList<FabricacionesDTO>();
		String estadoEnTabla = stringQueDescribeElEstadoDeLasOrdenesCanceladas;
		
		if(estadoEnTabla.toLowerCase().matches(".*"+estadoParametro.toLowerCase()+".*")) {
			for(FabricacionesDTO f: tabajosCancelados) {
				String pasoActualString = modeloFabricacion.readAllPasosFromOneReceta(f.getIdReceta()).get(f.getNroPasoActual()-1).getPasosDTO().getDescripcion();
				if(pasoActualString.toLowerCase().matches(".*"+pasoActParametro.toLowerCase()+".*")){
					trabajosCanceladosEnLista.add(f);
					List<OrdenFabricaDTO> todasLasOrdenes = modeloOrden.readAll();
					for(OrdenFabricaDTO of: todasLasOrdenes) {
						if(of.getIdOrdenFabrica() == f.getIdOrdenFabrica()) {
							OrdenFabricaDTO orden = of;
							MaestroProductoDTO producto = buscarProducto(orden.getIdProd());
							String nombreProducto = "";
							if(producto == null) {
								nombreProducto = error;
							}else {
								nombreProducto = producto.getDescripcion();
							}
							Object[] agregar = {f.getIdOrdenFabrica() ,orden.getIdSucursal(), nombreProducto, orden.getFechaRequerido(), orden.getCantidad(), pasoActualString , estadoEnTabla, "", ""};
							ventanaPrincipal.getModelOrdenes().addRow(agregar);
							
							ventanaPrincipal.getTablaFabricacionesEnMarcha().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
							    /**
								 * 
								 */
								private static final long serialVersionUID = 1L;

								@Override
							    public Component getTableCellRendererComponent(JTable table,
							            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

							        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

							        String status = (String)table.getModel().getValueAt(row, 6);
							        if (stringQueDescribeElEstadoDeLasOrdenesSiendoTrabajadas.equals(status)) {
							           setBackground(Color.ORANGE);
							           setForeground(Color.BLACK);
							        } else if ("completo".equals(status)){
							        	setBackground(Color.green);
							        	setForeground(Color.BLACK);
							        } else if (stringQueDescribeElEstadoDeLasOrdenesCanceladas.equals(status) ){
							        	setBackground(Color.red);
							        	setForeground(Color.WHITE);
							        }else {
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
		
		/*
		if(f.getNroPasoActual() <= modeloFabricacion.readAllPasosFromOneReceta(f.getIdReceta()).size()) {
			pasoActualString = descrPaso + ": " + f.getNroPasoActual() + " de " + 
					modeloFabricacion.readAllPasosFromOneReceta(f.getIdReceta()).size();
		}else {
			pasoActualString = stringQueDescribeLosTrabajosListosPeroEstanEnEsperaParaEnviar;
		}&& pasoActualString.toLowerCase().matches(".*"+pasoActParametro.toLowerCase()+".*")
		*/
		
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
//		List<OrdenFabricaDTO> listaAux = new ArrayList<OrdenFabricaDTO>();
		if(stringQueDescribeElPasoActualDeLosOrdenesSinEmpezar.toLowerCase().matches(".*"+pasoActParametro.toLowerCase()+".*") && stringQueDescribeElEstadoDeLasOrdenesSinEmpezar.toLowerCase().matches(".*"+estadoParametro.toLowerCase()+".*")) {
			ordenesEnLista = modeloFabricacion.readAllOrdenesSinTrabajar(producto,sucursal,id,fechaDesde,fechaHasta);
//			System.out.println(ordenesEnLista.size());
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
				if(modeloFabricacion.isRecetaDisponible(r) && r.getEstado().equals("Activo")) {
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
//		System.out.println(this.ventanaElegirReceta.getComboBox().getSelectedIndex() + " El combo box");
		if(this.ventanaElegirReceta.getComboBox().getSelectedIndex() == -1) {
			
			ventanaElegirReceta.getLblSolicitado().setText(nombreProducto+"; cantidad ordenada: "+this.ordenSeleccionado.getCantidad()+".");
			ventanaElegirReceta.getLblSucursal().setText("sucursal: "+ordenSeleccionado.getIdSucursal());
			ventanaElegirReceta.getLblIdPedido().setText("IdPedido: "+ordenSeleccionado.getIdOrdenFabrica());
			ventanaElegirReceta.getLblFecha().setText("Fecha requerido: "+ordenSeleccionado.getFechaRequerido());
			this.ventanaElegirReceta.getLblMensaje().setText("No es posible trabajar, no hay receta");
			reiniciarTablaIngredientes();
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
				
				
				//String cantUsarXunidad = p.getPasosDTO().getCantidadUsada().get(x)+" ";	//ANTES DEL BIGDECIMAL
				BigDecimal cantidad = new BigDecimal(p.getPasosDTO().getCantidadUsada().get(x)).setScale(2, RoundingMode.HALF_UP);;
				String cantUsarXunidad = cantidad+"";
				
				//String cantUsada = (p.getPasosDTO().getCantidadUsada().get(x)*this.ordenSeleccionado.getCantidad())+"";	//ANTES DEL BIGDECIMAL
				BigDecimal cantidad2 = new BigDecimal(p.getPasosDTO().getCantidadUsada().get(x)).setScale(2, RoundingMode.HALF_UP);;
				String cantUsada = cantidad2+"";
				
				//String cantActual = this.cantidadEnStock(p.getPasosDTO().getMateriales().get(x))+"";	//ANTES DEL BIGDECIMAL
				BigDecimal cantidad3 = new BigDecimal(this.cantidadEnStock(p.getPasosDTO().getMateriales().get(x))).setScale(2, RoundingMode.HALF_UP);;
				String cantActual = cantidad3+"";
				
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
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                String status = (String)table.getModel().getValueAt(row, 2);
                Double usar = Double.valueOf(status);
                status = (String)table.getModel().getValueAt(row, 3);
                Double tengo = Double.valueOf(status);
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
	
	private boolean hayStockSuficienteDeUnMaterial(int idMaestroProducto, double d) {
		//Primero cuento todo el stock luego juzgo
		List<StockDTO> todoElStock = modeloStock.readAll();
		Double cantidadTotalDisponible = 0.0;
		for(StockDTO s: todoElStock) {
			if(s.getIdProducto() == idMaestroProducto && s.getIdSucursal() == this.idFabrica) {
				cantidadTotalDisponible = cantidadTotalDisponible + s.getStockDisponible();
			}
		}
		return cantidadTotalDisponible >= d;
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
			//double cantUsar = (p.getPasosDTO().getCantidadUsada().get(x)*of.getCantidad());	//ANTES DEL BIGDECIMAL
			BigDecimal cantUsar = new BigDecimal(p.getPasosDTO().getCantidadUsada().get(x)*of.getCantidad()).setScale(2, RoundingMode.HALF_UP);;
			String cantUsada = cantUsar+"";
			//Double cantDisponible = this.cantidadEnStock(p.getPasosDTO().getMateriales().get(x));	//ANTES DEL BIGDECIMAL
			BigDecimal cantDisponible = new BigDecimal(this.cantidadEnStock(p.getPasosDTO().getMateriales().get(x))).setScale(2, RoundingMode.HALF_UP);;
			String cantActual = cantDisponible+"";
			String unidadMedida = p.getPasosDTO().getMateriales().get(x).getUnidadMedida();
			Object[] agregar = {desc, cantUsada, cantActual, unidadMedida};
			ventanaUnaTrabajo.getModelOrdenes().addRow(agregar);
			//Object[] agregar = {p.getPasosDTO().getMateriales().get(x).getDescripcion(), (p.getPasosDTO().getCantidadUsada().get(x)*of.getCantidad())};
			//ventanaUnaTrabajo.getModelOrdenes().addRow(agregar);
			
			//hayMateriales = hayMateriales && cantUsar <= cantDisponible;	//ANTES DEL BIGDECIMAL
			hayMateriales = hayMateriales && (cantUsar.compareTo(cantDisponible) <= 0);
			ventanaUnaTrabajo.getTablaIngredientes().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                    String status = (String)table.getModel().getValueAt(row, 1);
                    Double usar = Double.valueOf(status);
                    status = (String)table.getModel().getValueAt(row, 2);
                    Double tengo = Double.valueOf(status);
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
		DefaultComboBoxModel<String> valores = new DefaultComboBoxModel<String>();
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
//		System.out.println(anioCumpleCreado);
		
		seleccionarMes();
	}
	
	private void seleccionarMes() {
		ventanaParaCumple.getLblFecha().setText("Seleccione mes");
		ventanaParaCumple.getComboBox().removeAllItems();
//		DefaultComboBoxModel<String> valores = new DefaultComboBoxModel<String>();
		ventanaParaCumple.getComboBox().setModel(new DefaultComboBoxModel<String>(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		ventanaParaCumple.getComboBox().setSelectedIndex(0);
		
		for(ActionListener i: ventanaParaCumple.getBtnElegir().getActionListeners()) {
			ventanaParaCumple.getBtnElegir().removeActionListener(i);
		}
		ventanaParaCumple.getBtnElegir().addActionListener(e->elegirMes(e));
		
		ventanaParaCumple.mostrarVentana();
	}
	
	private void elegirMes(ActionEvent a) {
		mesCumpleCreado = Integer.toString(ventanaParaCumple.getComboBox().getSelectedIndex()+1);
//		System.out.println(anioCumpleCreado);
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
		DefaultComboBoxModel<String> valores = new DefaultComboBoxModel<String>();
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
//		System.out.println(anioCumpleCreado + " " + mesCumpleCreado + " " + diaCumpleCreado);
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
	
	private Double cantidadEnStock(MaestroProductoDTO producto) {
		List<StockDTO> todoElStock = modeloStock.readAll();
		Double cantidadTotalDisponible = 0.0;
		for(StockDTO s: todoElStock) {
			if(s.getIdProducto() == producto.getIdMaestroProducto() && s.getIdSucursal() == this.idFabrica) {
				cantidadTotalDisponible = cantidadTotalDisponible + s.getStockDisponible();
			}
		}
		return cantidadTotalDisponible;
	}
	
	public void actualizarColorSeleccionTabla() {
        this.ventanaPrincipal.getTablaFabricacionesEnMarcha().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                if(isSelected) {
                	return this;
                }
                String status = (String)table.getModel().getValueAt(row, 6);
		        if (stringQueDescribeElEstadoDeLasOrdenesSiendoTrabajadas.equals(status)) {
		           setBackground(Color.ORANGE);
		           setForeground(Color.BLACK);
		        } else if ("completo".equals(status)){
		        	setBackground(Color.green);
		        	setForeground(Color.BLACK);
		        } else if (stringQueDescribeElEstadoDeLasOrdenesCanceladas.equals(status) ){
		        	setBackground(Color.red);
		        	setForeground(Color.WHITE);
		        }else {
		        	setBackground(table.getBackground());
		        	setForeground(Color.BLACK);
		        }

                return this;
            }
        });
    }
	
	private void verMateriales(ActionEvent r) {
		this.ventanaPrincipal.cerrar();
		ControladorVerMateriales control = new ControladorVerMateriales(this.fabrica, this.controlador, this);
		control.inicializar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public static void main(String[] args){
		ReControladorOperario controladorOperatoria = new ReControladorOperario(null,
				new SucursalDTO(1, "", "", "", "", "",
				"", "", "",""));
		controladorOperatoria.inicializar();
		controladorOperatoria.mostrarVentana();
	}

}
