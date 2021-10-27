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
		this.ventanaTareasAutomatizadas.getBtnRegresar().addActionListener(a -> salir(a));
		
		this.ventanaTareasAutomatizadas.getBtnActualizar().addActionListener(a -> actualizarValores(a));
	}
	
	public void inicializar() {
		
		
		
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
			//Para compras virtuales
			this.ventanaTareasAutomatizadas.getComboBoxFrecuenciaProcesamientoCompraVirtuales().removeAll();
			this.ventanaTareasAutomatizadas.getComboBoxFrecuenciaProcesamientoCompraVirtuales().addItem(1+" minuto");
			this.ventanaTareasAutomatizadas.getComboBoxFrecuenciaProcesamientoCompraVirtuales().addItem(2+" minutos");
			this.ventanaTareasAutomatizadas.getComboBoxFrecuenciaProcesamientoCompraVirtuales().addItem(3+" minutos");
			for(int x = 5; x <= 130; x = x + 5) {
				this.ventanaTareasAutomatizadas.getComboBoxFrecuenciaProcesamientoCompraVirtuales().addItem(x+" minutos");
			}
			String CompraVirtualMinutosProceso = config.getValue("CompraVirtualMinutosProceso");
			this.ventanaTareasAutomatizadas.getComboBoxFrecuenciaProcesamientoCompraVirtuales().setSelectedItem(CompraVirtualMinutosProceso+" minutos");
			String CompraVirtualTolerancia = config.getValue("CompraVirtualTolerancia");
			this.ventanaTareasAutomatizadas.getSpinnerTolerancia().setValue(Integer.valueOf(CompraVirtualTolerancia));

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
		
        //Compras virtuales
        String minutosComprasVirtuales = (String) this.ventanaTareasAutomatizadas.getComboBoxFrecuenciaProcesamientoCompraVirtuales().getSelectedItem();
        minutosComprasVirtuales = minutosComprasVirtuales.split(" ")[0];
        int toleranciaComprasVirtuales = (int) this.ventanaTareasAutomatizadas.getSpinnerTolerancia().getValue();
        if(toleranciaComprasVirtuales < 0 || toleranciaComprasVirtuales >= 100) {
        	JOptionPane.showMessageDialog(null, "El valor de tolerancia no es valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
        	return;
        }
		
		try {
			config.writeValue("DiaDeEnvio", nuevoDia.toLowerCase());
			config.writeValue("HoraDeEnvio", hora);
			
			config.writeValue("CompraVirtualTolerancia", toleranciaComprasVirtuales+"");
			config.writeValue("CompraVirtualMinutosProceso", minutosComprasVirtuales);
			
			JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Actualizacion", JOptionPane.OK_OPTION);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
