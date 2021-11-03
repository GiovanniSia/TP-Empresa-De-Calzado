package inicioSesion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class sucursalProperties {
	private Logger log = Logger.getLogger(empleadoProperties.class);
	private static sucursalProperties INSTANCE;
	private Properties properties;

	public static sucursalProperties getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new sucursalProperties();
		}

		return INSTANCE;
	}

	private sucursalProperties() {
		BasicConfigurator.configure();
		leerProperties();
	}

	public void guardar() {
		try {
			FileOutputStream fileOS = new FileOutputStream("inicioSesion/sucursal.properties");
			properties.store(fileOS, null);

		} catch (Exception e) {
		}
	}

	public String obtenerProperty(String key) {
		return properties.getProperty(key);
	}

	private void leerProperties() {
		this.properties = new Properties();
		try {
			properties.load(new FileInputStream("inicioSesion/sucursal.properties"));
		} catch (IOException e) {
			log.info("Creando nuevo archivo de sucursal");
			File folder = new File("inicioSesion");
			if (!folder.exists()) {
				folder.mkdir();
			}
			guardar();

//			establecerPropertiesInicioSesion();
		}
	}

	public void writeValue(String key, String value) throws IOException {
		FileInputStream in = new FileInputStream("inicioSesion/sucursal.properties");
		properties.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream("inicioSesion/sucursal.properties");
		properties.setProperty(key, value);
		properties.store(out, null);
		out.close();
	}

	public String getValue(String key) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("inicioSesion/sucursal.properties");
			properties.load(inputStream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				inputStream.close();
		}
		return properties.getProperty(key);
	}

	public void establecerPropertiesSucursal(String IdSucursal,String Telefono,String Calle,String Altura,String Provincia,String Localidad,String Pais,String CodigoPostal,String Nombre) {
		try {
			writeValue("IdSucursal",IdSucursal);
			writeValue("Telefono",Telefono);
			writeValue("Calle",Calle);
			writeValue("Altura",Altura);
			writeValue("Provincia",Provincia);
			writeValue("Localidad",Localidad);	
			writeValue("Pais",Pais);	
			writeValue("CodigoPostal",CodigoPostal);	
			writeValue("Nombre",Nombre);	
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
