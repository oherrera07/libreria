package com.autozone.dao;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.autozone.database.DatabaseConnection;
import com.autozone.models.Libro;
import com.autozone.utils.Validator;

public class LibroDAO {

	public void agregarLibro(Libro libro) throws SQLException, IllegalArgumentException, IllegalAccessException {
		Validator.validate(libro);

		String sql = "INSERT INTO tbl_libro(isbn, titulo, autor, precio, estatus) VALUES (?, ?, ?, ?, ?);";
		try (Connection connection = DatabaseConnection.getInstance().getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql); 
			statement.setLong(1, libro.getIsbn());
			statement.setString(2, libro.getTitulo());
			statement.setString(3, libro.getAutor());
			statement.setBigDecimal(4, libro.getPrecio());
			statement.setString(5, "Disponible");

			statement.execute();
		} 
	}

	
	public void actualizarIsbn(int idLibro, long nuevoIsbn) throws SQLException {
		String sql = "update tbl_libro set ISBM = ? where id_libro = ?";
		
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setLong(1, nuevoIsbn);
			statement.setInt(2, idLibro);
			
			statement.execute();
			
		}
	}
	
	public void actualizarTitulo(int idLibro, String nuevoTitulo) throws SQLException {
		String sql = "update tbl_libro set titulo = ? where id_libro = ?";
		
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, nuevoTitulo);
			statement.setInt(2, idLibro);
			
			statement.execute();
			
		}
	}
	
	public void actualizarAutor(int idLibro, String nuevoAutor) throws SQLException {
		String sql = "update tbl_libro set autor = ? where id_libro = ?";
		
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, nuevoAutor);
			statement.setInt(2, idLibro);
			
			statement.execute();
			
		}
	}
	
	public void actualizarPrecio(int idLibro, BigDecimal nuevoPrecio) throws SQLException {
		String sql = "update tbl_libro set precio = ? where id_libro = ?";
		
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setBigDecimal(1, nuevoPrecio);
			statement.setInt(2, idLibro);
			
			statement.execute();
			
		}
	}

	public void borrarLibro(int idLibro) throws SQLException {
		String sql = "DELETE from tbl_libro WHERE id_libro = ?";
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareCall(sql);

			statement.setInt(1, idLibro);

			statement.execute();
		} 
	}

	public List<Libro> obtenerLibros() throws SQLException {
		String sql = "SELECT * FROM tbl_libro;";
		List<Libro> libros = new ArrayList<Libro>();
		
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			Libro libro = null;

			while (rs.next()) {
				libro = new Libro(
						rs.getLong("ISBN"),
						rs.getString("titulo"),
						rs.getString("autor"),
						rs.getBigDecimal("precio"),
						rs.getString("estatus")
						);
				
				libro.setIdLibro(rs.getInt("id_libro"));
				libros.add(libro);
						
			}

		} 
		return libros;
	}

	public void buscarLibrosPorIsbn(Long isbn) {
		String sql = "SELECT * FROM tbl_libro where ISBN = ?;";
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, isbn);
			statement.execute();

			ResultSet rs = statement.getResultSet();

			while (rs.next()) {
				String output = rs.getInt(1) + " " + rs.getLong(2) + " " + rs.getString(3) + " " + rs.getString(4) + " "
						+ rs.getBigDecimal(5);
				System.out.println(output);
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

	}

	public void buscarLibrosPorTitulo(String titulo) {
		String sql = "SELECT * FROM tbl_libro where titulo = ?;";
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, titulo);
			statement.execute();

			ResultSet rs = statement.getResultSet();

			while (rs.next()) {
				String output = rs.getInt(1) + " " + rs.getLong(2) + " " + rs.getString(3) + " " + rs.getString(4) + " "
						+ rs.getBigDecimal(5);
				System.out.println(output);
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

	}

	public void buscarLibrosPorAutor(String autor) {
		String sql = "SELECT * FROM tbl_libro where autor = ?;";
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, autor);
			statement.execute();

			ResultSet rs = statement.getResultSet();

			while (rs.next()) {
				String output = rs.getInt(1) + " " + rs.getLong(2) + " " + rs.getString(3) + " " + rs.getString(4) + " "
						+ rs.getBigDecimal(5);
				System.out.println(output);
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

	}

	public void buscarLibrosPorRangoPrecio(double precioMasBajo, double precioMasAlto) {

		String sql = "{call sp_display_by_price(?,?)}";
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			CallableStatement statement = connection.prepareCall(sql);

			statement.setDouble(1, precioMasBajo);
			statement.setDouble(2, precioMasAlto);

			statement.execute();

			ResultSet rs = statement.getResultSet();


			while (rs.next()) {
				String output = rs.getInt(1) + " " + rs.getLong(2) + " " + rs.getString(3) + " " + rs.getString(4) + " "
						+ rs.getBigDecimal(5);
				System.out.println(output);
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}


}
