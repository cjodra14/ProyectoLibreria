package com.carjotu.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class DAOCategoria {

	private String cod_categoria;
	private String nombre_categoria;
	
	public DAOCategoria() {
		
	}
	
	public DAOCategoria(String cod_categoria, String nombre_categoria) {
		this.cod_categoria = cod_categoria;
		this.nombre_categoria = nombre_categoria;
	}
	
	

	public String getCod_categoria() {
		return cod_categoria;
	}

	public void setCod_categoria(String cod_categoria) {
		this.cod_categoria = cod_categoria;
	}

	public String getNombre_categoria() {
		return nombre_categoria;
	}

	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}
	
	//INTEGRACIÓN CON LA BBDD
	//La clase DAO debe tener conexión con la BBDD para conectarse con su tabla
		private static Statement sentencia;
		private static ResultSet resultado;
			
	public static void setConexionBBDD(Statement sentencia, ResultSet resultado) {
		DAOCategoria.sentencia = sentencia;
		DAOCategoria.resultado = resultado;
			}
	
	//METODO CRUD
	//READ
	//Metodo que extrae todos los registros de la tabla
	
	public static Vector<DAOCategoria> obtenerCategorias() throws Exception {
		String sqlQuery = "SELECT * FROM libreria.categoria;";
		//Este metodo devolvera un vector de tipo <CategoriaDAO>
		return buscaResultadosConConsulta(sqlQuery);
		
	}
	public static DAOCategoria obtenerCategoria(String codCategoria) throws Exception {
		String sqlQuery = "select * from categoria WHERE cod_categoria= '" + codCategoria + "';";
		//Este metodo devolvera un vector tipo <CategoriaDAO>
		return buscaResultadosUnaCategoria(sqlQuery);
		
	}
	
	//Este metodo se utiliza para meter datos con la sentencia INSERT
	public static void insertarCategoria(String idCategoria, String nombreCategoria) throws Exception {
		
			
				String sqlQuery = "INSERT INTO categoria VALUES ('"+idCategoria+"','"+nombreCategoria+"');";
				DAOCategoria.sentencia.execute(sqlQuery);
				System.out.println("Los datos de la categoria con el código "+idCategoria+" han sido insertados con éxito.");
			
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar una categoria
			//nombre_categoria
			public static int modificarCategoria(String idCategoria,  String nombreCategoria) throws Exception{
				
					String sqlQuery = "UPDATE libreria.categoria SET nombre_categoria= '"+nombreCategoria+"' WHERE cod_categoria='"+idCategoria+"';";
					return DAOCategoria.sentencia.executeUpdate(sqlQuery);	
					
				
			} 
			
			//Este método ejecutará una sentencia DELETE para eliminar un autor
			public static int borrarCategoria(String idCategoria) throws Exception {
				String sqlQuery="DELETE FROM libreria.categoria WHERE cod_categoria='"+idCategoria+"'";
		
				return DAOCategoria.sentencia.executeUpdate(sqlQuery);
			}
			//FIN METODOS CRUD
			
			//MÉTODOS UTILITY DE CLASE
			//Método que extrae filas de la tabla a través de una consulta
			private static Vector<DAOCategoria> buscaResultadosConConsulta(String consulta) throws Exception{
				resultado = DAOCategoria.sentencia.executeQuery(consulta);
				return cargaResultSetToVector(resultado);
				
			}
			
			private static Vector<DAOCategoria> cargaResultSetToVector(ResultSet resultado) throws Exception {
				Vector<DAOCategoria> categorias = new Vector<DAOCategoria>();
				DAOCategoria categoria;
				
				while(resultado.next()) {
					String codCategoria = resultado.getString(1);
					String nombreCategoria = resultado.getString(2);
					categoria = new DAOCategoria(codCategoria,nombreCategoria);
					categorias.addElement(categoria);
				}
				
				return categorias;
			}
			private static DAOCategoria buscaResultadosUnaCategoria(String consulta) throws Exception{
				resultado = DAOCategoria.sentencia.executeQuery(consulta);
				return cargaResultSetToCategoria(resultado);
				
			}
			
			private static DAOCategoria cargaResultSetToCategoria(ResultSet resultado) throws Exception {
				
				DAOCategoria categoria = null;
				
				while(resultado.next()) {
					String codCategoria = resultado.getString(1);
					String nombreCategoria = resultado.getString(2);
					categoria = new DAOCategoria(codCategoria,nombreCategoria);			
				}
				
				return categoria;
			}
			
			public static void mostrarCategoria(DAOCategoria categoria) {
				System.out.print("ID: "+categoria.getCod_categoria()+"|| Nombre: "+categoria.getNombre_categoria());
				System.out.println();
			}
	}
			//FIN MÉTODOS UTILITY DE CLASE


	