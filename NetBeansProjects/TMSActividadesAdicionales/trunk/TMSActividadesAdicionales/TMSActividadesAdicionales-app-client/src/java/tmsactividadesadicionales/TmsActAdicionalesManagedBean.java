/*
 * TmsActAdicionalesManagedBean.java
 *
 * Created on 2 de octubre de 2007, 12:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tmsactividadesadicionales.solicitud.TmsActAdicionalesTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsActDatosAdicionalesTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsAuditoriaTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsCajasImpresorasTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsCajasTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsDatosAdicionalesTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsEmpresasTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsFormatosBoletoTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsOperadoresTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsPagosActAdicionalesTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsServiciosTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsUsuariosTblFacadeRemote;
import tmsactividadesadicionales.solicitud.TmsVariosFacadeRemote;

/**
 *
 * @author vgonzalez
 */
public class TmsActAdicionalesManagedBean {
     TmsActAdicionalesTblFacadeRemote        actAdicionalesTblFacadeRemote;
     TmsActDatosAdicionalesTblFacadeRemote   actDatosAdicionalesTblFacadeRemote;
     TmsDatosAdicionalesTblFacadeRemote      datosAdicionalesTblFacadeRemote;
     TmsPagosActAdicionalesTblFacadeRemote   pagosActAdicionalesTblFacadeRemote;
     TmsOperadoresTblFacadeRemote            operadoresTblFacadeRemote;
     TmsServiciosTblFacadeRemote             serviciosTblFacadeRemote;
     TmsEmpresasTblFacadeRemote              empresasTblFacadeRemote;
     TmsAuditoriaTblFacadeRemote             auditoriaTblFacadeRemote;
     TmsCajasImpresorasTblFacadeRemote       cajasImpresorasTblFacadeRemote;
     TmsCajasTblFacadeRemote                 cajasTblFacadeRemote;
     TmsFormatosBoletoTblFacadeRemote        formatosBoletoTblFacadeRemote;
     TmsVariosFacadeRemote                   variosFacadeRemote;
     TmsUsuariosTblFacadeRemote              usuariosTblFacadeRemote;



    
    /** Creates a new instance of TmsActAdicionalesManagedBean */
    public TmsActAdicionalesManagedBean() {
        actAdicionalesTblFacadeRemote       =  lookupTmsActAdicionalesTblFacadeRemote();
        actDatosAdicionalesTblFacadeRemote  =  lookupTmsActDatosAdicionalesTblFacadeRemote();
        datosAdicionalesTblFacadeRemote     =  lookupTmsDatosAdicionalesTblFacadeRemote();
        pagosActAdicionalesTblFacadeRemote  =  lookupTmsPagosActAdicionalesTblFacadeRemote();
        operadoresTblFacadeRemote           =  lookupTmsOperadoresTblFacadeRemote();
        serviciosTblFacadeRemote            =  lookupTmsServiciosTblFacadeRemote();
        empresasTblFacadeRemote             =  lookupTmsEmpresasTblFacadeRemote();
        auditoriaTblFacadeRemote            =  lookupTmsAuditoriaTblFacadeRemote();
        cajasImpresorasTblFacadeRemote      =  lookupTmsCajasImpresorasTblFacadeRemote();
        cajasTblFacadeRemote                =  lookupTmsCajasTblFacadeRemote();
        formatosBoletoTblFacadeRemote       =  lookupTmsFormatosBoletoTblFacade();
        variosFacadeRemote                  =  lookupTmsVariosFacadeFacade();
        usuariosTblFacadeRemote             = lookupTmsUsuariosTblFacadeRemote();
        
    }
    

    private TmsActAdicionalesTblFacadeRemote lookupTmsActAdicionalesTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsActAdicionalesTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsActAdicionalesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsActDatosAdicionalesTblFacadeRemote lookupTmsActDatosAdicionalesTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsActDatosAdicionalesTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsActDatosAdicionalesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsDatosAdicionalesTblFacadeRemote lookupTmsDatosAdicionalesTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsDatosAdicionalesTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsDatosAdicionalesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsPagosActAdicionalesTblFacadeRemote lookupTmsPagosActAdicionalesTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsPagosActAdicionalesTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsPagosActAdicionalesTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsOperadoresTblFacadeRemote lookupTmsOperadoresTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsOperadoresTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsOperadoresTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsServiciosTblFacadeRemote lookupTmsServiciosTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsactividadesadicionales.solicitud.TmsServiciosTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsServiciosTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsEmpresasTblFacadeRemote lookupTmsEmpresasTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsactividadesadicionales.solicitud.TmsEmpresasTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsEmpresasTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsAuditoriaTblFacadeRemote lookupTmsAuditoriaTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsAuditoriaTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsAuditoriaTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }    

    private TmsCajasTblFacadeRemote lookupTmsCajasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCajasTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsCajasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCajasImpresorasTblFacadeRemote lookupTmsCajasImpresorasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCajasImpresorasTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsCajasImpresorasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsFormatosBoletoTblFacadeRemote lookupTmsFormatosBoletoTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsFormatosBoletoTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsFormatosBoletoTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsVariosFacadeRemote lookupTmsVariosFacadeFacade() {
        try {
            Context c = new InitialContext();
            return (TmsVariosFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsVariosFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsUsuariosTblFacadeRemote lookupTmsUsuariosTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsUsuariosTblFacadeRemote) c.lookup("tmsactividadesadicionales.solicitud.TmsUsuariosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }    
    
}
