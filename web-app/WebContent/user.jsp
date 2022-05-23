<%@page import="com.carjotu.dao.DAOCliente"%>
<%@page import="com.carjotu.controler.ControladorCliente"%>
<%@page import="com.carjotu.model.ModeloLibreria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Registro - Carjotu</title>
    <meta name="description" content="Proyecto Librería online, Interfaces y Acceso a datos">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab:300,400|Roboto:300,400,700">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/Black-Navbar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
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
                    <li class="nav-item"><a class="nav-link login" href="signup.html" style="color: rgba(224,217,217,0.9);">Sign Up</a></li>
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
    <main class="page contact-page">
        <section class="portfolio-block contact">
            <div class="container">
                <div class="heading">
                    <h2>Actualizar datos</h2>
                </div>
                 <%DAOCliente cliente = new DAOCliente();
                 if(session.getAttribute("user").equals(request.getParameter("usuario"))){ %>
                
                <% String nomUsuario = request.getParameter("usuario");
                	ModeloLibreria modelo = new ModeloLibreria();
                	ControladorCliente controladorCliente = new ControladorCliente(modelo);
                	 cliente= controladorCliente.obtenerCliente(nomUsuario);
                }%>
                <form method="post" action="ServletActualizarInformacion">
                    <div class="mb-3"><h4><%= cliente.getUsuario() %>&nbsp; </h4></div>
                    <div class="mb-3"><label class="form-label" for="name">Nombre *</label><input class="form-control item" type="text" id="name" name="name" value=<%="\""+cliente.getNombre_cliente()+"\""  %> required=""></div>
                    <div><label class="form-label">Primer Apellido *</label><input class="form-control" type="text" id="p_apel" name="p_apel" required="" value=<%="\""+cliente.getP_apellido()+"\""  %>></div>
                    <div><label class="form-label">Segundo Apellido</label><input class="form-control" type="text" id="s_apel" name="s_apel" value=<%="\""+cliente.getS_apellido()+"\""  %>></div>
                    <div class="mb-3"><label class="form-label" for="">Dirección *</label><input class="form-control" type="text" id="dir" name="dir" required="" value=<%="\""+cliente.getDireccion()+"\""  %>></div>
                    <div><label class="form-label">Fecha de nacimiento</label><input class="form-control" id="birthdate" type="date" name="birthdate" value=<%="\""+cliente.getF_nacimiento()+"\""  %>></div>
                    <div class="mb-3"><label class="form-label" for="email">Email *</label><input class="form-control item" type="email" id="email" name="email" required="" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" value=<%="\""+cliente.getEmail()+"\""  %>></div>
                    <div><label class="form-label"><%=cliente.getDni()  %></label></div>
                    <div><label class="form-label">Contraseña *</label><input class="form-control" type="password" id="password" name="password" required=""></div>
                    <div class="mb-3"><input class="btn btn-primary btn-lg d-block w-100" type="submit" value="Guardar cambios" style="color: var(--bs-white);background: var(--bs-red);margin-top: 25px;"></div>
                </form>
            </div>
        </section>
    </main>
    <footer class="page-footer"></footer>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>