/*
 * SettingsCF_TMS.java
 *
 * Created on 27 de abril de 2009, 06:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_vta_productos_er;

import TMSVtaProductosER.solicitud.TMSVtaProductosERFacadeRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author brojas
 */
public class facadeVtaProductos {
    
    /**
     * Creates a new instance of SettingsCF_TMS
     */
    public facadeVtaProductos() {
    }

    public static TMSVtaProductosERFacadeRemote generico_tmsRemote;
    public final static String genericoFacade = "TMSVtaProductosER.solicitud.TMSVtaProductosERFacadeRemote";
   
 // lookups 
    public static void lookupGenerico(){  
        if ( generico_tmsRemote == null )
            generico_tmsRemote = (TMSVtaProductosERFacadeRemote) getEJB(genericoFacade);
    }        

    public static Object getEJB(String rutaEJB) {
       try {
            Context c = new InitialContext();
            return c.lookup(rutaEJB);
            
        }
        catch(NamingException ne) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
}
