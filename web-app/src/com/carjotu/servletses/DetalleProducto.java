package com.carjotu.servletses;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carjotu.controler.ControladorCategoria;
import com.carjotu.controler.ControladorCliente;
import com.carjotu.controler.ControladorEditorial;
import com.carjotu.controler.ControladorLibro;
import com.carjotu.dao.DAOLibro;
import com.carjotu.dao.DAOlibro_escritor;
import com.carjotu.model.ModeloLibreria;

/**
 * Servlet implementation class DetalleProducto
 */
@WebServlet("/DetalleProducto")
public class DetalleProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ControladorLibro controlador;
	private ModeloLibreria modelo;
	private ControladorCategoria controladorCat;
	private ControladorEditorial controladorEdit;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleProducto() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	modelo = new ModeloLibreria();
    	controlador = new ControladorLibro(modelo);
    	controladorCat= new ControladorCategoria(modelo);
    	controladorEdit = new ControladorEditorial(modelo);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		response.setHeader("Cache-Control", "no-cache");
		String isbn = request.getParameter("isbn");
		DAOLibro libro= null;
		try {
			libro = controlador.obtenerLibro(Long.parseLong(isbn));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Vector<DAOlibro_escritor> autores = new Vector();
         String listaAutores = "";
 		try {
 			autores =  controlador.obtenerLibroEscritorIsbn(Long.valueOf(libro.getIsbn()));
 			}catch(Exception e){
 				e.printStackTrace();
 			}
 		if(autores!=null){for(DAOlibro_escritor autorLibro: autores){
 				listaAutores += autorLibro.getNombreAutor()+" ";
 				if(autorLibro.getpApelAutor()!=null){
 					listaAutores+= autorLibro.getpApelAutor();
 				}
 				if(autores.lastElement()!=autorLibro){
 					listaAutores+=", ";
 				}
 				 
 		} }
		PrintWriter out = response.getWriter();
		try {
			out.println ("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
					+ "    <title>Project Page - Carjotu</title>\r\n"
					+ "    <meta name=\"description\" content=\"Proyecto Librera online, Interfaces y Acceso a datos\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Lato:300,400,700\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto+Slab:300,400|Roboto:300,400,700\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"assets/fonts/font-awesome.min.css\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"assets/fonts/ionicons.min.css\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"assets/css/Black-Navbar.css\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"assets/css/Login-Form-Clean.css\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"assets/css/MUSA_carousel-product-cart-slider-1.css\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"assets/css/MUSA_carousel-product-cart-slider.css\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"assets/css/Product-Details.css\">\r\n"
					+ "</head>\r\n"
					+ "\r\n"
					+ "<body>\r\n"
					+ "    <nav class=\"navbar navbar-dark navbar-expand-lg fixed-top bg-white portfolio-navbar gradient\" style=\"background: linear-gradient(white 1%, var(--bs-red) 1%, black 100%);\">\r\n"
					+ "        <div class=\"container\"><a class=\"navbar-brand logo\" href=\"index.jsp\">Carjotu</a><button data-bs-toggle=\"collapse\" class=\"navbar-toggler\" data-bs-target=\"#navbarNav\"><span class=\"visually-hidden\">Toggle navigation</span><span class=\"navbar-toggler-icon\"></span></button>\r\n"
					+ "            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\r\n"
					+ "                <ul class=\"navbar-nav ms-auto\">\r\n"
					+" <li class=\"nav-item\"><a class=\"nav-link active\" href=\"index.jsp\">Inicio</a></li>\r\n"
					+ " <li class=\"nav-item\"><a class=\"nav-link active\" href=\"catalogo.jsp\">Catálogo</a></li>");
					if(session.getAttribute("user")== null) {
					out.println("<li class=\"nav-item\"><a class=\"nav-link login\" href=\"login.jsp\" style=\"color: rgba(224,217,217,0.9);\">Log In</a></li>"
                    + "<li class=\"nav-item\"><a class=\"nav-link login\" href=\"signup.jsp\" style=\"color: rgba(224,217,217,0.9);\">Sign Up</a></li>");
                	 } 
                	 if(session.getAttribute("user")!= null){
                		 out.println("<li class=\"nav-item\"><a class=\"nav-link login\" href=\"user.jsp?usuario="+session.getAttribute("user")+"\" style=\"color: rgba(224,217,217,0.9);\">"+session.getAttribute("user")+"</a></li>\r\n"
                    + "<li class=\"nav-item\"><a class=\"nav-link login\" href=\"LoginServlet?operation=logout\" style=\"color: rgba(224,217,217,0.9);\">Log out</a></li>");}
					out.println("                	<li class=\"nav-item\"><a class=\"nav-link\" href=\"carrito.jsp\">Carrito</a></li>\r\n"
					+ "                </ul>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </nav>\r\n"
					+ "    <main class=\"page project-page\">\r\n"
					+ "        <section class=\"portfolio-block project\">\r\n"
					+ "            <div class=\"container\">\r\n"
					+ "                <h1 class=\"text-center\">Detalles del libro</h1>\r\n"
					+ "                <div class=\"row\">\r\n"
					+ "                    <div class=\"col-md-7\">\r\n"
					+ "                        <div class=\"row\">\r\n"
					+ "                            <div class=\"col-md-12\"><img class=\"img-thumbnail img-fluid center-block\" src=\"assets/books/"+libro.getImagen()+"\"></div>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"row\">\r\n"
					+ "                            <div class=\"col-md-5\">\r\n"
					+ "                                <h4>Autor:</h4>\r\n"
					+ "                            </div>\r\n"
					+ "                            <div class=\"col\">\r\n"
					+ "                                <p>"+listaAutores+"</p>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"row\">\r\n"
					+ "                            <div class=\"col-md-5\">\r\n"
					+ "                                <h4>Editorial:</h4>\r\n"
					+ "                            </div>\r\n"
					+ "                            <div class=\"col\">\r\n"
					+ "                                <p>"+controladorEdit.obtenerEditorial(libro.getCod_editorial()).getNombre_editorial()+"</p>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"row\">\r\n"
					+ "                            <div class=\"col-md-5\">\r\n"
					+ "                                <h4>Categoría:</h4>\r\n"
					+ "                            </div>\r\n"
					+ "                            <div class=\"col\">\r\n"
					+ "                                <p>"+controladorCat.obtenerCategoria(libro.getCod_categoria()).getNombre_categoria()+"</p>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"col-md-5\">\r\n"
					+ "                        <h1>"+libro.getTitulo()+"</h1>\r\n"
					+ "						   <h4>ISBN:"+libro.getIsbn()+"</h4>\r\n"
					+ "                        <p>"+libro.getDescripcion()+"</p>\r\n"
					+ "                        <h2 class=\"text-center text-success\">"+ String.format("%.2f", libro.getPrecio())+"<i class=\"fa fa-euro\"></i>&nbsp;</h2><button class=\"btn btn-danger btn-lg center-block\" type=\"button\"><i class=\"fa fa-cart-plus\"><a href='ServletCarrito?operation=increase&isbn="+libro.getIsbn()+"'/></i> Add to Cart</button>\r\n"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "        </section>\r\n"
					+ "    </main>\r\n"
					+ "    <footer class=\"page-footer\"></footer>\r\n"
					+ "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>\r\n"
					+ "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js\"></script>\r\n"
					+ "    <script src=\"assets/js/theme.js\"></script>\r\n"
					+ "</body>\r\n"
					+ "\r\n"
					+ "</html>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
