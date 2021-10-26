package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CajaDTO;
import dto.EmpleadoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.CajaDAO;

public class CajaDAOSQL implements CajaDAO {
	private static final String insert = "INSERT INTO Caja VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM Caja WHERE IdCaja = ?";
	private static final String update = "UPDATE Caja set IdSucursal=?,Fecha=?,Hora=?,Apertura=?,Cierre=?,AperturaNombre=?,CierreNombre=?,AuditApertura=?,AuditCierre=? where IdCaja=?";
	private static final String readall = "SELECT * FROM Caja";

	private static final String cerrarCaja = "UPDATE Caja SET Cierre=?, CierreNombre=? WHERE IdCaja=?";
	
	@Override
	public boolean insert(CajaDTO caja) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			/*
			  `IdSucursal` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NOT NULL,
  `Hora` TIME NOT NULL,
  `Apertura` int(11) NOT NULL,
  `Cierre` int(11) NOT NULL,	
  `AperturaNombre` varchar(45) NOT NULL,
  `CierreNombre` varchar(45) NOT NULL,
  `AuditApertura` TIME NOT NULL,
  `AuditCierre` TIME NOT NULL,
			
			*/
			statement.setInt(1, caja.getIdCaja());
			statement.setInt(2, caja.getIdSucursal());
			statement.setString(3, caja.getFecha());
			statement.setString(4, caja.getHora());
			statement.setInt(5, caja.getApertura());
			statement.setInt(6, caja.getCierre());
			statement.setString(7, caja.getAperturaNombre());
			statement.setString(8, caja.getCierreNombre());
			statement.setString(9, caja.getAuditApertura());
			statement.setString(10, caja.getAuditCierre());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}

	@Override
	public boolean delete(CajaDTO caja_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, caja_a_eliminar.getIdCaja());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public boolean update(int id_caja_a_actualizar, CajaDTO caja_nuevo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setInt(1, caja_nuevo.getIdSucursal());
			statement.setString(2, caja_nuevo.getFecha());
			statement.setString(3, caja_nuevo.getHora());
			statement.setInt(4, caja_nuevo.getApertura());
			statement.setInt(5, caja_nuevo.getCierre());
			statement.setString(6, caja_nuevo.getAperturaNombre());
			statement.setString(7, caja_nuevo.getCierreNombre());
			statement.setString(8, caja_nuevo.getAuditApertura());
			statement.setString(9, caja_nuevo.getAuditCierre());
			statement.setInt(10, id_caja_a_actualizar);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}

	@Override
	public List<CajaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<CajaDTO> caja = new ArrayList<CajaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				caja.add(getCajaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return caja;
	}

	@Override
	public List<CajaDTO> getCajaAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2,
			String txtAprox2) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM Caja WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'";

		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + "AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}

		ArrayList<CajaDTO> caja = new ArrayList<CajaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				caja.add(getCajaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return caja;
	}
	
	@Override
	public CajaDTO getCajaDeHoy(String nombreColumna1, String txtAprox1, String nombreColumna2,
			String txtAprox2) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel = "SELECT * FROM Caja WHERE " + nombreColumna1 + " like '%" + txtAprox1 + "%'";

		if (nombreColumna2 != null && txtAprox2 != null) {
			sel = sel + "AND " + nombreColumna2 + " LIKE '%" + txtAprox2 + "%'";
		}

		CajaDTO caja =null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				caja = this.getCajaDTO(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return caja;
	}


	private CajaDTO getCajaDTO(ResultSet resultSet) throws SQLException {
		int idCaja = resultSet.getInt("IdCaja");
		int idSucursal = resultSet.getInt("IdSucursal");
		String fecha = resultSet.getString("Fecha");
		String hora = resultSet.getString("Hora");
		int apertura = resultSet.getInt("Apertura");
		int cierre = resultSet.getInt("Cierre");
		String aperturaNombre = resultSet.getString("AperturaNombre");
		String cierreNombre = resultSet.getString("CierreNombre");
		String auditApertura = resultSet.getString("AuditApertura");
		String auditCierre = resultSet.getString("AuditCierre");

		return new CajaDTO(idCaja, idSucursal, fecha, hora, apertura, cierre, aperturaNombre, cierreNombre,
				auditApertura, auditCierre);
	}

	@Override
	public boolean cerrarCaja(EmpleadoDTO empleado,int idCaja) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(cerrarCaja);

			statement.setInt(1, empleado.getIdEmpleado());
			statement.setString(2, empleado.getNombre()+" "+empleado.getApellido());
			statement.setInt(3, idCaja);


			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}

}
