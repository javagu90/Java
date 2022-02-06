/*
 * TMSCorteRecManagedBean.java
 *
 * Created on 15 de agosto de 2007, 08:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package corterecaudacion;

import com.sun.xml.bind.v2.runtime.Name;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import xertmsCorteReca.entidad.TmsRecoleccionesTbl;
import xertmsCorteReca.solicitud.TmsActividadesSesionTblFacadeRemote;
import xertmsCorteReca.solicitud.TmsCortesTblFacadeRemote;
import xertmsCorteReca.solicitud.TmsRecoleccionesTblFacade;
import xertmsCorteReca.solicitud.TmsRecoleccionesTblFacadeRemote;
import xertmsCorteReca.solicitud.TmsSesionActividadesTblFacadeRemote;
import xertmsCorteReca.solicitud.TmsUsuariosTblFacade;
import xertmsCorteReca.solicitud.TmsUsuariosTblFacadeRemote;
import xertmsCorteReca.solicitud.TmsVtaTiposPagoTblFacadeRemote;

/**
 *
 * @author vgonzalez
 */
public class TMSCorteRecManagedBean {
    public TmsCortesTblFacadeRemote cortesTblFacadeRemote;
    public TmsUsuariosTblFacadeRemote usuariosTblFacadeRemote;
    public TmsRecoleccionesTblFacadeRemote recoleccionesTblFacadeRemote;
    public TmsActividadesSesionTblFacadeRemote actividadesSesionTblFacadeRemote;
    public TmsVtaTiposPagoTblFacadeRemote vtaTiposPagoTblFacadeRemote;
    public TmsSesionActividadesTblFacadeRemote sesionActividadesTblFacadeRemote;
                        
    /** Creates a new instance of TMSCorteRecManagedBean */
    public TMSCorteRecManagedBean() {
        cortesTblFacadeRemote = lookupTmsCortesTblFacadeRemote();
        usuariosTblFacadeRemote = lookupTmsUsuariosTblFacadeRemote();
        recoleccionesTblFacadeRemote = lookupTmsRecoleccionesTblFacadeRemote();
        actividadesSesionTblFacadeRemote = lookupTmsActividadesSesionTblFacadeRemote();
        vtaTiposPagoTblFacadeRemote = lookupTmsVtaTiposPagoTblFacadeRemote();
        sesionActividadesTblFacadeRemote = lookupTmsSesionActividadesTblFacadeRemote();
    }
    
    private TmsCortesTblFacadeRemote lookupTmsCortesTblFacadeRemote(){
            try {
            Context c = new InitialContext();
            return (TmsCortesTblFacadeRemote) c.lookup("xertmsCorteReca.solicitud.TmsCortesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }

    }

    private TmsUsuariosTblFacadeRemote lookupTmsUsuariosTblFacadeRemote(){
                try {
            Context c = new InitialContext();
            return (TmsUsuariosTblFacadeRemote) c.lookup("xertmsCorteReca.solicitud.TmsUsuariosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }

    }
    
    private TmsRecoleccionesTblFacadeRemote lookupTmsRecoleccionesTblFacadeRemote(){
                try {
            Context c = new InitialContext();
            return (TmsRecoleccionesTblFacadeRemote) c.lookup("xertmsCorteReca.solicitud.TmsRecoleccionesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }

    }

    private TmsActividadesSesionTblFacadeRemote lookupTmsActividadesSesionTblFacadeRemote() {
                try {
            Context c = new InitialContext();
            return (TmsActividadesSesionTblFacadeRemote) c.lookup("xertmsCorteReca.solicitud.TmsActividadesSesionTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsVtaTiposPagoTblFacadeRemote lookupTmsVtaTiposPagoTblFacadeRemote() {
                try {
            Context c = new InitialContext();
            return (TmsVtaTiposPagoTblFacadeRemote) c.lookup("xertmsCorteReca.solicitud.TmsVtaTiposPagoTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }

    }

    private TmsSesionActividadesTblFacadeRemote lookupTmsSesionActividadesTblFacadeRemote() {
                       try {
            Context c = new InitialContext();
            return (TmsSesionActividadesTblFacadeRemote) c.lookup("xertmsCorteReca.solicitud.TmsSesionActividadesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        } 
    }
    
}
