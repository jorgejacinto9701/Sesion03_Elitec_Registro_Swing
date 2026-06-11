package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Categoria;

public class CategoriaModel {

	public List<Categoria> ListaTodos() {
		List<Categoria> lista = new ArrayList<Categoria>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			//1 Crear conexion
			conn = util.MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "SELECT * FROM categoria";
			pstm = conn.prepareStatement(sql);
			
			//3 Ejecutar sentencia SQL
			rs = pstm.executeQuery();
			while (rs.next()) {
				Categoria c = new Categoria();
				c.setIdCategoria(rs.getInt("idCategoria"));
				c.setNombre(rs.getString("nombre"));
				lista.add(c);
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
        return lista;
	}
}
