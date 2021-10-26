package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.ConfiguracionBD;
import presentacion.vista.VentanaTareasAutomatizadas;

public class ControladorTareasAutomatizadas {

	VentanaTareasAutomatizadas ventanaTareasAutomatizadas;
	ConfiguracionBD config;
	
	public ControladorTareasAutomatizadas(VentanaTareasAutomatizadas ventanaTareasAutomatizadas,ConfiguracionBD config) {
		this.ventanaTareasAutomatizadas = ventanaTareasAutomatizadas;
		this.config=config;
	}
	
	public void inicializar() {
		
		this.ventanaTareasAutomatizadas.getBtnRegresar().addActionListener(a -> salir(a));
		
		this.ventanaTareasAutomatizadas.getBtnActualizar().addActionListener(a -> actualizarValores(a));
		
		llenarCbCorreosProv();
		setParametros();
		
		
	}

	private void salir(ActionEvent a) {
		this.ventanaTareasAutomatizadas.cerrar();
	}

	public void mostrarVentana() {
		this.ventanaTareasAutomatizadas.show();
	}
	
	public void cerrarVentana() {
		this.ventanaTareasAutomatizadas.cerrar();
	}
	

	
	private void llenarCbCorreosProv() {		
		String[] dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
		for(int i=0; i < dias.length; i++) {
			this.ventanaTareasAutomatizadas.getComboBoxPedidosDias().addItem(dias[i]);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void setParametros() {
		try {
			String h = config.getValue("HoraDeEnvio");
			System.out.println("hora que se obtiene del properties: "+h);
			int hora =Integer.parseInt(h.substring(0, 2));
			int min = Integer.parseInt(h.substring(3, 5));
			int seg = Integer.parseInt(h.substring(6, 8));
	
			this.ventanaTareasAutomatizadas.getSpinnerPedidosHora().setValue(new Date(0,0,0, hora,min,seg));
			
			String diaEnProperties = config.getValue("DiaDeEnvio");
			
			for(int i=0; i < this.ventanaTareasAutomatizadas.getComboBoxPedidosDias().getItemCount(); i++) {
				String itCb = this.ventanaTareasAutomatizadas.getComboBoxPedidosDias().getItemAt(i);
				if(itCb.toLowerCase().equals(diaEnProperties)) {
					this.ventanaTareasAutomatizadas.getComboBoxPedidosDias().setSelectedIndex(i);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	private void actualizarValores(ActionEvent a) {
		//por ahora solo se guardan en el properties 2 valores
		
		String nuevoDia = (String) this.ventanaTareasAutomatizadas.getComboBoxPedidosDias().getSelectedItem();
		
		DateFormat dateFmt = new SimpleDateFormat("HH:mm:ss");
		Date fechaHora = (Date) this.ventanaTareasAutomatizadas.getSpinnerPedidosHora().getValue();
	
		//Hora
		String hora = null;
        if (fechaHora != null) {
            hora = dateFmt.format(fechaHora);
        }else {
        	JOptionPane.showMessageDialog(null, "Debe establecer una hora", "Advertencia", JOptionPane.WARNING_MESSAGE);
        	return;
        }
		
        
		try {
			config.writeValue("DiaDeEnvio", nuevoDia.toLowerCase());
			config.writeValue("HoraDeEnvio", hora);
			JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Actualizacion", JOptionPane.OK_OPTION);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
