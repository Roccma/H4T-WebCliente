<%-- 
    Document   : verInformacionServicios
    Created on : 02-oct-2016, 22:38:50
    Author     : usuario
--%>
<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../jquery-ui.min.js"></script>
        <title>Help4Traveling - Servicios</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <script src="../js/jsVerServicios.js"></script>
        <link rel="stylesheet" href="../css/cssVerServicios.css"/>
        
        
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class="contenedorServicios">
            <div class ="divCategorias">
                <b>Categor&iacute;as disponibles</b>
                <br>
                <ul class = "listaCategorias">
                    
               </ul>
            </div>
            
            <%  response.setContentType("text/html;charset=UTF-8");        
                response.setCharacterEncoding("UTF-8");   
                String[] datosServicios = (String[]) session.getAttribute("servicios");
                String categoria = request.getParameter("categoria");
                String orden = request.getParameter("orden");
                
                session.setAttribute("categoriaSeleccionada",categoria);
                categoria = categoria.replace("Ã¡", "&aacute;");
                categoria = categoria.replace("Ã©", "&eacute;");
                categoria = categoria.replace("vehÃ­culo", "veh&iacute;culo");
                categoria = categoria.replace("Ã³", "&oacute;");
                categoria = categoria.replace("Ãº", "&uacute;");
                categoria = categoria.replace("Ã±", "&ntilde;");
                
            %>
                
                <div class = "divInfoCategoria">
                    <b class = "lblCategoria"><%=categoria%></b>&nbsp|&nbsp<b><%=datosServicios.length%></b>&nbsp servicios encontrados
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
            
                <%if(datosServicios.length > 0){
                    for (int i = 0; i < datosServicios.length; i++){ 
                    String datos = datosServicios[i];            
                    String[] informacionServicios = datos.split(";");
                    if(!informacionServicios[0].equals("hija")){                
                        if(!informacionServicios[0].equals("padre")){
                            String rutaImagen = "../servicios/" + informacionServicios[1] + "-" + informacionServicios[2] + "-1.jpg";%>

                            <div class ="divInformacionServicio">
                                <img src = "<%=rutaImagen%>" class = "imgServicio"></img>
                                &nbsp;&nbsp;
                                <div class = "nombreServicio">
                                    <%String informacionServicioRuta = "../InformacionServicioServlet?nombre=" + informacionServicios[1] + "&nickProveedor=" + informacionServicios[2];%>
                                    <a href ="<%=informacionServicioRuta%>"><%=informacionServicios[1]%></a>
                                    <br>
                                    <label class = "proveedorServicio"><%=informacionServicios[2]%></label>                    
                                </div>
                                <div class ="divPrecio">
                                <label class = "lblPrecio">U$S <%=informacionServicios[3]%></label>
                                </div>              
                            
                            </div>
                        <%}
                    }
                }
            }%>
            
            
            <!--div class ="divInformacionServicio">
                hola
            </div>
            <div class ="divInformacionServicio">
                hola
            </div>
            <div class ="divInformacionServicio">
                hola
            </div>
            <div class ="divInformacionServicio">
                hola
            </div>
            <div class ="divInformacionServicio">
                hola
            </div>
            <div class ="divInformacionServicio">
                hola
            </div-->
        </div>
    </body>
</html>
