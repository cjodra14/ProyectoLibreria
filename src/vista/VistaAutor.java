package vista;

import java.sql.*;
import java.util.*;

import controlador.ControladorAutor;
import dao.DAOAutor;


public class VistaAutor {

	private int opcion;
	private ControladorAutor controlador;
	private ResultSet resultado;
	private Scanner entrada;
	private String nombreAutor;
	private String primerApellido;
	private String segundoApellido;
	private String codAutor;
	
	public VistaAutor(ControladorAutor controlador) {
		this.controlador = controlador;
	}
	
	public void getAccion() {
		getMenu();
		getOpcion();
		
		do {
			switch(opcion) {
			case 1:
				altaAutores();
					break;
			case 2:
				try {
					modificacionAutores();
				}catch (Exception e) {
					System.err.println("Ha habido un error al modificar el autor");
				}
					break;
			case 3:
				borrarAutores();
					break;
			case 4:
				consultaAutores();
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
			System.out.println("	MANTENIMIENTO AUTOR		");
			System.out.println("==============================");
			System.out.println("Menu");
			System.out.println("-----");
			System.out.println("1 - Alta Autores");
			System.out.println("2 - Modificar Autores");
			System.out.println("3 - Borrar Autores");
			System.out.println("4 - Consulta Autores");
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
	
	private void altaAutores() {

		try {
		System.out.println("Nombre Autor");
		nombreAutor = entrada.nextLine();
		System.out.println("Primer Apellido");
		primerApellido = entrada.nextLine();
		System.out.println("Segundo Apellido");
		segundoApellido = entrada.nextLine();
		System.out.println("Codigo Autor");
		codAutor = entrada.nextLine();
		
		controlador.insertarAutor(codAutor, nombreAutor, primerApellido, segundoApellido);
		
		}catch(Exception exc) {
			System.err.println("FALLO AL DAR DE ALTA AL AUTOR");
			exc.printStackTrace();
		}
		
		
	}
	
	private void modificacionAutores() throws SQLException {
		
		
		
		System.out.println("Escribe idAutor a modificar");
		codAutor = entrada.nextLine();
		DAOAutor autor=controlador.obtenerAutor(codAutor);
		DAOAutor.mostrarAutor(autor);
		System.out.println("Nombre Autor");
		nombreAutor = entrada.nextLine();
		System.out.println("Primer Apellido");
		primerApellido = entrada.nextLine();
		System.out.println("Segundo Apellido");
		segundoApellido = entrada.nextLine();
		DAOAutor.modificarAutor(codAutor, nombreAutor, primerApellido, segundoApellido);
		
		
		
		
	}
	
	private void borrarAutores() {
		 String codAutor;
		System.out.println("Escribe idAutor");
		codAutor = entrada.nextLine();
		
		
		try {	
			if(controlador.borrarAutor(codAutor)>0) {
			System.out.println("Autor eliminado con exito");}
			else {
				System.out.println("No se ha eliminado ningun autor");
			}
		} catch (SQLException e) {
			System.err.println("Fallo al eliminar el Autor");
			e.printStackTrace();
		}
		
		
	}
	
	
	private void consultaAutores() {
		Vector<DAOAutor> autores= controlador.obtenerAutores();
		System.out.println("\nLISTADO DE AUTORES");
		System.out.println("======================");
		try {
			Iterator<DAOAutor> itAutores = autores.iterator();
			while(itAutores.hasNext()) {
				DAOAutor autor= itAutores.next();
				DAOAutor.mostrarAutor(autor);
				
			}
				
				
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  AUTORES");
			e.printStackTrace();
		}
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

