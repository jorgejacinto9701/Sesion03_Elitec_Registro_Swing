package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Alumno;
import util.MySqlDBConexion;

public class AlumnoModel {

	
	public int insertaAlumno(Alumno obj) {
		int salida = -1;

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "INSERT INTO alumno (nombre, dni, correo, fechaNacimiento) VALUES (?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getDni());
			pstm.setString(3, obj.getCorreo());
			pstm.setDate(4, java.sql.Date.valueOf(obj.getFechaNacimiento()));

			//3 Ejecutar sentencia SQL
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}
	
}
