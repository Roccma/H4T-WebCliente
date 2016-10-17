/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($){
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
     
    $('.img').on('click', function(){
       $('.imagen').attr('src', $(this).attr('src'));
       $('.visor').fadeIn(300, function(){
           $('.divImagen').fadeIn(300);
           $('.cerrar').fadeIn(300);
       });
    });
    $('.cerrar').on('click', function(){
        //$('.visor').fadeIn(400, function(){
           $('.divImagen').fadeOut(300);
           $('.cerrar').fadeOut(300, function(){
               $('.visor').fadeOut(300);
           });
       //})
    });
});
