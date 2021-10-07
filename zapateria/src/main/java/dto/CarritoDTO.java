package dto;

public class CarritoDTO {

	int idCarrito, IdCliente, idSucursal;
	double Total;
	
	public CarritoDTO(int idCarrito, int idCliente, int idSucursal, double total) {
		super();
		this.idCarrito = idCarrito;
		IdCliente = idCliente;
		this.idSucursal = idSucursal;
		Total = total;
	}
	
	public int getIdCarrito() {
		return idCarrito;
	}

	public int getIdCliente() {
		return IdCliente;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public double getTotal() {
		return Total;
	}

}
