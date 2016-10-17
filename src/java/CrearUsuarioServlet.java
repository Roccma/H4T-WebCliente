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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
/**
 *
 * @author kapo_
 */
@WebServlet(urlPatterns = {"/CrearUsuarioServlet"})
public class CrearUsuarioServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
           
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
        processRequest(request, response);
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
            HttpSession session = request.getSession();
            String nickname = request.getParameter("nickname");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String password = request.getParameter("password1");
            String fecha = request.getParameter("fechaNacimiento");
            
            byte[] bytesEncoded = Base64.getEncoder().encode(password .getBytes());
                
            String EncodedPassword = new String(bytesEncoded);
            
            String [] fechaPartida = new String[3];
            fechaPartida = fecha.split("/");
            LocalDate fechaNac = LocalDate.of(Integer.parseInt(fechaPartida[2]),Integer.parseInt(fechaPartida[0]) ,Integer.parseInt(fechaPartida[1]));
            ControladorClientes iccli = new ControladorClientes();
            String json;
            if(!iccli.correoValido(email))
                json = "{\"agregado\":\"errorCorreo\"}";
            else{
                try {    
                    iccli.agregarCliente(nickname, nombre, apellido, email, fechaNac, "src/Logica/perfiles/perfil.PNG", EncodedPassword);
                    session.setAttribute("nick", nickname);
                    session.setAttribute("carrito", new String[100]);
                    json = "{\"agregado\":true}";
                } catch (SQLException ex) {
                    json = "{\"agregado\":false}";
                } catch (ClassNotFoundException ex) {
                    json = "{\"agregado\":false}";
                }
            }
            
            response.getWriter().println(json);
        
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
