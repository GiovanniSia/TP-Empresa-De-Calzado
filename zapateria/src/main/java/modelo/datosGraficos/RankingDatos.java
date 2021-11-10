package modelo.datosGraficos;

import java.util.Date;
import java.util.HashMap;

import dto.IngresosDTO;
import dto.SucursalDTO;
import modelo.Ingresos;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;

public class RankingDatos {
	
	static public HashMap<SucursalDTO,Double> getRankingVentasXSucursales(int cantidadSucursalesDeseada, int diasParaAtras){
		HashMap<SucursalDTO,Double> ret = new HashMap<SucursalDTO,Double>();
		Sucursal modeloSucursal = new Sucursal(new DAOSQLFactory());
		for(SucursalDTO s: modeloSucursal.readAll()) {
			ret.put(s, getIngresos(s.getIdSucursal(), diasParaAtras));
		}
		return getTop(ret, cantidadSucursalesDeseada);
	}
	
	static private Double getIngresos(int idSucursal, int diasParaAtras) {
		Double ret = 0.0;
		Ingresos modeloIngreso = new Ingresos(new DAOSQLFactory());
		for(IngresosDTO i: modeloIngreso.readAll()) {
			if(i.getIdSucursal() == idSucursal) {
				if(fechaEsValida(i,diasParaAtras)) {
					ret += i.getTotal();
				}
			}
		}
		return ret;
	}
	
	@SuppressWarnings("deprecation")
	private static boolean fechaEsValida(IngresosDTO i, int diasParaAtras) {
		Date fechaComparar = new Date();
		Date fechaIngreso = new Date();
		String[] fechaIngregoString = i.getFecha().split("-"); 
		fechaIngreso.setYear(Integer.valueOf(fechaIngregoString[0])-1900);
		fechaIngreso.setMonth(Integer.valueOf(fechaIngregoString[1])-1);
		fechaIngreso.setDate(Integer.valueOf(fechaIngregoString[2]));
		
		fechaComparar.setDate(Integer.valueOf(fechaComparar.getDate()-diasParaAtras));
		//fechaComparar.setDate(1);
		System.out.println("fecha comparar: "+fechaComparar);
		System.out.println(fechaIngreso);
		if(fechaComparar.compareTo(fechaIngreso)>0) {
			return false;
		}
		System.out.println("La fecha da");
		return true;
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
		HashMap<SucursalDTO,Double> rankingSucursal = getRankingVentasXSucursales(3,1);
		for(SucursalDTO s: rankingSucursal.keySet()) {
			System.out.println(s.getNombre()+": "+rankingSucursal.get(s));
		}
	}

}
