package controlador;

import java.sql.SQLException;
import java.util.Vector;

import modelo.ModeloLibreria;
import dao.DAOAutor;
import dao.DAOCategoria;

public class ControladorCategoria {

	private ModeloLibreria modelo;
	private Vector<DAOCategoria> categorias;
	
	//Constructor
	public ControladorCategoria(ModeloLibreria modelo) {
		this.modelo=modelo;
	}
	
	//Este metodo retorna un vector de autores y lo obtiene del metodo obtenerAutores()
	public Vector<DAOCategoria> obtenerCategorias(){
		categorias=modelo.obtenerCategorias();
		return categorias;
	}
	
	public DAOCategoria obtenerCategoria(String cod_categoria) throws SQLException {
		DAOCategoria categoria;
		categoria=modelo.obtenerCategoria(cod_categoria);
		return categoria;
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void insertarCategoria(String idCategoria, String nombreCategoria) throws SQLException{
		modelo.insertarCategoria(idCategoria, nombreCategoria);;
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un autor
	public void modificarCategoria(String idCategoria,  String nombreCategoria) throws SQLException{
		modelo.modificarCategoria(idCategoria,  nombreCategoria);
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar un autor
	public int borrarCategoria(String idCategoria) throws SQLException{
		
		 return modelo.borrarCategoria(idCategoria);
	}
	

	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
}
