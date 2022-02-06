/*
 * TmsConsultasManagedBean.java
 *
 * Created on 9 de octubre de 2007, 10:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsconsultaocupacion;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tms_ocupacion.entidad.TmsEmpresasTbl;
import tms_ocupacion.solicitud.TmsBaseDatosConfigTblFacadeRemote;
import tms_ocupacion.solicitud.TmsCentralFacadeRemote;
import tms_ocupacion.solicitud.TmsCorridasTblFacadeRemote;
import tms_ocupacion.solicitud.TmsCorridasVentaTblFacadeRemote;
import tms_ocupacion.solicitud.TmsEmpresasTblFacade;
import tms_ocupacion.solicitud.TmsEmpresasTblFacadeRemote;
import tms_ocupacion.solicitud.TmsServiciosTblFacadeRemote;
import tms_ocupacion.solicitud.TmsVariosFacadeRemote;
import tms_ocupacion.solicitud.TmsVariosRemotoFacadeRemote;

/**
 *
 * @author vgonzalez
 */
public class TmsConsultasManagedBean {    
    TmsServiciosTblFacadeRemote             serviciosTblFacadeRemote;
    public TmsVariosFacadeRemote                   variosFacadeRemote;
    public TmsCentralFacadeRemote                   centralFacadeRemote;
    TmsCorridasVentaTblFacadeRemote         corridasVentaTblFacadeRemote;
    TmsVariosRemotoFacadeRemote             variosRemotoFacadeRemote;
    TmsEmpresasTblFacadeRemote              empresasTblFacadeRemote;
    TmsBaseDatosConfigTblFacadeRemote       baseDatosConfigTblFacadeRemote;
/*    TmsCorridasTblFacadeRemote              corridasTblFacadeRemote;
    //TmsEstadosTarjetaViajeTblFacadeRemote   estadosTarjetaViajeTblFacadeRemote;
    TmsTarjetasViajeTblFacadeRemote         tarjetasViajeTblFacadeRemote;
    TmsEstadosCorridaTblFacadeRemote        estadosCorridaTblFacadeRemote;
    TmsAutobusesTblFacadeRemote             autobusesTblFacadeRemote;
    TmsOperadoresTblFacadeRemote            operadoresTblFacadeRemote;
    TmsAccionesTblFacadeRemote              accionesTblFacadeRemote;
    TmsEstadosTblFacadeRemote               estadosTblFacadeRemote;
    */
    
    /**
     * Creates a new instance of TmsConsultasManagedBean
     */
    public TmsConsultasManagedBean() {
        serviciosTblFacadeRemote                =   lookupTmsServiciosTblFacadeRemote();
        variosFacadeRemote                      =   lookupTmsVariosFacadeRemote();
        centralFacadeRemote                     =   lookupTmsCentralFacadeRemote();
        corridasVentaTblFacadeRemote            =   lookupTmsCorridasVentaTblFacadeRemote();
        variosRemotoFacadeRemote                =   lookupTmsVariosRemotoFacadeRemote();
        empresasTblFacadeRemote                 =   lookupTmsEmpresasTblFacadeRemote();
        baseDatosConfigTblFacadeRemote          =   lookupTmsBaseDatosConfigTblFacadeRemote();
/*       corridasTblFacadeRemote                 =   lookupTmsCorridasTblFacadeRemote();
//        estadosTarjetaViajeTblFacadeRemote      =   lookupTmsEstadosTarjetaViajeTblFacadeRemote();
        tarjetasViajeTblFacadeRemote            =   lookupTmsTarjetasViajeTblFacadeRemote();
        estadosCorridaTblFacadeRemote           =   lookupTmsEstadosCorridaTblFacadeRemote();
        autobusesTblFacadeRemote                =   lookupTmsAutobusesTblFacadeRemote();
        operadoresTblFacadeRemote               =   lookupTmsOperadoresTblFacadeRemote();
        accionesTblFacadeRemote                 =   lookupTmsAccionesTblFacade();
        estadosTblFacadeRemote                  =   lookupTmsEstadosTblFacadeRemote();
        */
    }

    private TmsServiciosTblFacadeRemote lookupTmsServiciosTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsServiciosTblFacadeRemote) c.lookup("tms_ocupacion.solicitud.TmsServiciosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsVariosFacadeRemote lookupTmsVariosFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsVariosFacadeRemote) c.lookup("tms_ocupacion.solicitud.TmsVariosFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCentralFacadeRemote lookupTmsCentralFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCentralFacadeRemote) c.lookup("tms_ocupacion.solicitud.TmsCentralFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }    
    
    
    private TmsCorridasVentaTblFacadeRemote lookupTmsCorridasVentaTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCorridasVentaTblFacadeRemote) c.lookup("tms_ocupacion.solicitud.TmsCorridasVentaTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCorridasTblFacadeRemote lookupTmsCorridasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCorridasTblFacadeRemote) c.lookup("tms_ocupacion.solicitud.TmsCorridasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
/*
    private TmsEstadosTarjetaViajeTblFacadeRemote lookupTmsEstadosTarjetaViajeTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsEstadosTarjetaViajeTblFacadeRemote) c.lookup("tms_ocupacion.solicitud.TmsEstadosTarjetaViajeTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
*/


    private TmsBaseDatosConfigTblFacadeRemote lookupTmsBaseDatosConfigTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tms_ocupacion.solicitud.TmsBaseDatosConfigTblFacadeRemote) c.lookup("tms_ocupacion.solicitud.TmsBaseDatosConfigTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsVariosRemotoFacadeRemote lookupTmsVariosRemotoFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tms_ocupacion.solicitud.TmsVariosRemotoFacadeRemote) c.lookup("tms_ocupacion.solicitud.TmsVariosRemotoFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsEmpresasTblFacadeRemote lookupTmsEmpresasTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tms_ocupacion.solicitud.TmsEmpresasTblFacadeRemote) c.lookup("tms_ocupacion.solicitud.TmsEmpresasTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }    
}
