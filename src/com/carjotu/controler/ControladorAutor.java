package com.carjotu.controler;

import java.sql.SQLException;
import java.util.Vector;

import com.carjotu.dao.DAOAutor;
import com.carjotu.dao.DAOlibro_escritor;
import com.carjotu.model.ModeloLibreria;

public class ControladorAutor {

	private ModeloLibreria modelo;
	private Vector<DAOAutor> autores;
	
	//Constructor
	public ControladorAutor(ModeloLibreria modelo) {
		this.modelo=modelo;
	}
	
	//Este metodo retorna un vector de autores y lo obtiene del metodo obtenerAutores()
	public Vector<DAOAutor> obtenerAutores() throws Exception{
		autores=modelo.obtenerAutores();
		return autores;
	}
	
	public DAOAutor obtenerAutor(String idAutor) throws Exception {
		DAOAutor autor;
		autor=modelo.obtenerAutor(idAutor);
		return autor;
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void insertarAutor(String idAutor, String nombreAutor, String apel1, String apel2) throws Exception{
		modelo.insertarAutor(idAutor,nombreAutor,apel1,apel2);
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un autor
	public int modificarAutor(String idAutor, String nombreAutor, String apel1, String apel2) throws Exception{
		return modelo.modificarAutor(idAutor,nombreAutor,apel1,apel2);
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar un autor
	public int borrarAutor(String idAutor) throws Exception{
		
		 return modelo.borrarAutor(idAutor);
	}
	
	
	//Mostrar libros de cada autor
		public Vector<DAOlibro_escritor>  obtenerLibrosEscritores(){
			return modelo.obtenerLibrosEscritores();
		}
		
		//Mostrar los libros de un autor
			public Vector<DAOlibro_escritor>  obtenerLibroEscritorporCodAutor(String codAutor){
				return modelo.obtenerLibroEscritorporCodAutor(codAutor);
			}
	
	

	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
}
