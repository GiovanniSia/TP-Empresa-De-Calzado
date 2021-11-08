package presentacion.controlador.gerente;

import java.util.ArrayList;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import modelo.Localidad;
import modelo.Pais;
import modelo.Provincia;
import modelo.Sucursal;
import presentacion.vista.gerente.VentanaAltaSucursal;
import presentacion.vista.gerente.VentanaEditarLocalidad;
import presentacion.vista.gerente.VentanaEditarPais;
import presentacion.vista.gerente.VentanaEditarProvincia;

public class ControladorAltaSucursal {

	VentanaAltaSucursal ventanaAltaSucursal;
	
	ControladorEditarPais controladorEditarPais;
	VentanaEditarPais ventanaEditarPais;
	VentanaEditarProvincia ventanaEditarProvincia;
	ControladorEditarProvincia controladorEditarProvincia;
	VentanaEditarLocalidad ventanaEditarLocalidad;
	ControladorEditarLocalidad controladorEditarLocalidad;
	
	Pais pais;
	Provincia provincia;
	Localidad localidad;
	ArrayList<PaisDTO> todosLosPaises;
	ArrayList<ProvinciaDTO> todasLasProvincias;
	ArrayList<LocalidadDTO> todasLasLocalidades;
	ArrayList<ProvinciaDTO> provEnComboBox;
	ArrayList<LocalidadDTO> localidadEnComboBox;
	
	Sucursal sucursal;
	
	public ControladorAltaSucursal(Sucursal sucursal,Pais pais, Provincia provincia, Localidad localidad) {
		
	}
	
	public void inicializar() {
		
	}
	
}
