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
public class CopiarImagenesServlet extends HttpServlet {

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
        
        String pagina = request.getParameter("pagina");
        IControladorCategorias iccat = new ControladorCategorias();
        IControladorProveedores icprov = new ControladorProveedores();
        IControladorClientes iccli = new ControladorClientes();
        IControladorLogs iclog = new ControladorLogs();
        HttpSession session = request.getSession();
        
        ArrayList<DataCliente> clientes = iccli.verInfoCliente();
        for(int i = 0; i < clientes.size(); i++){
            //System.out.println(clientes.get(i).getNickname());
            BufferedImage img = iccli.imagenLogueado(clientes.get(i).getNickname());
            String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + clientes.get(i).getNickname() + ".jpg";
            File fichero = new File(homeUsuario);
            ImageIO.write(img, "jpg", fichero);
        }
        ArrayList<DataProveedor> proveedores = icprov.getInfoProveedores();
        
        for(int i = 0; i < proveedores.size(); i++){
            //System.out.println(proveedores.get(i).getNickname());
            ArrayList<DataImagen> imagenes = new ArrayList<DataImagen>();
            imagenes = icprov.getImagenesProv(proveedores.get(i).getNickname());
            if(imagenes.size() == 0){
                BufferedImage img = icprov.copiarImagenesServicio(System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\help4traveling\\Nuevo\\src\\Logica\\perfiles\\perfil.jpg");
                String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + proveedores.get(i).getNickname() + "-1.jpg";
                File fichero = new File(homeUsuario);
                ImageIO.write(img, "jpg", fichero);
            }
            else{
                for(int j = 0; j < imagenes.size(); j++){
                    BufferedImage img = icprov.copiarImagenesServicio(System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\help4traveling\\Nuevo\\" + imagenes.get(j).getPath());
                    int nro = j + 1;
                    String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + proveedores.get(i).getNickname() + "-" + nro + ".jpg";
                    File fichero = new File(homeUsuario);
                    ImageIO.write(img, "jpg", fichero);
                }
            }
        }
        ArrayList<DataServicio> servicios = icprov.getServicios();
        for(int i = 0; i < servicios.size(); i++){
            ArrayList<DataImagen> imagenes = icprov.getImagenes(servicios.get(i).getNombreServicio(), servicios.get(i).getNombreProveedor());
            if(imagenes.size() == 0){                
                BufferedImage img = icprov.copiarImagenesServicio(System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\help4traveling\\Nuevo\\src\\Logica\\imagenesservicios\\noImagen.jpg");
                String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\servicios\\" + servicios.get(i).getNombreServicio() + "-" + servicios.get(i).getNombreProveedor() + "-1.jpg";
                File fichero = new File(homeUsuario);
                ImageIO.write(img, "jpg", fichero);
            }
            else{
                for(int j = 0; j < imagenes.size(); j++){
                    BufferedImage img = icprov.copiarImagenesServicio(System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\help4traveling\\Nuevo\\" + imagenes.get(j).getPath());
                    int nro = j + 1;
                    String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\servicios\\" + servicios.get(i).getNombreServicio() + "-" + servicios.get(i).getNombreProveedor() + "-" + nro + ".jpg";
                    File fichero = new File(homeUsuario);
                    ImageIO.write(img, "jpg", fichero);
                }
            }
            
            
        }
        if(pagina.equals("servicios"))
            response.sendRedirect("VerServiciosServlet?categoria=Alojamiento&orden=p");
        else if(pagina.equals("proveedores")){
            response.sendRedirect("verProveedoresServlet?");
        }
        else if(pagina.equals("promociones")){
            response.sendRedirect("VerPromosServlet?orden=p");
        }
        else if(pagina.equals("perfil")){            
            response.sendRedirect("VerPerfilServlet");
        }
        else if(pagina.equals("imagen")){
            if((Boolean) session.getAttribute("agregarLog") == true){
                iclog.agregarLog(LocalDate.now(), "http://localhost:8080/h4t/pages/subirFoto.jsp", browser, System.getProperty("os.name"));
            }
            response.sendRedirect("pages/subirFoto.jsp");
        }
        else if(pagina.equals("index")){
            if((Boolean) session.getAttribute("agregarLog") == true){
                iclog.agregarLog(LocalDate.now(), "http://localhost:8080/h4t/index.jsp", browser, System.getProperty("os.name"));
            }
            if((Boolean) session.getAttribute("agregarLog") == true){
                iclog.agregarLog(LocalDate.now(), "http://localhost:8080/h4t/pages/index.jsp", browser, System.getProperty("os.name"));
            }
            response.sendRedirect("pages/index.jsp");
        }
        response.setContentType("text/html;charset=UTF-8");
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
            Logger.getLogger(CopiarImagenesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CopiarImagenesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CopiarImagenesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CopiarImagenesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
