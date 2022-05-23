package com.carjotu.controler;

import java.sql.SQLException;
import java.util.Vector;

import com.carjotu.dao.DAOCliente;
import com.carjotu.model.ModeloLibreria;

public class ControladorCliente {

	private ModeloLibreria modelo;
	private Vector<DAOCliente> clientes;
	
	//Constructor
	public ControladorCliente(ModeloLibreria modelo) {
		this.modelo=modelo;
	}
	
	//Este metodo retorna un vector de autores y lo obtiene del metodo obtenerAutores()
	public Vector<DAOCliente> obtenerClientes(){
		clientes=modelo.obtenerClientes();
		return clientes;
	}
	
	public DAOCliente obtenerCliente(String usuario) throws SQLException {
		DAOCliente cliente;
		cliente=modelo.obtenerCliente(usuario);
		return cliente;
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void insertarCliente(String usuario, String dni, String nombreCliente, String apel1, String apel2, String direccion, String email, String f_nacimiento, String pass) throws Exception{
		modelo.insertarCliente(usuario, dni, nombreCliente, apel1, apel2, direccion, email, f_nacimiento, pass);
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un autor
	public void modificarCliente(String usuario,String nombreCliente, String apel1, String apel2, String direccion, String email, String f_nacimiento, String pass) throws Exception{
		modelo.modificarCliente(usuario,nombreCliente,apel1,apel2,direccion,email,f_nacimiento,pass);
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar un autor
	public int borrarCliente(String idCliente) throws SQLException{
		
		 return modelo.borrarCliente(idCliente);
	}
	

	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
}

	
	
	
	
	
	
	
	
	
	
	

