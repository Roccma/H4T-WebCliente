/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fechasCorrectas(fecha1, fecha2){
    var datosFecha1 = fecha1.split("/");
    var datosFecha2 = fecha2.split("/");
    
    if(datosFecha1[2] > datosFecha2[2])
        return false;
    else{
        if(datosFecha1[2] < datosFecha2[2])
            return true;
        else{
            if(datosFecha1[1] > datosFecha2[1])
                return false;
            else{
                if(datosFecha1[1] < datosFecha2[1])
                    return true;
                else{
                    if(datosFecha1[0] > datosFecha2[0])
                        return false;
                    else{
                        return true;
                    }
                }
            }
        }
    }
}
    
function esNumerico(texto){
    for(var i = 0; i < texto.lenth; i++){
        alert(texto[i]);
    }
}
