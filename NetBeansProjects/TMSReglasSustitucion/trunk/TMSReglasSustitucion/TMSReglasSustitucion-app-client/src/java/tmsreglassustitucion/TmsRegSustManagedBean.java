/*
 * TmsRegSustManagedBean.java
 *
 * Created on 30 de agosto de 2007, 12:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsreglassustitucion;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tmsreglassustit.solicitud.TmsEstadosTblFacadeRemote;
import tmsreglassustit.solicitud.TmsFlotillasTblFacade;
import tmsreglassustit.solicitud.TmsFlotillasTblFacadeRemote;
import tmsreglassustit.solicitud.TmsReglasSustTblFacadeRemote;

/**
 *
 * @author vgonzalez
 */
public class TmsRegSustManagedBean {
    
    /**
     * Creates a new instance of TmsRegSustManagedBean
     */
    TmsFlotillasTblFacadeRemote    flotillasTblFacadeRemote;
    TmsEstadosTblFacadeRemote      estadosTblFacadeRemote;
    TmsReglasSustTblFacadeRemote   reglasSustTblFacadeRemote; 
    SimpleDateFormat ffecha =  new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat fhora  =  new SimpleDateFormat("HH:mm");

    
    public TmsRegSustManagedBean() {
        flotillasTblFacadeRemote  =  lookupTmsFlotillasTblFacade();
        estadosTblFacadeRemote    =  lookupTmsEstadosTblFacadeRemote();
        reglasSustTblFacadeRemote =  lookupTmsReglasSustTblFacadeRemote();
    }

    private TmsFlotillasTblFacadeRemote lookupTmsFlotillasTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsFlotillasTblFacadeRemote) c.lookup("tmsreglassustit.solicitud.TmsFlotillasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsEstadosTblFacadeRemote lookupTmsEstadosTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsreglassustit.solicitud.TmsEstadosTblFacadeRemote) c.lookup("tmsreglassustit.solicitud.TmsEstadosTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsReglasSustTblFacadeRemote lookupTmsReglasSustTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsreglassustit.solicitud.TmsReglasSustTblFacadeRemote) c.lookup("tmsreglassustit.solicitud.TmsReglasSustTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
