package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
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
	
	public ControladorBusquedaProductos(MaestroProducto maestroProducto, Stock stock, Sucursal sucursal) {
		this.maestroProducto = maestroProducto;
		this.stock = stock;
		this.sucursal = sucursal;
	}
	
	public void inicializar() {
		this.vistaBusquedaProductos = new vistaBusquedaProductos();
		
		this.maestroProducto = new MaestroProducto(new DAOSQLFactory());
		this.listaMaestroProducto = this.maestroProducto.readAll();
		
		this.stock = new Stock(new DAOSQLFactory());
		this.listaStock = this.stock.readAll();
		
		this.vistaBusquedaProductos.getTxtNombreProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		this.vistaBusquedaProductos.getTxtIdSucursal().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarBusqueda();
			}
		});
		escribirTablaCompleta();
		this.vistaBusquedaProductos.show();
		}
	
	
	//Busca el producto dado el nombre y el idSucursal
//	public void realizarBusqueda(ActionEvent a) {
	public void realizarBusqueda() {
		this.vistaBusquedaProductos.getModelTabla().setRowCount(0);//borrar datos de la tabla
		this.vistaBusquedaProductos.getModelTabla().setColumnCount(0);
		this.vistaBusquedaProductos.getModelTabla().setColumnIdentifiers(this.vistaBusquedaProductos.getNombreColumnas());
		
		String txtNombre = this.vistaBusquedaProductos.getTxtNombreProducto().getText();
		String txtSucu = this.vistaBusquedaProductos.getTxtIdSucursal().getText();
		
		if(txtNombre.equals("") &&  txtSucu.equals("")) { //nombreVacio pero sucursal no
			escribirTablaCompleta();
			return;
		}
		
		if(txtNombre.equals("") && txtSucu != null) {//ambos vacios
			escribirTablaDadoIdSucursal(Integer.parseInt(txtSucu));
			return;
		}
		
		if(txtNombre!=null && txtSucu.equals("")) {
			escribirTablaDadoProducto(txtNombre);
			return;
		}
		
		int txtSucursal = Integer.parseInt(this.vistaBusquedaProductos.getTxtIdSucursal().getText());			

		//caso todos los campos completos
		List<MaestroProductoDTO> productosPosibles = this.maestroProducto.getMaestroProducto(txtNombre);//posibles valores
		
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: productosPosibles) {
//				System.out.println("nombre Producto: " + m.getDescripcion()+"\nIdProd: "+ m.getIdMaestroProducto()+ "\nIdProductoStock: "+ s.getIdProducto()+"\nidSucursal del stock: "+s.getIdSucursal()+"\nsucursal: "+txtSucursal+"\ncodigoLote: "+s.getCodigoLote());
				if(txtSucursal == s.getIdSucursal() && s.getIdProducto() == m.getIdMaestroProducto()) {
					agregarATabla(s,m);
				}
			}
		}		
	}	
	
	public void escribirTablaCompleta() {
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: this.listaMaestroProducto) {
				if(s.getIdProducto()== m.getIdMaestroProducto()) {
					agregarATabla(s,m);
				}
			}
		}
	}
	public void escribirTablaDadoIdSucursal(int idSucursal){
		for(StockDTO s: this.listaStock) {
			for(MaestroProductoDTO m: this.listaMaestroProducto) {
				if(m.getIdMaestroProducto()==s.getIdProducto() && idSucursal == s.getIdSucursal()) {
					agregarATabla(s,m);
				}
			}
		}
	}

	public void escribirTablaDadoProducto(String nombre) {
		List<MaestroProductoDTO> productosPosibles = this.maestroProducto.getMaestroProducto(nombre);//posibles valores
		for(MaestroProductoDTO m: productosPosibles){
			for(StockDTO s: this.listaStock){
//				System.out.println("nombre Producto: " + m.getDescripcion()+"\nIdProd: "+ m.getIdMaestroProducto()+ "\nIdProductoStock: "+ s.getIdProducto()+"\nidSucursal del stock: "+s.getIdSucursal()+"\nsucursal: "+txtSucursal+"\ncodigoLote: "+s.getCodigoLote());
				if(m.getIdMaestroProducto()==s.getIdProducto()) {
					agregarATabla(s,m);
				}
			}
		}
	}
	
	public void agregarATabla(StockDTO s, MaestroProductoDTO m) {
		String descr = m.getDescripcion();
		String codLote = s.getCodigoLote(); 
		int idSucursal = s.getIdSucursal();
		int stockDisp = s.getStockDisponible();
		String tipo = m.getTipo();
		String fabricado = m.getFabricado();
		int precioCosto = m.getPrecioCosto();
		int precioVenta = m.getPrecioVenta();
		int puntoRepositorio = m.getPuntoRepositorio();
		
		Object[] fila = { descr,codLote,idSucursal,stockDisp,tipo,fabricado,precioCosto,precioVenta,puntoRepositorio };
		this.vistaBusquedaProductos.getModelTabla().addRow(fila);
	}

}
