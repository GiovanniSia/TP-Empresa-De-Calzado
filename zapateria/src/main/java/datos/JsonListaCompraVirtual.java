package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dto.CompraVirtualDTO;

public class JsonListaCompraVirtual {
	
	private static final String direccion = "compraVirtual";
	
	public static void guardarLista(ArrayList<CompraVirtualDTO> nuevaLista) {
		String archivoDestino = direccion;
		guardarEnJSON(nuevaLista ,getDireccion()+archivoDestino);
	}
	
	private static void guardarEnJSON(ArrayList<CompraVirtualDTO> nuevaLista, String archivoDestino)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(nuevaLista);
		try
		{
			FileWriter writer = new FileWriter(archivoDestino);
			writer.write(json);
			writer.close();
		}catch(Exception e) {}

	}
	
	public static ArrayList<CompraVirtualDTO> getLista() {
		String archivo = direccion;
		return leerJSON(getDireccion()+archivo);
	}
	
	private static ArrayList<CompraVirtualDTO> leerJSON(String archivo)
	{
		if(!existArchivo(archivo)) {
			return new ArrayList<CompraVirtualDTO>();
		}
		Gson gson = new Gson();
		ArrayList<CompraVirtualDTO> ret = null;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			
			ret = gson.fromJson(br, new TypeToken<ArrayList<CompraVirtualDTO>>(){}.getType());
			
			//ret = gson.fromJson(br, ListaDeEntidad.class);
		}
		catch (Exception e) {}
		return ret;
	}
	
	private static boolean existArchivo(String nombreArchivo) {
		File archivo = new File(nombreArchivo);
		return archivo.exists();
	}
	
	private static String getDireccion() {
		return "src/main/java/datos/";
	}
}
