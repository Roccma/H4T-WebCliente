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
public class ControladorPromocionesIT {
    
    public ControladorPromocionesIT() {
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
    public void getPromocionesOrdenPrecio() throws Exception {
        IControladorPromociones icprom = new ControladorPromociones();
        ArrayList promociones = icprom.getPromocionesOrdenPrecio();
        assertTrue(promociones.size() > 0);
    }
    
    @Test
    public void getPromocionesOrdenAlfabeticamente() throws Exception {
        IControladorPromociones icprom = new ControladorPromociones();
        ArrayList promociones = icprom.getPromocionesOrdenAlfabeticamente();
        assertTrue(promociones.size() > 0);
    }
    
    @Test
    public void getMaxPromociones() throws Exception {
        IControladorPromociones icprom = new ControladorPromociones();
        ArrayList promociones = icprom.getMaxPromociones();
        assertFalse(promociones.size() < 1);
    }
    
    @Test
    public void selectBusquedaDatosOrdenPrecio() throws Exception {
        IControladorPromociones icprom = new ControladorPromociones();
        ArrayList promociones = icprom.selectBusquedaDatosOrdenPrecio("este no tiene que dar algo");
        assertFalse(promociones.size() > 0);
    }
    
    @Test
    public void selectBusquedaDatosOrdenAlfabeticamente() throws Exception {
        IControladorPromociones icprom = new ControladorPromociones();
        ArrayList promociones = icprom.selectBusquedaDatosOrdenPrecio("a");
        assertTrue(promociones.size() > 0);
    }
}
