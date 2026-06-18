package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidad.Cliente;
import util.MySqlDBConexion;

public class ClienteModel {

	public int insertaCliente(Cliente obj) {
        int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "INSERT INTO cliente (nombre, dni, fechaRegistro, estado, idCategoria) VALUES (?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getDni());
			pstm.setTimestamp(3, java.sql.Timestamp.valueOf(obj.getFechaRegistro()));
			pstm.setInt(4, obj.getEstado());
			pstm.setInt(5, obj.getCategoria().getIdCategoria());
			
			//3 Ejecutar sentencia SQL
			salida = pstm.executeUpdate();
			
		}catch (Exception e) {
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
	
	
	public boolean existeClientePorDNI(String dni) {
		boolean existe = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			//1 Crear conexion
			conn = util.MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "SELECT count(*) FROM cliente WHERE dni = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dni);
			
			//3 Ejecutar sentencia SQL
			rs = pstm.executeQuery();
			if (rs.next()) {
				existe = rs.getInt(1) > 0;
			}
		}catch (Exception e) {
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
        return existe;
	}
	
	public int insertaClienteConStoredProcedure(Cliente obj) {
        int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "call sp_inserta_cliente(?,?,?,?,?)";
			pstm = conn.prepareCall(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getDni());
			pstm.setTimestamp(3, java.sql.Timestamp.valueOf(obj.getFechaRegistro()));
			pstm.setInt(4, obj.getEstado());
			pstm.setInt(5, obj.getCategoria().getIdCategoria());
			
			System.out.println("SQL ==> " + pstm);
			
			//3 Ejecutar sentencia SQL
			salida = pstm.executeUpdate();
			
		}catch (Exception e) {
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



