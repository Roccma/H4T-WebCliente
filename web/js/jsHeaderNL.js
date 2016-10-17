/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(
    function () {
    var $j = jQuery.noConflict();
    $("#fechaNacimiento" ).datepicker({
      changeMonth: true,
      changeYear: true,
      yearRange: "1940:2016"
    });
  }
  
);
jQuery(document).ready(function($) {
    $('#btnBuscarServProm').on('click', function(){
               location.href = "../BusquedaServlet?buscar=" + $('#buscarCampo').val() + "&orden=p";
           });
           
           $('#buscarCampo').on('keypress', function(e){
               if(e.which === 13){
                location.href = "../BusquedaServlet?buscar=" + $('#buscarCampo').val() + "&orden=p";
            }
           });
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
        $('#nickname').focus();
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
    $("#enviarFormNuevoUsuario").click(function(event){
        var error = false;
        event.preventDefault();
        //ert("Hola 1");
        
        function getBrowser(){
            
            var navegador = navigator.userAgent;
            if (navegador.indexOf('Trident') !=-1) {
                return "IE";
            }
            else if (navegador.indexOf('MSIE') !=-1) {
                return "IE";
            }
            else if (navegador.indexOf('Firefox') !=-1) {
                return "Firefox";
            } else if (navegador.indexOf('OPR') !=-1) {
                return "Opera";
            } else if (navegador.indexOf('Safari') !=-1) {
                return "Safari";
            }
            else if (navegador.indexOf('Chrome') !=-1) {
                return "Chrome";
            } else {
                return "N/D";
            }
        }
        
        $("#formSignup *").filter(":input").each(function() {
            //alert("Hola 2");
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
            $("#alertaRegistro").removeClass();
            $("#alertaRegistro").addClass('alert');
            $("#alertaRegistro").addClass('alert-danger');
            $("#alertaRegistro").html("<strong>ERROR: </strong>La contraseña ingresada no coincide con su confirmación");
            $("#alertaRegistro").css('opacity', '1');
            setTimeout(function(){
                $("#alertaRegistro").css('opacity', '0');
            },3500);
        }

        if( $("#emailI").hasClass('glyphicon-remove')){
            error = true;
            $("#emailI").parent().addClass('has-error');
            $("#alertaRegistro").removeClass();
            $("#alertaRegistro").addClass('alert');
            $("#alertaRegistro").addClass('alert-danger');
            $("#alertaRegistro").html("<strong>ERROR: </strong>El correo ya existe o tiene un formato inválido. Por favor, ingresarlo uno nuevo");
            $("#alertaRegistro").css('opacity', '1');
            setTimeout(function(){
                $("#alertaRegistro").css('opacity', '0');
            },3500);
        }
        
        if($("#nicknameI").hasClass('glyphicon-remove')){
            error = true;
            $("#alertaRegistro").removeClass();
            $("#alertaRegistro").addClass('alert');
            $("#alertaRegistro").addClass('alert-danger');
            $("#alertaRegistro").html("<strong>ERROR: </strong>Este nickname ya se encuentra en uso o es inválido. Por favor, ingresar uno nuevo");
            $("#alertaRegistro").css('opacity', '1');
            setTimeout(function(){
                $("#alertaRegistro").css('opacity', '0');
            },3500);
        }
        
        if($('#nickname').val().length < 1 || $('#nombre').val().length < 1 || $('#apellido').val().length < 1 || $('#email').val().length < 1 || $('#fechaNacimiento').val().length < 1 || $('#password1').val().length < 1 || $('#password2').val().length < 1){
            error = true;
            $("#alertaRegistro").removeClass();
            $("#alertaRegistro").addClass('alert');
            $("#alertaRegistro").addClass('alert-danger');
            $("#alertaRegistro").html("<strong>ERROR: </strong>Campos obligatorios");
            $("#alertaRegistro").css('opacity', '1');
            setTimeout(function(){
                $("#alertaRegistro").css('opacity', '0');
            },3500);
        }
        
        if(error == false){
            var data = $("#formSignup").serialize();
            console.log(data);
            $.ajax({
                url: '../CrearUsuarioServlet',
                type: 'POST',
                dataType: 'JSON',
                data: data
            })
            .done(function(response){
                if(!!response.agregado==true){
                    /*var nickname = $("#nickname").val();
                    window.location.replace("subirFoto.jsp?nick="+nickname);*/
                    location.href = "../CopiarImagenesServlet?pagina=imagen";
                    //location.href = "subirFoto.jsp";
                }
                else{
                    $("#alertaRegistro").removeClass();
                    $("#alertaRegistro").addClass('alert');
                    $("#alertaRegistro").addClass('alert-danger');
                    $("#alertaRegistro").html("<strong>ERROR: </strong>Ha ocurrido un error al crear su usuario. Por favor, vuelva a intentar en 5 minutos");
                    $("#alertaRegistro").animate({opacity: 1}, 1000);
                    setTimeout(function(){
                        $('#alertaRegistro').animate({opacity: 0}, 1000);
                    },3500);
                }
            });
        }
    });
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
                $("#alertaLogin").removeClass();
                    $("#alertaLogin").addClass('alert');
                    $("#alertaLogin").addClass('alert-danger');
                    $("#alertaLogin").html("<strong>ERROR: </strong> Campos obligatorios");
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
                    $("#alertaLogin").removeClass();
                    $("#alertaLogin").addClass('alert');
                    $("#alertaLogin").addClass('alert-danger');
                    $("#alertaLogin").html("<strong>ERROR: </strong> Los datos ingresados son incorrectos");
                }
            });
        }
        else
            return 0;
    });       
});

