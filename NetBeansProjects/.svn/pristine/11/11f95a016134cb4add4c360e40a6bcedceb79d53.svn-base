/*
 * TmsPuertasManagedBean.java
 *
 * Created on 9 de octubre de 2007, 10:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tmspuertas.solicitud.TmsFacadeGeneralPuertasRemote;

/**
 *
 * @author vgonzalez
 */
public class TmsPuertasManagedBean {    
    public TmsFacadeGeneralPuertasRemote    facadeGeneralPuertasRemote;

    
    /** Creates a new instance of TmsPuertasManagedBean */
    public TmsPuertasManagedBean() {
        facadeGeneralPuertasRemote              =  lookupTmsFacadeGeneralPuertasRemote();
    }


    private TmsFacadeGeneralPuertasRemote lookupTmsFacadeGeneralPuertasRemote() {
        try {
            Context c = new InitialContext();
            return (TmsFacadeGeneralPuertasRemote) c.lookup("tmspuertas.solicitud.TmsFacadeGeneralPuertasRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
}
