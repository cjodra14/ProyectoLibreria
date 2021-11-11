package dao;

import java.sql.*;
import java.util.*;

public class DAOlibro_escritor {
	int isbn;
	String cod_autor; //Solo se puede modificar el código de autor, no el isbn
	
	public DAOlibro_escritor() {
		
	}
	
	public DAOlibro_escritor(int isbn, String cod_autor){
		this.isbn = isbn;
		this.cod_autor = cod_autor;
	}
	
	
	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getCod_autor() {
		return cod_autor;
	}

	public void setCod_autor(String cod_autor) {
		this.cod_autor = cod_autor;
	}




		//INTEGRACIÓN CON LA BBDD
	//La clase DAO debe tener conexión con la BBDD para conectarse con su tabla
		private static Statement sentencia;
		private static ResultSet resultado;
		private int retorno;
			
	public static void setConexionBBDD(Statement sentencia, ResultSet resultado) {
		DAOlibro_escritor.sentencia = sentencia;
		DAOlibro_escritor.resultado = resultado;
			}
	
	//METODO CRUD
	//READ
	//Metodo que extrae todos los registros de la tabla
	
	public static Vector<DAOlibro_escritor> obtenerLibroEscritor() throws SQLException {
		String sqlQuery = "SELECT * FROM libreria.libro_escritor;";
		//Este metodo devolvera un vector de tipo <DAOlibro_escritor>
		return buscaResultadosConConsulta(sqlQuery);
		
	}
	public static DAOlibro_escritor obtenerLibroEscritorIsbn(String isbn) throws SQLException {
		String sqlQuery = "select * from libro_escritor WHERE isbn= '" + isbn + "';";
		//Este metodo devolvera un vector tipo <CategoriaDAO>
		return buscaResultadosUnLibroEscritor(sqlQuery);
		
	}
	
	public static DAOlibro_escritor obtenerLibroEscritorCodAutor(String cod_autor) throws SQLException {
		String sqlQuery = "select * from libro_escritor WHERE cod_autor= '" + cod_autor + "';";
		//Este metodo devolvera un vector tipo <CategoriaDAO>
		return buscaResultadosUnLibroEscritor(sqlQuery);
		
	}
	
	//Este metodo se utiliza para meter datos con la sentencia INSERT
	public static void insertarLibroEscritor(String isbn, String cod_autor) {
		
			try{
				String sqlQuery = "INSERT INTO libro_escritor VALUES ('"+isbn+"','"+cod_autor+"');";
				DAOlibro_escritor.sentencia.execute(sqlQuery);
				System.out.println("Se ha añadido el escritor "+cod_autor+" al libro con el isbn: "+isbn+" con exito.");
			}catch(SQLException e) {
				System.err.println("\nNo se han podido insertar el escritor "+cod_autor+" al libro con el isbn: "+isbn);
			}
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un autor
			//nombre_categoria
			public static void modificarLibroEscritor(String , String ){
				try {
					String sqlQuery = "UPDATE libreria.categoria SET nombre_categoria= '"+nombreCategoria+"' WHERE cod_categoria='"+idCategoria+"';";
					DAOlibro_escritor.sentencia.executeUpdate(sqlQuery);	
					System.out.println("La categoria con el código "+idCategoria+" ha sido modificado con éxito.");
				}catch(SQLException e) {
					System.err.println("Error al modificar el dato.\n"+e.getStackTrace());
				}
			} 
			
			//Este método ejecutará una sentencia DELETE para eliminar un autor
			public static int borrarCategoria(String idCategoria) throws SQLException {
				String sqlQuery="DELETE FROM libreria.categoria WHERE cod_categoria='"+idCategoria+"'";
		
				return DAOlibro_escritor.sentencia.executeUpdate(sqlQuery);
			}
			//FIN METODOS CRUD
			
			//MÉTODOS UTILITY DE CLASE
			//Método que extrae filas de la tabla a través de una consulta
			private static Vector<DAOlibro_escritor> buscaResultadosConConsulta(String consulta) throws SQLException{
				resultado = DAOlibro_escritor.sentencia.executeQuery(consulta);
				return cargaResultSetToVector(resultado);
				
			}
			
			private static Vector<DAOlibro_escritor> cargaResultSetToVector(ResultSet resultado) throws SQLException {
				Vector<DAOlibro_escritor> categorias = new Vector<DAOlibro_escritor>();
				DAOlibro_escritor categoria;
				
				while(resultado.next()) {
					String codCategoria = resultado.getString(1);
					String nombreCategoria = resultado.getString(2);
					categoria = new DAOlibro_escritor(codCategoria,nombreCategoria);
					categorias.addElement(categoria);
				}
				
				return categorias;
			}
			private static DAOlibro_escritor buscaResultadosUnaCategoria(String consulta) throws SQLException{
				resultado = DAOlibro_escritor.sentencia.executeQuery(consulta);
				return cargaResultSetToCategoria(resultado);
				
			}
			
			private static DAOlibro_escritor cargaResultSetToCategoria(ResultSet resultado) throws SQLException {
				
				DAOlibro_escritor categoria = null;
				
				while(resultado.next()) {
					String codCategoria = resultado.getString(1);
					String nombreCategoria = resultado.getString(2);
					categoria = new DAOlibro_escritor(codCategoria,nombreCategoria);			
				}
				
				return categoria;
			}
			
			public static void mostrarCategoria(DAOlibro_escritor categoria) {
				System.out.print("ID: "+categoria.getCod_categoria()+"|| Nombre: "+categoria.getNombre_categoria());
				System.out.println();
			}
	}
			//FIN MÉTODOS UTILITY DE CLASE


	
}
