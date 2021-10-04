package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import dto.MaestroProductoDTO;
import dto.StockDTO;
import modelo.MaestroProducto;
import modelo.Stock;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.vistaBusquedaProductos;

public class ControladorBusquedaProductos {
	
	
	Stock stock;
	List<StockDTO> listaStock;
	Sucursal sucursal;
	
	vistaBusquedaProductos vistaBusquedaProductos;
	
	List<MaestroProductoDTO> listaMaestroProducto;
	MaestroProducto maestroProducto;
	
//	public ControladorBusquedaProductos(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal) {
//		this.maestroProducto = maestroProducto;
//		this.stock = stock;
//		this.sucursal = sucursal;
//	}
	public ControladorBusquedaProductos() {
		inicializar();
	}
	
	
	public void inicializar() {
		this.vistaBusquedaProductos = new vistaBusquedaProductos();
		
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.listaMaestroProducto = this.maestroProducto.readAll();
		
		this.stock = new Stock(new DAOSQLFactory());
		this.listaStock = this.stock.readAll();
		
		this.vistaBusquedaProductos.getBtnBuscar().addActionListener(a -> realizarBusqueda(a));
		
		
		
		this.vistaBusquedaProductos.show();
		}
	
	
	//Busca el producto dado el nombre y el idSucursal
	public void realizarBusqueda(ActionEvent a) {
		String txtNombre = this.vistaBusquedaProductos.getTxtNombreProducto().getText();
		String txtSucu = this.vistaBusquedaProductos.getTxtIdSucursal().getText();
		
		if(txtNombre.equals("") &&  txtSucu == null) {
//			nombreVacio pero sucursal no
			System.out.println("el nombre esta vacio y el id sucursal vacio");
//			escribirTablaCompleta();
			return;
		}
		
		if(txtNombre.equals("") && txtSucu != null) {
			//ambos vacios
			System.out.println("el nombre esta vacio y el idSucursal lleno");
//			escribirTablaCompleta();
			return;
		}
		
		if(txtNombre!=null && txtSucu == null) {
			System.out.println("el nombre esta lleno y el idSucursal vacio");
			//nombreCompleto pero IdSucursal no
			return;
		}
		
		int txtSucursal = Integer.parseInt(this.vistaBusquedaProductos.getTxtIdSucursal().getText());
		
		this.vistaBusquedaProductos.getModelTabla().setRowCount(0);//borrar datos de la tabla
		this.vistaBusquedaProductos.getModelTabla().setColumnCount(0);
		this.vistaBusquedaProductos.getModelTabla().setColumnIdentifiers(this.vistaBusquedaProductos.getNombreColumnas());
		


			
		//caso todos los campos complt
		List<MaestroProductoDTO> productosPosibles = this.maestroProducto.getMaestroProducto(txtNombre);//posibles valores

		
		
		
		//caso todos los campos completos
		for(MaestroProductoDTO m: productosPosibles) {
			for(StockDTO s: this.listaStock) {
				if(txtSucursal == s.getIdSucursal() && s.getIdProducto() == m.getIdMaestroProducto()) {
					
					String descr = m.getDescripcion();
					String codigoLote = s.getCodigoLote();
					int stockDisp = s.getStockDisponible();
					String tipo = m.getTipo();
					String fabricado = m.getFabricado();
					int precioCosto = m.getPrecioCosto();
					int precioVenta = m.getPrecioVenta();
					int puntoRepositorio = m.getPuntoRepositorio();
					
					Object[] fila = { descr,codigoLote,stockDisp,tipo,fabricado,precioCosto,precioVenta,puntoRepositorio };
					this.vistaBusquedaProductos.getModelTabla().addRow(fila);
				}
			}
		}
					

			
				
			
		
		System.out.println("por lo menos se ejecuto");
		
	}	
	
	public void escribirTablaCompleta() {
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: this.listaMaestroProducto) {
				if(s.getIdProducto()== m.getIdMaestroProducto()) {
					String descr = m.getDescripcion();
					String codLote = s.getCodigoLote(); 
					int stockDisp = s.getStockDisponible();
					String tipo = m.getTipo();
					String fabricado = m.getFabricado();
					int precioCosto = m.getPrecioCosto();
					int precioVenta = m.getPrecioVenta();
					int puntoRepositorio = m.getPuntoRepositorio();
					
					Object[] fila = { descr,codLote,stockDisp,tipo,fabricado,precioCosto,precioVenta,puntoRepositorio };
					this.vistaBusquedaProductos.getModelTabla().addRow(fila);
				}
			}
		}
	}
	

	public void escribirTodosProductos(int idSucursal) {
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: this.listaMaestroProducto) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && s.getIdSucursal()==idSucursal) {
					String descr = m.getDescripcion();
					String codLote = s.getCodigoLote(); 
					int stockDisp = s.getStockDisponible();
					String tipo = m.getTipo();
					String fabricado = m.getFabricado();
					int precioCosto = m.getPrecioCosto();
					int precioVenta = m.getPrecioVenta();
					int puntoRepositorio = m.getPuntoRepositorio();
					
					Object[] fila = { descr,codLote,stockDisp,tipo,fabricado,precioCosto,precioVenta,puntoRepositorio };
					this.vistaBusquedaProductos.getModelTabla().addRow(fila);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new ControladorBusquedaProductos();
	}
}
