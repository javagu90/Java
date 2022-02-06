/*
 * Settings.java
 *
 * Created on 28 de diciembre de 2009, 04:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslealtad_carga;

import TmsLealtadSolicitud. TMSLealtadBatchFacadeRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author brojasa
 */
public class Settings {  
   
  public static TMSLealtadBatchFacadeRemote LealtadRemote;
    
  
    // servicios remotos  

    public final static String genericoFR = "TmsLealtadSolicitud.TMSLealtadBatchFacadeRemote";
    
    /** Creates a new instance of Settings */
    public Settings() {
    }
 // lookups 
    public static void lookupGenerico(){
        if ( LealtadRemote == null )
            LealtadRemote = (TMSLealtadBatchFacadeRemote) getEJB(genericoFR);        
    }        

    public static Object getEJB(String rutaEJB) {
       try {
            Context c = new InitialContext();
            return c.lookup(rutaEJB);
            
        }
        catch(NamingException ne) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            ne.printStackTrace();
            throw new RuntimeException(ne);
            
        }
    }
    
}
