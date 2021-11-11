package modelo.datosGraficos;

import java.util.Date;
import java.util.HashMap;

import dto.EmpleadoDTO;
import dto.FacturaDTO;
import dto.IngresosDTO;
import dto.SucursalDTO;
import modelo.Empleado;
import modelo.Factura;
import modelo.Ingresos;
import modelo.Sucursal;
import persistencia.dao.mysql.DAOSQLFactory;

public class RankingDatos {
	
	static public HashMap<SucursalDTO,Double> getRankingVentasXSucursales(int cantidadSucursalesDeseada, int diasParaAtras){
		HashMap<SucursalDTO,Double> ret = new HashMap<SucursalDTO,Double>();
		Sucursal modeloSucursal = new Sucursal(new DAOSQLFactory());
		for(SucursalDTO s: modeloSucursal.readAll()) {
			ret.put(s, getIngresosPorSucursal(s.getIdSucursal(), diasParaAtras));
		}
		return getTop(ret, cantidadSucursalesDeseada);
	}
	
	static public HashMap<EmpleadoDTO, Double> getRankingVentasxVendedor(int cantidadEmpleadosDeseado, int diasParaAtras){
		HashMap<EmpleadoDTO,Double> ret = new HashMap<EmpleadoDTO,Double>();
		Empleado modeloEmpleado = new Empleado(new DAOSQLFactory());
		for(EmpleadoDTO e: modeloEmpleado.readAll()) {
			ret.put(e, getIngresosPorEmpleado(e.getIdEmpleado(), diasParaAtras));
			System.out.println(ret.get(e)+" resultado final");
		}
		return ret;
	}
	
	static private Double getIngresosPorEmpleado(int idEmpleado, int diasParaAtras) {
		Double ret = 0.0;
		Factura modeloFactura = new Factura(new DAOSQLFactory());
		for(FacturaDTO i: modeloFactura.readAll()) {
			if(i.getIdVendedor() == idEmpleado) {
				if(fechaEsValida(i,diasParaAtras)) {
					System.out.println("Se sumo");
					ret += i.getTotalFactura();
					System.out.println(ret);
				}
			}
		}
		return ret;
	}
	
	static private Double getIngresosPorSucursal(int idSucursal, int diasParaAtras) {
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
		if(fechaComparar.compareTo(fechaIngreso)>0) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private static boolean fechaEsValida(FacturaDTO i, int diasParaAtras) {
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
		
		HashMap<EmpleadoDTO, Double> rankingVendedor = getRankingVentasxVendedor(2,0);
		for(EmpleadoDTO e: rankingVendedor.keySet()) {
			System.out.println(e.getNombre()+": "+rankingVendedor.get(e));
		}
	}

}
