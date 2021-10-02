package dto;

public class StockDTO {
	private int idStock;
	private int idSucursal;
	private int idProducto;
	private String codigoLote;
	private int stockDisponible;

	public StockDTO(int idStock, int idSucursal, int idProducto, String codigoLote, int stockDisponible) {
		this.idStock = idStock;
		this.idSucursal = idSucursal;
		this.idProducto = idProducto;
		this.codigoLote = codigoLote;
		this.stockDisponible = stockDisponible;
	}

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}

	public int getStockDisponible() {
		return stockDisponible;
	}

	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}

}
