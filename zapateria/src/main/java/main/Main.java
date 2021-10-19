package main;

import java.util.ArrayList;
import java.util.HashMap;

import datos.JsonListaCompraVirtual;
import dto.ClienteDTO;
import dto.CompraVirtualDTO;
import presentacion.controlador.Controlador;


public class Main {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		controlador.inicializar();
		controlador.mostrarVentanaMenu();
		
		ArrayList<CompraVirtualDTO> compras = new ArrayList<CompraVirtualDTO>();
		ClienteDTO cliente = new ClienteDTO(1, "Lolencio", "Apellido", "adadasd", "no", 1000,
				1000, "Mayorista", "E", "Activo", "calle", "altura",
				"ARG", "BS AS", "Tortuguitas", "1667");
		HashMap<Integer,Integer> detalle = new HashMap<Integer,Integer>();
		detalle.put(1, 5);
		CompraVirtualDTO cvd = new CompraVirtualDTO(cliente,detalle,1,500);
		compras.add(cvd);
		
		detalle = new HashMap<Integer,Integer>();
		detalle.put(1, 1);
		detalle.put(2, 3);
		CompraVirtualDTO cvd2 = new CompraVirtualDTO(cliente,detalle,2,3);
		compras.add(cvd2);
		
		JsonListaCompraVirtual.guardarLista(compras);
		
		compras = JsonListaCompraVirtual.getLista();
		for(CompraVirtualDTO cvFOR: compras) {
			System.out.println(cvFOR.getCliente().getIdCliente()+ ", "+cvFOR.getCliente().getNombre()+": "+cvFOR.getCompra().get(1));
		}
	}
}
