<%-- 
    Document   : verInformacionPromocion
    Created on : 05-oct-2016, 14:24:28
    Author     : usuario
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help4Traveling - Promociones</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <link href="../jquery-ui.min.css" type="text/css" rel="stylesheet">
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../jquery-ui.min.js"></script>
        <script src="../js/jquery.slides.js"></script>
        <script src="../js/jquery.numeric.js"></script>
        <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">
        <script src="../js/bootstrap.js"></script>
        <script src="../js/funciones.js"></script>
        <script src="../js/jsVerInformacionPromocion.js"></script>
        <link rel="stylesheet" href="../css/cssVerInformacionPromocion.css"/>
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class="contenedorInformacionPromociones">
            <%String[] datosPromocion = (String[]) session.getAttribute("informacionPromocion");%>
            <h3 class = "nombrePromocion"><%=datosPromocion[0]%></h3>
            <img src ="../perfiles/<%=datosPromocion[1]%>-1.jpg" class ="imgProveedor"/>
            <div class ="datosPromocion">
                <table class ="tblPromo">
                    <tr>
                        <td><span class = "fa fa-user"></span>&nbsp;&nbsp;<b>Proveedor:</b></td>  
                        <td class = "proveedorPromocion"><%=datosPromocion[1]%></td>
                    </tr>
                    <tr>
                        <td><span class = "fa fa-industry"></span>&nbsp;&nbsp;<b>Empresa:</b></td>  
                        <td class = "empresaPromocion"><%=datosPromocion[2]%></td>
                    </tr>
                    <tr>
                        <td><span class = "fa fa-money"></span>&nbsp;&nbsp;<b>Descuento:</b></td>  
                        <td class = "descuento"><%=datosPromocion[3]%>%</td>
                    </tr>
                    <tr>
                        <td colspan ="2"><label class = "precio">U$S&nbsp;<span class="price"><%=datosPromocion[4]%></span></label></td>
                    </tr>
                    <tr>
                        <td colspan = "2">
                            <label class = "lblFD">Fecha desde:&nbsp;</label>
                            <input type ="text" class ="txtFD" readonly/>
                            <label class = "lblFH">Fecha hasta:&nbsp;</label>
                            <input type ="text" class ="txtFH" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan ="2">
                            <label class = "lblCantidad">Cantidad:&nbsp;</label>
                            <input type ="text" class ="txtCantidad"></input>
                            <button class = "btnAgregarCarrito"><span class = "fa fa-cart-plus"></span>&nbsp;&nbsp;Agregar al carrito</button>
                        </td>
                    </tr>
                </table>
            </div>
            <div class ="serviciosPromocion">
                <label class ="lblCategorias"><span class = "fa fa-info-circle"></span>&nbsp;&nbsp;Servicios:&nbsp;</label>
                <%String[] servicios = datosPromocion[5].split(";");
                for(int i = 0; i < servicios.length; i++){%>
                    <div class ="divServicio"><a href = "../InformacionServicioServlet?nombre=<%=servicios[i]%>&nickProveedor=<%=datosPromocion[1]%>" target="_blank"><%=servicios[i]%></a></div>
                <%}%>
            </div>
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
