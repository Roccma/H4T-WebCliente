/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($){
    $('.txtCantidad').numeric();
    $('.txtCantidad').focus();
	$(".slides").slidesjs({
           play: {
	      active: true,
	        // [boolean] Generate the play and stop buttons.
	        // You cannot use your own buttons. Sorry.
	      effect: "slide",
	        // [string] Can be either "slide" or "fade".
	      interval: 500,
	        // [number] Time spent on each slide in milliseconds.
	      auto: false,
	        // [boolean] Start playing the slideshow on load.
	      swap: true,
	        // [boolean] show/hide stop and play buttons
	      pauseOnHover: false,
	        // [boolean] pause a playing slideshow on hover
	      restartDelay: 2500
	        // [number] restart delay on inactive slideshow
	    }
	});            
                  
});
$(document).ready(                
    function () {            
        
        var fecha = new Date();
        
        var dia = "";
        if(fecha.getDate() < 10){
            dia = "0" + fecha.getDate();
        }
        else{
            dia = fecha.getDate();
        }
        
        var mes = fecha.getMonth() + 1;
        
        if(mes < 10){
            mes = "0" + mes;
        }
        
        var today = dia + "/" + mes + "/" + fecha.getFullYear();
        
        $('.txtFD').val(today);
        $('.txtFH').val(today);
        
        var $j = jQuery.noConflict();
        $(".txtFD" ).datepicker({
          changeMonth: true,
          changeYear: true,
          dateFormat: 'dd/mm/yy'
        });
        
        $(".txtFH" ).datepicker({
          changeMonth: true,
          changeYear: true,
          dateFormat: 'dd/mm/yy'
        });
        
        $('.btnAgregarCarrito').on('click', function(){
            if($('.txtCantidad').val().length == 0){
                $('#mensajes').addClass("alert-danger");
                $('#mensajes').removeClass("alert-success");
                $('.txtCantidad').css('border', '1px solid #CB3434');
                $('#mensajes .titulo').text("ERROR: ");
                $('#mensajes .informacion').text("No se ha indicado la cantidad de servicios a reservar");
                $('#mensajes').css('display', 'block');
                $('.txtCantidad').focus();
                setTimeout(function(){
                    $("#mensajes").fadeOut("fast");
                    },3500);
            }
            else if($('.txtCantidad').val() < 1){
                $('#mensajes').addClass("alert-danger");
                $('#mensajes').removeClass("alert-success");
                $('.txtCantidad').css('border', '1px solid #CB3434');
                $('#mensajes .titulo').text("ERROR: ");
                $('#mensajes .informacion').text("La cantidad de unidades a reservar debe ser mayor o igual a 1");
                $('#mensajes').css('display', 'block');
                $('.txtCantidad').focus();
                setTimeout(function(){
                    $("#mensajes").fadeOut("fast");
                    },3500);
            }
            else if(!fechasCorrectas($('.txtFD').val(), $('.txtFH').val())){
                $('#mensajes').addClass("alert-danger");
                $('#mensajes').removeClass("alert-success");
                $('.txtCantidad').css('border', '1px solid grey');
                $('#mensajes .titulo').text("ERROR: ");
                $('#mensajes .informacion').text("La fecha de inicio debe ser menor o igual a la fecha de fin de la reserva");
                $('#mensajes').css('display', 'block');                        
                setTimeout(function(){
                    $("#mensajes").fadeOut("fast");
                    },3500);
            }
            else if(!fechasCorrectas(today, $('.txtFD').val())){
                $('#mensajes').addClass("alert-danger");
                $('#mensajes').removeClass("alert-success");
                $('.txtCantidad').css('border', '1px solid grey');
                $('#mensajes .titulo').text("ERROR: ");
                $('#mensajes .informacion').text("La fecha de inicio de la reserva debe ser mayor o igual a la fecha de hoy");
                $('#mensajes').css('display', 'block');                        
                setTimeout(function(){
                    $("#mensajes").fadeOut("fast");
                    },3500);
            }
            else{
                var precio = $('.lblPrecio').text();
                $.ajax({
                   url : '../AgregarAlCarritoServlet',
                   type : 'POST',
                   data : {'tipo' : 'servicio',
                        'fechaDesde' : $('.txtFD').val(),
                        'fechaHasta' : $('.txtFH').val(),
                        'cantidad' : $('.txtCantidad').val(),
                        'producto' : $('.nombreServicio').text(),
                        'proveedor' : $('.proveedor').text(),
                        'empresa' : $('.empresa').text(),
                        'precio' : precio.substr(4)},
                    success : function(response){
                        //alert(response);
                        if(response.trim() == "noSesion"){
                            $('#mensajes').addClass("alert-danger");
                            $('#mensajes').removeClass("alert-success");
                            $('.txtCantidad').css('border', '1px solid grey');
                            $('#mensajes .titulo').text("ERROR: ");
                            $('#mensajes .informacion').text("Para ejecutar esta acción, primero debe estar registrado en el sistema e ingresar con su cuenta de usuario");
                            $('#mensajes').css('display', 'block');                                    
                            setTimeout(function(){
                                $("#mensajes").fadeOut("fast");
                                },3500);
                        }
                        else if(response.trim() == "yaAgregado"){
                            $('#mensajes').addClass("alert-danger");
                            $('#mensajes').removeClass("alert-success");
                            $('.txtCantidad').css('border', '1px solid grey');
                            $('#mensajes .titulo').text("ERROR: ");
                            $('#mensajes .informacion').text("El producto ya se encuentra agregado a su carrito de compras");
                            $('#mensajes').css('display', 'block');                                    
                            setTimeout(function(){
                                $("#mensajes").fadeOut("fast");
                                },3500);
                        }
                        else{
                            $('#mensajes').removeClass("alert-danger");
                            $('#mensajes').addClass("alert-success");
                            $('.txtCantidad').css('border', '1px solid grey');
                            $('#mensajes .titulo').text("¡ÉXITO! ");
                            $('#mensajes .informacion').text("Se ha agregado el servicio al carrito de manera correcta");
                            $('#mensajes').css('display', 'block');
                            $('.txtCantidad').val("");
                            $('.txtFD').val(dia + "/" + mes + "/" + fecha.getFullYear());
                            $('.txtFH').val(dia + "/" + mes + "/" + fecha.getFullYear());
                            $('.txtCantidad').focus();                                    
                            setTimeout(function(){
                                $("#mensajes").fadeOut("fast");
                                },3500);
                             
                        }
                    }
                });
            }
        });
        
        $('.imgServicio').on('click', function(){
           $('.imagen').attr('src', $(this).attr('src'));
           $('.visor').fadeIn(300, function(){
               $('.divImagen').fadeIn(300);
               $('.cerrar').fadeIn(300);
           });
        });
        
        $('.cerrar').on('click', function(){
            $('.divImagen').fadeOut(300);
            $('.cerrar').fadeOut(300, function(){
                $('.visor').fadeOut(300);
            });
        });
    }
);
