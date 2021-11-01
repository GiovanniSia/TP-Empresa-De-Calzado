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

public class empleadoProperties {
	private Logger log = Logger.getLogger(empleadoProperties.class);
	private static empleadoProperties INSTANCE;
	private Properties properties;

	public static empleadoProperties getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new empleadoProperties();
		}

		return INSTANCE;
	}

	private empleadoProperties() {
		BasicConfigurator.configure();
		leerProperties();
	}

	public void guardar() {
		try {
			FileOutputStream fileOS = new FileOutputStream("inicioSesion/empleado.properties");
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
			properties.load(new FileInputStream("inicioSesion/empleado.properties"));
		} catch (IOException e) {
			log.info("Creando nuevo archivo de empleado");
			File folder = new File("inicioSesion");
			if (!folder.exists()) {
				folder.mkdir();
			}
			guardar();

//			establecerPropertiesInicioSesion();
		}
	}

	public void writeValue(String key, String value) throws IOException {
		FileInputStream in = new FileInputStream("inicioSesion/empleado.properties");
		properties.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream("inicioSesion/empleado.properties");
		properties.setProperty(key, value);
		properties.store(out, null);
		out.close();
	}

	public String getValue(String key) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("inicioSesion/empleado.properties");
			properties.load(inputStream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				inputStream.close();
		}
		return properties.getProperty(key);
	}

	public void establecerPropertiesEmpleado(String idEmpleado, String CUIL, String nombre, String apellido, String correoElectronico, String tipoEmpleado) {
		try {
			writeValue("IdEmpleado",idEmpleado);
			writeValue("CUIL",CUIL);
			writeValue("Nombre",nombre);
			writeValue("Apellido",apellido);
			writeValue("CorreoElectronico",correoElectronico);
			writeValue("TipoEmpleado",tipoEmpleado);		
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
