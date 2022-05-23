package com.carjotu.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class DAOEditorial {
	
	private String cod_editorial, nombre_editorial;
	public DAOEditorial() {
		
	}
	
	public DAOEditorial(String cod_editorial, String nombre_editorial) {
		this.cod_editorial=cod_editorial;
		this.nombre_editorial=nombre_editorial;
	}

	public String getCod_editorial() {
		return cod_editorial;
	}

	public void setCod_editorial(String cod_editorial) {
		this.cod_editorial = cod_editorial;
	}

	public String getNombre_editorial() {
		return nombre_editorial;
	}

	public void setNombre_editorial(String nombre_editorial) {
		this.nombre_editorial = nombre_editorial;
	}
	
	//INTEGRACIÓN CON LA BBDD
	//La clase DAO debe tener conexión con la BBDD para conectarse con su tabla
	private static Statement sentencia;
	private static ResultSet resultado;
	
	public static void setConexionBBDD(Statement sentencia, ResultSet resultado) {
		DAOEditorial.sentencia = sentencia;
		DAOEditorial.resultado = resultado;
	}
	
	//MÉTODOS CRUD
	//READ
	//Método que extrae todos los registros de la tabla
	public static Vector<DAOEditorial> obtenerEditoriales() throws Exception{
		String sqlQuery = "SELECT * FROM libreria.editorial; ;";
		return buscaResultadosConConsulta(sqlQuery);
	}
	
	public static DAOEditorial obtenerEditorial(String codEditorial) throws Exception{
		String sqlQuery = "select * from editorial WHERE cod_editorial='"+codEditorial+"';";
		return buscaResultadosUnaEditorial(sqlQuery);
	}
	//Este método se utiliza para meter datos con la sentencia INSERT
	public static void insertarEditorial(String codEditorial, String nombreEditorial) throws Exception{
	
			String sqlQuery = "INSERT INTO libreria.editorial VALUES ('"+codEditorial+"','"+nombreEditorial+"');";
			DAOEditorial.sentencia.execute(sqlQuery);
			System.out.println("Los datos de la editorial con el código "+codEditorial+" han sido insertados con exito");
		
	}
	//Este método ejecutará una sentencia UPDATE para modificar una editorial
	public static int modificarEditorial(String codEditorial, String nombreEditorial) throws Exception {
			String sqlQuery = "UPDATE libreria.editorial SET nombre_editorial= '"+nombreEditorial+"' WHERE cod_editorial='"+codEditorial+"';";
			return DAOEditorial.sentencia.executeUpdate(sqlQuery);
			
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar una editorial
	public static int borrarEditorial(String codEditorial) throws Exception {
		String sqlQuery="DELETE FROM libreria.editorial WHERE cod_editorial='"+codEditorial+"';";
		return DAOEditorial.sentencia.executeUpdate(sqlQuery);
	}
	//FIN METODOS CRUD
	
	//MÉTODOS UTILITY DE CLASE
	//Método que extrae filas de la tabla a través de una consulta
	private static Vector<DAOEditorial> buscaResultadosConConsulta(String consulta) throws Exception{
		
		resultado = DAOEditorial.sentencia.executeQuery(consulta);
		
		return cargaResultSetToVector(resultado);
		
	}
	
	private static Vector<DAOEditorial> cargaResultSetToVector(ResultSet resultado) throws Exception{
		Vector<DAOEditorial> editoriales = new Vector<DAOEditorial>();
		DAOEditorial editorial;
		
		while(resultado.next()) {
			String codEditorial = resultado.getString(1);
			String nombreEditorial = resultado.getString(2);
			editorial = new DAOEditorial(codEditorial,nombreEditorial);
			editoriales.addElement(editorial);
		}
		return editoriales;
	}
	private static DAOEditorial buscaResultadosUnaEditorial(String consulta) throws Exception{
		
		resultado = DAOEditorial.sentencia.executeQuery(consulta);
		return cargaResultSetToEditorial(resultado);
	}
	
	private static DAOEditorial cargaResultSetToEditorial(ResultSet resultado) throws Exception{
		
		DAOEditorial editorial = null;
		
		while(resultado.next()) {
			String codEditorial = resultado.getString(1);
			String nombreEditorial = resultado.getString(2);
			editorial = new DAOEditorial(codEditorial,nombreEditorial);			
		}
		
		return editorial;
	}
	
	public static void mostrarEditorial(DAOEditorial editorial) {
		System.out.println("Código editorial: "+editorial.getCod_editorial()+"|| Nombre editorial: "+editorial.getNombre_editorial());
		
	}
	//FIN MÉTODOS UTILITY DE CLASE
}
