package presentacion.controlador.supervisor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProveedorDTO;
import modelo.Proveedor;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Supervisor.VentanaAltaProveedor;

public class ControladorAltaProveedor {

	VentanaAltaProveedor ventanaAltaProveedor;
	
	ControladorGestionarProveedores controladorGestionarProveedores;

	ProveedorDTO proveedorAEditar;
	
	Proveedor proveedor;
	List<ProveedorDTO> todosLosProveedores;
	
	public ControladorAltaProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public void inicializar() {
		this.ventanaAltaProveedor = new VentanaAltaProveedor();
		this.todosLosProveedores = this.proveedor.readAll();
		this.ventanaAltaProveedor.getBtnRegistrar().addActionListener(a -> registrarProveedor());
		this.ventanaAltaProveedor.getBtnRegresar().addActionListener(a -> salir());
		
		this.ventanaAltaProveedor.getBtnEditar().addActionListener(a -> editarProveedor());
		validarTeclado();
	}
	
	public void setControladorGestionarProveedores(	ControladorGestionarProveedores controladorGestionarProveedores) {
		this.controladorGestionarProveedores = controladorGestionarProveedores;
	}
	
	public void setProveedorAEditar(ProveedorDTO prov) {
		this.proveedorAEditar=prov;
	}
	
	public void mostrarVentanaRegistrar() {
		this.ventanaAltaProveedor.getLblRegistrarProducto().setVisible(true);
		this.ventanaAltaProveedor.getBtnRegistrar().setVisible(true);
		this.ventanaAltaProveedor.show();
	}

	public void mostarVentanaEditar() {
		this.ventanaAltaProveedor.getLblEditar().setVisible(true);
		this.ventanaAltaProveedor.getBtnEditar().setVisible(true);
		setearDatos();
		
		this.ventanaAltaProveedor.show();
	}
	
	public void cerrarVentana() {
		this.ventanaAltaProveedor.cerrar();
	}
	
	public void salir() {
		this.proveedorAEditar = null;
		this.ventanaAltaProveedor.cerrar();
		this.controladorGestionarProveedores.inicializar();
		this.controladorGestionarProveedores.mostrarVentana();
	}
	
	public void setearDatos() {
		this.ventanaAltaProveedor.getTextNombre().setText(this.proveedorAEditar.getNombre());
		this.ventanaAltaProveedor.getTextCorreo().setText(this.proveedorAEditar.getCorreo());
		this.ventanaAltaProveedor.getTextLimiteCredito().setText(""+this.proveedorAEditar.getLimiteCredito());
	}
	
	
	public void registrarProveedor() {
		if(todosLosCamposSonValidos()) {
			String nombre = this.ventanaAltaProveedor.getTextNombre().getText();
			String correo = this.ventanaAltaProveedor.getTextCorreo().getText();
			double limiteCredito =Double.parseDouble(this.ventanaAltaProveedor.getTextLimiteCredito().getText());
			ProveedorDTO proveedorNuevo = new ProveedorDTO(0,nombre,correo,limiteCredito);
			
			if(yaExisteProveedor(proveedorNuevo)) {
				JOptionPane.showMessageDialog(null, "Ya existe un proveedor con este nombre");
				return;
			}
			
			boolean insert = this.proveedor.insert(proveedorNuevo);
			if(!insert) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar el proveedor");			
			}else {
				JOptionPane.showMessageDialog(null, "Proveedor agregado con exito");
				this.todosLosProveedores = this.proveedor.readAll();
				limpiarCampos();
			}
			
		}
	}
	
	public boolean yaExisteProveedor(ProveedorDTO proveedor) {
		for(ProveedorDTO p: this.todosLosProveedores) {
			if(proveedor.getNombre().equals(p.getNombre())) {
				return true;
			}
		}return false;
	}
	
	
	public boolean todosLosCamposSonValidos() {
		String nombre = this.ventanaAltaProveedor.getTextNombre().getText();
		if(nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre no puede ser vacio");
			return false;
		}
		if(nombre.length()>=44) {
			JOptionPane.showMessageDialog(null, "La cantidad de caracteres de nombre no puede ser mayor a 44");
			return false;	
		}
		
		String mail = this.ventanaAltaProveedor.getTextCorreo().getText();
		boolean m = mail.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if(!m) {
			JOptionPane.showMessageDialog(null, "El formato de mail es incorrecto");
			return false;
		}
		if(mail.length()>=44) {
			JOptionPane.showMessageDialog(null, "La cantidad de caracteres de mail no puede ser mayor a 44");
			return false;	
		}
		
		String limiteDeCredit = this.ventanaAltaProveedor.getTextLimiteCredito().getText();
		if(limiteDeCredit.equals("")) {
			JOptionPane.showMessageDialog(null, "El limite de credito no puede ser vacio");
			return false;		
		}else{
			double limiteDeCredito = 0;
			try {
				limiteDeCredito = Double.parseDouble(limiteDeCredit);
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Debe establecer un numero para el limite de credito");
				return false;
			}
			
			if(limiteDeCredito<0) {
				JOptionPane.showMessageDialog(null, "El limite de credito no puede ser menor a 0");
				return false;	
			}
			if(limiteDeCredito>999999) {
				JOptionPane.showMessageDialog(null, "El limite de credito no puede ser mayor a 999999");
				return false;		
			}
		}
		
		
		return true;
	}
	
	public void validarTeclado() {
		this.ventanaAltaProveedor.getTextNombre().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasNumerosYEspacios(e);
			}
		}));
		this.ventanaAltaProveedor.getTextCorreo().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarMinusculaDigitoPuntoArrobaYGuiones(e);
			}
		}));
		this.ventanaAltaProveedor.getTextLimiteCredito().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
	}
	
	public void editarProveedor() {
		if(todosLosCamposSonValidos()) {
			String nombre = this.ventanaAltaProveedor.getTextNombre().getText();
			String correo = this.ventanaAltaProveedor.getTextCorreo().getText();
			double limiteCredito =Double.parseDouble(this.ventanaAltaProveedor.getTextLimiteCredito().getText());
			ProveedorDTO proveedorNuevo = new ProveedorDTO(0,nombre,correo,limiteCredito);
			
			if(!nombre.equals(this.proveedorAEditar.getNombre())) {
				for(ProveedorDTO p: this.todosLosProveedores) {
					if(p.getNombre().equals(nombre) && p.getId()!=this.proveedorAEditar.getId()) {
						JOptionPane.showMessageDialog(null, "Ya existe un proveedor con este nombre");
						return;
					}
				}	
			}
			
			
			boolean update = this.proveedor.update(proveedorNuevo,this.proveedorAEditar.getId());
			if(!update) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error al editar el proveedor");			
			}else {
				JOptionPane.showMessageDialog(null, "Proveedor editado con exito");
				this.todosLosProveedores = this.proveedor.readAll();
				
			}
			
		}		
	}
	
	public void limpiarCampos() {
		this.ventanaAltaProveedor.getTextNombre().setText("");
		this.ventanaAltaProveedor.getTextCorreo().setText("");
		this.ventanaAltaProveedor.getTextLimiteCredito().setText("");		
	}
	
}
