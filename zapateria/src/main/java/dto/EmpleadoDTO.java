package dto;

public class EmpleadoDTO {

	int IdEmpleado;
	String CUIL, Nombre, Apellido, CorreoElectronico, TipoEmpleado, Contra;

	public EmpleadoDTO(int idEmpleado, String cUIL, String nombre, String apellido, String correoElectronico,
			String tipoEmpleado, String Contra) {
		super();
		IdEmpleado = idEmpleado;
		CUIL = cUIL;
		Nombre = nombre;
		Apellido = apellido;
		CorreoElectronico = correoElectronico;
		TipoEmpleado = tipoEmpleado;
		this.Contra = Contra;
	}
	
	
	public int getIdEmpleado() {
		return IdEmpleado;
	}

	public String getCUIL() {
		return CUIL;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public String getCorreoElectronico() {
		return CorreoElectronico;
	}

	public String getTipoEmpleado() {
		return TipoEmpleado;
	}

	public String getContra() {
		return Contra;
	}
}
