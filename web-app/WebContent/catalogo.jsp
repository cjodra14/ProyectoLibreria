<%@page import="com.carjotu.dao.DAOlibro_escritor"%>
<%@page import="com.carjotu.dao.DAOLibro"%>
<%@page import="java.util.Vector"%>
<%@page import="com.carjotu.controler.ControladorLibro"%>
<%@page import="com.carjotu.controler.ControladorCliente"%>
<%@page import="com.carjotu.model.ModeloLibreria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%
    ModeloLibreria modelo = new ModeloLibreria();
 	ControladorLibro controlador = new ControladorLibro(modelo);
 	Vector<DAOLibro> vectorLibros = controlador.obtenerLibros();
 	DAOLibro libro1 = vectorLibros.firstElement();
  %>
  
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Catalogo - Carjotu</title>
    <meta name="description" content="Proyecto Librería online, Interfaces y Acceso a datos">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab:300,400|Roboto:300,400,700">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/Black-Navbar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="assets/css/MUSA_carousel-product-cart-slider-1.css">
    <link rel="stylesheet" href="assets/css/MUSA_carousel-product-cart-slider.css">
</head>

<body>
    <nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-white portfolio-navbar gradient" style="background: linear-gradient(white 1%, var(--bs-red) 1%, black 100%);">
        <div class="container"><a class="navbar-brand logo" href="index.jsp">Carjotu</a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navbarNav"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    
                    <li class="nav-item"><a class="nav-link active" href="index.jsp">Inicio</a></li>
                    <li class="nav-item"><a class="nav-link active" href="catalogo.jsp">Catálogo</a></li>
                    <%if(session.getAttribute("user")== null) {%>
                    <li class="nav-item"><a class="nav-link login" href="login.jsp" style="color: rgba(224,217,217,0.9);">Log In</a></li>
                    <li class="nav-item"><a class="nav-link login" href="signup.jsp" style="color: rgba(224,217,217,0.9);">Sign Up</a></li>
                	 <%} %>
                	 <% 
      if(session.getAttribute("user")!= null)
      {%>
      
       <li class="nav-item"><a class="nav-link login" href=<%= "\"user.jsp?usuario="+session.getAttribute("user")+"\"" %> style="color: rgba(224,217,217,0.9);"><%= session.getAttribute("user") %></a></li>
                    <li class="nav-item"><a class="nav-link login" href="LoginServlet?operation=logout" style="color: rgba(224,217,217,0.9);">Log out</a></li>
      <%}
      %>
                	<li class="nav-item"><a class="nav-link" href="carrito.jsp">Carrito</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <main class="page projects-page">
        <section class="portfolio-block projects-cards">
            <div class="container"><div class="container">
    <div class="row">
        <div class="row">
            <div class="col-md-9">
                <h3>
                    Catálogo de libros</h3>
            </div>
        </div>
        <div id="carousel-example" class="carousel slide hidden-xs" data-ride="carousel">
            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active">
                    <div class="row">
                    <%for(DAOLibro libro: vectorLibros){ %>
                        <div class="col-sm-3">
                            <div class="col-item">
                                <div class="photo">
                                <!--C:\Eclipse_JavaSE\RepasoJava\ProyectoLibreria\-->
                                    <img src="assets\books\<%= libro.getImagen() %>" class="img-responsive" alt="Imagen del libro" height ="200px" width="300px" />
                                </div>
                                <div class="info">
                                    <div class="row">
                                        <div class="price ">
                                            <h5>
                                                <%= libro.getTitulo() %></h5>
                                            <h5 class="price-text-color">
                                            <%String precio = String.format("%.2f", libro.getPrecio()); %>
                                                <%= precio %>€</h5>
                                                <p>
                                                <%
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
                                        				 
                                        		} }%>
                                        		<%=	
                                        		
                                        			listaAutores

                                                %>
                                                </p>
                                        </div>
                                        
                                    </div>
                                    <div class="separator clear-left">
                                        <p class="btn-add">
                                            <i class="fa fa-shopping-cart"></i><a href='<%= "ServletCarrito?operation=increase&isbn="+libro.getIsbn()%>' class="hidden-sm">Añadir al carrito</a></p>
                                        <p class="btn-details">
                                            <i class="fa fa-list"></i><a href='<%= "DetalleProducto"+"?isbn="+libro.getIsbn()%>' class="hidden-sm">Ver Artículo</a></p>
                                    </div>
                                    <div class="clearfix">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%} %>
                       </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

        </section>
    </main>
    <footer class="page-footer"></footer>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>