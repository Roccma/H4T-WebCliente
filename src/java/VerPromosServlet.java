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
public class VerPromosServlet extends HttpServlet {

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
        String orden = request.getParameter("orden");
        IControladorPromociones icprom = new ControladorPromociones();
        IControladorProveedores icprov = new ControladorProveedores();
        IControladorLogs iclog = new ControladorLogs();
        ArrayList<DataPromocion> promociones = new ArrayList();
        if(orden.equals("p")){
            promociones = icprom.getPromocionesOrdenPrecio();
        }
        else{
            promociones = icprom.getPromocionesOrdenAlfabeticamente();
        }
            
        String[] datosPromociones = new String[promociones.size()];
        //ArrayList<DataImagen> imagenes = icprov.getImagenesProv("moody");
        for(int i = 0; i < promociones.size(); i++){
            datosPromociones[i] = promociones.get(i).getNombre() + ";" + promociones.get(i).getProveedor() + ";" + promociones.get(i).getDescuento() + ";" + promociones.get(i).getPrecio();
            /*ArrayList<DataImagen> imagenes = icprov.getImagenesProv(promociones.get(i).getProveedor());
            if(imagenes.size() == 0){
                BufferedImage bffrimg = icprov.copiarImagenesServicio(System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\help4traveling\\Nuevo\\src\\Logica\\perfiles\\perfil.png");
                String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + promociones.get(i).getProveedor() + "-1.jpg";
                File fichero = new File(homeUsuario);
                ImageIO.write(bffrimg, "jpg", fichero);
            }
            for(int j = 0; j < imagenes.size(); j++){
                BufferedImage bffrimg = icprov.copiarImagenesServicio(System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\help4traveling\\Nuevo\\" + imagenes.get(j).getPath());
                int nro = j + 1;
                String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + promociones.get(i).getProveedor() + "-" + nro + ".jpg";
                File fichero = new File(homeUsuario);
                ImageIO.write(bffrimg, "jpg", fichero);
            }*/
        }
        HttpSession session=request.getSession();
        session.setAttribute("promociones", datosPromociones);
        if((Boolean) session.getAttribute("agregarLog") == true){
            iclog.agregarLog(LocalDate.now(), "http://localhost:8080/h4t/pages/verPromociones.jsp?orden=" + orden, browser, System.getProperty("os.name"));
        }
        response.sendRedirect("pages/verPromociones.jsp?orden=" + orden);
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
            Logger.getLogger(VerPromosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerPromosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerPromosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerPromosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
