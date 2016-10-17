<%-- 
    Document   : verProveedor
    Created on : Oct 5, 2016, 8:04:07 PM
    Author     : chaos
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help4Traveling - Proveedores</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
         <script src="../js/jquery-3.1.0.js"></script>
        <script src="../jquery-ui.min.js"></script>
        <link rel="stylesheet" href="../css/cssVerProveedor.css"/>
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class="contenedorProveedores">
            <%String[] datosProveedores = (String[]) session.getAttribute("proveedores");
            if(datosProveedores.length > 0){
                for(int i = 0; i < datosProveedores.length; i++){
                    String[] datos = datosProveedores[i].split(";");%>
                    <div class="diviProveedor">
                        <img src="../perfiles/<%=datos[2]%>-1.jpg" class ="imgProveedor"/>

                     <div class ="datosProveedor"> 
                                    <br>
                                     <a href ="../InformacionProveedorServlet?nickProveedor=<%=datos[2]%>" class ="nombreProveedor"><%=datos[2]%></a>
                                    <br>
                                    <label class ="nickProveedor"><%=datos[0]%>&nbsp;<%=datos[1]%></label>
                                </div>

                      </div>
                <%}
            }%>
        </div>    
    </body>
</html>
