package servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServicioBBDD {

	//Definición de los servcios que esta clase puede prestar (public)
	public static final String MYSQL = "mysql";
	public static final String ORACLE = "oracle";
	public static final String POSTGRESQL = "postgresql";
	
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	private static final String POSTGRE_DRIVER = "orf.postgresql.Driver";

	private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/libreria?useSSL=false";
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost";
	private static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:0000/libreria";
	
	private static final String USER="root";
	private static final String USER_KEY = "romaol2";
	
	private static ServicioBBDD servicio;
	private Connection conexion;
	
	private ServicioBBDD(String driver, String url, String user, String pass) {
		try {
			//Carga del driver
			Class.forName(driver);
			
			//Establecimiento de la conexión con la base de datos
			this.conexion = DriverManager.getConnection(url,user, pass);
			
		}catch(ClassNotFoundException e) {
			System.err.println("Error en la carga del driver con la BBDD.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Fallo al establecer conexión");
			e.printStackTrace();
		}
	}
	
	public static synchronized ServicioBBDD obtenerServicio(String servicioBBDD) {
		if (ServicioBBDD.servicio == null) {
			switch (servicioBBDD) {
				case "mysql":
					ServicioBBDD.servicio = new ServicioBBDD(ServicioBBDD.MYSQL_DRIVER, ServicioBBDD.MYSQL_URL, ServicioBBDD.USER, ServicioBBDD.USER_KEY);
					break;
				case "oracle":
					ServicioBBDD.servicio = new ServicioBBDD(ServicioBBDD.ORACLE_DRIVER, ServicioBBDD.ORACLE_URL, ServicioBBDD.USER, ServicioBBDD.USER_KEY);
					break;
				case "postgreSQL":
					ServicioBBDD.servicio = new ServicioBBDD(ServicioBBDD.POSTGRE_DRIVER, ServicioBBDD.POSTGRESQL_URL, ServicioBBDD.USER, ServicioBBDD.USER_KEY);
					break;
			}
		}
		return servicio;
	}
	
	public Connection obtenerConexion() {
		return this.conexion;
	}
}
