<%@page import="com.carjotu.dao.DAOEditorial"%>
<%@page import="com.carjotu.controler.ControladorEditorial"%>
<%@page import="com.carjotu.dao.DAOLibro"%>
<%@page import="com.carjotu.controler.ControladorLibro"%>
<%@page import="com.carjotu.model.ModeloLibreria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.carjotu.beans.Producto"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% ModeloLibreria modelo = new ModeloLibreria();
 	ControladorLibro controladorLibro = new ControladorLibro(modelo);
 	ControladorEditorial controladorEditorial = new ControladorEditorial(modelo);
 	double total = 0d;
 	
 
 
 %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Home - Carjotu</title>
    <meta name="description" content="Proyecto LibrerÃ­a online, Interfaces y Acceso a datos">
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
    <link rel="stylesheet" href="assets/css/Product-Details.css">
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
    <main class="page lanidng-page">
        <section class="portfolio-block block-intro"><div class="shopping-cart">
<div class="px-4 px-lg-0">

  <div class="pb-5">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

          <!-- Shopping cart table -->
          <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col" class="border-0 bg-light">
                    <div class="p-2 px-3 text-uppercase">Product</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Precio</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Cantidad</div>
                  </th>
                   <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Subtotal</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Borrar</div>
                  </th>
                </tr>
              </thead>
              <tbody>
<% if(session.getAttribute("carrito")!=null){
HashMap<String, Producto> carrito =(HashMap<String, Producto>) session.getAttribute("carrito"); 
 for (Producto product : carrito.values()) {
DAOLibro libro  = controladorLibro.obtenerLibro(product.getIsbn());
	DAOEditorial editorial = controladorEditorial.obtenerEditorial(libro.getCod_editorial()); 
	
%>

                <tr>
                  <th scope="row" class="border-0">
                    <div class="p-2">
                      <img src="assets\books\<%= libro.getImagen() %>" alt="" width="70" class="img-fluid rounded shadow-sm">
                      <div class="ml-3 d-inline-block align-middle">
                        <h5 class="mb-0"> <a href='<%= "DetalleProducto"+"?isbn="+libro.getIsbn()%>' class="text-dark d-inline-block align-middle"><%=libro.getTitulo() %></a></h5><span class="text-muted font-weight-normal font-italic d-block"><%= editorial.getNombre_editorial() %></span>
                      </div>
                    </div>
                  </th>
                  <td class="border-0 align-middle"><strong><%= String.format("%.2f", libro.getPrecio()) %>€</strong></td>
                  <td class="border-0 align-middle"><a class="text-dark" href='<%= "ServletCarrito?operation=increase&isbn="+libro.getIsbn()%>'><i class="fa fa-plus"></i></a> <strong><%= product.getCantidad() %></strong> <a class="text-dark" href='<%= "ServletCarrito?operation=decrease&isbn="+libro.getIsbn()%>'><i class="fa fa-minus"></i></a></td>
                  <td class="border-0 align-middle"><strong><%= String.format("%.2f", (libro.getPrecio()*product.getCantidad())) %>€</strong></td>
                  <td class="border-0 align-middle"><a  href='<%= "ServletCarrito?operation=erase&isbn="+libro.getIsbn()%>' class="text-dark"><i class="fa fa-trash"></i></a></td>
                  <% total += libro.getPrecio()*product.getCantidad(); %>
                </tr>
                <%}} %>
              </tbody>
            </table>
          </div>
          <!-- End -->
        </div>
      </div>

      <div class="row py-5 p-4 bg-white rounded shadow-sm">
        <div class="col-lg-6">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Resumen del pedido </div>
          <div class="p-4">
            <p class="font-italic mb-4">Desglose del pedido antes y despues de impuestos</p>
            <ul class="list-unstyled mb-4">
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Subtotal </strong><strong><%= String.format("%.2f",total) %>€</strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Impuestos</strong><strong><%= String.format("%.2f",(total*0.21)) %>€</strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                <h5 class="font-weight-bold"><%= String.format("%.2f",(total+(total*0.21)))%>€</h5>
              </li>
            </ul><a href="ValidarCompra" class="btn btn-dark rounded-pill py-2 btn-block">Proceder a la compra</a>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>
</div></section>
    </main>
    <footer class="page-footer"></footer>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>


