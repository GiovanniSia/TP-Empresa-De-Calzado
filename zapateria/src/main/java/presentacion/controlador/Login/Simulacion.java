package presentacion.controlador.Login;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import presentacion.controlador.Controlador;

public class Simulacion extends SwingWorker<Boolean, Boolean> {

	Controlador controlador;
	JProgressBar progressBar;
	ControladorLogin controladorLogin;

	public Simulacion(Controlador controlador, JProgressBar progressBar,ControladorLogin controladorLogin) {
		this.controlador = controlador;
		this.progressBar = progressBar;
		this.controladorLogin = controladorLogin;
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		controladorLogin.bloquearUsuarioYClave();
		progressBar.setIndeterminate(true);
		controlador.inicializar();
		controlador.mostrarVentanaPorTipoEmpleado();
		controladorLogin.cerrarVentana();
		controladorLogin.limpiarCampos();
		return true;
	}

	public void done() {
		try {
			if (this.isCancelled() == false) {
				progressBar.setIndeterminate(false);
			}
		} catch (Exception ex) {

		}
	}
}
