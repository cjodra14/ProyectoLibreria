package com.carjotu.viewlog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import com.carjotu.controler.ControladorCliente;
import com.carjotu.dao.DAOCliente;

public class VistaCliente {

	private int opcion;
	private ControladorCliente controlador;
	private ResultSet resultado;
	private Scanner entrada;
	private String usuario;
	private String dni;
	private String nombre_cliente;
	private String p_apellido;
	private String s_apellido;
	private String direccion;
	private String email;
	private String f_nacimiento;
	private String pass;
	
	public VistaCliente(ControladorCliente controlador) {
		this.controlador = controlador;
	}
	
	public void getAccion() {
		getMenu();
		getOpcion();
		
		do {
			switch(opcion) {
			case 1:
				altaClientes();
					break;
			case 2:
				try {
					modificacionCliente();
				}catch (Exception e) {
					System.err.println("Ha habido un error al modificar el cliente");
				}
					break;
			case 3:
				borrarCliente();
					break;
			case 4:
				consultaCliente();
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
			System.out.println("	MANTENIMIENTO CLIENTE		");
			System.out.println("==============================");
			System.out.println("Menu");
			System.out.println("-----");
			System.out.println("1 - Alta Cliente");
			System.out.println("2 - Modificar Cliente");
			System.out.println("3 - Borrar Cliente");
			System.out.println("4 - Consulta Cliente");
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
	
	private void altaClientes() {

		try {
			do {
				
				System.out.println("dni");
				dni = entrada.nextLine();
				System.out.println("Nombre Cliente");
				nombre_cliente = entrada.nextLine();
				System.out.println("Primer Apellido");
				p_apellido = entrada.nextLine();
				System.out.println("Segundo Apellido");
				s_apellido = entrada.nextLine();
				System.out.println("Direccion");
				direccion = entrada.nextLine();
				System.out.println("Email");
				email = entrada.nextLine();
				System.out.println("Fecha de nacimiento");
				f_nacimiento = entrada.nextLine();
				System.out.println("Contraseña");
				pass = entrada.nextLine();
				System.out.println("Usuario");
				usuario = entrada.nextLine();}while(nombre_cliente.equals("")||usuario.equals(""));
			
		
		controlador.insertarCliente(usuario, dni, nombre_cliente, p_apellido, s_apellido, direccion, email, f_nacimiento, pass);
		
		}catch(Exception exc) {
			System.err.println("FALLO AL DAR DE ALTA AL CLIENTE");
			exc.printStackTrace();
		}
		
		
	}
	
	private void modificacionCliente() throws SQLException {
		
		
		do {
		System.out.println("Escribe idAutor a modificar");
		usuario = entrada.nextLine();
		}while(usuario.equals(""));
		DAOCliente cliente=controlador.obtenerCliente(usuario);
		DAOCliente.mostrarCliente(cliente);
		do {
		System.out.println("Nombre Cliente");
		nombre_cliente = entrada.nextLine();}while(nombre_cliente.equals(""));
		System.out.println("Primer Apellido");
		p_apellido = entrada.nextLine();
		System.out.println("Segundo Apellido");
		s_apellido = entrada.nextLine();
		
		DAOCliente.modificarCliente(usuario, dni, nombre_cliente, p_apellido, s_apellido, direccion, email, f_nacimiento, pass);
		
		
	}
	
	private void borrarCliente() {
		 String usuario;
		System.out.println("Escribe usuario");
		usuario = entrada.nextLine();
		
		
		try {	
			if(controlador.borrarCliente(usuario)>0) {
			System.out.println("Cliente eliminado con exito");}
			else {
				System.out.println("No se ha eliminado ningun cliente");
			}
		} catch (SQLException e) {
			System.err.println("Fallo al eliminar el Cliente");
			e.printStackTrace();
		}
		
		
	}
	
	
	private void consultaCliente() {
		Vector<DAOCliente> clientes= controlador.obtenerClientes();
		System.out.println("\nLISTADO DE CLIENTES");
		System.out.println("======================");
		try {
			Iterator<DAOCliente> itClientes = clientes.iterator();
			while(itClientes.hasNext()) {
				DAOCliente cliente= itClientes.next();
				DAOCliente.mostrarCliente(cliente);
				
			}
						
		} catch (Exception e) {
			System.err.println("Vista: FALLO A OBTENER  CLIENTES");
			e.printStackTrace();
		}
		}
		
	}
	
