package main;

import java.io.IOException;
import java.text.ParseException;

import modelo.ConfiguracionBD;
import modelo.EnviarCorreosAProveedoresAutomatico;

public class Main 
{

	public static void main(String[] args) throws IOException 
	{
		
		ConfiguracionBD config = ConfiguracionBD.getInstance(); 
		
		try {
			EnviarCorreosAProveedoresAutomatico.verificarEnvioDeMailsAutomatico(config);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
