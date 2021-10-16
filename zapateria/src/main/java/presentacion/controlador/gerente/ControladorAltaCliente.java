package presentacion.controlador.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import modelo.Cliente;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.gerente.VentanaAltaCliente;

public class ControladorAltaCliente {

	double limiteCreditoPorDefecto=1300;
	
	VentanaAltaCliente ventanaAltaCliente;
	
	Cliente cliente;
	ArrayList<ClienteDTO> listaClientes;
	
	public ControladorAltaCliente(Cliente cliente) {
		this.cliente=cliente;
		this.listaClientes = new ArrayList<ClienteDTO>();
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
	}

	public void registrarCliente(ActionEvent a) {
		if(!todosLosCamposSonValidos() && !clienteYaExiste()) {
			
			String nombre = this.ventanaAltaCliente.getTextNombre().getText();
			String apellido = this.ventanaAltaCliente.getTextApellido().getText();
			String CUIL = this.ventanaAltaCliente.getTextCUIL().getText();
			String mail = this.ventanaAltaCliente.getTextCorreo().getText();
			int limiteCredito =(int) this.limiteCreditoPorDefecto;
			int creditoDisp = (int)this.limiteCreditoPorDefecto;
			String tipoCliente =(String) this.ventanaAltaCliente.getComboBoxTipoCliente().getSelectedItem();
			String impuestoAFIP = (String) this.ventanaAltaCliente.getComboBoxImpuestoAFIP().getSelectedItem();
			String estado = "Activo";
			String calle = this.ventanaAltaCliente.getTextFieldCalle().getText();
			String altura = this.ventanaAltaCliente.getTextFieldAltura().getText();
			String pais = this.ventanaAltaCliente.getTextFieldPais().getText();
			String prov = this.ventanaAltaCliente.getTextFieldProvincia().getText();
			String localida = this.ventanaAltaCliente.getTextFieldLocalidad().getText();
			String codPostal = this.ventanaAltaCliente.getTextFieldCodPostal().getText();
			
			ClienteDTO cliente = new ClienteDTO(0,nombre,apellido,CUIL,mail,limiteCredito,creditoDisp,tipoCliente,impuestoAFIP,estado,calle,altura,pais,prov,localida,codPostal);
			
			this.cliente.insert(cliente);
			JOptionPane.showMessageDialog(null, "Se agrego el nuevo cliente al sistema");
		}
	}	
	
	public boolean clienteYaExiste() {
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
		if(!m && !mail.equals("")) {
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
		String pais = this.ventanaAltaCliente.getTextFieldPais().getText();
		if(pais.equals("")) {
			JOptionPane.showMessageDialog(null, "El pais no puede ser vacio");
			return false;
		}
		String prov = this.ventanaAltaCliente.getTextFieldProvincia().getText();
		if(prov.equals("")) {
			JOptionPane.showMessageDialog(null, "La provincia no puede ser vacia");
			return false;
		}
		String localida = this.ventanaAltaCliente.getTextFieldLocalidad().getText();
		if(localida.equals("")) {
			JOptionPane.showMessageDialog(null, "La localidad no puede ser vacia");
			return false;
		}
		String calle = this.ventanaAltaCliente.getTextFieldCalle().getText();
		if(calle.equals("")) {
			JOptionPane.showMessageDialog(null, "La calle no puede ser vacia");
			return false;
		}
		String altura = this.ventanaAltaCliente.getTextFieldAltura().getText();
		if(altura.equals("")) {
			JOptionPane.showMessageDialog(null, "La altura no puede ser vacia");
			return false;
		}
		String codPostal = this.ventanaAltaCliente.getTextFieldCodPostal().getText();
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
		//pais,prov,localidd,calle,altura,codpostal
		this.ventanaAltaCliente.getTextFieldPais().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		});
		this.ventanaAltaCliente.getTextFieldProvincia().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		});
		this.ventanaAltaCliente.getTextFieldLocalidad().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		});
		this.ventanaAltaCliente.getTextFieldCalle().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		});
		this.ventanaAltaCliente.getTextFieldAltura().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		});
		this.ventanaAltaCliente.getTextFieldCodPostal().addKeyListener(new KeyAdapter() {
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

	
	public static void main(String[] args) {
		Cliente cliente = new Cliente(new DAOSQLFactory());
		ControladorAltaCliente c = new ControladorAltaCliente(cliente);
		c.inicializar();
		c.mostrarVentana();
	}
	
}
