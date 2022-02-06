/*
 * TmsCambioEstadoOperadorManagedBean.java
 *
 * Created on 10 de septiembre de 2007, 08:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsestadosoperador;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tmsedosoperador.solicitud.TmsAccionesTblFacadeRemote;
import tmsedosoperador.solicitud.TmsOperadoresTblFacadeRemote;


/**
 *
 * @author vgonzalez
 */
public class TmsCambioEstadoOperadorManagedBean {
      TmsOperadoresTblFacadeRemote  operadoresTblFacadeRemote;
      TmsAccionesTblFacadeRemote    accionesTblFacadeRemote;
    
    /**
     * Creates a new instance of TmsCambioEstadoOperadorManagedBean
     */
    public TmsCambioEstadoOperadorManagedBean() {
        operadoresTblFacadeRemote       = lookupTmsOperadoresTblFacade();
        accionesTblFacadeRemote         = lookupTmsAccionesTblFacadeRemote();
    }
    

    private TmsOperadoresTblFacadeRemote lookupTmsOperadoresTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsOperadoresTblFacadeRemote) c.lookup("tmsedosoperador.solicitud.TmsOperadoresTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsAccionesTblFacadeRemote lookupTmsAccionesTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsedosoperador.solicitud.TmsAccionesTblFacadeRemote) c.lookup("tmsedosoperador.solicitud.TmsAccionesTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

}