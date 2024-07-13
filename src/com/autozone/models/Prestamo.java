package com.autozone.models;

import java.sql.Date;

public class Prestamo {

	private int idPrestamo;
	private int idMiembro;
	private int idLibro;
	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private String disponible;
	
	public Prestamo(int idMiembro, int idLibro, Date fechaPrestamo, Date fechaDevolucion, String disponible) {
		this.idMiembro = idMiembro;
		this.idLibro =idLibro;
		this.fechaPrestamo=fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.disponible = disponible;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public int getIdMiembro() {
		return idMiembro;
	}

	public void setIdMiembro(int idMiembro) {
		this.idMiembro = idMiembro;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	
	
	
}
