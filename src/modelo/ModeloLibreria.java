package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dao.DAOAutor;

public class ModeloLibreria {

		private Connection conexionBBDD;
		private Statement sentencia;
		private DAOAutor autor;
		private Vector<DAOAutor> autores;
		
		public ModeloLibreria() {
			try {
			this.conexionBBDD = servicio.ServicioMySQL.obtenerServicio().obtenerConexion();
			this.sentencia = this.conexionBBDD.createStatement();
			
			} catch (SQLException e) {
				System.err.println();
				e.printStackTrace();
			}
			
		}
		public void terminar () {
			try {
					sentencia.close();
					conexionBBDD.close();
				
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO AL CERRAR LA CONEXIÓN ");
				e.printStackTrace();
			}
		}
		
		
		////////////////////////////////////////////////////////////////
		///// INICIO DE LA PARTE DE NEGOCIO ///////////////////////////
		//////////////////////////////////////////////////////////////
		
		public Vector<DAOAutor> obtenerAutores(){
			try {
				
			
			DAOAutor.setConexionBBDD(sentencia, null);
			autores=DAOAutor.obtenerAutores();
			
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  AUTOR");
				e.printStackTrace();
			}
			return autores;
			
		}
		
		
	

}
