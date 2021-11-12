
package vista;

import java.util.*;

public class VistaPrincipal {
	VistaAutor vistaAutor;
	VistaEditorial vistaEditorial;
	VistaCategoria vistaCategoria; 
	VistaLibro vistaLibro;
	VistaCliente vistaCliente;
	
	
	
	
	
	private int opcion;	
	private Scanner entrada;
		
		public VistaPrincipal(VistaAutor vistaAutor, VistaEditorial vistaEditorial, VistaCategoria vistaCategoria, VistaLibro vistaLibro, VistaCliente vistaCliente) {
		this.vistaAutor=vistaAutor;
		this.vistaEditorial=vistaEditorial;
		this.vistaCategoria=vistaCategoria;
		this.vistaLibro=vistaLibro;
		this.vistaCliente=vistaCliente;
	}		
		
		public void getAccion() {
			getMenu();
			getOpcion();
			
			do {
				switch(opcion) {
				case 1:
					vistaAutor.getAccion();
						break;
				case 2:
					vistaEditorial.getAccion();
						break;
				case 3:
					vistaCategoria.getAccion();
						break;
				case 4:
					vistaLibro.getAccion();
						break;
				case 5:
					vistaCliente.getAccion();
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
				System.out.println("1 - Mantenimientos de Autores");
				System.out.println("2 - Mantenimiento de Editoriales");
				System.out.println("3 - Mantenimiento de Categorias");
				System.out.println("4 - Mantenimiento de Libros");
				System.out.println("5 - Mantenimiento de Cliente");
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
		
			
			
			
}

