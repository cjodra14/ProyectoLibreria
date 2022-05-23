package com.carjotu.servletses;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import sun.misc.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;




import com.carjotu.controler.ControladorCliente;
import com.carjotu.dao.DAOCliente;
import com.carjotu.model.ModeloLibreria;

@WebServlet("/LoginServlet")
public class ServletLogin extends HttpServlet
{


	private ControladorCliente controlador;
	private ModeloLibreria modelo;
	Vector<DAOCliente> clientes;
	
	
	Properties userpass = new Properties();
	private static final long serialVersionUID = 1L;
// Conjunto de logins y passwords permitidos
	
	
   // Metodo de inicializacion
	
   public void init(ServletConfig config) throws ServletException{

	   try {
		   
		   modelo = new ModeloLibreria();
		   controlador = new ControladorCliente(modelo);
		   clientes = modelo.obtenerClientes();
} catch (Exception e) {
	System.err.println("He fallado, ni lo intentes..."+e.getMessage());
}
	  
   }
   // Metodo para GET

   public void doGet(HttpServletRequest request, 
                     HttpServletResponse response) 
   throws ServletException, IOException
   {		
	   response.setHeader("Cache-Control", "no-cache");
	   if(request.getParameter("operation").equals("logout")) {
	    HttpSession session=request.getSession(false);
	      session.invalidate();
	      request.setAttribute("errMessage", "Sesión cerrada con exito");
          RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
          requestDispatcher.forward(request, response);
	      }
	  
   }	

   // Metodo para POST
	
   public void doPost(HttpServletRequest request, 
                      HttpServletResponse response) 
   throws ServletException, IOException
   {
	   try {
		   clientes = modelo.obtenerClientes();
	} catch (Exception e) {
		// TODO: handle exception
	}
	   
	   response.setHeader("Cache-Control", "no-cache");
	   //PrintWriter out = response.getWriter();
       String userName = request.getParameter("username");
       String password = request.getParameter("password");
       HttpSession session = request.getSession(true);
       DAOCliente clienteCheck = null;

       for (DAOCliente cliente : clientes) {
				if (cliente.getUsuario().equals(userName)) {
					if (cliente.getPass().equals(password)) {
						clienteCheck = cliente;
						
						
					}
				}else {
					
				}
			}
		
        //Calling authenticateUser function

       if(clienteCheck!=null) //If function returns success string then user will be rooted to Home page
        {
    	   session.setAttribute("user", clienteCheck.getUsuario());
			response.sendRedirect(request.getContextPath()+"/catalogo.jsp");
        }
        else
        {
        	request.setAttribute("errMessage", "Usuario o contraseña errones");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
//       	 out.println ("<HTML><BODY>");
//			out.println ("Usuario o contraseña incorrectos");
//			out.println ("</BODY></HTML>");	
       	  //request.setAttribute("errMessage", "Usuario o contraseña incorrectos"); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            //request.getRequestDispatcher("login.jsp").forward(request, response);//forwarding the request
        }
	//doGet(request, response);
   }
}
   