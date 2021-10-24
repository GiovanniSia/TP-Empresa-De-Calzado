package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);

	private Conexion() {
		try {
			
			// Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario,
			// versiones antiguas de mysql
			Class.forName("com.mysql.cj.jdbc.Driver"); // PARA VERSIONES MAS AVANZADAS
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zapateria", "root", "root");

			// Conexion con la base de datos en la nube
			// this.connection =
			// DriverManager.getConnection("jdbc:mysql://bxg6oprxsdqe3o0kxt53-mysql.services.clever-cloud.com:3306/bxg6oprxsdqe3o0kxt53","uvau8lhbjts2x60c","SdmScZxrm9q47VH7U2qZ");

			this.connection.setAutoCommit(false);
			log.info("Conexion exitosa");
		} catch (Exception e) {
			log.error("Conexion fallida", e);
		}
	}

	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.connection;
	}

	public void cerrarConexion() {
		try {
			this.connection.close();
			log.info("Conexion cerrada");
		} catch (SQLException e) {
			log.error("Error al cerrar la conexion!", e);
		}
		instancia = null;
	}
}
