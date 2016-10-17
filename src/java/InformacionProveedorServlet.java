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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chaos
 */
@WebServlet(urlPatterns = {"/InformacionProveedorServlet"})
public class InformacionProveedorServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
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
         String nickProveedor = request.getParameter("nickProveedor");
         IControladorProveedores icprov = new ControladorProveedores();
         IControladorLogs iclog = new ControladorLogs();
         DataProveedor dp = icprov.verInfoProveedor(nickProveedor);
         String[] datosProveedor = new String[9];
         datosProveedor[0]=dp.getNombre();
         datosProveedor[1]=dp.getApellido();
         datosProveedor[2]=dp.getNickname();
          datosProveedor[3]=dp.getEmail();
         datosProveedor[4]=dp.getFechaNac().getDayOfMonth()+"/"+dp.getFechaNac().getMonthValue()+"/"+dp.getFechaNac().getYear();
         datosProveedor[5]=dp.getNombreEmpresa();
         datosProveedor[8]=dp.getLink();
          ArrayList<DataImagen> imagenes = icprov.getImagenesProv(nickProveedor);
        /*if(imagenes.size() == 0){
            BufferedImage bffrimg = icprov.copiarImagenesServicio(System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\help4traveling\\Nuevo\\src\\Logica\\perfiles\\perfil.png");
            String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + nickProveedor + "-1.jpg";
            File fichero = new File(homeUsuario);
            ImageIO.write(bffrimg, "jpg", fichero);
        }
        for(int j = 0; j < imagenes.size(); j++){
            BufferedImage bffrimg = icprov.copiarImagenesServicio(System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\help4traveling\\Nuevo\\" + imagenes.get(j).getPath());
            int nro = j + 1;
            String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + nickProveedor + "-" + nro + ".jpg";
            File fichero = new File(homeUsuario);
            ImageIO.write(bffrimg, "jpg", fichero);
        }*/
        datosProveedor[7] = String.valueOf(imagenes.size());
        ArrayList<DataServicio> serviciosDeProveedor =icprov.getServiciosProveedor(nickProveedor);
        datosProveedor[6] = new String();
        for(int i = 0; i < serviciosDeProveedor.size(); i++){
            datosProveedor[6] = datosProveedor[6] + serviciosDeProveedor.get(i).getNombreServicio() + ";";
        }
         HttpSession session = request.getSession();
        session.setAttribute("informacionProveedor", datosProveedor);
        if((Boolean) session.getAttribute("agregarLog") == true){
            iclog.agregarLog(LocalDate.now(), "http://localhost:8080/h4t/pages/verInformacionProveedor.jsp", browser, System.getProperty("os.name"));
        }
        response.sendRedirect("pages/verInformacionProveedor.jsp");
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
            Logger.getLogger(InformacionProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InformacionProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InformacionProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InformacionProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
