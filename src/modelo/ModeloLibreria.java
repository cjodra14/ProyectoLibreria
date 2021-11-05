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
		//Método que extrae todos los registros de la tabla con una sentencia SELECT
		public DAOAutor obtenerAutor(String idAutor) throws SQLException {
			return DAOAutor.obtenerAutor(idAutor);
		}
		//Este método se utiliza para meter datos con la sentencia INSERT
		public void insertarDatos(String idAutor, String nombreAutor, String apel1, String apel2) {
			DAOAutor.insertarDatos(idAutor, nombreAutor,  apel1,  apel2);
		}
		//Este método ejecutará una sentencia UPDATE para modificar un autor
		public void modificarAutor(String idAutor, String nombreAutor, String apel1, String apel2) {
			DAOAutor.modificarAutor(idAutor, nombreAutor, apel1, apel2);
		}
		//Este método ejecutará una sentencia DELETE para eliminar un autor
		public void borrarAutor(String idAutor) throws SQLException {
			System.out.println("checkModelo\n");
			DAOAutor.borrarAutor(idAutor);
		}
		
		
	

}
