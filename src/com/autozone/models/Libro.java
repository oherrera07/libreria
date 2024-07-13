package com.autozone.models;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.autozone.annotations.NotNull;

public class Libro {
	
	private int idLibro;
	@NotNull
	private long isbn;
	@NotNull
	private String titulo;
	@NotNull
	private String autor;
	
	private BigDecimal precio;
	
	private String estatus;
	
	public Libro(long isbn, String titulo, String autor, BigDecimal precio, String estatus) {
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.isbn = isbn;
		
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

}