jQuery(document).ready(function($){
    jQuery("#email").on("keyup", function(event){
        if($(this).val() == "" ) {
            jQuery("#emailI").removeClass('glyphicon-ok');
            jQuery("#emailI").removeClass('glyphicon-remove');
            jQuery("#emailI").addClass('glyphicon-envelope');
        }
        if($(this).val().length>=1){
            var nickname = $(this).val();
            $.ajax({
                url: '../emailCheckServlet',
                type: 'GET',
                dataType: 'json',
                data: "email="+nickname,
            })
            .done(function(response) {
                if(!response.existe){
                    jQuery("#emailI").removeClass('glyphicon-envelope');
                    jQuery("#emailI").removeClass('glyphicon-remove');
                    jQuery("#emailI").addClass('glyphicon-ok');
                }
                else{
                    jQuery("#emailI").removeClass('glyphicon-envelope');
                    jQuery("#emailI").removeClass('glyphicon-ok');
                    jQuery("#emailI").addClass('glyphicon-remove');
                }
            })
            .fail(function(response) {
                
            });
            
        }   
    });
});
jQuery(document).ready(function($){
    jQuery("#nickname").on("keyup", function(event){
        if($(this).val()==""){
            jQuery("#nicknameI").removeClass('glyphicon-ok');
            jQuery("#nicknameI").removeClass('glyphicon-remove');
            jQuery("#nicknameI").addClass('glyphicon-question-sign');
        }
        if($(this).val().length <= 3 && $(this).val().length >= 1){
            jQuery("#nicknameI").removeClass('glyphicon-question-sign');
            jQuery("#nicknameI").removeClass('glyphicon-ok');
            jQuery("#nicknameI").addClass('glyphicon-remove');
        }
        if($(this).val().length >= 4 ){
            var nickname = $(this).val();
            $.ajax({
                url: '../userCheckServlet',
                type: 'GET',
                dataType: 'json',
                data: "nickname="+nickname,
            })
            .done(function(response) {
                if(!response.existe){
                    jQuery("#nicknameI").removeClass('glyphicon-question-sign');
                    jQuery("#nicknameI").removeClass('glyphicon-remove');
                    jQuery("#nicknameI").addClass('glyphicon-ok');
                }
                else{
                    jQuery("#nicknameI").removeClass('glyphicon-question-sign');
                    jQuery("#nicknameI").removeClass('glyphicon-ok');
                    jQuery("#nicknameI").addClass('glyphicon-remove');
                }
            })
            .fail(function(response) {

            });
            
        }   
    });
});

