/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($){
    function getHijas(padre){
        $.ajax({
            url : "../CatsServlet",
            type : "POST",
            data : {"tipo" : "hijas"},
            success : function(response){
                var hijas = response;
                var datosHijas = hijas.split(":");
                
                for(var i = 0; i < datosHijas.length; i++){
                    var nivel = datosHijas[i].split(";");
                    if(nivel[0].replace(" ","") == padre){
                        //alert("aca");
                        var id = "#lst" + padre.replace(" ","");
                        
                        $(id).append('<li class = "hija"><a href = "../VerServiciosServlet?categoria=' + nivel[1] + '&orden=' + $('#cmbTipoOrdenamiento').val() + '">' + nivel[1] + '</a><ul id = "lst' + nivel[1].replace(" ","") + '"></ul></li>');
                        getHijas(nivel[1].replace(" ",""));
                    }
                }
            }
        }); 
    };
    
    $.ajax({
       url : "../CatsServlet",
       type : "POST",
       data : {"tipo" : "padres"},
       success : function(response){
           var datos = response.split(";");
           for(var i = 0; i < datos.length-1; i++){
               $('.listaCategorias').append('<li class = "hija"><a href = "../VerServiciosServlet?categoria=' + datos[i] + '&orden=' + $('#cmbTipoOrdenamiento').val() + '">' + datos[i] + '</a><ul id = "lst' + datos[i] + '"></ul></li>');
               getHijas(datos[i]);
           }
       }
    });
    
    $('#cmbTipoOrdenamiento').on('change', function(){
        location.href = "../VerServiciosServlet?categoria=" + $('.lblCategoria').text() + "&orden=" + $('#cmbTipoOrdenamiento').val();
    });
});
