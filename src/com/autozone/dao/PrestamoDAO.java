package com.autozone.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import com.autozone.database.DatabaseConnection;
import com.autozone.models.Prestamo;

public class PrestamoDAO {
	
	public void registrarPrestamo(Prestamo prestamo) throws SQLException, IllegalArgumentException, IllegalAccessException {
		String sql = "INSERT INTO tbl_prestamo(id_miembro, id_libro, fecha_prestamo,  disponible) VALUES (?, ?, ?, ?);";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, prestamo.getIdMiembro());
			statement.setInt(2, prestamo.getIdLibro());
			statement.setDate(3, prestamo.getFechaPrestamo());
			//statement.setDate(4, prestamo.getFechaDevolucion());
			statement.setString(4, prestamo.getDisponible());
			
			statement.execute();
			
			System.out.println("Se registro un prestamo");
		}
	
	}
	
	public void cambiarEstatusLibro(int idLibro, String estatus) throws SQLException{
		String sql = "update tbl_libro set estatus = ? where id_libro = ? ";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, estatus);
			statement.setInt(2, idLibro);
			statement.execute();
		}
		
	}
	
	public void registrarDevolucion(int idPrestamo, Date fechaDevolucion) throws SQLException, IllegalArgumentException, IllegalAccessException {
		String sql = "UPDATE tbl_prestamo SET fecha_devolucion = ?, disponible = ? WHERE id_prestamo = ?";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, fechaDevolucion);
			statement.setString(2, "Si");
			statement.setInt(3, idPrestamo);
			
			statement.execute();
			
			System.out.println("Se registro una devolucion");
		}
	
	}
	
	public boolean verificarExistenciaMiembro(int idMiembro) throws SQLException{
		boolean res = false;
		String sql = "SELECT id_miembro FROM tbl_miembro WHERE id_miembro =?;";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idMiembro);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			if(rs.next())
				res = true;
		}
		return res;
	}
	
	public boolean verificarExistenciaLibro(int idLibro) throws SQLException{
		boolean res = false;
		String sql = "SELECT id_libro FROM tbl_libro WHERE id_libro =?;";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idLibro);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			if(rs.next())
				res = true;
		}
		return res;
	}
	
	public boolean verificarExistenciaPrestamo(int idPrestamo) throws SQLException{
		boolean res = false;
		String sql = "SELECT id_prestamo FROM tbl_prestamo WHERE id_prestamo =?;";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idPrestamo);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			if(rs.next())
				res = true;
		}
		return res;
	}
	
	public int obtenerIdLibroEnPrestamo(int idPrestamo) throws SQLException{
		int idLibro = 0;
		String sql = "SELECT id_libro FROM tbl_prestamo WHERE id_prestamo =?;";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idPrestamo);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			while(rs.next()) {
				 idLibro =rs.getInt(1);
			}
		}
				
		return idLibro;
	}
	
	public boolean verificarRegistrosMiembro(int idMiembro) throws SQLException{
		boolean res = false;
		String sql = "SELECT id_miembro FROM tbl_prestamo WHERE id_miembro =?;";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idMiembro);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			if(rs.next())
				res = true;
		}
		return res;
	}
	
	public boolean verificarRegistrosLibro(int idLibro) throws SQLException{
		boolean res = false;
		String sql = "SELECT id_miembro FROM tbl_prestamo WHERE id_miembro =?;";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idLibro);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			if(rs.next())
				res = true;
		}
		return res;
	}
	
	public void verHistorialPorMiembro(int idMiembro) throws SQLException{
		String sql = "{call sp_ver_historial(?)}";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()) {
			CallableStatement statement = connection.prepareCall(sql);
			
			statement.setInt(1, idMiembro);
			
			statement.execute();		
			ResultSet rs = statement.getResultSet();
			while (rs.next()) {
				String output = 	rs.getInt(1)+" "
						+rs.getInt(2)+" "+rs.getString(3)+" "
						+rs.getInt(4)+" "+rs.getString(5)+" "
						+rs.getString(6)+" "+rs.getString(7);
				System.out.println(output);
				
			}
		}
	}
	
	public boolean verDisponibilidadLibros(int idLibro) throws SQLException{
		boolean res = false;
		String sql = "{call sp_ver_disponibilidad_libros(?)}";
		
		try(Connection connection = DatabaseConnection.getInstance().getConnection()) {
			CallableStatement statement = connection.prepareCall(sql);
			
			statement.setInt(1, idLibro);
			
			statement.execute();		
			ResultSet rs = statement.getResultSet();
			if (rs.next()) {
				
				res=true;
				
			}
		}
		return res;
			
		}

}
