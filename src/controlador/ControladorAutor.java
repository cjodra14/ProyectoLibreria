package controlador;

import java.sql.SQLException;
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
	public DAOAutor obtenerAutor(String idAutor) throws SQLException{
		return modelo.obtenerAutor(idAutor);
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void insertarDatos(String idAutor, String nombreAutor, String apel1, String apel2) throws SQLException{
		modelo.insertarDatos(idAutor,nombreAutor,apel1,apel2);
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un autor
	public void modificarAutor(String idAutor, String nombreAutor, String apel1, String apel2) throws SQLException{
		modelo.modificarAutor(idAutor,nombreAutor,apel1,apel2);
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar un autor
	public void borrarAutor(String idAutor) throws SQLException{
		modelo.borrarAutor(idAutor);
	}
	
	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
}
