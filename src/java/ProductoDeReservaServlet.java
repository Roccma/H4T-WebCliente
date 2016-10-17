/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logica.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
public class ProductoDeReservaServlet extends HttpServlet {

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
        IControladorClientes iccli = new ControladorClientes();
        String nroReserva = (String) request.getParameter("nroReserva");
        //System.out.println("Código: " + nroReserva);
        String tipo = (String) request.getParameter("tipo");
        String nombreProducto = (String) request.getParameter("nombreProducto");
        String nickProveedor = (String) request.getParameter("nickProveedor");
        String fechaInicio = (String) request.getParameter("fechaInicio");
        String fechaFin = (String) request.getParameter("fechaFin");
        String cantidad = (String) request.getParameter("cantidad");
        String totalLinea = (String) request.getParameter("totalLinea");
        if(nombreProducto.equals("SudamÃ©rica-Casas")){
            nombreProducto = "Sudamérica-Casas";
        }    
        /*System.out.println("Tipo: " + tipo);
        System.out.println("1: " + nombreProducto);
        System.out.println("2: " + nickProveedor);
        System.out.println("Nro de reserva: " + nroReserva);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Total línea: " + totalLinea);*/
        String respuesta = "";
        try{
            iccli.datosAsociadosReservaWeb(Integer.parseInt(nroReserva), tipo.trim(), nombreProducto, nickProveedor, fechaInicio, fechaFin, Integer.parseInt(cantidad), Integer.parseInt(totalLinea));
            respuesta = "si";
        } catch (SQLException ex) {
            respuesta = ex.getMessage();
        } catch (ClassNotFoundException ex) {
            respuesta = ex.getMessage();
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println(respuesta);
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
            Logger.getLogger(ProductoDeReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDeReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            ex.getMessage();
        } catch (ClassNotFoundException ex) {
            ex.getMessage();
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
