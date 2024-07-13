package com.autozone.principal;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.autozone.dao.MiembroDAO;
import com.autozone.models.Miembro;

public class PrincipalMiembro {

	public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException {

		Scanner myInput = new Scanner(System.in);
		MiembroDAO miembroDAO = new MiembroDAO();
		int iMenuMiembro = 0;
		

		do {
			do {// menu PrincipalLibro miembro
				System.out.println("Que desea hacer? ");
				System.out.println("1) Agregar un miembro");
				System.out.println("2) Actualizar informacion de un miembro");
				System.out.println("3) Eliminar un miembro");
				System.out.println("4) Buscar Miembros");
				System.out.println("5) Salir");

				try {
					iMenuMiembro = Integer.parseInt(myInput.nextLine());
				} catch (NullPointerException | IllegalArgumentException e) {
					System.err.println("Opcion no valida");
				}
			} while (iMenuMiembro > 5 || iMenuMiembro < 1);

			switch (iMenuMiembro) {
			case 1:

				try {
					System.out.println("Agregar un miembro");
					System.out.println("Ingresa el nombre: ");
					String nombre = myInput.nextLine();
					if (nombre == null || nombre == "")
						throw new NullPointerException("El valor no puede ser nulo o vacio");
					if(!nombre.matches("[A-Z]*")) 
						throw new InputMismatchException("Ingrese un nombre valido");

					System.out.println("Ingresa la edad: ");
					int edad = Integer.parseInt(myInput.nextLine());
					System.out.println("Ingresa el telefono: ");
					String telefono = myInput.nextLine();
					if (telefono == null || telefono == "")
						throw new NullPointerException("El valor no puede ser nulo o vacio");
					System.out.println("Ingresa el email: ");
					String email = myInput.nextLine();

					Miembro miembro = new Miembro(nombre, edad, telefono, email);
					miembroDAO.agregarMiembro(miembro);
				} catch (NullPointerException | SQLException | IllegalArgumentException  e) {
					System.out.println("error en los datos");
					e.printStackTrace();
				}

				break;

			case 2:
				int iMenuActualizarMiembro;
				System.out.println("Actualizar informacion de un miembro");
				System.out.println("Ingresa el Id del miembro a actualizar: ");
				try {
					int idMiembro = Integer.parseInt(myInput.nextLine());
					do {

						System.out.println("Que es lo que quieres actualizar");
						System.out.println("1) Telefono");
						System.out.println("2) Email");
						System.out.println("3) Regresar");

						iMenuActualizarMiembro = Integer.parseInt(myInput.nextLine());

						switch (iMenuActualizarMiembro) {

						case 1:

							System.out.println("Actualizando Telefono");
							System.out.println("Ingresa el nuevo Telefono");
							String nuevoTelefono = myInput.nextLine();
							miembroDAO.actualizarTelefono(idMiembro, nuevoTelefono);

							break;

						case 2:
							System.out.println("Actualizando Email");
							System.out.println("Ingresa el nuevo Email");
							String nuevoEmail = myInput.nextLine();
							miembroDAO.actualizarEmail(idMiembro, nuevoEmail);

							break;

						case 3:

							break;

						default:
							System.out.println("opcion no valida");
							break;

						}

					} while (iMenuActualizarMiembro != 3);
				} catch (NoSuchElementException | NullPointerException | IllegalArgumentException | SQLException e) {
					System.out.println("error en los datos");
					e.printStackTrace();
				}

				break;

			case 3:
				try {
					System.out.println("Eliminar Miembro");
					System.out.println("Ingresa el Id del miembro a eliminar");
					int idMiembro = Integer.parseInt(myInput.nextLine());
					miembroDAO.borrarMiembro(idMiembro);
				} catch (NoSuchElementException | NullPointerException | IllegalArgumentException | SQLException e) {
					System.out.println("error en los datos");
					e.printStackTrace();
				}
				break;

			case 4:
				int iMenuBusqueda = 0;
				do {
				System.out.println("Buscar Miembros");
				System.out.println("Como quiere buscar los miembros?");
				System.out.println("1)Por nombre");
				System.out.println("2)Por Id");
				System.out.println("3)Por telefono");
				System.out.println("4)Regresar");
				try {
					iMenuBusqueda = Integer.parseInt(myInput.nextLine());
				}catch (Exception e) {
					System.out.println("opcion no valida");
					e.printStackTrace();
				}
					
						try {
						switch (iMenuBusqueda) {

						case 1:

							System.out.println("Buscando por nombre");
							System.out.println("ingrese el nombre a buscar");
							String nombre = myInput.nextLine();
							miembroDAO.buscarNombreMiembro(nombre);

							break;
						case 2:

							System.out.println("Buscando por Id");
							System.out.println("ingrese el Id a buscar");
							int idMiembro = Integer.parseInt(myInput.nextLine());
							miembroDAO.buscarIdMiembro(idMiembro);

							break;
						case 3:

							System.out.println("Buscando por telefono");
							System.out.println("ingrese el telefono a buscar");
							String telefono = myInput.nextLine();
							miembroDAO.buscarTelefonoMiembro(telefono);

							break;

						case 4:

							break;

						default:
							System.out.println("opcion no valida");
							break;

						}
					} catch (NullPointerException | IllegalArgumentException |SQLException|NoSuchElementException e) {
						System.err.println("Opcion no valida");
					}
				} while(iMenuBusqueda!=4);

				break;

			case 5:

				System.exit(0);
				break;

			}

		} while (iMenuMiembro != 5);

	}

}
