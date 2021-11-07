package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProveedorDTO;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import presentacion.controlador.Controlador;
import presentacion.vista.Supervisor.VentanaConsultarProveedores;

public class ControladorConsultarProveedor {

	Proveedor  proveedor;
	ProductoDeProveedor prodProveedor;
	
	List<ProveedorDTO> todosLosProveedores;
	
	VentanaConsultarProveedores ventanaConsultarProveedores;
	
	ControladorAltaProducto controladorAltaProducto;
	
	ControladorAsignarProductoAProveedor controladorAsignarProductoAProveedor;
	
	Controlador controlador;
	
	public ControladorConsultarProveedor(Controlador controlador,Proveedor proveedor,ProductoDeProveedor prodProveedor) {
		this.proveedor = proveedor;
		this.prodProveedor = prodProveedor;
		
		this.todosLosProveedores = new ArrayList<ProveedorDTO>();
		this.controlador=controlador;
	}
	
	public void setControladorAltaProducto(ControladorAltaProducto controladorAltaProducto) {
		this.controladorAltaProducto=controladorAltaProducto;
	}
	
	public void setControladorAsignarProductoAProveedor(ControladorAsignarProductoAProveedor controladorAsignarProductoAProveedor) {
		this.controladorAsignarProductoAProveedor=controladorAsignarProductoAProveedor;
	}
	
	public void inicializar() {
		this.ventanaConsultarProveedores = new VentanaConsultarProveedores();
		
		
		//ESTE ES PARA DAR DE ALTA PROD
		this.ventanaConsultarProveedores.getBtnSeleccionarProveedor().addActionListener(a -> seleccionarProveedor(a));
		
		//ESTE ES PARA ASIGNAR UN PROD AL PROV
		this.ventanaConsultarProveedores.getBtnEditarProveedor().addActionListener(a -> pasarAAsignarProductoAProveedor(a));
		
		this.ventanaConsultarProveedores.getBtnRegresar().addActionListener(a -> cerrarVentanaParaAltaProd(a));
		
		
		this.ventanaConsultarProveedores.getTextNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarBusqueda();
			}
		});
		
		this.todosLosProveedores = this.proveedor.readAll();
		llenarTabla();
		
	}
	
	public void cerrarVentanaParaAltaProd(ActionEvent a) {
		//si el boton seleccionar prov es visible significa que se abrio la ventana desde alta prod
		if(this.ventanaConsultarProveedores.getBtnSeleccionarProveedor().isVisible()) {
			this.ventanaConsultarProveedores.cerrar();
		}else {
			this.ventanaConsultarProveedores.cerrar();
			this.controlador.mostrarVentanaMenuDeSistemas();
		}
		
	}
	
	
	public void filtrarBusqueda() {
		String nombre = this.ventanaConsultarProveedores.getTextNombre().getText();
		
		ArrayList<ProveedorDTO> proveedoresAprox = (ArrayList<ProveedorDTO>) this.proveedor.getProveedorAproximado("Nombre", nombre);
		escribirTablaFiltrada(proveedoresAprox);
	}
	
	public void escribirTablaFiltrada(ArrayList<ProveedorDTO> proveedoresAprox) {
		this.ventanaConsultarProveedores.getModelTablaProveedores().setRowCount(0);//borrar datos de la tabla
		this.ventanaConsultarProveedores.getModelTablaProveedores().setColumnCount(0);
		this.ventanaConsultarProveedores.getModelTablaProveedores().setColumnIdentifiers(this.ventanaConsultarProveedores.getNombreColumnasProveedores());
		
//		"Nombre","Correo","Limite de crédito","Credito disponible"
		for(ProveedorDTO p: proveedoresAprox) {
			agregarATabla(p);
		}
		
	}
	
	
	public void agregarATabla(ProveedorDTO p) {
		String id = ""+p.getId();
		String nombre = p.getNombre();
		String correo = p.getCorreo();
		String credDisp =""+p.getCreditoDisponible();
		String[] filas= {id,nombre,correo,credDisp};
		this.ventanaConsultarProveedores.getModelTablaProveedores().addRow(filas);
	}
	
	public void mostrarVentanaParaAltaProducto() {
		this.ventanaConsultarProveedores.getBtnSeleccionarProveedor().setVisible(true);
		this.ventanaConsultarProveedores.show();
	}
	
	public void mostrarVentana() {
		this.ventanaConsultarProveedores.getBtnEditarProveedor().setVisible(true);
		this.ventanaConsultarProveedores.show();
	}
	
	public void mostrarBotonEditar() {
		this.ventanaConsultarProveedores.getBtnEditarProveedor().setVisible(true);
	}
	
	
	public void llenarTabla() {
		this.ventanaConsultarProveedores.getModelTablaProveedores().setRowCount(0);//borrar datos de la tabla
		this.ventanaConsultarProveedores.getModelTablaProveedores().setColumnCount(0);
		this.ventanaConsultarProveedores.getModelTablaProveedores().setColumnIdentifiers(this.ventanaConsultarProveedores.getNombreColumnasProveedores());
		
//		"Nombre","Correo","Limite de crédito","Credito disponible"
		for(ProveedorDTO p: this.todosLosProveedores) {
			agregarATabla(p);
		}
	}
	
	public void seleccionarProveedor(ActionEvent a) {
		int filaSeleccionada = this.ventanaConsultarProveedores.getTable().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun proveedor");
			return;
		}
		ProveedorDTO proveedorElegido = this.todosLosProveedores.get(filaSeleccionada);
		this.controladorAltaProducto.establecerProveedorElegido(proveedorElegido);
		
		this.ventanaConsultarProveedores.cerrar();
		
	}
	
	public void pasarAAsignarProductoAProveedor(ActionEvent a) {
		int filaSeleccionada = this.ventanaConsultarProveedores.getTable().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningun proveedor");
			return;
		}
		ProveedorDTO provSeleccionado = this.todosLosProveedores.get(filaSeleccionada);
		this.controladorAsignarProductoAProveedor.establecerProveedorElegido(provSeleccionado);
		
		this.ventanaConsultarProveedores.cerrar();
		this.controladorAsignarProductoAProveedor.inicializar();
		this.controladorAsignarProductoAProveedor.mostrarVentana();
		
		
	}
	
}
