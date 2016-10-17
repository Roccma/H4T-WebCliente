<%-- 
    Document   : index
    Created on : 12/09/2016, 05:41:36 PM
    Author     : kapo_
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">
        <link rel ="stylesheet" href ="../fonts.css"/>
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../js/jquery.slides2.js"></script>
        <script src="../js/bootstrap.js"></script>
        <script src="../js/jsIndex.js"></script>
        <title>Help4Traveling - Inicio</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <link rel="stylesheet" href="../css/cssIndex.css"/>
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class ="contenedorInicio">
            <h1 class = "titulo"><b>¡Bienvenido a Help4Traveling!</b></h1>
            <h2 class = "subtitulo">El lugar donde hacer tus compras online</h2>
            
            <div class ="slides">
                <img src ="../images/slide1.png" class = "imgSlide"/>
                <img src ="../images/slide2.png" class = "imgSlide"/>
                <img src ="../images/slide3.png" class = "imgSlide"/>
                <img src ="../images/slide4.png" class = "imgSlide"/>
                <img src ="../images/slide5.png" class = "imgSlide"/>
                <img src ="../images/slide6.png" class = "imgSlide"/>
                <img src ="../images/slide7.png" class = "imgSlide"/>                
            </div>
            <br><br>
            <div class ='promociones'>
                <br><br>
                <h3 class="subtituloOferta">¡No te pierdas estas ofertas!</h3>
                <br>                
            </div>
            <div class ='servicios'>
                <br><br>
                <h3 class="subtituloOferta">Nuestros servicios con m&aacute;s compras</h3>
                <br>
            </div>
            <img src ="../images/logo.png" class = "imgLogo"/>
            
        </div>       
        
    </body>

</html>
