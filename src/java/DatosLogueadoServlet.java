/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Logica.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class DatosLogueadoServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        IControladorClientes iccli = new ControladorClientes();
        if(session.getAttribute("nick") == null)            
            System.out.println("jok");
        else
            System.out.println((String) session.getAttribute("nick"));
        Logger.getLogger(DatosLogueadoServlet.class.getName()).log(Level.SEVERE, null, "aaa");
        
        
        BufferedImage imgPerfil = iccli.imagenLogueado((String) session.getAttribute("nick"));
        //System.out.println(imgPerfil);
        //DataCliente dc = iccli.seleccionarCliente((String) session.getAttribute("nick"));
        String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + session.getAttribute("nick") + ".jpg";
        File fichero = new File(homeUsuario);
        //BufferedImage imag=ImageIO.read(new ByteArrayInputStream(imgPerfil));
        ImageIO.write(imgPerfil, "jpg", fichero);
        //JOptionPane.showMessageDialog(null, fichero);
        
        //String resultado = session.getAttribute("nick").toString() + ";" + rutaImagen;
        //File archivo = new File("../../Help4Traveling/Nuevo");
        response.setContentType("text;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            //out.println(session.getAttribute("nick") + ";" + System.getProperty("user.home") + "\\Documentos\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + session.getAttribute("nick") + ".jpg");
            out.println((String) session.getAttribute("nick"));
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
            Logger.getLogger(DatosLogueadoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatosLogueadoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DatosLogueadoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatosLogueadoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
