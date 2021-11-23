package dto;

public class OrdenFabricaDTO {
	private int IdOrdenFabrica;
	private int IdProd;
	private String FechaRequerido;
	private Double Cantidad;
	private String CodigoLote;
	private int IdSucursal;

	public OrdenFabricaDTO(int idOrdenFabrica, int idProd, String fechaRequerido, Double getCantidadAReponer, String codigoLote,
			int idSucursal) {
		super();
		IdOrdenFabrica = idOrdenFabrica;
		IdProd = idProd;
		FechaRequerido = fechaRequerido;
		Cantidad = getCantidadAReponer;
		CodigoLote = codigoLote;
		IdSucursal = idSucursal;
	}

	public int getIdOrdenFabrica() {
		return IdOrdenFabrica;
	}

	public void setIdOrdenFabrica(int idOrdenFabrica) {
		IdOrdenFabrica = idOrdenFabrica;
	}

	public int getIdProd() {
		return IdProd;
	}

	public void setIdProd(int idProd) {
		IdProd = idProd;
	}

	public String getFechaRequerido() {
		return FechaRequerido;
	}

	public void setFechaRequerido(String fechaRequerido) {
		FechaRequerido = fechaRequerido;
	}

	public Double getCantidad() {
		return Cantidad;
	}

	public void setCantidad(Double cantidad) {
		Cantidad = cantidad;
	}

	public String getCodigoLote() {
		return CodigoLote;
	}

	public void setCodigoLote(String codigoLote) {
		CodigoLote = codigoLote;
	}

	public int getIdSucursal() {
		return IdSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		IdSucursal = idSucursal;
	}

}
