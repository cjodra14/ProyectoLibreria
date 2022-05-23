package com.carjotu.beans;

public class Producto {
	int cantidad;
	long isbn;
	public Producto(int cantidad, long isbn) {
		super();
		this.cantidad = cantidad;
		this.isbn = isbn;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	
	public void increase(){
		cantidad++;
	}
	public void decrease() {
		if (cantidad>1) {
			cantidad--;
		}
	}
	

}
