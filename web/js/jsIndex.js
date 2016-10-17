/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($){
  $('#buscarCampo').focus();
  
          //alert("llegue");
  $.ajax({
      url : '../IndexServlet',
      type : 'POST',
      success : function(response){
          //alert(response);
        var promopcionesservicios = response.split("*");
        var promociones = promopcionesservicios[0].split(":");
        var longitud = promociones.length - 1;
        if(longitud > 4)
            longitud = 4;
        for(var i = 0; i < longitud; i++){
            var datos = promociones[i].split(";");
            $('.promociones').append("<div class ='oferta'><img src ='../perfiles/" + datos[1].trim() + "-1.jpg'/><a href ='../InformacionPromocionServlet?nombrePromocion=" + datos[0].trim() + "&nickProveedor=" + datos[1].trim() + "' target = '_blank'>" + datos[0].trim() + " (" + datos[1].trim() +")</a></div>");
        }
        
        var servicios = promopcionesservicios[1].split(":");
        var longitud = servicios.length - 1;
        if(longitud > 4)
            longitud = 4;
        for(var i = 0; i < longitud; i++){
            var datos = servicios[i].split(";");
            $('.servicios').append("<div class ='oferta'><img src ='../servicios/" + datos[0].trim() + "-" + datos[1].trim() + "-1.jpg'/><a href ='../InformacionServicioServlet?nombre=" + datos[0].trim() + "&nickProveedor=" + datos[1].trim() + "&categoriaSeleccionada=" + datos[2].trim() + "' target = '_blank'>" + datos[0] + " (" + datos[1].trim() +")</a></div>");
        }
      }
  });
             
  $(".slides").slidesjs({
    play: {
      active: true,
        // [boolean] Generate the play and stop buttons.
        // You cannot use your own buttons. Sorry.
      effect: "slide",
        // [string] Can be either "slide" or "fade".
      interval: 7000,
        // [number] Time spent on each slide in milliseconds.
      auto: true,
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

