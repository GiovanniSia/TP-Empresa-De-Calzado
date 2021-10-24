package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import modelo.Cliente;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.gerente.VentanaAltaCliente;

public class ControladorAltaCliente {
	
	VentanaAltaCliente ventanaAltaCliente;
	
	Cliente cliente;
	ArrayList<ClienteDTO> listaClientes;
	
	Controlador controlador;
	
	public ControladorAltaCliente(Controlador controlador,Cliente cliente) {
		this.cliente=cliente;
		this.listaClientes = new ArrayList<ClienteDTO>();
		this.controlador= controlador;
	}
	
	public void inicializar() {
		this.ventanaAltaCliente = new VentanaAltaCliente(); 
		this.listaClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();
		
		this.ventanaAltaCliente.getBtnRegistrar().addActionListener(a -> registrarCliente(a));
		this.ventanaAltaCliente.getBtnCancelar().addActionListener(a -> salir(a));
		
		cargarComboBoxes();
		validarTeclado();
		
	}
	


	public void mostrarVentana() {
		this.ventanaAltaCliente.show();
	}
	
	public void salir(ActionEvent a) {
		this.ventanaAltaCliente.cerrar();
		this.controlador.inicializar();
		this.controlador.mostrarVentanaMenuDeSistemas();
	}

	public void registrarCliente(ActionEvent a) {
		
		if(todosLosCamposSonValidos()) {
			
			String nombre = this.ventanaAltaCliente.getTextNombre().getText();
			String apellido = this.ventanaAltaCliente.getTextApellido().getText();
			String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
			String mail = this.ventanaAltaCliente.getTextCorreo().getText();
			double limiteCredito = Double.parseDouble(this.ventanaAltaCliente.getTextSaldoInicial().getText());
			double creditoDisp = limiteCredito;
			String tipoCliente =(String) this.ventanaAltaCliente.getComboBoxTipoCliente().getSelectedItem();
			String impuestoAFIP = getIdItemImpuestoAFIP((String) this.ventanaAltaCliente.getComboBoxImpuestoAFIP().getSelectedItem());
			String estado = "Activo";
			String calle = this.ventanaAltaCliente.getTextCalle().getText();
			String altura = this.ventanaAltaCliente.getTextAltura().getText();
			String pais = this.ventanaAltaCliente.getTextPais().getText();
			String prov = this.ventanaAltaCliente.getTextProvincia().getText();
			String localida = this.ventanaAltaCliente.getTextLocalidad().getText();
			String codPostal = this.ventanaAltaCliente.getTextCodPostal().getText();
			
			ClienteDTO cliente = new ClienteDTO(0,nombre,apellido,CUIL,mail,limiteCredito,creditoDisp,tipoCliente,impuestoAFIP,estado,calle,altura,pais,prov,localida,codPostal);
			 if(clienteYaExiste()){
				 JOptionPane.showMessageDialog(null, "El cliente ya existe en el sistema");
				 return;
			 }
			
			this.cliente.insert(cliente);
			JOptionPane.showMessageDialog(null, "Se agrego el nuevo cliente al sistema");
			
			borrarDatosDeLosText();
			
		}
	}	
	
