package dao;

import java.sql.*;
import java.util.Vector;

public class DAOVenta_libro {
int npedido, isbn, cantidad;
String usuario,fecha, titulo;

public DAOVenta_libro() {
	
}

public DAOVenta_libro(int npedido, int isbn, int cantidad, String usuario, String fecha, String titulo) {
	super();
	this.npedido = npedido;
	this.isbn = isbn;
	this.cantidad = cantidad;
	this.usuario=usuario;
	this.fecha=fecha;
	this.titulo=titulo;
}

//Getters y setters
public int getNpedido() {
	return npedido;
}

public void setNpedido(int npedido) {
	this.npedido = npedido;
}

public int getIsbn() {
	return isbn;
}

public void setIsbn(int isbn) {
	this.isbn = isbn;
}

public int getCantidad() {
	return cantidad;
}

public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}

//INTEGRACI�N CON LA BBDD
//La clase DAO debe tener conexi�n con la BBDD para conectarse con su tabla
private static Statement sentencia;
private static ResultSet resultado;

public static void setConexionBBDD(Statement sentencia, ResultSet resultado) {
	DAOVenta_libro.sentencia = sentencia;
	DAOVenta_libro.resultado = resultado;
}

//M�TODOS CRUD
	//READ
	//M�todo que extra todos los registros de la tabla
	public static Vector<DAOVenta_libro> obtenerVentasLibro(String isbn) throws SQLException{
		String sqlQuery = "select VE.npedido, V.usuario, V.fecha, L.titulo from venta_libro VE, venta V, libro L;";
		//Este m�todo devolver� un vector de tipo <DAOVenta_libro>
		return buscaResultadosConConsulta(sqlQuery);	
	}
	public static DAOVenta obtenerVentaLibro(String npedido) throws SQLException{
		String sqlQuery = "select VE.npedido, V.usuario, V.fecha, L.titulo from venta_libro VE, venta V, libro L WHERE V.npedido='"+npedido+"';";
		return buscaResultadosUnaVenta(sqlQuery);
	}
	//Este m�todo se utiliza para meter datos con la sentencia INSERT
	public static void insertarVenta(int npedido, String isbn) {
		try {
			String sqlQuery = "INSERT INTO libreria.venta_libro VALUES ('"+npedido+"','"+isbn+"');";
			DAOVenta.sentencia.execute(sqlQuery);
			System.out.println("La venta con el n�mero de pedido "+npedido+" para el libro con el isbn "+isbn+"ha sido insertada con �xito.");
		}catch(SQLException e) {
			System.err.println("\nNo se han podido insertar datos en la venta con el c�digo "+npedido);
		}
	}

}
