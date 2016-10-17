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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author usuario
 */
public class BusquedaServlet extends HttpServlet {

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
        IControladorPromociones icprom = new ControladorPromociones();
        IControladorProveedores icprov = new ControladorProveedores(); 
        IControladorLogs iclog = new ControladorLogs();
        HttpSession session = request.getSession();
        String buscar = request.getParameter("buscar");
        if(buscar.equals("-")){
            buscar = (String) session.getAttribute("buscar");
        }
        buscar = buscar.replace("Ã¡", "á");
        buscar = buscar.replace("Ã©", "é");
        buscar = buscar.replace("Ã-", "í");
        buscar = buscar.replace("Ã³", "ó");
        buscar = buscar.replace("Ãº", "ú");
        buscar = buscar.replace("Ã‰", "É");
        buscar = buscar.replace("Ã“", "Ó");
        buscar = buscar.replace("Ãš", "Ú");
        buscar = buscar.replace("Ã±", "ñ");
        buscar = buscar.replace("Ã‘", "Ñ");      
        session.setAttribute("buscar", buscar);
        String orden = request.getParameter("orden");
        ArrayList<String> resultados = new ArrayList<String>();
        if(orden.equals("p")){
            resultados = icprom.selectBusquedaDatosOrdenPrecio(buscar);
        }
        else{
            resultados = icprom.selectBusquedaDatosOrdenAlfabeticamente(buscar);
        }
        String[] productos = new String[resultados.size()];
        for(int i = 0; i < resultados.size(); i++){
            String[] datosResultado = resultados.get(i).split(";");
            String link = "";
            ArrayList<DataCategoria> categorias = icprov.getCategorias(datosResultado[1], datosResultado[2]);
            if(datosResultado[0].equals("servicio"))
                link = "../InformacionServicioServlet?nombre=" + datosResultado[1] + "&nickProveedor=" + datosResultado[2] + "&categoriaSeleccionada=" + categorias.get(0); 
            else
                link = "../InformacionPromocionServlet?nombrePromocion=" + datosResultado[1] + "&nickProveedor=" + datosResultado[2];
            productos[i] = datosResultado[0] + ";" + datosResultado[1] + ";" + datosResultado[2] + ";" + datosResultado[3] + ";" + link + ";";
        }
        
        session.setAttribute("productos", productos);
        
        if((Boolean) session.getAttribute("agregarLog") == true){
            iclog.agregarLog(LocalDate.now(), "http://localhost:8080/h4t/pages/resultadoBusqueda.jsp?orden=" + orden, browser, System.getProperty("os.name"));
        }
        
        response.sendRedirect("pages/resultadoBusqueda.jsp?orden=" + orden);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           /* out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BusquedaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BusquedaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
            Logger.getLogger(BusquedaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BusquedaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BusquedaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BusquedaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
