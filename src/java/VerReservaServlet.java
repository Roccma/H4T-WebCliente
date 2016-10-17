/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Logica.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chaos
 */
@WebServlet(urlPatterns = {"/VerReservaServlet"})
public class VerReservaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        //session.setAttribute("nick", "eWatson");
        String browser = "";
        if(request.getHeader("USER-AGENT").indexOf("MSIE") > -1)
            browser = "IE";
        else if(request.getHeader("USER-AGENT").indexOf("Trident") > -1)
            browser = "IE";
        else if(request.getHeader("USER-AGENT").indexOf("OPR") > -1)
            browser = "Opera";
        else if(request.getHeader("USER-AGENT").indexOf("Firefox") > -1)
            browser = "Firefox";
        else if(request.getHeader("USER-AGENT").indexOf("Chrome") > -1)
            browser = "Chrome";        
        else if(request.getHeader("USER-AGENT").indexOf("Safari") > -1)
            browser = "Safari";
        else 
            browser = "N/D";
        IControladorClientes iccli = new ControladorClientes();
        IControladorLogs iclog = new ControladorLogs();
        //String browser = (String) request.getHeader("USER-AGENT");
        String resultado= "";
        if(request.getParameter("operacion").equals("tablaReservas")){
            ArrayList<DataReserva> listaReserva = iccli.reservasCliente(session.getAttribute("nick").toString());
        
            for(int i=0;i<listaReserva.size();i++){
                DataReserva datosReservaSeleccionados = iccli.getReserva(String.valueOf(listaReserva.get(i).getNumero()));
                String fecha = datosReservaSeleccionados.getFecha().toString();
                String[] datosFecha = fecha.split("-");
                resultado = resultado + "<tr><td class = 'cNumero'>" + listaReserva.get(i).getNumero() + "</td>";
                resultado = resultado + "<td class = 'cPrecio'>U$S " + datosReservaSeleccionados.getPrecio() + "</td>";
                resultado = resultado + "<td class = 'cFecha'>" + datosFecha[2]+"/"+datosFecha[1]+"/"+datosFecha[0] + "</td>";
                resultado = resultado + "<td class = 'cEstado'>" + datosReservaSeleccionados.getEstado() + "</td>";
                resultado = resultado + "<td class = 'cVer'><span class = 'fa fa-share-square ver' id = '" + listaReserva.get(i).getNumero() + "'></span></td>";
                resultado = resultado + "<td class = 'cCancelar'>";
                if(datosReservaSeleccionados.getEstado() == Estado.REGISTRADA)
                    resultado = resultado + "<span class = 'fa fa-times-circle cancelar' id = '" + listaReserva.get(i).getNumero() + "'></span>";
                /*else{
                    resultado = resultado + browser;
                }*/
                resultado = resultado + "</td>";
                resultado = resultado + "</tr>";
                
            }
            //resultado = "si";
        }
        else{
            IControladorProveedores icprov = new ControladorProveedores();
            String numeroReserva=request.getParameter("numeroReserva");
            ArrayList<DataCantidadReservasPromociones> listProm=iccli.getReservasPromo(numeroReserva);
            ArrayList<DataCantidadReservasServicios> listServ=iccli.getReservasServ(numeroReserva);
            for(int i=0;i<listServ.size();i++){
                resultado = resultado + "<tr><td class = 'cNombre' >" + listServ.get(i).getServicio()+ "</td>";
                resultado = resultado + "<td class = 'cProveedor' >" + listServ.get(i).getProveedor()+ "</td>";
                resultado = resultado + "<td class = 'cEmpresa' >" + icprov.getNombreEmpresa(listServ.get(i).getProveedor()).getNombreEmpresa()+ "</td>";
                resultado = resultado + "<td class = 'cCantidad' >" + listServ.get(i).getCantidad()+ "</td>";
                resultado = resultado + "<td class = 'cPrecioUnit'>U$S " + String.valueOf(listServ.get(i).getTotalLinea()/listServ.get(i).getCantidad())+ "</td>";
                resultado = resultado + "<td class = 'cTotalLinea'>U$S " +listServ.get(i).getTotalLinea()+ "</td>";
                resultado = resultado + "<td class = 'cFechaIni'> " + listServ.get(i).getFechaInicio().getDayOfMonth() + "/" + listServ.get(i).getFechaInicio().getMonthValue() + "/" + listServ.get(i).getFechaInicio().getYear() + "</td>";
                resultado = resultado + "<td class = 'cFechaFin'> " + listServ.get(i).getFechaFin().getDayOfMonth() + "/" + listServ.get(i).getFechaFin().getMonthValue() + "/" + listServ.get(i).getFechaFin().getYear() + "</td>";
                resultado = resultado + "<td class = 'cTipo' >SERVICIO</td></tr>";
                
            }
            for(int i=0;i<listProm.size();i++){
                resultado = resultado + "<tr><td class = 'cNombre' >" + listProm.get(i).getPromocion()+ "</td>";
                resultado = resultado + "<td class = 'cProveedor' >" + listProm.get(i).getProveedor()+ "</td>";
                resultado = resultado + "<td class = 'cEmpresa' >" + icprov.getNombreEmpresa(listProm.get(i).getProveedor()).getNombreEmpresa()+ "</td>";
                resultado = resultado + "<td class = 'cCantidad' >" + listProm.get(i).getCantidad()+ "</td>";
                resultado = resultado + "<td class = 'cPrecioUnit'>U$S " + listProm.get(i).getTotalLinea()/listProm.get(i).getCantidad()+ "</td>";
                resultado = resultado + "<td class = 'cTotalLinea'>U$S " + listProm.get(i).getTotalLinea()+ "</td>";
                resultado = resultado + "<td class = 'cFechaIni'> " + listProm.get(i).getFechaInicio().getDayOfMonth() + "/" + listProm.get(i).getFechaInicio().getMonthValue() + "/" + listProm.get(i).getFechaInicio().getYear() + "</td>";
                resultado = resultado + "<td class = 'cFechaFin'> " + listProm.get(i).getFechaFin().getDayOfMonth() + "/" + listProm.get(i).getFechaFin().getMonthValue() + "/" + listProm.get(i).getFechaFin().getYear() + "</td>";
                resultado = resultado + "<td class = 'cTipo' >PROMOCIÓN</td></tr>";
             }
            //resultado = String.valueOf(listServ.size() + listProm.size());
        }
        if((Boolean) session.getAttribute("agregarLog") == true){
            iclog.agregarLog(LocalDate.now(), "http://localhost:8080/h4t/pages/verReserva.jsp", browser, System.getProperty("os.name"));
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            //out.println(resultado);
            out.println(resultado);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
