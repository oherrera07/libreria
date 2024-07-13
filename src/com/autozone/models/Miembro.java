package com.autozone.models;

import com.autozone.annotations.Email;
import com.autozone.annotations.NotNull;

public class Miembro {
	private int idMiembro;
	@NotNull
	private String nombre;
	@NotNull
	private int edad;
	@NotNull
	private String telefono;
	@Email
	private String email;
	
	public Miembro(String nombre, int edad, String telefono, String email) {
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdMiembro() {
		return idMiembro;
	}

	public void setIdMiembro(int idMiembro) {
		this.idMiembro = idMiembro;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}
