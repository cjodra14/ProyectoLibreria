package com.carjotu.controler;

import java.sql.SQLException;
import java.util.Vector;

import com.carjotu.dao.DAOAutor;
import com.carjotu.dao.DAOVenta;
import com.carjotu.dao.DAOVenta_libro;
import com.carjotu.dao.DAOlibro_escritor;
import com.carjotu.model.ModeloLibreria;

public class ControladorVenta {

	private ModeloLibreria modelo;
	private Vector<DAOVenta> ventas;
	
	//Constructor
	public ControladorVenta(ModeloLibreria modelo) {
		this.modelo=modelo;
	}
	
	//Este metodo retorna un vector de autores y lo obtiene del metodo obtenerAutores()
	public Vector<DAOVenta> obtenerVentas() throws Exception{
		ventas=modelo.obtenerVentas();
		return ventas;
	}
	
	public DAOVenta  obtenerVentas(String npedido) throws Exception {
		DAOVenta venta;
		venta=modelo.obtenerVentas(npedido);
		return venta;
	}
	
	//Este método se utiliza para meter datos con la sentencia INSERT
	public void  insertarVenta(int npedido, String usuario, String fecha) throws Exception{
		modelo.insertarVenta(npedido, usuario, fecha);
	}
	
	//Este método ejecutará una sentencia UPDATE para modificar un autor
	public int  modificarVenta(int npedido, String usuario, String fecha) throws Exception{
		return modelo.modificarVenta(npedido, usuario, fecha);
	}
	
	//Este método ejecutará una sentencia DELETE para eliminar un autor
	public int borrarVenta(String npedido) throws Exception {
		 return modelo.borrarVenta(npedido);
	}
	
	public DAOVenta_libro obtenerVentaLibro(String npedido) throws Exception{
		return modelo.obtenerVentaLibro(npedido);
	}
	
	

	//Ejecutamos el método terminar para liberar memoria
	public void terminar() {
		modelo.terminar();
	}
}
