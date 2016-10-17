/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.image.BufferedImage;
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
public class ControladorClientesIT {
    
    public ControladorClientesIT() {
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
    public void imagenLogueado() throws Exception {
        IControladorClientes iccli = new ControladorClientes();
        BufferedImage img = iccli.imagenLogueado("oWood");
        assertNotNull(img);
    }
    
    @Test
    public void seleccionarClienteWeb() throws Exception {
        IControladorClientes iccli = new ControladorClientes();
        DataCliente cliente = iccli.seleccionarClienteWeb("eWatson");
        assertNotNull(cliente);
    }
    
    @Test
    public void encriptar() throws Exception {
        IControladorClientes iccli = new ControladorClientes();
        String encriptada = iccli.encriptar("123");
        assertEquals(encriptada, "MTIz");
    }
}
