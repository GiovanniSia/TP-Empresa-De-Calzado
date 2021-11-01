package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dto.CompraVirtualDTO;

public class JsonListaCompraVirtual {

	private static final String nombreArchivo = "compraVirtual.json";

	public static void guardarLista(ArrayList<CompraVirtualDTO> nuevaLista) {
		String archivoDestino = nombreArchivo;
		guardarEnJSON(nuevaLista, getDireccion() + archivoDestino);
	}

	public static ArrayList<CompraVirtualDTO> getLista() {
		String archivo = nombreArchivo;
		return leerJSON(getDireccion() + archivo);
	}

	public static void pasarComoCompletada() {
		ArrayList<CompraVirtualDTO> listaCompletada = getLista();
		guardarEnJSON(listaCompletada, getDireccionCompletado() + nombreArchivo);
		borrarArchivo();
	}

	private static void guardarEnJSON(ArrayList<CompraVirtualDTO> nuevaLista, String archivoDestino) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(nuevaLista);
		try {
			FileWriter writer = new FileWriter(archivoDestino);
			writer.write(json);
			writer.close();
		} catch (Exception e) {
		}

	}

	private static ArrayList<CompraVirtualDTO> leerJSON(String archivo) {
		if (!existArchivo(archivo)) {
			return new ArrayList<CompraVirtualDTO>();
		}
		Gson gson = new Gson();
		ArrayList<CompraVirtualDTO> ret = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));

			ret = gson.fromJson(br, new TypeToken<ArrayList<CompraVirtualDTO>>() {
			}.getType());
			br.close();
			// ret = gson.fromJson(br, ListaDeEntidad.class);
		} catch (Exception e) {
		}
		return ret;
	}

	private static boolean existArchivo(String nombreArchivo) {
		File archivo = new File(nombreArchivo);
		return archivo.exists();
	}

	private static String getDireccion() {
		return "src/main/java/datos/";
	}

	private static String getDireccionCompletado() {
		return "src/main/java/datos/procesados/";
	}

	private static boolean borrarArchivo() {
		/*
		try {
			File archivo = new File(getDireccion()+nombreArchivo);
			boolean estatus = archivo.delete();
			if (!estatus) {
				System.out.println("Error no se ha podido eliminar el archivo");
				return false;
			} else {
				System.out.println("Se ha eliminado el archivo exitosamente");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
		*/
		try {
			Files.delete(FileSystems.getDefault().getPath(getDireccion()+nombreArchivo));
			return true;
        } catch (NoSuchFileException x) {
            System.err.format("No se puedo borrar");
        } catch (IOException x) {
            System.err.println(x);
        }
		return false;
	}
}
