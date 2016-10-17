<%-- 
    Document   : verInformacionProveedor
    Created on : Oct 6, 2016, 7:02:27 PM
    Author     : chaos
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" href ="../fonts.css"/>
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../jquery-ui.min.js"></script>
        <script src="../js/jquery.slides.js"></script> 
        <title>Help4Traveling - Proveedores</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <script src="../js/jsVerInformacionProveedor.js"></script>
        <link href ="../css/cssVerInformacionProveedor.css" rel="stylesheet" />
    </head>
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
          <div class="contenedorInformacionProveedor">
            <%String[] datosProveedor = (String[]) session.getAttribute("informacionProveedor");%>
             <h3 class = "nombreProveedor"><%=datosProveedor[2]%></h3>
             <%int tam = Integer.parseInt(datosProveedor[7]);
                if(tam > 1){%>
                    <div class ="slides">
                    <%for(int i = 0; i<tam; i++){
                        int nro = i + 1;
                        String rutaImg = "../perfiles/" + datosProveedor[2] + "-" + nro + ".jpg";%>
                        <img src = "<%=rutaImg%>" class = "img" alt = ""/>
                    <%}%>                    
                    </div>
                <%}
                else{
                    String rutaImg = "../perfiles/" + datosProveedor[2] + "-1.jpg";%>
                    <img class = "imgProveedor img" src = "<%=rutaImg%>" alt = ""/>
                <%}%>
             <!--img src ="perfiles/<%=datosProveedor[2]%>-1.jpg" class ="imgProveedor"/-->
             <div class ="datosProveedor">
                <table class ="tblProveedor">
                    <tr>
                        <td class="titulo"><span class = "fa fa-user"></span>&nbsp;&nbsp;<b>Nombre:</b></td>  
                        <td><%=datosProveedor[0]%></td>
                    </tr>
                    <tr>
                        <td class="titulo"><span class = "fa fa-user"></span>&nbsp;&nbsp;<b>Apellido:</b></td>  
                        <td><%=datosProveedor[1]%></td>
                    </tr>
                  
                    <tr>
                        <td class="titulo"><span class = "fa fa-at"></span>&nbsp;&nbsp;<b>Correo electr&oacute;nico: </b></td>  
                        <td><%=datosProveedor[3]%></td>
                    </tr>
                    <tr>
                        <td class="titulo"><span class = "fa fa-birthday-cake"></span>&nbsp;&nbsp;<b>Fecha de nacimiento:</b></td>  
                        <td><%=datosProveedor[4]%></td>
                    </tr>
                    <tr>
                        <td class="titulo"><span class = "fa fa-industry"></span>&nbsp;&nbsp;<b>Empresa:</b></td>  
                        <td><%=datosProveedor[5]%></td>
                    </tr>
                    <tr>
                        <td class="titulo"><span class = "fa fa-globe"></span>&nbsp;&nbsp;<b>Sitio web:</b></td>  
                        <td><a href="<%=datosProveedor[8]%>" target="_blank"><%=datosProveedor[8]%></a></td>
                    </tr>
                </table>
            </div>
               <div class ="serviciosProveedor">
                <label class ="lblCategorias"><span class = "fa fa-info-circle"></span>&nbsp;&nbsp;Servicios:&nbsp;</label>
                <%String[] servicios = datosProveedor[6].split(";");
                if(servicios[0].length() > 0){
                    for(int i = 0; i < servicios.length; i++){%>
                        <div class ="divServicio"><a target="_blank" href = "../InformacionServicioServlet?nombre=<%=servicios[i]%>&nickProveedor=<%=datosProveedor[2]%>"><%=servicios[i]%></a></div>
                    <%}
                }
                else{%>
                        <label class = "sinServicios">El proveedor no tiene servicios asociados</label>
                <%}%>
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
