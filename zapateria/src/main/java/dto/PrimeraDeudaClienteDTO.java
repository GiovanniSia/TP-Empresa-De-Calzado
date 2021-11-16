package dto;

/*
CREATE TABLE `primeraDeudaCliente`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdCliente` int(11) NOT NULL,
  `FechaDeuda` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
);
*/
public class PrimeraDeudaClienteDTO {
	int id;
	int idCliente;
	String fechaDeuda;

	public PrimeraDeudaClienteDTO(int id, int idCliente, String fechaDeuda) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.fechaDeuda = fechaDeuda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getFechaDeuda() {
		return fechaDeuda;
	}

	public void setFechaDeuda(String fechaDeuda) {
		this.fechaDeuda = fechaDeuda;
	}

}
