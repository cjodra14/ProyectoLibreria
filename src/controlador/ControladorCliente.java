package controlador;

import java.sql.SQLException;
import java.util.Vector;

import dao.DAOCliente;
import modelo.ModeloLibreria;

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
	
	public DAOCliente obtenerCliente(String idCliente) throws SQLException {
		DAOCliente cliente;
		cliente=modelo.obtenerCliente(idCliente);
		return cliente;
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void insertarCliente(String usuario, String dni, String nombreCliente, String apel1, String apel2, String direccion, String email, String f_nacimiento, String pass) throws SQLException{
		modelo.insertarCliente(usuario, dni, nombreCliente, apel1, apel2, direccion, email, f_nacimiento, pass);
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un autor
	public void modificarCliente(String usuario, String dni, String nombreCliente, String apel1, String apel2, String direccion, String email, String f_nacimiento, String pass) throws SQLException{
		modelo.modificarCliente(usuario, dni, nombreCliente, apel1, apel2, direccion, email, f_nacimiento, pass);
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

	
	
	
	
	
	
	
	
	
	
	

