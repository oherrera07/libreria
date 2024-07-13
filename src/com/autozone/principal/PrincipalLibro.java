package com.autozone.principal;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.autozone.dao.LibroDAO;
import com.autozone.models.Libro;
import com.autozone.utils.Validator;

public class PrincipalLibro {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, SQLException, NullPointerException {
		// TODO Auto-generated method stub
		Scanner myInput = new Scanner(System.in);
		LibroDAO libroDAO = new LibroDAO();
		int op1 = 0;
		int idLibro = 0;
		int inputBusqueda=0;
		do {
			do {// menu PrincipalLibro
				System.out.println("Que desea hacer? ");
				System.out.println("1) Agregar un libro nuevo");
				System.out.println("2) Actualizar un libro");
				System.out.println("3) Eliminar un libro");
				System.out.println("4) Listar todos los libros");
				System.out.println("5) Buscar Libros");
				System.out.println("6) Salir");
				
				try {
				op1 = Integer.parseInt(myInput.nextLine());
				}catch (NullPointerException | IllegalArgumentException e) {
					System.err.println("Opcion no valida");
				}
			} while (op1 > 6 || op1 < 1);

			switch (op1) {

			case 1: // Agregar un libro nuevo
				
			
					
				try {
				System.out.println("Ingresa el ISBN: ");
				
				long isbn = Long.parseLong(myInput.nextLine());
				
				
				System.out.println("Ingresa el titulo: ");
				String titulo = myInput.nextLine();
				if(titulo == null || titulo=="") throw new NullPointerException("El valor no puede ser nulo o vacio");
				System.out.println("Ingresa el autor: ");
				String autor = myInput.nextLine();
				if(autor == null || autor=="") throw new NullPointerException("El valor no puede ser nulo o vacio");
				System.out.println("Ingresa el precio: ");
				BigDecimal precio = new BigDecimal(myInput.nextLine());

				Libro libro = new Libro(isbn, titulo, autor, precio,"");
				libroDAO.agregarLibro(libro);
			
			
				}catch (NumberFormatException | NullPointerException | SQLException e) {
					System.out.println("error en los datos");
					e.printStackTrace();				}
				
				
					
				

				// System.out.println("Libro agregado");
				break;
				

			case 2: // Actualizar un libro
			
				
				
				try {
				System.out.println("Ingresa el ID del libro a modificar");
				idLibro = Integer.parseInt(myInput.nextLine()); 
			
				
				
				
				
					int op2;

					do {

						System.out.println("Que quieres actualizar?");
						System.out.println("1) ISBN");
						System.out.println("2) Titulo");
						System.out.println("3) Autor");
						System.out.println("4) Precio");
						System.out.println("5) Regresar");
						op2 = Integer.parseInt(myInput.nextLine());
						switch (op2) {
						case 1: 
							
							System.out.println("Ingresa el nuevo ISBN: ");
							long nuevoIsbn = Long.parseLong(myInput.nextLine());
							libroDAO.actualizarIsbn(idLibro, nuevoIsbn);
							
							break;
						case 2:

							System.out.println("Ingresa el nuevo titulo: ");
							String nuevoTitulo = myInput.nextLine();
							libroDAO.actualizarTitulo(idLibro, nuevoTitulo);
							

							break;
						case 3:

							System.out.println("Ingresa el nuevo autor: ");
							String nuevoAutor = myInput.nextLine();
							libroDAO.actualizarAutor(idLibro, nuevoAutor);

							break;
						case 4:
							System.out.println("Ingresa el nuevo precio: ");
							BigDecimal nuevoPrecio = new BigDecimal(myInput.nextLine());
							libroDAO.actualizarPrecio(idLibro, nuevoPrecio);

							break;
						case 5:
							break;
						
							
						default:
							System.out.println("opcion no valida");
							break;
						}
					

					} while (op2 != 5);

					
				
				}catch (NoSuchElementException  | NullPointerException| IllegalArgumentException | SQLException e) {
					System.out.println("error en los datos");
					e.printStackTrace();
				}
				break;

			case 3: // Eliminar un libro
				try {
				System.out.println("Ingresa el ID del libro a eliminar: ");
				idLibro = Integer.parseInt(myInput.nextLine());
				
					libroDAO.borrarLibro(idLibro);
				
				
				}catch (NumberFormatException e) {
					System.out.println("algo salio mal");
					e.printStackTrace();
				}
				break;

			case 4: // listar libros
				
				List<Libro> libros = libroDAO.obtenerLibros();
				for (Libro l : libros) {
					System.out.println(l.getIdLibro() + "|" + l.getIsbn() + "|" + l.getTitulo() + "|" + l.getAutor()
							+ "|" + l.getPrecio());
				}
				
				
				break;

			case 5: // Buscar libros
				
				do {
					System.out.println("Que criterio de busqueda deseas elegir? ");
					System.out.println("1) Isbn ");
					System.out.println("2) Titulo ");
					System.out.println("3) Autor ");
					System.out.println("4) Rango de Precios ");
					System.out.println("5) Regresar ");
					try {
					inputBusqueda = Integer.parseInt(myInput.nextLine());
					}catch (Exception e) {
						System.out.println("opcion no valida");
						e.printStackTrace();
					}
					try {
					switch (inputBusqueda) {
					case 1:
						System.out.println("Ingresa el Isbn a buscar: ");
						long isbn = Long.parseLong(myInput.nextLine());
						libroDAO.buscarLibrosPorIsbn(isbn);
						break;
					case 2:
						System.out.println("Ingresa el Titulo a buscar: ");
						String titulo = myInput.nextLine();
						libroDAO.buscarLibrosPorTitulo(titulo);
						break;
					case 3:
						System.out.println("Ingresa el Autor a buscar: ");
						String autor = myInput.nextLine();
						libroDAO.buscarLibrosPorAutor(autor);
						break;
					case 4:
						System.out.println("Ingresa el valor mas bajo del rango: ");
						double precioMasBajo = Double.parseDouble(myInput.nextLine());
						System.out.println("Ingresa el valor mas alto del rango: ");
						double precioMasAlto = Double.parseDouble(myInput.nextLine());
						libroDAO.buscarLibrosPorRangoPrecio(precioMasBajo, precioMasAlto);
						break;
					}
					}catch (Exception e) {
						System.out.println("datos no validos");
						e.printStackTrace();
					}
				} while (inputBusqueda != 5);
				break;
			case 6: // Salir
				System.exit(0);
				break;
				
			default:
				System.out.println("opcion no valida");
				break;
			}
		} while (op1 != 6);

	}
}
