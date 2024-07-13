package com.autozone.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.autozone.database.DatabaseConnection;
import com.autozone.models.Libro;
import com.autozone.models.Miembro;

public class MiembroDAO {
	
	public void agregarMiembro(Miembro miembro) throws SQLException, IllegalArgumentException, IllegalAccessException {
		String sql = "INSERT INTO tbl_miembro(nombre, edad, telefono, email) VALUES (?, ?, ?, ?);";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, miembro.getNombre());
			statement.setInt(2, miembro.getEdad());
			statement.setString(3, miembro.getTelefono());
			statement.setString(4, miembro.getEmail());
			
			statement.execute();
		}
	
	}
	
	public void actualizarTelefono(int idMiembro, String nuevoTelefono) throws SQLException {
		String sql = "update tbl_miembro set telefono = ? where id_miembro = ?";
		
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
				
			statement.setString(1, nuevoTelefono);
			statement.setInt(2, idMiembro);
			
			statement.execute();
			
		}
	}
	
	public void actualizarEmail(int idMiembro, String nuevoEmail) throws SQLException {
		String sql = "update tbl_miembro set email = ? where id_miembro = ?";
		
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, nuevoEmail);
			statement.setInt(2, idMiembro);
			
			statement.execute();
			
		}
	}
	
	
	public void borrarMiembro(int idMiembro) throws SQLException{
		
		String sql = "DELETE from tbl_miembro WHERE id_miembro = ?";
		try(Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareCall(sql);
			
			statement.setInt(1, idMiembro);
			
			statement.execute();			
		} 
		
	}
	
	public void buscarIdMiembro(int idMiembro) throws SQLException {
		String sql = "SELECT * from tbl_miembro WHERE id_miembro = ?";
		
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, idMiembro);
			statement.execute();

			ResultSet rs = statement.getResultSet();

			while (rs.next()) {
				String output = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " "
						+ rs.getString(5);
				System.out.println(output);
			}
	
						
			}
		
	}
	
	public void buscarNombreMiembro(String nombre) throws SQLException{
		String sql = "SELECT * from tbl_miembro WHERE nombre = ?";
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, nombre);
			statement.execute();

			ResultSet rs = statement.getResultSet();

			while (rs.next()) {
				String output = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " "
						+ rs.getString(5);
				System.out.println(output);
			}
	
						
			}
		
	}
	
	public void buscarTelefonoMiembro(String telefono) throws SQLException{
		String sql = "SELECT * from tbl_miembro WHERE telefono = ?";
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, telefono);
			statement.execute();

			ResultSet rs = statement.getResultSet();

			while (rs.next()) {
				String output = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " "
						+ rs.getString(5);
				System.out.println(output);
			}
		
						
			}
		
	}
		

}
