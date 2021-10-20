package modelo.compraVirtual;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.ClienteDTO;
import dto.CompraVirtualDTO;
import dto.MaestroProductoDTO;
import dto.SucursalDTO;
import modelo.Cliente;
import modelo.MaestroProducto;
import modelo.Sucursal;
import modelo.generarOrdenesFabricacion;
import persistencia.dao.mysql.DAOSQLFactory;

public class ProcesarCompraVirtual {
	
	public static String validarDatosCliente(CompraVirtualDTO compraVirtual) {
		if(estaRegistradoElCliente(compraVirtual.getCUIL())) {
			return "";
		}
		String ret = "";
		if(!esPagoValido(compraVirtual.getPago())) {
			ret = ret + ";El pago no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getNombre())) {
			ret = ret + ";El nombre no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getApellido())) {
			ret = ret + ";El apellido no es valido";
		}
		if(!esMailValido(compraVirtual.getCorreoElectronico())) {
			ret = ret + ";El CorreoElectronico no es valido";
		}
		if(!esTipoClienteValido(compraVirtual.getTipoCliente())) {
			ret = ret + ";El TipoCliente no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getTipoCliente())) {
			ret = ret + ";El TipoCliente no es valido";
		}
		if(!esAfipValido(compraVirtual.getImpuestoAFIP())) {
			ret = ret + ";El tipo de afip no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getCalle())) {
			ret = ret + ";La calle no es valida";
		}
		if(!esDatoStringValido(compraVirtual.getAltura())) {
			ret = ret + ";La altura no es valida";
		}
		if(!esDatoStringValido(compraVirtual.getPais())) {
			ret = ret + ";El pais no es valido";
		}
		if(!esDatoStringValido(compraVirtual.getProvincia())) {
			ret = ret + ";La provincia no es valida";
		}
		if(!esDatoStringValido(compraVirtual.getLocalidad())) {
			ret = ret + ";La localidad no es valida";
		}
		if(!esDatoStringValido(compraVirtual.getCodPostal())) {
			ret = ret + ";El codigo postal no es valido";
		}
		/* por si lo quiero booleando
		boolean ret = true;
		ret = ret && esPagoValido(compraVirtual.getPago());
		ret = ret && esDatoStringValido(compraVirtual.getNombre());
		ret = ret && esDatoStringValido(compraVirtual.getApellido());
		ret = ret && esMailValido(compraVirtual.getCorreoElectronico());
		ret = ret && esTipoClienteValido(compraVirtual.getTipoCliente());
		ret = ret && esAfipValido(compraVirtual.getImpuestoAFIP());
		
		ret = ret && esDatoStringValido(compraVirtual.getCalle());
		ret = ret && esDatoStringValido(compraVirtual.getAltura());
		ret = ret && esDatoStringValido(compraVirtual.getPais());
		ret = ret && esDatoStringValido(compraVirtual.getProvincia());
		ret = ret && esDatoStringValido(compraVirtual.getLocalidad());
		ret = ret && esDatoStringValido(compraVirtual.getCodPostal());
		*/
		if(!esSucursalValida(compraVirtual.getIdSucursal())) {
			ret = ret + ";La sucursal no es valida";
		}else {
			for(int idProducto: compraVirtual.getCompra().keySet()) {
				if(!sePuedeVenderElProducto(idProducto, compraVirtual.getIdSucursal(), compraVirtual.getCompra().get(idProducto))) {
					ret = ret + ";No es posible vender uno de los productos";
				}
			}
		}
		
		if(ret.equals("")) {
			//darDeAltaCliente();
			Cliente modeloCliente = new Cliente(new DAOSQLFactory());
			modeloCliente.insert(new ClienteDTO(0, compraVirtual.getNombre(), compraVirtual.getApellido(),compraVirtual.getCUIL(), compraVirtual.getCorreoElectronico(), 0,
			0, compraVirtual.getTipoCliente(), compraVirtual.getImpuestoAFIP(), "Activo", compraVirtual.getCalle(), compraVirtual.getAltura(),
			compraVirtual.getPais(), compraVirtual.getProvincia(), compraVirtual.getLocalidad(), compraVirtual.getCodPostal()));
		}
		return ret;
	}
	
	private static boolean esAfipValido(String afip) {
		boolean ret = true;
		if(afip == null) {
			return false;
		}
		ret = ret && (afip.equals("RI") || afip.equals("E") || afip.equals("CF"));
		return ret;
	}
	
	private static boolean esTipoClienteValido(String tipoCliente) {
		boolean ret = true;
		if(tipoCliente == null) {
			return false;
		}
		ret = ret && (tipoCliente.equals("Mayorista") || tipoCliente.equals("Minorista"));
		return ret;
	}
	
	private static boolean esDatoStringValido(String dato) {
		boolean ret = true;
		if(dato == null)
			return false;
		ret = ret && !dato.equals("");
		return ret;
	}
	
	private static boolean esPagoValido(double pago) {
		return !(pago <= 0);
	}
	
	private static boolean estaRegistradoElCliente(String cuil) {
		boolean ret = false;
		if(cuil == null)
			return false;
		if(cuil.equals(""))
			return false;
		Cliente modeloCliente = new Cliente(new DAOSQLFactory());
		for(ClienteDTO c: modeloCliente.readAll()) {
			ret = ret || c.getCUIL().equals(cuil);
		}
		return ret;
	}
	
	private static boolean esMailValido(String email) {
		if(email == null) {
			return false;
		}
		Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            return true;
        }
        return false;
	}
	
	private static boolean esSucursalValida(int idSucursal) {
		boolean ret = false;
		Sucursal modeloSucursal = new Sucursal(new DAOSQLFactory());
		for(SucursalDTO s: modeloSucursal.readAll()) {
			ret = ret || s.getIdSucursal() == idSucursal;
		}
		return ret;
	}
	
	private static boolean sePuedeVenderElProducto(int idProducto, int idSucursal, int cantidadAComprar) {
		MaestroProductoDTO producto = getProducto(idProducto);
		if(producto == null) {
			return false;
		}
		if(!producto.getEstado().equals("Activo")) {
			return false;
		}
		int cantStock = generarOrdenesFabricacion.contarStockDeUnProductoEnUnaSucursal(idSucursal,idProducto);
		if(cantStock >= cantidadAComprar) {
			return true;
		}
		return false;
	}
	
	private static MaestroProductoDTO getProducto(int idProducto) {
		MaestroProducto modeloProducto = new MaestroProducto(new DAOSQLFactory());
		MaestroProductoDTO ret = null;
		for(MaestroProductoDTO mp: modeloProducto.readAll()) {
			if(mp.getIdMaestroProducto() == idProducto) {
				ret = mp;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
