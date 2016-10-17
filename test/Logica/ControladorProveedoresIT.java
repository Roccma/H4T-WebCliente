/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class ControladorProveedoresIT {
    
    public ControladorProveedoresIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void getMaxServicios() throws Exception {
        IControladorProveedores icprov = new ControladorProveedores();
        ArrayList servicios = icprov.getMaxServicios();
        assertTrue(servicios.size() > 0);
    }
    
    @Test
    public void getServiciosPorCategoriaOrdenPrecio() throws Exception {
        IControladorProveedores icprov = new ControladorProveedores();
        ArrayList<DataServicio> servicios = icprov.getServiciosPorCategoriaOrdenPrecio("Alojamiento");
        assertTrue(servicios.get(0).getNombreServicio().equals("Casa para p4 BsAs"));
    }
    
    @Test
    public void getServiciosPorCategoriaOrdenAlfabeticamente() throws Exception {
        IControladorProveedores icprov = new ControladorProveedores();
        ArrayList<DataServicio> servicios = icprov.getServiciosPorCategoriaOrdenAlfabeticamente("Vuelos");
        assertTrue(servicios.get(0).getNombreServicio().equals("Air-France-FC"));
    }
    
    @Test
    public void rutaImagenesServicios() throws Exception {
        IControladorProveedores icprov = new ControladorProveedores();
        ArrayList servicios = icprov.rutaImagenesServicios("Air-France-FC", "tCook");
        assertTrue(servicios.size() > 0);
    }
    
    @Test
    public void imagenesProveedorABytes() throws Exception {
        IControladorProveedores icprov = new ControladorProveedores();
        ArrayList servicios = icprov.imagenesProveedorABytes("moody1");
        assertNotNull(servicios);
    }
    
    @Test
    public void encriptar() throws Exception {
        IControladorProveedores icprov = new ControladorProveedores();
        String encriptada = icprov.encriptar("123");
        assertEquals(encriptada, "MTIz");
    }
    
    @Test
    public void getServiciosPorCategoria() throws Exception {
        IControladorProveedores icprov = new ControladorProveedores();
        ArrayList<DataServicio> servicios = icprov.getServiciosPorCategoria("Automóviles");
        assertTrue(servicios.size() > 0);
        
    }
}
