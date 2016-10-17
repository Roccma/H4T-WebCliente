/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($){                
               $.ajax({
                   url : '../DatosLogueadoServlet',
                   type : 'POST',
                   success : function(response){
                       //alert(response);
                       $('.infoLogueado label').text(response);
                       $('.imgPerfil').attr('src', '../perfiles/' + response + '.jpg');
                   },
                   error : function(object, error, resp){
                       //alert("error! " + resp);
                   }
               }); 
               
               var activo = false;
               $('.infoLogueado').on('click', function(){
                   if(!activo){
                       $('.opcionesLogueado').fadeIn();
                       activo = true;
                       $('#btnVerMasPerfil').removeClass('fa fa-angle-down');
                       $('#btnVerMasPerfil').addClass('fa fa-angle-up');
                   }
                   else{
                       $('.opcionesLogueado').fadeOut();
                       activo = false;
                       $('#btnVerMasPerfil').removeClass('fa fa-angle-up');
                       $('#btnVerMasPerfil').addClass('fa fa-angle-down');
                   }               
               });
               
               var activoDatos = false;
               
               $('.opcionDatosSistema').on('click', function(){
                   if(!activoDatos){
                       $('.submenuDatosSistema').fadeIn();
                       activoDatos = true;
                       $('.btnMasInformacion').removeClass('fa fa-angle-down');
                       $('.btnMasInformacion').addClass('fa fa-angle-up');
                   }
                   else{
                       $('.submenuDatosSistema').fadeOut();
                       activoDatos = false;
                       $('.btnMasInformacion').removeClass('fa fa-angle-up');
                       $('.btnMasInformacion').addClass('fa fa-angle-down');
                   }
               });
               
               
               $('#btnBuscarServProm').on('click', function(){
                   location.href = "../BusquedaServlet?buscar=" + $('#buscarCampo').val() + "&orden=p";
                   //alert("buscar");
                   
                   /*buscarCampo*/
               });
               
               $('#buscarCampo').on('keypress', function(e){
                   if(e.which === 13){
                    location.href = "../BusquedaServlet?buscar=" + $('#buscarCampo').val() + "&orden=p";
                }
                   //alert("buscar");
                   
                   /*buscarCampo*/
               });
               
               $('.cargarDatosDePrueba').on('click', function(){
                   $.ajax({
                      url : '../DatosDePruebaServlet',
                      type : 'POST',
                      data : {'tipo' : 'cargar'},
                      beforeSend : function(){
                          $('.fondoNegro').fadeIn(300, function(){
                              $('.mensaje').fadeIn(300);
                          });
                      },
                      success : function(){
                          $('.mensaje').css('background-color', '#B6F1BD');
                          $('.mensaje').css('color','#407946');
                          $('.mensaje').css('border', '5px solid #407946');
                          $('#spinner').removeClass('fa-spinner fa-pulse fa-3x fa-fw');
                          $('#spinner').addClass("fa-check");
                          $('.informacionDatos').text("Datos cargados correctamente");
                          $('.cerrarMensaje').fadeIn(300);
                      }
                   });
               });
               
               $('.eliminarDatosDeSistema').on('click', function(){
                   $.ajax({
                      url : '../DatosDePruebaServlet',
                      type : 'POST',
                      data : {'tipo' : 'eliminar'},
                      beforeSend : function(){
                          $('.fondoNegro').fadeIn(300, function(){
                              $('.mensajeEliminar').fadeIn(300);
                          });
                      },
                      success : function(){
                          $('.mensajeEliminar').css('background-color', '#B6F1BD');
                          $('.mensajeEliminar').css('color','#407946');
                          $('.mensajeEliminar').css('border', '5px solid #407946');
                          $('#spinnerEliminar').removeClass('fa-spinner fa-pulse fa-3x fa-fw');
                          $('#spinnerEliminar').addClass("fa-check");
                          $('.informacionDatos').text("Datos eliminados correctamente");
                          $('.cerrarMensaje').fadeIn(300);
                      }
                   });
               });
               
               $('.cerrarMensaje').on('click', function(){
                   location.href = "../CerrarSesionServlet";
               })
            });
$(document).ready(
    function () {
    var $j = jQuery.noConflict();
    $("#fechaNacimiento" ).datepicker({
      changeMonth: true,
      changeYear: true
    });
  }

);
    jQuery(document).ready(function($) {
        $("#ladoDerechoLogin").on('click', function(event) {
            $("#modalLogin").modal({
                backdrop: "static",
                keyboard: false,
                show: true
            });
        });
    });
    jQuery(document).ready(function($) {
        $("#AbrirModalRegistro").on('click', function(event) {
            $("#modalRegistroUsuario").modal({
                backdrop: "static",
                keyboard: false,
                show: true
            });
        });
    });
    jQuery(document).ready(function($) {
        $("#buscarCampo").on('focus', function(event) {
            $(this).attr('placeholder', 'Servicio o promoción a buscar...');
        });
        $("#buscarCampo").on('focusout', function(event) {
            $(this).attr('placeholder', 'Buscar');
        });
    });
jQuery(document).ready(function($) {
    $("#modalRegistroUsuario").on('hidden.bs.modal', function () {
        $("#formSignup *").filter(":input[type!=submit]").each(function() {
                $(this).parent().removeClass('has-error');
                $(this).parent().removeClass('has-success');            
                $(this).val("");
        });
    });
});
    $("#enviarFormNuevoUsuario").click(function(event){
        var error = false;
        event.preventDefault();
        $("#formSignup *").filter(":input").each(function() {
            $(this).parent().removeClass('has-error');
            if($(this).val()==""){
                $(this).parent().addClass('has-error');
                error = true;
            }
        });
        if($("#password1").val()!=$("#password2").val()){
            error = true;
            $("#password2").parent().addClass('has-error');
            $("#password1").parent().addClass('has-error');
        }
        if(error == false){
            var data = $("#formSignup").serialize();
            console.log(data);
            $.ajax({
                url: '../CrearUsuarioServlet',
                type: 'POST',
                dataType: 'JSON',
                data: data,
            })
            .done(function(response){
                if(!!response.agregado==true){
                    console.log($("#email").val());
                    $.ajax({
                        url: 'sendEmailConfirmation',
                        type: 'GET',
                        data: "emailTo="+$("#email").val()
                    })
                    .done(function(){

                    });
                    window.location.reload();
                }
                else{
                }
            });
        }
    }); 
$(document).ready(function(){
    $("#enviarFormIniciarSesion").on('click', function(event) {
        event.preventDefault();
        var error = false;
        $("#formLogin").find(':input').each(function(index, el) {
            $(this).parent().removeClass("has-error");
            if($(this).val()==""){
                $(this).parent().addClass('has-error');
                error = true;
            }
        });
        if(error == false){
            datos=$("#formLogin").serialize();
            $.ajax({
                url: '../IniciarSesionServlet',
                type: 'POST',
                data: datos,
                dataType: 'JSON',
            })
            .done(function(response) {
                if(!!response.validacion){
                    window.location.reload();
                }
                else{
                    alert("Contraseña incorrecta");
                }
            });
        }
        else
            return 0;
    });       
});
