<%-- 
    Document   : resultadoBusqueda
    Created on : 09-oct-2016, 4:26:44
    Author     : usuario
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../js/jquery.slides2.js"></script>
        <title>Help4Traveling - B&uacute;squeda</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <script src ="../js/jsResultadoBusqueda.js"></script>
        <link rel ="stylesheet" href="../css/cssResultadoBusqueda.css"/>
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class ="contenedorBusqueda">
            <%String orden = (String) request.getParameter("orden");
            String buscar = (String) session.getAttribute("buscar");
            String[] productos = (String[]) session.getAttribute("productos");%>
            <div class ="informacionResultados">
                <b>"<%=buscar%>"</b>&nbsp;|&nbsp;<b><%=productos.length%></b>&nbsp;productos encontrados
                <div class = "divOrden">
                    Ordenar por:&nbsp;
                    <%if(orden.equals("p")){%>
                        <select class="cmbOrden">
                            <option value ="p" selected>Precio (ascendente)</option>
                            <option value ="a">Alfabéticamente (a-z)</option>
                        </select>
                    <%}
                    else{%>
                        <select class="cmbOrden">
                            <option value ="p">Precio (ascendente)</option>
                            <option value ="a" selected>Alfabéticamente (a-z)</option>
                        </select>
                    <%}%>
                </div>
                <%for(int i = 0; i < productos.length; i++){
                        String[] datos = productos[i].split(";");%>
                    <div class ="informacionProducto <%if(i ==0) {%>primero <%}%>">
                        <%if(datos[0].equals("servicio")){%>
                            <img src ="../servicios/<%=datos[1]%>-<%=datos[2]%>-1.jpg" class ="imgProducto"/>
                        <%}else{%>
                            <img src ="../perfiles/<%=datos[2]%>-1.jpg" class ="imgProducto"/>
                        <%}%>
                        <div class="datosDelProducto">
                            <a href ="<%=datos[4]%>" class ="nombreProducto"><%=datos[1]%></a>
                            <br>
                            <label class ="nickProveedor"><%=datos[2]%></label>
                            <br><br>
                            <label class ="tipoProducto"><%if(datos[0].equals("servicio")){%>Servicio<%}else{%>Promoci&oacute;n<%}%></label>
                        </div>
                        <div class ="divPrecio">
                            U$S&nbsp;<%=datos[3]%>
                        </div>
                    </div> 
                <%}%>
            </div>
        </div>
    </body>
</html>
