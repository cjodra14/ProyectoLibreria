package vista;

import java.sql.*;
import java.util.*;
import java.util.ResourceBundle.Control;


public class VistaProyecto {

	private int opcion;
	private Control controlador;
	private ResultSet resultado;
	private Scanner entrada;
	
	public VistaProyecto(Control controlador) {
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
				modificacionAutores();
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
			
			System.out.println("APLICACION PROYECTO LIBRERIA");
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
		
		String nombreAutor;
		String primerApellido;
		String segundoApellido;
		String codAutor;
		
		entrada = new Scanner(System.in);
		
		try {
		System.out.println("Nombre Autor");
		nombreAutor = entrada.nextLine();
		System.out.println("Primer Apellido");
		primerApellido = entrada.nextLine();
		System.out.println("Segundo Apellido");
		segundoApellido = entrada.nextLine();
		System.out.println("Codigo Autor");
		codAutor = entrada.nextLine();
		
		}catch(SQLException exc) {
			System.err.println("FALLO AL DAR DE ALTA AL AUTOR");
			exc.printStackTrace();
		}
		
		
	}
	
	private void modificacionAutores() {
		// TODO Auto-generated method stub
		
		
		
		
		
	}
	
	private void borrarAutores(String codAutor) {
		
		
		
		
		
	}
	
	
	private void consultaAutores() {
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
