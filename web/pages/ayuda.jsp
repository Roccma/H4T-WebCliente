<%-- 
    Document   : ayuda
    Created on : 12/09/2016, 05:38:19 PM
    Author     : kapo_
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help4Traveling - Ayuda</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <link rel ="stylesheet" href="../css/cssAyuda.css"/>
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class="contenedorAyuda">
            <h2>¿Qué es Help4Traveling?</h2>
            <div class ="informacion">
                Help4Traveling es un portal de búsqueda intermedio de vuelos, hoteles, automóviles, entre otros. 
                <br><br>Toda persona tendrá acceso a conocer servicios y promociones de los mismos para poder reservar, sabiéndose datos de los mismos como fecha, ciudad orígen y destino.
            </div>
            <img src ="../images/logo.png" class = "imgLogo"/>
            <br><br>
            <h2>¿Cómo empezar?</h2>
            <br>
            <p>
                Para tener cuenta de usuario, dirigirse a &nbsp;<b>"<span class = "fa fa-user-plus"></span>&nbsp;Sign-up"</b>, y luego simplemente ingresar a través del botón &nbsp;<b>"<span class = "fa fa-unlock"></span>&nbsp;Login"</b>. De lo contrario, podrá utilizar el sitio como <b>visitante</b>.
            </p>
            <br><br><br><br><br>
            <h2>Funcionalidades del sistema</h2>
            <br>
            <h4><span class = "fa fa-truck"></span>&nbsp;<b>Proveedores</b></h4>
            <p>Allí se encuentra la información acerca de cada proveedor de servicios del sistema, como también se puede consultar cada servicio del mismo.</p>
            <br>
            <h4><span class = "fa fa-bed"></span>&nbsp;<b>Servicios</b></h4>
            <p>Para explorar los servicios que se brindan actualmente. En caso de estar interesado en alguno, se deberá agregar dicho servicio al <b>carrito</b>, indicando fecha de inicio y fin de la reserva y cantidad.</p>
            <br>
            <h4><span class = "fa fa-shopping-bag"></span>&nbsp;<b>Promociones</b></h4>
            <p>Brinda información (por ejemplo: descuento, precio) sobre las disitintas promociones de servicios que publican los proveedores. Al igual que los servicios, si se está interesado en alguno, agregarlo al carrito.</p>
            <br>
            <h4><span class = "fa fa-calendar"></span>&nbsp;<b>Reservas</b></h4>
            <p>Aquí se encuentran todos aquellos productos que luego de haberse encontrado en el carrito de compras, fueron aceptados (por lo tanto, reservados). En esta sección, se podrá ver con detalle cada una de esas reservas, como también cancelar aquellas que todavía no fueron facturadas ni pagadas.</p>
            <br>
            <h4><span class = "fa fa-shopping-cart"></span>&nbsp;<b>Carrito de compras</b></h4>
            <p>El carrito contiene todos los servicios y promociones en los que el usuario se encuentra interesado en comprar. Para finalizar esa compra, dentro de esta sección deberán confirmar estos productos.</p>
            <br>
            <h4><span class = "fa fa-search"></span>&nbsp;<b>Barra de búsqueda</b></h4>
            <p>Al ingresar cualquier cadena de texto, el sitio traerá como resultado aquellos servicios y promociones que contienen esa cadena de caracteres solicitada en su nombre, categoría o descripción. El resultado podrá ser ordenado por precio (ascendentemente), o de acuerdo al alfabeto (a-z).</p>
        </div>
    </body>
</html>
