package com.carjotu.controler;

import java.util.Vector;

import com.carjotu.dao.DAOLibro;
import com.carjotu.dao.DAOlibro_escritor;
import com.carjotu.model.ModeloLibreria;

public class ControladorLibro {
	
	
	private ModeloLibreria modelo;
	private Vector<DAOLibro> libros;
	
	//Constructor
	public ControladorLibro(ModeloLibreria modelo) {
		this.modelo=modelo;
	}
	
	//Este metodo retorna un vector de Libros y lo obtiene del metodo obtenerLibros()
	public Vector<DAOLibro> obtenerLibros() throws Exception{
		libros=modelo.obtenerLibros();
		return libros;
	}
	
	public DAOLibro obtenerLibro(long isbn) throws Exception {
		DAOLibro libro;
		libro=modelo.obtenerLibro(isbn);
		return libro;
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void insertarLibros(long isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,String cod_editorial, String cod_categoria) throws Exception{
		modelo.insertarLibros(isbn, titulo, precio, ud_stock, imagen, descripcion, cod_editorial, cod_categoria);;
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un libro
	public int modificarLibro(long isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,String cod_editorial, String cod_categoria) throws Exception{
		return modelo.modificarLibro(isbn, titulo, precio, ud_stock, imagen, descripcion, cod_editorial, cod_categoria);
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar un libro
	public int borrarLibro(long isbn)  throws Exception{
		
		 return modelo.borrarLibro(isbn);
	}
	
	//Mostrar autores de cada libro
	public Vector<DAOlibro_escritor>  obtenerLibrosEscritores(){
		return modelo.obtenerLibrosEscritores();
	}
	
	//Mostrar autores de un libro
		public Vector<DAOlibro_escritor>  obtenerLibroEscritorIsbn(long isbn){
			return modelo.obtenerLibroEscritorIsbn(isbn);
		}
		
	//Borrar autores de un libro
		public void eliminarLibroEscritor(String cod_autor, String isbn) throws Exception {
			modelo.eliminarLibroEscritor(cod_autor, isbn);
		}
		
	//Añadir autores a un libro
		public void insertarLibroEscritor(String isbn, String cod_autor) throws Exception{
			modelo.insertarLibroEscritor(isbn, cod_autor);			
		}
		
		

	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
	
}
