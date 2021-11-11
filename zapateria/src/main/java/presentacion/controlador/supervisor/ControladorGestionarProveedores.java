package presentacion.controlador.supervisor;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProductoDeProveedorDTO;
import dto.ProveedorDTO;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import presentacion.controlador.Controlador;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Supervisor.VentanaGestionarProveedores;

public class ControladorGestionarProveedores {

	Proveedor  proveedor;
	ProductoDeProveedor prodProveedor;
	
	List<ProveedorDTO> todosLosProveedores;
	
	List<ProveedorDTO> proveedorEnTabla;
	
	VentanaGestionarProveedores ventanaConsultarProveedores;
	
	ControladorAltaProducto controladorAltaProducto;
	
	ControladorAsignarProductoAProveedor controladorAsignarProductoAProveedor;
	
	Controlador controlador;
	
	ControladorAltaProveedor controladorAltaProveedor;
	
	public ControladorGestionarProveedores(Controlador controlador,Proveedor proveedor,ProductoDeProveedor prodProveedor) {
		this.proveedor = proveedor;
		this.prodProveedor = prodProveedor;
		
		this.todosLosProveedores = new ArrayList<ProveedorDTO>();
		this.proveedorEnTabla = new ArrayList<ProveedorDTO>();
		this.controlador=controlador;
	}
//	public ControladorGestionarProveedores(Proveedor proveedor,ProductoDeProveedor prodProveedor) {
//		this.proveedor = proveedor;
//		this.prodProveedor = prodProveedor;
//		
//		this.todosLosProveedores = new ArrayList<ProveedorDTO>();
//	}
	
	
	public void setControladorAltaProducto(ControladorAltaProducto controladorAltaProducto) {
		this.controladorAltaProducto=controladorAltaProducto;
	}
	
	public void setControladorAsignarProductoAProveedor(ControladorAsignarProductoAProveedor controladorAsignarProductoAProveedor) {
		this.controladorAsignarProductoAProveedor=controladorAsignarProductoAProveedor;
	}
	
	public void setControladorAltaProveedor(ControladorAltaProveedor controladorAltaProveedor) {
		this.controladorAltaProveedor = controladorAltaProveedor;
	}
	
	public void inicializar() {
		this.ventanaConsultarProveedores = new VentanaGestionarProveedores();
		
		
		//ESTE ES PARA DAR DE ALTA PROD
		this.ventanaConsultarProveedores.getBtnSeleccionarProveedor().addActionListener(a -> seleccionarProveedor(a));
		
		//ESTE ES PARA ASIGNAR UN PROD AL PROV
		this.ventanaConsultarProveedores.getBtnAsignarProdAProveedor().addActionListener(a -> pasarAAsignarProductoAProveedor(a));
		
		this.ventanaConsultarProveedores.getBtnRegresar().addActionListener(a -> cerrarVentana(a));
		
		
		this.ventanaConsultarProveedores.getTextNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarBusqueda();
			}
		});
		
		this.ventanaConsultarProveedores.getBtnAniadir().addActionListener(a -> pasarAAgregarProveedor());
	
		this.ventanaConsultarProveedores.getBtnEditar().addActionListener(a -> pasarAEditarProveedor());
		
		this.todosLosProveedores = this.proveedor.readAll();
		llenarTabla();
		
	}
	
	public void cerrarVentana(ActionEvent a) {
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
		this.proveedorEnTabla.removeAll(this.proveedorEnTabla);
		
//		"Nombre","Correo","Limite de crédito","Credito disponible"
		for(ProveedorDTO p: proveedoresAprox) {
			agregarATabla(p);
		}
		
	}
	
	
	public void agregarATabla(ProveedorDTO p) {
		String id = ""+p.getId();
		String nombre = p.getNombre();
		String correo = p.getCorreo();
		String credDisp =""+p.getLimiteCredito();
		String[] filas= {id,nombre,correo,credDisp};
		this.ventanaConsultarProveedores.getModelTablaProveedores().addRow(filas);
		this.proveedorEnTabla.add(p);
	}
	
	public void mostrarVentanaParaAltaProducto() {
		this.ventanaConsultarProveedores.getBtnSeleccionarProveedor().setVisible(true);
		this.ventanaConsultarProveedores.show();
	}
	
	public void mostrarVentana() {
		this.ventanaConsultarProveedores.getBtnAsignarProdAProveedor().setVisible(true);
		this.ventanaConsultarProveedores.getBtnAniadir().setVisible(true);
		this.ventanaConsultarProveedores.getBtnEditar().setVisible(true);
		this.ventanaConsultarProveedores.show();
	}
	
	public void mostrarBotonEditar() {
		this.ventanaConsultarProveedores.getBtnAsignarProdAProveedor().setVisible(true);
	}
	
	
	public void llenarTabla() {
		this.ventanaConsultarProveedores.getModelTablaProveedores().setRowCount(0);//borrar datos de la tabla
		this.ventanaConsultarProveedores.getModelTablaProveedores().setColumnCount(0);
		this.ventanaConsultarProveedores.getModelTablaProveedores().setColumnIdentifiers(this.ventanaConsultarProveedores.getNombreColumnasProveedores());
		this.proveedorEnTabla.removeAll(this.proveedorEnTabla);
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
		
		this.controladorAltaProducto.aniadirProveedor(proveedorElegido);
		
//		this.ventanaConsultarProveedores.cerrar();
		
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

	
	public void pasarAAgregarProveedor() {
		this.ventanaConsultarProveedores.cerrar();
		this.controladorAltaProveedor.inicializar();
		this.controladorAltaProveedor.mostrarVentanaRegistrar();
	}

	public void pasarAEditarProveedor() {
		int filaSeleccionada = this.ventanaConsultarProveedores.getTable().getSelectedRow();
		if(filaSeleccionada==-1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor");
			return;
		}
		ProveedorDTO proveedorElegido = this.proveedorEnTabla.get(filaSeleccionada);
		this.controladorAltaProveedor.setProveedorAEditar(proveedorElegido);
		
		this.ventanaConsultarProveedores.cerrar();
		this.controladorAltaProveedor.inicializar();
		this.controladorAltaProveedor.mostarVentanaEditar();
		
	}
	
}
