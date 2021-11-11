package vista;

import java.sql.*;
import java.util.*;

import controlador.ControladorEditorial;
import dao.DAOEditorial;


public class VistaEditorial {

	private int opcion;
	private ControladorEditorial controlador;
	private ResultSet resultado;
	private Scanner entrada;
	private String codEditorial;
	private String nombreEditorial;
	
	public VistaEditorial(ControladorEditorial controlador) {
		this.controlador = controlador;
	}
	
	public void getAccion() {
		getMenu();
		getOpcion();
		
		do {
			switch(opcion) {
			case 1:
				altaEditorial();
					break;
			case 2:
				try {
					modificacionEditorial();
				}catch (Exception e) {
					System.err.println("Ha habido un error al modificar la editorial");
				}
					break;
			case 3:
				borrarEditorial();
					break;
			case 4:
				consultaEditorial();
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
			System.out.println("	MANTENIMIENTO EDITORIAL		");
			System.out.println("==============================");
			System.out.println("Menu");
			System.out.println("-----");
			System.out.println("1 - Alta Editorial");
			System.out.println("2 - Modificar Editorial");
			System.out.println("3 - Borrar Editorial");
			System.out.println("4 - Consulta Editorial");
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
	
	private void altaEditorial() {
		
		
		
		
		try {
			do {
		System.out.println("Nombre Editorial");
		nombreEditorial = entrada.nextLine();
		System.out.println("Codigo Editorial");
		codEditorial = entrada.nextLine();}while(nombreEditorial.equals("")||codEditorial.equals(""));
		
		controlador.insertarEditorial(codEditorial, nombreEditorial);
		
		}catch(Exception exc) {
			System.err.println("FALLO AL DAR DE ALTA A LA EDITORIAL");
			exc.printStackTrace();
		}
		
		
	}
	
	private void modificacionEditorial() throws SQLException {
		
		
		do {
		System.out.println("Escribe el código de la Editorial a modificar");
		codEditorial = entrada.nextLine();
		}while(codEditorial.equals(""));
		DAOEditorial editorial=controlador.obtenerEditorial(codEditorial);
		DAOEditorial.mostrarEditorial(editorial);
		do {
		System.out.println("Nombre Editorial");
		nombreEditorial = entrada.nextLine();}while(nombreEditorial.equals(""));

		DAOEditorial.modificarEditorial(codEditorial, nombreEditorial);
		
	}
	
	private void borrarEditorial() {
		 String codEditorial;
		System.out.println("Escribe el codigo de la editorial");
		codEditorial = entrada.nextLine();
		
		
		try {	
			if(controlador.borrarEditorial(codEditorial)>0) {
			System.out.println("Editorial eliminada con exito");}
			else {
				System.out.println("No se ha eliminado ninguna editorial");
			}
		} catch (SQLException e) {
			System.err.println("Fallo al eliminar la Editorial");
			e.printStackTrace();
		}
		
		
	}
	
	
	private void consultaEditorial() {
		Vector<DAOEditorial> editoriales= controlador.obtenerEditoriales();
		System.out.println("\nLISTADO DE EDITORIALES");
		System.out.println("======================");
		try {
			Iterator<DAOEditorial> itEditorial = editoriales.iterator();
			while(itEditorial.hasNext()) {
				DAOEditorial editorial= itEditorial.next();
				DAOEditorial.mostrarEditorial(editorial);
				
			}
				
				
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER EDITORIALES");
			e.printStackTrace();
		}
		}
		
		
		
	
	
}

