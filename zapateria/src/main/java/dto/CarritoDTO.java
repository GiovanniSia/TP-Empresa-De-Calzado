package dto;

public class CarritoDTO {

	int idCarrito, idSucursal;
	double Total;
	String hora;
	
	public CarritoDTO(int idCarrito, int idSucursal, double total,String hora) {
		super();
		this.idCarrito = idCarrito;
		this.idSucursal = idSucursal;
		Total = total;
		this.hora = hora;
	}
	
	public int getIdCarrito() {
		return idCarrito;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public double getTotal() {
		return Total;
	}
	public String getHora() {
		return this.hora;
	}
}
