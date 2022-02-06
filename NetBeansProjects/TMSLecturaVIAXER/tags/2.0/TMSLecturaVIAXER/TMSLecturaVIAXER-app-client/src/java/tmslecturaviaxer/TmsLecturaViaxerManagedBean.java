/*
 * TmsLecturaViaxerManagedBean.java
 *
 * Created on 10 de septiembre de 2007, 08:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tmslecturaviaxer.entidad.TmsSesionActividadesTbl;
import tmslecturaviaxer.solicitud.TmsActividadesSesionTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsAuditoriaTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsBoletosBoleteraTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsCajasTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsCortesTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsOperadoresTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsRecoleccionesTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsSesionActividadesTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsTarjetasViajeTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsUsuariosDatafareTblFacadeRemote;
import tmslecturaviaxer.solicitud.TmsVariosFacadeRemote;
import tmslecturaviaxer.solicitud.TmsVtaTiposPagoTblFacadeRemote;

/**
 *
 * @author vgonzalez
 */
public class TmsLecturaViaxerManagedBean {
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
     * Creates a new instance of TmsLecturaViaxerManagedBean
     */
    public TmsLecturaViaxerManagedBean() {
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
            return (tmslecturaviaxer.solicitud.TmsVariosFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsVariosFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsOperadoresTblFacadeRemote lookupTmsOperadoresTblFacade() {
        try {
            Context c = new InitialContext();
            return (tmslecturaviaxer.solicitud.TmsOperadoresTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsOperadoresTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsTarjetasViajeTblFacadeRemote lookupTmsTarjetasViajeTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturaviaxer.solicitud.TmsTarjetasViajeTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsTarjetasViajeTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsBoletosBoleteraTblFacadeRemote lookupTmsBoletosBoleteraTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturaviaxer.solicitud.TmsBoletosBoleteraTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsBoletosBoleteraTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsActividadesSesionTblFacadeRemote lookupTmsActividadesSesionTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturaviaxer.solicitud.TmsActividadesSesionTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsActividadesSesionTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsAuditoriaTblFacadeRemote lookupTmsAuditoriaTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturaviaxer.solicitud.TmsAuditoriaTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsAuditoriaTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCajasTblFacadeRemote lookupTmsCajasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturaviaxer.solicitud.TmsCajasTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsCajasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCortesTblFacadeRemote lookupTmsCortesTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturaviaxer.solicitud.TmsCortesTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsCortesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsUsuariosDatafareTblFacadeRemote lookupTmsUsuariosTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (tmslecturaviaxer.solicitud.TmsUsuariosDatafareTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsUsuariosDatafareTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsSesionActividadesTblFacadeRemote lookupTmsSesionActividadesTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmslecturaviaxer.solicitud.TmsSesionActividadesTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsSesionActividadesTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsVtaTiposPagoTblFacadeRemote lookupTmsVtaTiposPagoTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmslecturaviaxer.solicitud.TmsVtaTiposPagoTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsVtaTiposPagoTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsRecoleccionesTblFacadeRemote lookupTmsRecoleccionesTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmslecturaviaxer.solicitud.TmsRecoleccionesTblFacadeRemote) c.lookup("tmslecturaviaxer.solicitud.TmsRecoleccionesTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    
    
    
}
