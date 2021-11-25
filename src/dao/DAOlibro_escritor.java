package dao;

import java.sql.*;
import java.util.*;

public class DAOlibro_escritor {
	long isbn;
	String cod_autor; //Solo se puede modificar el código de autor, no el isbn
	String nombreAutor, pApelAutor,sApelAutor,tituloLibro;
	
	public DAOlibro_escritor() {
		
	}
	
	public DAOlibro_escritor(long isbn, String cod_autor){
		this.isbn=isbn;
		this.cod_autor=cod_autor;
	}
	
	public DAOlibro_escritor(long isbn, String cod_autor, String nombreAutor, String pApelAutor, String sApelAutor, String tituloLibro){
		this.isbn = isbn;
		this.cod_autor = cod_autor;
		this.nombreAutor = nombreAutor;
		this.pApelAutor = pApelAutor;
		this.sApelAutor = sApelAutor;
		this.tituloLibro = tituloLibro;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getCod_autor() {
		return cod_autor;
	}

	public void setCod_autor(String cod_autor) {
		this.cod_autor = cod_autor;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public String getpApelAutor() {
		return pApelAutor;
	}

	public void setpApelAutor(String pApelAutor) {
		this.pApelAutor = pApelAutor;
	}

	public String getsApelAutor() {
		return sApelAutor;
	}

	public void setsApelAutor(String sApelAutor) {
		this.sApelAutor = sApelAutor;
	}

	public String getTituloLibro() {
		return tituloLibro;
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
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
	
	public static Vector<DAOlibro_escritor> obtenerLibrosEscritores() throws SQLException {
		String sqlQuery = "SELECT L.ISBN, A.cod_autor, A.nombre, A.p_apel, A.s_apel, L.titulo FROM libro_escritor LE, libro L, autor A WHERE A.cod_autor=LE.cod_autor AND L.isbn=LE.isbn;";
		//Este metodo devolvera un vector de tipo <DAOlibro_escritor>
		return buscaResultadosConConsulta(sqlQuery);
		
	}
	
	public static Vector<DAOlibro_escritor> obtenerLibroEscritoresObj(String cod_autor) throws SQLException {
		String sqlQuery = "SELECT * FROM libro_escritor WHERE cod_autor='"+cod_autor+"';";
		//Este metodo devolvera un vector de tipo <DAOlibro_escritor>
		return buscaResultadosConConsulta(sqlQuery);
		
	}
	
	
	public static Vector<DAOlibro_escritor> obtenerLibroEscritorIsbn(long isbn) throws SQLException {
		String sqlQuery = "SELECT L.ISBN, A.cod_autor, A.nombre, A.p_apel, A.s_apel, L.titulo FROM libro_escritor LE, libro L, autor A WHERE A.cod_autor=LE.cod_autor AND L.isbn=LE.isbn AND L.isbn= '" + isbn + "';";
		//Este metodo devolvera un vector tipo <CategoriaDAO>
		return buscaResultadosConConsulta(sqlQuery);
		
	}
	public static Vector<DAOlibro_escritor> obtenerLibroEscritorporCodAutor(String codAutor) throws SQLException {
		String sqlQuery = "SELECT L.ISBN, A.cod_autor, A.nombre, A.p_apel, A.s_apel, L.titulo FROM libro_escritor LE, libro L, autor A WHERE A.cod_autor=LE.cod_autor AND L.isbn=LE.isbn AND A.cod_autor= '" + codAutor + "';";
		//Este metodo devolvera un vector tipo <CategoriaDAO>
		return buscaResultadosConConsulta(sqlQuery);
		
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
	
	//Este método ejecutará una sentencia UPDATE para modificar el autor de un libro
			//nombre_categoria
			public static void modificarLibroEscritor(String isbn, String cod_autor){
				try {
					String sqlQuery = "UPDATE libreria.libro_escritor SET cod_autor= '"+cod_autor+"' WHERE isbn='"+isbn+"';";
					DAOlibro_escritor.sentencia.executeUpdate(sqlQuery);	
					System.out.println("El autor del libro con el isbn "+isbn+" ha sido modificado con éxito.");
				}catch(SQLException e) {
					System.err.println("Error al modificar el dato.\n"+e.getStackTrace());
				}
			} 
			
			//Este método ejecutará una sentencia DELETE para eliminar un 
			public static int borrarLibroEscritor(String cod_autor, String isbn) throws SQLException {
				String sqlQuery="DELETE FROM libreria.libro_escritor WHERE cod_autor='"+cod_autor+"' AND isbn='"+isbn+"';";
		
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
				Vector<DAOlibro_escritor> libro_escritores = new Vector<DAOlibro_escritor>();
				DAOlibro_escritor libro_escritor;
				
				while(resultado.next()) {
					long isbn = resultado.getLong(1);
					String cod_autor= resultado.getString(2);
					String nombreAutor = resultado.getString(3);
					String pApelAutor = resultado.getString(4);
					String sApelAutor = resultado.getString(5);
					String tituloLibro = resultado.getString(6);
					libro_escritor = new DAOlibro_escritor(isbn,cod_autor,nombreAutor,pApelAutor,sApelAutor,tituloLibro);
					libro_escritores.addElement(libro_escritor);
				}
				
				return libro_escritores;
			}
			
			private static DAOlibro_escritor buscaResultadosUnLibroEscritor(String consulta) throws SQLException{
				resultado = DAOlibro_escritor.sentencia.executeQuery(consulta);
				return cargaResultSetToLibroEscritor(resultado);
				
			}
			
			private static DAOlibro_escritor cargaResultSetToLibroEscritor(ResultSet resultado) throws SQLException {
				
				DAOlibro_escritor libro_escritor = null;
				
				while(resultado.next()) {
					int isbn = resultado.getInt(1);
					String cod_autor= resultado.getString(2);
					String nombreAutor = resultado.getString(3);
					String pApelAutor = resultado.getString(4);
					String sApelAutor = resultado.getString(5);
					String tituloLibro = resultado.getString(6);
					libro_escritor = new DAOlibro_escritor(isbn,cod_autor,nombreAutor,pApelAutor,sApelAutor,tituloLibro);
					
					}
				
				return libro_escritor;
			}
			
			public static void mostrarLibroEscritor(DAOlibro_escritor libro_escritor) {
				System.out.print("ISBN: "+libro_escritor.getIsbn()+"|| Titulo libro: "+libro_escritor.getTituloLibro()+" || Código del autor: "+libro_escritor.getCod_autor()+" || Nombre Autor: "+libro_escritor.getNombreAutor());
				if(libro_escritor.getpApelAutor()!=null)System.out.print(", "+libro_escritor.getpApelAutor());
				if(libro_escritor.getsApelAutor()!=null)System.out.print(", "+libro_escritor.getsApelAutor());
		System.out.println();
			}
	}
			//FIN MÉTODOS UTILITY DE CLASE


	

