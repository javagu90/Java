/*
 * TmsRecaudaManagedBean.java
 *
 * Created on 10 de septiembre de 2007, 08:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsrecaudacion;

import TmsRecaudacion.solicitud.TmsActividadesSesionTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsAuditoriaTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsBoletosBoleteraTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsCajasRecTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsCortesTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsOperadoresTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRecaudacionGastosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRecaudacionLineasTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRecaudacionTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRecoleccionesTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRutasTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsServiciosGastosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsSesionActividadesTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsTarjetasViajeTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsUsuariosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsVariosFacadeRemote;
import TmsRecaudacion.solicitud.TmsVtaTiposPagoTblFacadeRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
/**
 *
 * @author vgonzalez
 */
public class TmsRecaudaManagedBean {
    TmsVariosFacadeRemote               variosFacadeRemote;
    TmsOperadoresTblFacadeRemote        operadoresTblFacadeRemote;
    TmsServiciosGastosTblFacadeRemote   serviciosGastosTblFacadeRemote;
    TmsRutasTblFacadeRemote             rutasTblFacadeRemote;
    TmsCortesTblFacadeRemote            cortesFacateRemote;
    TmsSesionActividadesTblFacadeRemote sesionActFacateRemote;
    TmsRecoleccionesTblFacadeRemote     recoleccionFacateRemote;
    TmsAuditoriaTblFacadeRemote         auditoriaTblFacadeRemote;
    TmsUsuariosTblFacadeRemote          usuariosTblFacadeRemote;
    TmsActividadesSesionTblFacadeRemote actividadesSesionTblFacadeRemote;
    TmsCajasRecTblFacadeRemote             cajasTblFacadeRemote;
    TmsTarjetasViajeTblFacadeRemote    tarjetasViajeTblFacadeRemote;
    TmsRecaudacionTblFacadeRemote       recaudacionTblFacadeRemote;
    TmsRecaudacionLineasTblFacadeRemote recaudacionLineasTblFacadeRemote;
    TmsVtaTiposPagoTblFacadeRemote      vtaTiposPagoTblFacadeRemote;
    TmsRecaudacionGastosTblFacadeRemote recaudacionGastosTblFacadeRemote;
    TmsBoletosBoleteraTblFacadeRemote   boletosBoleteraTblFacadeRemote;
    /**
     * Creates a new instance of TmsRecaudaManagedBean
     */
    public TmsRecaudaManagedBean() {
        variosFacadeRemote              = lookupTmsVariosFacadeFacade();
        operadoresTblFacadeRemote       = lookupTmsOperadoresTblFacade();
        serviciosGastosTblFacadeRemote  = lookupTmsServiciosGastosTblFacadeRemote();        
        rutasTblFacadeRemote            = lookupTmsRutasTblFacadeRemote();
        cortesFacateRemote              = lookupTmsCortesTblFacade();
        sesionActFacateRemote           = lookupTmsSesionActividadesTblFacade();
        recoleccionFacateRemote         = lookupTmsRecoleccionesTblFacade();
        auditoriaTblFacadeRemote        = lookupTmsAuditoriaTblFacadeRemote();
        usuariosTblFacadeRemote         = lookupTmsUsuariosTblFacadeRemote();
        actividadesSesionTblFacadeRemote =  lookupTmsActividadesSesionTblFacadeRemote();
        cajasTblFacadeRemote             =  lookupTmsCajasTblFacadeRemote();
        tarjetasViajeTblFacadeRemote    = lookupTmsTarjetasViajeTblFacadeRemote();
        recaudacionTblFacadeRemote      = lookupTmsRecaudacionTblFacade();
        recaudacionLineasTblFacadeRemote= lookupTmsRecaudacionLineasTblFacadeRemote();
        vtaTiposPagoTblFacadeRemote     = lookupTmsVtaTiposPagoTblFacadeRemote();
        recaudacionGastosTblFacadeRemote= lookupTmsRecaudacionGastosTblFacadeRemote();
        boletosBoleteraTblFacadeRemote  = lookupTmsBoletosBoleteraTblFacadeRemote();
        
    }

    private TmsVariosFacadeRemote lookupTmsVariosFacadeFacade() {
        try {
            Context c = new InitialContext();
            return (TmsVariosFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsVariosFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsOperadoresTblFacadeRemote lookupTmsOperadoresTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsOperadoresTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsOperadoresTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsServiciosGastosTblFacadeRemote lookupTmsServiciosGastosTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsServiciosGastosTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsServiciosGastosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsRutasTblFacadeRemote lookupTmsRutasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsRutasTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsRutasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCortesTblFacadeRemote lookupTmsCortesTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsCortesTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsCortesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }    

    private TmsSesionActividadesTblFacadeRemote lookupTmsSesionActividadesTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsSesionActividadesTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsSesionActividadesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

private TmsRecoleccionesTblFacadeRemote lookupTmsRecoleccionesTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsRecoleccionesTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsRecoleccionesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsAuditoriaTblFacadeRemote lookupTmsAuditoriaTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsAuditoriaTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsAuditoriaTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsUsuariosTblFacadeRemote lookupTmsUsuariosTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsUsuariosTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsUsuariosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsSesionActividadesTblFacadeRemote lookupTmsSesionActividadesTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsSesionActividadesTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsSesionActividadesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    
    private TmsActividadesSesionTblFacadeRemote lookupTmsActividadesSesionTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsActividadesSesionTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsActividadesSesionTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
    
    private TmsCajasRecTblFacadeRemote lookupTmsCajasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCajasRecTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsCajasRecTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsTarjetasViajeTblFacadeRemote lookupTmsTarjetasViajeTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsTarjetasViajeTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsTarjetasViajeTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsRecaudacionTblFacadeRemote lookupTmsRecaudacionTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsRecaudacionTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsRecaudacionTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsRecaudacionLineasTblFacadeRemote lookupTmsRecaudacionLineasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsRecaudacionLineasTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsRecaudacionLineasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsVtaTiposPagoTblFacadeRemote lookupTmsVtaTiposPagoTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsVtaTiposPagoTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsVtaTiposPagoTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsRecaudacionGastosTblFacadeRemote lookupTmsRecaudacionGastosTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (TmsRecaudacion.solicitud.TmsRecaudacionGastosTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsRecaudacionGastosTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsBoletosBoleteraTblFacadeRemote lookupTmsBoletosBoleteraTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (TmsRecaudacion.solicitud.TmsBoletosBoleteraTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsBoletosBoleteraTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
