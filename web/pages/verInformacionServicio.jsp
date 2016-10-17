<%-- 
    Document   : verInformacionServicio
    Created on : 03-oct-2016, 16:24:18
    Author     : usuario
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../jquery-ui.min.css" type="text/css" rel="stylesheet">
        <link rel ="stylesheet" href ="../fonts.css"/>
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../jquery-ui.min.js"></script>
        <script src="../js/jquery.numeric.js"></script>
        <script src="../js/jquery.slides.js"></script>
        <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">
        <script src="../js/bootstrap.js"></script>    
        <title>Help4Traveling - Servicios</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <script src="../js/funciones.js"></script>
        <script src="../js/jsVerInformacionServicio.js"></script>
        <link rel="stylesheet" href="../css/cssVerInformacionServicio.css"/>
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <%String[] informacionServicio = (String[]) session.getAttribute("informacionServicio");
        String orden = (String) session.getAttribute("orden");
        String ruta = informacionServicio[8];
        String[] pasosRuta = ruta.split(">");%>
        <div class="contenedorServicios">
            <div class = "migaDePan">
                <%for(int i = 0; i < pasosRuta.length; i++){
                    String paso = pasosRuta[i].trim();
                    int j = i + 1;
                    if(j < pasosRuta.length){%>
                    <a href="../VerServiciosServlet?categoria=<%=paso%>&orden=<%=orden%>"><%=paso%></a>&nbsp>&nbsp;
                    <%}
                    else{%>
                    <b><%=paso%></b>
                    <%}%>
                <%}%>
            </div>
                <h3 class = "nombreServicio"><%=informacionServicio[0]%></h3>
            
            <%String[] imagenes = informacionServicio[6].split(";");
                if(imagenes.length > 1){%>
                    <div class ="slides">
                    <%for(int i = 0; i<imagenes.length; i++){
                        int nro = i + 1;
                        String rutaImg = "../servicios/" + informacionServicio[0] + "-" + informacionServicio[2] + "-" + nro + ".jpg";%>
                        <img src = "<%=rutaImg%>" class = "imgServicio" alt = ""/>
                    <%}%>                    
                    </div>
                <%}
                else{
                    String rutaImg = "../servicios/" + informacionServicio[0] + "-" + informacionServicio[2] + "-1.jpg";%>
                    <img class = "unaImagen imgServicio" src = "<%=rutaImg%>" alt = ""/>
                <%}%>
                
            <div class ="descripcion">
                <%=informacionServicio[1]%>
            </div>
            <div class ="precio">
                <label class = "lblPrecio">U$S&nbsp<%=informacionServicio[9]%></label>
                <br><br>
                <label class ="lblFD">Fecha desde:&nbsp;</label>
                <input type="text" class ="txtFD" readonly/>
                <label class ="lblFH">Fecha hasta:&nbsp;</label>
                <input type="text" class ="txtFH" readonly/>                
                <br><br>
                <label class = "lblCantidad">Cantidad:&nbsp;</label>
                <input type ="text" class ="txtCantidad"></input>
                <button class = "btnAgregarCarrito"><span class = "fa fa-cart-plus"></span>&nbsp;&nbsp;Agregar al carrito</button>
                <br>
                <label class = "lblCategorias"><span class = "fa fa-info-circle"></span>&nbsp;&nbsp;<b>Categor&iacute;as:</b></label>
                <%String[] categorias = informacionServicio[7].split(";");
                for(int i = 0; i < categorias.length; i++){%>
                    <div class = "categoria"><%=categorias[i]%></div>
                <%}%>
            </div>
            <table class ="tblDatosServicio">
                <tr>
                    <td><span class = "fa fa-user"></span>&nbsp;&nbsp;<b>Proveedor:&nbsp;</b></td>
                    <td class = "proveedor"><%=informacionServicio[2]%></td>
                </tr>
                <tr>
                    <td><span class = "fa fa-industry"></span>&nbsp;&nbsp;<b>Empresa:&nbsp;</b></td>
                    <td class = "empresa"><%=informacionServicio[3]%></td>
                </tr>
                <tr>
                    <td><span class = "fa fa-street-view"></span>&nbsp;&nbsp;<b>Or&iacute;gen:&nbsp;</b></td>
                    <td><%=informacionServicio[4]%></td>
                </tr>
                <tr>
                    <td><span class = "fa fa-map-marker"></span>&nbsp;&nbsp;<b>Destino:&nbsp;</b></td>
                    <td><%=informacionServicio[5]%></td>
                </tr>
            </table>
            <div id="mensajes" class="alert alert-danger">
                <strong class = "titulo">Error</strong><label class = "informacion">Info sobre el error</label>
            </div>            
        </div>
        <div class = "visor">
			
	</div>
	<div class = "cerrar">
            <span class = "fa fa-times-circle-o"></span>
	</div>
	<div class = "divImagen">
            <img src = "" class = "imagen"/>
	</div>
    </body>
</html>
