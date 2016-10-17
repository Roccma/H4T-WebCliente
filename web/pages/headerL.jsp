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
        <script src="../js/jsHeaderL.js"></script>
        <link rel ="stylesheet" href="../css/cssHeaderL.css"/>
    </head>
    <body>

        <!-- Modal -->
        <div id="modalPerfil" class="modal fade" role="dialog">
          <div class="modal-dialog personalizado">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Iniciar Sesión.</h4>
              </div>
              <div class="modal-body">
                    <form id="formLogin" action="login.jsp" method="GET">
                        <div class="form-group has-feedback" id="nicknameDiv">
                            <label for="nickname">Nickname</label>
                            <input class="form-control" type="text" id="nicknameLogin" name="nickname">
                            <i class="glyphicon glyphicon-user form-control-feedback"></i>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="nombre">Password</label>
                            <input class="form-control" type="password" id="passwordLogin" name="password">
                            <i class="glyphicon glyphicon-eye-open form-control-feedback"></i>
                        </div>
                        <center><input type="submit" id="enviarFormIniciarSesion" value="Iniciar Sesión!" class="btn btn-primary"></center>
                    </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
                        <button class="btn btn-default" id ="btnBuscarServProm" type="button">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </span>
                    </div>
                </div>
                <div class="infoRegistroRightSide" id="ladoDerechoSignup">
                    <ul class ="lstLogueado">
                        <li>
                            <div class = "infoLogueado">
                                <img src = "../images/perfil.png" class = "imgPerfil"></img>
                                <label></label>
                                &nbsp;&nbsp;
                                <span class="fa fa-angle-down" id = "btnVerMasPerfil"></span>
                            </div>
                            <ul class = "opcionesLogueado">
                                <li><a href = "../CopiarImagenesServlet?pagina=perfil"><span class = "	fa fa-user"></span>&nbsp;&nbsp;Ver perfil</a></li>
                                <li><a href = "../CerrarSesionServlet"><span class = "fa fa-unlock-alt"></span>&nbsp;&nbsp;Cerrar sesi&oacute;n</a></li>
                            </ul>
                        </li>


                    </ul>               
                    <div class="ayudaBotonDiv" id="ayudaBotonDiv">
                        <div class="carrito">
                            <a href="../VerCarritoServlet"><span class = "fa fa-shopping-cart"></span></a>
                        </div>
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
                    <li class = "submenuPrincipal"><a href = "../CopiarImagenesServlet?pagina=servicios"><i class="fa fa-bed" aria-hidden="true"> </i>&nbsp;Servicios</a></li>
                    <li class = "submenuPrincipal"><a href = "../CopiarImagenesServlet?pagina=promociones"><i class="fa fa-shopping-bag" aria-hidden="true"> </i>&nbsp;Promociones</a></li>
                    <li class = "submenuPrincipal"><a href = "verReserva.jsp"><i class="fa fa-calendar" aria-hidden="true"> </i>&nbsp;Reservas</a></li>
                    <li class = "submenuPrincipal opcionDatosSistema"><a href = "#"><i class="fa fa-database" aria-hidden="true"></i>&nbsp;Datos del sistema&nbsp;&nbsp;<span class = "fa fa-angle-down btnMasInformacion"></span></a>
                        <ul class ="submenuDatosSistema">
                            <li class = "cargarDatosDePrueba"><a href = "#">Cargar datos de prueba</a></li>
                            <li class = "eliminarDatosDeSistema"><a href = "#">Eliminar datos</a></li>
                        </ul>
                    </li>
                </ul>
            </div>

        </div>
        <div class = "fondoNegro">

        </div>
        <div class ="mensaje">
            <i class="fa fa-spinner fa-pulse fa-3x fa-fw" id = "spinner"></i><label class = "informacionDatos">&nbsp;Cargando datos de prueba</label>
        </div>

        <div class ="mensajeEliminar">
            <i class="fa fa-spinner fa-pulse fa-3x fa-fw" id = "spinnerEliminar"></i><label class = "informacionDatos">&nbsp;Eliminando datos del sistema</label>
        </div>

        <div class = "cerrarMensaje">
                <span class = "fa fa-times-circle-o"></span>
        </div>
    </body>
</html>