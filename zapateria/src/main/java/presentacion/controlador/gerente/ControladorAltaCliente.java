package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import dto.ClienteDTO;
import dto.HistorialCambioClienteDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PedidosPendientesDTO;
import dto.ProveedorDTO;
import dto.PrimeraDeudaClienteDTO;
import dto.ProvinciaDTO;
import inicioSesion.empleadoProperties;
import inicioSesion.sucursalProperties;
import modelo.Cliente;
import modelo.EnviadorDeMails;
import modelo.HistorialCambioCliente;
import modelo.Localidad;
import modelo.Pais;
import modelo.PrimeraDeudaCliente;
import modelo.Provincia;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.gerente.VentanaAltaCliente;
import presentacion.vista.gerente.VentanaEditarLocalidad;
import presentacion.vista.gerente.VentanaEditarPais;
import presentacion.vista.gerente.VentanaEditarProvincia;

public class ControladorAltaCliente {

	int idSucursal;
	int idEmpleado;

	VentanaAltaCliente ventanaAltaCliente;

	ControladorEditarPais controladorEditarPais;
	VentanaEditarPais ventanaEditarPais;
	VentanaEditarProvincia ventanaEditarProvincia;
	ControladorEditarProvincia controladorEditarProvincia;
	VentanaEditarLocalidad ventanaEditarLocalidad;
	ControladorEditarLocalidad controladorEditarLocalidad;

	Cliente cliente;
	ArrayList<ClienteDTO> listaClientes;

	ClienteDTO clienteSeteado;

	Pais pais;
	Provincia provincia;
	Localidad localidad;
	ArrayList<PaisDTO> todosLosPaises;
	ArrayList<ProvinciaDTO> todasLasProvincias;
	ArrayList<LocalidadDTO> todasLasLocalidades;
	ArrayList<ProvinciaDTO> provEnComboBox;
	ArrayList<LocalidadDTO> localidadEnComboBox;

	ControladorGestionarClientes controladorGestionarClientes;

	HistorialCambioCliente historialCambioCliente;

	public ControladorAltaCliente(Cliente cliente, Pais pais, Provincia provincia, Localidad localidad,
			HistorialCambioCliente historialCambioCliente) {
		this.cliente = cliente;
		this.listaClientes = new ArrayList<ClienteDTO>();

		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;

		this.historialCambioCliente = historialCambioCliente;

		this.todosLosPaises = new ArrayList<PaisDTO>();
		this.todasLasProvincias = new ArrayList<ProvinciaDTO>();
		this.todasLasLocalidades = new ArrayList<LocalidadDTO>();
		this.provEnComboBox = new ArrayList<ProvinciaDTO>();
		this.localidadEnComboBox = new ArrayList<LocalidadDTO>();

		this.controladorEditarPais = new ControladorEditarPais(this.pais);
		this.controladorEditarProvincia = new ControladorEditarProvincia(this.pais, this.provincia);
		this.controladorEditarLocalidad = new ControladorEditarLocalidad(this.pais, this.provincia, this.localidad);

	}

	public void setControladorGestionarClientes(ControladorGestionarClientes controladorGestionarClientes) {
		this.controladorGestionarClientes = controladorGestionarClientes;
	}

	public void inicializar() {
		setearDatosDeProperties();

		this.ventanaAltaCliente = new VentanaAltaCliente();

		this.controladorEditarPais.inicializar();
		this.controladorEditarProvincia.inicializar();
		this.controladorEditarLocalidad.inicializar();

		this.todosLosPaises = (ArrayList<PaisDTO>) this.pais.readAll();
		this.todasLasProvincias = (ArrayList<ProvinciaDTO>) this.provincia.readAll();
		this.todasLasLocalidades = (ArrayList<LocalidadDTO>) this.localidad.readAll();
		this.listaClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();

		this.ventanaAltaCliente.getComboBoxPais().addActionListener(a -> actualizarCbUbicacionDadoPais());

		this.ventanaAltaCliente.getComboBoxProvincia().addActionListener(a -> actualizarCbUbicacionDadaProv());

		this.ventanaAltaCliente.getBtnRegistrar().addActionListener(a -> registrarCliente(a));
		this.ventanaAltaCliente.getBtnCancelar().addActionListener(a -> salir());
		this.ventanaAltaCliente.getBtnUbicacion().addActionListener(a -> pasarAEditarUbicacion(a));
		this.ventanaAltaCliente.getBtnEditar().addActionListener(a -> editarCliente(a));

		cargarComboBoxes();
		validarTeclado();
	}

