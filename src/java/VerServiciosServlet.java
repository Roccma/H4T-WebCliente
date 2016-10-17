/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logica.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author usuario
 */
public class VerServiciosServlet extends HttpServlet {

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
        /*String datos = request.getParameter("datos");
        if(datos.equals("servicios")){*/
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
            request.setCharacterEncoding("UTF-8");
            String categoria = request.getParameter("categoria");
            String orden = request.getParameter("orden");
            IControladorProveedores icprov = new ControladorProveedores();
            IControladorCategorias iccat = new ControladorCategorias();
            IControladorLogs iclog = new ControladorLogs();
            String cat = categoria;
            if(categoria.equals("AutomÃ³viles"))
                cat = "Automoviles";
            else if(categoria.equals("UbicaciÃ³n"))
                cat = "Ubicacion";
            else if(categoria.equals("MontaÃ±a"))
                cat = "Monta";
            else if(categoria.equals("EconÃ³mico"))
                cat = "Economico";
            else if(categoria.equals("Tipo vehÃ­culo"))
                cat = "Tipo vehiculo";
            else if(categoria.equals("CamiÃ³n"))
                cat = "Camion";
            else if(categoria.equals("MediterrÃ¡neo"))
                cat = "Mediterraneo";
            
            ArrayList<DataServicio> dtservicios = new ArrayList();
            if(orden.equals("p")){
                dtservicios = icprov.getServiciosPorCategoriaOrdenPrecio(cat);
            }
            else{
                dtservicios = icprov.getServiciosPorCategoriaOrdenAlfabeticamente(cat);
            }
            /*ArrayList<DataCategoria> categoriasPadres = iccat.getCategoriasPadres();
            ArrayList categoriasRelacionadas = iccat.getCategoriasRelacionadas();
            int tam = dtservicios.size() + categoriasPadres.size() + categoriasRelacionadas.size();*/
            
            String[] datosServicios = new String[dtservicios.size()];
            int pos = 0;
            for(int i = 0; i < dtservicios.size(); i++){
                ArrayList<String> rutasImagenes = icprov.rutaImagenesServicios(dtservicios.get(i).getNombreServicio(), dtservicios.get(i).getNombreProveedor());
                datosServicios[i] = categoria + ";" + dtservicios.get(i).getNombreServicio() + ";" + dtservicios.get(i).getNombreProveedor() + ";" + dtservicios.get(i).getPrecioServicio() + ";";
                for(int j = 0; j < rutasImagenes.size(); j++){
                    if(j == 0)
                        datosServicios[i] = datosServicios[i] + rutasImagenes.get(j);
                    /*BufferedImage img = icprov.copiarImagenesServicio(rutasImagenes.get(j));
                    int nro = j + 1;
                    String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\servicios\\" + dtservicios.get(i).getNombreServicio() + "-" + dtservicios.get(i).getNombreProveedor() + "-" + nro + ".jpg";
                    File fichero = new File(homeUsuario);
                    ImageIO.write(img, "jpg", fichero);*/
                }
                pos = i;
            }
                
            HttpSession session = request.getSession();
            session.setAttribute("servicios", datosServicios);
            session.setAttribute("orden", orden);
            if((Boolean) session.getAttribute("agregarLog") == true){
                iclog.agregarLog(LocalDate.now(), "http://localhost:8080/h4t/pages/verServicios.jsp?categoria=" + categoria + "&orden=" + orden, browser, System.getProperty("os.name"));
            }
            response.sendRedirect("pages/verServicios.jsp?categoria=" + categoria + "&orden=" + orden);
        /*}
        else{
            IControladorCategorias iccat = new ControladorCategorias();
            ArrayList<DataCategoria> categoriasPadres = iccat.getCategoriasPadres();
            
        }*/
        
        
        response.setContentType("text/html;charset=UTF-8");        
        response.setCharacterEncoding("UTF-8");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerServiciosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerServiciosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerServiciosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerServiciosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
