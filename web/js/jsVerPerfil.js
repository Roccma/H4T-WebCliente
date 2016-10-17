/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($){        
               
  $('.informacionGeneral').show();
  $('.informacionReservas').hide();
  $('.pestanias li').on('click', function(e){
      e.preventDefault();
      $('.pestanias').removeClass('seleccionada');
      var id = $(this).find('a').attr('href');                   
      $(this).addClass("seleccionada");
      if(id == '#tab-1'){
          $('#pes2').removeClass("seleccionada");
          $('.informacionGeneral').show();
          $('.informacionReservas').hide();
      }
      else{
          $('#pes1').removeClass("seleccionada");
          $('.informacionGeneral').hide();
          $('.informacionReservas').show();
      }   
      
  });
  
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
              //alert(response);
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
              
  $('.imagenPerfil').on('click', function(){
      $('.imagen').attr('src', $('.imagenPerfil').attr('src'));
      $('.visor').fadeIn(300, function(){
          $('.divImagen').fadeIn(300);
          $('.cerrar').fadeIn(300);
      });
  });
  
  $('.cerrar').on('click', function(){
      $('.divImagen').fadeOut(300);
      $('.cerrar').fadeOut(300);
      $('.visor').fadeOut(300);
  });
});