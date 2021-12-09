package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOAutor {
		private String cod_autor;
		private String nombre_autor;
		private String p_apellido;
		private String s_apellido;
		
		public DAOAutor() {
		}

		public DAOAutor(String cod_autor, String nombre_autor) {
			this.cod_autor = cod_autor;
			this.nombre_autor = nombre_autor;
		}
		

		public DAOAutor(String cod_autor, String nombre_autor, String p_apellido) {
			super();
			this.cod_autor = cod_autor;
			this.nombre_autor = nombre_autor;
			this.p_apellido = p_apellido;
		}

		public DAOAutor(String cod_autor, String nombre_autor, String p_apellido, String s_apellido) {
			super();
			this.cod_autor = cod_autor;
			this.nombre_autor = nombre_autor;
			this.p_apellido = p_apellido;
			this.s_apellido = s_apellido;
		}

		public String getCod_autor() {
			return cod_autor;
		}

		public void setCod_autor(String cod_autor) {
			this.cod_autor = cod_autor;
		}

		public String getNombre_autor() {
			return nombre_autor;
		}

		public void setNombre_autor(String nombre_autor) {
			this.nombre_autor = nombre_autor;
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
		//INTEGRACIÓN CON LA BBDD
		//La clase DAO debe tener conexión con la BBDD para conectarse con su tabla
		private static Statement sentencia;
		private static ResultSet resultado;
		
		public static void setConexionBBDD(Statement sentencia, ResultSet resultado) {
			DAOAutor.sentencia = sentencia;
			DAOAutor.resultado = resultado;
		}
		//MÉTODOS CRUD
		//READ
		//Método que extrae todos los registros de la tabla
		public static Vector<DAOAutor> obtenerAutores() throws Exception{
			String sqlQuery = "select * from autor";
			//Este método devolverá un vector de tipo <AutorDAO>
			return buscaResultadosConConsulta(sqlQuery);	
		}
		public static DAOAutor obtenerAutor(String codAutor) throws Exception{
			String sqlQuery = "select * from autor WHERE cod_autor='"+codAutor+"';";
			return buscaResultadosUnAutor(sqlQuery);	
		}
		//Este método se utiliza para meter datos con la sentencia INSERT
		public static void insertarDatos(String idAutor, String nombreAutor, String apel1, String apel2) throws Exception {
			
				String sqlQuery = "INSERT INTO libreria.autor VALUES ('"+idAutor+"','"+nombreAutor+"','"+apel1+"','"+apel2+"');";
				DAOAutor.sentencia.execute(sqlQuery);
				System.out.println("Los datos del autor con el código "+idAutor+" han sido insertados con éxito.");
			
		}

		
		//Este método ejecutará una sentencia UPDATE para modificar un autor
		public static void modificarAutor(String idAutor, String nombreAutor, String apel1, String apel2) throws Exception{
			
				String sqlQuery = "UPDATE libreria.autor SET nombre= '"+nombreAutor+"',p_apel= '"+apel1+"',s_apel= '"+apel2+"' WHERE cod_autor='"+idAutor+"';";
				DAOAutor.sentencia.executeUpdate(sqlQuery);	
				System.out.println("El autor con el código "+idAutor+" ha sido modificado con éxito.");
			
		} 
		
		//Este método ejecutará una sentencia DELETE para eliminar un autor
		public static int borrarAutor(String idAutor) throws Exception {
			String sqlQuery="DELETE FROM libreria.autor WHERE cod_autor='"+idAutor+"'";
			return DAOAutor.sentencia.executeUpdate(sqlQuery);
		}
		//FIN METODOS CRUD
		
		//MÉTODOS UTILITY DE CLASE
		//Método que extrae filas de la tabla a través de una consulta
		private static Vector<DAOAutor> buscaResultadosConConsulta(String consulta) throws Exception{
			resultado = DAOAutor.sentencia.executeQuery(consulta);
			return cargaResultSetToVector(resultado);
			
		}
		
		private static Vector<DAOAutor> cargaResultSetToVector(ResultSet resultado) throws Exception {
			Vector<DAOAutor> autores = new Vector<DAOAutor>();
			DAOAutor autor;
			
			while(resultado.next()) {
				String codAutor = resultado.getString(1);
				String nombreAutor = resultado.getString(2);
				String pApelAutor = resultado.getString(3);
				String sApelAutor = resultado.getString(4);
				autor = new DAOAutor(codAutor,nombreAutor,pApelAutor,sApelAutor);
				autores.addElement(autor);
			}
			
			return autores;
		}
		private static DAOAutor buscaResultadosUnAutor(String consulta) throws Exception{
			resultado = DAOAutor.sentencia.executeQuery(consulta);
			return cargaResultSetToAutor(resultado);
			
		}
		
		private static DAOAutor cargaResultSetToAutor(ResultSet resultado) throws Exception {
			
			DAOAutor autor = null;
			
			while(resultado.next()) {
				String codAutor = resultado.getString(1);
				String nombreAutor = resultado.getString(2);
				String pApelAutor = resultado.getString(3);
				String sApelAutor = resultado.getString(4);
				autor = new DAOAutor(codAutor,nombreAutor,pApelAutor,sApelAutor);			
			}
			
			return autor;
		}
		
		public static void mostrarAutor(DAOAutor autor) {
			System.out.print("ID: "+autor.getCod_autor()+"|| Nombre: "+autor.getNombre_autor());
			if(autor.getP_apellido()!=null)System.out.print(", "+autor.getP_apellido());
			if(autor.getS_apellido()!=null)System.out.print(", "+autor.getS_apellido());
	System.out.println();
		}
		//FIN MÉTODOS UTILITY DE CLASE


}