	public boolean clienteYaExiste() {
		this.listaClientes = (ArrayList<ClienteDTO>) this.cliente.readAll();
		String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
		for(ClienteDTO c: this.listaClientes) {
			if(c.getCUIL().equals(CUIL)){
				return true;
			}
		}
		return false;
	}
	
	
	public boolean todosLosCamposSonValidos() {
		String nombre = this.ventanaAltaCliente.getTextNombre().getText();
		if(nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre no puede ser vacio");
			return false;
		}
		String apellido = this.ventanaAltaCliente.getTextApellido().getText();
		if(apellido.equals("")) {
			JOptionPane.showMessageDialog(null, "El apellido no puede ser vacio");
			return false;
		}
		String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
		if(CUIL.equals("")) {
			JOptionPane.showMessageDialog(null, "El CUIL no puede ser vacio");
			return false;
		}
		String mail = this.ventanaAltaCliente.getTextCorreo().getText();
		boolean m = mail.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if(!m) {
			JOptionPane.showMessageDialog(null, "El formato de mail es incorrecto");
			return false;
		}
		if(this.ventanaAltaCliente.getComboBoxImpuestoAFIP().getSelectedItem()=="Sin seleccionar") {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de impuesto AFIP");
			return false;
		}
		if(this.ventanaAltaCliente.getComboBoxTipoCliente().getSelectedItem()=="Sin seleccionar") {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de cliente");
			return false;
		}
		String saldoInicial = this.ventanaAltaCliente.getTextSaldoInicial().getText();
		if(saldoInicial.equals("")) {
			JOptionPane.showMessageDialog(null, "El saldo inicial no debe estar vacio");
			return false;
		}
		String pais = this.ventanaAltaCliente.getTextPais().getText();
		if(pais.equals("")) {
			JOptionPane.showMessageDialog(null, "El pais no puede ser vacio");
			return false;
		}
		String prov = this.ventanaAltaCliente.getTextProvincia().getText();
		if(prov.equals("")) {
			JOptionPane.showMessageDialog(null, "La provincia no puede ser vacia");
			return false;
		}
		String localida = this.ventanaAltaCliente.getTextLocalidad().getText();
		if(localida.equals("")) {
			JOptionPane.showMessageDialog(null, "La localidad no puede ser vacia");
			return false;
		}
		String calle = this.ventanaAltaCliente.getTextCalle().getText();
		if(calle.equals("")) {
			JOptionPane.showMessageDialog(null, "La calle no puede ser vacia");
			return false;
		}
		String altura = this.ventanaAltaCliente.getTextAltura().getText();
		if(altura.equals("")) {
			JOptionPane.showMessageDialog(null, "La altura no puede ser vacia");
			return false;
		}
		String codPostal = this.ventanaAltaCliente.getTextCodPostal().getText();
		if(codPostal.equals("")) {
			JOptionPane.showMessageDialog(null, "El cod postal no puede ser vacio");
			return false;
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
		
		//pais,prov,localidd,calle,altura,codpostal
		this.ventanaAltaCliente.getTextPais().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		});
		this.ventanaAltaCliente.getTextProvincia().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		});
		this.ventanaAltaCliente.getTextLocalidad().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		});
//		this.ventanaAltaCliente.getTextCalle().addKeyListener(new KeyAdapter() {
//			public void keyTyped(KeyEvent e) {
//				ValidadorTeclado.aceptarLetrasNumerosYEspacios(e);
//			}
//		});
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
	}
	
	
	public void cargarComboBoxes() {
		String[] tipoCliente = {"Mayorista","Minorista"};
		String[] impuestoAFIP = {"Responsable Inscripto","Monotributista","Exento"};
		
		for(int i=0; i<tipoCliente.length;i++) {
			this.ventanaAltaCliente.getComboBoxTipoCliente().addItem(tipoCliente[i]);
		}
		for(int i=0; i<impuestoAFIP.length; i++) {
			this.ventanaAltaCliente.getComboBoxImpuestoAFIP().addItem(impuestoAFIP[i]);
		}		
	}

	
	
	public String getIdItemImpuestoAFIP(String descr) {
		if(descr.equals("Responsable Inscripto")) {
			return "RI";
		}
		if(descr.equals("Monotributista")) {
			return "M";
		}
		if(descr.equals("Exento")) {
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
		this.ventanaAltaCliente.getTextPais().setText("");
		this.ventanaAltaCliente.getTextProvincia().setText("");
		this.ventanaAltaCliente.getTextLocalidad().setText("");
		this.ventanaAltaCliente.getTextCodPostal().setText("");
	}
	
}
