package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import dao.DAOAutor;

public class ModeloLibreria {

		private Connection conexionBBDD;
		private Statement sentencia;
		private DAOAutor autor;
		private Vector<DAOAutor> autores;
		Properties servicioElegido= new Properties();
		
		public ModeloLibreria() {
			try {
				try {
					servicioElegido.load(new FileInputStream(new File("serviceElegido.properties")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			this.conexionBBDD = servicio.ServicioBBDD.obtenerServicio(servicioElegido.getProperty("servicio")).obtenerConexion();
			this.sentencia = this.conexionBBDD.createStatement();
			DAOAutor.setConexionBBDD(sentencia, null);
			
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
				System.err.println("Modelo: FALLO AL CERRAR LA CONEXI�N ");
				e.printStackTrace();
			}
		}
		
		
		////////////////////////////////////////////////////////////////
		///// INICIO DE LA PARTE DE NEGOCIO ///////////////////////////
		//////////////////////////////////////////////////////////////
		
		public Vector<DAOAutor> obtenerAutores(){
			try {
				
			
			
			autores=DAOAutor.obtenerAutores();
			
			} catch (SQLException e) {
				System.err.println("Modelo: FALLO A OBTENER  AUTOR");
				e.printStackTrace();
			}
			return autores;
			
		}
		//M�todo que extrae todos los registros de la tabla con una sentencia SELECT
		public DAOAutor obtenerAutor(String idAutor) throws SQLException {
			return DAOAutor.obtenerAutor(idAutor);
		}
		//Este m�todo se utiliza para meter datos con la sentencia INSERT
		public void insertarDatos(String idAutor, String nombreAutor, String apel1, String apel2) {
			DAOAutor.insertarDatos(idAutor, nombreAutor,  apel1,  apel2);
		}
		//Este m�todo ejecutar� una sentencia UPDATE para modificar un autor
		public void modificarAutor(String idAutor, String nombreAutor, String apel1, String apel2) {
			DAOAutor.modificarAutor(idAutor, nombreAutor, apel1, apel2);
		}
		//Este m�todo ejecutar� una sentencia DELETE para eliminar un autor
		public int borrarAutor(String idAutor) throws SQLException {
			
			return DAOAutor.borrarAutor(idAutor);
		}
		
		
	

}
