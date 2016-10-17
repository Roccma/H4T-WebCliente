<%-- 
    Document   : verPromociones
    Created on : 05-oct-2016, 11:48:47
    Author     : usuario
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../jquery-ui.min.js"></script>
        <title>Help4Traveling - Promociones</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <link rel="stylesheet" href="../css/cssVerPromociones.css"/>
        <script src="../js/jsVerPromociones.js"></script>
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class="contenedorPromociones">
            <%String orden = request.getParameter("orden");            
            String[] datosPromociones = (String[]) session.getAttribute("promociones");%>
            <div class = "divInfoCategoria">
                    <b><%=datosPromociones.length%></b>&nbsp promociones encontradas
                    <div class = "orden">
                        Ordenar por:&nbsp;
                        <select id ="cmbTipoOrdenamiento">
                                <%if(orden.equals("p")){%>
                                    <option value ="p" selected>Precio (ascendente)</option>
                                    <option value ="a">Alfab&eacute;ticamente (a-z)</option>
                                <%}
                                else{%>
                                    <option value ="p">Precio (ascendente)</option>
                                    <option value ="a" selected>Alfab&eacute;ticamente (a-z)</option>
                                <%}%>
                        </select>
                    </div>
                </div>
            
            <%if(datosPromociones.length > 0){
                for(int i = 0; i < datosPromociones.length; i++){
                    String[] datos = datosPromociones[i].split(";");%>
                    
                    <div class = "contenidoPromo">
                        <%String ruta = "../perfiles/datos[1]-1.jpg";%>
                        <img src ="../perfiles/<%=datos[1]%>-1.jpg" class ="imgProveedor"/>
                        <div class ="datosPromo"> 
                            <br>
                            <a href ="../InformacionPromocionServlet?nombrePromocion=<%=datos[0]%>&nickProveedor=<%=datos[1]%>" class ="nombrePromo"><%=datos[0]%></a>
                            <br>
                            <label class ="nickProveedor"><%=datos[1]%></label>
                        </div>
                        <div class = "precioPromo">
                            U$S&nbsp;<%=datos[3]%>
                        </div>

                    </div>
                <%}
            }%>
        </div>
    </body>
</html>