	public void setearDatosDeProperties() {
		empleadoProperties empleado = empleadoProperties.getInstance();
		sucursalProperties sucu = sucursalProperties.getInstance();
		try {
			this.idSucursal = Integer.parseInt(sucu.getValue("IdSucursal"));
			this.idEmpleado = Integer.parseInt(empleado.getValue("IdEmpleado"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarVentana() {
		this.clienteSeteado = null;
		this.ventanaAltaCliente.getLblRegistrarCliente().setVisible(true);
		this.ventanaAltaCliente.getBtnRegistrar().setVisible(true);
		this.ventanaAltaCliente.show();
	}

	public void mostrarVentanaEditar() {
		this.ventanaAltaCliente.getLblTitulo().setText("Editar Cliente");
		this.ventanaAltaCliente.getBtnEditar().setVisible(true);
		this.ventanaAltaCliente.getLblEditarCliente().setVisible(true);
		this.ventanaAltaCliente.getLblLimiteDeCredito().setVisible(true);
		this.ventanaAltaCliente.getTextLimiteCredito().setVisible(true);
		this.ventanaAltaCliente.getLblEstado().setVisible(true);
		this.ventanaAltaCliente.getComboBoxEstado().setVisible(true);
		this.ventanaAltaCliente.show();
	}
	
	public void salir() {
		this.ventanaAltaCliente.cerrar();
		this.controladorGestionarClientes.inicializar();
		this.controladorGestionarClientes.mostrarVentana();
	}

	public void setearDatosDeCliente(ClienteDTO clienteAEditar) {
		this.clienteSeteado = clienteAEditar;

		this.ventanaAltaCliente.getTextNombre().setText(this.clienteSeteado.getNombre());
		this.ventanaAltaCliente.getTextApellido().setText(this.clienteSeteado.getApellido());
		this.ventanaAltaCliente.getTextCUIL().setText(this.clienteSeteado.getCUIL());
		this.ventanaAltaCliente.getTextCorreo().setText(this.clienteSeteado.getCorreo());

		this.ventanaAltaCliente.getComboBoxTipoCliente().setSelectedItem(this.clienteSeteado.getTipoCliente());

		String impuestoAFIP = obtenerNombreCategoria(clienteAEditar);
		this.ventanaAltaCliente.getComboBoxImpuestoAFIP().setSelectedItem(impuestoAFIP);

		BigDecimal credDisp = new BigDecimal(this.clienteSeteado.getCreditoDisponible());
		this.ventanaAltaCliente.getTextSaldoInicial().setText("" + credDisp);

		this.ventanaAltaCliente.getComboBoxPais().setSelectedItem(this.clienteSeteado.getPais());
		this.ventanaAltaCliente.getComboBoxProvincia().setSelectedItem(this.clienteSeteado.getProvincia());
		this.ventanaAltaCliente.getComboBoxLocalidad().setSelectedItem(this.clienteSeteado.getLocalidad());

		this.ventanaAltaCliente.getTextCalle().setText("" + this.clienteSeteado.getCalle());
		this.ventanaAltaCliente.getTextAltura().setText("" + this.clienteSeteado.getAltura());
		this.ventanaAltaCliente.getTextCodPostal().setText("" + this.clienteSeteado.getCodPostal());

		BigDecimal limitCred = new BigDecimal(this.clienteSeteado.getLimiteCredito());
		this.ventanaAltaCliente.getTextLimiteCredito().setText("" + limitCred);
		this.ventanaAltaCliente.getComboBoxEstado().setSelectedItem(this.clienteSeteado.getEstado());
	}

	public String obtenerNombreCategoria(ClienteDTO cliente) {
		String tipo = cliente.getImpuestoAFIP();
		if (tipo.equals("RI")) {
			return "Responsable Inscripto";
		}
		if (tipo.equals("M")) {
			return "Monotributista";
		}
		if (tipo.equals("CF")) {
			return "Consumidor Final";
		}
		if (tipo.equals("E")) {
			return "Exento";
		}
		return "Categoria no encontrada en el sistema";
	}

	public void editarCliente(ActionEvent a) {
		if (!todosLosCamposSonValidos()) {
			return;
		}

		ClienteDTO clienteActualizado = obtenerClienteDeVista();
		clienteActualizado.setEstado(this.ventanaAltaCliente.getComboBoxEstado().getSelectedItem().toString());
//		System.out.println("Nuevo estado: " + clienteActualizado.getEstado());

		guadarHistorialDeCambioDeCliente(this.clienteSeteado, clienteActualizado);

		boolean update = this.cliente.update(this.clienteSeteado.getIdCliente(), clienteActualizado);
		if (!update) {
			JOptionPane.showMessageDialog(null, "Error al actualizar el cliente ", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!clienteActualizado.getEstado().equals("Moroso")) {

			// Para agregar a la tabla primerClienteMoroso
			verificarClienteFuturoMoroso(clienteActualizado);

			cambiarEstadoClienteAutomaticoAMoroso(clienteActualizado);
		}

		JOptionPane.showMessageDialog(null, "Cliente actualizado con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
		salirEditar();
	}

	public void cambiarEstadoClienteAutomaticoAMoroso(ClienteDTO clienteActualizado) {

		if (clienteActualizado.getCreditoDisponible() == clienteActualizado.getLimiteCredito()) {
			if (existeEnTablaPrimeraDeudaCliente()) {
				System.out.println(obtenerPrimeraDeudaClienteDeCliente() != null);
				if (obtenerPrimeraDeudaClienteDeCliente() != null) {
					PrimeraDeudaCliente primeraDeudaCliente = new PrimeraDeudaCliente(new DAOSQLFactory());
					PrimeraDeudaClienteDTO clienteMorosoEntablaPrimerClienteMoroso = obtenerPrimeraDeudaClienteDeCliente();

					Cliente cliente = new Cliente(new DAOSQLFactory());
					ClienteDTO clienteElegido = cliente.selectCliente(clienteSeteado.getIdCliente());
					clienteElegido.setEstado("Activo");
					cliente.update(clienteSeteado.getIdCliente(), clienteElegido);

					primeraDeudaCliente.delete(clienteMorosoEntablaPrimerClienteMoroso);
				}
			}
		}
	}

	public PrimeraDeudaClienteDTO obtenerPrimeraDeudaClienteDeCliente() {
		PrimeraDeudaCliente primeraDeudaCliente = new PrimeraDeudaCliente(new DAOSQLFactory());
		List<PrimeraDeudaClienteDTO> listaPrimeraDeudaCliente;
		listaPrimeraDeudaCliente = primeraDeudaCliente.readAll();
		for (PrimeraDeudaClienteDTO p : listaPrimeraDeudaCliente) {
			if (p.getIdCliente() == clienteSeteado.getIdCliente()) {
				return new PrimeraDeudaClienteDTO(p.getId(), p.getIdCliente(), p.getFechaDeuda());
			}
		}
		return null;
	}

	public boolean existeEnTablaPrimeraDeudaCliente() {
		PrimeraDeudaCliente primeraDeudaCliente = new PrimeraDeudaCliente(new DAOSQLFactory());
		List<PrimeraDeudaClienteDTO> listaPrimeraDeudaCliente;
		listaPrimeraDeudaCliente = primeraDeudaCliente.readAll();
		for (PrimeraDeudaClienteDTO p : listaPrimeraDeudaCliente) {
			if (p.getIdCliente() == clienteSeteado.getIdCliente()) {
				return true;
			}
		}
		return false;
	}

	public void verificarClienteFuturoMoroso(ClienteDTO clienteActualizado) {
		if (esFuturoMoroso(clienteActualizado)) {
			if (!existeEnTablaPrimeraDeudaCliente()) {
				PrimeraDeudaCliente primeraDeudaCliente = new PrimeraDeudaCliente(new DAOSQLFactory());
				int idCliente = clienteSeteado.getIdCliente();
				String fechaDeuda = obtenerFechaDeHoy();
				PrimeraDeudaClienteDTO clienteMoroso = new PrimeraDeudaClienteDTO(0, idCliente, fechaDeuda);
				primeraDeudaCliente.insert(clienteMoroso);
			}
		}
	}

	public boolean esFuturoMoroso(ClienteDTO clienteActualizado) {
		if (clienteActualizado.getCreditoDisponible() < clienteActualizado.getLimiteCredito()) {
			return true;
		}
		return false;
	}

	public String obtenerFechaDeHoy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String fecha = dtf.format(LocalDateTime.now());
		return fecha;
	}

	public void salirEditar() {
		this.clienteSeteado = null;
		this.ventanaAltaCliente.getBtnEditar().setVisible(false);
		this.ventanaAltaCliente.cerrar();
		this.controladorGestionarClientes.inicializar();
		this.controladorGestionarClientes.mostrarVentana();

	}

	public void guadarHistorialDeCambioDeCliente(ClienteDTO clienteSeteado, ClienteDTO clienteActualizado) {
		int id = 0;
		int idEmpleado = this.idEmpleado;
		int idCliente = this.clienteSeteado.getIdCliente();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = dtf.format(LocalDateTime.now());

		String nombreAntiguo = clienteSeteado.getNombre();
		String nombreNuevo = clienteActualizado.getNombre();

		String apellidoAntiguo = clienteSeteado.getApellido();
		String apellidoNuevo = clienteActualizado.getApellido();

		String CUILAntiguo = clienteSeteado.getCUIL();
		String CUILNuevo = clienteActualizado.getCUIL();

		String correoAntiguo = clienteSeteado.getCorreo();
		String correoNuevo = clienteActualizado.getCorreo();

		double limiteCreditoAntiguo = clienteSeteado.getLimiteCredito();
		double limiteCreditoNuevo = clienteActualizado.getLimiteCredito();

		double creditoDisponibleAntiguo = clienteSeteado.getCreditoDisponible();
		double creditoDisponibleNuevo = clienteActualizado.getCreditoDisponible();

		String tipoClienteAntiguo = clienteSeteado.getTipoCliente();
		String tipoClienteNuevo = clienteActualizado.getTipoCliente();

		String impuestoAFIPAntiguo = clienteSeteado.getImpuestoAFIP();
		String impuestoAFIPNuevo = clienteActualizado.getImpuestoAFIP();

		String estadoAntiguo = clienteSeteado.getEstado();
		String estadoNuevo = clienteActualizado.getEstado();

		String calleAntiguo = clienteSeteado.getCalle();
		String calleNuevo = clienteActualizado.getCalle();

		String alturaAntiguo = clienteSeteado.getAltura();
		String alturaNuevo = clienteActualizado.getAltura();

		String paisAntiguo = clienteSeteado.getPais();
		String paisNuevo = clienteActualizado.getPais();

		String provinciaAntiguo = clienteSeteado.getProvincia();
		String provinciaNuevo = clienteActualizado.getProvincia();

		String localidadAntiguo = clienteSeteado.getLocalidad();
		String localidadNuevo = clienteActualizado.getLocalidad();

		String codPostalAntiguo = clienteSeteado.getCodPostal();
		String codPostalNuevo = clienteActualizado.getCodPostal();

		HistorialCambioClienteDTO historial = new HistorialCambioClienteDTO(id, idEmpleado, idCliente, fecha,
				nombreAntiguo, nombreNuevo, apellidoAntiguo, apellidoNuevo, CUILAntiguo, CUILNuevo, correoAntiguo,
				correoNuevo, limiteCreditoAntiguo, limiteCreditoNuevo, creditoDisponibleAntiguo, creditoDisponibleNuevo,
				tipoClienteAntiguo, tipoClienteNuevo, impuestoAFIPAntiguo, impuestoAFIPNuevo, estadoAntiguo,
				estadoNuevo, calleAntiguo, calleNuevo, alturaAntiguo, alturaNuevo, paisAntiguo, paisNuevo,
				provinciaAntiguo, provinciaNuevo, localidadAntiguo, localidadNuevo, codPostalAntiguo, codPostalNuevo);

		boolean insert = this.historialCambioCliente.insert(historial);
		if (!insert) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia,
					"Ha ocurrido un error al insertar el cambio en el historial");
		}

	}
	
	public void registrarCliente(ActionEvent a) {

		if (todosLosCamposSonValidos()) {
			ClienteDTO cliente = obtenerClienteDeVista();

			 if(clienteYaExiste()){
				 JOptionPane.showMessageDialog(null, "El cliente ya existe en el sistema");
				 return;
			 }
			
			boolean insert=this.cliente.insert(cliente);
			if(!insert) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar el nuevo cliente al sistema");
			}else {
				JOptionPane.showMessageDialog(null, "Se agrego el nuevo cliente al sistema");	
			}
			
			guardarHistorialParaNuevoCliente(cliente);

			Thread envioMail = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("se ejecuta el thread");
					EnviadorDeMails.enviarMailDeBienvenidaACliente(cliente);
				}
					
			});
			envioMail.start();

			borrarDatosDeLosText();
			salir();
//			System.out.println("THREAD ENVIOMAIL ESTA VIVO: "+envioMail.isAlive());
		}
	}	

	
	public void guardarHistorialParaNuevoCliente(ClienteDTO clienteNuevo) {
		int id = 0;
		int idEmpleado = this.idEmpleado;
		int idCliente = this.cliente.getIdUltimoCliente();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		String fecha = dtf.format(LocalDateTime.now());
		
		String nombreAntiguo = "";
		String nombreNuevo = clienteNuevo.getNombre();
		
		String apellidoAntiguo= "";
		String apellidoNuevo = clienteNuevo.getApellido();
		
		String CUILAntiguo = ""; 
		String CUILNuevo = clienteNuevo.getCUIL();
		
		String correoAntiguo = ""; 
		String correoNuevo = clienteNuevo.getCorreo();
		
		double limiteCreditoAntiguo = 0;
		double limiteCreditoNuevo = clienteNuevo.getLimiteCredito();
		
		double creditoDisponibleAntiguo = 0;
		double creditoDisponibleNuevo=clienteNuevo.getCreditoDisponible();
		
		String tipoClienteAntiguo = "";
		String tipoClienteNuevo = clienteNuevo.getTipoCliente();
		
		String impuestoAFIPAntiguo = "";
		String impuestoAFIPNuevo = clienteNuevo.getImpuestoAFIP();
		
		String estadoAntiguo = "";
		String estadoNuevo = clienteNuevo.getEstado();
		
		String calleAntiguo = "";
		String calleNuevo = clienteNuevo.getCalle();
		
		String alturaAntiguo = "";
		String alturaNuevo = clienteNuevo.getAltura();
		
		String paisAntiguo = "";
		String paisNuevo = clienteNuevo.getPais();
		
		String provinciaAntiguo = "";
		String provinciaNuevo = clienteNuevo.getProvincia();
		
		String localidadAntiguo = "";
		String localidadNuevo = clienteNuevo.getLocalidad();
		
		String codPostalAntiguo = "";
		String codPostalNuevo = clienteNuevo.getCodPostal();
		
		HistorialCambioClienteDTO historial = new HistorialCambioClienteDTO(id,idEmpleado,idCliente,fecha,nombreAntiguo,nombreNuevo,apellidoAntiguo,apellidoNuevo,CUILAntiguo,CUILNuevo,correoAntiguo,correoNuevo,limiteCreditoAntiguo,limiteCreditoNuevo,creditoDisponibleAntiguo,creditoDisponibleNuevo,tipoClienteAntiguo,tipoClienteNuevo,impuestoAFIPAntiguo,impuestoAFIPNuevo,estadoAntiguo,estadoNuevo,calleAntiguo,calleNuevo,alturaAntiguo,alturaNuevo,paisAntiguo,paisNuevo,provinciaAntiguo,provinciaNuevo,localidadAntiguo,localidadNuevo,codPostalAntiguo,codPostalNuevo);
		
		boolean insert = this.historialCambioCliente.insert(historial);
		if(!insert) {
			JOptionPane.showMessageDialog(ventanaEditarProvincia, "Ha ocurrido un error al insertar el cambio en el historial");
		}
	}
	
	
	
	public ClienteDTO obtenerClienteDeVista() {
		String nombre = this.ventanaAltaCliente.getTextNombre().getText();
		String apellido = this.ventanaAltaCliente.getTextApellido().getText();
		String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
		String mail = this.ventanaAltaCliente.getTextCorreo().getText();

		double limiteCredito;
		double creditoDisp = Double.parseDouble(this.ventanaAltaCliente.getTextSaldoInicial().getText());
		if (this.clienteSeteado != null) {
			limiteCredito = Double.parseDouble(this.ventanaAltaCliente.getTextLimiteCredito().getText());

		} else {
			limiteCredito = creditoDisp;
		}
		String tipoCliente = (String) this.ventanaAltaCliente.getComboBoxTipoCliente().getSelectedItem();
		String impuestoAFIP = getIdItemImpuestoAFIP(
				this.ventanaAltaCliente.getComboBoxImpuestoAFIP().getSelectedItem().toString());
		String estado = "Activo";
		String calle = this.ventanaAltaCliente.getTextCalle().getText();
		String altura = this.ventanaAltaCliente.getTextAltura().getText();
		String pais = this.ventanaAltaCliente.getComboBoxPais().getSelectedItem().toString();
		String prov = this.ventanaAltaCliente.getComboBoxProvincia().getSelectedItem().toString();
		String localida = this.ventanaAltaCliente.getComboBoxLocalidad().getSelectedItem().toString();
		String codPostal = this.ventanaAltaCliente.getTextCodPostal().getText();

		return new ClienteDTO(0, nombre, apellido, CUIL, mail, limiteCredito, creditoDisp, tipoCliente, impuestoAFIP,
				estado, calle, altura, pais, prov, localida, codPostal);
	}

	public boolean clienteYaExiste() {
		this.listaClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();
		String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
		for (ClienteDTO c : this.listaClientes) {
			if (c.getCUIL().equals(CUIL)) {
				return true;
			}
		}
		return false;
	}

	public boolean todosLosCamposSonValidos() {
		String nombre = this.ventanaAltaCliente.getTextNombre().getText();
		if (nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre no puede ser vacio");
			return false;
		}
		if(nombre.length()>=44) {
			JOptionPane.showMessageDialog(null, "La cantidad de caracteres de Nombre no puede ser mayor a 44");
			return false;	
		}
		
		String apellido = this.ventanaAltaCliente.getTextApellido().getText();
		if (apellido.equals("")) {
			JOptionPane.showMessageDialog(null, "La cantidad de caracteres de Apellido no puede ser mayor a 44");
			return false;
		}
		if(apellido.length()>=44) {
			JOptionPane.showMessageDialog(null, "El nombre no puede ser vacio");
			return false;
		}
		
		String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
		if (CUIL.equals("") || CUIL.length() != 11) {
			JOptionPane.showMessageDialog(null, "El CUIL no es correcto");
			return false;
		}
		
		String mail = this.ventanaAltaCliente.getTextCorreo().getText();
		boolean m = mail
				.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if (!m) {
			JOptionPane.showMessageDialog(null, "El formato de mail es incorrecto");
			return false;
		}
		if(mail.length()>=44) {
			JOptionPane.showMessageDialog(null, "La cantidad de caracteres de Mail no puede ser mayor a 44");
			return false;
		}
		
		if (this.ventanaAltaCliente.getComboBoxImpuestoAFIP().getSelectedItem() == "Sin seleccionar") {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de impuesto AFIP");
			return false;
		}
		if (this.ventanaAltaCliente.getComboBoxTipoCliente().getSelectedItem() == "Sin seleccionar") {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de cliente");
			return false;
		}
		String saldoInicial = this.ventanaAltaCliente.getTextSaldoInicial().getText();
		if (saldoInicial.equals("") || Double.parseDouble(saldoInicial) <= 0) {
			JOptionPane.showMessageDialog(null, "El saldo inicial es incorrecto");
			return false;
		}
		if(saldoInicial.length()>=44) {
			JOptionPane.showMessageDialog(null, "La cantidad de caracteres para Saldo inicial no puede ser mayor a 44");
			return false;
		}
		
		if (this.ventanaAltaCliente.getComboBoxEstado().getSelectedItem().toString() != null
				&& !this.ventanaAltaCliente.getTextLimiteCredito().getText().equals("")) {
			String tipoEstado = this.ventanaAltaCliente.getComboBoxEstado().getSelectedItem().toString();
			String limiteCredito = this.ventanaAltaCliente.getTextLimiteCredito().getText();

			if (tipoEstado.equals("Moroso") && Double.parseDouble(saldoInicial) == Double.parseDouble(limiteCredito)) {
				JOptionPane.showMessageDialog(null, "El cliente no puede ser moroso");
				return false;
			}

		}

		String pais = (String) this.ventanaAltaCliente.getComboBoxPais().getSelectedItem();
		if (pais == null) {
			JOptionPane.showMessageDialog(null, "El pais no puede ser vacio");
			return false;
		}
		String prov = (String) this.ventanaAltaCliente.getComboBoxProvincia().getSelectedItem();
		if (prov == null) {
			JOptionPane.showMessageDialog(null, "La provincia no puede ser vacia");
			return false;
		}
		String localida = (String) this.ventanaAltaCliente.getComboBoxLocalidad().getSelectedItem();
		if (localida == null) {
			JOptionPane.showMessageDialog(null, "La localidad no puede ser vacia");
			return false;
		}
		String calle = this.ventanaAltaCliente.getTextCalle().getText();
		if (calle.equals("")) {
			JOptionPane.showMessageDialog(null, "La calle no puede ser vacia");
			return false;
		}
		String altura = this.ventanaAltaCliente.getTextAltura().getText();
		if (altura.equals("") || altura.equals("0")) {
			JOptionPane.showMessageDialog(null, "La altura es incorrecta");
			return false;
		}
		String codPostal = this.ventanaAltaCliente.getTextCodPostal().getText();
		if (codPostal.equals("")) {
			JOptionPane.showMessageDialog(null, "El cod postal no puede ser vacio");
			return false;
		}

		// si se esta editando
		if (this.clienteSeteado != null) {
			String estadoSeleccionado = this.ventanaAltaCliente.getComboBoxEstado().getSelectedItem().toString();
			String limiteDeCredito = this.ventanaAltaCliente.getTextLimiteCredito().getText();

			if (estadoSeleccionado.equals("Sin seleccionar")) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un estado", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (limiteDeCredito.equals("") || Double.parseDouble(limiteDeCredito) <= 0) {
				JOptionPane.showMessageDialog(null, "El limite de credito es incorrecto", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (Double.parseDouble(limiteDeCredito) < Double.parseDouble(saldoInicial)) {
				JOptionPane.showMessageDialog(null, "El saldo no puede ser mayor al limite de credito", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		return true;

	}

	public void validarTeclado() {

		this.ventanaAltaCliente.getTextNombre().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaAltaCliente.getTextApellido().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaAltaCliente.getTextCUIL().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});
		this.ventanaAltaCliente.getTextCorreo().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarMinusculaDigitoPuntoArrobaYGuiones(e);
			}
		});

		this.ventanaAltaCliente.getTextSaldoInicial().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});

		this.ventanaAltaCliente.getTextAltura().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});
		this.ventanaAltaCliente.getTextCodPostal().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});

		this.ventanaAltaCliente.getTextLimiteCredito().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});

	}

	public void cargarComboBoxes() {

		String[] tipoCliente = { "Sin seleccionar", "Mayorista", "Minorista" };
		String[] impuestoAFIP = { "Sin seleccionar", "Responsable Inscripto", "Monotributista", "Exento",
				"Consumidor Final" };

		for (int i = 0; i < tipoCliente.length; i++) {
			this.ventanaAltaCliente.getComboBoxTipoCliente().addItem(tipoCliente[i]);
		}
		for (int i = 0; i < impuestoAFIP.length; i++) {
			this.ventanaAltaCliente.getComboBoxImpuestoAFIP().addItem(impuestoAFIP[i]);
		}

		String[] estado = { "Activo", "Inactivo", "Moroso" };
		for (int i = 0; i < estado.length; i++) {
			this.ventanaAltaCliente.getComboBoxEstado().addItem(estado[i]);
		}

		// COMBO BOXES PARA UBICACION

//		this.ventanaAltaCliente.getComboBoxPais().addItem("Sin seleccionar");
		if (this.todosLosPaises.size() == 0) {
			return;
		}
		for (PaisDTO p : this.todosLosPaises) {
			this.ventanaAltaCliente.getComboBoxPais().addItem(p.getNombrePais());
		}

		this.ventanaAltaCliente.getComboBoxPais().setSelectedIndex(0);
	}

	public String getIdItemImpuestoAFIP(String descr) {
		if (descr.equals("Responsable Inscripto")) {
			return "RI";
		}
		if (descr.equals("Monotributista")) {
			return "M";
		}
		if (descr.equals("Consumidor Final")) {
			return "CF";
		}
		if (descr.equals("Exento")) {
			return "E";
		}
		return "";
	}

	public void borrarDatosDeLosText() {
		this.ventanaAltaCliente.getTextNombre().setText("");
		this.ventanaAltaCliente.getTextApellido().setText("");
		this.ventanaAltaCliente.getTextCUIL().setText("");
		this.ventanaAltaCliente.getTextCorreo().setText("");

		this.ventanaAltaCliente.getComboBoxTipoCliente().setSelectedIndex(0);
		this.ventanaAltaCliente.getComboBoxImpuestoAFIP().setSelectedIndex(0);

		this.ventanaAltaCliente.getTextSaldoInicial().setText("");
		this.ventanaAltaCliente.getTextCalle().setText("");
		this.ventanaAltaCliente.getTextAltura().setText("");
//		this.ventanaAltaCliente.getTextPais().setText("");
//		this.ventanaAltaCliente.getTextProvincia().setText("");
//		this.ventanaAltaCliente.getTextLocalidad().setText("");
		this.ventanaAltaCliente.getTextCodPostal().setText("");
	}

	public void actualizarCbUbicacionDadoPais() {

		if (this.ventanaAltaCliente.getComboBoxPais().getSelectedItem() == null
				|| this.ventanaAltaCliente.getComboBoxPais().getSelectedIndex() == -1) {
			return;
		}
//		System.out.println("se ejecuta la escucha de cb pais");
		this.ventanaAltaCliente.getComboBoxProvincia().removeAllItems();
		this.provEnComboBox.removeAll(this.provEnComboBox);

		this.ventanaAltaCliente.getComboBoxLocalidad().removeAllItems();
		this.localidadEnComboBox.removeAll(this.localidadEnComboBox);

		PaisDTO paisSeleccionado = this.todosLosPaises
				.get(this.ventanaAltaCliente.getComboBoxPais().getSelectedIndex());

		if (paisSeleccionado == null) {
			return;
		}

		llenarCbProv(paisSeleccionado.getIdPais());

		if (this.provEnComboBox.size() == 0) {
			return;
		}

		ProvinciaDTO provEnCb = this.provEnComboBox.get(0);

		llenarCbLocalidad(provEnCb.getIdProvincia());

	}

	public void actualizarCbUbicacionDadaProv() {
		if (this.ventanaAltaCliente.getComboBoxProvincia().getSelectedItem() == null
				|| this.provEnComboBox.size() == 0) {
			return;
		}
//		System.out.println("se ejecuta la escucha de cb localidad");
		this.ventanaAltaCliente.getComboBoxLocalidad().removeAllItems();
		this.localidadEnComboBox.removeAll(this.localidadEnComboBox);

		ProvinciaDTO provincia = this.provEnComboBox
				.get(this.ventanaAltaCliente.getComboBoxProvincia().getSelectedIndex());
		if (provincia == null) {
			return;
		}

		llenarCbLocalidad(provincia.getIdProvincia());

	}

	public void llenarCbProv(int idPais) {
		ArrayList<ProvinciaDTO> provincias = (ArrayList<ProvinciaDTO>) getProvinciasDePais(idPais);
		for (ProvinciaDTO p : provincias) {
			if (idPais == p.getForeignPais()) {
				this.ventanaAltaCliente.getComboBoxProvincia().addItem(p.getNombreProvincia());
				this.provEnComboBox.add(p);
			}

		}
	}

	public void llenarCbLocalidad(int idProv) {
		ArrayList<LocalidadDTO> localidades = (ArrayList<LocalidadDTO>) getLocalidadesDeProvincia(idProv);
		for (LocalidadDTO l : localidades) {
			if (idProv == l.getIdForeignProvincia()) {
				this.ventanaAltaCliente.getComboBoxLocalidad().addItem(l.getNombreLocalidad());
				this.localidadEnComboBox.add(l);
			}

		}
	}

	public List<ProvinciaDTO> getProvinciasDePais(int idPais) {
		ArrayList<ProvinciaDTO> provincias = new ArrayList<ProvinciaDTO>();
		for (ProvinciaDTO p : this.todasLasProvincias) {
			if (idPais == p.getForeignPais()) {
				provincias.add(p);
			}
		}
		return provincias;
	}

	public List<LocalidadDTO> getLocalidadesDeProvincia(int idProv) {
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		for (LocalidadDTO l : this.todasLasLocalidades) {
			if (idProv == l.getIdForeignProvincia()) {
				localidades.add(l);
			}
		}
		return localidades;
	}

	public void pasarAEditarUbicacion(ActionEvent a) {
		int seleccion = JOptionPane.showOptionDialog(null, "Que desea editar?", "Editar Ubicacion",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por defecto.
				new Object[] { "Paises", "Provincias", "Localidades" }, // null para YES, NO y CANCEL
				"opcion 1");

		if (seleccion == 0) {
			if (this.controladorEditarPais.ventanaYaEstaInicializada()) {
//				System.out.println("la ventana edit pais ya esta inicializada");
				return;
			}
			this.controladorEditarProvincia.cerrarVentana();
			this.controladorEditarLocalidad.cerrarVentana();
			this.controladorEditarPais.setControladorAltaCliente(this);
			this.controladorEditarPais.inicializar();
			this.controladorEditarPais.mostrarVentana();
		}

		if (seleccion == 1) {
			if (this.controladorEditarProvincia.ventanaYaFueInicializada()) {
//				System.out.println("la ventana edit prov ya esta inicializada");
				return;
			}
			this.controladorEditarPais.cerrarVentana();
			this.controladorEditarLocalidad.cerrarVentana();
			this.controladorEditarProvincia.setControladorAltaCliente(this);
			this.controladorEditarProvincia.inicializar();
			this.controladorEditarProvincia.mostrarVentana();
		}
		if (seleccion == 2) {
			if (this.controladorEditarLocalidad.ventanaEstaInicializada()) {
				return;
			}
			this.controladorEditarProvincia.cerrarVentana();
			this.controladorEditarPais.cerrarVentana();
			this.controladorEditarLocalidad.setControladorAltaCliente(this);
			this.controladorEditarLocalidad.inicializar();
			this.controladorEditarLocalidad.mostrarVentana();
		}

	}

	public void actualizarComboBoxes() {
		this.todosLosPaises = (ArrayList<PaisDTO>) this.pais.readAll();
		this.todasLasProvincias = (ArrayList<ProvinciaDTO>) this.provincia.readAll();
		this.todasLasLocalidades = (ArrayList<LocalidadDTO>) this.localidad.readAll();

		this.ventanaAltaCliente.getComboBoxImpuestoAFIP().removeAllItems();
		this.ventanaAltaCliente.getComboBoxTipoCliente().removeAllItems();

		this.ventanaAltaCliente.getComboBoxPais().removeAllItems();
		this.ventanaAltaCliente.getComboBoxProvincia().removeAllItems();
		this.ventanaAltaCliente.getComboBoxLocalidad().removeAllItems();

		this.ventanaAltaCliente.getComboBoxEstado().removeAllItems();

		cargarComboBoxes();

		// los cb tienen una escucha que funciona cada vez que se modifica un cb. Por lo
		// que si se modifica el cb pais, se
		// ejecutar� la escucha de llenarCbDadoPais >:(.

	}
}
