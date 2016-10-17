/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logica.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class AgregarAlCarritoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws java.sql.SQLException
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
        IControladorProveedores icprov = new ControladorProveedores();
        String respuesta = "";
        if(session.getAttribute("nick") != null){
            String[] carrito = (String[]) session.getAttribute("carrito");
            int i = 0;
            while(carrito[i] != null){
                i++;
            }
            boolean yaAgregado = false;
            int posicion = 0;
            for(int j = 0; j < i; j++){
                String[] datos = carrito[j].split(";");
                //System.out.println(datos[4] + " : " + datos[5] + " : " + datos[1] + " : " + datos[2]);
                if(datos[4].equals(request.getParameter("producto")) && datos[5].equals(request.getParameter("proveedor")) && datos[1].equals(request.getParameter("fechaDesde")) && datos[2].equals(request.getParameter("fechaHasta"))){
                    posicion =  j;                    
                    yaAgregado = true;
                }
            }
            
            if(!yaAgregado){
                String datosCarrito = "";
                datosCarrito = datosCarrito + request.getParameter("tipo") + ";";
                datosCarrito = datosCarrito + request.getParameter("fechaDesde") + ";";
                datosCarrito = datosCarrito + request.getParameter("fechaHasta") + ";";
                datosCarrito = datosCarrito + request.getParameter("cantidad") + ";";
                datosCarrito = datosCarrito + request.getParameter("producto") + ";";
                datosCarrito = datosCarrito + request.getParameter("proveedor") + ";";
                datosCarrito = datosCarrito + request.getParameter("empresa") + ";";
                datosCarrito = datosCarrito + request.getParameter("precio") + ";";
                if(request.getParameter("tipo").equals("servicio")){
                    ArrayList<DataCategoria> categoria = icprov.getCategorias((String) request.getParameter("producto"), (String) request.getParameter("proveedor"));
                    datosCarrito = datosCarrito + categoria.get(0).getNombre() + ";";
                }
                carrito[i] = datosCarrito;
                session.setAttribute("carrito", carrito);
                respuesta = "si " + i;
            }
            else{
                String[] datosProducto = carrito[posicion].split(";");
                int cantidadNueva = Integer.parseInt(datosProducto[3]) + Integer.parseInt(request.getParameter("cantidad"));
                carrito[posicion] = datosProducto[0] + ";" + datosProducto[1] + ";" + datosProducto[2] + ";" + cantidadNueva + ";" + datosProducto[4] + ";" + datosProducto[5] + ";" + datosProducto[6] + ";" + datosProducto[7] + ";";
                if(datosProducto[8] != null){
                    carrito[posicion] = carrito[posicion] + datosProducto[8] + ";";
                }
                session.setAttribute("carrito", carrito);
                respuesta = "si (ya agregada)";
            }
            
        }
        else{
            respuesta = "noSesion";
        }
        System.out.println(respuesta);
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
