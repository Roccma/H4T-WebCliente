/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($){
   $('.Actualizar').on('click', function(){
       location.href="verCarrito.jsp";
   });
   
   $('.Comprar').on('click', function(){
       if($('.valorSubtotal').text() == "0"){
           $('#errorCarrito').css("display", "block");
           $('.informacionErrorCarrito').text("No hay productos en el carrito actualmente");
           setTimeout(function(){
                $("#errorCarrito").fadeOut("fast");
                },3500);
       }
       else{
           $.ajax({
                url : '../AgregarReservaServlet',
                type : 'POST',
                data : {'subtotal' : $('.valorSubtotal').text()},
                success : function(response){
                    var nro = response.trim();
                    var i = 0;
                    $('.datosProducto').each(function(){
                        var tipo = "#Tipo-" + i;
                        var nombreProducto = "#nombreProducto-" + i;
                        var nickProveedor = "#Proveedor-" + i;
                        var fechaInicio = "#FechaInicio-" + i;
                        var fechaFin = "#FechaFin-" + i;
                        var cantidad = "#cant-" + i;
                        var totalLinea = "#valorTotal-" +i;
                        $.ajax({
                             url : '../ProductoDeReservaServlet',
                             type : 'POST',
                             data : {'nroReserva' : nro,
                                     'tipo' : $(tipo).text(),
                                     'nombreProducto' : $(nombreProducto).text(),
                                     'nickProveedor' : $(nickProveedor).text(),
                                     'fechaInicio' : $(fechaInicio).text(),
                                     'fechaFin' : $(fechaFin).text(),
                                     'cantidad' : $(cantidad).text(),
                                     'totalLinea' : $(totalLinea).text()},
                             success : function(resp){
                             }
                         });
                         i++;
                    });
                    $.ajax({
                        url : '../VaciarCarritoServlet',
                        type : 'POST',
                        success : function(resp){
                            //alert(resp);
                            $('.numeroReservaExito').text(nro);
                            $('.fondoNegroCarrito').fadeIn(300, function(){
                                $('.exito').fadeIn(300);
                                $('.cerrarExito').fadeIn(300);
                            })
                        }
                    });
                }
            });
            
       }                   
   });
   
   $('.cerrarExito').on('click', function(){
       location.href = "verCarrito.jsp";
   });
   
   $('.eliminarProducto').on('click', function(){
       var eliminar = $(this).attr('id');
       var datos = eliminar.split("-");
       $.ajax({
          url : '../EliminarProductoDelCarrito',
          type : 'POST',
          data : {'elemento' : datos[1]},
          success : function(response){
              location.href = "verCarrito.jsp";
          },
          error : function(x, y, z){
          }
       });
   });
});
