package persistencia.dao.interfaz;

public interface DAOAbstractFactory {
	public ProductoDAO createProductoDAO();
	
	public ClienteDAO createClienteDAO();
	
	public EmpleadoDAO createEmpleadoDAO();
}
