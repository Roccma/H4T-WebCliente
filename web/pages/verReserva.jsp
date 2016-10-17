<%-- 
    Document   : verReserva
    Created on : Sep 20, 2016, 3:17:59 PM
    Author     : chaos
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Help4Traveling - Reservas</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../js/jsVerReserva.js"></script>
        <link rel="stylesheet" href="../css/cssVerReserva.css"/>
    </head>
    <body>
            <%HttpSession session = request.getSession();
            if(session.getAttribute("nick") == null){%>
                <jsp:include page="headerNL.jsp" />
            <%}else{%>
                <jsp:include page="headerL.jsp" />
            <%}%>
            <jsp:include page="footer.jsp" />
            <div class="contenedorReserva"> 
                <table class="tablaRes">
                    <thead>
                        <tr class="titulo"  > <td colspan="6">Reservas</td></tr>
                        <tr class="columna"> 
                            <td class='cNumero'>N&uacute;mero</td>
                            <td class='cPrecio'>Precio</td>
                            <td class='cFecha'>Fecha</td>
                            <td class='cEstado'>Estado</td>
                            <td class='cVer'>Ver</td>
                            <td class='cCancelar'>Cancelar</td>
                        </tr>
                    </thead>
                    <tbody class="cuerpoReservas" id="cuerpoReservas">
                             
                    </tbody>
                </table>
                
                <table class="tablaProductosReserva">
                    <thead>
                        <tr class="tituloProd"> <td colspan="9">Productos de la reserva</tr>
                        <tr class="columna">
                            <td class="cNombre">Nombre </td>
                            <td class="cProveedor">Proveedor </td>
                            <td class="cEmpresa">Empresa</td>
                            <td class="cCantidad">Cantidad</td>
                            <td class="cPrecioUnit">P. unitario </td>
                            <td class="cTotalLinea">Total linea </td>
                            <td class="cFechaIni">Fecha Inicio</td>
                            <td class="cFechaFin">Fecha Fin </td>
                            <td class="cTipo">Tipo</td>
                            
                        </tr>
                    </thead>
                    <tbody class="cuerpoProductosReserva">
                        
                    </tbody>
                </table>
            </div>
            <div id="mensajes" class="alert alert-success">
                <strong class = "tituloMenssaje">¡ÉXITO!</strong><label class = "informacion">&nbspLa reserva ha sido cancelada de manera correcta</label>
            </div>
            
    </body>
</html>
