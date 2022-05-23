package com.carjotu.servletses;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carjotu.beans.Producto;
import com.carjotu.controler.ControladorLibro;
import com.carjotu.controler.ControladorVenta;
import com.carjotu.model.ModeloLibreria;

/**
 * Servlet implementation class ValidarCompra
 */
@WebServlet("/ValidarCompra")
public class ValidarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ModeloLibreria modelo = new ModeloLibreria();
	ControladorVenta controladorVenta = new ControladorVenta(modelo);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarCompra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		HttpSession session = request.getSession(true);
		HashMap<String, Producto> librosEnCarrito  = (HashMap<String, Producto>) session.getAttribute("carrito");
		
		if (session.getAttribute("user")!=null) {
			
		
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");  
		   LocalDateTime now = LocalDateTime.now();  
		   int npedido=(int) (Math.random()*1000);
		try {
			controladorVenta.insertarVenta(npedido, (String)session.getAttribute("user"), (""+dtf.format(now)));
		
		for (Producto producto : librosEnCarrito.values()) {
			
			controladorVenta.insertarVentaLibro(npedido, String.valueOf(producto.getIsbn()),producto.getCantidad());
			
		}
		
		session.setAttribute("carrito", null);
		
		response.sendRedirect("index.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}else {
			response.sendRedirect("login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
