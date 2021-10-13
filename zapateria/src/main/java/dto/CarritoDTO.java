package dto;

public class CarritoDTO {
	
	int idCarrito, idSucursal,idCliente,idVendedor;
	double Total;
	String hora;

	public CarritoDTO(int idCarrito, int idSucursal, int idCliente, int idVendedor, double total, String hora) {
		super();
		this.idCarrito = idCarrito;
		this.idSucursal = idSucursal;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
		Total = total;
		this.hora = hora;
	}
	
	public int getIdCarrito() {
		return idCarrito;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public int getIdVendedor() {
		return idVendedor;
	}

	public double getTotal() {
		return Total;
	}

	public String getHora() {
		return hora;
	}

}
