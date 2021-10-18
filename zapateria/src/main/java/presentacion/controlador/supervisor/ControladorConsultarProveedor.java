package presentacion.controlador.supervisor;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProveedorDTO;
import modelo.ProductoDeProveedor;
import modelo.Proveedor;
import presentacion.vista.Supervisor.VentanaConsultarProveedores;

public class ControladorConsultarProveedor {

	Proveedor  proveedor;
	ProductoDeProveedor prodProveedor;
	
	List<ProveedorDTO> todosLosProveedores;
	
	VentanaConsultarProveedores ventanaConsultarProveedores;
	
	ControladorAltaProducto controladorAltaProducto;
	
	public ControladorConsultarProveedor(Proveedor proveedor,ProductoDeProveedor prodProveedor) {
		this.proveedor = proveedor;
		this.prodProveedor = prodProveedor;
		
		this.todosLosProveedores = new ArrayList<ProveedorDTO>();
	}
	
	public void setControladorAltaProducto(ControladorAltaProducto controladorAltaProducto) {
		this.controladorAltaProducto=controladorAltaProducto;
	}
	
	public void inicializar() {
		this.ventanaConsultarProveedores = new VentanaConsultarProveedores();
		
		
		this.ventanaConsultarProveedores.getBtnSeleccionarProveedor().addActionListener(a -> seleccionarProveedor(a));
		
		this.todosLosProveedores = this.proveedor.readAll();
		llenarTabla();
		
	}
	public void mostrarVentanaParaAltaProducto() {
		this.ventanaConsultarProveedores.getBtnSeleccionarProveedor().setVisible(true);
		this.ventanaConsultarProveedores.show();
	}
	public void mostrarBotonEditar() {
		this.ventanaConsultarProveedores.getBtnEditarProveedor().setVisible(true);
	}
	
	
	public void llenarTabla() {
//		"Nombre","Correo","Limite de crédito","Credito disponible"
		for(ProveedorDTO p: this.todosLosProveedores) {
			String nombre = p.getNombre();
			String correo = p.getCorreo();
			String limiteCred = ""+p.getLimiteCredito();
			String credDisp =""+p.getCreditoDisponible();
			String[] filas= {nombre,correo,limiteCred,credDisp};
			this.ventanaConsultarProveedores.getModelTablaProveedores().addRow(filas);
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
		this.controladorAltaProducto.inicializar();
		this.controladorAltaProducto.mostrarVentana();
		
	}
	
}
