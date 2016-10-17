<%-- 
    Document   : subirFoto
    Created on : 09/10/2016, 02:38:16 PM
    Author     : kapo_
--%>

<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
        <link href="../jquery-ui.min.css" type="text/css" rel="stylesheet">
        <script src="../js/jquery-3.1.0.js"></script>
        <script src="../jquery-ui.min.js"></script>
        <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">
        <script src="../js/bootstrap.js"></script>
        <title>Help4Traveling - Registro</title>
        <link rel="shortcut icon" type="image/ico" href="../images/iconoHelp4Traveling.ico" />
        <link href ="../css/cssSubirFoto.css" rel="stylesheet"/>
        <script src="../js/jsSubirFoto.js"></script>
    </head>    
    <body>
        <%HttpSession session = request.getSession();
        if(session.getAttribute("nick") == null){%>
            <jsp:include page="headerNL.jsp" />
        <%}else{%>
            <jsp:include page="headerL.jsp" />
        <%}%>
        <jsp:include page="footer.jsp" />
        <div class ="contenedorImagen">            
            <h2 class = "lblFelicitaciones">¡FELICITACIONES!</h2>
            <form  method="POST" enctype="multipart/form-data" style="margin-top: 150px;position:absolute;" action="../uploadImage" id = "frmImagen">
                <h4>El registro ha finalizado de manera correcta.<br>S&oacute;lo falta indicar (si lo desea) cu&aacute;l ser&aacute; su <b>imagen de perfil</b>:</h4>
                <!--input name="profile" type="file"></input>
                <input type="submit" value="Subit foto"/-->
                
                <img src = "../images/perfil.jpg" alt = "Foto de perfil" title = "Foto de perfil" name = "img" id = "imagenPerfil"/>
		<div class = "selFoto">
                    <span class = "fa fa-file-image-o"></span>&nbsp;Seleccionar imagen
                    <input type = "file" name = "profile" id = "profile" accept = "image/gif, image/jpg, image/jpeg, image/bmp"/>
		</div>
                <input type="button" value="No, gracias" id = "btnNo"/>
                <input type="submit" value="Aceptar" id = "btnAceptar"/>
            </form>
        </div>
    </body>
</html>
