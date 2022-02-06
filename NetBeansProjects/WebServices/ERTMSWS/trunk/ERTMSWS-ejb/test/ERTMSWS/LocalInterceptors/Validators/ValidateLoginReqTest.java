/*
 * ValidateLoginReqTest.java
 * JUnit based test
 *
 * Created on 24 de junio de 2010, 07:07 PM
 */

package ERTMSWS.LocalInterceptors.Validators;

import junit.framework.*;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.Login.LoginReq;
import java.util.Date;
import java.lang.reflect.Field;

/**
 *
 * @author opalafox
 */
public class ValidateLoginReqTest extends TestCase {
    
    public ValidateLoginReqTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of validar method, of class ERTMSWS.LocalInterceptors.Validators.ValidateLoginReq.
     */
    public void testValidar() throws Exception {
        System.out.println("validar");
        
        InvocationContext ic = null;
        ValidateLoginReq instance = new ValidateLoginReq();
        
        Object expResult = null;
        Object result = instance.validar(ic);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRequired method, of class ERTMSWS.LocalInterceptors.Validators.ValidateLoginReq.
     */
    public void testCheckRequired() throws Exception {
        System.out.println("checkRequired");
        
        ValidateLoginReq instance = new ValidateLoginReq();
        
        boolean expResult = true;
        boolean result = instance.checkRequired();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkOptional method, of class ERTMSWS.LocalInterceptors.Validators.ValidateLoginReq.
     */
    public void testCheckOptional() throws Exception {
        System.out.println("checkOptional");
        
        ValidateLoginReq instance = new ValidateLoginReq();
        
        boolean expResult = true;
        boolean result = instance.checkOptional();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
