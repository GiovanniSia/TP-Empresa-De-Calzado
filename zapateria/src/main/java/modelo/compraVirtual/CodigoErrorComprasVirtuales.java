package modelo.compraVirtual;

public class CodigoErrorComprasVirtuales {
	
	public static String getCodigoError() {
		return "ERROR-";
	}
	
	public static String getCodigoErrorNoStock() {
		return "ERROR-100";
	}
	
	public static String getCodigoErrorProductoNoValido() {
		return "ERROR-200";
	}
	
	public static String getCodigoErrorProductoNoDisponible() {
		return "ERROR-300";
	}
	
	public static String getCodigoErrorDatosClienteNuevoNoValido() {
		return "ERROR-001";
	}
	
	public static String getCodigoErrorPagaInsuficiente() {
		return "ERROR-400";
	}
	
	public static String getCodigoErrorPagaNoValido() {
		return "ERROR-401";
	}
	
	public static String getCodigoErrorUbicacionNoValido() {
		return "ERROR-071";
	}
	
	public static String getCodigoErrorCorreo() {
		return "ERROR-061";
	}
	
	public static String getCodigoErrorPais() {
		return "ERROR-062";
	}
	
	public static String getCodigoErrorProvincia() {
		return "ERROR-063";
	}
	
	public static String getCodigoErrorNroTelefono() {
		return "ERROR-998";
	}
	
	public static String getCodigoErrorSucursalNoValida() {
		return "ERROR-4-8-15-16-23-42";
	}
	
	public static String[] erroresInvalidantes() {
		String[] ret = new String[] {getCodigoErrorNoStock(),
				getCodigoErrorProductoNoValido(),
				getCodigoErrorProductoNoDisponible(),
				getCodigoErrorDatosClienteNuevoNoValido(),
				getCodigoErrorPagaInsuficiente(),
				getCodigoErrorPagaNoValido(),
				getCodigoErrorUbicacionNoValido(),
				getCodigoErrorNroTelefono()
				//,getCodigoErrorSucursalNoValida()
				};
		return ret;
	}

}
