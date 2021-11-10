package modelo.datosGraficos;

import java.util.HashMap;

import dto.IngresosDTO;
import dto.SucursalDTO;
import modelo.Ingresos;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;

public class RankingDatos {
	
	static public HashMap<SucursalDTO,Double> getRankingVentasXSucursales(int cantidadDeseada){
		HashMap<SucursalDTO,Double> ret = new HashMap<SucursalDTO,Double>();
		Sucursal modeloSucursal = new Sucursal(new DAOSQLFactory());
		for(SucursalDTO s: modeloSucursal.readAll()) {
			ret.put(s, getIngresos(s.getIdSucursal()));
		}
		return getTop(ret, cantidadDeseada);
	}
	
	static private Double getIngresos(int idSucursal) {
		Double ret = 0.0;
		Ingresos modeloIngreso = new Ingresos(new DAOSQLFactory());
		for(IngresosDTO i: modeloIngreso.readAll()) {
			if(i.getIdSucursal() == idSucursal) {
				ret += i.getTotal();
			}
		}
		return ret;
	}
	
	static private HashMap<SucursalDTO,Double> getTop(HashMap<SucursalDTO,Double> lista, int cantidadDeseada){
		HashMap<SucursalDTO,Double> ret = new HashMap<SucursalDTO,Double>();
		HashMap<SucursalDTO,Double> copia = lista;
		while(copia.keySet().size()>0 && cantidadDeseada>0) {
			SucursalDTO masAlto = getMasAlto(copia);
			ret.put(masAlto, copia.get(masAlto));
			copia.remove(masAlto);
			cantidadDeseada = cantidadDeseada-1;
		}
		return ret;
	}
	
	static private SucursalDTO getMasAlto(HashMap<SucursalDTO,Double> lista){
		HashMap<SucursalDTO,Double> copia = lista;
		SucursalDTO ret = null;
		for(SucursalDTO s: copia.keySet()) {
			if(ret == null) {
				ret = s;
			}
			if(copia.get(s)>lista.get(ret)){
				ret = s;
			}
		}
		return ret;
	}
	
	public static void main(String[] args){
		HashMap<SucursalDTO,Double> rankingSucursal = getRankingVentasXSucursales(2);
		for(SucursalDTO s: rankingSucursal.keySet()) {
			System.out.println(s.getNombre()+": "+rankingSucursal.get(s));
		}
	}

}
