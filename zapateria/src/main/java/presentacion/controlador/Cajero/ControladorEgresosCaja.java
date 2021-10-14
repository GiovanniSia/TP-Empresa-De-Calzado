package presentacion.controlador.Cajero;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;
import dto.EgresosDTO;
import dto.MedioPagoEgresoDTO;
import dto.TipoEgresosDTO;
import modelo.Egresos;
import modelo.MedioPagoEgreso;
import modelo.TipoEgresos;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Cajero.VentanaEgresoCaja;

public class ControladorEgresosCaja {
	private VentanaEgresoCaja ventanaEgresoCaja;
	private Egresos egresos;

	private List<TipoEgresosDTO> listaTipoEgresos;
	private TipoEgresos tipoEgresos;

	private final int idSucursal = 1;

	private List<MedioPagoEgresoDTO> listaMedioPagoEgreso;
	private MedioPagoEgreso medioPagoEgreso;

	Controlador controlador;
	
	public ControladorEgresosCaja(Controlador controlador,Egresos egresos) {
		this.ventanaEgresoCaja = new VentanaEgresoCaja();
		this.egresos = egresos;
		
		this.controlador = controlador;
		
	}

	public void inicializar() {
		this.ventanaEgresoCaja = new VentanaEgresoCaja();

		this.egresos = new Egresos(new DAOSQLFactory());

		// ComboBox TipoEgresos
		this.tipoEgresos = new TipoEgresos(new DAOSQLFactory());
		this.listaTipoEgresos = this.tipoEgresos.readAll();
		llenarCBTipoEgreso();

		// ComboBox MedioPagos
		this.medioPagoEgreso = new MedioPagoEgreso(new DAOSQLFactory());
		this.listaMedioPagoEgreso = this.medioPagoEgreso.readAll();
		llenarCBMedioPago();

		// Botones
		this.ventanaEgresoCaja.getBtnAtras().addActionListener(a -> atras(a));
		/*
		 * // AS private JLabel lblAS; private JTextField txtFieldAS;
		 * 
		 * // FA private JLabel lblFA; private JLabel lblActualizarFechaFA;
		 * 
		 * // PP private JLabel lblPP1; private JTextField txtFieldPPNroProveedor;
		 * private JLabel lblNroOrdenDe; private JTextField txtFieldPPNroOrdenCompra;
		 * 
		 * // NC private JLabel lblNC; private JTextField txtFieldNC;
		 * 
		 * Adelanto de sueldo Faltante Nota Credito Pago proveedor
		 * 
		 */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		this.ventanaEgresoCaja.getLblActualizarFechaFA().setText(fecha);

		// Cambia el estado de combobox
		this.ventanaEgresoCaja.getCbTipoEgreso().addActionListener(a -> tipoEgresoSeleccionado());

		this.ventanaEgresoCaja.getBtnConfirmarEgreso().addActionListener(c -> confirmarEgreso());

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
				String nc = this.ventanaEgresoCaja.getTxtFieldNC().getText();
				ingresarEgreso(nc);
			}
			if (tipoEgresoSeleccionado.equals("Pago proveedor")) {
				String ppNroProveedor = this.ventanaEgresoCaja.getTxtFieldPPNroProveedor().getText();
				String ppNroOrdenCompra = this.ventanaEgresoCaja.getTxtFieldPPNroOrdenCompra().getText();
				String detalle = ppNroProveedor + " - " + ppNroOrdenCompra;
				ingresarEgreso(detalle);
			}
		}
	}

	public boolean validarCampos() {
		String tipoEgresoSeleccionado = this.ventanaEgresoCaja.getTipoEgresoSeleccionado();
		String medioPagoSeleccionado =this.ventanaEgresoCaja.getMedioPagoSeleccionado();
		
		String as = this.ventanaEgresoCaja.getTxtFieldAS().getText();
		
		String nc = this.ventanaEgresoCaja.getTxtFieldNC().getText();
	
		String ppNroProveedor = this.ventanaEgresoCaja.getTxtFieldPPNroProveedor().getText();
		String ppNroOrdenCompra = this.ventanaEgresoCaja.getTxtFieldPPNroOrdenCompra().getText();
	
		if(tipoEgresoSeleccionado==null || tipoEgresoSeleccionado.equals("")) {
			JOptionPane.showMessageDialog(null, "Elige un tipo de egreso");
			return false;
		}
		
		if(tipoEgresoSeleccionado.equals("Adelanto de sueldo") && as.equals("")){
			JOptionPane.showMessageDialog(null, "El campo de tipo de egreso no puede estar vacio");
			return false;
		}
		
		if(tipoEgresoSeleccionado.equals("Nota Credito") && nc.equals("")){
			JOptionPane.showMessageDialog(null, "El campo de tipo de egreso no puede estar vacio");
			return false;
		}
		
		if(tipoEgresoSeleccionado.equals("Pago proveedor") && nc.equals("")){
			JOptionPane.showMessageDialog(null, "El campo de tipo de egreso no puede estar vacio");
			return false;
		}
		
		if(tipoEgresoSeleccionado.equals("Pago proveedor") && (ppNroProveedor.equals("") || ppNroOrdenCompra.equals("")) ){
			JOptionPane.showMessageDialog(null, "El campo de tipo de egreso no puede estar vacio");
			return false;
		}
		
		if(medioPagoSeleccionado==null || medioPagoSeleccionado.equals("")) {
			JOptionPane.showMessageDialog(null, "Elige un medio de pago");
			return false;
		}
		
		if(this.ventanaEgresoCaja.getTxtFieldMonto().getText().equals("0") || this.ventanaEgresoCaja.getTxtFieldMonto().getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Valor de monto invalido");
			return false;
		}
		return true;
	}

	public void tipoEgresoSeleccionado() {
		String tipoEgresoSeleccionado = this.ventanaEgresoCaja.getTipoEgresoSeleccionado();
		if (tipoEgresoSeleccionado.equals("Adelanto de sueldo")) {
			this.ventanaEgresoCaja.mostrarAS();
		}
		if (tipoEgresoSeleccionado.equals("Faltante")) {
			this.ventanaEgresoCaja.mostrarFA();
		}
		if (tipoEgresoSeleccionado.equals("Nota Credito")) {
			this.ventanaEgresoCaja.mostrarNC();
		}
		if (tipoEgresoSeleccionado.equals("Pago proveedor")) {
			this.ventanaEgresoCaja.mostrarPP();
		}
	}

	public void llenarCBTipoEgreso() {
		for (TipoEgresosDTO te : this.listaTipoEgresos) {
			this.ventanaEgresoCaja.getCbTipoEgreso().addItem(te.getDescripcion());
			;
		}
	}

	public void llenarCBMedioPago() {
		for (MedioPagoEgresoDTO mpe : this.listaMedioPagoEgreso) {
			this.ventanaEgresoCaja.getCbTipoMedioPago().addItem(mpe.getDescripcion());
			;
		}
	}

	public void atras(ActionEvent a) {
		this.ventanaEgresoCaja.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void ingresarEgreso(String detalle) {
		/*
		 * CREATE TABLE `Egresos` ( `Id` int(11) NOT NULL AUTO_INCREMENT, `IdSucursal`
		 * int(11) NOT NULL, `Fecha` DATE NOT NULL, `Hora` TIME NOT NULL, `Tipo`
		 * varchar(45) NOT NULL, `MedioPago` varchar(45) NOT NULL, `Detalle` varchar(45)
		 * NOT NULL, `Total` double(45,2) NOT NULL, PRIMARY KEY (`Id`) );
		 */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());
		DateTimeFormatter dtfhora = DateTimeFormatter.ofPattern("hh:mm");
		String hora = dtfhora.format(LocalDateTime.now());
		String tipoEgresoSeleccionado = this.ventanaEgresoCaja.getTipoEgresoSeleccionado();
		String tipo = obtenerAbrebiaturaMedioPago(this.ventanaEgresoCaja.getMedioPagoSeleccionado());
		String Detalle = detalle;
		int Monto = Integer.parseInt(this.ventanaEgresoCaja.getTxtFieldMonto().getText());

//		insert into Egresos values(0,idSucursal,fecha,hora,tipoEgresoSeleccionado,"EFE","Choripan",Monto);	

		EgresosDTO egresoNuevo = new EgresosDTO(0, idSucursal, fecha, hora, tipoEgresoSeleccionado, tipo, Detalle,
				Monto);

		this.egresos.insert(egresoNuevo);
		JOptionPane.showMessageDialog(null, "Egreso realizada con exito");

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

	public static void main(String[] args) {
//		Egresos modelo = new Egresos(new DAOSQLFactory());
//		ControladorEgresosCaja controlador = new ControladorEgresosCaja(modelo);
//		controlador.inicializar();
//		controlador.mostrarVentana();
	}
}
