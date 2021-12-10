package controlador;

import java.sql.SQLException;
import java.util.Vector;

import dao.DAOEditorial;
import modelo.ModeloLibreria;

public class ControladorEditorial {
	
	
	private ModeloLibreria modelo;
	private Vector<DAOEditorial> editoriales;
	
	//Constructor
	public ControladorEditorial(ModeloLibreria modelo) {
		this.modelo=modelo;
	}
	
	//Este metodo retorna un vector de editoriales y lo obtiene del metodo obtenerEditoriales()
	public Vector<DAOEditorial> obtenerEditoriales() throws Exception{
		editoriales=modelo.obtenerEditoriales();
		return editoriales;
	}
	
	public DAOEditorial obtenerEditorial(String cod_editorial) throws Exception {
		DAOEditorial editorial;
		editorial=modelo.obtenerEditorial(cod_editorial);
		return editorial;
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void insertarEditorial(String idEditorial, String nombreEditorial) throws Exception{
		modelo.insertarEditorial(idEditorial, nombreEditorial);
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar una editorial
	public void modificarEditorial(String nombreEditorial, String idEditorial) throws Exception{
		modelo.modificarEditorial(nombreEditorial, idEditorial);
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar una editorial
	public int borrarEditorial(String idEditorial) throws Exception{
		
		 return modelo.borrarEditorial(idEditorial);
	}
	

	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
}
