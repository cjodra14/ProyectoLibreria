package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import dao.*;

public class ModeloLibreria {

		private Connection conexionBBDD;
		private Statement sentencia;
		private DAOAutor autor;
		private DAOCategoria categoria;
		private DAOEditorial editorial;
		private Vector<DAOAutor> autores;
		private Vector<DAOCategoria> categorias;
		private Vector<DAOEditorial> editoriales;
		
		Properties servicioElegido= new Properties();
		
		public ModeloLibreria() {
			try {
				try {
					servicioElegido.load(new FileInputStream(new File("serviceElegido.properties")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			this.conexionBBDD = servicio.ServicioBBDD.obtenerServicio(servicioElegido.getProperty("servicio")).obtenerConexion();
			this.sentencia = this.conexionBBDD.createStatement();
			DAOAutor.setConexionBBDD(sentencia, null);
			DAOEditorial.setConexionBBDD(sentencia, null);
			DAOCategoria.setConexionBBDD(sentencia, null);
			
			} catch (SQLException e) {
				System.err.println();
				e.printStackTrace();
			}
			
		}
		public void terminar () {
			try {
					sentencia.close();
					conexionBBDD.close();
				
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO AL CERRAR LA CONEXIÓN ");
				e.printStackTrace();
			}
		}
		
		
		////////////////////////////////////////////////////////////////
		///// INICIO DE LA PARTE DE NEGOCIO ///////////////////////////
		//////////////////////////////////////////////////////////////
		
		
		
		
		
		///////////////  Autor //////////////////////////////////////////
		public Vector<DAOAutor> obtenerAutores(){
			try {
				
			
			
			autores=DAOAutor.obtenerAutores();
			
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  AUTOR");
				e.printStackTrace();
			}
			return autores;
			
		}
		//Método que extrae todos los registros de la tabla con una sentencia SELECT
		public DAOAutor obtenerAutor(String idAutor) throws SQLException {
			return DAOAutor.obtenerAutor(idAutor);
		}
		//Este método se utiliza para meter datos con la sentencia INSERT
		public void insertarAutor(String idAutor, String nombreAutor, String apel1, String apel2) {
			DAOAutor.insertarDatos(idAutor, nombreAutor,  apel1,  apel2);
		}
		//Este método ejecutará una sentencia UPDATE para modificar un autor
		public void modificarAutor(String idAutor, String nombreAutor, String apel1, String apel2) {
			DAOAutor.modificarAutor(idAutor, nombreAutor, apel1, apel2);
		}
		//Este método ejecutará una sentencia DELETE para eliminar un autor
		public int borrarAutor(String idAutor) throws SQLException {
			
			return DAOAutor.borrarAutor(idAutor);
		}
		
		
		
		//////////////////////////   Categoria  //////////////////////////////////
		public Vector<DAOCategoria> obtenerCategorias(){
			try {		
			
			categorias=DAOCategoria.obtenerCategorias();
			
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  CATEGORIA");
				e.printStackTrace();
			}
			return categorias;
			
		}
		//Método que extrae todos los registros de la tabla con una sentencia SELECT
		public DAOCategoria obtenerCategoria(String cod_categoria) throws SQLException {
			return DAOCategoria.obtenerCategoria(cod_categoria);
		}
		//Este método se utiliza para meter datos con la sentencia INSERT
		public void insertarCategoria(String idCategoria, String nombreCategoria) {
			DAOCategoria.insertarCategoria(idCategoria, nombreCategoria);
		}
		//Este método ejecutará una sentencia UPDATE para modificar una categoria
		public void modificarCategoria(String nombreCategoria,String idCategoria ) {
			DAOCategoria.modificarCategoria(nombreCategoria, idCategoria);
		}
		//Este método ejecutará una sentencia DELETE para eliminar una categoria
		public int borrarCategoria(String idCategoria) throws SQLException {
			
			return DAOCategoria.borrarCategoria(idCategoria);
		}
		
		
		//////////////////////   Editorial ////////////////////////////////////
		public Vector<DAOEditorial> obtenerEditoriales(){
			try {				
			editoriales=DAOEditorial.obtenerEditoriales();
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  EDITORIAL");
				e.printStackTrace();
			}
			return editoriales;
			
		}
		//Método que extrae todos los registros de la tabla con una sentencia SELECT
		public DAOEditorial obtenerEditorial(String cod_editorial) throws SQLException {
			return DAOEditorial.obtenerEditorial(cod_editorial);
		}
		//Este método se utiliza para meter datos con la sentencia INSERT
		public void insertarEditorial(String codEditorial, String nombreEditorial) throws SQLException {
			DAOEditorial.insertarEditorial(codEditorial, nombreEditorial);
		}
		//Este método ejecutará una sentencia UPDATE para modificar una editorial
		public void modificarEditorial(String codEditorial,String nombreEditorial ) {
			DAOEditorial.modificarEditorial(codEditorial, nombreEditorial);
		}
		//Este método ejecutará una sentencia DELETE para eliminar una editorial
		public int borrarEditorial(String codEditorial) throws SQLException {
			
			return DAOEditorial.borrarEditorial(codEditorial);
		}
		
	

}
