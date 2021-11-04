package controlador;

import java.util.Vector;

import modelo.ModeloLibreria;
import dao.DAOAutor;

public class ControladorAutor {

	private ModeloLibreria modelo;
	private Vector<DAOAutor> autores;
	
	//Constructor
	public ControladorAutor(ModeloLibreria modelo) {
		this.modelo=modelo;
	}
	
	//Este metodo retorna un vector de autores y lo obtiene del metodo obtenerAutores()
	public Vector<DAOAutor> obtenerAutores(){
		autores=modelo.obtenerAutores();
		return autores;
	}
	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
}
