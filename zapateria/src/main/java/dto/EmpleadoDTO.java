package dto;

public class EmpleadoDTO {

	int idEmpleado;
	String CUIL, nombre, apellido, correo, tipoEmpleado, contrasenia;

	public EmpleadoDTO(int idEmpleado, String CUIL, String nombre, String apellido, String correo,
			String tipoEmpleado, String contrasenia) {
		
		this.idEmpleado = idEmpleado;
		this.CUIL = CUIL;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.tipoEmpleado = tipoEmpleado;
		this.contrasenia = contrasenia;
	}
	
	
	public int getIdEmpleado() {
		return idEmpleado;
	}

	public String getCUIL() {
		return CUIL;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	public String getContrasenia() {
		return contrasenia;
	}
	
}
