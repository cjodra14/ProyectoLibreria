package servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ServicioMySQL {

	private static ServicioMySQL servicio;
	private Connection conexion;
	
	private ServicioMySQL() {
		try {
			//Carga el driver para MySQL
			Class.forName("com.mysql.jdbc.Driver");
			
			//Datos para la conexión
			  final String URL = "Jdbc:mysql://localhost:3306/libreria";
	          String user ="root";
	          String pass="romaol2";
			
			//Establecimiento de la conexión de la BDD
			this.conexion = DriverManager.getConnection(URL, user, pass);
		}catch(ClassNotFoundException e) {
			System.err.println("ServicioMySQL: DRIVER DE BASE DE DATOS NO CARGADO.");
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.err.println("ServicioMySQL: FALLO AL ESTABLECER LA CONEXIÓN.");
			e.printStackTrace();
		}
	}
	
	//Este método crea el servicio para la base de datos
	public static synchronized ServicioMySQL obtenerServicio() {
		//Synchronized --> Hasta que no termine ese proceso no puede entrar otro que intente conectarse al a BBDD
		if(servicio == null) {
			servicio = new ServicioMySQL();
		}
		return servicio;
	}
	
	//Este método proporciona la conexión creada a quien se la pide
	public Connection obtenerConexion() {
		return conexion;
	}
}
