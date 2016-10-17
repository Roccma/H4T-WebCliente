<%-- 
    Document   : index
    Created on : 09/09/2016, 06:38:37 PM
    Author     : kapo_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel ="stylesheet" type="text/css" href = "../css/cssHeaderNL.css" />
        <script src ="../js/jsHeaderNL.js"></script>
    </head>
    <body>
        <!-- Modal para registrarse -->
        <div id="modalRegistroUsuario" class="modal fade" role="dialog">
          <div class="modal-dialog">
            <!-- Contenido del modal -->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <center><h4 class="modal-title"><b>Nuevo usuario</b></h4></center>
                </div>
                <div class="modal-body">
                    <form id="formSignup" action="" method="GET">
                        <div class="form-group has-feedback" id="nicknameDiv">
                            <label for="nickname">Nickname:</label>
                            <input class="form-control" type="text" id="nickname" name="nickname">
                            <i class="glyphicon glyphicon-question-sign form-control-feedback" id="nicknameI"></i>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="nombre">Nombre:</label>
                            <input class="form-control" type="text" id="nombre" name="nombre">
                            <i class="glyphicon glyphicon-user form-control-feedback"></i>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="apellido">Apellido:</label>
                            <input class="form-control" type="text" id="apellido" name="apellido">
                            <i class="glyphicon glyphicon-user form-control-feedback"></i>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="email">Correo electr&oacute;nico:</label>
                            <input class="form-control" type="email" id="email" name="email">
                            <i id="emailI" class="glyphicon glyphicon-envelope form-control-feedback"></i>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="fechaNacimiento">Fecha de nacimiento:</label>
                            <input type="text" id="fechaNacimiento" name="fechaNacimiento" class="form-control" placeholder="30/08/1990">
                            <i class="glyphicon glyphicon-calendar form-control-feedback"></i> 
                        </div>
                        <div class="form-group has-feedback">
                            <label for="password1">Contrase&ntilde;a:</label>
                            <input class="form-control" type="password" id="password1" name="password1">
                            <i class="glyphicon glyphicon-lock form-control-feedback"></i>
                        </div>
                        <div class="form-group has-feedback">    
                            <label for="password2">Confirmaci&oacute;n de contrase&ntilde;a:</label>
                            <input class="form-control" type="password" id="password2" name="password2">
                            <i class="glyphicon glyphicon-lock form-control-feedback"></i>
                        </div>
                        <center><input type="submit" id="enviarFormNuevoUsuario" value="Aceptar" class="btn btn-primary"></center>
                    </form>
                </div>
                <div class="modal-footer">
                    <div style="opacity:0;" id="alertaRegistro">                    
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                </div>
            </div>

          </div>
        </div>
        <!-- Fin de la ventana modal para registrar usuario -->
        <!-- Inicio de la ventana Modal para Iniciar Sesión. -->

        <!-- Modal -->
        <div id="modalLogin" class="modal fade" role="dialog">
          <div class="modal-dialog personalizado">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <center><h4 class="modal-title"><b>Iniciar sesión</b></h4></center>
              </div>
              <div class="modal-body">
                    <form id="formLogin" action="login.jsp" method="GET">
                        <div class="form-group has-feedback" id="nicknameDiv">
                            <label for="nickname">Nickname:</label>
                            <input class="form-control" type="text" id="nicknameLogin" name="nickname">
                            <i class="glyphicon glyphicon-user form-control-feedback"></i>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="nombre">Contrase&ntilde;a:</label>
                            <input class="form-control" type="password" id="passwordLogin" name="password">
                            <i class="glyphicon glyphicon-eye-open form-control-feedback"></i>
                        </div>
                        <center><input type="submit" id="enviarFormIniciarSesion" value="Aceptar" class="btn btn-primary"></center>
                    </form>
              </div>
              <div class="modal-footer">
                  <div id="alertaLogin" display="opacity:0;" >

                  </div>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
              </div>
            </div>

          </div>
        </div>
        <!-- Fin de la ventana modal para Iniciar Sesión. -->
        <div class="headerContainer">
            <div class="headerCentered">
                <div onclick="location.href='index.jsp'" class="IconPanelLeftSide" id="iconPanel">
                    <img style='height: 100%; width: 100%; object-fit: contain' src='../images/logo.png' class = "logoH4T"/>                     
                </div>

                <div class="searcherMiddle" id="buscadorDiv">
                    <div class="input-group col-md-12">
                    <input type="text" class="form-control" placeholder="Buscar" id="buscarCampo" />
                    <span class="input-group-btn">
                        <button class="btn btn-default" id = "btnBuscarServProm" type="button">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </span>
                    </div>
                </div>
                <div class="infoRegistroRightSide" id="ladoDerechoSignup">
                    <div class="registrarseBotonDiv" id="registrarseBotonDiv">
                        <button  class="registrarseBoton" id="AbrirModalRegistro">
                            <span class = "fa fa-user-plus"></span>&nbsp;&nbsp;Sign-up
                        </button>
                    </div>
                    <div class="loginDiv" id="ladoDerechoLogin">
                        <button  class="loginBoton" id="botonLogin">
                            <span class = "fa fa-unlock"></span>&nbsp;&nbsp;Login
                        </button>
                    </div>
                    <div class="ayudaBotonDiv" id="ayudaBotonDiv">
                        <button  class="ayudaBoton" onclick="location.href = '../AyudaServlet'">
                            <span class = "fa fa-question-circle"></span>&nbsp;&nbsp;Ayuda
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="secondHeader">
            <div class="secondHeaderCenter">
                <ul class = "menuPrincipal">
                    <li class = "submenuPrincipal"><a href = "../CopiarImagenesServlet?pagina=proveedores"><i class="fa fa-truck" aria-hidden="true"> </i>&nbsp;Proveedores</a></li>
                    <!--li class = "submenuPrincipal"><a href = " alogajiento.mostrar("alojamientos"); -->
                    <!--li class = "submenuPrincipal"><a href = "VerServiciosServlet?categoria=Alojamientos"><i class="fa fa-bed" aria-hidden="true"> </i>&nbsp;Servicios</a></li-->
                    <li class = "submenuPrincipal"><a href = "../CopiarImagenesServlet?pagina=servicios"><i class="fa fa-bed" aria-hidden="true"> </i>&nbsp;Servicios</a></li>
                    <li class = "submenuPrincipal"><a href = "../VerPromosServlet?orden=p"><i class="fa fa-shopping-bag" aria-hidden="true"> </i>&nbsp;Promociones</a></li>
                </ul>
            </div>
        </div>

    </body>
</html>