package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;

public class DAOLibro {
		private long isbn;
		private String titulo;
		private double precio;
		private int ud_stock;
		private String imagen;
		private String descripcion;
		private String cod_editorial;
		private String cod_categoria;
		
		
		public DAOLibro() {
		}
		

	
		public DAOLibro(long isbn, String titulo, double precio, int ud_stock, String cod_editorial,
				String cod_categoria) {
			super();
			this.isbn = isbn;
			this.titulo = titulo;
			this.precio = precio;
			this.ud_stock = ud_stock;
			this.cod_editorial = cod_editorial;
			this.cod_categoria = cod_categoria;
		}



		public DAOLibro(long isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,
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



		public DAOLibro(long isbn, String titulo, String cod_editorial, String cod_categoria) {
			super();
			this.isbn = isbn;
			this.titulo = titulo;
			this.cod_editorial = cod_editorial;
			this.cod_categoria = cod_categoria;
		}
		



		public long getIsbn() {
			return isbn;
		}



		public void setIsbn(long isbn) {
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
		public static Vector<DAOLibro> obtenerLibros() throws SQLException{
			String sqlQuery = "select * from libro";
			//Este método devolverá un vector de tipo <DAOLibro>
			return buscaResultadosConConsulta(sqlQuery);	
		}
		public static DAOLibro obtenerlibro(long isbn) throws SQLException{
			String sqlQuery = "select * from libro WHERE isbn='"+isbn+"';";
			//Este método devolverá un vector de tipo <DAOLibro>
			return buscaResultadosUnLibro(sqlQuery);	
		}
		
		//Este método se utiliza para meter datos con la sentencia INSERT
		public static void insertarLibro(long isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,String cod_editorial, String cod_categoria) {
			try{
				String sqlQuery = "INSERT INTO libreria.libro VALUES ('"+isbn+"','"+titulo+"','"+precio+"','"+ud_stock+"','"+imagen+"','"+descripcion+"','"+cod_editorial+"','"+cod_categoria+"');";
				DAOLibro.sentencia.execute(sqlQuery);
				System.out.println("Los datos del libro con el isbn "+isbn+" han sido insertados con éxito.");
			}catch(SQLException e) {
				System.err.println("\nNo se han podido insertar datos en el libro con el isbn "+isbn);
			}
		}

		
		//Este método ejecutará una sentencia UPDATE para modificar un libro
		
		public static void modificarLibro(long isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,String cod_editorial, String cod_categoria){
			try {
				String sqlQuery = "UPDATE libreria.libro SET titulo= '"+titulo+"',precio= '"+precio+"',ud_stock= '"+ud_stock+"',imagen= '"+imagen+"'descripcion= '"+descripcion+"',cod_editorial= '"+cod_editorial+"',precio= '"+precio+"',cod_categoria= '"+cod_categoria+"'WHERE isbn='"+isbn+"';";
				DAOLibro.sentencia.executeUpdate(sqlQuery);	
				System.out.println("El libro con el isbn"+isbn+" ha sido modificado con éxito.");
			}catch(SQLException e) {
				System.err.println("Error al modificar el libro.\n"+e.getStackTrace());
			}
		} 
		
		//Este método ejecutará una sentencia DELETE para eliminar un libro
		public static int borrarLibro(long isbn) throws SQLException {
			String sqlQuery="DELETE FROM libreria.libro WHERE isbn='"+isbn+"'";
	
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
				long isbn = resultado.getLong(1);
				String titulo = resultado.getString(2);
				double precio = resultado.getDouble(3);
				int ud_stock = resultado.getInt(4);
				String imagen = resultado.getString(5);
				String descripcion = resultado.getString(6);
				String cod_editorial = resultado.getString(7);
				String cod_categoria = resultado.getString(8);
				libro = new DAOLibro(isbn, titulo, precio, ud_stock, imagen, descripcion,cod_editorial, cod_categoria);
				libros.addElement(libro);
			}
			
			return libros;
		}
		private static DAOLibro buscaResultadosUnLibro(String consulta) throws SQLException{
			resultado = DAOLibro.sentencia.executeQuery(consulta);
			return cargaResultSetToLibro(resultado);
			
		}
		
		private static DAOLibro cargaResultSetToLibro(ResultSet resultado) throws SQLException {
			
			DAOLibro libro = null;
			
			while(resultado.next()) {
				long isbn = resultado.getLong(1);
				String titulo = resultado.getString(2);
				double precio = resultado.getDouble(3);
				int ud_stock = resultado.getInt(4);
				String imagen = resultado.getString(5);
				String descripcion = resultado.getString(6);
				String cod_editorial = resultado.getString(7);
				String cod_categoria = resultado.getString(8);
				libro = new DAOLibro(isbn, titulo, precio, ud_stock, imagen, descripcion,cod_editorial, cod_categoria);
			}
			
			return libro;
		}
		
		public static void mostrarLibro(DAOLibro libro) {
			DecimalFormat dosDecimales = new DecimalFormat("0.00");
			System.out.print("ISBN: "+libro.getIsbn()+"|| Titulo del libro: "+libro.getTitulo()+"|| Precio: "+dosDecimales.format(libro.getPrecio())+"€ || ud_stock: "+libro.ud_stock+"|| imagen: "+libro.getImagen()+"|| descripcion: "+libro.getDescripcion()+"|| cod_editorial: "+libro.getCod_editorial()+"|| cod_categoria: "+libro.getCod_categoria());
			
	System.out.println();
		}
		//FIN MÉTODOS UTILITY DE CLASE


}
