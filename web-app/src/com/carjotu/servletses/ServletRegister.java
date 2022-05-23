package com.carjotu.servletses;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carjotu.controler.ControladorCliente;
import com.carjotu.dao.DAOCliente;
import com.carjotu.model.ModeloLibreria;

import sun.misc.BASE64Decoder;

@WebServlet("/RegisterServlet")
public class ServletRegister extends HttpServlet
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

   public void doGet(HttpServletRequest request, 
                     HttpServletResponse response) 
   throws ServletException, IOException
   {		
	
   }	

   // Metodo para POST
	
   public void doPost(HttpServletRequest request, 
                      HttpServletResponse response) 
   throws ServletException, IOException
   {
	   response.setHeader("Cache-Control", "no-cache");
	   PrintWriter out = response.getWriter();
       String userName = request.getParameter("username");
       String dni = request.getParameter("dni");
       String nombre = request.getParameter("name");
       String p_apel = request.getParameter("p_apel");
       String s_apel = request.getParameter("s_apel");
       String direccion = request.getParameter("dir");
       String email = request.getParameter("email");
       String fecha = request.getParameter("birthdate").toString();
       String password = request.getParameter("password");
       String userValidate = "";
       String error= "";

       for (DAOCliente cliente : clientes) {
				if (cliente.getUsuario().equals(userName) ||cliente.getDni().equals(dni)||cliente.getEmail().equals("email")) {
						userValidate="EXISTE";
				}else {
					userValidate = "OK";
				}
			}
		

       if(userValidate.equals("EXISTE"))
        {
    	   request.setAttribute("errMessage", "Error al introducir los datos");
	          RequestDispatcher requestDispatcher = request.getRequestDispatcher("/signup.jsp");
	          requestDispatcher.forward(request, response);
            }
        else if(userValidate.equals("OK"))
        {
        	
        	try {
				controlador.insertarCliente(userName, dni, nombre, p_apel, s_apel, direccion, email, fecha, password);
				request.setAttribute("errMessage", "Usuario registrado, yá puedes conectarte ");
		          RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
		          requestDispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
				request.setAttribute("errMessage", "Error al introducir los datos");
		          RequestDispatcher requestDispatcher = request.getRequestDispatcher("/signup.jsp");
		          requestDispatcher.forward(request, response);
			}
       	 
       	 }
	
   }
}