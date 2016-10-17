<%-- 
    Document   : verCarrito
    Created on : 10-oct-2016, 10:28:38
    Author     : usuario
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help4Traveling - Carrito de compras</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../jquery-ui.min.js"></script>
        <script src="../js/jsVerCarrito.js"></script>
        <link rel="stylesheet" href="../css/cssVerCarrito.css"/>
    </head>
    <body>
       <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class="contenedorCarrito">
            <%String[] datosCarrito = (String[]) session.getAttribute("carrito");
            int longitud = 0;
            while(datosCarrito[longitud] != null){
                longitud++;
            }
            
            int i = 0;
            
            int subtotal = 0;
            
            while(i < longitud){                
                String[] datos = datosCarrito[i].split(";");%>
                <div class ="datosProducto" id = "datosProducto">
                <%if(datos[0].equals("servicio")){%>
                 <img src="../servicios/<%=datos[4]%>-<%=datos[5]%>-1.jpg" class ="imgCarrito"/>
                 <%}
                else{%>
                <img  src="../perfiles/<%=datos[5]%>-1.jpg" class ="imgCarrito"/>
                <%}%>
                    <div class="datosDelCarrito">
                        <div class ="nombreCarrito">
                        <%if(datos[0].equals("servicio")){
                            session.setAttribute("categoriaSeleccionada", datos[8]);%>
                            <a href ="../InformacionServicioServlet?nombre=<%=datos[4]%>&nickProveedor=<%=datos[5]%>" class ="nombreProducto" id ="nombreProducto-<%=i%>" target = "_blank"><%=datos[4]%></a>
                        <%}else{%>
                            <a href ="../InformacionPromocionServlet?nombrePromocion=<%=datos[4]%>&nickProveedor=<%=datos[5]%>" class ="nombreProducto" id ="nombreProducto-<%=i%>" target = "_blank"><%=datos[4]%></a>
                        <%}%>
                            <br>
                            <label class ="Proveedor" id = "Proveedor-<%=i%>"><%=datos[5]%> </label>
                            <br><br>
                            <label class ="Tipo" id = "Tipo-<%=i%>"> <%if(datos[0].equals("servicio")){%>Servicio<%}else{%>Promoci&oacute;n<%}%></label>
                        </div>
                        <div  class="Fechas">                            
                            
                            <label class ="Cantidad" id = "Cantidad-<%=i%>">Cantidad: <b class="cant" id = "cant-<%=i%>"><%=datos[3]%></b> </label>
                            <br>
                            <label class ="PrecioUnitario" id = "PrecioUnitario-<%=i%>">Precio unitario: <b class="pu" id = "pu-<%=i%>">U$S <%=datos[7]%></b> </label>
                            <br><br>
                            <label class ="FechaInicio">Desde el <b  id = "FechaInicio-<%=i%>"><%=datos[1]%></b></label>
                            <label class ="FechaFin">hasta el <b  id = "FechaFin-<%=i%>"><%=datos[2]%></b></label>
                            <br>
                           </div>
                            <div  class="CantidadPrecio"
                            <%int j= Integer.parseInt(datos[3]) * Integer.parseInt(datos[7]);
                            subtotal = subtotal + j;%>
                            <label class ="Total" id = "Total-<%=i%>">U$S&nbsp;<span class = "valorTotal" id ="valorTotal-<%=i%>"><%=j%></span></label>
                            <br>
                             </div>
                            <label class = "lblEliminarProducto"  id ="eliminar-<%=i%>"><span class ="fa fa-times-circle eliminarProducto" id ="eliminar-<%=i%>"></span></label>
                        </div>
                </div>
                <% i++;
                }%>
                <div class = "subtotal">
                    Subtotal: <span class = "mostrarSubtotal">U$S&nbsp;<span class = "valorSubtotal"><%=subtotal%></span></span>
                </div>
                <div class="botones">
                <button class = "Comprar"><span class = "fa fa-money"></span>&nbsp;&nbsp;Comprar</button>
                <button class = "Actualizar"><span class = "fa fa-refresh"></span>&nbsp;&nbsp;Actualizar</button>
                 </div>
                <div id = "errorCarrito" class ="alert alert-danger">
                    <b>ERROR:</b>&nbsp;<span class = "informacionErrorCarrito">No hay productos en el carrito actualmente</span>
                </div>
        </div>
        <div class ="fondoNegroCarrito">
                    
        </div>
        <div class ="exito">
            <b class = "tituloExito">¡&Eacute;XITO!</b><br><br>La nueva reserva se ha realizado de manera correcta. <br>N&uacute;mero de reserva: <b class ="numeroReservaExito">1</b>
        </div>
        <div class = "cerrarExito">
            <span class = "fa fa-times-circle-o"></span>
	</div>
    </body>
</html>
