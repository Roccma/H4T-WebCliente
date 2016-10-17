/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logica.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(urlPatterns = {"/IndexServlet"})
public class IndexServlet extends HttpServlet {

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
        
        IControladorPromociones icprom = new ControladorPromociones();
        IControladorProveedores icprov = new ControladorProveedores();
        
        ArrayList<DataPromocion> promociones = icprom.getMaxPromociones();
        String resultado = "";
        for(int i = 0; i < promociones.size(); i++){
            resultado = resultado + promociones.get(i).getNombre() + ";" + promociones.get(i).getProveedor() + ":";
        }
        if(promociones.size() < 4){
            int cant = 4 - promociones.size();
            ArrayList<DataPromocion> segundasPromociones = icprom.getPromociones();
            int cont = 0;
            int encontrados = 0;
            while(cont < segundasPromociones.size() && encontrados < cant){
                if(resultado.indexOf(segundasPromociones.get(cont).getNombre()) == -1){
                    resultado = resultado + segundasPromociones.get(cont).getNombre() + ";" + segundasPromociones.get(cont).getProveedor() + ":";
                    encontrados++;
                }
                cont++;
            }
        }
        
        resultado = resultado + "*";
        
        ArrayList<DataServicio> servicios = icprov.getMaxServicios();
        
        for(int i = 0; i < servicios.size(); i++){
            ArrayList<DataCategoria> categoria = icprov.getCategorias(servicios.get(i).getNombreServicio(), servicios.get(i).getNombreProveedor());
            resultado = resultado + servicios.get(i).getNombreServicio() + ";" + servicios.get(i).getNombreProveedor() + ";" + categoria.get(0) + ":";
        }
        
        if(servicios.size() < 4) {
            int cant = 4 - servicios.size();
            ArrayList<DataServicio> segundosServicios = icprov.getServicios();
            
            int cont = 0;
            int encontrados = 0;
            
            while(cont < segundosServicios.size() && encontrados < cant){
                if(resultado.indexOf(segundosServicios.get(cont).getNombreServicio()) == -1){
                    ArrayList<DataCategoria> categoria = icprov.getCategorias(servicios.get(cont).getNombreServicio(), servicios.get(cont).getNombreProveedor());
                    resultado = resultado + segundosServicios.get(cont).getNombreServicio() + ";" + servicios.get(cont).getNombreProveedor() + ";" + categoria.get(0) + ":";
                    encontrados++;
                }
                cont++;
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println(resultado);
            //out.println("aca");
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
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
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
