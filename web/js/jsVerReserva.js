/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($){
    $('.tablaProductosReserva').hide();
    $.ajax({
        url:"../VerReservaServlet",
        type:"POST",
        data:{"operacion":"tablaReservas"},
        success:function(response){
            
           $('.cuerpoReservas').html(response);
            
        }
    });
    
    $('body').on('click', '.ver', function(){
        $.ajax({
            url:"../VerReservaServlet",
            type:"POST",
            data:{"operacion":"tablaProductos",
                    "numeroReserva":$(this).attr("id")},
            success:function(response){   
                $('.tablaProductosReserva').show();
                $('.cuerpoProductosReserva').html(response);   
            }
        });
    });
    
    $('body').on('click', '.cancelar', function(){
        $.ajax({
            url : '../CancelarReservaServlet',
            type : 'POST',
            data : {'numero' : $(this).attr('id')},
            success : function(response){
                $('#mensajes').css('display', 'block');
                setTimeout(function(){
                        $("#mensajes").fadeOut("fast");
                        },3500);
                $('.tablaProductosReserva').hide();
                $.ajax({
                    url:"../VerReservaServlet",
                    type:"POST",
                    data:{"operacion":"tablaReservas"},
                    success:function(response){
                       $('.cuerpoReservas').html(response);
                    }
                });
            }
        });
    });
        
    
});
