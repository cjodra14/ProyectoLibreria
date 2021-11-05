//POr ahora esta clase no es valida
//Es para hacer un menú unico



package vista;

import java.sql.*;
import java.util.*;

import controlador.ControladorAutor;
import dao.DAOAutor;
import modelo.ModeloLibreria;

public class VistaPrincipal {
	
	
	ModeloLibreria modelo;
	ControladorAutor controladorAutor= new ControladorAutor(modelo);
	VistaAutor ventanaAutores= new VistaAutor(controladorAutor);
	private int opcion;	
	private Scanner entrada;
	
	public VistaPrincipal(ModeloLibreria modelo) {
		super();
		this.modelo = modelo;
	}


		
		
		
		public VistaPrincipal() {
			this.controladorAutor = controladorAutor;
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
					//modificacionAutores();
						break;
				case 3:
					//borrarAutores();
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
			//controlador.terminar();
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
			
			}catch(Exception exc) {
				System.err.println("FALLO AL DAR DE ALTA AL AUTOR");
				exc.printStackTrace();
			}
			
			
		}
		
		private void modificacionAutores(String nombreAutor, String primerApellido, String segundoApellido,String codAutor) {
			// TODO Auto-generated method stub
			
			
			System.out.println("Escribe idAutor");
			codAutor = entrada.nextLine();
			
			
		}
		
		private void borrarAutores(String codAutor) {
			
			System.out.println("Escribe idAutor");
			codAutor = entrada.nextLine();
			
			
		}
		
		
		private void consultaAutores() {
			Vector<DAOAutor> autores= controladorAutor.obtenerAutores();
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

