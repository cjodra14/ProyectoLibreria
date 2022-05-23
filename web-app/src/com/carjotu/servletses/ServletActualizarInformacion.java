package com.carjotu.servletses;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carjotu.controler.ControladorCliente;
import com.carjotu.dao.DAOCliente;
import com.carjotu.dao.DAOLibro;
import com.carjotu.model.ModeloLibreria;

/**
 * Servlet implementation class ServletActualizarInformacion
 */
@WebServlet("/ServletActualizarInformacion")
public class ServletActualizarInformacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControladorCliente controlador;
	private ModeloLibreria modelo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActualizarInformacion() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	   modelo = new ModeloLibreria();
		   controlador = new ControladorCliente(modelo);
		  
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   response.setHeader("Cache-Control", "no-cache");
	   HttpSession session = request.getSession();
       String userName = (String) session.getAttribute("user");
       String nombre = request.getParameter("name");
       String p_apel = request.getParameter("p_apel");
       String s_apel = request.getParameter("s_apel");
       String direccion = request.getParameter("dir");
       String email = request.getParameter("email");
       String fecha = request.getParameter("birthdate").toString();
       String password = request.getParameter("password");
       String userValidate = "";
       String error= "";
       System.out.println(userName+" "+nombre+" "+ p_apel+" " +s_apel+" "+ direccion+" "+ email+" "+ fecha+" "+ password);

        	
        	try {
				controlador.modificarCliente(userName,nombre,p_apel,s_apel,direccion,email,fecha,password);
				request.setAttribute("errMessage", "Datos cambiados con exito ");
		          RequestDispatcher requestDispatcher = request.getRequestDispatcher("catalogo.jsp");
		          requestDispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				StackTraceElement[] elements =e.getStackTrace();
				for (StackTraceElement stackTraceElement : elements) {
					System.err.println(stackTraceElement);
				}
				request.setAttribute("errMessage", "Error al cambiar los datos");
		          RequestDispatcher requestDispatcher = request.getRequestDispatcher("catalogo.jsp");
		          requestDispatcher.forward(request, response);
			}
       	 
       	 }
		
	}



