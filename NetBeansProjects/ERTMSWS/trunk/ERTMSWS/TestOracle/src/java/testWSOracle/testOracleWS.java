/*
 * testOracleWS.java
 *
 * Created on 5 de julio de 2010, 11:14 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package testWSOracle;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author opalafox
 */
@WebService()
public class testOracleWS {
    /**
     * Web service operation
     */
    @WebMethod
    public String printHello() {
        // TODO implement operation 
        return "Hola Mundo!";
    }

    /**
     * Web service operation
     */
    @WebMethod
    public String printName(@WebParam(name = "Nombre") String Nombre) {
        // TODO implement operation 
        return "Hola "+Nombre;
    }
    
}
