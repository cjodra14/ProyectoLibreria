package com.carjotu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOCliente {

	private String usuario;
	private String dni;
	private String nombre_cliente;
	private String p_apellido;
	private String s_apellido;
	private String direccion;
	private String email;
	private String f_nacimiento;
	private String pass;
	
	public DAOCliente(String usuario, String dni, String nombre_cliente, String p_apellido, String s_apellido, String direccion, String email, String f_nacimiento, String pass) {
		
		this.usuario = usuario;
		this.dni = dni;
		this.nombre_cliente = nombre_cliente;
		this.p_apellido = p_apellido;
		this.s_apellido = s_apellido;
		this.direccion = direccion;
		this.email = email;
		this.f_nacimiento = f_nacimiento;
		this.pass = pass;
		
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getP_apellido() {
		return p_apellido;
	}

	public void setP_apellido(String p_apellido) {
		this.p_apellido = p_apellido;
	}	
	
	public String getS_apellido() {
		return s_apellido;
	}

	public void setS_apellido(String s_apellido) {
		this.s_apellido = s_apellido;
	}	
	
	
	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getF_nacimiento() {
		return f_nacimiento;
	}


	public void setF_nacimiento(String f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}

	//INTEGRACIÓN CON LA BBDD
	//La clase DAO debe tener conexión con la BBDD para conectarse con su tabla
	private static Statement sentencia;
	private static ResultSet resultado;
	
	public static void setConexionBBDD(Statement sentencia, ResultSet resultado) {
		DAOCliente.sentencia = sentencia;
		DAOCliente.resultado = resultado;
	}
	//MÉTODOS CRUD
	//READ
	//Método que extrae todos los registros de la tabla
	public static Vector<DAOCliente> obtenerClientes() throws SQLException{
		String sqlQuery = "select * from cliente";
		//Este método devolverá un vector de tipo <ClienteDAO>
		return buscaResultadosConConsulta(sqlQuery);	
	}
	public static DAOCliente obtenerCliente(String usuario) throws SQLException{
		String sqlQuery = "select * from cliente WHERE usuario='"+usuario+"';";
		return buscaResultadosUnCliente(sqlQuery);	
	}
	//Este método se utiliza para meter datos con la sentencia INSERT
	public static void insertarCliente(String usuario, String dni, String nombreCliente, String apel1, String apel2, String direccion, String email, String f_nacimiento, String pass) {
		try{
			//INSERT INTO `libreria`.`cliente` (`usuario`, `dni`, `nombre`, `p_apellido`, `s_apellido`, `direccion`, `email`, `f_nacimiento`, `pass`) VALUES ('asministrador', '12345678A', 'Julen', 'Chris', 'Imanol', 'iturribide', 'email@email.com', '1995-01-01', 'pass123');

			String sqlQuery = "INSERT INTO`libreria`.`cliente` (`usuario`, `dni`, `nombre`, `p_apellido`, `s_apellido`, `direccion`, `email`, `f_nacimiento`, `pass`) VALUES ('"+usuario+"','"+dni+"','"+nombreCliente+"','"+apel1+"','"+apel2+"','"+direccion+"','"+email+"','"+f_nacimiento+"','"+pass+");";
			DAOCliente.sentencia.execute(sqlQuery);
			System.out.println("Los datos del cliente con el nombre de usuario: "+usuario+" han sido insertados con éxito.");
		}catch(SQLException e) {
			System.err.println("\nNo se han podido insertar datos en el cliente con el usuario "+usuario);
		}
	}

	
	//Este método ejecutará una sentencia UPDATE para modificar un cliente
	public static void modificarCliente(String usuario, String dni, String nombreCliente, String apel1, String apel2, String direccion, String email, String f_nacimiento, String pass){
		try {
			String sqlQuery = "UPDATE libreria.cliente SET nombre= '"+nombreCliente+"',p_apel= '"+apel1+"',s_apel= '"+apel2+"', direccion='"+direccion+"', email='"+email+"', f_nacimiento='"+f_nacimiento+"' WHERE usuario='"+usuario+"';";
			DAOCliente.sentencia.executeUpdate(sqlQuery);	
			System.out.println("El usuario con el código "+usuario+" ha sido modificado con éxito.");
		}catch(SQLException e) {
			System.err.println("Error al modificar el dato.\n"+e.getStackTrace());
		}
	} 
	
	//Este método ejecutará una sentencia DELETE para eliminar un cliente
	public static int borrarCliente(String usuario) throws SQLException {
		String sqlQuery="DELETE FROM libreria.autor WHERE cod_autor='"+usuario+"'";
		return DAOCliente.sentencia.executeUpdate(sqlQuery);
	}
	//FIN METODOS CRUD
	
	//MÉTODOS UTILITY DE CLASE
	//Método que extrae filas de la tabla a través de una consulta
	private static Vector<DAOCliente> buscaResultadosConConsulta(String consulta) throws SQLException{
		resultado = DAOCliente.sentencia.executeQuery(consulta);
		return cargaResultSetToVector(resultado);
		
	}
	
	private static Vector<DAOCliente> cargaResultSetToVector(ResultSet resultado) throws SQLException {
		Vector<DAOCliente> clientes = new Vector<DAOCliente>();
		DAOCliente cliente;
		
		while(resultado.next()) {
			String usuario = resultado.getString(1);
			String dni = resultado.getString(2);
			String nombreCliente = resultado.getString(3);
			String pApelCliente = resultado.getString(4);
			String sApelCliente = resultado.getString(5);
			String direccion = resultado.getString(6);
			String email = resultado.getString(7);
			String fNacimiento = resultado.getString(8);
			String pass = resultado.getString(9);
			
			cliente = new DAOCliente(usuario,dni, nombreCliente,pApelCliente,sApelCliente,direccion,email,fNacimiento,pass);
						
			clientes.addElement(cliente);
		}
		
		return clientes;
	}
	private static DAOCliente buscaResultadosUnCliente(String consulta) throws SQLException{
		resultado = DAOCliente.sentencia.executeQuery(consulta);
		return cargaResultSetToCliente(resultado);
		
	}
	
	private static DAOCliente cargaResultSetToCliente(ResultSet resultado) throws SQLException {
		
		DAOCliente cliente = null;
		
		while(resultado.next()) {
			String usuario = resultado.getString(1);
			String dni = resultado.getString(2);
			String nombreCliente = resultado.getString(3);
			String pApelCliente = resultado.getString(4);
			String sApelCliente = resultado.getString(5);
			String direccion = resultado.getString(6);
			String email = resultado.getString(7);
			String fNacimiento = resultado.getString(8);
			String pass = resultado.getString(9);
			
			cliente = new DAOCliente(usuario,dni, nombreCliente,pApelCliente,sApelCliente,direccion,email,fNacimiento,pass);		
		}
		
		return cliente;
	}
	
	public static void mostrarCliente(DAOCliente cliente) {
		System.out.print("ID: "+cliente.getUsuario()+"|| Nombre: "+cliente.getNombre_cliente());
		if(cliente.getP_apellido()!=null)System.out.print(", "+cliente.getP_apellido());
		if(cliente.getS_apellido()!=null)System.out.print(", "+cliente.getS_apellido());
System.out.println();
		if(cliente.getDireccion()!=null)System.out.println("Dirección: "+cliente.getDireccion());
		System.out.println("Email: "+cliente.getEmail());
		if(cliente.getF_nacimiento()!=null)System.out.println("Fecha de nacimiento: "+cliente.getF_nacimiento());
	}
	//FIN MÉTODOS UTILITY DE CLASE


}