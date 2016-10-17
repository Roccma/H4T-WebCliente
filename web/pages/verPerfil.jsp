<%-- 
    Document   : verPerfil
    Created on : 05-oct-2016, 11:36:55
    Author     : usuario
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help4Traveling - Perfil</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../jquery-ui.min.js"></script>
        <script src="../js/jsVerPerfil.js"></script>
        <link rel="stylesheet" href="../css/cssVerPerfil.css"/>
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class="contenedorPerfil">
            <div class ="divPestanias">
                <ul class ="pestanias">
                    <li class ="seleccionada" id = "pes1"><a href="#tab-1"><span class = "fa fa-info-circle"></span>&nbsp;&nbsp;Informaci&oacute;n</a></li>
                    <li id = "pes2"><a href="#tab-2"><span class = "fa fa-calendar"></span>&nbsp;&nbsp;Reservas</a></li>
                </ul>
                <%String clienteLogueado = (String) session.getAttribute("nick");
                String[] datosCliente = (String[]) session.getAttribute("datosCliente");%>
                <div class ="linea"></div>
                <div class ="contenedorPestania" id ="tab-1">
                    <div class ="informacionImgPerfil">
                        <div class ="divImagenPerfil">
                            <img src ="../perfiles/<%=clienteLogueado%>.jpg" class ="imagenPerfil"/>
                            <label class ="nickname"><%=clienteLogueado%></label>
                        </div>                        
                    </div>
                    <div class ="informacionGeneral">
                        <table class ="tblInfoPerfil">
                            <tr>
                                <td><span class ="fa fa-user">&nbsp;&nbsp;</span><b>Nombre:</b></td>
                                <td><%=datosCliente[0]%></td>                                   
                            </tr>
                            <tr>
                                <td><span class ="fa fa-user"></span>&nbsp;&nbsp;<b>Apellido:</b></td>
                                <td><%=datosCliente[1]%></td>                                   
                            </tr>
                            <tr>
                                <td><span class ="fa fa-at"></span>&nbsp;&nbsp;<b>Correo electr&oacute;nico:</b></td>
                                <td><%=datosCliente[2]%></td>                                   
                            </tr>
                            <tr>
                                <td><span class ="fa fa-birthday-cake"></span>&nbsp;&nbsp;<b>Fecha de nacimiento:</b></td>
                                <td><%=datosCliente[3]%></td>                                   
                            </tr>
                        </table>
                    </div>
                    <div class="informacionReservas"> 
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
                                    <td class="cCantidad">Cant.</td>
                                    <td class="cPrecioUnit">P.U.</td>
                                    <td class="cTotalLinea">T.L.</td>
                                    <td class="cFechaIni">Fecha Inicio</td>
                                    <td class="cFechaFin">Fecha Fin </td>
                                    <td class="cTipo">Tipo</td>

                                </tr>
                            </thead>
                            <tbody class="cuerpoProductosReserva">

                            </tbody>
                        </table>
                    </div>
                </div>
                
            </div>
            
            <div id="mensajes" class="alert alert-success">
                <strong class = "tituloMenssaje">¡ÉXITO!</strong><label class = "informacion">&nbspLa reserva ha sido cancelada de manera correcta</label>
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
