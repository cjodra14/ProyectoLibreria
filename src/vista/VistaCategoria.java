package vista;

import java.sql.*;
import java.util.*;

import controlador.ControladorCategoria;
import dao.DAOCategoria;


public class VistaCategoria {

	private int opcion;
	private ControladorCategoria controlador;
	private ResultSet resultado;
	private Scanner entrada;
	private String codCategoria;
	private String nombreCategoria;
	
	public VistaCategoria(ControladorCategoria controlador) {
		this.controlador = controlador;
	}
	
	public void getAccion() {
		getMenu();
		getOpcion();
		
		do {
			switch(opcion) {
			case 1:
				altaCategoria();
					break;
			case 2:
				try {
					modificacionCategoria();
				}catch (Exception e) {
					System.err.println("Ha habido un error al modificar el autor");
				}
					break;
			case 3:
				borrarCategoria();
					break;
			case 4:
				consultaCategoria();
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
			System.out.println("APLICACION PROYECTO LIBRERIA");
			System.out.println("==============================");
			System.out.println("Menu");
			System.out.println("-----");
			System.out.println("1 - Alta Categoria");
			System.out.println("2 - Modificar Categoria");
			System.out.println("3 - Borrar Categoria");
			System.out.println("4 - Consulta Categoria");
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
	
	private void altaCategoria() {
		
		
		
		
		try {
		System.out.println("Nombre Autor");
		nombreCategoria = entrada.nextLine();
		System.out.println("Codigo Autor");
		codCategoria = entrada.nextLine();
		
		controlador.insertarCategoria(codCategoria, nombreCategoria);
		
		}catch(Exception exc) {
			System.err.println("FALLO AL DAR DE ALTA AL AUTOR");
			exc.printStackTrace();
		}
		
		
	}
	
	private void modificacionCategoria() throws SQLException {
		
		
		
		System.out.println("Escribe idCategoria a modificar");
		codCategoria = entrada.nextLine();
		DAOCategoria categoria=controlador.obtenerCategoria(codCategoria);
		DAOCategoria.mostrarCategoria(categoria);
		System.out.println("Nombre Categoria");
		nombreCategoria = entrada.nextLine();

		DAOCategoria.modificarCategoria(codCategoria, nombreCategoria);
		
	}
	
	private void borrarCategoria() {
		 String codCategoria;
		System.out.println("Escribe idCategoria");
		codCategoria = entrada.nextLine();
		
		
		try {	
			if(controlador.borrarCategoria(codCategoria)>0) {
			System.out.println("Autor eliminado con exito");}
			else {
				System.out.println("No se ha eliminado ningun categoria");
			}
		} catch (SQLException e) {
			System.err.println("Fallo al eliminar el Categoria");
			e.printStackTrace();
		}
		
		
	}
	
	
	private void consultaCategoria() {
		Vector<DAOCategoria> categorias= controlador.obtenerCategorias();
		System.out.println("\nLISTADO DE CATEGORIAS");
		System.out.println("======================");
		try {
			Iterator<DAOCategoria> itCategoria = categorias.iterator();
			while(itCategoria.hasNext()) {
				DAOCategoria categoria= itCategoria.next();
				DAOCategoria.mostrarCategoria(categoria);
				
			}
				
				
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  CATEGORIAS");
			e.printStackTrace();
		}
		}
		
		
		
	
	
}
