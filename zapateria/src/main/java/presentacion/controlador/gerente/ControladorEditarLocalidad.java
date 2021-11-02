package presentacion.controlador.gerente;

import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import modelo.Localidad;
import modelo.Pais;
import modelo.Provincia;
import presentacion.vista.gerente.VentanaEditarLocalidad;

public class ControladorEditarLocalidad {
	
	Pais pais;
	Provincia provincia;
	Localidad localidad;
	
	List<PaisDTO> todosLosPaises;
	List<ProvinciaDTO> todasLasProvincias;
	List<LocalidadDTO> todasLasLocalidades;
	
	PaisDTO paisSeleccionado;
	ProvinciaDTO provinciaSeleccionada;
	List<LocalidadDTO> localidadesEnTabla;
	
	VentanaEditarLocalidad ventanaEditarLocalidad;
	
	ControladorAltaCliente controladorAltaCliente;
	
	public ControladorEditarLocalidad(ControladorAltaCliente controladorAltaCliente, Pais pais, Provincia provincia, Localidad localidad) {
		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;
		this.controladorAltaCliente = controladorAltaCliente;
			
		this.ventanaEditarLocalidad = new VentanaEditarLocalidad();
		
	}
	
	public void inicializar() {
		this.todosLosPaises = this.pais.readAll();
		this.todasLasProvincias = this.provincia.readAll();
		this.todasLasLocalidades = this.localidad.readAll();
		escribirComboBoxes();
		llenarTablaPorDefecto();
		
	}

	
	public void mostrarVentana() {
		this.ventanaEditarLocalidad.show();
	}

	public void cerrarVentana() {
		this.ventanaEditarLocalidad.cerrar();
	}

	
	public void escribirComboBoxes() {
		for(PaisDTO p: this.todosLosPaises) {
			this.ventanaEditarLocalidad.getComboBoxPaises().addItem(p.getNombrePais());
		}
		this.ventanaEditarLocalidad.getComboBoxPaises().setSelectedIndex(0);
		this.paisSeleccionado = this.todosLosPaises.get(0);
		
		llenarCbProvinciaDadoPais(this.paisSeleccionado.getIdPais());
		
		
	}
	
	public void llenarCbProvinciaDadoPais(int idPais) {
		for(ProvinciaDTO prov: this.todasLasProvincias) {
			if(prov.getForeignPais()==idPais) {
				this.ventanaEditarLocalidad.getComboProvincias().addItem(prov.getNombreProvincia());
			}
		}
		this.ventanaEditarLocalidad.getComboProvincias().setSelectedIndex(0);
		this.provinciaSeleccionada = getProvincia(idPais, this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem().toString());
	}
	
	
	
	public ProvinciaDTO getProvincia(int idPais, String nombreProv) {
		for(ProvinciaDTO prov: this.todasLasProvincias) {
			if(prov.getForeignPais() == idPais && prov.getNombreProvincia().equals(nombreProv)) {
				return prov;
			}
		}
		return null;
		
	}
	
	public boolean ventanaEstaInicializada() {
		return this.ventanaEditarLocalidad.getFrame().isShowing();
	}
	
	public void llenarTablaPorDefecto() {
		
	}
	
}
