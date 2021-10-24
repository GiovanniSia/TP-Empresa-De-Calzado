package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ConfiguracionBD {
	private Logger log = Logger.getLogger(ConfiguracionBD.class);
	private static ConfiguracionBD INSTANCE;
	private Properties properties;
	
	public static ConfiguracionBD getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConfiguracionBD();	
		}
		
		return INSTANCE;
	}
	
	private ConfiguracionBD() {
		BasicConfigurator.configure();
		leerProperties();
	}

	public void guardar() {
		try {
			FileOutputStream fileOS = new FileOutputStream("config/config.properties");
			properties.store(fileOS, null);
//			writeValue("firstTime","true");
			
		} catch (Exception e) {
		}		
	}
	
//	public Properties obtenerProperties() {
//		return properties;
//	}

	public String obtenerProperty(String key) {
		return properties.getProperty(key);
	}

//	public void agregar(String key, String value) {
//		this.properties.put(key, value);	
//	}

	private void leerProperties() {
		this.properties = new Properties();
		try {
			properties.load(new FileInputStream("config/config.properties"));
		} catch (IOException e) {
			log.info("Creando nuevo archivo de configuraciÃ³n");
			File folder = new File("config");
			if (!folder.exists()) {
				folder.mkdir();
			}
			guardar();

			establecerPropertiesEnvioAutoMailPorDefecto();
		}
	}
	

	
	
	public void writeValue(String key, String value) throws IOException{
		FileInputStream in = new FileInputStream("config/config.properties");
//		Properties props = new Properties();
		properties.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream("config/config.properties");
		properties.setProperty(key, value);
		properties.store(out, null);
		out.close();		
	}
	
	public String getValue(String key) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("config/config.properties");
			properties.load(inputStream);
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(inputStream != null) inputStream.close();
		}
		return properties.getProperty(key);
	}
	
	
	private void establecerPropertiesEnvioAutoMailPorDefecto() {
		try {
			writeValue("DiaDeEnvio","jueves");
			writeValue("HoraDeEnvio","19:00:00");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
