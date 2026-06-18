package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
			String sql = "INSERT INTO alumno (nombre, dni, correo, fechaNacimiento, estado) VALUES (?,?,?,?,1)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getDni());
			pstm.setString(3, obj.getCorreo());
			pstm.setDate(4, java.sql.Date.valueOf(obj.getFechaNacimiento()));

			System.out.println("SQL: " + pstm.toString());
			
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
	
	
	public List<Alumno> listaAlumno(String nombre, String dni, String correo, LocalDate desde, LocalDate hasta) {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "SELECT * FROM alumno WHERE "
					+ " nombre LIKE ? AND "
					+ " ( ? ='' or dni = ? ) AND "
					+ " ( ? ='' or correo = ? ) AND "
					+ " ( ? ='9999-01-01' or fechaNacimiento >= ? ) AND "
					+ " ( ? ='9999-01-01' or fechaNacimiento <= ? ) ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + nombre + "%");
			pstm.setString(2, dni);
			pstm.setString(3, dni);
			pstm.setString(4, correo);
			pstm.setString(5, correo);
			pstm.setDate(6, java.sql.Date.valueOf(desde));
			pstm.setDate(7, java.sql.Date.valueOf(desde));
			pstm.setDate(8, java.sql.Date.valueOf(hasta));
			pstm.setDate(9, java.sql.Date.valueOf(hasta));
			
			//imprimir el query para verificar que se arma correctamente
			System.out.println("SQL: " + pstm.toString());
			
			//Se ejecuta el query en la base de datos
			rs = pstm.executeQuery();

			while (rs.next()) {
				Alumno a = new Alumno();
				a.setIdAlumno(rs.getInt("idAlumno"));
				a.setNombre(rs.getString("nombre"));
				a.setDni(rs.getString("dni"));
				a.setCorreo(rs.getString("correo"));
				a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
				a.setEstado(rs.getInt("estado"));
				lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}
	
	
	public int eliminaAlumno(int id) {
		int salida = -1;

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "delete from alumno where idalumno  = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			System.out.println("SQL: " + pstm.toString());
			
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
	
	public int actualizaAlumno(Alumno obj) {
		int salida = -1;

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "UPDATE alumno  set nombre =?, dni =?, correo =?, fechaNacimiento =?, estado =? where idAlumno = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getDni());
			pstm.setString(3, obj.getCorreo());
			pstm.setDate(4, java.sql.Date.valueOf(obj.getFechaNacimiento()));
			pstm.setInt(5, obj.getEstado());
			pstm.setInt(6, obj.getIdAlumno());
			
			System.out.println("SQL: " + pstm.toString());
			
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
	
	public Alumno buscaAlumno(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Alumno a = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "SELECT * FROM alumno WHERE idAlumno = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			
			//imprimir el query para verificar que se arma correctamente
			System.out.println("SQL: " + pstm.toString());
			
			//Se ejecuta el query en la base de datos
			rs = pstm.executeQuery();

			if (rs.next()) {
				a = new Alumno();
				a.setIdAlumno(rs.getInt("idAlumno"));
				a.setNombre(rs.getString("nombre"));
				a.setDni(rs.getString("dni"));
				a.setCorreo(rs.getString("correo"));
				a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
				a.setEstado(rs.getInt("estado"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return a;
	}
	
	public List<Alumno> listaTodos() {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "SELECT * FROM alumno";
			pstm = conn.prepareStatement(sql);
			
			//imprimir el query para verificar que se arma correctamente
			System.out.println("SQL: " + pstm.toString());
			
			//Se ejecuta el query en la base de datos
			rs = pstm.executeQuery();

			while (rs.next()) {
				Alumno a = new Alumno();
				a.setIdAlumno(rs.getInt("idAlumno"));
				a.setNombre(rs.getString("nombre"));
				a.setDni(rs.getString("dni"));
				a.setCorreo(rs.getString("correo"));
				a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
				a.setEstado(rs.getInt("estado"));
				lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}
	
}






