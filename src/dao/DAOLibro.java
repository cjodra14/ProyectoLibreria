package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOLibro {
		private int isbn;
		private String titulo;
		private double precio;
		private int ud_stock;
		private String imagen;
		private String descripcion;
		private String cod_editorial;
		private String cod_categoria;
		
		
		public DAOLibro() {
		}
		

	
		public DAOLibro(int isbn, String titulo, double precio, int ud_stock, String cod_editorial,
				String cod_categoria) {
			super();
			this.isbn = isbn;
			this.titulo = titulo;
			this.precio = precio;
			this.ud_stock = ud_stock;
			this.cod_editorial = cod_editorial;
			this.cod_categoria = cod_categoria;
		}



		public DAOLibro(int isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,
				String cod_editorial, String cod_categoria) {
			super();
			this.isbn = isbn;
			this.titulo = titulo;
			this.precio = precio;
			this.ud_stock = ud_stock;
			this.imagen = imagen;
			this.descripcion = descripcion;
			this.cod_editorial = cod_editorial;
			this.cod_categoria = cod_categoria;
			
		}



		public DAOLibro(int isbn, String titulo, String cod_editorial, String cod_categoria) {
			super();
			this.isbn = isbn;
			this.titulo = titulo;
			this.cod_editorial = cod_editorial;
			this.cod_categoria = cod_categoria;
		}
		



		public int getIsbn() {
			return isbn;
		}



		public void setIsbn(int isbn) {
			this.isbn = isbn;
		}



		public String getTitulo() {
			return titulo;
		}



		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}



		public double getPrecio() {
			return precio;
		}



		public void setPrecio(double precio) {
			this.precio = precio;
		}



		public int getUd_stock() {
			return ud_stock;
		}



		public void setUd_stock(int ud_stock) {
			this.ud_stock = ud_stock;
		}



		public String getImagen() {
			return imagen;
		}



		public void setImagen(String imagen) {
			this.imagen = imagen;
		}



		public String getDescripcion() {
			return descripcion;
		}



		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}



		public String getCod_editorial() {
			return cod_editorial;
		}



		public void setCod_editorial(String cod_editorial) {
			this.cod_editorial = cod_editorial;
		}



		public String getCod_categoria() {
			return cod_categoria;
		}



		public void setCod_categoria(String cod_categoria) {
			this.cod_categoria = cod_categoria;
		}




		//INTEGRACIÓN CON LA BBDD
		//La clase DAO debe tener conexión con la BBDD para conectarse con su tabla
		private static Statement sentencia;
		private static ResultSet resultado;
		
		public static void setConexionBBDD(Statement sentencia, ResultSet resultado) {
			DAOLibro.sentencia = sentencia;
			DAOLibro.resultado = resultado;
		}
		//MÉTODOS CRUD
		//READ
		//Método que extrae todos los registros de la tabla
		public static Vector<DAOLibro> obtenerAutores() throws SQLException{
			String sqlQuery = "select * from libro";
			//Este método devolverá un vector de tipo <DAOLibro>
			return buscaResultadosConConsulta(sqlQuery);	
		}
		public static DAOLibro obtenerAutor(int isbn) throws SQLException{
			String sqlQuery = "select * from libro WHERE cod_autor='"+isbn+"';";
			//Este método devolverá un vector de tipo <AutorDAO>
			return buscaResultadosUnAutor(sqlQuery);	
		}
		//Este método se utiliza para meter datos con la sentencia INSERT
		public static void insertarDatos(String idAutor, String nombreAutor, String apel1, String apel2) {
			try{
				String sqlQuery = "INSERT INTO libreria.autor VALUES ('"+idAutor+"','"+nombreAutor+"','"+apel1+"','"+apel2+"');";
				DAOLibro.sentencia.execute(sqlQuery);
				System.out.println("Los datos del autor con el código "+idAutor+" han sido insertados con éxito.");
			}catch(SQLException e) {
				System.err.println("\nNo se han podido insertar datos en el autor con el código "+idAutor);
			}
		}

		
		//Este método ejecutará una sentencia UPDATE para modificar un autor
		//cod_autor, nombre, p_apel, s_apel
		public static void modificarAutor(String idAutor, String nombreAutor, String apel1, String apel2){
			try {
				String sqlQuery = "UPDATE libreria.autor SET nombre= '"+nombreAutor+"',p_apel= '"+apel1+"',s_apel= '"+apel2+"' WHERE cod_autor='"+idAutor+"';";
				DAOLibro.sentencia.executeUpdate(sqlQuery);	
				System.out.println("El autor con el código "+idAutor+" ha sido modificado con éxito.");
			}catch(SQLException e) {
				System.err.println("Error al modificar el dato.\n"+e.getStackTrace());
			}
		} 
		
		//Este método ejecutará una sentencia DELETE para eliminar un autor
		public static int borrarAutor(String idAutor) throws SQLException {
			String sqlQuery="DELETE FROM libreria.autor WHERE cod_autor='"+idAutor+"'";
	
			return DAOLibro.sentencia.executeUpdate(sqlQuery);
		}
		//FIN METODOS CRUD
		
		//MÉTODOS UTILITY DE CLASE
		//Método que extrae filas de la tabla a través de una consulta
		private static Vector<DAOLibro> buscaResultadosConConsulta(String consulta) throws SQLException{
			resultado = DAOLibro.sentencia.executeQuery(consulta);
			return cargaResultSetToVector(resultado);
			
		}
		
		private static Vector<DAOLibro> cargaResultSetToVector(ResultSet resultado) throws SQLException {
			Vector<DAOLibro> libros = new Vector<DAOLibro>();
			DAOLibro libro;
			
			while(resultado.next()) {
				int isbn = resultado.getInt(1);
				String titulo = resultado.getString(2);
				double precio = resultado.getDouble(3);
				int ud_stock = resultado.getInt(4);
				//autor = new DAOLibro(codAutor,nombreAutor,pApelAutor,sApelAutor);
				//autores.addElement(autor);
			}
			
			return libros;
		}
		private static DAOLibro buscaResultadosUnAutor(String consulta) throws SQLException{
			resultado = DAOLibro.sentencia.executeQuery(consulta);
			return cargaResultSetToAutor(resultado);
			
		}
		
		private static DAOLibro cargaResultSetToAutor(ResultSet resultado) throws SQLException {
			
			DAOLibro autor = null;
			
			while(resultado.next()) {
				String codAutor = resultado.getString(1);
				String nombreAutor = resultado.getString(2);
				String pApelAutor = resultado.getString(3);
				String sApelAutor = resultado.getString(4);
				//autor = new DAOLibro(codAutor,nombreAutor,pApelAutor,sApelAutor);			
			}
			
			return autor;
		}
		
		public static void mostrarAutor(DAOLibro autor) {
			//System.out.print("ID: "+autor.getCod_autor()+"|| Nombre: "+autor.getNombre_autor());
			//if(autor.getP_apellido()!=null)System.out.print(", "+autor.getP_apellido());
			//if(autor.getS_apellido()!=null)System.out.print(", "+autor.getS_apellido());
	System.out.println();
		}
		//FIN MÉTODOS UTILITY DE CLASE


}
