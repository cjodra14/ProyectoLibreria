<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Login - Carjotu</title>
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
                    <li class="nav-item"><a class="nav-link login" href="login.jsp" style="color: rgba(224,217,217,0.9);">Log In</a></li>
                    <li class="nav-item"><a class="nav-link login" href="signup.jsp" style="color: rgba(224,217,217,0.9);">Sign Up</a></li>
                	<li class="nav-item"><a class="nav-link" href="carrito.jsp">Carrito</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <main class="page contact-page">
        <section class="portfolio-block contact">
            <div class="container">
            <span style="color:red; margin-left:auto;margin-right:auto;text-align:center"><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></span>
                <section class="login-clean">
                    <form method="post" action="LoginServlet">
                        <h2 class="visually-hidden">Login</h2>
                        <div class="illustration"><i class="icon ion-ios-navigate"></i></div>
                        <div class="mb-3"><input class="form-control" type="text" id="username" name="username" placeholder="Nombre de usuario" required=""></div>
                        <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Password" required=""></div>
                        <div class="mb-3"><input class="btn btn-primary d-block w-100" type="submit"></div>
                    </form>
                </section>
            </div>
        </section>
    </main>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>