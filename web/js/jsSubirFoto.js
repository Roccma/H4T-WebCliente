/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($){
   function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#imagenPerfil').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    } 
    
    function validarImg(path){
            var extension = (path.substring(path.lastIndexOf("."))).toLowerCase();
            if(extension === ".jpg" || extension === ".png" || extension === ".jpeg" || extension === ".bmp")
                    return true;
            else
                    return false;
    }
    
    $('#profile').on('change',function(){
            if(validarImg($('#profile').val()) === true){
                    readURL(this);
            }
    });
    
    $('#btnNo').on('click', function(){
        location.href = "../pages/index.jsp";
    });
    
});
