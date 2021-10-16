package presentacion.controlador;

import java.awt.event.KeyEvent;

public class ValidadorTeclado {
	public static void aceptarSoloNumeros(KeyEvent e ) {
		char caracter = e.getKeyChar();
		if (!Character.isDigit(caracter)) {
			e.consume(); 
		}
	}
	public static void aceptarSoloNumerosYpuntos(KeyEvent e) {
		char caracter = e.getKeyChar();
		if (!Character.isDigit(caracter) && caracter !='.') {
			e.consume(); 
		}
	}
	
	
	public static void aceptarLetrasYEspacios(KeyEvent e) {
		char caracter = e.getKeyChar();
		if (!Character.isLetter(caracter) && !Character.isSpaceChar(caracter)) {
			e.consume();
		}
	}
	
	public static void aceptarMinusculaDigitoPuntoArrobaYGuiones(KeyEvent e) {
		char caracter = e.getKeyChar();
		if (Character.isUpperCase(caracter) || !Character.isLetterOrDigit(caracter) && caracter != '.' 
				&& caracter !='-' && caracter !='_' && caracter != '@')  {
			e.consume();
		}
	}
	
	public static void aceptarLetrasNumerosYEspacios(KeyEvent e) {
		char caracter = e.getKeyChar();
		if (!Character.isLetterOrDigit(caracter) && !Character.isSpaceChar(caracter))  {
			e.consume();
		}
	}
}
