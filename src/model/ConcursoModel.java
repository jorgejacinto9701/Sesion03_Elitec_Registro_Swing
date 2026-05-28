package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Concurso;
import util.MySqlDBConexion;

public class ConcursoModel {

	public int insertaConcurso(Concurso obj){
		int salida = -1;

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "INSERT INTO concurso (nombre, fechaInicio, fechaFin, estado) VALUES (?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setDate(2, java.sql.Date.valueOf(obj.getFechaInicio()));
			pstm.setDate(3, java.sql.Date.valueOf(obj.getFechaFin()));
			pstm.setInt(4, obj.getEstado());
			
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
