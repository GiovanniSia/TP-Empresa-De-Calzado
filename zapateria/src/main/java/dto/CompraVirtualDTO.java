package dto;

import java.util.HashMap;

public class CompraVirtualDTO {
	
	private ClienteDTO cliente;
					//idProducto,cantidad
	private HashMap<Integer,Integer> compra;
	private int idSucursal;
	private double pago;
	
	public CompraVirtualDTO(ClienteDTO cliente, HashMap<Integer, Integer> compra, int idSucursal, double pago) {
		super();
		this.cliente = cliente;
		this.compra = compra;
		this.idSucursal = idSucursal;
		this.pago = pago;
	}

	public CompraVirtualDTO(ClienteDTO cliente, int idSucursal, double pago) {
		super();
		this.cliente = cliente;
		this.idSucursal = idSucursal;
		this.pago = pago;
		compra = new HashMap<Integer,Integer>();
	}
	
	public void agregarUnaCompra(int idProducto, int cantidad) {
		if(compra.containsKey(idProducto)) {
			compra.put(idProducto, compra.get(idProducto)+cantidad);
		}else {
			compra.put(idProducto, cantidad);
		}
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public HashMap<Integer, Integer> getCompra() {
		return compra;
	}

	public void setCompra(HashMap<Integer, Integer> compra) {
		this.compra = compra;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public double getPago() {
		return pago;
	}

	public void setPago(double pago) {
		this.pago = pago;
	}

}
