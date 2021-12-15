package com.carjotu.dao;

import java.sql.*;
import java.util.Vector;

public class DAOVenta_libro {
int npedido,  cantidad;
long isbn;
double precio;
String usuario,fecha, titulo;

public DAOVenta_libro() {
	
}

public DAOVenta_libro(int npedido, long isbn, int cantidad) {
	this.npedido=npedido;
	this.isbn=isbn;
	this.cantidad=cantidad;	
}

public DAOVenta_libro(int npedido, int isbn, int cantidad, String usuario, String fecha, String titulo, double precio) {
	super();
	this.npedido = npedido;
	this.isbn = isbn;
	this.cantidad = cantidad;
	this.usuario=usuario;
	this.fecha=fecha;
	this.titulo=titulo;
	this.precio=precio;
}

//Getters y setters
public int getNpedido() {
	return npedido;
}

public void setNpedido(int npedido) {
	this.npedido = npedido;
}

public long getIsbn() {
	return isbn;
}

public void setIsbn(long isbn) {
	this.isbn = isbn;
}

public int getCantidad() {
	return cantidad;
}

public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}

public double getPrecio() {
	return precio;
}

public void setPrecio(int precio) {
	this.precio = precio;
}

public String getUsuario() {
	return usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}

public String getFecha() {
	return fecha;
}

public void setFecha(String fecha) {
	this.fecha = fecha;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

//INTEGRACIÓN CON LA BBDD
//La clase DAO debe tener conexión con la BBDD para conectarse con su tabla
private static Statement sentencia;
private static ResultSet resultado;

public static void setConexionBBDD(Statement sentencia, ResultSet resultado) {
	DAOVenta_libro.sentencia = sentencia;
	DAOVenta_libro.resultado = resultado;
}

//MÉTODOS CRUD
	//READ
	//Método que extra todos los registros de la tabla
//	public static Vector<DAOVenta_libro> obtenerVentasLibro() throws SQLException{
//		String sqlQuery = "select VE.cantidad ,VE.ISBN,  L.titulo , L.precio from venta_libro VE, venta V, libro L WHERE L.ISBN=VE.ISBN;";
//		//Este método devolverá un vector de tipo <DAOVenta_libro>
////		return buscaResultadosConConsulta(sqlQuery);	
//	}
	public static DAOVenta_libro obtenerVentaLibro(String npedido) throws Exception{
		String sqlQuery = "select VE.npedido, VE.ISBN, VE.cantidad from venta_libro VE, venta V, libro L WHERE VE.npedido='"+npedido+"';";
		return buscaResultadosUnaVenta(sqlQuery);
	}
	//Este método se utiliza para meter datos con la sentencia INSERT
	public static void insertarVenta(int npedido, String isbn) {
		try {
			String sqlQuery = "INSERT INTO libreria.venta_libro VALUES ('"+npedido+"','"+isbn+"');";
			DAOVenta_libro.sentencia.execute(sqlQuery);
			System.out.println("La venta con el número de pedido "+npedido+" para el libro con el isbn "+isbn+"ha sido insertada con éxito.");
		}catch(SQLException e) {
			System.err.println("\nNo se han podido insertar datos en la venta con el código "+npedido);
		}
	}

	
	//Este método ejecutará una sentencia UPDATE para modificar una venta
	public static void modificarVenta(int npedido, int isbn, int cantidad, String usuario, String fecha, String titulo,Double precio){
		try {
			String sqlQuery = "UPDATE libreria.venta_libro SET ISBN= '"+isbn+"',cantidad= '"+cantidad+"',usuario= '"+usuario+"',fecha= '"+fecha+"',titulo= '"+titulo+"',precio= '"+precio+" WHERE npedido='"+npedido+"';";
			DAOVenta_libro.sentencia.executeUpdate(sqlQuery);	
			System.out.println("La venta con el número de pedido "+npedido+" ha sido modificada con éxito.");
		}catch(SQLException e) {
			System.err.println("Error al modificar la venta.\n"+e.getStackTrace());
		}
	} 
	
	
//Este método ejecutará una sentencia DELETE para eliminar una venta
public static int borrarVenta(String npedido) throws SQLException {
String sqlQuery="DELETE FROM libreria.venta WHERE npedido='"+npedido+"'";
return DAOVenta_libro.sentencia.executeUpdate(sqlQuery);
}
//FIN MÉTODOS CRUD
//MÉTODOS UTILITY DE CLASE
	//Método que extrae filas de la tabla a través de una consulta
//	private static Vector<DAOVenta_libro> buscaResultadosConConsulta(String consulta) throws SQLException{
//		resultado = DAOVenta_libro.sentencia.executeQuery(consulta);
//		return cargaResultSetToVector(resultado);
//
//	}
	
//	private static Vector<DAOVenta_libro> cargaResultSetToVector(ResultSet resultado) throws SQLException {
//		Vector<DAOVenta_libro> ventasLibros = new Vector<DAOVenta_libro>();
//		DAOVenta_libro ventaLibro;
//		while(resultado.next()) {
//			int cantidad = resultado.getInt(4);
//			String fecha= resultado.getString(5);
//			String titulo = resultado.getString(6);
//			Double precio = resultado.getDouble(7);
//			ventaLibro = new DAOVenta_libro(npedido,isbn,cantidad,usuario,fecha,titulo,precio);
//			ventasLibros.addElement(ventaLibro);
//		}
//		
//		return ventasLibros;
//}
	private static DAOVenta_libro buscaResultadosUnaVenta(String consulta) throws Exception{
		resultado = DAOVenta_libro.sentencia.executeQuery(consulta);
		return cargaResultSetToVentaLibro(resultado);
		
	}
	
	private static DAOVenta_libro cargaResultSetToVentaLibro(ResultSet resultado) throws Exception {
		
		DAOVenta_libro ventaLibro = null;
		
		while(resultado.next()) {
			int npedido = resultado.getInt(1);
			int isbn = resultado.getInt(2);
			int cantidad = resultado.getInt(3);
			
			ventaLibro = new DAOVenta_libro(npedido,isbn,cantidad);
		}
		
		return ventaLibro;
	}
	
	public static void mostrarVentaLibro(DAOVenta_libro ventaLibro) {
		System.out.println(ventaLibro.getCantidad()+"x  ||  "+ventaLibro.getIsbn()+"  ||  "+ventaLibro.getTitulo()+"  ||  "+ventaLibro.getPrecio());


	}
	//FIN MÉTODOS UTILITY DE CLASE

}


