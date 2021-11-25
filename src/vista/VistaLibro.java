package vista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import controlador.ControladorLibro;
import dao.DAOLibro;
import dao.DAOlibro_escritor;

public class VistaLibro {
		
		private int opcion;
		private long isbn=-1;
		private ControladorLibro controlador;
		private Scanner entrada;
		private String titulo;
		private double precio=-1;
		private int ud_stock=-1;
		private String imagen;
		private String descripcion;
		private String cod_editorial;
		private String cod_categoria;
		
		
		public VistaLibro(ControladorLibro controlador) {
			this.controlador = controlador;
		}
		
		public void getAccion() {
			getMenu();
			getOpcion();
			
			do {
				switch(opcion) {
				case 1:
					altaLibros();
						break;
				case 2:
					try {
						modificacionLibros();
					}catch (Exception e) {
						System.err.println("Ha habido un error al modificar el libro");
					}
						break;
				case 3:
					borrarLibros();
						break;
				case 4:
					consultaLibros();
						break;
				case 5:
					obtenerLibrosEscritores();
						break;
				case 6:
					obtenerLibrosEscritoresIsbn();
						break;
					default:
					System.out.println("La opcion no es correcta. \n");
						
				}
				if(opcion != 0) {
					getMenu();
					getOpcion();
				}
			}while(opcion != 0) ;
				System.out.println("ADIOS");
				System.out.println("-------");
				terminarAplicacion();
				System.exit(0);
			}
			
			

			private void getMenu() {
				
				System.out.println("\n==============================");
				System.out.println("	MANTENIMIENTO LIBRO		");
				System.out.println("==============================");
				System.out.println("Menu");
				System.out.println("-----");
				System.out.println("1 - Alta Libros");
				System.out.println("2 - Modificar Libros");
				System.out.println("3 - Borrar Libros");
				System.out.println("4 - Consulta Libros");
				System.out.println("5 - Obtener autores de los libros");
				System.out.println("6 - Obtener autores de un libro por ISBN");
				System.out.println("0 - Salir");
				
		}
		
		private void getOpcion() {
			entrada = new Scanner(System.in);
				System.out.println("Introduzca una opcion: ");
			try {
				opcion = entrada.nextInt();
				entrada.nextLine();
			}catch(InputMismatchException exc) {
				opcion = -1;
			}
		}
		
		//METODOS para solicitar los servicios de la Aplicacion
		
		
		private void terminarAplicacion() {
			entrada.close();
			controlador.terminar();
		}
		
		private void altaLibros() {

			try {
				do {
			System.out.println("ISBN");
			isbn = entrada.nextLong();
			entrada.nextLine();
			System.out.println("Titulo");
			titulo = entrada.nextLine();
			System.out.println("Precio");
			precio = entrada.nextDouble();
			System.out.println("Unidades en stock");
			ud_stock = entrada.nextInt();
			entrada.nextLine();
			System.out.println("Ruta de la imagen");
			imagen = entrada.nextLine();
			System.out.println("Descripcion");
			descripcion = entrada.nextLine();
			System.out.println("Código de la editorial");
			cod_editorial = entrada.nextLine();
			System.out.println("Código de la categoria");
			cod_categoria = entrada.nextLine();}while(isbn!=-1||titulo.equals("")||precio!=-1||ud_stock!=-1||cod_editorial.equals("")||cod_categoria.equals(""));
			
			controlador.insertarLibros(isbn, titulo, precio, ud_stock, imagen, descripcion,cod_editorial, cod_categoria);
			
			}catch(Exception exc) {
				System.err.println("FALLO AL DAR DE ALTA AL LIBRO");
				exc.printStackTrace();
			}
			
			
		}
		
		private void modificacionLibros() throws SQLException {
			
			
			do {
			System.out.println("Escribe el ISBN del libro a modificar");
			isbn = entrada.nextInt();}while(String.valueOf(isbn).equals(""));
			DAOLibro libro=controlador.obtenerLibro(isbn);
			DAOLibro.mostrarLibro(libro);
			entrada.nextLine();
			do {
			System.out.println("Titulo");
			titulo = entrada.nextLine();
			System.out.println("Precio");
			precio = entrada.nextDouble();
			System.out.println("Unidades en stock");
			ud_stock = entrada.nextInt();
			System.out.println("Ruta de la imagen");
			imagen = entrada.nextLine();
			System.out.println("Descripcion");
			descripcion = entrada.nextLine();
			System.out.println("Código de la editorial");
			cod_editorial = entrada.nextLine();
			System.out.println("Código de la categoria");
			cod_categoria = entrada.nextLine();}while(titulo.equals("")||String.valueOf(precio).equals("")||String.valueOf(ud_stock).equals("")||cod_editorial.equals("")||cod_categoria.equals(""));
			DAOLibro.modificarLibro(isbn, titulo, precio, ud_stock, imagen, descripcion,cod_editorial, cod_categoria);
			
		}
		
		private void borrarLibros() {
			System.out.println("Escribe el ISBN del libro que quieres borrar");
			isbn = entrada.nextInt();
			
			
			try {	
				if(controlador.borrarLibro(isbn)>0) {
				System.out.println("Libro eliminado con exito");}
				else {
					System.out.println("No se ha eliminado ningun libro");
				}
			} catch (SQLException e) {
				System.err.println("Fallo al eliminar el libro");
				e.printStackTrace();
			}
			
			
		}
		
		
		private void consultaLibros() {
			Vector<DAOLibro> libros= controlador.obtenerLibros();
			System.out.println("\nLISTADO DE LIBROS");
			System.out.println("======================");
			try {
				Iterator<DAOLibro> itLibros = libros.iterator();
				while(itLibros.hasNext()) {
					DAOLibro libro= itLibros.next();
					DAOLibro.mostrarLibro(libro);
					
				}
					
					
			} catch (Exception e) {
				System.err.println("Vista: FALLO A OBTENER LIBROS");
				e.printStackTrace();
			}
			}
		public void  obtenerLibrosEscritores(){
			Vector<DAOlibro_escritor> escritoresDeCadaLibro= controlador.obtenerLibrosEscritores();
			for (DAOlibro_escritor daOlibro_escritor : escritoresDeCadaLibro) {
				DAOlibro_escritor.mostrarLibroEscritor(daOlibro_escritor);
			}
		}
		public void  obtenerLibrosEscritoresIsbn(){
			long isbn;
			System.out.println("Introduce el ISBN del libro del que quieres obtener los autores ");
			isbn=entrada.nextLong();
			Vector<DAOlibro_escritor> escritoresDeCadaLibro= controlador.obtenerLibroEscritorIsbn(isbn);
			for (DAOlibro_escritor daOlibro_escritor : escritoresDeCadaLibro) {
				DAOlibro_escritor.mostrarLibroEscritor(daOlibro_escritor);
			}
		}
			
			
			
			

	}

