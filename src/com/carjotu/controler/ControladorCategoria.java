package com.carjotu.controler;

import java.sql.SQLException;
import java.util.Vector;

import com.carjotu.dao.DAOAutor;
import com.carjotu.dao.DAOCategoria;
import com.carjotu.model.ModeloLibreria;

public class ControladorCategoria {

	private ModeloLibreria modelo;
	private Vector<DAOCategoria> categorias;
	
	//Constructor
	public ControladorCategoria(ModeloLibreria modelo) {
		this.modelo=modelo;
	}
	
	//Este metodo retorna un vector de autores y lo obtiene del metodo obtenerAutores()
	public Vector<DAOCategoria> obtenerCategorias() throws Exception{
		categorias=modelo.obtenerCategorias();
		return categorias;
	}
	
	public DAOCategoria obtenerCategoria(String cod_categoria) throws Exception {
		DAOCategoria categoria;
		categoria=modelo.obtenerCategoria(cod_categoria);
		return categoria;
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void insertarCategoria(String idCategoria, String nombreCategoria) throws Exception{
		modelo.insertarCategoria(idCategoria, nombreCategoria);;
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un autor
	public void modificarCategoria(String idCategoria,  String nombreCategoria) throws Exception{
		modelo.modificarCategoria(idCategoria,  nombreCategoria);
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar un autor
	public int borrarCategoria(String idCategoria) throws Exception{
		
		 return modelo.borrarCategoria(idCategoria);
	}
	

	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
}
