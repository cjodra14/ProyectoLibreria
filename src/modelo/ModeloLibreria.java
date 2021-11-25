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
		private DAOLibro libro;
		private DAOCliente cliente;
		private DAOlibro_escritor libro_escritor;
		private DAOVenta venta;
	
		
		private Vector<DAOAutor> autores;
		private Vector<DAOCategoria> categorias;
		private Vector<DAOEditorial> editoriales;
		private Vector<DAOLibro> libros;
		private Vector<DAOCliente> clientes;
		private Vector<DAOlibro_escritor> libros_escritores;
		private Vector<DAOVenta> ventas;
	
		
		Properties servicioElegido= new Properties();
		
		public ModeloLibreria() {
			try {
				try {
					//Carga el archivo que elige que base de datos vamos a usar
					servicioElegido.load(new FileInputStream(new File("serviceElegido.properties")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//Hace la conexion segun los los parametros indicados en el archivo de propiedades
			this.conexionBBDD = servicio.ServicioBBDD.obtenerServicio(servicioElegido.getProperty("servicio")).obtenerConexion();
			this.sentencia = this.conexionBBDD.createStatement();
			
			// a�ade la conexi�n a cada DAO enviando el Statement a la DAO
			DAOAutor.setConexionBBDD(sentencia, null);
			DAOEditorial.setConexionBBDD(sentencia, null);
			DAOCategoria.setConexionBBDD(sentencia, null);
			DAOLibro.setConexionBBDD(sentencia, null);
			DAOCliente.setConexionBBDD(sentencia, null);
			DAOVenta.setConexionBBDD(sentencia, null);
			DAOlibro_escritor.setConexionBBDD(sentencia, null);
			
			} catch (SQLException e) {
				System.err.println();
				e.printStackTrace();
			}
			
		}
		public void terminar () {
			try {
				
				// Cierra procesos de conexi�n para liberar la memoria
					sentencia.close();
					conexionBBDD.close();
				
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO AL CERRAR LA CONEXI�N ");
				e.printStackTrace();
			}
		}
		
		
		////////////////////////////////////////////////////////////////
		///// INICIO DE LA PARTE DE NEGOCIO ///////////////////////////
		//////////////////////////////////////////////////////////////
		
		
		
		
		
		///////////////  Autor //////////////////////////////////////////
		
		
		// Extrae todos los registros de la tabla de Autores
		public Vector<DAOAutor> obtenerAutores(){
			try {
				
			
			
			autores=DAOAutor.obtenerAutores();
			
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  AUTOR");
				e.printStackTrace();
			}
			return autores;
			
		}
		//M�todo que extrae un registro de la tabla con una sentencia SELECT de un solo autor
		public DAOAutor obtenerAutor(String idAutor) throws SQLException {
			return DAOAutor.obtenerAutor(idAutor);
		}
		//Este m�todo se utiliza para meter datos con la sentencia INSERT en el autor
		public void insertarAutor(String idAutor, String nombreAutor, String apel1, String apel2) {
			DAOAutor.insertarDatos(idAutor, nombreAutor,  apel1,  apel2);
		}
		//Este m�todo ejecutar� una sentencia UPDATE para modificar un autor
		public void modificarAutor(String idAutor, String nombreAutor, String apel1, String apel2) {
			DAOAutor.modificarAutor(idAutor, nombreAutor, apel1, apel2);
		}
		//Este m�todo ejecutar� una sentencia DELETE para eliminar un autor
		public int borrarAutor(String idAutor) throws SQLException {
			
			return DAOAutor.borrarAutor(idAutor);
		}
		
		
		
		//////////////////////////   Categoria  //////////////////////////////////
		
		// Extrae todos los registros de la tabal Categoria
		public Vector<DAOCategoria> obtenerCategorias(){
			try {		
			
			categorias=DAOCategoria.obtenerCategorias();
			
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  CATEGORIA");
				e.printStackTrace();
			}
			return categorias;
			
		}
		//M�todo que extrae un registro de la tabla con una sentencia SELECT 
		public DAOCategoria obtenerCategoria(String cod_categoria) throws SQLException {
			return DAOCategoria.obtenerCategoria(cod_categoria);
		}
		//Este m�todo se utiliza para meter datos con la sentencia INSERT
		public void insertarCategoria(String idCategoria, String nombreCategoria) {
			DAOCategoria.insertarCategoria(idCategoria, nombreCategoria);
		}
		//Este m�todo ejecutar� una sentencia UPDATE para modificar una categoria
		public void modificarCategoria(String idCategoria,String  nombreCategoria) {
			DAOCategoria.modificarCategoria(idCategoria,  nombreCategoria);
		}
		//Este m�todo ejecutar� una sentencia DELETE para eliminar una categoria
		public int borrarCategoria(String idCategoria) throws SQLException {
			
			return DAOCategoria.borrarCategoria(idCategoria);
		}
		
		
		//////////////////////   Editorial ////////////////////////////////////
		//M�todo que extrae todos los registros de la tabla con una sentencia SELECT
		public Vector<DAOEditorial> obtenerEditoriales(){
			try {				
			editoriales=DAOEditorial.obtenerEditoriales();
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  EDITORIAL");
				e.printStackTrace();
			}
			return editoriales;
			
		}
		//M�todo que extrae un registro de la tabla con una sentencia SELECT
		public DAOEditorial obtenerEditorial(String cod_editorial) throws SQLException {
			return DAOEditorial.obtenerEditorial(cod_editorial);
		}
		//Este m�todo se utiliza para meter datos con la sentencia INSERT
		public void insertarEditorial(String codEditorial, String nombreEditorial) throws SQLException {
			DAOEditorial.insertarEditorial(codEditorial, nombreEditorial);
		}
		//Este m�todo ejecutar� una sentencia UPDATE para modificar una editorial
		public void modificarEditorial(String codEditorial,String nombreEditorial ) {
			DAOEditorial.modificarEditorial(codEditorial, nombreEditorial);
		}
		//Este m�todo ejecutar� una sentencia DELETE para eliminar una editorial
		public int borrarEditorial(String codEditorial) throws SQLException {
			
			return DAOEditorial.borrarEditorial(codEditorial);
		}
		
		
		/////////////////////// Libro /////////////////////////
		//M�todo que extrae todos los registros de la tabla con una sentencia SELECT
		public Vector<DAOLibro> obtenerLibros(){
			try {				
			libros=DAOLibro.obtenerLibros();
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  EDITORIAL");
				e.printStackTrace();
			}
			return libros;
			
		}
		//M�todo que extrae un registro de la tabla con una sentencia SELECT
		public DAOLibro obtenerLibro(long isbn) throws SQLException {
			return DAOLibro.obtenerlibro(isbn);
		}
		//Este m�todo se utiliza para meter datos con la sentencia INSERT
		public void insertarLibros(long isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,String cod_editorial, String cod_categoria) throws SQLException {
			DAOLibro.insertarLibro(isbn, titulo, precio, ud_stock, imagen, descripcion, cod_editorial, cod_categoria);
		}
		//Este m�todo ejecutar� una sentencia UPDATE para modificar un libro
		public void modificarLibro(long isbn, String titulo, double precio, int ud_stock, String imagen, String descripcion,String cod_editorial, String cod_categoria) {
			DAOLibro.modificarLibro(isbn, titulo, precio, ud_stock, imagen, descripcion, cod_editorial, cod_categoria);;
		}
		//Este m�todo ejecutar� una sentencia DELETE para eliminar un libro 
		public int borrarLibro(long isbn) throws SQLException {			
			return DAOLibro.borrarLibro(isbn);
		}
		
		//////////////////// Cliente /////////////////////////////////////////
		
		// Extrae todos los registros de la tabla de Clientes
				public Vector<DAOCliente> obtenerClientes(){
					try {		
					clientes=DAOCliente.obtenerClientes();
					
					} catch (SQLException e) {
						System.err.println("Modelo: FALLO A OBTENER  CLIENTES");
						e.printStackTrace();
					}
					return clientes;
					
				}
				//M�todo que extrae un registro de la tabla con una sentencia SELECT de un solo cliente
				public DAOCliente  obtenerCliente(String usuario) throws SQLException {
					return DAOCliente.obtenerCliente(usuario);
				}
				//Este m�todo se utiliza para meter datos con la sentencia INSERT en el cliente
				public void insertarCliente(String usuario, String dni, String nombreCliente, String apel1, String apel2, String direccion, String email, String f_nacimiento, String pass) {
					DAOCliente.insertarCliente(usuario, dni, nombreCliente, apel1, apel2, direccion, email, f_nacimiento, pass);
				}
				//Este m�todo ejecutar� una sentencia UPDATE para modificar un cliente
				public void modificarCliente(String usuario, String dni, String nombreCliente, String apel1, String apel2, String direccion, String email, String f_nacimiento, String pass) {
					DAOCliente.modificarCliente(usuario, dni, nombreCliente, apel1, apel2, direccion, email, f_nacimiento, pass);
				}
				//Este m�todo ejecutar� una sentencia DELETE para eliminar un cliente
				public int borrarCliente(String usuario) throws SQLException {
					
					return DAOCliente.borrarCliente(usuario);
				}
				
				
				/////////////////////// VENTAS /////////////////////////
				
				// Extrae todos los registros de la tabla de Clientes
				
				
				public Vector<DAOVenta> obtenerventas(){
					try {
						
					ventas=DAOVenta.obtenerVentas();
					
					} catch (SQLException e) {
						System.err.println("Modelo: FALLO A OBTENER  VENTAS");
						e.printStackTrace();
					}
					return ventas;
					
				}
				//M�todo que extrae un registro de la tabla con una sentencia SELECT de una sola venta
				public DAOVenta  obtenerVentas(String npedido) throws SQLException {
					return DAOVenta.obtenerVenta(npedido);
				}
				//Este m�todo se utiliza para meter datos con la sentencia INSERT en venta
				public void insertarVenta(int npedido, String usuario, String fecha) {
					DAOVenta.insertarVenta(npedido, usuario, fecha);
				}
				//Este m�todo ejecutar� una sentencia UPDATE para modificar una venta
				public void modificarVenta(int npedido, String usuario, String fecha) {
					DAOVenta.modificarVenta(npedido, usuario, fecha);
				}
				//Este m�todo ejecutar� una sentencia DELETE para eliminar una venta
				public int borrarVenta(String npedido) throws SQLException {
					
					return DAOVenta.borrarVenta(npedido);
				}
		
		
		
		///////////////// Relaci�n de libros con Autores /////////////////////
		
		//Muestra todos los libros con sus correspondientes autores
		public Vector<DAOlibro_escritor> obtenerLibrosEscritores(){
			try {
			libros_escritores=DAOlibro_escritor.obtenerLibrosEscritores();
			
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  LOS AUTORES DEL LIBRO");
				e.printStackTrace();
			}
			return libros_escritores;
			
		}
		
		
	

}
