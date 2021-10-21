package main;

import java.util.ArrayList;
import java.util.HashMap;

import datos.JsonListaCompraVirtual;
import dto.ClienteDTO;
import dto.CompraVirtualDTO;
import modelo.compraVirtual.ProcesarCompraVirtual;
import presentacion.controlador.Controlador;
import presentacion.reportes.ReporteFactura;


public class Main {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		controlador.inicializar();
		controlador.mostrarVentanaMenu();
		/*
		ArrayList<CompraVirtualDTO> compras = new ArrayList<CompraVirtualDTO>();
		HashMap<Integer,Integer> detalle = new HashMap<Integer,Integer>();
		detalle.put(1, 5);
		//CompraVirtualDTO cvd = new CompraVirtualDTO(cliente,detalle,1,500);
		CompraVirtualDTO cvd = new CompraVirtualDTO(detalle, 1, 35000, 2, "Juan", "Lopez","4223004","juan@mgail.com",
				//"Mayorista",
				"E","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
		compras.add(cvd);
		
		detalle = new HashMap<Integer,Integer>();
		detalle.put(1, 1);
		detalle.put(2, 3);
		CompraVirtualDTO cvd2 = new CompraVirtualDTO(detalle, 1, 14020, 1, "Sebas",
				"Cubilla", "1231312319987654", "sebastianx3600@gmail.com",
				//"Minorista", 
				"RI", "Calle falsa", "5421", "Argentina",
				"Buenos Aires", "Tortuguitas", "1667");
		compras.add(cvd2);
		
		
		detalle = new HashMap<Integer,Integer>();
		detalle.put(1, 1);
		detalle.put(2, 3);
		CompraVirtualDTO cvd3 = new CompraVirtualDTO(detalle, 1, 500, 1, "Pedro",
				"asd", "", "sebas@gmail.com", 
				//"Minorista", 
				"RI", "", "", "Argentina",
				"Buenos Aires", "Tortuguitas", "1667");
		compras.add(cvd3);
		JsonListaCompraVirtual.guardarLista(compras);
		compras = null;
		//compras = JsonListaCompraVirtual.getLista();
		//for(CompraVirtualDTO cvFOR: compras)
		//	System.out.println(cvFOR+ ", "+cvFOR.getNombre()+": "+cvFOR.getCompra().get(1));
		
		//ProcesarCompraVirtual.FuncionProcesarCompra();
		ProcesarCompraVirtual.RutinaProcesarCompra(1);
		*/
		
		
	}
}
