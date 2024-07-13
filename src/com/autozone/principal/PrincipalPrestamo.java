package com.autozone.principal;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.autozone.dao.LibroDAO;
import com.autozone.dao.PrestamoDAO;
import com.autozone.models.Prestamo;

public class PrincipalPrestamo {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Scanner myInput = new Scanner(System.in);
		PrestamoDAO prestamoDAO = new PrestamoDAO();
		int iMenuPrestamo = 0;
		do {
			do {// menu PrincipalLibro miembro
				System.out.println("Que desea hacer? ");
				System.out.println("1) Registrar Prestamo");
				System.out.println("2) Registrar Devolucion");
				System.out.println("3) Ver historial de prestamos por miembro");
				System.out.println("4) Ver Disponibilidad de libros");
				System.out.println("5) Salir");

				try {
					iMenuPrestamo = Integer.parseInt(myInput.nextLine());
				} catch (NullPointerException | IllegalArgumentException e) {
					System.err.println("Opcion no valida");
				}
			} while (iMenuPrestamo > 5 || iMenuPrestamo < 1);
			
			switch (iMenuPrestamo) {
			case 1:
				try {
				System.out.println("Registrar Prestamo");
				System.out.println("Ingresa el ID del Miembro: ");
				int idMiembro = Integer.parseInt(myInput.nextLine());
				if(!prestamoDAO.verificarExistenciaMiembro(idMiembro)) {
					System.err.println("El Miembro no existe en la base de datos");
					break;
				}
				System.out.println("Ingresa el ID del Libro: ");
				int idLibro = Integer.parseInt(myInput.nextLine());
				if(!prestamoDAO.verificarExistenciaLibro(idLibro)){
					System.err.println("El Libro no existe en la base de datos");
					break;
				}
				if(!prestamoDAO.verDisponibilidadLibros(idLibro)){
					System.out.println("Libro no disponible");
					break;
				}
				
				Date fechaPrestamo = Date.valueOf(LocalDate.now());
				Prestamo prestamo = new Prestamo(idMiembro, idLibro, fechaPrestamo, null,"No");
				String estatus = "En Prestamo";
				prestamoDAO.registrarPrestamo(prestamo);
				prestamoDAO.cambiarEstatusLibro(idLibro, estatus);
				
				}catch (NullPointerException | NumberFormatException | SQLException e) {
					System.out.println("Algo salio mal");
					e.printStackTrace();
				}
				
				break;
				
			case 2:
				try {
				System.out.println("Registrar Devolucion");
				System.out.println("Ingresa el ID del Prestamo: ");
				int idPrestamo = Integer.parseInt(myInput.nextLine());
				
				if(prestamoDAO.verificarExistenciaPrestamo(idPrestamo)) {
					
					Date fechaDevolucion = Date.valueOf(LocalDate.now());
					String estatus = "Disponible";
					prestamoDAO.registrarDevolucion(idPrestamo, fechaDevolucion);
					prestamoDAO.cambiarEstatusLibro(prestamoDAO.obtenerIdLibroEnPrestamo(idPrestamo), estatus);
					
				}else {
					System.out.println("Id No Existe");
				}
				}catch (NullPointerException | NumberFormatException | SQLException e) {
					System.out.println("Algo salio mal");
					e.printStackTrace();
				}
				break;
				
			case 3:
				try {
				System.out.println("Historial de prestamos por miembro");
				System.out.println("Ingresa El Id Del Miembro");
				int idMiembro = Integer.parseInt(myInput.nextLine());
				
				if(prestamoDAO.verificarRegistrosMiembro(idMiembro)) {
					
					prestamoDAO.verHistorialPorMiembro(idMiembro);
					
				}else {
					System.out.println("No existen registros de prestamos");
				}
				}catch (NullPointerException | NumberFormatException | SQLException e) {
					System.out.println("Algo salio mal");
					e.printStackTrace();
				}
				break;
				
			case 4:
				try {
				System.out.println("Ver disponibilidad de libros");
				System.out.println("Ingresa el Id del Libro");
				int idLibro = Integer.parseInt(myInput.nextLine());
				
				if(prestamoDAO.verificarExistenciaLibro(idLibro)){
					
					if(prestamoDAO.verDisponibilidadLibros(idLibro)) {
						System.out.println("El libro si esta disponible");
					}else {
						System.out.println("Libro no disponible");
					}
					
				}else {
					System.out.println("No se encontro el libro");
				}
				}catch (NullPointerException | NumberFormatException | SQLException e) {
					System.out.println("Algo salio mal");
					e.printStackTrace();
				}
				
				break;
				
			}

		} while (iMenuPrestamo != 5);
	}
}
