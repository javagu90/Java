/*
 * TmsLecturaDatafareManagedBean.java
 *
 * Created on 10 de septiembre de 2007, 08:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturadatafare;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tmslecturadatafare.entidad.TmsSesionActividadesTbl;
import tmslecturadatafare.solicitud.TmsActividadesSesionTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsAuditoriaTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsBoletosBoleteraTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsCajasTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsCortesTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsOperadoresTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsRecoleccionesTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsSesionActividadesTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsTarjetasViajeTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsUsuariosDatafareTblFacadeRemote;
import tmslecturadatafare.solicitud.TmsVariosFacadeRemote;
import tmslecturadatafare.solicitud.TmsVtaTiposPagoTblFacadeRemote;

/**
 *
 * @author vgonzalez
 */
public class TmsLecturaDatafareManagedBean {
    TmsVariosFacadeRemote               variosFacadeRemote;
    TmsOperadoresTblFacadeRemote        operadoresTblFacadeRemote;
    TmsTarjetasViajeTblFacadeRemote     tarjetasViajeTblFacadeRemote;
    TmsBoletosBoleteraTblFacadeRemote   boletosBoleteraTblFacadeRemote;
    TmsActividadesSesionTblFacadeRemote actividadesSesionTblFacadeRemote;
    TmsAuditoriaTblFacadeRemote         auditoriaTblFacadeRemote;
    TmsCajasTblFacadeRemote             cajasTblFacadeRemote;
    TmsCortesTblFacadeRemote            cortesTblFacadeRemote;
    TmsUsuariosDatafareTblFacadeRemote          usuariosTblFacadeRemote;
    TmsSesionActividadesTblFacadeRemote sesionActividadesTblFacadeRemote;
    TmsVtaTiposPagoTblFacadeRemote      vtaTiposPagoTblFacadeRemote;
    TmsRecoleccionesTblFacadeRemote     recoleccionFacateRemote;
    
    
    
    /**
     * Creates a new instance of TmsLecturaDatafareManagedBean
     */
    public TmsLecturaDatafareManagedBean() {
        variosFacadeRemote              = lookupTmsVariosFacadeFacade();
        operadoresTblFacadeRemote       = lookupTmsOperadoresTblFacade();
        tarjetasViajeTblFacadeRemote    = lookupTmsTarjetasViajeTblFacadeRemote();
        boletosBoleteraTblFacadeRemote  = lookupTmsBoletosBoleteraTblFacadeRemote();
        actividadesSesionTblFacadeRemote= lookupTmsActividadesSesionTblFacadeRemote();
        auditoriaTblFacadeRemote        = lookupTmsAuditoriaTblFacadeRemote();
        cajasTblFacadeRemote            = lookupTmsCajasTblFacadeRemote();
        cortesTblFacadeRemote           = lookupTmsCortesTblFacadeRemote();
        usuariosTblFacadeRemote         = lookupTmsUsuariosTblFacadeRemote();
        sesionActividadesTblFacadeRemote= lookupTmsSesionActividadesTblFacadeRemote();
        vtaTiposPagoTblFacadeRemote     = lookupTmsVtaTiposPagoTblFacadeRemote();
        recoleccionFacateRemote       = lookupTmsRecoleccionesTblFacadeRemote();
    }
    

    private TmsVariosFacadeRemote lookupTmsVariosFacadeFacade() {
        try {
            Context c = new InitialContext();
            return (tmslecturadatafare.solicitud.TmsVariosFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsVariosFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsOperadoresTblFacadeRemote lookupTmsOperadoresTblFacade() {
        try {
            Context c = new InitialContext();
            return (tmslecturadatafare.solicitud.TmsOperadoresTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsOperadoresTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsTarjetasViajeTblFacadeRemote lookupTmsTarjetasViajeTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturadatafare.solicitud.TmsTarjetasViajeTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsTarjetasViajeTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsBoletosBoleteraTblFacadeRemote lookupTmsBoletosBoleteraTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturadatafare.solicitud.TmsBoletosBoleteraTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsBoletosBoleteraTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsActividadesSesionTblFacadeRemote lookupTmsActividadesSesionTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturadatafare.solicitud.TmsActividadesSesionTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsActividadesSesionTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsAuditoriaTblFacadeRemote lookupTmsAuditoriaTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturadatafare.solicitud.TmsAuditoriaTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsAuditoriaTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCajasTblFacadeRemote lookupTmsCajasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturadatafare.solicitud.TmsCajasTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsCajasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCortesTblFacadeRemote lookupTmsCortesTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturadatafare.solicitud.TmsCortesTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsCortesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsUsuariosDatafareTblFacadeRemote lookupTmsUsuariosTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturadatafare.solicitud.TmsUsuariosDatafareTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsUsuariosDatafareTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsSesionActividadesTblFacadeRemote lookupTmsSesionActividadesTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmslecturadatafare.solicitud.TmsSesionActividadesTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsSesionActividadesTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsVtaTiposPagoTblFacadeRemote lookupTmsVtaTiposPagoTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmslecturadatafare.solicitud.TmsVtaTiposPagoTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsVtaTiposPagoTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsRecoleccionesTblFacadeRemote lookupTmsRecoleccionesTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmslecturadatafare.solicitud.TmsRecoleccionesTblFacadeRemote) c.lookup("tmslecturadatafare.solicitud.TmsRecoleccionesTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    
    
    
}
