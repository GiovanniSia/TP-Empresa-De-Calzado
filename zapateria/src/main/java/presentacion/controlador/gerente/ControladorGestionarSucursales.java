package presentacion.controlador.gerente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import dto.SucursalDTO;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.gerente.VentanaGestionarSucursales;

public class ControladorGestionarSucursales {

	Sucursal sucursal;
	
	List<SucursalDTO> todasLasSucursales;
	List<SucursalDTO> sucursalesEnTabla;
	VentanaGestionarSucursales ventanaGestionarSucursales;
	
	public ControladorGestionarSucursales(Sucursal sucursal) {
		this.sucursal = sucursal;
		
		this.todasLasSucursales = new ArrayList<SucursalDTO>();
		this.sucursalesEnTabla = new ArrayList<SucursalDTO>();
	}
	
	public void inicializar() {
		this.ventanaGestionarSucursales = new VentanaGestionarSucursales();
		this.todasLasSucursales = this.sucursal.readAll();
		
		this.ventanaGestionarSucursales.getBtnRegresar().addActionListener(a -> salir());
		
		this.ventanaGestionarSucursales.getTextNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});		
		llenarTablaCompleta();
		
	}
	
	public void mostrarVentana() {
		this.ventanaGestionarSucursales.show();
	}
	
	public void cerrarVentana() {
		this.ventanaGestionarSucursales.cerrar();
	}

	public void salir() {
		this.ventanaGestionarSucursales.cerrar();
	}
	
	public void llenarTablaCompleta(){
		limpiarTabla();
		
		for(SucursalDTO s: this.todasLasSucursales) {
			agregarATabla(s);
		}
	}
	
	public void realizarBusqueda() {
		String nombre = this.ventanaGestionarSucursales.getTextNombre().getText();
		
		ArrayList<SucursalDTO> listaFiltrada = (ArrayList<SucursalDTO>) this.sucursal.obtenerListaFiltrada("Nombre",nombre);
		llenarTablaFiltrada(listaFiltrada);
	}
	
	
	public void llenarTablaFiltrada(ArrayList<SucursalDTO> listaFiltrada) {
		limpiarTabla();
		for(SucursalDTO s: listaFiltrada) {
			agregarATabla(s);
		}
		
	}

	public void agregarATabla(SucursalDTO s) {
		int id=s.getIdSucursal();
		String nombre = s.getNombre();
		String tel = s.getTelefono();
		String calle = s.getCalle();
		String altura = s.getAltura();
		String prov = s.getProvincia();
		String loc = s.getLocalidad();
		String pais = s.getPais();
		String codPostal = s.getCodigoPostal();
		Object[] fila = {id,nombre,tel,calle,altura,prov,loc,pais,codPostal};
		this.ventanaGestionarSucursales.getModelTablaSucursales().addRow(fila);
		this.sucursalesEnTabla.add(s);	
	}
	
	public void limpiarTabla() {
		this.ventanaGestionarSucursales.getModelTablaSucursales().setRowCount(0);
		this.ventanaGestionarSucursales.getModelTablaSucursales().setColumnCount(0);
		this.ventanaGestionarSucursales.getModelTablaSucursales().setColumnIdentifiers(this.ventanaGestionarSucursales.getNombreColumnas());
		this.sucursalesEnTabla.removeAll(this.sucursalesEnTabla);
	}
	
	public static void main(String[] args) {
		Sucursal s = new Sucursal(new DAOSQLFactory());
		ControladorGestionarSucursales c = new ControladorGestionarSucursales(s);
		c.inicializar();
		c.mostrarVentana();
	}
	
}

