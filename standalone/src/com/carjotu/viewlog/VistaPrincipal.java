
package com.carjotu.viewlog;

import java.util.*;

import com.carjotu.controler.ControladorAutor;
import com.carjotu.controler.ControladorCategoria;
import com.carjotu.controler.ControladorCliente;
import com.carjotu.controler.ControladorEditorial;
import com.carjotu.controler.ControladorLibro;

public class VistaPrincipal {
	ControladorAutor controladorAutor;
	ControladorEditorial controladorEditorial;
	ControladorCategoria controladorCategoria;
	ControladorLibro controladorLibro;
	ControladorCliente controladorCliente;
	
	
	
	
	
	private int opcion;	
	private Scanner entrada;
		
		public VistaPrincipal(ControladorAutor controladorAutor, ControladorEditorial controladorEditorial, ControladorCategoria controladorCategoria, ControladorLibro controladorLibro, ControladorCliente controladorCliente) {
		this.controladorAutor=controladorAutor;
		this.controladorEditorial=controladorEditorial;
		this.controladorCategoria=controladorCategoria;
		this.controladorLibro=controladorLibro;
		this.controladorCliente=controladorCliente;
	}		
		
		public void getAccion() throws Exception {
			getMenu();
			getOpcion();
			
			do {
				switch(opcion) {
				case 1:
					VistaAutor ventanaAutor = new VistaAutor(controladorAutor);	
						break;
				case 2:
					VistaEditorial vistaEditorial= new VistaEditorial(controladorEditorial);
					vistaEditorial.getAccion();
						break;
				case 3:
					VistaCategoria vistaCategoria = new VistaCategoria(controladorCategoria);
					vistaCategoria.getAccion();
						break;
				case 4:
					VistaLibro vistaLibro= new VistaLibro(controladorLibro);
					vistaLibro.getAccion();
						break;
				case 5:
					VistaCliente vistaCliente = new VistaCliente(controladorCliente);
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

