package controlador;

import java.sql.SQLException;
import java.util.Vector;

import dao.DAOEditorial;
import dao.DAOLibro;
import dao.DAOlibro_escritor;
import modelo.ModeloLibreria;

public class ControladorLibro {
	
	
	private ModeloLibreria modelo;
	private Vector<DAOLibro> libros;
	
	//Constructor
	public ControladorLibro(ModeloLibreria modelo) {
		this.modelo=modelo;
	}
	
	//Este metodo retorna un vector de Libros y lo obtiene del metodo obtenerLibros()
	public Vector<DAOLibro> obtenerLibros(){
		libros=modelo.obtenerLibros();
		return libros;
	}
	
	public DAOLibro obtenerLibro(long isbn) throws SQLException {
		DAOLibro libro;
		libro=modelo.obtenerLibro(isbn);
		return libro;
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void insertarLibros(long isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,String cod_editorial, String cod_categoria) throws SQLException{
		modelo.insertarLibros(isbn, titulo, precio, ud_stock, imagen, descripcion, cod_editorial, cod_categoria);;
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un libro
	public void modificarLibro(long isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,String cod_editorial, String cod_categoria) throws SQLException{
		modelo.modificarLibro(isbn, titulo, precio, ud_stock, imagen, descripcion, cod_editorial, cod_categoria);;
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar un libro
	public int borrarLibro(long isbn)  throws SQLException{
		
		 return modelo.borrarLibro(isbn);
	}
	
	//Mostrar autores de cada libro
	public Vector<DAOlibro_escritor>  obtenerLibrosEscritores(){
		return modelo.obtenerLibrosEscritores();
	}
	

	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
}
