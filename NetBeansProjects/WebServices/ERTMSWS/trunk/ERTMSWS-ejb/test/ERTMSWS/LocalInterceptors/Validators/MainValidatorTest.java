/*
 * MainValidatorTest.java
 * JUnit based test
 *
 * Created on 24 de junio de 2010, 07:11 PM
 */

package ERTMSWS.LocalInterceptors.Validators;

import junit.framework.*;
import java.util.Vector;
import ERTMSWS.clases.Asientos;
import ERTMSWS.clases.FoliosBoletos;
import ERTMSWS.clasesx.ExchangeResp;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import ERTMSWS.clasesx.Login.LoginReq;

/**
 *
 * @author opalafox
 */
public class MainValidatorTest extends TestCase {
    
    public MainValidatorTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of isValidLength method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testIsValidLength() {
        System.out.println("isValidLength");
        
        String value = "";
        int length = 0;
        MainValidator instance = null;//new MainValidator();new MainValidatorator();
        
        boolean expResult = true;
        boolean result = instance.isValidLength(value, length);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidClaveCorrida method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testIsValidClaveCorrida() throws Exception {
        System.out.println("isValidClaveCorrida");
        
        String value = "";
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.isValidClaveCorrida(value);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidOrigen method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testIsValidOrigen() throws Exception {
        System.out.println("isValidOrigen");
        
        String value = "";
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.isValidOrigen(value);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidDestino method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testIsValidDestino() throws Exception {
        System.out.println("isValidDestino");
        
        String value = "";
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.isValidDestino(value);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidSesionId method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testIsValidSesionId() throws Exception {
        System.out.println("isValidSesionId");
        
        int ID = 0;
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.isValidSesionId(ID);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidAsientos method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testIsValidAsientos() throws Exception {
        System.out.println("isValidAsientos");
        
        Asientos[] asientos = null;
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.isValidAsientos(asientos);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidFoliosBoletos method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testIsValidFoliosBoletos() throws Exception {
        System.out.println("isValidFoliosBoletos");
        
        FoliosBoletos[] fBoletos = null;
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.isValidFoliosBoletos(fBoletos);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getErrorMessage method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testGetErrorMessage() {
        System.out.println("getErrorMessage");
        
        int error = 0;
        String variable = "";
        MainValidator instance = null;//new MainValidator();
        
        String expResult = "";
        String result = instance.getErrorMessage(error, variable);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidUsuarioNumero method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testIsValidUsuarioNumero() throws Exception {
        System.out.println("isValidUsuarioNumero");
        
        String uNumero = "";
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.isValidUsuarioNumero(uNumero);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidUsuarioContrasena method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testIsValidUsuarioContrasena() throws Exception {
        System.out.println("isValidUsuarioContrasena");
        
        String uContrasena = "";
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.isValidUsuarioContrasena(uContrasena);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of responseErrorClass method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testResponseErrorClass() {
        System.out.println("responseErrorClass");
        
        ExchangeResp exResp = null;
        Exception except = null;
        
        MainValidator.responseErrorClass(exResp, except);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRequired method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testCheckRequired() throws Exception {
        System.out.println("checkRequired");
        
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.checkRequired();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkOptional method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testCheckOptional() throws Exception {
        System.out.println("checkOptional");
        
        MainValidator instance = null;//new MainValidator();
        
        boolean expResult = true;
        boolean result = instance.checkOptional();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printParameters method, of class ERTMSWS.LocalInterceptors.Validators.MainValidator.
     */
    public void testPrintParameters() throws Exception {
        System.out.println("printParameters");
        
        Object ob = null;
        MainValidator instance = null;//new MainValidator();
        
        instance.printParameters(ob);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
