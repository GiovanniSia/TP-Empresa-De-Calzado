package presentacion.controlador.supervisor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import dto.MaestroProductoDTO;
import modelo.MaestroProducto;
import modelo.Proveedor;
import presentacion.controlador.ValidadorTeclado;
import presentacion.vista.Supervisor.VentanaAltaProducto;

public class ControladorAltaProducto {

	
	MaestroProducto maestroProducto;
	List<MaestroProductoDTO> todosLosProductos;
	
	VentanaAltaProducto ventanaAltaProducto;
	
	Proveedor proveedor;
	
	
	public ControladorAltaProducto(MaestroProducto maestroProducto) {
		this.maestroProducto=maestroProducto;
		this.todosLosProductos = new ArrayList<MaestroProductoDTO>();
	}
	
	public void inicializar() {
		this.ventanaAltaProducto = new VentanaAltaProducto();
		
		this.todosLosProductos = this.maestroProducto.readAll();
		
		validarTeclado();
	}
	
	public void mostrarVentana() {
		this.ventanaAltaProducto.show();
	}
	
	
	
	public void llenarCb() {
		String[] cbTipo = {"Producto Terminado","Materia Prima"};
		String[] cbFabricado = {"Si","No"};
		String[] proveedoresRegistrados;
	}
	
	
	public void validarTeclado() {

		this.ventanaAltaProducto.getTextDescripcion().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaAltaProducto.getTextCosto().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		}));
		this.ventanaAltaProducto.getTextPrecioMinorista().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		}));
		this.ventanaAltaProducto.getTextPrecioMayorista().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumerosYpuntos(e);
			}
		}));
		this.ventanaAltaProducto.getTextPuntoRepMinimo().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
		this.ventanaAltaProducto.getTextTalle().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloLetras(e);
			}
		}));
		this.ventanaAltaProducto.getTextUnidadMedida().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarLetrasYEspacios(e);
			}
		}));
		this.ventanaAltaProducto.getTextDiasParaReponer().addKeyListener((new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ValidadorTeclado.aceptarSoloNumeros(e);
			}
		}));
	}
	
	
}
