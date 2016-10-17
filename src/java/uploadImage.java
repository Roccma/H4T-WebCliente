/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logica.ControladorClientes;
import Logica.IControladorClientes;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 *
 * @author kapo_
 */
@WebServlet(urlPatterns = {"/uploadImage"})
public class uploadImage extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
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
            
            String nick = (String)session.getAttribute("nick");
            
            String homeUsuario = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\H4T-Web\\web\\perfiles\\" + nick + ".jpg";
            
            File fichero = new File(homeUsuario);
            fichero.delete();
            
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        
            if(!isMultiPart){
                return;
            }
            else{
                DiskFileItemFactory factory = new DiskFileItemFactory();
                
                ServletFileUpload upload = new ServletFileUpload(factory);
                try{
                    List fileItems = upload.parseRequest(request);
                    
                    Iterator I = fileItems.iterator();
                    
                    while(I.hasNext()){
                        FileItem fi = (FileItem)I.next();
                        File file = new File(homeUsuario);
                        fi.write(file);
                    }                   
                    
                    BufferedImage imgPerfil = ImageIO.read(fichero);
                    
                    IControladorClientes iccli = new ControladorClientes();
                    
                    iccli.agregarImagenPerfilWeb(imgPerfil, nick);
                    
                    response.sendRedirect("CopiarImagenesServlet?pagina=index");
                }
                catch(Exception ex){
                    
                }
            }
            
            /*Part filePart = request.getPart("profile");
            
            File uploads = new File("/perfiles");
            
            File file = new File(uploads, "yato.jpg" );
            
            InputStream fileContent = filePart.getInputStream();
            
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            */
        /*try {
            List<FileItem> item = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            
            for(FileItem items : item){
                
            InputStream fileContent = items.getInputStream();
            String homeUsuario =  "C:\\Users\\kapo_\\OneDrive\\Documentos\\NetBeansProjects\\H4T-Web\\web\\perfiles\\cualquiera.jpg";
            //File fichero = new File(homeUsuario);
            
            OutputStream os = new FileOutputStream(homeUsuario);
            
            int read = 0;
            
            byte[] bytes = new byte[1024];
            while((read = fileContent.read(bytes)) != -1){
                os.write(bytes, 0, read);
            }

            File uploads = new File("/perfiles");
            
            File file = new File(uploads, "Nuevo proveedor-1.jpg");
            
            //Files.copy(fileContent, fichero.toPath());
            
            }
            
            
        } catch (FileUploadException ex) {
            Logger.getLogger(uploadImage.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        */
        
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
