package com.carjotu.servletses;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carjotu.beans.Producto;
import com.carjotu.controler.ControladorLibro;
import com.carjotu.dao.DAOLibro;
import com.carjotu.model.ModeloLibreria;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/ServletCarrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ModeloLibreria modelo = new ModeloLibreria();
	ControladorLibro controladorLibro = new ControladorLibro(modelo);
	HashMap<String, DAOLibro> listadoLibros = new HashMap<String, DAOLibro>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCarrito() {
        
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try {
			Vector<DAOLibro> libros = controladorLibro.obtenerLibros();
			for (DAOLibro daoLibro : libros) {
				listadoLibros.put(String.valueOf(daoLibro.getIsbn()), daoLibro);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		HttpSession session = request.getSession(true);
		HashMap<String, Producto> librosEnCarrito  = (HashMap<String, Producto>) session.getAttribute("carrito"); ;
		if (librosEnCarrito==null) {
			librosEnCarrito = new HashMap<String, Producto>();
			session.setAttribute("carrito", librosEnCarrito);
		}
		
		
		if (request.getParameter("operation").equals("increase")) {
			
		
		String isbnProducto = request.getParameter("isbn");
		
		
		if (librosEnCarrito.containsKey(isbnProducto)) {
			Producto productoaux = librosEnCarrito.get(isbnProducto);
			if (productoaux.getCantidad()<listadoLibros.get(isbnProducto).getUd_stock()) {
				
			
			productoaux.increase();
			librosEnCarrito.replace(isbnProducto, productoaux);}
			session.setAttribute("carrito", librosEnCarrito);
			respuesta(request, response, librosEnCarrito);
			session.setAttribute("carrito", librosEnCarrito);
			while(!response.isCommitted()) {try {
				respuesta(request, response, librosEnCarrito);}catch(Exception e) {
					}
				}
			
		}else {
			if (0<listadoLibros.get(isbnProducto).getUd_stock()) {
				Producto productoaux = new Producto(1, Long.parseLong(isbnProducto));
				librosEnCarrito.put(isbnProducto, productoaux);
				}
			
			session.setAttribute("carrito", librosEnCarrito);
			while(!response.isCommitted()) {try {
				respuesta(request, response, librosEnCarrito);}catch(Exception e) {
					}
				}

		}
		}
		
		
		if (request.getParameter("operation").equals("decrease")) {
			String isbnProducto = request.getParameter("isbn");
			if (librosEnCarrito.containsKey(isbnProducto)) {
				Producto productoaux = librosEnCarrito.get(isbnProducto);
				productoaux.decrease();
				librosEnCarrito.replace(isbnProducto, productoaux);
				while(!response.isCommitted()) {try {
					respuesta(request, response, librosEnCarrito);}catch(Exception e) {
						}
					}
				
			}
			
			
			}
		
		if (request.getParameter("operation").equals("erase")) {
			String isbnProducto = request.getParameter("isbn");	
				librosEnCarrito.remove(isbnProducto);
				while(!response.isCommitted()) {try {
					respuesta(request, response, librosEnCarrito);}catch(Exception e) {
						}
					}	
			}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		HttpSession session = request.getSession(true);
		HashMap<String, Producto> librosEnCarrito = null;
		
		if (librosEnCarrito==null) {
			librosEnCarrito = new HashMap<String, Producto>();
			session.setAttribute("carrito", librosEnCarrito);
		}else {
			librosEnCarrito = (HashMap<String, Producto>) session.getAttribute("carrito");
		}
		
	}
	
	public void respuesta (HttpServletRequest request, HttpServletResponse response, HashMap<String, Producto> listaProductos) {
		for (Producto productoxx : listaProductos.values()) {
			System.out.println(productoxx.getIsbn()+"  --  "+productoxx.getCantidad());
			
		}
		String urlAnterior = request.getHeader("referer").substring(30);
		System.out.println(urlAnterior);
		try {if(urlAnterior.equals("/carrito.jsp")) {
			
			response.sendRedirect("carrito.jsp");
			
		}else {
			response.sendRedirect("catalogo.jsp");
		}} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
